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
