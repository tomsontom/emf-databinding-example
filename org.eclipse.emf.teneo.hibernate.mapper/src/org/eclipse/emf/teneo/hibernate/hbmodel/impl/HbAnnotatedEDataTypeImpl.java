/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedEDataTypeImpl.java,v 1.11 2008/01/18 06:21:37 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEDataTypeImpl;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.ForeignKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.Where;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Hb Annotated EData Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEDataTypeImpl#getHbTypeDef <em>Hb Type Def</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEDataTypeImpl#getHbWhere <em>Hb Where</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEDataTypeImpl#getHbColumns <em>Hb Columns</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEDataTypeImpl#getHbIdBag <em>Hb Id Bag</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEDataTypeImpl#getHbIndex <em>Hb Index</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HbAnnotatedEDataTypeImpl extends PAnnotatedEDataTypeImpl implements HbAnnotatedEDataType {

	private ForeignKey foreignKey = null;

	/**
	 * The cached value of the '{@link #getHbTypeDef() <em>Hb Type Def</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHbTypeDef()
	 * @generated
	 * @ordered
	 */
	protected TypeDef hbTypeDef;

	/**
	 * The cached value of the '{@link #getHbWhere() <em>Hb Where</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHbWhere()
	 * @generated
	 * @ordered
	 */
	protected Where hbWhere;

	/**
	 * The cached value of the '{@link #getHbColumns() <em>Hb Columns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHbColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> hbColumns;

	/**
	 * The cached value of the '{@link #getHbIdBag() <em>Hb Id Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHbIdBag()
	 * @generated
	 * @ordered
	 */
	protected IdBag hbIdBag;

	/**
	 * The cached value of the '{@link #getHbIndex() <em>Hb Index</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHbIndex()
	 * @generated
	 * @ordered
	 */
	protected Index hbIndex;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected HbAnnotatedEDataTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbmodelPackage.Literals.HB_ANNOTATED_EDATA_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EStructuralFeature getAnnotatedEStructuralFeature() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EStructuralFeature basicGetAnnotatedEStructuralFeature() {
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setAnnotatedEStructuralFeature(EStructuralFeature newAnnotatedEStructuralFeature) {
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDef getHbTypeDef() {
		return hbTypeDef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbTypeDef(TypeDef newHbTypeDef, NotificationChain msgs) {
		TypeDef oldHbTypeDef = hbTypeDef;
		hbTypeDef = newHbTypeDef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF, oldHbTypeDef, newHbTypeDef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbTypeDef(TypeDef newHbTypeDef) {
		if (newHbTypeDef != hbTypeDef) {
			NotificationChain msgs = null;
			if (hbTypeDef != null)
				msgs = ((InternalEObject)hbTypeDef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF, null, msgs);
			if (newHbTypeDef != null)
				msgs = ((InternalEObject)newHbTypeDef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF, null, msgs);
			msgs = basicSetHbTypeDef(newHbTypeDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF, newHbTypeDef, newHbTypeDef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Where getHbWhere() {
		return hbWhere;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbWhere(Where newHbWhere, NotificationChain msgs) {
		Where oldHbWhere = hbWhere;
		hbWhere = newHbWhere;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE, oldHbWhere, newHbWhere);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbWhere(Where newHbWhere) {
		if (newHbWhere != hbWhere) {
			NotificationChain msgs = null;
			if (hbWhere != null)
				msgs = ((InternalEObject)hbWhere).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE, null, msgs);
			if (newHbWhere != null)
				msgs = ((InternalEObject)newHbWhere).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE, null, msgs);
			msgs = basicSetHbWhere(newHbWhere, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE, newHbWhere, newHbWhere));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getHbColumns() {
		if (hbColumns == null) {
			hbColumns = new EObjectContainmentEList<Column>(Column.class, this, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS);
		}
		return hbColumns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdBag getHbIdBag() {
		return hbIdBag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbIdBag(IdBag newHbIdBag, NotificationChain msgs) {
		IdBag oldHbIdBag = hbIdBag;
		hbIdBag = newHbIdBag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG, oldHbIdBag, newHbIdBag);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbIdBag(IdBag newHbIdBag) {
		if (newHbIdBag != hbIdBag) {
			NotificationChain msgs = null;
			if (hbIdBag != null)
				msgs = ((InternalEObject)hbIdBag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG, null, msgs);
			if (newHbIdBag != null)
				msgs = ((InternalEObject)newHbIdBag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG, null, msgs);
			msgs = basicSetHbIdBag(newHbIdBag, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG, newHbIdBag, newHbIdBag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Index getHbIndex() {
		if (hbIndex != null && hbIndex.eIsProxy()) {
			InternalEObject oldHbIndex = (InternalEObject)hbIndex;
			hbIndex = (Index)eResolveProxy(oldHbIndex);
			if (hbIndex != oldHbIndex) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_INDEX, oldHbIndex, hbIndex));
			}
		}
		return hbIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Index basicGetHbIndex() {
		return hbIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbIndex(Index newHbIndex) {
		Index oldHbIndex = hbIndex;
		hbIndex = newHbIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_INDEX, oldHbIndex, hbIndex));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF:
				return basicSetHbTypeDef(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE:
				return basicSetHbWhere(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS:
				return ((InternalEList<?>)getHbColumns()).basicRemove(otherEnd, msgs);
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG:
				return basicSetHbIdBag(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF:
				return getHbTypeDef();
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE:
				return getHbWhere();
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS:
				return getHbColumns();
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG:
				return getHbIdBag();
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_INDEX:
				if (resolve) return getHbIndex();
				return basicGetHbIndex();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF:
				setHbTypeDef((TypeDef)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE:
				setHbWhere((Where)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS:
				getHbColumns().clear();
				getHbColumns().addAll((Collection<? extends Column>)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG:
				setHbIdBag((IdBag)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_INDEX:
				setHbIndex((Index)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF:
				setHbTypeDef((TypeDef)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE:
				setHbWhere((Where)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS:
				getHbColumns().clear();
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG:
				setHbIdBag((IdBag)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_INDEX:
				setHbIndex((Index)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF:
				return hbTypeDef != null;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_WHERE:
				return hbWhere != null;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS:
				return hbColumns != null && !hbColumns.isEmpty();
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG:
				return hbIdBag != null;
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE__HB_INDEX:
				return hbIndex != null;
		}
		return super.eIsSet(featureID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getForeignKey()
	 */
	public ForeignKey getForeignKey() {
		return foreignKey;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#setForeignKey(org.eclipse.emf.teneo.annotations.pannotation.ForeignKey)
	 */
	public void setForeignKey(ForeignKey value) {
		foreignKey = value;
	}

} // HbAnnotatedEDataTypeImpl
