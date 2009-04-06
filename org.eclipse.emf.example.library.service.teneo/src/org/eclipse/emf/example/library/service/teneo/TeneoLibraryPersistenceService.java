package org.eclipse.emf.example.library.service.teneo;

import java.io.IOException;
import java.util.Collections;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.example.library.service.BaseLibraryPersistenceService;
import org.eclipse.emf.example.library.service.teneo.internal.Activator;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.hibernate.HbDataStore;
import org.eclipse.emf.teneo.hibernate.HbHelper;
import org.eclipse.emf.teneo.hibernate.resource.HibernateResource;
import org.hibernate.cfg.Environment;

public abstract class TeneoLibraryPersistenceService extends BaseLibraryPersistenceService {
	private Resource resource;
	
	public String getCategory() {
		return "Teneo";
	}
	
	protected Resource doGetResource() {
		if( resource == null ) {
			initHibernate();
			initResource();
		}
		return resource;
	}
	
	protected IStatus doSave() {
		try {
			resource.save(null);
			return Status.OK_STATUS;
		} catch (IOException e) {
			return new Status(IStatus.ERROR,Activator.PLUGIN_ID,"Saveing failed",e);
		}
	}

	private void initResource() {
		String uriStr = "hibernate://?" + HibernateResource.DS_NAME_PARAM + "="
				+ "library&query1=FROM Library WHERE parentBranch = null";
		
		final URI uri = URI.createURI(uriStr);
		try {
			resource = getResourceSet().createResource(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resource != null) {
			// load the resource domain
			try {
				resource.load(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initHibernate() {
		// Set the database information, Environment is
		// org.hibernate.cfg.Environment
		final Properties props = new Properties();

		props.setProperty(Environment.DRIVER, doGetDriver());
		props.setProperty(Environment.USER, doGetUser());
		props.setProperty(Environment.PASS, doGetPassword());
		props.setProperty(Environment.URL, doGetUrl());
		props.setProperty(Environment.DIALECT, doGetDialect());
		props.setProperty(Environment.SHOW_SQL, "true");
		props.setProperty(PersistenceOptions.INHERITANCE_MAPPING, "JOINED");

//		HibernateResource
		
		HbDataStore hbds = HbHelper.INSTANCE.createRegisterDataStore("library");

		// sets its epackages stored in this datastore
		hbds.setEPackages(new EPackage[] { EXTLibraryPackage.eINSTANCE });

		// set the relational database to use and other properties
		hbds.setProperties(props);

		// initialize, also creates the database tables
		hbds.initialize();
	}

	protected abstract String doGetDriver();
	protected abstract String doGetUser();
	protected abstract String doGetPassword();
	protected abstract String doGetUrl();
	protected abstract String doGetDialect();
}
