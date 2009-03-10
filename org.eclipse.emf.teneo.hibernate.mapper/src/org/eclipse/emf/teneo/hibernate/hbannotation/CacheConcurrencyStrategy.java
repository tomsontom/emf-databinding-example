/**
 * <copyright>
 * </copyright>
 *
 * $Id: CacheConcurrencyStrategy.java,v 1.6 2007/07/04 19:31:48 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Cache Concurrency Strategy</b></em>',
 * and utility methods for working with them. <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCacheConcurrencyStrategy()
 * @model
 * @generated
 */
public enum CacheConcurrencyStrategy implements Enumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "NONE"), /**
	 * The '<em><b>READ ONLY</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #READ_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	READ_ONLY(1, "READ_ONLY", "READ_ONLY"), /**
	 * The '<em><b>NONSTRICT READ WRITE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONSTRICT_READ_WRITE_VALUE
	 * @generated
	 * @ordered
	 */
	NONSTRICT_READ_WRITE(2, "NONSTRICT_READ_WRITE", "NONSTRICT_READ_WRITE"), /**
	 * The '<em><b>READ WRITE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #READ_WRITE_VALUE
	 * @generated
	 * @ordered
	 */
	READ_WRITE(3, "READ_WRITE", "READ_WRITE"), /**
	 * The '<em><b>TRANSACTIONAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONAL_VALUE
	 * @generated
	 * @ordered
	 */
	TRANSACTIONAL(4, "TRANSACTIONAL", "TRANSACTIONAL");

/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;
/**
	 * The '<em><b>READ ONLY</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READ ONLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READ_ONLY
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int READ_ONLY_VALUE = 1;
/**
	 * The '<em><b>NONSTRICT READ WRITE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONSTRICT READ WRITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONSTRICT_READ_WRITE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONSTRICT_READ_WRITE_VALUE = 2;
/**
	 * The '<em><b>READ WRITE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>READ WRITE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #READ_WRITE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int READ_WRITE_VALUE = 3;
/**
	 * The '<em><b>TRANSACTIONAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TRANSACTIONAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSACTIONAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TRANSACTIONAL_VALUE = 4;
/**
	 * An array of all the '<em><b>Cache Concurrency Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final CacheConcurrencyStrategy[] VALUES_ARRAY =
		new CacheConcurrencyStrategy[] {
			NONE,
			READ_ONLY,
			NONSTRICT_READ_WRITE,
			READ_WRITE,
			TRANSACTIONAL,
		};
/**
	 * A public read-only list of all the '<em><b>Cache Concurrency Strategy</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<CacheConcurrencyStrategy> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

/**
	 * Returns the '<em><b>Cache Concurrency Strategy</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CacheConcurrencyStrategy get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CacheConcurrencyStrategy result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

/**
	 * Returns the '<em><b>Cache Concurrency Strategy</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CacheConcurrencyStrategy getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			CacheConcurrencyStrategy result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

/**
	 * Returns the '<em><b>Cache Concurrency Strategy</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CacheConcurrencyStrategy get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case READ_ONLY_VALUE: return READ_ONLY;
			case NONSTRICT_READ_WRITE_VALUE: return NONSTRICT_READ_WRITE;
			case READ_WRITE_VALUE: return READ_WRITE;
			case TRANSACTIONAL_VALUE: return TRANSACTIONAL;
		}
		return null;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;
/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;
/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private CacheConcurrencyStrategy(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
}
