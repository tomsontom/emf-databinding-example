/**
 * <copyright>
 * </copyright>
 *
 * $Id: OnDelete.java,v 1.3 2007/07/04 19:31:48 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>On Delete</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getOnDelete()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EStructuralFeature'"
 * @generated
 */
public interface OnDelete extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction
	 * @see #setAction(OnDeleteAction)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getOnDelete_Action()
	 * @model
	 * @generated
	 */
	OnDeleteAction getAction();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(OnDeleteAction value);

} // OnDelete
