package org.eclipse.emf.example.library.service.teneo.postgres.test1;

import org.eclipse.emf.example.library.service.teneo.postgres.PostgresLibraryPersistenceService;

public class Test1PersistenceService extends PostgresLibraryPersistenceService {

	@Override
	protected String doGetDriver() {
		return "org.postgresql.Driver";
	}

	@Override
	protected String doGetPassword() {
		return "test";
	}

	@Override
	protected String doGetUrl() {
		return "jdbc:postgresql://localhost/library";
	}

	@Override
	protected String doGetUser() {
		return "library";
	}

	public String getIdentifier() {
		return Activator.PLUGIN_ID + "test1";
	}

	public String getLabel() {
		return "PostgreSQL - Multi-User";
	}

	public String getToolTip() {
		return "PostgreSQL ("+doGetUrl()+")";
	}

}
