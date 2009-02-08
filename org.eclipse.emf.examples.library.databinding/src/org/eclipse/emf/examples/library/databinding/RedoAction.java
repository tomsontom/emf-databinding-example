/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: RedoAction.java,v 1.4 2006/12/28 06:50:05 marcelop Exp $
 */
package org.eclipse.emf.examples.library.databinding;


import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;


/**
 * An redo action is implemented by using the {@link org.eclipse.emf.common.command.CommandStack}.
 */
public class RedoAction extends Action
{
  protected EditingDomain domain;

  public RedoAction(EditingDomain domain)
  {
    super("Redo");
    this.domain = domain;
    update();
  }

  public RedoAction()
  {
    super("Redo");
  }

  /**
   * This returns the action's domain.
   */
  public EditingDomain getEditingDomain()
  {
    return domain;
  }

  /**
   * This sets the action's domain.
   */
  public void setEditingDomain(EditingDomain domain)
  {
    this.domain = domain;
  }

  @Override
  public void run()
  {
    domain.getCommandStack().redo();
  }

  public void update()
  {
    setEnabled(domain.getCommandStack().canRedo());

    Command redoCommand = domain.getCommandStack().getRedoCommand();
    if (redoCommand != null && redoCommand.getLabel() != null)
    {
      setText("Redo " + redoCommand.getLabel());
    }
    else
    {
      setText("Redo");
    }

    if (redoCommand != null && redoCommand.getDescription() != null)
    {
      setDescription("Redo " + redoCommand.getDescription());
    }
    else 
    {
      setDescription("Redo");
    }
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
   */
  @Deprecated
  public void setActiveEditor(IEditorPart editorPart)
  {
    setActiveWorkbenchPart(editorPart);
  }

  /**
   * @since 2.1.0
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
    }
  }
}
