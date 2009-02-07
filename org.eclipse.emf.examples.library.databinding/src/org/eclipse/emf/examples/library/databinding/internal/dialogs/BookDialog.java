package org.eclipse.emf.examples.library.databinding.internal.dialogs;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookCategory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.osgi.framework.internal.core.Tokenizer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import zhou.eclipse.tokenviewer.testing.tokenviewer.TokenViewer;

public class BookDialog extends TitleAreaDialog {
	private Book book;
	private EditingDomain domain;
	
	public BookDialog(Shell parentShell, EditingDomain domain, Book book) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Composite comp = new Composite(container,SWT.NONE); 
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2,false));
		
		DataBindingContext dbc = new DataBindingContext();

		
		/*
		 *  Title
		 */
		Label l = new Label(comp,SWT.NONE);
		l.setText("Title");
		
		Text t = new Text(comp,SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL)); 
		IValueProperty uProp = WidgetProperties.text(SWT.Modify);
		IEMFEditValueProperty mProp = EMFEditProperties.value(domain, EXTLibraryPackage.Literals.BOOK__TITLE);
		dbc.bindValue(uProp.observe(t), mProp.observe(book));

		/*
		 * Publication Date
		 */
		l = new Label(comp,SWT.NONE);
		l.setText("Publication Date");
		
		t = new Text(comp,SWT.BORDER);
		mProp = EMFEditProperties.value(domain, EXTLibraryPackage.Literals.ITEM__PUBLICATION_DATE);
		dbc.bindValue(uProp.observe(t), mProp.observe(book));
		
		
		/*
		 * Pages
		 */
		l = new Label(comp,SWT.NONE);
		l.setText("Pages");
		
		t = new Text(comp,SWT.BORDER);
		mProp = EMFEditProperties.value(domain, EXTLibraryPackage.Literals.BOOK__PAGES);
		dbc.bindValue(uProp.observe(t), mProp.observe(book));
		
		/*
		 * Category 
		 */
		l = new Label(comp,SWT.NONE);
		l.setText("Category");
		
		ComboViewer categoryViewer = new ComboViewer(comp,SWT.BORDER);
		categoryViewer.setContentProvider(new ArrayContentProvider());
		categoryViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				switch ((BookCategory)element) {
				case BIOGRAPHY_LITERAL:
					return "Biographie";
				case MYSTERY_LITERAL:
					return "Mystery";
				case SCIENCE_FICTION_LITERAL:
					return "Science Fiction";
				default:
					return "";
				}
			}
		});
		categoryViewer.setInput(new BookCategory[] { BookCategory.BIOGRAPHY_LITERAL, BookCategory.MYSTERY_LITERAL, BookCategory.SCIENCE_FICTION_LITERAL });
		
		/*
		 * Author 
		 */
		l = new Label(comp,SWT.NONE);
		l.setText("Author");
		
		TokenViewer authorViewer = new TokenViewer(comp);
		authorViewer.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		authorViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				Writer w = (Writer) element;
				return w.getLastName().toUpperCase() + ", " + w.getFirstName();
			}
		});
		
		return container;
	}
}
