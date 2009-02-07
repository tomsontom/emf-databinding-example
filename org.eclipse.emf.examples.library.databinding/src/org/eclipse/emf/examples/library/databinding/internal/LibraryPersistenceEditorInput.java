package org.eclipse.emf.examples.library.databinding.internal;

import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class LibraryPersistenceEditorInput implements IEditorInput {
	private ILibraryPersistenceService service;
	private IPersistableElement element = new IPersistableElement() {

		public String getFactoryId() {
			return "org.eclipse.emf.examples.library.databinding.libraryfactory";
		}

		public void saveState(IMemento memento) {
			memento.putString("serviceid", service.getIdentifier());
		}
		
	};
	
	public LibraryPersistenceEditorInput(ILibraryPersistenceService service) {
		this.service = service;
	}
	
	public boolean exists() {
		return this.service != null;
	}

	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		return service != null ? service.getLabel() : "";
	}

	public IPersistableElement getPersistable() {
		return service != null ? element : null;
	}

	public String getToolTipText() {
		return service != null ? service.getToolTip() : "";
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		if( adapter == ILibraryPersistenceService.class ) {
			return service;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryPersistenceEditorInput other = (LibraryPersistenceEditorInput) obj;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}

	
}
