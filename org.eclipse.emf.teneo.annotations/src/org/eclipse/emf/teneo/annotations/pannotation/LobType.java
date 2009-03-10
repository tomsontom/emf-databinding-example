/**
 * <copyright>
 * </copyright>
 *
 * $Id: LobType.java,v 1.6 2008/05/27 07:42:23 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Lob Type</b></em>',
 * and utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getLobType()
 * @model
 * @generated
 */
public final class LobType extends AbstractEnumerator {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright =
			"Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public\nLicense v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Martin Taal\n";

	/**
	 * The '<em><b>BLOB</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BLOB</b></em>' literal object isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #BLOB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int BLOB = 0;

	/**
	 * The '<em><b>CLOB</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CLOB</b></em>' literal object isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #CLOB_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CLOB = 1;

	/**
	 * The '<em><b>BLOB</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #BLOB
	 * @generated
	 * @ordered
	 */
	public static final LobType BLOB_LITERAL = new LobType(BLOB, "BLOB");

	/**
	 * The '<em><b>CLOB</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #CLOB
	 * @generated
	 * @ordered
	 */
	public static final LobType CLOB_LITERAL = new LobType(CLOB, "CLOB");

	/**
	 * An array of all the '<em><b>Lob Type</b></em>' enumerators. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final LobType[] VALUES_ARRAY = new LobType[] { BLOB_LITERAL, CLOB_LITERAL, };

	/**
	 * A public read-only list of all the '<em><b>Lob Type</b></em>' enumerators. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<LobType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Lob Type</b></em>' literal with the specified name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static LobType get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			LobType result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Lob Type</b></em>' literal with the specified value. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static LobType get(int value) {
		switch (value) {
			case BLOB:
				return BLOB_LITERAL;
			case CLOB:
				return CLOB_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private LobType(int value, String name) {
		super(value, name);
	}

} // LobType
