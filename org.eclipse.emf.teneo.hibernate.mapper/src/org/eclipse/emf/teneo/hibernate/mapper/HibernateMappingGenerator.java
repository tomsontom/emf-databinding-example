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
 *   Michael Kanaley, TIBCO Software Inc., custom type handling
 * </copyright>
 *
 * $Id: HibernateMappingGenerator.java,v 1.24 2009/03/07 21:15:19 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationFactory;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerAware;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbannotation.FilterDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.NamedQuery;
import org.eclipse.emf.teneo.hibernate.hbannotation.ParamDef;
import org.eclipse.emf.teneo.hibernate.hbannotation.Parameter;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEPackage;
import org.eclipse.emf.teneo.mapping.strategy.impl.ClassicEntityNameStrategy;
import org.eclipse.emf.teneo.simpledom.Document;
import org.eclipse.emf.teneo.simpledom.DocumentHelper;
import org.eclipse.emf.teneo.simpledom.Element;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * The main starting point for generating a hibernate mapping from a PAnnotated
 * model.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class HibernateMappingGenerator implements ExtensionPoint,
		ExtensionManagerAware {

	/** The logger */
	private static final Log log = LogFactory
			.getLog(HibernateMappingGenerator.class);

	/** The list of processed annotated classes */
	private Set<PAnnotatedEClass> processedPAClasses = null;

	/** the mapping context */
	private MappingContext hbmContext;

	/** The extensionManager */
	private ExtensionManager extensionManager;

	/** The persistenceoptions */
	private PersistenceOptions persistenceOptions;

	/**
	 * Register the entity names in context.
	 */
	protected void initEntityNames(MappingContext hbmContext,
			PAnnotatedModel paModel) {
		for (PAnnotatedEPackage pae : paModel.getPaEPackages()) {
			for (PAnnotatedEClass paClass : pae.getPaEClasses()) {
				if (paClass.getEntity() != null) {
					hbmContext.setEntityName(paClass.getModelEClass(),
							getEntityName(paClass));
				}
			}
		}
	}

	/**
	 * @return Returns the entity name for the given paClass
	 */
	protected String getEntityName(PAnnotatedEClass paClass) {
		final EClass eclass = paClass.getModelEClass();

		String name = paClass.getEntity().getName();
		if (name == null) {
			// TODO sure we do not need package here?
			// MT: I think for 99.9% of the cases there are no name clashes but
			// it is possible to
			// that a package name is required to make things unique. This can
			// be done in a next
			// release as an
			// optional feature.
			name = hbmContext.getEntityNameStrategy().toEntityName(eclass);
		}
		return name;
	}

	/** Generate a hibernate mapping xml document from the pamodel */
	public Document generate(PAnnotatedModel paModel) throws MappingException {
		if (log.isDebugEnabled()) {
			log.debug("Geneting Hibernate mapping for " + paModel);
		}
		try {
			this.hbmContext = getExtensionManager().getExtension(
					MappingContext.class);
			this.hbmContext.setMappingProperties(getPersistenceOptions());
			hbmContext.setPaModel(paModel);
			hbmContext.beginDocument(createDocument());
			initEntityNames(hbmContext, paModel);
			processTypedefs(paModel);
			processPersistentClasses(paModel);
			// Process Named Queries
			// Added by PhaneeshN
			// This will emit the named query implementation into the mapping
			// based on model annotation @NamedQuery(name={name},
			// query={query});
			// Named queries can be parameterized
			for (PAnnotatedEPackage paPackage : paModel.getPaEPackages()) {
				processPANamedQueries(paPackage);
				for (PAnnotatedEClass paEClass : paPackage.getPaEClasses()) {
					processPANamedQueries(paEClass);
				}
			}
			return hbmContext.endDocument();
		} catch (MappingException exc) {
			throw new MappingException("Hibernate mapping generation failed",
					exc);
		}
	}

	/**
	 * Generate the hibernate mapping xml as a string
	 */
	public String generateToString(PAnnotatedModel annotatedModel)
			throws MappingException {
		return generate(annotatedModel).emitXML();
	}

	/**
	 * @return Returns an empty document used as template for the genration.
	 */
	protected Document createDocument() {
		Document mappingDoc = new Document();
		mappingDoc
				.setDocType("<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\" "
						+ "\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">");
		mappingDoc.setRoot(DocumentHelper.createElement("hibernate-mapping"));
		// set auto-import is false if the default eclass naming strategy is not
		// used
		if (!(hbmContext.getEntityNameStrategy() instanceof ClassicEntityNameStrategy)) {
			log
					.debug("Setting auto-import=false because eclassnamingstrategy is not the defaulteclassnamestrategy");
			mappingDoc.getRoot().addAttribute("auto-import", "false");
		}
		return mappingDoc;
	}

	/** Process all annotated classes of the pamodel */
	protected void processPersistentClasses(PAnnotatedModel paModel) {
		try {
			processedPAClasses = new HashSet<PAnnotatedEClass>();
			for (PAnnotatedEPackage paPackage : paModel.getPaEPackages()) {
				for (PAnnotatedEClass paEClass : paPackage.getPaEClasses()) {
					// here, we eliminate map.enties
					if (!hbmContext.isMapEMapAsTrueMap()
							|| !StoreUtil.isMapEntry(paEClass.getModelEClass())) {
						processPAClass(paEClass);

					}
					mapFilterDef(hbmContext.getCurrent(),
							((HbAnnotatedEClass) paEClass).getFilterDef());
				}
				mapFilterDef(hbmContext.getCurrent(),
						((HbAnnotatedEPackage) paPackage).getFilterDef());
			}
		} finally {
			processedPAClasses = null;
		}
	}

	protected void mapFilterDef(Element parentElement,
			List<FilterDef> filterDefs) {
		for (FilterDef fd : filterDefs) {
			final Element fdElement = parentElement.addElement("filter-def");
			fdElement.addAttribute("name", fd.getName());
			if (fd.getDefaultCondition() != null) {
				fdElement.addAttribute("condition", fd.getDefaultCondition());
			}
			for (ParamDef pd : fd.getParameters()) {
				final Element pdElement = fdElement.addElement("filter-param");
				pdElement.addAttribute("name", pd.getName());
				pdElement.addAttribute("type", pd.getType());
			}
		}
	}

	/**
	 * Process the given class, ensures that processing order is consistent with
	 * inheritance order. The given paEClass is added to the processedPAClasses.
	 */
	protected void processPAClass(PAnnotatedEClass paEClass) {
		if (processedPAClasses.add(paEClass)) {
			// also mapped superclasses can have an entity but ignore them here
			if (paEClass.getEntity() != null
					&& paEClass.getMappedSuperclass() == null) {
				// this is a persistent entity
				PAnnotatedEClass paSuperEntity = paEClass.getPaSuperEntity();
				if (paSuperEntity != null) {
					// enforce processing order consistent with inheritance
					// order
					processPAClass(paSuperEntity);
				}

				// ignore the map entries which do not have an explicit entity
				if (paEClass.getModelEClass().getInstanceClass() == Map.Entry.class
						&& paEClass.getEntity() == null) {
					log.debug("Ignoring " + paEClass.getModelEClass().getName()
							+ " ignored, is a map entry");
					paEClass.setTransient(PannotationFactory.eINSTANCE
							.createTransient());
					return;
				}

				hbmContext.setCurrentEClass(paEClass.getModelEClass());
				hbmContext.getEntityMapper().processEntity(paEClass);

			} else if (log.isDebugEnabled()) {
				log.debug("Skipping non-persistent class " + paEClass);
			}
		}
	}

	/**
	 * Processes the typedef annotations and creates corresponding typedef
	 * instances in the mapping.
	 */
	protected void processTypedefs(PAnnotatedModel paModel) {
		// Walk thru all the packages looking for custom EDataTypes.
		for (PAnnotatedEPackage annotatedEPackage : paModel.getPaEPackages()) {
			HbAnnotatedEPackage paPackage = (HbAnnotatedEPackage) annotatedEPackage;

			// handle the typedefs
			for (TypeDef td : paPackage.getHbTypeDef()) {
				emitTypeDef(td);
			}

			// Walk thru all the classifiers of the given package.
			for (PAnnotatedEDataType annotatedEDataType : paPackage
					.getPaEDataTypes()) {
				final HbAnnotatedEDataType hed = (HbAnnotatedEDataType) annotatedEDataType;
				if (hed.getHbTypeDef() != null) {
					emitTypeDef(hed.getHbTypeDef());
				}
			}
		}
	}

	/**
	 * Process the given class and declare named queries that are annotated as
	 * Model Annotations
	 * 
	 * @author PhaneeshN <a
	 *         href="mailto:phaneesh.nagararaja@sos.sungard.com">phaneesh
	 *         .nagararaja@sos.sungard.com</a> <a
	 *         href="http://www.sungardhe.com">SunGard Higher Education</a>
	 */
	protected void processPANamedQueries(PAnnotatedEClass paEClass) {
		// TODO: Should be refactored into a NamedQueryMapper Extension and
		// register it into
		// extension Manager
		if (log.isDebugEnabled()) {
			log.debug("Processing Queries for "
					+ paEClass.getModelEClass().getName());
		}
		if (log.isDebugEnabled()) {
			log
					.debug("********************** Named Queries ***************************");
			for (NamedQuery namedQuery : ((HbAnnotatedEClass) paEClass)
					.getHbNamedQuery()) {
				log.info(namedQuery.getName() + ":" + namedQuery.getQuery());
			}
			log
					.debug("****************************************************************");
		}
		for (NamedQuery namedQuery : ((HbAnnotatedEClass) paEClass)
				.getHbNamedQuery()) {
			final Element target = this.hbmContext.getCurrent().addElement(
					"query");
			target.addAttribute("name", namedQuery.getName());
			target.addText("<![CDATA[" + namedQuery.getQuery() + "]]>");
		}
	}

	protected void processPANamedQueries(PAnnotatedEPackage paEPackage) {
		// TODO: Should be refactored into a NamedQueryMapper Extension and
		// register it into
		// extension Manager
		if (log.isDebugEnabled()) {
			log.debug("Processing Queries for "
					+ paEPackage.getModelEPackage().getName());
		}
		if (log.isDebugEnabled()) {
			log
					.debug("********************** Named Queries ***************************");
			for (NamedQuery namedQuery : ((HbAnnotatedEPackage) paEPackage)
					.getHbNamedQuery()) {
				log.info(namedQuery.getName() + ":" + namedQuery.getQuery());
			}
			log
					.debug("****************************************************************");
		}
		for (NamedQuery namedQuery : ((HbAnnotatedEPackage) paEPackage)
				.getHbNamedQuery()) {
			final Element target = this.hbmContext.getCurrent().addElement(
					"query");
			target.addAttribute("name", namedQuery.getName());
			target.addText("<![CDATA[" + namedQuery.getQuery() + "]]>");
		}
	}

	/** Emit a typedef */
	protected void emitTypeDef(TypeDef td) {
		final Element target = this.hbmContext.getCurrent().addElement(
				"typedef");
		target.addAttribute("name", td.getName());
		target.addAttribute("class", td.getTypeClass());
		for (Parameter param : td.getParameters()) {
			target.addElement("param").addAttribute("name", param.getName())
					.addText(param.getValue());
		}
	}

	/**
	 * @return the extensionManager
	 */
	public ExtensionManager getExtensionManager() {
		return extensionManager;
	}

	/**
	 * @param extensionManager
	 *            the extensionManager to set
	 */
	public void setExtensionManager(ExtensionManager extensionManager) {

		// set the default extensions for mapping in the extensionManager

		this.extensionManager = extensionManager;
	}

	/**
	 * @return the persistenceOptions
	 */
	public PersistenceOptions getPersistenceOptions() {
		return persistenceOptions;
	}

	/**
	 * @param persistenceOptions
	 *            the persistenceOptions to set
	 */
	public void setPersistenceOptions(PersistenceOptions persistenceOptions) {
		this.persistenceOptions = persistenceOptions;
	}
}