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
 * $Id: HbMapperConstants.java,v 1.11 2009/03/07 21:15:20 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

/**
 * AnnotationUtil used by the hibernate mapper
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.11 $
 */
public class HbMapperConstants {
	// the natural-id, the value of the attribute is mutable true/false
	public static final String NATURAL_ID_ATTR = "natural-id";

	/** The expected parameter name which contains the enum class name */
	public static final String ENUM_CLASS_PARAM = "enumClass";

	/** The expected parameter name which contains the enum class name */
	public static final String EPACKAGE_PARAM = "epackage";

	public static final String ECLASSIFIER_PARAM = "eclassifier";

	/**
	 * Name of the Hibernate type param that holds the name of the EMF attribute
	 * for the EMF custom data type.
	 */
	public static final String EDATATYPE_PARAM = "edatatype";

	/** Name used for the feature property in the featuremap entry */
	public static final String PROPERTY_FEATURE = "fme_feature";

	/** Name used for the text property in a mixed featuremap entry */
	public static final String PROPERTY_MIXED_TEXT = "fme_mixed_text";

	/** Name used for the text property in a mixed featuremap entry */
	public static final String PROPERTY_MIXED_COMMENT = "fme_mixed_comment";

	/** Name used for the text property in a mixed featuremap entry */
	public static final String PROPERTY_MIXED_CDATA = "fme_mixed_cdata";

	/** Used to tag an entity as a featuremap */
	public static final String FEATUREMAP_META = "featureMap";

	/** Name used for the eclass meta data */
	public static final String ECLASS_NAME_META = "eclassName";

	/** Name used for the epackage meta data */
	public static final String EPACKAGE_META = "epackage";

	/** The meta attribute for a synthetic id */
	public static final String ID_META = "syntheticId";

	/** The meta attribute for a synthetic version */
	public static final String VERSION_META = "syntheticVersion";
}