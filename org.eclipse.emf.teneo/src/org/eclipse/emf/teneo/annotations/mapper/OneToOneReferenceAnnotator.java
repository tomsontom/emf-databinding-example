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
 * $Id: OneToOneReferenceAnnotator.java,v 1.9 2008/09/21 19:03:20 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.OneToOne;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Annotates an ereference.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.9 $
 */

public class OneToOneReferenceAnnotator extends BaseEFeatureAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(OneToOneReferenceAnnotator.class);

	/** Annotate it */
	public void annotate(PAnnotatedEReference aReference) {
		final String logStr =
				aReference.getModelEReference().getName() + "/" +
						aReference.getModelEReference().getEContainingClass().getName();

		if (aReference.getOneToMany() != null || aReference.getManyToMany() != null ||
				aReference.getManyToOne() != null) {
			throw new StoreMappingException("The feature/eclass " + logStr + " should be a OneToOne but " +
					"it already has a OneToMany, ManyToMany or ManyToOne annotation");
		}

		final EReference eReference = (EReference) aReference.getModelElement();

		OneToOne oto = aReference.getOneToOne();
		if (oto == null) {
			log.debug("EReference + " + logStr + " does not have a onetoone annotation, adding one");
			oto = getFactory().createOneToOne();
			aReference.setOneToOne(oto);
			// removed unsettable because it is not used to define optional, it is used
			// to allow distinction between the default value set or a feature which has not been
			// set, this is used in validation
// oto.setOptional(!eReference.isRequired() || eReference.isUnsettable());
			oto.setOptional(!eReference.isRequired());
			oto.setEModelElement(eReference);
		} else {
			log.debug("EReference + " + logStr + " has an onetoone annotation setting defaults if required");
		}

		if (!oto.isSetFetch()) {
			oto.setFetch(getFetch(aReference.getAReferenceType()));
		}

		// determine where to put the mapped-by
		if (oto.getMappedBy() == null && setMappedBy(eReference)) {
			oto.setMappedBy(eReference.getEOpposite().getName());
		}

		if (getPersistenceOptions().isSetForeignKeyNames() && aReference.getForeignKey() == null) {
			// See bugzilla 211798: handle a specific case when this is a bidirectional
			// association. In that case the foreign key name has to be
			// the same on both sides and is set on the many-side. So use the
			// annotated reference from the other side to ensure that the same foreign key name
			// is used.
			if (eReference.getEOpposite() != null && !eReference.getEOpposite().isTransient()) {
				final PAnnotatedEReference aOpposite = aReference.getPaModel().getPAnnotated(eReference.getEOpposite());
				if (aOpposite != null && aOpposite.getTransient() == null) {
					// don't do anything as otherwise hibernate will create two
					// fk's with the same name

// if (aOpposite.getForeignKey() != null) {
// final ForeignKey fk = getFactory().createForeignKey();
// fk.setName(aOpposite.getForeignKey().getName());
// aReference.setForeignKey(fk);
// } else {
// aReference.setForeignKey(createFK(aOpposite));
// }
				} else {
					aReference.setForeignKey(createFK(aReference));
				}
			} else {
				aReference.setForeignKey(createFK(aReference));
			}
		}

		setCascade(oto.getCascade(), eReference.isContainment());

		if (getPersistenceOptions().isMapEmbeddableAsEmbedded() &&
				aReference.getAReferenceType().getEmbeddable() != null) {
			aReference.setEmbedded(getFactory().createEmbedded());
		}

		// Note: Sometimes EMF generated getters/setters have a
		// very generic type (EObject), if the type can be derived here then
		// this should
		// be added here
		if (oto.getTargetEntity() == null) {
			oto.setTargetEntity(getEntityName(eReference.getEReferenceType()));
		}
	}
}
