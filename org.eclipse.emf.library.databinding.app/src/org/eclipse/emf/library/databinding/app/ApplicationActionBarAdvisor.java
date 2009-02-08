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
package org.eclipse.emf.library.databinding.app;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	private IWorkbenchAction saveAction;
	private IWorkbenchAction saveAllAction;
	private IWorkbenchAction undoAction;
	private IWorkbenchAction redoAction;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	saveAction = ActionFactory.SAVE.create(window);
    	register(saveAction);
    	
    	saveAllAction = ActionFactory.SAVE_ALL.create(window);
    	register(saveAllAction);
    	
    	undoAction = ActionFactory.UNDO.create(window);
    	register(undoAction);
    	
    	redoAction = ActionFactory.REDO.create(window);
    	register(redoAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMenu = new MenuManager("&File",IWorkbenchActionConstants.M_FILE);
    	fileMenu.add(saveAction);
    	fileMenu.add(saveAllAction);
    	menuBar.add(fileMenu);
    	
    	MenuManager editMenu = new MenuManager("&Edit",IWorkbenchActionConstants.M_EDIT);
    	editMenu.add(undoAction);
    	editMenu.add(redoAction);
    	menuBar.add(editMenu);
    }
    
}
