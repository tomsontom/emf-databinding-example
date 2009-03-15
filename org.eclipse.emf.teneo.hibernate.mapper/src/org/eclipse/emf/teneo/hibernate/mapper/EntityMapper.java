/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: EntityMapper.java,v 1.45 2009/03/15 15:08:01 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorColumn;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorValue;
import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationFactory;
import org.eclipse.emf.teneo.annotations.pannotation.PrimaryKeyJoinColumn;
import org.eclipse.emf.teneo.annotations.pannotation.SecondaryTable;
import org.eclipse.emf.teneo.annotations.pannotation.Table;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.hbannotation.DiscriminatorFormula;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbEntity;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEClass;
import org.eclipse.emf.teneo.simpledom.DocumentHelper;
import org.eclipse.emf.teneo.simpledom.Element;

/**
 * Maps the entity and its features, also takes care of embedded superclass
 * mapping.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class EntityMapper extends AbstractMapper implements ExtensionPoint {

	/** Log it all */
	private static final Log log = LogFactory.getLog(EntityMapper.class);

	/** Convenience maps to inheritance strategy names and discriminator types */
	private static final String[] INHERITANCE_STRATEGY_NAMES;

	private static final String[] DISCRIMINATOR_TYPE_NAMES;

	/** Initialize the global arrays */
	static {
		INHERITANCE_STRATEGY_NAMES = new String[InheritanceType.VALUES.size()];
		INHERITANCE_STRATEGY_NAMES[InheritanceType.JOINED.getValue()] = "joined-subclass";
		INHERITANCE_STRATEGY_NAMES[InheritanceType.SINGLE_TABLE.getValue()] = "subclass";
		INHERITANCE_STRATEGY_NAMES[InheritanceType.TABLE_PER_CLASS.getValue()] = "union-subclass";

		DISCRIMINATOR_TYPE_NAMES = new String[DiscriminatorType.VALUES.size()];
		DISCRIMINATOR_TYPE_NAMES[DiscriminatorType.CHAR.getValue()] = "character";
		DISCRIMINATOR_TYPE_NAMES[DiscriminatorType.INTEGER.getValue()] = "integer";
		DISCRIMINATOR_TYPE_NAMES[DiscriminatorType.STRING.getValue()] = "string";
	}

	// Key used for grouping properties to the primary and secondary tables.
	private final String PRIMARY_TABLE_KEY = null;

	/**
	 * @return Return the name used by hibernate to denote the given inheritance
	 *         type. If the argument is null, the default inheritance type
	 *         (SINGLE_TABLE) is used instead.
	 */
	private static String hbInheritanceName(InheritanceType inheritanceType) {
		return EntityMapper.INHERITANCE_STRATEGY_NAMES[inheritanceType != null ? inheritanceType
				.getValue()
				: InheritanceType.SINGLE_TABLE.getValue()];
	}

	/**
	 * @return Return the name used by hibernate for the given dicriminator type
	 */
	private static String hbDiscriminatorType(DiscriminatorType dType) {
		return EntityMapper.DISCRIMINATOR_TYPE_NAMES[dType == null ? DiscriminatorType.STRING
				.getValue()
				: dType.getValue()];
	}

	/**
	 * @return Returns a new hibernate element for the given entity.
	 */
	private Element createEntity(PAnnotatedEClass entity,
			InheritanceType inhStrategy, PAnnotatedEClass superEntity,
			DiscriminatorValue dValue, Table table) {
		// determine what type of hibernate tag should be used
		final InheritanceType inheritanceStrategy = inhStrategy != null ? inhStrategy
				: InheritanceType.SINGLE_TABLE;
		final EClass eclass = entity.getModelEClass();
		final String hbClassName;
		if (superEntity == null) {
			hbClassName = "class";
		} else {
			hbClassName = EntityMapper.hbInheritanceName(inheritanceStrategy);
		}

		log.debug("Creating entity tag " + hbClassName);

		final Element target;

		final String entityName = getHbmContext().getEntityName(eclass);
		final String isAbstractStr = eclass.isAbstract() ? "true" : "false";
		final boolean doProxy = ((HbAnnotatedEClass) entity).getHbProxy() != null
				&& ((HbAnnotatedEClass) entity).getHbProxy().isLazy();
		if (entity.isOnlyMapAsEntity()) {
			target = getHbmContext().getCurrent().addElement(hbClassName)
					.addAttribute("entity-name", entityName).addAttribute(
							"abstract", isAbstractStr).addAttribute("lazy",
							"false");

			// note for composite ids the name must be set always!
			// entity.getAnnotatedEClass().getInstanceClass() != null) { //
			// ||
			// entity.getAnnotatedEClass().getInstanceClass() != null ||
			if (hasCompositeID(entity)) { // only for this specific case
				// it is
				// required to have the impl.name
				target.addAttribute("name", hbmContext
						.getInstanceClassName(entity.getModelEClass()));
			}
		} else if (getHbmContext().forceUseOfInstance(entity)) {

			final Element importElement = new Element("import");

			importElement.addAttribute("class",
					hbmContext.getInstanceClassName(entity.getModelEClass()))
					.addAttribute("rename", entityName);
			getHbmContext().getCurrent().add(getImportIndex(), importElement);

			target = getHbmContext().getCurrent().addElement(hbClassName)
					.addAttribute(
							"name",
							hbmContext.getInstanceClassName(entity
									.getModelEClass())).addAttribute(
							"abstract", isAbstractStr).addAttribute("lazy",
							doProxy ? "true" : "false");
		} else {
			target = getHbmContext().getCurrent().addElement(hbClassName)
					.addAttribute(
							"name",
							hbmContext.getInstanceClassName(entity
									.getModelEClass())).addAttribute(
							"entity-name", entityName).addAttribute("abstract",
							isAbstractStr).addAttribute("lazy",
							doProxy ? "true" : "false");
		}

		final HbAnnotatedEClass hbEntity = (HbAnnotatedEClass) entity;
		// TODO: the immutable on the eclass should be removed as it is now
		// covered through the HbEntity
		if (superEntity == null && hbEntity.getImmutable() != null) {
			target.addAttribute("mutable", "false");
		}

		if (hbEntity.getBatchSize() != null) {
			target.addAttribute("batch-size", ""
					+ hbEntity.getBatchSize().getSize());
		}

		if (hbEntity.getHbEntity() != null) {
			final HbEntity hbEntityAnnon = hbEntity.getHbEntity();
			if (superEntity == null) {
				if (!hbEntityAnnon.isMutable()
						&& target.getAttributeValue("mutable") == null) {
					target.addAttribute("mutable", "false");
				}
				if (hbEntityAnnon.getPolymorphism() != null) {
					target.addAttribute("polymorphism", hbEntityAnnon
							.getPolymorphism().getName().toLowerCase());
				}
				if (hbEntityAnnon.getOptimisticLock() != null) {
					target.addAttribute("optimistic-lock", hbEntityAnnon
							.getOptimisticLock().getName().toLowerCase());
				}
			}
			if (hbEntityAnnon.isDynamicInsert()) {
				target.addAttribute("dynamic-insert", "true");
			}
			if (hbEntityAnnon.isDynamicUpdate()) {
				target.addAttribute("dynamic-update", "true");
			}
			if (hbEntityAnnon.isSelectBeforeUpdate()) {
				target.addAttribute("select-before-update", "true");
			}
			if (hbEntityAnnon.getPersister() != null) {
				target.addAttribute("persister", hbEntityAnnon.getPersister());
			}

		}

		if (superEntity != null) {
			final String extendsEntity;
			if (superEntity.isOnlyMapAsEntity()
					|| !getHbmContext().forceUseOfInstance(superEntity)) {
				extendsEntity = getHbmContext().getEntityName(
						superEntity.getModelEClass());
			} else {
				extendsEntity = getHbmContext().getInstanceClassName(
						superEntity.getModelEClass());
			}
			target.addAttribute("extends", extendsEntity);
			log.debug("Extends " + extendsEntity);
		}

		if (dValue != null) {
			target.addAttribute("discriminator-value", dValue.getValue());
			log.debug("DValue " + dValue.getValue());
		}

		if (table != null
				&& hbClassName
						.compareTo(INHERITANCE_STRATEGY_NAMES[InheritanceType.SINGLE_TABLE
								.getValue()]) == 0) {
			log
					.warn("EClass/Entity ("
							+ entityName
							+ ") is mapped as subclass in a single table with its superclass but it also has a table annotation. This table annotation is ignored.");
		} else if (table != null) {
			if (table.getName() != null) {
				target.addAttribute("table", getHbmContext().trunc(
						table.getName(), false));
				log.debug("Table " + table.getName());
			}
			if (table.getSchema() != null) {
				target.addAttribute("schema", getHbmContext().trunc(
						table.getSchema(), false));
				log.debug("Schema " + table.getSchema());
			}
			if (table.getCatalog() != null) {
				target.addAttribute("catalog", getHbmContext().trunc(
						table.getCatalog(), false));
				log.debug("Catalog " + table.getCatalog());
			}
		}

		if (((HbAnnotatedEClass) entity).getHbWhere() != null) {
			target.addAttribute("where", ((HbAnnotatedEClass) entity)
					.getHbWhere().getClause());
		}

		if (((HbAnnotatedEClass) entity).getHbProxy() != null
				&& ((HbAnnotatedEClass) entity).getHbProxy().getProxyClass() != null) {
			final String proxyInterfaceName = ((HbAnnotatedEClass) entity)
					.getHbProxy().getProxyClass();
			target.addAttribute("proxy", proxyInterfaceName);
		}

		return target;
	}

	/** Return index were to place the import */
	protected int getImportIndex() {
		final List<?> children = getHbmContext().getCurrent().getChildren();
		int defaultResult = 0;
		for (int i = 0; i < children.size(); i++) {
			final Element e = (Element) children.get(i);
			final String name = e.getName();
			if (name.compareTo("class") == 0 || name.compareTo("subclass") == 0
					|| name.compareTo("joined-subclass") == 0
					|| name.compareTo("union-subclass") == 0) {
				return i;
			}
			if (name.compareTo("typedef") == 0 || name.compareTo("meta") == 0) {
				defaultResult = i + 1;
			}
		}
		return defaultResult;
	}

	/** Returns true if this entity or one of its superclasses has an idclass */
	private boolean hasCompositeID(PAnnotatedEClass aClass) {
		if (aClass.getIdClass() != null) {
			return true;
		}
		if (aClass.getPaSuperEntity() != null) {
			return hasCompositeID(aClass.getPaSuperEntity());
		}
		for (PAnnotatedEClass superAClass : aClass.getPaMappedSupers()) {
			if (hasCompositeID(superAClass)) {
				return true;
			}
		}
		{
			List<PAnnotatedEStructuralFeature> features = aClass
					.getPaEStructuralFeatures();
			for (PAnnotatedEStructuralFeature feature : features) {
				if (feature instanceof PAnnotatedEReference
						&& ((PAnnotatedEReference) feature).getEmbeddedId() != null) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Maps the given entity in its hbm context.
	 */
	public void processEntity(PAnnotatedEClass entity) {
		if (log.isDebugEnabled()) {
			log.debug("Mapping Entity " + entity);
		}

		final HbAnnotatedEClass hbEntity = (HbAnnotatedEClass) entity;
		Element entityElement = createEntity(entity, entity
				.getInheritanceStrategy(), entity.getPaSuperEntity(), entity
				.getDiscriminatorValue(), entity.getTable());
		final MappingContext mc = getHbmContext();

		mc.setCurrent(entityElement);
		mc.setCurrentTable(entity.getTable());
		// MT: moved to processFeatures method because this should be done after
		// the id
		// element has been placed

		if (entity.getPaSuperEntity() == null
				&& entity.getPrimaryKeyJoinColumns() != null
				&& entity.getPrimaryKeyJoinColumns().size() > 0) {
			log
					.warn("This entity ("
							+ entity.getEntity().getName()
							+ " is the root in the class hierarchy and "
							+ "has a pk joincolum annotation, this is not correct, ignoring pk joincolumn annotation");
		} else if (InheritanceType.SINGLE_TABLE.equals(entity
				.getInheritanceStrategy())
				&& entity.getPrimaryKeyJoinColumns() != null
				&& entity.getPrimaryKeyJoinColumns().size() > 0) {
			log
					.warn("Single table inheritance strategy (entity "
							+ entity.getEntity().getName()
							+ ", primary key join column annotation is ignored for subclass");
		} else if (entity.getPrimaryKeyJoinColumns() != null
				&& entity.getPrimaryKeyJoinColumns().size() > 0) {
			addPrimaryKeyJoinColumn(entity.getPrimaryKeyJoinColumns(), entity);
		} else if (entity.getPaSuperEntity() != null
				&& InheritanceType.JOINED.equals(entity
						.getInheritanceStrategy())) {
			final ArrayList<PrimaryKeyJoinColumn> list = new ArrayList<PrimaryKeyJoinColumn>();
			final PrimaryKeyJoinColumn pkjc = PannotationFactory.eINSTANCE
					.createPrimaryKeyJoinColumn();
			final String entityName = mc.getEntityName(entity.getModelEClass());
			mc.trunc(entityName + "id"); // TODO improve name
			// creation here
			list.add(pkjc);
			addPrimaryKeyJoinColumn(list, entity);
		}

		try {
			final List<PAnnotatedEStructuralFeature> inheritedFeatures = mc
					.getInheritedFeatures(entity);

			mc
					.setForceOptional(entity.getPaSuperEntity() != null
							&& (entity.getInheritanceStrategy() == null || InheritanceType.SINGLE_TABLE
									.equals(entity.getInheritanceStrategy())));

			mc.pushOverrideOnStack();
			mc.addAttributeOverrides(entity.getAttributeOverrides());
			mc.addAssociationOverrides(entity.getAssociationOverrides());

			if (inheritedFeatures.size() > 0) {
				log.debug("There are " + inheritedFeatures.size()
						+ " inherited features ");
			}

			try {
				processFeatures(inheritedFeatures);

				final EList<SecondaryTable> secondaryTables = entity
						.getSecondaryTables();
				if (secondaryTables == null || secondaryTables.isEmpty()) {
					// Process features normally.
					processFeatures(entity.getPaEStructuralFeatures());
				} else {
					// Special processing needed for secondary tables.
					processSecondaryTables(secondaryTables, entity);
				}
			} finally {
				mc.popOverrideStack();
			}
		} finally {
			mc.setForceOptional(false);
			mc.setCurrentTable(null);
			Element idElement = entityElement.element("id");
			if (idElement == null) {
				idElement = entityElement.element("composite-id");
			}

			// create a synthetic id for roots
			if (idElement == null && entity.getPaSuperEntity() == null
					&& entity.getPaMappedSupers().size() == 0) {
				idElement = IdMapper.addSyntheticId(hbmContext, entityElement);
			} else if (mc.mustAddSyntheticID(entity)) {
				idElement = IdMapper.addSyntheticId(hbmContext, entityElement);
			} else {
				addAccessor(idElement, hbmContext.getIdPropertyHandlerName());
			}

			if (idElement != null) {
				int index = entityElement.indexOf(idElement) + 1;

				if (((HbAnnotatedEClass) entity).getDiscriminatorFormula() != null) {
					entityElement.add(index++, createDiscriminatorElement(
							hbEntity.getDiscriminatorFormula(), entity
									.getDiscriminatorColumn(), hbEntity
									.getForceDiscriminator() != null));
				} else if (entity.getDiscriminatorColumn() != null) {
					// add discriminator element immediately after id element
					entityElement.add(index++, createDiscriminatorElement(
							entity.getDiscriminatorColumn(), hbEntity
									.getForceDiscriminator() != null));
				}

				// create and/or move version element (if present) immediately
				// after discriminator
				Element versionElement = entityElement.element("version");
				if (versionElement == null) {
					versionElement = addVersionProperty(hbEntity);
				}

				if (null != versionElement) { // In case this is not versioned
					entityElement.remove(versionElement);
					entityElement.add(index, versionElement);
				}
			}

			mapFilter(entityElement, ((HbAnnotatedEClass) entity).getFilter());

			mc.setCurrent(entityElement.getParent());

			// now process the featuremap entries if any
			processFeatureMapFeatures();
			int index = 1 + entityElement.getChildren().indexOf(idElement);
			// check if there is a discriminator, if so put it behind that one
			if (entityElement.element("discriminator") != null) {
				final Element elem = entityElement.element("discriminator");
				index = 1 + entityElement.getChildren().indexOf(elem);
			}

			handleNaturalId(entityElement, index);
		}

		// initially the comment is placed at the back, move it to the front
		final Element comment = addCommentElement(entity.getModelEClass(),
				entityElement);
		if (comment != null) {
			entityElement.remove(comment);
			entityElement.add(0, comment);
		}

		// place the tuplizer at the front
		mc.addTuplizerElement(entityElement, entity);

		if ((entity.getPaSuperEntity() == null || entity.getPaSuperEntity()
				.getMappedSuperclass() != null)
				&& ((HbAnnotatedEClass) entity).getHbCache() != null) {
			// then add the cache at the front
			addCacheElement(entityElement, ((HbAnnotatedEClass) entity)
					.getHbCache());
		}

		// and add the metas at the front
		final Element meta1 = new Element("meta");
		meta1.addAttribute("attribute", HbMapperConstants.ECLASS_NAME_META)
				.addText(entity.getModelEClass().getName());
		final Element meta2 = new Element("meta");
		meta2.addAttribute("attribute", HbMapperConstants.EPACKAGE_META)
				.addText(entity.getModelEClass().getEPackage().getNsURI());

		entityElement.add(0, meta1);
		entityElement.add(1, meta2);

		// and move the joins to the back
		final List<Element> toMove = new ArrayList<Element>();
		for (Element elem : entityElement.getChildren()) {
			if (elem.getName().equals("join")) {
				toMove.add(elem);
			}
		}
		entityElement.getChildren().removeAll(toMove);
		entityElement.getChildren().addAll(toMove);

	}

	// reorganize the elements which have a natural-id inside a natural-id tag
	protected void handleNaturalId(Element parentElement, int index) {
		log.debug("Checking for natural-ids");
		final List<Element> naturalIdElements = new ArrayList<Element>();
		boolean mutable = false;
		for (Element element : parentElement.getChildren()) {
			String value;
			if ((value = element
					.getAttributeValue(HbMapperConstants.NATURAL_ID_ATTR)) != null) {
				if (naturalIdElements.isEmpty()) {
					log.debug("Found natural-id, mutable: " + mutable);
					mutable = Boolean.parseBoolean(value);
				}
				naturalIdElements.add(element);
				element.removeAttribute(HbMapperConstants.NATURAL_ID_ATTR);
			}
		}
		if (!naturalIdElements.isEmpty()) {
			log
					.debug("Found "
							+ naturalIdElements.size()
							+ " naturalid properies, putting them inside a natural-id tag");
			parentElement.getChildren().removeAll(naturalIdElements);
			final Element naturalId = parentElement.addElement("natural-id");
			// remove it again to place it at the correct position a little
			// lower
			parentElement.remove(naturalId);
			naturalId.addAttribute("mutable", Boolean.toString(mutable));
			parentElement.add(index, naturalId);
			naturalId.getChildren().addAll(naturalIdElements);
			for (Element element : naturalIdElements) {
				element.setParent(naturalId);
			}
		}
	}

	/** Process the featuremap entries */
	private void processFeatureMapFeatures() {
		// this is a while and for loop, in the for loop the features of a
		// feature map
		// are processed, these features can again be a featuremap which are
		// added
		// to the featuremapmappers in the context, therefore this process walks
		// on
		List<FeatureMapMapping> featureMapMappers = getHbmContext()
				.getClearFeatureMapMappers();
		while (featureMapMappers.size() > 0) {

			for (FeatureMapMapping fmm : featureMapMappers) {
				fmm.process();
			}

			// read again because the fmm.process can result in new feature map
			// mappings
			featureMapMappers = getHbmContext().getClearFeatureMapMappers();
		}
	}

	/**
	 * Process the features of the mapped superclass so that they are added to
	 * the current mapping
	 */
	protected void processMappedSuper(PAnnotatedEClass paMappedSuper) {
		processFeatures(paMappedSuper.getPaEStructuralFeatures());
	}

	/** process the features of the annotated eclass */
	protected void processFeatures(List<PAnnotatedEStructuralFeature> features) {
		for (PAnnotatedEStructuralFeature annotatedEStructuralFeature : features) {
			processFeature(annotatedEStructuralFeature);
		}
	}

	/** Process secondary tables. */
	protected void processSecondaryTables(List<SecondaryTable> secondaryTables,
			PAnnotatedEClass entity) {
		// Group features by primary and secondary tables.
		// For the primary table we use null as the surrogate key. (HashMap
		// allows null keys.)
		final Set<String> tableNames = new HashSet<String>();
		tableNames.add(PRIMARY_TABLE_KEY);
		for (SecondaryTable secondaryTable : secondaryTables) {
			tableNames.add(secondaryTable.getName());
		}
		final Map<String, List<PAnnotatedEStructuralFeature>> featuresByTable = new HashMap<String, List<PAnnotatedEStructuralFeature>>();
		for (PAnnotatedEStructuralFeature feature : entity
				.getPaEStructuralFeatures()) {
			// find the table for the feature
			final String tableName = getHbmContext().getSecondaryTableName(
					feature);
			// if there put it in the correct list
			if (!tableNames.contains(tableName)) {
				final String message = "Feature \""
						+ feature.getModelElement().getName()
						+ "\" was mapped to undeclared secondary table \""
						+ tableName + "\".";
				log.error(message);
				throw new MappingException(message);
			}
			List<PAnnotatedEStructuralFeature> featuresInTable = featuresByTable
					.get(tableName);
			if (featuresInTable == null) {
				featuresInTable = new ArrayList<PAnnotatedEStructuralFeature>();
				featuresByTable.put(tableName, featuresInTable);
			}
			featuresInTable.add(feature);
		}

		// Process features in primary table.
		{
			final List<PAnnotatedEStructuralFeature> featuresInTable = featuresByTable
					.get(PRIMARY_TABLE_KEY);
			// can happen if there are only secondarytable features and no
			// primary table
			// features see bugzilla 235949
			if (featuresInTable != null) {
				for (PAnnotatedEStructuralFeature feature : featuresInTable) {
					processFeature(feature);
				}
			}
		}
		// Process features in secondary tables.
		for (SecondaryTable secondaryTable : secondaryTables) {
			if (!featuresByTable.containsKey(secondaryTable.getName())) {
				// The table was listed in the SecondaryTable annotation, but no
				// features were actually mapped to this
				// table.
				log.warn("No features mapped to secondary table "
						+ secondaryTable.getName());
				continue;
			}
			final List<PAnnotatedEStructuralFeature> featuresInTable = featuresByTable
					.get(secondaryTable.getName());

			// Create <join> element
			// See
			// http://www.hibernate.org/hib_docs/v3/reference/en/html/mapping.html#mapping-declaration
			// -join
			final Element joinElement = getHbmContext().getCurrent()
					.addElement("join");
			joinElement.addAttribute("table", getHbmContext().trunc(
					secondaryTable.getName().toUpperCase(), false));
			log.debug("Mapping features to secondary table \""
					+ secondaryTable.getName() + "\"");
			if (secondaryTable.getCatalog() != null) {
				joinElement
						.addAttribute("catalog", secondaryTable.getCatalog());
			}
			if (secondaryTable.getSchema() != null) {
				joinElement.addAttribute("schema", secondaryTable.getSchema());
			}
			entity.getModelEClass().getEIDAttribute();
			final List<PrimaryKeyJoinColumn> pkJoinColumns = secondaryTable
					.getPkJoinColumns();
			final Element keyElement = joinElement.addElement("key");
			for (PrimaryKeyJoinColumn pkJoinColumn : pkJoinColumns) {
				keyElement.addElement("column").addAttribute("name",
						getHbmContext().trunc(pkJoinColumn.getName()));
			}
			handleOndelete(keyElement, ((HbAnnotatedEClass) entity)
					.getHbOnDelete());

			// Process features in this secondary table.
			try {
				getHbmContext().setCurrent(joinElement);
				getHbmContext().setCurrentSecondaryTable(secondaryTable);
				for (PAnnotatedEStructuralFeature feature : featuresInTable) {
					processFeature(feature);
				}
			} finally {
				getHbmContext().setCurrentSecondaryTable(null);
				getHbmContext().setCurrent(joinElement.getParent());
			}
		}
	}

	/** Process one feature */
	protected void processFeature(PAnnotatedEStructuralFeature paFeature) {
		hbmContext.getFeatureMapper().process(paFeature);
	}

	/**
	 * Adds a key element to the current entity mapping, is for example used to
	 * join to the super class table.
	 */
	private void addPrimaryKeyJoinColumn(List<PrimaryKeyJoinColumn> pkJCs,
			PAnnotatedEClass entity) {
		log.debug("Adding primary key join column");

		final Element jcElement = getHbmContext().getCurrent()
				.addElement("key");
		handleOndelete(jcElement, ((HbAnnotatedEClass) entity).getHbOnDelete());

		for (PrimaryKeyJoinColumn pkJC : pkJCs) {
			final Element columnElement = jcElement.addElement("column");

			if (pkJC.getColumnDefinition() != null) {
				log
						.error("Unsupported column definition in primary key join column "
								+ pkJC);
				throw new MappingException(
						"Unsupported column definition in primary key join column",
						pkJC);
			}

			if (pkJC.getName() != null) {
				columnElement.addAttribute("name", getHbmContext().trunc(
						pkJC.getName()));
			}
			if (pkJC.getReferencedColumnName() != null) {
				columnElement.addAttribute("property-ref", pkJC
						.getReferencedColumnName());
			}
		}
	}

	/**
	 * Creates a discriminator element and returns it, so the caller should add
	 * it to the parent element
	 */
	private Element createDiscriminatorElement(DiscriminatorColumn dColumn,
			boolean force) {
		Element dcElement = DocumentHelper.createElement("discriminator");
		if (dColumn.getName() != null) {
			dcElement.addAttribute("column", getHbmContext().trunc(
					dColumn.getName()));
		}
		dcElement.addAttribute("type", hbDiscriminatorType(dColumn
				.getDiscriminatorType()));

		if (force) {
			dcElement.addAttribute("force", "true");
		}

		if (dColumn.isSetLength() && log.isDebugEnabled()) {
			log.debug("Ignoring length for discriminator column " + dColumn);
		}

		if (dColumn.getColumnDefinition() != null) {
			log.error("Unsupported column definition in discriminator column "
					+ dColumn);
			throw new MappingException(
					"Unsupported column definition in discriminator column",
					dColumn);
		}
		return dcElement;
	}

	// note dc can be null
	private Element createDiscriminatorElement(DiscriminatorFormula formula,
			DiscriminatorColumn dc, boolean force) {
		Element dcElement = DocumentHelper.createElement("discriminator");
		dcElement.addAttribute("formula", formula.getValue());

		if (force) {
			dcElement.addAttribute("force", "true");
		}

		if (dc != null && dc.getDiscriminatorType() != null) {
			dcElement.addAttribute("type", hbDiscriminatorType(dc
					.getDiscriminatorType()));
		}
		return dcElement;
	}

	/**
	 * Add a synthetic version if the entity does not define one
	 */
	private Element addVersionProperty(HbAnnotatedEClass aClass) {
		assert (getHbmContext().getCurrent().element("version") == null);

		if (!getHbmContext().alwaysVersion()) {
			return null;
		}
		final boolean skipVersionOnImmutable = !getHbmContext()
				.getPersistenceOptions()
				.isDiscriminatorVersionOnImmutableEClass();
		if (aClass.getImmutable() != null && skipVersionOnImmutable) {
			return null;
		}

		// note specific accessor is required because version accessor is not
		// retrieved through
		// emf tuplizer
		final Element versionElement = getHbmContext().getCurrent().addElement(
				"version").addAttribute("name",
				getHbmContext().getVersionColumnName()).addAttribute("column",
				getHbmContext().getVersionColumnName());
		final Element meta = new Element("meta");
		meta.addAttribute("attribute", HbMapperConstants.VERSION_META).addText(
				"true");
		versionElement.add(0, meta);

		versionElement.addAttribute("access", getHbmContext()
				.getSyntheticVersionPropertyHandlerName());

		return versionElement;
	}
}
