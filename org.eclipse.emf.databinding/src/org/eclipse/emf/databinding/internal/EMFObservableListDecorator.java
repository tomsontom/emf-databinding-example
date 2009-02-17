/*******************************************************************************
 * Copyright (c) 2007 Brad Reynolds and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Brad Reynolds - initial API and implementation
 *     Matthew Hall - bugs 208858, 246625
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/
package org.eclipse.emf.databinding.internal;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.list.DecoratingObservableList;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * {@link IEMFObservable} decorator for an {@link IObservableList}.
 * 
 * @since 1.1
 */
public class EMFObservableListDecorator extends DecoratingObservableList
		implements IEMFObservable {
	private EStructuralFeature feature;
	
	/**
	 * @param decorated
	 * @param feature
	 */
	public EMFObservableListDecorator(IObservableList decorated, EStructuralFeature feature) {
		super(decorated, true);
		this.feature = feature;
	}

	public synchronized void dispose() {
		this.feature = null;
		super.dispose();
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}

	public Object getObserved() {
		IObservable decorated = getDecorated();
		if (decorated instanceof IObserving)
			return ((IObserving) decorated).getObserved();
		return null;
	}
}