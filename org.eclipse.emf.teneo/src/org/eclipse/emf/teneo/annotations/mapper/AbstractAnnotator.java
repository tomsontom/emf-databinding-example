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
 * $Id: AbstractAnnotator.java,v 1.5 2008/09/06 00:14:04 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationFactory;
import org.eclipse.emf.teneo.extension.ExtensionInitializable;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerAware;
import org.eclipse.emf.teneo.mapping.strategy.EntityNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.SQLNameStrategy;
import org.eclipse.emf.teneo.mapping.strategy.StrategyUtil;

/**
 * The parent class of all annotator classes.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public abstract class AbstractAnnotator implements ExtensionManagerAware, ExtensionInitializable {

	protected PannotationFactory factory = PannotationFactory.eINSTANCE;
	private ExtensionManager extensionManager;
	private PAnnotatedModel annotatedModel;
	private SQLNameStrategy sqlNameStrategy;
	private EntityNameStrategy entityNameStrategy;
	private PersistenceOptions persistenceOptions;
	private EFeatureAnnotator eFeatureAnnotator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.extension.ExtensionInitializable#initializeExtension()
	 */
	public void initializeExtension() {
		sqlNameStrategy = getExtensionManager().getExtension(SQLNameStrategy.class);
		entityNameStrategy = getExtensionManager().getExtension(EntityNameStrategy.class);
	}

	/** Method is called after all the important members have been set */
	protected void initialize() {

	}

	/**
	 * Returns the entity name of the eclass, note that in case of maps a different approach is
	 * followed (the entity name of the value is returned.
	 */
	public String getEntityName(EClass eClass) {
		return StrategyUtil.getEntityName(entityNameStrategy, persistenceOptions, annotatedModel, eClass);
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
		this.extensionManager = extensionManager;
	}

	/**
	 * @return the annotatedModel
	 */
	public PAnnotatedModel getAnnotatedModel() {
		return annotatedModel;
	}

	/**
	 * @param annotatedModel
	 *            the annotatedModel to set
	 */
	public void setAnnotatedModel(PAnnotatedModel annotatedModel) {
		this.annotatedModel = annotatedModel;
	}

	/**
	 * @return the factory
	 */
	public PannotationFactory getFactory() {
		return factory;
	}

	/**
	 * @param factory
	 *            the factory to set
	 */
	public void setFactory(PannotationFactory factory) {
		this.factory = factory;
	}

	/**
	 * @return the sqlNameStrategy
	 */
	public SQLNameStrategy getSqlNameStrategy() {
		return sqlNameStrategy;
	}

	/**
	 * @return the entityNameStrategy
	 */
	public EntityNameStrategy getEntityNameStrategy() {
		return entityNameStrategy;
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

	/** Creates an annotator and sets all kinds of default info */
	protected <T extends AbstractAnnotator> T createAnnotator(Class<T> clz) {
		final T annotator = getExtensionManager().getExtension(clz);
		annotator.setAnnotatedModel(annotatedModel);
		annotator.setExtensionManager(getExtensionManager());
		annotator.setPersistenceOptions(persistenceOptions);
		annotator.setFactory(getFactory());
		annotator.initialize();
		return annotator;
	}

	/**
	 * @return the eFeatureAnnotator
	 */
	public EFeatureAnnotator getEFeatureAnnotator() {
		return eFeatureAnnotator;
	}

	/**
	 * @param featureAnnotator
	 *            the eFeatureAnnotator to set
	 */
	public void setEFeatureAnnotator(EFeatureAnnotator featureAnnotator) {
		eFeatureAnnotator = featureAnnotator;
	}

}
