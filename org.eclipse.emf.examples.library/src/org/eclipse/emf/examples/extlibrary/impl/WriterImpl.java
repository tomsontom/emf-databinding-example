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
 * $Id: WriterImpl.java,v 1.4 2007/02/20 17:41:55 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.WriterImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.WriterImpl#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WriterImpl extends PersonImpl implements Writer
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected WriterImpl()
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
		return EXTLibraryPackage.Literals.WRITER;
	}

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getName()
  {
    if (getFirstName() == null)
    {
      if (getLastName() == null)
      {
        return ""; //$NON-NLS-1$
      }
      else
      {
        return getLastName();
      }
    }
    else if (getLastName() == null)
    {
      return getFirstName();
    }
    else
    {
      StringBuffer result = new StringBuffer();

      result.append(getFirstName()).append(' ').append(getLastName());

      return result.toString();
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setName(String newName)
  {
    if (newName == null || newName.length() == 0)
    {
      setFirstName(null);
      setLastName(null);
    }
    else
    {
      int comma = newName.indexOf(',');
      if (comma < 0)
      {
        comma = newName.indexOf(' ');
      }

      if (comma >= 0)
      {
        setFirstName(newName.substring(0, comma).trim());
        setLastName(newName.substring(comma + 1).trim());
      }
      else
      {
        setFirstName(newName);
        setLastName(null);
      }
    }
  }

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @SuppressWarnings("unchecked")
  public EList<Book> getBooks()
  {
		return (EList<Book>)eGet(EXTLibraryPackage.Literals.WRITER__BOOKS, true);
	}

} //WriterImpl
