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
package org.eclipse.emf.examples.library.databinding.stock.handler;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;

public class StockItemValues implements IParameterValues {
	public Map<String,String> getParameterValues() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Book", "book");
		map.put("Book On Tape", "tapebook");
		map.put("Video", "video");
		return map;
	}

}
