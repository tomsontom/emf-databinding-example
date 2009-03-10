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
 *   Martin Taal - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ClassicSQLNameStrategy.java,v 1.14 2008/03/17 19:30:14 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerAware;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.SQLNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.StrategyUtil;
import org.eclipse.emf.teneo.util.AssertUtil;

/**
 * Implements the sql naming strategy of older versions of Teneo. This implementation is driven by
 * the options set in the PersistenceOptions.
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.14 $
 */
public class ClassicSQLNameStrategy implements SQLNameStrategy, ExtensionManagerAware {

	// The logger
	protected static final Log log = LogFactory.getLog(ClassicSQLNameStrategy.class);

	// The local members for several options
	protected String optionJoinTableNamingStrategy;
	protected String optionJoinColumnNamingStrategy;
	protected int optionMaximumSqlLength;
	protected EntityNameStrategy entityNameStrategy;
	protected PersistenceOptions persistenceOptions;
	protected boolean optionSQLUpperCase = false;
	protected boolean optionSQLLowerCase = false;

	private ExtensionManager extensionManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getPrimaryKeyJoinColumnName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass,
	 *      java.lang.String)
	 */
	public String getPrimaryKeyJoinColumnName(PAnnotatedEClass aSuperClass, String idFeature) {
		return convert(getEntityName(aSuperClass.getPaModel(), aSuperClass.getModelEClass()) + "_" + idFeature, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getSecondaryTablePrimaryKeyJoinColumnName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature)
	 */
	public String getSecondaryTablePrimaryKeyJoinColumnName(PAnnotatedEStructuralFeature iddef) {
		return convert(iddef.getModelEStructuralFeature().getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getTableName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass)
	 */
	public String getTableName(PAnnotatedEClass aClass) {
		return convert(getEntityName(aClass.getPaModel(), aClass.getModelEClass()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getColumnName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature)
	 */
	public String getColumnName(PAnnotatedEStructuralFeature aStructuralFeature, String prefix) {
		if (prefix != null) {
			return convert(prefix + "_" + aStructuralFeature.getModelEStructuralFeature().getName());
		}
		return convert(aStructuralFeature.getModelEStructuralFeature().getName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getForeignKeyName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature)
	 */
	public String getForeignKeyName(PAnnotatedEStructuralFeature aFeature) {
		final PAnnotatedEClass aClass = aFeature.getPaEClass();
		return convert(getEntityName(aClass.getPaModel(), aClass.getModelEClass()) + "_" +
				aFeature.getModelEStructuralFeature().getName(), true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getManyToOneJoinColumnNames(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference)
	 */
	public List<String> getManyToOneJoinColumnNames(PAnnotatedEReference aReference) {
		final EReference eref = aReference.getModelEReference();

		// isTransient occurs for computed featuremap features, these are ignored
		// later on
		assert (eref.isTransient() || !eref.isMany()); // otherwise this should have been a mtm

		// in case of many-to-one to qualify use the name of the class to which is refered
		// this is just there for backward compatibility, see the case of
		// the manytoone and onetoone below.
		final PAnnotatedEClass aClass;
		if (eref.getEOpposite() == null) {
			aClass = aReference.getAReferenceType();
		} else {
			// the aclass is just the class of the structuralfeature itself.
			// this is the most common case the name of the join column reflects
			// the owner of the one-to-many
			aClass = aReference.getPaEClass();
		}
		final String typeName = aClass.getModelEClass().getName();
		final String featureName = eref.getName();

		final List<String> result = new ArrayList<String>();
		final List<String> names =
				StrategyUtil.getIDFeaturesNames(aClass, persistenceOptions.getDefaultIDFeatureName());
		final boolean simpleNaming = optionJoinColumnNamingStrategy.compareTo("simple") == 0;
		for (String name : names) {
			final String postFix;
			if (names.size() == 1 && simpleNaming) {
				postFix = "";
			} else {
				postFix = "_" + name;
			}

			final String jcName;
			if (simpleNaming) {
				jcName = featureName + postFix;
			} else { // backward compatibility
				jcName = typeName + "_" + featureName + postFix;
			}
			result.add(convert(jcName, true));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getOneToManyEAttributeJoinColumns(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute)
	 */
	public List<String> getOneToManyEAttributeJoinColumns(PAnnotatedEAttribute aAttribute) {
		final PAnnotatedEClass aClass = aAttribute.getPaEClass();
		final String typeName = aClass.getModelEClass().getName();
		final String featureName = aAttribute.getModelEAttribute().getName();

		final List<String> result = new ArrayList<String>();
		final List<String> names =
				StrategyUtil.getIDFeaturesNames(aClass, persistenceOptions.getDefaultIDFeatureName());
		final boolean simpleNaming = optionJoinColumnNamingStrategy.compareTo("simple") == 0;
		for (String name : names) {
			final String postFix;
			if (names.size() == 1 && simpleNaming) {
				postFix = "";
			} else {
				postFix = "_" + name;
			}

			final String jcName = typeName + "_" + featureName + postFix;
			result.add(convert(jcName, true));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getOneToManyEReferenceJoinColumns(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference)
	 */
	public List<String> getOneToManyEReferenceJoinColumns(PAnnotatedEReference aReference) {
		final PAnnotatedEClass aClass = aReference.getPaEClass();
		final String typeName;
		final String featureName;
		// for backwards compatibility if the other side is there then use the name
		// of the other side for the typeName and featureName. The many-to-one determines
		// the naming
		if (aReference.getModelEReference().getEOpposite() != null) {
			typeName = aReference.getAReferenceType().getModelEClass().getName();
			featureName = aReference.getModelEReference().getEOpposite().getName();
		} else {
			typeName = aClass.getModelEClass().getName();
			featureName = aReference.getModelEReference().getName();
		}

		final List<String> result = new ArrayList<String>();
		final List<String> names =
				StrategyUtil.getIDFeaturesNames(aClass, persistenceOptions.getDefaultIDFeatureName());
		final boolean simpleNaming = optionJoinColumnNamingStrategy.compareTo("simple") == 0;
		for (String name : names) {
			final String postFix;
			if (names.size() == 1 && simpleNaming) {
				postFix = "";
			} else {
				postFix = "_" + name;
			}

			final String jcName = typeName + "_" + featureName + postFix;
			result.add(convert(jcName, true));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getJoinTableJoinColumns(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference,
	 *      boolean)
	 */
	public List<String> getJoinTableJoinColumns(PAnnotatedEReference aReference, boolean inverse) {
		final PAnnotatedEClass aClass;
		final String typeName;
		String featureName;
		if (inverse) {
			aClass = aReference.getAReferenceType();
			if (aReference.getModelEReference().getEOpposite() != null) {
				typeName = aReference.getAReferenceType().getModelEClass().getName();
				featureName = "_" + aReference.getModelEReference().getEOpposite().getName();
			} else {
				typeName = aReference.getAReferenceType().getModelEClass().getName();
				featureName = "";
			}
		} else {
			aClass = aReference.getPaEClass();
			typeName = aClass.getModelEClass().getName();
			featureName = "_" + aReference.getModelEReference().getName();
		}
		// for backward compatibility, only use featurename if the reference is
		// to itself
		if (aReference.getAReferenceType() != aReference.getPaEClass()) {
			featureName = "";
		}

		final List<String> result = new ArrayList<String>();
		final List<String> names =
				StrategyUtil.getIDFeaturesNames(aClass, persistenceOptions.getDefaultIDFeatureName());
		final boolean simpleNaming = optionJoinColumnNamingStrategy.compareTo("simple") == 0;
		for (String name : names) {
			final String postFix;
			if (names.size() == 1 && simpleNaming) {
				postFix = "";
			} else {
				postFix = "_" + name;
			}

			final String jcName = typeName + featureName + postFix;
			result.add(convert(jcName, true));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getJoinTableName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute)
	 */
	public String getJoinTableName(PAnnotatedEAttribute aAttribute) {
		// note for array the isMany is false, so disable this check
// assert (aAttribute.getModelEAttribute().isMany());
		final PAnnotatedEClass aClass = aAttribute.getPaEClass();
		return convert(getEntityName(aClass.getPaModel(), aClass.getModelEClass()) + "_" +
				aAttribute.getModelEAttribute().getName(), true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getJoinTableName(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference)
	 */
	public String getJoinTableName(PAnnotatedEReference aReference) {
		final EReference eReference = aReference.getModelEReference();
		final boolean isEObject = eReference.getEType() == ClassicEntityNameStrategy.EOBJECT_ECLASS;
		final String jTableName;

		EReference eOpposite = eReference.getEOpposite();
		if (aReference.getManyToMany() != null && aReference.getManyToMany().isIndexed()) {
			// In case the reference is not indexed then one join table can be
			// used for both sides
			// If indexed then separate join tables should be used.
			eOpposite = null; // trick to force a specific naming
		}

		if (!isEObject && optionJoinTableNamingStrategy.compareToIgnoreCase("ejb3") == 0) {
			// table name is the entityname of the eclass of the reference and entityname of
			// the eclass of the refered to
			final String thisEntityName = getEntityName(aReference.getPaModel(), eReference.getEContainingClass());
			final String thatEntityName = getEntityName(aReference.getPaModel(), eReference.getEReferenceType());

			if (eOpposite != null && eOpposite.isMany() && compareNames(eReference, eOpposite)) {
				jTableName = thatEntityName + "_" + thisEntityName;
			} else {
				jTableName = thisEntityName + "_" + thatEntityName;
			}
		} else {
			AssertUtil.assertTrue("option optionJoinTableNamingStrategy " + optionJoinTableNamingStrategy +
					" not supported", isEObject || optionJoinTableNamingStrategy.compareToIgnoreCase("unique") == 0);
			if (eOpposite != null && eOpposite.isMany() && compareNames(eReference, eOpposite)) {
				final String thatEntityName = getEntityName(aReference.getPaModel(), eOpposite.getEContainingClass());
				jTableName = thatEntityName + "_" + eOpposite.getName();
			} else {
				final String thisEntityName = getEntityName(aReference.getPaModel(), eReference.getEContainingClass());
				jTableName = thisEntityName + "_" + eReference.getName();
			}
		}
		return convert(jTableName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getDiscriminatorColumnName()
	 */
	public String getDiscriminatorColumnName() {
		return convert("DTYPE"); // replace with constant somewhere
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getVersionColumnName()
	 */
	public String getVersionColumnName() {
		return convert(persistenceOptions.getVersionColumnName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getIdBagIDColumn()
	 */
	public String getIdBagIDColumn() {
		return convert(persistenceOptions.getIDBagIDColumnName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getSyntheticIDColumnName()
	 */
	public String getSyntheticIDColumnName() {
		return convert(persistenceOptions.getIdColumnName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#setPersistenceOptions(org.eclipse.emf.teneo.PersistenceOptions)
	 */
	public void setPersistenceOptions(PersistenceOptions po) {
		optionMaximumSqlLength = po.getMaximumSqlNameLength();
		optionJoinTableNamingStrategy = po.getJoinTableNamingStrategy();
		optionJoinColumnNamingStrategy = po.getJoinColumnNamingStrategy();

		final String optionSQLCaseStrategy = po.getSQLCaseStrategy();
		if (optionSQLCaseStrategy.toLowerCase().compareTo("lowercase") == 0) {
			optionSQLLowerCase = true;
		} else if (optionSQLCaseStrategy.toLowerCase().compareTo("uppercase") == 0) {
			optionSQLUpperCase = true;
		}
		persistenceOptions = po;
	}

	// Returns the entityname of the refered to entity
	private String getEntityName(PAnnotatedModel paModel, EClass eClass) {
		return StrategyUtil.getEntityName(getEntityNameStrategy(), persistenceOptions, paModel, eClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#convert(java.lang.String)
	 */
	public String convert(String name) {
		return convert(name, false);
	}

	public String convert(String name, boolean truncPrefix) {
		final String result = trunc(optionMaximumSqlLength, name, truncPrefix);
		if (optionSQLLowerCase) {
			return result.toLowerCase();
		} else if (optionSQLUpperCase) {
			return result.toUpperCase();
		} else {
			return result;
		}
	}

	/**
	 * Determines where to place a certain annotation/characteristic, this is done by comparing
	 * names..
	 */
	private boolean compareNames(EReference here, EReference there) {
		final String nameHere = here.getEContainingClass().getName() + here.getName();
		final String nameThere = there.getEContainingClass().getName() + there.getName();
		assert (nameHere.compareTo(nameThere) != 0);
		return nameHere.compareTo(nameThere) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getExtensionManager()
	 */
	public ExtensionManager getExtensionManager() {
		return extensionManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#setExtensionManager(org.eclipse.emf.teneo.extension.ExtensionManager)
	 */
	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getEntityNameStrategy()
	 */
	public EntityNameStrategy getEntityNameStrategy() {
		if (entityNameStrategy == null) {
			entityNameStrategy = getExtensionManager().getExtension(EntityNameStrategy.class);
		}
		return entityNameStrategy;
	}

	/**
	 * Utility method to truncate a column/table name, the truncPrefix determines if the part before
	 * the _ (the prefix) or after the _ (the suffix) is truncated. A _ often occurs in a jointable
	 * name.
	 */
	protected String trunc(int optionMaximumSqlLength, String truncName, boolean truncPrefix) {
		final String correctedName = truncName.replace('.', '_');

		if (optionMaximumSqlLength == -1) {
			return correctedName;
		}
		if (correctedName.length() < optionMaximumSqlLength) {
			return correctedName;
		}

		if (!truncPrefix) { // this truncs away the suffix
			return correctedName.substring(0, optionMaximumSqlLength);
		}

		// truncate the part before the last _ because this is often the suffix
		final int underscore = correctedName.lastIndexOf('_');
		if (underscore != -1 && underscore > 0) {
			final String usStr = correctedName.substring(underscore);
			if ((optionMaximumSqlLength - usStr.length()) < 0) {
				return correctedName;
			}
			return correctedName.substring(0, optionMaximumSqlLength - usStr.length()) + usStr;
		}

		return correctedName.substring(0, optionMaximumSqlLength);
	}

}