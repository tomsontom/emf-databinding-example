/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Martin Taal
 * </copyright>
 *
 * $Id: EPackageConstructor.java,v 1.3 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * Reads epackages from different formats and makes them available to other beans.
 * 
 * @author <a href="mtaal@elver.org">Martin Taal</a>
 */
public class EPackageConstructor {

	// The logger
	private static final Log log = LogFactory.getLog(EPackageConstructor.class);

	// The ecore context in which this app runs
// private EcoreContext ecoreContext;

	// The arraylist of epackages, is build in the getEPackages call
	private List<EPackage> ePackages = null;

	// The classnames of EcoreModelClasses
	private List<String> ecoreModelClasses = new ArrayList<String>();

	// The array of model class names
	private List<String> ecoreModelFiles = new ArrayList<String>();

	private boolean initialized = false;

	/**
	 * The list of files with ecore or xsd models.
	 */
	public void setModelFiles(List<String> ecoreModelFiles) {
		this.ecoreModelFiles = ecoreModelFiles;
	}

	/**
	 * Set the list of EcoreModelPackages which are to be read.
	 */
	public void setModelClasses(List<String> ecoreModelClasses) {
		this.ecoreModelClasses = ecoreModelClasses;
	}

	/**
	 * This method will read the models from the modelFiles and the EcoreModelPackages. The
	 * EPackages are initialized and registered in the EPackage registry.
	 * 
	 * @return the EPackages which are read from the modelFiles or are defined in
	 *         EcoreModelPackages.
	 */
	public List<EPackage> getEPackages() {
		if (ePackages == null) {
			initialize();
		}
		return ePackages;
	}

	/** Initialize it all */
	private void initialize() {
		if (initialized) {
			return;
		}
		// build the list of EPackages
		log.debug("Reading EPackages");
		final ArrayList<EPackage> ePacks = new ArrayList<EPackage>();
		ePacks.addAll(buildFromModelClasses());
		ePacks.addAll(buildFromModelFiles());

		for (EPackage epackage : ePacks) {
			log.info("Registered EPackage: " + epackage.getNsURI());
		}

		// also add subpackages
		for (EPackage ePackage : new ArrayList<EPackage>(ePacks)) {
			addSubPackages(ePacks, ePackage);
		}

		ePackages = ePacks;
		initialized = true;
	}

	private void addSubPackages(List<EPackage> list, EPackage ePackage) {
		for (EPackage subPackage : ePackage.getESubpackages()) {
			if (!list.contains(subPackage)) {
				list.add(subPackage);
				addSubPackages(list, subPackage);
			}
		}
	}

	/**
	 * Build a list of EPackages from the passed EcoreModelClasses.
	 */
	private List<EPackage> buildFromModelClasses() {
		final ArrayList<EPackage> result = new ArrayList<EPackage>();
		for (String ecoreModelPackageClassName : ecoreModelClasses) {
			try {
				final Class<?> cls = this.getClass().getClassLoader().loadClass(ecoreModelPackageClassName);
				final Method m = cls.getMethod("init");

				// purposely passing null because it must be static
				final EPackage emp = (EPackage) m.invoke(null);

				// initialise the emp, will also read the epackage
				result.add(emp);
			} catch (Exception e) {
				throw new IllegalStateException(
					"Excption while trying to retrieve EcoreModelPackage instance from class: " +
							ecoreModelPackageClassName, e);
			}
		}
		return result;
	}

	/**
	 * Builds a list of epackages from the modelfiles, pre-normalized for duplicates and not
	 * registered.
	 */
	private List<EPackage> buildFromModelFiles() {
		// separate the files in xsd and ecore files and treat each of them separately
		final ArrayList<String> ecoreFiles = new ArrayList<String>();
		final ArrayList<String> xsdFiles = new ArrayList<String>();
		for (String modelFile : ecoreModelFiles) {
			if (modelFile.endsWith(".xsd")) {
				xsdFiles.add(modelFile.trim());
			} else {
				if (!modelFile.trim().endsWith(".ecore")) {
					log.warn("Filename " + modelFile + " passed as modelFile but it does not end on either " +
							"xsd or ecore, processing it anyway.");
				}
				ecoreFiles.add(modelFile.trim());
			}
		}

		// now get the ecores and xsd's parse them separately
		final ArrayList<EPackage> result = new ArrayList<EPackage>();
		result.addAll(readFromEcore(ecoreFiles));
// result.addAll(readFromXmlSchema(xsdFiles));

		return result;
	}

// /**
// * Builds and registers EPackages from an XML Schema.
// *
// * @param file
// * The XML Schema file.
// */
// private List<EPackage> readFromXmlSchema(List<String> xsdFiles) {
// final ArrayList<EPackage> result = new ArrayList<EPackage>();
// for (String xsdFile : xsdFiles) {
// log.debug("Building ECore model from XML Schema \"" + xsdFile + "\".");
// try {
// // final String path = ecoreContext.getQualifiedPath(xsdFile);
// final java.net.URI netURI = this.getClass().getResource(xsdFile).toURI();
// final URI uri = URI.createURI(netURI.toString());
//
// // Note: we use an inline SerializableXSDEcoreBuilder to avoid a dependency on
// // XSDEcoreBuilder during
// // classloading.
// for (Object obj : new XSDEcoreBuilder().generate(uri)) {
// final EPackage ePackage = (EPackage) obj;
// result.add(ePackage);
// }
// } catch (Exception e) {
// throw new StateException("Could not build ECore model from XML Schema, from file " + xsdFile, e);
// }
// }
// return result;
// }

	/**
	 * Reads the epackages present in the passed ecore files. Note this method does not register the
	 * epackages. It does not check for duplicates either.
	 */
	private List<EPackage> readFromEcore(List<String> ecoreFiles) {
		final ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new EcoreResourceFactoryImpl());
		final ArrayList<EPackage> epackages = new ArrayList<EPackage>();
		for (String ecoreFile : ecoreFiles) {
			log.debug("Reading ecore file: " + ecoreFile);
// final String path = ecoreContext.getQualifiedPath(ecoreFile);
// log.debug("Using qualified path: " + path);
			try {
				final java.net.URI netURI = this.getClass().getResource(ecoreFile).toURI();
				final Resource res = resourceSet.getResource(URI.createURI(netURI.toString()), true);
				for (Object obj : res.getContents()) {
					if (obj instanceof EPackage) {
						epackages.add((EPackage) obj);
					}
				}
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return epackages;
	}
}
