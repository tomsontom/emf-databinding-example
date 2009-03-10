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
 * $Id: ClassClassLoaderStrategy.java,v 1.5 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.classloader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Just returns the passed class loader.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class ClassClassLoaderStrategy implements ClassLoaderStrategy {
	/** The logger */
	private static Log log = LogFactory.getLog(ClassClassLoaderStrategy.class);

	/** The caller resolver */
	private static CallerResolver callerResolver;

	// Robust way of creating of the caller resolver
	static {
		try {
			// This can fail if the current SecurityManager does not allow
			// RuntimePermission ("createSecurityManager"):
			callerResolver = new CallerResolver();
		} catch (SecurityException se) {
			// set callerResolver to null and log
			log
					.error(
							"Class class loader resolver could not be created because of SecurityException "
									+ " just using the class loader of the classclassloader class, error msg: "
									+ se.getMessage(), se);
			callerResolver = null;
		}
	}

	/**
	 * Indexes into the current method call context with a given offset.
	 */
	private static Class<?> getCallerClass(int callerOffset) {
		if (callerResolver == null) {
			return ClassClassLoaderStrategy.class;
		}
		return callerResolver.getClassContext()[callerOffset];
	}

	/**
	 * Based on examples in
	 * http://www.javaworld.com/javaworld/javaqa/2003-06/01-qa-0606-load-p2.html
	 * 
	 * A helper class to get the call context. It subclasses SecurityManager to
	 * make getClassContext() accessible. An instance of CallerResolver only
	 * needs to be created, not installed as an actual security manager.
	 */
	private static final class CallerResolver extends SecurityManager {
		protected Class<?>[] getClassContext() {
			final Class<?>[] clsContext = super.getClassContext();
			return clsContext;
		}
	}

	/**
	 * Just returns the classClassLoader
	 */
	public ClassLoader getClassLoader() {
		/*
		 * 0: SecurityManager.getClassContext 1: getClassContext 2:
		 * getCallerClass 3: getClassLoader 4:
		 * ClassLoaderResolver.getClassLoader 5: app class
		 */
		final Class<?> clazz = getCallerClass(5);
		return clazz.getClassLoader();
	}
}
