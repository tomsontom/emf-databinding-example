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
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.library.databinding.common.converter.DateToStringConverter;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
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
	private EditingDomain domain;
	
	public BookOnTapeDialog(Shell parentShell, EditingDomain domain, Library library,
			BookOnTape bookOnTape) {
		super(parentShell);
		this.bookOnTape = bookOnTape;
		this.library = library;
		this.domain = domain;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
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
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(bookOnTape));
		
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
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(bookOnTape),
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
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(bookOnTape));
		
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
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(bookOnTape));
		
		return comp;
	}
}