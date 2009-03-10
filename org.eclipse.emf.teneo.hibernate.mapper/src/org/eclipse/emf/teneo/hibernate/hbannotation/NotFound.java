/**
 * <copyright>
 * </copyright>
 *
 * $Id: NotFound.java,v 1.1 2008/08/04 05:15:00 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Not Found</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFound#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getNotFound()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EReference'"
 * @generated
 */
public interface NotFound extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Action</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction
	 * @see #setAction(NotFoundAction)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getNotFound_Action()
	 * @model required="true"
	 * @generated
	 */
	NotFoundAction getAction();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFound#getAction <em>Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction
	 * @see #getAction()
	 * @generated
	 */
	void setAction(NotFoundAction value);

} // NotFound
