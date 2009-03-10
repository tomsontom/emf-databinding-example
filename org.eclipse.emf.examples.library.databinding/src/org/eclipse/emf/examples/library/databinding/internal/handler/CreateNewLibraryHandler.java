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
package org.eclipse.emf.examples.library.databinding.internal.handler;

import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
//import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;

public class CreateNewLibraryHandler extends AbstractHandler {
	public static final String commandId = "org.eclipse.emf.examples.library.databinding.library.new";
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEvaluationContext ctx = (IEvaluationContext) event.getApplicationContext();
		IEditorPart part = (IEditorPart) ctx.getVariable(ISources.ACTIVE_EDITOR_NAME);
		Library parent = (Library) ((IStructuredSelection)ctx.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME)).getFirstElement();
		
		ILibraryPersistenceService service = (ILibraryPersistenceService) part.getEditorInput().getAdapter(ILibraryPersistenceService.class);
		
		if( service != null ) {
			Library l = EXTLibraryFactory.eINSTANCE.createLibrary();
			Command cmd;
			
			if( parent == null ) {
				cmd = new AddCommand(service.getEditingDomain(),service.getResource().getContents(),l);
			} else {
				cmd = AddCommand.create(service.getEditingDomain(), parent, EXTLibraryPackage.Literals.LIBRARY__BRANCHES, l);
			}
			
			if( cmd.canExecute() ) {
//				List<?> list = parent.getBranches();
//				parent.eAdapters().add(new Adapter() {
//
//					public Notifier getTarget() {
//						// TODO Auto-generated method stub
//						return null;
//					}
//
//					public boolean isAdapterForType(Object type) {
//						// TODO Auto-generated method stub
//						return false;
//					}
//
//					public void notifyChanged(Notification notification) {
//						System.err.println("Notification: " + notification.getFeature());
//						
//						if( EXTLibraryPackage.Literals.LIBRARY__BRANCHES.equals(notification.getFeature()) ) {
//							System.err.println("New Value" + notification.getNewValue());
//							System.err.println(notification.getEventType() + "==" + Notification.ADD + " => " + (notification.getEventType() == Notification.ADD));
//						}
//						
//						System.err.println("========= DONE ============");
//					}
//
//					public void setTarget(Notifier newTarget) {
//						// TODO Auto-generated method stub
//						
//					}
//					
//				});
				service.getEditingDomain().getCommandStack().execute(cmd);
				
//				System.err.println(l.getParentBranch() == parent);
//				System.err.println("Parent: " + l.getParentBranch());
//				System.err.println("Branches: " + l.getParentBranch().getBranches());
//				System.err.println("Branches: " + l.getParentBranch().getBranches().getClass());
//				System.err.println("Content-Class: " + l.eContents().getClass());
//				System.err.println("Content: " + l.eContents());
//				System.err.println("List: " + list);
				return l;
			} else {
				throw new ExecutionException("Could not execute add library command.");
			}
		} else {
			throw new ExecutionException("No library service available");
		}
	}

}
