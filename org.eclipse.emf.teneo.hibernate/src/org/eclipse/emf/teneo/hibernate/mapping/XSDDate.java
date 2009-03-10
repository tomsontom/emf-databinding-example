/**
 * <copyright>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Brian Vetter
 * </copyright>
 *
 * $Id: XSDDate.java,v 1.2 2007/07/04 19:27:28 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.teneo.hibernate.HbStoreException;
import org.eclipse.emf.teneo.util.EcoreDataTypes;
import org.hibernate.HibernateException;

/**
 * Implements the hibernate UserType for EMF's XMLGregorianCalendar ("date" type in XSD).
 * 
 * @author <a href="mailto:bvetter@alterpoint.com">Brian Vetter</a>
 * @version $Id
 */
public class XSDDate extends XSDDateTime {

	static final long serialVersionUID = 1;

	// local copy of the datatype facatory
	private final DatatypeFactory dataTypeFactory;

	public XSDDate() {
		try {
			dataTypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new HbStoreException("Exception ", e);
		}
	}

	/*
	 * Returns the DATETIME type that maps to the sql TIMESTAMP type
	 * 
	 * @see org.hibernate.type.NullableType#sqlType()
	 */
	@Override
	public int sqlType() {
		return Types.DATE;
	}

	/*
	 * returns a name for the user type
	 * 
	 * @see org.hibernate.type.Type#getName()
	 */
	@Override
	public String getName() {
		return "xmldate";
	}

	/*
	 * Transform the date in the resultSet into a XMLGregorianCalendar instance.
	 * 
	 * @see org.hibernate.type.NullableType#get(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Object get(ResultSet resultSet, String name) throws SQLException {
		Date date = resultSet.getDate(name);
		if (date == null) {
			return null;
		}
		return EcoreDataTypes.INSTANCE.getXMLGregorianCalendar(date);
	}

	/*
	 * Transform the XMLCalendar into a date type to store in the database
	 * 
	 * @see org.hibernate.type.NullableType#set(java.sql.PreparedStatement, java.lang.Object, int)
	 */
	@Override
	public void set(PreparedStatement statement, Object value, int index) throws SQLException {
		java.sql.Date d = new java.sql.Date(((XMLGregorianCalendar) value).toGregorianCalendar().getTime().getTime());
		statement.setDate(index, d);
	}

	/*
	 * @see org.hibernate.type.NullableType#fromStringValue(java.lang.String)
	 */
	@Override
	public Object fromStringValue(String s) throws HibernateException {
		return dataTypeFactory.newXMLGregorianCalendar(s);
	}
}
