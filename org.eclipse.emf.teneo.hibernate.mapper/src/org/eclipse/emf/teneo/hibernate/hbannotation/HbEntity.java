/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbEntity.java,v 1.1 2008/08/26 21:19:08 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Hb Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicInsert <em>Dynamic Insert</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicUpdate <em>Dynamic Update</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isSelectBeforeUpdate <em>Select Before Update</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isMutable <em>Mutable</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPersister <em>Persister</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getOptimisticLock <em>Optimistic Lock</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPolymorphism <em>Polymorphism</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EStructuralFeature'"
 * @generated
 */
public interface HbEntity extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Dynamic Insert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Insert</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Insert</em>' attribute.
	 * @see #setDynamicInsert(boolean)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_DynamicInsert()
	 * @model
	 * @generated
	 */
	boolean isDynamicInsert();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicInsert <em>Dynamic Insert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Insert</em>' attribute.
	 * @see #isDynamicInsert()
	 * @generated
	 */
	void setDynamicInsert(boolean value);

	/**
	 * Returns the value of the '<em><b>Dynamic Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dynamic Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic Update</em>' attribute.
	 * @see #setDynamicUpdate(boolean)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_DynamicUpdate()
	 * @model
	 * @generated
	 */
	boolean isDynamicUpdate();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicUpdate <em>Dynamic Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic Update</em>' attribute.
	 * @see #isDynamicUpdate()
	 * @generated
	 */
	void setDynamicUpdate(boolean value);

	/**
	 * Returns the value of the '<em><b>Select Before Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Select Before Update</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Select Before Update</em>' attribute.
	 * @see #setSelectBeforeUpdate(boolean)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_SelectBeforeUpdate()
	 * @model
	 * @generated
	 */
	boolean isSelectBeforeUpdate();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isSelectBeforeUpdate <em>Select Before Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Select Before Update</em>' attribute.
	 * @see #isSelectBeforeUpdate()
	 * @generated
	 */
	void setSelectBeforeUpdate(boolean value);

	/**
	 * Returns the value of the '<em><b>Mutable</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutable</em>' attribute.
	 * @see #setMutable(boolean)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_Mutable()
	 * @model default="true"
	 * @generated
	 */
	boolean isMutable();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isMutable <em>Mutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mutable</em>' attribute.
	 * @see #isMutable()
	 * @generated
	 */
	void setMutable(boolean value);

	/**
	 * Returns the value of the '<em><b>Persister</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persister</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persister</em>' attribute.
	 * @see #setPersister(String)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_Persister()
	 * @model
	 * @generated
	 */
	String getPersister();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPersister <em>Persister</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persister</em>' attribute.
	 * @see #getPersister()
	 * @generated
	 */
	void setPersister(String value);

	/**
	 * Returns the value of the '<em><b>Optimistic Lock</b></em>' attribute.
	 * The default value is <code>"VERSION"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optimistic Lock</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optimistic Lock</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType
	 * @see #setOptimisticLock(OptimisticLockType)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_OptimisticLock()
	 * @model default="VERSION"
	 * @generated
	 */
	OptimisticLockType getOptimisticLock();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getOptimisticLock <em>Optimistic Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optimistic Lock</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType
	 * @see #getOptimisticLock()
	 * @generated
	 */
	void setOptimisticLock(OptimisticLockType value);

	/**
	 * Returns the value of the '<em><b>Polymorphism</b></em>' attribute.
	 * The default value is <code>"IMPLICIT"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Polymorphism</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Polymorphism</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType
	 * @see #setPolymorphism(PolymorphismType)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getHbEntity_Polymorphism()
	 * @model default="IMPLICIT"
	 * @generated
	 */
	PolymorphismType getPolymorphism();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPolymorphism <em>Polymorphism</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Polymorphism</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType
	 * @see #getPolymorphism()
	 * @generated
	 */
	void setPolymorphism(PolymorphismType value);

} // HbEntity
