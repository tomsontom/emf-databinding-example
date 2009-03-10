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
 * $Id: ExtensionManager.java,v 1.4 2008/04/11 23:43:43 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

/**
 * Manages a set of extensions. Currently for each extension point there will always be only one
 * extension instance.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */
public interface ExtensionManager {

	/**
	 * Register an extension. If there is already a non-default extension registered then it is not
	 * overwritten.
	 */
	public abstract void registerExtension(Extension extension);

	/**
	 * Return an instance of an extension, pass null if no constructor arguments are required for
	 * this extension
	 */
	public abstract ExtensionPoint getExtension(String point, Object[] initArgs);

	/**
	 * Convenience method which also performs the casting and uses the classname of the class
	 * parameter to search for the plugin.
	 */
	public abstract <T> T getExtension(Class<T> clz);

	/**
	 * Convenience method which also performs the casting and uses the classname of the class
	 * parameter to search for the plugin.
	 */
	public abstract <T> T getExtension(Class<T> clz, Object[] initArgs);

	/**
	 * Convenience method to register a user extension overriding a current extension. It will
	 * search for an existing extension using the point. If not found then an exception is thrown.
	 * In case a completely new extension is to be registered then use the registerExtension method.
	 * The singleton value from the existing extension is used. value is used from that extension.
	 */
	public void registerExtension(String point, String className);
}