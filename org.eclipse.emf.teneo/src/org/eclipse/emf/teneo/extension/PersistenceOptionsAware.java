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
 * $Id: PersistenceOptionsAware.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

import org.eclipse.emf.teneo.PersistenceOptions;

/**
 * An extension implementing this interface will be able to 'receive' an instance of the
 * PersistenceOptions.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public interface PersistenceOptionsAware {
	/** Set the relevant pamodel */
	void setPersistenceOptions(PersistenceOptions persistenceOptions);
}