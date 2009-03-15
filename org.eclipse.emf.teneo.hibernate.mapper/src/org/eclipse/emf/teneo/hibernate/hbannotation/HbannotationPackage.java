/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbannotationPackage.java,v 1.14 2009/03/15 15:08:01 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationFactory
 * @model kind="package"
 *        annotation="teneo.mapping.source 1='http://hibernate.elver.org/'"
 * @generated
 */
public interface HbannotationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "hbannotation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/teneo/2006/HbAnnotation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.emf.teneo.hibernate";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	HbannotationPackage eINSTANCE = org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbAnnotationImpl <em>Hb Annotation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbAnnotationImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbAnnotation()
	 * @generated
	 */
	int HB_ANNOTATION = 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ANNOTATION__EMODEL_ELEMENT = PannotationPackage.PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Hb Annotation</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int HB_ANNOTATION_FEATURE_COUNT = PannotationPackage.PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.CascadeImpl <em>Cascade</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.CascadeImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCascade()
	 * @generated
	 */
	int CASCADE = 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE__VALUE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Cascade</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.CollectionOfElementsImpl <em>Collection Of Elements</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.CollectionOfElementsImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCollectionOfElements()
	 * @generated
	 */
	int COLLECTION_OF_ELEMENTS = 2;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OF_ELEMENTS__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target Element</b></em>' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OF_ELEMENTS__TARGET_ELEMENT = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fetch</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OF_ELEMENTS__FETCH = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Collection Of Elements</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COLLECTION_OF_ELEMENTS_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbMapKeyImpl <em>Hb Map Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbMapKeyImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbMapKey()
	 * @generated
	 */
	int HB_MAP_KEY = 3;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_MAP_KEY__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_MAP_KEY__COLUMNS = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Hb Map Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_MAP_KEY_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParameterImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 4;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VALUE = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 5;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__TYPE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE__PARAMETERS = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.WhereImpl <em>Where</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.WhereImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getWhere()
	 * @generated
	 */
	int WHERE = 6;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Clause</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE__CLAUSE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Where</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHERE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.IdBagImpl <em>Id Bag</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.IdBagImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getIdBag()
	 * @generated
	 */
	int ID_BAG = 7;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_BAG__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Generator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_BAG__GENERATOR = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_BAG__TYPE = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Table</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_BAG__TABLE = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Id Bag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_BAG_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.GenericGeneratorImpl <em>Generic Generator</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.GenericGeneratorImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getGenericGenerator()
	 * @generated
	 */
	int GENERIC_GENERATOR = 8;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_GENERATOR__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_GENERATOR__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_GENERATOR__STRATEGY = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_GENERATOR__PARAMETERS = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Generic Generator</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERIC_GENERATOR_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.CacheImpl <em>Cache</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.CacheImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCache()
	 * @generated
	 */
	int CACHE = 9;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__USAGE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Region</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__REGION = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Include</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE__INCLUDE = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Cache</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CACHE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeDefImpl <em>Type Def</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeDefImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getTypeDef()
	 * @generated
	 */
	int TYPE_DEF = 10;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__PARAMETERS = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__TYPE_CLASS = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Type Def</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FetchImpl <em>Fetch</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FetchImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFetch()
	 * @generated
	 */
	int FETCH = 11;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FETCH__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FETCH__VALUE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Fetch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FETCH_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.OnDeleteImpl <em>On Delete</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.OnDeleteImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getOnDelete()
	 * @generated
	 */
	int ON_DELETE = 12;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_DELETE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_DELETE__ACTION = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>On Delete</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_DELETE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ProxyImpl <em>Proxy</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ProxyImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getProxy()
	 * @generated
	 */
	int PROXY = 13;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Proxy Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__PROXY_CLASS = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lazy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY__LAZY = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROXY_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.IndexImpl <em>Index</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.IndexImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getIndex()
	 * @generated
	 */
	int INDEX = 14;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Index</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDEX_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.GeneratedImpl <em>Generated</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.GeneratedImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getGenerated()
	 * @generated
	 */
	int GENERATED = 15;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATED__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATED__VALUE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generated</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATED_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.NamedQueryImpl <em>Named Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.NamedQueryImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNamedQuery()
	 * @generated
	 */
	int NAMED_QUERY = 16;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_QUERY__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_QUERY__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_QUERY__QUERY = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Named Query</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_QUERY_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterImpl <em>Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFilter()
	 * @generated
	 */
	int FILTER = 17;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__CONDITION = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParamDefImpl <em>Param Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParamDefImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getParamDef()
	 * @generated
	 */
	int PARAM_DEF = 18;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_DEF__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_DEF__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_DEF__TYPE = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Param Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_DEF_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl <em>Filter Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFilterDef()
	 * @generated
	 */
	int FILTER_DEF = 19;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_DEF__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_DEF__NAME = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default Condition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_DEF__DEFAULT_CONDITION = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_DEF__PARAMETERS = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Filter Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_DEF_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.DiscriminatorFormulaImpl <em>Discriminator Formula</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.DiscriminatorFormulaImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getDiscriminatorFormula()
	 * @generated
	 */
	int DISCRIMINATOR_FORMULA = 20;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_FORMULA__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_FORMULA__VALUE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Discriminator Formula</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_FORMULA_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.NaturalIdImpl <em>Natural Id</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.NaturalIdImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNaturalId()
	 * @generated
	 */
	int NATURAL_ID = 21;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_ID__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_ID__MUTABLE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Natural Id</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATURAL_ID_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.MapKeyManyToManyImpl <em>Map Key Many To Many</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.MapKeyManyToManyImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getMapKeyManyToMany()
	 * @generated
	 */
	int MAP_KEY_MANY_TO_MANY = 22;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_KEY_MANY_TO_MANY__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_KEY_MANY_TO_MANY__JOIN_COLUMNS = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Entity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_KEY_MANY_TO_MANY__TARGET_ENTITY = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Map Key Many To Many</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_KEY_MANY_TO_MANY_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ForceDiscriminatorImpl <em>Force Discriminator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ForceDiscriminatorImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getForceDiscriminator()
	 * @generated
	 */
	int FORCE_DISCRIMINATOR = 23;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORCE_DISCRIMINATOR__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Force Discriminator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORCE_DISCRIMINATOR_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ImmutableImpl <em>Immutable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ImmutableImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getImmutable()
	 * @generated
	 */
	int IMMUTABLE = 24;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMMUTABLE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Immutable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMMUTABLE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FormulaImpl <em>Formula</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FormulaImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFormula()
	 * @generated
	 */
	int FORMULA = 25;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA__VALUE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Formula</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.NotFoundImpl <em>Not Found</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.NotFoundImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNotFound()
	 * @generated
	 */
	int NOT_FOUND = 26;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_FOUND__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_FOUND__ACTION = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Not Found</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOT_FOUND_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl <em>Hb Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbEntity()
	 * @generated
	 */
	int HB_ENTITY = 27;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Dynamic Insert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__DYNAMIC_INSERT = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dynamic Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__DYNAMIC_UPDATE = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Select Before Update</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__SELECT_BEFORE_UPDATE = HB_ANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__MUTABLE = HB_ANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Persister</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__PERSISTER = HB_ANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Optimistic Lock</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__OPTIMISTIC_LOCK = HB_ANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Polymorphism</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY__POLYMORPHISM = HB_ANNOTATION_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Hb Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HB_ENTITY_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.BatchSizeImpl <em>Batch Size</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.BatchSizeImpl
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getBatchSize()
	 * @generated
	 */
	int BATCH_SIZE = 28;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_SIZE__EMODEL_ELEMENT = HB_ANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_SIZE__SIZE = HB_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Batch Size</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BATCH_SIZE_FEATURE_COUNT = HB_ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy <em>Cache Concurrency Strategy</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCacheConcurrencyStrategy()
	 * @generated
	 */
	int CACHE_CONCURRENCY_STRATEGY = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType <em>Hb Fetch Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbFetchType()
	 * @generated
	 */
	int HB_FETCH_TYPE = 30;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction <em>On Delete Action</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getOnDeleteAction()
	 * @generated
	 */
	int ON_DELETE_ACTION = 31;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime <em>Generation Time</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getGenerationTime()
	 * @generated
	 */
	int GENERATION_TIME = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction <em>Not Found Action</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNotFoundAction()
	 * @generated
	 */
	int NOT_FOUND_ACTION = 33;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType <em>Optimistic Lock Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getOptimisticLockType()
	 * @generated
	 */
	int OPTIMISTIC_LOCK_TYPE = 34;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType <em>Polymorphism Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getPolymorphismType()
	 * @generated
	 */
	int POLYMORPHISM_TYPE = 35;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType <em>Hb Cascade Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbCascadeType()
	 * @generated
	 */
	int HB_CASCADE_TYPE = 36;

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbAnnotation <em>Hb Annotation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hb Annotation</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbAnnotation
	 * @generated
	 */
	EClass getHbAnnotation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cascade <em>Cascade</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cascade</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Cascade
	 * @generated
	 */
	EClass getCascade();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cascade#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Cascade#getValue()
	 * @see #getCascade()
	 * @generated
	 */
	EAttribute getCascade_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements <em>Collection Of Elements</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Of Elements</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements
	 * @generated
	 */
	EClass getCollectionOfElements();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements#getTargetElement <em>Target Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Element</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements#getTargetElement()
	 * @see #getCollectionOfElements()
	 * @generated
	 */
	EAttribute getCollectionOfElements_TargetElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements#getFetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements#getFetch()
	 * @see #getCollectionOfElements()
	 * @generated
	 */
	EAttribute getCollectionOfElements_Fetch();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey <em>Hb Map Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hb Map Key</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey
	 * @generated
	 */
	EClass getHbMapKey();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey#getColumns <em>Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Columns</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey#getColumns()
	 * @see #getHbMapKey()
	 * @generated
	 */
	EReference getHbMapKey_Columns();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Parameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Parameter#getName()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Parameter#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Parameter#getValue()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Type <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Type#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Type#getType()
	 * @see #getType()
	 * @generated
	 */
	EAttribute getType_Type();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Type#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Type#getParameters()
	 * @see #getType()
	 * @generated
	 */
	EReference getType_Parameters();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Where <em>Where</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Where</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Where
	 * @generated
	 */
	EClass getWhere();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Where#getClause <em>Clause</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Clause</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Where#getClause()
	 * @see #getWhere()
	 * @generated
	 */
	EAttribute getWhere_Clause();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.IdBag <em>Id Bag</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Id Bag</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.IdBag
	 * @generated
	 */
	EClass getIdBag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.IdBag#getGenerator <em>Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generator</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.IdBag#getGenerator()
	 * @see #getIdBag()
	 * @generated
	 */
	EAttribute getIdBag_Generator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.IdBag#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.IdBag#getType()
	 * @see #getIdBag()
	 * @generated
	 */
	EAttribute getIdBag_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.IdBag#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.IdBag#getTable()
	 * @see #getIdBag()
	 * @generated
	 */
	EAttribute getIdBag_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator <em>Generic Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Generator</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator
	 * @generated
	 */
	EClass getGenericGenerator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator#getName()
	 * @see #getGenericGenerator()
	 * @generated
	 */
	EAttribute getGenericGenerator_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator#getStrategy()
	 * @see #getGenericGenerator()
	 * @generated
	 */
	EAttribute getGenericGenerator_Strategy();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator#getParameters()
	 * @see #getGenericGenerator()
	 * @generated
	 */
	EReference getGenericGenerator_Parameters();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache <em>Cache</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cache</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Cache
	 * @generated
	 */
	EClass getCache();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getUsage <em>Usage</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getUsage()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Usage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getRegion <em>Region</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Region</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getRegion()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Region();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getInclude <em>Include</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Include</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Cache#getInclude()
	 * @see #getCache()
	 * @generated
	 */
	EAttribute getCache_Include();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef <em>Type Def</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Def</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef
	 * @generated
	 */
	EClass getTypeDef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef#getName()
	 * @see #getTypeDef()
	 * @generated
	 */
	EAttribute getTypeDef_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef#getParameters()
	 * @see #getTypeDef()
	 * @generated
	 */
	EReference getTypeDef_Parameters();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef#getTypeClass <em>Type Class</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Class</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef#getTypeClass()
	 * @see #getTypeDef()
	 * @generated
	 */
	EAttribute getTypeDef_TypeClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Fetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Fetch
	 * @generated
	 */
	EClass getFetch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Fetch#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Fetch#getValue()
	 * @see #getFetch()
	 * @generated
	 */
	EAttribute getFetch_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete <em>On Delete</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>On Delete</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete
	 * @generated
	 */
	EClass getOnDelete();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDelete#getAction()
	 * @see #getOnDelete()
	 * @generated
	 */
	EAttribute getOnDelete_Action();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy <em>Proxy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Proxy</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Proxy
	 * @generated
	 */
	EClass getProxy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#getProxyClass <em>Proxy Class</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Proxy Class</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#getProxyClass()
	 * @see #getProxy()
	 * @generated
	 */
	EAttribute getProxy_ProxyClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#isLazy <em>Lazy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lazy</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Proxy#isLazy()
	 * @see #getProxy()
	 * @generated
	 */
	EAttribute getProxy_Lazy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Index <em>Index</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Index</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Index
	 * @generated
	 */
	EClass getIndex();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Index#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Index#getName()
	 * @see #getIndex()
	 * @generated
	 */
	EAttribute getIndex_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Generated <em>Generated</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generated</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Generated
	 * @generated
	 */
	EClass getGenerated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Generated#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Generated#getValue()
	 * @see #getGenerated()
	 * @generated
	 */
	EAttribute getGenerated_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery <em>Named Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Query</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery
	 * @generated
	 */
	EClass getNamedQuery();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery#getName()
	 * @see #getNamedQuery()
	 * @generated
	 */
	EAttribute getNamedQuery_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery#getQuery()
	 * @see #getNamedQuery()
	 * @generated
	 */
	EAttribute getNamedQuery_Query();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Filter
	 * @generated
	 */
	EClass getFilter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Filter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Filter#getName()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Filter#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Condition</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Filter#getCondition()
	 * @see #getFilter()
	 * @generated
	 */
	EAttribute getFilter_Condition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef <em>Param Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Param Def</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef
	 * @generated
	 */
	EClass getParamDef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef#getName()
	 * @see #getParamDef()
	 * @generated
	 */
	EAttribute getParamDef_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef#getType()
	 * @see #getParamDef()
	 * @generated
	 */
	EAttribute getParamDef_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef <em>Filter Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter Def</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef
	 * @generated
	 */
	EClass getFilterDef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef#getName()
	 * @see #getFilterDef()
	 * @generated
	 */
	EAttribute getFilterDef_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef#getDefaultCondition <em>Default Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Condition</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef#getDefaultCondition()
	 * @see #getFilterDef()
	 * @generated
	 */
	EAttribute getFilterDef_DefaultCondition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameters</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef#getParameters()
	 * @see #getFilterDef()
	 * @generated
	 */
	EReference getFilterDef_Parameters();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.DiscriminatorFormula <em>Discriminator Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discriminator Formula</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.DiscriminatorFormula
	 * @generated
	 */
	EClass getDiscriminatorFormula();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.DiscriminatorFormula#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.DiscriminatorFormula#getValue()
	 * @see #getDiscriminatorFormula()
	 * @generated
	 */
	EAttribute getDiscriminatorFormula_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId <em>Natural Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Natural Id</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId
	 * @generated
	 */
	EClass getNaturalId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId#isMutable <em>Mutable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mutable</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NaturalId#isMutable()
	 * @see #getNaturalId()
	 * @generated
	 */
	EAttribute getNaturalId_Mutable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany <em>Map Key Many To Many</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Key Many To Many</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany
	 * @generated
	 */
	EClass getMapKeyManyToMany();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany#getJoinColumns <em>Join Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany#getJoinColumns()
	 * @see #getMapKeyManyToMany()
	 * @generated
	 */
	EReference getMapKeyManyToMany_JoinColumns();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany#getTargetEntity <em>Target Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany#getTargetEntity()
	 * @see #getMapKeyManyToMany()
	 * @generated
	 */
	EAttribute getMapKeyManyToMany_TargetEntity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.ForceDiscriminator <em>Force Discriminator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Force Discriminator</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.ForceDiscriminator
	 * @generated
	 */
	EClass getForceDiscriminator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Immutable <em>Immutable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Immutable</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Immutable
	 * @generated
	 */
	EClass getImmutable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Formula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formula</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Formula
	 * @generated
	 */
	EClass getFormula();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.Formula#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.Formula#getValue()
	 * @see #getFormula()
	 * @generated
	 */
	EAttribute getFormula_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFound <em>Not Found</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Not Found</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFound
	 * @generated
	 */
	EClass getNotFound();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFound#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFound#getAction()
	 * @see #getNotFound()
	 * @generated
	 */
	EAttribute getNotFound_Action();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity <em>Hb Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hb Entity</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity
	 * @generated
	 */
	EClass getHbEntity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicInsert <em>Dynamic Insert</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic Insert</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicInsert()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_DynamicInsert();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicUpdate <em>Dynamic Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic Update</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isDynamicUpdate()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_DynamicUpdate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isSelectBeforeUpdate <em>Select Before Update</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Select Before Update</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isSelectBeforeUpdate()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_SelectBeforeUpdate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isMutable <em>Mutable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mutable</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#isMutable()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_Mutable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPersister <em>Persister</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Persister</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPersister()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_Persister();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getOptimisticLock <em>Optimistic Lock</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optimistic Lock</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getOptimisticLock()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_OptimisticLock();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPolymorphism <em>Polymorphism</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Polymorphism</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity#getPolymorphism()
	 * @see #getHbEntity()
	 * @generated
	 */
	EAttribute getHbEntity_Polymorphism();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize <em>Batch Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Batch Size</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize
	 * @generated
	 */
	EClass getBatchSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.BatchSize#getSize()
	 * @see #getBatchSize()
	 * @generated
	 */
	EAttribute getBatchSize_Size();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy <em>Cache Concurrency Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cache Concurrency Strategy</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy
	 * @generated
	 */
	EEnum getCacheConcurrencyStrategy();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType <em>Hb Fetch Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Hb Fetch Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType
	 * @generated
	 */
	EEnum getHbFetchType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction <em>On Delete Action</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>On Delete Action</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction
	 * @generated
	 */
	EEnum getOnDeleteAction();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime <em>Generation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generation Time</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime
	 * @generated
	 */
	EEnum getGenerationTime();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction <em>Not Found Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Not Found Action</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction
	 * @generated
	 */
	EEnum getNotFoundAction();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType <em>Optimistic Lock Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Optimistic Lock Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType
	 * @generated
	 */
	EEnum getOptimisticLockType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType <em>Polymorphism Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Polymorphism Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType
	 * @generated
	 */
	EEnum getPolymorphismType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType <em>Hb Cascade Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Hb Cascade Type</em>'.
	 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType
	 * @generated
	 */
	EEnum getHbCascadeType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	HbannotationFactory getHbannotationFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbAnnotationImpl <em>Hb Annotation</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbAnnotationImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbAnnotation()
		 * @generated
		 */
		EClass HB_ANNOTATION = eINSTANCE.getHbAnnotation();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.CascadeImpl <em>Cascade</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.CascadeImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCascade()
		 * @generated
		 */
		EClass CASCADE = eINSTANCE.getCascade();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CASCADE__VALUE = eINSTANCE.getCascade_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.CollectionOfElementsImpl <em>Collection Of Elements</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.CollectionOfElementsImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCollectionOfElements()
		 * @generated
		 */
		EClass COLLECTION_OF_ELEMENTS = eINSTANCE.getCollectionOfElements();

		/**
		 * The meta object literal for the '<em><b>Target Element</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_OF_ELEMENTS__TARGET_ELEMENT = eINSTANCE.getCollectionOfElements_TargetElement();

		/**
		 * The meta object literal for the '<em><b>Fetch</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute COLLECTION_OF_ELEMENTS__FETCH = eINSTANCE.getCollectionOfElements_Fetch();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbMapKeyImpl <em>Hb Map Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbMapKeyImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbMapKey()
		 * @generated
		 */
		EClass HB_MAP_KEY = eINSTANCE.getHbMapKey();

		/**
		 * The meta object literal for the '<em><b>Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HB_MAP_KEY__COLUMNS = eINSTANCE.getHbMapKey_Columns();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParameterImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PARAMETER__VALUE = eINSTANCE.getParameter_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TYPE__TYPE = eINSTANCE.getType_Type();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE__PARAMETERS = eINSTANCE.getType_Parameters();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.WhereImpl <em>Where</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.WhereImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getWhere()
		 * @generated
		 */
		EClass WHERE = eINSTANCE.getWhere();

		/**
		 * The meta object literal for the '<em><b>Clause</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WHERE__CLAUSE = eINSTANCE.getWhere_Clause();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.IdBagImpl <em>Id Bag</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.IdBagImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getIdBag()
		 * @generated
		 */
		EClass ID_BAG = eINSTANCE.getIdBag();

		/**
		 * The meta object literal for the '<em><b>Generator</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ID_BAG__GENERATOR = eINSTANCE.getIdBag_Generator();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ID_BAG__TYPE = eINSTANCE.getIdBag_Type();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ID_BAG__TABLE = eINSTANCE.getIdBag_Table();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.GenericGeneratorImpl <em>Generic Generator</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.GenericGeneratorImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getGenericGenerator()
		 * @generated
		 */
		EClass GENERIC_GENERATOR = eINSTANCE.getGenericGenerator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERIC_GENERATOR__NAME = eINSTANCE.getGenericGenerator_Name();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute GENERIC_GENERATOR__STRATEGY = eINSTANCE.getGenericGenerator_Strategy();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference GENERIC_GENERATOR__PARAMETERS = eINSTANCE.getGenericGenerator_Parameters();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.CacheImpl <em>Cache</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.CacheImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCache()
		 * @generated
		 */
		EClass CACHE = eINSTANCE.getCache();

		/**
		 * The meta object literal for the '<em><b>Usage</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CACHE__USAGE = eINSTANCE.getCache_Usage();

		/**
		 * The meta object literal for the '<em><b>Region</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CACHE__REGION = eINSTANCE.getCache_Region();

		/**
		 * The meta object literal for the '<em><b>Include</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CACHE__INCLUDE = eINSTANCE.getCache_Include();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeDefImpl <em>Type Def</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.TypeDefImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getTypeDef()
		 * @generated
		 */
		EClass TYPE_DEF = eINSTANCE.getTypeDef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TYPE_DEF__NAME = eINSTANCE.getTypeDef_Name();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DEF__PARAMETERS = eINSTANCE.getTypeDef_Parameters();

		/**
		 * The meta object literal for the '<em><b>Type Class</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TYPE_DEF__TYPE_CLASS = eINSTANCE.getTypeDef_TypeClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FetchImpl <em>Fetch</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FetchImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFetch()
		 * @generated
		 */
		EClass FETCH = eINSTANCE.getFetch();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute FETCH__VALUE = eINSTANCE.getFetch_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.OnDeleteImpl <em>On Delete</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.OnDeleteImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getOnDelete()
		 * @generated
		 */
		EClass ON_DELETE = eINSTANCE.getOnDelete();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ON_DELETE__ACTION = eINSTANCE.getOnDelete_Action();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ProxyImpl <em>Proxy</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ProxyImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getProxy()
		 * @generated
		 */
		EClass PROXY = eINSTANCE.getProxy();

		/**
		 * The meta object literal for the '<em><b>Proxy Class</b></em>' attribute feature.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROXY__PROXY_CLASS = eINSTANCE.getProxy_ProxyClass();

		/**
		 * The meta object literal for the '<em><b>Lazy</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROXY__LAZY = eINSTANCE.getProxy_Lazy();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.IndexImpl <em>Index</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.IndexImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getIndex()
		 * @generated
		 */
		EClass INDEX = eINSTANCE.getIndex();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute INDEX__NAME = eINSTANCE.getIndex_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.GeneratedImpl <em>Generated</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.GeneratedImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getGenerated()
		 * @generated
		 */
		EClass GENERATED = eINSTANCE.getGenerated();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATED__VALUE = eINSTANCE.getGenerated_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.NamedQueryImpl <em>Named Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.NamedQueryImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNamedQuery()
		 * @generated
		 */
		EClass NAMED_QUERY = eINSTANCE.getNamedQuery();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_QUERY__NAME = eINSTANCE.getNamedQuery_Name();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_QUERY__QUERY = eINSTANCE.getNamedQuery_Query();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterImpl <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFilter()
		 * @generated
		 */
		EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__NAME = eINSTANCE.getFilter_Name();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER__CONDITION = eINSTANCE.getFilter_Condition();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParamDefImpl <em>Param Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ParamDefImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getParamDef()
		 * @generated
		 */
		EClass PARAM_DEF = eINSTANCE.getParamDef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAM_DEF__NAME = eINSTANCE.getParamDef_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAM_DEF__TYPE = eINSTANCE.getParamDef_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl <em>Filter Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FilterDefImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFilterDef()
		 * @generated
		 */
		EClass FILTER_DEF = eINSTANCE.getFilterDef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_DEF__NAME = eINSTANCE.getFilterDef_Name();

		/**
		 * The meta object literal for the '<em><b>Default Condition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILTER_DEF__DEFAULT_CONDITION = eINSTANCE.getFilterDef_DefaultCondition();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER_DEF__PARAMETERS = eINSTANCE.getFilterDef_Parameters();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.DiscriminatorFormulaImpl <em>Discriminator Formula</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.DiscriminatorFormulaImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getDiscriminatorFormula()
		 * @generated
		 */
		EClass DISCRIMINATOR_FORMULA = eINSTANCE.getDiscriminatorFormula();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCRIMINATOR_FORMULA__VALUE = eINSTANCE.getDiscriminatorFormula_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.NaturalIdImpl <em>Natural Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.NaturalIdImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNaturalId()
		 * @generated
		 */
		EClass NATURAL_ID = eINSTANCE.getNaturalId();

		/**
		 * The meta object literal for the '<em><b>Mutable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NATURAL_ID__MUTABLE = eINSTANCE.getNaturalId_Mutable();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.MapKeyManyToManyImpl <em>Map Key Many To Many</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.MapKeyManyToManyImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getMapKeyManyToMany()
		 * @generated
		 */
		EClass MAP_KEY_MANY_TO_MANY = eINSTANCE.getMapKeyManyToMany();

		/**
		 * The meta object literal for the '<em><b>Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_KEY_MANY_TO_MANY__JOIN_COLUMNS = eINSTANCE.getMapKeyManyToMany_JoinColumns();

		/**
		 * The meta object literal for the '<em><b>Target Entity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP_KEY_MANY_TO_MANY__TARGET_ENTITY = eINSTANCE.getMapKeyManyToMany_TargetEntity();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ForceDiscriminatorImpl <em>Force Discriminator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ForceDiscriminatorImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getForceDiscriminator()
		 * @generated
		 */
		EClass FORCE_DISCRIMINATOR = eINSTANCE.getForceDiscriminator();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.ImmutableImpl <em>Immutable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.ImmutableImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getImmutable()
		 * @generated
		 */
		EClass IMMUTABLE = eINSTANCE.getImmutable();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.FormulaImpl <em>Formula</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.FormulaImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getFormula()
		 * @generated
		 */
		EClass FORMULA = eINSTANCE.getFormula();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMULA__VALUE = eINSTANCE.getFormula_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.NotFoundImpl <em>Not Found</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.NotFoundImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNotFound()
		 * @generated
		 */
		EClass NOT_FOUND = eINSTANCE.getNotFound();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOT_FOUND__ACTION = eINSTANCE.getNotFound_Action();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl <em>Hb Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbEntityImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbEntity()
		 * @generated
		 */
		EClass HB_ENTITY = eINSTANCE.getHbEntity();

		/**
		 * The meta object literal for the '<em><b>Dynamic Insert</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__DYNAMIC_INSERT = eINSTANCE.getHbEntity_DynamicInsert();

		/**
		 * The meta object literal for the '<em><b>Dynamic Update</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__DYNAMIC_UPDATE = eINSTANCE.getHbEntity_DynamicUpdate();

		/**
		 * The meta object literal for the '<em><b>Select Before Update</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__SELECT_BEFORE_UPDATE = eINSTANCE.getHbEntity_SelectBeforeUpdate();

		/**
		 * The meta object literal for the '<em><b>Mutable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__MUTABLE = eINSTANCE.getHbEntity_Mutable();

		/**
		 * The meta object literal for the '<em><b>Persister</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__PERSISTER = eINSTANCE.getHbEntity_Persister();

		/**
		 * The meta object literal for the '<em><b>Optimistic Lock</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__OPTIMISTIC_LOCK = eINSTANCE.getHbEntity_OptimisticLock();

		/**
		 * The meta object literal for the '<em><b>Polymorphism</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HB_ENTITY__POLYMORPHISM = eINSTANCE.getHbEntity_Polymorphism();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.impl.BatchSizeImpl <em>Batch Size</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.BatchSizeImpl
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getBatchSize()
		 * @generated
		 */
		EClass BATCH_SIZE = eINSTANCE.getBatchSize();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BATCH_SIZE__SIZE = eINSTANCE.getBatchSize_Size();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy <em>Cache Concurrency Strategy</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getCacheConcurrencyStrategy()
		 * @generated
		 */
		EEnum CACHE_CONCURRENCY_STRATEGY = eINSTANCE.getCacheConcurrencyStrategy();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType <em>Hb Fetch Type</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbFetchType()
		 * @generated
		 */
		EEnum HB_FETCH_TYPE = eINSTANCE.getHbFetchType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction <em>On Delete Action</em>}' enum.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getOnDeleteAction()
		 * @generated
		 */
		EEnum ON_DELETE_ACTION = eINSTANCE.getOnDeleteAction();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime <em>Generation Time</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getGenerationTime()
		 * @generated
		 */
		EEnum GENERATION_TIME = eINSTANCE.getGenerationTime();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction <em>Not Found Action</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.NotFoundAction
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getNotFoundAction()
		 * @generated
		 */
		EEnum NOT_FOUND_ACTION = eINSTANCE.getNotFoundAction();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType <em>Optimistic Lock Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.OptimisticLockType
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getOptimisticLockType()
		 * @generated
		 */
		EEnum OPTIMISTIC_LOCK_TYPE = eINSTANCE.getOptimisticLockType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType <em>Polymorphism Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.PolymorphismType
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getPolymorphismType()
		 * @generated
		 */
		EEnum POLYMORPHISM_TYPE = eINSTANCE.getPolymorphismType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType <em>Hb Cascade Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType
		 * @see org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationPackageImpl#getHbCascadeType()
		 * @generated
		 */
		EEnum HB_CASCADE_TYPE = eINSTANCE.getHbCascadeType();

	}

} // HbannotationPackage
