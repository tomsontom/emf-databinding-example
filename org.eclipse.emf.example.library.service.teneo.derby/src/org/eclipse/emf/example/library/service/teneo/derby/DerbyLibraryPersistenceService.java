package org.eclipse.emf.example.library.service.teneo.derby;

import org.eclipse.emf.example.library.service.teneo.TeneoLibraryPersistenceService;

public abstract class DerbyLibraryPersistenceService extends TeneoLibraryPersistenceService {

	@Override
	protected String doGetDialect() {
		return "org.hibernate.dialect.DerbyDialect";
	}

}