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
 * $Id: BaseEFeatureAnnotator.java,v 1.12 2008/10/12 11:38:11 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.annotations.pannotation.ForeignKey;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;
import org.eclipse.emf.teneo.annotations.pannotation.Temporal;
import org.eclipse.emf.teneo.annotations.pannotation.TemporalType;
import org.eclipse.emf.teneo.util.EcoreDataTypes;

/**
 * Placeholder for several utility methods which are relevant for annotating ereferences and
 * eattributes.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.12 $
 */

public abstract class BaseEFeatureAnnotator extends AbstractAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(BaseEFeatureAnnotator.class);

	private int defaultVarCharLength = -1;

	/** Create a foreign key and set its name */
	protected ForeignKey createFK(PAnnotatedEStructuralFeature aFeature) {
		final ForeignKey fk = getFactory().createForeignKey();
		fk.setName(getSqlNameStrategy().getForeignKeyName(aFeature));
		return fk;
	}

	protected FetchType getFetch(PAnnotatedEClass aClass) {
		return FetchType.EAGER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.teneo.annotations.mapper.AbstractAnnotator#setPersistenceOptions(org.eclipse
	 * .emf.teneo.PersistenceOptions)
	 */
	@Override
	public void setPersistenceOptions(PersistenceOptions persistenceOptions) {
		super.setPersistenceOptions(persistenceOptions);

		defaultVarCharLength = persistenceOptions.getDefaultVarCharLength();
	}

	/**
	 * Adds the column level constraints on the basis of the xsd extended meta data
	 */
	protected void addColumnConstraints(PAnnotatedEAttribute aAttribute) {

		final EAttribute eAttribute = aAttribute.getModelEAttribute();

		// decide if a column annotation should be added, this is done
		// when the maxLength or length, totalDigits or fractionDigits are set
		// and when no other column has been set
		if (aAttribute.getColumn() == null) {
			String maxLength = getExtendedMetaData(eAttribute, "maxLength");
			if (maxLength == null) {
				maxLength = getExtendedMetaData(eAttribute, "length");
			}
			if (maxLength == null && defaultVarCharLength > 0) {
				maxLength = "" + defaultVarCharLength;
			}
			final String totalDigits = getExtendedMetaData(eAttribute, "totalDigits");
			final String fractionDigits = getExtendedMetaData(eAttribute, "fractionDigits");
			boolean setUnique = false;
			// bugzilla 249246
			if (getPersistenceOptions().isIDFeatureAsPrimaryKey() && eAttribute.isID() && aAttribute.getId() == null) {
				if (aAttribute.getPaEClass().getPaSuperEntity() != null &&
						aAttribute.getPaEClass().getPaSuperEntity().getMappedSuperclass() == null) {
					setUnique = true;
				}
			}
			if (maxLength != null || setUnique || totalDigits != null || fractionDigits != null ||
					defaultVarCharLength > -1) {
				final Column column = getFactory().createColumn();
				// only support this for the string class, the length/maxlength
				// is also
				// used in case of the xsd list/union types but this can not be
				// enforced using a constraint on the
				// columnlength
				if (maxLength != null && eAttribute.getEAttributeType().getInstanceClass() != null &&
						eAttribute.getEAttributeType().getInstanceClass() == String.class) {
					column.setLength(Integer.parseInt(maxLength)); // you'll
					// find
					// parse
					// errors!
				}
				if (totalDigits != null) {
					column.setPrecision(Integer.parseInt(totalDigits));
				}
				if (fractionDigits != null) {
					column.setScale(Integer.parseInt(fractionDigits));
				}
				if (aAttribute.getBasic() != null) {
					column.setNullable(aAttribute.getBasic().isOptional());
				}
				if (setUnique) {
					column.setUnique(true);
				}
				aAttribute.setColumn(column);
			}
		} else if (aAttribute.getBasic() != null && !aAttribute.getColumn().isSetNullable()) {
			// bugzilla 226775
			aAttribute.getColumn().setNullable(aAttribute.getBasic().isOptional());
		}

		final Column c = aAttribute.getColumn();
		if (isStringType(aAttribute.getModelEAttribute()) && c != null && defaultVarCharLength > 0 && !c.isSetLength()) {
			c.setLength(defaultVarCharLength);
		}
	}

	private boolean isStringType(EAttribute eAttribute) {
		final Class<?> clz = eAttribute.getEAttributeType().getInstanceClass();
		if (clz != null && String.class.isAssignableFrom(clz)) {
			return true;
		}
		if (eAttribute.getEAttributeType() == XMLTypePackage.eINSTANCE.getString()) {
			return true;
		}
		if (eAttribute.getEAttributeType() == XMLTypePackage.eINSTANCE.getName_()) {
			return true;
		}
		if (eAttribute.getEAttributeType() == XMLTypePackage.eINSTANCE.getNCName()) {
			return true;
		}
		if (eAttribute.getEAttributeType() == XMLTypePackage.eINSTANCE.getToken()) {
			return true;
		}
		if (eAttribute.getEAttributeType() == XMLTypePackage.eINSTANCE.getQName()) {
			return true;
		}
		return false;
	}

	/** Return a list of join columns */
	protected List<JoinColumn> getJoinColumns(List<String> names, boolean optional, boolean isUpdateInsertable,
			PAnnotation pAnnotation) {
		final List<JoinColumn> result = new ArrayList<JoinColumn>();
		for (String name : names) {
			JoinColumn jc = getFactory().createJoinColumn();
			jc.setName(name);
			jc.setNullable(optional);
			jc.setUpdatable(isUpdateInsertable);
			jc.setInsertable(isUpdateInsertable);
			result.add(jc);
		}
		return result;
	}

	protected String getTargetTypeName(PAnnotatedEAttribute aAttribute) {
		return EcoreDataTypes.INSTANCE.getTargetTypeName(aAttribute);
	}

	/** Get a specific extended metadate */
	protected String getExtendedMetaData(EAttribute eAttribute, String key) {
		String value =
				EcoreDataTypes.INSTANCE.getEAnnotationValue(eAttribute,
					"http:///org/eclipse/emf/ecore/util/ExtendedMetaData", key);
		if (value == null) {
			value =
					EcoreDataTypes.INSTANCE.getEAnnotationValue(eAttribute.getEAttributeType(),
						"http:///org/eclipse/emf/ecore/util/ExtendedMetaData", key);
		}
		return value;
	}

	/** Determines if mapped by should be set */
	protected boolean setMappedBy(EReference eReference) {
		// only set in two way relation
		// if has not been set on the other side (mappedtoFields)
		// if not a containment relation, containment relations are handled
		// differently
		// the other side may neither be containment
		final EReference eOpposite = eReference.getEOpposite();
		if (eOpposite == null) {
			return false;
		}

		final PAnnotatedEReference aOpposite = getAnnotatedModel().getPAnnotated(eOpposite);
		if (aOpposite.getOneToOne() != null && aOpposite.getOneToOne().getMappedBy() != null) {
			return false;
		}

		return compareNames(eReference, eOpposite);
		// &&
		// !eReference.isContainment() && !eOpposite.isContainment();
	}

	/**
	 * Determines where to place a certain annotation/characteristic, this is done by comparing
	 * names..
	 */
	protected boolean compareNames(EReference here, EReference there) {
		final String nameHere = here.eClass().getName() + here.getName();
		final String nameThere = there.eClass().getName() + there.getName();
		assert (nameHere.compareTo(nameThere) != 0);
		return nameHere.compareTo(nameThere) > 0;
	}

	/**
	 * Checks if the cascade should be set in the cascade list, is only done if the list is empty
	 */
	protected void setCascade(List<CascadeType> cascadeList, boolean isContainment) {
		if (!cascadeList.isEmpty()) {
			return;
		}

		if (isContainment) {
			if (getPersistenceOptions().isSetCascadeAllOnContainment()) {
				cascadeList.add(CascadeType.ALL);
			} else {
				if (getPersistenceOptions().isSetCascadeRemoveOnContainment()) {
					cascadeList.add(CascadeType.REMOVE);
				}
				if (getPersistenceOptions().isSetCascadeMergeOnContainment()) {
					cascadeList.add(CascadeType.MERGE);
				}
				if (getPersistenceOptions().isSetCascadePersistOnContainment()) {
					cascadeList.add(CascadeType.PERSIST);
				}
				if (getPersistenceOptions().isSetCascadeRefreshOnContainment()) {
					cascadeList.add(CascadeType.REFRESH);
				}
			}
		} else if (getPersistenceOptions().isSetCascadePolicyForNonContainment()) {
			if (getPersistenceOptions().isSetCascadeMergeOnNonContainment()) {
				cascadeList.add(CascadeType.MERGE);
			}
			if (getPersistenceOptions().isSetCascadePersistOnNonContainment()) {
				cascadeList.add(CascadeType.PERSIST);
			}
			if (getPersistenceOptions().isSetCascadeRefreshOnNonContainment()) {
				cascadeList.add(CascadeType.REFRESH);
			}
		} else {
			cascadeList.add(CascadeType.MERGE);
			cascadeList.add(CascadeType.PERSIST);
			cascadeList.add(CascadeType.REFRESH);
		}
	}

	protected void setTemporal(PAnnotatedEAttribute aAttribute, TemporalType defaultTemporal) {
		final EAttribute eAttribute = aAttribute.getModelEAttribute();
		Class<?> clazz = eAttribute.getEAttributeType().getInstanceClass();
		// clazz is hidden somewhere
		if (clazz == null || Object.class.equals(clazz)) {
			ArrayList<EClassifier> eclassifiers =
					EcoreDataTypes.INSTANCE.getItemTypes((EDataType) eAttribute.getEType());
			for (EClassifier eclassifier : eclassifiers) {
				if (eclassifier.getInstanceClass() != null) {
					clazz = eclassifier.getInstanceClass();
					break;
				}
			}
		}

		final EDataType eDataType = aAttribute.getModelEAttribute().getEAttributeType();
		if (clazz != null &&
				(Date.class.isAssignableFrom(clazz) || eDataType == XMLTypePackage.eINSTANCE.getDate() || eDataType == XMLTypePackage.eINSTANCE
					.getDateTime())) {
			final Temporal temporal = getFactory().createTemporal();
			if (eDataType == XMLTypePackage.eINSTANCE.getDate()) {
				temporal.setValue(TemporalType.DATE);
			} else if (eDataType == XMLTypePackage.eINSTANCE.getDateTime()) {
				temporal.setValue(TemporalType.TIMESTAMP);
			} else {
				temporal.setValue(defaultTemporal);
			}
			aAttribute.setTemporal(temporal);
			temporal.setEModelElement(eAttribute);
		} else if (clazz != null &&
				(Calendar.class.isAssignableFrom(clazz) || eDataType == XMLTypePackage.eINSTANCE.getDate() || eDataType == XMLTypePackage.eINSTANCE
					.getDateTime())) {
			final Temporal temporal = getFactory().createTemporal();
			if (eDataType == XMLTypePackage.eINSTANCE.getDate()) {
				temporal.setValue(TemporalType.DATE);
			} else if (eDataType == XMLTypePackage.eINSTANCE.getDateTime()) {
				temporal.setValue(TemporalType.TIMESTAMP);
			} else {
				temporal.setValue(defaultTemporal);
			}
			aAttribute.setTemporal(temporal);
			temporal.setEModelElement(eAttribute);
		}
	}
}
