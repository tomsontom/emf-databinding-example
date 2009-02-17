/*******************************************************************************
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 *     Tom Schindl<tom.schindl@bestsolution.at> - Port to EMF
 ******************************************************************************/

package org.eclipse.emf.databinding.edit.properties;

import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * An IProperty extension interface providing access to details of bean
 * properties.
 * 
 * @since 1.1
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFEditProperty extends IProperty {
	/**
	 * @return the feature observed
	 */
	public EStructuralFeature getFeature();

	/**
	 * @return the domain the changes happen in
	 */
	public EditingDomain getDomain();
}
