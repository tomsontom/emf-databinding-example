/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal - Initial API and
 * implementation </copyright> $Id: StrategyUtil.java,v 1.6 2008/06/02 07:15:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.strategy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Contains different util methods related to strategies.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class StrategyUtil {

	/** Returns the entity name based on a specific logic */
	public static String getEntityName(EntityNameStrategy ens, PersistenceOptions po, PAnnotatedModel paModel,
			EClass eclass) {
		if (eclass == null) {
			throw new IllegalArgumentException(
				"Passed eclass is null."
						+ "This can occur if epackages which refer to eachother are placed in different ecore/xsd files "
						+ "and they are not read using one resource set. The reference from one epackage to another must be "
						+ "resolvable by EMF.");
		}

		// ok, here we figure out if it is an EMap. if so, we return the
		// destination child name, not the keyToValueEntry wrapper
		final PAnnotatedEClass aclass = (paModel != null ? paModel.getPAnnotated(eclass) : null);
		if (aclass == null && paModel != null) { // happens when the eclass is EObject itself
			return ens.toEntityName(eclass);
		}
		if (po.isMapEMapAsTrueMap() && StoreUtil.isMapEntry(eclass)) {
			// ok, it is an EMAp, get the annotaetd class of the child
			EStructuralFeature feature = eclass.getEStructuralFeature("value");
			if (feature instanceof EReference) {
				return getEntityName(ens, po, paModel, ((EReference) feature).getEReferenceType());
			}
			return ((EAttribute) feature).getEType().getInstanceClassName();
		}
//
// if (aclass != null && aclass.getEntity() != null && aclass.getEntity().getName() != null) {
// return aclass.getEntity().getName();
// }
		return ens.toEntityName(eclass);
	}

	/**
	 * Returns the list of names of id props of the eclass, walks the inheritance tree to find the
	 * id feature, if none is found then the
	 */
	public static List<String> getIDFeaturesNames(PAnnotatedEClass aClass, String optionDefaultIDFeatureName) {
		final List<String> list = getIDFeaturesNamesRecurse(aClass);
		// See, 172756
		if (list.isEmpty()) {
			list.add(optionDefaultIDFeatureName);
		}
		return list;
	}

	/** Internal will walk the inheritance tree to find the id feature */
	private static List<String> getIDFeaturesNamesRecurse(PAnnotatedEClass aClass) {
		final ArrayList<String> list = new ArrayList<String>();
		for (EStructuralFeature feature : aClass.getModelEClass().getEStructuralFeatures()) {
			final PAnnotatedEStructuralFeature aStructuralFeature = aClass.getPaModel().getPAnnotated(feature);
			if (aStructuralFeature instanceof PAnnotatedEAttribute) {
				final PAnnotatedEAttribute aAttribute = (PAnnotatedEAttribute) aStructuralFeature;
				final String attrName = aAttribute.getModelEAttribute().getName();
				if (aAttribute.getId() != null && !list.contains(attrName)) {
					list.add(attrName);
				}
			}
		}

		if (list.isEmpty() && aClass.getModelEClass().getESuperTypes().size() > 0) {
			for (EClass eClass : aClass.getModelEClass().getESuperTypes()) {
				final PAnnotatedEClass aSuperClass = aClass.getPaModel().getPAnnotated(eClass);
				if (aSuperClass != null) {
					final List<String> superList = getIDFeaturesNamesRecurse(aSuperClass);
					list.removeAll(superList);
					list.addAll(superList);
				}
				if (!list.isEmpty()) {
					return list;
				}
			}
			if (!list.isEmpty()) {
				return list;
			}
			// fall through
		}
		return list;
	}

}