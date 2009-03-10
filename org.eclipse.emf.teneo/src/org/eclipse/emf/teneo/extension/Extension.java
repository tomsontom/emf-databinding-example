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
 * $Id: Extension.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

/**
 * An Extension sets a certain ExtensionPoint in Teneo. Teneo will register default Extensions which
 * can be overridden by a user.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */

public class Extension {

	// the extension point id, normally a classname of an ExtensionPoint
	private String point;

	// the classname of the extensionpoint implementor
	private String className;

	// Is this a default extension registered by Teneo or a user plugin
	private boolean defaultExtension = false;

	// Is this a singleton within one ExtensionManager, default is true
	private boolean singleton = true;

	/**
	 * @return the point
	 */
	public String getPoint() {
		return point;
	}

	/**
	 * @param point
	 *            the point to set
	 */
	public void setPoint(String point) {
		this.point = point;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the defaultExtension
	 */
	public boolean isDefaultExtension() {
		return defaultExtension;
	}

	/**
	 * Default is false, this is the correct value for extensions created by users of Teneo. So
	 * normally this method does not need to be called.
	 * 
	 * @param defaultExtension
	 *            the defaultExtension to set
	 */
	public void setDefaultExtension(boolean defaultExtension) {
		this.defaultExtension = defaultExtension;
	}

	@Override
	public String toString() {
		return " point: " + getPoint() + " classname: " + getClassName() + " default: " + isDefaultExtension();
	}

	/**
	 * @return the singleton
	 */
	public boolean isSingleton() {
		return singleton;
	}

	/**
	 * @param singleton
	 *            the singleton to set
	 */
	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}
}