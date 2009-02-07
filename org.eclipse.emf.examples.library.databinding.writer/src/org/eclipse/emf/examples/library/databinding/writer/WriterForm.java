package org.eclipse.emf.examples.library.databinding.writer;

import java.util.Arrays;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.library.databinding.AbstractForm;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider.CondiditionalTemplate;
import org.eclipse.emf.examples.library.databinding.writer.handler.CreateNewWriterHandler;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
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
	
	@Override
	public TabItem doCreateForm(IWorkbenchPartSite site, TabFolder folder,
			EditingDomain domain, DataBindingContext context,
			IObservableValue master) {
		
		TabItem item = new TabItem(folder,SWT.NONE);
		item.setText("Writers");
		
		Composite comp = new Composite(folder,SWT.NONE);
		TableColumnLayout layout = new TableColumnLayout();
		comp.setLayout(layout);
		
		TableViewer writerViewer = new TableViewer(comp,SWT.FULL_SELECTION|SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL|SWT.MULTI);
		writerViewer.getTable().setHeaderVisible(true);
		writerViewer.getTable().setLinesVisible(true);
		
		ObservableListContentProvider cp = new ObservableListContentProvider();
		writerViewer.setContentProvider(cp);
		
		
		CondiditionalTemplate[] tpl = new CondiditionalTemplate[4];
		tpl[0] = new CondiditionalTemplate("${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Writer && ((Writer)element).getFirstName() != null;
			}
			
		};
		tpl[1] = new CondiditionalTemplate("${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Writer && ((Writer)element).getLastName() != null;
			}
			
		};
		tpl[2] = new CondiditionalTemplate("${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Writer && ((Writer)element).getName() != null;
			}
			
		};
		tpl[3] = new CondiditionalTemplate("-") {

			@Override
			public boolean isTemplate(EObject element) {
				return true;
			}
			
		};
		
		EStructuralFeature[] features = new EStructuralFeature[3];
		features[0] = EXTLibraryPackage.Literals.PERSON__FIRST_NAME;
		features[1] = EXTLibraryPackage.Literals.PERSON__LAST_NAME;
		features[2] = EXTLibraryPackage.Literals.WRITER__NAME;
		
		IObservableMap[] map = EMFEditObservables.observeMaps(domain, cp.getKnownElements(), features);
		
		TableViewerColumn c = new TableViewerColumn(writerViewer,SWT.NONE);
		c.getColumn().setText("Firstname");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		c.setLabelProvider(new ObservableColumnLabelProvider(new IObservableMap[] { map[0] },Arrays.asList(tpl[0],tpl[3])));
		
		c = new TableViewerColumn(writerViewer,SWT.NONE);
		c.getColumn().setText("Lastname");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		c.setLabelProvider(new ObservableColumnLabelProvider(new IObservableMap[] { map[1] },Arrays.asList(tpl[1],tpl[3])));
		
		c = new TableViewerColumn(writerViewer,SWT.NONE);
		c.getColumn().setText("Pen name");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		c.setLabelProvider(new ObservableColumnLabelProvider(new IObservableMap[] { map[2] },Arrays.asList(tpl[2],tpl[3])));
		
		MenuManager mgr = new MenuManager();
		writerViewer.getControl().setMenu(mgr.createContextMenu(writerViewer.getControl()));
		site.registerContextMenu("org.eclipse.emf.examples.library.databinding.libraryeditor.writer", mgr, writerViewer);
		
		writerViewer.setInput(EMFEditObservables.observeDetailList(Realm.getDefault(), domain, master, EXTLibraryPackage.Literals.LIBRARY__WRITERS));
		item.setControl(comp);
		
		return item;
	}

	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) { 
		if( commandId.equals(CreateNewWriterHandler.commandId) ) {
			MessageDialog.openError(getItem().getParent().getShell(), "Creating Stock item failed", exception.getMessage());
		}
	}

	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		if( commandId.equals(CreateNewWriterHandler.commandId) ) {
			getItem().getParent().setSelection(getItem());
		}
	}

}
