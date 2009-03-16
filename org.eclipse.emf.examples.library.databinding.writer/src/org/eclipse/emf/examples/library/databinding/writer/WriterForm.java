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
package org.eclipse.emf.examples.library.databinding.writer;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.commands.ExecutionEvent;
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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.library.databinding.AbstractForm;
import org.eclipse.emf.examples.library.databinding.common.FocusCellOwnerDrawHighlighter;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider.CondiditionalTemplate;
import org.eclipse.emf.examples.library.databinding.writer.handler.CreateNewWriterHandler;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.TableViewerFocusCellManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.swt.IFocusService;

public class WriterForm extends AbstractForm {
	private class LengthConverter extends Converter {

		public LengthConverter() {
			super(Collection.class, String.class);
		}

		public Object convert(Object fromObject) {
			return "Writers (" + ((Collection<?>) fromObject).size() + ")";
		}

	}

	private static final String PREFIX = "org.eclipse.emf.examples.library.databinding.writer";
	private static final String WRITER_TABLE = PREFIX + ".table";
	private TableViewer writerViewer;

	@Override
	public void doCreateForm(TabFolder folder, TabItem item,
			final EditingDomain domain, DataBindingContext dbc,
			IObservableValue master) {

		IValueProperty textProp = WidgetProperties.text();
		dbc.bindValue(textProp.observe(item), EMFEditObservables
				.observeDetailValue(Realm.getDefault(), domain, master,
						EXTLibraryPackage.Literals.LIBRARY__WRITERS),
				new UpdateValueStrategy(), new UpdateValueStrategy()
						.setConverter(new LengthConverter()));

		Composite comp = new Composite(folder, SWT.NONE);
		TableColumnLayout layout = new TableColumnLayout();
		comp.setLayout(layout);

		writerViewer = new TableViewer(comp, SWT.FULL_SELECTION
				| SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		writerViewer.getTable().setHeaderVisible(true);
		writerViewer.getTable().setLinesVisible(true);

		Color color = comp.getDisplay().getSystemColor(SWT.COLOR_LIST_SELECTION);
		final Color bg = new Color(comp.getDisplay(), new RGB( color.getRed() - 50, color.getGreen() - 50, color.getBlue() -50));
		
		writerViewer.getControl().addDisposeListener(new DisposeListener(){
		
			public void widgetDisposed(DisposeEvent e) {
				bg.dispose();
			}
		});
		
		TableViewerFocusCellManager focusCellManager = new TableViewerFocusCellManager(
				writerViewer, new FocusCellOwnerDrawHighlighter(writerViewer) {
					@Override
					protected Color getSelectedCellBackgroundColor(
							ViewerCell cell) {
						return bg;
					}
				});
		ColumnViewerEditorActivationStrategy actSupport = new ColumnViewerEditorActivationStrategy(
				writerViewer) {
			protected boolean isEditorActivationEvent(
					ColumnViewerEditorActivationEvent event) {
				boolean rv = false;
				
				if( event.eventType == ColumnViewerEditorActivationEvent.MOUSE_CLICK_SELECTION ) {
					rv = (((MouseEvent)event.sourceEvent).stateMask & SWT.MOD1) == SWT.MOD1;
				}
				
				return rv || event.eventType == ColumnViewerEditorActivationEvent.TRAVERSAL
						|| (event.eventType == ColumnViewerEditorActivationEvent.KEY_PRESSED && event.keyCode == SWT.CR)
						|| event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC;
			}
		};

		TableViewerEditor.create(writerViewer, focusCellManager, actSupport,
				ColumnViewerEditor.TABBING_HORIZONTAL
						//| ColumnViewerEditor.TABBING_MOVE_TO_ROW_NEIGHBOR
						| ColumnViewerEditor.TABBING_VERTICAL
						| ColumnViewerEditor.KEYBOARD_ACTIVATION);

		IFocusService focusService = (IFocusService) getSite().getService(
				IFocusService.class);
		focusService.addFocusTracker(writerViewer.getControl(), WRITER_TABLE);

		ObservableListContentProvider cp = new ObservableListContentProvider();
		writerViewer.setContentProvider(cp);

		CondiditionalTemplate[] tpl = new CondiditionalTemplate[3];
		tpl[0] = new CondiditionalTemplate("${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Writer
						&& ((Writer) element).getFirstName() != null;
			}

		};
		tpl[1] = new CondiditionalTemplate("${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element instanceof Writer
						&& ((Writer) element).getLastName() != null;
			}

		};
		tpl[2] = new CondiditionalTemplate("-") {

			@Override
			public boolean isTemplate(EObject element) {
				return true;
			}

		};

		EStructuralFeature[] features = new EStructuralFeature[2];
		features[0] = EXTLibraryPackage.Literals.PERSON__FIRST_NAME;
		features[1] = EXTLibraryPackage.Literals.PERSON__LAST_NAME;

		IObservableMap[] map = EMFEditObservables.observeMaps(domain, cp
				.getKnownElements(), features);

		TableViewerColumn c = new TableViewerColumn(writerViewer, SWT.NONE);
		c.getColumn().setText("Firstname");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1, 120));
		c
				.setLabelProvider(new ObservableColumnLabelProvider(
						new IObservableMap[] { map[0] }, Arrays.asList(tpl[0],
								tpl[2])));

		final TextCellEditor editor = new TextCellEditor(writerViewer
				.getTable());

		ObservableValueEditingSupport o = new ObservableValueEditingSupport(
				writerViewer, dbc) {

			@Override
			protected CellEditor getCellEditor(Object element) {
				return editor;
			}

			@Override
			protected IObservableValue doCreateElementObservable(
					Object element, ViewerCell cell) {
				return EMFEditObservables.observeValue(domain,
						(EObject) element,
						EXTLibraryPackage.Literals.PERSON__FIRST_NAME);
			}

			@Override
			protected IObservableValue doCreateCellEditorObservable(
					CellEditor cellEditor) {
				return SWTObservables.observeText(cellEditor.getControl());
			}
		};
		c.setEditingSupport(o);

		c = new TableViewerColumn(writerViewer, SWT.NONE);
		c.getColumn().setText("Lastname");
		layout.setColumnData(c.getColumn(), new ColumnWeightData(1, 120));
		c
				.setLabelProvider(new ObservableColumnLabelProvider(
						new IObservableMap[] { map[1] }, Arrays.asList(tpl[1],
								tpl[2])));
		o = new ObservableValueEditingSupport(writerViewer, dbc) {

			@Override
			protected CellEditor getCellEditor(Object element) {
				return editor;
			}

			@Override
			protected IObservableValue doCreateElementObservable(
					Object element, ViewerCell cell) {
				return EMFEditObservables.observeValue(domain,
						(EObject) element,
						EXTLibraryPackage.Literals.PERSON__LAST_NAME);
			}

			@Override
			protected IObservableValue doCreateCellEditorObservable(
					CellEditor cellEditor) {
				return SWTObservables.observeText(cellEditor.getControl());
			}
		};
		c.setEditingSupport(o);

		MenuManager mgr = new MenuManager();
		writerViewer.getControl().setMenu(
				mgr.createContextMenu(writerViewer.getControl()));
		getSite().registerContextMenu(
				"org.eclipse.emf.examples.library.databinding.writer", mgr,
				writerViewer);

		writerViewer.setInput(EMFEditObservables.observeDetailList(Realm
				.getDefault(), domain, master,
				EXTLibraryPackage.Literals.LIBRARY__WRITERS));
		item.setControl(comp);

	}

	@Override
	public void preExecute(String commandId, ExecutionEvent event) {

	}

	@Override
	public void postExecuteFailure(String commandId,
			ExecutionException exception) {
		if (commandId.equals(CreateNewWriterHandler.commandId)) {
			MessageDialog.openError(getItem().getParent().getShell(),
					"Creating Stock item failed", exception.getMessage());
		}
	}

	@Override
	public void postExecuteSuccess(String commandId, Object returnValue) {
		if (commandId.equals(CreateNewWriterHandler.commandId)) {
			getItem().getParent().setSelection(getItem());
			writerViewer.editElement(returnValue, 0);
		}
	}

	@Override
	protected void doDispose() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getContextIdPrefix() {
		return PREFIX;
	}
}
