package org.eclipse.emf.example.library.service.teneo.postgres;

import org.eclipse.emf.example.library.service.teneo.TeneoLibraryPersistenceService;

public abstract class PostgresLibraryPersistenceService extends TeneoLibraryPersistenceService { 

	@Override
	protected String doGetDialect() {
		return "org.hibernate.dialect.PostgreSQLDialect";
	}

}
