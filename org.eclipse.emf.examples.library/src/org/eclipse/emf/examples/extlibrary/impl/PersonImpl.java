/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: PersonImpl.java,v 1.3 2006/12/29 18:27:44 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PersonImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PersonImpl#getFirstName <em>First Name</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PersonImpl#getLastName <em>Last Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersonImpl extends CDOObjectImpl implements Person
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected PersonImpl()
  {
		super();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected EClass eStaticClass()
  {
		return EXTLibraryPackage.Literals.PERSON;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  protected int eStaticFeatureCount()
  {
		return 0;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getAddress()
  {
		return (String)eGet(EXTLibraryPackage.Literals.ADDRESSABLE__ADDRESS, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setAddress(String newAddress)
  {
		eSet(EXTLibraryPackage.Literals.ADDRESSABLE__ADDRESS, newAddress);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getFirstName()
  {
		return (String)eGet(EXTLibraryPackage.Literals.PERSON__FIRST_NAME, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setFirstName(String newFirstName)
  {
		eSet(EXTLibraryPackage.Literals.PERSON__FIRST_NAME, newFirstName);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getLastName()
  {
		return (String)eGet(EXTLibraryPackage.Literals.PERSON__LAST_NAME, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setLastName(String newLastName)
  {
		eSet(EXTLibraryPackage.Literals.PERSON__LAST_NAME, newLastName);
	}

} //PersonImpl
