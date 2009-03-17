package org.eclipse.emf.example.library.auth.client;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.example.library.auth.common.IAuthService;
import org.eclipse.emf.examples.library.databinding.core.ILoginService;

public class AuthServiceHandler implements ILoginService {
	private IAuthService authService;
	private String group = "admin";
	
	public void bind(IAuthService authService) {
		System.err.println("An auth service is injected: " + authService);
		this.authService = authService;
	}
	
	public void unbind(IAuthService authService) {
		this.authService = null;
	}

	public void addLoginListener(ILoginListener listener) {
		// TODO Auto-generated method stub
		
	}

	public String getGroup() {
		return group;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public IStatus login(String username, String password) {
		// TODO Auto-generated method stub
		if( authService != null ) {
			group = authService.login(username, password);
		}
		
		return Status.OK_STATUS;
	}

	public void removeLoginListener(ILoginListener listener) {
		// TODO Auto-generated method stub
		
	}
}
