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
 * $Id: UnidirectionalManyToManyAnnotator.java,v 1.7 2008/05/27 07:42:09 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToMany;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Annotates a many-to-many which is handled from one side.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.7 $
 */

public class UnidirectionalManyToManyAnnotator extends BaseEFeatureAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(UnidirectionalManyToManyAnnotator.class);

	/** Process the features of the eclass */
	public void annotate(PAnnotatedEReference aReference) {
		final String featureLogStr =
				aReference.getModelEReference().getName() + "/" +
						aReference.getModelEReference().getEContainingClass().getName();

		if (aReference.getOneToMany() != null || aReference.getOneToOne() != null || aReference.getManyToOne() != null) {
			throw new StoreMappingException("The feature/eclass " + featureLogStr + " should be a ManyToMany but " +
					"it already has a OneToMany, OneToOne or ManyToOne annotation");
		}

		final EReference eReference = (EReference) aReference.getModelElement();

		// note that mtm is always present because this case can not be discovered by Teneo
		final ManyToMany mtm = aReference.getManyToMany();
		log.debug("ManyToMany present check if default information should be added");
		mtm.setEModelElement(eReference);

		if (eReference.isContainment() || getPersistenceOptions().isSetDefaultCascadeOnNonContainment()) {
			setCascade(mtm.getCascade(), eReference.isContainment());
		}

		if (mtm.getTargetEntity() == null) {
			mtm.setTargetEntity(getEntityName(eReference.getEReferenceType()));
		}

		// with a unidirectional mtm the join is always placed here
		JoinTable joinTable = aReference.getJoinTable();
		if (joinTable == null) {
			joinTable = getFactory().createJoinTable();
			aReference.setJoinTable(joinTable);
		}
		joinTable.setEModelElement(eReference);

		if (getPersistenceOptions().isSetForeignKeyNames() && aReference.getForeignKey() == null) {
			aReference.setForeignKey(createFK(aReference));
		}

		// note that here not the eclass name is used for the opposite side but
		// the name of the targetentity
		// because that's the one which is known here
		if (joinTable.getName() == null) {
			joinTable.setName(getSqlNameStrategy().getJoinTableName(aReference));
		}
		if (joinTable.getJoinColumns() == null) {
			final List<String> names = getSqlNameStrategy().getJoinTableJoinColumns(aReference, false);
			joinTable.getJoinColumns().addAll(getJoinColumns(names, false, true, mtm));
		}
	}
}
