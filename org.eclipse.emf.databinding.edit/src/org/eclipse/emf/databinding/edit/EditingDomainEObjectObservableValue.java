/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EditingDomainEObjectObservableValue.java,v 1.1 2007/11/16 20:58:05 emerks Exp $
 */
package org.eclipse.emf.databinding.edit;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EObjectObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.
 * 
 * @deprecated you should not use this type it will be removed. Use the generic
 *             {@link IObservableValue}
 */
public class EditingDomainEObjectObservableValue extends EObjectObservableValue {
	protected EditingDomain domain;

	/**
	 * Create an observable value
	 * 
	 * @param domain
	 *            the editing domain the changes are happening in
	 * @param eObject
	 *            the object owning the observed list feature
	 * @param eStructuralFeature
	 *            the feature observed
	 * @deprecated you should use
	 *             {@link EMFEditObservables#observeValue(EditingDomain, EObject, EStructuralFeature)}
	 */
	public EditingDomainEObjectObservableValue(EditingDomain domain,
			EObject eObject, EStructuralFeature eStructuralFeature) {
		this(Realm.getDefault(), domain, eObject, eStructuralFeature);
	}

	/**
	 * Create an observable value
	 * 
	 * @param realm
	 *            the realm used to access the feature
	 * @param domain
	 *            the editing domain the changes are happening in
	 * @param eObject
	 *            the object owning the observed list feature
	 * @param eStructuralFeature
	 *            the feature observed
	 * @deprecated you should use
	 *             {@link EMFEditObservables#observeValue(Realm, EditingDomain, EObject, EStructuralFeature)}
	 */
	public EditingDomainEObjectObservableValue(Realm realm,
			EditingDomain domain, EObject eObject,
			EStructuralFeature eStructuralFeature) {
		super(realm, eObject, eStructuralFeature);
		this.domain = domain;
	}

	@Override
	public synchronized void dispose() {
		domain = null;
		super.dispose();
	}

	@Override
	protected void doSetValue(Object value) {
		Command command = SetCommand.create(domain, eObject,
				eStructuralFeature, value);
		domain.getCommandStack().execute(command);
	}
}
