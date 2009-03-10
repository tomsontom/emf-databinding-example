/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: ManyExternalReferenceMapper.java,v 1.1 2008/12/16 20:40:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.OneToMany;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps ereference with isMany is true which should be stored as an URI.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class ManyExternalReferenceMapper extends AbstractAssociationMapper
		implements ExtensionPoint {

	private static final Log log = LogFactory
			.getLog(ManyExternalReferenceMapper.class);

	/**
	 * Process a many=true EReference with URI attribute.
	 */
	public void processManyReference(PAnnotatedEReference paReference) {
		if (log.isDebugEnabled()) {
			log.debug("Generating many valued attribute mapping for "
					+ paReference);
		}

		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;

		final Element collElement = addCollectionElement(paReference);
		final Element keyElement = collElement.addElement("key");

		final JoinTable jt = paReference.getJoinTable();
		final List<JoinColumn> jcs = paReference.getJoinColumns() == null ? new ArrayList<JoinColumn>()
				: paReference.getJoinColumns();
		final OneToMany otm = paReference.getOneToMany();

		if (jt != null) {
			addJoinTable(hbReference, collElement, keyElement, jt);
			addKeyColumns(hbReference, keyElement, jcs);
		} else {
			// TODO should we also add joinColumns annotation?
			addKeyColumns(hbReference, keyElement, jcs);
		}

		if (otm.isIndexed() && hbReference.getHbIdBag() == null) {
			addListIndex(collElement, paReference);
		}

		addFetchType(collElement, otm.getFetch());
		addCascadesForMany(collElement, getCascades(hbReference.getHbCascade(),
				otm.getCascade()));

		addElementElement(collElement, paReference, getColumns(paReference),
				otm.getTargetEntity());

		addAccessor(collElement);

		mapFilter(collElement, ((HbAnnotatedETypeElement) hbReference)
				.getFilter());
	}
}
