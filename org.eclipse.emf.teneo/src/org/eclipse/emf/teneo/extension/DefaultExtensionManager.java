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
 * $Id: DefaultExtensionManager.java,v 1.6 2008/06/14 22:27:57 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.classloader.ClassLoaderResolver;

/**
 * Manages a set of extensions. Currently for each extension point there will always be only one
 * extension instance.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class DefaultExtensionManager implements ExtensionManager {

	private static Log log = LogFactory.getLog(DefaultExtensionManager.class);

	// Uses the default for now
	private ConcurrentHashMap<String, Extension> extensionRegistry = new ConcurrentHashMap<String, Extension>();

	// The instances of the extensions
	private ConcurrentHashMap<String, ExtensionPoint> extensionInstances =
			new ConcurrentHashMap<String, ExtensionPoint>();

	// The constructor cache
	private ConcurrentHashMap<String, Constructor<?>> constructorCache =
			new ConcurrentHashMap<String, Constructor<?>>();

	public DefaultExtensionManager() {
		ExtensionUtil.registerDefaultExtensions(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.extension.ExtensionManager#registerExtension(org.eclipse.emf.teneo.extension.Extension)
	 */
	public void registerExtension(Extension extension) {
		// check if there is already a non-default plugin registered with the same name
		if (extension.isDefaultExtension()) {
			final Extension currentExtension = extensionRegistry.get(extension.getPoint());
			if (currentExtension != null && !currentExtension.isDefaultExtension()) {
				log.debug("Not registering extension " + extension);
				log.debug("There is already a user plugin defined: " + currentExtension);
				return;
			}
		}
		if (extension.getPoint() == null) {
			throw new TeneoExtensionException("Point of extension may not be null");
		}
		if (extension.getClassName() == null) {
			throw new TeneoExtensionException("Classname of extension: " + extension.getPoint() + " may not be null");
		}
		log.debug("Registering " + extension);
		extensionRegistry.put(extension.getPoint(), extension);

		// remove any instances for this extension
		extensionInstances.remove(extension.getPoint());
	}

	public void registerExtension(String point, String className) {
		final Extension currentExtension = extensionRegistry.get(point);
		if (currentExtension == null) {
			throw new TeneoExtensionException("No default extension found using point: " + point +
					" is the point value correct?");
		}
		final Extension newExtension = new Extension();
		newExtension.setPoint(point);
		newExtension.setClassName(className);
		newExtension.setDefaultExtension(false);
		newExtension.setSingleton(currentExtension.isSingleton());
		registerExtension(newExtension);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.extension.ExtensionManager#getExtension(java.lang.String)
	 */
	public ExtensionPoint getExtension(String point, Object[] initArgs) {
		log.debug("Searching extension " + point);
		final Extension extension = extensionRegistry.get(point);
		if (extension == null) {
			throw new TeneoExtensionException("Extension point " + point + " not registered");
		}

		if (extension.isSingleton()) {
			final ExtensionPoint extensionInstance = extensionInstances.get(point);
			if (extensionInstance != null) {
				log.debug("Found instance " + extensionInstance.getClass().getClass());
				return extensionInstance;
			}
		}

		// get the clz
		// note that before the classloader was retrieved as an extension
		// however this is not logical, always use the classloaderresolver for this
		final Class<?> clz = ClassLoaderResolver.classForName(extension.getClassName());

		// check if this class indeed implements ExtensionPoint
		if (!(ExtensionPoint.class.isAssignableFrom(clz))) {
			throw new TeneoExtensionException("The requested extension " + clz.getName() +
					" does not implement the interface " + ExtensionPoint.class.getName());
		}

		try {
			final boolean constructorUsed;
			final ExtensionPoint extensionInstance;
			if (initArgs == null || initArgs.length == 0) { // use default constructor
				constructorUsed = false;
				extensionInstance = (ExtensionPoint) clz.newInstance();
			} else {
				log.debug("Initargs passed, using constructor for class " + clz.getName());
				constructorUsed = true;
				final Constructor<?> constructor = getConstructor(clz, initArgs);
				extensionInstance = (ExtensionPoint) constructor.newInstance(initArgs);
			}
			log.debug("Created extensionPoint instance: " + extensionInstance.getClass().getName());

			if (extensionInstance instanceof ExtensionManagerAware) {
				((ExtensionManagerAware) extensionInstance).setExtensionManager(this);
			}

			if (extensionInstance instanceof ExtensionInitializable) {
				log.debug("Initializing extension " + extensionInstance.getClass().getName());
				((ExtensionInitializable) extensionInstance).initializeExtension();
			}

			// note if a constructor is used instances are never cached because we assume
			// that instances always differ
			if (extension.isSingleton() && !constructorUsed) {
				log.debug("Caching extension instance as singleton " + extension);
				extensionInstances.put(point, extensionInstance);

				// now see if the extensioninstance also implements other extensionpoints
				registerForAllExtensionPoints(extensionInstance.getClass(), extensionInstance);
			}
			if (extension.isSingleton() && constructorUsed) {
				log.warn("The extension: " + extension.getPoint() +
						" is declared as a singleton but this getInstance call " +
						" passed initialization parameters so it is not cached, " + clz.getName());
			}

			return extensionInstance;
		} catch (Exception e) {
			throw new TeneoExtensionException("Exception while instantiating: " + extension.getClassName(), e);
		}
	}

	/** Return the constructor for a class and initialization arguments */
	protected Constructor<?> getConstructor(Class<?> clz, Object[] initArgs) throws NoSuchMethodException {
		Constructor<?> result = null;
		final Class<?>[] initTypes = new Class<?>[initArgs.length];
		int i = 0;
		final StringBuffer keyStr = new StringBuffer();
		for (Object o : initArgs) {
			if (keyStr.length() > 0) {
				keyStr.append(",");
			}
			if (o == null) {
				initTypes[i++] = null;
				keyStr.append("null");
			} else {
				initTypes[i++] = o.getClass();
				keyStr.append(o.getClass().getName());
			}
		}

		final String key = clz.getName() + keyStr;

		if ((result = constructorCache.get(key)) != null) {
			return result;
		}

		for (Constructor<?> constructor : clz.getConstructors()) {
			if (constructor.getParameterTypes().length != initTypes.length) {
				continue;
			}
			int j = 0;
			boolean found = true;
			for (Class<?> paramType : constructor.getParameterTypes()) {
				final Class<?> argumentType = initTypes[j++];
				if (argumentType == null && !Object.class.isAssignableFrom(paramType)) {
					found = false;
					break;
				} else if (argumentType == null && Object.class.isAssignableFrom(paramType)) {
					// just continue
				} else if (!paramType.isAssignableFrom(argumentType)) {
					found = false;
					break;
				}
			}
			if (found) {
				result = constructor;
				constructorCache.put(key, result);
				break;
			}
		}
		if (result == null) {
			throw new TeneoExtensionException("No constructor found for : " + clz.getName() +
					" and constructor argument types: " + keyStr);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.extension.ExtensionManager#getExtension(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> T getExtension(Class<T> clz) {
		return (T) getExtension(clz.getName(), null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.extension.ExtensionManager#getExtension(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public <T> T getExtension(Class<T> clz, Object[] initArgs) {
		return (T) getExtension(clz.getName(), initArgs);
	}

	/**
	 * Registers an instance for all other extensionpoints it implements if no other instance was
	 * already registered for it.
	 */
	private void registerForAllExtensionPoints(Class<?> cls, ExtensionPoint extensionInstance) {
		if (cls == null) {
			return;
		}

		// for its interfaces
		for (Class<?> interf : cls.getInterfaces()) {
			checkRegister(extensionRegistry.get(interf.getName()), extensionInstance);
		}

		// and for the class itself
		checkRegister(extensionRegistry.get(cls.getName()), extensionInstance);

		// and not the superclass, the check for null is done in the method itself
		registerForAllExtensionPoints(cls.getSuperclass(), extensionInstance);
	}

	// register the passed instance if it implements the extension and its class
	// is registered for that extension
	private void checkRegister(Extension extension, ExtensionPoint extensionInstance) {
		if (extension == null) {
			return;
		}
		if (extension.getClassName().compareTo(extensionInstance.getClass().getName()) == 0 &&
				extension.isSingleton() && extensionInstances.get(extension.getPoint()) == null) {
			log.debug("Also registering extensioninstance: " + extensionInstance.getClass().getName() +
					" for extension " + extension.getPoint());
			extensionInstances.put(extension.getPoint(), extensionInstance);
		}
	}
}