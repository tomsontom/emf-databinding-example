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
 * $Id: PersistableEList.java,v 1.21 2009/03/16 21:39:42 mtaal Exp $
 */

package org.eclipse.emf.teneo.mapping.elist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * A persistable elist which can be used by different or mappers. This
 * persistable elist works around the idea that the persisted list (e.g.
 * PersistentList in Hibernate) is the delegate for this elist.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.21 $
 */

public abstract class PersistableEList<E> extends DelegatingEcoreEList<E>
		implements PersistableDelegateList<E> {
	private static final long serialVersionUID = 1L;

	/** The logger */
	private static Log log = LogFactory.getLog(PersistableEList.class);

	/**
	 * The actual list, must never be an elist as notifications etc. are done by
	 * this list
	 */
	protected List<E> delegate;

	/** The structural feature modeled by this list */
	private EStructuralFeature estructuralFeature;

	/** The unique path to the efeature, used to support serializaion */
	private String eFeaturePath = "";

	/** Is loaded from backend */
	private boolean isLoaded = false;

	/** Is being loaded from backend */
	private boolean isLoading = false;

	/** The string used for logging */
	protected final String logString;

	protected Boolean isThisListWrapped;

	/** Constructor */
	public PersistableEList(InternalEObject owner, EStructuralFeature feature,
			List<E> list) {
		super(owner);
		estructuralFeature = feature;
		if (list == null) {
			delegate = new ArrayList<E>();
			isLoaded = true;
		} else if (list instanceof EList) {
			delegate = new ArrayList<E>(list);
			isLoaded = true;
		} else if (list instanceof ArrayList) { // already loaded lists are
			// packaged in an elist
			delegate = list;
			isLoaded = list.size() > 0;
		} else {
			delegate = list;
		}

		logString = "EList of type: " + this.getClass().getName()
				+ " of member " + estructuralFeature.getName() + " owned by "
				+ owner.getClass().getName() + " with delegate list "
				+ delegate.getClass().getName();

		log.debug("Created persistable list " + logString);
	}

	/** Takes care of serializing the efeature */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		eFeaturePath = StoreUtil.structuralFeatureToString(estructuralFeature);
		estructuralFeature = null;
		additionalWriteObject();
		out.defaultWriteObject();
	}

	/** Do your subclass thing for serialization */
	protected void additionalWriteObject() {
	}

	/** Takes care of deserializing the efeature */
	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		estructuralFeature = StoreUtil.stringToStructureFeature(eFeaturePath);
	}

	/*
	 * Get the underlying efeature
	 * 
	 * @see
	 * org.eclipse.emf.ecore.util.DelegatingEcoreEList#getEStructuralFeature()
	 */
	@Override
	public EStructuralFeature getEStructuralFeature() {
		return estructuralFeature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.util.DelegatingEcoreEList#getFeature()
	 */
	@Override
	public Object getFeature() {
		return estructuralFeature;
	}

	/** Return the isunique value of the efeature */
	@Override
	public boolean isUnique() {
		return estructuralFeature.isUnique();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.util.DelegatingEcoreEList#getFeatureID()
	 */
	@Override
	// todo: get rid of the override when the issue with the
	// delegatingecorelist.getFeatureId is
	// solved.
	public int getFeatureID() {
		return owner.eClass().getFeatureID(estructuralFeature);
	}

	/** Return the delegate list without doing a load */
	public List<E> getDelegate() {
		return delegate;
	}

	/** Returns the underlying elist */
	@Override
	protected List<E> delegateList() {
		load();

		return delegate;
	}

	/**
	 * If this instance is again wrapped by a NotifyingList then assume that the
	 * wrapper will be smart enough to do all the inverse things.... Note that
	 * the check if a list is wrapped is done once and then the result is
	 * cached. So this assumes that a list will not be re-wrapped.
	 * 
	 * @return false if the list is wrapped, otherwise the super hasInverse is
	 *         called.
	 */
	@Override
	protected boolean hasInverse() {
		if (isWrapped()) {
			return false;
		}

		return super.hasInverse();
	}
	
	  @Override
	  protected void dispatchNotification(Notification notification)
	  {
		  // don't notify anyone as the wrapper should do that
		  if (isWrapped()) {
			  return; 
		  }
		  super.dispatchNotification(notification);
	  }
	  
	  private boolean isWrapped() {
			if (isThisListWrapped == null) {
				final Object value = getEObject().eGet(getEStructuralFeature());
				isThisListWrapped = value != this
						&& value instanceof NotifyingList<?>;
			}
			return isThisListWrapped;
	  }
		  
	/** Replace the delegating list and set isLoaded = false */
	public void replaceDelegate(List<E> newDelegate) {
		// disabled this assertion because in case of a session refresh it is
		// possible
		// that the list is replaced by a persistent list
		// AssertUtil.assertTrue("This elist " + logString +
		// " already wraps an or specific list",
		// !isPersistencyWrapped());
		delegate = newDelegate;
		isLoaded = false;
	}

	/** Returns a string which can be used to log for this elist */
	public String getLogString() {
		return logString;
	}

	/**
	 * Performs the load action if not yet oaded and sends out the load
	 * notification.
	 */
	protected void load() {
		if (isLoaded) {
			return;
		}

		// When we are loading we should not be reloaded!
		// this can happen in the jpox elist impl. when detaching
		if (isLoading) {
			return;
		}

		isLoading = true;
		log.debug("Loading " + getLogString());

		// prevent notifications to be sent out
		boolean eDeliver = owner.eDeliver();
		boolean setDeliver = false;
		try {
			// only set to false if it was true
			if (eDeliver) {
				log.debug("Owner " + owner.getClass()
						+ " set eDeliver to false");
				owner.eSetDeliver(false);
				setDeliver = true;
			}
		} catch (UnsupportedOperationException e) {
			// in this case the eSetDeliver was not overridden from the
			// baseclass
			// ignore
		}
		try {
			doLoad();
		} finally {
			isLoaded = true;
			isLoading = false;
			if (setDeliver) {
				owner.eSetDeliver(eDeliver);
			}
		}
		// StoreUtil.dispatchEListLoadNotification(owner, this,
		// getEStructuralFeature());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.util.EcoreEList#isNotificationRequired()
	 */
	@Override
	protected boolean isNotificationRequired() {
		if (!isLoaded() || isLoading()) {
			return false; // not yet loaded so no notifications, prevents
			// infinite looping
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

	/**
	 * The load method which should be overridden by the subclass to add
	 * lazyloading
	 */
	protected abstract void doLoad();

	/** Returns true if the wrapped list is a persistency layer specific list */
	public abstract boolean isPersistencyWrapped();

	// ---------------------------- Overloaded delegate methods
	// --------------------------
	// These methods have been overridden to a load action before the backing
	// list is
	// accessed.

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateAdd(int,
	 * java.lang.Object)
	 */
	@Override
	protected void delegateAdd(int index, E object) {
		load();
		super.delegateAdd(index, object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateAdd(java.lang.Object)
	 */
	@Override
	protected void delegateAdd(E object) {
		load();
		super.delegateAdd(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateBasicList()
	 */
	@Override
	protected List<E> delegateBasicList() {
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
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateContains(java.lang
	 * .Object)
	 */
	@Override
	protected boolean delegateContains(Object object) {
		load();
		return super.delegateContains(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateContainsAll(java.
	 * util.Collection)
	 */
	@Override
	protected boolean delegateContainsAll(Collection<?> collection) {
		load();
		return super.delegateContainsAll(collection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateEquals(java.lang.
	 * Object)
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
	protected E delegateGet(int index) {
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
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateIndexOf(java.lang
	 * .Object)
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
	protected Iterator<E> delegateIterator() {
		load();
		return super.delegateIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateLastIndexOf(java.
	 * lang.Object)
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
	protected ListIterator<E> delegateListIterator() {
		return super.delegateListIterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateRemove(int)
	 */
	@Override
	protected E delegateRemove(int index) {
		load();
		return super.delegateRemove(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.DelegatingEList#delegateSet(int,
	 * java.lang.Object)
	 */
	@Override
	protected E delegateSet(int index, E object) {
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
	 * @see
	 * org.eclipse.emf.common.util.DelegatingEList#delegateToArray(java.lang
	 * .Object[])
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

	/** If not loaded then basicIterator will always return a false for hasNext */
	@Override
	public Iterator<E> basicIterator() {
		if (!isLoaded()) {
			return new NonResolvingEIterator<E>() {
				/** Always returns false */
				@Override
				public boolean hasNext() {
					return false;
				}
			};
		}

		return super.basicIterator();
	}

	/**
	 * If not loaded then basicIterator will always return a false for
	 * hasNext/hasPrevious
	 */
	@Override
	public ListIterator<E> basicListIterator() {
		if (!isLoaded()) {
			return new NonResolvingEListIterator<E>() {
				/** Always returns false */
				@Override
				public boolean hasNext() {
					return false;
				}

				/** Always returns false */
				@Override
				public boolean hasPrevious() {
					return false;
				}
			};
		}

		return super.basicListIterator();
	}

	/**
	 * If not loaded then basicIterator will always return a false for
	 * hasNext/hasPrevious
	 */
	@Override
	public ListIterator<E> basicListIterator(int index) {
		if (!isLoaded()) {
			// note no size check on index as this would load this thing
			return new NonResolvingEListIterator<E>() {
				/** Always returns false */
				@Override
				public boolean hasNext() {
					return false;
				}

				/** Always returns false */
				@Override
				public boolean hasPrevious() {
					return false;
				}
			};
		}

		return super.basicListIterator(index);
	}

	/**
	 * Is overridden because it can't use delegates for equality because the
	 * delegate (a hibernate or jpox list) will try to be equal with this
	 * persistable elist.
	 * 
	 * This method does jvm instance equality because doing a full-fledge equal
	 * would result in a load of the list.
	 */
	@Override
	public boolean equals(Object object) {
		return this == object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}