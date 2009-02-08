/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 195222)
 *     Tom Schindl<tom.schindl@bestsolution.at> - port to EMF
 ******************************************************************************/

package org.eclipse.emf.databinding.properties;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * An {@link IListProperty} extension interface with convenience methods for
 * creating nested bean properties.
 * 
 * @since 1.1
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFListProperty extends IEMFProperty, IListProperty {
	/**
	 * Returns a master-detail combination of this property and the specified
	 * value property.
	 * 
	 * @param featurePath
	 *            the value property to observe. May be nested e.g.
	 * 
	 *            <code>property.values(MyPackage.Literals.OBJ_PARENT, MyPackage.Literals.PARENT_NAME)</code>
	 * @return a nested combination of this property and the specified value
	 *         property.
	 * @see #values(IEMFValueProperty)
	 */
	public IEMFListProperty values(EStructuralFeature... featurePath);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * value property. The returned property will observe the specified value
	 * property for all elements observed by this list property.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * // Observes the list-typed &quot;children&quot; property of a Person object,
	 * // where the elements are Person objects
	 * IBeanListProperty children = BeanProperties.list(Person.class, &quot;children&quot;,
	 * 		Person.class);
	 * // Observes the string-typed &quot;name&quot; property of a Person object
	 * IBeanValueProperty name = BeanProperties.value(Person.class, &quot;name&quot;);
	 * // Observes the names of children of a Person object.
	 * IBeanListProperty childrenNames = children.values(name);
	 * </pre>
	 * 
	 * @param property
	 *            the detail property to observe
	 * @return a master-detail combination of this property and the specified
	 *         value property.
	 */
	public IEMFListProperty values(IEMFValueProperty property);
}
