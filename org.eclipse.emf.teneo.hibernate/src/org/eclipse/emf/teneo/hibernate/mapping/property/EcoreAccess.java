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
 * $Id: EcoreAccess.java,v 1.7 2008/04/20 10:31:56 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.property;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.DynamicValueHolder;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.teneo.util.FieldUtil;

/**
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.7 $
 */
public class EcoreAccess {

	/** Return the DynamicValueHolder */
	public static DynamicValueHolder getValueHolder(BasicEObjectImpl deo) {
		if (deo instanceof DynamicValueHolder) {
			return (DynamicValueHolder) deo;
		}
		return (DynamicValueHolder) FieldUtil.callMethod(deo, "eSettings", null);
	}

	/** Sets an elist using the passed feature */
	public static void setManyEFeatureValue(EStructuralFeature eFeature, Object value, BasicEObjectImpl owner) {
		final DynamicValueHolder dvh = getValueHolder(owner);
		dvh.dynamicSet(getFeatureId(owner, eFeature), value);
	}

	/** Gets an elist using the passed feature */
	public static EList<?> getManyEFeatureValue(EStructuralFeature eFeature, BasicEObjectImpl owner) {
		final DynamicValueHolder dvh = getValueHolder(owner);
		return (EList<?>) dvh.dynamicGet(getFeatureId(owner, eFeature));
	}

	public static int getFeatureId(BasicEObjectImpl owner, EStructuralFeature eFeature) {
		return owner.eClass().getFeatureID(eFeature);
	}

	/** Determines if a passed feature is a static feature */
	public static boolean isStaticFeature(EStructuralFeature eFeature, BasicEObjectImpl owner) {
		Integer staticFeatureCount = (Integer) FieldUtil.callMethod(owner, "eStaticFeatureCount", null);
		return getFeatureId(owner, eFeature) < staticFeatureCount.intValue();
	}
}