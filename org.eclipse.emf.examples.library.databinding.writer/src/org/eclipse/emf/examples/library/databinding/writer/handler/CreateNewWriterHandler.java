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
package org.eclipse.emf.examples.library.databinding.writer.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;

public class CreateNewWriterHandler extends AbstractHandler {
	public static final String commandId = "org.eclipse.emf.examples.library.databinding.writer.new";
	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEvaluationContext ctx = (IEvaluationContext) event.getApplicationContext();
		IEditorPart part = (IEditorPart) ctx.getVariable(ISources.ACTIVE_EDITOR_NAME);
		Library parent = (Library) ((IStructuredSelection)ctx.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME)).getFirstElement();

		if( parent != null ) {
			ILibraryPersistenceService service = (ILibraryPersistenceService) part.getEditorInput().getAdapter(ILibraryPersistenceService.class);
			if( service != null ) {
				Writer w = EXTLibraryFactory.eINSTANCE.createWriter();
				
				Command cmd = AddCommand.create(service.getEditingDomain(), parent, EXTLibraryPackage.Literals.LIBRARY__WRITERS, w);
				
				if( cmd.canExecute() ) {
					service.getEditingDomain().getCommandStack().execute(cmd);
					
					for( Writer tmp: parent.getWriters() ) {
						System.err.println(tmp + " => " + tmp.hashCode());
					}
					
					return w;	
				} else {
					throw new ExecutionException("Could not execute add writer command.");
				}
			} else {
				throw new ExecutionException("No library service.");
			}
		} else {
			throw new ExecutionException("No library to add the writer to.");
		}
	}

}
