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
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.databinding.property.value.ValueProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditObservableListDecorator;
import org.eclipse.emf.databinding.edit.internal.EMFEditObservableMapDecorator;
import org.eclipse.emf.databinding.edit.internal.EMFEditObservableValueDecorator;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditListProperty;
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
public class EMFEditValuePropertyDecorator extends ValueProperty implements IEMFEditValueProperty {
	private final IValueProperty delegate;
	private final EStructuralFeature feature;
	private EditingDomain domain;
	
	/**
	 * @param delegate
	 * @param feature
	 */
	public EMFEditValuePropertyDecorator(EditingDomain domain, IValueProperty delegate, EStructuralFeature feature) {
		this.delegate = delegate;
		this.feature = feature;
		this.domain = domain;
	}
	
	public Object getValueType() {
		return delegate.getValueType();
	}
	
	public EStructuralFeature getFeature() {
		return feature;
	}
	
	public IEMFEditValueProperty value(EStructuralFeature... featurePath) {
		return value(EMFEditProperties.value(domain, featurePath));
	}

	public IEMFEditValueProperty value(IEMFEditValueProperty property) {
		return new EMFEditValuePropertyDecorator(property.getDomain(), super.value(property),property.getFeature());
	}
	
	public IEMFEditListProperty list(EStructuralFeature featurePath) {
		return list(EMFEditProperties.list(domain, featurePath));
	}

	public IEMFEditListProperty list(IEMFEditListProperty property) {
		return new EMFEditListPropertyDecorator(property.getDomain(), super.list(property),property.getFeature());
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

	public IEMFEditMapProperty map(EStructuralFeature featurePath) {
		return map(EMFEditProperties.map(domain, featurePath));
	}

	public IEMFEditMapProperty map(IEMFEditMapProperty property) {
		return new EMFEditMapPropertyDecorator(property.getDomain(), super.map(property),property.getFeature());
	}

	public IObservableValue observe(Object source) {
		return new EMFEditObservableValueDecorator(domain, delegate.observe(source),
				feature);
	}
	
	public IObservableValue observe(Realm realm, Object source) {
		return new EMFEditObservableValueDecorator(domain,
				delegate.observe(realm, source), feature);
	}
	
	public IObservableFactory valueFactory() {
		return delegate.valueFactory();
	}

	public IObservableFactory valueFactory(Realm realm) {
		return delegate.valueFactory(realm);
	}

	public IObservableValue observeDetail(IObservableValue master) {
		return new EMFEditObservableValueDecorator(domain,delegate.observeDetail(master),
				feature);
	}

	public IObservableList observeDetail(IObservableList master) {
		return new EMFEditObservableListDecorator(domain, delegate.observeDetail(master),
				feature);
	}
	
	public IObservableMap observeDetail(IObservableSet master) {
		return new EMFEditObservableMapDecorator(domain, delegate.observeDetail(master),
				feature);
	}

	public IObservableMap observeDetail(IObservableMap master) {
		return new EMFEditObservableMapDecorator(domain, delegate.observeDetail(master),
				feature);
	}

	public EditingDomain getDomain() {
		return domain;
	}
}
