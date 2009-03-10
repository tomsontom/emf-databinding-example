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
 * $Id: AnnotationUtil.java,v 1.2 2008/02/28 07:09:12 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations;

import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;

/**
 * Utility class
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public class AnnotationUtil {

	/** Returns an annotation correct name value pair if the feature is set */
	public static String getAnnotationValue(org.eclipse.emf.ecore.EObject eObject, EStructuralFeature eFeature,
			boolean prependComma, List<String> importStatements) {
		final Object value = eObject.eGet(eFeature);
		if (value == null) {
			return "";
		}
		final String result;
		if (value instanceof String) {
			result = eFeature.getName() + " = \"" + (String) value + "\"";
		} else if (value instanceof Enumerator) {
			result = eFeature.getName() + " = " + ((Enumerator) value).getLiteral();
		} else if (value instanceof List<?>) {
			final List<?> values = (List<?>) value;
			if (values.size() == 0) {
				return "";
			}
			final StringBuffer sb = new StringBuffer();
			for (Object o : values) {
				final PAnnotation pAnnotation = (PAnnotation) o;
				if (sb.length() > 0) {
					sb.append(", ");
				}
				sb.append(pAnnotation.getJavaAnnotation(importStatements));
			}
			result = eFeature.getName() + " = {" + sb.toString() + "}";
		} else {
			throw new IllegalArgumentException("Unknown type " + value.getClass());
		}
		if (prependComma) {
			return ", " + result;
		}
		return result;
	}
}
