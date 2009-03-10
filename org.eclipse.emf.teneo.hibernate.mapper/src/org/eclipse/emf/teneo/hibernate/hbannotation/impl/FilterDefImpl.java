/**
 * <copyright>
 * </copyright>
 *
 * $Id: FilterDefImpl.java,v 1.1 2008/04/23 15:44:25 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl#getDefaultCondition <em>Default Condition</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FilterDefImpl extends HbAnnotationImpl implements FilterDef {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultCondition() <em>Default Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCondition()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_CONDITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultCondition() <em>Default Condition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCondition()
	 * @generated
	 * @ordered
	 */
	protected String defaultCondition = DEFAULT_CONDITION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<ParamDef> parameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FilterDefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbannotationPackage.Literals.FILTER_DEF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.FILTER_DEF__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultCondition() {
		return defaultCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultCondition(String newDefaultCondition) {
		String oldDefaultCondition = defaultCondition;
		defaultCondition = newDefaultCondition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.FILTER_DEF__DEFAULT_CONDITION, oldDefaultCondition, defaultCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParamDef> getParameters() {
		if (parameters == null) {
			parameters = new EObjectResolvingEList<ParamDef>(ParamDef.class, this, HbannotationPackage.FILTER_DEF__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HbannotationPackage.FILTER_DEF__NAME:
				return getName();
			case HbannotationPackage.FILTER_DEF__DEFAULT_CONDITION:
				return getDefaultCondition();
			case HbannotationPackage.FILTER_DEF__PARAMETERS:
				return getParameters();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HbannotationPackage.FILTER_DEF__NAME:
				setName((String)newValue);
				return;
			case HbannotationPackage.FILTER_DEF__DEFAULT_CONDITION:
				setDefaultCondition((String)newValue);
				return;
			case HbannotationPackage.FILTER_DEF__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends ParamDef>)newValue);
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
			case HbannotationPackage.FILTER_DEF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HbannotationPackage.FILTER_DEF__DEFAULT_CONDITION:
				setDefaultCondition(DEFAULT_CONDITION_EDEFAULT);
				return;
			case HbannotationPackage.FILTER_DEF__PARAMETERS:
				getParameters().clear();
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
			case HbannotationPackage.FILTER_DEF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case HbannotationPackage.FILTER_DEF__DEFAULT_CONDITION:
				return DEFAULT_CONDITION_EDEFAULT == null ? defaultCondition != null : !DEFAULT_CONDITION_EDEFAULT.equals(defaultCondition);
			case HbannotationPackage.FILTER_DEF__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", defaultCondition: ");
		result.append(defaultCondition);
		result.append(')');
		return result.toString();
	}

} //FilterDefImpl
