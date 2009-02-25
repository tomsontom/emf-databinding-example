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
 * $Id: LibraryImpl.java,v 1.7 2008/02/20 22:12:51 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.Borrower;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Employee;
import org.eclipse.emf.examples.extlibrary.Item;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.Writer;

//import org.eclipse.emf.ecore.util.EObjectResolvingEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getBorrowers <em>Borrowers</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getStock <em>Stock</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getParentBranch <em>Parent Branch</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getCasts <em>Casts</em>}</li>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl#getReaders <em>Readers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LibraryImpl extends EObjectImpl implements Library
{
  /**
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
  protected static final String ADDRESS_EDEFAULT = null;

  /**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
  protected String address = ADDRESS_EDEFAULT;

  /**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
  protected static final String NAME_EDEFAULT = null;

  /**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
  protected String name = NAME_EDEFAULT;

  /**
	 * The cached value of the '{@link #getWriters() <em>Writers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWriters()
	 * @generated
	 * @ordered
	 */
	protected EList<Writer> writers;

		/**
	 * The cached value of the '{@link #getEmployees() <em>Employees</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmployees()
	 * @generated
	 * @ordered
	 */
	protected EList<Employee> employees;

		/**
	 * The cached value of the '{@link #getBorrowers() <em>Borrowers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBorrowers()
	 * @generated
	 * @ordered
	 */
	protected EList<Borrower> borrowers;

		/**
	 * The cached value of the '{@link #getStock() <em>Stock</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getStock()
	 * @generated
	 * @ordered
	 */
  protected EList<Item> stock;

  /**
	 * The cached value of the '{@link #getBooks() <em>Books</em>}' reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getBooks()
	 * @generated
	 * @ordered
	 */
  protected EList<Book> books;

  /**
	 * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference list.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #getBranches()
	 * @generated
	 * @ordered
	 */
  protected EList<Library> branches;

  /**
	 * The cached value of the '{@link #getCasts() <em>Casts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCasts()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> casts;

		/**
	 * The cached value of the '{@link #getReaders() <em>Readers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReaders()
	 * @generated
	 * @ordered
	 */
	protected EList<Person> readers;

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected LibraryImpl()
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
		return EXTLibraryPackage.Literals.LIBRARY;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getAddress()
  {
		return address;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setAddress(String newAddress)
  {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.LIBRARY__ADDRESS, oldAddress, address));
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public String getName()
  {
		return name;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setName(String newName)
  {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.LIBRARY__NAME, oldName, name));
	}

  /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Writer> getWriters() {
		if (writers == null) {
			writers = new EObjectContainmentEList<Writer>(Writer.class, this, EXTLibraryPackage.LIBRARY__WRITERS);
		}
		return writers;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Employee> getEmployees() {
		if (employees == null) {
			employees = new EObjectContainmentEList<Employee>(Employee.class, this, EXTLibraryPackage.LIBRARY__EMPLOYEES);
		}
		return employees;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Borrower> getBorrowers() {
		if (borrowers == null) {
			borrowers = new EObjectContainmentEList<Borrower>(Borrower.class, this, EXTLibraryPackage.LIBRARY__BORROWERS);
		}
		return borrowers;
	}

		/**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<Item> getStock()
  {
    if (stock == null)
    {
      // create a custom list implementation that synchronizes its book
      //    content with the "books" subset
      stock = new EObjectContainmentEList<Item>(Item.class, this, EXTLibraryPackage.LIBRARY__STOCK)
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected void didAdd(int index, Item newObject)
          {
            if ((newObject instanceof Book) && !getBooks().contains(newObject))
            {
              // these lists are unordered, so index doesn't matter
              getBooks().add((Book)newObject);
            }
          }

          @Override
          protected void didRemove(int index, Item oldObject)
          {
            if ((oldObject instanceof Book) && getBooks().contains(oldObject))
            {
              // these lists are unordered, so index doesn't matter
              getBooks().remove(oldObject);
            }
          }

          @Override
          protected void didSet(int index, Item newObject, Item oldObject)
          {
            didRemove(index, oldObject);
            didAdd(index, newObject);
          }
        };
    }
    return stock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<Book> getBooks()
  {
    if (books == null)
    {
      // create a custom list implementation that synchronizes its content
      //    with the "stock" superset
      books = new EObjectEList<Book>(Book.class, this, EXTLibraryPackage.LIBRARY__BOOKS)
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected void didAdd(int index, Book newObject)
          {
            if (getStock().indexOf(newObject) == -1)
            {
              // these lists are unordered, so index doesn't matter
              getStock().add(newObject);
            }
          }

          @Override
          protected void didRemove(int index, Book oldObject)
          {
            if (getStock().indexOf(oldObject) != -1)
            {
              // these lists are unordered, so index doesn't matter
              getStock().remove(oldObject);
            }
          }

          @Override
          protected void didSet(int index, Book newObject, Book oldObject)
          {
            didRemove(index, oldObject);
            didAdd(index, newObject);
          }
        };
    }

    return books;
  }

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public EList<Library> getBranches()
  {
		if (branches == null) {
			branches = new EObjectContainmentWithInverseEList.Resolving<Library>(Library.class, this, EXTLibraryPackage.LIBRARY__BRANCHES, EXTLibraryPackage.LIBRARY__PARENT_BRANCH);
		}
		return branches;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Library getParentBranch()
  {
		if (eContainerFeatureID() != EXTLibraryPackage.LIBRARY__PARENT_BRANCH) return null;
		return (Library)eContainer();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public Library basicGetParentBranch()
  {
		if (eContainerFeatureID() != EXTLibraryPackage.LIBRARY__PARENT_BRANCH) return null;
		return (Library)eInternalContainer();
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public NotificationChain basicSetParentBranch(Library newParentBranch, NotificationChain msgs)
  {
		msgs = eBasicSetContainer((InternalEObject)newParentBranch, EXTLibraryPackage.LIBRARY__PARENT_BRANCH, msgs);
		return msgs;
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public void setParentBranch(Library newParentBranch)
  {
		if (newParentBranch != eInternalContainer() || (eContainerFeatureID() != EXTLibraryPackage.LIBRARY__PARENT_BRANCH && newParentBranch != null)) {
			if (EcoreUtil.isAncestor(this, newParentBranch))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentBranch != null)
				msgs = ((InternalEObject)newParentBranch).eInverseAdd(this, EXTLibraryPackage.LIBRARY__BRANCHES, Library.class, msgs);
			msgs = basicSetParentBranch(newParentBranch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.LIBRARY__PARENT_BRANCH, newParentBranch, newParentBranch));
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Person> getCasts() {
		if (casts == null) {
			casts = new EObjectContainmentEList<Person>(Person.class, this, EXTLibraryPackage.LIBRARY__CASTS);
		}
		return casts;
	}

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Person> getReaders() {
		if (readers == null) {
			readers = new EObjectContainmentEList<Person>(Person.class, this, EXTLibraryPackage.LIBRARY__READERS);
		}
		return readers;
	}

		/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
		switch (featureID) {
			case EXTLibraryPackage.LIBRARY__BRANCHES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBranches()).basicAdd(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentBranch((Library)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
		switch (featureID) {
			case EXTLibraryPackage.LIBRARY__WRITERS:
				return ((InternalEList<?>)getWriters()).basicRemove(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__EMPLOYEES:
				return ((InternalEList<?>)getEmployees()).basicRemove(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__BORROWERS:
				return ((InternalEList<?>)getBorrowers()).basicRemove(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__STOCK:
				return ((InternalEList<?>)getStock()).basicRemove(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__BRANCHES:
				return ((InternalEList<?>)getBranches()).basicRemove(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				return basicSetParentBranch(null, msgs);
			case EXTLibraryPackage.LIBRARY__CASTS:
				return ((InternalEList<?>)getCasts()).basicRemove(otherEnd, msgs);
			case EXTLibraryPackage.LIBRARY__READERS:
				return ((InternalEList<?>)getReaders()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
		switch (eContainerFeatureID()) {
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				return eInternalContainer().eInverseRemove(this, EXTLibraryPackage.LIBRARY__BRANCHES, Library.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
		switch (featureID) {
			case EXTLibraryPackage.LIBRARY__ADDRESS:
				return getAddress();
			case EXTLibraryPackage.LIBRARY__NAME:
				return getName();
			case EXTLibraryPackage.LIBRARY__WRITERS:
				return getWriters();
			case EXTLibraryPackage.LIBRARY__EMPLOYEES:
				return getEmployees();
			case EXTLibraryPackage.LIBRARY__BORROWERS:
				return getBorrowers();
			case EXTLibraryPackage.LIBRARY__STOCK:
				return getStock();
			case EXTLibraryPackage.LIBRARY__BOOKS:
				return getBooks();
			case EXTLibraryPackage.LIBRARY__BRANCHES:
				return getBranches();
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				if (resolve) return getParentBranch();
				return basicGetParentBranch();
			case EXTLibraryPackage.LIBRARY__CASTS:
				return getCasts();
			case EXTLibraryPackage.LIBRARY__READERS:
				return getReaders();
		}
		return super.eGet(featureID, resolve, coreType);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
		switch (featureID) {
			case EXTLibraryPackage.LIBRARY__ADDRESS:
				setAddress((String)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__NAME:
				setName((String)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__WRITERS:
				getWriters().clear();
				getWriters().addAll((Collection<? extends Writer>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__EMPLOYEES:
				getEmployees().clear();
				getEmployees().addAll((Collection<? extends Employee>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__BORROWERS:
				getBorrowers().clear();
				getBorrowers().addAll((Collection<? extends Borrower>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__STOCK:
				getStock().clear();
				getStock().addAll((Collection<? extends Item>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__BOOKS:
				getBooks().clear();
				getBooks().addAll((Collection<? extends Book>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__BRANCHES:
				getBranches().clear();
				getBranches().addAll((Collection<? extends Library>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				setParentBranch((Library)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__CASTS:
				getCasts().clear();
				getCasts().addAll((Collection<? extends Person>)newValue);
				return;
			case EXTLibraryPackage.LIBRARY__READERS:
				getReaders().clear();
				getReaders().addAll((Collection<? extends Person>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public void eUnset(int featureID)
  {
		switch (featureID) {
			case EXTLibraryPackage.LIBRARY__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case EXTLibraryPackage.LIBRARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EXTLibraryPackage.LIBRARY__WRITERS:
				getWriters().clear();
				return;
			case EXTLibraryPackage.LIBRARY__EMPLOYEES:
				getEmployees().clear();
				return;
			case EXTLibraryPackage.LIBRARY__BORROWERS:
				getBorrowers().clear();
				return;
			case EXTLibraryPackage.LIBRARY__STOCK:
				getStock().clear();
				return;
			case EXTLibraryPackage.LIBRARY__BOOKS:
				getBooks().clear();
				return;
			case EXTLibraryPackage.LIBRARY__BRANCHES:
				getBranches().clear();
				return;
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				setParentBranch((Library)null);
				return;
			case EXTLibraryPackage.LIBRARY__CASTS:
				getCasts().clear();
				return;
			case EXTLibraryPackage.LIBRARY__READERS:
				getReaders().clear();
				return;
		}
		super.eUnset(featureID);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public boolean eIsSet(int featureID)
  {
		switch (featureID) {
			case EXTLibraryPackage.LIBRARY__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case EXTLibraryPackage.LIBRARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EXTLibraryPackage.LIBRARY__WRITERS:
				return writers != null && !writers.isEmpty();
			case EXTLibraryPackage.LIBRARY__EMPLOYEES:
				return employees != null && !employees.isEmpty();
			case EXTLibraryPackage.LIBRARY__BORROWERS:
				return borrowers != null && !borrowers.isEmpty();
			case EXTLibraryPackage.LIBRARY__STOCK:
				return stock != null && !stock.isEmpty();
			case EXTLibraryPackage.LIBRARY__BOOKS:
				return books != null && !books.isEmpty();
			case EXTLibraryPackage.LIBRARY__BRANCHES:
				return branches != null && !branches.isEmpty();
			case EXTLibraryPackage.LIBRARY__PARENT_BRANCH:
				return basicGetParentBranch() != null;
			case EXTLibraryPackage.LIBRARY__CASTS:
				return casts != null && !casts.isEmpty();
			case EXTLibraryPackage.LIBRARY__READERS:
				return readers != null && !readers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

  /**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  @Override
  public String toString()
  {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (address: "); //$NON-NLS-1$
		result.append(address);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //LibraryImpl
