/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: OneToManyMapper.java,v 1.40 2009/03/07 21:15:19 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.OneToMany;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.simpledom.Element;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Maps a OneToMany element to its mapping Context.
 * <p>
 * Assumes that the given {@link PAnnotatedEStructuralFeature} is normal, i.e.
 * <ul>
 * <li>it is a {@link PAnnotatedEReference};
 * <li>it has a {@link OneToMany} annotation;
 * <li>TODO
 * </ul>
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class OneToManyMapper extends AbstractAssociationMapper implements
		ExtensionPoint {

	/** The log */
	private static final Log log = LogFactory.getLog(OneToManyMapper.class);

	/** Process the paReference */
	public void process(PAnnotatedEReference paReference) {
		// TODO assuming it coincides with specified targetEntity, correct?
		// Guaranteed by validation?
		if (getOtherSide(paReference) == null) {
			processOtMUni(paReference);
			// mappedBy is not set anymore because it controls inverse
			// see bugzilla 242479
			// } else if
			// (!paReference.getOneToMany().eIsSet(PannotationPackage.eINSTANCE.getOneToMany_MappedBy
			// ()
			// )) {
			// throw new MappingException(
			// "The many side of a bidirectional one to many association must be the owning side",
			// paReference);
		} else {
			// MT: TODO add check, in this case unique should always true
			// because an child can only occur once within
			// the collection because
			// of the bidirectional behavior.
			processOtMBidiInverse(paReference);
		}
	}

	/**
	 * joinTable.getInverseJoinColumns must be null TODO choose appropriate
	 * mapping according to the presence of JoinTable
	 */
	private void processOtMUni(PAnnotatedEReference paReference) {
		if (log.isDebugEnabled()) {
			log.debug("Generating one to many unidirectional mapping for "
					+ paReference);
		}

		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;
		final EReference eref = hbReference.getModelEReference();
		final EClass refType = eref.getEReferenceType();
		final PAnnotatedEClass referedToAClass = hbReference
				.getAReferenceType();
		boolean isMap = StoreUtil.isMap(eref)
				&& getHbmContext().isMapEMapAsTrueMap();

		// TODO add isUnique on interface
		// TODO request EMF team to deal correctly with unique attribute on
		// EReferences
		final Element collElement = addCollectionElement(paReference);
		addAccessor(collElement);

		if (hbReference.getImmutable() != null) {
			collElement.addAttribute("mutable", "false");
		}

		if (((HbAnnotatedEReference) paReference).getHbCache() != null) {
			addCacheElement(collElement, ((HbAnnotatedEReference) paReference)
					.getHbCache());
		}

		// .getAnnotatedElement().getName(),
		// paReference.getIndexed() != null &&
		// paReference.getIndexed().isValue());
		final Element keyElement = collElement.addElement("key");
		handleOndelete(keyElement, hbReference.getHbOnDelete());

		// TODO: throw error if both jointable and joincolumns have been set
		final List<JoinColumn> jcs = paReference.getJoinColumns() == null ? new ArrayList<JoinColumn>()
				: paReference.getJoinColumns();
		final JoinTable jt = paReference.getJoinTable();
		if (jt != null) {
			addJoinTable(hbReference, collElement, keyElement, jt);
		} else {
			addKeyColumns(hbReference, keyElement, jcs);
		}

		final OneToMany otm = hbReference.getOneToMany();
		if (hbReference.getHbIdBag() != null) {
			log.debug("Setting indexed=false because is an idbag");
			otm.setIndexed(false);
		}

		// a special case see here:
		// http://forum.hibernate.org/viewtopic.php?p=2383090
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=242479
		if (!otm.isIndexed() && hbReference.getHbIdBag() == null
				&& otm.getMappedBy() != null) {
			collElement.addAttribute("inverse", "true");
		}

		boolean isMapValueIsEntity = false;
		if (hbReference.getHbIdBag() == null && otm.isList()) {
			// now we check if it is a list or a map
			final EClass eclass = eref.getEReferenceType();
			if (hbReference.getMapKey() != null
					|| hbReference.getHbMapKey() != null
					|| hbReference.getMapKeyManyToMany() != null) {
				isMapValueIsEntity = (eclass.getEStructuralFeature("value") instanceof EReference);
				addMapKey(collElement, paReference);
			} else if (isMap) {
				isMapValueIsEntity = (eclass.getEStructuralFeature("value") instanceof EReference);
				addMapKey(collElement, hbReference);
			} else if (collElement.getName().compareTo("list") == 0) { // otm.isIndexed()
				addListIndex(collElement, paReference);
			}
		}

		final CollectionOfElements coe = hbReference
				.getHbCollectionOfElements();

		// TODO OneToMany and CollectionOfElements are mutually exclusive.
		// Should throw exception if both there?
		addFetchType(collElement, (null != coe) ? coe.getFetch() : otm
				.getFetch());
		addCascadesForMany(collElement, getCascades(hbReference.getHbCascade(),
				otm.getCascade()));
		List<JoinColumn> inverseJoinColumns = jt != null
				&& jt.getInverseJoinColumns() != null ? jt
				.getInverseJoinColumns() : new ArrayList<JoinColumn>();

		String targetName = null;

		targetName = otm.getTargetEntity();
		// final boolean isEasyEMFGenerated =
		// getHbmContext().isEasyEMFGenerated(refType);
		if (targetName == null) {
			targetName = getHbmContext().getEntityName(refType);
		}

		// MT a manytomany is only required in case of unique=false, note that
		// the ejb3 spec states that for uni otm
		// always a jointable should be
		// used (as a default). This is however to heavy for cases were a
		// jointable is not required at all. Also
		// hibernate supports uni otm without join table.
		if (hbReference.getEmbedded() != null) {
			addCompositeElement(collElement, hbReference);
		} else if (isMap && !isMapValueIsEntity) {
			final EClass eclass = eref.getEReferenceType();
			final EAttribute valueEAttribute = (EAttribute) eclass
					.getEStructuralFeature("value");
			final PAnnotatedEAttribute valuePAttribute = paReference
					.getPaModel().getPAnnotated(valueEAttribute);
			addElementElement(collElement, valuePAttribute,
					getColumns(valuePAttribute), otm.getTargetEntity());
		} else if (!isEObject(targetName) && jt != null) {
			// A m2m forces a join table, note that isunique does not completely
			// follow the semantics of emf, unique on
			// an otm means that an element can only occur once in the table, if
			// unique is false then you in effect have
			// a
			// mtm relation
			// because an item can occur twice or more in the list.
			// To force a jointable on a real otm a jointable annotation should
			// be specified.
			final Element mtm = addManyToMany(hbReference, referedToAClass,
					collElement, targetName, inverseJoinColumns, otm.isUnique());
			addForeignKeyAttribute(mtm, paReference);

			if (hbReference.getNotFound() != null) {
				mtm.addAttribute("not-found", hbReference.getNotFound()
						.getAction().getName().toLowerCase());
			}
		} else {
			final Element otmElement = addOneToMany(paReference,
					referedToAClass, collElement, eref.getName(), targetName);
			addForeignKeyAttribute(keyElement, paReference);

			if (hbReference.getNotFound() != null) {
				otmElement.addAttribute("not-found", hbReference.getNotFound()
						.getAction().getName().toLowerCase());
			}
		}

		mapFilter(collElement, ((HbAnnotatedETypeElement) paReference)
				.getFilter());
	}

	/**
	 * Process bidirectional one-to-many
	 */
	private void processOtMBidiInverse(PAnnotatedEReference paReference) {
		if (log.isDebugEnabled()) {
			log
					.debug("Generating one to many bidirectional inverse mapping for "
							+ paReference);
		}

		// final Element collElement =
		// addCollectionElement(paReference.getAnnotatedElement().getName(),
		// paReference.isIndexed());
		final Element collElement = addCollectionElement(paReference);
		addAccessor(collElement);
		final EReference eref = paReference.getModelEReference();
		final HbAnnotatedEReference hbReference = (HbAnnotatedEReference) paReference;
		final PAnnotatedEClass referedToAClass = hbReference
				.getAReferenceType();

		if (hbReference.getHbCache() != null) {
			addCacheElement(collElement, hbReference.getHbCache());
		}

		if (hbReference.getImmutable() != null) {
			collElement.addAttribute("mutable", "false");
		}

		// MT: note inverse does not work correctly with hibernate for indexed
		// collections, see 7.3.3 of the hibernate
		// manual 3.1.1
		final OneToMany otm = paReference.getOneToMany();
		if (!otm.isIndexed() && otm.getMappedBy() != null
				&& hbReference.getHbIdBag() == null) {
			collElement.addAttribute("inverse", "true");
		} else {
			log.debug("Inverse is not set on purpose for indexed collections");
		}

		final Element keyElement = collElement.addElement("key");
		handleOndelete(keyElement, ((HbAnnotatedEReference) paReference)
				.getHbOnDelete());

		// MT: added handling of join info
		final List<JoinColumn> jcs = paReference.getJoinColumns() == null ? new ArrayList<JoinColumn>()
				: paReference.getJoinColumns();
		final JoinTable jt = paReference.getJoinTable();
		if (jt != null) {
			addJoinTable(hbReference, collElement, keyElement, jt);
		} else {
			addKeyColumns(hbReference, keyElement, jcs);
		}

		addFetchType(collElement, otm.getFetch());
		addCascadesForMany(collElement, getCascades(hbReference.getHbCascade(),
				otm.getCascade()));

		boolean isMap = StoreUtil.isMap(eref)
				&& getHbmContext().isMapEMapAsTrueMap();
		boolean isMapValueIsEntity = false;
		if (hbReference.getHbIdBag() == null && otm.isList()) {
			// now we check if it is a list or a map
			if (hbReference.getMapKey() != null
					|| hbReference.getHbMapKey() != null
					|| hbReference.getMapKeyManyToMany() != null) {
				addMapKey(collElement, paReference);
			} else if (isMap) {
				final EClass eclass = eref.getEReferenceType();
				isMapValueIsEntity = (eclass.getEStructuralFeature("value") instanceof EReference);
				addMapKey(collElement, hbReference);
			} else if (collElement.getName().compareTo("list") == 0) { // otm.isIndexed()
				addListIndex(collElement, paReference);
			}
		}

		String targetName = otm.getTargetEntity();
		if (targetName == null) {
			targetName = getHbmContext()
					.getEntityName(eref.getEReferenceType());
		}

		if (paReference.getEmbedded() != null) {
			addCompositeElement(collElement, paReference);
		} else if (isMap && !isMapValueIsEntity) {
			final EClass eclass = eref.getEReferenceType();
			final EAttribute valueEAttribute = (EAttribute) eclass
					.getEStructuralFeature("value");
			final PAnnotatedEAttribute valuePAttribute = paReference
					.getPaModel().getPAnnotated(valueEAttribute);
			addElementElement(collElement, valuePAttribute,
					getColumns(valuePAttribute), otm.getTargetEntity());
		} else if (jt != null) {
			final List<JoinColumn> inverseJoinColumns = jt != null
					&& jt.getInverseJoinColumns() != null ? jt
					.getInverseJoinColumns() : new ArrayList<JoinColumn>();

			final Element mtm = addManyToMany(hbReference, referedToAClass,
					collElement, targetName, inverseJoinColumns, otm.isUnique());
			addForeignKeyAttribute(mtm, paReference);
			if (hbReference.getNotFound() != null) {
				mtm.addAttribute("not-found", hbReference.getNotFound()
						.getAction().getName().toLowerCase());
			}

		} else {
			final Element otmElement = addOneToMany(paReference,
					referedToAClass, collElement, eref.getName(), targetName);
			addForeignKeyAttribute(keyElement, paReference);
			if (hbReference.getNotFound() != null) {
				otmElement.addAttribute("not-found", hbReference.getNotFound()
						.getAction().getName().toLowerCase());
			}
		}

		mapFilter(collElement, ((HbAnnotatedETypeElement) paReference)
				.getFilter());
	}

	/**
	 * Creates a onetomany element.
	 * 
	 * @param collElement
	 * @param targetEntity
	 */
	protected Element addOneToMany(PAnnotatedEReference paReference,
			PAnnotatedEClass referedToAClass, Element collElement,
			String featureName, String targetEntity) {
		if (isEObject(targetEntity)) { // anytype
			final Element any = collElement.addElement("many-to-any")
					.addAttribute("id-type", "long");
			addColumnsAndFormula(any, paReference, getAnyTypeColumns(
					featureName, false), false, false);
			return any;
		} else {
			String tag = "one-to-many";
			if (((HbAnnotatedEReference) paReference).getHbIdBag() != null) {
				tag = "many-to-many";
			}

			if (referedToAClass.isOnlyMapAsEntity()
					|| !getHbmContext().forceUseOfInstance(referedToAClass)) {
				return collElement.addElement(tag).addAttribute("entity-name",
						targetEntity);
			} else {
				return collElement.addElement(tag).addAttribute(
						"class",
						getHbmContext().getInstanceClassName(
								referedToAClass.getModelEClass()));
			}
		}
	}

	/**
	 * Creates a many-to-many to handle the unidirectional manytomany. A
	 * unidirectional manytomany is now specified using the one to many
	 * annotation while its implementation has a join table.
	 */
	private Element addManyToMany(HbAnnotatedEReference hbReference,
			PAnnotatedEClass referedToAClass, Element collElement,
			String targetEntity, List<JoinColumn> invJoinColumns, boolean unique) {
		final Element manyToMany;
		if (referedToAClass.isOnlyMapAsEntity()
				|| !getHbmContext().forceUseOfInstance(referedToAClass)) {
			manyToMany = collElement.addElement("many-to-many").addAttribute(
					"entity-name", targetEntity).addAttribute("unique",
					unique ? "true" : "false");
		} else {
			manyToMany = collElement.addElement("many-to-many").addAttribute(
					"class",
					getHbmContext().getInstanceClassName(
							referedToAClass.getModelEClass())).addAttribute(
					"unique", unique ? "true" : "false");
		}
		addKeyColumns(hbReference, manyToMany, invJoinColumns); // pass null for
		// jointable

		return manyToMany;
	}

	/** Add composite-element */
	private Element addCompositeElement(Element collElement,
			PAnnotatedEReference paReference) {
		// TODO: handle nested components: nested-composite-element
		final Element componentElement = collElement.addElement(
				"composite-element").addAttribute(
				"class",
				getHbmContext().getInstanceClassName(
						paReference.getEReferenceType()));
		getHbmContext().setCurrent(componentElement);

		try {
			// process the features of the target
			final PAnnotatedEClass componentAClass = paReference
					.getAReferenceType();
			getHbmContext().processFeatures(
					componentAClass.getPaEStructuralFeatures());
		} finally {
			getHbmContext().setCurrent(collElement.getParent());
		}
		return componentElement;
	}
}
