/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: ManyToManyMapper.java,v 1.32 2009/03/07 21:15:20 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToMany;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.simpledom.Element;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Maps a many to many relation.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class ManyToManyMapper extends AbstractAssociationMapper implements
		ExtensionPoint {

	/** Logger */
	private static final Log log = LogFactory.getLog(ManyToManyMapper.class);

	/**
	 * Process a many to many for all cases
	 */
	public void process(PAnnotatedEReference paReference) {
		log.debug("Creating many-to-many for " + paReference);

		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;
		final EReference eref = paReference.getModelEReference();
		final JoinTable jt = hbReference.getJoinTable();
		final ManyToMany mtm = hbReference.getManyToMany();

		if (jt == null) {
			throw new MappingException("Jointable is mandatory "
					+ StoreUtil.toString(eref));
		}

		final Element collElement = addCollectionElement(hbReference);

		if (hbReference.getImmutable() != null) {
			collElement.addAttribute("mutable", "false");
		}

		if (((HbAnnotatedEReference) paReference).getHbCache() != null) {
			addCacheElement(collElement, ((HbAnnotatedEReference) paReference)
					.getHbCache());
		}

		final Element keyElement = collElement.addElement("key");
		handleOndelete(keyElement, hbReference.getHbOnDelete());

		boolean isMap = StoreUtil.isMap(eref)
				&& getHbmContext().isMapEMapAsTrueMap();
		if (mtm.isIndexed() && hbReference.getHbIdBag() == null) {
			// now we check if it is a list or a map
			if (hbReference.getMapKey() != null
					|| hbReference.getHbMapKey() != null
					|| hbReference.getMapKeyManyToMany() != null) {
				addMapKey(collElement, paReference);
			} else if (isMap) {
				addMapKey(collElement, hbReference);
			} else {
				addListIndex(collElement, hbReference);
			}
		}

		addFetchType(collElement, mtm.getFetch());
		addCascades(collElement, getCascades(hbReference.getHbCascade(), mtm
				.getCascade()), false);

		final PAnnotatedEClass referedToAClass = hbReference
				.getAReferenceType();
		String targetName = mtm.getTargetEntity();
		if (targetName == null) {
			targetName = getHbmContext().getEntityName(
					hbReference.getEReferenceType());
		}
		log.debug("Target entity-name " + targetName);

		final Element mtmElement;
		if (referedToAClass.isOnlyMapAsEntity()
				|| !getHbmContext().forceUseOfInstance(referedToAClass)) {
			mtmElement = collElement.addElement("many-to-many").addAttribute(
					"entity-name", targetName).addAttribute("unique", "false");
		} else {
			mtmElement = collElement.addElement("many-to-many").addAttribute(
					"class",
					getHbmContext().getInstanceClassName(
							hbReference.getEReferenceType())).addAttribute(
					"unique", "false");
		}

		if (hbReference.getNotFound() != null) {
			mtmElement.addAttribute("not-found", hbReference.getNotFound()
					.getAction().getName().toLowerCase());
		}

		addForeignKeyAttribute(mtmElement, hbReference);

		// inverse is not supported by indexed lists
		if ((!mtm.isIndexed() && hbReference.getHbIdBag() == null && mtm
				.getMappedBy() != null)) {
			// note inverse is required for many-to-many to work, otherwise
			// hibernate will insert
			// both sides of the relation twice.
			collElement.addAttribute("inverse", "true");
		} else if (mtm.getMappedBy() != null && mtm.isIndexed()) {
			log
					.warn("Indexed is true but indexed is not supported for inverse=true and many-to-many, not setting inverse=true");
		}

		// check for a special case that mapped by is set on both sides
		// should always be unequal to null
		if (hbReference.getModelEReference().getEOpposite() != null) {
			final PAnnotatedEReference aOpposite = hbReference.getPaModel()
					.getPAnnotated(
							hbReference.getModelEReference().getEOpposite());
			if (aOpposite.getManyToMany() != null
					&& aOpposite.getManyToMany().getMappedBy() != null
					&& mtm.getMappedBy() != null) {
				log
						.error("Mappedby is set on both sides of the many-to-many relation, this does not work, see the efeature: "
								+ hbReference.getModelElement().toString()
								+ ". Ignoring the mappedby in this efeature");
				mtm.setMappedBy(null);
			}
		}

		addJoinTable(hbReference, collElement, keyElement, jt);
		if (jt.getInverseJoinColumns() != null) {
			for (JoinColumn joinColumn : jt.getInverseJoinColumns()) {
				mtmElement.addElement("column").addAttribute("name",
						getHbmContext().trunc(joinColumn.getName()))
						.addAttribute("not-null",
								joinColumn.isNullable() ? "false" : "true")
						.addAttribute("unique",
								joinColumn.isUnique() ? "true" : "false");
			}
		}

		addAccessor(collElement);

		mapFilter(collElement, ((HbAnnotatedETypeElement) paReference)
				.getFilter());
	}
}
