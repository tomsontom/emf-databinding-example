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
 * $Id: OneToManyAttributeAnnotator.java,v 1.8 2008/05/27 07:42:10 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.annotations.pannotation.EnumType;
import org.eclipse.emf.teneo.annotations.pannotation.Enumerated;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.OneToMany;
import org.eclipse.emf.teneo.annotations.pannotation.TemporalType;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Annotates a one-to-many attribute (an eattribute with ismany=true), an example is a list of
 * primitives (list of ints).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */

public class OneToManyAttributeAnnotator extends BaseEFeatureAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(OneToManyAttributeAnnotator.class);

	private TemporalType optionDefaultTemporal = null;

	/** Process the features of the eclass */
	public void annotate(PAnnotatedEAttribute aAttribute) {
		final String logStr =
				aAttribute.getModelEAttribute().getName() + "/" +
						aAttribute.getModelEAttribute().getEContainingClass().getName();

		log.debug("EAttribute " + logStr + " needs a onetomany");

		final EAttribute eAttribute = (EAttribute) aAttribute.getModelElement();

		OneToMany otm = aAttribute.getOneToMany();
		final boolean otmWasSet = otm != null; // otm was set manually
		if (otm == null) {
			log.debug("One to many not present adding one");
			otm = getFactory().createOneToMany();
			aAttribute.setOneToMany(otm);
			otm.setEModelElement(eAttribute);

			if (getPersistenceOptions().isFetchContainmentEagerly()) {
				otm.setFetch(FetchType.EAGER);
			}
		} else {
			log.debug("One to many present adding default information if required");
		}

		if (getPersistenceOptions().isSetForeignKeyNames() && aAttribute.getForeignKey() == null) {
			aAttribute.setForeignKey(createFK(aAttribute));
		}

		// handle list of enums
		if (eAttribute.getEType() instanceof EEnum && aAttribute.getEnumerated() == null) {
			final Enumerated enumerated = getFactory().createEnumerated();
			enumerated.setValue(EnumType.STRING);
			enumerated.setEModelElement(eAttribute);
			aAttribute.setEnumerated(enumerated);
		}

		if (aAttribute.getTemporal() == null) {
			setTemporal(aAttribute, optionDefaultTemporal);
		}

		// set cascade if not set
		if (otm.getCascade().isEmpty()) {
			otm.getCascade().add(CascadeType.ALL);
		}

		if (otm.getTargetEntity() == null) {
			otm.setTargetEntity(getTargetTypeName(aAttribute));
		}

		if (aAttribute.getJoinTable() == null) {
			// note not optional because lists of simple types are embedded
			final JoinTable jt = getFactory().createJoinTable();
			jt.setName(getSqlNameStrategy().getJoinTableName(aAttribute));
			aAttribute.setJoinTable(jt);
		}

		if (aAttribute.getJoinColumns().size() == 0) {
			final List<String> names = getSqlNameStrategy().getOneToManyEAttributeJoinColumns(aAttribute);
			aAttribute.getJoinColumns().addAll(
				getJoinColumns(names, FeatureMapUtil.isFeatureMap(eAttribute), true, otm));
		}

		// set unique and indexed
		if (!otmWasSet) {
			log.debug("Setting indexed and unique on otm from eAttribute.isOrdered/isUnique "
					+ "because otm was not set manually");
			otm.setIndexed(eAttribute.isOrdered());
			otm.setUnique(eAttribute.isUnique());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.annotations.mapper.AbstractAnnotator#setPersistenceOptions(org.eclipse.emf.teneo.PersistenceOptions)
	 */
	@Override
	public void setPersistenceOptions(PersistenceOptions persistenceOptions) {
		super.setPersistenceOptions(persistenceOptions);

		optionDefaultTemporal = TemporalType.get(persistenceOptions.getDefaultTemporalValue());
		if (optionDefaultTemporal == null) {
			throw new StoreMappingException("Temporal value not found: " + persistenceOptions.getDefaultTemporalValue());
		}
	}

}
