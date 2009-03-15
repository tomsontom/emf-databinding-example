/**
 * <copyright>
 * </copyright>
 *
 * $Id: BatchSize.java,v 1.1 2009/03/15 15:08:01 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Batch Size</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize#getSize <em>Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getBatchSize()
 * @model annotation="teneo/internal/Target 0='EClass' 1='EReference'"
 * @generated
 */
public interface BatchSize extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(int)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getBatchSize_Size()
	 * @model required="true"
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

} // BatchSize
