package org.eclipse.emf.example.library.auth.common;

public interface IAuthService {
	public static final String ID = IAuthService.class.getName();
	public static final String WS_ID = "/LibraryAuthServiceWS"; //$NON-NLS-1$
	
	public boolean login(String username, String password);
	public String getRole(String username);
}
