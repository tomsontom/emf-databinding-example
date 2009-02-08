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
 *   Tom Schindl<tom.schindl@bestsolution.at>
 * </copyright>
 *
 * $Id: EMFDataBindingContext.java,v 1.1 2007/11/16 21:25:21 emerks Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * 
 * A DataBindingContext is the point of contact for the creation and management
 * of {@link Binding bindings}, and aggregates validation statuses of its
 * bindings, or more generally, its validation status providers.
 * <p>
 * A DataBindingContext provides the following abilities:
 * <ul>
 * <li>Ability to create bindings between {@link IObservableValue observable
 * values}.</li>
 * <li>Ability to create bindings between {@link IObservableList observable
 * lists}.</li>
 * <li>Access to the bindings created by the instance.</li>
 * <li>Access to the list of validation status providers (this includes all
 * bindings).</li>
 * </ul>
 * </p>
 * <p>
 * Multiple contexts can be used at any point in time. One strategy for the
 * management of contexts is the aggregation of validation statuses. For example
 * an <code>IWizardPage</code> could use a single context and the statuses could
 * be aggregated to set the page status and fulfillment. Each page in the
 * <code>IWizard</code> would have its own context instance.
 * </p>
 * 
 * @since 1.0
 */
public class EMFDataBindingContext extends DataBindingContext {
	/**
	 * Creates a data binding context, using the current default realm for the
	 * validation observables.
	 * 
	 * @see Realm
	 */
	public EMFDataBindingContext() {
		this(Realm.getDefault());
	}

	/**
	 * Creates a data binding context using the given realm for the validation
	 * observables.
	 * 
	 * @param validationRealm
	 *            the realm to be used for the validation observables
	 * 
	 * @see Realm
	 */
	public EMFDataBindingContext(Realm validationRealm) {
		super(validationRealm);
	}

	@Override
	protected UpdateValueStrategy createModelToTargetUpdateValueStrategy(
			IObservableValue fromValue, IObservableValue toValue) {
		return new EMFUpdateValueStrategy();
	}

	@Override
	protected UpdateValueStrategy createTargetToModelUpdateValueStrategy(
			IObservableValue fromValue, IObservableValue toValue) {
		return new EMFUpdateValueStrategy();
	}
}
