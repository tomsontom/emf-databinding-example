/*******************************************************************************
 * Copyright (c) 2008 BestSolution.at Systemhaus GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     tom.schindl@bestsolution - initial API and implementation
 ******************************************************************************/
package org.eclipse.emf.examples.library.databinding.common;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.examples.library.databinding.common.observables.FormTextProperty;

public class FormProperties {
	
	public static IValueProperty text() {
		return new FormTextProperty();
	}
}
