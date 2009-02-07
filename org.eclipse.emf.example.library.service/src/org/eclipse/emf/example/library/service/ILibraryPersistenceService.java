package org.eclipse.emf.example.library.service;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface ILibraryPersistenceService {
	public interface Listener {
		public void dirtyStateChanged(boolean newState);
	}
	
	public EditingDomain getEditingDomain();
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
