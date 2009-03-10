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
 * $Id: BookImpl.java,v 1.5 2008/12/22 14:25:21 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.BookCategory;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Writer;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getPages <em>Pages</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookImpl extends CirculatingItemImpl implements Book
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected BookImpl()
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
		return EXTLibraryPackage.Literals.BOOK;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getTitle()
  {
		return (String)eGet(EXTLibraryPackage.Literals.BOOK__TITLE, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTitle(String newTitle)
  {
		eSet(EXTLibraryPackage.Literals.BOOK__TITLE, newTitle);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public int getPages()
  {
		return ((Integer)eGet(EXTLibraryPackage.Literals.BOOK__PAGES, true)).intValue();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setPages(int newPages)
  {
		eSet(EXTLibraryPackage.Literals.BOOK__PAGES, new Integer(newPages));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public BookCategory getCategory()
  {
		return (BookCategory)eGet(EXTLibraryPackage.Literals.BOOK__CATEGORY, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setCategory(BookCategory newCategory)
  {
		eSet(EXTLibraryPackage.Literals.BOOK__CATEGORY, newCategory);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void unsetCategory()
  {
		eUnset(EXTLibraryPackage.Literals.BOOK__CATEGORY);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isSetCategory()
  {
		return eIsSet(EXTLibraryPackage.Literals.BOOK__CATEGORY);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Writer getAuthor()
  {
		return (Writer)eGet(EXTLibraryPackage.Literals.BOOK__AUTHOR, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setAuthor(Writer newAuthor)
  {
		eSet(EXTLibraryPackage.Literals.BOOK__AUTHOR, newAuthor);
	}

} //BookImpl
