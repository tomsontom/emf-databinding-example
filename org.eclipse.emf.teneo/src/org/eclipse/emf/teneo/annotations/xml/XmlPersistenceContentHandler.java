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
 * $Id: XmlPersistenceContentHandler.java,v 1.7 2008/06/02 07:15:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.xml;

import java.io.InputStream;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerAware;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX ContentHandler for processing XML persistence mapping. Used internally by
 * {@link XmlPersistenceMapper}.
 */
public class XmlPersistenceContentHandler extends DefaultHandler implements ExtensionPoint, ExtensionManagerAware {

	// Parse states

	// Document root.
	private static final int ROOT = 0;

	// <persistence-mapping>
	private static final int PERSISTENCE_MAPPING = 1;

	// <epackage>
	private static final int EPACKAGE = 2;

	// Annotation element for an <epackage>.
	private static final int EPACKAGE_ANNOTATION = 3;

	// <eclass>
	private static final int ECLASS = 4;

	// Annotation element for an <eclass>.
	private static final int ECLASS_ANNOTATION = 5;

	// <eattribute>, <ereference> or <property>.
	private static final int ESTRUCTURALFEATURE = 6;

	// Annotation element for an <eattribute>, <ereference> or <property>.
	private static final int ESTRUCTURALFEATURE_ANNOTATION = 7;

	// Annotation element inside another annotation.
	private static final int NESTED_ANNOTATION = 8;

	// Value for an annotation element.
	private static final int ANNOTATION_ATTRIBUTE = 9;

	// <eclass>
	private static final int EDATATYPE = 10;

	// Annotation element for an <eclass>.
	private static final int EDATATYPE_ANNOTATION = 11;

	// The pattern to split the XML element names against.
	private static Pattern XML_NAME_PATTERN = Pattern.compile("-");

	private static String convertXmlNameToEStructuralFeatureName(String xmlName) {
		final String[] elementNameParts = XML_NAME_PATTERN.split(xmlName);
		final StringBuffer featureName = new StringBuffer();
		for (int i = 0; i < elementNameParts.length; i++) {
			String part = elementNameParts[i];
			if (i > 0) {
				part = part.substring(0, 1).toUpperCase() + part.substring(1);
			}
			featureName.append(part);
		}
		return featureName.toString();
	}

	// The PAnnotatedModel that will be populated.
	private PAnnotatedModel pAnnotatedModel;

	// The PAnnotatedEPackage to which the XML annotations will be applied.
	private PAnnotatedEPackage pAnnotatedEPackage;

	// The current PAnnotatedEClass.
	private PAnnotatedEClass pAnnotatedEClass;

	// The current PAnnotatedEDataType.
	private PAnnotatedEDataType pAnnotatedEDataType;

	// The current PAnnotatedEStructuralFeature of pAnnotatedEClass.
	private PAnnotatedEStructuralFeature pAnnotatedEStructuralFeature;

	// Stack of PAnnotations.
	private Stack<PAnnotation> pAnnotations = new Stack<PAnnotation>();

	// The current EAttribute of the current pAnnotation. Used only for EDataTypes.
	private EAttribute pAnnotationEAttribute;

	// Stack of parse states.
	private Stack<Integer> parseStates = new Stack<Integer>();

	// prefix for extra efeature parsing
	private String prefix;

	// ExtensionManager
	private ExtensionManager extensionManager;

	/** The xml element to structural feature mapper */
	private XmlElementToEStructuralFeatureMapper xmlElementToEStructuralFeatureMapper;

	public XmlPersistenceContentHandler() {
		parseStates.push(ROOT);
	}

	/** Set the schema */
	public void setSchema(InputStream schema) {
		xmlElementToEStructuralFeatureMapper =
				getExtensionManager().getExtension(XmlElementToEStructuralFeatureMapper.class);
		xmlElementToEStructuralFeatureMapper.parseSchema(schema);
	}

	/**
	 * Returns the current parse state.
	 */
	protected int getParseState() {
		assert (parseStates.size() >= 1) : "Parse state stack must contain at least one element.";
		return (parseStates.peek()).intValue();
	}

	protected PAnnotation getPAnnotation() {
		return pAnnotations.peek();
	}

	/**
	 * Applies an annotation on an EObject based on the given XML element name.
	 * 
	 */
	@SuppressWarnings("unchecked")
	protected void applyAnnotation(EObject pAnnotatedEModelElement, String elementName, Attributes attributes)
			throws SAXException {
		final EStructuralFeature annotationEStructuralFeature =
				getEStructuralFeature(pAnnotatedEModelElement, elementName);
		if (annotationEStructuralFeature == null) {
			throw new SAXException("Cannot handle element <" + elementName + ">");
		}

		final PAnnotation pAnnotation =
				(PAnnotation) EcoreUtil.create((EClass) annotationEStructuralFeature.getEType());
		pAnnotations.push(pAnnotation);

		if (annotationEStructuralFeature.isMany()) {
			((List) pAnnotatedEModelElement.eGet(annotationEStructuralFeature)).add(pAnnotation);
		} else {
			pAnnotatedEModelElement.eSet(annotationEStructuralFeature, pAnnotation);
		}

		// Apply attributes to pAnnotation
		for (int i = 0, n = attributes.getLength(); i < n; i++) {
			final EAttribute eAttribute = (EAttribute) getEStructuralFeature(pAnnotation, attributes.getLocalName(i));
			final EDataType eDataType = eAttribute.getEAttributeType();
			final Object valueObject =
					eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, attributes.getValue(i));
			if (eAttribute.isMany()) {
				((List) pAnnotation.eGet(eAttribute)).add(valueObject);
			} else {
				pAnnotation.eSet(eAttribute, valueObject);
			}
		}

	}

	/**
	 * Returns an estructuralfeature on the basis of the name, mainly does conversion of the xmlName
	 * to the efeaturename, the prefix returned from getPrefix is also used. todo: move prefix
	 * handling to XmlElementToEStructuralFeatureMapper.
	 */
	protected EStructuralFeature getEStructuralFeature(EObject pAnnotatedEModelElement, String xmlName) {
		String annotationEStructuralFeatureName = convertXmlNameToEStructuralFeatureName(xmlName);
		EStructuralFeature annotationEStructuralFeature =
				pAnnotatedEModelElement.eClass().getEStructuralFeature(annotationEStructuralFeatureName);
		if (annotationEStructuralFeature == null) {
			annotationEStructuralFeatureName = xmlElementToEStructuralFeatureMapper.getEStructuralFeatureName(xmlName);
			annotationEStructuralFeature =
					pAnnotatedEModelElement.eClass().getEStructuralFeature(annotationEStructuralFeatureName);
		}
		// if still null then try with the prefix
		if (annotationEStructuralFeature == null) {
			// note if a prefix is added then the first character of the first part has to be
			// upper-cased
			String name = convertXmlNameToEStructuralFeatureName(xmlName);
			annotationEStructuralFeatureName = prefix + name.substring(0, 1).toUpperCase() + name.substring(1);
			;
			annotationEStructuralFeature =
					pAnnotatedEModelElement.eClass().getEStructuralFeature(annotationEStructuralFeatureName);
		}
		return annotationEStructuralFeature;
	}

	// --------------------------------------------------------------------
	// Implementation of ContentHandler interface.
	// --------------------------------------------------------------------
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// Change parse state.
		int newParseState;
		switch (getParseState()) {
			case ROOT:
				newParseState = PERSISTENCE_MAPPING;
				break;
			case PERSISTENCE_MAPPING:
				assert (localName.equals("epackage"));
				newParseState = EPACKAGE;
				break;
			case EPACKAGE:
				if (localName.equals("eclass")) {
					newParseState = ECLASS;
				} else if (localName.equals("edatatype")) {
					newParseState = EDATATYPE;
				} else {
					newParseState = EPACKAGE_ANNOTATION;
				}
				break;
			case ECLASS:
				if (localName.equals("eattribute") || localName.equals("ereference") || localName.equals("property")) {
					newParseState = ESTRUCTURALFEATURE;
				} else {
					newParseState = ECLASS_ANNOTATION;
				}
				break;
			case ESTRUCTURALFEATURE:
				newParseState = ESTRUCTURALFEATURE_ANNOTATION;
				break;
			case EDATATYPE:
				newParseState = EDATATYPE_ANNOTATION;
				break;
			case EPACKAGE_ANNOTATION:
			case ECLASS_ANNOTATION:
			case ESTRUCTURALFEATURE_ANNOTATION:
			case NESTED_ANNOTATION: {
				final EStructuralFeature annotationEStructuralFeature =
						getEStructuralFeature(getPAnnotation(), localName);
				if (annotationEStructuralFeature.getEType() instanceof EClass) {
					newParseState = NESTED_ANNOTATION;
				} else {
					newParseState = ANNOTATION_ATTRIBUTE;
				}
				break;
			}
			default:
				throw new ParseXMLAnnotationsException("Invalid parse state encountered.");
		}
		parseStates.push(new Integer(newParseState));

		// Act upon the new parse state.
		switch (getParseState()) {
			case EPACKAGE: {
				final String namespaceUri = attributes.getValue("namespace-uri");
				final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(namespaceUri);
				if (ePackage == null) {
					throw new SAXException("Could not find EPackage \"" + namespaceUri + "\".");
				}
				pAnnotatedEPackage = pAnnotatedModel.getPAnnotated(ePackage);
				if (pAnnotatedEPackage == null) {
					throw new SAXException("Could not find PAnnotatedEPackage \"" + namespaceUri + "\".");
				}
				break;
			}
			case ECLASS: {
				final String eClassName = attributes.getValue("name");
				final EClassifier eClassifier = pAnnotatedEPackage.getModelEPackage().getEClassifier(eClassName);
				if (eClassifier == null) {
					throw new SAXException("Could not find EClass \"" + eClassName + "\"");
				}
				if (!(eClassifier instanceof EClass)) {
					throw new SAXException("EClassifier \"" + eClassName + "\" is not an EClass.");
				}
				pAnnotatedEClass = pAnnotatedModel.getPAnnotated((EClass) eClassifier);
				break;
			}
			case EDATATYPE: {
				final String eDataTypeName = attributes.getValue("name");
				final EDataType et = (EDataType) pAnnotatedEPackage.getModelEPackage().getEClassifier(eDataTypeName);
				if (et == null) {
					throw new SAXException("Could not find EClass \"" + eDataTypeName + "\"");
				}
				pAnnotatedEDataType = pAnnotatedModel.getPAnnotated(et);
				break;
			}
			case ESTRUCTURALFEATURE: {
				final String eStructuralFeatureName = attributes.getValue("name");
				final EClass eClass = pAnnotatedEClass.getModelEClass();
				final EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(eStructuralFeatureName);
				if (eStructuralFeature == null) {
					throw new SAXException("Could not find EStructuralFeature \"" + eStructuralFeatureName +
							"\" in EClass \"" + eClass.getName() + "\".");
				} else if (localName.equals("eattribute") && !(eStructuralFeature instanceof EAttribute)) {
					throw new SAXException("EStructuralFeature \"" + eStructuralFeatureName + "\" in EClass \"" +
							eClass.getName() + "\" is not an EAttribute.");
				} else if (localName.equals("ereference") && !(eStructuralFeature instanceof EReference)) {
					throw new SAXException("EStructuralFeature \"" + eStructuralFeatureName + "\" in EClass \"" +
							eClass.getName() + "\" is not an EReference.");
				}
				pAnnotatedEStructuralFeature = pAnnotatedModel.getPAnnotated(eStructuralFeature);
				break;
			}
			case EPACKAGE_ANNOTATION:
				applyAnnotation(pAnnotatedEPackage, localName, attributes);
				break;
			case ECLASS_ANNOTATION:
				applyAnnotation(pAnnotatedEClass, localName, attributes);
				break;
			case ESTRUCTURALFEATURE_ANNOTATION:
				applyAnnotation(pAnnotatedEStructuralFeature, localName, attributes);
				break;
			case EDATATYPE_ANNOTATION:
				applyAnnotation(pAnnotatedEDataType, localName, attributes);
				break;
			case NESTED_ANNOTATION: {
				// final String eStructuralFeatureName =
				// convertElementNameToEStructuralFeatureName(localName);
				// final EReference annotationEStructuralFeature = (EReference)
				// getPAnnotation().eClass()
				// .getEStructuralFeature(eStructuralFeatureName);
				applyAnnotation(getPAnnotation(), localName, attributes);
				break;
			}
			case ANNOTATION_ATTRIBUTE: {
				final String eStructuralFeatureName = convertXmlNameToEStructuralFeatureName(localName);
				pAnnotationEAttribute =
						(EAttribute) getPAnnotation().eClass().getEStructuralFeature(eStructuralFeatureName);
				break;
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void characters(char[] ch, int start, int length) throws SAXException {
		final String value = new String(ch, start, length).trim();
		if (value.length() == 0) {
			return;
		}
		switch (getParseState()) {
			case EPACKAGE_ANNOTATION:
			case ECLASS_ANNOTATION:
			case ESTRUCTURALFEATURE_ANNOTATION:
			case NESTED_ANNOTATION: {
				// If we get here, we are dealing with a PAnnotation that has only one EAttribute.
				// I.e. there are no
				// child elements. Example: <discriminator-value>MyObject</discriminator-value>
				final PAnnotation pAnnotation = getPAnnotation();
				assert (pAnnotation.eClass().getEStructuralFeatures().size() == 1);
				final EAttribute eAttribute = (EAttribute) pAnnotation.eClass().getEStructuralFeatures().get(0);
				final EDataType eAttributeType = eAttribute.getEAttributeType();
				final Object valueObject =
						eAttributeType.getEPackage().getEFactoryInstance().createFromString(eAttributeType, value);
				pAnnotation.eSet(eAttribute, valueObject);
				break;
			}
			case ANNOTATION_ATTRIBUTE: {
				final EDataType eDataType = pAnnotationEAttribute.getEAttributeType();
				final Object valueObject =
						eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, value);
				if (pAnnotationEAttribute.isMany()) {
					((List) getPAnnotation().eGet(pAnnotationEAttribute)).add(valueObject);
				} else {
					getPAnnotation().eSet(pAnnotationEAttribute, valueObject);
				}
				break;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (getParseState()) {
			case EPACKAGE_ANNOTATION:
			case ECLASS_ANNOTATION:
			case ESTRUCTURALFEATURE_ANNOTATION:
			case NESTED_ANNOTATION:
				pAnnotations.pop();
				break;
			case ANNOTATION_ATTRIBUTE:
				pAnnotationEAttribute = null;
				break;
		}
		parseStates.pop();
	}

	// --------------------------------------------------------------------
	// Implementation of ErrorHandler interface.
	// --------------------------------------------------------------------

	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		throw e;
	}

	/**
	 * @return the extensionManager
	 */
	public ExtensionManager getExtensionManager() {
		return extensionManager;
	}

	/**
	 * @param extensionManager
	 *            the extensionManager to set
	 */
	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}

	/**
	 * @return the pAnnotatedModel
	 */
	public PAnnotatedModel getPAnnotatedModel() {
		return pAnnotatedModel;
	}

	/**
	 * @param annotatedModel
	 *            the pAnnotatedModel to set
	 */
	public void setPAnnotatedModel(PAnnotatedModel annotatedModel) {
		pAnnotatedModel = annotatedModel;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}