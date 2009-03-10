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
 *   Douglas Bitting
 *   Martin Taal
 *
 * </copyright>
 *
 * $Id: MapPersistableEMap.java,v 1.4 2008/06/09 22:09:50 mtaal Exp $
 */
package org.eclipse.emf.teneo.mapping.elist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * A persistable emap which is mapped as a real map to the db. It differs from its parent class
 * (PersistableEMap) because that class assumes that the EMap is mapped to the db as a list.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @author <a href="mailto:jdboudreault@gmail.com">Jean-Denis Boudreault</a>
 * 
 * @version $Revision: 1.4 $
 */
public abstract class MapPersistableEMap<K, V> extends PersistableEMap<K, V> implements
		PersistableDelegateList<BasicEMap.Entry<K, V>> {

	private static final long serialVersionUID = 1L;

	/** The logger */
	private static Log log = LogFactory.getLog(MapPersistableEMap.class);

	/**
	 * The persisted map handled by the orm layer. This delegate is the map we receive from the
	 * database provider. It is kept all the time, any changes to the PersistableEMap are replicated
	 * to the ormMap.
	 * 
	 * This field will be null unless there is a map waiting to be lazy loaded
	 */
	protected Map<K, V> ormMapDelegate = null;

	// Is this a map with primitives
	private final boolean mapValueIsEAttribute;

	/** Not supported constructor */
	public MapPersistableEMap(EClass entryEClass, EList<BasicEMap.Entry<K, V>> delegateEList) {
		super(entryEClass, delegateEList);
		throw new UnsupportedOperationException("Explicitly passing delegate list is not supported!");
	}

	/** Constructor */
	public MapPersistableEMap(EClass entryEClass, Class<?> entryClass, InternalEObject owner, EStructuralFeature feature) {
		// invoke constructor with no lazyLoadMapDelegate
		this(entryEClass, owner, feature, (java.util.Map<K, V>) null);
	}

	/**
	 * This version will set the lazyLoadMapDelegate if it is set. This version will prepare lazy
	 * lading if available
	 * 
	 * @param entryEClass
	 * @param entryClass
	 * @param owner
	 * @param featureID
	 * @param lazyLoadDelegate
	 *            a java.util.map that is a proxy collection taht will be used when lazy load is
	 *            invoked. if it is null, then the map is considered as loaded
	 */
	public MapPersistableEMap(EClass entryEClass, InternalEObject owner, EStructuralFeature feature,
			Map<K, V> ormMapDelegate) {
		super(entryEClass, owner, feature, new ArrayList<Entry<K, V>>());

		setORMMapDelegate(ormMapDelegate);

		// create our list as empty for now
		setDelegateEList(owner, feature, this.newList());

		if (isInitialized()) {
			// perform eager loading if the underlying list has been pre-loaded
			setLoaded();

			// sets the size of this map, depending on its load status
			size();
		}

		log.debug("Created persistable emap for entry eclass " + entryEClass.getName());
		mapValueIsEAttribute = entryEClass.getEStructuralFeature("value") instanceof EAttribute;
	}

	/**
	 * This version will create the lsit completely, there is no lazy lading from this constructor
	 * 
	 * @param entryEClass
	 * @param entryClass
	 * @param owner
	 * @param featureID
	 * @param ormMapDelegate
	 *            a java.util.map that is a proxy collection taht will be used when lazy load is
	 *            invoked. if it is null, then the map is considered as loaded
	 */
	public MapPersistableEMap(EClass entryEClass, InternalEObject owner, EStructuralFeature feature,
			List<BasicEMap.Entry<K, V>> list) {
		super(entryEClass, owner, feature, list);
		this.setORMMapDelegate(null);

		// this should do nothing but set us as already loaded
		setLoaded();

		// sets the size of this map
		size();

		log.debug("Created persistable emap for entry eclass " + entryEClass.getName());

		mapValueIsEAttribute = entryEClass.getEStructuralFeature("value") instanceof EAttribute;
	}

	/** Does nothing here */
	@Override
	protected void setDelegateEList(InternalEObject owner, EStructuralFeature feature,
			List<BasicEMap.Entry<K, V>> delegateORMList) {
	}

	/** Needs to be implemented by concrete subclass, does nothing here */
	@Override
	protected EList<BasicEMap.Entry<K, V>> createDelegateEList(InternalEObject owner, EStructuralFeature feature,
			List<BasicEMap.Entry<K, V>> delegateORMList) {
		throw new UnsupportedOperationException("This method should not be called!");
	}

	/**
	 * Override this method to determine if the ormmapdelegate colelction has been eagerly loaded or
	 * not
	 * 
	 * @return
	 */
	protected abstract boolean isORMMapDelegateLoaded();

	/**
	 * this method will check the status of the lazy loaded delegate and if ti is eager lodaed,
	 * perform our loading too.
	 */
	protected void setLoaded() {
		// now, we do a check to see if the lazyLoadedDelegate was eager loaded.
		// it was, this method will wrap its data and set ourselves as loaded
		if (this.getORMMapDelegate() == null) {
			this.setLoaded(true);
		} else if (this.getORMMapDelegate() != null && isORMMapDelegateLoaded()) {
			this.load();
		} else {
			this.setLoaded(false);
		}
	}

	/** Return the delegate list without doing a load */
	@Override
	public Object getDelegate() {
		// if there is a delegate then return that one
		// this ensures that hibernate always sees its map back
		if (getORMMapDelegate() != null) {
			return getORMMapDelegate();
		}
		// todo: throw error here?
		return map();
	}

	/** Replace the delegate */
	@Override
	@SuppressWarnings("unchecked")
	public void replaceDelegate(Object newDelegate) {
		// set the ormmapdelegate to null to handle the clear action
		setORMMapDelegate(null);
		doClear();

		// now set the new value in there
		setORMMapDelegate((Map<K, V>) newDelegate);
		setLoaded(false);
	}

	/** Returns the ormMapDelegate */
	public Map<K, V> getORMMapDelegate() {
		return ormMapDelegate;
	}

	/**
	 * This method sets the ormMapDelegate
	 */
	protected void setORMMapDelegate(Map<K, V> ormMapDelegate) {
		this.ormMapDelegate = ormMapDelegate;
	}

	/**
	 * @return the mapValueIsEAttribute
	 */
	public boolean isMapValueIsEAttribute() {
		return mapValueIsEAttribute;
	}

	/**
	 * Updates orm map
	 * 
	 * @see org.eclipse.emf.common.util.BasicEMap#didAdd(org.eclipse.emf.common.util.BasicEMap.Entry)
	 */
	@Override
	protected void didAdd(org.eclipse.emf.common.util.BasicEMap.Entry<K, V> entry) {
		if (getORMMapDelegate() != null) {
			getORMMapDelegate().put(entry.getKey(), entry.getValue());
		}
		super.didAdd(entry);
	}

	/**
	 * Sets the new value using the key in the orm map.
	 * 
	 * @see org.eclipse.emf.common.util.BasicEMap#didModify(org.eclipse.emf.common.util.BasicEMap.Entry,
	 *      java.lang.Object)
	 */
	@Override
	protected void didModify(org.eclipse.emf.common.util.BasicEMap.Entry<K, V> entry, V oldValue) {
		if (getORMMapDelegate() != null) {
			getORMMapDelegate().put(entry.getKey(), entry.getValue());
		}
		super.didModify(entry, oldValue);
	}

	/**
	 * Removes the entry from the orm map
	 * 
	 * @see org.eclipse.emf.common.util.BasicEMap#didRemove(org.eclipse.emf.common.util.BasicEMap.Entry)
	 */
	@Override
	protected void didRemove(org.eclipse.emf.common.util.BasicEMap.Entry<K, V> entry) {
		if (getORMMapDelegate() != null) {
			getORMMapDelegate().remove(entry.getKey());
		}
		super.didRemove(entry);
	}
}