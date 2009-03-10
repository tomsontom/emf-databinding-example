/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: ManyToOneMapper.java,v 1.33 2009/03/07 21:15:20 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.ManyToOne;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps a {@link ManyToOne} element to its {@link MappingContext}.
 * <p>
 * Assumes that the given {@link PAnnotatedEStructuralFeature} is a normal
 * ManyToOne, i.e.
 * <ul>
 * <li>it is a {@link PAnnotatedEReference};
 * <li>it has a {@link ManyToOne} annotation;
 * </ul>
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class ManyToOneMapper extends AbstractAssociationMapper implements
		ExtensionPoint {

	/** Log it */
	private static final Log log = LogFactory.getLog(ManyToOneMapper.class);

	/**
	 * Generate the hb mapping for the given reference and annotations.
	 */
	public void process(PAnnotatedEReference paReference) {
		log.debug("Process many-to-one " + paReference);

		final List<JoinColumn> jcs = getJoinColumns(paReference);

		final EClass referedTo = paReference.getModelEReference()
				.getEReferenceType();
		final ManyToOne mto = paReference.getManyToOne();
		String targetName = mto.getTargetEntity();
		if (targetName == null) {
			targetName = getHbmContext().getEntityName(referedTo);
		}

		log.debug("Target " + targetName);

		JoinTable joinTable = null;
		PAnnotatedEReference aOpposite = null;
		if (paReference.getModelEReference().getEOpposite() != null) {
			aOpposite = paReference.getPaModel().getPAnnotated(
					paReference.getModelEReference().getEOpposite());
			if (aOpposite.getOneToMany() != null
					&& !aOpposite.getOneToMany().isList()
					&& aOpposite.getJoinTable() != null) {
				joinTable = aOpposite.getJoinTable();
			}
		}

		final Element currentElement;
		if (joinTable != null) {
			final boolean addInverse = aOpposite != null
					&& aOpposite.getOneToMany() != null
					&& aOpposite.getOneToMany().getMappedBy() == null;

			currentElement = getHbmContext().getCurrent().addElement("join")
					.addAttribute("table", joinTable.getName()).addAttribute(
							"inverse", Boolean.toString(addInverse))
					.addAttribute("optional",
							Boolean.toString(mto.isOptional()));

		} else {
			currentElement = getHbmContext().getCurrent();
		}

		if (joinTable != null) {
			final Element keyElement = currentElement.addElement("key");
			addKeyColumns((HbAnnotatedETypeElement) paReference, keyElement,
					joinTable.getInverseJoinColumns());
		}

		final Element associationElement = addManyToOne(currentElement,
				paReference, targetName, false);
		addAccessor(associationElement);

		if (!associationElement.getName().equals("any")) {
			addLazyProxy(associationElement, mto.getFetch(), paReference);
		}

		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;
		final boolean isProperty = hbReference.getHbType() != null;

		if (!isProperty && hbReference.getNaturalId() != null) {
			associationElement.addAttribute(HbMapperConstants.NATURAL_ID_ATTR,
					Boolean.toString(hbReference.getNaturalId().isMutable()));
		}

		if (!isProperty && joinTable != null) {
			addJoinColumns(paReference, associationElement, joinTable
					.getJoinColumns(), mto.isOptional()
					|| getHbmContext().isDoForceOptional(paReference)
					|| getHbmContext().isCurrentElementFeatureMap());
		}

		if (!isProperty) {
			addCascadesForSingle(associationElement, getCascades(hbReference
					.getHbCascade(), mto.getCascade()));
		}

		if (hbReference.getHbFetch() != null) {
			associationElement.addAttribute("fetch", hbReference.getHbFetch()
					.getValue().getName().toLowerCase());
		}

		final boolean nullable = getHbmContext().isDoForceOptional(paReference)
				|| mto.isOptional()
				|| getHbmContext().isCurrentElementFeatureMap();

		if (isEObject(targetName)) {
			final String erefName = paReference.getModelEReference().getName();
			addColumnsAndFormula(associationElement, paReference,
					getAnyTypeColumns(erefName, true), true, false);
			// foreign key is not added when the reference is to a generic
			// EObject
		} else if (!isProperty) {
			addForeignKeyAttribute(associationElement, paReference);
			addLazyProxy(associationElement, mto.getFetch(), paReference);

			if (joinTable == null) {
				addJoinColumns(paReference, associationElement, jcs,
						getHbmContext().isDoForceOptional(paReference)
								|| mto.isOptional()
								|| getHbmContext().isCurrentElementFeatureMap());

				associationElement.addAttribute("not-null", nullable ? "false"
						: "true");
			}
		} else if (isProperty) {
			associationElement.addAttribute("not-null", nullable ? "false"
					: "true");
		}

		// note that the reference must be required, nullable and unique columns
		// are not supported
		// by ms sql server
		// because ms sql server also sees null as a value
		if (paReference.getModelEReference().isContainment() && !nullable) {
			associationElement.addAttribute("unique", "true");
		}

		// MT: TODO; the characteristic of the other side should be checked (if
		// present), if the otherside is a onetoone
		// then this
		// should be set to true. But then this is then handled by a
		// bidirectional onetoone (I think).
		// if (joinColumns.isEmpty())
		// associationElement.addAttribute("unique", "true");
	}
}
