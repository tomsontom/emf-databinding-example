/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 *     Matthew Hall - bug 195222, 247997, 261843
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/
package org.eclipse.emf.databinding.edit.properties;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.edit.properties.internal.EMFEditListProperty;
import org.eclipse.emf.databinding.edit.properties.internal.EMFEditListPropertyDecorator;
import org.eclipse.emf.databinding.edit.properties.internal.EMFEditMapProperty;
import org.eclipse.emf.databinding.edit.properties.internal.EMFEditMapPropertyDecorator;
import org.eclipse.emf.databinding.edit.properties.internal.EMFEditValueProperty;
import org.eclipse.emf.databinding.edit.properties.internal.EMFEditValuePropertyDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * A factory for creating properties for {@link EObject} objects
 * 
 * @since 1.1
 */
public class EMFEditProperties {
	/**
	 * Returns a value property for the given property name of an arbitrary bean
	 * class. Objects lacking the named property are treated the same as if the
	 * property always contains null.
	 * 
	 * @param domain
	 *            the domain the domain the changes happen in
	 * 
	 * @param featurePath
	 *            the property name. May be nested e.g.
	 *            <code>property.values(MyPackage.Literals.OBJ_PARENT, MyPackage.Literals.PARENT_NAME)</code>
	 * @return a value property for the given property name of an arbitrary bean
	 *         class.
	 */
	public static IEMFEditValueProperty value(EditingDomain domain,
			EStructuralFeature... featurePath) {
		IValueProperty property = new EMFEditValueProperty(domain,
				featurePath[0]);

		IEMFEditValueProperty emfProperty = new EMFEditValuePropertyDecorator(
				domain, property, featurePath[0]);
		for (int i = 1; i < featurePath.length; i++) {
			emfProperty = emfProperty.value(featurePath[i]);
		}
		return emfProperty;
	}

	// public static IEMFValueProperty[] values(EStructuralFeature[]...
	// features) {
	// return null;
	// }

	// public static IEMFSetProperty set(EStructuralFeature feature) {
	// ISetProperty property = new EMFSetProperty(feature, elementType);
	// return new EMFSetPropertyDecorator(property, feature);
	// }

	/**
	 * Returns a list property for the given property name of an arbitrary bean
	 * class. Objects lacking the named property are treated the same as if the
	 * property always contains an empty list.
	 * 
	 * @param domain
	 *            the domain the domain the changes happen in
	 * 
	 * @param feature
	 *            the property name
	 * @return a list property for the given property name of an arbitrary bean
	 *         class.
	 */
	public static IEMFEditListProperty list(EditingDomain domain,
			EStructuralFeature feature) {
		IListProperty property = new EMFEditListProperty(domain, feature);
		return new EMFEditListPropertyDecorator(domain, property, feature);
	}

	/**
	 * Returns a map property for the given property name of an arbitrary bean
	 * class. Objects lacking the named property are treated the same as if the
	 * property always contains an empty map.
	 * 
	 * @param domain
	 *            the domain the changes happen in
	 * 
	 * @param feature
	 *            the property name
	 * @return a map property for the given property name of an arbitrary bean
	 *         class.
	 */
	public static IEMFEditMapProperty map(EditingDomain domain,
			EStructuralFeature feature) {
		return map(domain, feature, null, null);
	}

	/**
	 * Returns a map property for the given property name of the given bean
	 * class.
	 * 
	 * @param domain
	 *            the domain the changes happen in
	 * 
	 * @param feature
	 *            the property name
	 * @param keyType
	 *            the key type for the returned map property
	 * @param valueType
	 *            the value type for the returned map property
	 * @return a map property for the given property name of the given bean
	 *         class.
	 */
	public static IEMFEditMapProperty map(EditingDomain domain,
			EStructuralFeature feature, Class<?> keyType, Class<?> valueType) {
		IMapProperty property = new EMFEditMapProperty(domain, feature,
				keyType, valueType);
		return new EMFEditMapPropertyDecorator(domain, property, feature);
	}
}
