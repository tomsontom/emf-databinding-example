/**
 * <copyright>
 * </copyright>
 *
 * $Id: Enumerated.java,v 1.5 2007/07/04 19:28:01 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Enumerated</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.Enumerated#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getEnumerated()
 * @model annotation="teneo/internal/Target 0='EStructuralFeature'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AllowedElementType'"
 * @generated
 */
public interface Enumerated extends PAnnotation {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. The default value is <code>"ORDINAL"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.annotations.pannotation.EnumType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EnumType
	 * @see #setValue(EnumType)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getEnumerated_Value()
	 * @model default="ORDINAL"
	 * @generated
	 */
	EnumType getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.Enumerated#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EnumType
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EnumType value);

} // Enumerated
