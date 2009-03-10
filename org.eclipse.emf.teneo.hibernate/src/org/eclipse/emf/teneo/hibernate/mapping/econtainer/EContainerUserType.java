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
 * $Id: EContainerUserType.java,v 1.10 2008/06/28 22:41:50 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.econtainer;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Hashtable;
import java.util.Map;

import org.dom4j.Node;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.teneo.classloader.ClassLoaderResolver;
import org.eclipse.emf.teneo.classloader.StoreClassLoadException;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.mapping.identifier.IdentifierCacheHandler;
import org.hibernate.EntityMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.TransientObjectException;
import org.hibernate.engine.ForeignKeys;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.impl.SessionImpl;
import org.hibernate.persister.entity.Joinable;
import org.hibernate.type.AbstractType;
import org.hibernate.type.AssociationType;
import org.hibernate.type.ForeignKeyDirection;
import org.hibernate.type.NullableType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

/**
 * Implements the EMF UserType for an Enum
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.10 $ $Date: 2008/06/28 22:41:50 $
 */

public class EContainerUserType extends AbstractType implements CompositeUserType, AssociationType {
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 6385066726834417274L;

	/** Separator used in encoding the class name and value */
	private static final String ENCODING_SEPARATOR = ";";

	/** The property names */
	private static final String[] propertyNames = new String[] { "containerclass", "containerid" };

	/** The property types (two strings) */
	private static final Type[] propertyTypes = new Type[] { Hibernate.STRING, Hibernate.STRING };

	/** The sql types */
	private static final int[] sqlTypes = new int[] { Types.VARCHAR, Types.VARCHAR };

	/** HashTable with cached constructors */
	private final Hashtable<String, Constructor<?>> constructorCache = new Hashtable<String, Constructor<?>>();

	/** HashTable with identifier types hashed by entityname */
	private final Hashtable<String, Type> identifierTypeCache = new Hashtable<String, Type>();

	/**
	 * Abstract method from super class, currently does not really print anything meaningfull
	 */
	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		return (value != null ? "EContainer: " + value.getClass().getName() : "EContainer null value");
	}

	/** The generic class returned (nl. Object) */
	public Class<?> getReturnedClass() {
		return Object.class;
	}

	/** Just returns passed value */
	public Object deepCopy(Object value, EntityMode entityMode, SessionFactoryImplementor factory)
			throws HibernateException {
		return value;
	}

	/** Not supported */
	public Object fromXMLNode(Node xml, Mapping factory) throws HibernateException {
		throw new UnsupportedOperationException("not supported for econtainer");
	}

	/** Two columns */
	public int getColumnSpan(Mapping mapping) throws MappingException {
		return 2;
	}

	/** A name */
	public String getName() {
		return "econtainer";
	}

	/** Does nothing */
	public boolean isDirty(Object old, Object current, boolean[] checkable, SessionImplementor session)
			throws HibernateException {
		return isDirty(old, current, session);
	}

	/** Not supported */
	public Object nullSafeGet(ResultSet rs, String name, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		throw new UnsupportedOperationException("not supported for econtainer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.type.Type#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int,
	 * boolean[], org.hibernate.engine.SessionImplementor)
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index, boolean[] settable,
			SessionImplementor session) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		if (settable == null || settable[0]) {
			nullSafeSet(st, value, index, session);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.type.Type#replace(java.lang.Object, java.lang.Object,
	 * org.hibernate.engine.SessionImplementor, java.lang.Object, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public Object replace(Object original, Object target, SessionImplementor session, Object owner, Map copyCache)
			throws HibernateException {
		return replace(original, target, session, owner);
	}

	/** Not supported */
	public void setToXMLNode(Node node, Object value, SessionFactoryImplementor factory) throws HibernateException {
		throw new UnsupportedOperationException("not supported for econtainer");
	}

	/** Two varchars */
	public int[] sqlTypes(Mapping mapping) throws MappingException {
		return sqlTypes;
	}

	/**
	 * Returns array of boolean denoting which columns are null when the value is null
	 */
	public boolean[] toColumnNullness(Object value, Mapping mapping) {
		boolean[] result = new boolean[getColumnSpan(mapping)];
		if (value != null) {
			for (int i = 0; i < result.length; i++) {
				result[i] = true;
			}
		}
		return result;
	}

	/**
	 * True as this is very similar to anytype, needed to return true because otherwise Hibernate
	 * would not recognize that the container object should be saved before this object and saving
	 * transient instance was called.
	 */
	@Override
	public boolean isAnyType() {
		return true;
	}

	/**
	 * Methods from
	 * 
	 * /** Reference to other object so true
	 */
	@Override
	public boolean isAssociationType() {
		return true;
	}

	/** And consists of multiple other objects */
	@Override
	public boolean isComponentType() {
		return false;
	}

	/** Not supported */
	public String getAssociatedEntityName(SessionFactoryImplementor factory) throws MappingException {
		throw new UnsupportedOperationException("Econtainer type is a generic type, no specific associated entity");
	}

	/** Not supported */
	public Joinable getAssociatedJoinable(SessionFactoryImplementor factory) throws MappingException {
		throw new UnsupportedOperationException("Econtainer type is a generic type, no specific associated entity");
	}

	/** From parent */
	public ForeignKeyDirection getForeignKeyDirection() {
		return ForeignKeyDirection.FOREIGN_KEY_FROM_PARENT;
	}

	/** Returns null */
	public String getLHSPropertyName() {
		return null;
	}

	/** Not supported */
	@SuppressWarnings("unchecked")
	public String getOnCondition(String alias, SessionFactoryImplementor factory, Map enabledFilters)
			throws MappingException {
		throw new UnsupportedOperationException("not supported for econtainer");
	}

	/** Returns null */
	public String getRHSUniqueKeyPropertyName() {
		return null;
	}

	/** False */
	public boolean isAlwaysDirtyChecked() {
		return false;
	}

	/** False */
	public boolean isEmbeddedInXML() {
		return false;
	}

	/** False */
	public boolean useLHSPrimaryKey() {
		return false;
	}

	/** Does nothing */
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	/** Is equal, does object equality? */
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	/** Just returns the hashcode of the passed object */
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	/** Not mutable */
	public boolean isMutable() {
		return false;
	}

	/** Can be any object */
	public Class<?> returnedClass() {
		return Object.class;
	}

	/**
	 * Translates the serialized cached object to a real object
	 */
	@Override
	public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
		// already correct
		if (!(cached instanceof ContainerPointer)) {
			final String entityName = session.bestGuessEntityName(cached);
			final Serializable idObject = getID(entityName, cached, session);
			return session.internalLoad(entityName, idObject, false, false);
		} else {
			final ContainerPointer cp = (ContainerPointer) cached;
			return cp.getObject(session);
		}
	}

	/** Create a containerpointer */
	public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
		if (value == null) {
			return null;
		}
		try {
			final String entityName = session.bestGuessEntityName(value);
			final Object idObject = getID(entityName, value, session);
			return new ContainerPointer(getIdentifierType(entityName, session), entityName, idObject.toString());
		} catch (TransientObjectException toe) {
			return null;
		}
	}

	@Override
	public Serializable disassemble(Object value, SessionImplementor session, Object owner) throws HibernateException {
		return disassemble(value, session);
	}

	/** Propertynames: containerclass, containerid */
	public String[] getPropertyNames() {
		return propertyNames;
	}

	/** The propertyTypes */
	public Type[] getPropertyTypes() {
		return propertyTypes;
	}

	/** Not supported */
	public Object getPropertyValue(Object component, int property) throws HibernateException {
		final Object container = ((InternalEObject) component).eContainer();
		if (container == null) {
			return null;
		}

		if (property == 0) {
			return container.getClass().getName();
		}
		if (property == 1) {
			return IdentifierCacheHandler.getInstance().getID(container);
		}

		throw new HbMapperException("Property: " + property + " not supported in " + component.getClass().getName());
	}

	/** Load the object on the basis of the data in the resultset */
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		final String cc = rs.getString(names[0]); // container class
		if (cc == null) {
			return null;
		}
		final String idStr = rs.getString(names[1]);
		if (idStr == null) {
			return null;
		}

		final Object obj = session.internalLoad(cc, extractID(getIdentifierType(cc, session), idStr), false, false);
		return obj;
	}

	/** Set the data in the resultset */
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
			st.setNull(index + 1, Types.VARCHAR);
		} else {
			// EObject eobj = (EObject) value;
			final String ename = session.bestGuessEntityName(value);
			st.setString(index, ename);
			st.setString(index + 1, createIDString(getIdentifierType(ename, session), getID(ename, value, session)));
		}
	}

	/** Returns the identifiertype on the basis of the class of the passed object */
	private Type getIdentifierType(String entityName, SessionImplementor session) {
		Type type = identifierTypeCache.get(entityName);
		if (type != null) {
			return type;
		}

		final Type identifierType =
				((SessionImpl) session).getFactory().getClassMetadata(entityName).getIdentifierType();
		identifierTypeCache.put(entityName, identifierType);
		return identifierType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.CompositeUserType#replace(java.lang.Object, java.lang.Object,
	 * org.hibernate.engine.SessionImplementor, java.lang.Object)
	 */
	public Object replace(Object original, Object target, SessionImplementor session, Object owner)
			throws HibernateException {
		if (original == null) {
			return null;
		}
		final String ename = session.bestGuessEntityName(original);
		final Serializable id = getID(ename, original, session);
		return session.internalLoad(ename, id, false, false);
	}

	/** Returns the id of the passed object */
	private Serializable getID(String entityName, Object value, SessionImplementor session) {
		Serializable result = ForeignKeys.getEntityIdentifierIfNotUnsaved(entityName, value, session);

		if (result != null) {
			return result;
		}
		return (Serializable) IdentifierCacheHandler.getInstance().getID(value);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.CompositeUserType#setPropertyValue(java.lang.Object, int,
	 *      java.lang.Object)
	 */
	public void setPropertyValue(Object component, int property, Object value) throws HibernateException {
		// do nothing and hope for the best
	}

	/** Creates the serializable id object from a string */
	private Serializable extractID(Type type, String idString) {
		// first handle the most common case
		if (type instanceof NullableType) {
			final NullableType ntype = (NullableType) type;
			return (Serializable) ntype.fromStringValue(idString);
		}

		// for all other cases the classname is encoded into the field
		final String className = idString.substring(0, idString.indexOf(ENCODING_SEPARATOR));
		final String strValue = idString.substring(1 + idString.indexOf(ENCODING_SEPARATOR));

		Constructor<?> constructor = constructorCache.get(className);
		if (constructor == null) {
			try {
				final Class<?> clazz = ClassLoaderResolver.classForName(className);
				constructor = clazz.getConstructor(new Class[] { String.class });
			} catch (StoreClassLoadException e) {
				throw new HbMapperException("Class " + className + " not found");
			} catch (NoSuchMethodException e) {
				throw new HbMapperException("Class " + className +
						" does not have a constructor with a String parameter!");
			}
		}
		if (constructor == null) {
			throw new HbMapperException("Class " + className + " does not have a constructor with a String parameter!");
		}

		try {
			return (Serializable) constructor.newInstance(new Object[] { strValue });
		} catch (InvocationTargetException e) {
			throw new HbMapperException("Can not instantiate: " + className + " using value " + strValue);
		} catch (InstantiationException e) {
			throw new HbMapperException("Can not instantiate: " + className + " using value " + strValue);
		} catch (IllegalAccessException e) {
			throw new HbMapperException("Can not instantiate: " + className + " using value " + strValue);
		}
	}

	/** Creates an id string from a serializable object */
	private String createIDString(Type type, Serializable id) {
		if (type instanceof NullableType) {
			final NullableType ntype = (NullableType) type;
			return ntype.toString(id);
		}

		return id.getClass().getName() + ENCODING_SEPARATOR + id.toString();
	}

	/**
	 * Creates a type of proxy object which keeps the container class and container id
	 */
	private class ContainerPointer implements Serializable {
		/**
		 * Serial version id
		 */
		private static final long serialVersionUID = -2777938032663239346L;

		/** The container class */
		private final String container;

		/** The id object */
		private final Serializable id;

		/** Constructor */
		ContainerPointer(Type type, String theContainer, String theIDStr) {
			container = theContainer;
			id = extractID(type, theIDStr);
		}

		/** Returns the container object pointed to */
		private Object getObject(SessionImplementor session) {
			return session.internalLoad(container, id, false, false);
		}
	}
}