/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedEPackage.java,v 1.8 2008/04/23 15:44:26 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator;
import org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Hb Annotated EPackage</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage#getHbGenericGenerators <em>Hb Generic Generators</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage#getHbTypeDef <em>Hb Type Def</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage#getHbNamedQuery <em>Hb Named Query</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage#getFilterDef <em>Filter Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEPackage()
 * @model
 * @generated
 */
public interface HbAnnotatedEPackage extends PAnnotatedEPackage {
	/**
	 * Returns the value of the '<em><b>Hb Generic Generators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.hibernate.hbannotation.GenericGenerator}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Hb Generic Generators</em>' containment reference isn't clear, there really should
	 * be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Generic Generators</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEPackage_HbGenericGenerators()
	 * @model containment="true"
	 * @generated
	 */
	EList<GenericGenerator> getHbGenericGenerators();

	/**
	 * Returns the value of the '<em><b>Hb Type Def</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Type Def</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Type Def</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEPackage_HbTypeDef()
	 * @model containment="true"
	 * @generated
	 */
	EList<TypeDef> getHbTypeDef();

	/**
	 * Returns the value of the '<em><b>Hb Named Query</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Named Query</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Named Query</em>' reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEPackage_HbNamedQuery()
	 * @model
	 * @generated
	 */
	EList<NamedQuery> getHbNamedQuery();

	/**
	 * Returns the value of the '<em><b>Filter Def</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Def</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Def</em>' reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedEPackage_FilterDef()
	 * @model
	 * @generated
	 */
	EList<FilterDef> getFilterDef();

} // HbAnnotatedEPackage
