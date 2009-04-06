package org.eclipse.emf.example.library.auth.client;

import org.eclipse.emf.example.library.auth.common.IAuthService;
import org.eclipse.emf.examples.library.databinding.core.ILoginService;
import org.eclipse.riena.core.injector.Inject;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

public class LoginServiceFactory extends AbstractServiceFactory {
	private ILoginService loginService;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object create(Class serviceInterface, IServiceLocator parentLocator,
			IServiceLocator locator) {
		if( serviceInterface == ILoginService.class ) {
			if( loginService == null ) {
				loginService = new AuthServiceHandler();
				Inject.service(IAuthService.class).into(loginService).andStart(Activator.getDefault().getBundleContext());
			}
			
			return loginService;
		}
		
		return null;
	}
}