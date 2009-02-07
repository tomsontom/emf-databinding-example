package org.eclipse.emf.examples.library.databinding.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.library.databinding.ILibraryServiceRegistry;

public class LibraryServiceRegistryImpl implements ILibraryServiceRegistry {
	
	private Collection<ILibraryPersistenceService> services = Collections
			.synchronizedCollection(new ArrayList<ILibraryPersistenceService>());

	private Collection<Listener> listeners = Collections
			.synchronizedCollection(new ArrayList<Listener>());

	public void registerService(ILibraryPersistenceService libraryService) {
		services.add(libraryService);
		for( Listener listener: listeners ) {
			listener.serviceAdded(libraryService);
		}
	}

	public void unregister(ILibraryPersistenceService libraryService) {
		services.remove(libraryService);
		for( Listener listener: listeners ) {
			listener.serviceRemoved(libraryService);
		}
	}

	public ILibraryPersistenceService getServiceById(String serviceId) {
		for (ILibraryPersistenceService s : services) {
			if (serviceId.equals(s.getIdentifier())) {
				return s;
			}
		}

		return null;
	}

	public ILibraryPersistenceService[] getServicesInCategory(String category) {
		ArrayList<ILibraryPersistenceService> services = new ArrayList<ILibraryPersistenceService>();
		for (ILibraryPersistenceService s : this.services) {
			if (category.equals(s.getCategory())) {
				services.add(s);
			}
		}
		return services.toArray(new ILibraryPersistenceService[0]);
	}

	public String[] getCategories() {
		ArrayList<String> cats = new ArrayList<String>();
		for (ILibraryPersistenceService s : services) {
			if (!cats.contains(s.getCategory())) {
				cats.add(s.getCategory());
			}
		}

		return cats.toArray(new String[0]);
	}
	
	public void addListener(Listener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(Listener listener) {
		listeners.add(listener);
	}
}
