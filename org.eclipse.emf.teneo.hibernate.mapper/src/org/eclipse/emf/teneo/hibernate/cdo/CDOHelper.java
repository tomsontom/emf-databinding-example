/***************************************************************************
 * Copyright (c) 2004 - 2008 Springsite B.V. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Martin Taal - initial API and implementation
 **************************************************************************/
package org.eclipse.emf.teneo.hibernate.cdo;

import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerFactory;
import org.eclipse.emf.teneo.extension.ExtensionUtil;
import org.eclipse.emf.teneo.hibernate.mapper.MappingContext;
import org.eclipse.emf.teneo.hibernate.mapper.MappingUtil;

/**
 * Offers convenience methods for generating a cdo enabled hibernate mapping.
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 */
public class CDOHelper {

	public static final String GENERATE_FOR_CDO = "generate_for_cdo";

	private static CDOHelper instance = new CDOHelper();

	/**
	 * @return the instance
	 */
	public static CDOHelper getInstance() {
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(CDOHelper instance) {
		CDOHelper.instance = instance;
	}

	public void registerCDOExtensions(ExtensionManager extensionManager) {
		MappingUtil.registerHbExtensions(extensionManager);
		extensionManager.registerExtension(ExtensionUtil.createExtension(MappingContext.class, CDOMappingContext.class,
			false));
	}

	/**
	 * Separate utility method, generates a hibernate mapping for a set of epackages and options.
	 * The hibernate.hbm.xml is returned as a string. The mapping is not registered or used in any
	 * other way by Elver.
	 */
	public String generateMapping(EPackage[] epackages, Properties props) {
		return generateMapping(epackages, props, ExtensionManagerFactory.getInstance().create());
	}

	/**
	 * Separate utility method, generates a hibernate mapping for a set of epackages and options.
	 * The hibernate.hbm.xml is returned as a string. The mapping is not registered or used in any
	 * other way by Elver.
	 */
	public String generateMapping(EPackage[] epackages, Properties props, ExtensionManager extensionManager) {
		props.put(PersistenceOptions.ALSO_MAP_AS_CLASS, "false");
		CDOHelper.getInstance().registerCDOExtensions(extensionManager);
		return MappingUtil.generateMapping(epackages, props, extensionManager);
	}
}
