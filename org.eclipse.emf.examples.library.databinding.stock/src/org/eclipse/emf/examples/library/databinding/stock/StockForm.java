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
package org.eclipse.emf.examples.library.databinding.stock;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.extlibrary.AudioVisualItem;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Item;
import org.eclipse.emf.examples.extlibrary.Periodical;
import org.eclipse.emf.examples.extlibrary.VideoCassette;
import org.eclipse.emf.examples.library.databinding.AbstractForm;
import org.eclipse.emf.examples.library.databinding.ISources;
import org.eclipse.emf.examples.library.databinding.SourceProvider;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider.CondiditionalTemplate;
import org.eclipse.emf.examples.library.databinding.stock.handler.CreateNewStockItemHandler;
import org.eclipse.emf.examples.library.databinding.stock.handler.EditStockItemHandler;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.services.ISourceProviderService;

public class StockForm extends AbstractForm {
	
	private TableViewer itemViewer;
	
	private class LengthConverter extends Converter {

		public LengthConverter() {
			super(Collection.class, String.class);
		}

		public Object convert(Object fromObject) {
			return "Stock (" + ((Collection<?>)fromObject).size() + " Items)";
		}
		
	}
	
	@Override
	public void doCreateForm(TabFolder folder, TabItem item, EditingDomain domain, DataBindingContext dbc,
			IObservableValue master) {
		Composite comp = new Composite(folder,SWT.NONE);
		IValueProperty textProp = WidgetProperties.text();
		dbc.bindValue(textProp.observe(item), EMFEditObservables.observeDetailValue(Realm.getDefault(), domain, master, EXTLibraryPackage.Literals.LIBRARY__STOCK),new UpdateValueStrategy(), new UpdateValueStrategy().setConverter(new LengthConverter()));
		
		TableColumnLayout layout = new TableColumnLayout();
		comp.setLayout(layout);
		
		itemViewer = new TableViewer(comp,SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION|SWT.MULTI);
		itemViewer.getTable().setHeaderVisible(true);
		itemViewer.getTable().setLinesVisible(true);
		ObservableListContentProvider cp = new ObservableListContentProvider();
		itemViewer.setContentProvider(cp);
		itemViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				ISourceProviderService service = (ISourceProviderService) getSite().getService(ISourceProviderService.class);
				SourceProvider pv = (SourceProvider) service.getSourceProvider(ISources.SELECTED_STOCK_ITEM_NAME);
				pv.setItemSelection(event.getSelection());
			}
			
		});
		
		CondiditionalTemplate tplBookTitle = new CondiditionalTemplate("${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Book && ((Book)element).getTitle() != null;
			}
			
		};
		CondiditionalTemplate tplAudiovisualTitle = new CondiditionalTemplate("${1}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof AudioVisualItem && ((AudioVisualItem)element).getTitle() != null;
			}
			
		};
		CondiditionalTemplate tplPeriodicalTitle = new CondiditionalTemplate("${2}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Periodical && ((Periodical)element).getTitle() != null;
			}
			
		};

		CondiditionalTemplate tplPublicationDate = new CondiditionalTemplate("${0,date,medium}") {

			@Override
			public boolean isTemplate(EObject element) {
				return ((Item)element).getPublicationDate() != null;
			}
			
		};
		
		CondiditionalTemplate tplNullValue = new CondiditionalTemplate("-") {

			@Override
			public boolean isTemplate(EObject element) {
				return true;
			}
			
		};

		IObservableMap featBookTitle = EMFEditObservables.observeMap(domain, cp.getKnownElements(), EXTLibraryPackage.Literals.BOOK__TITLE);
		IObservableMap featAudiovisualTitle = EMFEditObservables.observeMap(domain, cp.getKnownElements(), EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__TITLE);
		IObservableMap featPeriodicalTitle = EMFEditObservables.observeMap(domain, cp.getKnownElements(), EXTLibraryPackage.Literals.PERIODICAL__TITLE);
		IObservableMap featItemPublicationDate = EMFEditObservables.observeMap(domain, cp.getKnownElements(), EXTLibraryPackage.Literals.ITEM__PUBLICATION_DATE);
		
		
		TableViewerColumn c = new TableViewerColumn(itemViewer,SWT.NONE);
		c.getColumn().setText("Title");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		c.setLabelProvider(new ObservableColumnLabelProvider(new IObservableMap[] { featBookTitle, featAudiovisualTitle, featPeriodicalTitle },Arrays.asList(tplBookTitle,tplAudiovisualTitle,tplPeriodicalTitle,tplNullValue)));
		
		c = new TableViewerColumn(itemViewer,SWT.NONE);
		c.getColumn().setText("Type");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		c.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				if( element instanceof Book ) {
					return "Book";
				} else if( element instanceof BookOnTape ) {
					return "Book On Tape";
				} else if( element instanceof VideoCassette ) {
					return "Video";
				} else if( element instanceof Periodical ) {
					return "Periodical";
				} else {
					return "";
				}
			}
			
		});
		
		c = new TableViewerColumn(itemViewer,SWT.NONE);
		c.getColumn().setText("Publication Date");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1,120));
		c.setLabelProvider(new ObservableColumnLabelProvider(new IObservableMap[] { featItemPublicationDate },Arrays.asList(tplPublicationDate,tplNullValue)));
		
		
		MenuManager mgr = new MenuManager();
		itemViewer.getControl().setMenu(mgr.createContextMenu(itemViewer.getControl()));
		getSite().registerContextMenu("org.eclipse.emf.examples.library.databinding.stock", mgr, itemViewer);
		
		item.setControl(comp);
		itemViewer.setInput(EMFEditObservables.observeDetailList(Realm.getDefault(), domain, master, EXTLibraryPackage.Literals.LIBRARY__STOCK));
	}

	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) {
		if( commandId.equals(CreateNewStockItemHandler.commandId) ) {
			MessageDialog.openError(getItem().getParent().getShell(), "Creating Stock item failed", exception.getMessage());
		}
	}

	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		if( commandId.equals(CreateNewStockItemHandler.commandId) ) {
			getItem().getParent().setSelection(getItem());
			itemViewer.setSelection(new StructuredSelection(returnValue));
			
			IHandlerService hs = (IHandlerService) getSite().getService(IHandlerService.class);
			try {
				hs.executeCommand(EditStockItemHandler.commandId, null);
			} catch (Exception e) {
				MessageDialog.openError(getItem().getParent().getShell(), "Creating Stock item failed", e.getMessage());
			}
		}
	}

	@Override
	protected void doDispose() {
		
	}

}
