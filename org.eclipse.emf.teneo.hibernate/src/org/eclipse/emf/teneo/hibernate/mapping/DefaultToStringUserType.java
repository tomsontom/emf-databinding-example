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
 *   Michael Kanaley, TIBCO Software Inc.
 * </copyright>
 *
 * $Id: DefaultToStringUserType.java,v 1.4 2008/02/28 07:08:24 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.mapping;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.hibernate.mapper.HbMapperConstants;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

/**
 * Provides a Hibernate extension (called a UserType) to support serializing EMF custom datatypes into VARCHAR columns.
 * The custom DataType mappers provided by this plugin have the job of registering Hibernate typedefs that use this UserType implementation.
 * 
 * This class is intended for contribution to Teneo.
 * 
 * @author <a href="mailto:mkanaley@tibco.com">Mike Kanaley</a>
 */
public class DefaultToStringUserType implements UserType, ParameterizedType {

	private static final int[] SQL_TYPES = { Types.VARCHAR };
	private EFactory eFactory;
	private EDataType eDataType;

    /**
     * Return the types of the columns that this UserType will serialize into.
     * @return a single column of type VARCHAR.
     */
	public int[] sqlTypes() { return SQL_TYPES; }

    /**
     * Return the Java class of the object that is serialized for the column.
     * @return the Java instance class associated with the EMF DataType. 
     */
	public Class<?> returnedClass() { return this.eDataType.getInstanceClass(); }

    /**
     * Is this datatype mutable?
     * @return Being conservative - false always.
     */
	public boolean isMutable() { return false; }

    /**
     * Construct a new VARCHAR custom data type.
     */
	public DefaultToStringUserType() {}

    /**
     * Provide a copy of the datatypes. Converts to String and then back to datatype again.
     * @param value the value to copy.
     * @return the value always.
     */
	public Object deepCopy(Object value) {
		String data = this.eFactory.convertToString(this.eDataType, value);
		return this.eFactory.createFromString(this.eDataType, data);
	}

    /**
     * Are the two objects equal?
     * @param x an object to compare.
     * @param y an object to compare.
     * @return a standard equals test between the objects.
     */
	public boolean equals(Object x, Object y) {
		if (x == y) return true;
		if (x == null || y == null) return false;
		return x.equals(y);
	}

    /**
     * Populate the model object property from the ResultSet.
     * @param resultSet the non-null ResultSet from which the field will be populated.
     * @param names the names of the columns.
     * @param owner the owning object.
     * @return null if the column's value is null; otherwise, use the EMF factory to construct a new instance of the custom EMF data type from the contents of the String value of the column.
     * @throws SQLException if the value cannot be retrieved from the ResultSet.
     */
	public Object nullSafeGet(ResultSet resultSet,
							  String[] names,
							  Object owner)
			throws SQLException {

		String data = resultSet.getString(names[0]);
		if (data == null) {
			return null;
		}
		return this.eFactory.createFromString(this.eDataType, data);
	}

    /**
     * Populate the database statement from the model object property.
     * @param statement the non-null Statement to insert the value into.
     * @param value the object to convert.
     * @param index the index into the statement where to insert the converted value.
     * @throws SQLException if the converted value cannot be set in the statement.
     */
	public void nullSafeSet(PreparedStatement statement,
							Object value,
							int index)
			throws SQLException {
		String pvalue = null;
		if (value != null) {
			pvalue = this.eFactory.convertToString(this.eDataType, value); 
		}
		if (pvalue != null) {
			statement.setString(index, pvalue);
		} else {
			statement.setNull(index, Types.VARCHAR);			
		}
	}

    /**
     * No-op implementation.
     * @param value the value to dissemble.
     * @return the value passed in.
     */
	public Serializable disassemble(Object value) {
		return (Serializable) value;
	}

    /**
     * No-op implementation.
     * @param cachedValue the value to assemble.
     * @param owner the owning object.
     * @return the cachedValue passed in.
     */
	public Object assemble(Serializable cachedValue, Object owner) {
		return cachedValue;
	}

    /**
     * No-op implementation.
     * @param original the value to replace.
     * @param target the target object.
     * @param owner the owning object.
     * @return the original value passed in.
     */
	public Object replace(Object original, Object target, Object owner) {
		return original;
	}

    /**
     * No-op implementation.
     * @param x the object to get the hashcode for.
     * @return x's hashcode.
     */
	public int hashCode(Object x) {
		return x.hashCode();
	}
	
	/************************************************************************
	 * ParameterizedType implementation
	 ************************************************************************/
    /**
     * Read in the type parameters from the Hibernate mapping and determine the EMF factory and custom data type to use.
     * @param properties the properties containing key value pairs for the {@link #PACKAGE_IMPLEMENTATION_CLASS_NAME_PROPERTY} and {@link #ATTRIBUTE_NAME_PROPERTY} parameters.
     */
	public void setParameterValues(Properties properties) {
		final String ePackageNsUri = properties.getProperty(HbMapperConstants.EPACKAGE_PARAM);
		if (ePackageNsUri == null || ePackageNsUri.length() == 0) {
			throw new IllegalArgumentException("Could not find custom UserType property " + HbMapperConstants.EPACKAGE_PARAM);
		}
		final EPackage epackage = EPackage.Registry.INSTANCE.getEPackage(ePackageNsUri);
		if (epackage == null) {
			throw new IllegalArgumentException("Could not find ePackage using nsuri " + ePackageNsUri);
		}
		this.eFactory = epackage.getEFactoryInstance();
		final String edatatypeName = properties.getProperty(HbMapperConstants.EDATATYPE_PARAM);
		if (edatatypeName == null) {
			throw new IllegalArgumentException("Could not find custom UserType property " + HbMapperConstants.EDATATYPE_PARAM);
		}
		final EClassifier eclassifier = epackage.getEClassifier(edatatypeName);
		if (eclassifier instanceof EDataType) {
			this.eDataType = (EDataType)eclassifier;
		} else {
			if (eclassifier == null) {
				throw new IllegalArgumentException("Missing attribute " + edatatypeName + " in package implementation " + epackage.getName());
			} else {
				throw new IllegalArgumentException("Found property of type " + eclassifier.getClass().getName() + " when an EDataType was expected.");
			}
		}
	}
}