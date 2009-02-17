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
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.databinding.property.value.ValueProperty;
import org.eclipse.emf.databinding.internal.EMFObservableListDecorator;
import org.eclipse.emf.databinding.internal.EMFObservableMapDecorator;
import org.eclipse.emf.databinding.internal.EMFObservableValueDecorator;
import org.eclipse.emf.databinding.properties.EMFProperties;
import org.eclipse.emf.databinding.properties.IEMFListProperty;
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
public class EMFValuePropertyDecorator extends ValueProperty implements IEMFValueProperty {
	private final IValueProperty delegate;
	private final EStructuralFeature feature;
	
	/**
	 * @param delegate
	 * @param feature
	 */
	public EMFValuePropertyDecorator(IValueProperty delegate, EStructuralFeature feature) {
		this.delegate = delegate;
		this.feature = feature;
	}
	
	public Object getValueType() {
		return delegate.getValueType();
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}
	
	public IEMFValueProperty value(EStructuralFeature... featurePath) {
		return value(EMFProperties.value(featurePath));
	}

	public IEMFValueProperty value(IEMFValueProperty property) {
		return new EMFValuePropertyDecorator(super.value(property),property.getFeature());
	}
	
	public IEMFListProperty list(EStructuralFeature featurePath) {
		return list(EMFProperties.list(featurePath));
	}

	public IEMFListProperty list(IEMFListProperty property) {
		return new EMFListPropertyDecorator(super.list(property),property.getFeature());
	}
	
//	public IEMFSetProperty set(EStructuralFeature featurePath) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public IEMFSetProperty set(IEMFSetProperty property) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public IEMFMapProperty map(EStructuralFeature featurePath) {
		return map(EMFProperties.map(featurePath));
	}

	public IEMFMapProperty map(IEMFMapProperty property) {
		return new EMFMapPropertyDecorator(super.map(property),property.getFeature());
	}

	public IObservableValue observe(Object source) {
		return new EMFObservableValueDecorator(delegate.observe(source),
				feature);
	}
	
	public IObservableValue observe(Realm realm, Object source) {
		return new EMFObservableValueDecorator(
				delegate.observe(realm, source), feature);
	}
	
	public IObservableFactory valueFactory() {
		return delegate.valueFactory();
	}

	public IObservableFactory valueFactory(Realm realm) {
		return delegate.valueFactory(realm);
	}

	public IObservableValue observeDetail(IObservableValue master) {
		return new EMFObservableValueDecorator(delegate.observeDetail(master),
				feature);
	}

	public IObservableList observeDetail(IObservableList master) {
		return new EMFObservableListDecorator(delegate.observeDetail(master),
				feature);
	}
	
	public IObservableMap observeDetail(IObservableSet master) {
		return new EMFObservableMapDecorator(delegate.observeDetail(master),
				feature);
	}

	public IObservableMap observeDetail(IObservableMap master) {
		return new EMFObservableMapDecorator(delegate.observeDetail(master),
				feature);
	}
}
