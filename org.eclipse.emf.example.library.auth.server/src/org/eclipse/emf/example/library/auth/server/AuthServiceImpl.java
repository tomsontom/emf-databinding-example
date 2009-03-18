package org.eclipse.emf.example.library.auth.server;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.example.library.auth.common.IAuthService;

public class AuthServiceImpl implements IAuthService {
	private Properties users;
	private Properties groups;
	
	public AuthServiceImpl() throws InstantiationException {
		users = new Properties();
		String userfile = null;
		
		for( String s : Platform.getCommandLineArgs() ) {

			if( s.startsWith("-userfile") ) {
				userfile = s.split("=")[1];
			}			
		}
		
		if( userfile == null ) {
			throw new InstantiationException("No user db specified");
		}
		try {
			FileInputStream in = new FileInputStream(new File(userfile));
			users.load(in);
			in.close();			
		} catch (Exception e) {
			throw new InstantiationException(e.getMessage());
		}
	}

	public boolean login(String username, String password) {
		if( password == null ) {
			return false;
		}
		
		return password.equals(users.getProperty(username));
	}

	public String getRole(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
