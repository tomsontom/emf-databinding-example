/**
 * <copyright>
 * </copyright>
 *
 * $Id: ManyToOne.java,v 1.6 2008/09/21 19:03:18 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Many To One</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getTargetEntity <em>Target Entity</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getCascade <em>Cascade</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getFetch <em>Fetch</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#isOptional <em>Optional</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToOne()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EReference'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='IsNotManyValued'"
 * @generated
 */
public interface ManyToOne extends PAnnotation{
	/**
	 * Returns the value of the '<em><b>Fetch</b></em>' attribute. The default value is <code>"EAGER"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.annotations.pannotation.FetchType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fetch</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Fetch</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.FetchType
	 * @see #setFetch(FetchType)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToOne_Fetch()
	 * @model default="EAGER"
	 * @generated
	 */
	FetchType getFetch();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getFetch <em>Fetch</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.FetchType
	 * @see #isSetFetch()
	 * @see #unsetFetch()
	 * @see #getFetch()
	 * @generated
	 */
	void setFetch(FetchType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getFetch <em>Fetch</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFetch()
	 * @see #getFetch()
	 * @see #setFetch(FetchType)
	 * @generated
	 */
	void unsetFetch();

	/**
	 * Returns whether the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getFetch <em>Fetch</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Fetch</em>' attribute is set.
	 * @see #unsetFetch()
	 * @see #getFetch()
	 * @see #setFetch(FetchType)
	 * @generated
	 */
	boolean isSetFetch();

	/**
	 * Returns the value of the '<em><b>Optional</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optional</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optional</em>' attribute.
	 * @see #setOptional(boolean)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToOne_Optional()
	 * @model default="true"
	 * @generated
	 */
	boolean isOptional();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#isOptional <em>Optional</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optional</em>' attribute.
	 * @see #isOptional()
	 * @generated
	 */
	void setOptional(boolean value);

	/**
	 * Returns the value of the '<em><b>Target Entity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Entity</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Entity</em>' attribute.
	 * @see #setTargetEntity(String)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToOne_TargetEntity()
	 * @model
	 * @generated
	 */
	String getTargetEntity();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getTargetEntity <em>Target Entity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Entity</em>' attribute.
	 * @see #getTargetEntity()
	 * @generated
	 */
	void setTargetEntity(String value);

	/**
	 * Returns the value of the '<em><b>Cascade</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.CascadeType}.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.annotations.pannotation.CascadeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cascade</em>' attribute list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cascade</em>' attribute list.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.CascadeType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToOne_Cascade()
	 * @model
	 * @generated
	 */
	EList<CascadeType> getCascade();

} // ManyToOne
