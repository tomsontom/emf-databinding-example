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
package org.eclipse.emf.databinding.edit.properties.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditMapProperty;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * @since 1.1
 */
public class EMFEditMapPropertyDecorator extends MapProperty implements
		IEMFEditMapProperty {
	private final IMapProperty delegate;
	private final EStructuralFeature feature;
	private EditingDomain domain;

	/**
	 * @param delegate
	 * @param feature
	 */
	public EMFEditMapPropertyDecorator(EditingDomain domain, IMapProperty delegate, EStructuralFeature feature) {
		this.delegate = delegate;
		this.feature = feature;
		this.domain = domain;
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
	
	public IEMFEditMapProperty values(EStructuralFeature... featurePath) { 
		return values(EMFEditProperties.value(domain, featurePath));
	}

	public IEMFEditMapProperty values(IEMFEditValueProperty property) {
		return new EMFEditMapPropertyDecorator(property.getDomain(), super.values(property),property.getFeature());
	}

	public IObservableMap observe(Object source) {
		return new EMFEditObservableMapDecorator(domain, delegate.observe(source),
				feature);
	}
	
	public IObservableMap observe(Realm realm, Object source) {
		return new EMFEditObservableMapDecorator(domain, delegate.observe(realm, source),
				feature);
	}
	
	public IObservableFactory mapFactory() {
		return delegate.mapFactory();
	}

	public IObservableFactory mapFactory(Realm realm) {
		return delegate.mapFactory(realm);
	}

	public IObservableMap observeDetail(IObservableValue master) {
		return new EMFEditObservableMapDecorator(domain, delegate.observeDetail(master),
				feature);
	}

	public EditingDomain getDomain() {
		return domain;
	}
}
