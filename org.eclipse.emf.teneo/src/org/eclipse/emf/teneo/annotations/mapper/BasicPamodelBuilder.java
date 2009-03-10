/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Martin Taal
 *   Davide Marchignoli
 * </copyright>
 *
 * $Id: BasicPamodelBuilder.java,v 1.4 2008/02/28 07:08:32 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelFactory;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Convience class for building a <code>PAnnotatedModel</code>.
 * 
 * @author <a href="mailto:marchign at elver.org">Davide Marchignoli</a>
 */
public class BasicPamodelBuilder implements ExtensionPoint {

	private PAnnotatedModel target = null;

	/**
	 * Uses an empty freshly instantiated PAnnotatedModel as target.
	 */
	public BasicPamodelBuilder() {
		setPAnnotatedModel(createPAnnotatedModel());
	}

	/** Create the pAnnotatedModel */
	public PAnnotatedModel createPAnnotatedModel() {
		return PamodelFactory.eINSTANCE.createPAnnotatedModel();
	}

	/**
	 * Uses the given PAnnotatedMmodel as target.
	 */
	public BasicPamodelBuilder(PAnnotatedModel target) {
		setPAnnotatedModel(target);
	}

	/**
	 * Sets the target PAnnotatedModel
	 */
	public void setPAnnotatedModel(PAnnotatedModel target) {
		this.target = target;
	}

	/**
	 * @return the target model.
	 */
	public PAnnotatedModel getPAnnotatedModel() {
		return target;
	}

	/**
	 * @return a <code>PAnnotatedModelElement</code> assoiciated to the given
	 *         <code>EModelElement</code>. The element is created only if not already present in
	 *         the model.
	 */
	protected PAnnotatedEModelElement create(EModelElement eModelElement) {
		PAnnotatedEModelElement paElement = target.getPAnnotated(eModelElement);
		if (paElement == null) {
			// Factor out actual model creation so that extensions can create their
			// own model elements.
			paElement = doCreate(eModelElement);
		}
		return paElement;
	}

	/**
	 * @return A newly created PAnnotatedEModelElement. This method is only responsible for the
	 *         actual creation (and initialization) of this object. No other logic should happen
	 *         here. This allows subclasses to alter how objects are created. If you'd like to alter
	 *         any other logic around the creation, you should override the <code>create</code>
	 *         method(s).
	 * @throws AssertionError
	 */
	protected PAnnotatedEModelElement doCreate(EModelElement eModelElement) throws AssertionError {
		final EClass eModelElementEClass = eModelElement.eClass();
		PAnnotatedEModelElement paElement;
		switch (eModelElementEClass.getClassifierID()) {
			case EcorePackage.EATTRIBUTE:
				paElement = PamodelFactory.eINSTANCE.createPAnnotatedEAttribute();
				break;
			case EcorePackage.EREFERENCE:
				paElement = PamodelFactory.eINSTANCE.createPAnnotatedEReference();
				break;
			case EcorePackage.ECLASS:
				paElement = PamodelFactory.eINSTANCE.createPAnnotatedEClass();
				break;
			case EcorePackage.EPACKAGE:
				paElement = PamodelFactory.eINSTANCE.createPAnnotatedEPackage();
				break;
			case EcorePackage.EENUM:
			case EcorePackage.EDATA_TYPE:
				paElement = PamodelFactory.eINSTANCE.createPAnnotatedEDataType();
				break;
			default:
				throw new AssertionError("Trying to build PAnnotatedEModelElement for a " + eModelElementEClass);
		}
		paElement.setModelElement((ENamedElement) eModelElement);
		return paElement;
	}

	/**
	 * @return a <code>PAnnotatedEPackage</code> associated to the given <code>EPackage</code>
	 *         and adds it the model.
	 *         <p>
	 *         The <code>PAnnotatedEPackage</code> is created only if not already present in the
	 *         model.
	 */
	protected PAnnotatedEPackage pElement(EPackage ePackage) {
		PAnnotatedEPackage pPackage = (PAnnotatedEPackage) create(ePackage);
		if (pPackage.eContainer() == null) {
			target.getPaEPackages().add(pPackage);
		}
		return pPackage;
	}

	/**
	 * @return a <code>PAnnotatedEClass</code> associated to the given <code>EClass</code> and
	 *         adds it the model.
	 *         <p>
	 *         The <code>PAnnotatedEClass</code> is created only if not already present in the
	 *         model.
	 *         <p>
	 *         The operation may involve the creation of a <code>PAnnotatedEPackage</code>
	 *         associated to the given <code>EClass</code> package.
	 */
	protected PAnnotatedEClass pElement(EClass eClass) {
		PAnnotatedEClass pClass = (PAnnotatedEClass) create(eClass);
		pElement(eClass.getEPackage()).getPaEClasses().add(pClass);
		return pClass;
	}

	/**
	 * @return a <code>PAnnotatedEStructuralFeature</code> associated to the given
	 *         <code>EStructuralFeature</code> and adds it the model.
	 *         <p>
	 *         The <code>PAnnotatedEStructuralFeature</code> is created only if not already
	 *         present in the model.
	 *         <p>
	 *         The operation may involve the creation of a <code>PAnnotatedEPackage</code> and a
	 *         <code>PAnnotatedEClass</code>.
	 */
	protected PAnnotatedEModelElement pElement(EStructuralFeature eFeature) {
		PAnnotatedEStructuralFeature pFeature = (PAnnotatedEStructuralFeature) create(eFeature);
		pElement(eFeature.getEContainingClass()).getPaEStructuralFeatures().add(pFeature);
		return pFeature;
	}

	/**
	 * @return a <code>PAnnotatedEStructuralFeature</code> associated to the given
	 *         <code>EStructuralFeature</code> and adds it the model.
	 *         <p>
	 *         The <code>PAnnotatedEStructuralFeature</code> is created only if not already
	 *         present in the model.
	 *         <p>
	 *         The operation may involve the creation of a <code>PAnnotatedEPackage</code> and a
	 *         <code>PAnnotatedEClass</code>.
	 */
	protected PAnnotatedEDataType pElement(EDataType eDataType) {
		PAnnotatedEDataType pDataType = (PAnnotatedEDataType) create(eDataType);
		pElement(eDataType.getEPackage()).getPaEDataTypes().add(pDataType);
		return pDataType;
	}

	/**
	 * @return a <code>PAnnotatedEModelElement</code> associated to the given
	 *         <code>EModelElement</code> and adds it the model.
	 * @see #pElement(EPackage)
	 * @see #pElement(EClass)
	 * @see #pElement(EStructuralFeature)
	 */
	protected PAnnotatedEModelElement pElement(final EModelElement eElement) throws AssertionError {
		PAnnotatedEModelElement pElement = null;
		switch (eElement.eClass().getClassifierID()) {
			case EcorePackage.EATTRIBUTE:
			case EcorePackage.EREFERENCE:
				pElement = pElement((EStructuralFeature) eElement);
				break;
			case EcorePackage.ECLASS:
				pElement = pElement((EClass) eElement);
				break;
			case EcorePackage.EPACKAGE:
				pElement = pElement((EPackage) eElement);
				break;
			case EcorePackage.EDATA_TYPE:
				pElement = pElement((EDataType) eElement);
				break;
			default:
				throw new AssertionError("Trying to build PAnnotatedEModelElement for a " + eElement.eClass());
		}
		return pElement;
	}

	/**
	 * Builds a <code>PAnnotatedEPackage</code> associated to the given <code>EPackage</code>
	 * (if such an <code>PAnnotatedEPackage</code> does not yet exists) and adds it to the target
	 * model.
	 */
	public void add(EPackage ePackage) {
		pElement(ePackage);
	}

	/**
	 * Builds a <code>PAnnotatedEClass</code> associated to the given <code>EClass</code> (if
	 * such an <code>PAnnotatedEClass</code> does not yet exists) and adds it to the target model.
	 * 
	 * <p>
	 * The creation of a new <code>PAnnotatedEClass</code> may involve the creation of a
	 * <code>PAnnotatedEPackage</code> associated to the containing <code>EPackage</code> of the
	 * given class.
	 */
	public void add(EClass eClass) {
		pElement(eClass);
	}

	/**
	 * Add to the the target model a new <code>PAnnotatedEStructuralFeature</code> refering to the
	 * given EStructuralFeature.
	 * 
	 * <p>
	 * A PAnnotatedEClass and a PAnnotatedEPackage for the containing EClass and EPackage are added
	 * if needed.
	 * 
	 * <p>
	 * The added element have no annotations. Elements for which a corresponding PAnnotatedElement
	 * is already present in the target model are ignored.
	 */
	public void add(EStructuralFeature eFeature) {
		pElement(eFeature);
	}

	/**
	 * Add the given annotation to the given PAnnotatedEModelElement.
	 * 
	 * @throws IllegalArgumentException
	 *             if the given PAnnotation is not admitted for the given PAnnotatedEModelElement.
	 *             protected void setPAnnotation(PAnnotatedEModelElement pElement, PAnnotation
	 *             pAnnotation) { EReference pAnnotationRef =
	 *             PamodelPackage.eINSTANCE.pAnnotationReference(pElement.eClass(),
	 *             pAnnotation.eClass()); if (pAnnotationRef == null) throw new
	 *             IllegalArgumentException("PAnnotation of type '" + pAnnotation.eClass() + "' does
	 *             not apply to elements of type '" + pElement.eClass() + "'");
	 *             pElement.eSet(pAnnotationRef, pAnnotation); }
	 */

	/**
	 * Add the given PAnnotation to the target model.
	 * 
	 * <p>
	 * This operation may involve the addition to the model of a newly created
	 * PAnnotatedEModelElement for the PAnnotation EModelElement.
	 * 
	 * @throws NullPointerException
	 *             if either <code>pAnnotation</code> or
	 *             <code>pAnnotation.getEModelElement()</code> are null.
	 * @throws IllegalArgumentException
	 *             if the given <code>PAnnotation</code> references an invalid
	 *             <code>PAnnotatedElement</code> public void add(PAnnotation pAnnotation) {
	 *             PAnnotatedEModelElement pElement = pElement(pAnnotation.getEModelElement());
	 *             setPAnnotation(pElement, pAnnotation); }
	 */

	/**
	 * Add to the the target model a new PAnnotatedPackage refering to the given EPackage.
	 * Recursively adds a PAnnotatedEClass for each EClass in the given EPackage (see
	 * {@link addEClass}).
	 * 
	 * <p>
	 * The added elements have no annotations. Elements for which a corresponding PAnnotatedElement
	 * is already present in the target model are ignored.
	 */
	public void addRecurse(EPackage ePackage) {
		PAnnotatedEPackage paPackage = pElement(ePackage);
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				addRecurse(paPackage, (EClass) eClassifier);
			} else if (eClassifier instanceof EDataType) {
				pElement((EDataType) eClassifier);
			}
		}
	}

	/**
	 * used by {@link #addRecurse(EPackage)} to avoid recomputing the container multiple times.
	 */
	protected void addRecurse(PAnnotatedEPackage paPackage, EClass eClass) {
		PAnnotatedEClass paClass = (PAnnotatedEClass) create(eClass);
		if (paClass.eContainer() == null) {
			paPackage.getPaEClasses().add(paClass);
		}
		for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
			add(paClass, eStructuralFeature);
		}
	}

	/**
	 * Add to the the target model a new PAnnotatedPackage refering to the given EClass. Recursively
	 * adds a PAnnotatedEStructuralFeature for each EStructuralFeature in the given EClass (see
	 * {@link addEStructuralFeature}).
	 * 
	 * <p>
	 * A PAnnotatedEPackage for the containng EPackage is added if needed.
	 * 
	 * <p>
	 * The added elements have no annotations.
	 * 
	 * <p>
	 * Elements for which a corresponding PAnnotatedElement is already present in the target model
	 * are ignored. public void addRecurse(EClass eClass) { addRecurse((PAnnotatedEPackage)
	 * pElement(eClass), eClass); }
	 */

	/**
	 * used by {@link #addRecurse(EClass)} to avoid recomputing the container multiple times.
	 */
	protected void add(PAnnotatedEClass paClass, EStructuralFeature eFeature) {
		PAnnotatedEStructuralFeature paFeature = (PAnnotatedEStructuralFeature) create(eFeature);
		if (paFeature.eContainer() == null) {
			paClass.getPaEStructuralFeatures().add(paFeature);
		}
	}
}
