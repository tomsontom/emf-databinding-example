/**
 * <copyright> 
 *
 * Copyright (c) 2007 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: $
 */
package org.eclipse.emf.databinding.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.databinding.observable.AbstractObservable;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffEntry;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.NotifyingList;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;

public class EWritableList<Type> extends AbstractObservable implements IObservableList {
	private NotifyingList<Type> wrappedList;
	private Object elementType;
	private boolean stale = false;
	
	private class Listener extends AdapterImpl {
		private Object feature;
		
		public Listener(Object feature) {
			this.feature = feature;
		}
		
		@Override
		public void notifyChanged(Notification msg) {
			
			if( feature == null && msg.getFeature() == null && msg.getFeatureID(Resource.class) != Resource.RESOURCE__CONTENTS ) {
				return;
			}
			
			if (feature == msg.getFeature() && !msg.isTouch()) {
				final ListDiff diff;
				switch (msg.getEventType()) {
				case Notification.ADD: {
					diff = Diffs.createListDiff(Diffs.createListDiffEntry(msg
							.getPosition(), true, msg.getNewValue()));
					fireListChange(diff);
					break;
				}
				case Notification.ADD_MANY: {
					Collection<?> newValues = (Collection<?>) msg.getNewValue();
					ListDiffEntry[] listDiffEntries = new ListDiffEntry[newValues
							.size()];
					int position = msg.getPosition();
					int index = 0;
					for (Object newValue : newValues) {
						listDiffEntries[index++] = Diffs.createListDiffEntry(
								position++, true, newValue);
					}
					diff = Diffs.createListDiff(listDiffEntries);
					fireListChange(diff);
					break;
				}
				case Notification.REMOVE: {
					diff = Diffs.createListDiff(Diffs.createListDiffEntry(msg
							.getPosition(), false, msg.getOldValue()));
					fireListChange(diff);
					break;
				}
				case Notification.REMOVE_MANY: {
					Collection<?> oldValues = (Collection<?>) msg.getOldValue();
					ListDiffEntry[] listDiffEntries = new ListDiffEntry[oldValues
							.size()];
					int position = msg.getPosition();
					int index = 0;
					for (Object oldValue : oldValues) {
						listDiffEntries[index++] = Diffs.createListDiffEntry(
								position++, false, oldValue);
					}
					diff = Diffs.createListDiff(listDiffEntries);
					fireListChange(diff);
					break;
				}
				case Notification.MOVE: {
					Object movedValue = msg.getNewValue();
					ListDiffEntry[] listDiffEntries = new ListDiffEntry[2];
					listDiffEntries[0] = Diffs.createListDiffEntry(
							(Integer) msg.getOldValue(), false, movedValue);
					listDiffEntries[1] = Diffs.createListDiffEntry(msg
							.getPosition(), true, movedValue);
					diff = Diffs.createListDiff(listDiffEntries);
					fireListChange(diff);
					break;
				}
				case Notification.UNSET: {
					// This just represents going back to the unset state, but
					// that doesn't affect the contents of the list.
					//
					return;
				}
				}
				
//				System.err.println("CHANGE: " + diff.getDifferences()[0].getElement());
				
//				fireListChange(diff);
//				listener.handlePropertyChange(new SimplePropertyEvent(msg
//						.getNotifier(), EMFListProperty.this, diff));
			}
		}
		
	}
	
	private Adapter listener;
	
	public EWritableList(NotifyingList<Type> wrappedList) {
		this(Realm.getDefault(), wrappedList);
	}
	
	public EWritableList(Realm realm, NotifyingList<Type> wrappedList) {
		this(realm, wrappedList, null);
	}

	public EWritableList(Realm realm, NotifyingList<Type> wrappedList, Class<Type> elementType) {
		super(realm);
		this.wrappedList = wrappedList; 
		this.elementType = elementType;
		if( wrappedList.getNotifier() instanceof Notifier ) {
			Notifier notifier = (Notifier) wrappedList.getNotifier();
			listener = new Listener(wrappedList.getFeature());
			notifier.eAdapters().add(listener);
		} else {
			throw new IllegalArgumentException("Wrapped list must have a notifier attached!");
		}		
	}
	
	protected void fireListChange(ListDiff diff) {
		// fire general change event first
		super.fireChange();
		fireEvent(new ListChangeEvent(this, diff));
	}
	
	@Override
	public synchronized void dispose() {
		((Notifier)wrappedList.getNotifier()).eAdapters().remove(listener);
		super.dispose();
	}
	
	protected void getterCalled() {
		ObservableTracker.getterCalled(this);
	}

	public boolean add(Object o) {
		checkRealm();
		return wrappedList.add((Type) o);
	}

	public boolean addAll(Collection c) {
		checkRealm();
		return wrappedList.addAll(c);
	}

	public boolean addAll(int index, Collection c) {
		checkRealm();
		return wrappedList.addAll(index, c);
	}

	public void addListChangeListener(IListChangeListener listener) {
		addListener(ListChangeEvent.TYPE, listener);
	}

	public boolean contains(Object o) {
		getterCalled();
		return wrappedList.contains(o);
	}

	public boolean containsAll(Collection c) {
		getterCalled();
		return wrappedList.containsAll(c);
	}

	public Object get(int index) {
		getterCalled();
		return wrappedList.get(index);
	}

	public Object getElementType() {
		checkRealm();
		return elementType;
	}

	public int indexOf(Object o) {
		getterCalled();
		return wrappedList.indexOf(o);
	}

	public boolean isEmpty() {
		getterCalled();
		return wrappedList.isEmpty();
	}

	public Iterator<Type> iterator() {
		getterCalled();
		return wrappedList.iterator();
	}

	public int lastIndexOf(Object o) {
		getterCalled();
		return wrappedList.lastIndexOf(o);
	}

	public ListIterator<Type> listIterator() {
		getterCalled();
		return wrappedList.listIterator();
	}

	public ListIterator<Type> listIterator(int index) {
		getterCalled();
		return wrappedList.listIterator(index);
	}

	public Object move(int oldIndex, int newIndex) {
		checkRealm();
		return wrappedList.move(oldIndex, newIndex);
	}

	public boolean remove(Object o) {
		checkRealm();
		return wrappedList.remove(o);
	}

	public Object remove(int index) {
		checkRealm();
		return wrappedList.remove(index);
	}

	public boolean removeAll(Collection c) {
		checkRealm();
		return wrappedList.removeAll(c);
	}

	public void removeListChangeListener(IListChangeListener listener) {
		removeListener(ListChangeEvent.TYPE, listener);
	}

	public boolean retainAll(Collection c) {
		checkRealm();
		return wrappedList.retainAll(c);
	}

	public Object set(int index, Object element) {
		checkRealm();
		return wrappedList.set(index, (Type) element);
	}

	public int size() {
		getterCalled();
		return wrappedList.size();
	}

	public List<Type> subList(int fromIndex, int toIndex) {
		getterCalled();
		return wrappedList.subList(fromIndex, toIndex);
	}

	public Object[] toArray() {
		getterCalled();
		return wrappedList.toArray();
	}

	public Object[] toArray(Object[] a) {
		getterCalled();
		return wrappedList.toArray();
	}

	public void add(int index, Object element) {
		checkRealm();
		wrappedList.add(index, (Type) element);
	}

	public void clear() {
		checkRealm();
		wrappedList.clear();
	}

	public boolean isStale() {
		getterCalled();
		return stale;
	}	
	
	public void setStale(boolean stale) {
		checkRealm();

		boolean wasStale = this.stale;
		this.stale = stale;
		if (!wasStale && stale) {
			fireStale();
		}
	}
}
