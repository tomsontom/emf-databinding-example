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
 * $Id: HbManyToOneReferenceAnnotator.java,v 1.5 2008/09/21 19:26:04 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.ManyToOneReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;

/**
 * Annotates an ereference.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class HbManyToOneReferenceAnnotator extends ManyToOneReferenceAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(HbManyToOneReferenceAnnotator.class);

	private boolean optionSetProxy = false;

	/** Annotate it */
	@Override
	public void annotate(PAnnotatedEReference aReference) {
		if (getPersistenceOptions().isAddIndexForForeignKey()) {
			HbAnnotationUtil.setIndex(aReference, this);
		}
		super.annotate(aReference);
	}

	@Override
	protected FetchType getFetch(PAnnotatedEClass aClass) {
		if (aClass == null) {
			// happens when any type
			return FetchType.EAGER;
		}
		if (optionSetProxy) {
			return FetchType.LAZY;
		}
		if (((HbAnnotatedEClass) aClass).getHbProxy() != null) {
			return FetchType.LAZY;
		}
		return super.getFetch(aClass);
	}

	@Override
	public void setPersistenceOptions(PersistenceOptions persistenceOptions) {
		super.setPersistenceOptions(persistenceOptions);
		optionSetProxy = persistenceOptions.isSetProxy();
	}

}
