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
 * $Id: Constants.java,v 1.7 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * AnnotationUtil used when reading a property file is also used by resources.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.7 $
 */ 

public class Constants {
	/** StructuralFeatures for TEXT content (part of a mixed complex type) */
	public static final EStructuralFeature TEXT = XMLTypePackage.eINSTANCE
			.getXMLTypeDocumentRoot_Text();

	/** StructuralFeatures for CDATA content (part of a mixed complex type) */
	public static final EStructuralFeature CDATA = XMLTypePackage.eINSTANCE
			.getXMLTypeDocumentRoot_CDATA();

	/** StructuralFeatures for COMMENT content (part of a mixed complex type) */
	public static final EStructuralFeature COMMENT = XMLTypePackage.eINSTANCE
			.getXMLTypeDocumentRoot_Comment();

	/**
	 * The name under which this connection (PersistenceManagerFactory) is
	 * registered
	 */
	public static final String PROP_NAME = "name";

	/** The extension used to find the default editor */
	public static final String PROP_EDITOR_EXTENSTION = "editorextension";

	/** The id used to find the default editor */
	public static final String PROP_EDITOR_ID = "editorid";

	/**
	 * The NS URI of the epackage handled by this database (can be more than
	 * one, then should be a comma delimited list)
	 */
	public static final String PROP_EPACKAGE_NSURI = "nsuri";

	/** The epackage property */
	public static final String PROP_EPACKAGE = "epackage";

	/** The database name property */
	public static final String PROP_DB_NAME = "dbname";

	/** The database user property */
	public static final String PROP_DB_USER = "dbuser";

	/** The database password property */
	public static final String PROP_DB_PWD = "dbpassword";

	/** The database driver property */
	public static final String PROP_DB_DRIVER = "dbdriver";

	/** The database url property */
	public static final String PROP_DB_URL = "dburl";

	/** The database dialect property */
	public static final String PROP_DB_DIALECT = "dbdialect";

	/** Denotes the start range from which Elver notifications are derived */
	public static final int NOTIFICATION_START_EVENT_TYPE_COUNT = Notification.EVENT_TYPE_COUNT + 1000;

	/** Nofitication Used by elver to notify an elist load */
	public static final int ELIST_LOAD_NOTIFICATION = NOTIFICATION_START_EVENT_TYPE_COUNT + 1;
}
