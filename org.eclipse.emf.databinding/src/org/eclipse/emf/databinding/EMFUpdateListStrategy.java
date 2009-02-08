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
 * $Id: EMFUpdateListStrategy.java,v 1.1 2007/11/16 21:25:21 emerks Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateListStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * Customizes a {@link Binding} between two {@link IObservableList observable
 * lists}. The following behaviors can be customized via the strategy:
 * <ul>
 * <li>Conversion</li>
 * <li>Automatic processing</li>
 * </ul>
 * <p>
 * Conversion:<br/>
 * When elements are added they can be {@link #convert(Object) converted} to the
 * destination element type.
 * </p>
 * <p>
 * Automatic processing:<br/>
 * The processing to perform when the source observable changes. This behavior
 * is configured via policies provided on construction of the strategy (e.g.
 * {@link #POLICY_NEVER}, {@link #POLICY_ON_REQUEST}, {@link #POLICY_UPDATE}).
 * </p>
 * 
 * 
 * @see DataBindingContext#bindList(IObservableList, IObservableList,
 *      UpdateListStrategy, UpdateListStrategy)
 * @see IConverter
 * @since 1.0
 */
public class EMFUpdateListStrategy extends UpdateListStrategy {
	/**
	 * Creates a new update list strategy for automatically updating the
	 * destination observable list whenever the source observable list changes.
	 * A default converter will be provided. The defaults can be changed by
	 * calling one of the setter methods.
	 */
	public EMFUpdateListStrategy() {
		this(true, POLICY_UPDATE);
	}
	
	/**
	 * Creates a new update list strategy with a configurable update policy. A
	 * default converter will be provided. The defaults can be changed by
	 * calling one of the setter methods.
	 *
	 * @param updatePolicy
	 *            one of {@link #POLICY_NEVER}, {@link #POLICY_ON_REQUEST}, or
	 *            {@link #POLICY_UPDATE}
	 */
	public EMFUpdateListStrategy(int updatePolicy) {
		this(true, updatePolicy);
	}

	/**
	 * Creates a new update list strategy with a configurable update policy. A
	 * default converter will be provided if <code>provideDefaults</code> is
	 * <code>true</code>. The defaults can be changed by calling one of the
	 * setter methods.
	 *
	 * @param provideDefaults
	 *            if <code>true</code>, default validators and a default
	 *            converter will be provided based on the observable list's
	 *            type.
	 * @param updatePolicy
	 *            one of {@link #POLICY_NEVER}, {@link #POLICY_ON_REQUEST}, or
	 *            {@link #POLICY_UPDATE}
	 */
	public EMFUpdateListStrategy(boolean provideDefaults, int updatePolicy) {
		super(provideDefaults, updatePolicy);
	}

	@Override
	protected IConverter createConverter(Object fromType, Object toType) {
		if (fromType == String.class) {
			if (toType instanceof EAttribute) {
				final EAttribute eAttribute = (EAttribute) toType;
				final EDataType eDataType = eAttribute.getEAttributeType();
				final EFactory eFactory = eDataType.getEPackage()
						.getEFactoryInstance();
				return new Converter(fromType, toType) {
					public Object convert(Object fromObject) {
						return eFactory.createFromString(eDataType,
								(String) fromObject);
					}
				};
			}
		} else if (toType == String.class) {
			if (fromType instanceof EAttribute) {
				final EAttribute eAttribute = (EAttribute) fromType;
				final EDataType eDataType = eAttribute.getEAttributeType();
				final EFactory eFactory = eDataType.getEPackage()
						.getEFactoryInstance();
				return new Converter(fromType, toType) {
					public Object convert(Object fromObject) {
						return eFactory.convertToString(eDataType, fromObject);
					}
				};
			}
		}
		return super.createConverter(fromType, toType);
	}
}
