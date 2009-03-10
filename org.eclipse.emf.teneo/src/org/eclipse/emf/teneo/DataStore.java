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
 * $Id: DataStore.java,v 1.3 2008/03/10 21:31:20 mtaal Exp $
 */

package org.eclipse.emf.teneo;

import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.extension.ExtensionManager;

/**
 * Generic datastore interface used by hibernate and jpox.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.3 $
 */

public interface DataStore {

	/** @return the mapped epackages */
	EPackage[] getEPackages();

	/**
	 * @return the properties, the combination of jpox and hibernate properties
	 */
	Properties getProperties();

	/** Return the extension manager */
	ExtensionManager getExtensionManager();

	/** Return the name */
	String getName();
}