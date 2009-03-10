/**
 * <copyright>
 * </copyright>
 *
 * $Id: ProxyImpl.java,v 1.6 2008/01/18 06:21:36 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.Proxy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Proxy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ProxyImpl#getProxyClass <em>Proxy Class</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ProxyImpl#isLazy <em>Lazy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProxyImpl extends HbAnnotationImpl implements Proxy {
	/**
	 * The default value of the '{@link #getProxyClass() <em>Proxy Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyClass()
	 * @generated
	 * @ordered
	 */
	protected static final String PROXY_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProxyClass() <em>Proxy Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProxyClass()
	 * @generated
	 * @ordered
	 */
	protected String proxyClass = PROXY_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #isLazy() <em>Lazy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLazy()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LAZY_EDEFAULT = true; // TODO The default value literal "" is not valid.

	/**
	 * The cached value of the '{@link #isLazy() <em>Lazy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLazy()
	 * @generated
	 * @ordered
	 */
	protected boolean lazy = LAZY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProxyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbannotationPackage.Literals.PROXY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProxyClass() {
		return proxyClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProxyClass(String newProxyClass) {
		String oldProxyClass = proxyClass;
		proxyClass = newProxyClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.PROXY__PROXY_CLASS, oldProxyClass, proxyClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLazy() {
		return lazy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLazy(boolean newLazy) {
		boolean oldLazy = lazy;
		lazy = newLazy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, HbannotationPackage.PROXY__LAZY, oldLazy, lazy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HbannotationPackage.PROXY__PROXY_CLASS:
				return getProxyClass();
			case HbannotationPackage.PROXY__LAZY:
				return isLazy() ? Boolean.TRUE : Boolean.FALSE;
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
			case HbannotationPackage.PROXY__PROXY_CLASS:
				setProxyClass((String)newValue);
				return;
			case HbannotationPackage.PROXY__LAZY:
				setLazy(((Boolean)newValue).booleanValue());
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
			case HbannotationPackage.PROXY__PROXY_CLASS:
				setProxyClass(PROXY_CLASS_EDEFAULT);
				return;
			case HbannotationPackage.PROXY__LAZY:
				setLazy(LAZY_EDEFAULT);
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
			case HbannotationPackage.PROXY__PROXY_CLASS:
				return PROXY_CLASS_EDEFAULT == null ? proxyClass != null : !PROXY_CLASS_EDEFAULT.equals(proxyClass);
			case HbannotationPackage.PROXY__LAZY:
				return lazy != LAZY_EDEFAULT;
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
		result.append(" (proxyClass: ");
		result.append(proxyClass);
		result.append(", lazy: ");
		result.append(lazy);
		result.append(')');
		return result.toString();
	}

} //ProxyImpl
