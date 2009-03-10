/**
 * <copyright>
 * </copyright>
 *
 * $Id: PamodelFactoryImpl.java,v 1.24 2008/01/29 12:58:06 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.teneo.annotations.pamodel.*;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelFactory;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PamodelFactoryImpl extends EFactoryImpl implements PamodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PamodelFactory init() {
		try {
			PamodelFactory thePamodelFactory = (PamodelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/teneo/elver/2005/pamodel"); 
			if (thePamodelFactory != null) {
				return thePamodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PamodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PamodelFactoryImpl() {
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
			case PamodelPackage.PANNOTATED_MODEL: return createPAnnotatedModel();
			case PamodelPackage.PANNOTATED_EPACKAGE: return createPAnnotatedEPackage();
			case PamodelPackage.PANNOTATED_ECLASS: return createPAnnotatedEClass();
			case PamodelPackage.PANNOTATED_EATTRIBUTE: return createPAnnotatedEAttribute();
			case PamodelPackage.PANNOTATED_EREFERENCE: return createPAnnotatedEReference();
			case PamodelPackage.PANNOTATED_EDATA_TYPE: return createPAnnotatedEDataType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedModel createPAnnotatedModel() {
		PAnnotatedModelImpl pAnnotatedModel = new PAnnotatedModelImpl();
		return pAnnotatedModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedEPackage createPAnnotatedEPackage() {
		PAnnotatedEPackageImpl pAnnotatedEPackage = new PAnnotatedEPackageImpl();
		return pAnnotatedEPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedEClass createPAnnotatedEClass() {
		PAnnotatedEClassImpl pAnnotatedEClass = new PAnnotatedEClassImpl();
		return pAnnotatedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedEAttribute createPAnnotatedEAttribute() {
		PAnnotatedEAttributeImpl pAnnotatedEAttribute = new PAnnotatedEAttributeImpl();
		return pAnnotatedEAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedEDataType createPAnnotatedEDataType() {
		PAnnotatedEDataTypeImpl pAnnotatedEDataType = new PAnnotatedEDataTypeImpl();
		return pAnnotatedEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedEReference createPAnnotatedEReference() {
		PAnnotatedEReferenceImpl pAnnotatedEReference = new PAnnotatedEReferenceImpl();
		return pAnnotatedEReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PamodelPackage getPamodelPackage() {
		return (PamodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PamodelPackage getPackage() {
		return PamodelPackage.eINSTANCE;
	}

} //PamodelFactoryImpl
