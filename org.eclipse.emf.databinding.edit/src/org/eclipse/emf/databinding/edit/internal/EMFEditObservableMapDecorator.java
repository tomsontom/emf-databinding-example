/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 221704)
 *     Matthew Hall - bug 246625
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.map.DecoratingObservableMap;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * {@link IEMFObservable} decorator for an {@link IObservableMap}.
 * 
 * @since 1.1
 */
public class EMFEditObservableMapDecorator extends DecoratingObservableMap
		implements IEMFObservable {
	private EStructuralFeature feature;
	private EditingDomain domain;

	/**
	 * @param decorated
	 * @param feature
	 */
	public EMFEditObservableMapDecorator(EditingDomain domain, IObservableMap decorated, EStructuralFeature feature) {
		super(decorated, true);
		this.feature = feature;
		this.domain = domain;
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
