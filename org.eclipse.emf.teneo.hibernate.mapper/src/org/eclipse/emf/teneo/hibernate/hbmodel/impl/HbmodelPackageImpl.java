/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbmodelPackageImpl.java,v 1.13 2009/03/07 21:15:19 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;

import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;

import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;

import org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl;

import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEModelElement;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelFactory;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HbmodelPackageImpl extends EPackageImpl implements HbmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedETypeElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedEAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedEClassEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedEModelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedEPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedEReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hbAnnotatedEDataTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HbmodelPackageImpl() {
		super(eNS_URI, HbmodelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HbmodelPackage init() {
		if (isInited) return (HbmodelPackage)EPackage.Registry.INSTANCE.getEPackage(HbmodelPackage.eNS_URI);

		// Obtain or create and register package
		HbmodelPackageImpl theHbmodelPackage = (HbmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof HbmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new HbmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PamodelPackage.eINSTANCE.eClass();
		PannotationPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		HbannotationPackageImpl theHbannotationPackage = (HbannotationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(HbannotationPackage.eNS_URI) instanceof HbannotationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(HbannotationPackage.eNS_URI) : HbannotationPackage.eINSTANCE);

		// Create package meta-data objects
		theHbmodelPackage.createPackageContents();
		theHbannotationPackage.createPackageContents();

		// Initialize created meta-data
		theHbmodelPackage.initializePackageContents();
		theHbannotationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theHbmodelPackage.freeze();

		return theHbmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedETypeElement() {
		return hbAnnotatedETypeElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbWhere() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbCollectionOfElements() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbMapKey() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbColumns() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbCascade() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbIdBag() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_HbIndex() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_Filter() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_MapKeyManyToMany() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedETypeElement_Formula() {
		return (EReference)hbAnnotatedETypeElementEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedEAttribute() {
		return hbAnnotatedEAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEAttribute_HbType() {
		return (EReference)hbAnnotatedEAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEAttribute_Generated() {
		return (EReference)hbAnnotatedEAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEAttribute_NaturalId() {
		return (EReference)hbAnnotatedEAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedEClass() {
		return hbAnnotatedEClassEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_HbCache() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_HbOnDelete() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_HbWhere() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_HbProxy() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_HbNamedQuery() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_FilterDef() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_Filter() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_DiscriminatorFormula() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_ForceDiscriminator() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_Immutable() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEClass_HbEntity() {
		return (EReference)hbAnnotatedEClassEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedEModelElement() {
		return hbAnnotatedEModelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedEPackage() {
		return hbAnnotatedEPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEPackage_HbGenericGenerators() {
		return (EReference)hbAnnotatedEPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEPackage_HbTypeDef() {
		return (EReference)hbAnnotatedEPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEPackage_HbNamedQuery() {
		return (EReference)hbAnnotatedEPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEPackage_FilterDef() {
		return (EReference)hbAnnotatedEPackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedEReference() {
		return hbAnnotatedEReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_HbCache() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_HbFetch() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_HbOnDelete() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_NaturalId() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_Immutable() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_NotFound() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEReference_HbType() {
		return (EReference)hbAnnotatedEReferenceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHbAnnotatedEDataType() {
		return hbAnnotatedEDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEDataType_HbTypeDef() {
		return (EReference)hbAnnotatedEDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEDataType_HbWhere() {
		return (EReference)hbAnnotatedEDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEDataType_HbColumns() {
		return (EReference)hbAnnotatedEDataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEDataType_HbIdBag() {
		return (EReference)hbAnnotatedEDataTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getHbAnnotatedEDataType_HbIndex() {
		return (EReference)hbAnnotatedEDataTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HbmodelFactory getHbmodelFactory() {
		return (HbmodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		hbAnnotatedETypeElementEClass = createEClass(HB_ANNOTATED_ETYPE_ELEMENT);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_WHERE);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_COLLECTION_OF_ELEMENTS);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_MAP_KEY);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_COLUMNS);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_CASCADE);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_ID_BAG);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__HB_INDEX);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__FILTER);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__MAP_KEY_MANY_TO_MANY);
		createEReference(hbAnnotatedETypeElementEClass, HB_ANNOTATED_ETYPE_ELEMENT__FORMULA);

		hbAnnotatedEAttributeEClass = createEClass(HB_ANNOTATED_EATTRIBUTE);
		createEReference(hbAnnotatedEAttributeEClass, HB_ANNOTATED_EATTRIBUTE__HB_TYPE);
		createEReference(hbAnnotatedEAttributeEClass, HB_ANNOTATED_EATTRIBUTE__GENERATED);
		createEReference(hbAnnotatedEAttributeEClass, HB_ANNOTATED_EATTRIBUTE__NATURAL_ID);

		hbAnnotatedEClassEClass = createEClass(HB_ANNOTATED_ECLASS);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__HB_CACHE);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__HB_ON_DELETE);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__HB_WHERE);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__HB_PROXY);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__HB_NAMED_QUERY);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__FILTER_DEF);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__FILTER);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__DISCRIMINATOR_FORMULA);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__FORCE_DISCRIMINATOR);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__IMMUTABLE);
		createEReference(hbAnnotatedEClassEClass, HB_ANNOTATED_ECLASS__HB_ENTITY);

		hbAnnotatedEModelElementEClass = createEClass(HB_ANNOTATED_EMODEL_ELEMENT);

		hbAnnotatedEPackageEClass = createEClass(HB_ANNOTATED_EPACKAGE);
		createEReference(hbAnnotatedEPackageEClass, HB_ANNOTATED_EPACKAGE__HB_GENERIC_GENERATORS);
		createEReference(hbAnnotatedEPackageEClass, HB_ANNOTATED_EPACKAGE__HB_TYPE_DEF);
		createEReference(hbAnnotatedEPackageEClass, HB_ANNOTATED_EPACKAGE__HB_NAMED_QUERY);
		createEReference(hbAnnotatedEPackageEClass, HB_ANNOTATED_EPACKAGE__FILTER_DEF);

		hbAnnotatedEReferenceEClass = createEClass(HB_ANNOTATED_EREFERENCE);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__HB_CACHE);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__HB_FETCH);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__HB_ON_DELETE);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__NATURAL_ID);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__IMMUTABLE);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__NOT_FOUND);
		createEReference(hbAnnotatedEReferenceEClass, HB_ANNOTATED_EREFERENCE__HB_TYPE);

		hbAnnotatedEDataTypeEClass = createEClass(HB_ANNOTATED_EDATA_TYPE);
		createEReference(hbAnnotatedEDataTypeEClass, HB_ANNOTATED_EDATA_TYPE__HB_TYPE_DEF);
		createEReference(hbAnnotatedEDataTypeEClass, HB_ANNOTATED_EDATA_TYPE__HB_WHERE);
		createEReference(hbAnnotatedEDataTypeEClass, HB_ANNOTATED_EDATA_TYPE__HB_COLUMNS);
		createEReference(hbAnnotatedEDataTypeEClass, HB_ANNOTATED_EDATA_TYPE__HB_ID_BAG);
		createEReference(hbAnnotatedEDataTypeEClass, HB_ANNOTATED_EDATA_TYPE__HB_INDEX);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PamodelPackage thePamodelPackage = (PamodelPackage)EPackage.Registry.INSTANCE.getEPackage(PamodelPackage.eNS_URI);
		HbannotationPackage theHbannotationPackage = (HbannotationPackage)EPackage.Registry.INSTANCE.getEPackage(HbannotationPackage.eNS_URI);
		PannotationPackage thePannotationPackage = (PannotationPackage)EPackage.Registry.INSTANCE.getEPackage(PannotationPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		hbAnnotatedETypeElementEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEStructuralFeature());
		hbAnnotatedETypeElementEClass.getESuperTypes().add(this.getHbAnnotatedEModelElement());
		hbAnnotatedEAttributeEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEAttribute());
		hbAnnotatedEAttributeEClass.getESuperTypes().add(this.getHbAnnotatedETypeElement());
		hbAnnotatedEClassEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEClass());
		hbAnnotatedEModelElementEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEModelElement());
		hbAnnotatedEPackageEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEPackage());
		hbAnnotatedEReferenceEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEReference());
		hbAnnotatedEReferenceEClass.getESuperTypes().add(this.getHbAnnotatedETypeElement());
		hbAnnotatedEDataTypeEClass.getESuperTypes().add(thePamodelPackage.getPAnnotatedEDataType());
		hbAnnotatedEDataTypeEClass.getESuperTypes().add(this.getHbAnnotatedEModelElement());

		// Initialize classes and features; add operations and parameters
		initEClass(hbAnnotatedETypeElementEClass, HbAnnotatedETypeElement.class, "HbAnnotatedETypeElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHbAnnotatedETypeElement_HbWhere(), theHbannotationPackage.getWhere(), null, "hbWhere", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_HbCollectionOfElements(), theHbannotationPackage.getCollectionOfElements(), null, "hbCollectionOfElements", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_HbMapKey(), theHbannotationPackage.getHbMapKey(), null, "hbMapKey", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_HbColumns(), thePannotationPackage.getColumn(), null, "hbColumns", null, 0, -1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_HbCascade(), theHbannotationPackage.getCascade(), null, "hbCascade", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_HbIdBag(), theHbannotationPackage.getIdBag(), null, "hbIdBag", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_HbIndex(), theHbannotationPackage.getIndex(), null, "hbIndex", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_Filter(), theHbannotationPackage.getFilter(), null, "Filter", null, 0, -1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_MapKeyManyToMany(), theHbannotationPackage.getMapKeyManyToMany(), null, "mapKeyManyToMany", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedETypeElement_Formula(), theHbannotationPackage.getFormula(), null, "formula", null, 0, 1, HbAnnotatedETypeElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hbAnnotatedEAttributeEClass, HbAnnotatedEAttribute.class, "HbAnnotatedEAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHbAnnotatedEAttribute_HbType(), theHbannotationPackage.getType(), null, "hbType", null, 0, 1, HbAnnotatedEAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEAttribute_Generated(), theHbannotationPackage.getGenerated(), null, "generated", null, 0, 1, HbAnnotatedEAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEAttribute_NaturalId(), theHbannotationPackage.getNaturalId(), null, "naturalId", null, 0, 1, HbAnnotatedEAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hbAnnotatedEClassEClass, HbAnnotatedEClass.class, "HbAnnotatedEClass", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHbAnnotatedEClass_HbCache(), theHbannotationPackage.getCache(), null, "hbCache", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_HbOnDelete(), theHbannotationPackage.getOnDelete(), null, "hbOnDelete", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_HbWhere(), theHbannotationPackage.getWhere(), null, "hbWhere", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_HbProxy(), theHbannotationPackage.getProxy(), null, "hbProxy", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_HbNamedQuery(), theHbannotationPackage.getNamedQuery(), null, "hbNamedQuery", null, 0, -1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_FilterDef(), theHbannotationPackage.getFilterDef(), null, "FilterDef", null, 0, -1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_Filter(), theHbannotationPackage.getFilter(), null, "Filter", null, 0, -1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_DiscriminatorFormula(), theHbannotationPackage.getDiscriminatorFormula(), null, "discriminatorFormula", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_ForceDiscriminator(), theHbannotationPackage.getForceDiscriminator(), null, "forceDiscriminator", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_Immutable(), theHbannotationPackage.getImmutable(), null, "immutable", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEClass_HbEntity(), theHbannotationPackage.getHbEntity(), null, "hbEntity", null, 0, 1, HbAnnotatedEClass.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hbAnnotatedEModelElementEClass, HbAnnotatedEModelElement.class, "HbAnnotatedEModelElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(hbAnnotatedEPackageEClass, HbAnnotatedEPackage.class, "HbAnnotatedEPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHbAnnotatedEPackage_HbGenericGenerators(), theHbannotationPackage.getGenericGenerator(), null, "hbGenericGenerators", null, 0, -1, HbAnnotatedEPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEPackage_HbTypeDef(), theHbannotationPackage.getTypeDef(), null, "hbTypeDef", null, 0, -1, HbAnnotatedEPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEPackage_HbNamedQuery(), theHbannotationPackage.getNamedQuery(), null, "hbNamedQuery", null, 0, -1, HbAnnotatedEPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEPackage_FilterDef(), theHbannotationPackage.getFilterDef(), null, "FilterDef", null, 0, -1, HbAnnotatedEPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hbAnnotatedEReferenceEClass, HbAnnotatedEReference.class, "HbAnnotatedEReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHbAnnotatedEReference_HbCache(), theHbannotationPackage.getCache(), null, "hbCache", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEReference_HbFetch(), theHbannotationPackage.getFetch(), null, "hbFetch", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEReference_HbOnDelete(), theHbannotationPackage.getOnDelete(), null, "hbOnDelete", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEReference_NaturalId(), theHbannotationPackage.getNaturalId(), null, "naturalId", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEReference_Immutable(), theHbannotationPackage.getImmutable(), null, "immutable", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEReference_NotFound(), theHbannotationPackage.getNotFound(), null, "notFound", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEReference_HbType(), theHbannotationPackage.getType(), null, "hbType", null, 0, 1, HbAnnotatedEReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hbAnnotatedEDataTypeEClass, HbAnnotatedEDataType.class, "HbAnnotatedEDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHbAnnotatedEDataType_HbTypeDef(), theHbannotationPackage.getTypeDef(), null, "hbTypeDef", null, 0, 1, HbAnnotatedEDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEDataType_HbWhere(), theHbannotationPackage.getWhere(), null, "hbWhere", null, 0, 1, HbAnnotatedEDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEDataType_HbColumns(), thePannotationPackage.getColumn(), null, "hbColumns", null, 0, -1, HbAnnotatedEDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEDataType_HbIdBag(), theHbannotationPackage.getIdBag(), null, "hbIdBag", null, 0, 1, HbAnnotatedEDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHbAnnotatedEDataType_HbIndex(), theHbannotationPackage.getIndex(), null, "hbIndex", null, 0, 1, HbAnnotatedEDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// teneo/internal/PersistenceMapping
		createPersistenceMappingAnnotations();
	}

	/**
	 * Initializes the annotations for <b>teneo/internal/PersistenceMapping</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createPersistenceMappingAnnotations() {
		String source = "teneo/internal/PersistenceMapping";		
		addAnnotation
		  (getHbAnnotatedETypeElement_HbMapKey(), 
		   source, 
		   new String[] {
			 "elementName", "hb-map-key"
		   });		
		addAnnotation
		  (getHbAnnotatedETypeElement_MapKeyManyToMany(), 
		   source, 
		   new String[] {
			 "elementName", "hb-map-key"
		   });
	}

} //HbmodelPackageImpl
