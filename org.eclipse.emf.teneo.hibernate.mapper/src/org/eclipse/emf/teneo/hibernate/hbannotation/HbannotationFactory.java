/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbannotationFactory.java,v 1.12 2009/03/15 15:08:01 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 * @see org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage
 * @generated
 */
public interface HbannotationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	HbannotationFactory eINSTANCE = org.eclipse.emf.teneo.hibernate.hbannotation.impl.HbannotationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Cascade</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>Cascade</em>'.
	 * @generated
	 */
	Cascade createCascade();

	/**
	 * Returns a new object of class '<em>Collection Of Elements</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collection Of Elements</em>'.
	 * @generated
	 */
	CollectionOfElements createCollectionOfElements();

	/**
	 * Returns a new object of class '<em>Hb Map Key</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Hb Map Key</em>'.
	 * @generated
	 */
	HbMapKey createHbMapKey();

	/**
	 * Returns a new object of class '<em>Parameter</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>Parameter</em>'.
	 * @generated
	 */
	Parameter createParameter();

	/**
	 * Returns a new object of class '<em>Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>Type</em>'.
	 * @generated
	 */
	Type createType();

	/**
	 * Returns a new object of class '<em>Where</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>Where</em>'.
	 * @generated
	 */
	Where createWhere();

	/**
	 * Returns a new object of class '<em>Id Bag</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>Id Bag</em>'.
	 * @generated
	 */
	IdBag createIdBag();

	/**
	 * Returns a new object of class '<em>Generic Generator</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>Generic Generator</em>'.
	 * @generated
	 */
	GenericGenerator createGenericGenerator();

	/**
	 * Returns a new object of class '<em>Cache</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>Cache</em>'.
	 * @generated
	 */
	Cache createCache();

	/**
	 * Returns a new object of class '<em>Type Def</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>Type Def</em>'.
	 * @generated
	 */
	TypeDef createTypeDef();

	/**
	 * Returns a new object of class '<em>Fetch</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>Fetch</em>'.
	 * @generated
	 */
	Fetch createFetch();

	/**
	 * Returns a new object of class '<em>On Delete</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>On Delete</em>'.
	 * @generated
	 */
	OnDelete createOnDelete();

	/**
	 * Returns a new object of class '<em>Proxy</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>Proxy</em>'.
	 * @generated
	 */
	Proxy createProxy();

	/**
	 * Returns a new object of class '<em>Index</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return a new object of class '<em>Index</em>'.
	 * @generated
	 */
	Index createIndex();

	/**
	 * Returns a new object of class '<em>Generated</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generated</em>'.
	 * @generated
	 */
	Generated createGenerated();

	/**
	 * Returns a new object of class '<em>Named Query</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Named Query</em>'.
	 * @generated
	 */
	NamedQuery createNamedQuery();

	/**
	 * Returns a new object of class '<em>Filter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter</em>'.
	 * @generated
	 */
	Filter createFilter();

	/**
	 * Returns a new object of class '<em>Param Def</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Param Def</em>'.
	 * @generated
	 */
	ParamDef createParamDef();

	/**
	 * Returns a new object of class '<em>Filter Def</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Filter Def</em>'.
	 * @generated
	 */
	FilterDef createFilterDef();

	/**
	 * Returns a new object of class '<em>Discriminator Formula</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Discriminator Formula</em>'.
	 * @generated
	 */
	DiscriminatorFormula createDiscriminatorFormula();

	/**
	 * Returns a new object of class '<em>Natural Id</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Natural Id</em>'.
	 * @generated
	 */
	NaturalId createNaturalId();

	/**
	 * Returns a new object of class '<em>Map Key Many To Many</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Map Key Many To Many</em>'.
	 * @generated
	 */
	MapKeyManyToMany createMapKeyManyToMany();

	/**
	 * Returns a new object of class '<em>Force Discriminator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Force Discriminator</em>'.
	 * @generated
	 */
	ForceDiscriminator createForceDiscriminator();

	/**
	 * Returns a new object of class '<em>Immutable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Immutable</em>'.
	 * @generated
	 */
	Immutable createImmutable();

	/**
	 * Returns a new object of class '<em>Formula</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formula</em>'.
	 * @generated
	 */
	Formula createFormula();

	/**
	 * Returns a new object of class '<em>Not Found</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Not Found</em>'.
	 * @generated
	 */
	NotFound createNotFound();

	/**
	 * Returns a new object of class '<em>Hb Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Hb Entity</em>'.
	 * @generated
	 */
	HbEntity createHbEntity();

	/**
	 * Returns a new object of class '<em>Batch Size</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Batch Size</em>'.
	 * @generated
	 */
	BatchSize createBatchSize();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	HbannotationPackage getHbannotationPackage();

} // HbannotationFactory
