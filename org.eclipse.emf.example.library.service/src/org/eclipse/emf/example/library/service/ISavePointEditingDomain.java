/*******************************************************************************
 * Copyright (c) 2009 Tom Schindl and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.example.library.service;

import org.eclipse.emf.edit.domain.EditingDomain;

public interface ISavePointEditingDomain extends EditingDomain {
	public ISavePoint getSavePoint(String savepoint);
	public ISavePoint addSavePoint(String savepoint);
//	public void removeSavePoint(ISavePoint savepoint);
//	public boolean rollbackToSavePoint(String savepoint);
}
