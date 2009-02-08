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
package org.eclipse.emf.examples.library.databinding.common.observables;

import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.ui.forms.widgets.Form;

public class FormTextProperty extends SimpleValueProperty {

	@Override
	public INativePropertyListener adaptListener(
			ISimplePropertyListener listener) {
		return null;
	}

	@Override
	protected void doAddListener(Object source, INativePropertyListener listener) {
		
	}

	@Override
	protected Object doGetValue(Object source) {
		return ((Form)source).getText();
	}

	@Override
	protected void doRemoveListener(Object source,
			INativePropertyListener listener) {
		
	}

	@Override
	protected void doSetValue(Object source, Object value) {
		((Form)source).setText(value != null ? value.toString() : "");
	}

	public Object getValueType() {
		return String.class;
	}

}