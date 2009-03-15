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
 *   Martin Taal
 * </copyright>
 *
 * $Id: NewEContainerFeatureIDPropertyHandler.java,v 1.1 2009/03/15 14:49:46 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.econtainer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.mapping.econtainer.EContainerFeatureIDUserType.EContainerFeatureIDHolder;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.eclipse.emf.teneo.util.FieldUtil;
import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;

/**
 * Handles the eContainerFeatureId field of an EObjectImpl.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.1 $
 */

public class NewEContainerFeatureIDPropertyHandler implements PropertyAccessor,
		Getter, Setter, ExtensionPoint {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -414024662032391298L;

	private static Log log = LogFactory
			.getLog(NewEContainerFeatureIDPropertyHandler.class);

	private Field ecField;

	public NewEContainerFeatureIDPropertyHandler() {
		log.debug("Created eContainer property handler");
		ecField = FieldUtil.getField(EObjectImpl.class, "eContainerFeatureID");
	}

	public Getter getGetter(Class theClass, String propertyName)
			throws PropertyNotFoundException {
		return this;
	}

	public Setter getSetter(Class theClass, String propertyName)
			throws PropertyNotFoundException {
		return this;
	}

	public Object get(Object owner) throws HibernateException {
		final InternalEObject eObject = (InternalEObject) owner;
		if (eObject.eContainer() == null) {
			return null;
		}
		final EContainerFeatureIDHolder holder = new EContainerFeatureIDHolder();
		holder.setEClass(eObject.eContainer().eClass());
		holder.setEFeature(eObject.eContainingFeature());
		return holder;
	}

	@SuppressWarnings("unchecked")
	public Object getForInsert(Object owner, Map mergeMap,
			SessionImplementor session) throws HibernateException {
		return get(owner);
	}

	public void set(Object target, Object value,
			SessionFactoryImplementor factory) throws HibernateException {
		AssertUtil.assertInstanceOfNotNull(target, InternalEObject.class);
		AssertUtil.assertInstanceOf(value, EContainerFeatureIDHolder.class);

		if (value != null) {
			EContainerFeatureIDHolder holder = (EContainerFeatureIDHolder) value;
			try {
				ecField.set(target, getContainerFeatureId(holder.getEClass(),
						(EObject) target, holder.getEFeature()));
			} catch (Exception e) {
				throw new HbMapperException(
						"Exception when setting econtainer for: "
								+ target.getClass().getName() + " to value: "
								+ value, e);
			}
		}
	}

	public int getContainerFeatureId(EClass containingEClass,
			EObject contained, EStructuralFeature eFeature) {
		if (eFeature instanceof EAttribute) {
			return InternalEObject.EOPPOSITE_FEATURE_BASE
					- containingEClass.getFeatureID(eFeature);

		}
		final EReference eReference = (EReference) eFeature;
		if (eReference.getEOpposite() != null) {
			final EReference containerEReference = eReference.getEOpposite();
			return contained.eClass().getFeatureID(containerEReference);
		} else {
			return InternalEObject.EOPPOSITE_FEATURE_BASE
					- containingEClass.getFeatureID(eReference);
		}
	}

	public Method getMethod() {
		return null;
	}

	public String getMethodName() {
		return null;
	}

	public Class<?> getReturnType() {
		return EStructuralFeature.class;
	}
}