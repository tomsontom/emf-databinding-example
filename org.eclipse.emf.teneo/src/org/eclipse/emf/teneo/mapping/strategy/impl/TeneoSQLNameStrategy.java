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
 * $Id: TeneoSQLNameStrategy.java,v 1.6 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.strategy.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.mapping.strategy.StrategyUtil;

/**
 * Differences between this implementation and the ClassicSQLNameStrategy is the way truncation is
 * done if a name is longer than the sql name length constraint. To truncate a name this class will
 * first remove vowels (in the order: u, o, a, e, i) and if that is not enough it will truncate the
 * different parts of a name (separated by _).
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */
public class TeneoSQLNameStrategy extends ClassicSQLNameStrategy {

	// The logger
	protected static final Log log = LogFactory.getLog(TeneoSQLNameStrategy.class);

	private static String[] removables = new String[] { "u", "o", "a", "e", "i" };

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.SqlNameStrategy#getManyToOneJoinColumnNames(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference)
	 */
	@Override
	public List<String> getManyToOneJoinColumnNames(PAnnotatedEReference aReference) {
		final EReference eref = aReference.getModelEReference();

		// isTransient occurs for computed featuremap features, these are ignored
		// later on
		assert (eref.isTransient() || !eref.isMany()); // otherwise this should have been a mtm

		// in case of many-to-one to qualify use the name of the class to which is refered
		// this is only used in case of non-simple naming (simple naming is better readable)!
		final PAnnotatedEClass aClass;
		if (eref.getEOpposite() == null) {
			aClass = aReference.getAReferenceType();
		} else {
			// the aclass is just the class of the structuralfeature itself.
			// This is done so that both sides of the relationship use the same columns
			aClass = aReference.getPaEClass();
		}
		final String typeName = aClass.getModelEClass().getName();
		final String featureName = eref.getName();

		final List<String> result = new ArrayList<String>();
		final List<String> names =
				StrategyUtil.getIDFeaturesNames(aReference.getAReferenceType(), persistenceOptions
					.getDefaultIDFeatureName());
		final boolean simpleNaming = optionJoinColumnNamingStrategy.compareTo("simple") == 0;
		for (String name : names) {
			final String postFix;
			if (names.size() == 1 && simpleNaming) {
				postFix = "";
			} else {
				postFix = "_" + name;
			}

			final String jcName;
			if (simpleNaming) {
				jcName = featureName + postFix;
			} else { // backward compatibility
				jcName = typeName + "_" + featureName + postFix;
			}
			result.add(convert(jcName, true));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.mapping.strategy.impl.ClassicSQLNameStrategy#trunc(int,
	 *      java.lang.String, boolean)
	 */
	@Override
	public String trunc(int maxSqlLength, String truncName, boolean truncPrefix) {
		String correctedName = truncName.replace('.', '_');
		if (maxSqlLength == -1) {
			return correctedName;
		}
		if (correctedName.length() <= maxSqlLength) {
			return correctedName;
		}

		correctedName = correctedName.replaceAll("__", "_");
		if (correctedName.startsWith("_")) {
			correctedName = correctedName.substring(1);
		}

		if (correctedName.length() <= maxSqlLength) {
			return correctedName;
		}

		// first do some standard things
		// truncate the standard e_id
		correctedName = correctedName.replaceAll("e_id", "id");
		if (correctedName.length() <= maxSqlLength) {
			return correctedName;
		}

		// now do vowel truncation preserving the first character
		char correctedNameFirstChar = correctedName.charAt(0);
		String correctedNameTail = correctedName.substring(1);
		for (String vowel : getRemovableCharacters()) {
			while (correctedNameTail.indexOf(vowel) != -1 || correctedNameTail.indexOf(vowel.toUpperCase()) != -1) {
				if (correctedNameTail.indexOf(vowel) != -1) {
					correctedNameTail = correctedNameTail.replaceFirst(vowel, "");
				} else {
					correctedNameTail = correctedNameTail.replaceFirst(vowel.toUpperCase(), "");
				}
				correctedNameTail = correctedNameTail.replaceAll("__", "_");
				if ((correctedNameTail.length() + 1) <= maxSqlLength) {
					return correctedNameFirstChar + correctedNameTail;
				}
			}
		}

		// still failed do length truncation
		return doLengthTruncation(maxSqlLength, correctedNameFirstChar + correctedNameTail);
	}

	private String doLengthTruncation(int maxSqlLength, String correctedName) {
		// failed do length truncation with the remainder
		final int underscore = correctedName.lastIndexOf('_');
		if (underscore == -1) {
			return correctedName.substring(0, maxSqlLength);
		}

		// now do the complex logic to truncate different parts
		final String[] parts = correctedName.split("_");
		int maxLength = -1;
		for (String part : parts) {
			if (part.length() > maxLength && part.length() > 0) {
				maxLength = part.length();
			}
		}

		// can this ever happen
		int totalLength = correctedName.length();
		while (maxLength > 1 && totalLength > maxSqlLength) {
			totalLength = 0;
			int newMax = 0;
			for (int i = 0; i < parts.length; i++) {
				if (parts[i].length() == maxLength) {
					parts[i] = parts[i].substring(0, maxLength - 1);
				}
				if (parts[i].length() > newMax) {
					newMax = parts[i].length();
				}
				totalLength += parts[i].length();
			}
			totalLength += parts.length - 1; // count the underscores
			maxLength = newMax;
		}

		final StringBuffer result = new StringBuffer();
		for (String part : parts) {
			if (result.length() > 0) {
				result.append("_");
			}
			result.append(part);
		}

		return result.toString();
	}

	/**
	 * Return the characters to remove, the character removal is done in order of the returned
	 * array. This method is provided to be overridden to pass a custom set of removable characters.
	 */
	protected String[] getRemovableCharacters() {
		return removables;
	}
}