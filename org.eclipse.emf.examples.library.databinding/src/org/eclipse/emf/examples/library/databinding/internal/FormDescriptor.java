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
package org.eclipse.emf.examples.library.databinding.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.examples.library.databinding.AbstractForm;


public class FormDescriptor {
	private IConfigurationElement element;
	
	private static final String ATT_ID = "id";
	
	private static final String ATT_CLASS = "class";
	
	private static final String ATT_LABEL = "label";
	
	private String id;

	public FormDescriptor(IConfigurationElement element) {
		this.id = element.getAttribute(ATT_ID);
		this.element = element;
	}
	
	public AbstractForm createFormInstance() throws CoreException {
		return (AbstractForm) element.createExecutableExtension(ATT_CLASS);
	}
	
	public String getLabel() {
		return element.getAttribute(ATT_LABEL);
	}
	
	public String getId() {
		return id;
	}	
}