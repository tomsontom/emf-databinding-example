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
 * $Id: ClassicEntityNameStrategy.java,v 1.4 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.strategy.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.classloader.ClassLoaderResolver;
import org.eclipse.emf.teneo.classloader.StoreClassLoadException;
import org.eclipse.emf.teneo.ecore.EModelResolver;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;

/**
 * This implementation assumes that EClass names are unique. It will (de)Resolve using the EClass
 * name.
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */
public class ClassicEntityNameStrategy implements EntityNameStrategy {

	/** The logger */
	private static Log log = LogFactory.getLog(ClassicEntityNameStrategy.class);

	/** The singleton instance as it is thread safe */
	public static final ClassicEntityNameStrategy INSTANCE = new ClassicEntityNameStrategy();

	// The pamodel for which this is done
	private PAnnotatedModel paModel;

	private ExtensionManager extensionManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.elver.ecore.spring.EClassResolver#deResolve(org.eclipse.emf.ecore.EClass)
	 */
	public String toEntityName(EClass eClass) {
		if (eClass == EOBJECT_ECLASS) {
			return EOBJECT_ECLASS_NAME;
		}

		if (eClass == null) {
			throw new IllegalArgumentException(
				"Passed eclass is null."
						+ "This can occur if epackages which refer to eachother are placed in different ecore/xsd files "
						+ "and they are not read using one resource set. The reference from one epackage to another must be "
						+ "resolvable by EMF.");
		}

		if (eClass.getName() == null) {
			throw new IllegalArgumentException(
				"EClass " +
						eClass.toString() +
						" has a null name." +
						"This can occur if epackages which refer to eachother are placed in different ecore/xsd files " +
						"and they are not read using one resource set. The reference from one epackage to another must be " +
						"resolvable by EMF.");
		}

		return eClass.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.elver.ecore.spring.EClassResolver#resolve(java.lang.String)
	 */
	public EClass toEClass(String eClassName) {
		if (eClassName == null) {
			throw new IllegalArgumentException("eClassName may not be null");
		}

		if (eClassName.compareTo(EOBJECT_ECLASS_NAME) == 0) {
			return EcorePackage.eINSTANCE.getEObject();
		}

		// now try all epackages
		EClass eClass = null;
		for (final PAnnotatedEPackage aPackage : getPaModel().getPaEPackages()) {
			for (final PAnnotatedEClass aClass : aPackage.getPaEClasses()) {
				final EClass checkEClass = aClass.getModelEClass();
				if (checkEClass.getName().compareTo(eClassName) == 0) {
					if (eClass != null) {
						// doubly entry! Actually require different resolver
						// doubly entry! Actually require different resolver
						throw new IllegalArgumentException("There is more than one EClass with the same name (" +
								eClassName + " in EPackage " + eClass.getEPackage().getName() + " and " +
								aPackage.getModelEPackage().getName() + ". A different EClassResolver should be used.");
					}
				}
				eClass = checkEClass;
			}
		}

		// we didn'y find it, perhaps it is fully qualified, lets try by full class name
		if (eClass == null) {
			try {
				final Class<?> cls = ClassLoaderResolver.classForName(eClassName);
				eClass = EModelResolver.instance().getEClass(cls);
			} catch (StoreClassLoadException e) {
				log.debug("Failed to retreive ECLass for name: " + eClassName +
						". This is no problem if this is a featuremap.");
			}
		}

		if (eClass == null) {
			throw new IllegalArgumentException("No EClass found using " + eClassName);
		}
		return eClass;
	}

	/**
	 * @return the paModel
	 */
	public PAnnotatedModel getPaModel() {
		return paModel;
	}

	/**
	 * @param paModel
	 *            the paModel to set
	 */
	public void setPaModel(PAnnotatedModel paModel) {
		this.paModel = paModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.extension.ExtensionManagerAware#setExtensionManager(org.eclipse.emf.teneo.extension.ExtensionManager)
	 */
	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}

	/**
	 * @return the extensionManager
	 */
	public ExtensionManager getExtensionManager() {
		return extensionManager;
	}
}
