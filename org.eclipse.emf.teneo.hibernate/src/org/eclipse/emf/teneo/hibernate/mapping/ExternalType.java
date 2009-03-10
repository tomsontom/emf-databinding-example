/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 *
 * $Id: ExternalType.java,v 1.2 2008/12/17 06:13:20 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.mapping;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.teneo.hibernate.mapper.HbMapperConstants;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

/**
 * Provides a way to store external references (references to objects not in the
 * same datastore) as a string/uri.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 */
public class ExternalType implements UserType, ParameterizedType {

	private static final int[] SQL_TYPES = { Types.VARCHAR };
	private EClass eClass;

	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	public Class<?> returnedClass() {
		return eClass.getInstanceClass();
	}

	public boolean isMutable() {
		return true;
	}

	public ExternalType() {
	}

	public Object deepCopy(Object value) {
		return value;
	}

	public boolean equals(Object x, Object y) {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		return x.equals(y);
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
			throws SQLException {

		final String data = resultSet.getString(names[0]);
		if (data == null) {
			return null;
		}

		// now create a new instance and set its proxyuri
		final InternalEObject newValue = (InternalEObject) eClass.getEPackage()
				.getEFactoryInstance().create(eClass);
		final URI uri = URI.createURI(data);
		newValue.eSetProxyURI(uri);
		return newValue;
	}

	public void nullSafeSet(PreparedStatement statement, Object value, int index)
			throws SQLException {
		String pvalue = null;
		if (value != null) {
			final Resource res = ((InternalEObject) value).eResource();
			URI uri = res.getURI();
			final String fragment = res.getURIFragment((EObject) value);
			uri = uri.appendFragment(fragment);
			pvalue = uri.toString();
		}
		if (pvalue != null) {
			statement.setString(index, pvalue);
		} else {
			statement.setNull(index, Types.VARCHAR);
		}
	}

	public Serializable disassemble(Object value) {
		return (Serializable) value;
	}

	public Object assemble(Serializable cachedValue, Object owner) {
		return cachedValue;
	}

	public Object replace(Object original, Object target, Object owner) {
		return original;
	}

	public int hashCode(Object x) {
		return x.hashCode();
	}

	public void setParameterValues(Properties properties) {
		final String ePackageNsUri = properties
				.getProperty(HbMapperConstants.EPACKAGE_PARAM);
		if (ePackageNsUri == null || ePackageNsUri.length() == 0) {
			throw new IllegalArgumentException(
					"Could not find custom UserType property "
							+ HbMapperConstants.EPACKAGE_PARAM);
		}
		final EPackage epackage = EPackage.Registry.INSTANCE
				.getEPackage(ePackageNsUri);
		if (epackage == null) {
			throw new IllegalArgumentException(
					"Could not find ePackage using nsuri " + ePackageNsUri);
		}
		final String eClassName = properties
				.getProperty(HbMapperConstants.ECLASS_NAME_META);
		if (eClassName == null) {
			throw new IllegalArgumentException(
					"Could not find custom UserType property "
							+ HbMapperConstants.ECLASS_NAME_META);
		}
		final EClassifier eclassifier = epackage.getEClassifier(eClassName);
		if (eclassifier instanceof EClass) {
			eClass = (EClass) eclassifier;
		} else {
			if (eclassifier == null) {
				throw new IllegalArgumentException("Missing eClass "
						+ eClassName + " in package implementation "
						+ epackage.getName());
			} else {
				throw new IllegalArgumentException("Found property of type "
						+ eclassifier.getClass().getName()
						+ " when an EClass was expected.");
			}
		}
	}
}