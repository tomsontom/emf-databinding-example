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

public class UnrecognizedTokenException extends Exception {
	private static final long serialVersionUID = -2403526272633365765L;

	private final int len;
	private final Object parent;
	private final int start;

	public UnrecognizedTokenException(Object parent, int start, int len) {
		this.parent = parent;
		this.start = start;
		this.len = len;
	}
	
	public int getLength() {
		return len;
	}
	
	public Object getParent() {
		return parent;
	}
	
	public int getStart() {
		return start;
	}
}
