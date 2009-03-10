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
 * $Id: HbExtraLazyPersistableEList.java,v 1.9 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.elist;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.mapping.elist.PersistableEList;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentIdentifierBag;
import org.hibernate.collection.PersistentList;

/**
 * Implements the hibernate persistable elist with extra lazy behavior, most operations should not
 * load the complete list. This is targeted at very large lists. Note that this list can not work in
 * a detached mode.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.9 $
 */

public class HbExtraLazyPersistableEList<E> extends PersistableEList<E> implements ExtensionPoint {

	/** The logger */
	// private static Log log =
	// LogFactory.getLog(HbExtraLazyPersistableEList.class);
	/**
	 * Serial version id
	 */
	private static final long serialVersionUID = 5479222310361594394L;

	/** Constructor */
	public HbExtraLazyPersistableEList(InternalEObject owner, EStructuralFeature feature, List<E> list) {
		super(owner, feature, list);
	}

	/**
	 * Override isLoaded to check if the delegate lists was not already loaded by hibernate behind
	 * the scenes, this happens with eagerly loaded lists.
	 */
	@Override
	public boolean isLoaded() {
		return true; // always assume loaded
	}

	/**
	 * Do the actual load can be overridden, the doload does nothing as this is the responsibility
	 * of the caller
	 */
	@Override
	protected synchronized void doLoad() {
	}

	/** If the delegate has been initialized */
	public boolean isInitialized() {
		return ((PersistentCollection) delegate).wasInitialized();
	}

	/** Overridden because general list type is not supported as a replacement */
	@Override
	public void replaceDelegate(List<E> newDelegate) {
		if (newDelegate instanceof PersistentList) {
			// disabled this assertion because in case of a session refresh it
			// is possible
			// that the list is replaced by a persistent list
			// AssertUtil.assertTrue("This elist " + logString + " contains a
			// different list than the " +
			// " passed list",
			// ((PersistentList)newDelegate).isWrapper(delegate));
			super.replaceDelegate(newDelegate);
		} else if (newDelegate instanceof PersistentBag) {
			// disabled this assertion because in case of a session refresh it
			// is possible
			// that the list is replaced by a persistent list
			// AssertUtil.assertTrue("This elist " + logString + " contains a
			// different list than the " +
			// " passed list",
			// ((PersistentBag)newDelegate).isWrapper(delegate));
			super.replaceDelegate(newDelegate);
		} else if (newDelegate instanceof PersistentIdentifierBag) {
			// Added to support <idbag>
			super.replaceDelegate(newDelegate);
		} else if (newDelegate == delegate) // this can occur and is okay, do
		// nothing in this case
		{

		} else {
			throw new HbMapperException("Type " + newDelegate.getClass().getName() + " can not be " +
					" used as a replacement for elist " + logString);
		}
	}

	/** Returns true if the wrapped list is a persistency layer specific list */
	@Override
	public boolean isPersistencyWrapped() {
		return delegate instanceof PersistentCollection;
	}
}