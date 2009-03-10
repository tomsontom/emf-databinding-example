/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * PersistenceMappingBuilder.java,v 1.10 2007/02/08 23:12:35 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.StoreAnnotationsException;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.parser.EAnnotationParserImporter;
import org.eclipse.emf.teneo.annotations.xml.XmlPersistenceMapper;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Receives a list of ecore files and generates a mapping model using different
 * strategies. The mapping model is returned.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.9 $
 */
public class PersistenceMappingBuilder implements ExtensionPoint {

	/** The logger */
	protected static final Log log = LogFactory
			.getLog(PersistenceMappingBuilder.class);

	/** The instance to use */
	public static final PersistenceMappingBuilder INSTANCE = new PersistenceMappingBuilder();

	/**
	 * Receives a list of ecore files and returns a Mapping
	 */
	public PAnnotatedModel buildMapping(String[] ecoreFiles,
			PersistenceOptions po, ExtensionManager extensionManager) {
		return buildMapping(StoreUtil.readEPackages(ecoreFiles), po,
				extensionManager);
	}

	/**
	 * Builds a persistence mapping for one or more epackages
	 * 
	 * @Deprecated use the method with the List<EPackage> parameter
	 */
	public PAnnotatedModel buildMapping(EPackage[] epackages,
			PersistenceOptions po, ExtensionManager extensionManager) {
		return buildMapping(Arrays.asList(epackages), po, extensionManager);
	}

	/**
	 * Builds a persistence mapping for one or more epackages
	 */
	public PAnnotatedModel buildMapping(List<EPackage> epacks,
			PersistenceOptions po, ExtensionManager extensionManager) {
		// read the subepackages
		final List<EPackage> epackages = new ArrayList<EPackage>();
		for (EPackage epack : epacks) {
			resolveSubPackages(epack, epackages);
		}

		// DCB: Introduce indirection so that extensions to annotation
		// processing mechanism
		// can provide their own model builder.
		BasicPamodelBuilder pamodelBuilder = extensionManager
				.getExtension(BasicPamodelBuilder.class);
		log.debug("Creating pamodel for the following epackages");
		for (EPackage element : epackages) {
			log.debug(element.getName());
			pamodelBuilder.addRecurse(element);
		}

		log.debug("Create base pannotated model");
		PAnnotatedModel pam = pamodelBuilder.getPAnnotatedModel();

		log
				.debug("Deprecated eannotations with http://annotations.elver.org or http://ejb.elver.org are ignored.");
		// if (po.isIgnoreEAnnotations()) {
		// log.debug("Ignoring eannotations");
		// } else {
		// log.debug("Import eannotations");
		// // DCB: Introduce indirection so that extensions to annotation
		// processing mechanism
		// // can provide their own model builder.
		// EannotationPamodelBuilder epb = getAnnotationModelBuilder();
		// epb.setPAnnotatedModel(pam);
		// epb.processCurrentPAnnotatedModel();
		// }

		if (po.isIgnoreEAnnotations()) {
			log.debug("Ignoring annotations");
		} else {
			log.debug("Parse annotations");
			extensionManager.getExtension(EAnnotationParserImporter.class)
					.process(pam);
		}

		if (po.getPersistenceXmlPath() != null) {
			try {
				final PersistenceFileProvider fileProvider = extensionManager
						.getExtension(PersistenceFileProvider.class);
				final InputStream in = fileProvider.getFileContent(null, po
						.getPersistenceXmlPath());
				if (in == null) {
					throw new RuntimeException(
							"Could not find persistence XML resource in classpath: \""
									+ po.getPersistenceXmlPath() + "\".");
				}
				final XmlPersistenceMapper xmlPersistenceMapper = extensionManager
						.getExtension(XmlPersistenceMapper.class);
				xmlPersistenceMapper.setXmlMapping(in);
				xmlPersistenceMapper.applyPersistenceMapping(pam);
				in.close();
				final InputStream[] iss = getAdditionalXMLMappings();
				for (InputStream element : iss) {
					xmlPersistenceMapper.setXmlMapping(element);
					xmlPersistenceMapper.applyPersistenceMapping(pam);
					element.close();
				}
			} catch (IOException e) {
				throw new StoreAnnotationsException(
						"Exception while loading xml persistence mappings", e);
			}
		}

		// now the annotations on the edatatype should be copied to the
		// annotations on the
		// eattribute, overwrite may not occur!
		processEDataTypeAnnotations(pam);

		log.debug("Add default annotations");
		// DCB: Introduce indirection so that extensions to annotation
		// processing mechanism
		// can provide their own default annotation.
		pam.setInitialized(true);
		extensionManager.getExtension(AnnotationGenerator.class).map(pam, po);

		log.debug("Returning created pamodel");
		return pam;
	}

	private void resolveSubPackages(EPackage epack, List<EPackage> epacks) {
		if (!epacks.contains(epack)) {
			epacks.add(epack);
		}

		for (EPackage subEPackage : epack.getESubpackages()) {
			resolveSubPackages(subEPackage, epacks);
		}
	}

	/**
	 * For each pannotated eattribute find the pannotated edatatype and copy the
	 * values of the estructuralfeature if not yet set in the eattribute
	 */
	protected void processEDataTypeAnnotations(PAnnotatedModel pam) {
		log
				.debug("Copying annotations on edatatypes over eattribute annotations!");
		for (PAnnotatedEPackage pep : pam.getPaEPackages()) {
			for (PAnnotatedEClass pec : pep.getPaEClasses()) {
				for (PAnnotatedEStructuralFeature pef : pec
						.getPaEStructuralFeatures()) {
					if (pef instanceof PAnnotatedEAttribute) {
						final PAnnotatedEAttribute pea = (PAnnotatedEAttribute) pef;
						final EDataType et = pea.getModelEAttribute()
								.getEAttributeType();
						final PAnnotatedEDataType ped = pam.getPAnnotated(et);
						if (ped == null) {
							continue; // not an explicit modeled edatatype
						}
						for (EStructuralFeature esf : ped.eClass()
								.getEAllStructuralFeatures()) {
							final EStructuralFeature asf = pea.eClass()
									.getEStructuralFeature(esf.getName());
							if (asf != null && !pea.eIsSet(asf)
									&& ped.eIsSet(esf)) {
								log.debug("Copying value for feature "
										+ esf.getName() + " from edatatype "
										+ et.getName() + " to "
										+ pea.getModelEAttribute().getName());

								final Object obj = ped.eGet(esf);
								if (obj instanceof Collection) {
									pea.eSet(asf, EcoreUtil
											.copyAll((Collection<?>) obj));
								} else if (obj instanceof EObject) {
									pea
											.eSet(asf, EcoreUtil
													.copy((EObject) obj));
								} else {
									throw new StoreAnnotationsException(
											"Class "
													+ obj.getClass().getName()
													+ " not supported should be eobject or collection");
								}
							}
						}
					}
				}
			}
		}
	}

	/** Additional inputstreams for xml mappings */
	protected InputStream[] getAdditionalXMLMappings() {
		return new InputStream[0];
	}
}