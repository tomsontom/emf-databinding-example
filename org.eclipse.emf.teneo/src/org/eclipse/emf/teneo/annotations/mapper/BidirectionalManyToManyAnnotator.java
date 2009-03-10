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
 * $Id: BidirectionalManyToManyAnnotator.java,v 1.8 2008/05/27 07:42:10 mtaal Exp $
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
 * Annotates a bidirectional many-to-many ereference.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */

public class BidirectionalManyToManyAnnotator extends BaseEFeatureAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(BidirectionalManyToManyAnnotator.class);

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
		final EReference eOpposite = eReference.getEOpposite();
		assert (eOpposite != null && eOpposite.isMany());

		ManyToMany mtm = aReference.getManyToMany();
		final boolean mtmWasSet = mtm != null; // mtm was set manually
		if (mtm == null) {
			log.debug("Adding manytomany annotations to ereference: " + featureLogStr);
			mtm = getFactory().createManyToMany();
			aReference.setManyToMany(mtm);
			mtm.setEModelElement(eReference);
		} else {
			log.debug("ManyToMany present check if default information should be added");
		}

		if (eReference.isContainment() || getPersistenceOptions().isSetDefaultCascadeOnNonContainment()) {
			setCascade(mtm.getCascade(), eReference.isContainment());
		}

		if (mtm.getTargetEntity() == null) {
			mtm.setTargetEntity(getEntityName(eReference.getEReferenceType()));
		}

		if (getPersistenceOptions().isSetForeignKeyNames() && aReference.getForeignKey() == null) {
			aReference.setForeignKey(createFK(aReference));
		}

		// determine where to place the jointable annotation and where to place
		// the mappedby
		// use a certain logic to determine as each is only set on one side
		// note that the join is always set on the other side of mapped by!
		// note that we can not do setJoinHere = !setMappedByHere because there
		// are situations
		// that even for mtm no mappedby is set on either side, nl. in case of
		// containment

		// also check if the other side has a (manual) manytomany with mappedby
		// set
		// bugzilla: 164808
		final PAnnotatedEReference otherPA = aReference.getPaModel().getPAnnotated(eOpposite);
		if (mtm.getMappedBy() == null && setMappedBy(eReference) &&
				(otherPA.getManyToMany() == null || otherPA.getManyToMany().getMappedBy() == null)) {
			mtm.setMappedBy(eOpposite.getName());
		}

		JoinTable joinTable = aReference.getJoinTable();
		if (joinTable == null) {
			joinTable = getFactory().createJoinTable();
			aReference.setJoinTable(joinTable);
		}
		joinTable.setEModelElement(eReference);

		// set unique and indexed
		// disabled because indexed = false now for mtm,
		// to overcome this the user has to explicitly set a mtm annotation.
		if (!mtmWasSet) {
			log.debug("Setting indexed and unique from ereference.isOrdered/isUnique "
					+ "because mtm was not set manually!");
			mtm.setIndexed(!getPersistenceOptions().alwaysMapListAsBag() && eReference.isOrdered());
		}

		// NOTE that the ejb3 spec states that the jointable should be the
		// concatenation of the
		// tablenames of the owning entities with an underscore, this will
		// quickly lead to nameclashes
		// in the case there is more than one relation between two classes. This
		// can be pretty likely
		// if the inheritance strategy is single_table.
		// now possibility to use a different naming strategy
		if (joinTable.getName() == null) {
			joinTable.setName(getSqlNameStrategy().getJoinTableName(aReference));
		}
		if (joinTable.getJoinColumns().size() == 0) {
			final List<String> names = getSqlNameStrategy().getJoinTableJoinColumns(aReference, false);
			joinTable.getJoinColumns().addAll(getJoinColumns(names, false, true, mtm));
		}
		if (joinTable.getInverseJoinColumns().size() == 0) {
			final List<String> names = getSqlNameStrategy().getJoinTableJoinColumns(aReference, true);
			joinTable.getInverseJoinColumns().addAll(getJoinColumns(names, false, true, mtm));
		}
	}
}
