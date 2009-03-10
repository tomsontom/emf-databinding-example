/**
 * <copyright>
 * </copyright>
 *
 * $Id: PamodelValidator.java,v 1.27 2009/02/24 12:04:59 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage
 * @generated
 */
public class PamodelValidator extends EObjectValidator {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final PamodelValidator INSTANCE = new PamodelValidator();

	/**
	 * A constant for the
	 * {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of
	 * diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes}
	 * from this package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.teneo.annotations.pamodel";

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for
	 * additional hand written constants in a derived class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PamodelValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return PamodelPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the
	 * model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case PamodelPackage.PANNOTATED_EMODEL_ELEMENT:
			return validatePAnnotatedEModelElement(
					(PAnnotatedEModelElement) value, diagnostics, context);
		case PamodelPackage.PANNOTATED_MODEL:
			return validatePAnnotatedModel((PAnnotatedModel) value,
					diagnostics, context);
		case PamodelPackage.PANNOTATED_EPACKAGE:
			return validatePAnnotatedEPackage((PAnnotatedEPackage) value,
					diagnostics, context);
		case PamodelPackage.PANNOTATED_ECLASS:
			return validatePAnnotatedEClass((PAnnotatedEClass) value,
					diagnostics, context);
		case PamodelPackage.PANNOTATED_ESTRUCTURAL_FEATURE:
			return validatePAnnotatedEStructuralFeature(
					(PAnnotatedEStructuralFeature) value, diagnostics, context);
		case PamodelPackage.PANNOTATED_EATTRIBUTE:
			return validatePAnnotatedEAttribute((PAnnotatedEAttribute) value,
					diagnostics, context);
		case PamodelPackage.PANNOTATED_EREFERENCE:
			return validatePAnnotatedEReference((PAnnotatedEReference) value,
					diagnostics, context);
		case PamodelPackage.PANNOTATED_ETYPED_ELEMENT:
			return validatePAnnotatedETypedElement(
					(PAnnotatedETypedElement) value, diagnostics, context);
		case PamodelPackage.PANNOTATED_EDATA_TYPE:
			return validatePAnnotatedEDataType((PAnnotatedEDataType) value,
					diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEModelElement(
			PAnnotatedEModelElement pAnnotatedEModelElement,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(
				pAnnotatedEModelElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedEModelElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(
					pAnnotatedEModelElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEModelElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEModelElement, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEModelElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedEModelElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEModelElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the PAnnotationElementCommutes constraint of '
	 * <em>PAnnotated EModel Element</em>'. <!-- begin-user-doc --> Check that
	 * each contained PAnnotation references the same eModelElement <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEModelElement_PAnnotationElementCommutes(
			PAnnotatedEModelElement pAnnotatedEModelElement,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		// if (false) {
		// if (diagnostics != null) {
		// diagnostics.add
		// (createDiagnostic
		// (Diagnostic.ERROR,
		// DIAGNOSTIC_SOURCE,
		// 0,
		// "_UI_GenericConstraint_diagnostic",
		// new Object[] { "PAnnotationElementCommutes",
		// getObjectLabel(pAnnotatedEModelElement, context) },
		// new Object[] { pAnnotatedEModelElement },
		// context));
		// }
		// return false;
		// }
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedModel(PAnnotatedModel pAnnotatedModel,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(pAnnotatedModel,
				diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedModel,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(pAnnotatedModel,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedModel, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedModel, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedModel, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedModel,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedModel_AtMostOnePackage(pAnnotatedModel,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedModel_DistinctGenerators(
					pAnnotatedModel, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the AtMostOnePackage constraint of '<em>PAnnotated Model</em>'.
	 * Verifies that the model does not contain distinct PAnnotatedEPackage
	 * refering to the same EPackage.
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedModel_AtMostOnePackage(
			PAnnotatedModel pAnnotatedModel, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		// if (false) {
		// if (diagnostics != null) {
		// diagnostics.add(createDiagnostic(Diagnostic.ERROR,
		// DIAGNOSTIC_SOURCE, 0,
		// "_UI_GenericConstraint_diagnostic", new Object[] {
		// "AtMostOnePackage",
		// getObjectLabel(pAnnotatedModel, context) },
		// new Object[] { pAnnotatedModel }, context));
		// }
		// return false;
		// }
		return true;
	}

	/**
	 * Validates the DistinctGenerators constraint of '<em>PAnnotated Model</em>
	 * '. <!-- begin-user-doc --> Check that each in each scope no distinct
	 * TableGenerator or SequenceGenerator use the same name. <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedModel_DistinctGenerators(
			PAnnotatedModel pAnnotatedModel, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"DistinctGenerators",
								getObjectLabel(pAnnotatedModel, context) },
						new Object[] { pAnnotatedModel }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEPackage(
			PAnnotatedEPackage pAnnotatedEPackage, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(pAnnotatedEPackage,
				diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedEPackage,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(pAnnotatedEPackage,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEPackage,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEPackage, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEPackage, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedEPackage,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEPackage_AtMostOneEClass(
					pAnnotatedEPackage, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the AtMostOneEClass constraint of '<em>PAnnotated EPackage</em>
	 * '. Verifies that the package does not contain distinct PAnnotatedEClass
	 * refering to the same EClass.
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEPackage_AtMostOneEClass(
			PAnnotatedEPackage pAnnotatedEPackage, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"AtMostOneEClass",
								getObjectLabel(pAnnotatedEPackage, context) },
						new Object[] { pAnnotatedEPackage }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass(PAnnotatedEClass pAnnotatedEClass,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(pAnnotatedEClass,
				diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedEClass,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(pAnnotatedEClass,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEClass,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEClass, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedEClass,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_SameEFeatures(pAnnotatedEClass,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_EPackageCommutes(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_ProperPrimaryKey(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_EntityOrEmbeddableOrMappedSuper(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_OverriddenAreDefined(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_ProperInheritance(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_ProperDiscriminator(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_SingleTableForSingleStrategy(
					pAnnotatedEClass, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEClass_ProperPKJoin(pAnnotatedEClass,
					diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the SameEFeatures constraint of '<em>PAnnotated EClass</em>'.
	 * <!-- begin-user-doc --> Check that each of the annotated EClass features
	 * are annotated exactly once. <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_SameEFeatures(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"SameEFeatures",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the EPackageCommutes constraint of '<em>PAnnotated EClass</em>
	 * '. Verifies that the containing (if specified) PAnnotatedEPackage
	 * corresponds to the containing EPackage of the referenced EClass (if
	 * specified).
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_EPackageCommutes(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"EPackageCommutes",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ProperPrimaryKey constraint of '<em>PAnnotated EClass</em>
	 * '. <!-- begin-user-doc --> EJB3-SPEC 2.1.4 <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_ProperPrimaryKey(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"ProperPrimaryKey",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the EntityOrEmbeddableOrMappedSuper constraint of '
	 * <em>PAnnotated EClass</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_EntityOrEmbeddableOrMappedSuper(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"EntityOrEmbeddableOrMappedSuper",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the OverriddenAreDefined constraint of '
	 * <em>PAnnotated EClass</em>'. <!-- begin-user-doc --> EJB3-SPEC 9.1.10
	 * <p>
	 * Check that each overridden property is defined in a mapped superclass.
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_OverriddenAreDefined(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"OverriddenAreDefined",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ProperInheritance constraint of '<em>PAnnotated EClass</em>
	 * '. <!-- begin-user-doc --> EJB3-SPEC 9.1.27 <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_ProperInheritance(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"ProperInheritance",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ProperDiscriminator constraint of '
	 * <em>PAnnotated EClass</em>'. <!-- begin-user-doc --> EJB3-SPEC 9.1.28
	 * EJB3-SPEC 9.1.29 <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_ProperDiscriminator(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"ProperDiscriminator",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the SingleTableForSingleStrategy constraint of '
	 * <em>PAnnotated EClass</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_SingleTableForSingleStrategy(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"SingleTableForSingleStrategy",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the ProperPKJoin constraint of '<em>PAnnotated EClass</em>'.
	 * <!-- begin-user-doc --> EJB3-SPEC 9.1.30 <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEClass_ProperPKJoin(
			PAnnotatedEClass pAnnotatedEClass, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"ProperPKJoin",
								getObjectLabel(pAnnotatedEClass, context) },
						new Object[] { pAnnotatedEClass }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEStructuralFeature(
			PAnnotatedEStructuralFeature pAnnotatedEStructuralFeature,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(
				pAnnotatedEStructuralFeature, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(
					pAnnotatedEStructuralFeature, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(
					pAnnotatedEStructuralFeature, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEStructuralFeature,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEStructuralFeature,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEStructuralFeature,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(
					pAnnotatedEStructuralFeature, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEStructuralFeature, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEStructuralFeature_EClassCommutes(
					pAnnotatedEStructuralFeature, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEStructuralFeature_TransientNotAnnotated(
					pAnnotatedEStructuralFeature, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the EClassCommutes constraint of '
	 * <em>PAnnotated EStructural Feature</em>'. Verifies that the containing
	 * (if specified) PAnnotatedEClass corresponds to the containing EClass of
	 * the referenced EStructuralFeature (if specified).
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEStructuralFeature_EClassCommutes(
			PAnnotatedEStructuralFeature pAnnotatedEStructuralFeature,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"EClassCommutes",
										getObjectLabel(
												pAnnotatedEStructuralFeature,
												context) },
								new Object[] { pAnnotatedEStructuralFeature },
								context));
			}
			return false;
		}
		return validatePAnnotatedETypedElement_EClassCommutes(
				pAnnotatedEStructuralFeature, diagnostics, context);
	}

	/**
	 * Validates the TransientNotAnnotated constraint of '
	 * <em>PAnnotated EStructural Feature</em>'. <!-- begin-user-doc -->
	 * EJB3-SPEC 2.1.1 <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEStructuralFeature_TransientNotAnnotated(
			PAnnotatedEStructuralFeature pAnnotatedEStructuralFeature,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"TransientNotAnnotated",
										getObjectLabel(
												pAnnotatedEStructuralFeature,
												context) },
								new Object[] { pAnnotatedEStructuralFeature },
								context));
			}
			return false;
		}
		return validatePAnnotatedETypedElement_TransientNotAnnotated(
				pAnnotatedEStructuralFeature, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEAttribute(
			PAnnotatedEAttribute pAnnotatedEAttribute,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(
				pAnnotatedEAttribute, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedEAttribute,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(pAnnotatedEAttribute,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEAttribute,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEAttribute, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEAttribute,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedEAttribute,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEAttribute, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEStructuralFeature_EClassCommutes(
					pAnnotatedEAttribute, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEStructuralFeature_TransientNotAnnotated(
					pAnnotatedEAttribute, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEAttribute_NotAnnotatedAsBasic(
					pAnnotatedEAttribute, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEAttribute_GeneratedOnId(
					pAnnotatedEAttribute, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEAttribute_DefinedGenerator(
					pAnnotatedEAttribute, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEAttribute_IdOnEntityOrMappedSuper(
					pAnnotatedEAttribute, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the NotAnnotatedAsBasic constraint of '
	 * <em>PAnnotated EAttribute</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEAttribute_NotAnnotatedAsBasic(
			PAnnotatedEAttribute pAnnotatedEAttribute,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"NotAnnotatedAsBasic",
										getObjectLabel(pAnnotatedEAttribute,
												context) },
								new Object[] { pAnnotatedEAttribute }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the GeneratedOnId constraint of '<em>PAnnotated EAttribute</em>
	 * '. <!-- begin-user-doc --> EJB3-SPEC 9.1.9 <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEAttribute_GeneratedOnId(
			PAnnotatedEAttribute pAnnotatedEAttribute,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"GeneratedOnId",
										getObjectLabel(pAnnotatedEAttribute,
												context) },
								new Object[] { pAnnotatedEAttribute }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the DefinedGenerator constraint of '
	 * <em>PAnnotated EAttribute</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEAttribute_DefinedGenerator(
			PAnnotatedEAttribute pAnnotatedEAttribute,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"DefinedGenerator",
										getObjectLabel(pAnnotatedEAttribute,
												context) },
								new Object[] { pAnnotatedEAttribute }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the IdOnEntityOrMappedSuper constraint of '
	 * <em>PAnnotated EAttribute</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEAttribute_IdOnEntityOrMappedSuper(
			PAnnotatedEAttribute pAnnotatedEAttribute,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"IdOnEntityOrMappedSuper",
										getObjectLabel(pAnnotatedEAttribute,
												context) },
								new Object[] { pAnnotatedEAttribute }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEDataType(
			PAnnotatedEDataType pAnnotatedEDataType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(
				pAnnotatedEDataType, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedEDataType,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(pAnnotatedEDataType,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEDataType,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEDataType, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEDataType, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedEDataType,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEDataType, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEDataType_NotAnnotatedAsBasic(
					pAnnotatedEDataType, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEDataType_GeneratedOnId(
					pAnnotatedEDataType, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEDataType_DefinedGenerator(
					pAnnotatedEDataType, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEDataType_IdOnEntityOrMappedSuper(
					pAnnotatedEDataType, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the NotAnnotatedAsBasic constraint of '
	 * <em>PAnnotated EData Type</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEDataType_NotAnnotatedAsBasic(
			PAnnotatedEDataType pAnnotatedEDataType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"NotAnnotatedAsBasic",
								getObjectLabel(pAnnotatedEDataType, context) },
						new Object[] { pAnnotatedEDataType }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the GeneratedOnId constraint of '<em>PAnnotated EData Type</em>
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEDataType_GeneratedOnId(
			PAnnotatedEDataType pAnnotatedEDataType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"GeneratedOnId",
								getObjectLabel(pAnnotatedEDataType, context) },
						new Object[] { pAnnotatedEDataType }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the DefinedGenerator constraint of '
	 * <em>PAnnotated EData Type</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEDataType_DefinedGenerator(
			PAnnotatedEDataType pAnnotatedEDataType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"DefinedGenerator",
								getObjectLabel(pAnnotatedEDataType, context) },
						new Object[] { pAnnotatedEDataType }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the IdOnEntityOrMappedSuper constraint of '
	 * <em>PAnnotated EData Type</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEDataType_IdOnEntityOrMappedSuper(
			PAnnotatedEDataType pAnnotatedEDataType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add(createDiagnostic(Diagnostic.ERROR,
						DIAGNOSTIC_SOURCE, 0,
						"_UI_GenericConstraint_diagnostic", new Object[] {
								"IdOnEntityOrMappedSuper",
								getObjectLabel(pAnnotatedEDataType, context) },
						new Object[] { pAnnotatedEDataType }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this
	 * validator's diagnostics. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to
		// this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEReference(
			PAnnotatedEReference pAnnotatedEReference,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(
				pAnnotatedEReference, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedEReference,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(pAnnotatedEReference,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedEReference,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedEReference, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedEReference,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedEReference,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedEReference, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEStructuralFeature_EClassCommutes(
					pAnnotatedEReference, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEStructuralFeature_TransientNotAnnotated(
					pAnnotatedEReference, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEReference_NotAnnotatedAsEmbed(
					pAnnotatedEReference, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEReference_AssociationAgreeWithReference(
					pAnnotatedEReference, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEReference_AssociationWellFormed(
					pAnnotatedEReference, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the NotAnnotatedAsEmbed constraint of '
	 * <em>PAnnotated EReference</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEReference_NotAnnotatedAsEmbed(
			PAnnotatedEReference pAnnotatedEReference,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"NotAnnotatedAsEmbed",
										getObjectLabel(pAnnotatedEReference,
												context) },
								new Object[] { pAnnotatedEReference }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the AssociationAgreeWithReference constraint of '
	 * <em>PAnnotated EReference</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEReference_AssociationAgreeWithReference(
			PAnnotatedEReference pAnnotatedEReference,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"AssociationAgreeWithReference",
										getObjectLabel(pAnnotatedEReference,
												context) },
								new Object[] { pAnnotatedEReference }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the AssociationWellFormed constraint of '
	 * <em>PAnnotated EReference</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedEReference_AssociationWellFormed(
			PAnnotatedEReference pAnnotatedEReference,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"AssociationWellFormed",
										getObjectLabel(pAnnotatedEReference,
												context) },
								new Object[] { pAnnotatedEReference }, context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedETypedElement(
			PAnnotatedETypedElement pAnnotatedETypedElement,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(
				pAnnotatedETypedElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(pAnnotatedETypedElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(
					pAnnotatedETypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(pAnnotatedETypedElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(pAnnotatedETypedElement, diagnostics,
					context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(pAnnotatedETypedElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(pAnnotatedETypedElement,
					diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedEModelElement_PAnnotationElementCommutes(
					pAnnotatedETypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedETypedElement_EClassCommutes(
					pAnnotatedETypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validatePAnnotatedETypedElement_TransientNotAnnotated(
					pAnnotatedETypedElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the EClassCommutes constraint of '
	 * <em>PAnnotated ETyped Element</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedETypedElement_EClassCommutes(
			PAnnotatedETypedElement pAnnotatedETypedElement,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"EClassCommutes",
										getObjectLabel(pAnnotatedETypedElement,
												context) },
								new Object[] { pAnnotatedETypedElement },
								context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the TransientNotAnnotated constraint of '
	 * <em>PAnnotated ETyped Element</em>'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validatePAnnotatedETypedElement_TransientNotAnnotated(
			PAnnotatedETypedElement pAnnotatedETypedElement,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and
		// message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics
						.add(createDiagnostic(Diagnostic.ERROR,
								DIAGNOSTIC_SOURCE, 0,
								"_UI_GenericConstraint_diagnostic",
								new Object[] {
										"TransientNotAnnotated",
										getObjectLabel(pAnnotatedETypedElement,
												context) },
								new Object[] { pAnnotatedETypedElement },
								context));
			}
			return false;
		}
		return true;
	}

} // PamodelValidator
