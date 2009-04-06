package org.eclipse.emf.example.library.auth.client;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.example.library.auth.common.IAuthService;
import org.eclipse.emf.examples.library.databinding.core.ILoginService;

public class AuthServiceHandler implements ILoginService {
	private IAuthService authService;
	private String group = "admin";
	private String username;
	private String password;
	
	public void bind(IAuthService authService) {
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
		return password;
	}

	public String getUsername() {
		return username;
	}

	public IStatus login(String username, String password) {
		// TODO Auto-generated method stub
		if( authService != null ) {
			if( authService.login(username, password) ) {
				group = authService.getRole(username);
			} else {
				return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Username and/or Password wrong!");
			}
		}
		
		this.username = username;
		this.password = password;
		
		return Status.OK_STATUS;
	}

	public void removeLoginListener(ILoginListener listener) {
		// TODO Auto-generated method stub
		
	}
}
