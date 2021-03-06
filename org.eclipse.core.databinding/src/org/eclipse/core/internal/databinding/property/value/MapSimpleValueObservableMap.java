/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 *     Matthew Hall - bug 262269
 ******************************************************************************/

package org.eclipse.core.internal.databinding.property.value;

import java.util.AbstractSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IStaleListener;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.StaleEvent;
import org.eclipse.core.databinding.observable.map.AbstractObservableMap;
import org.eclipse.core.databinding.observable.map.IMapChangeListener;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapChangeEvent;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.IPropertyObservable;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.SimplePropertyEvent;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.core.internal.databinding.IdentityMap;
import org.eclipse.core.internal.databinding.IdentitySet;
import org.eclipse.core.internal.databinding.Util;
import org.eclipse.core.internal.databinding.observable.IdentityObservableSet;

/**
 * @since 1.2
 * 
 */
public class MapSimpleValueObservableMap extends AbstractObservableMap
		implements IPropertyObservable {
	private IObservableMap masterMap;
	private SimpleValueProperty detailProperty;

	private IObservableSet knownMasterValues;
	private Map cachedValues;

	private boolean updating = false;

	private IMapChangeListener masterListener = new IMapChangeListener() {
		public void handleMapChange(final MapChangeEvent event) {
			if (!isDisposed()) {
				updateKnownValues();
				if (!updating)
					fireMapChange(convertDiff(event.diff));
			}
		}

		private void updateKnownValues() {
			Set knownValues = new IdentitySet(masterMap.values());
			knownMasterValues.retainAll(knownValues);
			knownMasterValues.addAll(knownValues);
		}

		private MapDiff convertDiff(MapDiff diff) {
			Map oldValues = new IdentityMap();
			Map newValues = new IdentityMap();

			Set addedKeys = diff.getAddedKeys();
			for (Iterator it = addedKeys.iterator(); it.hasNext();) {
				Object key = it.next();
				Object newSource = diff.getNewValue(key);
				Object newValue = detailProperty.getValue(newSource);
				newValues.put(key, newValue);
			}

			Set removedKeys = diff.getRemovedKeys();
			for (Iterator it = removedKeys.iterator(); it.hasNext();) {
				Object key = it.next();
				Object oldSource = diff.getOldValue(key);
				Object oldValue = detailProperty.getValue(oldSource);
				oldValues.put(key, oldValue);
			}

			Set changedKeys = new IdentitySet(diff.getChangedKeys());
			for (Iterator it = changedKeys.iterator(); it.hasNext();) {
				Object key = it.next();

				Object oldSource = diff.getOldValue(key);
				Object newSource = diff.getNewValue(key);

				Object oldValue = detailProperty.getValue(oldSource);
				Object newValue = detailProperty.getValue(newSource);

				if (Util.equals(oldValue, newValue)) {
					it.remove();
				} else {
					oldValues.put(key, oldValue);
					newValues.put(key, newValue);
				}
			}

			return Diffs.createMapDiff(addedKeys, removedKeys, changedKeys,
					oldValues, newValues);
		}
	};

	private IStaleListener staleListener = new IStaleListener() {
		public void handleStale(StaleEvent staleEvent) {
			fireStale();
		}
	};

	private INativePropertyListener detailListener;

	/**
	 * @param map
	 * @param valueProperty
	 */
	public MapSimpleValueObservableMap(IObservableMap map,
			SimpleValueProperty valueProperty) {
		super(map.getRealm());
		this.masterMap = map;
		this.detailProperty = valueProperty;

		ISimplePropertyListener listener = new ISimplePropertyListener() {
			public void handlePropertyChange(SimplePropertyEvent event) {
				notifyIfChanged(event.getSource());
			}
		};
		this.detailListener = detailProperty.adaptListener(listener);
	}

	protected void firstListenerAdded() {
		knownMasterValues = new IdentityObservableSet(getRealm(), null);
		cachedValues = new IdentityMap();
		knownMasterValues.addSetChangeListener(new ISetChangeListener() {
			public void handleSetChange(SetChangeEvent event) {
				for (Iterator it = event.diff.getRemovals().iterator(); it
						.hasNext();) {
					Object key = it.next();
					detailProperty.removeListener(key, detailListener);
					cachedValues.remove(key);
				}
				for (Iterator it = event.diff.getAdditions().iterator(); it
						.hasNext();) {
					Object key = it.next();
					cachedValues.put(key, detailProperty.getValue(key));
					detailProperty.addListener(key, detailListener);
				}
			}
		});
		knownMasterValues.addAll(masterMap.values());

		masterMap.addMapChangeListener(masterListener);
		masterMap.addStaleListener(staleListener);
	}

	protected void lastListenerRemoved() {
		masterMap.removeMapChangeListener(masterListener);
		masterMap.removeStaleListener(staleListener);
		if (knownMasterValues != null) {
			knownMasterValues.clear(); // removes attached listeners
			knownMasterValues.dispose();
			knownMasterValues = null;
		}
		cachedValues.clear();
		cachedValues = null;
	}

	private Set entrySet;

	public Set entrySet() {
		getterCalled();
		if (entrySet == null)
			entrySet = new EntrySet();
		return entrySet;
	}

	class EntrySet extends AbstractSet {
		public Iterator iterator() {
			return new Iterator() {
				Iterator it = masterMap.entrySet().iterator();

				public boolean hasNext() {
					getterCalled();
					return it.hasNext();
				}

				public Object next() {
					getterCalled();
					Map.Entry next = (Map.Entry) it.next();
					return new MapEntry(next.getKey());
				}

				public void remove() {
					it.remove();
				}
			};
		}

		public int size() {
			return masterMap.size();
		}
	}

	class MapEntry implements Map.Entry {
		private Object key;

		MapEntry(Object key) {
			this.key = key;
		}

		public Object getKey() {
			getterCalled();
			return key;
		}

		public Object getValue() {
			getterCalled();
			if (!masterMap.containsKey(key))
				return null;
			return detailProperty.getValue(masterMap.get(key));
		}

		public Object setValue(Object value) {
			if (!masterMap.containsKey(key))
				return null;
			Object source = masterMap.get(key);

			Object oldValue = detailProperty.getValue(source);

			updating = true;
			try {
				detailProperty.setValue(source, value);
			} finally {
				updating = false;
			}

			notifyIfChanged(source);

			return oldValue;
		}

		public boolean equals(Object o) {
			getterCalled();
			if (o == this)
				return true;
			if (o == null)
				return false;
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry that = (Map.Entry) o;
			return Util.equals(this.getKey(), that.getKey())
					&& Util.equals(this.getValue(), that.getValue());
		}

		public int hashCode() {
			getterCalled();
			Object value = getValue();
			return (key == null ? 0 : key.hashCode())
					^ (value == null ? 0 : value.hashCode());
		}
	}

	public Object put(Object key, Object value) {
		if (!masterMap.containsKey(key))
			return null;
		Object masterValue = masterMap.get(key);
		Object oldValue = detailProperty.getValue(key);
		detailProperty.setValue(masterValue, value);
		notifyIfChanged(masterValue);
		return oldValue;
	}

	private void notifyIfChanged(Object masterValue) {
		if (cachedValues != null) {
			final Set keys = keysFor(masterValue);

			final Object oldValue = cachedValues.get(masterValue);
			final Object newValue = detailProperty.getValue(masterValue);

			if (!Util.equals(oldValue, newValue)) {
				cachedValues.put(masterValue, newValue);
				fireMapChange(new MapDiff() {
					public Set getAddedKeys() {
						return Collections.EMPTY_SET;
					}

					public Set getChangedKeys() {
						return keys;
					}

					public Set getRemovedKeys() {
						return Collections.EMPTY_SET;
					}

					public Object getNewValue(Object key) {
						return newValue;
					}

					public Object getOldValue(Object key) {
						return oldValue;
					}
				});
			}
		}
	}

	private Set keysFor(Object value) {
		Set keys = new IdentitySet();

		for (Iterator it = masterMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Entry) it.next();
			if (entry.getValue() == value) {
				keys.add(entry.getKey());
			}
		}

		return keys;
	}

	public boolean isStale() {
		getterCalled();
		return masterMap.isStale();
	}

	private void getterCalled() {
		ObservableTracker.getterCalled(this);
	}

	public Object getObserved() {
		return masterMap;
	}

	public IProperty getProperty() {
		return detailProperty;
	}

	public synchronized void dispose() {
		if (masterMap != null) {
			masterMap.removeMapChangeListener(masterListener);
			masterMap = null;
		}
		if (knownMasterValues != null) {
			knownMasterValues.clear(); // detaches listeners
			knownMasterValues.dispose();
			knownMasterValues = null;
		}

		masterListener = null;
		detailListener = null;
		detailProperty = null;
		cachedValues = null;

		super.dispose();
	}
}
