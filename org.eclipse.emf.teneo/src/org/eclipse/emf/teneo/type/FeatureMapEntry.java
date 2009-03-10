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
 *
 * </copyright>
 *
 * $Id: FeatureMapEntry.java,v 1.7 2008/04/11 23:43:43 mtaal Exp $
 */

package org.eclipse.emf.teneo.type;

import java.io.IOException;
import java.io.Serializable;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.teneo.EContainerRepairControl;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Is used to replace the EMF feature map entry with an entry which can be handled by the or layer.
 * 
 * The FeatureMap.Entry.Internal methods are handled through a delegate. Based on the efeature the
 * correct delegate is created.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.7 $
 */

public abstract class FeatureMapEntry implements FeatureMap.Entry.Internal, Serializable {
	private static final long serialVersionUID = 1L;

	/** The structural feature which defines which element this is */
	private EStructuralFeature eStructuralFeature;

	/** Path to the efeature for serialization support */
	private String eFeaturePath;

	/** And its value */
	private Object value;

	/** Keeps track if the class was initialized */
	private boolean initialized = false;

	/** The delegate which implements the inverse action */
	private InverseAction inverseAction;

	/**
	 * The featuremap to which we are connected. Is used to determine if entries have been added to
	 * another featuremap. This happens in copy actions.
	 */
	private FeatureMap.Internal owningMap;

	/** Constructor called by the storage layer, fields need to be set by calls to subclass */
	public FeatureMapEntry() {
	}

	/** Constructor called by the storage layer, fields need to be set by calls to subclass */
	public FeatureMapEntry(EStructuralFeature feature, Object val) {
		eStructuralFeature = feature;
		value = val;
		initialized = true;
		initializeSpecificImplementation();
		setInverseAction();
	}

	/** Takes care of serializing the efeature */
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		eFeaturePath = StoreUtil.structuralFeatureToString(eStructuralFeature);
		eStructuralFeature = null;
		out.defaultWriteObject();
	}

	/** Takes care of deserializing the efeature */
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		eStructuralFeature = StoreUtil.stringToStructureFeature(eFeaturePath);
	}

	/** Set the inverseaction delegate, must be called after the efeature is set */
	private void setInverseAction() {
		if (eStructuralFeature instanceof EReference) {
			final EReference eref = (EReference) eStructuralFeature;
			if (eref.getEOpposite() != null) {
				inverseAction = new BidirectionalInverseAction();
			} else if (eref.isContainment()) {
				inverseAction = new ContainmentInverseAction();
			} else {
				inverseAction = new InverseAction();
			}
		} else {
			inverseAction = new InverseAction();
		}
	}

	/** Sets the featuremap, is done when an entry is added to the featuremap */
	public void setFeatureMap(FeatureMap.Internal featureMap) {
		owningMap = featureMap;

		/*
		 * if (value != null && value instanceof InternalEObject && eStructuralFeature instanceof
		 * EReference && ((EReference)eStructuralFeature).isContainment()) {
		 * ((InternalEObject)value).eSetResource((Resource.Internal)owningMap.getEObject().eResource(),
		 * null); }
		 */
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

	/** Set the value from a previous entry */
	public void setEntry(FeatureMap.Entry entry) {
		eStructuralFeature = entry.getEStructuralFeature();
		value = entry.getValue();
		initialized = true; // needs to be set before the call to the subclass, otherwise infinite
							// looping
		initializeSpecificImplementation();
		setInverseAction();
	}

	/** Initializes this class from the values in the subclass */
	public void initialize() {
		eStructuralFeature = retrieveStructuralFeature(getStructuralFeatureDBID());
		value = getValueFromSpecificImplementation(eStructuralFeature);
		initialized = true;
		setInverseAction();
	}

	/**
	 * Needs to be implemented by the subclass, returns the value based on one of the fields set
	 * through the db
	 */
	protected abstract Object getValueFromSpecificImplementation(EStructuralFeature eFeature);

	/** Needs to be implemented by the subclass, returns the database id of the structural feature */
	protected abstract String getStructuralFeatureDBID();

	/**
	 * Is called by the super class to notify the subclass that it needs to set its fields based on
	 * the structural feature
	 */
	protected abstract void initializeSpecificImplementation();

	/** Method which needs to be called by the subclass to set the superclass members */
	public void setFields(EStructuralFeature structuralFeature, Object structuralValue) {
		eStructuralFeature = structuralFeature;
		value = structuralValue;
		initialized = true; // do this before the call to the subclass
		// initialize the subclass so that the fields are stored in the db
		initializeSpecificImplementation();
		setInverseAction();
	}

	/** Returns structural feature */
	public EStructuralFeature getEStructuralFeature() {
		if (!initialized) {
			initialize();
		}

		return eStructuralFeature;
	}

	/** Returns the value */
	public Object getValue() {
		if (!initialized) {
			initialize();
		}

		return value;
	}

	/**
	 * Returns the string which is used to store the unique identification of this structuralfeature
	 * in the db
	 */
	protected String createStructuralFeatureDBID() {
		return StoreUtil.structuralFeatureToString(getEStructuralFeature());
	}

	/** Gets a structuralfeature on the basis of the passed id */
	protected EStructuralFeature retrieveStructuralFeature(String dbid) {
		return StoreUtil.stringToStructureFeature(dbid);
	}

	/**
	 * Checks if a certain feature has a certain name or that its group (if present) has this name,
	 * in which case it is also set to true.
	 */
	protected boolean featureForField(String name) {
		if (eStructuralFeature.getName().compareTo(name) == 0) {
			return true;
		}

		// check the group feature
		final EStructuralFeature groupFeature = ExtendedMetaData.INSTANCE.getGroup(eStructuralFeature);
		if (groupFeature != null && groupFeature.getName().compareTo(name) == 0) {
			return true;
		}

		final EStructuralFeature affiliatedFeature = ExtendedMetaData.INSTANCE.getAffiliation(eStructuralFeature);
		if (affiliatedFeature != null && affiliatedFeature.getName().compareTo(name) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Sets the container property of the value if the value is an EObject and the feature is a
	 * containment feature.
	 */
	public void setContainer(InternalEObject owner) {
		if (!initialized) {
			initialize();
		}

		if (value != null && value instanceof InternalEObject && eStructuralFeature instanceof EReference &&
				((EReference) eStructuralFeature).isContainment()) {
			EContainerRepairControl.setContainer(owner, (InternalEObject) value, eStructuralFeature);
		}
	}

	/** Code copied from FeatureMapUtil.EntryImpl */
	@Override
	public boolean equals(Object that) {
		if (!initialized) {
			initialize();
		}

		if (this == that) {
			return true;
		} else if (!(that instanceof FeatureMap.Entry)) {
			return false;
		} else {
			FeatureMap.Entry entry = (FeatureMap.Entry) that;
			return entry.getEStructuralFeature() == eStructuralFeature &&
					(value == null ? entry.getValue() == null : value.equals(entry.getValue()));
		}
	}

	/** Code copied from FeatureMapUtil.EntryImpl */
	@Override
	public int hashCode() {
		/*
		 * Used to create a hashcode which maps all instances of one class to the same hashcode Is
		 * required because the normal hashcode method (see commented out part below) resulted in
		 * null-pointer exceptions in hibernate because the content of the entry was used for
		 * determining the hashcode while the object was not initialized from the db
		 */
		return this.getClass().hashCode();
		/*
		 * if (!initialized) initialize();
		 * 
		 * return eStructuralFeature.hashCode() ^ (value == null ? 0 : value.hashCode());
		 */
	}

	/** Code copied from FeatureMapUtil.EntryImpl */
	@Override
	public String toString() {
		if (!initialized) {
			initialize();
		}

		String prefix = eStructuralFeature.getEContainingClass().getEPackage().getNsPrefix();
		eStructuralFeature.getName();
		return (prefix != null && prefix.length() != 0 ? prefix + ":" + eStructuralFeature.getName()
				: eStructuralFeature.getName()) +
				"=" + value;
	}

	/** Create copy with same feature and different value */
	public Internal createEntry(InternalEObject value) {
		return createEntry((Object) value);
	}

	/** Create copy with same feature and different value */
	public abstract Internal createEntry(Object value);

	/** Do inverse action */
	public NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications) {
		return inverseAction.inverseAdd(owner, featureID, notifications);
	}

	/** Do inverse action */
	public NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID,
			NotificationChain notifications) {
		return inverseAction.inverseAdd(owner, otherEnd, featureID, notifications);
	}

	/** Do inverse action */
	public NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications) {
		return inverseAction.inverseRemove(owner, featureID, notifications);
	}

	/** Do inverse action */
	public NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID,
			NotificationChain notifications) {
		return inverseAction.inverseRemove(owner, otherEnd, featureID, notifications);
	}

	/** Validate type of object against the type of the efeature */
	public void validate(Object value) {
		if (value != null && !eStructuralFeature.getEType().isInstance(value)) {
			String valueClass =
					value instanceof EObject ? ((EObject) value).eClass().getName() : value.getClass().getName();
			throw new ClassCastException("The feature '" + eStructuralFeature.getName() + "'s type '" +
					eStructuralFeature.getEType().getName() + "' does not permit a value of type '" + valueClass + "'");
		}
	}

	/** Internal class to handle inverse actions */
	private class InverseAction {

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseAdd(InternalEObject owner, int featureID, NotificationChain notifications) {
			return inverseAdd(owner, value, featureID, notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseRemove(InternalEObject owner, int featureID, NotificationChain notifications) {
			return inverseRemove(owner, value, featureID, notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID,
				NotificationChain notifications) {
			return notifications;
		}

		/** Handles inverse action, differs on the basis of the feature type */
		public NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID,
				NotificationChain notifications) {
			return notifications;
		}

		/** validate the type of the value with the type expected by the efeature */
		public void validate(Object value) {
			if (value != null && !eStructuralFeature.getEType().isInstance(value)) {
				String valueClass =
						value instanceof EObject ? ((EObject) value).eClass().getName() : value.getClass().getName();
				throw new ClassCastException("The feature '" + eStructuralFeature.getName() + "'s type '" +
						eStructuralFeature.getEType().getName() + "' does not permit a value of type '" + valueClass +
						"'");
			}
		}
	}

	/** Containment Inverse Action */
	private class ContainmentInverseAction extends InverseAction {

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID,
				NotificationChain notifications) {
			return inverseAdd(owner, (InternalEObject) value, featureID, notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID,
				NotificationChain notifications) {
			return inverseRemove(owner, (InternalEObject) value, featureID, notifications);
		}

		/** Does inverse action on other end */
		private NotificationChain inverseAdd(InternalEObject owner, InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				int containmentFeatureID = owner.eClass().getFeatureID(eStructuralFeature);
				notifications =
						otherEnd.eInverseAdd(owner, InternalEObject.EOPPOSITE_FEATURE_BASE -
								(containmentFeatureID == -1 ? featureID : containmentFeatureID), null, notifications);
			}

			return notifications;
		}

		/** Does inverse action on other end */
		private NotificationChain inverseRemove(InternalEObject owner, InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				int containmentFeatureID = owner.eClass().getFeatureID(eStructuralFeature);
				notifications =
						otherEnd.eInverseRemove(owner, InternalEObject.EOPPOSITE_FEATURE_BASE -
								(containmentFeatureID == -1 ? featureID : containmentFeatureID), null, notifications);
			}

			return notifications;
		}
	}

	/** Bidirectional feature value */
	private class BidirectionalInverseAction extends InverseAction {

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseAdd(InternalEObject owner, Object otherEnd, int featureID,
				NotificationChain notifications) {
			return inverseAdd(owner, (InternalEObject) value, featureID, notifications);
		}

		/** Handles inverse action, differs on the basis of the feature type */
		@Override
		public NotificationChain inverseRemove(InternalEObject owner, Object otherEnd, int featureID,
				NotificationChain notifications) {
			return inverseRemove(owner, (InternalEObject) value, featureID, notifications);
		}

		/** Does inverse action on other end */
		private final NotificationChain inverseAdd(InternalEObject owner, InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				notifications =
						otherEnd.eInverseAdd(owner, otherEnd.eClass().getFeatureID(
							((EReference) eStructuralFeature).getEOpposite()), null, notifications);
			}

			return notifications;
		}

		/** Does inverse action on other end */
		private final NotificationChain inverseRemove(InternalEObject owner, InternalEObject otherEnd, int featureID,
				NotificationChain notifications) {
			if (otherEnd != null) {
				notifications =
						otherEnd.eInverseRemove(owner, otherEnd.eClass().getFeatureID(
							((EReference) eStructuralFeature).getEOpposite()), null, notifications);
			}
			return notifications;
		}
	}
}