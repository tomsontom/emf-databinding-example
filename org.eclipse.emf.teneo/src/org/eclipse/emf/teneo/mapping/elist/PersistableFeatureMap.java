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
 * $Id: PersistableFeatureMap.java,v 1.11 2008/10/27 13:18:32 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.elist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.DelegatingFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.teneo.TeneoException;
import org.eclipse.emf.teneo.type.FeatureMapEntry;
import org.eclipse.emf.teneo.util.AssertUtil;

/**
 * A persistable elist which can be used by different or mappers. This persistable elist works
 * around the idea that the persisted list (e.g. PersistentList in Hibernate) is the delegate for
 * this elist.
 * 
 * Note the delegate**() methods are overridden to force a load before anything else happens with
 * the delegated list. The addUnique. addSet methods are overridden to ensure that the featuremap
 * entries of the right type are passed to the persistent store.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.11 $
 */

public abstract class PersistableFeatureMap extends DelegatingFeatureMap implements
		PersistableDelegateList<FeatureMap.Entry> {

	private static final long serialVersionUID = 1L;

	/** The logger */
	private static Log log = LogFactory.getLog(PersistableFeatureMap.class);

	/** The actual list, must never be an elist as notifications etc. are done by this list */
	protected List<FeatureMap.Entry> delegate;

	/** Is loaded from backend */
	private boolean isLoaded = false;

	/** Is being loaded from backend */
	private boolean isLoading = false;

	/** The string used for logging */
	protected final String logString;

	/** The type of the elements in this list */
	private final Class<? extends FeatureMap.Entry> elementType;

	/** Constructor */
	public PersistableFeatureMap(InternalEObject owner, EStructuralFeature feature, List<FeatureMap.Entry> list) {
		super(owner, feature);
		elementType = determineElementType();

		if (list == null) {
			delegate = new ArrayList<FeatureMap.Entry>();
			isLoaded = true;
		} else if (list instanceof EList) {
			AssertUtil.assertTrue("The passed elist is not a featuremap but a : " + list.getClass().getName() +
					". Error in featureMap: " + getLogString(), list instanceof FeatureMap);

			delegate = replaceEntryAll(list);
			isLoaded = true;
		} else {
			delegate = list;
			isLoaded = list.size() > 0;
		}

		logString =
				"FeatureMap of member " + getEStructuralFeature().getName() + " owned by " +
						owner.getClass().getName() + " with delegate list " + delegate.getClass().getName();

		log.debug("Created persistable featuremap " + logString);
	}

	/** Returns the element type to be used */
	protected abstract Class<? extends FeatureMap.Entry> determineElementType();

	/** Returns the element type */
	public Class<? extends FeatureMap.Entry> getElementType() {
		return elementType;
	}

	/** Shortcut to replace entries */
	protected FeatureMap.Entry replaceEntry(FeatureMap.Entry entry) {
		if (entry instanceof FeatureMapEntry && ((FeatureMapEntry) entry).belongsToFeatureMap(this)) {
			return entry;
		}

		final FeatureMap.Entry emfEntry = entry;
		return createEntry(emfEntry.getEStructuralFeature(), emfEntry.getValue());
	}

	/** Convenience to replace all */
	private List<FeatureMap.Entry> replaceEntryAll(Collection<? extends FeatureMap.Entry> coll) {
		final ArrayList<FeatureMap.Entry> result = new ArrayList<FeatureMap.Entry>();
		for (FeatureMap.Entry fe : coll) {
			result.add(replaceEntry(fe));
		}
		return result;
	}

	/** Creates an exception with the logID added, without a cause */
	protected TeneoException createException(String msg) {
		return new TeneoException(msg + "\n" + getLogString());
	}

	/** Creates an exception with the logID added, without a cause */
	protected TeneoException createException(String msg, Throwable t) {
		return new TeneoException(msg + "\n" + getLogString(), t);
	}

	/** Return the delegate list without doing a load */
	public List<FeatureMap.Entry> getDelegate() {
		return delegate;
	}

	/** Returns the underlying elist */
	@Override
	protected List<FeatureMap.Entry> delegateList() {
		load();

		return delegate;
	}

	/** Replace the delegating list */
	public void replaceDelegate(List<FeatureMap.Entry> newDelegate) {
		AssertUtil.assertTrue("This featuremap " + logString + " already wraps an or specific featuremap",
			!isPersistencyWrapped());

		delegate = newDelegate;
		isLoaded = false;
	}

	/** Returns a string which can be used to log for this elist */
	public String getLogString() {
		return logString;
	}

	/** Performs the load action if not yet loaded and sends out the load notification */
	protected void load() {
		if (isLoaded) {
			return;
		}

		// When we are loading we should not be reloaded!
		// this can happen in the jpox fm impl. when detaching
		if (isLoading) {
			return;
		}

		isLoading = true;
		doLoad();
		isLoaded = true;
		isLoading = false;
		// StoreUtil.dispatchEListLoadNotification(owner, this, getEStructuralFeature());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.util.EcoreEList#isNotificationRequired()
	 */
	@Override
	protected boolean isNotificationRequired() {
		if (!isLoaded() || isLoading()) {
			return false; // not yet loaded so no notifications, prevents infinite looping
		}
		return super.isNotificationRequired();
	}

	/** Is loaded */
	public boolean isLoaded() {
		return isLoaded;
	}

	/** Is loaded */
	public void setIsLoaded(boolean isLoaded) {
		this.isLoaded = isLoaded;
	}

	/** Is loading */
	public void setIsLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

	/** Returns true if the load action is running and false otherwise */
	public boolean isLoading() {
		return isLoading;
	}

	/** The load method which should be overridden by the subclass to add lazyloading */
	protected abstract void doLoad();

	/** Returns true if the wrapped list is a persistency layer specific list */
	public abstract boolean isPersistencyWrapped();

	/** Override the didadd to enable opposite setting */
	// MT not necessary anymore in new EMF versions
// @Override
// protected void didAdd(int index, FeatureMap.Entry obj) {
// final NotificationChain nc = inverseAdd(obj, null);
// if (nc != null && isNotificationRequired()) {
// nc.dispatch();
// }
// super.didAdd(index, obj);
// }
	/* Override the didremove to enable opposite setting */
// @Override
// protected void didRemove(int index, FeatureMap.Entry obj) {
// final NotificationChain nc = inverseRemove(obj, null);
// if (nc != null && isNotificationRequired()) {
// nc.dispatch();
// }
// super.didRemove(index, obj);
// }
	// ---------------------------- Overloaded delegate methods --------------------------
	// These methods have been overridden to a load action before the backing list is
	// accessed.
	/** OVerridden to create the correct featuremap entry */
	@Override
	protected abstract FeatureMap.Entry createEntry(EStructuralFeature eStructuralFeature, Object value);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateAdd(int, java.lang.Object)
	 */
	@Override
	protected void delegateAdd(int index, FeatureMap.Entry object) {
		load();
		super.delegateAdd(index, object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateAdd(java.lang.Object)
	 */
	@Override
	protected void delegateAdd(FeatureMap.Entry object) {
		load();
		super.delegateAdd(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl#addAllUnique(java.util.Collection
	 * )
	 */
	@Override
	public boolean addAllUnique(Collection<? extends FeatureMap.Entry> collection) {
		return super.addAllUnique(replaceEntryAll(collection));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl#addAllUnique(int,
	 * java.util.Collection)
	 */
	@Override
	public boolean addAllUnique(int index, Collection<? extends FeatureMap.Entry> collection) {
		return super.addAllUnique(index, replaceEntryAll(collection));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl#addUnique(int,
	 * java.lang.Object)
	 */
	@Override
	public void addUnique(int index, FeatureMap.Entry object) {
		super.addUnique(index, replaceEntry(object));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl#addUnique(java.lang.Object)
	 */
	@Override
	public void addUnique(FeatureMap.Entry object) {
		super.addUnique(replaceEntry(object));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.impl.DelegatingNotifyingListImpl#setUnique(int,
	 * java.lang.Object)
	 */
	@Override
	public FeatureMap.Entry setUnique(int index, FeatureMap.Entry object) {
		return super.setUnique(index, replaceEntry(object));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateBasicList()
	 */
	@Override
	protected List<FeatureMap.Entry> delegateBasicList() {
		load();
		return super.delegateBasicList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateClear()
	 */
	@Override
	protected void delegateClear() {
		load();
		super.delegateClear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateContains(java.lang.Object)
	 */
	@Override
	protected boolean delegateContains(Object object) {
		load();
		return super.delegateContains(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateContainsAll(java.util.Collection)
	 */
	@Override
	protected boolean delegateContainsAll(Collection<?> collection) {
		load();
		return super.delegateContainsAll(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateEquals(java.lang.Object)
	 */
	@Override
	protected boolean delegateEquals(Object object) {
		load();
		return super.delegateEquals(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateGet(int)
	 */
	@Override
	protected FeatureMap.Entry delegateGet(int index) {
		load();
		return super.delegateGet(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateHashCode()
	 */
	@Override
	protected int delegateHashCode() {
		load();
		return super.delegateHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateIndexOf(java.lang.Object)
	 */
	@Override
	protected int delegateIndexOf(Object object) {
		load();
		return super.delegateIndexOf(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateIsEmpty()
	 */
	@Override
	protected boolean delegateIsEmpty() {
		load();
		return super.delegateIsEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateIterator()
	 */
	@Override
	protected Iterator<FeatureMap.Entry> delegateIterator() {
		load();
		return super.delegateIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateLastIndexOf(java.lang.Object)
	 */
	@Override
	protected int delegateLastIndexOf(Object object) {
		load();
		return super.delegateLastIndexOf(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateListIterator()
	 */
	@Override
	protected ListIterator<FeatureMap.Entry> delegateListIterator() {
		// TODO Auto-generated method stub
		return super.delegateListIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateRemove(int)
	 */
	@Override
	protected FeatureMap.Entry delegateRemove(int index) {
		load();
		return super.delegateRemove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateSet(int, java.lang.Object)
	 */
	@Override
	protected FeatureMap.Entry delegateSet(int index, FeatureMap.Entry object) {
		load();
		return super.delegateSet(index, object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateSize()
	 */
	@Override
	protected int delegateSize() {
		load();
		return super.delegateSize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateToArray()
	 */
	@Override
	protected Object[] delegateToArray() {
		load();
		return super.delegateToArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateToArray(java.lang.Object[])
	 */
	@Override
	protected <T> T[] delegateToArray(T[] array) {
		load();
		return super.delegateToArray(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateToString()
	 */
	@Override
	protected String delegateToString() {
		load();
		return super.delegateToString();
	}

	/**
	 * Is overridden because it can't use delegates for equality because the delegate (a hibernate
	 * or jpox list) will try to be equal with this persistable elist.
	 * 
	 * This method does jvm instance equality because doing a full-fledge equal would result in a
	 * load of the list.
	 */
	@Override
	public boolean equals(Object object) {
		return this == object;
	}
}