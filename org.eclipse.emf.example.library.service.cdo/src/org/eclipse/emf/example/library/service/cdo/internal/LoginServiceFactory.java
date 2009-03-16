package org.eclipse.emf.example.library.service.cdo.internal;

import org.eclipse.emf.examples.library.databinding.core.ILoginService;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

public class LoginServiceFactory extends AbstractServiceFactory {
	private ILoginService loginService;
	
	@Override
	public Object create(Class serviceInterface, IServiceLocator parentLocator,
			IServiceLocator locator) {
		if( serviceInterface == ILoginService.class ) {
			if( loginService == null ) {
				loginService = new CDOLoginServiceImpl();
			}
			
			return loginService;
		}
		
		return null;
	}

}
