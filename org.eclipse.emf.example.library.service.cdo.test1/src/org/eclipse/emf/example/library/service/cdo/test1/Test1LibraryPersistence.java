package org.eclipse.emf.example.library.service.cdo.test1;

import org.eclipse.emf.example.library.service.cdo.CDOLibraryPersistence;

public class Test1LibraryPersistence extends CDOLibraryPersistence {

	@Override
	protected String doGetHost() {
		return "localhost";
	}

	@Override
	protected String doGetPort() {
		return "2036";
	}

	@Override
	protected String doGetRepositoryId() {
		return "repo1";
	}

	@Override
	protected String doGetResourcePath() {
		return "/test1";
	}

	public String getIdentifier() {
		return Activator.PLUGIN_ID + "test1";
	}

	public String getLabel() {
		return "CDO - Multi";
	}

	public String getToolTip() {
		return "CDO (" + doGetHost() + ":" + doGetPort() + ")";
	}
}