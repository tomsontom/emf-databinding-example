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
 * $Id: BookOnTapeImpl.java,v 1.4 2007/02/20 17:41:55 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.BookOnTape;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.Writer;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book On Tape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookOnTapeImpl#getReader <em>Reader</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.BookOnTapeImpl#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BookOnTapeImpl extends AudioVisualItemImpl implements BookOnTape
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected BookOnTapeImpl()
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
		return EXTLibraryPackage.Literals.BOOK_ON_TAPE;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Person getReader()
  {
		return (Person)eGet(EXTLibraryPackage.Literals.BOOK_ON_TAPE__READER, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setReader(Person newReader)
  {
		eSet(EXTLibraryPackage.Literals.BOOK_ON_TAPE__READER, newReader);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Writer getAuthor()
  {
		return (Writer)eGet(EXTLibraryPackage.Literals.BOOK_ON_TAPE__AUTHOR, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setAuthor(Writer newAuthor)
  {
		eSet(EXTLibraryPackage.Literals.BOOK_ON_TAPE__AUTHOR, newAuthor);
	}

} //BookOnTapeImpl
