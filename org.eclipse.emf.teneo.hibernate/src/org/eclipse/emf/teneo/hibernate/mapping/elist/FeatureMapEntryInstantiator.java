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
 * $Id: FeatureMapEntryInstantiator.java,v 1.5 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.elist;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.hibernate.mapper.HbMapperConstants;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.Instantiator;

/**
 * Instantiates feature map entries.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class FeatureMapEntryInstantiator implements Instantiator {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -1219767393020090471L;

	/** The logger */
	private static Log log = LogFactory
			.getLog(FeatureMapEntryInstantiator.class);

	/** The persistentclass for which we do all this */
	private final PersistentClass persistentClass;

	/** The proxy interface if used */
	private final Class<?> proxyInterface;

	/** Constructor */
	public FeatureMapEntryInstantiator(PersistentClass pc) {
		AssertUtil.assertTrue(pc.getEntityName()
				+ " does not have a meta attribute", pc
				.getMetaAttribute(HbMapperConstants.FEATUREMAP_META) != null);
		log.debug("Creating fme instantiator for " + pc.getEntityName());
		proxyInterface = pc.getProxyInterface();
		persistentClass = pc;
	}

	/** Instantiates using the EFactory */
	public Object instantiate() {
		final HibernateFeatureMapEntry fme = new HibernateFeatureMapEntry();
		fme.setEntityName(persistentClass.getEntityName());
		return fme;
	}

	/** Instantiates using the EFactory */
	public Object instantiate(Serializable id) {
		return instantiate();
	}

	/** Checks using the mapped class or the proxy interface */
	public boolean isInstance(Object object) {
		return HibernateFeatureMapEntry.class.isInstance(object)
				|| (proxyInterface != null && proxyInterface.isInstance(object));
	}
}