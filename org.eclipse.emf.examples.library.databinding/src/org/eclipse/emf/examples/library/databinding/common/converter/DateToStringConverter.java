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
