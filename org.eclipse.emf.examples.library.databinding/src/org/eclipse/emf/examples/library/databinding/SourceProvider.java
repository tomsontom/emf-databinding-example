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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.AbstractSourceProvider;

public class SourceProvider extends AbstractSourceProvider {
	private ISelection selection;
	
	public void dispose() {
		selection = null;
	}

	public Map<String,Object> getCurrentState() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("selectedStockItemName", selection);
		return map;
	}

	public String[] getProvidedSourceNames() {
		return new String[] { "selectedStockItemName" };
	}
	
	public void setItemSelection(ISelection selection) {
		this.selection = selection;
		fireSourceChanged(org.eclipse.ui.ISources.WORKBENCH, ISources.SELECTED_STOCK_ITEM_NAME, selection);
	}
}
