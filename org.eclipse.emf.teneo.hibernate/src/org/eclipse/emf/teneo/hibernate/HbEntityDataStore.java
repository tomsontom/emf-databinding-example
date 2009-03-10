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
 * $Id: HbEntityDataStore.java,v 1.17 2009/02/24 12:04:50 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceFileProvider;
import org.eclipse.emf.teneo.hibernate.mapper.MappingUtil;
import org.eclipse.emf.teneo.hibernate.mapping.EMFInitializeCollectionEventListener;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.event.InitializeCollectionEventListener;

/**
 * Adds Hibernate Entitymanager behavior to the hbDataStore.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.17 $
 */
public class HbEntityDataStore extends HbDataStore implements
		EntityManagerFactory {

	/** The logger */
	private static Log log = LogFactory.getLog(HbEntityDataStore.class);

	/** The persistency manager factory */
	private EntityManagerFactory entityManagerFactory;

	/** The used Hibernate configuration */
	private Ejb3Configuration ejb3Configuration;

	/** Initializes this Data Store */
	@Override
	public void initialize() {
		MappingUtil.registerHbExtensions(getExtensionManager());

		log.debug("Initializing EJB3 Hb Entity DataStore");
		// check a few things
		if (getEPackages() == null) {
			throw new HbMapperException("EPackages are not set");
			// if (getName() == null)
			// throw new HbStoreException("Name is not set");
		}

		// reset interceptor
		setInterceptor(null);

		log.debug(">>>>> Creating EJB3 Configuration");
		ejb3Configuration = createConfiguration();

		mapModel();

		setPropertiesInConfiguration();

		initializeDataStore();

		// wait for the session factory until the database is (re)created
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
		entityManagerFactory = buildEntityManagerFactory();

		// register ourselves
		HbHelper.INSTANCE.register(this);

		setInitialized(true);
	}

	/** Build the mappings in the configuration */
	@Override
	protected void buildMappings() {
		getConfiguration().buildMappings();
	}

	/** Set the event listener, can be overridden, in this impl. it does nothing */
	@Override
	protected void setEventListeners() {
		final EMFInitializeCollectionEventListener eventListener = getExtensionManager()
				.getExtension(EMFInitializeCollectionEventListener.class);
		getConfiguration()
				.getEventListeners()
				.setInitializeCollectionEventListeners(
						new InitializeCollectionEventListener[] { eventListener });
	}

	/** Sets the interceptor */
	@Override
	protected void setInterceptor() {
		if (getInterceptor() != null) {
			return;
		}
		final Interceptor interceptor = getHbContext().createInterceptor(
				getHibernateConfiguration(), getEntityNameStrategy());
		getConfiguration().setInterceptor(interceptor);
		setInterceptor(interceptor);
	}

	/** Returns a new ejb3 configuration object */
	protected Ejb3Configuration createConfiguration() {
		return new Ejb3Configuration();
	}

	/** Sets the properties in the Hibernate Configuration. */
	protected void setPropertiesInConfiguration() {
		Properties properties = getProperties();
		if (properties != null) {
			setDefaultProperties(properties);
			getConfiguration().addProperties(properties);
		}
	}

	/**
	 * Maps an ecore model of one ore more epackages into a hibernate xml String
	 * which is added to the passed configuration
	 */
	protected void mapModel() {
		if (getPersistenceOptions().isUseMappingFile()
				|| getPersistenceOptions().getMappingFilePath() != null) {
			log.debug("Searching hbm files in class paths of epackages");
			final String[] fileList = getMappingFileList();
			for (String element : fileList) {
				log.debug("Adding file " + element
						+ " to Hibernate Configuration");
				final PersistenceFileProvider pfp = getExtensionManager()
						.getExtension(PersistenceFileProvider.class);
				final InputStream is = pfp.getFileContent(this.getClass(),
						element);
				if (is == null) {
					throw new HbStoreException("Path to mapping file: "
							+ element + " does not exist!");
				}
				getConfiguration().addInputStream(is);
			}
		} else {
			setMappingXML(mapEPackages());
			// TODO replace this
			final StringBufferInputStream is = new StringBufferInputStream(
					getMappingXML());
			getConfiguration().addInputStream(is);
		}
	}

	/** Build the session factory */
	protected EntityManagerFactory buildEntityManagerFactory() {
		return getConfiguration().createEntityManagerFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.jpox.emf.IEMFDataStore#close()
	 */
	@Override
	public void close() {
		if (getEntityManagerFactory().isOpen()) {
			getEntityManagerFactory().close();
		}
	}

	/** Get the session factory */
	public EntityManagerFactory getEntityManagerFactory() {
		if (!isInitialized()) {
			initialize();
		}
		assert (entityManagerFactory != null);
		return entityManagerFactory;
	}

	/** Return a new session wrapper */
	@Override
	public SessionWrapper createSessionWrapper() {
		return new HbEntityManagerWrapper(this);
	}

	/**
	 * @return the ejbConfiguration
	 */
	public Ejb3Configuration getConfiguration() {
		return ejb3Configuration;
	}

	/**
	 * @return the hbConfiguration
	 */
	@Override
	public Configuration getHibernateConfiguration() {
		return ejb3Configuration.getHibernateConfiguration();
	}

	/** Return the Classmappings as an iterator */
	@Override
	protected Iterator<?> getClassMappings() {
		return getConfiguration().getClassMappings();
	}

	/** Is added for interface compliance with HbDataStore, should not be used */
	@Override
	public SessionFactory getSessionFactory() {
		throw new UnsupportedOperationException(
				"This method should not be called, use getEntityManagerFactory");
	}

	public EntityManager createEntityManager() {
		return getEntityManagerFactory().createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public EntityManager createEntityManager(Map arg0) {
		return getEntityManagerFactory().createEntityManager(arg0);
	}

	public boolean isOpen() {
		return getEntityManagerFactory().isOpen();
	}
}