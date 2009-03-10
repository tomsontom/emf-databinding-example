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
 * $Id: ManyToOneReferenceAnnotator.java,v 1.13 2008/10/12 11:24:35 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToOne;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Annotates an ereference.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.13 $
 */

public class ManyToOneReferenceAnnotator extends BaseEFeatureAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(ManyToOneReferenceAnnotator.class);

	/** Annotate it */
	public void annotate(PAnnotatedEReference aReference) {
		final String logStr =
				aReference.getModelEReference().getName() + "/" +
						aReference.getModelEReference().getEContainingClass().getName();

		if (aReference.getOneToMany() != null || aReference.getManyToMany() != null || aReference.getOneToOne() != null) {
			throw new StoreMappingException("The feature/eclass " + logStr + " should be a ManyToOne but " +
					"it already has a OneToMany, ManyToMany or OneToOne annotation");
		}

		final EReference eReference = (EReference) aReference.getModelElement();

		ManyToOne mto = aReference.getManyToOne();
		if (mto == null) {
			log.debug("EReference + " + logStr + " does not have a manytoone annotation, adding one");
			mto = getFactory().createManyToOne();
			aReference.setManyToOne(mto);
			// removed unsettable because it is not used to define optional, it is used
			// to allow distinction between the default value set or a feature which has not been
			// set, this is used in validation
// mto.setOptional(!eReference.isRequired() || eReference.isUnsettable() ||
// eReference.getEOpposite() != null);
			mto.setOptional(!eReference.isRequired() || eReference.getEOpposite() != null);
			mto.setEModelElement(eReference);
		} else {
			log.debug("EReference + " + logStr + " does have a manytoone annotation, using it");
		}

		if (!mto.isSetFetch()) {
			mto.setFetch(getFetch(aReference.getAReferenceType()));
		}

		if (eReference.isContainment() || getPersistenceOptions().isSetDefaultCascadeOnNonContainment()) {
			setCascade(mto.getCascade(), eReference.isContainment());
		}

		// NOTE: Sometimes EMF generated getters/setters have a
		// very generic type (EObject), if the type can be derived here then
		// this should
		// be added here
		if (mto.getTargetEntity() == null) {
			mto.setTargetEntity(getEntityName(eReference.getEReferenceType()));
		}

		if (getPersistenceOptions().isSetForeignKeyNames() && aReference.getForeignKey() == null) {
			aReference.setForeignKey(createFK(aReference));
		}

		if (getPersistenceOptions().isMapEmbeddableAsEmbedded() &&
				aReference.getAReferenceType().getEmbeddable() != null) {
			aReference.setEmbedded(getFactory().createEmbedded());
		}

		// create a set of joincolumns, note that if this is a two-way relation
		// then
		// the other side will use the name of the ereference as second
		// parameter,
		// matching the joincolumns on the other side
		if (aReference.getJoinColumns() == null || aReference.getJoinColumns().isEmpty()) {
			if (aReference.getAReferenceType() != null) {
				// == null if the reference is to a high level type such as an eobject

				// Set the join columns to not insertable/updatable if this is the many-to-one side
				// of a bidirectional relation with a one-to-many list (indexed!) on the other side.
				boolean isInsertableUpdatable = true;
				if (eReference.getEOpposite() != null && !eReference.getEOpposite().isTransient()) {
					final PAnnotatedEReference aOpposite = getAnnotatedModel().getPAnnotated(eReference.getEOpposite());

					boolean hasJoinTable =
							(!aOpposite.getModelEReference().isContainment() && getPersistenceOptions()
								.isJoinTableForNonContainedAssociations()) ||
									aOpposite.getJoinTable() != null;

					if (!hasJoinTable && aOpposite.getOneToMany() != null && aOpposite.getOneToMany().isList()) {
						isInsertableUpdatable = false;
					}
				}
				// old:
				// isInsertableUpdatable = eReference.getEOpposite() == null ||
				// eReference.getEOpposite().isTransient()

				// NOTE that currently in all cases if there is an opposite Teneo assumes
				// that it is managed from the other side. In reality this only needs to
				// be done if the other side is indexed.
				// NOTE: otm/mto with join table is not supported at the moment!
				final List<String> names = getSqlNameStrategy().getManyToOneJoinColumnNames(aReference);
				aReference.getJoinColumns().addAll(getJoinColumns(names, mto.isOptional(), isInsertableUpdatable, mto));
			}
		} else {
			// if nullable was not set explicitly then use the mto optional feature
			for (JoinColumn jc : aReference.getJoinColumns()) {
				if (!jc.isSetNullable()) {
					jc.setNullable(mto.isOptional());
				}
			}
		}
	}
}
