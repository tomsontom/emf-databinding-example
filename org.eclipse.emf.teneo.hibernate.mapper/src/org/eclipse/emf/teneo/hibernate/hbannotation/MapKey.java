/**
 * <copyright>
 * </copyright>
 *
 * $Id: MapKey.java,v 1.6 2007/07/04 19:31:48 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.teneo.annotations.pannotation.Column;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Map Key</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.MapKey#getColumns <em>Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getMapKey()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EStructuralFeature'"
 * @generated
 */
public interface MapKey extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Columns</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getMapKey_Columns()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Column> getColumns();

} // MapKey
