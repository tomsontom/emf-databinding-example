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
 * $Id: AnyEObjectType.java,v 1.5 2008/02/28 07:08:24 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.mapping;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Node;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.hibernate.HbStoreException;
import org.hibernate.EntityMode;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.CascadeStyle;
import org.hibernate.engine.ForeignKeys;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.persister.entity.Joinable;
import org.hibernate.type.AbstractComponentType;
import org.hibernate.type.AbstractType;
import org.hibernate.type.AssociationType;
import org.hibernate.type.ForeignKeyDirection;
import org.hibernate.type.Type;

/**
 * Supports persisting the reference to any persistable EObect, it stores the entity name, and the
 * id in a string field
 * 
 * @author <a href="mailto:mkanaley@tibco.com">Mike Kanaley</a>
 */
public class AnyEObjectType extends AbstractType implements AbstractComponentType, AssociationType {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 3857353606004705457L;

	private static final String[] PROPERTY_NAMES = new String[] { "class", "idtype", "idstr" };

	private static final int[] SQL_TYPES = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

	/** Constructor by id */
	private final HashMap<String, Constructor<?>> constructors = new HashMap<String, Constructor<?>>();

	/**
	 * Return the types of the columns that this UserType will serialize into.
	 * 
	 * @return a single column of type VARCHAR.
	 */
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	/** Just returns the value */
	public Object deepCopy(Object value, EntityMode entityMode, SessionFactoryImplementor factory)
			throws HibernateException {
		return value;
	}

	/* See superclass */
	public boolean isMethodOf(Method method) {
		return false;
	}

	/** Checks using equals */
	@Override
	public boolean isSame(Object x, Object y, EntityMode entityMode) throws HibernateException {
		if (x != null) {
			return x.equals(y);
		}
		return x == y;
	}

	/** Compare is not implemented, returning 0 for now */
	@Override
	public int compare(Object x, Object y, EntityMode entityMode) {
		return 0;
	}

	/** Return the column span */
	public int getColumnSpan(Mapping session) throws MappingException {
		return SQL_TYPES.length;
	}

	/** The name of the type */
	public String getName() {
		return "AnyEObject";
	}

	/** Return false for safety */
	public boolean isMutable() {
		return false;
	}

	/** Returns unsupportedexception */
	public Object nullSafeGet(ResultSet rs, String name, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		throw new UnsupportedOperationException("Type is a multicolumn type");
	}

	/** Returns the object from the resultset */
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		final String entityName = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		final String idType = rs.getString(names[1]);
		if (rs.wasNull()) {
			return null;
		}
		final String idStr = rs.getString(names[2]);
		if (rs.wasNull()) {
			return null;
		}

		return session.internalLoad(entityName, getId(idStr, idType), true, false);
	}

	/** Creates an id object of the correct type */
	private Serializable getId(String idStr, String idType) {
		try {
			Constructor<?> constructor = constructors.get(idType);
			if (constructor == null) {
				final Class<?> idClass = this.getClass().getClassLoader().loadClass(idType);
				constructor = idClass.getConstructor(new Class[] { String.class });
				constructors.put(idType, constructor);
			}
			return (Serializable) constructor.newInstance(new Object[] { idStr });
		} catch (Exception e) {
			throw new HbStoreException("Could not create id type for " + idType + " and id " + idStr, e);
		}
	}

	@Override
	public Object hydrate(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		final String entityName = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		final String idType = rs.getString(names[1]);
		if (rs.wasNull()) {
			return null;
		}
		final String idStr = rs.getString(names[2]);
		if (rs.wasNull()) {
			return null;
		}

		return new EObjectCacheEntry(entityName, getId(idStr, idType));
	}

	@Override
	public Object resolve(Object value, SessionImplementor session, Object owner) throws HibernateException {
		EObjectCacheEntry entry = (EObjectCacheEntry) value;
		return session.internalLoad(entry.entityName, entry.id, true, false);
	}

	/*
	 * public Object semiResolve(Object value, SessionImplementor session, Object owner) throws
	 * HibernateException { throw new UnsupportedOperationException("Any mappings may not form part
	 * of a property-ref"); }
	 */

	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		nullSafeSet(st, value, index, null, session);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index, boolean[] settable,
			SessionImplementor session) throws HibernateException, SQLException {

		String entityName = null;
		String idStr = null;
		String idType = null;
		if (value != null) {
			entityName = session.bestGuessEntityName(value);
			Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, value, session);
			idType = id.getClass().getName();
			idStr = id.toString();
			st.setString(index, entityName);
			st.setString(index + 1, idType);
			st.setString(index + 2, idStr);
		} else {
			st.setNull(index, SQL_TYPES[0]);
			st.setNull(index, SQL_TYPES[1]);
			st.setNull(index, SQL_TYPES[2]);
		}
	}

	/** Returns EObject */
	public Class<?> getReturnedClass() {
		return EObject.class;
	}

	public int[] sqlTypes(Mapping mapping) throws MappingException {
		return SQL_TYPES;
	}

	public void setToXMLNode(Node xml, Object value, SessionFactoryImplementor factory) {
		throw new UnsupportedOperationException("Any types cannot be stringified");
	}

	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		return value == null ? "null" : value.getClass().getName();
	}

	public Object fromXMLNode(Node xml, Mapping factory) throws HibernateException {
		throw new UnsupportedOperationException(); // TODO: is this right??
	}

	public static final class EObjectCacheEntry implements Serializable {
		/**
		 * Serial Version ID
		 */
		private static final long serialVersionUID = 1030890286147221359L;

		/** The cached entityName */
		String entityName;

		/** And its id */
		Serializable id;

		EObjectCacheEntry(String entityName, Serializable id) {
			this.entityName = entityName;
			this.id = id;
		}
	}

	@Override
	public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
		final EObjectCacheEntry entry = (EObjectCacheEntry) cached;
		return entry == null ? null : session.internalLoad(entry.entityName, entry.id, true, false);
	}

	@Override
	public Serializable disassemble(Object value, SessionImplementor session, Object owner) throws HibernateException {
		if (value == null) {
			return null;
		}
		final String entityName = session.bestGuessEntityName(value);
		final Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, value, session);
		return new EObjectCacheEntry(entityName, id);
	}

	@Override
	public boolean isAnyType() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public Object replace(Object original, Object target, SessionImplementor session, Object owner, Map copyCache)
			throws HibernateException {
		if (original == null) {
			return null;
		} else {
			final String entityName = session.bestGuessEntityName(original);
			final Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, original, session);
			return session.internalLoad(entityName, id, true, false);
		}
	}

	public CascadeStyle getCascadeStyle(int i) {
		return CascadeStyle.NONE;
	}

	public FetchMode getFetchMode(int i) {
		return FetchMode.SELECT;
	}

	public String[] getPropertyNames() {
		return PROPERTY_NAMES;
	}

	public Object getPropertyValue(Object component, int i, SessionImplementor session) throws HibernateException {
		if (component != null) {
			final String entityName = session.bestGuessEntityName(component);
			Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, component, session);
			switch (i) {
				case 0:
					return session.bestGuessEntityName(component);
				case 1:
					return id.getClass().getName();
				case 2:
					return id.toString();
				default:
					throw new HbStoreException("Index " + i + "not supported");
			}
		}
		return null;
	}

	public Object[] getPropertyValues(Object component, SessionImplementor session) throws HibernateException {
		if (component != null) {
			final String entityName = session.bestGuessEntityName(component);
			Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, component, session);
			return new Object[] { session.bestGuessEntityName(component), id.getClass().getName(), id.toString() };
		}
		return null;
	}

	public Type[] getSubtypes() {
		return new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING };
	}

	public void setPropertyValues(Object component, Object[] values, EntityMode entityMode) throws HibernateException {
		throw new UnsupportedOperationException();
	}

	public Object[] getPropertyValues(Object component, EntityMode entityMode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isComponentType() {
		return true;
	}

	public ForeignKeyDirection getForeignKeyDirection() {
		return ForeignKeyDirection.FOREIGN_KEY_FROM_PARENT;
	}

	@Override
	public boolean isAssociationType() {
		return true;
	}

	public boolean useLHSPrimaryKey() {
		return false;
	}

	public Joinable getAssociatedJoinable(SessionFactoryImplementor factory) {
		throw new UnsupportedOperationException("any types do not have a unique referenced persister");
	}

	@Override
	public boolean isModified(Object old, Object current, boolean[] checkable, SessionImplementor session)
			throws HibernateException {
		if (current == null) {
			return old != null;
		}
		if (old == null) {
			return current != null;
		}

		final EObjectCacheEntry entry = (EObjectCacheEntry) old;
		final String entityName = session.bestGuessEntityName(current);
		final Serializable id = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, current, session);
		if (checkable[0] && entry.entityName.compareTo(entityName) != 0) {
			return true;
		}
		if ((checkable[1] || checkable[2]) && !id.equals(entry.id)) {
			return true;
		}
		return false;
	}

	public String getAssociatedEntityName(SessionFactoryImplementor factory) throws MappingException {
		throw new UnsupportedOperationException("any types do not have a unique referenced persister");
	}

	public boolean[] getPropertyNullability() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getOnCondition(String alias, SessionFactoryImplementor factory, Map enabledFilters)
			throws MappingException {
		throw new UnsupportedOperationException();
	}

	public boolean isReferenceToPrimaryKey() {
		return true;
	}

	public String getRHSUniqueKeyPropertyName() {
		return null;
	}

	public String getLHSPropertyName() {
		return null;
	}

	public boolean isAlwaysDirtyChecked() {
		return false;
	}

	public boolean isEmbeddedInXML() {
		return false;
	}

	public boolean[] toColumnNullness(Object value, Mapping mapping) {
		boolean[] result = new boolean[getColumnSpan(mapping)];
		if (value != null) {
			Arrays.fill(result, true);
		}
		return result;
	}

	public boolean isDirty(Object old, Object current, boolean[] checkable, SessionImplementor session)
			throws HibernateException {
		return isDirty(old, current, session);
	}

	public boolean isEmbedded() {
		return false;
	}
}