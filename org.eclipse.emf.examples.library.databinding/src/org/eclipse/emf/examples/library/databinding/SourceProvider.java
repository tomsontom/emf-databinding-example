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
package org.eclipse.emf.examples.library.databinding;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.examples.library.databinding.core.ILoginService;
import org.eclipse.emf.examples.library.databinding.core.ILoginService.ILoginListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.PlatformUI;

public class SourceProvider extends AbstractSourceProvider {
	private ISelection selection;
	private String group = "admin";
	private ILoginListener listener = new ILoginListener(){
	
		public void userLoginSuccessful(String user, String group, String pwd) {
			setGroup(group);
		}
	
		public void userLoginFailed(String user, String pwd) {
			
		}
	};
	
	public SourceProvider() {
		ILoginService s = (ILoginService) PlatformUI.getWorkbench().getService(ILoginService.class);
		if( s != null ) {
			group = s.getGroup() != null ? s.getGroup() : "admin";
			s.addLoginListener(listener);
		}
	}
	
	public void dispose() {
		selection = null;
		ILoginService s = (ILoginService) PlatformUI.getWorkbench().getService(ILoginService.class);
		if( s != null ) {
			s.removeLoginListener(listener);
		}
	}

	public Map<String,Object> getCurrentState() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedStockItemName", selection);
		map.put("userGroup", group);
		return map;
	}

	public String[] getProvidedSourceNames() {
		return new String[] { "selectedStockItemName", "userGroup" };
	}
	
	public void setGroup(String group) {
		this.group = group;
		fireSourceChanged(org.eclipse.ui.ISources.WORKBENCH, ISources.USER_GROUP, group);
	}
	
	public void setItemSelection(ISelection selection) {
		this.selection = selection;
		fireSourceChanged(org.eclipse.ui.ISources.WORKBENCH, ISources.SELECTED_STOCK_ITEM_NAME, selection);
	}
}
