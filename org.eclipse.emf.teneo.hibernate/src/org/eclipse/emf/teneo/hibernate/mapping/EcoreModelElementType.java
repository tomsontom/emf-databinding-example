package org.eclipse.emf.teneo.hibernate.mapping;

/**
 * <copyright>
 *
 * Copyright (c) 2009 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Martin Taal
 * </copyright>
 *
 * $Id: EcoreModelElementType.java,v 1.2 2009/03/15 14:49:46 mtaal Exp $
 */

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * Persists references to EClassifiers and EStructuralFeatures as a varchar
 * field.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $ $Date: 2009/03/15 14:49:46 $
 */

public class EcoreModelElementType implements UserType {

	private static final int[] SQL_TYPES = new int[] { Types.VARCHAR };
	private static final String SEPARATOR = "_;_";

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return convertFromString((String) cached);
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return convertToString(value);
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == null && y == null) {
			return true;
		}

		if (x == null || y == null) {
			return false;
		}

		if (x.getClass() != y.getClass()) {
			return false;
		}
		return x.equals(y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		final String value = rs.getString(names[0]);
		if (rs.wasNull()) {
			return null;
		}
		return convertFromString(value);
	}

	private Object convertFromString(String value) {
		final String[] values = value.split(SEPARATOR);
		final String nsuri = values[0];
		final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsuri);
		if (values.length == 1) { // EPackage
			return ePackage;
		} else if (values.length == 2) { // EClassifier
			final String eClassifierName = values[1];
			final EClassifier eClassifier = ePackage
					.getEClassifier(eClassifierName);
			return eClassifier;
		} else {
			final String eClassifierName = values[1];
			final EClassifier eClassifier = ePackage
					.getEClassifier(eClassifierName);
			final EClass eClass = (EClass) eClassifier;
			final String eFeatureName = values[2];
			return eClass.getEStructuralFeature(eFeatureName);
		}
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.VARCHAR);
		} else {
			st.setString(index, convertToString(value));
		}
	}

	private String convertToString(Object value) {
		if (value instanceof EPackage) {
			final String uri = ((EPackage) value).getNsURI();
			return uri;

		} else if (value instanceof EClassifier) {
			final EClassifier eClassifier = (EClassifier) value;
			final String uri = eClassifier.getEPackage().getNsURI();
			final String eClassifierName = eClassifier.getName();
			return uri + SEPARATOR + eClassifierName;

		} else {
			final EStructuralFeature feature = (EStructuralFeature) value;
			final String uri = feature.getEContainingClass().getEPackage()
					.getNsURI();
			final String eClassName = feature.getEContainingClass().getName();
			final String eFeatureName = feature.getName();
			return uri + SEPARATOR + eClassName + SEPARATOR + eFeatureName;
		}
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	public Class<?> returnedClass() {
		return EStructuralFeature.class;
	}

	public int[] sqlTypes() {
		return SQL_TYPES;
	}
}