package org.eclipse.emf.examples.library.databinding.editor.forms;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.library.databinding.internal.handler.CreateNewStockItemHandler;
import org.eclipse.emf.examples.library.databinding.internal.handler.CreateNewWriterHandler;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.IWorkbenchPartSite;

public class WriterForm extends AbstractForm {
	private TabItem item;
	
	@Override
	public void createForm(IWorkbenchPartSite site, TabFolder folder,
			EditingDomain domain, DataBindingContext context,
			IObservableValue master) {
		
		item = new TabItem(folder,SWT.NONE);
		item.setText("Writers");
		
		Composite comp = new Composite(folder,SWT.NONE);
		TableColumnLayout layout = new TableColumnLayout();
		comp.setLayout(layout);
		
		TableViewer writerViewer = new TableViewer(comp,SWT.FULL_SELECTION|SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI);
		writerViewer.getTable().setHeaderVisible(true);
		writerViewer.getTable().setLinesVisible(true);
		
		
		TableViewerColumn c = new TableViewerColumn(writerViewer,SWT.NONE);
		c.getColumn().setText("Firstname");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		
		c = new TableViewerColumn(writerViewer,SWT.NONE);
		c.getColumn().setText("Lastname");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		
		c = new TableViewerColumn(writerViewer,SWT.NONE);
		c.getColumn().setText("Pen name");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		
		
		MenuManager mgr = new MenuManager();
		writerViewer.getControl().setMenu(mgr.createContextMenu(writerViewer.getControl()));
		site.registerContextMenu("org.eclipse.emf.examples.library.databinding.libraryeditor.writer", mgr, writerViewer);
		
		item.setControl(comp);
	}

	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) { 
		if( commandId.equals(CreateNewWriterHandler.commandId) ) {
			MessageDialog.openError(item.getParent().getShell(), "Creating Stock item failed", exception.getMessage());
		}
	}

	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		// TODO Auto-generated method stub
		
	}

}
