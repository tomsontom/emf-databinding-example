/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedEPackageImpl.java,v 1.9 2008/04/23 15:44:26 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl;
import org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator;
import org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Hb Annotated EPackage</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEPackageImpl#getHbGenericGenerators <em>Hb Generic Generators</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEPackageImpl#getHbTypeDef <em>Hb Type Def</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEPackageImpl#getHbNamedQuery <em>Hb Named Query</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.impl.HbAnnotatedEPackageImpl#getFilterDef <em>Filter Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HbAnnotatedEPackageImpl extends PAnnotatedEPackageImpl implements HbAnnotatedEPackage {
	/**
	 * The cached value of the '{@link #getHbGenericGenerators() <em>Hb Generic Generators</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getHbGenericGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<GenericGenerator> hbGenericGenerators;

	/**
	 * The cached value of the '{@link #getHbTypeDef() <em>Hb Type Def</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHbTypeDef()
	 * @generated
	 * @ordered
	 */
	protected EList<TypeDef> hbTypeDef;

	/**
	 * The cached value of the '{@link #getHbNamedQuery() <em>Hb Named Query</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHbNamedQuery()
	 * @generated
	 * @ordered
	 */
	protected EList<NamedQuery> hbNamedQuery;

	/**
	 * The cached value of the '{@link #getFilterDef() <em>Filter Def</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilterDef()
	 * @generated
	 * @ordered
	 */
	protected EList<FilterDef> filterDef;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected HbAnnotatedEPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbmodelPackage.Literals.HB_ANNOTATED_EPACKAGE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GenericGenerator> getHbGenericGenerators() {
		if (hbGenericGenerators == null) {
			hbGenericGenerators = new EObjectContainmentEList<GenericGenerator>(GenericGenerator.class, this, HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS);
		}
		return hbGenericGenerators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TypeDef> getHbTypeDef() {
		if (hbTypeDef == null) {
			hbTypeDef = new EObjectContainmentEList<TypeDef>(TypeDef.class, this, HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF);
		}
		return hbTypeDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NamedQuery> getHbNamedQuery() {
		if (hbNamedQuery == null) {
			hbNamedQuery = new EObjectResolvingEList<NamedQuery>(NamedQuery.class, this, HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_NAMED_QUERY);
		}
		return hbNamedQuery;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FilterDef> getFilterDef() {
		if (filterDef == null) {
			filterDef = new EObjectResolvingEList<FilterDef>(FilterDef.class, this, HbmodelPackage.HB_ANNOTATED_EPACKAGE__FILTER_DEF);
		}
		return filterDef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS:
				return ((InternalEList<?>)getHbGenericGenerators()).basicRemove(otherEnd, msgs);
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF:
				return ((InternalEList<?>)getHbTypeDef()).basicRemove(otherEnd, msgs);
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
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS:
				return getHbGenericGenerators();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF:
				return getHbTypeDef();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_NAMED_QUERY:
				return getHbNamedQuery();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__FILTER_DEF:
				return getFilterDef();
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
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS:
				getHbGenericGenerators().clear();
				getHbGenericGenerators().addAll((Collection<? extends GenericGenerator>)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF:
				getHbTypeDef().clear();
				getHbTypeDef().addAll((Collection<? extends TypeDef>)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_NAMED_QUERY:
				getHbNamedQuery().clear();
				getHbNamedQuery().addAll((Collection<? extends NamedQuery>)newValue);
				return;
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__FILTER_DEF:
				getFilterDef().clear();
				getFilterDef().addAll((Collection<? extends FilterDef>)newValue);
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
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS:
				getHbGenericGenerators().clear();
				return;
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF:
				getHbTypeDef().clear();
				return;
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_NAMED_QUERY:
				getHbNamedQuery().clear();
				return;
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__FILTER_DEF:
				getFilterDef().clear();
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
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS:
				return hbGenericGenerators != null && !hbGenericGenerators.isEmpty();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF:
				return hbTypeDef != null && !hbTypeDef.isEmpty();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__HB_NAMED_QUERY:
				return hbNamedQuery != null && !hbNamedQuery.isEmpty();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE__FILTER_DEF:
				return filterDef != null && !filterDef.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // HbAnnotatedEPackageImpl
