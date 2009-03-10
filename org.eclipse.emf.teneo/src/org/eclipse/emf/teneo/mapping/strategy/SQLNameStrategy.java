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
 * $Id: SQLNameStrategy.java,v 1.8 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.strategy;

import java.util.List;

import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Takes care of creating correct names for sql artifacts such as tables, columns, foreign keys,
 * etc.
 * 
 * Note that strategies are normally created once for each instance of persistenceoptions.
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */
public interface SQLNameStrategy extends ExtensionPoint {

	/**
	 * Converts the name to the required sql setting (length and case). If the name is too long (see
	 * option maximum sql name length) then the prefix is trunced away.
	 */
	public abstract String convert(String name);

	/**
	 * Converts the name to the required sql setting (length and case). If the name is too long (see
	 * the option maximum sql name length) then: if truncPrefix = false then the part after the _ is
	 * trunced. if truncPrefix = true then the part before the _ is trunced. An underscore often
	 * occurs in the name of a join table.
	 */
	public String convert(String name, boolean truncPrefix);

	/** The join column name used to join a joined-subclass table with its parent table */
	public abstract String getPrimaryKeyJoinColumnName(PAnnotatedEClass aSuperClass, String idFeature);

	/** The join colum name for the secondary table */
	public abstract String getSecondaryTablePrimaryKeyJoinColumnName(PAnnotatedEStructuralFeature iddef);

	/** Returns the table name for a passed AnnotatedEClass */
	public abstract String getTableName(PAnnotatedEClass aClass);

	/** Simple column name with optional prefix */
	public abstract String getColumnName(PAnnotatedEStructuralFeature aStructuralFeature, String prefix);

	/**
	 * Return the name of the foreign key used for this aReference. If null is returned then the
	 * name of the foreign key is not set. Returns the concatenation of the entityname of the aclass
	 * to which the areference belongs.
	 * 
	 * This method is normally called when the PersistenceOption CREATE_READABLE_FOREIGN_KEY_NAMES
	 * is true.
	 */
	public abstract String getForeignKeyName(PAnnotatedEStructuralFeature aFeature);

	/** Return joincolumn names for many-to-one */
	public abstract List<String> getManyToOneJoinColumnNames(PAnnotatedEReference aReference);

	/** Return a list of join columns for a many is eAttribute */
	public abstract List<String> getOneToManyEAttributeJoinColumns(PAnnotatedEAttribute aAttribute);

	/** Return a list of join columns for a many is eReference */
	public abstract List<String> getOneToManyEReferenceJoinColumns(PAnnotatedEReference aReference);

	/**
	 * Return a list of join columns for a join table for a many to many
	 */
	public abstract List<String> getJoinTableJoinColumns(PAnnotatedEReference aReference, boolean inverse);

	/** Return the name of the join table in case of a list of simpletypes */
	public abstract String getJoinTableName(PAnnotatedEAttribute aAttribute);

	/** Return the name of the join table */
	public abstract String getJoinTableName(PAnnotatedEReference aReference);

	public abstract String getDiscriminatorColumnName();

	/**
	 * Return the name of the version column used.
	 */
	public abstract String getVersionColumnName();

	/**
	 * Return the column name for the id column of the idbag join table.
	 */
	public abstract String getIdBagIDColumn();

	/** Return the column name for the synthetic ID column */
	public abstract String getSyntheticIDColumnName();

	/**
	 * Sets the PersistenceOptions used. This is mainly to support backward compatibility with older
	 * version in which the naming strategy was controlled by options.
	 */
	public abstract void setPersistenceOptions(PersistenceOptions po);
}