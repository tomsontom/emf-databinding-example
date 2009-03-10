/**
 * <copyright>
 * </copyright>
 *
 * $Id: Generated.java,v 1.1 2007/07/11 17:35:11 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generated</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbannotation.Generated#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getGenerated()
 * @model annotation="http://annotation.elver.org/internal/Target 0='EAttribute'"
 * @generated
 */
public interface Generated extends HbAnnotation {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime
	 * @see #setValue(GenerationTime)
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage#getGenerated_Value()
	 * @model
	 * @generated
	 */
	GenerationTime getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Generated#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime
	 * @see #getValue()
	 * @generated
	 */
	void setValue(GenerationTime value);

} // Generated
