/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: ManyAttributeMapper.java,v 1.25 2009/03/07 21:15:20 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.OneToMany;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps many valued attributes.
 * <p>
 * Assumes that the given {@link PAnnotatedEAttribute} is normal, i.e.
 * <ul>
 * <li>it is a {@link PAnnotatedEReference};
 * <li>it has a {@link OneToMany} annotation;
 * <li>oneToMany.getCascade() is ALL
 * <li>oneToMany.getTargetEntity is not specified
 * <li>the {@link OneToMany} annotation have the following attributes set:
 * <li>TODO which is the meaning of a column annotation? Can it have one?
 * </ul>
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class ManyAttributeMapper extends AbstractAssociationMapper implements
		ExtensionPoint {

	/** The logger */
	private static final Log log = LogFactory.getLog(ManyAttributeMapper.class);

	/**
	 * Process a many=true EAttribute
	 */
	public void processManyAttribute(PAnnotatedEAttribute paAttribute) {
		if (log.isDebugEnabled()) {
			log.debug("Generating many valued attribute mapping for "
					+ paAttribute);
		}

		final HbAnnotatedEAttribute hbAttribute = (HbAnnotatedEAttribute) paAttribute;
		final EAttribute eattr = paAttribute.getModelEAttribute();

		final boolean isArray = eattr.getEType().getInstanceClass() != null
				&& eattr.getEType().getInstanceClass().isArray();

		final Element collElement = addCollectionElement(paAttribute);
		final Element keyElement = collElement.addElement("key");

		final JoinTable jt = paAttribute.getJoinTable();
		final List<JoinColumn> jcs = paAttribute.getJoinColumns() == null ? new ArrayList<JoinColumn>()
				: paAttribute.getJoinColumns();
		final OneToMany otm = paAttribute.getOneToMany();

		if (jt != null) {
			addJoinTable(hbAttribute, collElement, keyElement, jt);
			addKeyColumns(hbAttribute, keyElement, jcs);
		} else {
			// TODO should we also add joinColumns annotation?
			addKeyColumns(hbAttribute, keyElement, jcs);
		}

		if (!otm.isIndexed() && isArray) {
			log
					.warn("One to many is not indexed but this is an array, force=ing index column!");
		}

		if ((otm.isIndexed() || isArray) && hbAttribute.getHbIdBag() == null) {
			addListIndex(collElement, paAttribute);
		}

		if (!isArray) {
			addFetchType(collElement, otm.getFetch());
		}
		addCascadesForMany(collElement, getCascades(hbAttribute.getHbCascade(),
				otm.getCascade()));

		if (FeatureMapUtil.isFeatureMap(paAttribute.getModelEAttribute())) {
			FeatureMapMapping fmm = new FeatureMapMapping(getHbmContext(),
					paAttribute);
			getHbmContext().addFeatureMapMapper(fmm);
			collElement.addElement("one-to-many").addAttribute("entity-name",
					fmm.getEntityName());
		} else {
			addElementElement(collElement, paAttribute,
					getColumns(paAttribute), otm.getTargetEntity());
		}

		addAccessor(collElement);

		mapFilter(collElement, ((HbAnnotatedETypeElement) paAttribute)
				.getFilter());
	}
}
