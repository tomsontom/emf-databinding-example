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
 * $Id: HibernateResource.java,v 1.26 2008/08/26 20:19:39 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.teneo.hibernate.EMFInterceptor;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.HbSessionWrapper;
import org.eclipse.emf.teneo.hibernate.HbUtil;
import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.eclipse.emf.teneo.hibernate.mapping.identifier.IdentifierUtil;
import org.eclipse.emf.teneo.resource.StoreResource;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.hibernate.Session;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.SessionImpl;

/**
 * Hibernate Resource. The hibernate resource has a Session during its lifetime. A transaction is
 * started before the load and it is stopped just after the save. The session is disconnected and
 * reconnected when loading and saving.
 * 
 * When you create a HbDataStore through the appropriate method in the HibernateHelper class. The
 * name you passed there can be used as a parameter in the uri used to create this resource (using
 * the parameter pmfname). The uri is then: hibernate://?dsname=myemf.
 * 
 * Another simple trick which is used to fool emf a bit is that the extension of the uri can also be
 * used to init a hibernate resource!
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.26 $
 */

public class HibernateResource extends StoreResource implements HbResource {
	/** The logger */
	private static Log log = LogFactory.getLog(HibernateResource.class);

	/** The fragment separator */
	private static String SEPARATOR = "|";

	/** The store used to determine where to query for the data */
	protected HbDataStore emfDataStore;

	/** The uri parameter under which to store a session controller */
	public static String SESSION_CONTROLLER_PARAM = "sessionController";

	/** The session wrapper used for long transactions */
	protected SessionWrapper sessionWrapper = null; // is opened at first load

	/** The session controller */
	protected SessionController sessionController = null;

	/** Is set to true if there is a sessionController */
	private boolean hasSessionController = false;

	/**
	 * The constructor, gets an uri and retrieves the backing OJBStore
	 */
	public HibernateResource(URI uri) {
		super(uri);

		log.debug("Creating hibernateresource using uri: " + uri.toString());

		final Map<String, String> params = decodeQueryString(uri.query());

		if (params.get(DS_NAME_PARAM) != null) { // only the name
			setDefinedQueries(getQueries(params));
			emfDataStore = HbHelper.INSTANCE.getDataStore(getParam(params, DS_NAME_PARAM, uri.query()));
		} else if (params.get(SESSION_CONTROLLER_PARAM) != null) {

			setDefinedQueries(getQueries(params));

			final String scName = getParam(params, SESSION_CONTROLLER_PARAM, uri.query());
			sessionController = SessionController.getSessionController(scName);
			log.debug("Using session controller " + scName);
			emfDataStore = sessionController.getHbDataStore();
			hasSessionController = true;
		} else if (uri.fileExtension() != null) // this is probably a platform
		// uri!
		{
			log.debug("Trying fileextension: " + uri.fileExtension());
			// then try the extension of the resource
			emfDataStore = HbHelper.INSTANCE.getDataStore(uri.fileExtension());

			// if null then assume that this is a properties file
			if (emfDataStore == null) {
				log.debug("No datastore defined for extension, assuming this is a property file " + uri.toString());
				try {
					final URIConverter uriConverter = getURIConverter();
					final InputStream is = uriConverter.createInputStream(uri);
					final Properties props = new Properties();
					props.load(is);
					is.close();
					emfDataStore = HbUtil.getCreateDataStore(props);
					setDefinedQueries(getQueries(props));
				} catch (IOException e) {
					throw new HbMapperException("Exception when reading properties from: " + uri.toString(), e);
				}
			}
		}
		if (emfDataStore == null) {
			throw new HbMapperException("No HbDataStore can be found using the uri " + uri.toString());
		}
		log.debug("Using emf data store using  " + emfDataStore.getName());
		super.init(emfDataStore.getTopEntities());
	}

	/** Returns the emfdatastore */
	public HbDataStore getEMFDataStore() {
		return emfDataStore;
	}

	/**
	 * Returns the session of this resource, if no session is set yet then creates it using the
	 * datastore. As a default the FlushMode is set to Never.
	 * 
	 * Deprecated, use getSessionWrapper (to support ejb3)
	 */
	@Deprecated
	public Session getSession() {
		return (Session) getSessionWrapper().getSession();
	}

	/** Return the sessionwrapper */
	public SessionWrapper getSessionWrapper() {
		if (sessionWrapper == null) {
			if (hasSessionController) {
				sessionWrapper = sessionController.getSessionWrapper();
			} else {
				// session can be null when this is an xml import! ;
				sessionWrapper = emfDataStore.createSessionWrapper();
				sessionWrapper.setFlushModeManual();
			}
		}
		return sessionWrapper;
	}

	/**
	 * Sets the session, overwrites current session. Deprecated use setSessionWrapper.
	 */
	@Deprecated
	public void setSession(Session session) {
		if (session != null) {
			this.sessionWrapper = new HbSessionWrapper(emfDataStore, session);
		} else {
			this.sessionWrapper = null;
		}
	}

	/**
	 * Unpacks the id string and reads an object from the db, note for each read a transaction is
	 * opened, unless the session is controlled by the caller.
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#getEObjectByID(java.lang.String)
	 */
	@Override
	protected EObject getEObjectByID(String id) {
		// try to find the different parts of the id
		if (id == null) {
			return super.getEObjectByID(id);
		}
		if (getIntrinsicIDToEObjectMap() != null) {
			final EObject firstCheck = getIntrinsicIDToEObjectMap().get(id);
			if (firstCheck != null) {
				return firstCheck;
			}
		}

		if (log.isDebugEnabled()) {
			log.debug("Reading eobject using urifragment " + id);
		}
		final String[] parts = id.split("\\" + SEPARATOR);

		if (parts.length != 2) {
			if (log.isDebugEnabled()) {
				log.debug("Not a valid urifragment (" + id + ") for the hibernate resource, trying the superclass");
			}
			return super.getEObjectByID(id);
		}

		// build a query
		final EClass eclass = emfDataStore.getEntityNameStrategy().toEClass(parts[0]);
		final int splitIndex = parts[1].indexOf("=");
		if (splitIndex == -1) {
			if (log.isDebugEnabled()) {
				log.debug("Not a valid urifragment (" + id + ") for the hibernate resource, trying the superclass");
			}
			return super.getEObjectByID(id);
		}
		final String idStr = parts[1].substring(1 + splitIndex);

		// try to find the object using the id-part
		final EObject eObject = super.getEObjectByID(idStr);
		if (eObject != null) {
			return eObject;
		}

		final boolean oldLoading = isLoading();
		boolean err = true;
		try {
			setIsLoading(true);
			if (!hasSessionController) {
				getSessionWrapper().beginTransaction();
			}

			final Object result =
					getSessionWrapper().get(parts[0], (Serializable) HbUtil.stringToId(eclass, emfDataStore, idStr));
			if (result == null) {
				if (log.isDebugEnabled()) {
					log.debug("Object not found in the db, trying the parent");
				}
				err = false;
				return super.getEObjectByID(id);
			}
			final InternalEObject internalEObject = (InternalEObject) result;
			// only add if not yet part of a resource
			if (internalEObject.eResource() == null) {
				addUsingContainmentStructure((InternalEObject) result);
			}
			err = false;
			return (EObject) result;
		} finally {
			setIsLoading(oldLoading);
			if (!hasSessionController) {
				if (err) {
					getSessionWrapper().rollbackTransaction();
					getSessionWrapper().close();
				} else {
					getSessionWrapper().commitTransaction();
				}
			}
		}
	}

	/**
	 * Creates a unique id string from the eobject. The id string will contain a link to the type
	 * (eclass) and the string version of the id itself. This method assumes that the id can be
	 * converted from and to a string!
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl#getURIFragment(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public String getURIFragment(EObject object) {
		if (object == null) {
			return null;
		}
		final String theId = HbUtil.idToString(object, emfDataStore);
		if (theId == null) {
			return super.getURIFragment(object);
		}
		final StringBuffer idStr = new StringBuffer();
		idStr.append(emfDataStore.getEntityNameStrategy().toEntityName(object.eClass()));
		idStr.append(SEPARATOR);
		idStr.append("id=" + theId);
		return idStr.toString();
	}

	/**
	 * Sets the sessionwrapper, overwrites current session.
	 */
	public void setSessionWrapper(SessionWrapper sessionWrapper) {
		this.sessionWrapper = sessionWrapper;
	}

	/** Returns the session, does nothing in this impl */
	public void returnSession(Session theSession) {
		// do nothing
	}

	/** Returns the sessionwrapper to the resource so that it can do clean up (or not) */
	public void returnSessionWrapper(SessionWrapper sessionWrapper) {

	}

	/**
	 * Returns an array of EObjects which refer to a certain EObject, note if the array is of length
	 * zero then no refering EObjects where found.
	 */
	@Override
	public Object[] getCrossReferencers(EObject referedTo) {
		boolean err = true;
		final SessionWrapper mySessionWrapper = getSessionWrapper();
		try {
			if (!hasSessionController) {
				mySessionWrapper.beginTransaction();
			}
			final Object[] result = emfDataStore.getCrossReferencers(mySessionWrapper, referedTo);
			err = false;

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new HbMapperException("Exception when doing cross reference search " + emfDataStore.getName(), e);
		} finally {
			if (!hasSessionController) {
				if (err) {
					mySessionWrapper.rollbackTransaction();
					mySessionWrapper.close();
				} else {
					mySessionWrapper.commitTransaction();
				}
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
		final SessionWrapper mySessionWrapper = getSessionWrapper();
		try {
			if (!hasSessionController) {
				mySessionWrapper.beginTransaction();
			}

			for (EObject eobject : super.getContents()) {
				mySessionWrapper.saveOrUpdate(eobject);
			}

			// delete all deleted objects
			for (Object obj : removedEObjects) {
				final InternalEObject eobj = (InternalEObject) obj;
				if (eobj.eResource() != null && eobj.eResource() != this) {
					continue;
				}

				if (IdentifierUtil.getID(obj, (SessionImplementor) mySessionWrapper.getHibernateSession()) != null) // persisted
				// object
				{
					if (eobj.eDirectResource() == null || eobj.eDirectResource() == this) {
						mySessionWrapper.delete(obj);
						EMFInterceptor.registerCollectionsForDereferencing((EObject) obj);
					}
				}
			}

			// now flush everything
			if (!hasSessionController) {
				mySessionWrapper.flush();
			}

			err = false;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new HbMapperException("Exception when saving resource " + emfDataStore.getName(), e);
		} finally {
			if (!hasSessionController) {
				if (err) {
					mySessionWrapper.rollbackTransaction();
					// see bugzilla 221950
// mySessionWrapper.close();
				} else {
					mySessionWrapper.commitTransaction();
				}
			}
		}
	}

	/**
	 * Loads all the objects in the global list
	 */
	@Override
	protected List<EObject> loadResource(Map<?, ?> options) {
		log.debug("Loading resource: " + getURI().toString());

		// first clear the old list
		boolean err = true;
		final SessionWrapper mySessionWrapper = getSessionWrapper();
		try {
			if (!hasSessionController) {
				mySessionWrapper.beginTransaction();
			}

			// note we have to a call to the super class otherwise an infinite
			// loop is created
			final List<EObject> storeList = loadFromStore(mySessionWrapper);
			log.debug("Loaded " + storeList.size() + " objects");
			err = false;
			return storeList;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			throw new HbMapperException("Exception when saving resource " + emfDataStore.getName(), e);
		} finally {
			if (!hasSessionController) {
				if (err) {
					mySessionWrapper.rollbackTransaction();
					mySessionWrapper.close();
				} else {
					mySessionWrapper.commitTransaction();
				}
			}
		}
	}

	/**
	 * Rollsback the transaction if any and clears different lists to start with an empty resource
	 * again.
	 */
	@Override
	protected void doUnload() {
		super.doUnload();

		if (!hasSessionController) {
			if (!getSessionWrapper().isEJB3EntityManager()) {
				AssertUtil.assertTrue("Session must be disconnected in unload", !((SessionImpl) getSessionWrapper()
					.getSession()).isTransactionInProgress());
			}
			log.debug("Doing unload, closing and nullifying session");
			getSessionWrapper().close();
			setSessionWrapper(null);
		} else {
			log
				.debug("Doing unload, has session controller, sessioncontroller is therefor responsible for session close");
		}
	}

	/**
	 * This method can be overridden to implement specific load behavior. Note that a transaction
	 * has already been started. The session is passed as a parameter, this is the same session
	 * which can be retrieved using the getSession method. The read objects should be returned in
	 * the list. Note that after this call the retrieved objects are put in the resource content.
	 */
	protected List<EObject> loadFromStore(SessionWrapper sess) {
		if (definedQueriesPresent()) {
			return loadUsingDefinedQueries(sess);
		} else {
			return loadUsingTopClasses(sess);
		}
	}

	/** Reads data based on the topclasses list */
	private ArrayList<EObject> loadUsingTopClasses(SessionWrapper sess) {
		log.debug("Loading resource " + getURI() + " using top classes");
		final ArrayList<EObject> readObjects = new ArrayList<EObject>();
		for (final String topClassName : topClassNames) {
			log.debug("Loading objects using hql: FROM " + topClassName);

			final List<?> qryResult;
			if (sess.isEJB3EntityManager()) {
				qryResult = sess.executeQuery("select o from " + topClassName + " o");
			} else {
				qryResult = sess.executeQuery("from " + topClassName);
			}
			final Iterator<?> it = qryResult.iterator();
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
	private ArrayList<EObject> loadUsingDefinedQueries(SessionWrapper sess) {
		log.debug("Loading resource " + getURI() + " using defined queries");
		final ArrayList<EObject> readObjects = new ArrayList<EObject>();
		final String[] qrys = getDefinedQueries();
		for (String element : qrys) {
			final List<?> qryResult = sess.executeQuery(element);
			log.debug("Loading objects using hql: " + element);
			final Iterator<?> it = qryResult.iterator();
			while (it.hasNext()) {
				final Object obj = it.next();
				readObjects.add((EObject) obj);
			}
		}
		return readObjects;
	}

	/** Reads a set of objects into the resource by using a query. */
	public Object[] getObjectsByQuery(String query, boolean cache) {
		log.debug("Started listing objects by query " + query + " in resource " + getURI());
		SessionWrapper mySessionWrapper = null;
		boolean err = true;
		setIsLoading(true);
		try {
			mySessionWrapper = getSessionWrapper();
			if (!hasSessionController) {
				mySessionWrapper.beginTransaction();
			}
			final List<?> qryResult = mySessionWrapper.executeQuery(query, cache);
			for (Object object : qryResult) {
				if (object instanceof InternalEObject) {
					final InternalEObject eObject = (InternalEObject) object;
					// only add if the object is not already part of this
					// resource.
					// if already part of this resource then it should have been
					// loaded through
					// a containment relation.
					assert (eObject.eResource() != this || loadedEObjectSet.contains(eObject) || newEObjects
						.contains(eObject));
					addToContent(eObject);
				}
			}

			err = false;
			log.debug("Listed " + qryResult.size() + " objects using query " + query + " in resource " + getURI());
			return qryResult.toArray();
		} finally {
			if (!hasSessionController) {
				if (err) {
					mySessionWrapper.rollbackTransaction();
					mySessionWrapper.close();
				} else {
					mySessionWrapper.commitTransaction();
				}
			}
			setIsLoading(false);
			log.debug("Finished getting objects by query " + query + " in resource " + getURI());
		}
	}

	/**
	 * @return the hasSessionController
	 */
	public boolean isHasSessionController() {
		return hasSessionController;
	}

	@Override
	public boolean isLoading() {
		return isLoading;
	}

	/** Load additional objects into the contents using a query */
	public Object[] listByQuery(String query, boolean cache) {
		log.debug("Started listing objects by query " + query + " in resource " + getURI());
		SessionWrapper mySessionWrapper = null;
		boolean err = true;
		setIsLoading(true);
		final Notification notification = setLoaded(true);
		try {
			mySessionWrapper = getSessionWrapper();
			if (!hasSessionController) {
				mySessionWrapper.beginTransaction();
			}
			final List<?> qryResult = mySessionWrapper.executeQuery(query, cache);
			for (Object object : qryResult) {
				if (object instanceof InternalEObject) {
					addToContent((InternalEObject) object);
				}
			}

			err = false;
			log.debug("Listed " + qryResult.size() + " objects using query " + query + " in resource " + getURI());
			return qryResult.toArray();
		} finally {
			setIsLoading(false);
			if (!hasSessionController) {
				if (err) {
					mySessionWrapper.rollbackTransaction();
					mySessionWrapper.close();
				} else {
					mySessionWrapper.commitTransaction();
				}
			}
			if (notification != null) {
				eNotify(notification);
			}
			log.debug("Finished listing objects by query " + query + " in resource " + getURI());
		}
	}
}