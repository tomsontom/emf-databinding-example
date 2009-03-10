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
 * $Id: ParseXMLAnnotationsException.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.xml;

import org.eclipse.emf.teneo.annotations.StoreAnnotationsException;

/**
 * Is thrown in the org.eclipse.emf.teneo.annotations.xml package. Takes care of logging the cause.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public class ParseXMLAnnotationsException extends StoreAnnotationsException {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -8670363508437586401L;

	/**
	 * The constructor, logs the exception also
	 */
	public ParseXMLAnnotationsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	/**
	 * The constructor, logs the exception also
	 */
	public ParseXMLAnnotationsException(String msg) {
		super(msg);
	}

	/**
	 * The constructor, logs the exception also
	 */
	public ParseXMLAnnotationsException(Throwable t) {
		super(t.getMessage(), t);
	}
}
