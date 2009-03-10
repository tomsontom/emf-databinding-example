/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: OneToOneMapper.java,v 1.35 2009/03/07 21:15:19 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.OneToOne;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps a {@link OneToOne} element to its {@link MappingContext}.
 * <p>
 * Assumes that the given {@link PAnnotatedEStructuralFeature} is a normal
 * OneToOne, i.e.
 * <ul>
 * <li>it is a {@link PAnnotatedEReference};
 * <li>it has a {@link OneToOne} annotation;
 * <li>each attribute on the {@link OneToOne} annotation is set possibly except
 * for {@link OneToOne#getMappedBy()};
 * <li>TODO requirements on JoinColumns/PrimaryKeyJoinColumn
 * </ul>
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class OneToOneMapper extends AbstractAssociationMapper implements
		ExtensionPoint {

	private static final Log log = LogFactory.getLog(OneToOneMapper.class);

	/** Process the one-to-one */
	public void process(PAnnotatedEReference paReference) {
		final PAnnotatedEReference opposite = getOtherSide(paReference);

		if (opposite != null) {
			// handle the case of a primary key one-to-one
			if (!paReference.getPrimaryKeyJoinColumns().isEmpty()
					|| (opposite != null && !opposite
							.getPrimaryKeyJoinColumns().isEmpty())) {
				createOneToOne(paReference);
			} else {
				// For a non-pk one-to-one, one side is mapped as oto, and the
				// other as mto.
				// the oto is the one with the mappedBy attribute
				if (paReference.getOneToOne().getMappedBy() != null) {
					createOneToOne(paReference);
				} else {
					createManyToOne(paReference);
				}
			}
		} else { // can this case occur? A non-bidirectional one-to-one?
			createManyToOne(paReference);
		}

		// if (opposite == null ||
		// paReference.getOneToOne().eIsSet(PannotationPackage.eINSTANCE.getOneToOne_MappedBy()))
		// {
		// if (!paReference.getPrimaryKeyJoinColumns().isEmpty() ||
		// (opposite != null && !opposite.getPrimaryKeyJoinColumns().isEmpty()))
		// {
		// createOneToOne(paReference);
		// } else if
		// (paReference.getOneToOne().eIsSet(PannotationPackage.eINSTANCE.getOneToOne_MappedBy()))
		// {
		// createOneToOne(paReference);
		// } else {
		// createManyToOne(paReference);
		// }
		// } else {
		// createOneToOne(paReference);
		// }
	}

	/** Create hibernate many-to-one mapping */
	private void createManyToOne(PAnnotatedEReference paReference) {
		log.debug("Generating many to one mapping for onetoone" + paReference);

		final OneToOne oto = paReference.getOneToOne();
		final EReference eref = paReference.getModelEReference();
		String specifiedName = oto.getTargetEntity();

		if (specifiedName == null) {
			specifiedName = getHbmContext().getEntityName(
					eref.getEReferenceType());
		}

		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;
		final Element associationElement = addManyToOne(getHbmContext()
				.getCurrent(), paReference, specifiedName, false);
		addAccessor(associationElement);

		addCascadesForSingle(associationElement, getCascades(hbReference
				.getHbCascade(), oto.getCascade()));

		final boolean isNullable = (oto.isOptional()
				|| getHbmContext().isDoForceOptional(paReference) || getHbmContext()
				.isCurrentElementFeatureMap());

		associationElement.addAttribute("not-null", (isNullable ? "false"
				: "true"));

		if (!associationElement.getName().equals("any")) {
			addLazyProxy(associationElement, oto.getFetch(), paReference);
		}

		if (hbReference.getHbFetch() != null) {
			associationElement.addAttribute("fetch", hbReference.getHbFetch()
					.getValue().getName().toLowerCase());
		}

		if (isEObject(specifiedName)) {
			addColumnsAndFormula(associationElement, paReference,
					getAnyTypeColumns(eref.getName(), true), true, false);
			// foreign key is not added when the reference is to a generic
			// EObject
		} else {
			addForeignKeyAttribute(associationElement, paReference);
			addLazyProxy(associationElement, oto.getFetch(), paReference);
			final List<JoinColumn> joinColumns = getJoinColumns(paReference);
			addJoinColumns(paReference, associationElement, joinColumns,
					isNullable);

			// apparently sql server does not like a unique constraint on a
			// nullable column
			// null values also seem to be seen as a unique value.
			if (!isNullable) {
				associationElement.addAttribute("unique", "true");
			}
		}
	}

	/** Create hibernate one-to-one mapping */
	private void createOneToOne(PAnnotatedEReference paReference) {
		if (log.isDebugEnabled()) {
			log
					.debug("Generating one to one bidirectional inverse mapping for "
							+ paReference);
		}

		final OneToOne oto = paReference.getOneToOne();
		String targetName = oto.getTargetEntity();
		if (targetName == null) {
			targetName = getHbmContext().getEntityName(
					paReference.getEReferenceType());
		}

		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;
		final EReference eref = paReference.getModelEReference();
		final EReference otherSide = eref.getEOpposite();
		final Element associationElement = addOneToOne(paReference,
				getHbmContext().getPropertyName(eref), targetName);
		addAccessor(associationElement);

		addForeignKeyAttribute(associationElement, paReference);
		addCascadesForSingle(associationElement, getCascades(hbReference
				.getHbCascade(), oto.getCascade()));
		addLazyProxy(associationElement, oto.getFetch(), paReference);

		if (hbReference.getHbFetch() != null) {
			associationElement.addAttribute("fetch", hbReference.getHbFetch()
					.getValue().getName().toLowerCase());
		}

		// add the other-side
		final boolean primaryKeyJoin = !paReference.getPrimaryKeyJoinColumns()
				.isEmpty()
				|| (otherSide != null && !getOtherSide(paReference)
						.getPrimaryKeyJoinColumns().isEmpty());

		if (!primaryKeyJoin && otherSide != null && oto.getMappedBy() != null) {
			associationElement.addAttribute("property-ref", getHbmContext()
					.getPropertyName(otherSide));
		}

		// apparently is always allowed for a one-to-one, if it is bidirectional
		// if (paReference.getPrimaryKeyJoinColumns().size() > 0) {

		// place constrained when:
		// 1) this side has a primary key join column annotation
		boolean addConstrained = !paReference.getPrimaryKeyJoinColumns()
				.isEmpty();
		if (!addConstrained && otherSide == null) {
			// 2) there is no other side and it is mandatory see here:
			// http://www.hibernate.org/162.html
			addConstrained = eref.isRequired();
		} else if (!addConstrained && otherSide != null) {
			// 3) the most difficult one, the other side must be a many-to-one
			// this is the case when it does not have a pk join column and
			// mappedby is
			// null, or when it has a many-to-one annotation.
			final PAnnotatedEReference aOpposite = paReference.getPaModel()
					.getPAnnotated(otherSide);
			addConstrained = aOpposite.getManyToOne() != null
					&& eref.isRequired();
			if (!addConstrained) {
				addConstrained = eref.isRequired()
						&& aOpposite.getPrimaryKeyJoinColumns().isEmpty()
						&& aOpposite.getOneToOne() != null
						&& aOpposite.getOneToOne().getMappedBy() == null;
			}
		}

		if (addConstrained) {
			associationElement.addAttribute("constrained", "true");
		}
		// }
	}
}
