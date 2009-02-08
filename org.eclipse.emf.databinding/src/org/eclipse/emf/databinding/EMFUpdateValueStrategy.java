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
 * $Id: EMFUpdateValueStrategy.java,v 1.2 2008/02/22 12:10:18 emerks Exp $
 */
package org.eclipse.emf.databinding;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;

/**
 * <p>
 * <b>PROVISIONAL This API is subject to arbitrary change, including renaming or
 * removal.</b>
 * </p>
 * Customizes a {@link Binding} between two {@link IObservableValue observable
 * values}. The following behaviors can be customized via the strategy:
 * <ul>
 * <li>Validation</li>
 * <li>Conversion</li>
 * <li>Automatic processing</li>
 * </ul>
 * <p>
 * The update phases are:
 * <ol>
 * <li>Validate after get - {@link #validateAfterGet(Object)}</li>
 * <li>Conversion - {@link #convert(Object)}</li>
 * <li>Validate after conversion - {@link #validateAfterConvert(Object)}</li>
 * <li>Validate before set - {@link #validateBeforeSet(Object)}</li>
 * <li>Value set - {@link #doSet(IObservableValue, Object)}</li>
 * </ol>
 * </p>
 * <p>
 * Validation:<br/>
 * {@link IValidator Validators} validate the value at multiple phases in the
 * update process. Statuses returned from validators are aggregated into a
 * <code>MultiStatus</code> until a status of <code>ERROR</code> or
 * <code>CANCEL</code> is encountered. Either of these statuses will abort the
 * update process. These statuses are available as the
 * {@link Binding#getValidationStatus() binding validation status}.
 * </p>
 * <p>
 * Conversion:<br/>
 * A {@link IConverter converter} will convert the value from the type of the
 * source observable into the type of the destination. The strategy has the
 * ability to default converters for common scenarios.
 * </p>
 * <p>
 * Automatic processing:<br/>
 * The processing to perform when the source observable changes. This behavior
 * is configured via policies provided on construction of the strategy (e.g.
 * {@link #POLICY_NEVER}, {@link #POLICY_CONVERT}, {@link #POLICY_ON_REQUEST},
 * {@link #POLICY_UPDATE}).
 * </p>
 * 
 * @see DataBindingContext#bindValue(IObservableValue, IObservableValue,
 *      UpdateValueStrategy, UpdateValueStrategy)
 * @see Binding#getValidationStatus()
 * @see IValidator
 * @see IConverter
 * @since 1.0
 */
public class EMFUpdateValueStrategy extends UpdateValueStrategy {
	/**
	 * Creates a new update value strategy for automatically updating the
	 * destination observable value whenever the source observable value
	 * changes. Default validators and a default converter will be provided. The
	 * defaults can be changed by calling one of the setter methods.
	 */
	public EMFUpdateValueStrategy() {
		this(true, POLICY_UPDATE);
	}
	
	/**
	 * Creates a new update value strategy with a configurable update policy.
	 * Default validators and a default converter will be provided. The defaults
	 * can be changed by calling one of the setter methods.
	 *
	 * @param updatePolicy
	 *            one of {@link #POLICY_NEVER}, {@link #POLICY_ON_REQUEST},
	 *            {@link #POLICY_CONVERT}, or {@link #POLICY_UPDATE}
	 */
	public EMFUpdateValueStrategy(int updatePolicy) {
		this(true, updatePolicy);
	}

	/**
	 * Creates a new update value strategy with a configurable update policy.
	 * Default validators and a default converter will be provided if
	 * <code>provideDefaults</code> is <code>true</code>. The defaults can
	 * be changed by calling one of the setter methods.
	 *
	 * @param provideDefaults
	 *            if <code>true</code>, default validators and a default
	 *            converter will be provided based on the observable value's
	 *            type.
	 * @param updatePolicy
	 *            one of {@link #POLICY_NEVER}, {@link #POLICY_ON_REQUEST},
	 *            {@link #POLICY_CONVERT}, or {@link #POLICY_UPDATE}
	 */
	public EMFUpdateValueStrategy(boolean provideDefaults, int updatePolicy) {
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
						String value = fromObject == null ? null : fromObject
								.toString();
						if (eAttribute.isMany()) {
							List<Object> result = new ArrayList<Object>();
							if (value != null) {
								for (String element : value.split(" ")) {
									result.add(eFactory.createFromString(
											eDataType, element));
								}
							}
							return result;
						} else {
							return eFactory.createFromString(eDataType, value);
						}
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
						if (eAttribute.isMany()) {
							StringBuilder result = new StringBuilder();
							for (Object value : (List<?>) fromObject) {
								if (result.length() == 0) {
									result.append(' ');
								}
								result.append(eFactory.convertToString(
										eDataType, value));
							}
							return result.toString();
						} else {
							return eFactory.convertToString(eDataType,
									fromObject);
						}
					}
				};
			}
		}
		return super.createConverter(fromType, toType);
	}
}
