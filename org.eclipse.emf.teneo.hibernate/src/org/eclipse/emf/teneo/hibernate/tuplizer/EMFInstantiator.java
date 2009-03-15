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
 * $Id: EMFInstantiator.java,v 1.8 2009/03/15 08:09:22 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.tuplizer;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.HbUtil;
import org.eclipse.emf.teneo.type.PersistentStoreAdapter;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.Instantiator;

/**
 * Instantiates eobjects using the efactory.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */

public class EMFInstantiator implements Instantiator {

	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 6946442685247491904L;

	/** The logger */
	private static Log log = LogFactory.getLog(EMFInstantiator.class);

	/** The EClass for which we do all this */
	private final EClass eclass;

	/** The proxy interface if used */
	private final Class<?> proxyInterface;

	/** The mapped class */
	private final Class<?> mappedClass;

	/** Constructor */
	public EMFInstantiator(EClass eclass, PersistentClass pc) {
		log.debug("Creating eobject instantiator for " + pc.getEntityName()
				+ " and eclass " + eclass.getName());
		proxyInterface = pc.getProxyInterface();
		this.eclass = eclass;
		mappedClass = eclass.getInstanceClass();
	}

	/** Constructor */
	public EMFInstantiator(EClass eclass, Component component) {
		log.debug("Creating eobject instantiator for component eclass "
				+ eclass.getName());
		this.eclass = eclass;
		mappedClass = eclass.getInstanceClass();
		proxyInterface = null;
	}

	/** Instantiates using EcoreUtil.create() */
	public Object instantiate() {
		final EObject eobject = EcoreUtil.create(eclass);

		final PersistentStoreAdapter adapter = HbUtil
				.getPersistentStoreAdapter(eobject);
		adapter.setTargetCreatedByORM(true);

		if (eobject == null) {
			throw new HbMapperException(
					"The mapped "
							+ mappedClass.getName()
							+ " class can not be instantiated."
							+ " Possibly the class it is not an eclass or it is abstract.");
		}
		return eobject;
	}

	/** Instantiates using EcoreUtil.create() */
	public Object instantiate(Serializable id) {
		final EObject eobject = EcoreUtil.create(eclass);

		final PersistentStoreAdapter adapter = HbUtil
				.getPersistentStoreAdapter(eobject);
		adapter.setTargetCreatedByORM(true);

		if (eobject == null) {
			throw new HbMapperException(
					"The mapped "
							+ mappedClass.getName()
							+ " class can not be instantiated."
							+ " Possibly the class it is not an eclass or it is abstract.");
		}
		return eobject;
	}

	/** Checks using the mapped class or the proxy interface */
	public boolean isInstance(Object object) {
		if (!(object instanceof EObject)) {
			return false;
		}
		final EObject eobject = (EObject) object;
		if (eobject.eClass() == eclass) {
			return true;
		}
		if (isSuperTypeOf(eclass, eobject.eClass())) {
			return true;
		}
		return (proxyInterface != null && proxyInterface.isInstance(object));
	}

	/** Is eclass superclass */
	private boolean isSuperTypeOf(EClass superEClass, EClass eclass) {
		for (EClass testSuperEClass : eclass.getESuperTypes()) {
			if (testSuperEClass == superEClass) {
				return true;
			}
			if (isSuperTypeOf(superEClass, testSuperEClass)) {
				return true;
			}
		}
		return false;
	}

}