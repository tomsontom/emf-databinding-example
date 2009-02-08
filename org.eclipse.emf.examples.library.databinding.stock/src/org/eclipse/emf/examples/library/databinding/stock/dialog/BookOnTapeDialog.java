/*******************************************************************************
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.examples.library.databinding.stock.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.example.library.service.ISavePoint;
import org.eclipse.emf.example.library.service.ISavePointEditingDomain;
import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.examples.library.databinding.common.converter.DateToStringConverter;
import org.eclipse.emf.examples.library.databinding.tokenviewer.TokenViewer;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
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

public class BookOnTapeDialog extends TitleAreaDialog {
	private BookOnTape bookOnTape;
	private Library library;
	private ISavePointEditingDomain domain;
	private ISavePoint savePoint;

	public BookOnTapeDialog(Shell parentShell, ISavePointEditingDomain domain,
			Library library, BookOnTape bookOnTape) {
		super(parentShell);
		this.bookOnTape = bookOnTape;
		this.library = library;
		this.domain = domain;
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500,super.getInitialSize().y);
	}
	
	@Override
	public int open() {
		savePoint = domain.addSavePoint(EcoreUtil.generateUUID());
		return super.open();
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText("Edit Book On Tape");
		setTitle("Edit the book on tape");
		setMessage("Edit the data of the book on tape with the form below and press ok when done or cancel to undo all modifications.");

		Composite container = (Composite) super.createDialogArea(parent);

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
				EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__TITLE);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(bookOnTape));

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
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(bookOnTape), new UpdateValueStrategy(),
				new UpdateValueStrategy()
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
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(bookOnTape));

		/*
		 * Length
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Length (minutes)");

		t = new Text(comp, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__MINUTES_LENGTH);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(bookOnTape));

		/*
		 * Author
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Author");
		{
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
			authorViewer.setInput(EMFEditObservables.observeList(Realm
					.getDefault(), domain, library,
					EXTLibraryPackage.Literals.LIBRARY__WRITERS));

			mProp = EMFEditProperties.value(domain,
					EXTLibraryPackage.Literals.BOOK_ON_TAPE__AUTHOR);
			IValueProperty selectionProp = ViewerProperties.singleSelection();
			dbc.bindValue(selectionProp.observe(authorViewer), mProp
					.observe(bookOnTape));
		}

		/*
		 * Reader
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Reader");
		{
			TokenViewer readerViewer = new TokenViewer(comp);
			readerViewer.getControl().setLayoutData(
					new GridData(GridData.FILL_HORIZONTAL));
			readerViewer.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					Writer w = (Writer) element;
					return w.getLastName() + ", " + w.getFirstName();
				}
			});

			readerViewer.setContentProvider(new ArrayContentProvider());
			readerViewer.setInput(EMFEditObservables
					.observeList(Realm.getDefault(), domain, library,
							EXTLibraryPackage.Literals.LIBRARY__READERS));

			mProp = EMFEditProperties.value(domain,
					EXTLibraryPackage.Literals.BOOK_ON_TAPE__READER);
			IValueProperty selectionProp = ViewerProperties.singleSelection();
			dbc.bindValue(selectionProp.observe(readerViewer), mProp
					.observe(bookOnTape));
		}

		return comp;
	}
	
	@Override
	protected void okPressed() {
		savePoint.dispose();
		super.okPressed();
	}
	
	@Override
	protected void cancelPressed() {
		if( savePoint.canRollback() ) {
			savePoint.rollback();
			super.cancelPressed();
		} else {
			MessageDialog.openInformation(getShell(), "Rollback failed", "An error occurred while rolling back to the save point!");
		}
	}
}
