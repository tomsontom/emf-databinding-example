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
 * $Id: HbSessionWrapper.java,v 1.10 2008/08/03 21:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.hibernate.FlushMode;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.impl.SessionImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.JoinedSubclassEntityPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.persister.entity.UnionSubclassEntityPersister;

/**
 * Wraps a standard hibernate session.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.10 $
 */
public class HbSessionWrapper implements SessionWrapper {

	/** The hibernate session */
	private Session session = null;

	/** The datastore which created me */
	private final HbDataStore hbDataStore;

	private FlushMode flushMode;

	/** Constructor */
	public HbSessionWrapper(HbDataStore hbDataStore) {
		this.hbDataStore = hbDataStore;
	}

	/** Set the session in the constructor */
	public HbSessionWrapper(HbDataStore hbDataStore, Session session) {
		this.hbDataStore = hbDataStore;
		this.session = session;
	}

	/**
	 * Return the session, return is an object to support both session as well as entitymanager.
	 */
	public Object getSession() {
		if (session == null) {
			session = hbDataStore.getSessionFactory().openSession();
		}
		return session;
	}

	/** Convenience which casts */
	private Session getSessionInternal() {
		return (Session) getSession();
	}

	/** Begin a transaction */
	public void beginTransaction() {
		getSessionInternal().beginTransaction();
	}

	/** Commit a transaction */
	public void commitTransaction() {
		getSessionInternal().getTransaction().commit();
	}

	/** Rollback transaction */
	public void rollbackTransaction() {
		getSessionInternal().getTransaction().rollback();
	}

	/** Return an object using the entityname and a serializable id */
	public Object get(String entityName, Serializable id) {
		return getSessionInternal().get(entityName, id);
	}

	/** Query */
	public List<?> executeQuery(String qry) {
		final Query query = getSessionInternal().createQuery(qry);
		return query.list();
	}

	/** Query */
	public List<?> executeQuery(String qry, String entityParameter, Object entity) {
		final Query query = getSessionInternal().createQuery(qry);
		query.setEntity(entityParameter, entity);
		return query.list();
	}

	/** Query */
	public List<?> executeQuery(String qry, List<Object> parameters) {
		final Query query = getSessionInternal().createQuery(qry);
		int pos = 0;
		for (Object obj : parameters) {
			query.setParameter(pos++, obj);
		}
		return query.list();
	}

	/** Query */
	public List<?> executeQuery(String qry, boolean cacheable) {
		final Query query = getSessionInternal().createQuery(qry);
		query.setCacheable(cacheable);
		return query.list();
	}

	/** Query with named parameters */
	public List<?> executeQuery(String qry, Map<String, Object> namedParameters) {
		final Query query = getSessionInternal().createQuery(qry);
		for (Map.Entry<String, Object> entry : namedParameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.list();
	}

	/** Does this impl. wrap an entitymanager */
	public boolean isEJB3EntityManager() {
		return false;
	}

	public void restorePreviousFlushMode() {
		if (flushMode != null) {
			getSessionInternal().setFlushMode(flushMode);
			flushMode = null;
		}
	}

	/** Set the flushmode */
	public void setFlushModeManual() {
		flushMode = getSessionInternal().getFlushMode();
		getSessionInternal().setFlushMode(FlushMode.MANUAL);
	}

	/** Close the underlying session */
	public void close() {
		getSessionInternal().close();
	}

	/** Save or update the pass object */
	public void saveOrUpdate(Object obj) {
		getSessionInternal().saveOrUpdate(obj);
	}

	/** Delete the object */
	public void delete(Object obj) {
		getSessionInternal().delete(obj);
	}

	/** Flush the session */
	public void flush() {
		getSessionInternal().flush();
	}

	/** Is transaction active */
	public boolean isTransactionActive() {
		return ((SessionImpl) getSessionInternal()).isTransactionInProgress();
	}

	/** Refresh the object */
	public void refresh(Object obj) {
		getSessionInternal().refresh(obj, LockMode.NONE);
	}

	/** Check if a certain class is mapped using a certain inheritance strategy */
	public boolean isInheritanceStrategy(Class<?> cls, InheritanceType strategy) {
		final String clsName = cls.getName();
		final String realName = clsName.substring(clsName.lastIndexOf('.') + 1, clsName.length() - 4);
		final ClassMetadata cmd = hbDataStore.getSessionFactory().getClassMetadata(realName);
		if (strategy.equals(InheritanceType.SINGLE_TABLE)) {
			return cmd instanceof SingleTableEntityPersister;
		}
		if (strategy.equals(InheritanceType.JOINED)) {
			return cmd instanceof JoinedSubclassEntityPersister;
		}
		if (strategy.equals(InheritanceType.TABLE_PER_CLASS)) {
			return cmd instanceof UnionSubclassEntityPersister;
		}
		throw new HbStoreException("Strategy: " + strategy.toString() + " not supported ");
	}

	/** Clear the session */
	public void clear() {
		getSessionInternal().clear();
	}

	public void persist(Object obj) {
		getSessionInternal().save(obj);
	}

	public Session getHibernateSession() {
		return getSessionInternal();
	}

}