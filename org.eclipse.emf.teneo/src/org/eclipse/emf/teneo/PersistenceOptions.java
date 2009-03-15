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
 *   Martin Taal - Initial API and implementation
 *   Jason Henriksen - Mapping File Path
 *   Jason Henriksen - XSDDate and XSDDateTime constants
 * </copyright>
 *
 * $Id: PersistenceOptions.java,v 1.49 2009/03/15 14:49:49 mtaal Exp $
 */

package org.eclipse.emf.teneo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Defines the property names used in the persistence mapping.
 * <p>
 * As a convenience, this class offers type-safe property accessor wrappers.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.49 $
 */
public class PersistenceOptions implements ExtensionPoint {

	public static final String DEFAULT_CLASSPATH_FILENAME = "/teneo-persistence.properties";

	public static final String RUNTIME_PREFIX = "teneo.runtime.";

	public static final String MAPPING_PREFIX = "teneo.mapping.";

	public static final String NAMING_PREFIX = "teneo.naming.";

	/** The logger */
	private static Log log = LogFactory.getLog(PersistenceOptions.class);

	// START: ++++++++++++++++++++++ SQL Naming related Options
	// ++++++++++++++++++++++++++++++++++++
	/**
	 * Join table naming strategy, two values: ejb3 and unique
	 * 
	 * @Deprecated use the extensionManager concept
	 */
	public static final String JOIN_TABLE_NAMING_STRATEGY = NAMING_PREFIX
			+ "join_table_naming_strategy";

	/**
	 * Deprecated use JOIN_TABLE_NAMING_STRATEGY
	 * 
	 * @Deprecated
	 */
	public static final String JOIN_TABLE_NAMING_STRATEGY_OLD = MAPPING_PREFIX
			+ "join_table_naming_strategy";

	/**
	 * Join column naming strategy, two values: unique and simple. unique is the
	 * default and results in guaranteed unique naming for the join columns
	 * simple will always try to use minimal name lengths and will most of the
	 * time just use the efeaturename.
	 */
	public static final String JOIN_COLUMN_NAMING_STRATEGY = NAMING_PREFIX
			+ "join_column_naming_strategy";

	/** The column name of the id column in the idbag, default is id */
	public static final String IDBAG_ID_COLUMN_NAME = NAMING_PREFIX
			+ "idbag_id_column_name";

	/** Name of version column, default value is e_version */
	public static final String VERSION_COLUMN_NAME = NAMING_PREFIX
			+ "version_column";

	/** Name of id column, default value is e_id */
	public static final String ID_COLUMN_NAME = NAMING_PREFIX
			+ "default_id_column";

	/**
	 * Truncate the column name if the length is larger than this property. In
	 * case of concatenating property names for foreign keys
	 */
	public static final String MAXIMUM_SQL_NAME_LENGTH = NAMING_PREFIX
			+ "max_sql_name_length";

	/**
	 * The option which determines the casing of columns and table names,
	 * lowercase will result in lowercase letters, uppercase in uppercase, none
	 * will just work as it did until now
	 */
	public static final String SQL_CASE_STRATEGY = NAMING_PREFIX + "strategy";

	/**
	 * If set then the system will generate readable foreign key names. The
	 * default is true. Set to false for backward compatibility. Note that
	 * before the default value of this property was false.
	 */
	public static final String SET_FOREIGN_KEY_NAME = NAMING_PREFIX
			+ "set_foreign_key_name";

	/**
	 * The escape character to use when escaping table and column names.
	 * Standard Hibernate uses the ` (backtick). This is the default value.
	 */
	public static final String SQL_NAME_ESCAPE_CHARACTER = MAPPING_PREFIX
			+ "sql_name_escape_character";

	/**
	 * The sql name strategy, if not set then the ClassicSQLNameStrategy is
	 * used.
	 * 
	 * @Deprecated use the extensionManager concept
	 */
	public static final String SQL_NAME_STRATEGY = NAMING_PREFIX
			+ "sql_name_strategy";

	// END: ++++++++++++++++++++++ SQL Naming related Options
	// ++++++++++++++++++++++++++++++++++++

	/**
	 * Controls if non-mutable eclasses should have a discriminator or version
	 * column. Default is false.
	 * 
	 * @Deprecated use the extensionManager concept
	 */
	public static final String SQL_DISCRIMINATOR_VERSION_IMMUTABLE_ECLASS = NAMING_PREFIX
			+ "sql_discriminator_version_immutable_eclass";

	/**
	 * Controls how the econtainer feature id is persisted. There are three
	 * values: featureid or featurename
	 * 
	 * @Deprecated use the extensionManager concept
	 */
	public static final String ECONTAINER_FEATURE_PERSISTENCE_STRATEGY = NAMING_PREFIX
			+ "econtainer_feature_persistence_strategy";

	/**
	 * see bugzilla 227673, option can be used to set the hibernate usertype
	 * used for xsd date and xsd date time fields.
	 */
	public static final String USER_XSDDATE_TYPE = MAPPING_PREFIX
			+ "UserDateType";
	public static final String USER_XSDDATETIME_TYPE = MAPPING_PREFIX
			+ "UserDateTimeType";

	/**
	 * This option can be used to control the actual xsd date class used, as a
	 * default the javax.xml.datatype.XMLGregorianCalendar class is used.
	 */
	public static final String XSDDATE_CLASS = MAPPING_PREFIX + "XSDDateClass";

	/**
	 * The default length of a varchar column. Normally hibernate will choose to
	 * set this to 255.
	 */
	public static final String DEFAULT_VARCHAR_LENGTH = MAPPING_PREFIX
			+ "default_varchar_length";

	/**
	 * The maximum length of the comments which are copied from the model to the
	 * mapping file. The default is zero which means no comments are copied from
	 * the model to the mapping.
	 */
	public static final String MAX_COMMENT_LENGTH = MAPPING_PREFIX
			+ "max_comment_length";

	/**
	 * EClass marked with Embeddable is always embedded, default is false. If
	 * this is set to true then it is not required anymore to set a
	 * 
	 * @Embedded annotation on an ereference,
	 * @Embeddable on the EClass is then sufficient.
	 */
	public static final String MAP_EMBEDDABLE_AS_EMBEDDED = MAPPING_PREFIX
			+ "map_embeddable_as_embedded";

	/** Optimistic locking */
	public static final String OPTIMISTIC = MAPPING_PREFIX
			+ "optimistic_locking";

	/**
	 * Add an index for each foreign key mapping. Some databases do not add an
	 * index for a foreign key automatically. Setting this to true will
	 * automatically add an index field to each foreign key.
	 */
	public static final String ADD_INDEX_FOR_FOREIGN_KEY = MAPPING_PREFIX
			+ "add_index_for_fk";

	/**
	 * Set or not set the cascade attribute on a mto, mtm or otm non-containment
	 * relation. The backward compatible value is true. The better performing
	 * value is false. The default is false.
	 * 
	 * @Deprecated use CASCADE_POLICY_ON_NON_CONTAINMENT
	 */
	public static final String SET_DEFAULT_CASCADE_ON_NON_CONTAINMENT = MAPPING_PREFIX
			+ "set_default_cascade_on_non_containment";

	/** Inheritance mapping */
	public static final String INHERITANCE_MAPPING = MAPPING_PREFIX
			+ "inheritance";

	/**
	 * Can be set to force an update of the schema when the application starts.
	 * Note this option is only meaningfull for jpox, for hibernate use the
	 * hibernate property: hibernate.hbm2ddl_auto
	 */
	public static final String UPDATE_SCHEMA = RUNTIME_PREFIX + "update_schema";

	/** Force all containment relations to be eagerly loaded or not */
	public static final String FETCH_CONTAINMENT_EAGERLY = MAPPING_PREFIX
			+ "fetch_containment_eagerly";

	/**
	 * Set cascade all (incl. orphan delete) on containment relation.
	 * 
	 * @Deprecated use CASCADE_POLICY_ON_CONTAINMENT
	 */
	public static final String SET_CASCADE_ALL_ON_CONTAINMENT = MAPPING_PREFIX
			+ "cascade_all_on_containment";

	/**
	 * Can be used to set custom cascade policy for containment : <br>
	 * <code>ALL</code> or a combination of (<code>REMOVE</code>,
	 * <code>REFRESH</code>, <code>PERSIST</code>,<code>MERGE</code>) <br>
	 * e.g. : REMOVE,PERSIST,MERGE <br>
	 * Warning : ALL != REMOVE,REFRESH,PERSIST,MERGE <br>
	 * but ALL == REMOVE with delete Orphan, REFRESH,PERSIST,MERGE
	 */
	public static final String CASCADE_POLICY_ON_CONTAINMENT = MAPPING_PREFIX
			+ "cascade_policy_on_containment";

	/**
	 * Can be used to set custom cascade policy for non containment : <br>
	 * a combination of (<code>REFRESH</code>,<code>PERSIST</code>,
	 * <code>MERGE</code>) e.g. : PERSIST,MERGE
	 */
	public static final String CASCADE_POLICY_ON_NON_CONTAINMENT = MAPPING_PREFIX
			+ "cascade_policy_on_non_containment";

	/**
	 * Can be used to control if the entity ann. should be added automatically
	 * to the model elements or that the default annotator should work according
	 * to the ejb3 spec.
	 */
	public static final String SET_ENTITY_AUTOMATICALLY = MAPPING_PREFIX
			+ "set_entity_automatically";

	/**
	 * Map all lists as a bag to the db (does not map the list index to the db),
	 * default is false
	 */
	public static final String ALWAYS_MAP_LIST_AS_BAG = MAPPING_PREFIX
			+ "always_map_list_as_bag";

	/**
	 * Map all lists as a hibernate idbag to the db (does not map the list index
	 * to the db), default is false
	 */
	public static final String MAP_ALL_LISTS_AS_IDBAG = MAPPING_PREFIX
			+ "map_all_lists_as_idbag";

	/** Use static hibernate mapping file */
	public static final String USE_MAPPING_FILE = MAPPING_PREFIX
			+ "hibernate_mapping_file";

	/**
	 * The complete resource path to the mapping file, can be used instead of
	 * the USE_MAPPING_FILE option
	 */
	public static final String MAPPING_FILE_PATH = MAPPING_PREFIX
			+ "mapping_file_name";

	/**
	 * Automatically add
	 * 
	 * @Id to ID feature
	 * 
	 * @id annotation to ID xsd type
	 */
	public static final String ID_FEATURE_AS_PRIMARY_KEY = NAMING_PREFIX
			+ "id_feature_as_primary_key";

	/**
	 * Automatically add
	 * 
	 * @GeneratedValue to ID feature for which
	 * @Id is added automatically, default is true.
	 */
	public static final String SET_GENERATED_VALUE_ON_ID_FEATURE = NAMING_PREFIX
			+ "set_generated_value_on_id_feature";

	/**
	 * The name of the id feature if no feature has a
	 * 
	 * @id annotation
	 */
	public static final String DEFAULT_ID_FEATURE_NAME = NAMING_PREFIX
			+ "default_id_feature";

	/**
	 * The path of the persistence XML file.
	 */
	public static final String PERSISTENCE_XML = MAPPING_PREFIX
			+ "persistence_xml";

	/**
	 * Ignore mapping EAnnotations. Primarily meant for test cases that use
	 * Persistence XML mapping, so that they can reuse the same sample models.
	 */
	public static final String IGNORE_EANNOTATIONS = MAPPING_PREFIX
			+ "ignore_eannotations";

	/**
	 * Map all emaps as true hibernate maps, default is true. In EMF an EMap is
	 * in fact an EList with Map entries. Originally Teneo maps this as a
	 * hibernate list. In the new behavior hibernate can map the emap as a real
	 * map. The default is true.
	 */
	public static final String EMAP_AS_TRUE_MAP = MAPPING_PREFIX
			+ "emap_as_true_map";

	/**
	 * This option controls if in case of hibernate also a name attribute should
	 * be added to the class/subclass tag. By adding this a class is mapped as
	 * an entity as well as a normal class. Also mapping as a normal class has
	 * the advantage that proxies can be used and that queries can use actual
	 * class names and interface names. This option is really there for backward
	 * compatibility. There are no apparent dis-advantages of adding a name
	 * attribute so the default of this option is true. Note that an eclass must
	 * have an implementation class otherwise this option has no effect.
	 * Interfaces are for example always mapped as an entity.
	 */
	public static final String ALSO_MAP_AS_CLASS = MAPPING_PREFIX
			+ "also_map_as_class";

	/**
	 * This option controls if as a default all classes should be proxied (for
	 * hibernate). This means that you don't need to add a
	 * 
	 * @Proxy annotation to each eclass. As a default Teneo will use the eclass
	 *        interface as the proxy class. When this is set to true then the
	 *        option ALSO_MAP_AS_CLASS should also be true.
	 */
	public static final String SET_PROXY = MAPPING_PREFIX + "set_proxy";

	/**
	 * Disable EContainer mapping.
	 */
	public static final String DISABLE_ECONTAINER_MAPPING = MAPPING_PREFIX
			+ "disable_econtainer";

	/**
	 * Option to specify that for non-contained one-to-many always a join table
	 * is used, default is true
	 */
	public static final String JOIN_TABLE_FOR_NON_CONTAINED_ASSOCIATIONS = MAPPING_PREFIX
			+ "join_table_for_non_contained_associations";

	/**
	 * Determines whether to always include a version mapping even if one is not
	 * specified. Defaults to "true"
	 */
	public static final String ALWAYS_VERSION = MAPPING_PREFIX
			+ "always_version";

	/**
	 * The default cache strategy, can be one of: NONE, READ_ONLY,
	 * NONSTRICT_READ_ONLY, READ_WRITE, TRANSACTIONAl. If different than NONE
	 * (=default) then for Hibernate every class will be second-level cached!
	 */
	public static final String DEFAULT_CACHE_STRATEGY = MAPPING_PREFIX
			+ "default_cache_strategy";

	/** The default time/date type used */
	public static final String DEFAULT_TEMPORAL_VALUE = MAPPING_PREFIX
			+ "default_temporal";

	/** Returns the default properties used in the system */
	public static Properties getDefaultProperties() {
		final Properties props = new Properties();
		props.setProperty(JOIN_TABLE_FOR_NON_CONTAINED_ASSOCIATIONS, "true");
		props.setProperty(USE_MAPPING_FILE, "false");
		// props.setProperty(MAPPING_FILE_PATH, null); // null is the default
		// anyway
		props.setProperty(SET_CASCADE_ALL_ON_CONTAINMENT, "");
		props.setProperty(CASCADE_POLICY_ON_CONTAINMENT, "ALL");
		props.setProperty(OPTIMISTIC, "true");
		props.setProperty(UPDATE_SCHEMA, "true");
		props.setProperty(FETCH_CONTAINMENT_EAGERLY, "false");
		props.setProperty(SET_ENTITY_AUTOMATICALLY, "true");
		props.setProperty(VERSION_COLUMN_NAME, "e_version");
		props.setProperty(SQL_CASE_STRATEGY, "lowercase");
		props.setProperty(ID_COLUMN_NAME, "e_id");
		props.setProperty(DISABLE_ECONTAINER_MAPPING, "false");
		props.setProperty(MAXIMUM_SQL_NAME_LENGTH, "-1");
		props.setProperty(IGNORE_EANNOTATIONS, "false");
		props.setProperty(ALWAYS_VERSION, "true");
		props.setProperty(DEFAULT_CACHE_STRATEGY, "NONE");
		props.setProperty(JOIN_TABLE_NAMING_STRATEGY, "unique");
		// props.setProperty(JOIN_TABLE_NAMING_STRATEGY_OLD, "unique");
		props.setProperty(JOIN_COLUMN_NAMING_STRATEGY, "unique");
		props.setProperty(DEFAULT_TEMPORAL_VALUE, "TIMESTAMP");
		props.setProperty(DEFAULT_ID_FEATURE_NAME, "e_id");
		props.setProperty(ID_FEATURE_AS_PRIMARY_KEY, "true");
		props.setProperty(SET_GENERATED_VALUE_ON_ID_FEATURE, "true");
		props.setProperty(EMAP_AS_TRUE_MAP, "true");
		props.setProperty(ALWAYS_MAP_LIST_AS_BAG, "false");
		props.setProperty(ALSO_MAP_AS_CLASS, "true");
		props.setProperty(SET_PROXY, "false");
		props.setProperty(MAP_ALL_LISTS_AS_IDBAG, "false");
		props.setProperty(IDBAG_ID_COLUMN_NAME, "ID");
		props.setProperty(ADD_INDEX_FOR_FOREIGN_KEY, "false");
		props.setProperty(SET_DEFAULT_CASCADE_ON_NON_CONTAINMENT, "");
		props.setProperty(CASCADE_POLICY_ON_NON_CONTAINMENT, "");
		props.setProperty(SET_FOREIGN_KEY_NAME, "true");
		props.setProperty(MAP_EMBEDDABLE_AS_EMBEDDED, "false");
		props.setProperty(MAX_COMMENT_LENGTH, "0");
		props.setProperty(DEFAULT_VARCHAR_LENGTH, "-1");
		props.setProperty(SQL_NAME_ESCAPE_CHARACTER, "`");
		props.setProperty(USER_XSDDATE_TYPE,
				"org.eclipse.emf.teneo.hibernate.mapping.XSDDate");
		props.setProperty(USER_XSDDATETIME_TYPE,
				"org.eclipse.emf.teneo.hibernate.mapping.XSDDateTime");
		props.setProperty(XSDDATE_CLASS,
				"javax.xml.datatype.XMLGregorianCalendar");
		props.setProperty(SQL_DISCRIMINATOR_VERSION_IMMUTABLE_ECLASS, "true");
		props.setProperty(ECONTAINER_FEATURE_PERSISTENCE_STRATEGY,
				"FEATURENAME");
		return props;
	}

	/**
	 * The wrapped Properties instance.
	 */
	private final Properties properties;

	/**
	 * Construct a new instance using Properties.
	 */
	public PersistenceOptions(Properties properties) {
		this.properties = getDefaultProperties();

		if (properties != null) {
			this.properties.putAll(properties);
		}

		logProperties();
	}

	/**
	 * Constructs a new instance by loading properties from
	 * "/elver-persistence.properties" at the root of the classpath.
	 */
	public PersistenceOptions() {
		this.properties = getDefaultProperties();

		final Properties props = new Properties();
		InputStream in = null;
		try {
			in = this.getClass()
					.getResourceAsStream(DEFAULT_CLASSPATH_FILENAME);
			if (in != null) {
				log.debug("Loading persistence options from classpath \""
						+ DEFAULT_CLASSPATH_FILENAME + "\".");
				props.load(in);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error loading \""
					+ DEFAULT_CLASSPATH_FILENAME + "\" from classpath:"
					+ e.getMessage(), e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// Ignore.
			}
		}
		this.properties.putAll(props);

		logProperties();
	}

	/** Dump the props */
	public void logProperties() {
		log.debug("Properties of PersistenceOptions:");
		for (Object key : properties.keySet()) {
			log.debug(key + ": " + properties.get(key));
		}
	}

	/** Return the default temporal value */
	public String getDefaultTemporalValue() {
		return properties.getProperty(DEFAULT_TEMPORAL_VALUE);
	}

	/** Return the IDBAG_ID_COLUMN_NAME */
	public String getIDBagIDColumnName() {
		return properties.getProperty(IDBAG_ID_COLUMN_NAME);
	}

	/**
	 * Returns the value of the EMAP_AS_TRUE_MAP option, default is false
	 */
	public boolean isMapEMapAsTrueMap() {
		return Boolean.valueOf(properties.getProperty(EMAP_AS_TRUE_MAP))
				.booleanValue();
	}

	/**
	 * Returns the value of the SET_DEFAULT_CASCADE_ON_MTO_MTM option, default
	 * is false
	 */
	public boolean isSetDefaultCascadeOnNonContainment() {
		String property = properties
				.getProperty(SET_DEFAULT_CASCADE_ON_NON_CONTAINMENT);
		if (!property.equals("")) {
			return Boolean.valueOf(property).booleanValue();
		}
		return !properties.getProperty(CASCADE_POLICY_ON_NON_CONTAINMENT)
				.equals("");
	}

	public boolean isSetCascadePolicyForNonContainment() {
		return !properties.getProperty(CASCADE_POLICY_ON_NON_CONTAINMENT)
				.equals("");
	}

	/**
	 * Returns true if the CASCADE_POLICY_ON_NON_CONTAINMENT property contains
	 * the merge cascade type
	 */
	public boolean isSetCascadeMergeOnNonContainment() {
		return isSetCascadeOnNonContainement(CascadeType.MERGE.getName());
	}

	/**
	 * Returns true if the CASCADE_POLICY_ON_NON_CONTAINMENT property contains
	 * the persist cascade type
	 */
	public boolean isSetCascadePersistOnNonContainment() {
		return isSetCascadeOnNonContainement(CascadeType.PERSIST.getName());
	}

	/**
	 * Returns true if the CASCADE_POLICY_ON_NON_CONTAINMENT property contains
	 * the refresh cascade type
	 */
	public boolean isSetCascadeRefreshOnNonContainment() {
		return isSetCascadeOnNonContainement(CascadeType.REFRESH.getName());
	}

	/**
	 * Returns true if the CASCADE_POLICY_ON_NON_CONTAINMENT property contains
	 * the given cascade type
	 */
	private boolean isSetCascadeOnNonContainement(String cascadeType) {
		return isSetCascade(properties
				.getProperty(CASCADE_POLICY_ON_NON_CONTAINMENT), cascadeType);
	}

	/**
	 * Returns the value of the SET_FOREIGN_KEY_NAME option, default is true
	 */
	public boolean isSetForeignKeyNames() {
		return Boolean.valueOf(properties.getProperty(SET_FOREIGN_KEY_NAME))
				.booleanValue();
	}

	/**
	 * Returns the value of the ALSO_MAP_AS_CLASS option, default is false
	 */
	public boolean isAlsoMapAsClass() {
		return Boolean.valueOf(properties.getProperty(ALSO_MAP_AS_CLASS))
				.booleanValue();
	}

	/**
	 * Returns the value of the MAP_EMBEDDABLE_AS_EMBEDDED option, default is
	 * false
	 */
	public boolean isMapEmbeddableAsEmbedded() {
		return Boolean.valueOf(
				properties.getProperty(MAP_EMBEDDABLE_AS_EMBEDDED))
				.booleanValue();
	}

	/**
	 * Returns true if the proxy annotation should be added automatically
	 */
	public boolean isSetProxy() {
		return Boolean.valueOf(properties.getProperty(SET_PROXY))
				.booleanValue();
	}

	/**
	 * Returns the value of the ALWAYS_MAP_LIST_AS_BAG option, default is false
	 */
	public boolean alwaysMapListAsBag() {
		return Boolean.valueOf(properties.getProperty(ALWAYS_MAP_LIST_AS_BAG))
				.booleanValue();
	}

	/**
	 * Returns the value of the MAP_ALL_LISTS_AS_IDBAG option, default is false
	 */
	public boolean alwaysMapListAsIdBag() {
		return Boolean.valueOf(properties.getProperty(MAP_ALL_LISTS_AS_IDBAG))
				.booleanValue();
	}

	/** Returns the value of the ADD_INDEX_FOR_FOREIGN_KEY option */
	public boolean isAddIndexForForeignKey() {
		return Boolean.valueOf(
				properties.getProperty(ADD_INDEX_FOR_FOREIGN_KEY))
				.booleanValue();
	}

	/**
	 * Returns the value of the UseJoinTableForNonContainedAssociations option,
	 * default is false
	 */
	public boolean isJoinTableForNonContainedAssociations() {
		return Boolean
				.valueOf(
						properties
								.getProperty(JOIN_TABLE_FOR_NON_CONTAINED_ASSOCIATIONS))
				.booleanValue();
	}

	/** Returns the value of the UseMappingFile option, default is false */
	public boolean isUseMappingFile() {
		return Boolean.valueOf(properties.getProperty(USE_MAPPING_FILE))
				.booleanValue();
	}

	/** Returns the value of the MAPPING_FILE_PATH option, default is "" */
	public String getMappingFilePath() {
		return properties.getProperty(MAPPING_FILE_PATH);
	}

	/** Returns the value of the id feature as primary key */
	public boolean isIDFeatureAsPrimaryKey() {
		return Boolean.valueOf(
				properties.getProperty(ID_FEATURE_AS_PRIMARY_KEY))
				.booleanValue();
	}

	/** Returns the value of the SET_GENERATED_VALUE_ON_ID_FEATURE option */
	public boolean isSetGeneratedValueOnIDFeature() {
		return Boolean.valueOf(
				properties.getProperty(SET_GENERATED_VALUE_ON_ID_FEATURE))
				.booleanValue();
	}

	/** Returns the value of the orphan delete on containment, default is true */
	public boolean isSetCascadeAllOnContainment() {
		if (!properties.getProperty(SET_CASCADE_ALL_ON_CONTAINMENT).equals("")) {
			return Boolean.valueOf(
					properties.getProperty(SET_CASCADE_ALL_ON_CONTAINMENT))
					.booleanValue();
		}
		return isSetCascadeOnContainement(CascadeType.ALL.getName());
	}

	/**
	 * Returns true if CASCADE_POLICY_ON_CONTAINMENT property contains the MERGE
	 * cascade type
	 */
	public boolean isSetCascadeMergeOnContainment() {
		return isSetCascadeOnContainement(CascadeType.MERGE.getName());
	}

	/**
	 * Returns true if CASCADE_POLICY_ON_CONTAINMENT property contains the
	 * PERSIST cascade type
	 */
	public boolean isSetCascadePersistOnContainment() {
		return isSetCascadeOnContainement(CascadeType.PERSIST.getName());
	}

	/**
	 * Returns true if CASCADE_POLICY_ON_CONTAINMENT property contains the
	 * REMOVE cascade type
	 */
	public boolean isSetCascadeRemoveOnContainment() {
		return isSetCascadeOnContainement(CascadeType.REMOVE.getName());
	}

	/**
	 * Returns true if CASCADE_POLICY_ON_CONTAINMENT property contains the
	 * REFRESH cascade type
	 */
	public boolean isSetCascadeRefreshOnContainment() {
		return isSetCascadeOnContainement(CascadeType.REFRESH.getName());
	}

	/**
	 * Returns true if CASCADE_POLICY_ON_CONTAINMENT property contains the given
	 * cascade type
	 */
	private boolean isSetCascadeOnContainement(String cascadeType) {
		return isSetCascade(properties
				.getProperty(CASCADE_POLICY_ON_CONTAINMENT), cascadeType);
	}

	/** Returns true if property contains the given cascade type */
	private boolean isSetCascade(String property, String cascadeType) {
		return Pattern.matches(".*\\b" + cascadeType.toUpperCase() + "\\b.*",
				property.toUpperCase());
	}

	/** Returns the value of the Optimistic option, default is true */
	public boolean isUseOptimisticLocking() {
		return Boolean.valueOf(properties.getProperty(OPTIMISTIC))
				.booleanValue();
	}

	/** Returns the value of the UpdateSchema option, default is true */
	public boolean isUpdateSchema() {
		return Boolean.valueOf(properties.getProperty(UPDATE_SCHEMA))
				.booleanValue();
	}

	/** Returns the value of the fetch containment eagerly, default is false */
	public boolean isFetchContainmentEagerly() {
		return Boolean.valueOf(
				properties.getProperty(FETCH_CONTAINMENT_EAGERLY))
				.booleanValue();
	}

	/** Is set entity automatically, default is true */
	public boolean isSetEntityAutomatically() {
		return Boolean
				.valueOf(properties.getProperty(SET_ENTITY_AUTOMATICALLY))
				.booleanValue();
	}

	/** Returns the inheritance mapping strategy, can be null */
	public String getInheritanceMapping() {
		return properties.getProperty(INHERITANCE_MAPPING);
	}

	/** Returns the value of the version column option, returns null if not set */
	public String getVersionColumnName() {
		return properties.getProperty(VERSION_COLUMN_NAME);
	}

	/** Returns the value of the naming strategy, default is lower case */
	public String getSQLCaseStrategy() {
		return properties.getProperty(SQL_CASE_STRATEGY);
	}

	/** Returns the value of the id column option, returns null if not set */
	public String getIdColumnName() {
		return properties.getProperty(ID_COLUMN_NAME);
	}

	/** Returns the value of the default id property */
	public String getDefaultIDFeatureName() {
		return properties.getProperty(DEFAULT_ID_FEATURE_NAME);
	}

	/** Return the sql escape character, default is ` */
	public String getSqlNameEscapeCharacter() {
		return properties.getProperty(SQL_NAME_ESCAPE_CHARACTER);
	}

	/** Returns the value of the join table naming strategy */
	public String getJoinTableNamingStrategy() {
		if (properties.get(JOIN_TABLE_NAMING_STRATEGY_OLD) != null) {
			log.warn("The option " + JOIN_TABLE_NAMING_STRATEGY_OLD
					+ " is deprecated, please use: "
					+ JOIN_TABLE_NAMING_STRATEGY);
			return properties.getProperty(JOIN_TABLE_NAMING_STRATEGY);
		}
		return properties.getProperty(JOIN_TABLE_NAMING_STRATEGY);
	}

	/** Returns the value of the join column naming strategy */
	public String getJoinColumnNamingStrategy() {
		return properties.getProperty(JOIN_COLUMN_NAMING_STRATEGY);
	}

	/**
	 * Returns the default second level caching strategy, default value is NONE
	 * (no second level caching).
	 */
	public String getDefaultCacheStrategy() {
		return properties.getProperty(DEFAULT_CACHE_STRATEGY);
	}

	/** Return the default varchar length */
	public int getDefaultVarCharLength() {
		return Integer.parseInt(properties.getProperty(DEFAULT_VARCHAR_LENGTH));
	}

	/** Are econtainer mappings (hibernate) disabled */
	public boolean isDisableEContainerMapping() {
		return Boolean.valueOf(
				properties.getProperty(DISABLE_ECONTAINER_MAPPING))
				.booleanValue();
	}

	/** Return the value of the MAX_COMMENT_LENGTH */
	public int getMaximumCommentLength() {
		final String colLength = properties.getProperty(MAX_COMMENT_LENGTH);
		try {
			final int maxLength = Integer.parseInt(colLength);
			return maxLength;
		} catch (NumberFormatException e) {
			log.error("Property " + MAXIMUM_SQL_NAME_LENGTH
					+ " as a non-integer value: " + colLength
					+ " value is ignored");
			return 0;
		}
	}

	/** Return the max. sql name length, or -1 if not set or illegal */
	public int getMaximumSqlNameLength() {
		final String colLength = properties
				.getProperty(MAXIMUM_SQL_NAME_LENGTH);
		try {
			final int maxLength = Integer.parseInt(colLength);
			if (maxLength == 0) {
				throw new TeneoException(
						"The option MAXIMUM_SQL_NAME_LENGTH has a value of zero. "
								+ "This will result in empty column and table names in the mapping! "
								+ "Please change this option to a more usable value.");
			}
			if (maxLength < 4 && maxLength > -1) {
				log.warn("The option MAXIMUM_SQL_NAME_LENGTH has a low value: "
						+ maxLength + ". Are you sure this is correct?");
			}
			return maxLength;
		} catch (NumberFormatException e) {
			log.error("Property " + MAXIMUM_SQL_NAME_LENGTH
					+ " as a non-integer value: " + colLength
					+ " value is ignored");
			return -1;
		}
	}

	/**
	 * Returns the value of the SQL_DISCRIMINATOR_VERSION_IMMUTABLE_ECLASS
	 * option.
	 */
	public Boolean isDiscriminatorVersionOnImmutableEClass() {
		return Boolean
				.valueOf(
						properties
								.getProperty(SQL_DISCRIMINATOR_VERSION_IMMUTABLE_ECLASS))
				.booleanValue();
	}

	/**
	 * Returns the path of the XML persistence mapping file or null if the
	 * property has not been defined.
	 */
	public String getPersistenceXmlPath() {
		return properties.getProperty(PERSISTENCE_XML);
	}

	/**
	 * Returns a boolean indication whether to ignore mapping EAnnotations.
	 */
	public boolean isIgnoreEAnnotations() {
		return Boolean.valueOf(properties.getProperty(IGNORE_EANNOTATIONS))
				.booleanValue();
	}

	/**
	 * Returns an array of all String constants.
	 * 
	 * @return
	 */
	public static String[] propertyNames() {
		final List<String> names = new ArrayList<String>();
		for (Field field : PersistenceOptions.class.getFields()) {
			try {
				if ((field.getModifiers() & Modifier.STATIC) > 0
						& field.getType().equals(String.class)) {
					final String value = (String) field.get(null);
					if (value.startsWith("teneo.")) {
						names.add(value);
					}
				}
			} catch (IllegalAccessException e) {
			}
		}
		Collections.sort(names);
		return names.toArray(new String[names.size()]);
	}

	public boolean getAlwaysVersion() {
		return Boolean.valueOf(properties.getProperty(ALWAYS_VERSION))
				.booleanValue();
	}

	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Creates the correct sql name strategy based on the String setting.
	 * 
	 * @Deprecated use the SQLNameStrategy and extensionManager concept
	 */
	public String getSQLNameStrategy() {
		return properties.getProperty(SQL_NAME_STRATEGY);
	}

	public String getUserXSDDateType() {
		return properties.getProperty(USER_XSDDATE_TYPE);
	}

	public String getUserXSDDateTime() {
		return properties.getProperty(USER_XSDDATETIME_TYPE);
	}

	public String getXSDDateClass() {
		return properties.getProperty(XSDDATE_CLASS);
	}

	public EContainerFeaturePersistenceStrategy getEContainerFeaturePersistenceStrategy() {
		String strategy = properties
				.getProperty(ECONTAINER_FEATURE_PERSISTENCE_STRATEGY);
		if (strategy == null) {
			throw new TeneoException(
					"Option ECONTAINER_FEATURE_PERSISTENCE_STRATEGY not set, please set it to one of: featureid, featurename, both");
		}
		EContainerFeaturePersistenceStrategy result = EContainerFeaturePersistenceStrategy
				.valueOf(strategy.toUpperCase());
		if (result == null) {
			throw new TeneoException(
					"Option ECONTAINER_FEATURE_PERSISTENCE_STRATEGY not set correctly ("
							+ strategy
							+ "), please set it to one of: featureid, featurename, both");
		}
		return result;
	}

	public enum EContainerFeaturePersistenceStrategy {
		FEATURENAME, FEATUREID, BOTH
	}
}