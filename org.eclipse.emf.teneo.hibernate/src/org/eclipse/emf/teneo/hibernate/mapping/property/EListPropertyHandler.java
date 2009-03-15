/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * EListPropertyHandler.java,v 1.12 2007/03/20 23:33:48 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl.EStoreEList;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.teneo.extension.ExtensionManager;
import org.eclipse.emf.teneo.extension.ExtensionManagerAware;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.HbUtil;
import org.eclipse.emf.teneo.hibernate.mapping.elist.HbExtraLazyPersistableEList;
import org.eclipse.emf.teneo.hibernate.mapping.elist.HibernatePersistableEList;
import org.eclipse.emf.teneo.hibernate.mapping.elist.HibernatePersistableEMap;
import org.eclipse.emf.teneo.hibernate.mapping.elist.MapHibernatePersistableEMap;
import org.eclipse.emf.teneo.mapping.elist.MapPersistableEMap;
import org.eclipse.emf.teneo.mapping.elist.PersistableDelegateList;
import org.eclipse.emf.teneo.mapping.elist.PersistableEList;
import org.eclipse.emf.teneo.mapping.elist.PersistableEMap;
import org.eclipse.emf.teneo.type.PersistentStoreAdapter;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.eclipse.emf.teneo.util.FieldUtil;
import org.eclipse.emf.teneo.util.StoreUtil;
import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;

/**
 * Implements the accessor for EMF EList members for Hibernate. This can be an
 * EReference or an Eattribute with many=true. This class implements both the
 * getter, setter and propertyaccessor interfaces. When the getGetter and
 * getSetter methods are called it returns itself.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.28 $
 */
@SuppressWarnings("unchecked")
public class EListPropertyHandler implements Getter, Setter, PropertyAccessor,
		ExtensionPoint, ExtensionManagerAware {
	/**
	 * Generated Serial Version ID
	 */
	private static final long serialVersionUID = 2255108246093951341L;

	/** The logger */
	private static Log log = LogFactory.getLog(EListPropertyHandler.class);

	/** The EStructuralFeature of this accessor */
	protected EStructuralFeature eFeature;

	/** Extra lazy behavior! */
	private boolean extraLazy;

	/** Map emap as a real map */
	private boolean newEMapMapping;

	/** It this a map */
	private boolean isAMap;

	/** The extension manager */
	private ExtensionManager extensionManager;

	/** Initialize this instance */
	public void initialize(EStructuralFeature eFeature, boolean extraLazy,
			boolean newEMapMapping) {
		this.extraLazy = extraLazy;
		this.eFeature = eFeature;
		log.debug("Created getter/setter for " + StoreUtil.toString(eFeature));
		AssertUtil.assertTrue("Many must be true but this isn't the case for "
				+ StoreUtil.toString(eFeature), eFeature.isMany());
		isAMap = StoreUtil.isMap(eFeature);
		this.newEMapMapping = newEMapMapping;
	}

	public EStructuralFeature getEFeature() {
		return eFeature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.PropertyAccessor#getGetter(java.lang.Class,
	 * java.lang.String)
	 */
	public Getter getGetter(Class theClass, String propertyName)
			throws PropertyNotFoundException {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.PropertyAccessor#getSetter(java.lang.Class,
	 * java.lang.String)
	 */
	public Setter getSetter(Class theClass, String propertyName)
			throws PropertyNotFoundException {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#get(java.lang.Object)
	 */
	public Object get(Object owner) throws HibernateException {

		final PersistentStoreAdapter adapter = HbUtil
				.getPersistentStoreAdapter((EObject) owner);
		if (!adapter.isTargetCreatedByORM()) {
			final Object value = adapter.getStoreCollection(eFeature);
			if (value != null) {
				return value;
			}
		}

		Object obj = ((EObject) owner).eGet(eFeature);

		if (isEStoreList(obj)) {
			final EStore eStore = ((InternalEObject) owner).eStore();
			// the call to size forces a load, this is a trick to
			// force the estore to create a list, otherwise the .get
			// will return a null value.
			if (eStore.size((InternalEObject) owner, eFeature) != -1) {
				obj = eStore.get((InternalEObject) owner, eFeature,
						EStore.NO_INDEX);
			}
		}

		if (obj instanceof PersistableDelegateList) {
			return ((PersistableDelegateList) obj).getDelegate();
		}
		if (obj instanceof EcoreEMap && newEMapMapping) {
			return ((EcoreEMap<?, ?>) obj).map();
		}

		if (adapter.isTargetCreatedByORM() && obj instanceof BasicFeatureMap) {
			// this one is replaced here
			// because the entries
			// need to be changed to hibernate entries
			final PersistableDelegateList pelist = (PersistableDelegateList) createPersistableList(
					(InternalEObject) owner, eFeature, (List) obj);
			final EObject eobj = (EObject) owner;
			if (!EcoreAccess.isStaticFeature(eFeature, (BasicEObjectImpl) eobj)) {
				if (log.isDebugEnabled()) {
					log.debug("Dynamic elist, set using the esettings");
				}
				EcoreAccess.setManyEFeatureValue(eFeature, pelist,
						(BasicEObjectImpl) owner);
			} else {
				// TODO: currently it is required to use the field setter
				// instead of the eSet method
				// because EMF does not support direct setting of the elist
				// feature.
				// UPDATE: for dynamic eclasses now elists are also supported
				// the reason that the javafield is determined here and not at
				// construction time
				// is that the owner passed in the construction can be an
				// interface while there
				// are multiple implementors. FieldUtil does caching of
				// fieldnames and fields.
				final Field javaField = FieldUtil.getField(owner.getClass(),
						getFieldName(owner));
				try {
					javaField.set(owner, pelist);
				} catch (Exception e) {
					throw new HbMapperException("The field "
							+ javaField.getName()
							+ " can not be set using object "
							+ pelist.getClass().getName() + " on target "
							+ owner.getClass().getName(), e);
				}
			}
		}

		if (obj instanceof EList<?>) {
			return processList(obj);
		}

		return obj;
	}

	protected boolean isEStoreList(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof EStoreEList<?>) {
			return true;
		}
		if (o instanceof EStoreEObjectImpl.BasicEStoreEList) {
			return true;
		}
		return false;
	}

	protected String getFieldName(Object owner) {
		return eFeature.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getForInsert(java.lang.Object,
	 * java.util.Map, org.hibernate.engine.SessionImplementor)
	 */
	public Object getForInsert(Object owner, Map mergeMap,
			SessionImplementor session) throws HibernateException {
		final PersistentStoreAdapter adapter = HbUtil
				.getPersistentStoreAdapter((EObject) owner);
		if (!adapter.isTargetCreatedByORM()) {
			final Object value = adapter.getStoreCollection(eFeature);
			if (value != null) {
				return value;
			}
		}

		Object obj = ((EObject) owner).eGet(eFeature);

		if (isEStoreList(obj)) {
			final EStore eStore = ((InternalEObject) owner).eStore();
			// the call to size forces a load, this is a trick to
			// force the estore to create a list, otherwise the .get
			// will return a null value.
			if (eStore.size((InternalEObject) owner, eFeature) != -1) {
				obj = eStore.get((InternalEObject) owner, eFeature,
						EStore.NO_INDEX);
			}
		}

		if (obj instanceof PersistableDelegateList) {
			return ((PersistableDelegateList) obj).getDelegate();
		}
		if (obj instanceof EcoreEMap && newEMapMapping) {
			return ((EcoreEMap<?, ?>) obj).map();
		}

		// if this is a elist then give a normal arraylist to
		// hibernate otherwise hb will wrap the elist, the hb wrapper
		// is again wrapped by teneo resulting in notifications being send
		// out by both the teneo wrapper as the wrapped elist
		if (obj instanceof EList<?>) {
			return processList(obj);
		}

		// todo maybe throw error in all other cases?
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMethod()
	 */
	public Method getMethod() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMethodName()
	 */
	public String getMethodName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getReturnType()
	 */
	public Class getReturnType() {
		return EList.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Setter#set(java.lang.Object,
	 * java.lang.Object, org.hibernate.engine.SessionFactoryImplementor)
	 */
	public void set(Object target, Object value,
			SessionFactoryImplementor factory) throws HibernateException {

		final PersistentStoreAdapter adapter = HbUtil
				.getPersistentStoreAdapter((EObject) target);
		if (!adapter.isTargetCreatedByORM()) {
			adapter.addStoreCollection(eFeature, value);
			return;
		}

		if (!EcoreAccess.isStaticFeature(eFeature, (BasicEObjectImpl) target)) {
			if (log.isDebugEnabled()) {
				log.debug("Dynamic elist, set using the esettings");
			}
			Object currentValue = EcoreAccess.getManyEFeatureValue(eFeature,
					(BasicEObjectImpl) target);

			if (isEStoreList(currentValue)) {
				final EStore eStore = ((InternalEObject) target).eStore();
				if (eStore.size((InternalEObject) target, eFeature) != -1) {
					currentValue = eStore.get((InternalEObject) target,
							eFeature, EStore.NO_INDEX);
				}
			}

			// if currentvalue is not null then use the passed value
			if (currentValue != null
					&& currentValue instanceof PersistableEList) {
				((PersistableEList) currentValue).replaceDelegate((List) value);
			} else {
				if (value instanceof Map) {
					EcoreAccess.setManyEFeatureValue(eFeature,
							createPersistableMap((InternalEObject) target,
									eFeature, (Map) value),
							(BasicEObjectImpl) target);
				} else {
					EcoreAccess.setManyEFeatureValue(eFeature,
							createPersistableList((InternalEObject) target,
									eFeature, (List) value),
							(BasicEObjectImpl) target);
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("Set value " + value.getClass().getName()
						+ " for target " + target.getClass().getName()
						+ " field " + getFieldName(target));
			}

		} else {
			// the reason that the javafield is determined here and not at
			// construction time
			// is that the owner passed in the construction can be an interface
			// while there
			// are multiple implementors. FieldUtil does caching of fieldnames
			// and fields.
			final Field javaField = FieldUtil.getField(target.getClass(),
					getFieldName(target));

			try {
				final Object currentValue = javaField.get(target);

				// if already set then ignore it
				if (currentValue == value) {
					return; // nothing to do here
				}

				// the delegating map was passed to hibernate, now getting it
				// back
				if (value instanceof EMap.InternalMapView<?, ?>
						&& (currentValue == ((EMap.InternalMapView<?, ?>) value)
								.eMap())) {
					return;
				}

				// already handled
				if (currentValue instanceof PersistableDelegateList<?>
						&& value == ((PersistableDelegateList<?>) currentValue)
								.getDelegate()) {
					return;
				}

				// the follow 3 if statements handle the refresh action, the
				// underlying orm
				// collection is replaced
				if (currentValue != null
						&& currentValue instanceof PersistableEList
						&& value != ((PersistableEList) currentValue)
								.getDelegate()) {
					((PersistableEList) currentValue)
							.replaceDelegate((List) value);
				} else if (currentValue != null
						&& currentValue instanceof PersistableEMap
						&& value != ((PersistableEMap) currentValue)
								.getDelegate()) {
					((PersistableEMap) currentValue).replaceDelegate(value);
				} else if (currentValue != null
						&& currentValue instanceof MapPersistableEMap
						&& value != ((MapPersistableEMap) currentValue)
								.getORMMapDelegate()) {
					((PersistableEMap) currentValue).replaceDelegate(value);
				} else { // then wrap the hibernate collection
					if (value instanceof Map) {
						javaField.set(target,
								createPersistableMap((InternalEObject) target,
										eFeature, (Map) value));
					} else {
						javaField.set(target, createPersistableList(
								(InternalEObject) target, eFeature,
								(List) value));
					}
				}
				if (log.isDebugEnabled()) {
					log.debug("Set value " + value.getClass().getName()
							+ " for target " + target.getClass().getName()
							+ " field " + getFieldName(target));
				}
			} catch (Exception e) {
				throw new HbMapperException("The field "
						+ (javaField != null ? javaField.getName()
								: getFieldName(target))
						+ " can not be set using object "
						+ value.getClass().getName() + " on target "
						+ target.getClass().getName(), e);
			}
		}
	}

	/**
	 * Create a EMap. Create method can be overridden
	 */
	protected EList createPersistableMap(InternalEObject target,
			EStructuralFeature estruct, Map map) {
		final EReference eref = (EReference) estruct;
		if (log.isDebugEnabled()) {
			log.debug("Detected EMAP for " + estruct.getName());
		}
		assert (isAMap);
		assert (newEMapMapping);
		return getExtensionManager().getExtension(
				MapHibernatePersistableEMap.class,
				new Object[] { target, eref, map });
	}

	/** Creates a persistablemap or list */
	protected EList createPersistableList(InternalEObject target,
			EStructuralFeature estruct, List list) {
		if (estruct instanceof EReference) {
			final EReference eref = (EReference) estruct;
			// the test for emap checks: the entry class must have a
			// instanceclass: Map.Entry
			// and the entry class must have two efeatures with the name key and
			// value
			if (StoreUtil.isMap(estruct)) {
				if (log.isDebugEnabled()) {
					log.debug("Detected EMAP for " + estruct.getName());
				}

				return getExtensionManager().getExtension(
						HibernatePersistableEMap.class,
						new Object[] { target, eref, list });
			}
		}
		if (extraLazy) {
			return getExtensionManager().getExtension(
					HbExtraLazyPersistableEList.class,
					new Object[] { target, estruct, list });
		}
		return getExtensionManager().getExtension(
				HibernatePersistableEList.class,
				new Object[] { target, estruct, list });
	}

	protected List<Object> processList(Object list) {
		return new ArrayList<Object>((List<Object>) list);
	}

	/**
	 * @return the extensionManager
	 */
	public ExtensionManager getExtensionManager() {
		return extensionManager;
	}

	/**
	 * @param extensionManager
	 *            the extensionManager to set
	 */
	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}
}