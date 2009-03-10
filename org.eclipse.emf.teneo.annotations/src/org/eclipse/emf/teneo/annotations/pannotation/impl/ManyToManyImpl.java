/**
 * <copyright>
 * </copyright>
 *
 * $Id: ManyToManyImpl.java,v 1.14 2008/08/11 20:42:23 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToMany;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Many To Many</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl#getTargetEntity <em>Target Entity</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl#getCascade <em>Cascade</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl#getFetch <em>Fetch</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl#getMappedBy <em>Mapped By</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl#isIndexed <em>Indexed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ManyToManyImpl extends PAnnotationImpl implements ManyToMany {
	/**
	 * The default value of the '{@link #getTargetEntity() <em>Target Entity</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTargetEntity()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_ENTITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetEntity() <em>Target Entity</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTargetEntity()
	 * @generated
	 * @ordered
	 */
	protected String targetEntity = TARGET_ENTITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCascade() <em>Cascade</em>}' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCascade()
	 * @generated
	 * @ordered
	 */
	protected EList<CascadeType> cascade;

	/**
	 * The default value of the '{@link #getFetch() <em>Fetch</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFetch()
	 * @generated
	 * @ordered
	 */
	protected static final FetchType FETCH_EDEFAULT = FetchType.LAZY;

	/**
	 * The cached value of the '{@link #getFetch() <em>Fetch</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getFetch()
	 * @generated
	 * @ordered
	 */
	protected FetchType fetch = FETCH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedBy() <em>Mapped By</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMappedBy()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_BY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedBy() <em>Mapped By</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMappedBy()
	 * @generated
	 * @ordered
	 */
	protected String mappedBy = MAPPED_BY_EDEFAULT;

	/**
	 * The default value of the '{@link #isIndexed() <em>Indexed</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isIndexed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INDEXED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isIndexed() <em>Indexed</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isIndexed()
	 * @generated
	 * @ordered
	 */
	protected boolean indexed = INDEXED_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ManyToManyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PannotationPackage.Literals.MANY_TO_MANY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FetchType getFetch() {
		return fetch;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFetch(FetchType newFetch) {
		FetchType oldFetch = fetch;
		fetch = newFetch == null ? FETCH_EDEFAULT : newFetch;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.MANY_TO_MANY__FETCH, oldFetch, fetch));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetEntity() {
		return targetEntity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetEntity(String newTargetEntity) {
		String oldTargetEntity = targetEntity;
		targetEntity = newTargetEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.MANY_TO_MANY__TARGET_ENTITY, oldTargetEntity, targetEntity));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CascadeType> getCascade() {
		if (cascade == null) {
			cascade = new EDataTypeUniqueEList<CascadeType>(CascadeType.class, this, PannotationPackage.MANY_TO_MANY__CASCADE);
		}
		return cascade;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedBy() {
		return mappedBy;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedBy(String newMappedBy) {
		String oldMappedBy = mappedBy;
		mappedBy = newMappedBy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.MANY_TO_MANY__MAPPED_BY, oldMappedBy, mappedBy));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIndexed() {
		return indexed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexed(boolean newIndexed) {
		boolean oldIndexed = indexed;
		indexed = newIndexed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.MANY_TO_MANY__INDEXED, oldIndexed, indexed));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PannotationPackage.MANY_TO_MANY__TARGET_ENTITY:
				return getTargetEntity();
			case PannotationPackage.MANY_TO_MANY__CASCADE:
				return getCascade();
			case PannotationPackage.MANY_TO_MANY__FETCH:
				return getFetch();
			case PannotationPackage.MANY_TO_MANY__MAPPED_BY:
				return getMappedBy();
			case PannotationPackage.MANY_TO_MANY__INDEXED:
				return isIndexed() ? Boolean.TRUE : Boolean.FALSE;
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
			case PannotationPackage.MANY_TO_MANY__TARGET_ENTITY:
				setTargetEntity((String)newValue);
				return;
			case PannotationPackage.MANY_TO_MANY__CASCADE:
				getCascade().clear();
				getCascade().addAll((Collection<? extends CascadeType>)newValue);
				return;
			case PannotationPackage.MANY_TO_MANY__FETCH:
				setFetch((FetchType)newValue);
				return;
			case PannotationPackage.MANY_TO_MANY__MAPPED_BY:
				setMappedBy((String)newValue);
				return;
			case PannotationPackage.MANY_TO_MANY__INDEXED:
				setIndexed(((Boolean)newValue).booleanValue());
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
			case PannotationPackage.MANY_TO_MANY__TARGET_ENTITY:
				setTargetEntity(TARGET_ENTITY_EDEFAULT);
				return;
			case PannotationPackage.MANY_TO_MANY__CASCADE:
				getCascade().clear();
				return;
			case PannotationPackage.MANY_TO_MANY__FETCH:
				setFetch(FETCH_EDEFAULT);
				return;
			case PannotationPackage.MANY_TO_MANY__MAPPED_BY:
				setMappedBy(MAPPED_BY_EDEFAULT);
				return;
			case PannotationPackage.MANY_TO_MANY__INDEXED:
				setIndexed(INDEXED_EDEFAULT);
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
			case PannotationPackage.MANY_TO_MANY__TARGET_ENTITY:
				return TARGET_ENTITY_EDEFAULT == null ? targetEntity != null : !TARGET_ENTITY_EDEFAULT.equals(targetEntity);
			case PannotationPackage.MANY_TO_MANY__CASCADE:
				return cascade != null && !cascade.isEmpty();
			case PannotationPackage.MANY_TO_MANY__FETCH:
				return fetch != FETCH_EDEFAULT;
			case PannotationPackage.MANY_TO_MANY__MAPPED_BY:
				return MAPPED_BY_EDEFAULT == null ? mappedBy != null : !MAPPED_BY_EDEFAULT.equals(mappedBy);
			case PannotationPackage.MANY_TO_MANY__INDEXED:
				return indexed != INDEXED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (targetEntity: ");
		result.append(targetEntity);
		result.append(", cascade: ");
		result.append(cascade);
		result.append(", fetch: ");
		result.append(fetch);
		result.append(", mappedBy: ");
		result.append(mappedBy);
		result.append(", indexed: ");
		result.append(indexed);
		result.append(')');
		return result.toString();
	}

	/** Returns true if this one to many should be modeled as a list */
	public boolean isList() {
		return isIndexed();
	}

	@Override
	protected String getAnnotationValue(org.eclipse.emf.ecore.EObject eObject, EStructuralFeature eFeature,
			boolean prependComma, List<String> imports) {
		if (eFeature == PannotationPackage.eINSTANCE.getManyToMany_TargetEntity()) {
			// do not return anything because jpa expects a real classname,
			// the targetentity only has an entityname here.
			return "";
		}
		return super.getAnnotationValue(eObject, eFeature, prependComma, imports);
	}

} // ManyToManyImpl
