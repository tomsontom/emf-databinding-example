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
 * </copyright>
 *
 * $Id: AbstractProcessingContext.java,v 1.8 2008/12/16 20:40:34 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pannotation.AssociationOverride;
import org.eclipse.emf.teneo.annotations.pannotation.AttributeOverride;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;

/**
 * ProcessingContext which handles attributes overrides.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */

public class AbstractProcessingContext {

	/** The logger for all these exceptions */
	protected static final Log log = LogFactory
			.getLog(AbstractProcessingContext.class);

	/** The current list of overrides */
	private Map<String, Object> currentOverrides = new HashMap<String, Object>();

	/**
	 * Pushes the current overrides on the stack, to be popped later, this is to
	 * handle nested components
	 */
	private Stack<Map<String, Object>> overrideStack = new Stack<Map<String, Object>>();

	/**
	 * Pushes the current embedding feature on the stack, to be popped later,
	 * this is to handle nested components and automatic renaming of props
	 */
	private Stack<PAnnotatedEStructuralFeature> embeddingFeatureStack = new Stack<PAnnotatedEStructuralFeature>();

	/**
	 * Add attribute overrides, happens for each mapped superclass and each
	 * embedded component
	 */
	public void addAttributeOverrides(EList<AttributeOverride> aos) {
		if (aos != null) {
			for (AttributeOverride override : aos) {
				currentOverrides.put(override.getName(), override.getColumn());
			}
		}
	}

	/** Add association overrides, for each mapped subclass */
	public void addAssociationOverrides(EList<AssociationOverride> overrides) {
		if (overrides != null) {
			for (AssociationOverride override : overrides) {
				currentOverrides.put(override.getName(), override
						.getJoinColumns());
			}
		}
	}

	/**
	 * Pushes the current overrides on the stack, to be popped later, this is to
	 * handle nested components
	 */
	public void pushOverrideOnStack() {
		overrideStack.push(new HashMap<String, Object>(currentOverrides));
	}

	/** Pop the current overrides on the stack */
	public void popOverrideStack() {
		currentOverrides = overrideStack.pop();
	}

	/** Pushes the current embedding feature on the stack */
	public void pushEmbeddingFeature(PAnnotatedEStructuralFeature er) {
		embeddingFeatureStack.push(er);
	}

	/** Pops the current embedding feature from the stack */
	public void popEmbeddingFeature() {
		embeddingFeatureStack.pop();
	}

	/** Peeks for the current embedding feature */
	public PAnnotatedEStructuralFeature getEmbeddingFeature() {
		if (embeddingFeatureStack.isEmpty()) {
			return null;
		}
		return embeddingFeatureStack.peek();
	}

	/** Clear the override is done before an entity is processed */
	public void clearOverrides() {
		currentOverrides.clear();
	}

	/** Return the overridden column for the passed attribute */
	public Column getAttributeOverride(PAnnotatedEStructuralFeature paFeature) {
		return getAttributeOverride(paFeature.getModelElement().getName());
	}

	/** Return the overridden Joincolumns for the indicated featureName */
	public Column getAttributeOverride(String featureName) {
		final Column c = (Column) currentOverrides.get(featureName);
		if (c == null) {
			final Object o = getFromStack(featureName);
			if (o != null && o instanceof Column) {
				return (Column) o;
			}
		}
		return c;
	}

	/** Return the overridden JoinColumns for this reference */
	public List<JoinColumn> getAssociationOverrides(
			PAnnotatedEReference paReference) {
		return getAssociationOverrides(paReference.getModelEReference()
				.getName());
	}

	@SuppressWarnings("unchecked")
	public List<JoinColumn> getAssociationOverrides(String featureName) {
		final List<JoinColumn> jcs = (List<JoinColumn>) currentOverrides
				.get(featureName);
		if (jcs == null) {
			final Object o = getFromStack(featureName);
			if (o instanceof List<?>) {
				return (List<JoinColumn>) o;
			}
		}
		return jcs;
	}

	private Object getFromStack(String name) {
		for (int i = (overrideStack.size() - 1); i >= 0; i--) {
			final Map<String, Object> checkOverride = overrideStack.get(i);
			final Object o = checkOverride.get(name);
			if (o != null) {
				return o;
			}
		}
		return null;
	}

	/**
	 * This method returns all inherited features which need to be added to the
	 * mapping of the aclass itself. The method makes a distinction makes a
	 * distinction between the first supertype (the first one in the list) and
	 * later ones. The features of the first type are only added to the mapping
	 * if the first type is a mappedsuperclass, in all other cases the features
	 * of the first type are not mapped in the aclass itself because they are
	 * inherited (the mapping describes the inheritance relation). For the other
	 * supertypes (located at index 1 and up in getESuperTypes) the features are
	 * mapped as properties in the class itself. The superEntity is the super
	 * aclass denoted as the real supertype extended by teneo.
	 */
	public List<PAnnotatedEStructuralFeature> getInheritedFeatures(
			PAnnotatedEClass aClass) {
		// if no supertypes then there are no inherited features
		final EClass eclass = aClass.getModelEClass();
		if (eclass.getESuperTypes().size() == 0) {
			return new ArrayList<PAnnotatedEStructuralFeature>();
		}
		log
				.debug("Determining inherited features which are mapped locally for "
						+ aClass.getModelEClass().getName());
		final List<EStructuralFeature> inheritedFeatures = new ArrayList<EStructuralFeature>(
				eclass.getEAllStructuralFeatures());

		// remove all the features of the eclass itself
		inheritedFeatures.removeAll(eclass.getEStructuralFeatures());

		// check if the type has a supertype (a non-transient,
		// non-mappedsuperclass, if so then
		// remove all features inherited from the first supertype
		// as this inheritance is done in the mapping file
		if (aClass.getPaSuperEntity() != null) {
			inheritedFeatures.removeAll(aClass.getPaSuperEntity()
					.getModelEClass().getEAllStructuralFeatures());
		}

		// get all efeatures from direct mappedsuperclasses
		// the id feature inherited from a direct mappedsuperclass should be
		// maintained in other cases the id features are not mapped locally.
		// The system can also ignore this and let the user be more carefull not
		// to
		// add id features here and there in the inheritance structure but this
		// is
		// more robust
		removeIdFeatures(aClass, inheritedFeatures);

		// convert the result
		final PAnnotatedModel paModel = aClass.getPaModel();
		final ArrayList<PAnnotatedEStructuralFeature> result = new ArrayList<PAnnotatedEStructuralFeature>();
		for (EStructuralFeature esf : inheritedFeatures) {
			result.add(paModel.getPAnnotated(esf));
		}

		return result;
	}

	/**
	 * Remove all id-features not inherited from a direct mapped superclass, and
	 * add the features from the mapped superclass
	 */
	private void removeIdFeatures(PAnnotatedEClass aClass,
			List<EStructuralFeature> inheritedFeatures) {
		// first get all the mapped superclasses
		final ArrayList<EClass> mappedSuperEClasses = new ArrayList<EClass>();
		for (EClass superEClass : aClass.getModelEClass().getESuperTypes()) {
			final PAnnotatedEClass superPAClass = aClass.getPaModel()
					.getPAnnotated(superEClass);
			if (superPAClass != null
					&& superPAClass.getMappedSuperclass() != null) {
				mappedSuperEClasses.add(superPAClass.getModelEClass());
			}
		}

		// now get all the efeatures of the mappedsuperclasses to prevent any id
		// features from them being removed, only do that when the aclass does
		// not
		// have a real super type, in that case the id can be inherited from the
		// mappedsuperclass
		final ArrayList<EStructuralFeature> mappedSuperFeatures = new ArrayList<EStructuralFeature>();
		if (aClass.getPaSuperEntity() == null
				|| aClass.getPaSuperEntity().getMappedSuperclass() != null) {
			for (EClass mappedSuperEClass : mappedSuperEClasses) {
				mappedSuperFeatures.removeAll(mappedSuperEClass
						.getEAllStructuralFeatures());
				mappedSuperFeatures.addAll(mappedSuperEClass
						.getEAllStructuralFeatures());
			}
		}

		// now remove all id features not coming from a direct mapped superclass
		final ArrayList<EStructuralFeature> toRemove = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature esf : inheritedFeatures) {
			final PAnnotatedEStructuralFeature pef = aClass.getPaModel()
					.getPAnnotated(esf);

			if (pef instanceof PAnnotatedEAttribute
					&& ((PAnnotatedEAttribute) pef).getId() != null
					&& !mappedSuperFeatures.contains(esf)) {
				toRemove.add(esf);
			}
		}
		inheritedFeatures.removeAll(toRemove);
	}

	//
	// /** Returns all mapped super classes */
	// public List<PAnnotatedEClass> getMappedSuperClasses(PAnnotatedEClass
	// entity) {
	// final List<PAnnotatedEClass> result = new ArrayList<PAnnotatedEClass>();
	// for (EClass superEClass : entity.getAnnotatedEClass().getESuperTypes()) {
	// final PAnnotatedEClass superPAClass = entity.getPaModel()
	// .getPAnnotated(superEClass);
	// if (superPAClass != null
	// && superPAClass.getMappedSuperclass() != null) {
	// result.add(superPAClass);
	// // and add the mapped super classes of the mapped superclass
	// // note that only the unbroken chain of mappedsuperclasses is
	// // added to the result, if there
	// // is a non-mappedsuperclass in the inheritance then it stops
	// // there
	// // issue also identified by Douglas Bitting
	// result.addAll(getMappedSuperClasses(superPAClass));
	// }
	// }
	//
	// return result;
	// }

	/**
	 * Returns true if the eclass only has mappedsuperclasses without id
	 * annotated property
	 */
	public boolean mustAddSyntheticID(PAnnotatedEClass entity) {
		if (entity.hasIdAnnotatedFeature()) {
			return false;
		}
		for (EClass superEClass : entity.getModelEClass().getEAllSuperTypes()) {
			final PAnnotatedEClass superPAClass = entity.getPaModel()
					.getPAnnotated(superEClass);
			if (superPAClass != null
					&& superPAClass.getMappedSuperclass() == null) {
				return false;
			} else if (superPAClass != null
					&& superPAClass.getMappedSuperclass() != null) {
				if (superPAClass.hasIdAnnotatedFeature()) {
					return false;
				}
			}
		}

		return true;
	}
}