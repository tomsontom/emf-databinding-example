package org.eclipse.emf.example.library.service.teneo.derby.test1;

import org.eclipse.emf.example.library.service.teneo.derby.DerbyLibraryPersistenceService;

public class Test1PersistenceService extends DerbyLibraryPersistenceService {

	@Override
	protected String doGetDriver() {
		return "org.apache.derby.jdbc.EmbeddedDriver";
	}

	@Override
	protected String doGetPassword() {
		return "test";
	}

	@Override
	protected String doGetUrl() {
		String url = "jdbc:derby:" + System.getProperty("user.home") + "/test1;create=true";
		System.err.println(url);
		return url;
	}

	@Override
	protected String doGetUser() {
		return "library";
	}

	public String getIdentifier() {
		return Activator.PLUGIN_ID + "test1";
	}

	public String getLabel() {
		return "Derby-Test1";
	}

	public String getToolTip() {
		return "Derby ("+doGetUrl()+")";
	}

}
