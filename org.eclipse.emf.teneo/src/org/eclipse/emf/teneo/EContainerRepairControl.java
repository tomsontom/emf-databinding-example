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
 * $Id: EContainerRepairControl.java,v 1.10 2009/03/15 23:25:09 mtaal Exp $
 */

package org.eclipse.emf.teneo;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.teneo.mapping.elist.PersistableDelegateList;
import org.eclipse.emf.teneo.mapping.elist.PersistableEList;
import org.eclipse.emf.teneo.mapping.elist.PersistableFeatureMap;

/**
 * Supports the repair of the eContainer and resource setting of child objects
 * when an object is loaded from the backing store.
 * 
 * Repair of the eContainer is required in two distinct cases: 1) 1:1 relation:
 * in this case the repair is implemented in the caching mechanism. This was the
 * correct location because in jpox an object is added to the level 1 cache just
 * after it is retrieved from the db and before it is passed on to the
 * requesting application. 2) 1:n relation: in this case the EListWrapper knows
 * that a containment relation is being loaded and calls the equivalent methods
 * here.
 * 
 * Note that both cases need to take into account two-way relatiofns. For
 * two-way relations the featureid of the opposing ereferencing is used. For
 * one-way relations emf apparently works with negative featureid's.
 * 
 * This class also supports caching so that the system can quickly determine if
 * for a certain class eContainers need to be set in child objects.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.10 $
 */

public class EContainerRepairControl {
	/** The logger */
	private static Log log = LogFactory.getLog(EContainerRepairControl.class);

	/** Hashmap of classes for which no repair is required */
	private static final Hashtable<Class<?>, Class<?>> norepairRequired = new Hashtable<Class<?>, Class<?>>();

	/** Hashmap of repair controls for a certain class */
	private static final Hashtable<Class<?>, List<RepairControl>> repair = new Hashtable<Class<?>, List<RepairControl>>();

	/**
	 * Recursively sets the resource of the object and all its referenced
	 * objects, only if the object has a resource which is not set and does not
	 * have a container.
	 */
	public static void setEResourceToAlLContent(InternalEObject start,
			Resource res) {
		for (EStructuralFeature estruct : start.eClass()
				.getEAllStructuralFeatures()) {
			if (estruct instanceof EReference) {
				final EReference eref = (EReference) estruct;
				if (eref.isMany()) {
					final EList<?> list = (EList<?>) start.eGet(eref);
					if (list == null) {
						continue;
					}
					if ((list instanceof PersistableEList)
							&& !((PersistableEList<?>) list).isLoaded()) {
						continue;
					}
					if ((list instanceof PersistableFeatureMap)
							&& !((PersistableFeatureMap) list).isLoaded()) {
						continue;
					}
					for (int i = 0; i < list.size(); i++) {
						final InternalEObject child = (InternalEObject) list
								.get(i);
						if (child.eResource() == null) // no container
						{
							setResource(child, new ArrayList<EObject>(),
									(Resource.Internal) res);
						}
					}
				} else {
					final InternalEObject child = (InternalEObject) start
							.eGet(eref);
					if (child != null && child.eResource() == null) {
						setResource(child, new ArrayList<EObject>(),
								(Resource.Internal) res);
					}
				}
			}
		}
	}

	/** Sets the resource on an object or if it has a container on its container */
	private static void setResource(InternalEObject eobj,
			ArrayList<EObject> objs, Resource.Internal res) {
		// been here go away
		if (objs.contains(eobj)) {
			return;
		}

		// set the resource here or at the container
		if (eobj.eResource() == null) {
			if (eobj.eContainer() == null) {
				eobj.eSetResource(res, null);
			} else {
				objs.add(eobj);
				setResource((InternalEObject) eobj.eContainer(), objs, res);
			}
		}
	}

	/** Method to repair the eContainer of the child object of this object */
	public static void repair(Object owner) {
		if (log.isDebugEnabled()) {
			log.debug("Repairing container relations of children of: "
					+ owner.getClass().getName());
		}

		if (!(owner instanceof InternalEObject)) {
			return;
		}

		if (norepairRequired.get(owner.getClass()) != null) {
			return;
		}

		List<RepairControl> repairList = repair.get(owner.getClass());

		if (repairList == null) {
			repairList = buildRepairList((InternalEObject) owner);
		}

		if (log.isDebugEnabled() && repairList.size() > 0) {
			log.debug("Repairing container relations of children of: "
					+ owner.getClass().getName());
		}

		for (int i = 0; i < repairList.size(); i++) {
			RepairControl repairControl = repairList.get(i);
			if (log.isDebugEnabled()) {
				log.debug("Repairing reference "
						+ repairControl.container.getName() + " to child "
						+ repairControl.childClass.getName());
			}

			repairControl.repair((InternalEObject) owner);
		}
	}

	/**
	 * Convenience method to just set the container directly for an object, this
	 * method does not cascade down. The featureid is the id of the feature of
	 * the owner which contains the child. The feature id is corrected in the
	 * method.
	 */
	public static void setContainer(InternalEObject owner,
			InternalEObject child, EStructuralFeature estruct) {
		if (child.eContainer() == owner) {
			return;
		}

		final int featureID;
		if (estruct instanceof EReference
				&& ((EReference) estruct).getEOpposite() != null) {
			featureID = child.eClass().getFeatureID(
					((EReference) estruct).getEOpposite());
		} else {

			featureID = InternalEObject.EOPPOSITE_FEATURE_BASE
					- owner.eClass().getFeatureID(estruct);
		}
		child.eBasicSetContainer(owner, featureID, null);
	}

	/**
	 * Method to repair the eContainer of the child object of this object. Note
	 * the featureid is internally translated to an econtainer id, nl. subtract
	 * from EOPPOSITE_FEATURE_BASE
	 */
	public static void repair(Object owner, Object child,
			EStructuralFeature estruct) {

		if (!(owner instanceof InternalEObject)) {
			return;
		}
		if (!(child instanceof InternalEObject)) {
			return;
		}

		final int correctedFeatureID;
		if (estruct instanceof EReference
				&& ((EReference) estruct).getEOpposite() != null) {
			correctedFeatureID = ((InternalEObject) child).eClass()
					.getFeatureID(((EReference) estruct).getEOpposite());
		} else {
			correctedFeatureID = InternalEObject.EOPPOSITE_FEATURE_BASE
					- ((InternalEObject) owner).eClass().getFeatureID(estruct);
		}

		if (norepairRequired.get(owner.getClass()) != null) {
			return;
		}

		List<RepairControl> repairList = repair.get(owner.getClass());

		if (repairList == null) {
			repairList = buildRepairList((InternalEObject) owner);
		}

		if (log.isDebugEnabled() && repairList.size() > 0) {
			log.debug("Repairing container relations of children of: "
					+ owner.getClass().getName());
		}

		for (int i = 0; i < repairList.size(); i++) {
			RepairControl repairControl = repairList.get(i);
			if (repairControl.getFeatureID() == correctedFeatureID
					&& ((Class<?>) repairControl.childClass)
							.isAssignableFrom(child.getClass())) {
				repairControl.repair((InternalEObject) owner,
						(InternalEObject) child);
				return;
			}
		}
	}

	/** Builds a repair control list for an object */
	private static List<RepairControl> buildRepairList(InternalEObject owner) {
		final ArrayList<RepairControl> result = new ArrayList<RepairControl>();
		for (EStructuralFeature estruct : owner.eClass()
				.getEAllStructuralFeatures()) {
			if (estruct instanceof EReference) {
				final EReference eref = (EReference) estruct;
				if (eref.isContainment()) {
					// now check if we are two or not
					if (eref.getEOpposite() != null) {
						result.add(new TwoWayContainer(eref, eref
								.getEOpposite()));
					} else {
						result.add(new OneWayContainer(eref));
					}
				}
			}
		}

		if (result.size() == 0) {
			norepairRequired.put(owner.getClass(), owner.getClass());
		} else {
			repair.put(owner.getClass(), result);
		}
		return result;
	}

	/** Abstract class for repairing containers */
	private static abstract class RepairControl {
		/** The ereference of the owner which contains the childs */
		private final EReference container;

		/** Some shortcuts for spead */
		private final Class<?> childClass;

		/** Featureid set as container */
		private final int featureID;

		/** Constructor */
		RepairControl(EReference containerReference, int myFeatureID) {
			container = containerReference;
			childClass = container.getEType().getInstanceClass();
			featureID = myFeatureID;
		}

		/** Returns the feature id of this containment relation */
		public int getFeatureID() {
			return featureID;
		}

		/** Repairs all containers of the owner */
		void repair(InternalEObject owner) {
			// The container repair of a list is done through the repair(owner,
			// child) method,
			// directly in the elist.doLoadFromStore method
			if (container.isMany()) {
				return;
			}

			final Object containedObject = owner.eGet(container);
			if (containedObject == null) // not set
			{
				return;
			}

			// no list should be caught in the first line
			assert (!(containedObject instanceof PersistableDelegateList));

			/*
			 * if (containedObject instanceof JPOXEList) { if
			 * (((JPOXEList)containedObject).getOwner() == owner) return;
			 * 
			 * throw new StoreJPOXEmfException("Owner of containerobject is
			 * different from passed owner, " + "this should have been solved in
			 * the elist" + containedObject.getClass() + "/" +
			 * owner.getClass().getName() + "/" + container.getName()); }
			 */

			if (!(containedObject instanceof InternalEObject)) {
				return;
			}

			final InternalEObject containedEObject = (InternalEObject) containedObject;
			if (containedEObject.eContainer() == owner) {
				return; // already set?
			}

			if (log.isDebugEnabled()) {
				log.debug("Set container of child "
						+ containedObject.getClass().getName()
						+ " containerfield " + container.getName());
			}

			// and set it
			containedEObject.eBasicSetContainer(owner, featureID, null);

			// also repair the resource if applicable!
			/*
			 * if (containedObject instanceof InternalEObject) { final
			 * InternalEObject eobj = (InternalEObject)containedObject; if
			 * (eobj.eResource() != owner.eResource()) {
			 * log.debug("Set resource of eobj " + eobj.getClass().getName() +
			 * " to resource " + owner.eResource().getURI());
			 * eobj.eSetResource((Resource.Internal)owner.eResource(), null); }
			 * }
			 */

			// and also do its children
			EContainerRepairControl.repair(containedEObject);
		}

		/** Repairs all specific relation */
		void repair(InternalEObject owner, InternalEObject child) {
			if (!childClass.isAssignableFrom(child.getClass())) {
				return; // not handled by this container
			}

			if (child.eContainer() == owner) {
				return; // already set?
			}

			if (log.isDebugEnabled()) {
				log.debug("Set container of child "
						+ child.getClass().getName() + " containerfield "
						+ container.getName());
			}

			// and set it
			child.eBasicSetContainer(owner, featureID, null);

			// also repair the resource if applicable!
			/*
			 * if (child instanceof InternalEObject) { final InternalEObject
			 * eobj = (InternalEObject)child; Object ores = owner.eResource();
			 * Object eres = eobj.eResource(); if (eobj.eResource() !=
			 * owner.eResource()) { log.debug("Set resource of eobj
			 * " + eobj.getClass().getName() + " to resource " +
			 * owner.eResource().getURI());
			 * eobj.eSetResource((Resource.Internal)owner.eResource(), null); }
			 * }
			 */

			EContainerRepairControl.repair(child);
		}
	}

	/**
	 * Class handles a oneway container relation, in this case the
	 * eBasicSetContainer is used.
	 */
	private static class OneWayContainer extends RepairControl {
		/** Constructor */
		OneWayContainer(EReference containerReference) {
			super(containerReference, InternalEObject.EOPPOSITE_FEATURE_BASE
					- containerReference.getFeatureID());
		}
	}

	/**
	 * Class handles a twoway container relation, in this case the
	 * eBasicSetContainer is used.
	 */
	private static class TwoWayContainer extends RepairControl {
		/** Constructor */
		TwoWayContainer(EReference containerReference, EReference toContainer) {
			super(containerReference, toContainer.getFeatureID());
		}
	}
}