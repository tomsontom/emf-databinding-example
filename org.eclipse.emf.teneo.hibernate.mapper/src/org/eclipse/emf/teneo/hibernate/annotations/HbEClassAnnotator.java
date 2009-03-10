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
 * $Id: HbEClassAnnotator.java,v 1.6 2008/06/10 08:24:59 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.EClassAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.StoreMappingException;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.ecore.EModelResolver;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cache;
import org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationFactory;
import org.eclipse.emf.teneo.hibernate.hbannotation.Proxy;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;

/**
 * Sets the annotation on an eclass.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class HbEClassAnnotator extends EClassAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(HbEClassAnnotator.class);

	private String defaultCacheStrategy = CacheConcurrencyStrategy.NONE.getName();
	private boolean optionSetProxy = false;

	/**
	 * Returns the annotated version of an EClass, Returns false if no efeatures of this eclass
	 * should be annotated, true if its features can be annotated.
	 */
	@Override
	protected boolean annotate(PAnnotatedEClass aClass) {
		final boolean res = super.annotate(aClass);

		final HbAnnotatedEClass hbClass = (HbAnnotatedEClass) aClass;
		final EClass eclass = aClass.getModelEClass();
		final Class<?> concreteClass = EModelResolver.instance().getJavaClass(eclass);

		// automatically add the proxy annotation
		if (optionSetProxy && hbClass.getHbProxy() == null) {
			if (concreteClass != null) {
				final Proxy proxy = HbannotationFactory.eINSTANCE.createProxy();
				proxy.setLazy(true);
				// interface class is set below.
				((HbAnnotatedEClass) aClass).setHbProxy(proxy);
				log.debug("Set proxy to true (" + proxy.getProxyClass() + ") for eclass " +
						aClass.getModelEClass().getName());
			}
		}

		if (hbClass.getHbProxy() != null && concreteClass != null) {
			// todo add check that there is an impl class
			aClass.setOnlyMapAsEntity(false);

			// set interfacename if not set
			final Proxy proxy = hbClass.getHbProxy();
			final Class<?> interfaceClass = EModelResolver.instance().getJavaInterfaceClass(eclass);
			if (interfaceClass != null) {
				proxy.setProxyClass(interfaceClass.getName());
			} else { // set the class itself
				proxy.setProxyClass(concreteClass.getName());
			}
		}

		// now handle the case of defaultCacheStrategy which is different than
		// none
		boolean hasCache = ((HbAnnotatedEClass) aClass).getHbCache() != null;

		if (aClass.getPaSuperEntity() != null && hasCache) {
			log.warn("EClass: " + aClass.getModelEClass().getName() +
					" has a cache strategy defined while it has a superclass, this strategy is ignored.");
			return res;
		}

		if (!hasCache && defaultCacheStrategy.compareToIgnoreCase(CacheConcurrencyStrategy.NONE.getName()) != 0) {
			final CacheConcurrencyStrategy ccs = CacheConcurrencyStrategy.getByName(defaultCacheStrategy);
			if (ccs == null) {
				throw new StoreMappingException("The default cache strategy: " + defaultCacheStrategy +
						" is not one of the allowed values (uppercase) " +
						"as defined in the JPA Hibernate Annotation Extensions.");
			}

			log.debug("Setting cache strategy " + defaultCacheStrategy + " on " + aClass.getModelEClass().getName());
			final Cache cache = HbannotationFactory.eINSTANCE.createCache();
			cache.setUsage(ccs);
			((HbAnnotatedEClass) aClass).setHbCache(cache);
		}
		return res;
	}

	protected boolean addDiscriminator(PAnnotatedEClass aClass) {
		final HbAnnotatedEClass hbClass = (HbAnnotatedEClass) aClass;
		return hbClass.getDiscriminatorFormula() == null;
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
		optionSetProxy = persistenceOptions.isSetProxy();
	}

	/** Set the super entity */
	@Override
	protected void setSuperEntity(PAnnotatedEClass aClass) {
		assert (aClass.getPaSuperEntity() == null);

		// let the superclass do it
		if (aClass.getEntity() != null && aClass.getEntity().getExtends() != null) {
			super.setSuperEntity(aClass);
			return;
		}

		final EClass eclass = aClass.getModelEClass();
		if (eclass.getESuperTypes().size() == 0) {
			aClass.setPaSuperEntity(null);
			return;
		}

		PAnnotatedEClass superAClass = getPaSuperEntity(aClass, false);
		if (superAClass == null) {
			superAClass = getPaSuperEntity(aClass, true);
		}
		aClass.setPaSuperEntity(superAClass);
	}

	/** Compute the annotated superclass, ignore interfaces if parameterized */
	private PAnnotatedEClass getPaSuperEntity(PAnnotatedEClass aClass, boolean allowInterfaces) {
		final PAnnotatedModel model = aClass.getPaModel();
		for (EClass superEClass : aClass.getModelEClass().getESuperTypes()) {
			final PAnnotatedEClass x = model.getPAnnotated(superEClass);
			if (x.getEntity() != null && x.getMappedSuperclass() == null &&
					(allowInterfaces || !x.getModelEClass().isInterface())) {
				return x;
			}
		}
		return null;
	}

	/**
	 * Map Interface EClasses, default false, overridden by hibernate to return true
	 */
	@Override
	protected boolean mapInterfaceEClass() {
		return true;
	}

	/** Map a mapped superclass, this differs for jpox and hibernate */
	@Override
	protected boolean mapMappedSuperEClass() {
		return true;
	}

}
