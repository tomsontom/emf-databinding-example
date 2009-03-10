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
 *   Benjamin Cabe
 * </copyright>
 *
 * $Id: SessionWrapper.java,v 1.8 2008/06/29 14:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.hibernate.Session;

/**
 * Wraps a session or an entity manager. Is used to support both standard hibernate as well as
 * hibernate entitymanager. The differences between these two are hidden behind this interface (with
 * different implementations for either case). The Teneo runtime code uses this interface to start
 * and commit transactions and perform queries.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */
public interface SessionWrapper {

	/**
	 * Return the session, return is an object to support both session as well as entitymanager.
	 */
	Object getSession();

	/** Begin a transaction */
	void beginTransaction();

	/** Commit a transaction */
	void commitTransaction();

	/** Rollback transaction */
	void rollbackTransaction();

	/** Return an object using the entityname and a serializable id */
	Object get(String entityName, Serializable id);

	/** Query */
	List<?> executeQuery(String qry);

	/** Query */
	List<?> executeQuery(String qry, String entityParameter, Object entity);

	/** Query */
	List<?> executeQuery(String qry, boolean cacheable);

	/** Query */
	List<?> executeQuery(String qry, List<Object> parameters);

	/** Query with named parameters */
	List<?> executeQuery(String qry, Map<String, Object> namedParameters);

	/** Does this impl. wrap an entitymanager */
	boolean isEJB3EntityManager();

	public void restorePreviousFlushMode();

	/** Set the flushmode in the session */
	void setFlushModeManual();

	/** Close the session/entitymanager */
	void close();

	/** Save or update the pass object */
	void saveOrUpdate(Object obj);

	/** Delete the object */
	void delete(Object obj);

	/** Flush the session */
	void flush();

	/** Clear the session */
	void clear();

	/** Is transaction active */
	boolean isTransactionActive();

	/** Refresh the object */
	void refresh(Object obj);

	/** Check if a certain class is mapped using a certain inheritance strategy */
	public boolean isInheritanceStrategy(Class<?> cls, InheritanceType strategy);

	/** Persist a new object */
	public void persist(Object obj);

	/** Return the underlying session */
	public Session getHibernateSession();
}