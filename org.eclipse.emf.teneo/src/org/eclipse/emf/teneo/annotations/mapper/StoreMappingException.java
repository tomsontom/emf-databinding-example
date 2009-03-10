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
 * $Id: StoreMappingException.java,v 1.4 2008/05/27 07:42:10 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import org.eclipse.emf.teneo.TeneoException;

/**
 * This exception is the base class of all exceptions which occur in the mapping process. This class
 * offers automatic logging to commons logging. Note that this class extends RuntimeException, so no
 * forced throws and catch statements. Although there are very differing views on this topic but it
 * is our experience that to many checked exceptions only distract the programmer and have no added
 * value.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */

public class StoreMappingException extends TeneoException {
	/**
	 * Serial id
	 */
	private static final long serialVersionUID = 4685665979865102829L;

	/**
	 * The constructor, logs the exception also
	 */
	public StoreMappingException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * The constructor, logs the exception also
	 */
	public StoreMappingException(String msg) {
		super(msg);
	}
}
