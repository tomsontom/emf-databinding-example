/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: MappingContext.java,v 1.33 2008/12/16 20:40:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.AbstractProcessingContext;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable;
import org.eclipse.emf.teneo.annotations.pannotation.Table;
import org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint;
import org.eclipse.emf.teneo.ecore.EModelResolver;
import org.eclipse.emf.teneo.extension.ExtensionInitializable;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerAware;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.SQLNameStrategy;
import org.eclipse.emf.teneo.simpledom.Document;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps a basic attribute with many=true, e.g. list of simpletypes.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.33 $
 */
public class MappingContext extends AbstractProcessingContext implements
		ExtensionPoint, ExtensionInitializable, ExtensionManagerAware {

	/** The xml document to which all elements are added */
	private Document mappingDoc;

	/** The current element, normally a class element */
	private Element currentElement;

	/** Mapping from eclass to entity name */
	private Map<EClass, String> entityNames = null;

	/**
	 * Keeps track of the list of featuremapmappers created for the current
	 * entity
	 */
	private final List<FeatureMapMapping> featureMapMappers = new ArrayList<FeatureMapMapping>();

	/** The list of eattributes for which a featuremap mapping was created */
	private final List<EAttribute> handledFeatureMapEAttributes = new ArrayList<EAttribute>();

	/** the mapper used for features */
	private FeatureMapper featureMapper;

	/**
	 * Is the current element a mixed or a feature map, in this case all
	 * features should be not required. TODO: check, does this work with
	 * embedded components in a feature map?
	 */
	private boolean currentElementFeatureMap = false;

	/** The current table element is set when an entity starts */
	private Table currentTable = null;

	/**
	 * The current column prefix, is used in case of multiple mixed types in one
	 * eclass.
	 */
	private String namePrefix = "";

	/** The current secondary table being processed. May be null. */
	private SecondaryTable currentSecondaryTable = null;

	/** The current eclass */
	protected EClass currentEClass = null;

	/** The current efeature being processed */
	protected EStructuralFeature currentEFeature = null;
	protected PAnnotatedEStructuralFeature currentAFeature = null;

	/** The entity mapper */
	private EntityMapper entityMapper;

	/** The extensionmanager used */
	private ExtensionManager extensionManager;

	/** The option to qualify entity names */
	private EntityNameStrategy entityNameStrategy = null;

	/** Version column name */
	private String versionColumnName = null;

	/** ID column name */
	private String idColumnName = null;

	/** Maximum column name */
	protected int maximumSqlNameLength = -1;

	/** The sql case strategy */
	protected SQLNameStrategy sqlNameStrategy;

	/** The escape character string used for escaping sql names */
	protected String escapeCharacter;

	/**
	 * Set force optional, force optional is used in case a subclass is stored
	 * in the same table as its superclass, in this case all properties of the
	 * subclass are denoted as optional.
	 */
	private boolean forceOptional = false;

	// Options
	private boolean alwaysVersion;
	private boolean isMapEMapAsTrueMap;
	private String idbagIDColumnName = "ID";

	// The pa model for which this is all done, is set when generation starts
	private PAnnotatedModel paModel = null;

	// The maximum comment length allowed
	private int maximumCommentLength = 0;

	private PersistenceOptions persistenceOptions;

	/** Returns the entitymapper */
	public EntityMapper getEntityMapper() {
		return entityMapper;
	}

	/** Set relevant properties */
	protected void setMappingProperties(PersistenceOptions po) {
		versionColumnName = po.getVersionColumnName();
		idColumnName = po.getIdColumnName();
		maximumSqlNameLength = po.getMaximumSqlNameLength();
		alwaysVersion = po.getAlwaysVersion();
		isMapEMapAsTrueMap = po.isMapEMapAsTrueMap();
		idbagIDColumnName = po.getIDBagIDColumnName();
		maximumCommentLength = po.getMaximumCommentLength();
		escapeCharacter = po.getSqlNameEscapeCharacter();
		persistenceOptions = po;
	}

	/** Return the concrete impl. class */
	protected String getInstanceClassName(EClass eClass) {
		final Class<?> clz = getInstanceClass(eClass);
		if (clz != null) {
			return clz.getName();
		}
		log
				.debug("Instance class for eclass " + eClass.getName()
						+ " is null ");
		return null;
	}

	/** Return the concrete impl. class, if none is found then null is returned */
	protected Class<?> getInstanceClass(EClassifier eclassifier) {
		return EModelResolver.instance().getJavaClass(eclassifier);
	}

	/**
	 * @return Returns the entity name for the given entity EClass.
	 */
	public String getEntityName(EClass entityEClass) {
		return getEntityName(entityEClass, true);
	}

	/**
	 * @return Returns the entity name for the given entity EClass.
	 */
	public String getEntityName(EClass entityEClass, boolean throwCheckException) {
		String name = entityNames.get(entityEClass);
		if (name == null) {
			final Class<?> implClass = getInstanceClass(entityEClass);
			if (implClass != null) {
				name = implClass.getName();
			}
		}
		if (throwCheckException && name == null) {
			throw new IllegalStateException(
					"An entity name has not been registered for "
							+ entityEClass);
		}
		return name;
	}

	/** Set an entityname for a eclass */
	public void setEntityName(EClass entityEClass, String entityName) {
		entityNames.put(entityEClass, entityName);
	}

	/** Start a document */
	public void beginDocument(Document draft) {
		mappingDoc = draft;
		currentElement = draft.getRoot();
		entityNames = new HashMap<EClass, String>();
	}

	/** Finished creating the document */
	public Document endDocument() {
		Document builtDocument = mappingDoc;
		mappingDoc = null;
		currentElement = null;
		entityNames = null;
		return builtDocument;
	}

	/** The current element to which new elements are added */
	public Element getCurrent() {
		return currentElement;
	}

	/** Set the current element to which new elements are added */
	public void setCurrent(Element newCurrent) {
		this.currentElement = newCurrent;
	}

	/**
	 * Note this call will also clear the current list of featuremappers;
	 * 
	 * @return the featureMapMappers gathered during the entity processing
	 */
	public List<FeatureMapMapping> getClearFeatureMapMappers() {
		final ArrayList<FeatureMapMapping> result = new ArrayList<FeatureMapMapping>(
				featureMapMappers); // clone
		// the
		// list!
		featureMapMappers.clear();
		return result;
	}

	/**
	 * @param Adds
	 *            a featureMapMapper to the featuremapp mapper list
	 */
	public void addFeatureMapMapper(FeatureMapMapping featureMapMapper) {
		if (!handledFeatureMapEAttributes.contains(featureMapMapper
				.getEAttribute())) {
			featureMapMappers.add(featureMapMapper);
			handledFeatureMapEAttributes.add(featureMapMapper.getEAttribute());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.teneo.extension.ExtensionInitializable#initializeExtension
	 * ()
	 */
	public void initializeExtension() {
		featureMapper = createFeatureMapper();
		entityMapper = getExtensionManager().getExtension(EntityMapper.class);
		entityMapper.setHbmContext(this);
	}

	/**
	 * @return The builder used by entity mapped that maps features to hbm.
	 */
	private FeatureMapper createFeatureMapper() {
		final FeatureMapper featureMapper = getExtensionManager().getExtension(
				FeatureMapper.class);
		featureMapper.setHbmContext(this);

		featureMapper.setBasicMapper(createMapper(BasicMapper.class));
		featureMapper
				.setManyAttributeMapper(createMapper(ManyAttributeMapper.class));
		featureMapper.setEmbeddedMapper(createMapper(EmbeddedMapper.class));
		featureMapper.setIdMapper(createMapper(IdMapper.class));
		featureMapper.setManyToManyMapper(createMapper(ManyToManyMapper.class));
		featureMapper.setManyToOneMapper(createMapper(ManyToOneMapper.class));
		featureMapper.setOneToManyMapper(createMapper(OneToManyMapper.class));
		featureMapper.setOneToOneMapper(createMapper(OneToOneMapper.class));
		featureMapper
				.setManyExternalReferenceMapper(createMapper(ManyExternalReferenceMapper.class));
		return featureMapper;
	}

	protected <T> T createMapper(Class<T> clz) {
		final T t = getExtensionManager().getExtension(clz);
		((AbstractMapper) t).setHbmContext(this);
		return t;
	}

	/** process the features of the annotated eclass */
	protected void processFeatures(List<PAnnotatedEStructuralFeature> features) {
		for (PAnnotatedEStructuralFeature annotatedEStructuralFeature : features) {
			entityMapper.processFeature(annotatedEStructuralFeature);
		}
	}

	/**
	 * @return the featureMapper
	 */
	public FeatureMapper getFeatureMapper() {
		return featureMapper;
	}

	/**
	 * @return the currentElementFeatureMap
	 */
	public boolean isCurrentElementFeatureMap() {
		return currentElementFeatureMap;
	}

	/**
	 * @param currentElementFeatureMap
	 *            the currentElementFeatureMap to set
	 */
	public void setCurrentElementFeatureMap(boolean currentElementFeatureMap) {
		this.currentElementFeatureMap = currentElementFeatureMap;
	}

	/**
	 * @return the currentTable
	 */
	public Table getCurrentTable() {
		return currentTable;
	}

	/**
	 * @param currentTable
	 *            the currentTable to set
	 */
	public void setCurrentTable(Table currentTable) {
		this.currentTable = currentTable;
	}

	/**
	 * @param currentSecondaryTable
	 */
	public void setCurrentSecondaryTable(SecondaryTable currentSecondaryTable) {
		this.currentSecondaryTable = currentSecondaryTable;
	}

	/** Get unique constraint key. */
	public String getUniqueConstraintKey(String colName) {
		// Obtain UniqueConstraints from secondary or primary table.
		List<UniqueConstraint> uniqueConstraints = null;
		if (currentSecondaryTable != null) {
			uniqueConstraints = currentSecondaryTable.getUniqueConstraints();
		} else if (currentTable != null) {
			uniqueConstraints = currentTable.getUniqueConstraints();
		}
		if (uniqueConstraints == null) {
			return null;
		}

		// NOTE: Hibernate does not support one column being part of multiple
		// unique constraints.
		for (int i = 0, n = uniqueConstraints.size(); i < n; i++) {
			UniqueConstraint uniqueConstraint = uniqueConstraints.get(i);
			if (uniqueConstraint.getColumnNames().contains(colName)) {
				return "c" + i;
			}

		}
		return null;
	}

	/**
	 * @return the versionColumnName
	 */
	public String getVersionColumnName() {
		return versionColumnName;
	}

	/**
	 * @param versionColumnName
	 *            the versionColumnName to set
	 */
	public void setVersionColumnName(String versionColumnName) {
		this.versionColumnName = versionColumnName;
	}

	/**
	 * @return the maximumColumnNameLength
	 */
	public int getMaximumColumnNameLength() {
		return maximumSqlNameLength;
	}

	/** Default is trunc */
	protected String trunc(String name) {
		return trunc(name, true);
	}

	/**
	 * Utility method to truncate a column/table name. This method also repairs
	 * the name if an efeature was inherited and really belongs to another
	 * eclass. In this case jointables and join keys must be renamed to the new
	 * eclass. TODO: handle the case that the jointable/columns were set
	 * manually. This procedure will override them (only applies in case of
	 * multiple inheritance/mappedsuperclass). This renaming is required for the
	 * case that an ereference is inherited from a mapped superclass, in this
	 * case the join-column of the e-reference will be placed in another table.
	 * If one ereference is inherited by multiple subtypes then this goes wrong
	 * because they then all share the same join column with foreign keys
	 * relating it to different tables, and multiple foreign keys on one column
	 * can not point to different directions.
	 * 
	 * This method is also called for table names.
	 * 
	 */
	protected String trunc(String truncName, boolean truncPrefix) {

		// see bugzilla 225818
		// in case of attributeoverride then do not repair the name
		final String useName;
		// method is also called for table names
		if (currentAFeature != null) {
			boolean override = false;
			if (currentAFeature instanceof PAnnotatedEAttribute) {
				override = getAttributeOverride(currentAFeature) != null;
			} else {
				override = getAssociationOverrides((PAnnotatedEReference) currentAFeature) != null;
			}

			final String otherEntityName = getEntityName(currentEFeature
					.getEContainingClass(), false);
			// if the current name starts with the name of the mapped superclass
			// then
			// change it back to the current eclass, do not do this in case of
			// override
			if (!override
					&& currentEFeature.getEContainingClass() != currentEClass
					&& otherEntityName != null
					&& truncName.toUpperCase().startsWith(
							otherEntityName.toUpperCase())) {
				log.debug("Replacing name of table/joincolumn " + truncName);
				// get rid of the first part
				useName = getNamePrefix()
						+ getEntityName(currentEClass)
						+ truncName
								.substring(getEntityName(
										currentEFeature.getEContainingClass())
										.length());
				log.debug("with " + useName + " because efeature is inherited");
				log
						.debug("This renaming does not work in case of manually specified joincolumn/table names and mappedsuperclass or multiple inheritance!");
			} else {
				useName = getNamePrefix() + truncName;
			}
		} else {
			useName = getNamePrefix() + truncName;
		}

		if (escapeCharacter.length() > 0
				&& useName.indexOf(escapeCharacter) == 0) {
			return getSqlNameStrategy().convert(useName, false);
		}

		return escapeCharacter + getSqlNameStrategy().convert(useName, false)
				+ escapeCharacter;
	}

	/**
	 * @return the idColumnName
	 */
	public String getIdColumnName() {
		return idColumnName;
	}

	/** Return the alwaysversion option */
	public boolean alwaysVersion() {
		return alwaysVersion;
	}

	/** Returns the list of eattrs, note list is updated outside of this object */
	public List<EAttribute> getHandledFeatureMapEAttributes() {
		return handledFeatureMapEAttributes;
	}

	/** Returns the correct property name */
	public String getPropertyName(EStructuralFeature ef) {
		return ef.getName();
	}

	/** Return the version property handler */
	public String getSyntheticVersionPropertyHandlerName() {
		return "org.eclipse.emf.teneo.hibernate.mapping.property.VersionPropertyHandler";
	}

	/** Return the id property handler */
	public String getSyntheticIdPropertyHandlerName() {
		return "org.eclipse.emf.teneo.hibernate.mapping.identifier.IdentifierPropertyHandler";
	}

	/** Return the standard property handler */
	public String getPropertyHandlerName() {
		return "";
	}

	/** Return the version property handler for a normal version field */
	public String getVersionPropertyHandlerName() {
		return "";
	}

	/** Return the id property handler for a normal id property handler */
	public String getIdPropertyHandlerName() {
		return "";
	}

	/** Return the component property handler */
	public String getComponentPropertyHandlerName() {
		return "";
	}

	/** Is this a dynamic eclass, i.e. it has no instanceclass */
	public boolean isDynamic(EClassifier eclassifier) {
		return !EModelResolver.instance().hasImplementationClass(eclassifier);
	}

	/**
	 * Use the implementation name as the mapping and never use entity-mapping,
	 * always false in this implementation
	 */
	public boolean forceUseOfInstance(PAnnotatedEClass aclass) {
		return false;
	}

	/**
	 * Returns true if the instance classes have been generated by emf. For
	 * Teneo this is always the case. Overriders can support a different
	 * generation strategy.
	 */
	public boolean isGeneratedByEMF() {
		return true;
	}

	/**
	 * There are four cases: EMF generated, EMF Dynamic, Easy EMF Generated,
	 * Easy EMF Dynamic public boolean isEasyEMFGenerated(EClassifier
	 * eclassifier) { return
	 * EModelResolver.instance().hasImplementationClass(eclassifier); } public
	 * boolean isEasyEMFDynamic(EClassifier eclassifier) { return
	 * !isEasyEMFGenerated(eclassifier) &&
	 * EModelResolver.instance().isRegistered( eclassifier.getEPackage()); }
	 * public boolean isEMFGenerated(EClassifier eclassifier) { return
	 * eclassifier.getInstanceClass() != null; }
	 */
	//
	// public boolean isEMFDynamic(EClassifier eclassifier) {
	// return !isEasyEMFDynamic(eclassifier) && !isEMFGenerated(eclassifier);
	// }
	//
	// /** Return the impl class */
	// public Class<?> getImpl(EClassifier eclassifier) {
	// return EModelResolver.instance().getJavaClass(eclassifier);
	// }
	//
	// /** Check if this is an entity (so without an impl class) */
	// public boolean hasImpl(PAnnotatedEStructuralFeature af) {
	// return EModelResolver.instance().hasImplementationClass(
	// af.getAnnotatedEStructuralFeature().getEContainingClass());
	// }
	/** Add a tuplizer element or not */
	public void addTuplizerElement(Element entityElement,
			PAnnotatedEClass aclass) {
	}

	/** Returns the enumusertype class name */
	public String getEnumUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.ENumUserType";
	}

	/** Returns the xml duration class name */
	public String getDurationType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.XSDDuration";
	}

	/** Returns the enum user type integer name */
	public String getEnumIntegerUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.ENumUserIntegerType";
	}

	/** Returns the enumusertype class name for the dynamic case */
	public String getDynamicEnumUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.DynamicENumUserType";
	}

	/** Returns the user type used to persist external references */
	public String getExternalUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.ExternalType";
	}

	/** Returns the enum user type integer name for the dynamic case */
	public String getDynamicEnumIntegerUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.DynamicENumUserIntegerType";
	}

	/** Return the default user type */
	public String getDefaultUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.DefaultToStringUserType";
	}

	/** Returns the default any type */
	public String getAnytype() {
		return "org.eclipse.emf.teneo.hibernate.mapping.AnyEObjectType";
	}

	/** Returns the usertype used to handle the xsd date */
	public String getXSDDateUserType() {
		// --- JJH
		return persistenceOptions.getUserXSDDateType();
		// return "org.eclipse.emf.teneo.hibernate.mapping.XSDDate";
		// --- JJH
	}

	/** Returns the usertype used to handle the xsd datetime */
	public String getXSDDateTimeUserType() {
		// --- JJH
		return persistenceOptions.getUserXSDDateTime();
		// return "org.eclipse.emf.teneo.hibernate.mapping.XSDDateTime";
		// --- JJH
	}

	/**
	 * @return the eclassNameStrategy
	 */
	public EntityNameStrategy getEntityNameStrategy() {
		if (entityNameStrategy == null) {
			entityNameStrategy = getExtensionManager().getExtension(
					EntityNameStrategy.class);
			entityNameStrategy.setPaModel(getPaModel()); // this call is not
			// really required
			// but
			// for safety reasons
		}
		return entityNameStrategy;
	}

	/**
	 * @return the isMapEMapAsTrueMap
	 */
	public boolean isMapEMapAsTrueMap() {
		return isMapEMapAsTrueMap;
	}

	/**
	 * @return the currentEClass
	 */
	public EClass getCurrentEClass() {
		return currentEClass;
	}

	/**
	 * @param currentEClass
	 *            the currentEClass to set
	 */
	public void setCurrentEClass(EClass currentEClass) {
		this.currentEClass = currentEClass;
	}

	/**
	 * @return the currentEFeature
	 */
	public EStructuralFeature getCurrentEFeature() {
		return currentEFeature;
	}

	/**
	 * @param currentEFeature
	 *            the currentEFeature to set
	 */
	public void setCurrentFeature(PAnnotatedEStructuralFeature currentFeature) {
		this.currentAFeature = currentFeature;
		this.currentEFeature = currentFeature == null ? null : currentFeature
				.getModelEStructuralFeature();
	}

	/**
	 * @return the idbagIDColumnName
	 */
	public String getIdbagIDColumnName() {
		return idbagIDColumnName;
	}

	/**
	 * @return the forceOptional
	 */
	public boolean isDoForceOptional(PAnnotatedEStructuralFeature aFeature) {
		final boolean hasSecondaryTable = getSecondaryTableName(aFeature) != null;
		return !hasSecondaryTable && forceOptional;
	}

	/**
	 * @param forceOptional
	 *            the forceOptional to set
	 */
	public void setForceOptional(boolean forceOptional) {
		this.forceOptional = forceOptional;
	}

	/**
	 * @return the paModel
	 */
	public PAnnotatedModel getPaModel() {
		return paModel;
	}

	/**
	 * @param paModel
	 *            the paModel to set
	 */
	public void setPaModel(PAnnotatedModel paModel) {
		this.paModel = paModel;
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
	 * @return the sqlNameStrategy
	 */
	public SQLNameStrategy getSqlNameStrategy() {
		if (sqlNameStrategy == null) {
			sqlNameStrategy = getExtensionManager().getExtension(
					SQLNameStrategy.class);
		}
		return sqlNameStrategy;
	}

	/**
	 * @return the maximumCommentLength
	 */
	public int getMaximumCommentLength() {
		return maximumCommentLength;
	}

	/**
	 * @return the namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @param namePrefix
	 *            the namePrefix to set
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * @return the escapeCharacter
	 */
	public String getEscapeCharacter() {
		return escapeCharacter;
	}

	/**
	 * @param escapeCharacter
	 *            the escapeCharacter to set
	 */
	public void setEscapeCharacter(String escapeCharacter) {
		this.escapeCharacter = escapeCharacter;
	}

	/**
	 * @return the persistenceOptions
	 */
	public PersistenceOptions getPersistenceOptions() {
		return persistenceOptions;
	}

	/**
	 * Returns the table name from the column annotation or the joincolumn
	 * annotation Also takes associationoverride or attributeoverride into
	 * account
	 */
	public String getSecondaryTableName(PAnnotatedEStructuralFeature pef) {
		String tableName = null;

		if (pef instanceof PAnnotatedEAttribute) {
			final PAnnotatedEAttribute pea = (PAnnotatedEAttribute) pef;
			Column c = getAttributeOverride(pea);
			if (c == null) {
				c = pef.getColumn();
			}
			if (c != null) {
				tableName = c.getTable();
			}
		} else {
			final PAnnotatedEReference per = (PAnnotatedEReference) pef;
			pushOverrideOnStack();
			addAttributeOverrides(per.getAttributeOverrides());
			addAssociationOverrides(per.getAssociationOverrides());

			try {
				if (per.getEmbedded() != null) {
					// check the embedded efeatures
					// take the first feature of the target type
					// assume that they are all handled in the same table
					final EClass eClass = per.getModelEReference()
							.getEReferenceType();
					for (EAttribute ea : eClass.getEAllAttributes()) {
						final Column c = getAttributeOverride(ea.getName());
						if (c != null && c.getTable() != null) {
							return c.getTable();
						}
					}
					for (EReference er : eClass.getEAllReferences()) {
						final List<JoinColumn> jcs = getAssociationOverrides(er
								.getName());
						if (jcs != null && jcs.size() > 0) {
							return jcs.get(0).getTable();
						}
					}
				} else {
					List<JoinColumn> jcs = getAssociationOverrides(per);
					if (jcs == null || jcs.size() == 0) {
						jcs = per.getJoinColumns();
					}
					if (jcs != null && jcs.size() > 0) {
						for (JoinColumn jc : jcs) {
							if (jc.getTable() != null) {
								tableName = jc.getTable();
								break;
							}
						}
					}
				}
			} finally {
				popOverrideStack();
			}
		}
		return tableName;
	}

}