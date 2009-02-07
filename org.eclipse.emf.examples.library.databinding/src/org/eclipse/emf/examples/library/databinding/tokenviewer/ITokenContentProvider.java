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

import org.eclipse.jface.viewers.IStructuredContentProvider;

public interface ITokenContentProvider extends IStructuredContentProvider {
	Object fromString(Object parent, String value);
	String toString(Object object);
	Object[] getChildren(Object object, String prefix);
}
