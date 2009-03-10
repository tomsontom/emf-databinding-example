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
 * $Id: HbHelper.java,v 1.14 2008/04/16 21:08:01 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceMappingBuilder;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerFactory;
import org.eclipse.emf.teneo.hibernate.mapper.HibernateMappingGenerator;
import org.eclipse.emf.teneo.hibernate.mapper.MappingUtil;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.PersistentClass;

/**
 * Is the main entry point for 'outside' users to create, register and retrieve EMF Data stores.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.14 $
 */
public class HbHelper {
	/** The logger */
	private static Log log = LogFactory.getLog(HbHelper.class);

	/** The singleton instance of this class */
	public static final HbHelper INSTANCE = new HbHelper();

	/** The list of EMF Datastores mapped by name */
	private final Hashtable<String, HbDataStore> emfDataStores = new Hashtable<String, HbDataStore>();

	/** The list of emf datastores mapped by hibernate persistent class */
	private final Hashtable<Object, HbDataStore> dataStoreByPersistentClass = new Hashtable<Object, HbDataStore>();

	/** The registered emf data store factory */
	private static HbDataStoreFactory emfDataStoreFactory = new HbDataStoreFactory() {
		public HbDataStore createHbDataStore() {
			return new HbSessionDataStore();
		}
	};

	/**
	 * @param emfDataStoreFactory
	 *            the emfDataStoreFactory to set
	 */
	public static void setHbDataStoreFactory(HbDataStoreFactory hbDataStoreFactory) {
		HbHelper.emfDataStoreFactory = hbDataStoreFactory;
	}

	/** Put a datastore in the dataStoreByPersistentClass */
	void registerDataStoreByPC(HbDataStore ds) {
		for (Iterator<?> it = ds.getClassMappings(); it.hasNext();) {
			final PersistentClass pc = (PersistentClass) it.next();
			if (dataStoreByPersistentClass.get(pc) != null) {
				throw new HbMapperException("There is already a datastore registered for this pc: " +
						pc.getEntityName() + (dataStoreByPersistentClass.get(pc)).getName() + "/" + ds.getName());
			}
			log.debug("Datastore: " + ds.getName() + " registered for pc: " + pc.getEntityName());
			dataStoreByPersistentClass.put(pc, ds);
		}
	}

	/** Register the datastore also for the components */
	void registerDataStoreByComponent(HbDataStore ds, Component component) {
		log.debug("Datastore: " + ds.getName() + " registered for component: " + component.getComponentClassName());
		dataStoreByPersistentClass.put(component, ds);
	}

	/** Return the datastore on the basis of the pc */
	public HbDataStore getDataStore(PersistentClass pc) {
		final HbDataStore ds = dataStoreByPersistentClass.get(pc);
		if (ds == null) {
			throw new HbMapperException("No datastore for pc " + pc.getEntityName());
		}
		return ds;
	}

	/** Return the datastore on the basis of the component */
	public HbDataStore getDataStore(Component component) {
		final HbDataStore ds = dataStoreByPersistentClass.get(component);
		if (ds == null) {
			throw new HbMapperException("No datastore for pc " + component.getComponentClassName());
		}
		return ds;
	}

	/** Clears the list of session factories */
	public synchronized void closeAll() {
		for (HbDataStore emfds : emfDataStores.values()) {
			emfds.close();
		}
		emfDataStores.clear();
		dataStoreByPersistentClass.clear();
	}

	/** Deregisters a session factory from the registry */
	public synchronized void deRegisterDataStore(String name) {
		if (name == null) {
			throw new HbMapperException("An unique name should be specified when deregistering a session factory");
		}
		final HbDataStore emfds = emfDataStores.get(name);
		if (emfds == null) {
			log.warn("No session factory registered under the name: " + name);
			return;
		}

		for (Iterator<?> it = emfds.getClassMappings(); it.hasNext();) {
			final PersistentClass pc = (PersistentClass) it.next();
			HbDataStore removedDS = dataStoreByPersistentClass.remove(pc);
			if (removedDS != emfds) {
				throw new HbMapperException("Removed datastore is unequal to deregistered ds: " + removedDS.getName() +
						"/" + emfds.getName() + "/" + pc.getEntityName());
			}
		}

		log.debug("Removing and closing emf data store: " + name);
		emfDataStores.remove(name);
		emfds.close();
	}

	/**
	 * Creates and register a HibernateEMFDataStore, initialization has to be done by the caller
	 */
	public synchronized HbDataStore createRegisterDataStore(String name) {
		HbDataStore emfds = emfDataStores.get(name);
		if (emfds != null) {
			log.warn("EMF Data Store already registered under name: " + name + ", returning it");
			return emfds;
		}

		log.info("Creating emf data store and registering it under name: " + name);
		emfds = emfDataStoreFactory.createHbDataStore();
		emfds.setName(name);
		// next call is done automatically
		// emfDataStores.put(name, emfds);
		log.info("Returning created emf data store, initialize this newly created data store!");
		return emfds;
	}

	/** Register a datastore */
	public void register(HbDataStore hbDataStore) {
		emfDataStores.put(hbDataStore.getName(), hbDataStore);
	}

	/** Return a emf data store */
	public HbDataStore getDataStore(String name) {
		final HbDataStore hds = emfDataStores.get(name);
		if (hds == null) {
			log.debug("No datastore found using " + name);
		}
		return hds;
	}

	/**
	 * Separate utility method, generates a hibernate mapping for a set of epackages and options.
	 * The hibernate.hbm.xml is returned as a string. The mapping is not registered or used in any
	 * other way by Elver.
	 */
	public String generateMapping(EPackage[] epackages, Properties props) {
		return generateMapping(epackages, props, ExtensionManagerFactory.getInstance().create());
	}

	/**
	 * Separate utility method, generates a hibernate mapping for a set of epackages and options.
	 * The hibernate.hbm.xml is returned as a string. The mapping is not registered or used in any
	 * other way by Elver.
	 */
	public String generateMapping(EPackage[] epackages, Properties props, ExtensionManager extensionManager) {
		MappingUtil.registerHbExtensions(extensionManager);

		log.debug("Generating mapping file passed epackages");
		// DCB: Use Hibernate-specific annotation processing mechanism. This
		// allows use of
		// Hibernate-specific annotations.
		final PersistenceOptions po = extensionManager.getExtension(PersistenceOptions.class, new Object[] { props });
		final PAnnotatedModel paModel =
				extensionManager.getExtension(PersistenceMappingBuilder.class).buildMapping(epackages, po,
					extensionManager);
		final HibernateMappingGenerator hmg = extensionManager.getExtension(HibernateMappingGenerator.class);
		hmg.setPersistenceOptions(po);
		return hmg.generateToString(paModel);
	}
}