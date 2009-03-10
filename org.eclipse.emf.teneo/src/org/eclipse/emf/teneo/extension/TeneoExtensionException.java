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
 * $Id: TeneoExtensionException.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

import org.eclipse.emf.teneo.TeneoException;

/**
 * Is thrown in case of an illegal situation in handling extensions.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public class TeneoExtensionException extends TeneoException {
	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 7433341056815136417L;

	/**
	 * The constructor, logs the exception also
	 */
	public TeneoExtensionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * The constructor, logs the exception also
	 */
	public TeneoExtensionException(String msg) {
		super(msg);
	}
}
