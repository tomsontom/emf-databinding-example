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
 * $Id: EMFInitializeCollectionEventListener.java,v 1.3 2008/06/02 07:15:33 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.mapping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.hibernate.HibernateException;
import org.hibernate.event.InitializeCollectionEvent;
import org.hibernate.event.def.DefaultInitializeCollectionEventListener;

/**
 * Sets eDeliver to false for the owner of the collection during the initialization of the
 * collection.
 * 
 * @author <a href="mailto:mtaal@elver.org">Mike Kanaley</a>
 */
public class EMFInitializeCollectionEventListener extends DefaultInitializeCollectionEventListener implements
		ExtensionPoint {

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.event.def.DefaultInitializeCollectionEventListener#onInitializeCollection(org.hibernate.event.InitializeCollectionEvent)
	 */
	@Override
	public void onInitializeCollection(InitializeCollectionEvent event) throws HibernateException {
		final Object owner = event.getCollection().getOwner();
		boolean setDeliver = false;
		boolean eDeliver = false;
		EObject eobj = null;
		if (owner instanceof EObject) {
			eobj = (EObject) owner;
			eDeliver = eobj.eDeliver();
			try {
				// only set to false if it was true
				if (eDeliver) {
					eobj.eSetDeliver(false);
					setDeliver = true;
				}
			} catch (UnsupportedOperationException e) {
				// in this case the eSetDeliver was not overridden from the baseclass
				// ignore
			}
		}
		try {
			super.onInitializeCollection(event);
		} finally {
			if (setDeliver) {
				eobj.eSetDeliver(eDeliver);
			}
		}
	}
}