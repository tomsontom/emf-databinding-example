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
 * $Id: HbUnidirectionalManyToManyAnnotator.java,v 1.3 2008/05/27 07:42:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.annotations.mapper.UnidirectionalManyToManyAnnotator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;

/**
 * Annotates a one-to-many attribute (an eattribute with ismany=true), an example is a list of
 * primitives (list of ints).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.3 $
 */

public class HbUnidirectionalManyToManyAnnotator extends UnidirectionalManyToManyAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(HbUnidirectionalManyToManyAnnotator.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.annotations.mapper.UnidirectionalManyToManyAnnotator#annotate(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference)
	 */
	@Override
	public void annotate(PAnnotatedEReference reference) {
		if (getPersistenceOptions().isAddIndexForForeignKey()) {
			HbAnnotationUtil.setIndex(reference, this);
		}
		annotate(reference);
	}
}
