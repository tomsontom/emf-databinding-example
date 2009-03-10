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
 *   Martin Taal - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ExtensionUtil.java,v 1.11 2009/02/24 12:05:05 mtaal Exp $
 */

package org.eclipse.emf.teneo.extension;

import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.mapper.AnnotationGenerator;
import org.eclipse.emf.teneo.annotations.mapper.BasicPamodelBuilder;
import org.eclipse.emf.teneo.annotations.mapper.BidirectionalManyToManyAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.EClassAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.EDataTypeAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.EFeatureAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.ManyToOneReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.OneToManyAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.OneToManyReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.OneToOneReferenceAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceFileProvider;
import org.eclipse.emf.teneo.annotations.mapper.PersistenceMappingBuilder;
import org.eclipse.emf.teneo.annotations.mapper.SingleAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.UnidirectionalManyToManyAnnotator;
import org.eclipse.emf.teneo.annotations.parser.EAnnotationParserImporter;
import org.eclipse.emf.teneo.annotations.xml.XmlElementToEStructuralFeatureMapper;
import org.eclipse.emf.teneo.annotations.xml.XmlPersistenceContentHandler;
import org.eclipse.emf.teneo.annotations.xml.XmlPersistenceMapper;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.SQLNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.impl.EntityResolvingNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.impl.TeneoSQLNameStrategy;

/**
 * Contains simple utility methods.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.11 $
 */

public class ExtensionUtil {

	/** Creates a default extension */
	public static Extension createExtension(Class<?> extensionPoint,
			Class<?> clz) {
		return createExtension(extensionPoint, clz, true);
	}

	public static Extension createExtension(Class<?> extensionPoint,
			Class<?> clz, boolean defaultExtension) {
		final Extension extension = new Extension();
		extension.setPoint(extensionPoint.getName());
		extension.setClassName(clz.getName());
		extension.setDefaultExtension(defaultExtension);
		return extension;
	}

	public static Extension createExtension(String pointClassName,
			boolean singleton) {
		final Extension extension = new Extension();
		extension.setPoint(pointClassName);
		extension.setClassName(pointClassName);
		extension.setDefaultExtension(true);
		extension.setSingleton(singleton);
		return extension;
	}

	public static Extension createExtension(String pointClassName,
			String className, boolean singleton) {
		final Extension extension = new Extension();
		extension.setPoint(pointClassName);
		extension.setClassName(className);
		extension.setDefaultExtension(true);
		extension.setSingleton(singleton);
		return extension;
	}

	/** Register a number of default Extensions */
	public static void registerDefaultExtensions(ExtensionManager em) {

		// the ones coming from this plugin
		em.registerExtension(createExtension(BasicPamodelBuilder.class,
				BasicPamodelBuilder.class));
		em.registerExtension(createExtension(AnnotationGenerator.class,
				AnnotationGenerator.class));
		em.registerExtension(createExtension(EAnnotationParserImporter.class,
				EAnnotationParserImporter.class));
		em.registerExtension(createExtension(PersistenceMappingBuilder.class,
				PersistenceMappingBuilder.class));
		em.registerExtension(createExtension(XmlPersistenceMapper.class,
				XmlPersistenceMapper.class));

		em.registerExtension(createExtension(PersistenceFileProvider.class,
				PersistenceFileProvider.class));

		// from now on always use the classloader
		// em.registerExtension(createExtension(ClassLoaderStrategy.class,
		// ContextClassLoaderStrategy.class));
		em.registerExtension(createExtension(EntityNameStrategy.class,
				EntityResolvingNameStrategy.class));
		em.registerExtension(createExtension(SQLNameStrategy.class,
				TeneoSQLNameStrategy.class));
		em.registerExtension(createExtension(
				XmlPersistenceContentHandler.class,
				XmlPersistenceContentHandler.class));
		em.registerExtension(createExtension(
				XmlElementToEStructuralFeatureMapper.class,
				XmlElementToEStructuralFeatureMapper.class));
		em.registerExtension(createExtension(PersistenceOptions.class,
				PersistenceOptions.class));

		// annotator related
		em.registerExtension(createExtension(EClassAnnotator.class,
				EClassAnnotator.class));
		em.registerExtension(createExtension(EFeatureAnnotator.class,
				EFeatureAnnotator.class));
		em.registerExtension(createExtension(OneToManyAttributeAnnotator.class,
				OneToManyAttributeAnnotator.class));
		em.registerExtension(createExtension(SingleAttributeAnnotator.class,
				SingleAttributeAnnotator.class));
		em.registerExtension(createExtension(
				BidirectionalManyToManyAnnotator.class,
				BidirectionalManyToManyAnnotator.class));
		em.registerExtension(createExtension(
				UnidirectionalManyToManyAnnotator.class,
				UnidirectionalManyToManyAnnotator.class));
		em.registerExtension(createExtension(EDataTypeAnnotator.class,
				EDataTypeAnnotator.class));
		em.registerExtension(createExtension(OneToManyReferenceAnnotator.class,
				OneToManyReferenceAnnotator.class));
		em.registerExtension(createExtension(OneToOneReferenceAnnotator.class,
				OneToOneReferenceAnnotator.class));
		em.registerExtension(createExtension(ManyToOneReferenceAnnotator.class,
				ManyToOneReferenceAnnotator.class));

		// from the hibernate plugin
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.HbContext", true));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerAccessor",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerFeatureIDAccessor",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerFeatureIDPropertyHandler",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerPropertyHandler",
						false));

		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.property.EListPropertyHandler",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.property.EReferencePropertyHandler",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.property.FeatureMapEntryFeatureURIPropertyHandler",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.property.FeatureMapEntryPropertyHandler",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.property.FeatureMapPropertyHandler",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.property.VersionPropertyHandler",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.EMFInterceptor", false));

		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.elist.HibernatePersistableEList",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.elist.HibernatePersistableEMap",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.elist.HibernatePersistableFeatureMap",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.elist.HbExtraLazyPersistableEList",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.elist.MapHibernatePersistableEMap",
						false));

		// hibernate mapper
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.HibernateMappingGenerator",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.MappingContext",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.mapper.BasicMapper", false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.EmbeddedMapper",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.mapper.EntityMapper", false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.mapper.FeatureMapper", false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.mapper.IdMapper", false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.mapper.ManyAttributeMapper",
				false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.hibernate.mapper.ManyToManyMapper",
				false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.ManyToOneMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.MappingContext",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.OneToManyMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.OneToOneMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapper.ManyExternalReferenceMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.hibernate.mapping.EMFInitializeCollectionEventListener",
						false));

		// jpox mapping
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.association.EmbeddedMapper",
				false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.association.ManyToManyMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.association.ManyToOneMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.association.OneToManyMapper",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.association.OneToOneMapper",
				false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.property.BasicMapper",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.property.ColumnMapper",
				false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.property.EClassFeatureMapper",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.property.IdMapper", false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.property.InheritanceMapper",
				false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.property.JoinColumnMapper",
				false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.property.ManyBasicMapper",
				false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.property.TableMapper",
						false));
		em
				.registerExtension(createExtension(
						"org.eclipse.emf.teneo.jpox.mapper.JPOXMappingGenerator",
						false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.MappingContext", false));
		em.registerExtension(createExtension(
				"org.eclipse.emf.teneo.jpox.mapper.NamingHandler", false));

	}
}