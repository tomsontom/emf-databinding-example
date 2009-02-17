/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Tom Schindl<tom.schindl@bestsolution.at>
 * </copyright>
 *
 * $Id: EObjectObservableValue.java,v 1.2 2008/01/26 21:01:07 emerks Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.AbstractObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.ValueDiff;
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
 * @deprecated you should not use this type it will be removed. Use the generic
 *             {@link IObservableValue}
 */
public class EObjectObservableValue extends AbstractObservableValue implements
		IObserving {
	/**
	 * The object owning the feature
	 */
	protected EObject eObject;

	/**
	 * The feature observed
	 */
	protected EStructuralFeature eStructuralFeature;

	/**
	 * The adapter attached to listen for changes
	 */
	protected Adapter listener;

	/**
	 * Create a new observable
	 * 
	 * @param eObject
	 *            the object owning the observed feature
	 * @param eStructuralFeature
	 *            the feature observed
	 * @deprecated you should use
	 *             {@link EMFObservables#observeValue(EObject, EStructuralFeature)}
	 */
	public EObjectObservableValue(EObject eObject,
			EStructuralFeature eStructuralFeature) {
		this(Realm.getDefault(), eObject, eStructuralFeature);
	}

	/**
	 * Create a new observable
	 * 
	 * @param realm
	 *            the realm used to sync the feature
	 * @param eObject
	 *            the object owning the observed feature
	 * @param eStructuralFeature
	 *            the feature observed
	 */
	public EObjectObservableValue(Realm realm, EObject eObject,
			EStructuralFeature eStructuralFeature) {
		super(realm);
		this.eObject = eObject;
		this.eStructuralFeature = eStructuralFeature;
	}

	@Override
	public synchronized void dispose() {
		if (listener != null) {
			eObject.eAdapters().remove(listener);
			listener = null;
		}
		eObject = null;
		eStructuralFeature = null;
		super.dispose();
	}

	public Object getObserved() {
		return eObject;
	}

	@Override
	protected void firstListenerAdded() {
		listener = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification notification) {
				if (eStructuralFeature == notification.getFeature()
						&& !notification.isTouch()) {
					final ValueDiff diff = Diffs.createValueDiff(notification
							.getOldValue(), notification.getNewValue());
					getRealm().exec(new Runnable() {
						public void run() {
							fireValueChange(diff);
						}
					});
				}
			}
		};
		eObject.eAdapters().add(listener);
	}

	@Override
	protected void lastListenerRemoved() {
		eObject.eAdapters().remove(listener);
		listener = null;
	}

	@Override
	protected Object doGetValue() {
		return eObject.eGet(eStructuralFeature);
	}

	@Override
	protected void doSetValue(Object value) {
		eObject.eSet(eStructuralFeature, value);
	}

	public Object getValueType() {
		return eStructuralFeature;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(getClass().getName());
		result.append('@');
		result.append(Integer.toHexString(hashCode()));

		result.append(" (eObject:");
		result.append(eObject);
		result.append(")");

		result.append(" (eStructuralFeature: ");
		result.append(eStructuralFeature);
		result.append(")");

		try {
			Object value = eObject.eGet(eStructuralFeature, false);
			result.append(" (value: ");
			result.append(value);
			result.append(")");
		} catch (Exception exception) {
			// Ignore.
		}

		return result.toString();
	}
}
