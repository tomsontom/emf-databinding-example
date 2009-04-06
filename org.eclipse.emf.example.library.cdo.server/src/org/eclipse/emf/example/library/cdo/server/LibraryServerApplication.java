/**
 * Copyright (c) 2004 - 2009 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Eike Stepper - initial API and implementation
 */
package org.eclipse.emf.example.library.cdo.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.cdo.common.revision.CDORevision;
import org.eclipse.emf.cdo.internal.server.RepositoryConfigurator;
import org.eclipse.emf.cdo.internal.server.bundle.OM;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.ISession;
import org.eclipse.emf.cdo.server.ITransaction;
import org.eclipse.emf.cdo.server.IStoreAccessor.CommitContext;
import org.eclipse.emf.cdo.spi.common.revision.InternalCDORevision;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.example.library.auth.common.IAuthService;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.net4j.TransportConfigurator;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.OSGiApplication;
import org.eclipse.net4j.util.om.monitor.OMMonitor;
import org.eclipse.riena.core.injector.Inject;
import org.osgi.framework.ServiceReference;

/**
 * @author Eike Stepper
 */
public class LibraryServerApplication extends OSGiApplication {
	public static final String ID = OM.BUNDLE_ID + ".app";

	private IRepository[] repositories;

	private IAcceptor[] acceptors;

	private IRepository.WriteAccessHandler writeHandler = new IRepository.WriteAccessHandler() {

		public void handleTransactionBeforeCommitting(ITransaction transaction,
				CommitContext commitContext, OMMonitor monitor)
				throws RuntimeException {
			String userId = transaction.getSession().getUserID();
			
			if (userId == null) {
				throw new SecurityException("You are not logged in!");
			}

			List<CDORevision> revisions = new ArrayList<CDORevision>();

			{
				CDORevision[] revs = commitContext.getNewObjects();

				if (revs != null) {
					revisions.addAll(Arrays.asList(revs));
				}
				
				// FIXME Checking removes?
			}

			if (revisions.size() > 0) {
				EPackage libraryPackage = commitContext.getPackageRegistry()
						.getEPackage(EXTLibraryPackage.eNS_URI);
				EClass libraryClass = (EClass) libraryPackage
						.getEClassifier("Library");
				String role = null;
				
				for (CDORevision rev : revisions) {

					if (libraryClass == rev.getEClass()) {
						if( role == null ) {
							role = getRole(userId);
						}
						if( ! role.equals("admin") ) {
							throw new IllegalAccessError("You are not allowed to write items");
						}
					}
				}
			}

		}
		
		private String getRole(String userId) {
			if( authService != null ) {
				return authService.getRole(userId);
			}
			return "admin";
		}
	};
	
	private IAuthService authService;

	public LibraryServerApplication() {
		super(ID);
	}
	
	public void bind(IAuthService authService) {
		this.authService = authService;
	}
	
	public void unbind(IAuthService authService) {
		this.authService = null;
	}

	@Override
	protected void doStart() throws Exception {
		super.doStart();
		OM.LOG.info("CDO Server starting");
		Inject.service(IAuthService.class).into(this).andStart(Activator.getDefault().getBundleContext());
		
		File configFile = OMPlatform.INSTANCE.getConfigFile("cdo-server.xml");
		if (configFile != null && configFile.exists()) {
			RepositoryConfigurator repositoryConfigurator = new RepositoryConfigurator(
					IPluginContainer.INSTANCE);
			repositories = repositoryConfigurator.configure(configFile);
			if (repositories == null || repositories.length == 0) {
				OM.LOG.warn("No repositories configured");
			}

			for (IRepository rep : repositories) {
				rep.addHandler(writeHandler);
			}

			TransportConfigurator net4jConfigurator = new TransportConfigurator(
					IPluginContainer.INSTANCE);
			acceptors = net4jConfigurator.configure(configFile);
			if (acceptors == null || acceptors.length == 0) {
				OM.LOG.warn("No acceptors configured");
			}
		} else {
			OM.LOG.warn("CDO server configuration not found: "
					+ configFile.getAbsolutePath());
		}

		OM.LOG.info("CDO Server started");
	}

	@Override
	protected void doStop() throws Exception {
		OM.LOG.info("CDO Server stopping");
		if (acceptors != null) {
			for (IAcceptor acceptor : acceptors) {
				acceptor.close();
			}
		}

		if (repositories != null) {
			for (IRepository repository : repositories) {
				LifecycleUtil.deactivate(repository);
			}
		}

		OM.LOG.info("CDO Server stopped");
		super.doStop();
	}
}