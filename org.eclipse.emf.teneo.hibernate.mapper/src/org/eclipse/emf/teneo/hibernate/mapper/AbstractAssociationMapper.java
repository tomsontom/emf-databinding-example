/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * AbstractAssociationMapper.java,v 1.17 2007/04/17 15:49:50 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.annotations.pannotation.JoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.JoinTable;
import org.eclipse.emf.teneo.annotations.pannotation.MapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbCascadeType;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany;
import org.eclipse.emf.teneo.hibernate.hbannotation.Parameter;
import org.eclipse.emf.teneo.hibernate.hbannotation.Type;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEReference;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement;
import org.eclipse.emf.teneo.simpledom.Element;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Class contains different convenience methods which are of use for association
 * mapping.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public abstract class AbstractAssociationMapper extends AbstractMapper {

	/** Logger */
	private static final Log log = LogFactory
			.getLog(AbstractAssociationMapper.class);

	/**
	 * @return
	 */
	protected Element addOneToOne(PAnnotatedEReference aReference,
			String assocName, String targetEntity) {
		final PAnnotatedEClass referedToAClass = aReference.getAReferenceType();
		final Element element;
		if (referedToAClass.isOnlyMapAsEntity()
				|| !getHbmContext().forceUseOfInstance(referedToAClass)) {
			element = getHbmContext().getCurrent().addElement("one-to-one")
					.addAttribute("name", assocName).addAttribute(
							"entity-name", targetEntity);
		} else {
			element = getHbmContext().getCurrent().addElement("one-to-one")
					.addAttribute("name", assocName).addAttribute(
							"class",
							getHbmContext().getInstanceClassName(
									referedToAClass.getModelEClass()));
		}

		if (aReference instanceof HbAnnotatedEReference) {
			final HbAnnotatedEReference hae = (HbAnnotatedEReference) aReference;
			if (hae.getHbFetch() != null) {
				element.addAttribute("fetch", hae.getHbFetch().getValue()
						.getName().toLowerCase());
			}
		}
		return element;
	}

	// translates jpa CascadeType to HbCascadeType
	protected List<HbCascadeType> getCascades(Cascade cascade,
			List<CascadeType> cascades) {
		if (cascade != null) {
			return cascade.getValue();
		}
		return convertCascade(cascades);
	}

	protected List<HbCascadeType> convertCascade(List<CascadeType> cascades) {
		final List<HbCascadeType> res = new ArrayList<HbCascadeType>();
		for (CascadeType ct : cascades) {
			if (ct == CascadeType.ALL) {
				res.add(HbCascadeType.ALL);
			} else if (ct == CascadeType.MERGE) {
				res.add(HbCascadeType.MERGE);
			} else if (ct == CascadeType.PERSIST) {
				res.add(HbCascadeType.PERSIST);
				res.add(HbCascadeType.SAVE_UPDATE);
				res.add(HbCascadeType.LOCK);
			} else if (ct == CascadeType.REFRESH) {
				res.add(HbCascadeType.REFRESH);
			} else if (ct == CascadeType.REMOVE) {
				res.add(HbCascadeType.REMOVE);
			} else if (ct == CascadeType.NONE) {
				return new ArrayList<HbCascadeType>();
			}
		}
		return res;
	}

	/** Adds a manytoone tag to the current element of the hbmcontext */
	protected Element addManyToOne(Element currentParent,
			PAnnotatedEReference aReference, String referedTo,
			boolean isPartOfKey) {
		final String assocName = getHbmContext().getPropertyName(
				aReference.getModelEReference());
		log.debug("addManyToOne " + assocName + "/" + referedTo);

		if (isEObject(referedTo)) {
			return getHbmContext().getCurrent().addElement("any").addAttribute(
					"name", assocName).addAttribute("id-type", "long");
		}

		final String tagName;
		if (((HbAnnotatedEReference) aReference).getHbType() != null) {
			tagName = "property";
			final Element element = currentParent.addElement(tagName)
					.addAttribute("name", assocName);
			final Type hbType = ((HbAnnotatedEReference) aReference)
					.getHbType();
			final List<Parameter> params = hbType.getParameters();
			if (params == null || params.isEmpty()) {
				element.addAttribute("type", hbType.getType());
			} else {
				final Element typeElement = element.addElement("type")
						.addAttribute("name", hbType.getType());
				for (Parameter param : params) {
					typeElement.addElement("param").addAttribute("name",
							param.getName()).addText(param.getValue());
				}
			}
			return element;
		} else if (isPartOfKey) {
			tagName = "key-many-to-one";
		} else {
			tagName = "many-to-one";
		}

		final EClass referedToEClass = aReference.getModelEReference()
				.getEReferenceType();
		final PAnnotatedEClass referedToAClass = aReference.getPaModel()
				.getPAnnotated(referedToEClass);
		final Element element;
		if (referedToAClass.isOnlyMapAsEntity()
				|| !getHbmContext().forceUseOfInstance(referedToAClass)) {
			element = currentParent.addElement(tagName).addAttribute("name",
					assocName).addAttribute("entity-name", referedTo);
		} else {
			element = currentParent.addElement(tagName).addAttribute("name",
					assocName).addAttribute("class",
					getHbmContext().getInstanceClassName(referedToEClass));
		}

		if (aReference instanceof HbAnnotatedEReference) {
			final HbAnnotatedEReference hae = (HbAnnotatedEReference) aReference;
			if (hae.getHbFetch() != null) {
				element.addAttribute("fetch", hae.getHbFetch().getValue()
						.getName().toLowerCase());
			}
		}

		return element;
	}

	/**
	 * Adds joincolumns to the associationElement, sets the insert and update
	 * attributes of the associationElement on the basis of the
	 * insertable/updatable attributes of the joinColumns. Note that the
	 * joinColumns list can be empty. forcenullable is set to true when a
	 * feature map entry is being processed.
	 */
	protected void addJoinColumns(PAnnotatedEReference per,
			Element associationElement, List<JoinColumn> joinColumns,
			boolean forceNullable) {
		log.debug("addJoinColumns " + associationElement.getName()
				+ "/ no of joincolumns" + joinColumns.size());

		// assumption is that if one column is not insertable then the
		// association is
		// not insertable, same for updatable
		boolean insertable = true;
		boolean updatable = true;

		for (JoinColumn joinColumn : joinColumns) {
			log.debug("JoinColumn " + joinColumn.getName());

			Element columnElement = associationElement.addElement("column")
					.addAttribute(
							"not-null",
							(joinColumn.isNullable() && joinColumn
									.isSetNullable())
									|| forceNullable ? "false" : "true")
					.addAttribute("unique",
							joinColumn.isUnique() ? "true" : "false");
			if (joinColumn.getName() != null) {

				columnElement.addAttribute("name", getHbmContext().trunc(
						joinColumn.getName()));
				final String uc = getHbmContext().getUniqueConstraintKey(
						joinColumn.getName());
				if (uc != null) {
					columnElement.addAttribute("unique-key", uc);
				}
			}

			final Index index = ((HbAnnotatedETypeElement) per).getHbIndex();
			if (index != null) {
				columnElement.addAttribute("index", index.getName());
			}

			// keep track if all joinColumns are insertable/updatable for in
			// that case the
			// associationElement is also insertable/updatable or not
			insertable &= joinColumn.isInsertable();
			updatable &= joinColumn.isUpdatable();

			// disabled this because not-null is specified as optional on the
			// many-to-one tag
			// also unique is more difficult
			// associationElement.addAttribute("not-null",
			// !joinColumn.isNullable() ? "true" : "false");
			// associationElement.addAttribute("unique", joinColumn.isUnique() ?
			// "true" : "false");
			// if
			// (joinColumn.eIsSet(PannotationPackage.eINSTANCE.getJoinColumn_ReferencedColumnName()))
			// TODO is this foreign key ?
			// MT: see the property-ref in hibernate, is used when the reference
			// is not on the primary key
			// of the target table but on some other column

			// MT: TODO add check on not insertable/updatable which is strange
			// for a joincolumn, this check
			// is present in onetomany mapper

			// --- JJH
			addCommentElement(per.getModelEReference(), columnElement);
			// --- JJH

		}
		// ugly but effective
		if (associationElement.getName().compareTo("map-key-many-to-many") != 0
				&& associationElement.getName().compareTo("join") != 0
				&& associationElement.getName().compareTo("key-many-to-one") != 0) {
			associationElement.addAttribute("insert", Boolean
					.toString(insertable));
			associationElement.addAttribute("update", Boolean
					.toString(updatable));
		}
	}

	/**
	 * Creates cascades for onetoone/manytoone, they differ from many relations
	 * because no delete-orphan is supported.
	 * 
	 * @param associationElement
	 *            : the element to which the cascades are added.
	 * @param cascade
	 *            : list of cascade annotation types
	 */
	protected void addCascadesForSingle(Element associationElement,
			List<HbCascadeType> cascades) {
		addCascades(associationElement, cascades, false);
	}

	/**
	 * Adds a foreign key attribute to the collection element, if the aFeature
	 * has a foreign key
	 */
	protected void addForeignKeyAttribute(Element manyElement,
			PAnnotatedEStructuralFeature aFeature) {
		if (aFeature.getForeignKey() != null) {
			manyElement.addAttribute("foreign-key", aFeature.getForeignKey()
					.getName());
		}
	}

	/**
	 * Creates cascades for onetomany, it differs from single relations because
	 * delete-orphan is supported when cascade=all
	 * 
	 * @param associationElement
	 *            : the element to which the cascades are added.
	 * @param cascade
	 *            : list of cascade annotation types
	 */
	protected void addCascadesForMany(Element associationElement,
			List<HbCascadeType> cascades) {
		addCascades(associationElement, cascades, true);
	}

	/**
	 * Creates cascades for onetoone/manytoone, they differ from many relations
	 * because no delete-orphan is supported.
	 * 
	 * @param associationElement
	 *            : the element to which the cascades are added.
	 * @param cascade
	 *            : list of cascade annotation types
	 * @param addDeleteOrphan
	 *            : if true then delete-orphan is added in case of cascade all
	 */
	protected void addCascades(Element associationElement,
			List<HbCascadeType> cascades, boolean addDeleteOrphan) {
		if (!cascades.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (HbCascadeType cascade : cascades) {
				if (cascade == HbCascadeType.ALL) {
					sb.append("all,"); // assuming all appears alone
					if (addDeleteOrphan) {
						sb.append("delete-orphan,");
					}
					break;
				} else if (cascade == HbCascadeType.PERSIST) {
					sb.append("persist,");
				} else if (cascade == HbCascadeType.MERGE) {
					sb.append("merge,");
				} else if (cascade == HbCascadeType.REFRESH) {
					sb.append("refresh,");
				} else if (cascade == HbCascadeType.REMOVE) {
					sb.append("delete,");
				} else if (cascade == HbCascadeType.DELETE) {
					sb.append("delete,");
				} else if (cascade == HbCascadeType.DELETE_ORPHAN) {
					sb.append("delete-orphan,");
				} else if (cascade == HbCascadeType.EVICT) {
					sb.append("evict,");
				} else if (cascade == HbCascadeType.LOCK) {
					sb.append("lock,");
				} else if (cascade == HbCascadeType.REPLICATE) {
					sb.append("replicate,");
				} else if (cascade == HbCascadeType.SAVE_UPDATE) {
					sb.append("save-update,");
				} else {
					throw new MappingException("Cascade " + cascade.getName()
							+ " not supported");
				}
			}
			associationElement.addAttribute("cascade", sb.substring(0, sb
					.length() - 1));
		}
	}

	/**
	 * Sets the lazy attribute of the associationElement based on the fetchtype.
	 */
	protected void addFetchType(Element associationElement, FetchType fetch) {
		if (fetch == null) {
			return;
		}
		// TODO: when proxies are supported the below should be changed!
		if (FetchType.EXTRA.equals(fetch)) {
			associationElement.addAttribute("lazy", "extra");
		} else {
			associationElement.addAttribute("lazy", FetchType.LAZY
					.equals(fetch) ? "true" : "false");
		}
	}

	protected void addLazyProxy(Element element, FetchType fetch,
			PAnnotatedEReference paReference) {
		final HbAnnotatedEClass haClass = (HbAnnotatedEClass) paReference
				.getAReferenceType();

		boolean lazyFetch = fetch == null || fetch == FetchType.LAZY;
		boolean doProxy = lazyFetch
				&& (haClass.getHbProxy() != null && haClass.getHbProxy()
						.isLazy());
		if (doProxy && lazyFetch) {
			element.addAttribute("lazy", "proxy");
		} else {
			element.addAttribute("lazy", "false");
		}
	}

	/**
	 * Adds a listindex element with the column name set to the give collection
	 * element.
	 */
	protected void addListIndex(Element collElement,
			PAnnotatedEStructuralFeature aFeature) {
		// TODO use column name generator
		String name = getIndexColumnName(aFeature);

		log.debug("Add list index " + name + " to "
				+ aFeature.getModelEStructuralFeature().getName());

		collElement.addElement("list-index").addAttribute("column",
				getHbmContext().trunc(name));
	}

	protected String getIndexColumnName(PAnnotatedEStructuralFeature aFeature) {
		return (aFeature.getPaEClass().getModelEClass().getName() + "_"
				+ aFeature.getModelEStructuralFeature().getName() + "_IDX")
				.toUpperCase();
	}

	/**
	 * Adds a map-key element with the column name set to the give selected
	 * column element.
	 */
	// bugzilla 238515
	// protected void addMapKey(Element collElement,
	// PAnnotatedEStructuralFeature aFeature, MapKey
	// mapKey) {
	//
	// log.debug("Add map key " + mapKey.getName() + " to " +
	// aFeature.getModelEStructuralFeature().getName());
	//
	// // now, we add the column type. this is a required field
	// final EStructuralFeature keyFeature =
	// ((EReference)
	// aFeature.getModelElement()).getEReferenceType().getEStructuralFeature("key");
	// if (keyFeature instanceof EReference) {
	// final PAnnotatedEClass referedAClass =
	// aFeature.getPaModel().getPAnnotated(((EReference)
	// keyFeature).getEReferenceType());
	// if (referedAClass.isOnlyMapAsEntity() ||
	// !getHbmContext().forceUseOfInstance(referedAClass)) {
	// final String entityName = hbmContext.getEntityName(((EReference)
	// keyFeature).getEReferenceType());
	// collElement.addElement("map-key-many-to-many").addAttribute("entity-name",
	// entityName);
	// } else {
	// collElement.addElement("map-key-many-to-many").addAttribute("class",
	// getHbmContext().getInstanceClassName(referedAClass.getModelEClass()));
	// }
	// } else {
	// final PAnnotatedEAttribute paAttribute =
	// (PAnnotatedEAttribute) aFeature.getPaModel().getPAnnotated(keyFeature);
	// final Element mapKeyElement =
	// collElement.addElement("map-key").addAttribute("column",
	// getHbmContext().trunc(mapKey.getName()));
	// setType(paAttribute, mapKeyElement);
	// }
	// // "type", attr.getEType().getInstanceClassName());
	// }
	/**
	 * Add a mapkey taking into account if the key is an entity or a simple type
	 */
	protected void addMapKey(Element collElement, PAnnotatedEReference aref) {
		final EReference eref = aref.getModelEReference();
		final HbAnnotatedEReference hbRef = (HbAnnotatedEReference) aref;
		final EStructuralFeature keyFeature = eref.getEReferenceType()
				.getEStructuralFeature("key");

		if (hbRef.getHbMapKey() != null && hbRef.getMapKey() != null) {
			log
					.warn("The EReference "
							+ aref.getModelElement().getName()
							+ " has both a javax.persistence.MapKey as well as a hibernate MapKey annotation this is not correct, only one of the two should be used.");
		}

		if (hbRef.getHbMapKey() != null) {
			final org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey mapKey = hbRef
					.getHbMapKey();
			final PAnnotatedEAttribute paAttribute = (PAnnotatedEAttribute) aref
					.getPaModel().getPAnnotated(keyFeature);
			final Element mapKeyElement = collElement.addElement("map-key");
			if (mapKey.getColumns() != null && mapKey.getColumns().size() > 0) {
				addColumnsAndFormula(mapKeyElement, aref, mapKey.getColumns(),
						false, false, false, false);
			}
			setType(paAttribute, mapKeyElement);
		} else if (hbRef.getMapKey() != null) {
			final MapKey mapKey = hbRef.getMapKey();
			final PAnnotatedEAttribute paAttribute = (PAnnotatedEAttribute) aref
					.getPaModel().getPAnnotated(keyFeature);
			final Element mapKeyElement = collElement.addElement("map-key")
					.addAttribute("column",
							getHbmContext().trunc(mapKey.getName()));
			setType(paAttribute, mapKeyElement);
		} else if (hbRef.getMapKeyManyToMany() != null) {
			final MapKeyManyToMany mkm = hbRef.getMapKeyManyToMany();
			final PAnnotatedEClass referedAClass = aref.getPaModel()
					.getPAnnotated(
							((EReference) keyFeature).getEReferenceType());
			final Element mkmElement = collElement
					.addElement("map-key-many-to-many");
			if (referedAClass.isOnlyMapAsEntity()
					|| !getHbmContext().forceUseOfInstance(referedAClass)) {
				final String entityName = mkm.getTargetEntity() != null ? mkm
						.getTargetEntity() : hbmContext
						.getEntityName(((EReference) keyFeature)
								.getEReferenceType());
				mkmElement.addAttribute("entity-name", entityName);
			} else {
				mkmElement.addAttribute("class", getHbmContext()
						.getInstanceClassName(referedAClass.getModelEClass()));
			}
			if (mkm.getJoinColumns() != null && mkm.getJoinColumns().size() > 0) {
				addJoinColumns(hbRef, mkmElement, mkm.getJoinColumns(), false);
			}
		} else if (keyFeature instanceof EReference) {
			final PAnnotatedEClass referedAClass = aref.getPaModel()
					.getPAnnotated(
							((EReference) keyFeature).getEReferenceType());
			if (referedAClass.isOnlyMapAsEntity()
					|| !getHbmContext().forceUseOfInstance(referedAClass)) {
				final String entityName = hbmContext
						.getEntityName(((EReference) keyFeature)
								.getEReferenceType());
				collElement.addElement("map-key-many-to-many").addAttribute(
						"entity-name", entityName);
			} else {
				collElement.addElement("map-key-many-to-many").addAttribute(
						"class",
						getHbmContext().getInstanceClassName(
								referedAClass.getModelEClass()));
			}
		} else {
			// final String type =
			// hbType(aref.getPaModel().getPAnnotated((EAttribute) feature));
			final Element mapKey = collElement.addElement("map-key"); // .addAttribute("type",
			// type);
			final PAnnotatedEAttribute paAttribute = aref.getPaModel()
					.getPAnnotated((EAttribute) keyFeature);
			setType(paAttribute, mapKey);
		}
	}

	/**
	 * @return a newly added hibernate for given collection
	 * @deprecated use addCollectionElement(PAnnotatedEStructuralFeature)
	 *             instead. protected Element addCollectionElement(String name,
	 *             boolean isIndexed) { return
	 *             getHbmContext().getCurrent().addElement(isIndexed ? "list" :
	 *             "bag").addAttribute("name", name); // .addAttribute("access",
	 *             "org.eclipse.emf.teneo.hibernate.mapping.elist.EListPropertyAccessor"
	 *             ); }
	 */

	/**
	 * Creates a Hibernate collection element:
	 * <ul>
	 * <li>"&lt;list&gt;" if the collection is indexed.
	 * <li>"&lt;bag&gt;" if the collection is not indexed.
	 * <li>"&lt;idbag&gt;" if the collection is not indexed and has an IdBag
	 * annotation.
	 * </ul>
	 * 
	 * @param hbFeature
	 *            The structural feature for which to create collection.
	 * @return The collection element.
	 */
	protected Element addCollectionElement(
			PAnnotatedEStructuralFeature paFeature) {
		final Element collectionElement;
		HbAnnotatedETypeElement hbFeature = (HbAnnotatedETypeElement) paFeature;
		final IdBag idBag = hbFeature.getHbIdBag();

		final EStructuralFeature estruct = paFeature
				.getModelEStructuralFeature();
		final boolean isArray = estruct instanceof EAttribute
				&& estruct.getEType().getInstanceClass() != null
				&& estruct.getEType().getInstanceClass().isArray();
		final boolean isMap = StoreUtil.isMap(estruct)
				&& getHbmContext().isMapEMapAsTrueMap();

		// disabled following check because it also failed for many eattribute
		// which even with a onetomany
		// do not create a onetomany tag
		// if (paFeature.getOneToMany() != null && paFeature.getJoinTable() ==
		// null && idBag != null) {
		// throw new MappingException("Cannot use one-to-many attribute mapping
		// without jointable in combination with
		// IdBag.");
		// }
		final boolean hasOrderBy = paFeature instanceof PAnnotatedEReference
				&& ((PAnnotatedEReference) paFeature).getOrderBy() != null;
		final boolean hasWhereClause = paFeature instanceof PAnnotatedEReference
				&& ((HbAnnotatedEReference) paFeature).getHbWhere() != null;

		if (isArray) { // array type
			collectionElement = getHbmContext().getCurrent()
					.addElement("array");
		} else if (isMap) {
			collectionElement = getHbmContext().getCurrent().addElement("map");
		} else if (idBag != null) {
			collectionElement = getHbmContext().getCurrent()
					.addElement("idbag");
		} else if (getHbmContext().isGeneratedByEMF()
				&& hbFeature.getOneToMany() != null
				&& hbFeature.getOneToMany().isList()) {
			if (hasOrderBy && hbFeature.getOneToMany().isIndexed()) {
				log
						.warn("One to many ereference has indexed=true and has orderby set. Ignoring indexed and using orderby, assuming set "
								+ hbFeature);
			}

			if (hasOrderBy || !hbFeature.getOneToMany().isIndexed()) {
				collectionElement = getHbmContext().getCurrent().addElement(
						"bag");
			} else {
				collectionElement = getHbmContext().getCurrent().addElement(
						"list");
			}
		} else if (!getHbmContext().isGeneratedByEMF()
				&& hbFeature.getOneToMany() != null) {
			if (hasOrderBy && hbFeature.getOneToMany().isIndexed()) {
				log
						.warn("One to many ereference has indexed=true and has orderby set. "
								+ "Ignoring indexed and using orderby, assuming set "
								+ hbFeature);
			}

			if (!hbFeature.getOneToMany().isList() || hasOrderBy) {
				collectionElement = getHbmContext().getCurrent().addElement(
						"set");
			} else if (hbFeature.getOneToMany().isList()
					&& !hbFeature.getOneToMany().isIndexed()) {
				collectionElement = getHbmContext().getCurrent().addElement(
						"bag");
			} else {
				collectionElement = getHbmContext().getCurrent().addElement(
						"list");
			}
		} else if (hbFeature instanceof PAnnotatedEReference
				&& ((PAnnotatedEReference) hbFeature).getManyToMany() != null
				&& ((PAnnotatedEReference) hbFeature).getManyToMany().isList()) {
			collectionElement = getHbmContext().getCurrent().addElement("list");
		} else {
			collectionElement = getHbmContext().getCurrent().addElement("bag");
		}

		collectionElement.addAttribute("name", getHbmContext().getPropertyName(
				hbFeature.getModelEStructuralFeature()));
		if (idBag != null) {
			final String generator = (idBag.getGenerator() == null ? "increment"
					: idBag.getGenerator());
			final String type = (idBag.getType() == null ? "long" : idBag
					.getType());
			if (false && idBag.getTable() != null) {
				collectionElement.addAttribute("table", idBag.getTable());
			}
			final Element collectionIdElement = collectionElement
					.addElement("collection-id");
			collectionIdElement.addAttribute("column", hbmContext
					.getIdbagIDColumnName());
			collectionIdElement.addAttribute("type", type);

			collectionIdElement.addElement("generator").addAttribute("class",
					generator);
		}

		if (hbFeature instanceof HbAnnotatedEReference) {
			final HbAnnotatedEReference hae = (HbAnnotatedEReference) hbFeature;
			if (hae.getHbFetch() != null) {
				collectionElement.addAttribute("fetch", hae.getHbFetch()
						.getValue().getName().toLowerCase());
			}
		}

		if (hasOrderBy) {
			final PAnnotatedEClass aClass = ((PAnnotatedEReference) paFeature)
					.getAReferenceType();
			final String name = getColumnNameForOrderBy(aClass,
					((PAnnotatedEReference) paFeature).getOrderBy().getValue());
			collectionElement.addAttribute("order-by", name);
		}

		if (hasWhereClause) {
			collectionElement.addAttribute("where",
					((HbAnnotatedEReference) paFeature).getHbWhere()
							.getClause());
		}

		return collectionElement;
	}

	// returns the column name of a certain feature in the target entity
	protected String getColumnNameForOrderBy(PAnnotatedEClass aClass,
			String orderBy) {
		// handle the case of multiple features separated by commas
		final StringBuilder sb = new StringBuilder();
		final String[] orderBys = orderBy.split(",");
		for (String ob : orderBys) {
			// handle direction asc/desc
			ob = ob.trim();
			String direction = " asc";
			if (ob.indexOf(" ") != -1) {
				final int index = ob.lastIndexOf(" ");
				direction = ob.substring(index);
				ob = ob.substring(0, index).trim();
				if (ob.trim().startsWith(getHbmContext().getEscapeCharacter())) {
					ob = ob.trim().substring(
							getHbmContext().getEscapeCharacter().length());
				}
				if (ob.trim().endsWith(getHbmContext().getEscapeCharacter())) {
					ob = ob.trim().substring(
							0,
							ob.trim().length()
									- getHbmContext().getEscapeCharacter()
											.length());
				}
			}
			boolean found = false;
			for (PAnnotatedEStructuralFeature aFeature : getAllFeatures(aClass)) {
				if (aFeature.getModelElement().getName().compareTo(ob) == 0) {
					if (aFeature instanceof PAnnotatedEReference) {
						throw new MappingException(
								"Feature "
										+ ob
										+ " is an ereference, onle eattribute is supported");
					}
					found = true;
					final PAnnotatedEAttribute attr = (PAnnotatedEAttribute) aFeature;
					final List<Column> cs = getColumns(attr);
					if (cs.isEmpty()) {
						if (sb.length() > 0) {
							sb.append(",");
						}
						sb.append(escapeName(getColumnName(attr)) + direction);
					} else {
						for (Column c : cs) {
							if (sb.length() > 0) {
								sb.append(",");
							}
							sb.append(escapeName(c.getName()) + direction);
						}
					}
				}
			}
			if (!found) {
				throw new MappingException("Feature " + ob
						+ " not found in eclass "
						+ aClass.getModelEClass().getName());
			}
		}
		return sb.toString();
	}

	private String escapeName(String name) {
		// assume it also ends with it...
		if (name.startsWith(getHbmContext().getEscapeCharacter())) {
			return name;
		}
		return getHbmContext().getEscapeCharacter() + name
				+ getHbmContext().getEscapeCharacter();
	}

	/**
	 * Add Element element in given collection element.
	 */
	protected Element addElementElement(Element collElement,
			PAnnotatedEStructuralFeature paFeature, List<Column> columns,
			String targetEntity) {
		final Element elElement;
		// if (targetEntity == null || paAttribute.getEnumerated() != null ||
		// StoreUtil.isQName(paAttribute.getModelEAttribute())) {
		// MT: the target type name is ignored for a many element, it is always
		// recomputed
		elElement = collElement.addElement("element");
		setType(paFeature, elElement);
		// } else { // in this case the defaultannotator has set the
		// targetentity!
		// elElement = collElement.addElement("element").addAttribute("type",
		// targetEntity);
		// }
		if (columns != null && columns.size() > 0) {
			addColumnsAndFormula(elElement, paFeature, columns, getHbmContext()
					.isCurrentElementFeatureMap(), true);
		}
		return elElement;
	}

	/**
	 * Adds columns to a key element. Also sets update on the key element based
	 * on the values in the columns.
	 */
	protected void addKeyColumns(HbAnnotatedETypeElement per,
			Element keyElement, List<JoinColumn> joinColumns) {
		log.debug("Adding key columns");
		boolean setUpdatable = false;
		boolean isUpdatable = false;
		for (JoinColumn joinColumn : joinColumns) {
			log.debug("Column " + joinColumn.getName());

			if (!setUpdatable && keyElement.getName().compareTo("key") == 0) {
				isUpdatable = joinColumn.isUpdatable();
				keyElement.addAttribute("update", isUpdatable ? "true"
						: "false");
				setUpdatable = true;
			}

			// these checks are disabled because they do not apply in case the
			// join column
			// is added to a join table
			if (!joinColumn.isInsertable()) {
				log.error("Unsupported non insertable join column in "
						+ joinColumn);
				throw new MappingException(
						"Unsupported non insertable join column", joinColumn);
			}
			if (setUpdatable && joinColumn.isUpdatable() != isUpdatable) {
				log.error("Unsupported join column updatable in " + joinColumn);
				throw new MappingException("Unsupported join column updatable",
						joinColumn);
			}

			final Element ce = keyElement.addElement("column").addAttribute(
					"name", getHbmContext().trunc(joinColumn.getName()))
					.addAttribute("not-null",
							joinColumn.isNullable() ? "false" : "true")
					.addAttribute("unique",
							joinColumn.isUnique() ? "true" : "false");

			// --- JJH, adapted by Martin Taal
			addCommentElement(per.getModelElement(), ce);
			// --- JJH

			if (per != null) { // is null in case of jointables
				final Index index = (per).getHbIndex();
				if (index != null) {
					ce.addAttribute("index", index.getName());
				}
			}

			// bz247939, after disabling these lines it all seemed to work fine
			// if (joinColumn.getTable() != null) {
			// log.error("Unsupported secondary table in " + joinColumn);
			// throw new MappingException("Unsupported secondary table",
			// joinColumn);
			// }

			if (joinColumn.getColumnDefinition() != null) {
				log.error("Unsupported column definition in " + joinColumn);
				throw new MappingException("Unsupported column definition",
						joinColumn);
			}
		}
		// TODO jc.getReferencedColumnName();
	}

	/**
	 * Adds a jointable and possible joincolumns to the passed key element.
	 * 
	 * @param collElement
	 * @param joinTable
	 */
	protected void addJoinTable(HbAnnotatedETypeElement hbAnnotatedElement,
			Element collElement, Element keyElement, JoinTable joinTable) {
		if (joinTable == null) {
			log.debug("No joinTable");
			return;
		}
		if (joinTable.getCatalog() != null) {
			collElement.addAttribute("catalog", joinTable.getCatalog());
		}
		if (joinTable.getSchema() != null) {
			collElement.addAttribute("schema", joinTable.getSchema());
		}
		if (joinTable.getName() != null) {
			collElement.addAttribute("table", getHbmContext().trunc(
					joinTable.getName()));
		}
		if (joinTable.getUniqueConstraints().size() > 0) {
			log.error("Unsupported unique constraints in " + joinTable);
			throw new MappingException("Unsupported unique constraints",
					joinTable);
		}
		addKeyColumns(hbAnnotatedElement, keyElement, joinTable
				.getJoinColumns()/*
								 * == null ? new ArrayList() : (List)joinTable.
								 * getJoinColumns ().getValue()
								 */);
	}
}
