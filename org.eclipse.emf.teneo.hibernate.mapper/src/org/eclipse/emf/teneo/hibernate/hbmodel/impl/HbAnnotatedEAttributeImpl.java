/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedEAttributeImpl.java,v 1.12 2008/07/12 13:10:33 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEAttributeImpl;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbannotation.Filter;
import org.eclipse.emf.teneo.hibernate.hbannotation.Formula;
import org.eclipse.emf.teneo.hibernate.hbannotation.Generated;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany;
import org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId;
import org.eclipse.emf.teneo.hibernate.hbannotation.Type;
import org.eclipse.emf.teneo.hibernate.hbannotation.Where;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEModelElement;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Hb Annotated EAttribute</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbWhere <em>Hb Where</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbCollectionOfElements <em>Hb Collection Of Elements</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbMapKey <em>Hb Map Key</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbColumns <em>Hb Columns</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbCascade <em>Hb Cascade</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbIdBag <em>Hb Id Bag</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbIndex <em>Hb Index</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getMapKeyManyToMany <em>Map Key Many To Many</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getFormula <em>Formula</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getHbType <em>Hb Type</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getGenerated <em>Generated</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEAttributeImpl#getNaturalId <em>Natural Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HbAnnotatedEAttributeImpl extends PAnnotatedEAttributeImpl implements HbAnnotatedEAttribute {
	/**
	 * The cached value of the '{@link #getHbWhere() <em>Hb Where</em>}' containment reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getHbWhere()
	 * @generated
	 * @ordered
	 */
	protected Where hbWhere;

	/**
	 * The cached value of the '{@link #getHbCollectionOfElements() <em>Hb Collection Of Elements</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHbCollectionOfElements()
	 * @generated
	 * @ordered
	 */
	protected CollectionOfElements hbCollectionOfElements;

	/**
	 * The cached value of the '{@link #getHbMapKey() <em>Hb Map Key</em>}' containment reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getHbMapKey()
	 * @generated
	 * @ordered
	 */
	protected HbMapKey hbMapKey;

	/**
	 * The cached value of the '{@link #getHbColumns() <em>Hb Columns</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHbColumns()
	 * @generated
	 * @ordered
	 */
	protected EList<Column> hbColumns;

	/**
	 * The cached value of the '{@link #getHbCascade() <em>Hb Cascade</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHbCascade()
	 * @generated
	 * @ordered
	 */
	protected Cascade hbCascade;

	/**
	 * The cached value of the '{@link #getHbIdBag() <em>Hb Id Bag</em>}' containment reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
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
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected EList<Filter> filter;

	/**
	 * The cached value of the '{@link #getMapKeyManyToMany() <em>Map Key Many To Many</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMapKeyManyToMany()
	 * @generated
	 * @ordered
	 */
	protected MapKeyManyToMany mapKeyManyToMany;

	/**
	 * The cached value of the '{@link #getFormula() <em>Formula</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected Formula formula;

	/**
	 * The cached value of the '{@link #getHbType() <em>Hb Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHbType()
	 * @generated
	 * @ordered
	 */
	protected Type hbType;

	/**
	 * The cached value of the '{@link #getGenerated() <em>Generated</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGenerated()
	 * @generated
	 * @ordered
	 */
	protected Generated generated;

	/**
	 * The cached value of the '{@link #getNaturalId() <em>Natural Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNaturalId()
	 * @generated
	 * @ordered
	 */
	protected NaturalId naturalId;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected HbAnnotatedEAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbmodelPackage.Literals.HB_ANNOTATED_EATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Where getHbWhere() {
		return hbWhere;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbWhere(Where newHbWhere, NotificationChain msgs) {
		Where oldHbWhere = hbWhere;
		hbWhere = newHbWhere;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE, oldHbWhere, newHbWhere);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbWhere(Where newHbWhere) {
		if (newHbWhere != hbWhere) {
			NotificationChain msgs = null;
			if (hbWhere != null)
				msgs = ((InternalEObject)hbWhere).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE, null, msgs);
			if (newHbWhere != null)
				msgs = ((InternalEObject)newHbWhere).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE, null, msgs);
			msgs = basicSetHbWhere(newHbWhere, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE, newHbWhere, newHbWhere));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionOfElements getHbCollectionOfElements() {
		return hbCollectionOfElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbCollectionOfElements(CollectionOfElements newHbCollectionOfElements, NotificationChain msgs) {
		CollectionOfElements oldHbCollectionOfElements = hbCollectionOfElements;
		hbCollectionOfElements = newHbCollectionOfElements;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS, oldHbCollectionOfElements, newHbCollectionOfElements);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbCollectionOfElements(CollectionOfElements newHbCollectionOfElements) {
		if (newHbCollectionOfElements != hbCollectionOfElements) {
			NotificationChain msgs = null;
			if (hbCollectionOfElements != null)
				msgs = ((InternalEObject)hbCollectionOfElements).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS, null, msgs);
			if (newHbCollectionOfElements != null)
				msgs = ((InternalEObject)newHbCollectionOfElements).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS, null, msgs);
			msgs = basicSetHbCollectionOfElements(newHbCollectionOfElements, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS, newHbCollectionOfElements, newHbCollectionOfElements));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public HbMapKey getHbMapKey() {
		return hbMapKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbMapKey(HbMapKey newHbMapKey, NotificationChain msgs) {
		HbMapKey oldHbMapKey = hbMapKey;
		hbMapKey = newHbMapKey;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY, oldHbMapKey, newHbMapKey);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbMapKey(HbMapKey newHbMapKey) {
		if (newHbMapKey != hbMapKey) {
			NotificationChain msgs = null;
			if (hbMapKey != null)
				msgs = ((InternalEObject)hbMapKey).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY, null, msgs);
			if (newHbMapKey != null)
				msgs = ((InternalEObject)newHbMapKey).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY, null, msgs);
			msgs = basicSetHbMapKey(newHbMapKey, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY, newHbMapKey, newHbMapKey));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Column> getHbColumns() {
		if (hbColumns == null) {
			hbColumns = new EObjectContainmentEList<Column>(Column.class, this, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS);
		}
		return hbColumns;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Cascade getHbCascade() {
		return hbCascade;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbCascade(Cascade newHbCascade, NotificationChain msgs) {
		Cascade oldHbCascade = hbCascade;
		hbCascade = newHbCascade;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE, oldHbCascade, newHbCascade);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbCascade(Cascade newHbCascade) {
		if (newHbCascade != hbCascade) {
			NotificationChain msgs = null;
			if (hbCascade != null)
				msgs = ((InternalEObject)hbCascade).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE, null, msgs);
			if (newHbCascade != null)
				msgs = ((InternalEObject)newHbCascade).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE, null, msgs);
			msgs = basicSetHbCascade(newHbCascade, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE, newHbCascade, newHbCascade));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IdBag getHbIdBag() {
		return hbIdBag;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbIdBag(IdBag newHbIdBag, NotificationChain msgs) {
		IdBag oldHbIdBag = hbIdBag;
		hbIdBag = newHbIdBag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG, oldHbIdBag, newHbIdBag);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbIdBag(IdBag newHbIdBag) {
		if (newHbIdBag != hbIdBag) {
			NotificationChain msgs = null;
			if (hbIdBag != null)
				msgs = ((InternalEObject)hbIdBag).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG, null, msgs);
			if (newHbIdBag != null)
				msgs = ((InternalEObject)newHbIdBag).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG, null, msgs);
			msgs = basicSetHbIdBag(newHbIdBag, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG, newHbIdBag, newHbIdBag));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX, oldHbIndex, hbIndex));
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
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX, oldHbIndex, hbIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Filter> getFilter() {
		if (filter == null) {
			filter = new EObjectResolvingEList<Filter>(Filter.class, this, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER);
		}
		return filter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapKeyManyToMany getMapKeyManyToMany() {
		return mapKeyManyToMany;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapKeyManyToMany(MapKeyManyToMany newMapKeyManyToMany, NotificationChain msgs) {
		MapKeyManyToMany oldMapKeyManyToMany = mapKeyManyToMany;
		mapKeyManyToMany = newMapKeyManyToMany;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY, oldMapKeyManyToMany, newMapKeyManyToMany);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapKeyManyToMany(MapKeyManyToMany newMapKeyManyToMany) {
		if (newMapKeyManyToMany != mapKeyManyToMany) {
			NotificationChain msgs = null;
			if (mapKeyManyToMany != null)
				msgs = ((InternalEObject)mapKeyManyToMany).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY, null, msgs);
			if (newMapKeyManyToMany != null)
				msgs = ((InternalEObject)newMapKeyManyToMany).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY, null, msgs);
			msgs = basicSetMapKeyManyToMany(newMapKeyManyToMany, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY, newMapKeyManyToMany, newMapKeyManyToMany));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Formula getFormula() {
		if (formula != null && formula.eIsProxy()) {
			InternalEObject oldFormula = (InternalEObject)formula;
			formula = (Formula)eResolveProxy(oldFormula);
			if (formula != oldFormula) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA, oldFormula, formula));
			}
		}
		return formula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Formula basicGetFormula() {
		return formula;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormula(Formula newFormula) {
		Formula oldFormula = formula;
		formula = newFormula;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA, oldFormula, formula));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getHbType() {
		return hbType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHbType(Type newHbType, NotificationChain msgs) {
		Type oldHbType = hbType;
		hbType = newHbType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE, oldHbType, newHbType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setHbType(Type newHbType) {
		if (newHbType != hbType) {
			NotificationChain msgs = null;
			if (hbType != null)
				msgs = ((InternalEObject)hbType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE, null, msgs);
			if (newHbType != null)
				msgs = ((InternalEObject)newHbType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE, null, msgs);
			msgs = basicSetHbType(newHbType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE, newHbType, newHbType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Generated getGenerated() {
		return generated;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGenerated(Generated newGenerated, NotificationChain msgs) {
		Generated oldGenerated = generated;
		generated = newGenerated;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED, oldGenerated, newGenerated);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGenerated(Generated newGenerated) {
		if (newGenerated != generated) {
			NotificationChain msgs = null;
			if (generated != null)
				msgs = ((InternalEObject)generated).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED, null, msgs);
			if (newGenerated != null)
				msgs = ((InternalEObject)newGenerated).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED, null, msgs);
			msgs = basicSetGenerated(newGenerated, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED, newGenerated, newGenerated));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NaturalId getNaturalId() {
		return naturalId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNaturalId(NaturalId newNaturalId, NotificationChain msgs) {
		NaturalId oldNaturalId = naturalId;
		naturalId = newNaturalId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID, oldNaturalId, newNaturalId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNaturalId(NaturalId newNaturalId) {
		if (newNaturalId != naturalId) {
			NotificationChain msgs = null;
			if (naturalId != null)
				msgs = ((InternalEObject)naturalId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID, null, msgs);
			if (newNaturalId != null)
				msgs = ((InternalEObject)newNaturalId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID, null, msgs);
			msgs = basicSetNaturalId(newNaturalId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID, newNaturalId, newNaturalId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE:
				return basicSetHbWhere(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS:
				return basicSetHbCollectionOfElements(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY:
				return basicSetHbMapKey(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS:
				return ((InternalEList<?>)getHbColumns()).basicRemove(otherEnd, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE:
				return basicSetHbCascade(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG:
				return basicSetHbIdBag(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY:
				return basicSetMapKeyManyToMany(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE:
				return basicSetHbType(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED:
				return basicSetGenerated(null, msgs);
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID:
				return basicSetNaturalId(null, msgs);
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
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE:
				return getHbWhere();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS:
				return getHbCollectionOfElements();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY:
				return getHbMapKey();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS:
				return getHbColumns();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE:
				return getHbCascade();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG:
				return getHbIdBag();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX:
				if (resolve) return getHbIndex();
				return basicGetHbIndex();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER:
				return getFilter();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY:
				return getMapKeyManyToMany();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA:
				if (resolve) return getFormula();
				return basicGetFormula();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE:
				return getHbType();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED:
				return getGenerated();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID:
				return getNaturalId();
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
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE:
				setHbWhere((Where)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS:
				setHbCollectionOfElements((CollectionOfElements)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY:
				setHbMapKey((HbMapKey)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS:
				getHbColumns().clear();
				getHbColumns().addAll((Collection<? extends Column>)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE:
				setHbCascade((Cascade)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG:
				setHbIdBag((IdBag)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX:
				setHbIndex((Index)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER:
				getFilter().clear();
				getFilter().addAll((Collection<? extends Filter>)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY:
				setMapKeyManyToMany((MapKeyManyToMany)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA:
				setFormula((Formula)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE:
				setHbType((Type)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED:
				setGenerated((Generated)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID:
				setNaturalId((NaturalId)newValue);
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
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE:
				setHbWhere((Where)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS:
				setHbCollectionOfElements((CollectionOfElements)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY:
				setHbMapKey((HbMapKey)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS:
				getHbColumns().clear();
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE:
				setHbCascade((Cascade)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG:
				setHbIdBag((IdBag)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX:
				setHbIndex((Index)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER:
				getFilter().clear();
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY:
				setMapKeyManyToMany((MapKeyManyToMany)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA:
				setFormula((Formula)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE:
				setHbType((Type)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED:
				setGenerated((Generated)null);
				return;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID:
				setNaturalId((NaturalId)null);
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
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE:
				return hbWhere != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS:
				return hbCollectionOfElements != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY:
				return hbMapKey != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS:
				return hbColumns != null && !hbColumns.isEmpty();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE:
				return hbCascade != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG:
				return hbIdBag != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX:
				return hbIndex != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER:
				return filter != null && !filter.isEmpty();
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY:
				return mapKeyManyToMany != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA:
				return formula != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_TYPE:
				return hbType != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__GENERATED:
				return generated != null;
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__NATURAL_ID:
				return naturalId != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == HbAnnotatedEModelElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == HbAnnotatedETypeElement.class) {
			switch (derivedFeatureID) {
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_WHERE;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_COLLECTION_OF_ELEMENTS;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_MAP_KEY;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_COLUMNS;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_CASCADE;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_ID_BAG;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_INDEX;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__FILTER;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__MAP_KEY_MANY_TO_MANY;
				case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA: return HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__FORMULA;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == HbAnnotatedEModelElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == HbAnnotatedETypeElement.class) {
			switch (baseFeatureID) {
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_WHERE: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_WHERE;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_COLLECTION_OF_ELEMENTS: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLLECTION_OF_ELEMENTS;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_MAP_KEY: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_MAP_KEY;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_COLUMNS: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_COLUMNS;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_CASCADE: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_CASCADE;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_ID_BAG: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_ID_BAG;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__HB_INDEX: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__HB_INDEX;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__FILTER: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FILTER;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__MAP_KEY_MANY_TO_MANY: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__MAP_KEY_MANY_TO_MANY;
				case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT__FORMULA: return HbmodelPackage.HB_ANNOTATED_EATTRIBUTE__FORMULA;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} // HbAnnotatedEAttributeImpl
