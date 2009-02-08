package org.eclipse.emf.examples.library.databinding.stock.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.example.library.service.ISavePoint;
import org.eclipse.emf.example.library.service.ISavePointEditingDomain;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Periodical;
import org.eclipse.emf.examples.library.databinding.common.converter.DateToStringConverter;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PeriodicalDialog extends TitleAreaDialog {
	private Periodical periodical;
	private ISavePointEditingDomain domain;
	private ISavePoint savePoint;
	
	public PeriodicalDialog(Shell parentShell, ISavePointEditingDomain domain,
			Periodical periodical) {
		super(parentShell);
		this.periodical = periodical;
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
		Composite container = (Composite) super.createDialogArea(parent);

		getShell().setText("Edit Periodical");
		setTitle("Edit the periodical");
		setMessage("Edit the data of the periodical with the form below and press ok when done or cancel to undo all modifications.");

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
				EXTLibraryPackage.Literals.PERIODICAL__TITLE);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(periodical));

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
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(periodical),
				new UpdateValueStrategy(), new UpdateValueStrategy()
						.setConverter(new DateToStringConverter()));
		
		/*
		 * Issues per year
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Issues / year");
		
		t = new Text(comp, SWT.BORDER);
		t.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.PERIODICAL__ISSUES_PER_YEAR);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp.observe(periodical));
		
		return container;
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