/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedEReference.java,v 1.13 2009/03/15 15:08:01 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel;

import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cache;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.Fetch;
import org.eclipse.emf.teneo.hibernate.hbannotation.Immutable;
import org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId;
import org.eclipse.emf.teneo.hibernate.hbannotation.NotFound;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete;
import org.eclipse.emf.teneo.hibernate.hbannotation.Type;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Hb Annotated EReference</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbCache <em>Hb Cache</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbFetch <em>Hb Fetch</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbOnDelete <em>Hb On Delete</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getNaturalId <em>Natural Id</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getImmutable <em>Immutable</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getNotFound <em>Not Found</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbType <em>Hb Type</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getBatchSize <em>Batch Size</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference()
 * @model
 * @generated
 */
public interface HbAnnotatedEReference extends PAnnotatedEReference, HbAnnotatedETypeElement {
	/**
	 * Returns the value of the '<em><b>Hb Cache</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Cache</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Cache</em>' containment reference.
	 * @see #setHbCache(Cache)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_HbCache()
	 * @model containment="true"
	 * @generated
	 */
	Cache getHbCache();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbCache <em>Hb Cache</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Cache</em>' containment reference.
	 * @see #getHbCache()
	 * @generated
	 */
	void setHbCache(Cache value);

	/**
	 * Returns the value of the '<em><b>Hb Fetch</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Fetch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Fetch</em>' reference.
	 * @see #setHbFetch(Fetch)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_HbFetch()
	 * @model
	 * @generated
	 */
	Fetch getHbFetch();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbFetch <em>Hb Fetch</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Fetch</em>' reference.
	 * @see #getHbFetch()
	 * @generated
	 */
	void setHbFetch(Fetch value);

	/**
	 * Returns the value of the '<em><b>Hb On Delete</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb On Delete</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb On Delete</em>' reference.
	 * @see #setHbOnDelete(OnDelete)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_HbOnDelete()
	 * @model
	 * @generated
	 */
	OnDelete getHbOnDelete();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbOnDelete <em>Hb On Delete</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb On Delete</em>' reference.
	 * @see #getHbOnDelete()
	 * @generated
	 */
	void setHbOnDelete(OnDelete value);

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
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_NaturalId()
	 * @model containment="true"
	 * @generated
	 */
	NaturalId getNaturalId();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getNaturalId <em>Natural Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Natural Id</em>' containment reference.
	 * @see #getNaturalId()
	 * @generated
	 */
	void setNaturalId(NaturalId value);

	/**
	 * Returns the value of the '<em><b>Immutable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Immutable</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Immutable</em>' containment reference.
	 * @see #setImmutable(Immutable)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_Immutable()
	 * @model containment="true"
	 * @generated
	 */
	Immutable getImmutable();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getImmutable <em>Immutable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Immutable</em>' containment reference.
	 * @see #getImmutable()
	 * @generated
	 */
	void setImmutable(Immutable value);

	/**
	 * Returns the value of the '<em><b>Not Found</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Not Found</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Not Found</em>' containment reference.
	 * @see #setNotFound(NotFound)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_NotFound()
	 * @model containment="true"
	 * @generated
	 */
	NotFound getNotFound();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getNotFound <em>Not Found</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Not Found</em>' containment reference.
	 * @see #getNotFound()
	 * @generated
	 */
	void setNotFound(NotFound value);

	/**
	 * Returns the value of the '<em><b>Hb Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Type</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Type</em>' containment reference.
	 * @see #setHbType(Type)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_HbType()
	 * @model containment="true"
	 * @generated
	 */
	Type getHbType();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getHbType <em>Hb Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Type</em>' containment reference.
	 * @see #getHbType()
	 * @generated
	 */
	void setHbType(Type value);

	/**
	 * Returns the value of the '<em><b>Batch Size</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Batch Size</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Batch Size</em>' reference.
	 * @see #setBatchSize(BatchSize)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEReference_BatchSize()
	 * @model
	 * @generated
	 */
	BatchSize getBatchSize();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference#getBatchSize <em>Batch Size</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Batch Size</em>' reference.
	 * @see #getBatchSize()
	 * @generated
	 */
	void setBatchSize(BatchSize value);

} // HbAnnotatedEReference
