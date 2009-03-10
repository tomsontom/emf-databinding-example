/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedEAttribute.java,v 1.7 2008/06/29 14:23:05 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel;

import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbannotation.Generated;
import org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId;
import org.eclipse.emf.teneo.hibernate.hbannotation.Type;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Hb Annotated EAttribute</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute#getHbType <em>Hb Type</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute#getGenerated <em>Generated</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute#getNaturalId <em>Natural Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEAttribute()
 * @model
 * @generated
 */
public interface HbAnnotatedEAttribute extends PAnnotatedEAttribute, HbAnnotatedETypeElement {
	/**
	 * Returns the value of the '<em><b>Hb Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Type</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Type</em>' containment reference.
	 * @see #setHbType(Type)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEAttribute_HbType()
	 * @model containment="true"
	 * @generated
	 */
	Type getHbType();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute#getHbType <em>Hb Type</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Type</em>' containment reference.
	 * @see #getHbType()
	 * @generated
	 */
	void setHbType(Type value);

	/**
	 * Returns the value of the '<em><b>Generated</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generated</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generated</em>' containment reference.
	 * @see #setGenerated(Generated)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEAttribute_Generated()
	 * @model containment="true"
	 * @generated
	 */
	Generated getGenerated();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute#getGenerated <em>Generated</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Generated</em>' containment reference.
	 * @see #getGenerated()
	 * @generated
	 */
	void setGenerated(Generated value);

	/**
	 * Returns the value of the '<em><b>Natural Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Natural Id</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Natural Id</em>' containment reference.
	 * @see #setNaturalId(NaturalId)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEAttribute_NaturalId()
	 * @model containment="true"
	 * @generated
	 */
	NaturalId getNaturalId();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute#getNaturalId <em>Natural Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Natural Id</em>' containment reference.
	 * @see #getNaturalId()
	 * @generated
	 */
	void setNaturalId(NaturalId value);

} // HbAnnotatedEAttribute
