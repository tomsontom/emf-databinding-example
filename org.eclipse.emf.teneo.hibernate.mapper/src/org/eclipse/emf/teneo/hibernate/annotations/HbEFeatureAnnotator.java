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
 * $Id: HbEFeatureAnnotator.java,v 1.3 2008/05/27 07:42:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.eclipse.emf.teneo.annotations.mapper.EFeatureAnnotator;

/**
 * Overrides the mapVolitileFeature (is true for hibernate).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.3 $
 */

public class HbEFeatureAnnotator extends EFeatureAnnotator {

	/** Map the feature if it is volatile, default is false */
	@Override
	protected boolean mapVolitatileFeature() {
		return true;
	}
}
