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
 * $Id: MappingUtil.java,v 1.7 2008/12/16 20:40:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.Properties;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.BasicPamodelBuilder;
import org.eclipse.emf.teneo.annotations.mapper.EClassAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.EDataTypeAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.EFeatureAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.ManyToOneReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.OneToManyAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.OneToManyReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.OneToOneReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceMappingBuilder;
import org.eclipse.emf.teneo.annotations.mapper.SingleAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.parser.EAnnotationParserImporter;
import org.eclipse.emf.teneo.annotations.xml.XmlPersistenceMapper;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerFactory;
import org.eclipse.emf.teneo.extension.ExtensionUtil;
import org.eclipse.emf.teneo.hibernate.annotations.HbAnnotationModelBuilder;
import org.eclipse.emf.teneo.hibernate.annotations.HbEAnnotationParserImporter;
import org.eclipse.emf.teneo.hibernate.annotations.HbEClassAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbEDataTypeAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbEFeatureAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbManyToOneReferenceAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbOneToManyAttributeAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbOneToManyReferenceAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbOneToOneReferenceAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbSingleAttributeAnnotator;
import org.eclipse.emf.teneo.hibernate.annotations.HbXmlPersistenceMapper;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;

/**
 * Contains some utility methods.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.7 $
 */
public class MappingUtil {

	/**
	 * Separate utility method, generates a hibernate mapping for a set of
	 * epackages and options. The hibernate.hbm.xml is returned as a string. The
	 * mapping is not registered or used in any other way by Elver.
	 */
	public static String generateMapping(EPackage[] epackages, Properties props) {
		final ExtensionManager extensionManager = ExtensionManagerFactory
				.getInstance().create();
		return generateMapping(epackages, props, extensionManager);
	}

	public static String generateMapping(EPackage[] epackages,
			Properties props, ExtensionManager extensionManager) {
		registerHbExtensions(extensionManager);

		// DCB: Use Hibernate-specific annotation processing mechanism. This
		// allows use of
		// Hibernate-specific annotations.
		final PersistenceOptions po = extensionManager.getExtension(
				PersistenceOptions.class, new Object[] { props });
		final PAnnotatedModel paModel = extensionManager.getExtension(
				PersistenceMappingBuilder.class).buildMapping(epackages, po,
				extensionManager);
		final HibernateMappingGenerator hmg = extensionManager
				.getExtension(HibernateMappingGenerator.class);
		hmg.setPersistenceOptions(po);
		return hmg.generateToString(paModel);
	}

	/**
	 * Determine the collection element set, bag or list. Only used in case
	 * Teneo operates for non-emf code
	 */
	public static String getCollectionElement(HbAnnotatedETypeElement hbFeature) {
		final boolean hasOrderBy = hbFeature instanceof PAnnotatedEReference
				&& ((PAnnotatedEReference) hbFeature).getOrderBy() != null;

		if (!hbFeature.getOneToMany().isList() || hasOrderBy) {
			return "set";
		} else if (hbFeature.getOneToMany().isList()
				&& !hbFeature.getOneToMany().isIndexed()) {
			return "bag";
		} else {
			return "list";
		}
	}

	/** Registers default hb extensions */
	public static void registerHbExtensions(ExtensionManager extensionManager) {
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				BasicPamodelBuilder.class, HbAnnotationModelBuilder.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				EAnnotationParserImporter.class,
				HbEAnnotationParserImporter.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				XmlPersistenceMapper.class, HbXmlPersistenceMapper.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				SingleAttributeAnnotator.class,
				HbSingleAttributeAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				EClassAnnotator.class, HbEClassAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				EFeatureAnnotator.class, HbEFeatureAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				OneToManyAttributeAnnotator.class,
				HbOneToManyAttributeAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				EDataTypeAnnotator.class, HbEDataTypeAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				OneToManyReferenceAnnotator.class,
				HbOneToManyReferenceAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				OneToOneReferenceAnnotator.class,
				HbOneToOneReferenceAnnotator.class));
		extensionManager.registerExtension(ExtensionUtil.createExtension(
				ManyToOneReferenceAnnotator.class,
				HbManyToOneReferenceAnnotator.class));
	}
}