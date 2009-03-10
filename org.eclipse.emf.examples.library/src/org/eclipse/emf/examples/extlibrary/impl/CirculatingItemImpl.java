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
 * $Id: CirculatingItemImpl.java,v 1.5 2008/12/22 14:25:22 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.extlibrary.Borrower;
import org.eclipse.emf.examples.extlibrary.CirculatingItem;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Lendable;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Circulating Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.CirculatingItemImpl#getCopies <em>Copies</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.CirculatingItemImpl#getBorrowers <em>Borrowers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CirculatingItemImpl extends ItemImpl implements CirculatingItem
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected CirculatingItemImpl()
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
		return EXTLibraryPackage.Literals.CIRCULATING_ITEM;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public int getCopies()
  {
		return ((Integer)eGet(EXTLibraryPackage.Literals.LENDABLE__COPIES, true)).intValue();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setCopies(int newCopies)
  {
		eSet(EXTLibraryPackage.Literals.LENDABLE__COPIES, new Integer(newCopies));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @SuppressWarnings("unchecked")
  public EList<Borrower> getBorrowers()
  {
		return (EList<Borrower>)eGet(EXTLibraryPackage.Literals.LENDABLE__BORROWERS, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
		if (baseClass == Lendable.class) {
			switch (derivedFeatureID) {
				case EXTLibraryPackage.CIRCULATING_ITEM__COPIES: return EXTLibraryPackage.LENDABLE__COPIES;
				case EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS: return EXTLibraryPackage.LENDABLE__BORROWERS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
		if (baseClass == Lendable.class) {
			switch (baseFeatureID) {
				case EXTLibraryPackage.LENDABLE__COPIES: return EXTLibraryPackage.CIRCULATING_ITEM__COPIES;
				case EXTLibraryPackage.LENDABLE__BORROWERS: return EXTLibraryPackage.CIRCULATING_ITEM__BORROWERS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //CirculatingItemImpl
