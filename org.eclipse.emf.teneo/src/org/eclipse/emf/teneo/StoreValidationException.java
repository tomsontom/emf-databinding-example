/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal - Initial API and
 * implementation </copyright> $Id: StoreValidationException.java,v 1.4 2007/02/08 23:14:41 mtaal
 * Exp $
 */

package org.eclipse.emf.teneo;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * Is used to contain a list of Diagnostics which contain error messages found during the save of a
 * resource.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class StoreValidationException extends TeneoException {
	/**
	 * Serializable id
	 */
	private static final long serialVersionUID = 7433341056815136417L;

	/** Creates the message */
	private static String createMessage(Diagnostic[] diagnostics) {
		final StringBuffer result = new StringBuffer();
		for (int i = 0; i < diagnostics.length; i++) {
			if (i > 0) {
				result.append("\n");
			}
			result.append(diagnostics[i].getMessage());

			for (Diagnostic childDiagnostic : diagnostics[i].getChildren()) {
				switch (childDiagnostic.getSeverity()) {
					case Diagnostic.ERROR:
						result.append("\n\t" + childDiagnostic.getMessage());
				}
			}
		}
		return result.toString();
	}

	/** The list of diagnostics */
	private final Diagnostic[] diagnostics;

	/**
	 * The constructor, logs the exception also
	 */
	public StoreValidationException(Diagnostic[] diags) {
		super(createMessage(diags));
		diagnostics = diags;
	}

	/** Returns the list of diagnostics of this exception */
	public Diagnostic[] getDiagnostics() {
		return diagnostics;
	}
}
