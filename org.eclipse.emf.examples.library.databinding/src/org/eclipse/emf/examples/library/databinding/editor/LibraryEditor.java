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
package org.eclipse.emf.examples.library.databinding.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService.Listener;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.library.databinding.AbstractForm;
import org.eclipse.emf.examples.library.databinding.common.FormProperties;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider.CondiditionalTemplate;
import org.eclipse.emf.examples.library.databinding.internal.Activator;
import org.eclipse.emf.examples.library.databinding.internal.FormDescriptor;
import org.eclipse.emf.examples.library.databinding.internal.FormExtensionHandler.IModificationListener;
import org.eclipse.emf.examples.library.databinding.internal.handler.CreateNewLibraryHandler;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListTreeContentProvider;
import org.eclipse.jface.databinding.viewers.TreeStructureAdvisor;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.EditorPart;

public class LibraryEditor extends EditorPart implements IEditingDomainProvider {
	private Listener listener = new Listener() {

		public void dirtyStateChanged(boolean newState) {
			firePropertyChange(ISaveablePart2.PROP_DIRTY);
		}

	};

	private class ListFactory implements IObservableFactory {

		public IObservable createObservable(Object target) {
			if (target instanceof IObservable) {
				return (IObservable) target;
			} else {
				return EMFObservables.observeList(Realm.getDefault(), (EObject) target,
						EXTLibraryPackage.Literals.LIBRARY__BRANCHES);
			}

		}

	}

	private class StructureAdvisor extends TreeStructureAdvisor {

		@Override
		public Object getParent(Object element) {
			return ((Library) element).getParentBranch();
		}

		@Override
		public Boolean hasChildren(Object element) {
			if (((Library) element).getBranches().size() > 0) {
				return true;
			}
			// Vital because when returning false the addition of the first item
			// trigger a viewer refresh
			return null;
		}
	}

	private class NullConverter extends Converter {

		public NullConverter() {
			super(String.class, String.class);
		}

		public Object convert(Object fromObject) {
			return fromObject == null
					|| fromObject.toString().trim().length() == 0 ? "*NONAME*"
					: fromObject;
		}

	}

	private ILibraryPersistenceService p;
	private StructuredViewer viewer;
	private int[] weights = new int[] { 30, 70 };
	private java.util.List<AbstractForm> subforms = new ArrayList<AbstractForm>();
	private TabFolder subfolder;

	@Override
	public void doSave(IProgressMonitor monitor) {
		p.save();
	}

	@Override
	public void doSaveAs() {
		p.save();
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
		p = (ILibraryPersistenceService) getEditorInput().getAdapter(
				ILibraryPersistenceService.class);
		p.addListener(listener);

	}

	@Override
	public boolean isDirty() {
		return p.isDirty();
	}

	@Override
	public void dispose() {
		if( p.isDirty() ) {
			CommandStack stack = p.getEditingDomain().getCommandStack();
			while( stack.canUndo() ) {
				stack.undo();
			}
		}
		p.removeListener(listener);
		super.dispose();
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		SashForm form = new SashForm(parent, SWT.HORIZONTAL);
		createViewer(form);
		createDetailArea(form);
		initListener();
		form.setWeights(weights);
	}

	private void initListener() {
		ICommandService cmdService = (ICommandService) getSite().getService(
				ICommandService.class);
		cmdService.addExecutionListener(new IExecutionListener() {

			public void notHandled(String commandId,
					NotHandledException exception) {

			}

			public void postExecuteFailure(String commandId,
					ExecutionException exception) {
				System.err.println("Fail");
				// FIXME Only handle if the editor is the active one
				Shell shell = viewer.getControl().getShell();
				if (commandId.equals(CreateNewLibraryHandler.commandId)) {
					MessageDialog.openError(shell, "Creating Library failed",
							exception.getMessage());
				}

				for (AbstractForm form : subforms) {
					form.postExecuteFailure(commandId, exception);
				}

			}

			public void postExecuteSuccess(String commandId, Object returnValue) {
				// FIXME Only handle if the editor is the active one
				System.err.println("Successfull");
				if (commandId.equals(CreateNewLibraryHandler.commandId)) {
					Library l = (Library) returnValue;
					
					if (l.getParentBranch() != null) {
						((TreeViewer) viewer).setExpandedState(l
								.getParentBranch(), true);
					}
					viewer.setSelection(new StructuredSelection(l));
				}

				for (AbstractForm form : subforms) {
					form.postExecuteSuccess(commandId, returnValue);
				}

			}

			public void preExecute(String commandId, ExecutionEvent event) {

			}

		});
	}

	private void createDetailArea(SashForm sashform) {
		FormToolkit toolkit = new FormToolkit(sashform.getDisplay());

		final DataBindingContext dbc = new DataBindingContext();
		IValueProperty viewerProp = ViewerProperties.singleSelection();
		IValueProperty textProp = WidgetProperties.text(SWT.Modify);
		IValueProperty formTextProp = FormProperties.text();

		Composite comp = new Composite(sashform, SWT.NONE);
		comp.setLayout(new FillLayout());

		Form form = toolkit.createForm(comp);
		toolkit.decorateFormHeading(form);
		form.setText("");

		form.getBody().setLayout(new GridLayout(2, false));
		toolkit.createLabel(form.getBody(), "Name");

		Text t = toolkit.createText(form.getBody(), "");
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final IObservableValue viewerSelection = viewerProp.observe(viewer);

		IEMFEditValueProperty prop = EMFEditProperties.value(p
				.getEditingDomain(), EXTLibraryPackage.Literals.LIBRARY__NAME);

		IObservableValue uObs = prop.observeDetail(viewerSelection);

		dbc.bindValue(SWTObservables.observeDelayedValue(400, (ISWTObservableValue) textProp.observe(t)), uObs);
		dbc.bindValue(formTextProp.observe(form), uObs,
				new UpdateValueStrategy(), new UpdateValueStrategy()
						.setConverter(new NullConverter()));

		GridData gd = new GridData(GridData.BEGINNING, GridData.BEGINNING,
				false, false);
		gd.verticalIndent = 10;
		toolkit.createLabel(form.getBody(), "Address").setLayoutData(gd);
		t = toolkit.createText(form.getBody(), "", SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = t.getLineHeight() * 7;
		t.setLayoutData(gd);

		prop = EMFEditProperties.value(p.getEditingDomain(),
				EXTLibraryPackage.Literals.ADDRESSABLE__ADDRESS);
		dbc.bindValue(SWTObservables.observeDelayedValue(400, (ISWTObservableValue) textProp.observe(t)), prop.observeDetail(viewerSelection));

		subfolder = new TabFolder(form.getBody(), SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		subfolder.setLayoutData(gd);

		int i = 0;
		for (FormDescriptor desc : Activator.getDefault().getFormDescriptors()) {
			try {
				createForm(i++, desc, dbc, viewerSelection);
			} catch (CoreException e) {
				ErrorDialog.openError(viewer.getControl().getShell(),
						"Error loading forms", e.getMessage(), e.getStatus());
				Activator.getDefault().getLog().log(e.getStatus());
			}
		}

		if (subfolder.getItemCount() > 0) {
			subfolder.setSelection(0);
		} else {
			subfolder.setVisible(false);
		}

		Activator.getDefault().getFormHandler().addModificationListener(
				new IModificationListener() {

					public void formAdd(final int index, final FormDescriptor descriptor) {

						if (!viewer.getControl().isDisposed()) {
							viewer.getControl().getDisplay().syncExec(
									new Runnable() {

										public void run() {
											try {
												createForm(index, descriptor, dbc,
														viewerSelection);
											} catch (CoreException e) {
												ErrorDialog.openError(viewer
														.getControl()
														.getShell(),
														"Error loading forms",
														e.getMessage(), e
																.getStatus());
												Activator.getDefault().getLog()
														.log(e.getStatus());
											}
										}

									});
						}

					}

					public void formRemoved(FormDescriptor descriptor) {
						String id = descriptor.getId();
						Iterator<AbstractForm> it = subforms.iterator();
						while( it.hasNext() ) {
							final AbstractForm form = it.next();
							if (form.getId().equals(id)) {
								if (!viewer.getControl().isDisposed()) {
									viewer.getControl().getDisplay().syncExec(
											new Runnable() {

												public void run() {
													form.dispose();
												}

											});
									it.remove();
								}

							}
						}
					}

				});
	}

	private void createForm(int index, FormDescriptor desc, DataBindingContext dbc,
			IObservableValue viewerSelection) throws CoreException {
		AbstractForm subform = desc.createFormInstance();
		subforms.add(index, subform);
		
		subform.setId(desc.getId());
		subform.createForm(getSite(), subfolder, index, p.getEditingDomain(), dbc,
				viewerSelection);
	}

	private void createViewer(SashForm form) {
		viewer = new TreeViewer(form);
		viewer.setUseHashlookup(true);
		ObservableListTreeContentProvider cp = new ObservableListTreeContentProvider(
				new ListFactory(), new StructureAdvisor());
		viewer.setContentProvider(cp);
		CondiditionalTemplate defaultTemplate = new CondiditionalTemplate(
				"${0}") {

			@Override
			public boolean isTemplate(EObject element) {
				return element.eGet(EXTLibraryPackage.Literals.LIBRARY__NAME) != null;
			}
		};

		CondiditionalTemplate nullTemplate = new CondiditionalTemplate(
				"*NO NAME*") {

			@Override
			public boolean isTemplate(EObject element) {
				return element.eGet(EXTLibraryPackage.Literals.LIBRARY__NAME) == null;
			}
		};

		IObservableMap map = EMFEditObservables.observeMap(
				p.getEditingDomain(), cp.getKnownElements(),
				EXTLibraryPackage.Literals.LIBRARY__NAME);
		viewer.setLabelProvider(new ObservableColumnLabelProvider(
				new IObservableMap[] { map }, Arrays.asList(defaultTemplate,
						nullTemplate)));
		viewer.setInput(p.getLibraries());

		MenuManager mgr = new MenuManager();
		mgr.add(new GroupMarker("additions"));
		getSite().registerContextMenu(mgr, viewer);
		getSite().setSelectionProvider(viewer);
		viewer.getControl().setMenu(mgr.createContextMenu(viewer.getControl()));
	}

	@Override
	public void setFocus() {
		if (viewer.getSelection().isEmpty()) {
			Tree t = (Tree) viewer.getControl();
			if (t.getItemCount() > 0) {
				viewer.setSelection(new StructuredSelection(t.getItem(0)
						.getData()));
			}
		}
		viewer.getControl().setFocus();
	}

	public EditingDomain getEditingDomain() {
		return p != null ? p.getEditingDomain() : null;
	}
}