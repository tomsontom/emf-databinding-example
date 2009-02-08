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
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.list.ListProperty;
import org.eclipse.emf.databinding.properties.EMFProperties;
import org.eclipse.emf.databinding.properties.IEMFListProperty;
import org.eclipse.emf.databinding.properties.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * @since 1.1
 */
public class EMFListPropertyDecorator extends ListProperty implements IEMFListProperty {
	private final IListProperty delegate;
	private final EStructuralFeature feature;
	
	/**
	 * @param delegate
	 * @param feature
	 */
	public EMFListPropertyDecorator(IListProperty delegate, EStructuralFeature feature) {
		this.delegate = delegate;
		this.feature = feature;
	}
	
	public Object getElementType() {
		return feature.getEType().getInstanceClass();
	}
	
	public IEMFListProperty values(EStructuralFeature... feature) {
		return values(EMFProperties.value(feature));
	}
	public IEMFListProperty values(IEMFValueProperty property) {
		return new EMFListPropertyDecorator(super.values(property),property.getFeature());
	}
	public EStructuralFeature getFeature() {
		return feature;
	}
	
	public IObservableList observe(Object source) {
		return new EMFObservableListDecorator(delegate.observe(source),
				feature);
	}
	
	public IObservableList observe(Realm realm, Object source) {
		return new EMFObservableListDecorator(delegate.observe(realm, source),
				feature);
	}
	
	public IObservableFactory listFactory() {
		return delegate.listFactory();
	}
	
	public IObservableFactory listFactory(Realm realm) {
		return delegate.listFactory(realm);
	}

	public IObservableList observeDetail(IObservableValue master) {
		return new EMFObservableListDecorator(delegate.observeDetail(master),
				feature);
	}
}
