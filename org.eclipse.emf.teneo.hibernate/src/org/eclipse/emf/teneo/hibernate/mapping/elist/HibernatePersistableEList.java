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
 * $Id: HibernatePersistableEList.java,v 1.22 2008/06/29 14:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.elist;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.teneo.EContainerRepairControl;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.eclipse.emf.teneo.hibernate.resource.HbResource;
import org.eclipse.emf.teneo.mapping.elist.PersistableEList;
import org.eclipse.emf.teneo.resource.StoreResource;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.hibernate.collection.AbstractPersistentCollection;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentIdentifierBag;
import org.hibernate.collection.PersistentList;

/**
 * Implements the hibernate persistable elist.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.22 $
 */

public class HibernatePersistableEList<E> extends PersistableEList<E> implements ExtensionPoint {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -4553160393592497834L;
	/** The logger */
	private static Log log = LogFactory.getLog(HibernatePersistableEList.class);

	/** Constructor */
	public HibernatePersistableEList(InternalEObject owner, EStructuralFeature feature, List<E> list) {
		super(owner, feature, list);
	}

	/** If the delegate has been initialized */
	public boolean isInitialized() {
		return ((PersistentCollection) delegate).wasInitialized();
	}

	/**
	 * Override isLoaded to check if the delegate lists was not already loaded by hibernate behind
	 * the scenes, this happens with eagerly loaded lists.
	 */
	@Override
	public boolean isLoaded() {
		// if the delegated list was loaded under the hood and this
		// HibernatePersistableEList did
		// not yet notice it then do the local load behavior.
		// delegate is loaded in case of subselect or eager loading
		final boolean isDelegateLoaded =
				delegate instanceof AbstractPersistentCollection &&
						((AbstractPersistentCollection) delegate).wasInitialized();
		if (!super.isLoaded() && !isLoading() && isDelegateLoaded) {
			if (log.isDebugEnabled()) {
				log.debug("Persistentlist already initialized, probably eagerly loaded: " + getLogString());
			}
			try {
				setIsLoading(true);
				// do load to load the resource
				doLoad();
				setIsLoaded(true);
			} finally {
				setIsLoading(false);
			}
		}
		return super.isLoaded();
	}

	/** Do the actual load can be overridden */
	@Override
	protected synchronized void doLoad() {
		AssertUtil.assertTrue("EList " + logString, !isLoaded());

		log.debug("Started loading elist " + logString);

		SessionWrapper sessionWrapper = null;
		boolean controlsTransaction = false;
		boolean err = true;
		Resource res = null;
		try {
			res = owner.eResource();
			if (res != null && res instanceof HbResource) {
				sessionWrapper = ((HbResource) res).getSessionWrapper();
				if (res.isLoaded()) // resource is loaded reopen transaction
				{
					// if the delegate is already loaded then no transaction is
					// required
					final boolean isDelegateLoaded =
							delegate instanceof AbstractPersistentCollection &&
									((AbstractPersistentCollection) delegate).wasInitialized();
					if (!isDelegateLoaded && !sessionWrapper.isTransactionActive()) {
						log.debug("Reconnecting session to read a lazy collection, elist: " + logString);
						controlsTransaction = true;
						sessionWrapper.beginTransaction();
						sessionWrapper.setFlushModeManual();
					} else {
						log.debug("Delegate loaded or resource session is still active, using it");
					}
				} else {
					log.debug("Elist uses session from resource, " + logString);
				}
			} else {
				log.debug("EList is not loaded in session context");
			}

			if (controlsTransaction) {
				assert (res instanceof HbResource);
				((StoreResource) res).setIsLoading(true);
			}

			try {
				Object[] objs = delegate.toArray(); // this forces the load

				// disabled for now as containers are persisted by hibernate
				// anyway
				if (isContainment()) {
					for (Object element : objs) {
						if (element instanceof InternalEObject) {
							EContainerRepairControl.setContainer(owner, (InternalEObject) element,
								getEStructuralFeature());
						}
					}
				}

				// add the new objects to the resource so they are tracked
				if (res != null && res instanceof StoreResource) {
					// attach the new contained objects so that they are adapted
					// when required
					for (Object element : objs) {
						if (element instanceof EObject) {
							((StoreResource) res).addToContentOrAttach((InternalEObject) element,
								(EReference) getEStructuralFeature());
						}
					}
				}

				log.debug("Loaded " + objs.length + " from backend store for " + logString);
			} finally {
				if (controlsTransaction) {
					((StoreResource) res).setIsLoading(false);
				}
			}
			err = false;
		} finally {
			if (controlsTransaction) {
				if (err) {
					sessionWrapper.rollbackTransaction();
					sessionWrapper.restorePreviousFlushMode();
				} else {
					// a bit rough but delete from the persitence context
					// otherwise
					// hibernate will think that this collection is not attached
					// to anything and
					// will delete me
					// getSession().getPersistenceContext().getCollectionEntries().remove(this);
					sessionWrapper.commitTransaction();
					sessionWrapper.restorePreviousFlushMode();
				}
			}
			if (sessionWrapper != null) {
				((HbResource) res).returnSessionWrapper(sessionWrapper);
			}
		}
		log.debug("Finished loading elist " + logString);
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