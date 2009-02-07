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
