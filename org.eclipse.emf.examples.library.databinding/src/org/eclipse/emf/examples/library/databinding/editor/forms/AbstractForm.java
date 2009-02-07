package org.eclipse.emf.examples.library.databinding.editor.forms;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.ui.IWorkbenchPartSite;

public abstract class AbstractForm {
	public abstract void createForm(IWorkbenchPartSite site, TabFolder folder, EditingDomain domain, DataBindingContext context, IObservableValue master);
	public abstract void postExecuteFailure(String commandId,ExecutionException exception);
	public abstract void postExecuteSuccess(String commandId, Object returnValue);
}
