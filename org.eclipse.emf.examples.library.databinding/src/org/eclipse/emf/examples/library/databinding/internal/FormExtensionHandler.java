/*******************************************************************************
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.examples.library.databinding.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;

public class FormExtensionHandler implements IExtensionChangeHandler {
	public interface IModificationListener {
		public void formAdd(FormDescriptor descriptor);
		
		public void formRemoved(FormDescriptor descriptor);
	}

	public static final String FORM_EXT = "subform";

	private static final String ELEM_DATASOURCE = "form";
	
	private Collection<FormDescriptor> descriptors;
	
	private Collection<IModificationListener> listeners = new ArrayList<IModificationListener>();
	
	public FormExtensionHandler(Collection<FormDescriptor> descriptors) {
		this.descriptors = descriptors;
	}
	
	protected boolean readElement(IExtensionTracker tracker,
			IConfigurationElement element) {
		String elemName = element.getName();
		if (elemName.equals(ELEM_DATASOURCE)) {
			return readDatasourceElement(tracker, element);
		}
		return false;
	}

	private boolean readDatasourceElement(IExtensionTracker tracker,
			IConfigurationElement element) {
		FormDescriptor descriptor = new FormDescriptor(element);
		descriptors.add(descriptor);
		tracker.registerObject(element.getDeclaringExtension(), descriptor, IExtensionTracker.REF_WEAK);
		
		for( IModificationListener l: listeners ) {
			l.formAdd(descriptor);
		}
		
		return true;
	}

	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		synchronized (descriptors) {
			IConfigurationElement[] elements = extension
					.getConfigurationElements();
			for (int i = 0; i < elements.length; i++) {
				if (!readElement(tracker, elements[i])) {
					log(
							elements[i],
							"Failed to read element",
							IStatus.ERROR);
				}
			}
		}
	}

	private void log(IConfigurationElement element, String text, int error) {
		Activator.getDefault().getLog().log(
				new Status(IStatus.ERROR, Activator.PLUGIN_ID, text));
	}

	public void removeExtension(IExtension extension, Object[] objects) {
		synchronized (descriptors) {
			for (int i = 0; i < objects.length; i++) {
				descriptors.remove(objects[i]);
				FormDescriptor descriptor = (FormDescriptor) objects[i];
				for( IModificationListener l : listeners ) {
					l.formRemoved(descriptor);
				}
			}
		}
	}
	
	public void addModificationListener(IModificationListener listener) {
		listeners.add(listener);
	}
	
	public void removeModificationListener(IModificationListener listener) {
		listeners.remove(listener);
	}
}