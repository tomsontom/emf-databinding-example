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
 * $Id: HbOneToManyReferenceAnnotator.java,v 1.4 2008/05/27 07:42:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.OneToManyReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.StoreMappingException;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cache;
import org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationFactory;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;

/**
 * Annotates an ereference.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */

public class HbOneToManyReferenceAnnotator extends OneToManyReferenceAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(HbOneToManyReferenceAnnotator.class);
	private String defaultCacheStrategy = CacheConcurrencyStrategy.NONE.getName();

	/** Annotate it */
	@Override
	public void annotate(PAnnotatedEReference aReference) {
		boolean isCollectionOfElements =
				(aReference instanceof HbAnnotatedEReference && null != ((HbAnnotatedEReference) aReference)
					.getHbCollectionOfElements());

		// add an idbag annotation
		if (getPersistenceOptions().alwaysMapListAsIdBag() && aReference.getModelEReference().isMany() &&
				aReference.getOneToMany() == null && !aReference.getModelEReference().isContainment()) {
			((HbAnnotatedEReference) aReference).setHbIdBag(HbannotationFactory.eINSTANCE.createIdBag());
			// add a join table
		}

		if (getPersistenceOptions().isAddIndexForForeignKey()) {
			HbAnnotationUtil.setIndex(aReference, this);
		}

		if (!isCollectionOfElements) {
			super.annotate(aReference);
		}

		// now handle the case of defaultCacheStrategy which is different than
		// none
		boolean hasCache = ((HbAnnotatedEReference) aReference).getHbCache() != null;
		if (!hasCache && defaultCacheStrategy.compareToIgnoreCase(CacheConcurrencyStrategy.NONE.getName()) != 0) {
			final CacheConcurrencyStrategy ccs = CacheConcurrencyStrategy.getByName(defaultCacheStrategy);
			if (ccs == null) {
				throw new StoreMappingException("The default cache strategy: " + defaultCacheStrategy +
						" is not one of the allowed values (uppercase) " +
						"as defined in the JPA Hibernate Annotation Extensions.");
			}

			log.debug("Setting cache strategy " + defaultCacheStrategy + " on " +
					aReference.getModelEReference().getName());
			final Cache cache = HbannotationFactory.eINSTANCE.createCache();
			cache.setUsage(ccs);
			((HbAnnotatedEReference) aReference).setHbCache(cache);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.annotations.mapper.AbstractAnnotator#setPersistenceOptions(org.eclipse.emf.teneo.PersistenceOptions)
	 */
	@Override
	public void setPersistenceOptions(PersistenceOptions persistenceOptions) {
		super.setPersistenceOptions(persistenceOptions);
		defaultCacheStrategy = persistenceOptions.getDefaultCacheStrategy();
	}
}
