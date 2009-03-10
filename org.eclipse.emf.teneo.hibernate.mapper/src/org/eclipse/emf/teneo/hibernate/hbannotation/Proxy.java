/**
 * <copyright>
 * </copyright>
 *
 * $Id: Proxy.java,v 1.3 2007/07/04 19:31:48 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#getProxyClass <em>Proxy Class</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#isLazy <em>Lazy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getProxy()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EClass'"
 * @generated
 */
public interface Proxy extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Proxy Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Proxy Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Proxy Class</em>' attribute.
	 * @see #setProxyClass(String)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getProxy_ProxyClass()
	 * @model
	 * @generated
	 */
	String getProxyClass();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#getProxyClass <em>Proxy Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Proxy Class</em>' attribute.
	 * @see #getProxyClass()
	 * @generated
	 */
	void setProxyClass(String value);

	/**
	 * Returns the value of the '<em><b>Lazy</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lazy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lazy</em>' attribute.
	 * @see #setLazy(boolean)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getProxy_Lazy()
	 * @model default="true"
	 * @generated
	 */
	boolean isLazy();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#isLazy <em>Lazy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lazy</em>' attribute.
	 * @see #isLazy()
	 * @generated
	 */
	void setLazy(boolean value);

} // Proxy
