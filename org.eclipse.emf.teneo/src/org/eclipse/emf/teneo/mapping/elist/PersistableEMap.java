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
 * $Id: PersistableEMap.java,v 1.11 2008/06/03 08:30:15 mtaal Exp $
 */
package org.eclipse.emf.teneo.mapping.elist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreEMap;

/**
 * A persistable emap which uses the PersistableEList as its delegate. Note that this implementation
 * is based on the implementation of the superclass. The superclass makes use of a delegate list to
 * store its content. This implementation puts a persistent list in this member.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @author <a href="mailto:jdboudreault@gmail.com">Jean-Denis Boudreault</a>
 * 
 * @version $Revision: 1.11 $
 */
public abstract class PersistableEMap<K, V> extends EcoreEMap<K, V> implements
		PersistableDelegateList<BasicEMap.Entry<K, V>> {

	private static final long serialVersionUID = 1L;

	/** The logger */
	private static Log log = LogFactory.getLog(PersistableEMap.class);

	/** The logstring */
	protected String logString;

	/** Used for assertion */
	private final int featureID;

	/** Is loaded from backend */
	private boolean isLoaded = false;

	/** Is being loaded from backend */
	private boolean isLoading = false;

	/**
	 * The owner of the objet. we must keep a copy since emap does not have one and the delegate
	 * EList does not expose this field publicly
	 */
	private InternalEObject owner;

	/**
	 * The persisted map handled by the orm layer. This delegate is the map we receive from the
	 * database provider. It is kept all the time, any changes to the PersistableEMap are replicated
	 * to the ormMap.
	 * 
	 * This field will be null unless there is a map waiting to be lazy loaded
	 */
	protected Map<K, V> ormMapDelegate = null;

	/** Not supported constructor */
	public PersistableEMap(EClass entryEClass, EList<BasicEMap.Entry<K, V>> delegateEList) {
		super(entryEClass, Map.Entry.class, delegateEList);
		throw new UnsupportedOperationException("Explicitly passing delegate list is not supported!");
	}

	/** Constructor */
	public PersistableEMap(EClass entryEClass, Class<?> entryClass, InternalEObject owner, EStructuralFeature feature) {
		// invoke constructor with no lazyLoadMapDelegate
		super(entryEClass, Map.Entry.class, owner, owner.eClass().getFeatureID(feature));

		setDelegateEList(owner, feature, new ArrayList<Entry<K, V>>());

		this.owner = owner;
		this.featureID = owner.eClass().getFeatureID(feature);
		log.debug("Created persistable emap for entry eclass " + entryEClass.getName());
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
	public PersistableEMap(EClass entryEClass, InternalEObject owner, EStructuralFeature feature,
			List<BasicEMap.Entry<K, V>> list) {
		super(entryEClass, Map.Entry.class, owner, owner.eClass().getFeatureID(feature));

		this.owner = owner;
		this.featureID = owner.eClass().getFeatureID(feature);

		// create our list
		setDelegateEList(owner, feature, list);

		// sets the size of this map
		// size();

		log.debug("Created persistable emap for entry eclass " + entryEClass.getName());
	}

	/** Sets the delegatelist to a persistablelist */
	protected void setDelegateEList(InternalEObject owner, EStructuralFeature feature,
			List<BasicEMap.Entry<K, V>> delegateORMList) {
		assert (owner.eClass().getFeatureID(feature) == featureID);

		// NOTE BEWARE: the delegateEList is a member of the superclass!
		delegateEList = createDelegateEList(owner, feature, delegateORMList);

		logString =
				"EMap with entry eclass: " + entryEClass.getName() + " of member " + feature.getName() + " owned by " +
						owner.getClass().getName() + " with delegate list " + delegateORMList.getClass().getName();

		log.debug("Created/reset elist " + logString);

		if (delegateORMList instanceof EList) {
			setLoaded(true);
		} else if (delegateORMList instanceof ArrayList) { // already loaded lists are packaged in
			// an elist
			setLoaded(delegateORMList.size() > 0);
		}
	}

	/** Needs to be implemented by concrete subclass */
	protected abstract EList<BasicEMap.Entry<K, V>> createDelegateEList(InternalEObject owner,
			EStructuralFeature feature, List<BasicEMap.Entry<K, V>> delegateORMList);

	/** Replace the delegate */
	@SuppressWarnings("unchecked")
	public void replaceDelegate(Object newDelegate) {
		setDelegateEList(owner, getEStructuralFeature(), (List<BasicEMap.Entry<K, V>>) newDelegate);
		setLoaded(false);
	}

	/**
	 * Performs the load action if not yet oaded and sends out the load notification.
	 */
	protected void load() {
		if (isLoaded) {
			// reset the size
			size = delegateEList.size();
			return;
		}

		// When we are loading we should not be reloaded!
		// this can happen in the jpox elist impl. when detaching
		if (isLoading) {
			return;
		}

		isLoading = true;
		log.debug("Loading " + getLogString());

		// set the size
		size = this.size();

		// prevent notifications to be sent out
		boolean eDeliver = this.getOwner().eDeliver();
		boolean setDeliver = false;
		try {
			// only set to false if it was true
			if (eDeliver) {
				log.debug("Owner " + getOwner().getClass() + " set eDeliver to false");
				getOwner().eSetDeliver(false);
				setDeliver = true;
			}
		} catch (UnsupportedOperationException e) {
			// in this case the eSetDeliver was not overridden from the
			// baseclass
			// ignore
		}
		try {
			doLoad();

			// set the size
			size = this.size();
		} finally {
			isLoaded = true;
			isLoading = false;
			if (setDeliver) {
				owner.eSetDeliver(eDeliver);
			}
		}
	}

	/**
	 * The load method which should be overridden by the subclass to add lazyloading
	 */
	protected abstract void doLoad();

//
// /**
// * Overridden to prevent the super
// */
// @Override
// public void initializeDelegateEList() {
// this.isLoaded = false;
// this.size = 0;
// this.delegateEList = null;
// }

	/** Return ourselves, this class assumes that the emap is mapped as a list */
	public Object getDelegate() {
		return this;
	}

	/** Returns true if the elist is loaded */
	public boolean isLoaded() {
		return isLoaded;
	}

	/**
	 * Overridden for access to size member
	 */
	@Override
	protected void ensureEntryDataExists() {
		load();
		super.ensureEntryDataExists();
	}

	/**
	 * Overridden because of access to size attribute
	 */
	@Override
	public int size() {
		// the subclass can override the size to perform smart size determination
		if (!this.isLoaded()) {
			load();
		}
		size = delegateEList.size();
		return super.size();
	}

	/**
	 * Overridden because of access to size attribute
	 */
	@Override
	public boolean isEmpty() {
		size();
		if (!this.isLoaded()) {
			return (this.size == 0);
		}

		return super.isEmpty();
	}

	/*
	 * Javadoc copied from interface. Overridden because of access to size attribute
	 */
	@Override
	public boolean containsKey(Object key) {
		load();
		return super.containsKey(key);
	}

	/*
	 * Javadoc copied from interface. Overridden because of access to size attribute
	 */
	@Override
	public Set<K> keySet() {
		load();
		return super.keySet();
	}

	/*
	 * Javadoc copied from interface. Overridden because of access to size attribute
	 */
	@Override
	public Collection<V> values() {
		load();
		return super.values();
	}

	/*
	 * Javadoc copied from interface. Overridden because of access to size attribute
	 */
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		load();
		return super.entrySet();
	}

	/*
	 * Javadoc copied from interface. Overridden because of access to size attribute
	 */
	@Override
	public boolean containsValue(Object value) {
		load();
		return super.containsValue(value);
	}

	/*
	 * Javadoc copied from interface. Overridden because of access to size attribute
	 */
	@Override
	public V get(Object key) {
		load();
		return super.get(key);
	}

	@Override
	public Map<K, V> map() {
		load();
		if (view == null) {
			view = new View<K, V>();
		}
		if (view.map == null) {
			view.map = new PersistableDelegatingMap();
		}

		return view.map;
	}

	/** Used to tag the returned map class and give access to the owner */
	public class PersistableDelegatingMap extends DelegatingMap {

		/** Return my owner */
		public PersistableEMap<K, V> getOwner() {
			return PersistableEMap.this;
		}

	}

	/** Set the delegate again */

	protected boolean isLoading() {
		return isLoading;
	}

	protected void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

	protected void setLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
		log.debug("Isloaded is " + isLoaded);
	}

	protected String getLogString() {
		return logString;
	}

	protected InternalEObject getOwner() {
		return owner;
	}

	@Override
	public NotificationChain basicAdd(java.util.Map.Entry<K, V> object, NotificationChain notifications) {
		load();
		return super.basicAdd(object, notifications);
	}

	@Override
	public org.eclipse.emf.common.util.BasicEMap.Entry<K, V> basicGet(int index) {
		load();
		return super.basicGet(index);
	}

	@Override
	public Iterator<java.util.Map.Entry<K, V>> basicIterator() {
		load();
		return super.basicIterator();
	}

	@Override
	public List<java.util.Map.Entry<K, V>> basicList() {
		load();
		return super.basicList();
	}

	@Override
	public ListIterator<java.util.Map.Entry<K, V>> basicListIterator() {
		load();
		return super.basicListIterator();
	}

	@Override
	public ListIterator<java.util.Map.Entry<K, V>> basicListIterator(int index) {
		load();
		return super.basicListIterator(index);
	}

	@Override
	public NotificationChain basicRemove(Object object, NotificationChain notifications) {
		load();
		return super.basicRemove(object, notifications);
	}

	@Override
	public void addUnique(java.util.Map.Entry<K, V> object) {
		load();
		super.addUnique(object);
	}

	@Override
	public void addUnique(int index, java.util.Map.Entry<K, V> object) {
		load();
		super.addUnique(index, object);
	}

	@Override
	public java.util.Map.Entry<K, V> setUnique(int index, java.util.Map.Entry<K, V> object) {
		load();
		return super.setUnique(index, object);
	}

	@Override
	public boolean add(java.util.Map.Entry<K, V> object) {
		load();
		return super.add(object);
	}

	@Override
	public void add(int index, java.util.Map.Entry<K, V> object) {
		load();
		super.add(index, object);
	}

	@Override
	public boolean addAll(Collection<? extends java.util.Map.Entry<K, V>> collection) {
		load();
		return super.addAll(collection);
	}

	@Override
	public boolean addAll(int index, Collection<? extends java.util.Map.Entry<K, V>> collection) {
		load();
		return super.addAll(index, collection);
	}

	@Override
	public void clear() {
		this.isLoaded = false;
		super.clear();
	}

	@Override
	public org.eclipse.emf.common.util.BasicEMap.Entry<K, V> get(int index) {
		load();
		return super.get(index);
	}

	@Override
	protected int indexOf(int hash) {
		load();
		return super.indexOf(hash);
	}

	@Override
	public int indexOf(Object object) {
		load();
		return super.indexOf(object);
	}

	@Override
	public int indexOfKey(Object key) {
		load();
		return super.indexOfKey(key);
	}

	@Override
	public Iterator<java.util.Map.Entry<K, V>> iterator() {
		load();
		return super.iterator();
	}

	@Override
	public int lastIndexOf(Object object) {
		load();
		return super.lastIndexOf(object);
	}

	@Override
	public ListIterator<java.util.Map.Entry<K, V>> listIterator() {
		load();
		return super.listIterator();
	}

	@Override
	public ListIterator<java.util.Map.Entry<K, V>> listIterator(int index) {
		load();
		return super.listIterator(index);
	}

	@Override
	public void move(int index, java.util.Map.Entry<K, V> object) {
		load();
		super.move(index, object);
	}

	@Override
	public java.util.Map.Entry<K, V> move(int targetIndex, int sourceIndex) {
		load();
		return super.move(targetIndex, sourceIndex);
	}

	@Override
	public V put(K key, V value) {
		load();
		return super.put(key, value);
	}

	@Override
	public void putAll(EMap<? extends K, ? extends V> map) {
		load();
		super.putAll(map);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		load();
		super.putAll(map);
	}

	@Override
	protected V putEntry(org.eclipse.emf.common.util.BasicEMap.Entry<K, V> entry, V value) {
		load();
		return super.putEntry(entry, value);
	}

	@Override
	public java.util.Map.Entry<K, V> remove(int index) {
		load();
		return super.remove(index);
	}

	@Override
	public boolean remove(Object object) {

		load();
		return super.remove(object);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		load();
		return super.removeAll(collection);
	}

	@Override
	protected V removeEntry(int index, int entryIndex) {
		load();
		return super.removeEntry(index, entryIndex);
	}

	@Override
	public V removeKey(Object key) {
		load();
		return super.removeKey(key);
	}

	@Override
	protected V resolve(K key, V value) {
		load();
		return super.resolve(key, value);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		load();
		return super.retainAll(collection);
	}

	@Override
	public java.util.Map.Entry<K, V> set(int index, java.util.Map.Entry<K, V> object) {
		load();
		return super.set(index, object);
	}

	@Override
	public List<java.util.Map.Entry<K, V>> subList(int start, int end) {
		load();
		return super.subList(start, end);
	}

	@Override
	public Object[] toArray() {
		load();
		return super.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		load();
		return super.toArray(array);
	}

	@Override
	public void set(Object value) {
		load();
		super.set(value);
	}
}