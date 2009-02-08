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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

public class LibraryElementFactory implements IElementFactory {

	public IAdaptable createElement(IMemento memento) {
		ILibraryPersistenceService service = Activator.getDefault().getLiberyRegistry().getServiceById(memento.getString("serviceid"));
		if( service != null ) {
			return new LibraryPersistenceEditorInput(service);
		}
		return null;
	}

}
