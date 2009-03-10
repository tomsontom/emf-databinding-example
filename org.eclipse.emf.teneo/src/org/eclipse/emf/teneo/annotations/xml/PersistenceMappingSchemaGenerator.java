/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Martin Taal
 * </copyright>
 *
 * $Id: PersistenceMappingSchemaGenerator.java,v 1.4 2008/06/29 14:49:50 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.xml;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.StoreAnnotationsException;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.simpledom.Attribute;
import org.eclipse.emf.teneo.simpledom.Document;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Parses the pamodel and pannotation model to generate a xsd.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */

public class PersistenceMappingSchemaGenerator {

	/** The source name used to set to which estructural feature a tag belongs */
	public static final String ESTRUCTURAL_FEATURE_SOURCE_NAME = "teneo/internal/EStructuralFeatureName";

	/** Used to set efeatures in the ecore model to be ignored */
	public static final String PERSISTENCE_MAPPING_SOURCE = "teneo/internal/PersistenceMapping";

	/** Is set on efeatures to denote that they are not supported */
	private static final String UNSUPPORTED_SOURCE = "teneo/internal/Unsupported";

	public static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";

	/** The main method, ugly but effective */
	public static void main(String[] args) {
		final PersistenceMappingSchemaGenerator pmsg = new PersistenceMappingSchemaGenerator();
		pmsg.setAnnotationEPackages(new EPackage[] { PannotationPackage.eINSTANCE });
		pmsg.setModelEPackage(PamodelPackage.eINSTANCE);
		try {
			final FileWriter fw = new FileWriter("/home/mtaal/mytmp/persistence-mapping.xsd");
			fw.write(pmsg.generate());
			fw.close();
		} catch (Exception e) {
			throw new StoreAnnotationsException("Exception while generating mapping.xsd", e);
		}
	}

	/** The pamodel */
	private EPackage modelEPackage;

	/** And the annotations packages */
	private EPackage[] annotationEPackages;

	/** Mapping from ecore simple types to schema simple types */
	private final Map<String, String> schemaTypeNamesByAnnotationType = new HashMap<String, String>();

	/** Target name space */
	private String nameSpace = "http://www.eclipse.org/emft/teneo";

	/** Initialize some main things */
	private void initialize() {
		schemaTypeNamesByAnnotationType.put("EBoolean", "xsd:boolean");
		schemaTypeNamesByAnnotationType.put("EInt", "xsd:int");
		schemaTypeNamesByAnnotationType.put("ELong", "xsd:long");
		schemaTypeNamesByAnnotationType.put("EString", "xsd:string");
	}

	/** Generate into a string */
	public String generate() {

		initialize();

		final Document doc = new Document();

		// The root Element
		final Element root =
				new Element("xsd:schema").addAttribute("targetNamespace", nameSpace).addAttribute("elementFormDefault",
					"qualified").addAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema").addAttribute("xmlns",
					nameSpace);

		root.addElement("xsd:element").addAttribute("name", "persistence-mapping").addAttribute("type",
			"PersistenceMapping");
		root.addElement("xsd:complexType").addAttribute("name", "PersistenceMapping").addElement("xsd:sequence")
			.addAttribute("minOccurs", "1").addAttribute("maxOccurs", "unbounded").addElement("xsd:element")
			.addAttribute("name", "epackage").addAttribute("type", "EPackage");

		// first determine which types have only one string field, these are handled
		// slightly different because this makes the xml easier
		for (EPackage annotationEPackage : annotationEPackages) {
			final List<EClassifier> eclassifiers = new ArrayList<EClassifier>(annotationEPackage.getEClassifiers());
			for (EClassifier eClassifier : eclassifiers) {
				String schemaTypeName = eClassifier.getName();
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;

					// Annotation types with a single feature are converted to simple type
					// references in the schema.
					if (oneMappableFeature(eClass)) {
						final EStructuralFeature eStructuralFeature = eClass.getEStructuralFeatures().get(0);
						final EClassifier eType = eStructuralFeature.getEType();
						schemaTypeName = schemaTypeNamesByAnnotationType.get(eType.getName());
						if (schemaTypeName == null) {
							schemaTypeName = eType.getName();
						}
						schemaTypeNamesByAnnotationType.put(eClassifier.getName(), schemaTypeName);
						continue;
					}
				}
				schemaTypeNamesByAnnotationType.put(eClassifier.getName(), schemaTypeName);
			}
		}

		// process the annotations first to get the correct typing
		final List<Element> annotationList = new ArrayList<Element>();
		for (EPackage annotationEPackage : annotationEPackages) {
			annotationList.addAll(processAnnotationEPackage(annotationEPackage));
		}

		root.addElement(createEPackageElement());
		root.addElement(createEClassElement());
		root.addElement(createEAttributeElement());
		root.addElement(createEReferenceElement());
		root.addElement(createPropertyElement());
		root.addElement(createEDataTypeElement());
		root.getChildren().addAll(annotationList);

		doc.setRoot(root);
		return doc.emitXML();
	}

	/** process annotation packages */
	private List<Element> processAnnotationEPackage(EPackage epackage) {
		final ArrayList<Element> elemList = new ArrayList<Element>();
		final List<EClassifier> eclassifiers = new ArrayList<EClassifier>(epackage.getEClassifiers());
		Collections.sort(eclassifiers, new ENamedElementComparator());

		for (EClassifier eClassifier : eclassifiers) {
			if (isIgnorable(eClassifier) || isUnsupported(eClassifier)) {
				continue;
			}

			String schemaTypeName = eClassifier.getName();
			if (eClassifier instanceof EClass) {
				final EClass eClass = (EClass) eClassifier;
				if (eClass.isAbstract()) {
					continue;
				}

				final List<EStructuralFeature> eStructuralFeatures = eClass.getEAllStructuralFeatures();
				if (eStructuralFeatures.isEmpty()) {
					continue;
				}

				// Annotation types with a single feature are converted to simple type references in
				// the schema.
				if (oneMappableFeature(eClass)) {
					/*
					 * final EStructuralFeature eStructuralFeature = (EStructuralFeature)
					 * eClass.getEStructuralFeatures().get(0); final EClassifier eType =
					 * eStructuralFeature.getEType(); schemaTypeName = (String)
					 * schemaTypeNamesByAnnotationType.get(eType.getName()); if (schemaTypeName ==
					 * null) { schemaTypeName = eType.getName(); }
					 * schemaTypeNamesByAnnotationType.put(eClassifier.getName(), schemaTypeName);
					 */
					continue;
				}

				final Element complexTypeElement = createSchemaComplexType(eClass.getName());
				elemList.add(complexTypeElement);

				final Element choiceElement = complexTypeElement.addElement("xsd:choice");
				addZeroUnbounded(choiceElement);
				processStructuralFeatures(choiceElement, eStructuralFeatures);
				if (choiceElement.getChildren().size() == 0) {
					complexTypeElement.getChildren().remove(choiceElement);
				}
			} else if (eClassifier instanceof EEnum) {
				elemList.add(processEnum((EEnum) eClassifier));
			} else {
				throw new RuntimeException("Unexpected EClassifier: " + eClassifier.eClass().getName());
			}

			schemaTypeNamesByAnnotationType.put(eClassifier.getName(), schemaTypeName);
		}
		return elemList;
	}

	/** If more than one mappable structuralfeature */
	private boolean oneMappableFeature(EClass eclass) {
		int cnt = 0;
		for (EStructuralFeature ef : eclass.getEStructuralFeatures()) {
			if (!isIgnorable(ef) && !isUnsupported(ef)) {
				cnt++;
			}
		}
		return cnt == 1;
	}

	/** Process an enum */
	private Element processEnum(EEnum eEnum) {
		final Element simpleTypeElement = createSchemaSimpleType(eEnum.getName(), null);
		final Element restrictionElement = new Element("xsd:restriction");
		restrictionElement.addAttribute(new Attribute("base", "xsd:token"));
		simpleTypeElement.addElement(restrictionElement);
		final List<EEnumLiteral> literals = eEnum.getELiterals();
		for (EEnumLiteral literal : literals) {
			final Element enumerationElement = new Element("xsd:enumeration");
			restrictionElement.addElement(enumerationElement);
			enumerationElement.addAttribute(new Attribute("value", literal.getLiteral()));
		}
		return simpleTypeElement;
	}

	/** Do the epackage */
	private Element createEPackageElement() {
		final Element epackElement = new Element("xsd:complexType").addAttribute("name", "EPackage");
		final Element choiceElement = epackElement.addElement("xsd:choice");
		addZeroUnbounded(choiceElement);
		processStructuralFeatures(choiceElement, getPAnnotatedEPackage().getEAllStructuralFeatures());
		choiceElement.addElement("xsd:element").addAttribute("name", "eclass").addAttribute("type", "EClass");
		choiceElement.addElement("xsd:element").addAttribute("name", "edatatype").addAttribute("type", "EDataType");
		// add the namespace-uri attribute
		epackElement.addElement("xsd:attribute").addAttribute("name", "namespace-uri").addAttribute("type",
			"xsd:anyURI").addAttribute("use", "required");
		return epackElement;
	}

	/** Do the eClass */
	private Element createEClassElement() {
		final Element eclassElement = new Element("xsd:complexType").addAttribute("name", "EClass");
		final Element choiceElement = eclassElement.addElement("xsd:choice");
		addZeroUnbounded(choiceElement);
		processStructuralFeatures(choiceElement, getPAnnotatedEClass().getEAllStructuralFeatures());

		choiceElement.addElement("xsd:element").addAttribute("name", "eattribute").addAttribute("type", "EAttribute");
		choiceElement.addElement("xsd:element").addAttribute("name", "ereference").addAttribute("type", "EReference");
		choiceElement.addElement("xsd:element").addAttribute("name", "property").addAttribute("type", "Property");
		choiceElement.addElement("xsd:element").addAttribute("name", "edatatype").addAttribute("type", "EDataType");

		eclassElement.addElement("xsd:attribute").addAttribute("name", "name").addAttribute("type", "xsd:token")
			.addAttribute("use", "required");
		return eclassElement;
	}

	/** Do the eReference element */
	private Element createEReferenceElement() {
		final Element erefElement = new Element("xsd:complexType").addAttribute("name", "EReference");
		final Element choiceElement = erefElement.addElement("xsd:choice");
		addZeroUnbounded(choiceElement);
		processStructuralFeatures(choiceElement, getPAnnotatedEReference().getEAllStructuralFeatures());
		erefElement.addElement("xsd:attribute").addAttribute("name", "name").addAttribute("type", "xsd:token")
			.addAttribute("use", "required");
		return erefElement;
	}

	/** Do the eAttribute */
	private Element createEAttributeElement() {
		final Element eattrElement = new Element("xsd:complexType").addAttribute("name", "EAttribute");
		final Element choiceElement = eattrElement.addElement("xsd:choice");
		addZeroUnbounded(choiceElement);
		processStructuralFeatures(choiceElement, getPAnnotatedEAttribute().getEAllStructuralFeatures());
		eattrElement.addElement("xsd:attribute").addAttribute("name", "name").addAttribute("type", "xsd:token")
			.addAttribute("use", "required");
		return eattrElement;
	}

	/** Do the eDataType */
	private Element createEDataTypeElement() {
		final Element eattrElement = new Element("xsd:complexType").addAttribute("name", "EDataType");
		final Element choiceElement = eattrElement.addElement("xsd:choice");
		addZeroUnbounded(choiceElement);
		processStructuralFeatures(choiceElement, getPAnnotatedEDataType().getEAllStructuralFeatures());
		eattrElement.addElement("xsd:attribute").addAttribute("name", "name").addAttribute("type", "xsd:token")
			.addAttribute("use", "required");
		return eattrElement;
	}

	/** Do the property (comb. of ereference and eattribute */
	private Element createPropertyElement() {
		final Element propertyElement = new Element("xsd:complexType").addAttribute("name", "Property");
		final Element choiceElement = propertyElement.addElement("xsd:choice");
		addZeroUnbounded(choiceElement);
		final List<EStructuralFeature> features =
				new ArrayList<EStructuralFeature>(getPAnnotatedEAttribute().getEAllStructuralFeatures());
		features.removeAll(getPAnnotatedEReference().getEAllStructuralFeatures());
		features.addAll(getPAnnotatedEReference().getEAllStructuralFeatures());

		processStructuralFeatures(choiceElement, features);
		propertyElement.addElement("xsd:attribute").addAttribute("name", "name").addAttribute("type", "xsd:token")
			.addAttribute("use", "required");
		return propertyElement;
	}

	/** Walk through a pamodel type and add references to each type to the passed element */
	private void processStructuralFeatures(Element mainElement, List<EStructuralFeature> eStructuralFeatures) {
		final List<EStructuralFeature> eFeatures = new ArrayList<EStructuralFeature>(eStructuralFeatures);
		Collections.sort(eFeatures, new ENamedElementComparator());
		for (EStructuralFeature ef : eFeatures) {
			processStructuralFeature(mainElement, ef);
		}
	}

	/**
	 * Processes the EStructuralFeatures of a Pamodel EClass.
	 */
	private void processStructuralFeature(Element parentElement, EStructuralFeature eStructuralFeature) {
		final EClassifier eType = eStructuralFeature.getEType();

		if (isIgnorable(eStructuralFeature) || isIgnorable(eType) || isUnsupported(eType)) {
			return;
		}

		final int minOccurs = (eStructuralFeature.isRequired() ? 1 : 0);

		// Determine the element name.
		final EAnnotation eAnnotation = eStructuralFeature.getEAnnotation(PERSISTENCE_MAPPING_SOURCE);
		String elementName = null;
		if (eAnnotation != null) {
			elementName = eAnnotation.getDetails().get("elementName");
		}
		if (elementName == null) {
			// No explicit XML element name specified, so derive from the name instead.
			elementName = eStructuralFeature.getName();
			if (eStructuralFeature.isMany() && elementName.endsWith("s")) {
				elementName = elementName.substring(0, elementName.length() - 1);
			}
		}

		// check for double occurences, can occur when doing the property tag
		// which combines ereference and eattribute features
		final String xmlName = convertToXmlName(elementName);
		for (Element otherElem : parentElement.getChildren()) {
			String name;
			if ((name = otherElem.getAttributeValue("name")) != null && name.compareTo(xmlName) == 0) {
				return;
			}
		}
		if (parentElement.element(convertToXmlName(elementName)) != null) {
			return;
		}

		String typeName = schemaTypeNamesByAnnotationType.get(eType.getName());
		if (typeName == null) {
			typeName = eType.getName();
		}

		if (eStructuralFeature instanceof EReference) {
			// EReferences are represented by child elements.
			final Element element = createSchemaElement(elementName, typeName, eStructuralFeature.getName());
			if (parentElement.getName().compareTo("xsd:choice") != 0) {
				element.addAttribute(new Attribute("minOccurs", String.valueOf(minOccurs)));
				if (eStructuralFeature.isMany()) {
					element.addAttribute(new Attribute("maxOccurs", "unbounded"));
				}
			}
			parentElement.addElement(element);
		} else {
			// EAttributes are represented by attributes and optional child elements in case of many
			// multiplicity.
			final Element attributeElement =
					createSchemaAttribute(eStructuralFeature.getName(), typeName, eStructuralFeature.getName());
			attributeElement.addAttribute(new Attribute("use", (minOccurs == 0 ? "optional" : "required")));
			parentElement.getParent().addElement(attributeElement);
			if (eStructuralFeature.isMany()) {
				final Element element =
						createSchemaElement(eStructuralFeature.getName(), typeName, eStructuralFeature.getName());
				parentElement.addElement(element);
				if (parentElement.getName().compareTo("xsd:choice") != 0) {
					element.addAttribute(new Attribute("minOccurs", "0"));
					element.addAttribute(new Attribute("maxOccurs", "unbounded"));
				}
			}
		}
	}

	/** Return the PAnnotatedEClass */
	protected EClass getPAnnotatedEPackage() {
		return (EClass) modelEPackage.getEClassifier("PAnnotatedEPackage");
	}

	/** Return the PAnnotatedEClass */
	protected EClass getPAnnotatedEClass() {
		return (EClass) modelEPackage.getEClassifier("PAnnotatedEClass");
	}

	/** Return the PAnnotatedEReference */
	protected EClass getPAnnotatedEReference() {
		return (EClass) modelEPackage.getEClassifier("PAnnotatedEReference");
	}

	/** Return the PAnnotatedEAttribute */
	protected EClass getPAnnotatedEAttribute() {
		return (EClass) modelEPackage.getEClassifier("PAnnotatedEAttribute");
	}

	/** Return the PAnnotatedEDataType */
	protected EClass getPAnnotatedEDataType() {
		return (EClass) modelEPackage.getEClassifier("PAnnotatedEDataType");
	}

	/**
	 * Tests whether an EModelElement can be ignored for persistence mapping.
	 * 
	 */
	protected static boolean isIgnorable(EModelElement eModelElement) {
		final EAnnotation eAnnotation = eModelElement.getEAnnotation(PERSISTENCE_MAPPING_SOURCE);
		boolean ignore = false;
		if (eAnnotation != null) {
			ignore = Boolean.valueOf(eAnnotation.getDetails().get("ignore")).booleanValue();
		}
		return ignore;
	}

	/**
	 * Creates an XML Schema element. (&lt;xsd:element&gt;)
	 */
	private Element createSchemaElement(String name, String type, String eStructuralFeatureName) {
		final Element element = new Element("xsd:element");
		element.addAttribute(new Attribute("name", convertToXmlName(name)));
		element.addAttribute(new Attribute("type", type));
		if (!name.equals(eStructuralFeatureName)) {
			addAppInfoElement(element, eStructuralFeatureName);
		}
		return element;
	}

	/**
	 * Creates an XML Schema complex type. (&lt;xsd:complexType&gt;)
	 */
	private Element createSchemaSimpleType(String name, String type) {
		final Element element = new Element("xsd:simpleType");
		element.addAttribute(new Attribute("name", name));
		if (type != null) {
			element.addAttribute(new Attribute("type", type));
		}
		return element;
	}

	/**
	 * Creates an XML Schema attribute element. (&lt;xsd:attribute&gt;)
	 */
	private Element createSchemaAttribute(String name, String type, String eStructuralFeatureName) {
		final Element element = new Element("xsd:attribute");
		element.addAttribute(new Attribute("name", convertToXmlName(name)));
		element.addAttribute(new Attribute("type", type));
		if (!name.equals(eStructuralFeatureName)) {
			addAppInfoElement(element, eStructuralFeatureName);
		}
		return element;
	}

	private static void addAppInfoElement(final Element element, String eStructuralFeatureName) {
		final Element annotationElement = new Element("xsd:annotation");
		element.addElement(annotationElement);
		final Element appInfoElement = new Element("xsd:appinfo");
		appInfoElement.addAttribute(new Attribute("source", ESTRUCTURAL_FEATURE_SOURCE_NAME));
		appInfoElement.setText(eStructuralFeatureName);
		annotationElement.addElement(appInfoElement);
	}

	/**
	 * Creates an XML Schema complex type. (&lt;xsd:complexType&gt;)
	 */
	private Element createSchemaComplexType(String name) {
		final Element element = new Element("xsd:complexType");
		element.addAttribute(new Attribute("name", name));
		return element;
	}

	/**
	 * Tests whether an EModelElement is unsupported.
	 * 
	 */
	protected static boolean isUnsupported(EModelElement eModelElement) {
		return (eModelElement.getEAnnotation(UNSUPPORTED_SOURCE) != null);
	}

	/** Add minOccurs and maxOccurs */
	private void addZeroUnbounded(Element elem) {
		addMinMaxOccurs(elem, "0", "unbounded");
	}

	/** Add minOccurs and maxOccurs */
	private void addMinMaxOccurs(Element elem, String min, String max) {
		elem.addAttribute("minOccurs", min).addAttribute("maxOccurs", max);
	}

	/**
	 * Converts mixed-case names to XML names.
	 * <p>
	 * Example: "generatedValue" -> "generated-value".
	 * 
	 * @param name
	 * @return
	 */
	protected String convertToXmlName(String name) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0, n = name.length(); i < n; i++) {
			char ch = name.charAt(i);
			if (Character.isUpperCase(ch)) {
				if (i > 0) {
					sb.append('-');
				}
				ch = Character.toLowerCase(ch);
			}
			sb.append(ch);
		}
		return sb.toString();
	}

	/** EFeature comparator */
	private class ENamedElementComparator implements Comparator<ENamedElement> {
		/** Compare features */
		public int compare(ENamedElement e1, ENamedElement e2) {
			return e1.getName().compareTo(e2.getName());
		}
	}

	/**
	 * @return the annotationEPackages
	 */
	public EPackage[] getAnnotationEPackages() {
		return annotationEPackages;
	}

	/**
	 * @param annotationEPackages
	 *            the annotationEPackages to set
	 */
	public void setAnnotationEPackages(EPackage[] annotationEPackages) {
		this.annotationEPackages = annotationEPackages;
	}

	/**
	 * @return the modelEPackage
	 */
	public EPackage getModelEPackage() {
		return modelEPackage;
	}

	/**
	 * @param modelEPackage
	 *            the modelEPackage to set
	 */
	public void setModelEPackage(EPackage modelEPackage) {
		this.modelEPackage = modelEPackage;
	}

	/**
	 * @return the nameSpace
	 */
	public String getNameSpace() {
		return nameSpace;
	}

	/**
	 * @param nameSpace
	 *            the nameSpace to set
	 */
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
}
