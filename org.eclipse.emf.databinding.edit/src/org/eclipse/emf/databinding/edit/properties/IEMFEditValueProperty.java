/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 195222)
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/

package org.eclipse.emf.databinding.edit.properties;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * An {@link IValueProperty} extension interface with convenience methods for
 * creating nested bean properties.
 * 
 * @since 1.1
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFEditValueProperty extends IEMFEditProperty, IValueProperty {
	/**
	 * Returns a master-detail combination of this property and the specified
	 * value property.
	 * 
	 * @param featurePath
	 *            the value property to observe. May be nested e.g.
	 * 
	 *            <code>property.values(MyPackage.Literals.OBJ_PARENT, MyPackage.Literals.PARENT_NAME)</code>
	 * @return a master-detail combination of this property and the specified
	 *         value property.
	 * @see #value(IEMFEditValueProperty)
	 */
	public IEMFEditValueProperty value(EStructuralFeature... featurePath);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * value property. The returned property will observe the specified detail
	 * value property for the value of the master value property.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * // Observes the Node-typed &quot;parent&quot; property of a Node object
	 * IBeanValueProperty parent = BeanProperties.value(Node.class, &quot;parent&quot;);
	 * // Observes the string-typed &quot;name&quot; property of a Node object
	 * IBeanValueProperty name = BeanProperties.value(Node.class, &quot;name&quot;);
	 * // Observes the name of the parent of a Node object.
	 * IBeanValueProperty parentName = parent.value(name);
	 * </pre>
	 * 
	 * @param property
	 *            the detail property to observe
	 * @return a master-detail combination of this property and the specified
	 *         value property.
	 */
	public IEMFEditValueProperty value(IEMFEditValueProperty property);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * list property.
	 * 
	 * @param feature
	 *            the list property to observe
	 * @return a master-detail combination of this property and the specified
	 *         list property.
	 * @see #list(IEMFEditListProperty)
	 */
	public IEMFEditListProperty list(EStructuralFeature feature);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * list property. The returned property will observe the specified list
	 * property for the value of the master property.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * // Observes the Node-typed &quot;parent&quot; property of a Node object.
	 * IBeanValueProperty parent = BeanProperties.value(Node.class, &quot;parent&quot;);
	 * // Observes the List-typed &quot;children&quot; property of a Node object
	 * // where the elements are Node objects
	 * IBeanListProperty children = BeanProperties.list(Node.class, &quot;children&quot;,
	 * 		Node.class);
	 * // Observes the children of the parent (siblings) of a Node object.
	 * IBeanListProperty siblings = parent.list(children);
	 * </pre>
	 * 
	 * @param property
	 *            the detail property to observe
	 * @return a master-detail combination of this property and the specified
	 *         list property.
	 */
	public IEMFEditListProperty list(IEMFEditListProperty property);

	// /**
	// * Returns a master-detail combination of this property and the specified
	// * set property.
	// *
	// * @param propertyName
	// * the set property to observe
	// * @return a master-detail combination of this property and the specified
	// * set property.
	// * @see #set(IEMFSetProperty)
	// */
	// public IEMFSetProperty set(EStructuralFeature featurePath);
	//
	// /**
	// * Returns a master-detail combination of this property and the specified
	// * set property. The returned property will observe the specified set
	// * property for the value of the master property.
	// * <p>
	// * Example:
	// *
	// * <pre>
	// * // Observes the Node-typed &quot;parent&quot; property of a Node
	// object.
	// * IBeanValueProperty parent = BeanProperties.value(Node.class,
	// &quot;parent&quot;);
	// * // Observes the Set-typed &quot;children&quot; property of a Node
	// object
	// * // where the elements are Node objects
	// * IBeanSetProperty children = BeanProperties.set(Node.class,
	// &quot;children&quot;,
	// * Node.class);
	// * // Observes the children of the parent (siblings) of a Node object.
	// * IBeanSetProperty siblings = parent.set(children);
	// * </pre>
	// *
	// * @param property
	// * the detail property to observe
	// * @return a master-detail combination of this property and the specified
	// * set property.
	// */
	// public IEMFSetProperty set(IEMFSetProperty property);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * map property.
	 * 
	 * @param feature
	 *            the map property to observe
	 * @return a master-detail combination of this property and the specified
	 *         map property.
	 * @see #map(IEMFEditMapProperty)
	 */
	public IEMFEditMapProperty map(EStructuralFeature feature);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * map property. The returned property will observe the specified map
	 * property for the value of the master property.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * // Observes the Contact-typed &quot;supervisor&quot; property of a
	 * // Contact class 
	 * IBeanValueProperty supervisor = BeanProperties.value(Contact.class,
	 * 		&quot;supervisor&quot;);
	 * // Observes the property &quot;phoneNumbers&quot; of a Contact object--a property mapping
	 * // from PhoneNumberType to PhoneNumber &quot;set-typed &quot;children&quot;,
	 * IBeanMapProperty phoneNumbers = BeanProperties.map(Contact.class,
	 * 		&quot;phoneNumbers&quot;, PhoneNumberType.class, PhoneNumber.class);
	 * // Observes the phone numbers of a contact's supervisor:
	 * IBeanMapProperty supervisorPhoneNumbers = supervisor.map(phoneNumbers);
	 * </pre>
	 * 
	 * @param property
	 *            the detail property to observe
	 * @return a master-detail combination of this property and the specified
	 *         map property.
	 */
	public IEMFEditMapProperty map(IEMFEditMapProperty property);
}
