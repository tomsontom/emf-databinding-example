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
 * $Id: ParserUtil.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Util class
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public class ParserUtil {

	/**
	 * @return Returns the result of converting the String value to the given
	 *         data type.
	 * @throws EAnnotationImportException
	 */
	public static Object convertValue(EDataType eType, String value) {
		try {
			return EcoreUtil.createFromString(eType, value);
		} catch (IllegalArgumentException e) {
			// now try without the first dot
			if (value != null && value.indexOf('.') != -1) {
				try {
					return EcoreUtil.createFromString(eType, value.substring(1 + value.indexOf('.')));
				} catch (IllegalArgumentException x) {
					throw new AnnotationParserException("Cannot convert '" + value
							+ "' to '" + eType.getName() + "' type", e);
				}
			}
			throw new AnnotationParserException("Cannot convert '" + value
					+ "' to '" + eType.getName() + "' type", e);
		}
	}

	/** Get a structuralfeature */
	public static EStructuralFeature getEStructuralFeature(EClass eClass,
			String name) {
		try {
			for (EStructuralFeature ef : eClass.getEAllStructuralFeatures()) {
				if (ef.getName().compareToIgnoreCase(name) == 0)
					return ef;
			}
			throw new AnnotationParserException("No efeature " + name
					+ " for eclass " + eClass.getName());
		} catch (IllegalArgumentException e) {
			throw new AnnotationParserException("Cannot convert '" + name
					+ "' to an efeature for eclass " + eClass.getName());
		}
	}
}
