/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbannotationValidator.java,v 1.16 2009/03/07 21:15:20 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.teneo.annotations.pannotation.util.PannotationValidator;
import org.eclipse.emf.teneo.hibernate.hbannotation.*;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cache;
import org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbannotation.DiscriminatorFormula;
import org.eclipse.emf.teneo.hibernate.hbannotation.Fetch;
import org.eclipse.emf.teneo.hibernate.hbannotation.Filter;
import org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.ForceDiscriminator;
import org.eclipse.emf.teneo.hibernate.hbannotation.Formula;
import org.eclipse.emf.teneo.hibernate.hbannotation.Generated;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbAnnotation;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Immutable;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany;
import org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery;
import org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId;
import org.eclipse.emf.teneo.hibernate.hbannotation.NotFound;
import org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction;
import org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType;
import org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.Parameter;
import org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType;
import org.eclipse.emf.teneo.hibernate.hbannotation.Proxy;
import org.eclipse.emf.teneo.hibernate.hbannotation.Type;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.Where;

/**
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc
 * -->
 * 
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage
 * @generated
 */
public class HbannotationValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final HbannotationValidator INSTANCE = new HbannotationValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.teneo.hibernate.hbannotation";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected PannotationValidator pannotationValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public HbannotationValidator() {
		super();
		pannotationValidator = PannotationValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return HbannotationPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case HbannotationPackage.HB_ANNOTATION:
				return validateHbAnnotation((HbAnnotation)value, diagnostics, context);
			case HbannotationPackage.CASCADE:
				return validateCascade((Cascade)value, diagnostics, context);
			case HbannotationPackage.COLLECTION_OF_ELEMENTS:
				return validateCollectionOfElements((CollectionOfElements)value, diagnostics, context);
			case HbannotationPackage.HB_MAP_KEY:
				return validateHbMapKey((HbMapKey)value, diagnostics, context);
			case HbannotationPackage.PARAMETER:
				return validateParameter((Parameter)value, diagnostics, context);
			case HbannotationPackage.TYPE:
				return validateType((Type)value, diagnostics, context);
			case HbannotationPackage.WHERE:
				return validateWhere((Where)value, diagnostics, context);
			case HbannotationPackage.ID_BAG:
				return validateIdBag((IdBag)value, diagnostics, context);
			case HbannotationPackage.GENERIC_GENERATOR:
				return validateGenericGenerator((GenericGenerator)value, diagnostics, context);
			case HbannotationPackage.CACHE:
				return validateCache((Cache)value, diagnostics, context);
			case HbannotationPackage.TYPE_DEF:
				return validateTypeDef((TypeDef)value, diagnostics, context);
			case HbannotationPackage.FETCH:
				return validateFetch((Fetch)value, diagnostics, context);
			case HbannotationPackage.ON_DELETE:
				return validateOnDelete((OnDelete)value, diagnostics, context);
			case HbannotationPackage.PROXY:
				return validateProxy((Proxy)value, diagnostics, context);
			case HbannotationPackage.INDEX:
				return validateIndex((Index)value, diagnostics, context);
			case HbannotationPackage.GENERATED:
				return validateGenerated((Generated)value, diagnostics, context);
			case HbannotationPackage.NAMED_QUERY:
				return validateNamedQuery((NamedQuery)value, diagnostics, context);
			case HbannotationPackage.FILTER:
				return validateFilter((Filter)value, diagnostics, context);
			case HbannotationPackage.PARAM_DEF:
				return validateParamDef((ParamDef)value, diagnostics, context);
			case HbannotationPackage.FILTER_DEF:
				return validateFilterDef((FilterDef)value, diagnostics, context);
			case HbannotationPackage.DISCRIMINATOR_FORMULA:
				return validateDiscriminatorFormula((DiscriminatorFormula)value, diagnostics, context);
			case HbannotationPackage.NATURAL_ID:
				return validateNaturalId((NaturalId)value, diagnostics, context);
			case HbannotationPackage.MAP_KEY_MANY_TO_MANY:
				return validateMapKeyManyToMany((MapKeyManyToMany)value, diagnostics, context);
			case HbannotationPackage.FORCE_DISCRIMINATOR:
				return validateForceDiscriminator((ForceDiscriminator)value, diagnostics, context);
			case HbannotationPackage.IMMUTABLE:
				return validateImmutable((Immutable)value, diagnostics, context);
			case HbannotationPackage.FORMULA:
				return validateFormula((Formula)value, diagnostics, context);
			case HbannotationPackage.NOT_FOUND:
				return validateNotFound((NotFound)value, diagnostics, context);
			case HbannotationPackage.HB_ENTITY:
				return validateHbEntity((HbEntity)value, diagnostics, context);
			case HbannotationPackage.CACHE_CONCURRENCY_STRATEGY:
				return validateCacheConcurrencyStrategy((CacheConcurrencyStrategy)value, diagnostics, context);
			case HbannotationPackage.HB_FETCH_TYPE:
				return validateHbFetchType((HbFetchType)value, diagnostics, context);
			case HbannotationPackage.ON_DELETE_ACTION:
				return validateOnDeleteAction((OnDeleteAction)value, diagnostics, context);
			case HbannotationPackage.GENERATION_TIME:
				return validateGenerationTime((GenerationTime)value, diagnostics, context);
			case HbannotationPackage.NOT_FOUND_ACTION:
				return validateNotFoundAction((NotFoundAction)value, diagnostics, context);
			case HbannotationPackage.OPTIMISTIC_LOCK_TYPE:
				return validateOptimisticLockType((OptimisticLockType)value, diagnostics, context);
			case HbannotationPackage.POLYMORPHISM_TYPE:
				return validatePolymorphismType((PolymorphismType)value, diagnostics, context);
			case HbannotationPackage.HB_CASCADE_TYPE:
				return validateHbCascadeType((HbCascadeType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbAnnotation(HbAnnotation hbAnnotation,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(hbAnnotation, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(hbAnnotation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the CompatibleEModelElementType constraint of '<em>Hb Annotation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbAnnotation_CompatibleEModelElementType(
			HbAnnotation hbAnnotation, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "CompatibleEModelElementType", getObjectLabel(hbAnnotation, context) },
						 new Object[] { hbAnnotation },
						 context));
			}
			return false;
		}
		return pannotationValidator.validatePAnnotation_CompatibleEModelElementType(hbAnnotation, diagnostics, context);
	}

	/**
	 * Validates the AnnotationIsSupported constraint of '<em>Hb Annotation</em>'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbAnnotation_AnnotationIsSupported(
			HbAnnotation hbAnnotation, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "AnnotationIsSupported", getObjectLabel(hbAnnotation, context) },
						 new Object[] { hbAnnotation },
						 context));
			}
			return false;
		}
		return pannotationValidator.validatePAnnotation_AnnotationIsSupported(hbAnnotation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCascade(Cascade cascade,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(cascade, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(cascade, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollectionOfElements(
			CollectionOfElements collectionOfElements,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(collectionOfElements, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(collectionOfElements, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbMapKey(HbMapKey hbMapKey,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(hbMapKey, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(hbMapKey, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParameter(Parameter parameter,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(parameter, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(parameter, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateType(Type type, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(type, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(type, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(type, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWhere(Where where, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(where, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(where, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(where, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(where, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(where, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(where, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(where, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(where, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(where, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIdBag(IdBag idBag, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(idBag, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(idBag, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGenericGenerator(GenericGenerator genericGenerator,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(genericGenerator, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(genericGenerator, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCache(Cache cache, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(cache, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(cache, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeDef(TypeDef typeDef,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(typeDef, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(typeDef, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFetch(Fetch fetch, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(fetch, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(fetch, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOnDelete(OnDelete onDelete,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(onDelete, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(onDelete, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProxy(Proxy proxy, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(proxy, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(proxy, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNamedQuery(NamedQuery namedQuery,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(namedQuery, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(namedQuery, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilter(Filter filter, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(filter, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(filter, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParamDef(ParamDef paramDef,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(paramDef, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(paramDef, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFilterDef(FilterDef filterDef,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(filterDef, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(filterDef, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDiscriminatorFormula(
			DiscriminatorFormula discriminatorFormula,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(discriminatorFormula, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(discriminatorFormula, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNaturalId(NaturalId naturalId,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(naturalId, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(naturalId, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMapKeyManyToMany(MapKeyManyToMany mapKeyManyToMany,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(mapKeyManyToMany, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(mapKeyManyToMany, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateForceDiscriminator(
			ForceDiscriminator forceDiscriminator, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(forceDiscriminator, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(forceDiscriminator, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateImmutable(Immutable immutable,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(immutable, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(immutable, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormula(Formula formula,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(formula, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(formula, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNotFound(NotFound notFound,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(notFound, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(notFound, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbEntity(HbEntity hbEntity,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(hbEntity, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(hbEntity, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIndex(Index index, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(index, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(index, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(index, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(index, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(index, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(index, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(index, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(index, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(index, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGenerated(Generated generated,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validate_EveryMultiplicityConforms(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_CompatibleEModelElementType(generated, diagnostics, context);
		if (result || diagnostics != null) result &= validateHbAnnotation_AnnotationIsSupported(generated, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCacheConcurrencyStrategy(
			CacheConcurrencyStrategy cacheConcurrencyStrategy,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbFetchType(HbFetchType hbFetchType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOnDeleteAction(OnDeleteAction onDeleteAction,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGenerationTime(GenerationTime generationTime,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNotFoundAction(NotFoundAction notFoundAction,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOptimisticLockType(
			OptimisticLockType optimisticLockType, DiagnosticChain diagnostics,
			Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePolymorphismType(PolymorphismType polymorphismType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHbCascadeType(HbCascadeType hbCascadeType,
			DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // HbannotationValidator
