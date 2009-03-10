/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * HbDataStore.java,v 1.21 2007/04/17 15:49:44 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.teneo.DataStore;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.TeneoException;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceMappingBuilder;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.classloader.StoreClassLoadException;
import org.eclipse.emf.teneo.ecore.EModelResolver;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerFactory;
import org.eclipse.emf.teneo.hibernate.mapper.HbMapperConstants;
import org.eclipse.emf.teneo.hibernate.mapper.HibernateMappingGenerator;
import org.eclipse.emf.teneo.hibernate.mapper.MappingUtil;
import org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerAccessor;
import org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerFeatureIDAccessor;
import org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerUserType;
import org.eclipse.emf.teneo.hibernate.mapping.elist.HibernateFeatureMapEntry;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResourceFactory;
import org.eclipse.emf.teneo.hibernate.resource.HibernateXMLResourceFactory;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.util.StoreUtil;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.HashtableCacheProvider;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.engine.CascadeStyle;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.ManyToOne;
import org.hibernate.mapping.OneToMany;
import org.hibernate.mapping.OneToOne;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.SimpleValue;
import org.hibernate.mapping.Table;
import org.hibernate.mapping.Value;

/**
 * Common base class for the standard hb datastore and the entity manager
 * oriented datastore.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.47 $
 */
public abstract class HbDataStore implements DataStore {

	/** The logger */
	private static Log log = LogFactory.getLog(HbDataStore.class);

	/** Initialize EMF */
	static {
		initializeTypes();
	}

	/** Initializes emf types with jpox */
	private static synchronized void initializeTypes() {
		log.debug("Initializing protocol/extension for hibernate");
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put(
				"hibernate", new HibernateResourceFactory());
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put("ehb",
				new HibernateResourceFactory());
		Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap().put(
				"hbxml", new HibernateXMLResourceFactory());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"hibernate", new HibernateResourceFactory());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"ehb", new HibernateResourceFactory());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
				"hbxml", new HibernateXMLResourceFactory());
	}

	/** HashMap with referers */
	private HashMap<String, java.util.List<ReferenceTo>> referers;

	/** The array with entities (eclasses) which are not contained */
	private String[] topEntities;

	/** The name under which it is registered */
	private String name;

	/** The list of epackages stored in the datastore */
	private EPackage[] ePackages;

	private EPackageConstructor ePackageConstructor = null;

	/** Update the schema option */
	// private boolean updateSchema = true;
	/** The hb context */
	private HbContext hbContext = null;

	/** The pannotated model, is set in the mapEPackages method */
	private PAnnotatedModel paModel = null;

	/** The properties used to create the hibernate configuration object */
	private PersistenceOptions persistenceOptions;

	/** The properties */
	private Properties properties = new Properties();

	/** The interceptor */
	private Interceptor interceptor;

	/**
	 * The used mapping if not passed through a hbm file, can be retrieved for
	 * debugging purposes
	 */
	private String mappingXML = null;

	/** The extensionManager */
	private ExtensionManager extensionManager;

	/** the entitynamestrategy is read from the extensionManager */
	private EntityNameStrategy entityNameStrategy;

	/**
	 * @return the dsName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the epackages
	 */
	public EPackage[] getEPackages() {
		if (ePackages == null && ePackageConstructor != null) {
			final java.util.List<EPackage> ePacks = ePackageConstructor
					.getEPackages();
			final EPackage[] ePacksArray = new EPackage[ePacks.size()];
			int i = 0;
			for (EPackage ePack : ePacks) {
				ePacksArray[i++] = ePack;
			}
			setEPackages(ePacksArray);
		}

		return ePackages;
	}

	/**
	 * @param epackages
	 *            the epackages to set
	 */
	public void setEPackages(EPackage[] epackages) {
		// automatically add EPackage
		final List<EPackage> epacks = new ArrayList<EPackage>();
		for (EPackage epack : epackages) {
			resolveSubPackages(epack, epacks);
		}

		this.ePackages = epacks.toArray(new EPackage[epacks.size()]);
	}

	private void resolveSubPackages(EPackage epack, List<EPackage> epacks) {
		if (!epacks.contains(epack)) {
			epacks.add(epack);
		}

		for (EPackage subEPackage : epack.getESubpackages()) {
			resolveSubPackages(subEPackage, epacks);
		}
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
		HbHelper.INSTANCE.register(this);
	}

	/**
	 * The entities (eclasses) which are not contained in another eclass.
	 * 
	 * @return the topEntities
	 */
	public String[] getTopEntities() {
		return topEntities;
	}

	/** Initialize the subclass */
	public abstract void initialize();

	/** Initializes this Data Store */
	protected void initializeDataStore() {
		// buildmappings has to be done before setting the tuplizers because
		// buildMappings will ensure that the element
		// is set in the List properties.
		buildMappings();

		setInterceptor();

		log.debug("Determine referers for each class");

		referers = computeReferers();

		topEntities = computeTopEntities();

		// now add the econtainer mappings to the contained types, only for
		// unidirectional container relations
		addContainerMappings();

		setTuplizer();

		// set the event listeners
		setEventListeners();

		if (getPersistenceOptions().isUpdateSchema()) {
			log
					.warn("The teneo update schema option is not used anymore for hibernate, use the hibernate option: hibernate.hbm2ddl.auto");
		}

		log.debug("Registering datastore with persistent classes");
		HbHelper.INSTANCE.registerDataStoreByPC(this);
	}

	/** Build the mappings in the configuration */
	protected abstract void buildMappings();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.jpox.emf.IEMFDataStore#close()
	 */
	public abstract void close();

	/** Return the hibernate configuration */
	protected abstract Configuration getHibernateConfiguration();

	/**
	 * Gets the persistence options. The persistence options is a type
	 * representation of the persistence options. If not set through the
	 * setPersistenceProperties method then a properties file is searched If
	 * found it is used to set the persistence options.
	 * <p>
	 * If no properties have been set explicitly, the method will attempt to
	 * load them from the file "/elver-persistence.properties" at the root of
	 * the classpath. (A mechanism similar to "hibernate.properties".)
	 * 
	 * @throws HbMapperException
	 *             if an error occured reading the properties file.
	 * @return the persistence options as a Properties instance.
	 */
	public PersistenceOptions getPersistenceOptions() {
		if (persistenceOptions == null) {
			final Properties props = new Properties();
			final InputStream in = this.getClass().getResourceAsStream(
					PersistenceOptions.DEFAULT_CLASSPATH_FILENAME);
			if (in != null) {
				try {
					props.load(in);
				} catch (IOException e) {
					throw new HbMapperException(e);
				} finally {
					try {
						in.close();
					} catch (IOException e) {
						throw new HbMapperException(e);
					}
				}
			}
			persistenceOptions = getExtensionManager().getExtension(
					PersistenceOptions.class, new Object[] { props });
		}
		return persistenceOptions;
	}

	/**
	 * Sets the persistence options.
	 * 
	 * @deprecated use setProperties
	 */
	@Deprecated
	public void setPersistenceProperties(Properties persistenceOptions) {
		this.persistenceOptions = getExtensionManager().getExtension(
				PersistenceOptions.class, new Object[] { persistenceOptions });
	}

	/**
	 * @deprecated use getProperties
	 */
	@Deprecated
	public Properties getHibernateProperties() {
		return properties;
	}

	/**
	 * @deprecated use getProperties
	 */
	@Deprecated
	public Properties getPersistenceProperties() {
		return persistenceOptions.getProperties();
	}

	/**
	 * @deprecated use getProperties
	 */
	@Deprecated
	public void setHibernateProperties(Properties hibernateProperties) {
		this.properties = hibernateProperties;
	}

	/** Sets both the persistence as well as the hibernate properties */
	public void setProperties(Properties props) {
		this.persistenceOptions = getExtensionManager().getExtension(
				PersistenceOptions.class, new Object[] { props });
		this.properties = props;
	}

	protected void setDefaultProperties(Properties properties) {
		if (properties.getProperty("hibernate.cache.provider_class") == null) {
			log.warn("No hibernate cache provider set, using "
					+ HashtableCacheProvider.class.getName());
			log
					.warn("For production use please set the ehcache (or other) provider explicitly and configure it");
			properties.setProperty("hibernate.cache.provider_class",
					HashtableCacheProvider.class.getName());
		}
		final String hbmUpdate = properties
				.getProperty(Environment.HBM2DDL_AUTO);
		if (hbmUpdate == null) {
			log.info("Hibernate property: " + Environment.HBM2DDL_AUTO
					+ " not set, setting to update");
			properties.setProperty(Environment.HBM2DDL_AUTO, "update");
		}
		log.debug("Setting properties in Hibernate Configuration:");
		logProperties(properties);
	}

	/** Returns the combined hibernate and persistence properties */
	public Properties getProperties() {
		final Properties props = new Properties();
		props.putAll(properties);
		props.putAll(getPersistenceOptions().getProperties());
		return props;
	}

	/** Get the session factory */
	public abstract SessionFactory getSessionFactory();

	/** Return a new session wrapper */
	public abstract SessionWrapper createSessionWrapper();

	/** Is the store initialized */
	private boolean initialized = false;

	/**
	 * @return the hbContext
	 */
	public HbContext getHbContext() {
		if (hbContext == null) {
			hbContext = getExtensionManager().getExtension(HbContext.class);
		}
		return hbContext;
	}

	/**
	 * @param hbContext
	 *            the hbContext to set
	 */
	public void setHbContext(HbContext hbContext) {
		hbContext.setExtensionManager(getExtensionManager());
		this.hbContext = hbContext;
	}

	/** Return the Classmappings as an iterator */
	protected abstract Iterator<?> getClassMappings();

	/**
	 * Returns an array of EObjects and FeatureMapEntries which refer to a
	 * certain EObject, note if the array is of length zero then no refering
	 * EObjects where found. The passed Session is used to create a query. The
	 * transaction handling should be done by the caller.
	 */
	public Object[] getCrossReferencers(Session session, Object referedTo) {
		final ArrayList<Object> result = getCrossReferencers(
				new HbSessionWrapper(this, session), referedTo, false);

		return result.toArray(new Object[result.size()]);
	}

	/**
	 * Returns an array of EObjects and FeatureMapEntries which refer to a
	 * certain EObject, note if the array is of length zero then no refering
	 * EObjects where found. The passed Session is used to create a query. The
	 * transaction handling should be done by the caller.
	 */
	public Object[] getCrossReferencers(SessionWrapper sessionWrapper,
			Object referedTo) {
		final ArrayList<Object> result = getCrossReferencers(sessionWrapper,
				referedTo, false);
		return result.toArray(new Object[result.size()]);
	}

	/**
	 * Returns an array of EObjects which refer to a certain EObject, note if
	 * the array is of length zero then no refering EObjects where found. The
	 * passed Session is used to create a query. The transaction handling should
	 * be done by the caller. onlyContainers means to only check containment
	 * relations.
	 */
	private ArrayList<Object> getCrossReferencers(
			SessionWrapper sessionWrapper, Object referedTo,
			boolean onlyContainers) {
		assert (referedTo != null);

		String targetEntityName = null;
		if (referedTo instanceof EObject) {
			final EObject eReferedTo = (EObject) referedTo;
			targetEntityName = getEntityNameStrategy().toEntityName(
					eReferedTo.eClass());
		} else if (referedTo instanceof HibernateFeatureMapEntry) {
			final HibernateFeatureMapEntry fme = (HibernateFeatureMapEntry) referedTo;
			targetEntityName = fme.getEntityName();
		} else {
			throw new IllegalArgumentException("Non eobject not yet supported "
					+ referedTo.getClass().getName());
		}

		final java.util.List<ReferenceTo> refersList = referers
				.get(targetEntityName);
		if (refersList == null || refersList.size() == 0) {
			return new ArrayList<Object>();
		}
		final ArrayList<Object> result = new ArrayList<Object>();
		for (int i = 0; i < refersList.size(); i++) {
			final ReferenceTo refersTo = refersList.get(i);

			// if we only check containment relations then skip this
			if (onlyContainers && !refersTo.isContainer()) {
				continue;
			}

			final java.util.List<?> list = sessionWrapper.executeQuery(refersTo
					.getQueryStr(), "to", referedTo);
			for (Object obj : list) {
				if (obj instanceof HibernateFeatureMapEntry) {
					// search then again with the
					final ArrayList<Object> fms = getCrossReferencers(
							sessionWrapper, obj, false);
					if (fms.size() == 0) {
						new AssertionError(
								"The featuremap for featuremap entry "
										+ obj.getClass().getName()
										+ " can not be found");
					}
					obj = fms.get(0);
				}

				// AssertUtil.assertTrue("Getting refersto of " +
				// referedTo.getClass().getName() +
				// ", however one of the refersto is not an eobject but a " +
				// obj.getClass().getName(),
				// obj instanceof EObject);

				if (!result.contains(obj)) {
					result.add(obj);
				}
			}
		}

		return result;
	}

	/** Compute the top eclasses */
	protected String[] computeTopEntities() {
		final ArrayList<String> result = new ArrayList<String>();
		for (Iterator<?> pcs = getClassMappings(); pcs.hasNext();) {
			final PersistentClass pc = (PersistentClass) pcs.next();

			java.util.List<ReferenceTo> refs = referers.get(getMappedName(pc));
			boolean topEntity = true;
			if (refs != null) {
				for (ReferenceTo referenceTo : refs) {
					ReferenceTo rt = referenceTo;
					if (rt.isContainer) {
						topEntity = false;
						break;
					}
				}
			}
			try {
				// see bugzilla 220106
				if (topEntity && (pc.isAbstract() == null || !pc.isAbstract())) {
					result.add(getMappedName(pc));
				}
			} catch (Exception e) {
				e.printStackTrace(System.err);
				throw new TeneoException(e.getMessage(), e);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	/** Adds a econtainer mapping to the class mapping */
	protected void addContainerMappings() {
		if (getPersistenceOptions().isDisableEContainerMapping()) {
			log.debug("EContainer mapping disabled.");
			return;
		}
		for (Iterator<?> pcs = getClassMappings(); pcs.hasNext();) {
			final PersistentClass pc = (PersistentClass) pcs.next();

			// if a featuremap then just return
			if (HbUtil.getEClassNameFromFeatureMapMeta(pc) != null) {
				continue;
			}

			// check if container is required is done in the
			// addContainerMapping call
			addContainerMapping(pc);
		}
	}

	/** Sets the tuplizer */
	protected void setTuplizer() {
		for (Iterator<?> pcs = getClassMappings(); pcs.hasNext();) {
			final PersistentClass pc = (PersistentClass) pcs.next();
			if (pc.getMetaAttribute(HbMapperConstants.FEATUREMAP_META) != null) { // featuremap
				// entry
				pc.addTuplizer(EntityMode.MAP,
						getHbContext().getFeatureMapEntryTuplizer(
								getHibernateConfiguration()).getName());
			} else if (pc.getMetaAttribute(HbMapperConstants.ECLASS_NAME_META) != null) {
				// only the pc's with this meta should get a tuplizer

				pc.addTuplizer(EntityMode.MAP, getHbContext()
						.getEMFTuplizerClass(getHibernateConfiguration())
						.getName());
				pc.addTuplizer(EntityMode.POJO, getHbContext()
						.getEMFTuplizerClass(getHibernateConfiguration())
						.getName());
			} else if (pc.getMetaAttribute(HbMapperConstants.ECLASS_NAME_META) == null) {
				// don't change these pc's any further, these are not eclasses
				continue;
			}

			// also set the tuplizer for the components, and register for the
			// component

			// Build a list of all properties.
			java.util.List<Property> properties = new ArrayList<Property>();
			final Property identifierProperty = pc.getIdentifierProperty();
			if (identifierProperty != null) {
				properties.add(identifierProperty);
			}
			for (Iterator<?> it = pc.getPropertyIterator(); it.hasNext();) {
				properties.add((Property) it.next());
			}

			// Now set component tuplizers where necessary.
			for (Object name2 : properties) {
				Property prop = (Property) name2;
				if (prop.getName().compareTo("_identifierMapper") == 0) {
					continue; // ignore this one
				}
				final Value value = prop.getValue();
				if (value instanceof Component) {
					setComponentTuplizer((Component) value,
							getHibernateConfiguration());
				} else if (value instanceof Collection
						&& ((Collection) value).getElement() instanceof Component) {
					setComponentTuplizer((Component) ((Collection) value)
							.getElement(), getHibernateConfiguration());
				}
			}
		}
	}

	/** Set the event listener, can be overridden, in this impl. it does nothing */
	protected void setEventListeners() {
	}

	/**
	 * Sets the emf component tuplizer (if it is an eclass) or the hibernate
	 * component tuplizer
	 */
	protected void setComponentTuplizer(Component component, Configuration cfg) {
		// check if the eclass exists
		// todo: change recognizing a component to using metadata!
		final EClass eClass = getEntityNameStrategy().toEClass(
				component.getComponentClassName());
		if (eClass != null) {
			log.debug("Found " + eClass.getName() + " as a component");
		} else {
			return;
		}
		// is a
		// valid
		// eclass
		component.addTuplizer(EntityMode.MAP, getHbContext()
				.getEMFComponentTuplizerClass(cfg).getName());
		component.addTuplizer(EntityMode.POJO, getHbContext()
				.getEMFComponentTuplizerClass(cfg).getName());
		HbHelper.INSTANCE.registerDataStoreByComponent(this, component);
	}

	/** Returns true if the pc is contained */
	private boolean isContained(PersistentClass pc) {
		java.util.List<ReferenceTo> refs = referers.get(getMappedName(pc));
		if (refs == null) {
			return false;
		}
		for (ReferenceTo rt : refs) {
			if (rt.isContainer) {
				return true;
			}
		}
		return false;
	}

	/** Sets initialized */
	protected void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * Gets the initialized state.
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/** Sets the interceptor */
	protected abstract void setInterceptor();

	/** Generate a hibernate mapping xml string from a set of epackages */
	protected String mapEPackages() {
		log.debug("Generating mapping file from in-mem ecore");
		// DCB: Use Hibernate-specific annotation processing mechanism. This
		// allows use of
		// Hibernate-specific annotations.
		final PersistenceOptions po = getPersistenceOptions();
		setPaModel(getExtensionManager().getExtension(
				PersistenceMappingBuilder.class).buildMapping(getEPackages(),
				po, getExtensionManager()));
		final HibernateMappingGenerator hmg = getExtensionManager()
				.getExtension(HibernateMappingGenerator.class);
		hmg.setPersistenceOptions(po);
		return hmg.generateToString(getPaModel());
	}

	/** Recursively check the container prop in the super hierarchy */
	private boolean hasEContainerProp(PersistentClass pc) {
		final Iterator<?> it = pc.getPropertyIterator();
		while (it.hasNext()) {
			final Property prop = (Property) it.next();
			if (prop.getName().equals(HbConstants.PROPERTY_ECONTAINER)) {
				return true;
			}
		}
		if (pc.getSuperclass() == null) {
			return false;
		}
		return hasEContainerProp(pc.getSuperclass());
	}

	/** Updates the database schema */
	// this method is removed because it is just as easy to use the hibernate
	// option
	// hibernate.hbm2ddl.auto directly
	// protected void updateDatabaseSchema() {
	// if (getPersistenceOptions().isUpdateSchema()) {
	// log.debug("Database schema not updated, option " +
	// PersistenceOptions.UPDATE_SCHEMA +
	// " has been set to false");
	// return;
	// }
	// log.debug("Starting update of schema");
	// new SchemaUpdate(getHibernateConfiguration()).execute(false, true);
	// log.debug(">>> Update of schema finished");
	// }
	/**
	 * Adds a econtainer mapping to the class mapping, is only called for
	 * eclasses which do not have am explicit feature which points to the
	 * container
	 */
	protected void addContainerMapping(PersistentClass pc) {

		// always first check if the super class should have a container mapping
		if (pc.getSuperclass() != null) {
			addContainerMapping(pc.getSuperclass());
		}

		if (!isContained(pc)) {
			return;
		}
		if (hasEContainerProp(pc)) {
			return;
		}

		log.debug("Adding container mapping for " + getMappedName(pc));
		// check if there are not alreadyecontai ner features for the eclass

		final EClass eclass;
		if (pc.getEntityName() != null) {
			eclass = getEntityNameStrategy().toEClass(pc.getEntityName());
		} else {
			eclass = EModelResolver.instance().getEClass(pc.getMappedClass());
		}

		// DCB: Provide a way to avoid container mappings for a particular
		// class. You'd do this if, for example,
		// you never load the contained objects except through the containers...
		// or, you don't fit the use case
		// for which this was put together (i.e., the generated model editing
		// code tries to eagerly resolve the
		// container)
		if (eclass == null
				|| eclass
						.getEAnnotation("http://facet.elver.org/SkipContainerMappings") != null) {
			return; // featuremap
		}

		for (EReference eref : eclass.getEAllReferences()) {
			if (eref.isContainer()) {
				log
						.debug("There are container ereferences present, assuming that no separate econtainer columns are required.");
				return;
			}
		}

		log.debug("Adding eContainer and econtainerfeatureid properties to "
				+ pc.getClassName());

		final Property eContainer = new Property();
		eContainer.setName(HbConstants.PROPERTY_ECONTAINER);
		eContainer.setMetaAttributes(new HashMap<Object, Object>());
		eContainer.setNodeName(eContainer.getName());
		eContainer.setPropertyAccessorName(EContainerAccessor.class.getName());

		final SimpleValue sv = new SimpleValue(pc.getTable());
		sv.setTypeName(EContainerUserType.class.getName());

		final Column eccColumn = new Column(HbConstants.COLUMN_ECONTAINER_CLASS);
		sv.addColumn(checkColumnExists(pc.getTable(), eccColumn));

		final Column ecColumn = new Column(HbConstants.COLUMN_ECONTAINER);
		sv.addColumn(checkColumnExists(pc.getTable(), ecColumn));

		eContainer.setValue(sv);
		pc.addProperty(eContainer);

		final Property ecFID = new Property();
		ecFID.setName(HbConstants.PROPERTY_ECONTAINER_FEATURE_ID);
		ecFID.setMetaAttributes(new HashMap<Object, Object>());
		ecFID.setNodeName(ecFID.getName());
		ecFID.setPropertyAccessorName(EContainerFeatureIDAccessor.class
				.getName());
		final SimpleValue svfid = new SimpleValue(pc.getTable());
		svfid.setTypeName("integer");

		final Column ecfColumn = new Column(
				HbConstants.COLUMN_ECONTAINER_FEATUREID);
		svfid.addColumn(checkColumnExists(pc.getTable(), ecfColumn));

		ecFID.setValue(svfid);
		pc.addProperty(ecFID);
	}

	/** Checks if a certain column already exists in a class */
	private Column checkColumnExists(Table table, Column searchCol) {
		final Column foundCol = table.getColumn(searchCol);
		if (foundCol != null) {
			return foundCol;
		}
		table.addColumn(searchCol);
		return searchCol;
	}

	/**
	 * Checks if the passed object is by any change a contained object and if so
	 * returns true
	 */
	public boolean isContainedObject(Object obj) {
		// TODO also check containment for superclasses
		throw new UnsupportedOperationException("Operation is not supported");

		// final ArrayList theReferers = (ArrayList)
		// referers.get(obj.getClass().getName());
		// if (theReferers == null || theReferers.size() == 0)
		// return false;
		// for (int i = 0; i < theReferers.size(); i++) {
		// final ReferenceTo refTo = (ReferenceTo) theReferers.get(i);
		// if (refTo.isContainer())
		// return true;
		// }
		// return false;
	}

	/**
	 * Import the complete content from an inputstream into the EMF Data Store.
	 * The ExportTarget is the constant defined in the EMFDataStore interface.
	 */
	public void importDataStore(InputStream is, int importFormat) {
		final Resource importResource;
		if (importFormat == HbConstants.EXCHANGE_FORMAT_XML) {
			importResource = new XMLResourceImpl();
		} else {
			importResource = new XMIResourceImpl();
		}

		final HibernateResource hibResource = new HibernateResource(URI
				.createFileURI("." + name));

		try {
			importResource.load(is, Collections.EMPTY_MAP);
			hibResource.getContents().addAll(importResource.getContents());
			hibResource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new HbMapperException("Exception when importing " + name, e);
		}
	}

	/**
	 * Export the complete content of the EMF Data Store to an outputstream, the
	 * exportFormat is a HbConstants.EXCHANGE_FORMAT_XML or
	 * HbConstants.EXCHANGE_FORMAT_XMI, the encoding can be null and is used to
	 * set XMLResource.OPTION_ENCODING.
	 */
	public void exportDataStore(OutputStream os, int exportFormat,
			String encoding) {
		final HibernateResource hibResource = new HibernateResource(URI
				.createFileURI("." + name));
		hibResource.load(Collections.EMPTY_MAP);

		try {
			final Resource exportResource;
			if (exportFormat == HbConstants.EXCHANGE_FORMAT_XML) {
				exportResource = new XMLResourceImpl();
			} else {
				exportResource = new XMIResourceImpl();
			}

			exportResource.getContents().addAll(hibResource.getContents());

			final HashMap<String, String> options = new HashMap<String, String>();
			if (encoding != null) {
				options.put(XMLResource.OPTION_ENCODING, encoding);
			}

			exportResource.save(os, options);

			hibResource.unload();
		} catch (IOException e) {
			throw new HbMapperException("Exception when exporting " + name, e);
		}
	}

	/**
	 * Computes the referers, handles the lazy for containment
	 */
	protected HashMap<String, java.util.List<ReferenceTo>> computeReferers() {
		final HashMap<String, java.util.List<ReferenceTo>> result = new HashMap<String, java.util.List<ReferenceTo>>();

		final Iterator<?> it = getClassMappings();
		final ArrayList<String> fmes = new ArrayList<String>();
		while (it.hasNext()) {
			final PersistentClass pc = (PersistentClass) it.next();

			// keep track which are the feature map entries
			if (pc.getMetaAttribute(HbMapperConstants.FEATUREMAP_META) != null) {
				fmes.add(getMappedName(pc));
			}

			// everyone should have a list otherwise the copying of referers to
			// super types to
			// this type does not work
			if (result.get(getMappedName(pc)) == null) {
				result.put(getMappedName(pc), new ArrayList<ReferenceTo>());
			}

			final Iterator<?> propIt = pc.getPropertyIterator();
			while (propIt.hasNext()) {
				// handle few cases
				// OneToOne or ManyToOne, referenced class can be obtained from
				// Value and then getReferencedEntityName
				// List: in this case search for a structural feature and get
				// the EType from it
				// if no structural feature then use the type name and hope for
				// the best.

				final Property prop = (Property) propIt.next();
				EClass eClass = null;
				if (pc.getMetaAttribute(HbMapperConstants.FEATUREMAP_META) == null) {
					if (pc.getEntityName() != null) {
						eClass = getEntityNameStrategy().toEClass(
								pc.getEntityName());
					} else {
						eClass = EModelResolver.instance().getEClass(
								pc.getMappedClass());
					}
				}

				final EStructuralFeature ef = eClass == null ? null : StoreUtil
						.getEStructuralFeature(eClass, prop.getName());
				try {
					String toEntity = "";
					boolean isContainer = false;
					boolean isMany = false;
					if (prop.getValue() instanceof ManyToOne) {
						final ManyToOne mto = (ManyToOne) prop.getValue();
						toEntity = mto.getReferencedEntityName();
						if (ef != null) {
							isContainer = ef instanceof EReference
									&& ((EReference) ef).isContainment();
						} else {
							isContainer = prop.getCascadeStyle()
									.hasOrphanDelete()
									|| prop.getCascade().compareTo("all") == 0; // ugly
							// but
						}
						// this was
						// the only
						// way to
						// get all
						// there!
					} else if (prop.getValue() instanceof OneToOne) {
						final OneToOne oto = (OneToOne) prop.getValue();
						toEntity = oto.getReferencedEntityName();
						if (ef != null) {
							isContainer = ef instanceof EReference
									&& ((EReference) ef).isContainment();
						} else {
							isContainer = prop.getCascadeStyle()
									.hasOrphanDelete()
									|| prop.getCascadeStyle() == CascadeStyle.ALL;
						}
					} else if (prop.getValue() instanceof Collection) {
						isMany = true;
						if (ef == null) { // TODO can this happen?
							isContainer = prop.getCascadeStyle()
									.hasOrphanDelete()
									|| prop.getCascadeStyle() == CascadeStyle.ALL;
							if (((Collection) prop.getValue()).getElement() instanceof OneToMany) {
								final Collection coll = (Collection) prop
										.getValue();
								toEntity = ((OneToMany) coll.getElement())
										.getReferencedEntityName();
							} else if (((Collection) prop.getValue())
									.getElement() instanceof ManyToOne) {
								final Collection coll = (Collection) prop
										.getValue();
								toEntity = ((ManyToOne) coll.getElement())
										.getReferencedEntityName();
							} else {
								throw new HbMapperException("Type "
										+ ((Collection) prop.getValue())
												.getElement().getClass()
												.getName() + " not supported");
							}
						} else {
							// in case of featuremap set containment always on
							// true because only the featuremap entries
							// themselves know if they are containment
							if (ef instanceof EAttribute
									&& ((EAttribute) ef).getEType()
											.getInstanceClass() == Entry.class) {
								isContainer = true;
								final OneToMany otm = (OneToMany) ((Collection) prop
										.getValue()).getElement();
								toEntity = otm.getReferencedEntityName();
							} else if (ef instanceof EReference) {
								final EReference er = (EReference) ef;
								isContainer = er.isContainment(); // prop.getCascadeStyle().
								// hasOrphanDelete()
								// ||
								// prop.getCascadeStyle()
								// ==
								// CascadeStyle.ALL;
								toEntity = getEntityNameStrategy()
										.toEntityName(
												((EReference) ef)
														.getEReferenceType());
							} else if (ef instanceof EAttribute
									&& ef.getEType() instanceof EClass) { // TODO
								// can
								// this
								// ever
								// happen?
								isContainer = true; // prop.getCascadeStyle().hasOrphanDelete()
								// || prop.getCascadeStyle()
								// == CascadeStyle.ALL;
								toEntity = getEntityNameStrategy()
										.toEntityName((EClass) ef.getEType());
							}
							// filter out non eobjects
							else {
								continue;
							}
						}
					} else {
						continue;
					}

					java.util.List<ReferenceTo> list = result.get(toEntity);
					if (list == null) {
						list = new ArrayList<ReferenceTo>();
						result.put(toEntity, list);
					}

					list.add(new ReferenceTo(getMappedName(pc), prop,
							isContainer, isMany, toEntity));
				} catch (StoreClassLoadException e) {
					throw new HbMapperException(
							"Class not found using property: " + prop.getName()
									+ " of " + prop, e);
				}
			}
		}

		// at the end for each class all the refersto of superclasses and
		// interfaces are added also
		final ArrayList<EClass> classDone = new ArrayList<EClass>();
		for (String em : result.keySet()) {
			// only do this if not a fme
			if (!fmes.contains(em)) {
				setRefersToOfSupers(em, result, classDone);
			}
		}
		return result;
	}

	/** Returns either the entityname or the classname, which ever is filled */
	private String getMappedName(PersistentClass pc) {
		if (pc.getEntityName() != null) {
			return pc.getEntityName();
		}
		return pc.getClassName();
	}

	/**
	 * Add the refersto for each superclass/interface to the subclass refersto
	 * list. As a convenience returns the set list
	 */
	private java.util.List<ReferenceTo> setRefersToOfSupers(String eClassUri,
			HashMap<String, java.util.List<ReferenceTo>> refersTo,
			ArrayList<EClass> classDone) {
		final EntityNameStrategy ens = getEntityNameStrategy();
		EClass eclass;
		// eclass = null when the refered to eclass is not mapped
		// because it is is embeddable
		eclass = ens.toEClass(eClassUri);
		if (eclass == null) {
			return new ArrayList<ReferenceTo>();
		}

		if (classDone.contains(eclass)) {
			return refersTo.get(eClassUri);
		}

		final java.util.List<ReferenceTo> thisList = refersTo.get(ens
				.toEntityName(eclass));
		if (thisList == null) {
			return new ArrayList<ReferenceTo>();
		}
		for (EClass class1 : eclass.getESuperTypes()) {
			String eclassUri = ens.toEntityName(class1);
			addUnique(thisList, setRefersToOfSupers(eclassUri, refersTo,
					classDone));
		}
		classDone.add(eclass);
		return thisList;
	}

	/** Adds list 2 to list 1 without duplicates */
	private void addUnique(java.util.List<ReferenceTo> l1,
			java.util.List<ReferenceTo> l2) {
		if (l2 == null) {
			return; // this is a valid situation so do nothing
		}

		final Iterator<ReferenceTo> it = l2.iterator();
		while (it.hasNext()) {
			final ReferenceTo obj = it.next();
			if (!l1.contains(obj)) {
				l1.add(obj);
			}
		}
	}

	/** Dump properties in the log */
	protected void logProperties(Properties props) {
		final Iterator<?> it = props.keySet().iterator();
		while (it.hasNext()) {
			final String key = (String) it.next();
			log.info(key + ": " + props.get(key));
		}
	}

	/** Contains the reference to a class which refers to another reference */
	public static class ReferenceTo {

		/** Is contained */
		private final boolean isContainer;

		/** The query used to find the occurence */
		private final String qryStr;

		/** Constructor */
		public ReferenceTo(String fromEntity, Property prop,
				boolean isContainer, boolean isMany, String toEntity) {
			this.isContainer = isContainer;
			if (isMany) {
				qryStr = "SELECT ref FROM "
						+ fromEntity
						+ " as ref, "
						+ toEntity
						+ " as refTo WHERE refTo = :to and refTo in elements(ref."
						+ prop.getName() + ")";
			} else {
				qryStr = "SELECT ref FROM " + fromEntity
						+ " as ref WHERE :to = ref." + prop.getName();
			}
		}

		/**
		 * @return Returns the isContainer.
		 */
		public boolean isContainer() {
			return isContainer;
		}

		/** Returns the query string used used */
		public String getQueryStr() {
			return qryStr;
		}
	}

	/**
	 * Return the list of mapping files. If the mapping file path property of
	 * persistenceoptions was set then this is returned, otherwise the classpath
	 * is searched for the mapping file.
	 */
	protected String[] getMappingFileList() {
		if (getPersistenceOptions().getMappingFilePath() != null) {
			log.debug("Using specified list of mapping files "
					+ getPersistenceOptions().getMappingFilePath());
			return getPersistenceOptions().getMappingFilePath().split(",");
		} else if (getPersistenceOptions().isUseMappingFile()) {
			// register otherwise the getFileList will not work
			EModelResolver.instance().register(getEPackages());

			log.debug("Searching hbm files in class paths of epackages");
			return StoreUtil.getFileList(HbConstants.HBM_FILE_NAME, null);
		} else {
			throw new HbStoreException(
					"This method may only be called if either the useMappingFile property or the MappingFilePath property has been set");
		}
	}

	/**
	 * @return the mappingXML
	 */
	public String getMappingXML() {
		return mappingXML;
	}

	/**
	 * @param mappingXML
	 *            the mappingXML to set
	 */
	public void setMappingXML(String mappingXML) {
		this.mappingXML = mappingXML;
	}

	/**
	 * @return the interceptor
	 */
	public Interceptor getInterceptor() {
		return interceptor;
	}

	/** Returns the persistent class for a certain EObject */
	public PersistentClass getPersistentClass(String entityName) {
		final Iterator<?> it = getClassMappings();
		while (it.hasNext()) {
			final PersistentClass pc = (PersistentClass) it.next();
			if (pc.getEntityName() != null
					&& pc.getEntityName().equals(entityName)) {
				return pc;
			}
		}
		return null;
	}

	/**
	 * @param interceptor
	 *            the interceptor to set
	 */
	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	/**
	 * @return the paModel
	 */
	public PAnnotatedModel getPaModel() {
		if (paModel == null) {
			// happens in case a hbm file is used
			// just create the pamodel with the default values
			paModel = getExtensionManager().getExtension(
					PersistenceMappingBuilder.class).buildMapping(
					getEPackages(), getPersistenceOptions(),
					getExtensionManager());
		}
		return paModel;
	}

	/**
	 * @return the referers
	 */
	public HashMap<String, java.util.List<ReferenceTo>> getReferers() {
		return referers;
	}

	/**
	 * If the extensionManager is not yet set then the DefaultExtensionManager
	 * is used.
	 * 
	 * @return the extensionManager
	 */
	public ExtensionManager getExtensionManager() {
		if (extensionManager == null) {
			setExtensionManager(ExtensionManagerFactory.getInstance().create());
		}
		return extensionManager;
	}

	/**
	 * @param extensionManager
	 *            the extensionManager to set
	 */
	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
		MappingUtil.registerHbExtensions(extensionManager);
	}

	/**
	 * @return the entityNameStrategy
	 */
	public EntityNameStrategy getEntityNameStrategy() {
		if (entityNameStrategy == null) {
			entityNameStrategy = getExtensionManager().getExtension(
					EntityNameStrategy.class);
			entityNameStrategy.setPaModel(getPaModel());
		}
		return entityNameStrategy;
	}

	/**
	 * @param paModel
	 *            the paModel to set
	 */
	public void setPaModel(PAnnotatedModel paModel) {
		this.paModel = paModel;
	}

	/**
	 * Facilitates setting ePackages through Spring
	 */
	public void setEPackageClasses(java.util.List<String> ePackageClasses) {
		if (ePackageConstructor == null) {
			ePackageConstructor = new EPackageConstructor();
		}
		ePackageConstructor.setModelClasses(ePackageClasses);
	}

	public void setEPackageFiles(java.util.List<String> ePackageFiles) {
		if (ePackageConstructor == null) {
			ePackageConstructor = new EPackageConstructor();
		}
		ePackageConstructor.setModelFiles(ePackageFiles);
	}
}