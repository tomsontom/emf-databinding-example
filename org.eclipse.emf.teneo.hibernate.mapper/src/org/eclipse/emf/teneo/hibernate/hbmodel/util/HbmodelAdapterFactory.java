/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbmodelAdapterFactory.java,v 1.4 2008/06/10 08:24:59 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage
 * @generated
 */
public class HbmodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static HbmodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbmodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = HbmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HbmodelSwitch<Adapter> modelSwitch =
		new HbmodelSwitch<Adapter>() {
			@Override
			public Adapter caseHbAnnotatedETypeElement(HbAnnotatedETypeElement object) {
				return createHbAnnotatedETypeElementAdapter();
			}
			@Override
			public Adapter caseHbAnnotatedEAttribute(HbAnnotatedEAttribute object) {
				return createHbAnnotatedEAttributeAdapter();
			}
			@Override
			public Adapter caseHbAnnotatedEClass(HbAnnotatedEClass object) {
				return createHbAnnotatedEClassAdapter();
			}
			@Override
			public Adapter caseHbAnnotatedEModelElement(HbAnnotatedEModelElement object) {
				return createHbAnnotatedEModelElementAdapter();
			}
			@Override
			public Adapter caseHbAnnotatedEPackage(HbAnnotatedEPackage object) {
				return createHbAnnotatedEPackageAdapter();
			}
			@Override
			public Adapter caseHbAnnotatedEReference(HbAnnotatedEReference object) {
				return createHbAnnotatedEReferenceAdapter();
			}
			@Override
			public Adapter caseHbAnnotatedEDataType(HbAnnotatedEDataType object) {
				return createHbAnnotatedEDataTypeAdapter();
			}
			@Override
			public Adapter casePAnnotatedEModelElement(PAnnotatedEModelElement object) {
				return createPAnnotatedEModelElementAdapter();
			}
			@Override
			public Adapter casePAnnotatedETypedElement(PAnnotatedETypedElement object) {
				return createPAnnotatedETypedElementAdapter();
			}
			@Override
			public Adapter casePAnnotatedEStructuralFeature(PAnnotatedEStructuralFeature object) {
				return createPAnnotatedEStructuralFeatureAdapter();
			}
			@Override
			public Adapter casePAnnotatedEAttribute(PAnnotatedEAttribute object) {
				return createPAnnotatedEAttributeAdapter();
			}
			@Override
			public Adapter casePAnnotatedEClass(PAnnotatedEClass object) {
				return createPAnnotatedEClassAdapter();
			}
			@Override
			public Adapter casePAnnotatedEPackage(PAnnotatedEPackage object) {
				return createPAnnotatedEPackageAdapter();
			}
			@Override
			public Adapter casePAnnotatedEReference(PAnnotatedEReference object) {
				return createPAnnotatedEReferenceAdapter();
			}
			@Override
			public Adapter casePAnnotatedEDataType(PAnnotatedEDataType object) {
				return createPAnnotatedEDataTypeAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement <em>Hb Annotated EType Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement
	 * @generated
	 */
	public Adapter createHbAnnotatedETypeElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute <em>Hb Annotated EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute
	 * @generated
	 */
	public Adapter createHbAnnotatedEAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass <em>Hb Annotated EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass
	 * @generated
	 */
	public Adapter createHbAnnotatedEClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEModelElement <em>Hb Annotated EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEModelElement
	 * @generated
	 */
	public Adapter createHbAnnotatedEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage <em>Hb Annotated EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage
	 * @generated
	 */
	public Adapter createHbAnnotatedEPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference <em>Hb Annotated EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference
	 * @generated
	 */
	public Adapter createHbAnnotatedEReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType <em>Hb Annotated EData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType
	 * @generated
	 */
	public Adapter createHbAnnotatedEDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement <em>PAnnotated EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement
	 * @generated
	 */
	public Adapter createPAnnotatedEModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement <em>PAnnotated ETyped Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement
	 * @generated
	 */
	public Adapter createPAnnotatedETypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature <em>PAnnotated EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature
	 * @generated
	 */
	public Adapter createPAnnotatedEStructuralFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute <em>PAnnotated EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute
	 * @generated
	 */
	public Adapter createPAnnotatedEAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass <em>PAnnotated EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass
	 * @generated
	 */
	public Adapter createPAnnotatedEClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage <em>PAnnotated EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage
	 * @generated
	 */
	public Adapter createPAnnotatedEPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference <em>PAnnotated EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference
	 * @generated
	 */
	public Adapter createPAnnotatedEReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType <em>PAnnotated EData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType
	 * @generated
	 */
	public Adapter createPAnnotatedEDataTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //HbmodelAdapterFactory
