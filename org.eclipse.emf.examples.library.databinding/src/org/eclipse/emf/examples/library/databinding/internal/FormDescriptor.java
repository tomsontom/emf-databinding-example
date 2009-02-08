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
package org.eclipse.emf.examples.library.databinding.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.examples.library.databinding.AbstractForm;


public class FormDescriptor {
	private IConfigurationElement element;
	
	private static final String ATT_ID = "id";
	
	private static final String ATT_CLASS = "class";
	
	private static final String ATT_WEIGHT = "weight";
	
	private String id;
	
	private double weight;

	public FormDescriptor(IConfigurationElement element) {
		this.id = element.getAttribute(ATT_ID);
		try {
			this.weight = Double.parseDouble(element.getAttribute(ATT_WEIGHT));	
		} catch (NumberFormatException e) {
		}
		
		this.element = element;
	}
	
	public AbstractForm createFormInstance() throws CoreException {
		return (AbstractForm) element.createExecutableExtension(ATT_CLASS);
	}
	
	public String getId() {
		return id;
	}
	
	public double getWeight() {
		return weight;
	}
}