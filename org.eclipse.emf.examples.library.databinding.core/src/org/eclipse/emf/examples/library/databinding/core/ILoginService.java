package org.eclipse.emf.examples.library.databinding.core;

import org.eclipse.core.runtime.IStatus;

public interface ILoginService {
	public interface ILoginListener {
		public void userLoginSuccessful(String user, String group, String pwd);
		public void userLoginFailed(String user, String pwd);
	}
	
	public String getUsername();
	
	public void addLoginListener(ILoginListener listener);
	
	public void removeLoginListener(ILoginListener listener);
	
	public IStatus login(String username, String password);
}
