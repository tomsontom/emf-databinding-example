package org.eclipse.emf.examples.library.databinding.stock.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookCategory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.library.databinding.common.converter.DateToStringConverter;
import org.eclipse.emf.examples.library.databinding.tokenviewer.TokenViewer;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class BookDialog extends TitleAreaDialog {
	private Book book;
	private Library library;
	private EditingDomain domain;

	public BookDialog(Shell parentShell, EditingDomain domain, Library library,
			Book book) {
		super(parentShell);
		this.book = book;
		this.library = library;
		this.domain = domain;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500,super.getInitialSize().y);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);

		getShell().setText("Edit Book");
		setTitle("Edit the book");
		setMessage("Edit the data of the book with the form below and press ok when done or cancel to undo all modifications.");

		Composite comp = new Composite(container, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));

		DataBindingContext dbc = new DataBindingContext();

		/*
		 * Title
		 */
		Label l = new Label(comp, SWT.NONE);
		l.setText("Title");

		Text t = new Text(comp, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		IValueProperty uProp = WidgetProperties.text(SWT.Modify);
		IEMFEditValueProperty mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.BOOK__TITLE);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(book));

		/*
		 * Publication Date
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Publication Date");

		t = new Text(comp, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.ITEM__PUBLICATION_DATE);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(book),
				new UpdateValueStrategy(), new UpdateValueStrategy()
						.setConverter(new DateToStringConverter()));

		/*
		 * Copies
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Copies");

		t = new Text(comp, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.LENDABLE__COPIES);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(book));

		/*
		 * Pages
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Pages");

		t = new Text(comp, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.BOOK__PAGES);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(book));

		/*
		 * Category
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Category");

		ComboViewer categoryViewer = new ComboViewer(comp, SWT.BORDER);
		categoryViewer.setContentProvider(new ArrayContentProvider());
		categoryViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				switch ((BookCategory) element) {
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
		categoryViewer.getCombo().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));
		categoryViewer.setInput(new BookCategory[] {
				BookCategory.BIOGRAPHY_LITERAL, BookCategory.MYSTERY_LITERAL,
				BookCategory.SCIENCE_FICTION_LITERAL });
		IValueProperty selectionProp = ViewerProperties.singleSelection();
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.BOOK__CATEGORY);
		dbc.bindValue(selectionProp.observe(categoryViewer), mProp
				.observe(book));

		/*
		 * Author
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Author");

		TokenViewer authorViewer = new TokenViewer(comp);
		authorViewer.getControl().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));
		authorViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				Writer w = (Writer) element;
				return w.getLastName() + ", " + w.getFirstName();
			}
		});
		authorViewer.setContentProvider(new ArrayContentProvider());
		authorViewer.setInput(EMFEditObservables.observeList(
				Realm.getDefault(), domain, library,
				EXTLibraryPackage.Literals.LIBRARY__WRITERS));

		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.BOOK__AUTHOR);
		dbc.bindValue(selectionProp.observe(authorViewer), mProp.observe(book));

		return container;
	}
}
