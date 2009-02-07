package org.eclipse.emf.examples.library.databinding.internal.handler;

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
