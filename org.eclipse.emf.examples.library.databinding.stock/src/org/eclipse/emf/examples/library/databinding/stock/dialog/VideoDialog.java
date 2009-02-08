package org.eclipse.emf.examples.library.databinding.stock.dialog;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.properties.EMFEditProperties;
import org.eclipse.emf.databinding.edit.properties.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.example.library.service.ISavePoint;
import org.eclipse.emf.example.library.service.ISavePointEditingDomain;
import org.eclipse.emf.examples.extlibrary.EXTLibraryFactory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.VideoCassette;
import org.eclipse.emf.examples.library.databinding.common.ObservableColumnLabelProvider;
import org.eclipse.emf.examples.library.databinding.common.converter.DateToStringConverter;
import org.eclipse.emf.examples.library.databinding.stock.Activator;
import org.eclipse.emf.examples.library.databinding.tokenviewer.TokenViewer;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class VideoDialog extends TitleAreaDialog {
	class AddCastDialog extends Dialog {
		TokenViewer viewer;
		Person person;
		
		AddCastDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		protected Control createDialogArea(Composite parent) {
			getShell().setText("Add cast");
			Composite container = (Composite) super.createDialogArea(parent);
			Composite comp = new Composite(container,SWT.NONE);
			comp.setLayout(new GridLayout(3,false));
			comp.setLayoutData(new GridData(GridData.FILL_BOTH));
			
			Label l = new Label(comp,SWT.NONE);
			l.setText("Name");
			
			viewer = new TokenViewer(comp);
			viewer.getControl().setLayoutData(new GridData(150,SWT.DEFAULT));
			viewer.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					Person p = (Person) element;
					return p.getLastName() + ", " + p.getFirstName();
				}
			});
			viewer.setContentProvider(new ArrayContentProvider());
			viewer.setInput(EMFEditObservables
					.observeList(Realm.getDefault(), domain, library,
							EXTLibraryPackage.Literals.LIBRARY__CASTS));
			
			Button b = new Button(comp,SWT.PUSH);
			b.setImage(Activator.getDefault().getImageRegistry().get(Activator.IMG_VIDEO_CAST_NEW));
			b.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					Person p = EXTLibraryFactory.eINSTANCE.createPerson();
					String[] parts = viewer.getText().split(",");
					if( parts.length == 2 ) {
						p.setLastName(parts[0].trim());
						p.setFirstName(parts[1].trim());
						Command cmd = AddCommand.create(domain, library, EXTLibraryPackage.Literals.LIBRARY__CASTS, p);
						
						if( cmd.canExecute() ) {
							domain.getCommandStack().execute(cmd);
							person = p;
							okPressed();
						}
					} else {
						MessageDialog.openInformation(getShell(), "Not enough data", "The entry has to have lastname and firstname separated by ','.");
					}
					
				}
			});
			
			return container;
		}
		
		@Override
		protected void okPressed() {
			if( person == null ) {
				person = (Person) ((IStructuredSelection)viewer.getSelection()).getFirstElement();
			}
			
			if( person != null ) {
				super.okPressed();	
			}	
		}
	}
	
	private VideoCassette video;
	private Library library;
	private ISavePointEditingDomain domain;
	private ISavePoint savePoint;
	
	public VideoDialog(Shell parentShell, ISavePointEditingDomain domain,
			Library library, VideoCassette video) {
		super(parentShell);
		this.domain = domain;
		this.library = library;
		this.video = video;
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
		getShell().setText("Edit Videocassette");
		setTitle("Edit the Videocassette");
		setMessage("Edit the data of the videocassette with the form below and press ok when done or cancel to undo all modifications.");

		Composite container = (Composite) super.createDialogArea(parent);

		Composite comp = new Composite(container, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(3, false));

		DataBindingContext dbc = new DataBindingContext();

		/*
		 * Title
		 */
		Label l = new Label(comp, SWT.NONE);
		l.setText("Title");

		Text t = new Text(comp, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan=2;
		t.setLayoutData(gd);
		IValueProperty uProp = WidgetProperties.text(SWT.Modify);
		IEMFEditValueProperty mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__TITLE);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(video));

		/*
		 * Publication Date
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Publication Date");

		t = new Text(comp, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan=2;
		t.setLayoutData(gd);
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.ITEM__PUBLICATION_DATE);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(video), new UpdateValueStrategy(),
				new UpdateValueStrategy()
						.setConverter(new DateToStringConverter()));

		/*
		 * Copies
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Copies");

		t = new Text(comp, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan=2;
		t.setLayoutData(gd);
		mProp = EMFEditProperties.value(domain,
				EXTLibraryPackage.Literals.LENDABLE__COPIES);
		dbc.bindValue(SWTObservables.observeDelayedValue(400,
				(ISWTObservableValue) uProp.observe(t)), mProp
				.observe(video));

		/*
		 * Cast
		 */
		l = new Label(comp, SWT.NONE);
		l.setText("Cast");
		gd = new GridData();
		gd.verticalAlignment=SWT.BEGINNING;
		gd.verticalIndent=5;
		l.setLayoutData(gd);
		
		TableViewer viewer = new TableViewer(comp,SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL|SWT.FULL_SELECTION);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = viewer.getTable().getItemHeight() * 8;
		viewer.getControl().setLayoutData(gd); 
		ObservableListContentProvider cp = new ObservableListContentProvider();
		viewer.setContentProvider(cp);
		
		EStructuralFeature[] features = new EStructuralFeature[2];
		features[0] = EXTLibraryPackage.Literals.PERSON__LAST_NAME;
		features[1] = EXTLibraryPackage.Literals.PERSON__FIRST_NAME;
		
		IObservableMap[] map = EMFEditObservables.observeMaps(domain, cp.getKnownElements(), features); 
		viewer.setLabelProvider(new ObservableColumnLabelProvider(map,"${0}, ${1}"));
		viewer.setInput(EMFEditObservables.observeList(domain, video, EXTLibraryPackage.Literals.VIDEO_CASSETTE__CAST));
		
		Button b = new Button(comp,SWT.PUSH);
		b.setImage(Activator.getDefault().getImageRegistry().get(Activator.IMG_VIDEO_CAST_ADD));
		gd = new GridData();
		gd.verticalAlignment=SWT.BEGINNING;
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AddCastDialog dia = new AddCastDialog(getShell());
				if( dia.open() == IDialogConstants.OK_ID) {
					Command cmd = AddCommand.create(domain, video, EXTLibraryPackage.Literals.VIDEO_CASSETTE__CAST, dia.person);
					
					if( cmd.canExecute() ) {
						domain.getCommandStack().execute(cmd);
					}
				}
			}
		});
		
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
