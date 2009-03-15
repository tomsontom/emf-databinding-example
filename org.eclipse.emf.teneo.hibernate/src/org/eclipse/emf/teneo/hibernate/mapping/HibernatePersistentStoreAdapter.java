/**
 * <copyright>
 *
 * Copyright (c) 2009 Springsite BV (The Netherlands) and others
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
 * $Id: HibernatePersistentStoreAdapter.java,v 1.1 2009/03/15 08:09:22 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.teneo.hibernate.mapping.elist.HibernateFeatureMapEntry;
import org.eclipse.emf.teneo.type.PersistentStoreAdapter;

/**
 * See superclass.
 * 
 * This class adds translation of featuremapentries to hibernate feature map
 * entries.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.1 $
 */

public class HibernatePersistentStoreAdapter extends PersistentStoreAdapter {
	private static final long serialVersionUID = 1L;

	@Override
	protected Object replaceValue(Object value, EStructuralFeature eFeature) {
		if (value instanceof FeatureMap.Entry) {
			final FeatureMap.Entry entry = (FeatureMap.Entry) value;
			final HibernateFeatureMapEntry fme = new HibernateFeatureMapEntry();
			fme.setFeatureValue(entry.getEStructuralFeature(),
					entry.getValue(),
					(FeatureMap.Internal) ((EObject) getTarget())
							.eGet(eFeature));
			return fme;
		}
		return value;
	}

	@Override
	protected List<Object> replaceValues(List<Object> values,
			EStructuralFeature eFeature) {
		final List<Object> result = new ArrayList<Object>();
		for (Object value : values) {
			result.add(replaceValue(value, eFeature));
		}
		return result;
	}
}