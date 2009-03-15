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
 * $Id: HibernateFeatureMapEntry.java,v 1.9 2009/03/15 08:09:22 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.elist;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.teneo.EContainerRepairControl;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Is used to replace the EMF feature map entry with an entry which can be
 * handled by the or layer.
 * 
 * The special feature of this entry is that all allowed features can be set,
 * internally it keeps a list of feature value pairs. Only one of these pairs is
 * the valid one, which one is determined by the value of the eStructuralFeature
 * member.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.9 $
 */

public class HibernateFeatureMapEntry implements FeatureMap.Entry.Internal,
		Serializable {

	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 3946138277481892125L;

	/**
	 * Gets an 'normal' FeatureMap.Entry and if it is not a FeatureMapEntry
	 * replaces it with a specific implementation.
	 */
	public static HibernateFeatureMapEntry replaceEntry(Object obj,
			FeatureMap.Internal owningMap) {
		// do special check, in case the featuremap entry does not need to be
		// changed
		if (obj instanceof HibernateFeatureMapEntry) {
			final HibernateFeatureMapEntry fmEntry = (HibernateFeatureMapEntry) obj;

			// return the entry if it is not yet set, in this case it does not
			// yet belong to a
			// featuremap and can be used. This happens with a featuremap which
			// has already been
			// replaced.
			if (!fmEntry.isFeatureMapSet()
					|| fmEntry.belongsToFeatureMap(owningMap)) {
				return fmEntry;
			}
		}
		HibernateFeatureMapEntry entry = new HibernateFeatureMapEntry();
		entry.setEntry((FeatureMap.Entry) obj, owningMap);
		return entry;
	}

	/**
	 * Replaces standard FeatureMap.Entry with a FeatureMapEntry for a
	 * collection
	 */
	public static Collection<HibernateFeatureMapEntry> replaceEntryAll(
			Collection<FeatureMap.Entry> c, Class<?> replaceByType,
			FeatureMap.Internal owningMap) {
		final ArrayList<HibernateFeatureMapEntry> newEntries = new ArrayList<HibernateFeatureMapEntry>();
		final Iterator<FeatureMap.Entry> it = c.iterator();
		while (it.hasNext()) {
			newEntries.add(replaceEntry(it.next(), owningMap));
		}
		return newEntries;
	}

	/** Creates an entry with the correct type */
	public static FeatureMap.Entry createEntry(EStructuralFeature feature,
			Object value, FeatureMap.Internal owningMap) {
		HibernateFeatureMapEntry entry = new HibernateFeatureMapEntry();
		entry.setFeatureValue(feature, value, owningMap);
		return entry;
	}

	/**
	 * Method which creates a list of entries based on one feature and multiple
	 * values
	 */
	public static Collection<FeatureMap.Entry> createEntryAll(
			EStructuralFeature feature, Collection<?> values,
			FeatureMap.Internal owningMap) {
		final ArrayList<FeatureMap.Entry> entries = new ArrayList<FeatureMap.Entry>();
		final Iterator<?> it = values.iterator();
		while (it.hasNext()) {
			entries.add(createEntry(feature, it.next(), owningMap));
		}
		return entries;
	}

	/** The structural feature which defines which element this is */
	private EStructuralFeature eStructuralFeature;

	/** To store the efeature during serialization */
	private String eFeaturePath;

	/**
	 * The featuremap to which we are connected. Is used to determine if entries
	 * have been added to another featuremap. This happens in copy actions.
	 */
	private FeatureMap.Internal owningMap;

	/** The feature value map */
	private ArrayList<FeatureValue> featureValues = new ArrayList<FeatureValue>();

	/**
	 * The entity name of this entry, is filled when the object is read from the
	 * db
	 */
	private String entityName = null;

	/**
	 * Constructor called by the storage layer, fields need to be set by calls
	 * to subclass
	 */
	public HibernateFeatureMapEntry() {
	}

	/** Sets the featuremap, is done when an entry is added to the featuremap */
	public void setFeatureMap(FeatureMap.Internal featureMap) {
		owningMap = featureMap;
		if (featureMap != null) {
			entityName = StoreUtil.getEntityName(featureMap
					.getEStructuralFeature());
		}
	}

	/** Unsets the featuremap, is done when an entry is removed */
	public void unsetFeatureMap() {
		owningMap = null;
	}

	/** Is true if this featureMap already belongs to a Map */
	public boolean isFeatureMapSet() {
		return owningMap != null;
	}

	/** Is true if this featureMap already belongs to the passed map */
	public boolean belongsToFeatureMap(FeatureMap.Internal fm) {
		return owningMap == fm; // object equality!
	}

	/** Takes care of serializing the efeature */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		eFeaturePath = StoreUtil.structuralFeatureToString(eStructuralFeature);
		eStructuralFeature = null;
		out.defaultWriteObject();
	}

	/** Takes care of deserializing the efeature */
	private void readObject(java.io.ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		eStructuralFeature = StoreUtil.stringToStructureFeature(eFeaturePath);
	}

	/** Set the value from a previous entry */
	public void setEntry(FeatureMap.Entry entry, FeatureMap.Internal owningMap) {
		eStructuralFeature = entry.getEStructuralFeature();
		featureValues.add(createFeatureValue(eStructuralFeature, entry
				.getValue()));
		setFeatureMap(owningMap);
	}

	/** Returns structural feature */
	public EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	/**
	 * @param structuralFeature
	 *            the eStructuralFeature to set
	 */
	private void setEStructuralFeature(EStructuralFeature structuralFeature) {
		eStructuralFeature = structuralFeature;
	}

	/** Return the string repr. of the feature */
	public String getFeatureURI() {
		return StoreUtil.structuralFeatureToString(getEStructuralFeature());
	}

	/** Set the feature on the basis of the string */
	public void setEStructuralFeature(String featureURI) {
		setEStructuralFeature(StoreUtil.stringToStructureFeature(featureURI));
	}

	/** Returns the value */
	public Object getValue() {
		return getValue(getEStructuralFeature());
	}

	/**
	 * Add a feature value combination to the entry, only one of these values is
	 * the valid one but the other nullable values are stored in the db
	 */
	public void addFeatureValue(EStructuralFeature feature, Object value) {
		featureValues.add(createFeatureValue(feature, value));
	}

	/** Sets the exact feature value for this entry */
	public void setFeatureValue(EStructuralFeature feature, Object value,
			FeatureMap.Internal owningMap) {
		featureValues.add(createFeatureValue(feature, value));
		setEStructuralFeature(feature);
		setFeatureMap(owningMap);
	}

	/** Returns the value for a specific feature */
	public Object getValue(EStructuralFeature feature) {
		for (FeatureValue fv : featureValues) {
			if (fv.matchesFeature(feature)) {
				return fv.getValue();
			}
		}
		return null; // TODO: maybe throw error?
	}

	/** get the real feature value */
	private FeatureValue getFeatureValue() {
		final EStructuralFeature feature = getEStructuralFeature();

		AssertUtil.assertTrue("Feature is not set", feature != null);

		for (FeatureValue fv : featureValues) {
			if (fv.matchesFeature(feature)) {
				return fv;
			}
		}

		// can not get here, see assertion above
		return null;
	}

	/**
	 * Sets the container property of the value if the value is an EObject and
	 * the feature is a containment feature.
	 */
	public void setContainer(InternalEObject owner) {
		final Object value = getValue();
		if (value != null && value instanceof InternalEObject
				&& eStructuralFeature instanceof EReference
				&& ((EReference) eStructuralFeature).isContainment()) {
			EContainerRepairControl.setContainer(owner,
					(InternalEObject) value, eStructuralFeature);
		}
	}

	/** Code copied from FeatureMapUtil.EntryImpl */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		} else if (!(that instanceof FeatureMap.Entry)) {
			return false;
		} else {
			final FeatureMap.Entry entry = (FeatureMap.Entry) that;
			final Object value = getValue();
			return entry.getEStructuralFeature() == eStructuralFeature
					&& (value == null ? entry.getValue() == null : value
							.equals(entry.getValue()));
		}
	}

	/** Code copied from FeatureMapUtil.EntryImpl */
	@Override
	public int hashCode() {
		/*
		 * Used to create a hashcode which maps all instances of one class to
		 * the same hashcode Is required because the normal hashcode method (see
		 * hashcode impl in emf EntryImpl) resulted in null-pointer exceptions
		 * in hibernate because the content of the entry was used for
		 * determining the hashcode while the object was not initialized from
		 * the db
		 */
		return this.getClass().hashCode();
	}

	/** Code copied from FeatureMapUtil.EntryImpl */
	@Override
	public String toString() {
		String prefix = eStructuralFeature.getEContainingClass().getEPackage()
				.getNsPrefix();
		eStructuralFeature.getName();
		return (prefix != null && prefix.length() != 0 ? prefix + ":"
				+ eStructuralFeature.getName() : eStructuralFeature.getName())
				+ "=" + getValue();
	}

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param entityName
	 *            the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	/** Create the correct feature value type */
	private FeatureValue createFeatureValue(EStructuralFeature feature,
			Object value) {
		if (feature instanceof EReference
				&& ((EReference) feature).getEOpposite() != null) {
			return new InverseFeatureValue(feature, value);
		} else if (feature instanceof EReference
				&& ((EReference) feature).isContainment()) {
			return new ContainmentFeatureValue(feature, value);
		} else {
			return new FeatureValue(feature, value);
		}
	}

	/** Create a copy of this entry with a different value */
	public Internal createEntry(InternalEObject value) {
		HibernateFeatureMapEntry hfm = new HibernateFeatureMapEntry();
		hfm.setFeatureValue(getEStructuralFeature(), value, owningMap);
		return hfm;
	}

	/**
	 * Create a copy of this entry with a different value, calls
	 * createEntry(InternalEObject value)
	 */
	public Internal createEntry(Object value) {
		return createEntry((InternalEObject) value);
	}

	/** Does inverse action on other end */
	public final NotificationChain inverseAdd(InternalEObject owner,
			int featureID, NotificationChain notifications) {
		return getFeatureValue().inverseAdd(owner, getValue(), featureID,
				notifications);
	}

	/** Does inverse action on other end */
	public final NotificationChain inverseRemove(InternalEObject owner,
			int featureID, NotificationChain notifications) {
		return getFeatureValue().inverseRemove(owner, getValue(), featureID,
				notifications);
	}

	/** Does inverse action on other end */
	public final NotificationChain inverseAdd(InternalEObject owner,
			Object otherEnd, int featureID, NotificationChain notifications) {
		return getFeatureValue().inverseAdd(owner, getValue(), featureID,
				notifications);
	}

	/** Does inverse action on other end */
	public NotificationChain inverseRemove(InternalEObject owner,
			Object otherEnd, int featureID, NotificationChain notifications) {
		return getFeatureValue().inverseRemove(owner, otherEnd, featureID,
				notifications);
	}

	/** validate the type of the value with the type expected by the efeature */
	public void validate(Object value) {
		getFeatureValue().validate(value);
	}

	/** Class to store feature value pairs together with their validator */
	private class FeatureValue implements Serializable {

		/**
		 * Generated Serial Version ID
		 */
		private static final long serialVersionUID = 3665363921316852811L;

		/** The feature */
		protected EStructuralFeature feature;

		/** The featurepath, is used during serialization */
		private String featurePath;

		/** Its value (can be null) */
		protected final Object value;

		/** Constructor */
		private FeatureValue(EStructuralFeature feature, Object value) {
			this.feature = feature;
			this.value = value;
		}

		/** Takes care of serializing the efeature */
		private void writeObject(java.io.ObjectOutputStream out)
				throws IOException {
			featurePath = StoreUtil.structuralFeatureToString(feature);
			feature = null;
			out.defaultWriteObject();
		}

		/** Takes care of deserializing the efeature */
		private void readObject(java.io.ObjectInputStream in)
				throws IOException, ClassNotFoundException {
			in.defaultReadObject();
			feature = StoreUtil.stringToStructureFeature(featurePath);
		}

		/**
		 * Returns true if this feature value corresponds to the passed feature
		 * (taking into account substitution groups
		 */
		private boolean matchesFeature(EStructuralFeature eFeature) {
			if (feature.equals(eFeature)) {
				return true;
			}

			// compare on the basis of the affiliates (substitutiongroup)
			final EStructuralFeature aff1 = ExtendedMetaData.INSTANCE
					.getAffiliation(owningMap.getEObject().eClass(), feature);
			final EStructuralFeature aff2 = ExtendedMetaData.INSTANCE
					.getAffiliation(owningMap.getEObject().eClass(), eFeature);
			if (aff1 != null && aff2 != null && aff1 == aff2) {
				return true;
			}
			return false;
		}

		/** Returns the value */
		private Object getValue() {
			return value;
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseAdd(InternalEObject owner,
				int featureID, NotificationChain notifications) {
			return inverseAdd(owner, value, featureID, notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseRemove(InternalEObject owner,
				int featureID, NotificationChain notifications) {
			return inverseRemove(owner, value, featureID, notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseAdd(InternalEObject owner,
				Object otherEnd, int featureID, NotificationChain notifications) {
			return notifications;
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseRemove(InternalEObject owner,
				Object otherEnd, int featureID, NotificationChain notifications) {
			return notifications;
		}

		/**
		 * validate the type of the value with the type expected by the efeature
		 */
		public void validate(Object value) {
			if (value != null
					&& !eStructuralFeature.getEType().isInstance(value)) {
				String valueClass = value instanceof EObject ? ((EObject) value)
						.eClass().getName()
						: value.getClass().getName();
				throw new ClassCastException("The feature '"
						+ eStructuralFeature.getName() + "'s type '"
						+ eStructuralFeature.getEType().getName()
						+ "' does not permit a value of type '" + valueClass
						+ "'");
			}
		}
	}

	/** Containment feature value */
	private class ContainmentFeatureValue extends FeatureValue {

		/**
		 * Generated serial id
		 */
		private static final long serialVersionUID = -5915172909939056481L;

		/** Constructor */
		private ContainmentFeatureValue(EStructuralFeature feature, Object value) {
			super(feature, value);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseAdd(InternalEObject owner,
				Object otherEnd, int featureID, NotificationChain notifications) {
			return inverseAdd(owner, (InternalEObject) value, featureID,
					notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseRemove(InternalEObject owner,
				Object otherEnd, int featureID, NotificationChain notifications) {
			return inverseRemove(owner, (InternalEObject) value, featureID,
					notifications);
		}

		/** Does inverse action on other end */
		private NotificationChain inverseAdd(InternalEObject owner,
				InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				int containmentFeatureID = owner.eClass().getFeatureID(
						eStructuralFeature);
				notifications = otherEnd.eInverseAdd(owner,
						InternalEObject.EOPPOSITE_FEATURE_BASE
								- (containmentFeatureID == -1 ? featureID
										: containmentFeatureID), null,
						notifications);
			}

			return notifications;
		}

		/** Does inverse action on other end */
		private NotificationChain inverseRemove(InternalEObject owner,
				InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				int containmentFeatureID = owner.eClass().getFeatureID(
						eStructuralFeature);
				notifications = otherEnd.eInverseRemove(owner,
						InternalEObject.EOPPOSITE_FEATURE_BASE
								- (containmentFeatureID == -1 ? featureID
										: containmentFeatureID), null,
						notifications);
			}

			return notifications;
		}
	}

	/** Bidirectional feature value */
	private class InverseFeatureValue extends FeatureValue {

		/**
		 * Generated Serial Version ID
		 */
		private static final long serialVersionUID = 7207038502480577523L;

		/** Constructor */
		private InverseFeatureValue(EStructuralFeature feature, Object value) {
			super(feature, value);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseAdd(InternalEObject owner,
				Object otherEnd, int featureID, NotificationChain notifications) {
			return inverseAdd(owner, (InternalEObject) value, featureID,
					notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseRemove(InternalEObject owner,
				Object otherEnd, int featureID, NotificationChain notifications) {
			return inverseRemove(owner, (InternalEObject) value, featureID,
					notifications);
		}

		/** Does inverse action on other end */
		private final NotificationChain inverseAdd(InternalEObject owner,
				InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				notifications = otherEnd.eInverseAdd(owner, otherEnd.eClass()
						.getFeatureID(
								((EReference) eStructuralFeature)
										.getEOpposite()), null, notifications);
			}

			return notifications;
		}

		/** Does inverse action on other end */
		private final NotificationChain inverseRemove(InternalEObject owner,
				InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				notifications = otherEnd.eInverseRemove(owner, otherEnd
						.eClass().getFeatureID(
								((EReference) eStructuralFeature)
										.getEOpposite()), null, notifications);
			}
			return notifications;
		}
	}
}