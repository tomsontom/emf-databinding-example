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
 * $Id: MapHibernatePersistableEMap.java,v 1.8 2008/06/29 14:24:25 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.elist;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.SessionWrapper;
import org.eclipse.emf.teneo.hibernate.resource.HbResource;
import org.eclipse.emf.teneo.mapping.elist.MapPersistableEMap;
import org.eclipse.emf.teneo.resource.StoreResource;
import org.hibernate.Session;
import org.hibernate.collection.AbstractPersistentCollection;
import org.hibernate.collection.PersistentMap;

/**
 * Implements the hibernate persistable emap using a real map mapping (instead of the previous list
 * mapping).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @author <a href="mailto:jdboudreault@gmail.com">Jean-Denis Boudreault</a>
 * @version $Revision: 1.8 $
 */

public class MapHibernatePersistableEMap<K, V> extends MapPersistableEMap<K, V> implements ExtensionPoint {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -4553160393592497834L;

	/** The logger */
	private static Log log = LogFactory.getLog(MapHibernatePersistableEMap.class);

	/**
	 * Constructor: this version will take a natural map as input (probided by hibernate) and
	 * transform the entries into the EMF keyToValue format
	 * 
	 * 
	 */
	public MapHibernatePersistableEMap(InternalEObject owner, EReference eref, Map<K, V> map) {
		super(eref.getEReferenceType(), owner, eref, map);
	}

	/**
	 * This constructor is usually called when the list is created and filled by the user first.
	 * when called by a hibernate load, the other overload with a map input is used instead.
	 * 
	 */
	public MapHibernatePersistableEMap(InternalEObject owner, EReference eref, List<BasicEMap.Entry<K, V>> list) {
		super(eref.getEReferenceType(), owner, eref, list);
	}

	/** If the delegate has been initialized */
	public boolean isInitialized() {
		return isORMMapDelegateLoaded();
	}

	/**
	 * Override isLoaded to check if the delegate lists was not already loaded by hibernate behind
	 * the scenes, this happens with eagerly loaded lists.
	 */
	@Override
	public boolean isLoaded() {
		// if the lazyload delegate is null, then this map is already loaded
		if (ormMapDelegate == null) {
			return true;
		}

		// if the delegated map was loaded under the hood and this
		// HibernatePersistableEMap did
		// not yet notice it then do the local load behavior.
		// delegate is loaded in case of subselect or eager loading
		if (!super.isLoaded() && !isLoading() && isORMMapDelegateLoaded()) {
			log.debug("Persistentlist already initialized, probably eagerly loaded: " + getLogString());
			try {
				this.setLoading(true);
				// do load to load the resource
				doLoad();
				this.setLoading(true);
			} finally {
				this.setLoading(false);
			}
		}
		return super.isLoaded();
	}

	/**
	 * Overridden because of access to size attribute. This version will try to read the collection
	 * size without lading it if it is lazy loaded
	 */
	@Override
	public int size() {
		if (size != 0) {
			return size;
		}

		// if we are not loaded yet, we return the size of the buffered lazy
		// load delegate
		if (!isMapValueIsEAttribute() && this.getORMMapDelegate() != null) {
			if (!this.isORMMapDelegateLoaded() && (this.getORMMapDelegate() instanceof AbstractPersistentCollection)) {
				try {
					// here is a neat trick. we use reflection to get the
					// session of the persistanMap.
					Field field = AbstractPersistentCollection.class.getDeclaredField("session");
					field.setAccessible(true);
					Session s = (Session) field.get(this.getORMMapDelegate());

					// now that we have the session, we can query the size of
					// the list without loading it
					size =
							((Long) s.createFilter(this.getORMMapDelegate(), "select count(*)").list().get(0))
								.intValue();
					return size;
				} catch (Throwable t) {
					// ignore on purpose, let the call to super handle it
				}
			}
		}

		// didnt work, so we simply call the parent version
		return super.size();
	}

	/**
	 * this method is used to determine if the underlying hibernate map collection has been eagerly
	 * loaded
	 * 
	 * @return true if is laoded, false if not
	 */
	@Override
	protected boolean isORMMapDelegateLoaded() {
		if (this.getORMMapDelegate() == null) {
			return false;
		}

		return ((this.ormMapDelegate instanceof PersistentMap) && (((PersistentMap) ormMapDelegate).wasInitialized()));
	}

	/**
	 * Do the actual load and wrapping. can be overridden
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doLoad() {
		SessionWrapper sessionWrapper = null;
		boolean controlsTransaction = false;
		boolean err = true;
		Resource res = null;
		final Map<K, V> delegate = getORMMapDelegate();

		try {
			res = getEObject().eResource();
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
					log.debug("EMap uses session from resource, " + logString);
				}
			} else {
				log.debug("EMap is not loaded in session context");
			}

			if (controlsTransaction) {
				assert (res instanceof HbResource);
				((StoreResource) res).setIsLoading(true);
			}

			try {
				// set all entries in ourselves by wrapping the hibernate map.
				// this also forces the load
				for (Object o : this.getORMMapDelegate().entrySet()) {
					final Map.Entry entry = (Map.Entry) o;
					put((K) entry.getKey(), (V) entry.getValue());
				}

				// add the new objects to the resource so they are tracked
				if (res != null && res instanceof StoreResource) {
					// attach the new contained objects so that they are adapted
					// when required
					for (Object o : entrySet()) {
						if (o instanceof EObject) {
							((StoreResource) res).addToContentOrAttach((InternalEObject) o,
								(EReference) getEStructuralFeature());
						}
					}
				}
				log.debug("Loaded " + delegate.size() + " from backend store for " + logString);
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
				((HbResource) res).returnSessionWrapper(sessionWrapper);
			}
		}
		log.debug("Finished loading emap " + logString);
	}

	/** Return the hibernate map */
	@Override
	public Object getDelegate() {
		return getORMMapDelegate();
	}
}