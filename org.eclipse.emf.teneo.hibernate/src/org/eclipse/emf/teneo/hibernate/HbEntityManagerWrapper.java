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
 * $Id: HbEntityManagerWrapper.java,v 1.14 2008/06/29 14:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.hibernate.Session;
import org.hibernate.ejb.EntityManagerImpl;
import org.hibernate.engine.ForeignKeys;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.mapping.JoinedSubclass;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.SingleTableSubclass;
import org.hibernate.mapping.UnionSubclass;

/**
 * Wraps a hibernate entity manager.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.14 $
 */
public class HbEntityManagerWrapper implements SessionWrapper {

	/** The hibernate session */
	private EntityManager entityManager = null;

	/** The datastore which created me */
	private final HbEntityDataStore hbEntityDataStore;

	/** The current transaction */
	private EntityTransaction entityTransaction = null;

	private FlushModeType flushMode = null;

	/** Constructor */
	public HbEntityManagerWrapper(HbEntityDataStore hbEntityDataStore) {
		this.hbEntityDataStore = hbEntityDataStore;
	}

	/** Set the session in the constructor */
	public HbEntityManagerWrapper(HbEntityDataStore hbEntityDataStore, EntityManager entityManager) {
		this.hbEntityDataStore = hbEntityDataStore;
		this.entityManager = entityManager;
	}

	/**
	 * Return the session or entityManager, return is an object to support both session as well as
	 * entitymanager.
	 */
	public Object getClassicSession() {
		if (entityManager == null) {
			entityManager = hbEntityDataStore.getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	/**
	 * Return the session or entityManager, return is an object to support both session as well as
	 * entitymanager.
	 */
	public Object getSession() {
		if (entityManager == null) {
			entityManager = hbEntityDataStore.getEntityManagerFactory().createEntityManager();
		}
		return entityManager;
	}

	/** Convenience which casts */
	public EntityManager getEntityManager() {
		return (EntityManager) getSession();
	}

	/** Begin a transaction */
	public void beginTransaction() {
		assert (entityTransaction == null);
		entityTransaction = getEntityManager().getTransaction();
		entityTransaction.begin();
	}

	/** Commit a transaction */
	public void commitTransaction() {
		if (entityTransaction == null) {
			throw new IllegalStateException("EntityTransaction is null, call begin before commit!");
		}
		entityTransaction.commit();
		entityTransaction = null;
	}

	/** Rollback transaction */
	public void rollbackTransaction() {
		if (entityTransaction == null) {
			throw new IllegalStateException("EntityTransaction is null, call begin before commit!");
		}
		entityTransaction.rollback();
		entityTransaction = null;
	}

	/** Return an object using the entityname and a serializable id */
	public Object get(String entityName, Serializable id) {
		return ((EntityManagerImpl) getEntityManager()).getSession().get(entityName, id);
	}

	/** Query */
	public List<?> executeQuery(String qry) {
		final Query query = getEntityManager().createQuery(qry);
		return query.getResultList();
	}

	/** Query */
	public List<?> executeQuery(String qry, boolean cacheable) {
		final Query query = getEntityManager().createQuery(qry);
		// todo: cacheable in ejb3?
		// query.setCacheable(cacheable);
		return query.getResultList();
	}

	/** Query */
	public List<?> executeQuery(String qry, String entityParameter, Object entity) {
		final Query query = getEntityManager().createQuery(qry);
		query.setParameter(entityParameter, entity);
		return query.getResultList();
	}

	/** Query */
	public List<?> executeQuery(String qry, List<Object> parameters) {
		final Query query = getEntityManager().createQuery(qry);
		int pos = 1;
		for (Object obj : parameters) {
			query.setParameter(pos++, obj);
		}
		return query.getResultList();
	}

	/** Query with named parameters */
	public List<?> executeQuery(String qry, Map<String, Object> namedParameters) {
		final Query query = getEntityManager().createQuery(qry);
		for (Map.Entry<String, Object> entry : namedParameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
	}

	/** Does this impl. wrap an entitymanager */
	public boolean isEJB3EntityManager() {
		return true;
	}

	public void restorePreviousFlushMode() {
		if (flushMode != null) {
			getEntityManager().setFlushMode(flushMode);
			flushMode = null;
		}
	}

	/** Set the flushmode */
	public void setFlushModeManual() {
		flushMode = getEntityManager().getFlushMode();
		getEntityManager().setFlushMode(FlushModeType.COMMIT);
	}

	/** Close the underlying session */
	public void close() {
		getEntityManager().close();
	}

	/** Save or update the pass object */
	public void saveOrUpdate(Object obj) {
		final Session session = ((EntityManagerImpl) getEntityManager()).getSession();
		final String entityName = hbEntityDataStore.getInterceptor().getEntityName(obj);
		if (((SessionImplementor) session).getPersistenceContext().isEntryFor(obj)) {
			getEntityManager().persist(obj);
		} else if (ForeignKeys.isNotTransient(entityName, obj, false, (SessionImplementor) session) ||
				!ForeignKeys.isTransient(entityName, obj, false, (SessionImplementor) session)) {
			// this is a trick because ejb3 does not support saveOrUpdate (why did they not add
			// this behavior!)
			session.saveOrUpdate(obj);
		} else {
			getEntityManager().persist(obj);
		}
	}

	/** Delete the object */
	public void delete(Object obj) {
		getEntityManager().remove(obj);
	}

	/** Flush the session */
	public void flush() {
		getEntityManager().flush();
	}

	/** Is transaction active */
	public boolean isTransactionActive() {
		return entityTransaction != null && entityTransaction.isActive();
	}

	/** Refresh the object */
	public void refresh(Object obj) {
		getEntityManager().refresh(obj);
	}

	/** Check if a certain class is mapped using a certain inheritance strategy */
	public boolean isInheritanceStrategy(Class<?> cls, InheritanceType strategy) {
		final String name = cls.getName();
		final String realName = name.substring(name.lastIndexOf('.') + 1, name.length() - 4);
		final PersistentClass cmd = hbEntityDataStore.getConfiguration().getClassMapping(realName);
		if (strategy.equals(InheritanceType.SINGLE_TABLE)) {
			return cmd instanceof SingleTableSubclass;
		}
		if (strategy.equals(InheritanceType.JOINED)) {
			return cmd instanceof JoinedSubclass;
		}
		if (strategy.equals(InheritanceType.TABLE_PER_CLASS)) {
			return cmd instanceof UnionSubclass;
		}
		throw new HbStoreException("Strategy: " + strategy.toString() + " not supported ");
	}

	/** Clear the session */
	public void clear() {
		getEntityManager().clear();
	}

	public void persist(Object obj) {
		getEntityManager().persist(obj);
	}

	public Session getHibernateSession() {
		return (Session) getEntityManager().getDelegate();
	}
}