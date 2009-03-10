/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbmodelFactoryImpl.java,v 1.6 2008/03/30 10:01:15 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.teneo.hibernate.hbmodel.*;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelFactory;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HbmodelFactoryImpl extends EFactoryImpl implements HbmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HbmodelFactory init() {
		try {
			HbmodelFactory theHbmodelFactory = (HbmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/teneo/2006/HbModel"); 
			if (theHbmodelFactory != null) {
				return theHbmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new HbmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case HbmodelPackage.HB_ANNOTATED_EATTRIBUTE: return createHbAnnotatedEAttribute();
			case HbmodelPackage.HB_ANNOTATED_ECLASS: return createHbAnnotatedEClass();
			case HbmodelPackage.HB_ANNOTATED_EPACKAGE: return createHbAnnotatedEPackage();
			case HbmodelPackage.HB_ANNOTATED_EREFERENCE: return createHbAnnotatedEReference();
			case HbmodelPackage.HB_ANNOTATED_EDATA_TYPE: return createHbAnnotatedEDataType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbAnnotatedEAttribute createHbAnnotatedEAttribute() {
		HbAnnotatedEAttributeImpl hbAnnotatedEAttribute = new HbAnnotatedEAttributeImpl();
		return hbAnnotatedEAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbAnnotatedEClass createHbAnnotatedEClass() {
		HbAnnotatedEClassImpl hbAnnotatedEClass = new HbAnnotatedEClassImpl();
		return hbAnnotatedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbAnnotatedEPackage createHbAnnotatedEPackage() {
		HbAnnotatedEPackageImpl hbAnnotatedEPackage = new HbAnnotatedEPackageImpl();
		return hbAnnotatedEPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbAnnotatedEReference createHbAnnotatedEReference() {
		HbAnnotatedEReferenceImpl hbAnnotatedEReference = new HbAnnotatedEReferenceImpl();
		return hbAnnotatedEReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbAnnotatedEDataType createHbAnnotatedEDataType() {
		HbAnnotatedEDataTypeImpl hbAnnotatedEDataType = new HbAnnotatedEDataTypeImpl();
		return hbAnnotatedEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbmodelPackage getHbmodelPackage() {
		return (HbmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static HbmodelPackage getPackage() {
		return HbmodelPackage.eINSTANCE;
	}

} //HbmodelFactoryImpl
