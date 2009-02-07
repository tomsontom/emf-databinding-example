package org.eclipse.emf.examples.library.databinding.common.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.databinding.conversion.Converter;

public class DateToStringConverter extends Converter {
	private DateFormat format = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
	
	public DateToStringConverter() {
		super(Date.class, String.class);
	}

	public Object convert(Object fromObject) {
		if( fromObject != null ) {
			return format.format(fromObject);
		}
		return null;
	}

}
