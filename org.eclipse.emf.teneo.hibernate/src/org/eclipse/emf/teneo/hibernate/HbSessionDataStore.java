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
 * $Id: HbSessionDataStore.java,v 1.17 2009/03/15 14:49:46 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceFileProvider;
import org.eclipse.emf.teneo.hibernate.mapper.MappingUtil;
import org.eclipse.emf.teneo.hibernate.mapping.EMFInitializeCollectionEventListener;
import org.hibernate.Interceptor;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.InitializeCollectionEventListener;

/**
 * Holds the SessionFactory and performs different initialization related
 * actions. Initializes the database and offers xml import and export methods.
 * In addition can be used to retrieve all referers to a certain eobject.
 * <p>
 * The behavior can be overridden by overriding the protected methods and
 * implementing/registering your own HbDataStoreFactory in the HibernateHelper.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.17 $
 */

public class HbSessionDataStore extends HbBaseSessionDataStore {

	private static final long serialVersionUID = 1L;

	/** The logger */
	private static Log log = LogFactory.getLog(HbSessionDataStore.class);

	/** The used Hibernate configuration */
	private Configuration hbConfiguration;

	/** Initializes this Data Store */
	@Override
	public void initialize() {
		MappingUtil.registerHbExtensions(getExtensionManager());

		log.debug("Initializing Hb Session DataStore");

		// check a few things
		if (getEPackages() == null) {
			throw new HbMapperException("EPackages are not set");
			// if (getName() == null)
			// throw new HbStoreException("Name is not set");
		}

		// reset interceptor
		setInterceptor(null);

		log.debug(">>>>> Creating HB Configuration");
		hbConfiguration = createConfiguration();

		mapModel();

		setPropertiesInConfiguration();

		initializeDataStore();

		// will close the current sessionfactory if it was set
		closeSessionFactory();

		buildSessionFactory();

		setInitialized(true);
	}

	/** Set the event listener, can be overridden */
	@Override
	protected void setEventListeners() {
		final EMFInitializeCollectionEventListener eventListener = getExtensionManager()
				.getExtension(EMFInitializeCollectionEventListener.class);
		getConfiguration()
				.getEventListeners()
				.setInitializeCollectionEventListeners(
						new InitializeCollectionEventListener[] { eventListener });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.hibernate.HbContext#createConfiguration()
	 */
	protected Configuration createConfiguration() {
		return new Configuration();
	}

	/** Return the Classmappings as an iterator */
	@Override
	public Iterator<?> getClassMappings() {
		return getConfiguration().getClassMappings();
	}

	/** Build the mappings in the configuration */
	@Override
	protected void buildMappings() {
		getConfiguration().buildMappings();
	}

	/** Sets the interceptor */
	@Override
	protected void setInterceptor() {
		if (getInterceptor() != null) { // probably overridden
			return;
		}
		final Interceptor interceptor = getHbContext().createInterceptor(
				getHibernateConfiguration(), getEntityNameStrategy());
		getConfiguration().setInterceptor(interceptor);
		setInterceptor(interceptor);
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

		if (getPersistenceOptions().getMappingFilePath() != null
				|| getPersistenceOptions().isUseMappingFile()) {
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
			getConfiguration().addXML(getMappingXML());
		}
	}

	/** Build the session factory */
	protected void buildSessionFactory() {
		setSessionFactory(getConfiguration().buildSessionFactory());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.jpox.emf.IEMFDataStore#close()
	 */
	@Override
	public void close() {
		if (!getSessionFactory().isClosed()) {
			getSessionFactory().close();
		}
	}

	/** Return a new session wrapper */
	@Override
	public SessionWrapper createSessionWrapper() {
		return new HbSessionWrapper(this);
	}

	/**
	 * @return the hbConfiguration
	 */
	public Configuration getConfiguration() {
		return hbConfiguration;
	}

	/**
	 * @return the hbConfiguration
	 */
	@Override
	public Configuration getHibernateConfiguration() {
		return getConfiguration();
	}
}