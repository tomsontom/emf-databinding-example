/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbMapKey.java,v 1.1 2008/07/06 16:25:28 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.teneo.annotations.pannotation.Column;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hb Map Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey#getColumns <em>Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbMapKey()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EStructuralFeature'"
 * @generated
 */
public interface HbMapKey extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbMapKey_Columns()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Column> getColumns();

} // HbMapKey
