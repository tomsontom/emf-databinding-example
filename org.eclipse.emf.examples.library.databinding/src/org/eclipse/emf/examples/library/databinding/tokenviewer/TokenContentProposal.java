/*******************************************************************************
 * Copyright (c) 2009 Eugene Ostroukhov and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eugene Ostroukhov - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.examples.library.databinding.tokenviewer;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.viewers.IBaseLabelProvider;

final class TokenContentProposal implements IContentProposal {
	private final Object element;
	private final ITokenContentProvider provider;
	private String value;
	private final IBaseLabelProvider labelProvider;

	public TokenContentProposal(Object element, ITokenContentProvider provider, IBaseLabelProvider labelProvider) {
		super();
		this.labelProvider = labelProvider;
		this.element = element;
		this.provider = provider;
	}

	public String getContent() {
		return getString();
	}
	
	public int getCursorPosition() {
		return getString().length();
	}

	public String getDescription() {
		if (labelProvider instanceof IDescriptionProvider) {
			return ((IDescriptionProvider) labelProvider).getDescription(element);
		}
		return null;
	}

	public Object getElement() {
		return element;
	}

	public String getLabel() {
		return null;
	}

	private String getString() {
		if (value == null) {
			value = provider.toString(element);
		}
		return value;
	}
}
