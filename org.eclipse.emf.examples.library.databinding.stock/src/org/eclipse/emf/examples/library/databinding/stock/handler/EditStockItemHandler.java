package org.eclipse.emf.examples.library.databinding.stock.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.emf.example.library.service.ILibraryPersistenceService;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.Item;
import org.eclipse.emf.examples.library.databinding.stock.dialog.BookDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PlatformUI;

public class EditStockItemHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEvaluationContext ctx = (IEvaluationContext) event.getApplicationContext();
		IEditorPart part = (IEditorPart) ctx.getVariable(ISources.ACTIVE_EDITOR_NAME);
		ILibraryPersistenceService service = (ILibraryPersistenceService) part.getEditorInput().getAdapter(ILibraryPersistenceService.class);
		
		Item item = (Item) ((IStructuredSelection) ctx.getVariable(org.eclipse.emf.examples.library.databinding.ISources.SELECTED_STOCK_ITEM_NAME)).getFirstElement();
		
		if( item instanceof Book ) {
			BookDialog dialog = new BookDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),service.getEditingDomain(),(Book)item);
			dialog.open();
		} else if( item instanceof BookOnTape ) {
			
		} else {
			
		}
		
		return null;
	}

}
