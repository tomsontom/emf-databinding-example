/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbEntityImpl.java,v 1.1 2008/08/26 21:19:07 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType;
import org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hb Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#isDynamicInsert <em>Dynamic Insert</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#isDynamicUpdate <em>Dynamic Update</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#isSelectBeforeUpdate <em>Select Before Update</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#isMutable <em>Mutable</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#getPersister <em>Persister</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#getOptimisticLock <em>Optimistic Lock</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl#getPolymorphism <em>Polymorphism</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HbEntityImpl extends HbAnnotationImpl implements HbEntity {
	/**
	 * The default value of the '{@link #isDynamicInsert() <em>Dynamic Insert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDynamicInsert()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DYNAMIC_INSERT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDynamicInsert() <em>Dynamic Insert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDynamicInsert()
	 * @generated
	 * @ordered
	 */
	protected boolean dynamicInsert = DYNAMIC_INSERT_EDEFAULT;

	/**
	 * The default value of the '{@link #isDynamicUpdate() <em>Dynamic Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDynamicUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DYNAMIC_UPDATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDynamicUpdate() <em>Dynamic Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDynamicUpdate()
	 * @generated
	 * @ordered
	 */
	protected boolean dynamicUpdate = DYNAMIC_UPDATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSelectBeforeUpdate() <em>Select Before Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSelectBeforeUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SELECT_BEFORE_UPDATE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSelectBeforeUpdate() <em>Select Before Update</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSelectBeforeUpdate()
	 * @generated
	 * @ordered
	 */
	protected boolean selectBeforeUpdate = SELECT_BEFORE_UPDATE_EDEFAULT;

	/**
	 * The default value of the '{@link #isMutable() <em>Mutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MUTABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMutable() <em>Mutable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected boolean mutable = MUTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPersister() <em>Persister</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersister()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSISTER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersister() <em>Persister</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersister()
	 * @generated
	 * @ordered
	 */
	protected String persister = PERSISTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getOptimisticLock() <em>Optimistic Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptimisticLock()
	 * @generated
	 * @ordered
	 */
	protected static final OptimisticLockType OPTIMISTIC_LOCK_EDEFAULT = OptimisticLockType.VERSION;

	/**
	 * The cached value of the '{@link #getOptimisticLock() <em>Optimistic Lock</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptimisticLock()
	 * @generated
	 * @ordered
	 */
	protected OptimisticLockType optimisticLock = OPTIMISTIC_LOCK_EDEFAULT;

	/**
	 * The default value of the '{@link #getPolymorphism() <em>Polymorphism</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolymorphism()
	 * @generated
	 * @ordered
	 */
	protected static final PolymorphismType POLYMORPHISM_EDEFAULT = PolymorphismType.IMPLICIT;

	/**
	 * The cached value of the '{@link #getPolymorphism() <em>Polymorphism</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPolymorphism()
	 * @generated
	 * @ordered
	 */
	protected PolymorphismType polymorphism = POLYMORPHISM_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HbEntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbannotationPackage.Literals.HB_ENTITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDynamicInsert() {
		return dynamicInsert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicInsert(boolean newDynamicInsert) {
		boolean oldDynamicInsert = dynamicInsert;
		dynamicInsert = newDynamicInsert;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__DYNAMIC_INSERT, oldDynamicInsert, dynamicInsert));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDynamicUpdate() {
		return dynamicUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDynamicUpdate(boolean newDynamicUpdate) {
		boolean oldDynamicUpdate = dynamicUpdate;
		dynamicUpdate = newDynamicUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__DYNAMIC_UPDATE, oldDynamicUpdate, dynamicUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSelectBeforeUpdate() {
		return selectBeforeUpdate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectBeforeUpdate(boolean newSelectBeforeUpdate) {
		boolean oldSelectBeforeUpdate = selectBeforeUpdate;
		selectBeforeUpdate = newSelectBeforeUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__SELECT_BEFORE_UPDATE, oldSelectBeforeUpdate, selectBeforeUpdate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMutable() {
		return mutable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMutable(boolean newMutable) {
		boolean oldMutable = mutable;
		mutable = newMutable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__MUTABLE, oldMutable, mutable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPersister() {
		return persister;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersister(String newPersister) {
		String oldPersister = persister;
		persister = newPersister;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__PERSISTER, oldPersister, persister));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptimisticLockType getOptimisticLock() {
		return optimisticLock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptimisticLock(OptimisticLockType newOptimisticLock) {
		OptimisticLockType oldOptimisticLock = optimisticLock;
		optimisticLock = newOptimisticLock == null ? OPTIMISTIC_LOCK_EDEFAULT : newOptimisticLock;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__OPTIMISTIC_LOCK, oldOptimisticLock, optimisticLock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolymorphismType getPolymorphism() {
		return polymorphism;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPolymorphism(PolymorphismType newPolymorphism) {
		PolymorphismType oldPolymorphism = polymorphism;
		polymorphism = newPolymorphism == null ? POLYMORPHISM_EDEFAULT : newPolymorphism;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.HB_ENTITY__POLYMORPHISM, oldPolymorphism, polymorphism));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HbannotationPackage.HB_ENTITY__DYNAMIC_INSERT:
				return isDynamicInsert() ? Boolean.TRUE : Boolean.FALSE;
			case HbannotationPackage.HB_ENTITY__DYNAMIC_UPDATE:
				return isDynamicUpdate() ? Boolean.TRUE : Boolean.FALSE;
			case HbannotationPackage.HB_ENTITY__SELECT_BEFORE_UPDATE:
				return isSelectBeforeUpdate() ? Boolean.TRUE : Boolean.FALSE;
			case HbannotationPackage.HB_ENTITY__MUTABLE:
				return isMutable() ? Boolean.TRUE : Boolean.FALSE;
			case HbannotationPackage.HB_ENTITY__PERSISTER:
				return getPersister();
			case HbannotationPackage.HB_ENTITY__OPTIMISTIC_LOCK:
				return getOptimisticLock();
			case HbannotationPackage.HB_ENTITY__POLYMORPHISM:
				return getPolymorphism();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HbannotationPackage.HB_ENTITY__DYNAMIC_INSERT:
				setDynamicInsert(((Boolean)newValue).booleanValue());
				return;
			case HbannotationPackage.HB_ENTITY__DYNAMIC_UPDATE:
				setDynamicUpdate(((Boolean)newValue).booleanValue());
				return;
			case HbannotationPackage.HB_ENTITY__SELECT_BEFORE_UPDATE:
				setSelectBeforeUpdate(((Boolean)newValue).booleanValue());
				return;
			case HbannotationPackage.HB_ENTITY__MUTABLE:
				setMutable(((Boolean)newValue).booleanValue());
				return;
			case HbannotationPackage.HB_ENTITY__PERSISTER:
				setPersister((String)newValue);
				return;
			case HbannotationPackage.HB_ENTITY__OPTIMISTIC_LOCK:
				setOptimisticLock((OptimisticLockType)newValue);
				return;
			case HbannotationPackage.HB_ENTITY__POLYMORPHISM:
				setPolymorphism((PolymorphismType)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HbannotationPackage.HB_ENTITY__DYNAMIC_INSERT:
				setDynamicInsert(DYNAMIC_INSERT_EDEFAULT);
				return;
			case HbannotationPackage.HB_ENTITY__DYNAMIC_UPDATE:
				setDynamicUpdate(DYNAMIC_UPDATE_EDEFAULT);
				return;
			case HbannotationPackage.HB_ENTITY__SELECT_BEFORE_UPDATE:
				setSelectBeforeUpdate(SELECT_BEFORE_UPDATE_EDEFAULT);
				return;
			case HbannotationPackage.HB_ENTITY__MUTABLE:
				setMutable(MUTABLE_EDEFAULT);
				return;
			case HbannotationPackage.HB_ENTITY__PERSISTER:
				setPersister(PERSISTER_EDEFAULT);
				return;
			case HbannotationPackage.HB_ENTITY__OPTIMISTIC_LOCK:
				setOptimisticLock(OPTIMISTIC_LOCK_EDEFAULT);
				return;
			case HbannotationPackage.HB_ENTITY__POLYMORPHISM:
				setPolymorphism(POLYMORPHISM_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HbannotationPackage.HB_ENTITY__DYNAMIC_INSERT:
				return dynamicInsert != DYNAMIC_INSERT_EDEFAULT;
			case HbannotationPackage.HB_ENTITY__DYNAMIC_UPDATE:
				return dynamicUpdate != DYNAMIC_UPDATE_EDEFAULT;
			case HbannotationPackage.HB_ENTITY__SELECT_BEFORE_UPDATE:
				return selectBeforeUpdate != SELECT_BEFORE_UPDATE_EDEFAULT;
			case HbannotationPackage.HB_ENTITY__MUTABLE:
				return mutable != MUTABLE_EDEFAULT;
			case HbannotationPackage.HB_ENTITY__PERSISTER:
				return PERSISTER_EDEFAULT == null ? persister != null : !PERSISTER_EDEFAULT.equals(persister);
			case HbannotationPackage.HB_ENTITY__OPTIMISTIC_LOCK:
				return optimisticLock != OPTIMISTIC_LOCK_EDEFAULT;
			case HbannotationPackage.HB_ENTITY__POLYMORPHISM:
				return polymorphism != POLYMORPHISM_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dynamicInsert: ");
		result.append(dynamicInsert);
		result.append(", dynamicUpdate: ");
		result.append(dynamicUpdate);
		result.append(", selectBeforeUpdate: ");
		result.append(selectBeforeUpdate);
		result.append(", mutable: ");
		result.append(mutable);
		result.append(", persister: ");
		result.append(persister);
		result.append(", optimisticLock: ");
		result.append(optimisticLock);
		result.append(", polymorphism: ");
		result.append(polymorphism);
		result.append(')');
		return result.toString();
	}

} //HbEntityImpl
