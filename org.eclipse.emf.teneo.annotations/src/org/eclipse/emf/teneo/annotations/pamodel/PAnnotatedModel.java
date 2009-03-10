/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotatedModel.java,v 1.15 2008/10/13 05:35:42 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.TableGenerator;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PAnnotated Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel#getPaEPackages <em>Pa EPackages</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedModel()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AtMostOnePackage DistinctGenerators'"
 * @generated
 */
public interface PAnnotatedModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Pa EPackages</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaModel <em>Pa Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pa EPackages</em>' containment reference list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pa EPackages</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage#getPAnnotatedModel_PaEPackages()
	 * @see org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage#getPaModel
	 * @model opposite="paModel" containment="true"
	 * @generated
	 */
	EList<PAnnotatedEPackage> getPaEPackages();

	/**
	 * @return Returns the unique <code>PAnnotatedEDataType</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EDataType</code>.
	 */
	public PAnnotatedEDataType getPAnnotated(EDataType e);

	/**
	 * @return Returns the unique <code>PAnnotatedEModelElement</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EModelElement</code>.
	 */
	PAnnotatedEModelElement getPAnnotated(EModelElement e);

	/**
	 * @return Returns the unique <code>PAnnotatedEPackage</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EPackage</code>.
	 */
	PAnnotatedEPackage getPAnnotated(EPackage p);

	/**
	 * @return Returns the unique <code>PAnnotatedEClass</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EClass</code>.
	 */
	PAnnotatedEClass getPAnnotated(EClass c);

	/**
	 * @return Returns the unique <code>PAnnotatedEStructuralFeature</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EStructuralFeature</code>.
	 */
	PAnnotatedEStructuralFeature getPAnnotated(EStructuralFeature f);

	/**
	 * @return Returns the unique <code>PAnnotatedEAttribute</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EAttribute</code>.
	 */
	PAnnotatedEAttribute getPAnnotated(EAttribute f);

	/**
	 * @return Returns the unique <code>PAnnotatedEReference</code> associated (via the
	 *         <code>annotatedElement</code> feature) to the given <code>EReference</code>.
	 */
	PAnnotatedEReference getPAnnotated(EReference r);

	/**
	 * Returns a sequence generator on the basis of its name, if not found then an exception is
	 * thrown. efeature is passed for debugging purposes.
	 */
	public SequenceGenerator getSequenceGenerator(EStructuralFeature efeature, String name);

	public SequenceStyleGenerator getSequenceStyleGenerator(EStructuralFeature efeature, String name);

	/**
	 * Returns a table generator on the basis of its name, if not found then an exception is thrown.
	 * efeature is passed for debugging purposes.
	 */
	public TableGenerator getTableGenerator(EStructuralFeature efeature, String name);

	/**
	 * @return the initialized
	 */
	public boolean isInitialized();

	/**
	 * @param initialized
	 *            the initialized to set
	 */
	public void setInitialized(boolean initialized);

	/** Dump this model to xml */
	public String toXML();

	/** Return the eclass annotated with this entity name. If not found then an exception is thrown */
	public EClass getEClass(String entityName);

	/** @return true if there is annotated eclass with the passed entityname */
	public boolean hasEClass(String entityName);

	/**
	 * @return the EClassifier for a certain name. First the eclasses are searched using the name as
	 *         the entityname then the edatatypes are searched.
	 */
	public EClassifier getEClassifier(String name);

	/**
	 * @return true if there is an EClass with the name as entityname or an EDataType with the name
	 *         passed as a parameter.
	 */
	public boolean hasEClassifier(String name);

	/** Return the mapping from epackage elements to annotated package elements */
	public Map<EModelElement, PAnnotatedEModelElement> getModelElementMapping();

	/** Merge the passed annotated model in the one present */
	public void merge(PAnnotatedModel pModel);

} // PAnnotatedModel
