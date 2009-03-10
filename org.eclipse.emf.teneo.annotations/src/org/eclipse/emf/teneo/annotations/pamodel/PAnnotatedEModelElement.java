/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotatedEModelElement.java,v 1.8 2008/01/18 06:20:56 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel;

import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;
import org.eclipse.emf.teneo.annotations.pannotation.Transient;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PAnnotated EModel Element</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getModelElement <em>Model Element</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getTransient <em>Transient</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEModelElement()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='PAnnotationElementCommutes'"
 * @generated
 */
public interface PAnnotatedEModelElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotated Element</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Element</em>' reference.
	 * @see #setModelElement(ENamedElement)
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEModelElement_ModelElement()
	 * @model derived="true"
	 *        annotation="teneo/internal/PersistenceMapping ignore='true'"
	 * @generated
	 */
	ENamedElement getModelElement();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getModelElement <em>Model Element</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element</em>' reference.
	 * @see #getModelElement()
	 * @generated
	 */
	void setModelElement(ENamedElement value);

	/**
	 * Returns the value of the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transient</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transient</em>' containment reference.
	 * @see #setTransient(Transient)
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEModelElement_Transient()
	 * @model containment="true"
	 * @generated
	 */
	Transient getTransient();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getTransient <em>Transient</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transient</em>' containment reference.
	 * @see #getTransient()
	 * @generated
	 */
	void setTransient(Transient value);

	/**
	 * @return Returns the PAnnotatedModel containing this element.
	 */
	PAnnotatedModel getPaModel();

	/**
	 * @return Returns the list of annotations defined on this annotated element
	 */
	List<PAnnotation> getAnnotations();

} // PAnnotatedEModelElement
