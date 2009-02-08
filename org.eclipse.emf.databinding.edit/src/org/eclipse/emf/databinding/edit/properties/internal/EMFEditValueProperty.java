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
package org.eclipse.emf.databinding.edit.properties.internal;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.SimplePropertyEvent;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * @since 1.1
 */
public class EMFEditValueProperty extends SimpleValueProperty {
	private EStructuralFeature feature;
	private EditingDomain domain;
	
	/**
	 * @param feature
	 */
	public EMFEditValueProperty(EditingDomain domain, EStructuralFeature feature) {
		this.feature = feature;
		this.domain = domain;
	}
	
	public Object getValueType() {
		return feature.getEType().getInstanceClass();
	}
	
	@Override
	protected Object doGetValue(Object source) {
		EObject eObj = (EObject) source;
		return eObj.eGet(feature);
	}

	
	@Override
	protected void doSetValue(Object source, Object value) {
		EObject eObj = (EObject) source;
		Command command = SetCommand.create(domain, eObj, feature, value);
	    domain.getCommandStack().execute(command);
	}
	
	@Override
	public INativePropertyListener adaptListener(
			ISimplePropertyListener listener) {
		return new Listener(listener);
	}
	
	private class Listener extends AdapterImpl implements INativePropertyListener {
		private final ISimplePropertyListener listener;
		
		private Listener(ISimplePropertyListener listener) {
			this.listener = listener;
		}

		@Override
		public void notifyChanged(Notification msg) {
			if( feature == msg.getFeature() && !msg.isTouch() ) {
				ValueDiff diff;
				Object oldValue = msg.getOldValue();
				Object newValue = msg.getNewValue();
				if (oldValue != null && newValue != null) {
					diff = Diffs.createValueDiff(oldValue, newValue);
				} else {
					diff = null;
				}
				listener.handlePropertyChange(new SimplePropertyEvent(msg.getNotifier(), EMFEditValueProperty.this, diff));				
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
}