/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * StoreAnnotationsException.java,v 1.4 2007/02/01 12:35:03 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations;

import org.eclipse.emf.teneo.TeneoException;

/**
 * Is thrown in the org.eclipse.emf.teneo.annotations package. Takes care of logging the cause.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public class StoreAnnotationsException extends TeneoException {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 4685665979865102829L;

	/**
	 * The constructor, logs the exception also
	 */
	public StoreAnnotationsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * The constructor, logs the exception also
	 */
	public StoreAnnotationsException(String msg) {
		super(msg);
	}
}
