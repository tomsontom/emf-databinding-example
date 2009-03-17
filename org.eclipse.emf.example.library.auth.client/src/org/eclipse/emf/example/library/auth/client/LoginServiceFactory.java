package org.eclipse.emf.example.library.auth.client;

import org.eclipse.emf.example.library.auth.common.IAuthService;
import org.eclipse.emf.examples.library.databinding.core.ILoginService;
import org.eclipse.riena.core.injector.Inject;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;
import org.osgi.framework.ServiceReference;

public class LoginServiceFactory extends AbstractServiceFactory {
	private ILoginService loginService;
	
	@Override
	public Object create(Class serviceInterface, IServiceLocator parentLocator,
			IServiceLocator locator) {
		if( serviceInterface == ILoginService.class ) {
			System.err.println("Getting Service");
			if( loginService == null ) {
				loginService = new AuthServiceHandler();
				System.err.println("Injecting service: " + Activator.getDefault().getBundleContext());
				ServiceReference ref = Activator.getDefault().getBundleContext().getServiceReference(IAuthService.class.getName());
				System.err.println(ref);
				Inject.service(IAuthService.class).into(loginService).andStart(Activator.getDefault().getBundleContext());
				System.err.println("Done");
			}
			
			return loginService;
		}
		
		return null;
	}

}