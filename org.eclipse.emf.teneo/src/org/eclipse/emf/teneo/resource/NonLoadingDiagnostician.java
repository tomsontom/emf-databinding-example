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
 * $Id: NonLoadingDiagnostician.java,v 1.5 2008/06/02 07:15:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.resource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;

/**
 * Extends the default EMF Diagnostican to prevent the validation to load unloaded lists.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class NonLoadingDiagnostician extends Diagnostician {

	/** The instance */
	public static NonLoadingDiagnostician INSTANCE = new NonLoadingDiagnostician();

	/** Overriden to prevent loading of complete content */
	@Override
	protected boolean doValidateContents(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		List<EObject> eContents = NonLoadingEContentsEList.create(eObject, true);
		if (!eContents.isEmpty()) {
			Iterator<EObject> i = eContents.iterator();
			EObject child = i.next();
			boolean result = validate(child, diagnostics, context);
			while (i.hasNext() && (result || diagnostics != null)) {
				child = i.next();
				result &= validate(child, diagnostics, context);
			}
			return result;
		}
		return true;
	}
}
