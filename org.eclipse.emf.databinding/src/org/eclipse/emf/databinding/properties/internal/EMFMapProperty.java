/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 *     Matthew Hall - bug 195222
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/
package org.eclipse.emf.databinding.properties.internal;

import java.util.Map;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.SimplePropertyEvent;
import org.eclipse.core.databinding.property.map.SimpleMapProperty;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * 
 * @since 1.1
 */
public class EMFMapProperty extends SimpleMapProperty {

	private final EStructuralFeature feature;
	private final Class<?> keyType;
	private final Class<?> valueType;

	/**
	 * @param feature
	 * @param keyType
	 * @param valueType
	 */
	public EMFMapProperty(EStructuralFeature feature, Class<?> keyType,
			Class<?> valueType) {
		this.feature = feature;
		this.keyType = keyType;
		this.valueType = valueType;
	}

	public Object getKeyType() {
		return keyType;
	}

	public Object getValueType() {
		return valueType;
	}

	@Override
	protected Map<?, ?> doGetMap(Object source) {
		EObject eObj = (EObject) source;
		return (Map<?, ?>) eObj.eGet(feature);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doSetMap(Object source, Map map, MapDiff diff) {
		EObject eObject = (EObject) source;
		eObject.eSet(feature, map);
	}

	@Override
	public INativePropertyListener adaptListener(
			ISimplePropertyListener listener) {
		return new Listener(listener);
	}

	private class Listener extends AdapterImpl implements
			INativePropertyListener {
		private final ISimplePropertyListener listener;

		private Listener(ISimplePropertyListener listener) {
			this.listener = listener;
		}

		@Override
		public void notifyChanged(Notification msg) {
			if (feature == msg.getFeature() && !msg.isTouch()) {
				// TODO
				// This assumes we only get a SET notification, which isn't a
				// good assumption.
				//
				final MapDiff diff = Diffs.createMapDiffSingleChange(msg
						.getNotifier(), msg.getOldValue(), msg.getNewValue());
				listener.handlePropertyChange(new SimplePropertyEvent(msg
						.getNotifier(), EMFMapProperty.this, diff));
			}
		}
	}

	@Override
	protected void doAddListener(Object source, INativePropertyListener listener) {
		EObject eObj = (EObject) source;
		eObj.eAdapters().add((Adapter) listener);
	}

	@Override
	protected void doRemoveListener(Object source,
			INativePropertyListener listener) {
		EObject eObj = (EObject) source;
		eObj.eAdapters().remove((Adapter) listener);
	}

	@Override
	public String toString() {
		String s = feature.getName() + "{:}"; //$NON-NLS-1$
		if (keyType != null || valueType != null)
			s += "<" + (keyType != null ? keyType.getName() : "null") + ", " //$NON-NLS-1$ //$NON-NLS-2$
					+ (valueType != null ? valueType.getName() : "null") + ">"; //$NON-NLS-1$
		return super.toString();
	}
}
