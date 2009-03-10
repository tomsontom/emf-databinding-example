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
 * $Id: EmbeddedMapper.java,v 1.19 2008/12/07 13:50:09 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps Embedded properties.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class EmbeddedMapper extends AbstractMapper implements ExtensionPoint {

	// the logger
	private static final Log log = LogFactory.getLog(EmbeddedMapper.class);

	/**
	 * Process Embedded object
	 */
	public void process(PAnnotatedEReference paReference) {
		log.debug("Processing embedded: " + paReference.toString());

		// push the current overrides
		getHbmContext().pushOverrideOnStack();
		// and add our own
		getHbmContext().addAttributeOverrides(
				paReference.getAttributeOverrides());
		getHbmContext().addAssociationOverrides(
				paReference.getAssociationOverrides());

		// push the feature is used for automatic renaming
		getHbmContext().pushEmbeddingFeature(paReference);
		try {
			// make a difference between a many-to-one component and
			// multi-component
			if (paReference.getManyToOne() != null) {
				processSingleEmbedded(paReference, paReference
						.getModelEReference().getEReferenceType());
			} else if (paReference.getOneToOne() != null) {
				processSingleEmbedded(paReference, paReference
						.getModelEReference().getEReferenceType());
			} else {
				if (paReference.getManyToMany() != null) {
					throw new MappingException(
							"ManyToMany can not be combined with Embedded "
									+ paReference);
				} else if (paReference.getOneToMany() == null) {
					throw new MappingException(
							"OneToMany must be set for embedded elist type: "
									+ paReference);
				}

				// only one to many
				processMultiEmbedded(paReference);
			}
		} finally {
			// and continue with the previous set of overrides
			getHbmContext().popOverrideStack();
			getHbmContext().popEmbeddingFeature();
		}
	}

	/** Process a many-to-one component */
	private void processSingleEmbedded(PAnnotatedEReference paReference,
			EClass target) {
		log.debug("Processing single embedded: " + paReference.toString());

		final Element componentElement = getHbmContext().getCurrent()
				.addElement("component").addAttribute("name",
						paReference.getModelEReference().getName());

		// todo: change recognizing a component to using metadata!
		// then the class tag can point to a real impl. class@
		componentElement.addAttribute("class", getHbmContext()
				.getInstanceClassName(target)); // implClass
		// .
		// getName
		// (
		// )
		// )
		// ;
		getHbmContext().setCurrent(componentElement);
		try {
			// process the features of the target
			final PAnnotatedEClass componentAClass = paReference.getPaModel()
					.getPAnnotated(
							paReference.getModelEReference()
									.getEReferenceType());

			getHbmContext().processFeatures(getAllFeatures(componentAClass));
		} finally {
			getHbmContext().setCurrent(componentElement.getParent());
		}

		addAccessor(componentElement, hbmContext
				.getComponentPropertyHandlerName());
	}

	/** Process a list of components */
	private void processMultiEmbedded(PAnnotatedEReference paReference) {
		log.debug("Processing multi embedded: " + paReference.toString());

		// let the featureprocessor handle this, the one to many is handled by
		// the OneToManyMapper
		getHbmContext().getFeatureMapper().getOneToManyMapper().process(
				paReference);
	}
}
