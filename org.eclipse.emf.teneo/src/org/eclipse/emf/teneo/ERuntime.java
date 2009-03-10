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
 * $Id: ERuntime.java,v 1.17 2008/09/15 11:33:38 mtaal Exp $
 */

package org.eclipse.emf.teneo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.type.impl.XMLTypePackageImpl;
import org.eclipse.emf.teneo.classloader.ClassLoaderResolver;
import org.eclipse.emf.teneo.classloader.StoreClassLoadException;
import org.eclipse.emf.teneo.ecore.EModelResolver;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * The ERuntime contains references to EPackages which are persistable, i.e. are persisted.
 * 
 * It is used to compute information related to concrete class - eclass mapping, interface to
 * concrete class, references for cross reference computation, contained computations.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.17 $
 */

public class ERuntime extends EModelResolver {

	/** The logger */
	private static Log log = LogFactory.getLog(ERuntime.class);

	/** The singleton instance */
	public static final ERuntime INSTANCE = new ERuntime();

	/** The list of epackages processed here */
	private final ArrayList<EPackage> epackages = new ArrayList<EPackage>();

	/** The mapping from concrete classes to eclass and back */
	private final HashMap<Class<?>, EClass> concreteToEClass = new HashMap<Class<?>, EClass>();

	private final HashMap<Class<?>, EClass> interfaceToEClass = new HashMap<Class<?>, EClass>();

	private final HashMap<EClassifier, Class<?>> eclassifierToConcrete = new HashMap<EClassifier, Class<?>>();

	/** The list of topclasses/interfaces */
	private final ArrayList<Class<?>> topClasses = new ArrayList<Class<?>>();

	/** The list of contained classes/interfaces */
	private final ArrayList<Class<?>> containedClasses = new ArrayList<Class<?>>();

	/** Register the epackages */
	@Override
	public synchronized void register(EPackage[] epacks) {
		for (int i = 0; i < epacks.length; i++) {

			if (!epackages.contains(epacks[i])) {
				epackages.add(epacks[i]);
			}
		}

		computeConcreteInstanceMapping();
		computeContainedClasses();
	}

	/** Resets the maps/lists */
	@Override
	public void clear() {
		epackages.clear();
		containedClasses.clear();
		concreteToEClass.clear();
		interfaceToEClass.clear();
		eclassifierToConcrete.clear();
		topClasses.clear();
	}

	/**
	 * Computes which classes are contained and which are non-contained. Method must be called after
	 * the computeReferers method!
	 */
	private void computeContainedClasses() {

		topClasses.clear();
		containedClasses.clear();

		for (int i = 0; i < epackages.size(); i++) {
			final EPackage epack = epackages.get(i);
			if (ignorePackage(epack)) {
				continue;
			}

			for (EClassifier eclassifier : epack.getEClassifiers()) {
				if (!(eclassifier instanceof EClass)) {
					continue;
				}

				final EClass eclass = (EClass) eclassifier;

				// bit ugly compare on name, but document root should be ignored
				// otherwise everything is contained
				if (ExtendedMetaData.INSTANCE.isDocumentRoot(eclass)) {
					continue;
				}

				for (EReference eref : eclass.getEReferences()) {
					if (!eref.isContainment()) {
						continue;
					}
					final Class<?> toClass = eref.getEType().getInstanceClass();
					if (!containedClasses.contains(toClass)) {
						containedClasses.add(toClass);
					}
				}
			}
		}

		// and then when it is not contained add it to the contained list
		for (Class<?> clazz : interfaceToEClass.keySet()) {
			if (containedClasses.contains(clazz)) {
				continue; // already determined so continue
			}

			if (isSelfOrSuperContained(clazz, containedClasses)) {
				containedClasses.add(clazz);
			} else {
				final EClass eClass = getEClass(clazz);
				// remove all the abstract types
				// see bugzilla 220106
				if (eClass == null || !eClass.isAbstract()) {
					topClasses.add(clazz);
				}
			}
		}

		// topclasses are cleaned because they are used to query and otherwise
		// different queries would return overlapping results (because of
		// polymor.)
		cleanList(topClasses);
		cleanList(containedClasses);
	}

	/**
	 * Walks through a interface inheritance structure and determines if a superclass is contained
	 * if so then the class is added to the containedclasses
	 */
	private boolean isSelfOrSuperContained(Class<?> checkClass, ArrayList<Class<?>> containedClasses) {
// assert (checkClass.isInterface());
		if (containedClasses.contains(checkClass)) {
			return true;
		}
		final Class<?>[] interfaces = checkClass.getInterfaces();
		for (Class<?> element : interfaces) {
			if (isSelfOrSuperContained(element, containedClasses)) {
				return true;
			}
		}
		return false;
	}

	/** Returns the list of topclasses */
	public Class<?>[] getTopClasses() {
		return topClasses.toArray(new Class[topClasses.size()]);
	}

	/** Return the list of interfaces */
	public Set<Class<?>> getAllInterfaces() {
		return interfaceToEClass.keySet();
	}

	/** Returns all concrete classes */
	public Set<Class<?>> getAllConcreteClasses() {
		return concreteToEClass.keySet();
	}

	/**
	 * Retains only the root parent class in a list, so if an entry in the list as a parent in the
	 * same list then the child is deleted from the list
	 */
	private void cleanList(ArrayList<Class<?>> list) {
		final ArrayList<Class<?>> toRemove = new ArrayList<Class<?>>();
		for (Class<?> clazz : list) {
			if (clazz == null) {
				continue;
			}
			final Class<?>[] supers = clazz.getInterfaces();
			for (Class<?> element : supers) {
				if (list.contains(element)) {
					toRemove.add(clazz);
					break;
				}
			}
		}
		list.removeAll(toRemove);
	}

	/** Determines concrete impl classes for each eclass */
	private void computeConcreteInstanceMapping() {
		concreteToEClass.clear();
		eclassifierToConcrete.clear();
		interfaceToEClass.clear();

		// walk through all the epackages
		for (int i = 0; i < epackages.size(); i++) {
			final EPackage epack = epackages.get(i);

			if (ignorePackage(epack)) {
				log.debug("Not determining concrete classes for package " + epack.getName());
				continue;
			}

			log.debug("Determining concrete classes for package " + epack.getName());

			for (EClassifier eclassifier : epack.getEClassifiers()) {
				if (!(eclassifier instanceof EClass)) {
					continue;
				}

				final Object instance = create((EClass) eclassifier);
				if (instance != null && !(instance instanceof DynamicEObjectImpl)) {
					eclassifierToConcrete.put(eclassifier, instance.getClass());
					concreteToEClass.put(instance.getClass(), (EClass) eclassifier);
				}
				if (eclassifier.getInstanceClass() != null) {
					interfaceToEClass.put(eclassifier.getInstanceClass(), (EClass) eclassifier);
				}
			}
		}

		// packaged in an extra arraylist to prevent concurrent modification
		// exception.
		final List<Class<?>> classes = new ArrayList<Class<?>>(concreteToEClass.keySet());
		for (Class<?> clz : classes) {
			addAbstractSupers(clz);
		}
	}

	/**
	 * Walks up the class hierarchy and adds the superclasses to the concrete-interface mapping
	 * class sets
	 */
	private void addAbstractSupers(Class<?> clazz) {

		// clazz is null or not an eobject
		if (clazz == null || !EObject.class.isAssignableFrom(clazz)) {
			return;
		}

		// if already been here then go on for the superclasses
		if (concreteToEClass.get(clazz) != null) {
			addAbstractSupers(clazz.getSuperclass());
			return;
		}

		// new one, find all its interfaces
		final Class<?>[] interf = clazz.getInterfaces();
		for (Class<?> element : interf) {
			if (EObject.class.isAssignableFrom(element)) {
				final EClass eclass = interfaceToEClass.get(element);
				concreteToEClass.put(clazz, eclass);
				eclassifierToConcrete.put(eclass, clazz);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.jpox.emf.IEMFDataStore#getEPackages()
	 */
	@Override
	public EPackage[] getEPackages() {
		return epackages.toArray(new EPackage[epackages.size()]);
	}

	/** Returns true if the epackage is registered here */
	@Override
	public boolean isRegistered(EPackage epackage) {
		return epackages.contains(epackage);
	}

	/** Convenience method to easily determine which packages should be ignored */
	private static boolean ignorePackage(EPackage epack) {
		if (true) {
			return false;
		}
		if (epack instanceof XMLTypePackageImpl) {
			return true; // ignore this
		}
		if (epack instanceof EcorePackageImpl) {
			return true; // ignore this
		}
		return false;
	}

	/** Returns the instanceclass for a passed interface */
	public Class<?> getInstanceClass(Class<?> interf) {
		final EClass eclass = interfaceToEClass.get(interf);
		if (eclass == null) {
			throw new TeneoException("No eclass for interf " + interf.getName());
		}
		return getJavaClass(eclass);
	}

	/** Returns the instanceclass for a passed eclass */
	@Override
	public Class<?> getJavaClass(EClassifier eclassifier) {
		if (eclassifier instanceof EClass) {
			final EClass eclass = (EClass) eclassifier;
			if (eclass.isInterface()) {
				return eclass.getInstanceClass();
			}
		}
		return eclassifierToConcrete.get(eclassifier);
	}

	/** Returns the interface class for a passed eclass */
	@Override
	public Class<?> getJavaInterfaceClass(EClass eclass) {
		return eclass.getInstanceClass();
	}

	/** Returns true if the passed EClass has a javaClass representation. */
	@Override
	public boolean hasImplementationClass(EClassifier eclassifier) {
		return null != getJavaClass(eclassifier);
	}

	/** Returns null */
	@Override
	public Object create(EClass eclass) {
		// abstract instance classes are added later
		if (eclass.isAbstract() || eclass.isInterface()) {
			return null;
		}

		// Check if the class is persistable
		try {
			return EcoreUtil.create(eclass);
		} catch (Exception e) {
			// log but do nothing because this happens when we try to create an
			// object
			// with an invalid classifier, which is a eclass!
			log.debug("The classifier: " + eclass.getName() + " is not a valid eclass");

			return null;
		}
	}

	/** Get the eclass for a certain class */
	@Override
	public EClass getEClass(Class<?> clazz) {
		if (clazz.isInterface()) {
			return interfaceToEClass.get(clazz);
		}
		return concreteToEClass.get(clazz);
	}

	/** Get the eclass for a certain class name */
	public EClass getEClass(String classname) {
		try {
			return getEClass(ClassLoaderResolver.classForName(classname));
		} catch (StoreClassLoadException e) {
			log.debug("Failed to retreive ECLass for name: " + classname);
			return null;
		}
	}

	/**
	 * Returns the structural feature for a certain field and object comby. Null is returned if
	 * nothing is found
	 */
	public EStructuralFeature getStructuralFeature(Class<?> clazz, String FieldName) {
		final EClass eclass = getEClass(clazz);
		if (eclass == null) {
			return null;
		}
		return StoreUtil.getEStructuralFeature(eclass, FieldName);
	}

	/**
	 * Returns the list of EMF interfaces which are contained. Only the topmost interface in a class
	 * hierarchy is returned. This can be used to automatically create the econtainer field
	 * mappings.
	 * 
	 * Note that multiple classes in one inheritance structure can be present.
	 */
	public Class<?>[] getContainedInterfaces() {
		return containedClasses.toArray(new Class[containedClasses.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.teneo.ecore.EModelResolver#getAllClassesAndInterfaces()
	 */
	@Override
	public List<Class<?>> getAllClassesAndInterfaces() {
		final List<Class<?>> result = new ArrayList<Class<?>>();
		result.addAll(getAllConcreteClasses());
		result.addAll(getAllInterfaces());
		return result;
	}
}