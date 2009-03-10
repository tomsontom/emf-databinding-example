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
 * $Id: ClassLoaderResolver.java,v 1.5 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.classloader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.TeneoException;

/**
 * Is responsible for determining which class loader to use.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class ClassLoaderResolver {
	/** The logger */
	private static Log log = LogFactory.getLog(ClassClassLoaderStrategy.class);

	/** The classloader strategy used */
	private static ClassLoaderStrategy classLoaderStrategy;

	/** Static initializer */
	static {
		try {
			setClassLoaderStrategy(new ContextClassLoaderStrategy());
		} catch (Exception e) {
			throw new TeneoException(
					"Exception when setting default class loader strategy", e);
		}
	}

	/** Returns a class based on the name */
	public static Class<?> classForName(String name) {
		try {
			return Class.forName(name, true, getClassLoader());
		} catch (Exception e) {
			throw new StoreClassLoadException("Class for name exception ", e);
		}
	}

	/** Return a classloader */
	public static ClassLoader getClassLoader() {
		return classLoaderStrategy.getClassLoader();
	}

	/**
	 * @return Returns the classLoaderStrategy.
	 */
	public static ClassLoaderStrategy getClassLoaderStrategy() {
		return classLoaderStrategy;
	}

	/**
	 * @param classLoaderStrategy
	 *            The classLoaderStrategy to set.
	 */
	public static void setClassLoaderStrategy(
			ClassLoaderStrategy classLoaderStrategy) {
		ClassLoaderResolver.classLoaderStrategy = classLoaderStrategy;
		log.info("Class loader strategy set to: "
				+ classLoaderStrategy.getClass().getName());
	}
}
