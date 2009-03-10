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
 * $Id: DynamicENumUserIntegerType.java,v 1.6 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import org.eclipse.emf.common.util.Enumerator;
import org.hibernate.HibernateException;

/**
 * Implements the EMF UserType for an Enum in a dynamic model, for an integer field.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $ $Date: 2008/02/28 07:08:24 $
 */

public class DynamicENumUserIntegerType extends DynamicENumUserType {

	/** The sql types used for enums */
	private static final int[] SQL_TYPES = new int[] { Types.INTEGER };

	/** Hashmap with string to enum mappings */
	private final HashMap<Integer, Enumerator> localCache = new HashMap<Integer, Enumerator>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[],
	 *      java.lang.Object)
	 */
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		final int value = rs.getInt(names[0]);
		if (rs.wasNull()) {
			return null;
		}

		Integer objValue = new Integer(value);
		Enumerator enumValue = localCache.get(objValue);
		if (enumValue != null) {
			return enumValue;
		}

		enumValue = enumInstance.getEEnumLiteral(objValue.intValue());
		localCache.put(objValue, enumValue);
		return enumValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement,
	 *      java.lang.Object, int)
	 */
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.INTEGER);
		} else {
			st.setInt(index, ((Enumerator) value).getValue());
		}
	}

	/** An enum is stored in one varchar */
	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}
}