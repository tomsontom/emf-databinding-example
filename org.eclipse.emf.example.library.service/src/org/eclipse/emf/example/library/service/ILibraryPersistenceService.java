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

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;

public interface ILibraryPersistenceService {
	public interface Listener {
		public void dirtyStateChanged(boolean newState);
	}
	
	public ISavePointEditingDomain getEditingDomain();
	public IStatus save();
	public IObservableList getLibraries();
	public String getIdentifier();
	public String getLabel();
	public String getToolTip();
	public String getCategory();
	public boolean isDirty();
	public Resource getResource();
	
	public void addListener(Listener listener);
	public void removeListener(Listener listener);
}
