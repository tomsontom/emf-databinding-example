/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbmodelSwitch.java,v 1.2 2007/11/14 16:38:34 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement;

import org.eclipse.emf.teneo.hibernate.hbmodel.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage
 * @generated
 */
public class HbmodelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static HbmodelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbmodelSwitch() {
		if (modelPackage == null) {
			modelPackage = HbmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case HbmodelPackage.HB_ANNOTATED_ETYPE_ELEMENT: {
				HbAnnotatedETypeElement hbAnnotatedETypeElement = (HbAnnotatedETypeElement)theEObject;
				T result = caseHbAnnotatedETypeElement(hbAnnotatedETypeElement);
				if (result == null) result = casePAnnotatedEStructuralFeature(hbAnnotatedETypeElement);
				if (result == null) result = caseHbAnnotatedEModelElement(hbAnnotatedETypeElement);
				if (result == null) result = casePAnnotatedETypedElement(hbAnnotatedETypeElement);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedETypeElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE: {
				HbAnnotatedEAttribute hbAnnotatedEAttribute = (HbAnnotatedEAttribute)theEObject;
				T result = caseHbAnnotatedEAttribute(hbAnnotatedEAttribute);
				if (result == null) result = casePAnnotatedEAttribute(hbAnnotatedEAttribute);
				if (result == null) result = caseHbAnnotatedETypeElement(hbAnnotatedEAttribute);
				if (result == null) result = casePAnnotatedEStructuralFeature(hbAnnotatedEAttribute);
				if (result == null) result = caseHbAnnotatedEModelElement(hbAnnotatedEAttribute);
				if (result == null) result = casePAnnotatedETypedElement(hbAnnotatedEAttribute);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedEAttribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbmodelPackage.HB_ANNOTATED_ECLASS: {
				HbAnnotatedEClass hbAnnotatedEClass = (HbAnnotatedEClass)theEObject;
				T result = caseHbAnnotatedEClass(hbAnnotatedEClass);
				if (result == null) result = casePAnnotatedEClass(hbAnnotatedEClass);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedEClass);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbmodelPackage.HB_ANNOTATED_EMODEL_ELEMENT: {
				HbAnnotatedEModelElement hbAnnotatedEModelElement = (HbAnnotatedEModelElement)theEObject;
				T result = caseHbAnnotatedEModelElement(hbAnnotatedEModelElement);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedEModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE: {
				HbAnnotatedEPackage hbAnnotatedEPackage = (HbAnnotatedEPackage)theEObject;
				T result = caseHbAnnotatedEPackage(hbAnnotatedEPackage);
				if (result == null) result = casePAnnotatedEPackage(hbAnnotatedEPackage);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedEPackage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbmodelPackage.HB_ANNOTATED_EREFERENCE: {
				HbAnnotatedEReference hbAnnotatedEReference = (HbAnnotatedEReference)theEObject;
				T result = caseHbAnnotatedEReference(hbAnnotatedEReference);
				if (result == null) result = casePAnnotatedEReference(hbAnnotatedEReference);
				if (result == null) result = caseHbAnnotatedETypeElement(hbAnnotatedEReference);
				if (result == null) result = casePAnnotatedEStructuralFeature(hbAnnotatedEReference);
				if (result == null) result = caseHbAnnotatedEModelElement(hbAnnotatedEReference);
				if (result == null) result = casePAnnotatedETypedElement(hbAnnotatedEReference);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedEReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE: {
				HbAnnotatedEDataType hbAnnotatedEDataType = (HbAnnotatedEDataType)theEObject;
				T result = caseHbAnnotatedEDataType(hbAnnotatedEDataType);
				if (result == null) result = casePAnnotatedEDataType(hbAnnotatedEDataType);
				if (result == null) result = caseHbAnnotatedEModelElement(hbAnnotatedEDataType);
				if (result == null) result = casePAnnotatedEModelElement(hbAnnotatedEDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EType Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EType Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedETypeElement(HbAnnotatedETypeElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EAttribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EAttribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedEAttribute(HbAnnotatedEAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EClass</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EClass</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedEClass(HbAnnotatedEClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedEModelElement(HbAnnotatedEModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EPackage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EPackage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedEPackage(HbAnnotatedEPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EReference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EReference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedEReference(HbAnnotatedEReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Hb Annotated EData Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Hb Annotated EData Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHbAnnotatedEDataType(HbAnnotatedEDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEModelElement(PAnnotatedEModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated ETyped Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated ETyped Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedETypedElement(PAnnotatedETypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EStructural Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EStructural Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEStructuralFeature(PAnnotatedEStructuralFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EAttribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EAttribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEAttribute(PAnnotatedEAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EClass</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EClass</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEClass(PAnnotatedEClass object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EPackage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EPackage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEPackage(PAnnotatedEPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EReference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EReference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEReference(PAnnotatedEReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>PAnnotated EData Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>PAnnotated EData Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePAnnotatedEDataType(PAnnotatedEDataType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //HbmodelSwitch
