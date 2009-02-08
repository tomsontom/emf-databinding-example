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
package org.eclipse.emf.examples.library.databinding.internal.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.library.databinding.ILibraryServiceRegistry;
import org.eclipse.emf.examples.library.databinding.internal.Activator;
import org.eclipse.emf.examples.library.databinding.internal.LibraryPersistenceEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class OpenLibraryEditorHandler extends AbstractHandler {
	public static final String commandId = "org.eclipse.emf.examples.library.databinding.openlibrary";
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ILibraryServiceRegistry registry = Activator.getDefault().getLiberyRegistry();
		if( registry != null ) {
			String serviceId = event.getParameter("org.eclipse.emf.examples.library.databinding.persistenceid");
			ILibraryPersistenceService service = registry.getServiceById(serviceId);
			if( service != null ) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new LibraryPersistenceEditorInput(service), "org.eclipse.emf.examples.library.databinding.libraryeditor");
				} catch (PartInitException e) {
					return new Status(IStatus.ERROR,Activator.PLUGIN_ID,"Opening dialog failed",e);
				}	
			}
		}
		return Status.OK_STATUS;
	}

}
