/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotatedEPackage.java,v 1.13 2008/10/13 05:35:42 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.TableGenerator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PAnnotated EPackage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaModel <em>Pa Model</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getModelEPackage <em>Model EPackage</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaEClasses <em>Pa EClasses</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getSequenceGenerators <em>Sequence Generators</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getTableGenerators <em>Table Generators</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaEDataTypes <em>Pa EData Types</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getSequenceStyleGenerators <em>Sequence Style Generators</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AtMostOneEClass'"
 * @generated
 */
public interface PAnnotatedEPackage extends PAnnotatedEModelElement {
	/**
	 * Returns the value of the '<em><b>Pa Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel#getPaEPackages <em>Pa EPackages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pa Model</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pa Model</em>' container reference.
	 * @see #setPaModel(PAnnotatedModel)
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_PaModel()
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel#getPaEPackages
	 * @model opposite="paEPackages" transient="false"
	 *        annotation="teneo/internal/PersistenceMapping ignore='true'"
	 * @generated
	 */
	PAnnotatedModel getPaModel();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaModel <em>Pa Model</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pa Model</em>' container reference.
	 * @see #getPaModel()
	 * @generated
	 */
	void setPaModel(PAnnotatedModel value);

	/**
	 * Returns the value of the '<em><b>Annotated EPackage</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotated EPackage</em>' reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Annotated EPackage</em>' reference.
	 * @see #setModelEPackage(EPackage)
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_AnnotatedEPackage()
	 * @model required="true" annotation="teneo/internal/PersistenceMapping ignore='true'"
	 * @generated
	 */
	EPackage getModelEPackage();

	/**
	 * Returns the value of the '<em><b>Pa EClasses</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPaEPackage <em>Pa EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pa EClasses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pa EClasses</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_PaEClasses()
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass#getPaEPackage
	 * @model opposite="paEPackage" containment="true"
	 *        annotation="teneo/internal/PersistenceMapping ignore='true'"
	 * @generated
	 */
	EList<PAnnotatedEClass> getPaEClasses();

	/**
	 * Returns the value of the '<em><b>Sequence Generators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Sequence Generators</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Generators</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_SequenceGenerators()
	 * @model containment="true"
	 * @generated
	 */
	EList<SequenceGenerator> getSequenceGenerators();

	/**
	 * Returns the value of the '<em><b>Table Generators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.TableGenerator}.
	 * <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Table Generators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Generators</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_TableGenerators()
	 * @model containment="true"
	 * @generated
	 */
	EList<TableGenerator> getTableGenerators();

	/**
	 * Returns the value of the '<em><b>Pa EData Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getPaEPackage <em>Pa EPackage</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pa EData Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pa EData Types</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_PaEDataTypes()
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType#getPaEPackage
	 * @model opposite="paEPackage" containment="true"
	 *        annotation="teneo/internal/PersistenceMapping ignore='true'"
	 * @generated
	 */
	EList<PAnnotatedEDataType> getPaEDataTypes();

	/**
	 * Returns the value of the '<em><b>Sequence Style Generators</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Style Generators</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Style Generators</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedEPackage_SequenceStyleGenerators()
	 * @model containment="true"
	 * @generated
	 */
	EList<SequenceStyleGenerator> getSequenceStyleGenerators();
} // PAnnotatedEPackage
