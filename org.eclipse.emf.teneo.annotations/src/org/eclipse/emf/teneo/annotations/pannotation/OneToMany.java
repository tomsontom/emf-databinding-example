/**
 * <copyright>
 * </copyright>
 *
 * $Id: OneToMany.java,v 1.10 2008/08/11 20:42:23 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>One To Many</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getTargetEntity <em>Target Entity</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getCascade <em>Cascade</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getFetch <em>Fetch</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getMappedBy <em>Mapped By</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EStructuralFeature'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='IsManyValued'"
 * @generated
 */
public interface OneToMany extends PAnnotation {
	/**
	 * Returns the value of the '<em><b>Fetch</b></em>' attribute. The default value is <code>"LAZY"</code>. The
	 * literals are from the enumeration {@link org.eclipse.emf.teneo.annotations.pannotation.FetchType}. <!--
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany_Fetch()
	 * @model default="LAZY"
	 * @generated
	 */
	FetchType getFetch();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getFetch <em>Fetch</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fetch</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.FetchType
	 * @see #getFetch()
	 * @generated
	 */
	void setFetch(FetchType value);

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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany_TargetEntity()
	 * @model
	 * @generated
	 */
	String getTargetEntity();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getTargetEntity <em>Target Entity</em>}' attribute.
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany_Cascade()
	 * @model
	 * @generated
	 */
	EList<CascadeType> getCascade();

	/**
	 * Returns the value of the '<em><b>Mapped By</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped By</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped By</em>' attribute.
	 * @see #setMappedBy(String)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany_MappedBy()
	 * @model
	 * @generated
	 */
	String getMappedBy();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getMappedBy <em>Mapped By</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped By</em>' attribute.
	 * @see #getMappedBy()
	 * @generated
	 */
	void setMappedBy(String value);

	/**
	 * Returns the value of the '<em><b>Indexed</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed</em>' attribute.
	 * @see #setIndexed(boolean)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany_Indexed()
	 * @model default="true"
	 * @generated
	 */
	boolean isIndexed();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#isIndexed <em>Indexed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indexed</em>' attribute.
	 * @see #isIndexed()
	 * @generated
	 */
	void setIndexed(boolean value);

	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getOneToMany_Unique()
	 * @model default="true"
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);

	/** Returns true if this one to many should be modeled as a list */
	boolean isList();
} // OneToMany
