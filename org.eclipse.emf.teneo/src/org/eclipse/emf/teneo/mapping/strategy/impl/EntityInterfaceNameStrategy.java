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
 * $Id: EntityInterfaceNameStrategy.java,v 1.1 2008/08/03 21:24:26 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.strategy.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * This implementation will first use the name of the entity annotation and then the interface name
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.1 $
 */
public class EntityInterfaceNameStrategy extends EntityResolvingNameStrategy {

	/** The logger */
	private static Log log = LogFactory.getLog(EntityInterfaceNameStrategy.class);

	/** The singleton instance as it is thread safe */
	public static final EntityInterfaceNameStrategy INSTANCE = new EntityInterfaceNameStrategy();

	// The pamodel for which this is done
	private PAnnotatedModel paModel;

	// Internal cache name from name to eclass
	private ConcurrentHashMap<String, EClass> entityNameToEClass = new ConcurrentHashMap<String, EClass>();

	private ExtensionManager extensionManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.elver.ecore.spring.EClassResolver#deResolve(org.eclipse.emf.ecore.EClass)
	 */
	@Override
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

		// check if there is an entity annotation on the eclass with a name set
		final PAnnotatedEClass aClass = getPaModel().getPAnnotated(eClass);
		if (aClass != null && aClass.getEntity() != null && aClass.getEntity().getName() != null) {
			return aClass.getEntity().getName();
		}

		if (eClass.getInstanceClassName() != null) {
			return eClass.getInstanceClassName();
		}
		return eClass.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.elver.ecore.spring.EClassResolver#resolve(java.lang.String)
	 */
	@Override
	public EClass toEClass(String eClassName) {
		if (eClassName == null) {
			throw new IllegalArgumentException("eClassName may not be null");
		}

		if (eClassName.compareTo(EOBJECT_ECLASS_NAME) == 0) {
			return EcorePackage.eINSTANCE.getEObject();
		}

		EClass eClass = null;
		if ((eClass = entityNameToEClass.get(eClassName)) != null) {
			return eClass;
		}

		// first try the entityname
		for (final PAnnotatedEPackage aPackage : getPaModel().getPaEPackages()) {
			for (final PAnnotatedEClass aClass : aPackage.getPaEClasses()) {
				if (aClass.getEntity() != null && aClass.getEntity().getName() != null &&
						aClass.getEntity().getName().compareTo(eClassName) == 0 &&
						!StoreUtil.isMapEntry(aClass.getModelEClass())) { // map entries are
					// ignored
					if (eClass != null) {
						// doubly entry! Actually require different resolver
						throw new IllegalArgumentException("There is more than one EClass with the same name (" +
								eClassName + " in EPackage " + eClass.getEPackage().getName() + " and " +
								aPackage.getModelEPackage().getName() + ". A different EClassResolver should be used.");
					}
					eClass = aClass.getModelEClass();
				}
			}
		}
		if (eClass != null) {
			entityNameToEClass.put(eClassName, eClass);
			return eClass;
		}
		// now try the eclassname itself
		for (final PAnnotatedEPackage aPackage : getPaModel().getPaEPackages()) {
			for (PAnnotatedEClass aClass : aPackage.getPaEClasses()) {
				if (aClass.getEntity() != null && aClass.getEntity().getName() != null &&
						aClass.getEntity().getName().compareTo(eClassName) == 0) {
					if (eClass != null) {
						// doubly entry! Actually require different resolver
						throw new IllegalArgumentException("There is more than one EClass with the same name (" +
								eClassName + " in EPackage " + eClass.getEPackage().getName() + " and " +
								aPackage.getModelEPackage().getName() + ". A different EClassResolver should be used.");
					}
					eClass = aClass.getModelEClass();
				}
			}
		}

		// we didn'y find it, perhaps it is fully qualified, lets try by full class name
// if (eClass == null) {
// try {
// final Class<?> cls = ClassLoaderResolver.classForName(eClassName);
// eClass = EModelResolver.instance().getEClass(cls);
// } catch (StoreClassLoadException e) {
// log.debug("Failed to retreive EClass for name: " + eClassName +
// ". This is no problem if this is a featuremap.");
// }
// }

		if (eClass == null) {
			log.debug("Failed to retreive EClass for name: " + eClassName +
					". This is no problem if this is a featuremap.");
			return null;
// throw new IllegalArgumentException("No EClass found using " + eClassName);
		}
		entityNameToEClass.put(eClassName, eClass);
		return eClass;
	}

	/**
	 * @return the paModel
	 */
	@Override
	public PAnnotatedModel getPaModel() {
		return paModel;
	}

	/**
	 * @param paModel
	 *            the paModel to set
	 */
	@Override
	public void setPaModel(PAnnotatedModel paModel) {
		this.paModel = paModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.teneo.extension.ExtensionManagerAware#setExtensionManager(org.eclipse.emf
	 * .teneo.extension.ExtensionManager)
	 */
	@Override
	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}

	/**
	 * @return the extensionManager
	 */
	@Override
	public ExtensionManager getExtensionManager() {
		return extensionManager;
	}
}
