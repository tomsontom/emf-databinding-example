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
package org.eclipse.emf.examples.library.databinding;

import org.eclipse.emf.example.library.service.ILibraryPersistenceService;

public interface ILibraryServiceRegistry {
	
	public interface Listener {
		public void serviceAdded(ILibraryPersistenceService service);

		public void serviceRemoved(ILibraryPersistenceService service);
	}

	void registerService(ILibraryPersistenceService libraryService);

	void unregister(ILibraryPersistenceService libraryService);

	ILibraryPersistenceService getServiceById(String serviceId);
	
	ILibraryPersistenceService[] getServicesInCategory(String category);
	
	String[] getCategories();
	
	void addListener(Listener listener);
	
	void removeListener(Listener listener);
}
