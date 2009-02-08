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
package org.eclipse.emf.examples.library.databinding.view;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IParameter;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.library.databinding.ILibraryServiceRegistry;
import org.eclipse.emf.examples.library.databinding.ILibraryServiceRegistry.Listener;
import org.eclipse.emf.examples.library.databinding.internal.Activator;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

public class LibraryExplorerViewPart extends ViewPart {
	private class LibraryContentProvider implements ITreeContentProvider {
		private LibraryCategory[] categories = new LibraryCategory[0];

		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof LibraryCategory) {
				return ((LibraryCategory) parentElement).getServices();
			}
			return new Object[0];
		}

		public Object getParent(Object element) {
			if (element instanceof ILibraryPersistenceService) {
				String cat = ((ILibraryPersistenceService) element)
						.getCategory();
				for (LibraryCategory c : categories) {
					if (c.equals(cat)) {
						return c;
					}
				}
			}
			return null;
		}

		public boolean hasChildren(Object element) {
			return element instanceof LibraryCategory;
		}

		public Object[] getElements(Object inputElement) {
			if (inputElement == null) {
				categories = new LibraryCategory[0];
			} else {
				ILibraryServiceRegistry reg = (ILibraryServiceRegistry) inputElement;
				String[] c = reg.getCategories();
				categories = new LibraryCategory[c.length];
				int i = 0;
				for (String tmp : c) {
					categories[i++] = new LibraryCategory(tmp, reg);
				}
			}
			return categories;
		}

		public void dispose() {

		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

	}

	private class LibraryCategory {
		private String categoryName;
		private ILibraryServiceRegistry reg;

		public LibraryCategory(String categoryName, ILibraryServiceRegistry reg) {
			this.categoryName = categoryName;
			this.reg = reg;
		}

		public ILibraryPersistenceService[] getServices() {
			return reg.getServicesInCategory(categoryName);
		}
	}

	private class LibraryLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			if (element instanceof LibraryCategory) {
				return ((LibraryCategory) element).categoryName;
			} else {
				return ((ILibraryPersistenceService) element).getLabel();
			}
		}
	}

	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent);
		final LibraryContentProvider cp = new LibraryContentProvider();
		viewer.setContentProvider(cp);
		viewer.setLabelProvider(new LibraryLabelProvider());

		ILibraryServiceRegistry reg = Activator.getDefault()
				.getLiberyRegistry();
		reg.addListener(new Listener() {

			public void serviceAdded(ILibraryPersistenceService service) {
				for (LibraryCategory c : cp.categories) {
					if (c.categoryName.equals(service.getCategory())) {
						final LibraryCategory tmp = c;
						viewer.getControl().getDisplay().asyncExec(
								new Runnable() {

									public void run() {
										viewer.refresh(tmp);
									}

								});
						return;
					}
				}

				viewer.refresh();
			}

			public void serviceRemoved(ILibraryPersistenceService service) {
				for (LibraryCategory c : cp.categories) {
					if (c.categoryName.equals(service.getCategory())) {
						final LibraryCategory tmp = c;
						if (!viewer.getControl().isDisposed()) {
							viewer.getControl().getDisplay().syncExec(
									new Runnable() {

										public void run() {
											if (!viewer.getControl()
													.isDisposed()) {
												viewer.refresh(tmp);
											}

										}

									});

						}
						return;
					}
				}

				viewer.refresh();
			}

		});
		viewer.setInput(reg);
		viewer.addOpenListener(new IOpenListener() {

			public void open(OpenEvent event) {
				IHandlerService handlerService = (IHandlerService) getSite().getService(IHandlerService.class);
				ICommandService commandService = (ICommandService) getSite().getService(ICommandService.class);
				Command openEditorCmd = commandService.getCommand("org.eclipse.emf.examples.library.databinding.openlibrary");
				try {
					IParameter parameter = openEditorCmd.getParameter("org.eclipse.emf.examples.library.databinding.persistenceid");
					for( Object o: ((IStructuredSelection)event.getSelection()).toList() ) {
						if( o instanceof ILibraryPersistenceService ) {
							ILibraryPersistenceService s = (ILibraryPersistenceService) o;
							Parameterization param = new Parameterization(parameter,s.getIdentifier());
							ParameterizedCommand cmd = new ParameterizedCommand(openEditorCmd,new Parameterization[] {param});
							handlerService.executeCommand(cmd, null);
						}
							
					}
				} catch (NotDefinedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotEnabledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotHandledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}

	@Override
	public void setFocus() {
		viewer.getTree().setFocus();
	}

}
