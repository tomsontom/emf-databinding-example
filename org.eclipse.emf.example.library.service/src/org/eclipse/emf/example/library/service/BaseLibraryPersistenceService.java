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
package org.eclipse.emf.example.library.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.example.library.service.internal.SavePointEditingDomain;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;

public abstract class BaseLibraryPersistenceService implements
		ILibraryPersistenceService {
	private ISavePointEditingDomain editingDomain;
	private ResourceSet resourceSet;
	private boolean dirtyState = false;
	private BasicCommandStack commandStack;
	private Collection<Listener> listeners = new ArrayList<Listener>();

	private class CommandStackListenerImpl implements CommandStackListener {

		public void commandStackChanged(EventObject event) {
			BasicCommandStack stack = (BasicCommandStack) event.getSource();
			Command mostRecentCommand = stack.getMostRecentCommand();
			if (mostRecentCommand != null) {
				boolean newState = stack.isSaveNeeded();
				if (newState != dirtyState) {
					dirtyState = !dirtyState;
					fireDirtyStateChanged();
				}
			}
		}
	}

	public BaseLibraryPersistenceService() {
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(EXTLibraryPackage.eNS_URI,
				EXTLibraryPackage.eINSTANCE);

		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		// adapterFactory.addAdapterFactory(new
		// SoccerItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		commandStack = new BasicCommandStack();
		commandStack.addCommandStackListener(new CommandStackListenerImpl());
		editingDomain = new SavePointEditingDomain(new AdapterFactoryEditingDomain(adapterFactory,
				commandStack, resourceSet));
	}

	public ISavePointEditingDomain getEditingDomain() {
		return editingDomain;
	}

	public IObservableList getLibraries() {
		return EMFObservables.observeResourceContents(doGetResource());
	}

	private void fireDirtyStateChanged() {
		for (Listener l : listeners) {
			l.dirtyStateChanged(dirtyState);
		}
	}

	public IStatus save() {
		IStatus s = doSave();

		if (s.isOK()) {
			commandStack.saveIsDone();
			dirtyState = false;
			fireDirtyStateChanged();
		}

		return s;
	}

	protected final ResourceSet getResourceSet() {
		return resourceSet;
	}
	
	public Resource getResource() {
		return doGetResource();
	}

	protected abstract Resource doGetResource();

	protected abstract IStatus doSave();

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}

	public boolean isDirty() {
		return dirtyState;
	}
}