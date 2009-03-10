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
 * $Id: EContainerPropertyHandler.java,v 1.6 2008/02/28 07:08:24 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapping.econtainer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.util.AssertUtil;
import org.eclipse.emf.teneo.util.FieldUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;

/**
 * Implements the accessor for eContainer member
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class EContainerPropertyHandler implements Getter, Setter, ExtensionPoint {
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -414024662032391298L;

	/** The logger */
	private static Log log = LogFactory.getLog(EContainerPropertyHandler.class);

	/** The javafield of the eContainer */
	private Field ecField;

	/** Constructor */
	public void initialize(String field) {
		log.debug("Created eContainer property handler");
		ecField = FieldUtil.getField(EObjectImpl.class, "eContainer");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#get(java.lang.Object)
	 */
	public Object get(Object owner) throws HibernateException {
		return ((EObject) owner).eContainer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getForInsert(java.lang.Object, java.util.Map,
	 *      org.hibernate.engine.SessionImplementor)
	 */
	@SuppressWarnings("unchecked")
	public Object getForInsert(Object owner, Map mergeMap, SessionImplementor session) throws HibernateException {
		return ((EObject) owner).eContainer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Setter#set(java.lang.Object, java.lang.Object,
	 *      org.hibernate.engine.SessionFactoryImplementor)
	 */
	public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException {
		AssertUtil.assertInstanceOfNotNull(target, InternalEObject.class);
		AssertUtil.assertInstanceOf(value, EObject.class);
		try {
			ecField.set(target, value);
		} catch (Exception e) {
			throw new HbMapperException("Exception when setting econtainer for: " + target.getClass().getName() +
					" to value: " + value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMethod()
	 */
	public Method getMethod() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getMethodName()
	 */
	public String getMethodName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.property.Getter#getReturnType()
	 */
	public Class<?> getReturnType() {
		return EObject.class;
	}
}