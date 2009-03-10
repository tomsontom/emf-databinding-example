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
 *   Martin Taal - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: NonLoadingEContentsEList.java,v 1.5 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.resource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.teneo.mapping.elist.PersistableEList;
import org.eclipse.emf.teneo.mapping.elist.PersistableEMap;
import org.eclipse.emf.teneo.mapping.elist.PersistableFeatureMap;

/**
 * Is a contents elist which will only iterate over loaded efeatures.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class NonLoadingEContentsEList<E> extends EContentsEList<E> {

	/** Creates an instance of a NonResolvingEContentsEList for the EObject */
	public static EContentsEList<EObject> create(EObject eObject,
			boolean forValidation) {
		final ArrayList<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
		for (EReference eref : eObject.eClass().getEAllReferences()) {
			if (!eref.isContainment())
				continue;
			if (eref.isMany()) {
				List<?> list = (List<?>) eObject.eGet(eref);
				if ((list instanceof PersistableEList)
						&& ((PersistableEList<?>) list).isLoaded()) {
					result.add(eref);
				} else if ((list instanceof PersistableEMap)
						&& ((PersistableEMap<?, ?>) list).isLoaded()) {
					result.add(eref);
				} else if ((list instanceof PersistableFeatureMap)
						&& ((PersistableFeatureMap) list).isLoaded()) {
					result.add(eref);
				} else if (eref.getLowerBound() > 0 && forValidation) {
					result.add(eref);
				}
				if (!(list instanceof PersistableEList)
						&& !(list instanceof PersistableFeatureMap)
						&& !(list instanceof PersistableEMap)) {
					result.add(eref);
				}

			} else {
				result.add(eref);
			}
		}
		return new NonLoadingEContentsEList<EObject>(eObject, result);
	}

	private NonLoadingEContentsEList(EObject eObject,
			List<? extends EStructuralFeature> eStructuralFeatures) {
		super(eObject, eStructuralFeatures);
	}
}
