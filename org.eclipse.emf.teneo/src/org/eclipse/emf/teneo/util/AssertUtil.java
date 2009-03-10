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
 * $Id: AssertUtil.java,v 1.6 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.util;

import org.eclipse.emf.ecore.EObject;

/**
 * Contains utility methods for assertions
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class AssertUtil {
	/** AssertTrue with message */
	public static void assertTrue(String msg, boolean value) {
		if (!value) {
			throw new AssertionError(msg);
		}
	}

	/** Same resource check */
	public static void assertResource(EObject obj1, EObject obj2) {
		if (obj1.eResource() == null && ((EObject) obj2).eResource() == null)
			return;
		if (obj1.eResource() != ((EObject) obj2).eResource()) {
			throw new AssertionError("The resources are different: "
					+ obj1.getClass().getName() + "/"
					+ obj2.getClass().getName());
		}
	}

	/** Checks for correct container relations */
	public static void assertContainer(EObject parent, EObject child) {
		if (parent != child.eContainer()) {
			throw new AssertionError(
					"The child's container is incorrect! parent/child: "
							+ parent.getClass().getName() + "/"
							+ child.getClass().getName());
		}
	}

	/** Asserts that the passed entry is null */
	public static void assertIsNull(Object obj) {
		if (obj != null) {
			throw new AssertionError("Passed object: "
					+ obj.getClass().getName()
					+ " is not null while this was expected");
		}
	}

	/**
	 * Checks if the passed object is of the class specified, null values are
	 * ignored
	 */
	public static void assertInstanceOf(Object obj, Class<?> expClass) {
		if (obj == null)
			return;
		if (!(expClass.isAssignableFrom(obj.getClass()))) {
			throw new AssertionError("Expected class: " + expClass.getName()
					+ " but object has class: " + obj.getClass().getName());
		}
	}

	/**
	 * Checks if the passed object is of the class specified, null values throw
	 * an exception
	 */
	public static void assertInstanceOfNotNull(Object obj, Class<?> expClass) {
		if (obj == null) {
			throw new AssertionError(
					"Checking instanceof but object is null, expecting class: "
							+ expClass.getName());
		}
		if (!(expClass.isAssignableFrom(obj.getClass()))) {
			throw new AssertionError("Expected class: " + expClass.getName()
					+ " but object has class: " + obj.getClass().getName());
		}
	}

	/** Checks object memory equality */
	public static void assertSameObject(Object obj1, Object obj2) {
		if (obj1 != obj2) {
			throw new AssertionError("Objects are not the same");
		}
	}

	/** Checks object memory inequality */
	public static void assertNotSameObject(Object obj1, Object obj2) {
		if (obj1 == obj2) {
			throw new AssertionError("Objects are the same");
		}
	}

}