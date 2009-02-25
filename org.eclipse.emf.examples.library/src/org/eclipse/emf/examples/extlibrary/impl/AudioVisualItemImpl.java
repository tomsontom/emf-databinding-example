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
 * $Id: AudioVisualItemImpl.java,v 1.3 2008/12/22 14:25:21 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.AudioVisualItem;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Audo Visual Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudioVisualItemImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudioVisualItemImpl#getMinutesLength <em>Minutes Length</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.AudioVisualItemImpl#isDamaged <em>Damaged</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AudioVisualItemImpl extends CirculatingItemImpl implements AudioVisualItem
{
  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected AudioVisualItemImpl()
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
		return EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getTitle()
  {
		return (String)eGet(EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__TITLE, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setTitle(String newTitle)
  {
		eSet(EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__TITLE, newTitle);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public int getMinutesLength()
  {
		return (Integer)eGet(EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__MINUTES_LENGTH, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setMinutesLength(int newMinutesLength)
  {
		eSet(EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__MINUTES_LENGTH, newMinutesLength);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public boolean isDamaged()
  {
		return (Boolean)eGet(EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__DAMAGED, true);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setDamaged(boolean newDamaged)
  {
		eSet(EXTLibraryPackage.Literals.AUDIO_VISUAL_ITEM__DAMAGED, newDamaged);
	}

} //AudioVisualItemImpl
