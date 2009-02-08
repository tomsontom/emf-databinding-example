/*******************************************************************************
 * Copyright (c) 2007 Brad Reynolds and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Brad Reynolds - initial API and implementation
 *     Matthew Hall - bug 246625
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/
package org.eclipse.emf.databinding.edit.properties.internal;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.properties.IEMFObservable;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * {@link IEMFObservable} decorator for an {@link IObservableValue}.
 * 
 * @since 1.1
 */
public class EMFEditObservableValueDecorator extends DecoratingObservableValue
		implements IEMFObservable {
	private EStructuralFeature feature;
	private EditingDomain domain;
	
	/**
	 * @param decorated
	 * @param feature
	 */
	public EMFEditObservableValueDecorator(EditingDomain domain, IObservableValue decorated, EStructuralFeature feature) {
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
