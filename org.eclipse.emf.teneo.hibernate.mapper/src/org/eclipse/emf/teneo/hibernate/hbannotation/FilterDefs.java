/**
 * <copyright>
 * </copyright>
 *
 * $Id: FilterDefs.java,v 1.1 2008/04/23 15:44:26 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Defs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.FilterDefs#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getFilterDefs()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EClass' 1='EReference'"
 * @generated
 */
public interface FilterDefs extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getFilterDefs_Value()
	 * @model required="true"
	 * @generated
	 */
	EList<ParamDef> getValue();

} // FilterDefs
