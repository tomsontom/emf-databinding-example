/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal Brian
 * Vetter </copyright> $Id: AbstractMapper.java,v 1.43 2008/12/17 06:13:15 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.EnumType;
import org.eclipse.emf.teneo.annotations.pannotation.Enumerated;
import org.eclipse.emf.teneo.annotations.pannotation.External;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationFactory;
import org.eclipse.emf.teneo.annotations.pannotation.TemporalType;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cache;
import org.eclipse.emf.teneo.hibernate.hbannotation.Filter;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction;
import org.eclipse.emf.teneo.hibernate.hbannotation.Parameter;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.simpledom.Element;
import org.eclipse.emf.teneo.util.EcoreDataTypes;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Base class for all mapping classes. Provides access to the hbmcontext.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public abstract class AbstractMapper {
	/** The list of types which translate to a hibernate types */
	private static final String[] TEMPORAL_TYPE_NAMES;

	/** Initialize TEMPORAL_TYPE_NAMES */
	static {
		TEMPORAL_TYPE_NAMES = new String[TemporalType.VALUES.size()];
		TEMPORAL_TYPE_NAMES[TemporalType.DATE.getValue()] = "date";
		TEMPORAL_TYPE_NAMES[TemporalType.TIME.getValue()] = "time";
		TEMPORAL_TYPE_NAMES[TemporalType.TIMESTAMP.getValue()] = "timestamp";
	}

	/** Returns the correct temporal type for hibernate */
	private static String hbType(TemporalType temporalType) {
		return TEMPORAL_TYPE_NAMES[temporalType != null ? temporalType
				.getValue() : TemporalType.TIMESTAMP.getValue()];
	}

	/** logs it all */
	// private static final Log log = LogFactory.getLog(AbstractMapper.class);
	/** return the opposite of an association */
	protected PAnnotatedEReference getOtherSide(PAnnotatedEReference paReference) {
		// TODO assuming that mappedBy coincide with opposite, check in
		// validation
		if (paReference.getModelEReference().getEOpposite() == null) {
			return null;
		}
		return paReference.getPaModel().getPAnnotated(
				paReference.getModelEReference().getEOpposite());
	}

	/** The mapping context of this mapping action */
	protected MappingContext hbmContext;

	/**
	 * @return The mapping context used by this mapper
	 */
	protected MappingContext getHbmContext() {
		return hbmContext;
	}

	/** Set the hbm context */
	protected void setHbmContext(MappingContext hbmContext) {
		this.hbmContext = hbmContext;
	}

	protected void addAccessor(Element element, String propertyHandler) {
		if (propertyHandler != null && propertyHandler.length() > 0) {
			element.addAttribute("access", hbmContext.getPropertyHandlerName());
		}
	}

	protected void addAccessor(Element element) {
		addAccessor(element, hbmContext.getPropertyHandlerName());
	}

	protected void setType(PAnnotatedEStructuralFeature paFeature,
			Element propElement) {
		if (paFeature instanceof PAnnotatedEAttribute) {
			setType((PAnnotatedEAttribute) paFeature, propElement);
		} else {
			setType((PAnnotatedEReference) paFeature, propElement);
		}
	}

	/** Handles the External annotation */
	protected void setType(PAnnotatedEReference paReference, Element propElement) {
		final External external = paReference.getExternal();
		if (external == null) {
			throw new MappingException(
					"External annotation not set on eReference "
							+ StoreUtil.toString(paReference
									.getModelEReference()));
		}

		final Element typeElement = propElement.addElement("type");
		if (external.getType() == null) { // standard external
			typeElement.addAttribute("name", getHbmContext()
					.getExternalUserType());
		} else {
			typeElement.addAttribute("name", external.getType());
		}
		typeElement.addElement("param").addAttribute("name",
				HbMapperConstants.ECLASS_NAME_META).addText(
				paReference.getModelEReference().getEReferenceType().getName());
		typeElement.addElement("param").addAttribute("name",
				HbMapperConstants.EPACKAGE_PARAM).addText(
				paReference.getModelEReference().getEReferenceType()
						.getEPackage().getNsURI());

	}

	/** Handles the type or typedef annotations */
	protected void setType(PAnnotatedEAttribute paAttribute, Element propElement) {

		// handle the type annotation
		final HbAnnotatedEAttribute hea = (HbAnnotatedEAttribute) paAttribute;
		final EDataType ed = (EDataType) hea.getModelEAttribute().getEType();
		final HbAnnotatedEDataType hed = (HbAnnotatedEDataType) hea
				.getPaModel().getPAnnotated(ed);

		final String name;
		final List<Parameter> params;
		if (hea.getHbType() != null) {
			name = hea.getHbType().getType();
			params = hea.getHbType().getParameters();
		} else if (hed != null && hed.getHbTypeDef() != null) {
			name = hed.getHbTypeDef().getName();
			params = null;
		} else {
			name = null;
			params = null;
		}
		if (name != null) {
			if (params == null || params.isEmpty()) {
				// simple
				propElement.addAttribute("type", name);
			} else {
				final Element typeElement = propElement.addElement("type")
						.addAttribute("name", name);
				for (Parameter param : params) {
					typeElement.addElement("param").addAttribute("name",
							param.getName()).addText(param.getValue());
				}
			}
		} else if (paAttribute.getEnumerated() != null) {
			handleEnumType(paAttribute, propElement);
		} else if (StoreUtil.isQName(paAttribute.getModelEAttribute())) {
			propElement.addAttribute("type",
					"org.eclipse.emf.teneo.hibernate.mapping.QNameUserType");
		} else {
			final String hType = hbType(paAttribute);
			if (hType != null) {
				propElement.addAttribute("type", hType);
			} else {
				final Element typeElement = propElement.addElement("type")
						.addAttribute("name", hbmContext.getDefaultUserType());
				typeElement.addElement("param").addAttribute("name",
						HbMapperConstants.EDATATYPE_PARAM).addText(
						paAttribute.getModelEAttribute().getEAttributeType()
								.getName());
				typeElement.addElement("param").addAttribute("name",
						HbMapperConstants.EPACKAGE_PARAM).addText(
						paAttribute.getModelEAttribute().getEType()
								.getEPackage().getNsURI());
			}
		}
	}

	/** Handle the enum type */
	private void handleEnumType(PAnnotatedEAttribute paAttribute,
			Element propElement) {
		final Enumerated enumerated = paAttribute.getEnumerated();
		assert (enumerated != null);
		final EAttribute eattr = paAttribute.getModelEAttribute();
		final EClassifier eclassifier = eattr.getEType();
		if (!getHbmContext().isGeneratedByEMF()
				&& getHbmContext().getInstanceClass(eclassifier) != null) {
			final Class<?> instanceClass = getHbmContext().getInstanceClass(
					eclassifier);
			propElement.addElement("type").addAttribute("name",
					getEnumUserType(enumerated)).addElement("param")
					.addAttribute("name", HbMapperConstants.ENUM_CLASS_PARAM)
					.addText(instanceClass.getName());
		} else if (getHbmContext().isGeneratedByEMF()
				&& eclassifier.getInstanceClass() != null) {
			propElement.addElement("type").addAttribute("name",
					getEnumUserType(enumerated)).addElement("param")
					.addAttribute("name", HbMapperConstants.ENUM_CLASS_PARAM)
					.addText(eclassifier.getInstanceClass().getName());
		} else { // must be emf dynamic
			final Element typeElement = propElement.addElement("type")
					.addAttribute("name", hbDynamicEnumType(enumerated));
			typeElement.addElement("param").addAttribute("name",
					HbMapperConstants.ECLASSIFIER_PARAM).addText(
					eclassifier.getName());
			typeElement.addElement("param").addAttribute("name",
					HbMapperConstants.EPACKAGE_PARAM).addText(
					eclassifier.getEPackage().getNsURI());
		}

	}

	// gather the pafeatures of the supertypes also
	protected List<PAnnotatedEStructuralFeature> getAllFeatures(
			PAnnotatedEClass componentAClass) {
		final ArrayList<PAnnotatedEStructuralFeature> result = new ArrayList<PAnnotatedEStructuralFeature>();
		result.addAll(componentAClass.getPaEStructuralFeatures());
		for (EClass eClass : componentAClass.getModelEClass().getESuperTypes()) {
			final PAnnotatedEClass aSuperClass = componentAClass.getPaModel()
					.getPAnnotated(eClass);
			if (aSuperClass != null) {
				result.addAll(getAllFeatures(aSuperClass));
			}
		}
		return result;
	}

	/**
	 * @return Returns the hibernate name for the given Ecore data type.
	 * @throws MappingException
	 *             if no corresponding hb type is defined.
	 */
	protected String hbType(PAnnotatedEAttribute paAttribute) {
		final EAttribute eAttribute = paAttribute.getModelEAttribute();
		final HbAnnotatedEDataType hed = (HbAnnotatedEDataType) paAttribute
				.getPaModel().getPAnnotated(eAttribute.getEAttributeType());
		final EDataType eDataType = paAttribute.getModelEAttribute()
				.getEAttributeType();
		if (hed != null && hed.getHbTypeDef() != null) {
			return hed.getHbTypeDef().getName();
		} else if (paAttribute.getLob() != null) {
			if (EcoreDataTypes.INSTANCE.isByteArray(eDataType)) {
				return "binary";
			} else if (EcoreDataTypes.INSTANCE.isEString(eDataType)) {
				return "text";
			} else {
				throw new MappingException(
						"Lob annotations can only be used with Strings or byte arrays. "
								+ "Attribute is of type: " + eDataType);
			}
		} else if (EcoreDataTypes.INSTANCE.isEWrapper(eDataType)
				|| EcoreDataTypes.INSTANCE.isEPrimitive(eDataType)) {
			return eDataType.getInstanceClassName();
		} else if (EcoreDataTypes.INSTANCE.isEString(eDataType)) {
			return eDataType.getInstanceClassName();
		} else if (EcoreDataTypes.INSTANCE.isEDateTime(eDataType)
				|| (paAttribute.getTemporal() != null && paAttribute
						.getTemporal().getValue().getValue() == TemporalType.TIMESTAMP_VALUE)) {
			return getEDateTimeClass(paAttribute);
		} else if (EcoreDataTypes.INSTANCE.isEDuration(eDataType)) {
			return hbmContext.getDurationType();
		} else if (EcoreDataTypes.INSTANCE.isEDate(eDataType, getHbmContext()
				.getPersistenceOptions())
				|| (paAttribute.getTemporal() != null && paAttribute
						.getTemporal().getValue().getValue() == TemporalType.DATE_VALUE)) {
			return getEDateClass(paAttribute);
		} else if (eDataType.getInstanceClass() != null
				&& eDataType.getInstanceClass() == Object.class) {
			// null forces caller to use usertype
			return null; // "org.eclipse.emf.teneo.hibernate.mapping.DefaultToStringUserType";
			// } else if (eDataType.getInstanceClass() != null) {
			// return eDataType.getInstanceClassName();
		} else {
			final String result = EcoreDataTypes.INSTANCE
					.getTargetTypeName(paAttribute);
			if (result.compareTo(Object.class.getName()) == 0) {
				// all edatatypes are translatable to a string, done by caller
				return null; // "org.eclipse.emf.teneo.hibernate.mapping.DefaultToStringUserType";
			}
			return result;
		}
	}

	/** Returns the type name of a many attribute */
	protected String getTargetTypeName(PAnnotatedEAttribute aAttribute) {
		final EAttribute eAttribute = aAttribute.getModelEAttribute();
		// check on equality on object.class is used for listunion simpleunions
		final Class<?> instanceClass = eAttribute.getEAttributeType()
				.getInstanceClass();
		if (instanceClass != null && !Object.class.equals(instanceClass)
				&& !List.class.equals(instanceClass)) {
			if (instanceClass.isArray()) {
				// get rid of the [] at the end
				return eAttribute.getEType().getInstanceClassName()
						.substring(
								0,
								eAttribute.getEType().getInstanceClassName()
										.length() - 2);
			}
			return instanceClass.getName();
		} else {
			// the type is hidden somewhere deep get it
			// the edatatype is the java.util.list
			// it has an itemType which is the name of the element edatatype
			// which contains the instanceclass
			// takes also into account inheritance between datatypes
			// NOTE the otm.targetentity can consist of a comma delimited list
			// of target
			// entities this is required for listunion types but is not
			// according to the ejb3 spec!
			ArrayList<EClassifier> eclassifiers = EcoreDataTypes.INSTANCE
					.getItemTypes((EDataType) eAttribute.getEType());
			if (eclassifiers.size() > 0) {
				StringBuffer result = new StringBuffer();
				for (int i = 0; i < eclassifiers.size(); i++) {
					final EClassifier eclassifier = eclassifiers.get(i);
					if (i > 0) {
						result.append(",");
					}
					result.append(eclassifier.getInstanceClassName());
				}
				return result.toString();
			} else {
				return Object.class.getName();
			}
		}
	}

	/**
	 * Returns the correct enum primitive hibernate type, for Elver this is a
	 * hibernate user type.
	 */
	public String getEnumUserType(Enumerated enumerated) {
		if (EnumType.STRING == enumerated.getValue()) {
			return getHbmContext().getEnumUserType();
		} else {
			return getHbmContext().getEnumIntegerUserType();
		}
	}

	/**
	 * Returns the correct enum primitive hibernate type, for Elver this is a
	 * hibernate user type.
	 */
	protected String hbDynamicEnumType(Enumerated enumerated) {
		if (EnumType.STRING == enumerated.getValue()) {
			return getHbmContext().getDynamicEnumUserType();
		} else {
			return getHbmContext().getDynamicEnumIntegerUserType();
		}
	}

	/*
	 * @return The name of the java class needed to map the date type
	 */
	public String getEDateClass(PAnnotatedEAttribute paAttribute) {
		final EDataType eDataType = paAttribute.getModelEAttribute()
				.getEAttributeType();

		assert (EcoreDataTypes.INSTANCE.isEDate(eDataType, getHbmContext()
				.getPersistenceOptions()));

		if (XMLTypePackage.eINSTANCE.getDate().equals(eDataType)) {
			return getHbmContext().getXSDDateUserType();
		}

		if (paAttribute.getTemporal() != null) {
			final TemporalType tt = paAttribute.getTemporal().getValue();
			return hbType(tt);
		}

		// only override if the user did not specify a more specific class
		if (EcoreDataTypes.INSTANCE.isEDate(eDataType, getHbmContext()
				.getPersistenceOptions())) {
			// EMF returns an XSD Date type as an Object instance. go figure.
			// note that I would prefer to use the class instance to get the
			// name
			// but for other reasons I do not want to have references to the
			// org.eclipse.emf.teneo.hibernate plugin.
			return getHbmContext().getXSDDateUserType();
		}

		// TODO: should it not use the eDataType.getInstanceClass()? Hmm if the
		// user
		// really wants a different mapping he/she should use maybe a usertype??
		return Date.class.getName();
	}

	/*
	 * @return The name of the java class needed to map the datetime/timestamp
	 * type
	 */
	public String getEDateTimeClass(PAnnotatedEAttribute paAttribute) {
		final EDataType eDataType = paAttribute.getModelEAttribute()
				.getEAttributeType();

		assert (EcoreDataTypes.INSTANCE.isEDateTime(eDataType));

		if (XMLTypePackage.eINSTANCE.getDateTime().equals(eDataType)) {
			return getHbmContext().getXSDDateTimeUserType();
		}

		if (paAttribute.getTemporal() != null) {
			final TemporalType tt = paAttribute.getTemporal().getValue();
			return hbType(tt);
		}

		if (EcoreDataTypes.INSTANCE.isEDateTime(eDataType)) {
			// EMF returns an XSD Date type as an Object instance. go figure.
			// note that I would prefer to use the class instance to get the
			// name
			// but for other reasons I do not want to have references to the
			// org.eclipse.emf.teneo.hibernate plugin.
			return getHbmContext().getXSDDateTimeUserType();
		}

		// TODO: should it not use the eDataType.getInstanceClass()? Hmm if the
		// user
		// really wants a different mapping he/she should use maybe a usertype??
		return Timestamp.class.getName();
	}

	/**
	 * Returns the (possibly overridden) JoinColumns annotations for the given
	 * reference or an empty list if no JoinColumns were defined.
	 */
	protected List<JoinColumn> getJoinColumns(PAnnotatedEReference paReference) {
		List<JoinColumn> joinColumns = getHbmContext().getAssociationOverrides(
				paReference);
		if (joinColumns == null) {
			return paReference.getJoinColumns();
		}
		return joinColumns;
	}

	/** Adds a cache element */
	protected void addCacheElement(Element parent, Cache cache) {
		// translate to hibernate specific notation
		final String usage = cache.getUsage().getName().toLowerCase()
				.replaceAll("_", "-");

		Element cacheElement = parent.addElement("cache").addAttribute("usage",
				usage);
		if (cache.getRegion() != null) {
			cacheElement.addAttribute("region", cache.getRegion());
		}
		if (cache.getInclude() != null) {
			cacheElement.addAttribute("include", cache.getInclude());
		}
		parent.remove(cacheElement);
		parent.add(0, cacheElement);
	}

	/** Same as above only handles multiple columns */
	protected void addColumnsAndFormula(Element propertyElement,
			PAnnotatedEStructuralFeature pef, List<Column> columns,
			boolean isNullable, boolean setColumnAttributesInProperty) {
		addColumnsAndFormula(propertyElement, pef, columns, isNullable,
				setColumnAttributesInProperty, false, false);
	}

	/** Same as above only handles multiple columns */
	protected void addColumnsAndFormula(Element propertyElement,
			PAnnotatedEStructuralFeature pef, List<Column> columns,
			boolean isNullable, boolean setColumnAttributesInProperty,
			boolean isUnique, boolean isIdProperty) {
		// if no columns set then use some default
		final HbAnnotatedETypeElement hbFeature = (HbAnnotatedETypeElement) pef;
		if (columns.isEmpty() && hbFeature.getFormula() == null) {
			final Column col = PannotationFactory.eINSTANCE.createColumn();
			col.setName(getColumnName(pef));
			col.setNullable(isNullable);
			if (isIdProperty) {
				col.setUnique(false);
			} else {
				col.setUnique(isUnique);
			}

			if (pef instanceof HbAnnotatedEAttribute
					&& ((HbAnnotatedEAttribute) pef).getGenerated() != null
					&& ((HbAnnotatedEAttribute) pef).getGenerated().getValue() != null
					&& ((HbAnnotatedEAttribute) pef).getGenerated().getValue() != GenerationTime.NEVER) {
				col.setInsertable(false);
				col.setUpdatable(false);
			}

			columns.add(col);
		}
		for (Column column : columns) {
			addColumn(propertyElement, pef, column, isNullable,
					setColumnAttributesInProperty);
		}

		// do the formula part
		if (hbFeature.getFormula() != null) {
			propertyElement.addElement("formula").addText(
					hbFeature.getFormula().getValue());
		}
	}

	protected String getColumnName(PAnnotatedEStructuralFeature pef) {
		if (getHbmContext().getEmbeddingFeature() != null) { // embedded
			// TODO: check illegal, embedded component can not really have an id
			final PAnnotatedEStructuralFeature embeddingFeature = getHbmContext()
					.getEmbeddingFeature();
			return getHbmContext().getSqlNameStrategy().getColumnName(pef,
					embeddingFeature.getModelEStructuralFeature().getName());
		} else {
			return getHbmContext().getSqlNameStrategy()
					.getColumnName(pef, null);
		}
	}

	/**
	 * Add a comment element, if the eModelElement has documentation, returns
	 * the comment element
	 */
	protected Element addCommentElement(EModelElement eModelElement,
			Element hbmElement) {
		if (hbmContext.getMaximumCommentLength() == 0) {
			return null;
		}
		final String commentData = EcoreUtil.getDocumentation(eModelElement);
		if (commentData != null) {
			final Element comment = hbmElement.addElement("comment");
			String commentText = commentData.replace('\'', ' ').replace('"',
					' ');
			if (commentText.length() > hbmContext.getMaximumCommentLength()) {
				commentText = commentText.substring(0, hbmContext
						.getMaximumCommentLength());
			}
			comment.addText(commentText);
			return comment;
		}
		return null;
	}

	/** Adds anytype columns */
	protected List<Column> getAnyTypeColumns(String featureName,
			boolean isNullable) {
		final ArrayList<Column> result = new ArrayList<Column>();
		final Column typeColumn = PannotationFactory.eINSTANCE.createColumn();
		typeColumn.setName(hbmContext.trunc(featureName + "_type"));
		typeColumn.setNullable(isNullable);
		result.add(typeColumn);
		final Column idColumn = PannotationFactory.eINSTANCE.createColumn();
		idColumn.setName(hbmContext.trunc(featureName + "_id"));
		idColumn.setNullable(isNullable);
		result.add(idColumn);
		return result;
	}

	/**
	 * Returns the (possibly overridden) columns annotation for the given
	 * attribute.
	 */
	protected List<Column> getColumns(PAnnotatedEStructuralFeature paFeature) {
		final Column defaultColumn = paFeature.getColumn();
		final Column oc = getHbmContext().getAttributeOverride(paFeature);

		if (oc != null) {
			final ArrayList<Column> result = new ArrayList<Column>();
			result.add(oc);
			return result;
		}
		// try multiple columns
		final HbAnnotatedETypeElement hae = (HbAnnotatedETypeElement) paFeature;
		if (hae.getHbColumns().size() > 0) {
			return hae.getHbColumns();
		}
		final ArrayList<Column> result = new ArrayList<Column>();
		if (defaultColumn != null) {
			result.add(defaultColumn);
		}
		return result;
	}

	/** Sets property attributes on the basis of the column */
	private void addColumn(Element propertyElement,
			PAnnotatedEStructuralFeature pef, Column column,
			boolean isNullable, boolean setColumnAttributesInProperty) {
		if (column != null) {
			if (setColumnAttributesInProperty) {
				// this is not the nicest place to do this
				if (propertyElement.getName().compareTo("property") == 0
						|| propertyElement.getName().compareTo("many-to-one") == 0) {
					propertyElement.addAttribute("insert", column
							.isInsertable() ? "true" : "false");
					propertyElement.addAttribute("update",
							column.isUpdatable() ? "true" : "false");
				}
				// MT: I think that the column nullability should not be used
				// for setting not-null
				// on the property, this is already specified by the optional
				// attribute on the
				// basic annotation. Maybe a check can be used instead to detect
				// inconsistenties
				// in the column attributes and the basic ann.
				// Note that the ejb3 spec says that optional should be
				// disregarded for primitive
				// types which I
				// do not understand.
				// I disabled it for now to ignore for the test cases.
				// MT05032006: After some more thought the column nullability
				// can be used in case of
				// single table inheritance mapping
				if (!propertyElement.getName().equals("any")) {
					propertyElement.addAttribute("not-null", isNullable
							|| column.isNullable() ? "false" : "true");
				}
				propertyElement.addAttribute("unique",
						column.isUnique() ? "true" : "false");
			}
			addColumnElement(propertyElement, pef, column, isNullable);
		}
	}

	protected void mapFilter(Element parentElement, List<Filter> filters) {
		for (Filter filter : filters) {
			final Element filterElement = parentElement.addElement("filter");
			filterElement.addAttribute("name", filter.getName());
			if (filter.getCondition() != null) {
				filterElement.addAttribute("condition", filter.getCondition());
			}
		}
	}

	/**
	 * Add a columnelement to the property, takes into account length, precision
	 * etc. forceNullable is set when the feature belongs to a featuremap
	 */
	private void addColumnElement(Element propertyElement,
			PAnnotatedEStructuralFeature pef, Column column,
			boolean forceNullable) {
		if (column != null) {
			Element columnElement = propertyElement.addElement("column")
					.addAttribute(
							"not-null",
							column.isNullable() || forceNullable ? "false"
									: "true").addAttribute("unique",
							column.isUnique() ? "true" : "false");
			final String name;
			if (column.getName() != null) {
				name = column.getName();
			} else {
				if (getHbmContext().getEmbeddingFeature() != null) { // embedded
					// TODO: check illegal, embedded component can not really
					// have an id
					final PAnnotatedEStructuralFeature embeddingFeature = getHbmContext()
							.getEmbeddingFeature();
					name = getHbmContext().getSqlNameStrategy().getColumnName(
							pef,
							embeddingFeature.getModelEStructuralFeature()
									.getName());
				} else {
					name = getHbmContext().getSqlNameStrategy().getColumnName(
							pef, null);
				}
			}
			columnElement.addAttribute("name", getHbmContext().trunc(name));
			if (column.isSetLength()) {
				columnElement.addAttribute("length", Integer.toString(column
						.getLength()));
			}
			if (column.isSetPrecision()) {
				columnElement.addAttribute("precision", Integer.toString(column
						.getPrecision()));
			}
			if (column.isSetScale()) {
				columnElement.addAttribute("scale", Integer.toString(column
						.getScale()));
			}
			if (column.getColumnDefinition() != null) {
				columnElement.addAttribute("sql-type", column
						.getColumnDefinition());
			}
			final String uc = getHbmContext().getUniqueConstraintKey(name);
			if (uc != null) {
				columnElement.addAttribute("unique-key", uc);
			}
			if (pef instanceof HbAnnotatedETypeElement
					&& ((HbAnnotatedETypeElement) pef).getHbIndex() != null) {
				final Index index = ((HbAnnotatedETypeElement) pef)
						.getHbIndex();
				columnElement.addAttribute("index", index.getName());
			}

			// --- JJH, adapted by MT
			addCommentElement(pef.getModelElement(), columnElement);
			// --- JJH
		}
	}

	/** Add the ondelete mapping to the key */
	protected void handleOndelete(Element keyElement, OnDelete onDelete) {
		if (true || onDelete == null) {
			return;
		}

		if (onDelete.getAction().equals(OnDeleteAction.CASCADE)) {
			keyElement.addAttribute("on-delete", "cascade");
		} else {
			keyElement.addAttribute("on-delete", "noaction");
		}
	}

	/** Returns true if the target is the general EObject type */
	protected boolean isEObject(String typeName) {
		if (typeName == null) {
			return false;
		}
		return typeName.compareTo(EntityNameStrategy.EOBJECT_ECLASS_NAME) == 0;
	}
}