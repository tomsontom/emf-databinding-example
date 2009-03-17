package org.eclipse.emf.example.library.service.cdo;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CDOUtil;
import org.eclipse.emf.cdo.view.CDOAdapterPolicy;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.example.library.service.BaseLibraryPersistenceService;
import org.eclipse.emf.example.library.service.cdo.internal.Activator;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.library.databinding.core.ILoginService;
import org.eclipse.net4j.connector.ConnectorCredentialsInjector;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;
import org.eclipse.ui.PlatformUI;

public abstract class CDOLibraryPersistence extends BaseLibraryPersistenceService {
	private CDOResource resource;
	private CDOTransaction transaction;
	
	@Override
	protected Resource doGetResource() {
		if( resource == null ) {
			init();
		}
		
		return resource;
	}

	private void init() {
//		ILoginService loginService = (ILoginService) PlatformUI.getWorkbench().getService(ILoginService.class);
//		
//		PasswordCredentialsProvider pv = new PasswordCredentialsProvider(loginService.getUsername(),loginService.getPassword());
//		ConnectorCredentialsInjector ccj;
//		ccj = new ConnectorCredentialsInjector(null, pv);
//		IPluginContainer.INSTANCE.addPostProcessor(ccj);
		
		IConnector connector = TCPUtil.getConnector(IPluginContainer.INSTANCE, doGetHost() + ":" + doGetPort() );
		CDOSessionConfiguration configuration = CDOUtil.createSessionConfiguration();
	    configuration.setConnector(connector);
	    configuration.setRepositoryName(doGetRepositoryId());

	    CDOSession session = configuration.openSession();
	    session.getPackageRegistry().putEPackage(EXTLibraryPackage.eINSTANCE);

	    // Open transaction
	    transaction = session.openTransaction();
	    transaction.options().addChangeSubscriptionPolicy(CDOAdapterPolicy.ALL);

	    // Get or create resource
	    resource = transaction.getOrCreateResource(doGetResourcePath());
	}
	
	protected abstract String doGetResourcePath();
	protected abstract String doGetRepositoryId();
	protected abstract String doGetHost();
	protected abstract String doGetPort();
	
	@Override
	protected IStatus doSave() {
		try {
			transaction.commit();
			return Status.OK_STATUS;
		} catch( Exception e ) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(),e);
		}
	}

	public String getCategory() {
		return "CDO";
	}
}
