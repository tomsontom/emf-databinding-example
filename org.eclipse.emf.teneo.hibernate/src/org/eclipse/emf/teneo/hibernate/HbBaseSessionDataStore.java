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
 * $Id: HbBaseSessionDataStore.java,v 1.4 2008/03/30 10:01:11 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.Reference;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.classic.Session;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.metadata.CollectionMetadata;
import org.hibernate.stat.Statistics;

/**
 * Holds the sessionfactory related methods, makes the HbSessionDataStore better readable.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */
@SuppressWarnings("unchecked")
public abstract class HbBaseSessionDataStore extends HbDataStore implements SessionFactory {

	private static final long serialVersionUID = 1L;

	/** The persistency manager factory */
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	@Override
	public SessionFactory getSessionFactory() {
		if (!isInitialized()) {
			initialize();
		}
		return sessionFactory;
	}

	// close session factory if set
	protected void closeSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
			sessionFactory = null;
		}
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void evict(Class persistentClass, Serializable id) throws HibernateException {
		getSessionFactory().evict(persistentClass, id);
	}

	public void evict(Class persistentClass) throws HibernateException {
		getSessionFactory().evict(persistentClass);
	}

	public void evictCollection(String roleName, Serializable id) throws HibernateException {
		getSessionFactory().evictCollection(roleName, id);
	}

	public void evictCollection(String roleName) throws HibernateException {
		getSessionFactory().evictCollection(roleName);
	}

	public void evictEntity(String entityName, Serializable id) throws HibernateException {
		getSessionFactory().evictEntity(entityName, id);
	}

	public void evictEntity(String entityName) throws HibernateException {
		getSessionFactory().evictEntity(entityName);
	}

	public void evictQueries() throws HibernateException {
		getSessionFactory().evictQueries();
	}

	public void evictQueries(String cacheRegion) throws HibernateException {
		getSessionFactory().evictQueries(cacheRegion);
	}

	public Map getAllClassMetadata() throws HibernateException {
		return getSessionFactory().getAllClassMetadata();
	}

	public Map getAllCollectionMetadata() throws HibernateException {
		return getSessionFactory().getAllCollectionMetadata();
	}

	public ClassMetadata getClassMetadata(Class persistentClass) throws HibernateException {
		return getSessionFactory().getClassMetadata(persistentClass);
	}

	public ClassMetadata getClassMetadata(String entityName) throws HibernateException {
		return getSessionFactory().getClassMetadata(entityName);
	}

	public CollectionMetadata getCollectionMetadata(String roleName) throws HibernateException {
		return getSessionFactory().getCollectionMetadata(roleName);
	}

	public Session getCurrentSession() throws HibernateException {
		return getSessionFactory().getCurrentSession();
	}

	public Set getDefinedFilterNames() {
		return getSessionFactory().getDefinedFilterNames();
	}

	public FilterDefinition getFilterDefinition(String filterName) throws HibernateException {
		return getSessionFactory().getFilterDefinition(filterName);
	}

	public Reference getReference() throws NamingException {
		return getSessionFactory().getReference();
	}

	public Statistics getStatistics() {
		return getSessionFactory().getStatistics();
	}

	public boolean isClosed() {
		return getSessionFactory().isClosed();
	}

	public Session openSession() throws HibernateException {
		return getSessionFactory().openSession();
	}

	public Session openSession(Connection connection, Interceptor interceptor) {
		return getSessionFactory().openSession(connection, interceptor);
	}

	public Session openSession(Connection connection) {
		return getSessionFactory().openSession(connection);
	}

	public Session openSession(Interceptor interceptor) throws HibernateException {
		return getSessionFactory().openSession(interceptor);
	}

	public StatelessSession openStatelessSession() {
		return getSessionFactory().openStatelessSession();
	}

	public StatelessSession openStatelessSession(Connection connection) {
		return getSessionFactory().openStatelessSession(connection);
	}
}