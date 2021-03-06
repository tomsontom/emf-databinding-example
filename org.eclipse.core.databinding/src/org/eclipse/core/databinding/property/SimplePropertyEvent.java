/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 ******************************************************************************/

package org.eclipse.core.databinding.property;

import java.util.EventObject;

import org.eclipse.core.databinding.observable.IDiff;
import org.eclipse.core.internal.databinding.Util;

/**
 * Base class for change events in the properties API
 * 
 * @since 1.2
 */
public final class SimplePropertyEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	/**
	 * The property that changed
	 */
	public final IProperty property;

	/**
	 * A diff object describing the change in state, or null for an unknown
	 * change.
	 */
	public final IDiff diff;

	/**
	 * Constructs a PropertyChangeEvent with the given attributes
	 * 
	 * @param source
	 *            the property source
	 * @param property
	 *            the property that changed on the source
	 * @param diff
	 *            a diff describing the change in state, or null if the change
	 *            is unknown.
	 */
	public SimplePropertyEvent(Object source, IProperty property, IDiff diff) {
		super(source);
		this.property = property;
		this.diff = diff;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		SimplePropertyEvent that = (SimplePropertyEvent) obj;
		return Util.equals(getSource(), that.getSource())
				&& Util.equals(this.property, that.property)
				&& Util.equals(this.diff, that.diff);
	}

	public int hashCode() {
		int hash = 17;
		hash = hash * 37 + getSource().hashCode();
		hash = hash * 37 + property.hashCode();
		hash = hash * 37 + (diff == null ? 0 : diff.hashCode());
		return hash;
	}
}
