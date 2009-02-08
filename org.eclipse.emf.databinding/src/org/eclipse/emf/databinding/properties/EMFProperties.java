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
package org.eclipse.emf.databinding.properties;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.properties.internal.EMFListProperty;
import org.eclipse.emf.databinding.properties.internal.EMFListPropertyDecorator;
import org.eclipse.emf.databinding.properties.internal.EMFMapProperty;
import org.eclipse.emf.databinding.properties.internal.EMFMapPropertyDecorator;
import org.eclipse.emf.databinding.properties.internal.EMFValueProperty;
import org.eclipse.emf.databinding.properties.internal.EMFValuePropertyDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * A factory for creating properties for {@link EObject} objects
 * 
 * @since 1.1
 */
public class EMFProperties {
	/**
	 * Returns a value property for the given property name of an arbitrary bean
	 * class. Objects lacking the named property are treated the same as if the
	 * property always contains null.
	 * 
	 * @param featurePath
	 *            the property name. May be nested e.g.
	 *            <code>property.values(MyPackage.Literals.OBJ_PARENT, MyPackage.Literals.PARENT_NAME)</code>
	 * @return a value property for the given property name of an arbitrary bean
	 *         class.
	 */
	public static IEMFValueProperty value(EStructuralFeature... featurePath) {
		IValueProperty property = new EMFValueProperty(featurePath[0]);

		IEMFValueProperty emfProperty = new EMFValuePropertyDecorator(property,
				featurePath[0]);
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
	 * @param feature
	 *            the property name
	 * @return a list property for the given property name of an arbitrary bean
	 *         class.
	 */
	public static IEMFListProperty list(EStructuralFeature feature) {
		IListProperty property = new EMFListProperty(feature);
		return new EMFListPropertyDecorator(property, feature);
	}

	/**
	 * Returns a map property for the given property name of an arbitrary bean
	 * class. Objects lacking the named property are treated the same as if the
	 * property always contains an empty map.
	 * 
	 * @param feature
	 *            the property name
	 * @return a map property for the given property name of an arbitrary bean
	 *         class.
	 */
	public static IEMFMapProperty map(EStructuralFeature feature) {
		return map(feature, null, null);
	}

	/**
	 * Returns a map property for the given property name of the given bean
	 * class.
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
	public static IEMFMapProperty map(EStructuralFeature feature,
			Class<?> keyType, Class<?> valueType) {
		IMapProperty property = new EMFMapProperty(feature, keyType, valueType);
		return new EMFMapPropertyDecorator(property, feature);
	}
}
