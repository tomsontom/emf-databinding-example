/**
 * <copyright>
 * </copyright>
 *
 * $Id: Temporal.java,v 1.5 2007/07/04 19:28:00 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Temporal</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.Temporal#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getTemporal()
 * @model annotation="teneo/internal/Target 0='EAttribute'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AllowedElementType'"
 * @generated
 */
public interface Temporal extends PAnnotation {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. The default value is <code>"TIMESTAMP"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.annotations.pannotation.TemporalType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TemporalType
	 * @see #setValue(TemporalType)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getTemporal_Value()
	 * @model default="TIMESTAMP"
	 * @generated
	 */
	TemporalType getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.Temporal#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TemporalType
	 * @see #getValue()
	 * @generated
	 */
	void setValue(TemporalType value);

} // Temporal
