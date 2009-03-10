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
 * $Id: QNameUserType.java,v 1.2 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.xml.namespace.QName;

import org.eclipse.emf.teneo.hibernate.HbStoreException;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Implements the Hibernate UserType for the QName. It stores the three parts of the qname in one
 * varchar.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $ $Date: 2008/02/28 07:08:24 $
 */

public class QNameUserType implements UserType {

	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
	 */
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
	 */
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	/** Compares the int values of the enumerates */
	public boolean equals(Object x, Object y) throws HibernateException {
		// todo: check compare on null values
		if (x == null && y == null) {
			return true;
		}

		if (x == null || y == null) {
			return false;
		}

		if (x.getClass() != y.getClass()) {
			return false;
		}

		final QName q1 = (QName) x;
		final QName q2 = (QName) y;

		return q1.toString().compareTo(q2.toString()) == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
	public int hashCode(Object x) throws HibernateException {
		return x.toString().hashCode();
	}

	/** Not mutable */
	public boolean isMutable() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[],
	 *      java.lang.Object)
	 */
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		final String str = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		return convertFromString(str);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
	 *      java.lang.Object, int)
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			st.setString(index, convertToString((QName) value));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	/** Returns the parameterizezd enumType */
	public Class<?> returnedClass() {
		return QName.class;
	}

	/** An enum is stored in one varchar */
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	protected String convertToString(QName qName) {
		return "{" + qName.getNamespaceURI() + "}" + qName.getPrefix() + ":" + qName.getLocalPart();
	}

	protected QName convertFromString(String str) {
		if (str.indexOf("{") == -1) {
			throw new HbStoreException("String " + str + " can not be converted to a QName, missing starting {");
		}
		final int endIndexNS = str.indexOf("}");
		if (endIndexNS == -1) {
			throw new HbStoreException("String " + str +
					" can not be converted to a QName, missing end ns delimiter } ");
		}
		final int prefixIndex = str.indexOf(":", endIndexNS);
		if (prefixIndex == -1) {
			throw new HbStoreException("String " + str + " can not be converted to a QName, missing prefix delimiter :");
		}
		final String ns = str.substring(1, endIndexNS);
		final String prefix = str.substring(endIndexNS + 1, prefixIndex);
		final String localPart = str.substring(prefixIndex + 1);
		return new QName(ns, localPart, prefix);
	}
}