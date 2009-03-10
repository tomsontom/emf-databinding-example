/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: ClassicMappingContext.java,v 1.6 2008/07/13 13:12:31 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper.classic;

import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.mapper.MappingContext;

/**
 * Maps a basic attribute with many=true, e.g. list of simpletypes.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */
public class ClassicMappingContext extends MappingContext {

	/**
	 * Utilit method to truncate a column/table name. This method also repairs the name if an
	 * efeature was inherited and really belongs to another eclass. In this case jointables and join
	 * keys must be renamed to the new eclass. TODO: handle the case that the jointable/columns were
	 * set manually. This procedure will override them (only applies in case of multiple
	 * inheritance/mappedsuperclass).
	 * 
	 * This renaming is required for the case that an ereference is inherited from a mapped
	 * superclass, in this case the join-column of the e-reference will be placed in another table.
	 * If one ereference is inherited by multiple subtypes then this goes wrong because they then
	 * all share the same join column with foreign keys relating it to different tables, and
	 * multiple foreign keys on one column can not point to different directions.
	 * 
	 * TODO: is really only required for eattributes TODO: it ignores attributeoverrides
	 */
	@Override
	protected String trunc(String truncName, boolean truncSuffix) {
		final String useName;
		// currentEFeature is null in the beginning
		if (currentAFeature != null) {
			boolean override = false;
			if (currentAFeature instanceof PAnnotatedEAttribute) {
				override = getAttributeOverride((PAnnotatedEAttribute) currentAFeature) != null;
			} else {
				override = getAssociationOverrides((PAnnotatedEReference) currentAFeature) != null;
			}

			final String otherEntityName = getEntityName(currentEFeature.getEContainingClass(), false);
			// if the current name starts with the name of the mapped superclass then
			// change it back to the current eclass, do not do this in case of override
			if (!override && currentEFeature.getEContainingClass() != currentEClass && otherEntityName != null &&
					truncName.toUpperCase().startsWith(otherEntityName.toUpperCase())) {
				log.debug("Replacing name of table/joincolumn " + truncName);
				// get rid of the first part
				useName =
						getEntityName(currentEClass) +
								truncName.substring(getEntityName(currentEFeature.getEContainingClass()).length());
				log.debug("with " + useName + " because efeature is inherited");
				log
					.debug("This renaming does not work in case of manually specified joincolumn/table names and mappedsuperclass or multiple inheritance!");
			} else {
				useName = truncName;
			}
		} else {
			useName = truncName;
		}

		if (maximumSqlNameLength == -1) {
			return escape(useName);
		}
		if (useName.length() < maximumSqlNameLength) {
			return escape(useName);
		}

		// truncate the part before the last _ because this is often the suffix
		final int underscore = useName.lastIndexOf('_');
		if (truncSuffix && underscore != -1 && underscore > 0) {
			final String usStr = useName.substring(underscore);
			if ((maximumSqlNameLength - usStr.length()) < 0) {
				return escape(useName);
			}
			return escape(useName.substring(0, maximumSqlNameLength - usStr.length()) + usStr);
		}

		return escape(useName.substring(0, maximumSqlNameLength));
	}

	/** Escape the column name */
	protected String escape(String name) {
		if (getEscapeCharacter().length() == 0 || name.indexOf(getEscapeCharacter()) == 0) {
			return getSqlNameStrategy().convert(name);
		}

		return getEscapeCharacter() + getSqlNameStrategy().convert(name) + getEscapeCharacter();
	}

}