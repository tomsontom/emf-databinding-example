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
 * $Id: SessionController.java,v 1.8 2008/06/29 14:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.resource;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.hibernate.Session;

/**
 * A session controller handles one session. The session controller can be registered and retrieved
 * by name. The session controller is used by resources to share one session over different
 * resources. The resources get the session controller name as a parameter in the uri.
 * 
 * This class offers the registry functionality as well as a default implementation of the session
 * controller.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */

public class SessionController {
	/** The logger */
	private static Log log = LogFactory.getLog(SessionController.class);

	/** The static map of session controllers */
	private static Hashtable<String, SessionController> sessionControllers = new Hashtable<String, SessionController>();

	/** Register a session controller */
	public static synchronized void registerSessionController(String name, SessionController sc) {
		if (sessionControllers.get(name) != null) {
			throw new HbMapperException("There is already a session controller registered with the name: " + name);
		}
		log.debug("Registering session controller: " + name);
		sessionControllers.put(name, sc);
	}

	/** Deregisters a session controller */
	public static synchronized void deRegisterSessionController(String name) {
		if (sessionControllers.get(name) == null) {
			throw new HbMapperException("There is no session controller registered with the name: " + name);
		}
		log.debug("De-Registering session controller: " + name);
		sessionControllers.remove(name);
	}

	/** Returns a session controller using the name */
	public static synchronized SessionController getSessionController(String name) {
		return sessionControllers.get(name);
	}

	/** The local session wrapper */
	protected SessionWrapper sessionWrapper;

	/** The hb datastore from which the sessions are retrieved */
	protected HbDataStore hbDataStore;

	/**
	 * @return the hbDataStore
	 */
	public HbDataStore getHbDataStore() {
		return hbDataStore;
	}

	/**
	 * @param hbDataStore
	 *            the hbDataStore to set
	 */
	public void setHbDataStore(HbDataStore hbDataStore) {
		this.hbDataStore = hbDataStore;
	}

	/**
	 * Note fails when using ejb data store.
	 * 
	 * @return the session
	 */
	@Deprecated
	public Session getSession() {
		return (Session) getSessionWrapper().getSession();
	}

	/** Return the session wrapper */
	public SessionWrapper getSessionWrapper() {
		if (sessionWrapper == null) {
			sessionWrapper = hbDataStore.createSessionWrapper();
		}
		return sessionWrapper;
	}
}
