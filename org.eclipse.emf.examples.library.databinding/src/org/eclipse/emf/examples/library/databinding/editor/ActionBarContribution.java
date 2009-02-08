package org.eclipse.emf.examples.library.databinding.editor;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.examples.library.databinding.RedoAction;
import org.eclipse.emf.examples.library.databinding.UndoAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;

public class ActionBarContribution extends EditorActionBarContributor {
	protected UndoAction undoAction;
	protected RedoAction redoAction;
	protected IEditorPart activeEditor;
	
	public ActionBarContribution() {
		
	}

	@Override
	public void init(IActionBars actionBars) {
		super.init(actionBars);
		undoAction = new UndoAction();
//	    undoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
	    actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
	    
	    redoAction = new RedoAction();
//	    redoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
	    actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
	}
	
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);
		
	}
	
	public void setActiveEditor(IEditorPart part)  {
		super.setActiveEditor(part);

	    if (part != activeEditor)
	    {
	      if (activeEditor != null)
	      {
	        deactivate();
	      }

	      if (part instanceof IEditingDomainProvider)
	      {
	        activeEditor = part;
	        activate();

	      }
	    }
	}
	
	public void deactivate() {
		undoAction.setActiveWorkbenchPart(null);
	    redoAction.setActiveWorkbenchPart(null);
	}
	
	public void activate() {
		undoAction.setActiveWorkbenchPart(activeEditor);
	    redoAction.setActiveWorkbenchPart(activeEditor);
	}
}
