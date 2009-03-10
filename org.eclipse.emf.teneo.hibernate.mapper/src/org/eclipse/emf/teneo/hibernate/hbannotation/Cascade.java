/**
 * <copyright>
 * </copyright>
 *
 * $Id: Cascade.java,v 1.8 2009/03/07 21:15:20 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Cascade</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cascade#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCascade()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EStructuralFeature'"
 * @generated
 */
public interface Cascade extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute list. The list contents are of
	 * type {@link org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType}. The literals are
	 * from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute list.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCascade_Value()
	 * @model
	 * @generated
	 */
	EList<HbCascadeType> getValue();

} // Cascade
