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
 * $Id: HibernatePersistableFeatureMap.java,v 1.14 2008/06/29 14:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.elist;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.teneo.EContainerRepairControl;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;
import org.eclipse.emf.teneo.mapping.elist.PersistableFeatureMap;
import org.eclipse.emf.teneo.resource.StoreResource;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.hibernate.collection.AbstractPersistentCollection;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.collection.PersistentList;

/**
 * Implements the hibernate persistable elist.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.14 $
 */

public class HibernatePersistableFeatureMap extends PersistableFeatureMap implements ExtensionPoint {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -1916994464446630140L;

	/** The logger */
	private static Log log = LogFactory.getLog(HibernatePersistableFeatureMap.class);

	/** Constructor */
	public HibernatePersistableFeatureMap(InternalEObject owner, EStructuralFeature feature, List<FeatureMap.Entry> list) {
		super(owner, feature, list);

		if (isLoaded()) {
			for (Entry entry : getDelegate()) {
				final HibernateFeatureMapEntry fme = (HibernateFeatureMapEntry) entry;
				if (!fme.belongsToFeatureMap(this)) {
					fme.setFeatureMap(this);
				}
			}
		}
	}

	/** Returns the element type to be used */
	@Override
	protected Class<? extends FeatureMap.Entry> determineElementType() {
		return HibernateFeatureMapEntry.class;
	}

	/** OVerridden to create the correct featuremap entry */
	@Override
	protected FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value) {
		final HibernateFeatureMapEntry entry = new HibernateFeatureMapEntry();
		entry.setFeatureValue(eStructuralFeature, value, this);
		return entry;
	}

	/** Shortcut to replace entries */
	protected FeatureMap.Entry replaceEntry(Object entry) {
		if (entry instanceof HibernateFeatureMapEntry && ((HibernateFeatureMapEntry) entry).belongsToFeatureMap(this)) {
			return (HibernateFeatureMapEntry) entry;
		}

		final FeatureMap.Entry emfEntry = (FeatureMap.Entry) entry;
		final HibernateFeatureMapEntry fme = new HibernateFeatureMapEntry();
		fme.setFeatureValue(emfEntry.getEStructuralFeature(), emfEntry.getValue(), this);
		return fme;
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
		if (delegate instanceof AbstractPersistentCollection) {
			if (((AbstractPersistentCollection) delegate).wasInitialized()) {
				// delegate is loaded in case of subselect or eager loading
				if (isLoading()) {
					// probably are we doing this already return
					return false;
				}
				log.debug("Persistentlist already initialized, probably eagerly loaded: " + getLogString());
				try {
					setIsLoading(true);
					// do load to load the resource
					doLoad();
					setIsLoaded(true);
				} finally {
					setIsLoading(false);
				}
			}
		}
		return super.isLoaded();
	}

	/** Do the actual load can be overridden, called from the subclass */
	@Override
	protected synchronized void doLoad() {
		AssertUtil.assertTrue("EList " + logString, !isLoaded());

		SessionWrapper sessionWrapper = null;
		boolean controlsSession = false;
		boolean err = true;
		try {
			final Resource res = owner.eResource();

			if (res != null && res instanceof HibernateResource) {
				sessionWrapper = ((HibernateResource) res).getSessionWrapper();
				if (res.isLoaded()) { // resource is loaded reopen transaction
					if (!sessionWrapper.isTransactionActive()) {
						log.debug("Reconnecting session to read a lazy collection, Featuremap: " + logString);
						controlsSession = true;
						sessionWrapper.beginTransaction();
						sessionWrapper.setFlushModeManual();
					} else {
						log.debug("Resource session is still active, using it");
					}
				} else {
					log.debug("Featuremap uses session from resource, " + logString);
				}
			} else {
				log.debug("EList is not loaded in session context");
			}

			Object[] objs = delegate.toArray(); // this forces the load

			// set the owner of the feature map entries
			// set the econtainer
			for (Object element : objs) {
				final HibernateFeatureMapEntry fme = (HibernateFeatureMapEntry) element;
				fme.setFeatureMap(this);

				if (fme.getEStructuralFeature() instanceof EReference &&
						((EReference) fme.getEStructuralFeature()).isContainment()) {
					final InternalEObject eobj = (InternalEObject) fme.getValue();
					if (eobj != null) {
						EContainerRepairControl.setContainer(owner, eobj, fme.getEStructuralFeature());
						if (res != null && res instanceof StoreResource &&
								fme.getEStructuralFeature() instanceof EReference) {
							((StoreResource) res).addToContentOrAttach((InternalEObject) fme.getValue(),
								(EReference) fme.getEStructuralFeature());
						}
					}
				}
			}

			err = false;
			log.debug("Loaded " + objs.length + " from backend store for " + logString);
		} finally {
			if (controlsSession) {
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
		}
	}

	/** Overridden because general list type is not supported as a replacement */
	@Override
	public void replaceDelegate(List<FeatureMap.Entry> newDelegate) {
		if (newDelegate instanceof PersistentList) {
			AssertUtil.assertTrue("This elist " + logString + " contains a different list than the " + " passed list",
				((PersistentList) newDelegate).isWrapper(delegate));
			super.replaceDelegate(newDelegate);
		} else if (newDelegate instanceof PersistentBag) {
			AssertUtil.assertTrue("This elist " + logString + " contains a different list than the " + " passed list",
				((PersistentBag) newDelegate).isWrapper(delegate));
			super.replaceDelegate(newDelegate);
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