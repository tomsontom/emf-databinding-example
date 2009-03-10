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
 * $Id: HbDataStoreFactory.java,v 1.4 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

/**
 * Defines the factory interface for creating Hibernate Configuration. By
 * specifying their own configuration factory the client application can add
 * their own properties and control the creation of session factories.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.4 $
 */
public interface HbDataStoreFactory {
	/**
	 * The method which gets called to create a EMFDataStore. The default
	 * factory creates a standard EMFDataStore
	 */
	public HbDataStore createHbDataStore();
}