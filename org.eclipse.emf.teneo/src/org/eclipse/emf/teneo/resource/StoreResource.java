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
 *   Benjamin Cabé - See bugzilla 176356
 *
 * </copyright>
 *
 * $Id: StoreResource.java,v 1.37 2009/02/24 12:05:05 mtaal Exp $
 */

package org.eclipse.emf.teneo.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.teneo.EContainerRepairControl;
import org.eclipse.emf.teneo.StoreValidationException;
import org.eclipse.emf.teneo.util.FieldUtil;

/**
 * General part of Store Resources. Main feature is that it keeps track of
 * changes to the resource content and that settrackingmodification will not
 * load unloaded elists.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.37 $
 */

public abstract class StoreResource extends ResourceImpl {

	/** The name of the parameter used for the datastore name */
	public static final String DS_NAME_PARAM = "dsname";

	/**
	 * Prefix to use when specifying query parameters in the uri or in the
	 * property file
	 */
	public static final String QUERY_PREFIX = "query";

	/** Load strategy */
	public static final String LOAD_STRATEGY_PARAM = "loadStrategy";

	/** The default strategy */
	public static final String ADD_TO_CONTENTS = "addToContents";

	/** The just set eResource */
	public static final String SET_ERESOURCE = "setEResource";

	/**
	 * Don't do anything, risk of dangling_references but is better if objects
	 * get added to other resources anyway.
	 */
	public static final String NOT_SET_ERESOURCE = "notSetEResource";

	/** The logger */
	private static Log log = LogFactory.getLog(StoreResource.class);

	/**
	 * The list of objects which will be deleted at commit time. Needs to be a
	 * list because of the order of deletion.
	 */
	protected List<EObject> removedEObjects = new ArrayList<EObject>();

	/**
	 * The set of objects loaded from the backend. objects which have been
	 * removed are still part of this list. This is to prevent them from being
	 * added to the newEObjects set.
	 */
	protected HashSet<EObject> loadedEObjects = new HashSet<EObject>();
	// is used for more efficient access
	protected HashSet<EObject> loadedEObjectSet = new HashSet<EObject>();

	/**
	 * The set of new objects new EObjects are never part of the loadedEObjects,
	 * when a newEObject is removed it is just removed from this set and not
	 * added to the removed EObjects. new eobjects are never part of the
	 * modifiedEObjects set.
	 */
	protected List<EObject> newEObjects = new ArrayList<EObject>();
	// is used for more efficient access
	protected HashSet<EObject> newEObjectSet = new HashSet<EObject>();

	/**
	 * The set of changed objects, this contains the loadedEObjects which have
	 * changed new EObjects are never part of this set
	 */
	protected HashSet<EObject> modifiedEObjects = new HashSet<EObject>();

	/**
	 * Apparently the super EMF resource classes have a different idea of loaded
	 * During the unload action they again start loading from the db, this
	 * should be prevented.
	 */
	protected boolean isUnLoading = false;

	/** The list of topclasses */
	protected String[] topClassNames;

	/**
	 * The list of queries if they are defined for this resource. If not set
	 * (length is 0) then the resource will as default behavior read the
	 * topclasses of the database.
	 */
	private String[] definedQueries = new String[0];

	/** Send notifications under load */
	private boolean sendNotificationsOnLoad = true;

	/** Is send notification set by parameter */
	private boolean sendNotificationSetByParam = false;

	/** The load strategy */
	private String loadStrategy = SET_ERESOURCE;

	/**
	 * The constructor, gets an uri and retrieves the backing OJBStore
	 */
	public StoreResource(URI uri) {
		super(uri);

		final Map<String, String> params = decodeQueryString(uri.query());
		if (params.get(LOAD_STRATEGY_PARAM) != null) {
			loadStrategy = params.get(LOAD_STRATEGY_PARAM);
		}

		if (log.isDebugEnabled()) {
			log.debug("Created " + this.getClass().getName() + " using uri: "
					+ uri.toString());
		}

		if (params.get(XMLResource.OPTION_DISABLE_NOTIFY) != null) {
			sendNotificationSetByParam = true;
			sendNotificationsOnLoad = "false".compareToIgnoreCase(params
					.get(XMLResource.OPTION_DISABLE_NOTIFY)) == 0;
		} else if (params.get(XMIResource.OPTION_DISABLE_NOTIFY) != null) {
			sendNotificationSetByParam = true;
			sendNotificationsOnLoad = "false".compareToIgnoreCase(params
					.get(XMIResource.OPTION_DISABLE_NOTIFY)) == 0;
		}

		setLoaded(false);

		// note this ugly statement is required because in this class
		// it is required to have direct access to the contents member
		// the call to getSuperContents will initialize it
		if (getContents().size() == 0) {
			log.debug("Initialized contents member");
		}

		// set the intrinsicmap
		setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
	}

	/** Sets topclasses */
	protected void init(String[] topClasses) {
		topClassNames = topClasses;
	}

	public void setIsLoading(boolean loading) {
		isLoading = loading;
	}

	// ----------------------------------- Defined Queries
	// ----------------------------------

	/** Sets the defined queries */
	public void setDefinedQueries(String[] qrys) {
		log.debug("Setting defined queries of resource " + getURI().toString()
				+ "/" + this.getClass().getName());
		for (String element : qrys) {
			log.debug("Adding query: " + element);
		}

		definedQueries = qrys;
	}

	/** Returns true if there are defined queries */
	public boolean definedQueriesPresent() {
		return definedQueries.length > 0;
	}

	/**
	 * Returns the current array of defined queries. Note if there are no
	 * definedQueries then an array of length 0 is returned.
	 */
	public String[] getDefinedQueries() {
		return definedQueries;
	}

	/** Returns a list of queries if they are passed as parameters */
	protected String[] getQueries(Map<?, ?> params) {
		final ArrayList<String> queries = new ArrayList<String>();
		for (Object key : params.keySet()) {
			if (((String) key).startsWith(QUERY_PREFIX)) {
				queries.add((String) params.get(key));
			}
		}
		return queries.toArray(new String[queries.size()]);
	}

	/** Get the parameter from the hashmap, if not found then throw an exception */
	protected String getParam(Map<String, String> params, String paramName,
			String report) {
		final String param = params.get(paramName);
		if (param == null) {
			throw new StoreResourceException("Parameter " + paramName
					+ " missing in querystring: " + report);
		}
		return param;
	}

	/** Decode the query string in a hashmap */
	protected Map<String, String> decodeQueryString(String qryStr) {
		final TreeMap<String, String> result = new TreeMap<String, String>();

		if (qryStr == null) {
			return result;
		}

		final String[] qryParts = qryStr.split("&");
		for (final String qryPart : qryParts) {
			final String fieldName = qryPart.substring(0, qryPart.indexOf('='));
			final String fieldValue = URI.decode(qryPart.substring(qryPart
					.indexOf('=') + 1));
			result.put(fieldName, fieldValue);
		}
		return result;
	}

	// ------------------------------ Subclass methods
	// ---------------------------------

	/**
	 * Returns an array of EObjects which refer to a certain EObject, note if
	 * the array is of length zero then no refering EObjects where found.
	 */
	public abstract Object[] getCrossReferencers(EObject referedTo);

	/** Load implemented by subclass */
	protected abstract List<EObject> loadResource(Map<?, ?> options);

	/** Save implemented by subclass */
	protected abstract void saveResource(Map<?, ?> options);

	// ------------------------------------ Load
	// ------------------------------------------

	/** Returns true if the resource is unloading */
	public boolean isUnLoading() {
		return isUnLoading;
	}

	/** Loads the resource */
	@Override
	public void load(Map<?, ?> options) {
		if (isUnLoading) {
			return;
		}
		if (isLoaded()) {
			return;
		}
		if (isLoading()) {
			return;
		}

		String option;
		if (options != null
				&& (option = (String) options
						.get(XMLResource.OPTION_DISABLE_NOTIFY)) != null) {
			sendNotificationsOnLoad = "false".compareToIgnoreCase(option) == 0;
		} else if (options != null
				&& (option = (String) options
						.get(XMIResource.OPTION_DISABLE_NOTIFY)) != null) {
			sendNotificationsOnLoad = "false".compareToIgnoreCase(option) == 0;
		} else if (!sendNotificationSetByParam) {
			sendNotificationsOnLoad = true;
		}

		if (options != null && options.get(LOAD_STRATEGY_PARAM) != null) {
			loadStrategy = (String) options.get(LOAD_STRATEGY_PARAM);
		}

		isLoading = true;
		Notification notification = setLoaded(true);
		try {
			clearChangeTrackerArrays();
			List<EObject> list = loadResource(options);
			for (EObject eObject : list) {
				addToContent((InternalEObject) eObject);
			}
		} finally {
			if (notification != null) {
				eNotify(notification);
			}

			setModified(false);
			isLoading = false;
		}
	}

	/** Add to this resource */
	protected void addToContent(InternalEObject eObject) {
		// note direct access to super member
		if (!getLocalContents().contains(eObject)) {
			final NotificationChain notifications = getLocalContents()
					.basicAdd(eObject, null);
			if (notifications != null && sendNotificationsOnLoad) {
				notifications.dispatch();
			}
			setEResource(eObject, true);
			attached(eObject);
		}
	}

	protected void addUsingContainmentStructure(InternalEObject eObject) {
		final boolean oldLoading = isLoading();
		try {
			setIsLoading(true);
			// find the topcontainer
			InternalEObject currentEObject = eObject;
			while (currentEObject.eContainer() != null
					&& !currentEObject.eContainmentFeature().isResolveProxies()) {
				currentEObject = (InternalEObject) currentEObject.eContainer();
			}
			// if the topcontainer is not yet part of the resource then add it
			if (!loadedEObjectSet.contains(currentEObject)) {
				final NotificationChain notifications = getLocalContents()
						.basicAdd(eObject, null);
				if (notifications != null && sendNotificationsOnLoad) {
					notifications.dispatch();
				}
				setEResource(eObject, true);
				// attached will also call the children
				attached(currentEObject);
			} else if (!loadedEObjectSet.contains(eObject)) {
				// container is already part of the resource
				// just add the current object to the loaded eobjects
				attached(eObject);
			}
		} finally {
			setIsLoading(oldLoading);
		}
	}

	// This method is called when ereferences are resolved.
	public void addToContentOrAttach(InternalEObject eObject,
			EReference eReference) {
		final boolean oldLoading = isLoading();
		try {
			if (loadStrategy.compareTo(ADD_TO_CONTENTS) == 0) {
				if (eObject.eResource() != null && eObject.eResource() != this) {
					return;
				}
				// always add to the resource, this will change
				// the contents of the resource
				addUsingContainmentStructure(eObject);
			} else if (eReference.isContainment()) {
				// if resolve proxies then it is allowed to have child objects
				// in other
				// resources
				if (eReference.isResolveProxies()
						&& eObject.eResource() != null
						&& eObject.eResource() != this) {
					return;
				}
				// only add if contained, just add to the loaded eobjects lists
				assert (eObject.eContainer().eResource() == this);
				attached(eObject);
			} else if (loadStrategy.compareTo(SET_ERESOURCE) == 0) {
				// this is not the nicest solution because everyone is added to
				// the top of the
				// resource.
				// but it prevents dangling references
				// when objects are not added explicitly to a resource
				setEResource(eObject, false);
				attached(eObject);
			}
		} finally {
			setIsLoading(oldLoading);
		}
	}

	/**
	 * Called by subclass
	 * 
	 * /** Saves the resource
	 */
	@Override
	public void save(Map<?, ?> options) {
		boolean err = true;
		try {
			setAllowNotifications(false);
			validateContents();
			saveResource(options);
			err = false;
		} finally {
			// now clear the changed eobjects and move the new objects
			// to the loaded eobjects
			setAllowNotifications(true);
			if (!err) {
				modifiedEObjects.clear();
				removedEObjects.clear();
				loadedEObjects.addAll(newEObjects);
				loadedEObjectSet.addAll(newEObjects);
				newEObjects.clear();
				newEObjectSet.clear();
				setModified(false);
			}
		}
	}

	/**
	 * Clears different lists to start with an empty resource again.
	 */
	@Override
	protected void doUnload() {
		isUnLoading = true;
		super.doUnload();
		clearChangeTrackerArrays();
		isUnLoading = false;
	}

	/*
	 * Javadoc copied from interface.
	 */
	@Override
	public EList<EObject> getContents() {
		if (contents == null) {
			contents = new LocalContentsEList();
		}
		return contents;
	}

	protected LocalContentsEList getLocalContents() {
		return (LocalContentsEList) getContents();
	}

	// this specific ContentsElist overrides inverseremove to handle the
	// following case
	// using queries it is possible to load a parent and child both in the root
	// of the
	// resource. During unload of the resource the child is removed from the
	// parent
	// see the BasicEObjectImpl.eSetResource implementation. This is undesirable
	// therefore
	// the inverseRemove method is overridden.
	// See bugzilla 227500
	protected class LocalContentsEList extends ContentsEList<EObject> {
		private static final long serialVersionUID = 1L;

		@Override
		public NotificationChain inverseRemove(EObject object,
				NotificationChain notifications) {
			if (!isUnLoading()) {
				return super.inverseRemove(object, notifications);
			}
			if (!unloadingContents.contains(object)) {
				return super.inverseRemove(object, notifications);
			}

			final InternalEObject eObject = (InternalEObject) object;
			final InternalEObject eContainer = eObject.eInternalContainer();
			if (eContainer == null) {
				return super.inverseRemove(object, notifications);
			}

			// specific case which works fine as in this case the
			// object is not removed from its container
			if (eObject.eContainmentFeature().isResolveProxies()) {
				return super.inverseRemove(object, notifications);
			}

			// can this ever happen?
			if (!(eObject instanceof BasicEObjectImpl)) {
				return super.inverseRemove(object, notifications);
			}

			// now the only thing remaining is mimick the inverseRemove without
			// removal
			// from the container
			// this is the invariant:
			// eDirectResource() != null && eContainer != null
			// in this case the directresource has to be set, using java
			// reflection to handle that
			final Resource oldResource = eObject.eDirectResource();
			if (oldResource != null) {
				notifications = ((InternalEList<?>) oldResource.getContents())
						.basicRemove(this, notifications);
			}
			FieldUtil.callMethod(eObject, "eSetDirectResource",
					new Object[] { null });

			return notifications;
		}
	}

	/**
	 * Validate the contents of the resource, only the changed/new EObjects are
	 * checked
	 */
	protected void validateContents() throws StoreValidationException {
		// get the changed or new eobjects
		final ArrayList<EObject> newOrChangedObjects = new ArrayList<EObject>(
				newEObjects);
		newOrChangedObjects.addAll(modifiedEObjects);

		log.debug("Validating contents of resource " + uri + " approx. "
				+ newOrChangedObjects.size() + " will be checked");

		final ArrayList<org.eclipse.emf.common.util.Diagnostic> diags = new ArrayList<org.eclipse.emf.common.util.Diagnostic>();
		for (int i = 0; i < newOrChangedObjects.size(); i++) {
			final InternalEObject obj = (InternalEObject) newOrChangedObjects
					.get(i);

			// ensure that the resource is set correctly before validating
			if (obj.eResource() != this) {
				assert (obj.eResource() == this);
			}
			EContainerRepairControl.setEResourceToAlLContent(obj, this);

			if (newOrChangedObjects.contains(obj.eContainer())) {
				continue; // they will be checked as part of their container
			}

			final org.eclipse.emf.common.util.Diagnostic diag = validateObject(newOrChangedObjects
					.get(i));
			if (diag != null) {
				diags.add(diag);
			}
		}
		log.debug("Found " + diags.size() + " errors ");
		if (diags.size() > 0) {
			throw new StoreValidationException(diags
					.toArray(new org.eclipse.emf.common.util.Diagnostic[diags
							.size()]));
		}
	}

	/** Copied from IBM tutorial, validates one eobject */
	public org.eclipse.emf.common.util.Diagnostic validateObject(EObject eObject) {
		org.eclipse.emf.common.util.Diagnostic diagnostic = NonLoadingDiagnostician.INSTANCE
				.validate(eObject);
		if (diagnostic.getSeverity() == org.eclipse.emf.common.util.Diagnostic.ERROR) {
			return diagnostic;
		}
		return null;
	}

	// -------------------------- Content Iteration
	// -----------------------------------

	// /**
	// * Override load to force a load when getContents is called without a
	// previous load call.
	// */
	// @Override
	// public EList<EObject> getContents() {
	// // the getContents should not load but should return an
	// // empty string if not yet loaded.
	// // if (!isLoaded() && !isLoading) {
	// // load(null);
	// // }
	// //
	// // and then do our thing!
	// return super.getContents();
	// }
	//
	// /**
	// * Extra method to allow subclasses to easily reach the contents of the
	// superclass of this class
	// */
	// protected EList<EObject> getSuperContents() {
	// return super.getContents();
	// }

	// -------------------------------- Change Tracker
	// ----------------------------------

	/** Clears the change tracker arrays */
	private void clearChangeTrackerArrays() {
		removedEObjects.clear();
		newEObjects.clear();
		newEObjectSet.clear();
		loadedEObjects.clear();
		loadedEObjectSet.clear();
		modifiedEObjects.clear();
	}

	/**
	 * Keeps track of changed objects, CHECK: currently if a tree of objects has
	 * been added to this resource and afterwards a child in the tree is changed
	 * then the child is added to the modifiedEObjects list while its containing
	 * parent is part of the addedEObjects list. This should maybe be prevented
	 * here but this can come at some cost as it means that the complete
	 * container path has to be loaded for each modification.
	 */
	public void modifiedEObject(EObject eObject) {
		// if (addedEObjects.contains(eObject)) return; // see remark above, if
		// childs are added to the modified list
		// then childs also

		if (loadedEObjectSet.contains(eObject)
				&& !modifiedEObjects.contains(eObject)) {
			assert (!newEObjectSet.contains(eObject));
			modifiedEObjects.add(eObject);
		}
	}

	/** Keeps track of new objects */
	public void addedEObject(EObject eObject) {
		if (isLoading()) {
			if (removedEObjects.contains(eObject)) {
				// special case an eobject was removed from the resource but is
				// readded during load of an elist
				// the remove will be done at save
				return;
			}
			// assert (!removedEObjects.contains(eObject));
			assert (!loadedEObjectSet.contains(eObject));
			loadedEObjects.add(eObject);
			loadedEObjectSet.add(eObject);
		} else {
			// assert (!removedEObjects.contains(eObject));
			assert (!newEObjectSet.contains(eObject));
			if (removedEObjects.remove(eObject)) {
				modifiedEObjects.add(eObject);
			} else {
				newEObjects.add(eObject);
				newEObjectSet.add(eObject);
			}
		}
	}

	/** Keeps track of removed objects */
	public void removedEObject(EObject eObject) {
		assert (!removedEObjects.contains(eObject));

		if (newEObjectSet.contains(eObject)) {
			assert (newEObjects.contains(eObject));
			newEObjects.remove(eObject); // just remove from this list
			newEObjectSet.remove(eObject);
			return;
		}

		if (!loadedEObjectSet.contains(eObject)) {
			assert (loadedEObjects.contains(eObject));
		}
		assert (!removedEObjects.contains(eObject));

		if (!(eObject instanceof BasicEMap.Entry)) {
			removedEObjects.add(eObject);
		}

		loadedEObjects.remove(eObject);
		loadedEObjectSet.remove(eObject);
		modifiedEObjects.remove(eObject);
	}

	/** Object is attached, is overridden to use non-resolving iterator */
	@Override
	public void attached(EObject eObject) {
		attachedHelper(eObject);
		for (Iterator<EObject> tree = getNonResolvingContent(eObject)
				.basicIterator(); tree.hasNext();) {
			final Object obj = tree.next();
			// before this called:
			// attachedHelper((EObject) obj);
			// however this resulted in child elements (which were loaded) not
			// being
			// added to the internal id map
			attached((EObject) obj);
		}
	}

	/** Detached means deleted from resource */
	@Override
	public void detached(EObject eObject) {
		detachedHelper(eObject);
		for (EObject object : getNonResolvingContent(eObject)) {
			detachedHelper(object);
		}
	}

	/**
	 * Attached the object to this resource, This object will be stored at the
	 * next save action. Also handles the specific id generation used for
	 * different resource impl.
	 * 
	 * Note that this method does not add the object to the
	 * resource.getContents(). It just adds the object to the internal lists of
	 * this resource.
	 */
	// 20 April 2008: cleaned up side-effects of this method, add to contents,
	// setting
	// eresource has been disabled, this should be done in calling methods
	@Override
	protected void attachedHelper(EObject eObject) {
		// also attach the container
		// if (eObject.eContainer() != null && eObject.eContainer().eResource()
		// == null &&
		// !eObject.eContainmentFeature().isResolveProxies()) {
		// attached(eObject.eContainer());
		// }

		// a bit strange as an object can only be contained once but this can
		// happen if someone
		// adds an object to a resource directly and then later add this same
		// object as a child
		// to a container
		if (newEObjectSet.contains(eObject)
				|| loadedEObjectSet.contains(eObject)) {
			return;
		}

		// Already belongs to another resource
		if (eObject.eResource() != null && eObject.eResource() != this) {
			return;
		}

		addedEObject(eObject);

		// if (eObject instanceof InternalEObject && eObject.eResource() ==
		// null) {
		// setEResource((InternalEObject) eObject, false);
		// }

		// NOTE: should this really happen here? My feel is that this should
		// be cleaned up and be done outside of this method
		// This is required here because the attached method is called
		// recursively for the container, see the first if in this method
		// only add an eobject to contents if it does not have a container or if
		// the container
		// relation allows resolve proxies (container and contained can be in
		// different resources)
		// and the load strategy is correct
		// if ((eObject.eContainer() == null || eObject.eContainmentFeature() ==
		// null ||
		// eObject.eContainmentFeature()
		// .isResolveProxies()) &&
		// !getContents().contains(eObject) &&
		// loadStrategy.compareTo(ADD_TO_CONTENTS) == 0) {
		// // note direct access to super contents
		// final NotificationChain notifications = contents.basicAdd(eObject,
		// null);
		// if (notifications != null && sendNotificationsOnLoad) {
		// notifications.dispatch();
		// }
		// }

		if (isTrackingModification()) {
			eObject.eAdapters().add(modificationTrackingAdapter);
		}

		Map<String, EObject> map = getIntrinsicIDToEObjectMap();
		if (map != null) {
			String id = EcoreUtil.getID(eObject);
			if (id != null) {
				map.put(id, eObject);
			} else {
				id = getURIFragment(eObject);
				if (id != null) {
					map.put(id, eObject);
				}
			}
		}

		// now also attach all ereferences with single values which are
		// contained
		for (EReference eref : eObject.eClass().getEAllReferences()) {
			if (!eref.isMany() && eObject.eGet(eref, false) != null) { // the
				// ismanies
				// are handled
				// differently
				final Resource res = ((EObject) eObject.eGet(eref)).eResource();
				if (res == null) { // attach it to this resource because it has
					// no other
					final InternalEObject referedTo = (InternalEObject) eObject
							.eGet(eref);
					addToContentOrAttach(referedTo, eref);
				}
			}
		}
	}

	/**
	 * Add to the contents of this resource and dispatch if send notification.
	 * It also takes care of attaching this object and its contained children to
	 * the internal lists and setting the eResource.
	 * 
	 * Approaches: - Set eResource and add to contents (if no econtainer) - Just
	 * set eResource and add to contents if it does not have a container public
	 * void addToResource(InternalEObject eObject, boolean forceAddToContents) {
	 * // if it has an econtainer then also add the econtainer if
	 * (eObject.eContainer() != null && eObject.eContainer().eResource() ==
	 * null) { addToResource((InternalEObject)eObject.eContainer(), false); } //
	 * if the econtainer is already part of this resource then just attach if
	 * (!forceAddToContents && eObject.eContainer() != null &&
	 * eObject.eContainer().eResource() == this) { attached(eObject); return; }
	 * // probably lazy load action of non-containment, already part of this
	 * just return if (!forceAddToContents && eObject.eResource() == this &&
	 * !loadedEObjects.contains(eObject) && !removedEObjects.contains(eObject)
	 * && !newEObjects.contains(eObject)) { attached(eObject); return; } //
	 * already part of another resource if (!forceAddToContents &&
	 * eObject.eResource() != null) { return; }
	 * 
	 * final ContentsEList elist = (ContentsEList) super.getContents(); if
	 * (elist.contains(eObject)) { // can happen because of extends,
	 * polymorphism return; } // fill in the resource, do not use the normal add
	 * method because it // is possible that a child of a container is loaded,
	 * in that case // the normal add will remove the container of the object
	 * when the // resource is set in the child object, this issue can happen
	 * with // direct reads using queries. NotificationChain notification =
	 * null; if (loadStrategy.compareToIgnoreCase(ADD_TO_CONTENTS) == 0 ||
	 * forceAddToContents) { notification = elist.basicAdd(eObject, null); } if
	 * (eObject.eResource() == null || (forceAddToContents &&
	 * eObject.eResource() != this)) { setEResource(eObject,
	 * forceAddToContents); } // attached(eObject); if (sendNotificationsOnLoad
	 * && notification != null) { notification.dispatch(); } }
	 */

	/**
	 * Sets the eresource by walking up the containment structure and finding
	 * the highest parent. There the resource is set.If the resource is already
	 * set nothing is done.
	 */
	public void setEResource(InternalEObject eobj, boolean force) {
		if (eobj.eResource() != null && eobj.eResource() != this && !force) {
			return;
		}

		InternalEObject currentEObject = eobj;
		while (currentEObject.eContainer() != null
				&& !currentEObject.eContainmentFeature().isResolveProxies()) {
			currentEObject = (InternalEObject) currentEObject.eContainer();
		}
		if (currentEObject.eResource() != this) {
			currentEObject.eSetResource(this, null);
			attached(currentEObject);
		}
	}

	/**
	 * Overridden to also support persistence specific id instead of single emf
	 * id
	 */
	@Override
	protected void detachedHelper(EObject eObject) {

		// support move to other resource
		if (eObject.eResource() != this) {
			removedEObjects.remove(eObject);
			modifiedEObjects.remove(eObject);
			loadedEObjects.remove(eObject);
			loadedEObjectSet.remove(eObject);
			newEObjects.remove(eObject);
			newEObjectSet.remove(eObject);
			return;
		}

		removedEObject(eObject);

		Map<String, EObject> map = getIntrinsicIDToEObjectMap();
		if (map != null) {
			String id = EcoreUtil.getID(eObject);
			if (id != null) {
				map.remove(id);
			} else {
				id = getURIFragment(eObject);
				if (id != null) {
					map.remove(id);
				}
			}
		}

		if (isTrackingModification()) {
			eObject.eAdapters().remove(modificationTrackingAdapter);
		}
	}

	/** Returns the added objects */
	public List<EObject> getNewEObjects() {
		return newEObjects;
	}

	public HashSet<EObject> getNewEObjectSet() {
		return newEObjectSet;
	}

	/** Return the new eobjects */
	public HashSet<EObject> getModifiedEObjects() {
		return modifiedEObjects;
	}

	/** Return the new eobjects */
	public List<EObject> getRemovedEObjects() {
		return removedEObjects;
	}

	/**
	 * Overridden to make it non resolving for not loaded elists and proxy
	 * eobjects
	 */
	@Override
	public void setTrackingModification(boolean isTrackingModification) {
		boolean oldIsTrackingModification = modificationTrackingAdapter != null;

		if (oldIsTrackingModification != isTrackingModification) {
			if (isTrackingModification) {

				// note the global modification adapter is set after the for
				// loop
				// because in the for loop extra objects can be added to the
				// resource
				// these objects would get two adapters, once in the
				// attachedHelper method
				// and once here, btw can also prevent this by adding a contains
				// check within
				// for loop
				final Adapter adapter = createModificationTrackingAdapter();
				for (Iterator<EObject> i = getNonResolvingAllContents(); i
						.hasNext();) {
					EObject eObject = i.next();
					assert (!eObject.eAdapters().contains(adapter));
					eObject.eAdapters().add(adapter);
				}
				modificationTrackingAdapter = adapter;
			} else {
				Adapter oldModificationTrackingAdapter = modificationTrackingAdapter;

				for (Iterator<EObject> i = getNonResolvingAllContents(); i
						.hasNext();) {
					EObject eObject = i.next();
					assert (eObject.eAdapters()
							.contains(modificationTrackingAdapter));
					eObject.eAdapters().remove(oldModificationTrackingAdapter);
				}
				modificationTrackingAdapter = null;
			}
		}

		if (eNotificationRequired()) {
			Notification notification = new NotificationImpl(Notification.SET,
					oldIsTrackingModification, isTrackingModification) {
				@Override
				public Object getNotifier() {
					return StoreResource.this;
				}

				@Override
				public int getFeatureID(Class<?> expectedClass) {
					return RESOURCE__IS_TRACKING_MODIFICATION;
				}
			};
			eNotify(notification);
		}
	}

	/**
	 * Always returns a non-resolving iterator because resolving is defined on
	 * model level and the resource should adhere to that
	 */
	@SuppressWarnings("serial")
	protected TreeIterator<EObject> getNonResolvingAllContents() {
		return new AbstractTreeIterator<EObject>(this, false) {
			@Override
			public Iterator<EObject> getChildren(Object object) {
				return object == StoreResource.this ? getLocalContents()
						.basicIterator() : getNonResolvingContent(
						(EObject) object).basicIterator();
			}
		};
	}

	@Override
	// Method is called at unload, only loaded content should be iterated
	protected TreeIterator<EObject> getAllProperContents(List<EObject> contents) {
		return getNonResolvingAllContents();
	}

	/** Returns a non-resolving contents elist for an eobject */
	protected EContentsEList<EObject> getNonResolvingContent(EObject eObject) {
		return NonLoadingEContentsEList.create(eObject, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.emf.ecore.resource.impl.ResourceImpl#
	 * createModificationTrackingAdapter()
	 */
	@Override
	protected Adapter createModificationTrackingAdapter() {
		return new StoreModificationTrackingAdapter();
	}

	// Enable or disable notifications for the current content
	protected void setAllowNotifications(boolean allow) {
		for (Iterator<?> i = getNonResolvingAllContents(); i.hasNext();) {
			EObject eObject = (EObject) i.next();
			eObject.eSetDeliver(allow);
		}
	}

	/**
	 * An adapter implementation for tracking resource modification, registers
	 * changed objects
	 */
	protected class StoreModificationTrackingAdapter extends AdapterImpl {
		@Override
		public void notifyChanged(Notification notification) {
			switch (notification.getEventType()) {
			case Notification.SET:
			case Notification.UNSET:
			case Notification.MOVE: {
				if (!notification.isTouch()) {
					setModified(true);
					modifiedEObject((EObject) notification.getNotifier());
				}
				break;
			}
			case Notification.ADD:
			case Notification.REMOVE:
			case Notification.ADD_MANY:
			case Notification.REMOVE_MANY: {
				setModified(true);
				break;
			}
			}
		}
	}

	/**
	 * @return the sendNotificationsOnLoad
	 */
	public boolean isSendNotificationsOnLoad() {
		return sendNotificationsOnLoad;
	}
}