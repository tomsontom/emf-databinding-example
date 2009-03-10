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
 * $Id: HbSingleAttributeAnnotator.java,v 1.1 2008/07/12 13:10:34 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.eclipse.emf.teneo.annotations.mapper.SingleAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;

/**
 * Handles the hibernate formula (don't add columns if there is a formula annotation).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.1 $
 */

public class HbSingleAttributeAnnotator extends SingleAttributeAnnotator {
	@Override
	protected void addColumnConstraints(PAnnotatedEAttribute aAttribute) {
		final HbAnnotatedEAttribute hbAttr = (HbAnnotatedEAttribute) aAttribute;
		if (hbAttr.getFormula() != null) {
			return;
		}
		super.addColumnConstraints(aAttribute);
	}

}
