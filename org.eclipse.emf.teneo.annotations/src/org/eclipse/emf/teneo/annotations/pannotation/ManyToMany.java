/**
 * <copyright>
 * </copyright>
 *
 * $Id: ManyToMany.java,v 1.12 2008/08/11 20:42:23 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Many To Many</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getTargetEntity <em>Target Entity</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getCascade <em>Cascade</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getFetch <em>Fetch</em>}</li>
 * <li>{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getMappedBy <em>Mapped By</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToMany()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EReference'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='IsManyValued'"
 * @generated
 */
public interface ManyToMany extends PAnnotation {
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToMany_Fetch()
	 * @model default="LAZY"
	 * @generated
	 */
	FetchType getFetch();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getFetch <em>Fetch</em>}' attribute.
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToMany_TargetEntity()
	 * @model
	 * @generated
	 */
	String getTargetEntity();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getTargetEntity <em>Target Entity</em>}' attribute.
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToMany_Cascade()
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToMany_MappedBy()
	 * @model
	 * @generated
	 */
	String getMappedBy();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getMappedBy <em>Mapped By</em>}' attribute.
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
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getManyToMany_Indexed()
	 * @model default="true"
	 * @generated
	 */
	boolean isIndexed();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#isIndexed <em>Indexed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indexed</em>' attribute.
	 * @see #isIndexed()
	 * @generated
	 */
	void setIndexed(boolean value);

	/** Returns true if this one to many should be modeled as a list */
	boolean isList();

} // ManyToMany
