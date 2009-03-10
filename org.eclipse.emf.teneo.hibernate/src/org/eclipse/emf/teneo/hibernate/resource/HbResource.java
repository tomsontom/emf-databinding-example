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
 * $Id: HbResource.java,v 1.5 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.resource;

import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.hibernate.Session;

/**
 * Defines the common interface for HbResource Impls.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public interface HbResource {

	/**
	 * Returns the session of the resource.
	 */
	Session getSession();

	/** Returns the session to the resource so that it can do clean up (or not) */
	void returnSession(Session session);

	/** Set isloading on the resource */
	void setIsLoading(boolean isLoading);

	/** Return the sessionwrapper */
	public SessionWrapper getSessionWrapper();

	/** Returns the sessionwrapper to the resource so that it can do clean up (or not) */
	void returnSessionWrapper(SessionWrapper sessionWrapper);
}
