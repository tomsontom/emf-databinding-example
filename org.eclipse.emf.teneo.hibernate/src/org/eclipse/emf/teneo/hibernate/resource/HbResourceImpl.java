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
 * $Id: HbResourceImpl.java,v 1.11 2008/06/28 22:41:49 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.teneo.Constants;
import org.eclipse.emf.teneo.hibernate.EMFInterceptor;
import org.eclipse.emf.teneo.hibernate.HbConstants;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.HbSessionWrapper;
import org.eclipse.emf.teneo.hibernate.HbUtil;
import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.eclipse.emf.teneo.hibernate.mapping.identifier.IdentifierUtil;
import org.eclipse.emf.teneo.resource.StoreResource;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.util.IdentityMap;

/**
 * HbResource. This hibernate resource creates a new session for each load and save action. When
 * elists are lazily loaded then a new Session is created and the current content is added to the
 * session.
 * 
 * When you create a HbDataStore through the appropriate method in the HbHelper class. The name you
 * passed there can be used as a parameter in the uri used to create this resource (using the
 * parameter pmfname). The uri is then: hibernate://?dsname=myemf.
 * 
 * Another simple trick which is used to fool emf a bit is that the extension of the uri can also be
 * used to init a hibernate resource!
 * 
 * WARNING: This is an untested and experimental class, it is not intended to be used in production
 * situations.
 * 
 * This class does not support the SessionController.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.11 $
 */

public class HbResourceImpl extends StoreResource implements HbResource {
	/** The logger */
	private static Log log = LogFactory.getLog(HbResourceImpl.class);

	/** This threadlocal can be used by lazy loaders to store a session */
	// public static final ThreadLocal threadSession = new ThreadLocal();
	/** The store used to determine where to query for the data */
	protected HbDataStore emfDataStore;

	/**
	 * The constructor, gets an uri and retrieves the backing OJBStore
	 */
	public HbResourceImpl(URI uri) {
		super(uri);

		log.debug("Creating hibernateresource using uri: " + uri.toString());

		final Map<String, String> params = decodeQueryString(uri.query());

		String emfdsName = null;
		if (uri.query() == null && uri.fileExtension() != null) // this is
		// probably a
		// platform uri!
		{
			if (HbConstants.EHB_FILE_EXTENSION.compareTo(uri.fileExtension()) == 0) {
				log.debug("Assuming this is a property file " + uri.toString());
				try {
					final URIConverter uriConverter = getURIConverter();
					final InputStream is = uriConverter.createInputStream(uri);
					final Properties props = new Properties();
					props.load(is);
					is.close();
					emfdsName = props.getProperty(Constants.PROP_NAME);
					emfDataStore = HbUtil.getCreateDataStore(props);
					setDefinedQueries(getQueries(props));
				} catch (IOException e) {
					throw new HbMapperException("Exception when reading properties from: " + uri.toString(), e);
				}
			} else {
				log.debug("Trying fileextension: " + uri.fileExtension());
				// then try the extension of the resource
				emfdsName = uri.fileExtension();
			}
		} else if (params.get(DS_NAME_PARAM) != null) // only the name
		{
			emfdsName = getParam(params, DS_NAME_PARAM, uri.query());
			setDefinedQueries(getQueries(params));
		}

		if (emfdsName == null) {
			throw new HbMapperException("The Resource can not be initialized using the querystring: " + uri.query() +
					". Are all the required parameters present?");
		}
		log.debug("Looking for emf data store using  " + emfdsName);

		emfDataStore = HbHelper.INSTANCE.getDataStore(emfdsName);

		super.init(emfDataStore.getTopEntities());
	}

	/** Returns the emfdatastore */
	public HbDataStore getEMFDataStore() {
		return emfDataStore;
	}

	/**
	 * Creates the session of this resource. As a default the FlushMode is set to Never. The loaded
	 * objects of this resource are merged into the session. It is the responsibility of the caller
	 * to close the session or call the returnSession method here.
	 */
	public Session getSession() {
		if (log.isDebugEnabled()) {
			log.debug("Creating session");
		}
		final SessionFactory sessionFactory = emfDataStore.getSessionFactory();
		final Session session = sessionFactory.openSession();
		session.setFlushMode(FlushMode.MANUAL);

		if (loadedEObjects.size() > 0) {
			session.beginTransaction();

			// merge the loaded objects into the session
			if (log.isDebugEnabled()) {
				log.debug("Merging " + loadedEObjects.size() + " eobjects into new session ");
			}
			for (Object obj : loadedEObjects) {
				session.lock(obj, LockMode.NONE);
			}
			session.getTransaction().commit();
		}

		return session;
	}

	/** Return a sessionwrapper */
	public SessionWrapper getSessionWrapper() {
		return new HbSessionWrapper(getEMFDataStore(), getSession());
	}

	/** Returns the sessionwrapper to the resource so that it can do clean up (or not) */
	public void returnSessionWrapper(SessionWrapper sessionWrapper) {
		returnSession(sessionWrapper.getHibernateSession());
	}

	/** Returns the session, closes it */
	public void returnSession(Session theSession) {
		// solves a bug with older versions of Hibernate, see the EMFInterceptor
		Map.Entry<?, ?>[] collectionEntryArray =
				IdentityMap.concurrentEntries(((SessionImplementor) theSession).getPersistenceContext()
					.getCollectionEntries());
		for (Entry<?, ?> element : collectionEntryArray) {
			((PersistentCollection) element.getKey()).unsetSession((SessionImplementor) theSession);
		}

		theSession.close();
	}

	/**
	 * Returns an array of EObjects which refer to a certain EObject, note if the array is of length
	 * zero then no refering EObjects where found.
	 */
	@Override
	public Object[] getCrossReferencers(EObject referedTo) {
		Transaction tx = null;
		boolean err = true;
		final Session mySession = getSession();
		try {
			tx = mySession.beginTransaction();
			final Object[] result = emfDataStore.getCrossReferencers(mySession, referedTo);
			err = false;

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new HbMapperException("Exception when doing cross reference search " + emfDataStore.getName(), e);
		} finally {
			if (err) {
				if (tx != null) {
					tx.rollback();
				}
				mySession.close();
			} else {
				tx.commit();
			}
		}
	}

	/**
	 * Saves the changed objects or removes the detached objects from this resource.
	 */
	@Override
	protected void saveResource(Map<?, ?> options) {
		log.debug("Saving resource with uri: " + getURI());

		boolean err = true;
		Transaction tx = null;
		final Session mySession = getSession();
		try {
			tx = mySession.beginTransaction();

			final List<EObject> list = super.getContents();
			for (int i = 0; i < list.size(); i++) {
				final Object obj = list.get(i);
				// if (IdentifierCacheHandler.getInstance().getID(obj) == null) // new object
				// {
				mySession.saveOrUpdate(obj);
				// }
				// else do nothing because hibernate does this automatically??
			}

			// delete all deleted objects
			for (Object obj : removedEObjects) {
				if (IdentifierUtil.getID(obj, (SessionImplementor) mySession) != null) // persisted
				// object
				{
					mySession.delete(obj);
					EMFInterceptor.registerCollectionsForDereferencing((EObject) obj);
				}
			}

			// now flush everything
			mySession.flush();
			err = false;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new HbMapperException("Exception when saving resource " + emfDataStore.getName(), e);
		} finally {
			if (err) {
				if (tx != null) {
					tx.rollback();
				}
			} else {
				tx.commit();
			}
			returnSession(mySession);
		}
	}

	/**
	 * Loads all the objects in the global list
	 */
	@Override
	protected List<EObject> loadResource(Map<?, ?> options) {
		log.debug("Loading resource: " + getURI().toString());

		// first clear the old list
		Transaction tx = null;
		boolean err = true;
		final Session mySession = getSession();
		try {
			tx = mySession.beginTransaction();

			// note we have to a call to the super class otherwise an infinite
			// loop is created
			final List<EObject> storeList = loadFromStore(mySession);
			log.debug("Loaded " + storeList.size() + " objects");
			err = false;
			return storeList;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new HbMapperException("Exception when saving resource " + emfDataStore.getName(), e);
		} finally {
			if (err) {
				if (tx != null) {
					tx.rollback();
				}
			} else {
				tx.commit();
			}
			returnSession(mySession);
		}
	}

	/**
	 * Rollsback the transaction if any and clears different lists to start with an empty resource
	 * again. Note that the super.dounload is not called because that clears the list resulting in
	 * all kinds of undesirable inverseremoves.
	 */
	@Override
	protected void doUnload() {
		super.doUnload();
	}

	/**
	 * This method can be overridden to implement specific load behavior. Note that a transaction
	 * has already been started. The session is passed as a parameter, this is the same session
	 * which can be retrieved using the getSession method. The read objects should be returned in
	 * the list. Note that after this call the retrieved objects are put in the resource content.
	 */
	protected List<EObject> loadFromStore(Session sess) {
		if (definedQueriesPresent()) {
			return loadUsingDefinedQueries(sess);
		} else {
			return loadUsingTopClasses(sess);
		}
	}

	/** Reads data based on the topclasses list */
	private ArrayList<EObject> loadUsingTopClasses(Session sess) {
		log.debug("Loading resource " + getURI() + " using top classes");
		final ArrayList<EObject> readObjects = new ArrayList<EObject>();
		for (final String topClassName : topClassNames) {
			log.debug("Loading objects using hql: FROM " + topClassName);

			final Query qry = sess.createQuery("FROM " + topClassName);
			final Iterator<?> it = qry.list().iterator();
			while (it.hasNext()) {
				final EObject eobj = (EObject) it.next();
				// extra check on container because sometimes contained items
				// are still read in
				// case of multiple inheritance
				if (eobj.eContainer() == null) {
					readObjects.add(eobj);
				}
			}
		}
		return readObjects;
	}

	/** Reads data based using defined queries */
	private ArrayList<EObject> loadUsingDefinedQueries(Session sess) {
		log.debug("Loading resource " + getURI() + " using defined queries");
		final ArrayList<EObject> readObjects = new ArrayList<EObject>();
		final String[] qrys = getDefinedQueries();
		for (String element : qrys) {
			final Query qry = sess.createQuery(element);
			log.debug("Loading objects using hql: " + element);
			final Iterator<?> it = qry.list().iterator();
			while (it.hasNext()) {
				final Object obj = it.next();
				readObjects.add((EObject) obj);
			}
		}
		return readObjects;
	}
}