/**
 * <copyright>
 * </copyright>
 *
 * $Id: PannotationPackage.java,v 1.17 2008/12/16 20:40:18 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2004/EmfaticAnnotationMap Target='teneo/internal/Target' Unsupported='teneo/internal/Unsupported'"
 *        annotation="teneo.mapping.source 0='http://annotation.elver.org/' 1='http://ejb.elver.org/'"
 * @generated
 */
public interface PannotationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pannotation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/teneo/elver/2005/PAnnotation";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.elver.annotation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	PannotationPackage eINSTANCE = org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.PAnnotationImpl <em>PAnnotation</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PAnnotationImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getPAnnotation()
	 * @generated
	 */
	int PANNOTATION = 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PANNOTATION__EMODEL_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>PAnnotation</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PANNOTATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.AttributeOverrideImpl <em>Attribute Override</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.AttributeOverrideImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getAttributeOverride()
	 * @generated
	 */
	int ATTRIBUTE_OVERRIDE = 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OVERRIDE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OVERRIDE__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Column</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OVERRIDE__COLUMN = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Override</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OVERRIDE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.BasicImpl <em>Basic</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.BasicImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getBasic()
	 * @generated
	 */
	int BASIC = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ColumnImpl <em>Column</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ColumnImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getColumn()
	 * @generated
	 */
	int COLUMN = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorColumnImpl <em>Discriminator Column</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorColumnImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getDiscriminatorColumn()
	 * @generated
	 */
	int DISCRIMINATOR_COLUMN = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorValueImpl <em>Discriminator Value</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorValueImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getDiscriminatorValue()
	 * @generated
	 */
	int DISCRIMINATOR_VALUE = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddableImpl <em>Embeddable</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddableImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEmbeddable()
	 * @generated
	 */
	int EMBEDDABLE = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.MappedSuperclassImpl <em>Mapped Superclass</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.MappedSuperclassImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getMappedSuperclass()
	 * @generated
	 */
	int MAPPED_SUPERCLASS = 22;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedImpl <em>Embedded</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEmbedded()
	 * @generated
	 */
	int EMBEDDED = 8;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedIdImpl <em>Embedded Id</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedIdImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEmbeddedId()
	 * @generated
	 */
	int EMBEDDED_ID = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EnumeratedImpl <em>Enumerated</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EnumeratedImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEnumerated()
	 * @generated
	 */
	int ENUMERATED = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EntityImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.GeneratedValueImpl <em>Generated Value</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.GeneratedValueImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getGeneratedValue()
	 * @generated
	 */
	int GENERATED_VALUE = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.IdImpl <em>Id</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.IdImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getId()
	 * @generated
	 */
	int ID = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.IdClassImpl <em>Id Class</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.IdClassImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getIdClass()
	 * @generated
	 */
	int ID_CLASS = 14;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.InheritanceImpl <em>Inheritance</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.InheritanceImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getInheritance()
	 * @generated
	 */
	int INHERITANCE = 15;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinColumnImpl <em>Join Column</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.JoinColumnImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getJoinColumn()
	 * @generated
	 */
	int JOIN_COLUMN = 16;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl <em>Join Table</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getJoinTable()
	 * @generated
	 */
	int JOIN_TABLE = 17;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.LobImpl <em>Lob</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.LobImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getLob()
	 * @generated
	 */
	int LOB = 18;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl <em>Many To Many</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getManyToMany()
	 * @generated
	 */
	int MANY_TO_MANY = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToOneImpl <em>Many To One</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToOneImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getManyToOne()
	 * @generated
	 */
	int MANY_TO_ONE = 20;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.MapKeyImpl <em>Map Key</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.MapKeyImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getMapKey()
	 * @generated
	 */
	int MAP_KEY = 21;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.OneToManyImpl <em>One To Many</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.OneToManyImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOneToMany()
	 * @generated
	 */
	int ONE_TO_MANY = 23;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.OneToOneImpl <em>One To One</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.OneToOneImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOneToOne()
	 * @generated
	 */
	int ONE_TO_ONE = 24;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.OrderByImpl <em>Order By</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.OrderByImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOrderBy()
	 * @generated
	 */
	int ORDER_BY = 25;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.PrimaryKeyJoinColumnImpl <em>Primary Key Join Column</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PrimaryKeyJoinColumnImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getPrimaryKeyJoinColumn()
	 * @generated
	 */
	int PRIMARY_KEY_JOIN_COLUMN = 26;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SecondaryTableImpl <em>Secondary Table</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.SecondaryTableImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getSecondaryTable()
	 * @generated
	 */
	int SECONDARY_TABLE = 27;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceGeneratorImpl <em>Sequence Generator</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceGeneratorImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getSequenceGenerator()
	 * @generated
	 */
	int SEQUENCE_GENERATOR = 28;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TableImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTable()
	 * @generated
	 */
	int TABLE = 29;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TableGeneratorImpl <em>Table Generator</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TableGeneratorImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTableGenerator()
	 * @generated
	 */
	int TABLE_GENERATOR = 30;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TemporalImpl <em>Temporal</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TemporalImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTemporal()
	 * @generated
	 */
	int TEMPORAL = 31;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TransientImpl <em>Transient</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TransientImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTransient()
	 * @generated
	 */
	int TRANSIENT = 32;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.UniqueConstraintImpl <em>Unique Constraint</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.UniqueConstraintImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getUniqueConstraint()
	 * @generated
	 */
	int UNIQUE_CONSTRAINT = 33;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.VersionImpl <em>Version</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.VersionImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getVersion()
	 * @generated
	 */
	int VERSION = 34;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.AssociationOverrideImpl <em>Association Override</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.AssociationOverrideImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getAssociationOverride()
	 * @generated
	 */
	int ASSOCIATION_OVERRIDE = 2;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_OVERRIDE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_OVERRIDE__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_OVERRIDE__JOIN_COLUMNS = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Association Override</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_OVERRIDE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BASIC__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Fetch</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC__FETCH = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC__OPTIONAL = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Basic</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASIC_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int COLUMN__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__UNIQUE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__NULLABLE = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__INSERTABLE = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__UPDATABLE = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Column Definition</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__COLUMN_DEFINITION = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Table</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__TABLE = PANNOTATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__LENGTH = PANNOTATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__PRECISION = PANNOTATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN__SCALE = PANNOTATION_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Column</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLUMN_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_COLUMN__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_COLUMN__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Discriminator Type</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_COLUMN__DISCRIMINATOR_TYPE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column Definition</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_COLUMN__COLUMN_DEFINITION = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_COLUMN__LENGTH = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Discriminator Column</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_COLUMN_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_VALUE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_VALUE__VALUE = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Discriminator Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCRIMINATOR_VALUE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMBEDDABLE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Embeddable</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDABLE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMBEDDED__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Embedded</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_ID__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Embedded Id</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMBEDDED_ID_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENTITY__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__EXTENDS = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ENUMERATED__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATED__VALUE = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enumerated</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATED_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int GENERATED_VALUE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATED_VALUE__STRATEGY = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Generator</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATED_VALUE__GENERATOR = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Generated Value</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERATED_VALUE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ID__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the the '<em>Id</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ID_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ID_CLASS__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_CLASS__VALUE = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Id Class</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ID_CLASS_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INHERITANCE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHERITANCE__STRATEGY = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Inheritance</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INHERITANCE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Column Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__REFERENCED_COLUMN_NAME = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__UNIQUE = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Nullable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__NULLABLE = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Insertable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__INSERTABLE = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Updatable</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__UPDATABLE = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Column Definition</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__COLUMN_DEFINITION = PANNOTATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Table</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN__TABLE = PANNOTATION_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Join Column</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_COLUMN_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__CATALOG = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__SCHEMA = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__JOIN_COLUMNS = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Inverse Join Columns</b></em>' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__INVERSE_JOIN_COLUMNS = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Unique Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE__UNIQUE_CONSTRAINTS = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Join Table</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TABLE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOB__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the the '<em>Lob</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOB_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target Entity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY__TARGET_ENTITY = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cascade</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY__CASCADE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fetch</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY__FETCH = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Mapped By</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY__MAPPED_BY = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Indexed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY__INDEXED = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Many To Many</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_MANY_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANY_TO_ONE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target Entity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANY_TO_ONE__TARGET_ENTITY = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cascade</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MANY_TO_ONE__CASCADE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fetch</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_ONE__FETCH = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_ONE__OPTIONAL = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Many To One</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MANY_TO_ONE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAP_KEY__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_KEY__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map Key</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_KEY_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MAPPED_SUPERCLASS__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Mapped Superclass</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPED_SUPERCLASS_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target Entity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__TARGET_ENTITY = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cascade</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__CASCADE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fetch</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__FETCH = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Mapped By</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__MAPPED_BY = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Indexed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__INDEXED = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY__UNIQUE = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>One To Many</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_MANY_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Target Entity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE__TARGET_ENTITY = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cascade</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE__CASCADE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fetch</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE__FETCH = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE__OPTIONAL = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Mapped By</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE__MAPPED_BY = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>One To One</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_TO_ONE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ORDER_BY__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER_BY__VALUE = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Order By</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDER_BY_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY_JOIN_COLUMN__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY_JOIN_COLUMN__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referenced Column Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY_JOIN_COLUMN__REFERENCED_COLUMN_NAME = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column Definition</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY_JOIN_COLUMN__COLUMN_DEFINITION = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Primary Key Join Column</em>' class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMARY_KEY_JOIN_COLUMN_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE__CATALOG = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE__SCHEMA = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Pk Join Columns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE__PK_JOIN_COLUMNS = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Unique Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE__UNIQUE_CONSTRAINTS = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Secondary Table</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECONDARY_TABLE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_GENERATOR__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_GENERATOR__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sequence Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_GENERATOR__SEQUENCE_NAME = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_GENERATOR__INITIAL_VALUE = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Allocation Size</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_GENERATOR__ALLOCATION_SIZE = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Sequence Generator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_GENERATOR_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TABLE__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__CATALOG = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__SCHEMA = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Unique Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__UNIQUE_CONSTRAINTS = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Table</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__TABLE = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Catalog</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__CATALOG = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__SCHEMA = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Pk Column Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__PK_COLUMN_NAME = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Value Column Name</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__VALUE_COLUMN_NAME = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Pk Column Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__PK_COLUMN_VALUE = PANNOTATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__INITIAL_VALUE = PANNOTATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Allocation Size</b></em>' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__ALLOCATION_SIZE = PANNOTATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Unique Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR__UNIQUE_CONSTRAINTS = PANNOTATION_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Table Generator</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_GENERATOR_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__VALUE = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Temporal</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSIENT__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Transient</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSIENT_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Column Names</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT__COLUMN_NAMES = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unique Constraint</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_CONSTRAINT_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The number of structural features of the '<em>Version</em>' class.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERSION_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ForeignKeyImpl <em>Foreign Key</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ForeignKeyImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getForeignKey()
	 * @generated
	 */
	int FOREIGN_KEY = 35;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Foreign Key</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOREIGN_KEY_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl <em>Sequence Style Generator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getSequenceStyleGenerator()
	 * @generated
	 */
	int SEQUENCE_STYLE_GENERATOR = 36;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR__NAME = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sequence Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE = PANNOTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Increment Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE = PANNOTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Optimizer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR__OPTIMIZER = PANNOTATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Sequence Style Generator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_STYLE_GENERATOR_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ExternalImpl <em>External</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ExternalImpl
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getExternal()
	 * @generated
	 */
	int EXTERNAL = 37;

	/**
	 * The feature id for the '<em><b>EModel Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL__EMODEL_ELEMENT = PANNOTATION__EMODEL_ELEMENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL__TYPE = PANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>External</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_FEATURE_COUNT = PANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.FetchType <em>Fetch Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.FetchType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getFetchType()
	 * @generated
	 */
	int FETCH_TYPE = 41;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType <em>Discriminator Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getDiscriminatorType()
	 * @generated
	 */
	int DISCRIMINATOR_TYPE = 39;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.EnumType <em>Enum Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EnumType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEnumType()
	 * @generated
	 */
	int ENUM_TYPE = 40;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.InheritanceType <em>Inheritance Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.InheritanceType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getInheritanceType()
	 * @generated
	 */
	int INHERITANCE_TYPE = 43;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.CascadeType <em>Cascade Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.CascadeType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getCascadeType()
	 * @generated
	 */
	int CASCADE_TYPE = 38;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.TemporalType <em>Temporal Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TemporalType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTemporalType()
	 * @generated
	 */
	int TEMPORAL_TYPE = 44;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.OptimizerType <em>Optimizer Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OptimizerType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOptimizerType()
	 * @generated
	 */
	int OPTIMIZER_TYPE = 45;

	/**
	 * The meta object id for the '{@link org.eclipse.emf.teneo.annotations.pannotation.GenerationType <em>Generation Type</em>}' enum.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.teneo.annotations.pannotation.GenerationType
	 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getGenerationType()
	 * @generated
	 */
	int GENERATION_TYPE = 42;

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.PAnnotation <em>PAnnotation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>PAnnotation</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PAnnotation
	 * @generated
	 */
	EClass getPAnnotation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.emf.teneo.annotations.pannotation.PAnnotation#getEModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EModel Element</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PAnnotation#getEModelElement()
	 * @see #getPAnnotation()
	 * @generated
	 */
	EReference getPAnnotation_EModelElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride <em>Attribute Override</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Override</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride
	 * @generated
	 */
	EClass getAttributeOverride();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride#getName()
	 * @see #getAttributeOverride()
	 * @generated
	 */
	EAttribute getAttributeOverride_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride#getColumn()
	 * @see #getAttributeOverride()
	 * @generated
	 */
	EReference getAttributeOverride_Column();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Basic <em>Basic</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Basic</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Basic
	 * @generated
	 */
	EClass getBasic();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Basic#getFetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Basic#getFetch()
	 * @see #getBasic()
	 * @generated
	 */
	EAttribute getBasic_Fetch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Basic#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Basic#isOptional()
	 * @see #getBasic()
	 * @generated
	 */
	EAttribute getBasic_Optional();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Column <em>Column</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column
	 * @generated
	 */
	EClass getColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#getName()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#getLength()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#getScale <em>Scale</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#getScale()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Scale();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#isUnique()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Unique();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#getPrecision <em>Precision</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Precision</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#getPrecision()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Precision();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#isNullable()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Nullable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#isInsertable <em>Insertable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Insertable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#isInsertable()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Insertable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#isUpdatable <em>Updatable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Updatable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#isUpdatable()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Updatable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#getColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Definition</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#getColumnDefinition()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_ColumnDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Column#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Column#getTable()
	 * @see #getColumn()
	 * @generated
	 */
	EAttribute getColumn_Table();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn <em>Discriminator Column</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discriminator Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn
	 * @generated
	 */
	EClass getDiscriminatorColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getName()
	 * @see #getDiscriminatorColumn()
	 * @generated
	 */
	EAttribute getDiscriminatorColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getDiscriminatorType <em>Discriminator Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Discriminator Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getDiscriminatorType()
	 * @see #getDiscriminatorColumn()
	 * @generated
	 */
	EAttribute getDiscriminatorColumn_DiscriminatorType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getLength()
	 * @see #getDiscriminatorColumn()
	 * @generated
	 */
	EAttribute getDiscriminatorColumn_Length();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue <em>Discriminator Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discriminator Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue
	 * @generated
	 */
	EClass getDiscriminatorValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue#getValue()
	 * @see #getDiscriminatorValue()
	 * @generated
	 */
	EAttribute getDiscriminatorValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Definition</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn#getColumnDefinition()
	 * @see #getDiscriminatorColumn()
	 * @generated
	 */
	EAttribute getDiscriminatorColumn_ColumnDefinition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Embeddable <em>Embeddable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Embeddable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Embeddable
	 * @generated
	 */
	EClass getEmbeddable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.MappedSuperclass <em>Mapped Superclass</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mapped Superclass</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MappedSuperclass
	 * @generated
	 */
	EClass getMappedSuperclass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Embedded <em>Embedded</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Embedded</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Embedded
	 * @generated
	 */
	EClass getEmbedded();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.EmbeddedId <em>Embedded Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Embedded Id</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EmbeddedId
	 * @generated
	 */
	EClass getEmbeddedId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Enumerated <em>Enumerated</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumerated</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Enumerated
	 * @generated
	 */
	EClass getEnumerated();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Enumerated#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Enumerated#getValue()
	 * @see #getEnumerated()
	 * @generated
	 */
	EAttribute getEnumerated_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Entity#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Entity#getName()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Entity#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extends</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Entity#getExtends()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Extends();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue <em>Generated Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generated Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue
	 * @generated
	 */
	EClass getGeneratedValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue#getStrategy()
	 * @see #getGeneratedValue()
	 * @generated
	 */
	EAttribute getGeneratedValue_Strategy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue#getGenerator <em>Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Generator</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.GeneratedValue#getGenerator()
	 * @see #getGeneratedValue()
	 * @generated
	 */
	EAttribute getGeneratedValue_Generator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Id <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Id</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Id
	 * @generated
	 */
	EClass getId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.IdClass <em>Id Class</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Id Class</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.IdClass
	 * @generated
	 */
	EClass getIdClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.IdClass#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.IdClass#getValue()
	 * @see #getIdClass()
	 * @generated
	 */
	EAttribute getIdClass_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Inheritance <em>Inheritance</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Inheritance</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Inheritance
	 * @generated
	 */
	EClass getInheritance();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Inheritance#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Inheritance#getStrategy()
	 * @see #getInheritance()
	 * @generated
	 */
	EAttribute getInheritance_Strategy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn <em>Join Column</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn
	 * @generated
	 */
	EClass getJoinColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getName()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isUnique()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_Unique();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isNullable <em>Nullable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nullable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isNullable()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_Nullable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isInsertable <em>Insertable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Insertable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isInsertable()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_Insertable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isUpdatable <em>Updatable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Updatable</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#isUpdatable()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_Updatable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Definition</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getColumnDefinition()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_ColumnDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getTable()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_Table();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getReferencedColumnName <em>Referenced Column Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referenced Column Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinColumn#getReferencedColumnName()
	 * @see #getJoinColumn()
	 * @generated
	 */
	EAttribute getJoinColumn_ReferencedColumnName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable <em>Join Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable
	 * @generated
	 */
	EClass getJoinTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getName()
	 * @see #getJoinTable()
	 * @generated
	 */
	EAttribute getJoinTable_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Catalog</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getCatalog()
	 * @see #getJoinTable()
	 * @generated
	 */
	EAttribute getJoinTable_Catalog();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getSchema()
	 * @see #getJoinTable()
	 * @generated
	 */
	EAttribute getJoinTable_Schema();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getJoinColumns <em>Join Columns</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getJoinColumns()
	 * @see #getJoinTable()
	 * @generated
	 */
	EReference getJoinTable_JoinColumns();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getInverseJoinColumns <em>Inverse Join Columns</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Inverse Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getInverseJoinColumns()
	 * @see #getJoinTable()
	 * @generated
	 */
	EReference getJoinTable_InverseJoinColumns();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getUniqueConstraints <em>Unique Constraints</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unique Constraints</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.JoinTable#getUniqueConstraints()
	 * @see #getJoinTable()
	 * @generated
	 */
	EReference getJoinTable_UniqueConstraints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Lob <em>Lob</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lob</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Lob
	 * @generated
	 */
	EClass getLob();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany <em>Many To Many</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many To Many</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany
	 * @generated
	 */
	EClass getManyToMany();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getFetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getFetch()
	 * @see #getManyToMany()
	 * @generated
	 */
	EAttribute getManyToMany_Fetch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getTargetEntity <em>Target Entity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getTargetEntity()
	 * @see #getManyToMany()
	 * @generated
	 */
	EAttribute getManyToMany_TargetEntity();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getCascade <em>Cascade</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cascade</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getCascade()
	 * @see #getManyToMany()
	 * @generated
	 */
	EAttribute getManyToMany_Cascade();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getMappedBy <em>Mapped By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mapped By</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#getMappedBy()
	 * @see #getManyToMany()
	 * @generated
	 */
	EAttribute getManyToMany_MappedBy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#isIndexed <em>Indexed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Indexed</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToMany#isIndexed()
	 * @see #getManyToMany()
	 * @generated
	 */
	EAttribute getManyToMany_Indexed();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne <em>Many To One</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Many To One</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToOne
	 * @generated
	 */
	EClass getManyToOne();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getFetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getFetch()
	 * @see #getManyToOne()
	 * @generated
	 */
	EAttribute getManyToOne_Fetch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#isOptional()
	 * @see #getManyToOne()
	 * @generated
	 */
	EAttribute getManyToOne_Optional();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getTargetEntity <em>Target Entity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getTargetEntity()
	 * @see #getManyToOne()
	 * @generated
	 */
	EAttribute getManyToOne_TargetEntity();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getCascade <em>Cascade</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cascade</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ManyToOne#getCascade()
	 * @see #getManyToOne()
	 * @generated
	 */
	EAttribute getManyToOne_Cascade();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.MapKey <em>Map Key</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Key</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKey
	 * @generated
	 */
	EClass getMapKey();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.MapKey#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.MapKey#getName()
	 * @see #getMapKey()
	 * @generated
	 */
	EAttribute getMapKey_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany <em>One To Many</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>One To Many</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany
	 * @generated
	 */
	EClass getOneToMany();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getFetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getFetch()
	 * @see #getOneToMany()
	 * @generated
	 */
	EAttribute getOneToMany_Fetch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getTargetEntity <em>Target Entity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getTargetEntity()
	 * @see #getOneToMany()
	 * @generated
	 */
	EAttribute getOneToMany_TargetEntity();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getCascade <em>Cascade</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cascade</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getCascade()
	 * @see #getOneToMany()
	 * @generated
	 */
	EAttribute getOneToMany_Cascade();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getMappedBy <em>Mapped By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mapped By</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany#getMappedBy()
	 * @see #getOneToMany()
	 * @generated
	 */
	EAttribute getOneToMany_MappedBy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#isIndexed <em>Indexed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Indexed</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany#isIndexed()
	 * @see #getOneToMany()
	 * @generated
	 */
	EAttribute getOneToMany_Indexed();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToMany#isUnique <em>Unique</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unique</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToMany#isUnique()
	 * @see #getOneToMany()
	 * @generated
	 */
	EAttribute getOneToMany_Unique();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne <em>One To One</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>One To One</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne
	 * @generated
	 */
	EClass getOneToOne();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getFetch <em>Fetch</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fetch</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getFetch()
	 * @see #getOneToOne()
	 * @generated
	 */
	EAttribute getOneToOne_Fetch();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne#isOptional()
	 * @see #getOneToOne()
	 * @generated
	 */
	EAttribute getOneToOne_Optional();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getTargetEntity <em>Target Entity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getTargetEntity()
	 * @see #getOneToOne()
	 * @generated
	 */
	EAttribute getOneToOne_TargetEntity();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getCascade <em>Cascade</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cascade</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getCascade()
	 * @see #getOneToOne()
	 * @generated
	 */
	EAttribute getOneToOne_Cascade();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getMappedBy <em>Mapped By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mapped By</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OneToOne#getMappedBy()
	 * @see #getOneToOne()
	 * @generated
	 */
	EAttribute getOneToOne_MappedBy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.OrderBy <em>Order By</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Order By</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OrderBy
	 * @generated
	 */
	EClass getOrderBy();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.OrderBy#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OrderBy#getValue()
	 * @see #getOrderBy()
	 * @generated
	 */
	EAttribute getOrderBy_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn <em>Primary Key Join Column</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primary Key Join Column</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn
	 * @generated
	 */
	EClass getPrimaryKeyJoinColumn();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn#getName()
	 * @see #getPrimaryKeyJoinColumn()
	 * @generated
	 */
	EAttribute getPrimaryKeyJoinColumn_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn#getColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column Definition</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn#getColumnDefinition()
	 * @see #getPrimaryKeyJoinColumn()
	 * @generated
	 */
	EAttribute getPrimaryKeyJoinColumn_ColumnDefinition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn#getReferencedColumnName <em>Referenced Column Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referenced Column Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn#getReferencedColumnName()
	 * @see #getPrimaryKeyJoinColumn()
	 * @generated
	 */
	EAttribute getPrimaryKeyJoinColumn_ReferencedColumnName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable <em>Secondary Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secondary Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable
	 * @generated
	 */
	EClass getSecondaryTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getName()
	 * @see #getSecondaryTable()
	 * @generated
	 */
	EAttribute getSecondaryTable_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Catalog</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getCatalog()
	 * @see #getSecondaryTable()
	 * @generated
	 */
	EAttribute getSecondaryTable_Catalog();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getSchema()
	 * @see #getSecondaryTable()
	 * @generated
	 */
	EAttribute getSecondaryTable_Schema();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getPkJoinColumns <em>Pk Join Columns</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pk Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getPkJoinColumns()
	 * @see #getSecondaryTable()
	 * @generated
	 */
	EReference getSecondaryTable_PkJoinColumns();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getUniqueConstraints <em>Unique Constraints</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unique Constraints</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable#getUniqueConstraints()
	 * @see #getSecondaryTable()
	 * @generated
	 */
	EReference getSecondaryTable_UniqueConstraints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator <em>Sequence Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Generator</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator
	 * @generated
	 */
	EClass getSequenceGenerator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getName()
	 * @see #getSequenceGenerator()
	 * @generated
	 */
	EAttribute getSequenceGenerator_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getInitialValue()
	 * @see #getSequenceGenerator()
	 * @generated
	 */
	EAttribute getSequenceGenerator_InitialValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getSequenceName <em>Sequence Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequence Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getSequenceName()
	 * @see #getSequenceGenerator()
	 * @generated
	 */
	EAttribute getSequenceGenerator_SequenceName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getAllocationSize <em>Allocation Size</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation Size</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator#getAllocationSize()
	 * @see #getSequenceGenerator()
	 * @generated
	 */
	EAttribute getSequenceGenerator_AllocationSize();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Table <em>Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Table
	 * @generated
	 */
	EClass getTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Table#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Table#getName()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Table#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Catalog</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Table#getCatalog()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Catalog();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Table#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Table#getSchema()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Schema();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.Table#getUniqueConstraints <em>Unique Constraints</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unique Constraints</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Table#getUniqueConstraints()
	 * @see #getTable()
	 * @generated
	 */
	EReference getTable_UniqueConstraints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator <em>Table Generator</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table Generator</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator
	 * @generated
	 */
	EClass getTableGenerator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getName()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getAllocationSize <em>Allocation Size</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation Size</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getAllocationSize()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_AllocationSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getTable <em>Table</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Table</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getTable()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_Table();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getCatalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Catalog</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getCatalog()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_Catalog();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Schema</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getSchema()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_Schema();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getPkColumnName <em>Pk Column Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pk Column Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getPkColumnName()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_PkColumnName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getValueColumnName <em>Value Column Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Column Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getValueColumnName()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_ValueColumnName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getInitialValue()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_InitialValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getPkColumnValue <em>Pk Column Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pk Column Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getPkColumnValue()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EAttribute getTableGenerator_PkColumnValue();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getUniqueConstraints <em>Unique Constraints</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Unique Constraints</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TableGenerator#getUniqueConstraints()
	 * @see #getTableGenerator()
	 * @generated
	 */
	EReference getTableGenerator_UniqueConstraints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Temporal <em>Temporal</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporal</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Temporal
	 * @generated
	 */
	EClass getTemporal();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.Temporal#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Temporal#getValue()
	 * @see #getTemporal()
	 * @generated
	 */
	EAttribute getTemporal_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Transient <em>Transient</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transient</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Transient
	 * @generated
	 */
	EClass getTransient();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint <em>Unique Constraint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unique Constraint</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint
	 * @generated
	 */
	EClass getUniqueConstraint();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint#getColumnNames <em>Column Names</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Column Names</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.UniqueConstraint#getColumnNames()
	 * @see #getUniqueConstraint()
	 * @generated
	 */
	EAttribute getUniqueConstraint_ColumnNames();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.Version <em>Version</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Version</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.Version
	 * @generated
	 */
	EClass getVersion();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.ForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Foreign Key</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ForeignKey
	 * @generated
	 */
	EClass getForeignKey();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.ForeignKey#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.ForeignKey#getName()
	 * @see #getForeignKey()
	 * @generated
	 */
	EAttribute getForeignKey_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator <em>Sequence Style Generator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Style Generator</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator
	 * @generated
	 */
	EClass getSequenceStyleGenerator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getName()
	 * @see #getSequenceStyleGenerator()
	 * @generated
	 */
	EAttribute getSequenceStyleGenerator_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getSequenceName <em>Sequence Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sequence Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getSequenceName()
	 * @see #getSequenceStyleGenerator()
	 * @generated
	 */
	EAttribute getSequenceStyleGenerator_SequenceName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getInitialValue <em>Initial Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Initial Value</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getInitialValue()
	 * @see #getSequenceStyleGenerator()
	 * @generated
	 */
	EAttribute getSequenceStyleGenerator_InitialValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getIncrementSize <em>Increment Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Increment Size</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getIncrementSize()
	 * @see #getSequenceStyleGenerator()
	 * @generated
	 */
	EAttribute getSequenceStyleGenerator_IncrementSize();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getOptimizer <em>Optimizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optimizer</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getOptimizer()
	 * @see #getSequenceStyleGenerator()
	 * @generated
	 */
	EAttribute getSequenceStyleGenerator_Optimizer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.External <em>External</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.External
	 * @generated
	 */
	EClass getExternal();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.External#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.External#getType()
	 * @see #getExternal()
	 * @generated
	 */
	EAttribute getExternal_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride <em>Association Override</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Override</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride
	 * @generated
	 */
	EClass getAssociationOverride();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride#getName()
	 * @see #getAssociationOverride()
	 * @generated
	 */
	EAttribute getAssociationOverride_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride#getJoinColumns <em>Join Columns</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join Columns</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride#getJoinColumns()
	 * @see #getAssociationOverride()
	 * @generated
	 */
	EReference getAssociationOverride_JoinColumns();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.FetchType <em>Fetch Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Fetch Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.FetchType
	 * @generated
	 */
	EEnum getFetchType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType <em>Discriminator Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Discriminator Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType
	 * @generated
	 */
	EEnum getDiscriminatorType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.EnumType <em>Enum Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Enum Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.EnumType
	 * @generated
	 */
	EEnum getEnumType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.InheritanceType <em>Inheritance Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Inheritance Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.InheritanceType
	 * @generated
	 */
	EEnum getInheritanceType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.CascadeType <em>Cascade Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Cascade Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.CascadeType
	 * @generated
	 */
	EEnum getCascadeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.TemporalType <em>Temporal Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Temporal Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.TemporalType
	 * @generated
	 */
	EEnum getTemporalType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.OptimizerType <em>Optimizer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Optimizer Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OptimizerType
	 * @generated
	 */
	EEnum getOptimizerType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.emf.teneo.annotations.pannotation.GenerationType <em>Generation Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Generation Type</em>'.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.GenerationType
	 * @generated
	 */
	EEnum getGenerationType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PannotationFactory getPannotationFactory();

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
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.PAnnotationImpl <em>PAnnotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PAnnotationImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getPAnnotation()
		 * @generated
		 */
		EClass PANNOTATION = eINSTANCE.getPAnnotation();

		/**
		 * The meta object literal for the '<em><b>EModel Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PANNOTATION__EMODEL_ELEMENT = eINSTANCE.getPAnnotation_EModelElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.AttributeOverrideImpl <em>Attribute Override</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.AttributeOverrideImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getAttributeOverride()
		 * @generated
		 */
		EClass ATTRIBUTE_OVERRIDE = eINSTANCE.getAttributeOverride();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_OVERRIDE__NAME = eINSTANCE.getAttributeOverride_Name();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTE_OVERRIDE__COLUMN = eINSTANCE.getAttributeOverride_Column();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.AssociationOverrideImpl <em>Association Override</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.AssociationOverrideImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getAssociationOverride()
		 * @generated
		 */
		EClass ASSOCIATION_OVERRIDE = eINSTANCE.getAssociationOverride();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION_OVERRIDE__NAME = eINSTANCE.getAssociationOverride_Name();

		/**
		 * The meta object literal for the '<em><b>Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_OVERRIDE__JOIN_COLUMNS = eINSTANCE.getAssociationOverride_JoinColumns();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.BasicImpl <em>Basic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.BasicImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getBasic()
		 * @generated
		 */
		EClass BASIC = eINSTANCE.getBasic();

		/**
		 * The meta object literal for the '<em><b>Fetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC__FETCH = eINSTANCE.getBasic_Fetch();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BASIC__OPTIONAL = eINSTANCE.getBasic_Optional();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ColumnImpl <em>Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ColumnImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getColumn()
		 * @generated
		 */
		EClass COLUMN = eINSTANCE.getColumn();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__NAME = eINSTANCE.getColumn_Name();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__UNIQUE = eINSTANCE.getColumn_Unique();

		/**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__NULLABLE = eINSTANCE.getColumn_Nullable();

		/**
		 * The meta object literal for the '<em><b>Insertable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__INSERTABLE = eINSTANCE.getColumn_Insertable();

		/**
		 * The meta object literal for the '<em><b>Updatable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__UPDATABLE = eINSTANCE.getColumn_Updatable();

		/**
		 * The meta object literal for the '<em><b>Column Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__COLUMN_DEFINITION = eINSTANCE.getColumn_ColumnDefinition();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__TABLE = eINSTANCE.getColumn_Table();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__LENGTH = eINSTANCE.getColumn_Length();

		/**
		 * The meta object literal for the '<em><b>Precision</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__PRECISION = eINSTANCE.getColumn_Precision();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLUMN__SCALE = eINSTANCE.getColumn_Scale();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorColumnImpl <em>Discriminator Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorColumnImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getDiscriminatorColumn()
		 * @generated
		 */
		EClass DISCRIMINATOR_COLUMN = eINSTANCE.getDiscriminatorColumn();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCRIMINATOR_COLUMN__NAME = eINSTANCE.getDiscriminatorColumn_Name();

		/**
		 * The meta object literal for the '<em><b>Discriminator Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCRIMINATOR_COLUMN__DISCRIMINATOR_TYPE = eINSTANCE.getDiscriminatorColumn_DiscriminatorType();

		/**
		 * The meta object literal for the '<em><b>Column Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCRIMINATOR_COLUMN__COLUMN_DEFINITION = eINSTANCE.getDiscriminatorColumn_ColumnDefinition();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCRIMINATOR_COLUMN__LENGTH = eINSTANCE.getDiscriminatorColumn_Length();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorValueImpl <em>Discriminator Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.DiscriminatorValueImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getDiscriminatorValue()
		 * @generated
		 */
		EClass DISCRIMINATOR_VALUE = eINSTANCE.getDiscriminatorValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISCRIMINATOR_VALUE__VALUE = eINSTANCE.getDiscriminatorValue_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddableImpl <em>Embeddable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddableImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEmbeddable()
		 * @generated
		 */
		EClass EMBEDDABLE = eINSTANCE.getEmbeddable();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedImpl <em>Embedded</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEmbedded()
		 * @generated
		 */
		EClass EMBEDDED = eINSTANCE.getEmbedded();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedIdImpl <em>Embedded Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EmbeddedIdImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEmbeddedId()
		 * @generated
		 */
		EClass EMBEDDED_ID = eINSTANCE.getEmbeddedId();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EntityImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__NAME = eINSTANCE.getEntity_Name();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__EXTENDS = eINSTANCE.getEntity_Extends();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.EnumeratedImpl <em>Enumerated</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.EnumeratedImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEnumerated()
		 * @generated
		 */
		EClass ENUMERATED = eINSTANCE.getEnumerated();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATED__VALUE = eINSTANCE.getEnumerated_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.GeneratedValueImpl <em>Generated Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.GeneratedValueImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getGeneratedValue()
		 * @generated
		 */
		EClass GENERATED_VALUE = eINSTANCE.getGeneratedValue();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATED_VALUE__STRATEGY = eINSTANCE.getGeneratedValue_Strategy();

		/**
		 * The meta object literal for the '<em><b>Generator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERATED_VALUE__GENERATOR = eINSTANCE.getGeneratedValue_Generator();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.IdImpl <em>Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.IdImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getId()
		 * @generated
		 */
		EClass ID = eINSTANCE.getId();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.IdClassImpl <em>Id Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.IdClassImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getIdClass()
		 * @generated
		 */
		EClass ID_CLASS = eINSTANCE.getIdClass();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ID_CLASS__VALUE = eINSTANCE.getIdClass_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.InheritanceImpl <em>Inheritance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.InheritanceImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getInheritance()
		 * @generated
		 */
		EClass INHERITANCE = eINSTANCE.getInheritance();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INHERITANCE__STRATEGY = eINSTANCE.getInheritance_Strategy();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinColumnImpl <em>Join Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.JoinColumnImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getJoinColumn()
		 * @generated
		 */
		EClass JOIN_COLUMN = eINSTANCE.getJoinColumn();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__NAME = eINSTANCE.getJoinColumn_Name();

		/**
		 * The meta object literal for the '<em><b>Referenced Column Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__REFERENCED_COLUMN_NAME = eINSTANCE.getJoinColumn_ReferencedColumnName();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__UNIQUE = eINSTANCE.getJoinColumn_Unique();

		/**
		 * The meta object literal for the '<em><b>Nullable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__NULLABLE = eINSTANCE.getJoinColumn_Nullable();

		/**
		 * The meta object literal for the '<em><b>Insertable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__INSERTABLE = eINSTANCE.getJoinColumn_Insertable();

		/**
		 * The meta object literal for the '<em><b>Updatable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__UPDATABLE = eINSTANCE.getJoinColumn_Updatable();

		/**
		 * The meta object literal for the '<em><b>Column Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__COLUMN_DEFINITION = eINSTANCE.getJoinColumn_ColumnDefinition();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_COLUMN__TABLE = eINSTANCE.getJoinColumn_Table();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl <em>Join Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.JoinTableImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getJoinTable()
		 * @generated
		 */
		EClass JOIN_TABLE = eINSTANCE.getJoinTable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TABLE__NAME = eINSTANCE.getJoinTable_Name();

		/**
		 * The meta object literal for the '<em><b>Catalog</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TABLE__CATALOG = eINSTANCE.getJoinTable_Catalog();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TABLE__SCHEMA = eINSTANCE.getJoinTable_Schema();

		/**
		 * The meta object literal for the '<em><b>Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TABLE__JOIN_COLUMNS = eINSTANCE.getJoinTable_JoinColumns();

		/**
		 * The meta object literal for the '<em><b>Inverse Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TABLE__INVERSE_JOIN_COLUMNS = eINSTANCE.getJoinTable_InverseJoinColumns();

		/**
		 * The meta object literal for the '<em><b>Unique Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TABLE__UNIQUE_CONSTRAINTS = eINSTANCE.getJoinTable_UniqueConstraints();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.LobImpl <em>Lob</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.LobImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getLob()
		 * @generated
		 */
		EClass LOB = eINSTANCE.getLob();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl <em>Many To Many</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToManyImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getManyToMany()
		 * @generated
		 */
		EClass MANY_TO_MANY = eINSTANCE.getManyToMany();

		/**
		 * The meta object literal for the '<em><b>Target Entity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_MANY__TARGET_ENTITY = eINSTANCE.getManyToMany_TargetEntity();

		/**
		 * The meta object literal for the '<em><b>Cascade</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_MANY__CASCADE = eINSTANCE.getManyToMany_Cascade();

		/**
		 * The meta object literal for the '<em><b>Fetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_MANY__FETCH = eINSTANCE.getManyToMany_Fetch();

		/**
		 * The meta object literal for the '<em><b>Mapped By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_MANY__MAPPED_BY = eINSTANCE.getManyToMany_MappedBy();

		/**
		 * The meta object literal for the '<em><b>Indexed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_MANY__INDEXED = eINSTANCE.getManyToMany_Indexed();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToOneImpl <em>Many To One</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ManyToOneImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getManyToOne()
		 * @generated
		 */
		EClass MANY_TO_ONE = eINSTANCE.getManyToOne();

		/**
		 * The meta object literal for the '<em><b>Target Entity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_ONE__TARGET_ENTITY = eINSTANCE.getManyToOne_TargetEntity();

		/**
		 * The meta object literal for the '<em><b>Cascade</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_ONE__CASCADE = eINSTANCE.getManyToOne_Cascade();

		/**
		 * The meta object literal for the '<em><b>Fetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_ONE__FETCH = eINSTANCE.getManyToOne_Fetch();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MANY_TO_ONE__OPTIONAL = eINSTANCE.getManyToOne_Optional();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.MapKeyImpl <em>Map Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.MapKeyImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getMapKey()
		 * @generated
		 */
		EClass MAP_KEY = eINSTANCE.getMapKey();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP_KEY__NAME = eINSTANCE.getMapKey_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.MappedSuperclassImpl <em>Mapped Superclass</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.MappedSuperclassImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getMappedSuperclass()
		 * @generated
		 */
		EClass MAPPED_SUPERCLASS = eINSTANCE.getMappedSuperclass();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.OneToManyImpl <em>One To Many</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.OneToManyImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOneToMany()
		 * @generated
		 */
		EClass ONE_TO_MANY = eINSTANCE.getOneToMany();

		/**
		 * The meta object literal for the '<em><b>Target Entity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_MANY__TARGET_ENTITY = eINSTANCE.getOneToMany_TargetEntity();

		/**
		 * The meta object literal for the '<em><b>Cascade</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_MANY__CASCADE = eINSTANCE.getOneToMany_Cascade();

		/**
		 * The meta object literal for the '<em><b>Fetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_MANY__FETCH = eINSTANCE.getOneToMany_Fetch();

		/**
		 * The meta object literal for the '<em><b>Mapped By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_MANY__MAPPED_BY = eINSTANCE.getOneToMany_MappedBy();

		/**
		 * The meta object literal for the '<em><b>Indexed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_MANY__INDEXED = eINSTANCE.getOneToMany_Indexed();

		/**
		 * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_MANY__UNIQUE = eINSTANCE.getOneToMany_Unique();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.OneToOneImpl <em>One To One</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.OneToOneImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOneToOne()
		 * @generated
		 */
		EClass ONE_TO_ONE = eINSTANCE.getOneToOne();

		/**
		 * The meta object literal for the '<em><b>Target Entity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_ONE__TARGET_ENTITY = eINSTANCE.getOneToOne_TargetEntity();

		/**
		 * The meta object literal for the '<em><b>Cascade</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_ONE__CASCADE = eINSTANCE.getOneToOne_Cascade();

		/**
		 * The meta object literal for the '<em><b>Fetch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_ONE__FETCH = eINSTANCE.getOneToOne_Fetch();

		/**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_ONE__OPTIONAL = eINSTANCE.getOneToOne_Optional();

		/**
		 * The meta object literal for the '<em><b>Mapped By</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ONE_TO_ONE__MAPPED_BY = eINSTANCE.getOneToOne_MappedBy();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.OrderByImpl <em>Order By</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.OrderByImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOrderBy()
		 * @generated
		 */
		EClass ORDER_BY = eINSTANCE.getOrderBy();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ORDER_BY__VALUE = eINSTANCE.getOrderBy_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.PrimaryKeyJoinColumnImpl <em>Primary Key Join Column</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PrimaryKeyJoinColumnImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getPrimaryKeyJoinColumn()
		 * @generated
		 */
		EClass PRIMARY_KEY_JOIN_COLUMN = eINSTANCE.getPrimaryKeyJoinColumn();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMARY_KEY_JOIN_COLUMN__NAME = eINSTANCE.getPrimaryKeyJoinColumn_Name();

		/**
		 * The meta object literal for the '<em><b>Referenced Column Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMARY_KEY_JOIN_COLUMN__REFERENCED_COLUMN_NAME = eINSTANCE.getPrimaryKeyJoinColumn_ReferencedColumnName();

		/**
		 * The meta object literal for the '<em><b>Column Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMARY_KEY_JOIN_COLUMN__COLUMN_DEFINITION = eINSTANCE.getPrimaryKeyJoinColumn_ColumnDefinition();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SecondaryTableImpl <em>Secondary Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.SecondaryTableImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getSecondaryTable()
		 * @generated
		 */
		EClass SECONDARY_TABLE = eINSTANCE.getSecondaryTable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECONDARY_TABLE__NAME = eINSTANCE.getSecondaryTable_Name();

		/**
		 * The meta object literal for the '<em><b>Catalog</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECONDARY_TABLE__CATALOG = eINSTANCE.getSecondaryTable_Catalog();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECONDARY_TABLE__SCHEMA = eINSTANCE.getSecondaryTable_Schema();

		/**
		 * The meta object literal for the '<em><b>Pk Join Columns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECONDARY_TABLE__PK_JOIN_COLUMNS = eINSTANCE.getSecondaryTable_PkJoinColumns();

		/**
		 * The meta object literal for the '<em><b>Unique Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECONDARY_TABLE__UNIQUE_CONSTRAINTS = eINSTANCE.getSecondaryTable_UniqueConstraints();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceGeneratorImpl <em>Sequence Generator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceGeneratorImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getSequenceGenerator()
		 * @generated
		 */
		EClass SEQUENCE_GENERATOR = eINSTANCE.getSequenceGenerator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_GENERATOR__NAME = eINSTANCE.getSequenceGenerator_Name();

		/**
		 * The meta object literal for the '<em><b>Sequence Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_GENERATOR__SEQUENCE_NAME = eINSTANCE.getSequenceGenerator_SequenceName();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_GENERATOR__INITIAL_VALUE = eINSTANCE.getSequenceGenerator_InitialValue();

		/**
		 * The meta object literal for the '<em><b>Allocation Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_GENERATOR__ALLOCATION_SIZE = eINSTANCE.getSequenceGenerator_AllocationSize();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TableImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTable()
		 * @generated
		 */
		EClass TABLE = eINSTANCE.getTable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__NAME = eINSTANCE.getTable_Name();

		/**
		 * The meta object literal for the '<em><b>Catalog</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__CATALOG = eINSTANCE.getTable_Catalog();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__SCHEMA = eINSTANCE.getTable_Schema();

		/**
		 * The meta object literal for the '<em><b>Unique Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE__UNIQUE_CONSTRAINTS = eINSTANCE.getTable_UniqueConstraints();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TableGeneratorImpl <em>Table Generator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TableGeneratorImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTableGenerator()
		 * @generated
		 */
		EClass TABLE_GENERATOR = eINSTANCE.getTableGenerator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__NAME = eINSTANCE.getTableGenerator_Name();

		/**
		 * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__TABLE = eINSTANCE.getTableGenerator_Table();

		/**
		 * The meta object literal for the '<em><b>Catalog</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__CATALOG = eINSTANCE.getTableGenerator_Catalog();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__SCHEMA = eINSTANCE.getTableGenerator_Schema();

		/**
		 * The meta object literal for the '<em><b>Pk Column Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__PK_COLUMN_NAME = eINSTANCE.getTableGenerator_PkColumnName();

		/**
		 * The meta object literal for the '<em><b>Value Column Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__VALUE_COLUMN_NAME = eINSTANCE.getTableGenerator_ValueColumnName();

		/**
		 * The meta object literal for the '<em><b>Pk Column Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__PK_COLUMN_VALUE = eINSTANCE.getTableGenerator_PkColumnValue();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__INITIAL_VALUE = eINSTANCE.getTableGenerator_InitialValue();

		/**
		 * The meta object literal for the '<em><b>Allocation Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE_GENERATOR__ALLOCATION_SIZE = eINSTANCE.getTableGenerator_AllocationSize();

		/**
		 * The meta object literal for the '<em><b>Unique Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TABLE_GENERATOR__UNIQUE_CONSTRAINTS = eINSTANCE.getTableGenerator_UniqueConstraints();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TemporalImpl <em>Temporal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TemporalImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTemporal()
		 * @generated
		 */
		EClass TEMPORAL = eINSTANCE.getTemporal();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPORAL__VALUE = eINSTANCE.getTemporal_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.TransientImpl <em>Transient</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.TransientImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTransient()
		 * @generated
		 */
		EClass TRANSIENT = eINSTANCE.getTransient();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.UniqueConstraintImpl <em>Unique Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.UniqueConstraintImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getUniqueConstraint()
		 * @generated
		 */
		EClass UNIQUE_CONSTRAINT = eINSTANCE.getUniqueConstraint();

		/**
		 * The meta object literal for the '<em><b>Column Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIQUE_CONSTRAINT__COLUMN_NAMES = eINSTANCE.getUniqueConstraint_ColumnNames();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.VersionImpl <em>Version</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.VersionImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getVersion()
		 * @generated
		 */
		EClass VERSION = eINSTANCE.getVersion();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ForeignKeyImpl <em>Foreign Key</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ForeignKeyImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getForeignKey()
		 * @generated
		 */
		EClass FOREIGN_KEY = eINSTANCE.getForeignKey();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOREIGN_KEY__NAME = eINSTANCE.getForeignKey_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl <em>Sequence Style Generator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getSequenceStyleGenerator()
		 * @generated
		 */
		EClass SEQUENCE_STYLE_GENERATOR = eINSTANCE.getSequenceStyleGenerator();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_STYLE_GENERATOR__NAME = eINSTANCE.getSequenceStyleGenerator_Name();

		/**
		 * The meta object literal for the '<em><b>Sequence Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME = eINSTANCE.getSequenceStyleGenerator_SequenceName();

		/**
		 * The meta object literal for the '<em><b>Initial Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE = eINSTANCE.getSequenceStyleGenerator_InitialValue();

		/**
		 * The meta object literal for the '<em><b>Increment Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE = eINSTANCE.getSequenceStyleGenerator_IncrementSize();

		/**
		 * The meta object literal for the '<em><b>Optimizer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEQUENCE_STYLE_GENERATOR__OPTIMIZER = eINSTANCE.getSequenceStyleGenerator_Optimizer();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.impl.ExternalImpl <em>External</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.ExternalImpl
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getExternal()
		 * @generated
		 */
		EClass EXTERNAL = eINSTANCE.getExternal();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTERNAL__TYPE = eINSTANCE.getExternal_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.CascadeType <em>Cascade Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.CascadeType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getCascadeType()
		 * @generated
		 */
		EEnum CASCADE_TYPE = eINSTANCE.getCascadeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType <em>Discriminator Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getDiscriminatorType()
		 * @generated
		 */
		EEnum DISCRIMINATOR_TYPE = eINSTANCE.getDiscriminatorType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.EnumType <em>Enum Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.EnumType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getEnumType()
		 * @generated
		 */
		EEnum ENUM_TYPE = eINSTANCE.getEnumType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.FetchType <em>Fetch Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.FetchType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getFetchType()
		 * @generated
		 */
		EEnum FETCH_TYPE = eINSTANCE.getFetchType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.GenerationType <em>Generation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.GenerationType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getGenerationType()
		 * @generated
		 */
		EEnum GENERATION_TYPE = eINSTANCE.getGenerationType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.InheritanceType <em>Inheritance Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.InheritanceType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getInheritanceType()
		 * @generated
		 */
		EEnum INHERITANCE_TYPE = eINSTANCE.getInheritanceType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.TemporalType <em>Temporal Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.TemporalType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getTemporalType()
		 * @generated
		 */
		EEnum TEMPORAL_TYPE = eINSTANCE.getTemporalType();

		/**
		 * The meta object literal for the '{@link org.eclipse.emf.teneo.annotations.pannotation.OptimizerType <em>Optimizer Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.teneo.annotations.pannotation.OptimizerType
		 * @see org.eclipse.emf.teneo.annotations.pannotation.impl.PannotationPackageImpl#getOptimizerType()
		 * @generated
		 */
		EEnum OPTIMIZER_TYPE = eINSTANCE.getOptimizerType();

	}

	/**
	 * @return Returns the eModelElement s types the given annotation applies to. Returns null if the given EClass is
	 *         not annotation Class.
	 */
	EClass[] getTargets(EClass pAnnotationEClass);

	/**
	 * @param pAnnotationEClass
	 *            EClass of the annotation.
	 * @param targetElementEClass
	 *            EClass of the target.
	 * @return Returns true if and only pAnnotationEClass is the EClass of a PAnnotation and targetElementEClass is the
	 *         EClass of a possible target of such annotation.
	 */
	boolean isTarget(EClass pAnnotationEClass, EClass targetElementEClass);

} // PannotationPackage
