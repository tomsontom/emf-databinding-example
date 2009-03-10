/**
 * <copyright>
 * </copyright>
 *
 * $Id: PamodelPackage.java,v 1.19 2008/12/16 20:40:18 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelFactory
 * @model kind="package"
 * @generated
 */
public interface PamodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pamodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/teneo/elver/2005/pamodel";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.elver.pamodel";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	PamodelPackage eINSTANCE = org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEModelElementImpl <em>PAnnotated EModel Element</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEModelElementImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEModelElement()
	 * @generated
	 */
	int PANNOTATED_EMODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EMODEL_ELEMENT__MODEL_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EMODEL_ELEMENT__TRANSIENT = 1;

	/**
	 * The number of structural features of the '<em>PAnnotated EModel Element</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedModelImpl <em>PAnnotated Model</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedModelImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedModel()
	 * @generated
	 */
	int PANNOTATED_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Pa EPackages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_MODEL__PA_EPACKAGES = 0;

	/**
	 * The number of structural features of the '<em>PAnnotated Model</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_MODEL_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl <em>PAnnotated EPackage</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEPackage()
	 * @generated
	 */
	int PANNOTATED_EPACKAGE = 2;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__MODEL_ELEMENT = PANNOTATED_EMODEL_ELEMENT__MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__TRANSIENT = PANNOTATED_EMODEL_ELEMENT__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Pa Model</b></em>' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__PA_MODEL = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__MODEL_EPACKAGE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pa EClasses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__PA_ECLASSES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sequence Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Table Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__TABLE_GENERATORS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Pa EData Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__PA_EDATA_TYPES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Sequence Style Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>PAnnotated EPackage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EPACKAGE_FEATURE_COUNT = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl <em>PAnnotated EClass</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEClass()
	 * @generated
	 */
	int PANNOTATED_ECLASS = 3;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__MODEL_ELEMENT = PANNOTATED_EMODEL_ELEMENT__MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__TRANSIENT = PANNOTATED_EMODEL_ELEMENT__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Pa EPackage</b></em>' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__PA_EPACKAGE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__MODEL_ECLASS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Pa EStructural Features</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Attribute Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Discriminator Column</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Discriminator Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__DISCRIMINATOR_VALUE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Embeddable</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__EMBEDDABLE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Mapped Superclass</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__MAPPED_SUPERCLASS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__ENTITY = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Id Class</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__ID_CLASS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Inheritance</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__INHERITANCE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Primary Key Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Secondary Tables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__SECONDARY_TABLES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Table</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__TABLE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Table Generator</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__TABLE_GENERATOR = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Association Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>PAnnotated EClass</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ECLASS_FEATURE_COUNT = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedETypedElementImpl <em>PAnnotated ETyped Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedETypedElementImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedETypedElement()
	 * @generated
	 */
	int PANNOTATED_ETYPED_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__MODEL_ELEMENT = PANNOTATED_EMODEL_ELEMENT__MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__TRANSIENT = PANNOTATED_EMODEL_ELEMENT__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Attribute Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__ATTRIBUTE_OVERRIDES = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Join Table</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__JOIN_TABLE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>One To Many</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__ONE_TO_MANY = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sequence Generator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__SEQUENCE_GENERATOR = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Table Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__TABLE_GENERATORS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__JOIN_COLUMNS = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT__COLUMN = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>PAnnotated ETyped Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ETYPED_ELEMENT_FEATURE_COUNT = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEStructuralFeatureImpl <em>PAnnotated EStructural Feature</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEStructuralFeatureImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEStructuralFeature()
	 * @generated
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE = 4;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ELEMENT = PANNOTATED_ETYPED_ELEMENT__MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__TRANSIENT = PANNOTATED_ETYPED_ELEMENT__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Attribute Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__ATTRIBUTE_OVERRIDES = PANNOTATED_ETYPED_ELEMENT__ATTRIBUTE_OVERRIDES;

	/**
	 * The feature id for the '<em><b>Join Table</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__JOIN_TABLE = PANNOTATED_ETYPED_ELEMENT__JOIN_TABLE;

	/**
	 * The feature id for the '<em><b>One To Many</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__ONE_TO_MANY = PANNOTATED_ETYPED_ELEMENT__ONE_TO_MANY;

	/**
	 * The feature id for the '<em><b>Sequence Generator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__SEQUENCE_GENERATOR = PANNOTATED_ETYPED_ELEMENT__SEQUENCE_GENERATOR;

	/**
	 * The feature id for the '<em><b>Table Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__TABLE_GENERATORS = PANNOTATED_ETYPED_ELEMENT__TABLE_GENERATORS;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__JOIN_COLUMNS = PANNOTATED_ETYPED_ELEMENT__JOIN_COLUMNS;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__COLUMN = PANNOTATED_ETYPED_ELEMENT__COLUMN;

	/**
	 * The feature id for the '<em><b>Pa EClass</b></em>' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__PA_ECLASS = PANNOTATED_ETYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model EStructural Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ESTRUCTURAL_FEATURE = PANNOTATED_ETYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE__FOREIGN_KEY = PANNOTATED_ETYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>PAnnotated EStructural Feature</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT = PANNOTATED_ETYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEAttributeImpl <em>PAnnotated EAttribute</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEAttributeImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEAttribute()
	 * @generated
	 */
	int PANNOTATED_EATTRIBUTE = 5;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__MODEL_ELEMENT = PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ELEMENT;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEDataTypeImpl <em>PAnnotated EData Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEDataTypeImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEDataType()
	 * @generated
	 */
	int PANNOTATED_EDATA_TYPE = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEReferenceImpl <em>PAnnotated EReference</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEReferenceImpl
	 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEReference()
	 * @generated
	 */
	int PANNOTATED_EREFERENCE = 6;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__TRANSIENT = PANNOTATED_ESTRUCTURAL_FEATURE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Attribute Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__ATTRIBUTE_OVERRIDES = PANNOTATED_ESTRUCTURAL_FEATURE__ATTRIBUTE_OVERRIDES;

	/**
	 * The feature id for the '<em><b>Join Table</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__JOIN_TABLE = PANNOTATED_ESTRUCTURAL_FEATURE__JOIN_TABLE;

	/**
	 * The feature id for the '<em><b>One To Many</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__ONE_TO_MANY = PANNOTATED_ESTRUCTURAL_FEATURE__ONE_TO_MANY;

	/**
	 * The feature id for the '<em><b>Sequence Generator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__SEQUENCE_GENERATOR = PANNOTATED_ESTRUCTURAL_FEATURE__SEQUENCE_GENERATOR;

	/**
	 * The feature id for the '<em><b>Table Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__TABLE_GENERATORS = PANNOTATED_ESTRUCTURAL_FEATURE__TABLE_GENERATORS;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__JOIN_COLUMNS = PANNOTATED_ESTRUCTURAL_FEATURE__JOIN_COLUMNS;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__COLUMN = PANNOTATED_ESTRUCTURAL_FEATURE__COLUMN;

	/**
	 * The feature id for the '<em><b>Pa EClass</b></em>' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__PA_ECLASS = PANNOTATED_ESTRUCTURAL_FEATURE__PA_ECLASS;

	/**
	 * The feature id for the '<em><b>Model EStructural Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__MODEL_ESTRUCTURAL_FEATURE = PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ESTRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__FOREIGN_KEY = PANNOTATED_ESTRUCTURAL_FEATURE__FOREIGN_KEY;

	/**
	 * The feature id for the '<em><b>Model EAttribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__MODEL_EATTRIBUTE = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Basic</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__BASIC = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Enumerated</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__ENUMERATED = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Generated Value</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__GENERATED_VALUE = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__ID = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Lob</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__LOB = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Temporal</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__TEMPORAL = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Version</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE__VERSION = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>PAnnotated EAttribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EATTRIBUTE_FEATURE_COUNT = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__MODEL_ELEMENT = PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__TRANSIENT = PANNOTATED_ESTRUCTURAL_FEATURE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Attribute Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__ATTRIBUTE_OVERRIDES = PANNOTATED_ESTRUCTURAL_FEATURE__ATTRIBUTE_OVERRIDES;

	/**
	 * The feature id for the '<em><b>Join Table</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__JOIN_TABLE = PANNOTATED_ESTRUCTURAL_FEATURE__JOIN_TABLE;

	/**
	 * The feature id for the '<em><b>One To Many</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__ONE_TO_MANY = PANNOTATED_ESTRUCTURAL_FEATURE__ONE_TO_MANY;

	/**
	 * The feature id for the '<em><b>Sequence Generator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__SEQUENCE_GENERATOR = PANNOTATED_ESTRUCTURAL_FEATURE__SEQUENCE_GENERATOR;

	/**
	 * The feature id for the '<em><b>Table Generators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__TABLE_GENERATORS = PANNOTATED_ESTRUCTURAL_FEATURE__TABLE_GENERATORS;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__JOIN_COLUMNS = PANNOTATED_ESTRUCTURAL_FEATURE__JOIN_COLUMNS;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__COLUMN = PANNOTATED_ESTRUCTURAL_FEATURE__COLUMN;

	/**
	 * The feature id for the '<em><b>Pa EClass</b></em>' container reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__PA_ECLASS = PANNOTATED_ESTRUCTURAL_FEATURE__PA_ECLASS;

	/**
	 * The feature id for the '<em><b>Model EStructural Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__MODEL_ESTRUCTURAL_FEATURE = PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ESTRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Foreign Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__FOREIGN_KEY = PANNOTATED_ESTRUCTURAL_FEATURE__FOREIGN_KEY;

	/**
	 * The feature id for the '<em><b>Model EReference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__MODEL_EREFERENCE = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Embedded</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__EMBEDDED = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Embedded Id</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__EMBEDDED_ID = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Many To Many</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__MANY_TO_MANY = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Many To One</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__MANY_TO_ONE = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Map Key</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__MAP_KEY = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>One To One</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__ONE_TO_ONE = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Order By</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__ORDER_BY = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Primary Key Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__PRIMARY_KEY_JOIN_COLUMNS = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Association Overrides</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__ASSOCIATION_OVERRIDES = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>External</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE__EXTERNAL = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>PAnnotated EReference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EREFERENCE_FEATURE_COUNT = PANNOTATED_ESTRUCTURAL_FEATURE_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__MODEL_ELEMENT = PANNOTATED_EMODEL_ELEMENT__MODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__TRANSIENT = PANNOTATED_EMODEL_ELEMENT__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Model EData Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__MODEL_EDATA_TYPE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Basic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__BASIC = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Enumerated</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__ENUMERATED = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Generated Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__GENERATED_VALUE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__ID = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Lob</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__LOB = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Temporal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__TEMPORAL = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Version</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__VERSION = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Pa EPackage</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__PA_EPACKAGE = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE__COLUMN = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>PAnnotated EData Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATED_EDATA_TYPE_FEATURE_COUNT = PANNOTATED_EMODEL_ELEMENT_FEATURE_COUNT + 10;


	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement <em>PAnnotated EModel Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EModel Element</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement
	 * @generated
	 */
	EClass getPAnnotatedEModelElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model Element</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getModelElement()
	 * @see #getPAnnotatedEModelElement()
	 * @generated
	 */
	EReference getPAnnotatedEModelElement_ModelElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getTransient <em>Transient</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Transient</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement#getTransient()
	 * @see #getPAnnotatedEModelElement()
	 * @generated
	 */
	EReference getPAnnotatedEModelElement_Transient();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel <em>PAnnotated Model</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated Model</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel
	 * @generated
	 */
	EClass getPAnnotatedModel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel#getPaEPackages <em>Pa EPackages</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pa EPackages</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel#getPaEPackages()
	 * @see #getPAnnotatedModel()
	 * @generated
	 */
	EReference getPAnnotatedModel_PaEPackages();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage <em>PAnnotated EPackage</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EPackage</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage
	 * @generated
	 */
	EClass getPAnnotatedEPackage();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaModel <em>Pa Model</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pa Model</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaModel()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_PaModel();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getModelEPackage <em>Model EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model EPackage</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getModelEPackage()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_ModelEPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaEClasses <em>Pa EClasses</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pa EClasses</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaEClasses()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_PaEClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getSequenceGenerators <em>Sequence Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sequence Generators</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getSequenceGenerators()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_SequenceGenerators();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getTableGenerators <em>Table Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Table Generators</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getTableGenerators()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_TableGenerators();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaEDataTypes <em>Pa EData Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pa EData Types</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaEDataTypes()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_PaEDataTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getSequenceStyleGenerators <em>Sequence Style Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sequence Style Generators</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getSequenceStyleGenerators()
	 * @see #getPAnnotatedEPackage()
	 * @generated
	 */
	EReference getPAnnotatedEPackage_SequenceStyleGenerators();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass <em>PAnnotated EClass</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EClass</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass
	 * @generated
	 */
	EClass getPAnnotatedEClass();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPaEPackage <em>Pa EPackage</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pa EPackage</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPaEPackage()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_PaEPackage();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getModelEClass <em>Model EClass</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model EClass</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getModelEClass()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_ModelEClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPaEStructuralFeatures <em>Pa EStructural Features</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pa EStructural Features</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPaEStructuralFeatures()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_PaEStructuralFeatures();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getAttributeOverrides <em>Attribute Overrides</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Overrides</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getAttributeOverrides()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_AttributeOverrides();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getDiscriminatorColumn <em>Discriminator Column</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Discriminator Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getDiscriminatorColumn()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_DiscriminatorColumn();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getDiscriminatorValue <em>Discriminator Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Discriminator Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getDiscriminatorValue()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_DiscriminatorValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getEmbeddable <em>Embeddable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Embeddable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getEmbeddable()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_Embeddable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getMappedSuperclass <em>Mapped Superclass</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mapped Superclass</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getMappedSuperclass()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_MappedSuperclass();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Entity</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getEntity()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_Entity();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getIdClass <em>Id Class</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Id Class</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getIdClass()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_IdClass();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getInheritance <em>Inheritance</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Inheritance</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getInheritance()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_Inheritance();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPrimaryKeyJoinColumns <em>Primary Key Join Columns</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Primary Key Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPrimaryKeyJoinColumns()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_PrimaryKeyJoinColumns();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getSecondaryTables <em>Secondary Tables</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Secondary Tables</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getSecondaryTables()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_SecondaryTables();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getTable()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_Table();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getTableGenerator <em>Table Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Table Generator</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getTableGenerator()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_TableGenerator();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getAssociationOverrides <em>Association Overrides</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Association Overrides</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getAssociationOverrides()
	 * @see #getPAnnotatedEClass()
	 * @generated
	 */
	EReference getPAnnotatedEClass_AssociationOverrides();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature <em>PAnnotated EStructural Feature</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EStructural Feature</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature
	 * @generated
	 */
	EClass getPAnnotatedEStructuralFeature();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getPaEClass <em>Pa EClass</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pa EClass</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getPaEClass()
	 * @see #getPAnnotatedEStructuralFeature()
	 * @generated
	 */
	EReference getPAnnotatedEStructuralFeature_PaEClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getModelEStructuralFeature <em>Model EStructural Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model EStructural Feature</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getModelEStructuralFeature()
	 * @see #getPAnnotatedEStructuralFeature()
	 * @generated
	 */
	EReference getPAnnotatedEStructuralFeature_ModelEStructuralFeature();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Foreign Key</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature#getForeignKey()
	 * @see #getPAnnotatedEStructuralFeature()
	 * @generated
	 */
	EReference getPAnnotatedEStructuralFeature_ForeignKey();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute <em>PAnnotated EAttribute</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EAttribute</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute
	 * @generated
	 */
	EClass getPAnnotatedEAttribute();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getModelEAttribute <em>Model EAttribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model EAttribute</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getModelEAttribute()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_ModelEAttribute();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getBasic <em>Basic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getBasic()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_Basic();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getEnumerated <em>Enumerated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumerated</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getEnumerated()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_Enumerated();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getGeneratedValue <em>Generated Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Generated Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getGeneratedValue()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_GeneratedValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Id</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getId()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_Id();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getLob <em>Lob</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lob</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getLob()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_Lob();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getTemporal <em>Temporal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Temporal</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getTemporal()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_Temporal();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Version</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute#getVersion()
	 * @see #getPAnnotatedEAttribute()
	 * @generated
	 */
	EReference getPAnnotatedEAttribute_Version();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType <em>PAnnotated EData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EData Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType
	 * @generated
	 */
	EClass getPAnnotatedEDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getModelEDataType <em>Model EData Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model EData Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getModelEDataType()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_ModelEDataType();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getBasic <em>Basic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Basic</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getBasic()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Basic();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getEnumerated <em>Enumerated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Enumerated</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getEnumerated()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Enumerated();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getGeneratedValue <em>Generated Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Generated Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getGeneratedValue()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_GeneratedValue();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Id</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getId()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Id();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getLob <em>Lob</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Lob</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getLob()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Lob();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getTemporal <em>Temporal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Temporal</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getTemporal()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Temporal();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Version</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getVersion()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Version();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getPaEPackage <em>Pa EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pa EPackage</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getPaEPackage()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_PaEPackage();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getColumn()
	 * @see #getPAnnotatedEDataType()
	 * @generated
	 */
	EReference getPAnnotatedEDataType_Column();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference <em>PAnnotated EReference</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated EReference</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference
	 * @generated
	 */
	EClass getPAnnotatedEReference();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getModelEReference <em>Model EReference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model EReference</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getModelEReference()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_ModelEReference();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getEmbedded <em>Embedded</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Embedded</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getEmbedded()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_Embedded();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getEmbeddedId <em>Embedded Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Embedded Id</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getEmbeddedId()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_EmbeddedId();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getManyToMany <em>Many To Many</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Many To Many</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getManyToMany()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_ManyToMany();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getManyToOne <em>Many To One</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Many To One</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getManyToOne()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_ManyToOne();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getMapKey <em>Map Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map Key</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getMapKey()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_MapKey();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getOneToOne <em>One To One</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>One To One</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getOneToOne()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_OneToOne();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getOrderBy <em>Order By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Order By</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getOrderBy()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_OrderBy();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getPrimaryKeyJoinColumns <em>Primary Key Join Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Primary Key Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getPrimaryKeyJoinColumns()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_PrimaryKeyJoinColumns();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getAssociationOverrides <em>Association Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Association Overrides</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getAssociationOverrides()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_AssociationOverrides();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getExternal <em>External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>External</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference#getExternal()
	 * @see #getPAnnotatedEReference()
	 * @generated
	 */
	EReference getPAnnotatedEReference_External();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement <em>PAnnotated ETyped Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotated ETyped Element</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement
	 * @generated
	 */
	EClass getPAnnotatedETypedElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getAttributeOverrides <em>Attribute Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Overrides</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getAttributeOverrides()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_AttributeOverrides();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getJoinTable <em>Join Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Join Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getJoinTable()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_JoinTable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getOneToMany <em>One To Many</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>One To Many</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getOneToMany()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_OneToMany();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getSequenceGenerator <em>Sequence Generator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sequence Generator</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getSequenceGenerator()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_SequenceGenerator();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getTableGenerators <em>Table Generators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Table Generators</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getTableGenerators()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_TableGenerators();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getJoinColumns <em>Join Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getJoinColumns()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_JoinColumns();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedETypedElement#getColumn()
	 * @see #getPAnnotatedETypedElement()
	 * @generated
	 */
	EReference getPAnnotatedETypedElement_Column();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PamodelFactory getPamodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEModelElementImpl <em>PAnnotated EModel Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEModelElementImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEModelElement()
		 * @generated
		 */
		EClass PANNOTATED_EMODEL_ELEMENT = eINSTANCE.getPAnnotatedEModelElement();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EMODEL_ELEMENT__MODEL_ELEMENT = eINSTANCE.getPAnnotatedEModelElement_ModelElement();

		/**
		 * The meta object literal for the '<em><b>Transient</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EMODEL_ELEMENT__TRANSIENT = eINSTANCE.getPAnnotatedEModelElement_Transient();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedModelImpl <em>PAnnotated Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedModelImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedModel()
		 * @generated
		 */
		EClass PANNOTATED_MODEL = eINSTANCE.getPAnnotatedModel();

		/**
		 * The meta object literal for the '<em><b>Pa EPackages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_MODEL__PA_EPACKAGES = eINSTANCE.getPAnnotatedModel_PaEPackages();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl <em>PAnnotated EPackage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEPackage()
		 * @generated
		 */
		EClass PANNOTATED_EPACKAGE = eINSTANCE.getPAnnotatedEPackage();

		/**
		 * The meta object literal for the '<em><b>Pa Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__PA_MODEL = eINSTANCE.getPAnnotatedEPackage_PaModel();

		/**
		 * The meta object literal for the '<em><b>Model EPackage</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__MODEL_EPACKAGE = eINSTANCE.getPAnnotatedEPackage_ModelEPackage();

		/**
		 * The meta object literal for the '<em><b>Pa EClasses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__PA_ECLASSES = eINSTANCE.getPAnnotatedEPackage_PaEClasses();

		/**
		 * The meta object literal for the '<em><b>Sequence Generators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS = eINSTANCE.getPAnnotatedEPackage_SequenceGenerators();

		/**
		 * The meta object literal for the '<em><b>Table Generators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__TABLE_GENERATORS = eINSTANCE.getPAnnotatedEPackage_TableGenerators();

		/**
		 * The meta object literal for the '<em><b>Pa EData Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__PA_EDATA_TYPES = eINSTANCE.getPAnnotatedEPackage_PaEDataTypes();

		/**
		 * The meta object literal for the '<em><b>Sequence Style Generators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS = eINSTANCE.getPAnnotatedEPackage_SequenceStyleGenerators();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl <em>PAnnotated EClass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEClassImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEClass()
		 * @generated
		 */
		EClass PANNOTATED_ECLASS = eINSTANCE.getPAnnotatedEClass();

		/**
		 * The meta object literal for the '<em><b>Pa EPackage</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__PA_EPACKAGE = eINSTANCE.getPAnnotatedEClass_PaEPackage();

		/**
		 * The meta object literal for the '<em><b>Model EClass</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__MODEL_ECLASS = eINSTANCE.getPAnnotatedEClass_ModelEClass();

		/**
		 * The meta object literal for the '<em><b>Pa EStructural Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__PA_ESTRUCTURAL_FEATURES = eINSTANCE.getPAnnotatedEClass_PaEStructuralFeatures();

		/**
		 * The meta object literal for the '<em><b>Attribute Overrides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__ATTRIBUTE_OVERRIDES = eINSTANCE.getPAnnotatedEClass_AttributeOverrides();

		/**
		 * The meta object literal for the '<em><b>Discriminator Column</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__DISCRIMINATOR_COLUMN = eINSTANCE.getPAnnotatedEClass_DiscriminatorColumn();

		/**
		 * The meta object literal for the '<em><b>Discriminator Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__DISCRIMINATOR_VALUE = eINSTANCE.getPAnnotatedEClass_DiscriminatorValue();

		/**
		 * The meta object literal for the '<em><b>Embeddable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__EMBEDDABLE = eINSTANCE.getPAnnotatedEClass_Embeddable();

		/**
		 * The meta object literal for the '<em><b>Mapped Superclass</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__MAPPED_SUPERCLASS = eINSTANCE.getPAnnotatedEClass_MappedSuperclass();

		/**
		 * The meta object literal for the '<em><b>Entity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__ENTITY = eINSTANCE.getPAnnotatedEClass_Entity();

		/**
		 * The meta object literal for the '<em><b>Id Class</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__ID_CLASS = eINSTANCE.getPAnnotatedEClass_IdClass();

		/**
		 * The meta object literal for the '<em><b>Inheritance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__INHERITANCE = eINSTANCE.getPAnnotatedEClass_Inheritance();

		/**
		 * The meta object literal for the '<em><b>Primary Key Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__PRIMARY_KEY_JOIN_COLUMNS = eINSTANCE.getPAnnotatedEClass_PrimaryKeyJoinColumns();

		/**
		 * The meta object literal for the '<em><b>Secondary Tables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__SECONDARY_TABLES = eINSTANCE.getPAnnotatedEClass_SecondaryTables();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__TABLE = eINSTANCE.getPAnnotatedEClass_Table();

		/**
		 * The meta object literal for the '<em><b>Table Generator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__TABLE_GENERATOR = eINSTANCE.getPAnnotatedEClass_TableGenerator();

		/**
		 * The meta object literal for the '<em><b>Association Overrides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ECLASS__ASSOCIATION_OVERRIDES = eINSTANCE.getPAnnotatedEClass_AssociationOverrides();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEStructuralFeatureImpl <em>PAnnotated EStructural Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEStructuralFeatureImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEStructuralFeature()
		 * @generated
		 */
		EClass PANNOTATED_ESTRUCTURAL_FEATURE = eINSTANCE.getPAnnotatedEStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Pa EClass</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ESTRUCTURAL_FEATURE__PA_ECLASS = eINSTANCE.getPAnnotatedEStructuralFeature_PaEClass();

		/**
		 * The meta object literal for the '<em><b>Model EStructural Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ESTRUCTURAL_FEATURE__MODEL_ESTRUCTURAL_FEATURE = eINSTANCE.getPAnnotatedEStructuralFeature_ModelEStructuralFeature();

		/**
		 * The meta object literal for the '<em><b>Foreign Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ESTRUCTURAL_FEATURE__FOREIGN_KEY = eINSTANCE.getPAnnotatedEStructuralFeature_ForeignKey();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEAttributeImpl <em>PAnnotated EAttribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEAttributeImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEAttribute()
		 * @generated
		 */
		EClass PANNOTATED_EATTRIBUTE = eINSTANCE.getPAnnotatedEAttribute();

		/**
		 * The meta object literal for the '<em><b>Model EAttribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__MODEL_EATTRIBUTE = eINSTANCE.getPAnnotatedEAttribute_ModelEAttribute();

		/**
		 * The meta object literal for the '<em><b>Basic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__BASIC = eINSTANCE.getPAnnotatedEAttribute_Basic();

		/**
		 * The meta object literal for the '<em><b>Enumerated</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__ENUMERATED = eINSTANCE.getPAnnotatedEAttribute_Enumerated();

		/**
		 * The meta object literal for the '<em><b>Generated Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__GENERATED_VALUE = eINSTANCE.getPAnnotatedEAttribute_GeneratedValue();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__ID = eINSTANCE.getPAnnotatedEAttribute_Id();

		/**
		 * The meta object literal for the '<em><b>Lob</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__LOB = eINSTANCE.getPAnnotatedEAttribute_Lob();

		/**
		 * The meta object literal for the '<em><b>Temporal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__TEMPORAL = eINSTANCE.getPAnnotatedEAttribute_Temporal();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EATTRIBUTE__VERSION = eINSTANCE.getPAnnotatedEAttribute_Version();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEDataTypeImpl <em>PAnnotated EData Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEDataTypeImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEDataType()
		 * @generated
		 */
		EClass PANNOTATED_EDATA_TYPE = eINSTANCE.getPAnnotatedEDataType();

		/**
		 * The meta object literal for the '<em><b>Model EData Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__MODEL_EDATA_TYPE = eINSTANCE.getPAnnotatedEDataType_ModelEDataType();

		/**
		 * The meta object literal for the '<em><b>Basic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__BASIC = eINSTANCE.getPAnnotatedEDataType_Basic();

		/**
		 * The meta object literal for the '<em><b>Enumerated</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__ENUMERATED = eINSTANCE.getPAnnotatedEDataType_Enumerated();

		/**
		 * The meta object literal for the '<em><b>Generated Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__GENERATED_VALUE = eINSTANCE.getPAnnotatedEDataType_GeneratedValue();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__ID = eINSTANCE.getPAnnotatedEDataType_Id();

		/**
		 * The meta object literal for the '<em><b>Lob</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__LOB = eINSTANCE.getPAnnotatedEDataType_Lob();

		/**
		 * The meta object literal for the '<em><b>Temporal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__TEMPORAL = eINSTANCE.getPAnnotatedEDataType_Temporal();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__VERSION = eINSTANCE.getPAnnotatedEDataType_Version();

		/**
		 * The meta object literal for the '<em><b>Pa EPackage</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__PA_EPACKAGE = eINSTANCE.getPAnnotatedEDataType_PaEPackage();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EDATA_TYPE__COLUMN = eINSTANCE.getPAnnotatedEDataType_Column();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEReferenceImpl <em>PAnnotated EReference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEReferenceImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedEReference()
		 * @generated
		 */
		EClass PANNOTATED_EREFERENCE = eINSTANCE.getPAnnotatedEReference();

		/**
		 * The meta object literal for the '<em><b>Model EReference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__MODEL_EREFERENCE = eINSTANCE.getPAnnotatedEReference_ModelEReference();

		/**
		 * The meta object literal for the '<em><b>Embedded</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__EMBEDDED = eINSTANCE.getPAnnotatedEReference_Embedded();

		/**
		 * The meta object literal for the '<em><b>Embedded Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__EMBEDDED_ID = eINSTANCE.getPAnnotatedEReference_EmbeddedId();

		/**
		 * The meta object literal for the '<em><b>Many To Many</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__MANY_TO_MANY = eINSTANCE.getPAnnotatedEReference_ManyToMany();

		/**
		 * The meta object literal for the '<em><b>Many To One</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__MANY_TO_ONE = eINSTANCE.getPAnnotatedEReference_ManyToOne();

		/**
		 * The meta object literal for the '<em><b>Map Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__MAP_KEY = eINSTANCE.getPAnnotatedEReference_MapKey();

		/**
		 * The meta object literal for the '<em><b>One To One</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__ONE_TO_ONE = eINSTANCE.getPAnnotatedEReference_OneToOne();

		/**
		 * The meta object literal for the '<em><b>Order By</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__ORDER_BY = eINSTANCE.getPAnnotatedEReference_OrderBy();

		/**
		 * The meta object literal for the '<em><b>Primary Key Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__PRIMARY_KEY_JOIN_COLUMNS = eINSTANCE.getPAnnotatedEReference_PrimaryKeyJoinColumns();

		/**
		 * The meta object literal for the '<em><b>Association Overrides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__ASSOCIATION_OVERRIDES = eINSTANCE.getPAnnotatedEReference_AssociationOverrides();

		/**
		 * The meta object literal for the '<em><b>External</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_EREFERENCE__EXTERNAL = eINSTANCE.getPAnnotatedEReference_External();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedETypedElementImpl <em>PAnnotated ETyped Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedETypedElementImpl
		 * @see org.eclipse.emf.teneo.annotations.pamodel.impl.PamodelPackageImpl#getPAnnotatedETypedElement()
		 * @generated
		 */
		EClass PANNOTATED_ETYPED_ELEMENT = eINSTANCE.getPAnnotatedETypedElement();

		/**
		 * The meta object literal for the '<em><b>Attribute Overrides</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__ATTRIBUTE_OVERRIDES = eINSTANCE.getPAnnotatedETypedElement_AttributeOverrides();

		/**
		 * The meta object literal for the '<em><b>Join Table</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__JOIN_TABLE = eINSTANCE.getPAnnotatedETypedElement_JoinTable();

		/**
		 * The meta object literal for the '<em><b>One To Many</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__ONE_TO_MANY = eINSTANCE.getPAnnotatedETypedElement_OneToMany();

		/**
		 * The meta object literal for the '<em><b>Sequence Generator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__SEQUENCE_GENERATOR = eINSTANCE.getPAnnotatedETypedElement_SequenceGenerator();

		/**
		 * The meta object literal for the '<em><b>Table Generators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__TABLE_GENERATORS = eINSTANCE.getPAnnotatedETypedElement_TableGenerators();

		/**
		 * The meta object literal for the '<em><b>Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__JOIN_COLUMNS = eINSTANCE.getPAnnotatedETypedElement_JoinColumns();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATED_ETYPED_ELEMENT__COLUMN = eINSTANCE.getPAnnotatedETypedElement_Column();

}

	/**
	 * @return Returns the EReference in paElementEClass that has pAnnotationEClass as reference type.
	 *         <p>
	 *         Returns null if either paElementEClass is not the EClass of a PAnnotatedElement, or pAnnotationEClass is
	 *         not the EClass of a PAnnotation, or such a feature does not exist in paElementEClass.
	 */
	public EReference pAnnotationReference(EClass paElementEClass, EClass pAnnotationEClass);

} // PamodelPackage
