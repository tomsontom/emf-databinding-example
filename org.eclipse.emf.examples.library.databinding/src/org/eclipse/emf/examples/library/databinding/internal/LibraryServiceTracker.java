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

import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.library.databinding.ILibraryServiceRegistry;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

public class LibraryServiceTracker extends ServiceTracker {
	
	private int finderCount = 0;
	private boolean registering = false;
	private ServiceRegistration registration = null;
	private ILibraryServiceRegistry registry;
	
	public LibraryServiceTracker(BundleContext context, ILibraryServiceRegistry registry) {
		super(context, ILibraryPersistenceService.class.getName(), null);
		this.registry = registry;
	}
	
	public Object addingService(ServiceReference reference) {
		ILibraryPersistenceService libraryService = (ILibraryPersistenceService) context.getService(reference); 
		registry.registerService(libraryService); 
 
		synchronized(this) { 
			finderCount ++; 
			if (registering) 
				return libraryService; 
			registering = (finderCount == 1); 
			if (!registering) 
				return libraryService; 
		} 
 
		ServiceRegistration reg = context.registerService( ILibraryServiceRegistry.class.getName(), registry, null); 
 
		synchronized(this) { 
			registering = false; 
			registration = reg; 
		} 
 
		return libraryService; 

	}
	
	public void removedService(ServiceReference reference, Object service) {
		ILibraryPersistenceService libraryService = (ILibraryPersistenceService) service; 
		registry.unregister(libraryService); 
		context.ungetService(reference);
		
		ServiceRegistration needsUnregistration = null; 
		synchronized(this) { 
			finderCount --; 
			if (finderCount == 0) { 
				needsUnregistration = registration; 
				registration = null; 
			} 
		} 
 
		if(needsUnregistration != null) { 
			needsUnregistration.unregister(); 
		}
	}
}
