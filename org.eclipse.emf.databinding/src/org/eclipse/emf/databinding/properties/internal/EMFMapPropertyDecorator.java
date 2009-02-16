/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 195222)
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/
package org.eclipse.emf.databinding.properties.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;
import org.eclipse.emf.databinding.properties.EMFProperties;
import org.eclipse.emf.databinding.properties.IEMFMapProperty;
import org.eclipse.emf.databinding.properties.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * @since 1.1
 */
public class EMFMapPropertyDecorator extends MapProperty implements
		IEMFMapProperty {
	private final IMapProperty delegate;
	private final EStructuralFeature feature;

	/**
	 * @param delegate
	 * @param feature
	 */
	public EMFMapPropertyDecorator(IMapProperty delegate, EStructuralFeature feature) {
		this.delegate = delegate;
		this.feature = feature;
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}

	public Object getKeyType() {
		return delegate.getKeyType();
	}

	public Object getValueType() {
		return delegate.getValueType();
	}
	
	public IEMFMapProperty values(EStructuralFeature... featurePath) { 
		return values(EMFProperties.value(featurePath));
	}

	public IEMFMapProperty values(IEMFValueProperty property) {
		return new EMFMapPropertyDecorator(super.values(property),property.getFeature());
	}

	public IObservableMap observe(Object source) {
		return new EMFObservableMapDecorator(delegate.observe(source),
				feature);
	}
	
	public IObservableMap observe(Realm realm, Object source) {
		return new EMFObservableMapDecorator(delegate.observe(realm, source),
				feature);
	}
	
	public IObservableFactory mapFactory() {
		return delegate.mapFactory();
	}

	public IObservableFactory mapFactory(Realm realm) {
		return delegate.mapFactory(realm);
	}

	public IObservableMap observeDetail(IObservableValue master) {
		return new EMFObservableMapDecorator(delegate.observeDetail(master),
				feature);
	}
	
	public String toString() {
		return delegate.toString();
	}
}
