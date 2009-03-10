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
 * $Id: PeriodicalImpl.java,v 1.4 2008/12/22 14:25:22 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Periodical;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Periodical</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PeriodicalImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.PeriodicalImpl#getIssuesPerYear <em>Issues Per Year</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PeriodicalImpl extends ItemImpl implements Periodical
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected PeriodicalImpl()
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
		return EXTLibraryPackage.Literals.PERIODICAL;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getTitle()
  {
		return (String)eGet(EXTLibraryPackage.Literals.PERIODICAL__TITLE, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTitle(String newTitle)
  {
		eSet(EXTLibraryPackage.Literals.PERIODICAL__TITLE, newTitle);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public int getIssuesPerYear()
  {
		return ((Integer)eGet(EXTLibraryPackage.Literals.PERIODICAL__ISSUES_PER_YEAR, true)).intValue();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setIssuesPerYear(int newIssuesPerYear)
  {
		eSet(EXTLibraryPackage.Literals.PERIODICAL__ISSUES_PER_YEAR, new Integer(newIssuesPerYear));
	}

} //PeriodicalImpl
