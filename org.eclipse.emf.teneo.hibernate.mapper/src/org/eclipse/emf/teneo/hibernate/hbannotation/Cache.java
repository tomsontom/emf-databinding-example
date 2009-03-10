/**
 * <copyright>
 * </copyright>
 *
 * $Id: Cache.java,v 1.5 2007/07/04 19:31:48 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Cache</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getUsage <em>Usage</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getRegion <em>Region</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getInclude <em>Include</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCache()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EReference' 1='EClass'"
 * @generated
 */
public interface Cache extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Usage</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Usage</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy
	 * @see #setUsage(CacheConcurrencyStrategy)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCache_Usage()
	 * @model
	 * @generated
	 */
	CacheConcurrencyStrategy getUsage();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getUsage <em>Usage</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy
	 * @see #getUsage()
	 * @generated
	 */
	void setUsage(CacheConcurrencyStrategy value);

	/**
	 * Returns the value of the '<em><b>Region</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Region</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Region</em>' attribute.
	 * @see #setRegion(String)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCache_Region()
	 * @model
	 * @generated
	 */
	String getRegion();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getRegion <em>Region</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Region</em>' attribute.
	 * @see #getRegion()
	 * @generated
	 */
	void setRegion(String value);

	/**
	 * Returns the value of the '<em><b>Include</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include</em>' attribute.
	 * @see #setInclude(String)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getCache_Include()
	 * @model
	 * @generated
	 */
	String getInclude();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getInclude <em>Include</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Include</em>' attribute.
	 * @see #getInclude()
	 * @generated
	 */
	void setInclude(String value);

} // Cache
