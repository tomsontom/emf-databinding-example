package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.examples.extlibrary.Book;
import org.eclipse.emf.examples.extlibrary.Borrower;
import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Employee;
import org.eclipse.emf.examples.extlibrary.Item;
import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.Person;
import org.eclipse.emf.examples.extlibrary.Writer;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;

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
public class LibraryImpl extends CDOObjectImpl implements Library {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EXTLibraryPackage.Literals.LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAddress() {
		return (String)eGet(EXTLibraryPackage.Literals.ADDRESSABLE__ADDRESS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(String newAddress) {
		eSet(EXTLibraryPackage.Literals.ADDRESSABLE__ADDRESS, newAddress);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String)eGet(EXTLibraryPackage.Literals.LIBRARY__NAME, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eSet(EXTLibraryPackage.Literals.LIBRARY__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Writer> getWriters() {
		return (EList<Writer>)eGet(EXTLibraryPackage.Literals.LIBRARY__WRITERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Employee> getEmployees() {
		return (EList<Employee>)eGet(EXTLibraryPackage.Literals.LIBRARY__EMPLOYEES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Borrower> getBorrowers() {
		return (EList<Borrower>)eGet(EXTLibraryPackage.Literals.LIBRARY__BORROWERS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Item> getStock() {
		return (EList<Item>)eGet(EXTLibraryPackage.Literals.LIBRARY__STOCK, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Book> getBooks() {
		return (EList<Book>)eGet(EXTLibraryPackage.Literals.LIBRARY__BOOKS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Library> getBranches() {
		return (EList<Library>)eGet(EXTLibraryPackage.Literals.LIBRARY__BRANCHES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library getParentBranch() {
		return (Library)eGet(EXTLibraryPackage.Literals.LIBRARY__PARENT_BRANCH, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentBranch(Library newParentBranch) {
		eSet(EXTLibraryPackage.Literals.LIBRARY__PARENT_BRANCH, newParentBranch);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Person> getCasts() {
		return (EList<Person>)eGet(EXTLibraryPackage.Literals.LIBRARY__CASTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Person> getReaders() {
		return (EList<Person>)eGet(EXTLibraryPackage.Literals.LIBRARY__READERS, true);
	}

} //LibraryImpl