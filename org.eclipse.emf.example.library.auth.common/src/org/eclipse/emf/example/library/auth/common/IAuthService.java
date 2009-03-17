package org.eclipse.emf.example.library.auth.common;

public interface IAuthService {
	public static final String ID = IAuthService.class.getName();
	public static final String WS_ID = "/LibraryAuthServiceWS"; //$NON-NLS-1$
	
	public String login(String username, String password);
}
