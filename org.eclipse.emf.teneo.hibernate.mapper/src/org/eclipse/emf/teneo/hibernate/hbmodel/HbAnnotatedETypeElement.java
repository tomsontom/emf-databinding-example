/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotatedETypeElement.java,v 1.11 2008/07/12 13:10:33 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.Column;
import org.eclipse.emf.teneo.hibernate.hbannotation.Cascade;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbannotation.Filter;
import org.eclipse.emf.teneo.hibernate.hbannotation.Formula;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbMapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.IdBag;
import org.eclipse.emf.teneo.hibernate.hbannotation.Index;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKey;
import org.eclipse.emf.teneo.hibernate.hbannotation.MapKeyManyToMany;
import org.eclipse.emf.teneo.hibernate.hbannotation.Where;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Hb Annotated EType Element</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbWhere <em>Hb Where</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbCollectionOfElements <em>Hb Collection Of Elements</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbMapKey <em>Hb Map Key</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbColumns <em>Hb Columns</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbCascade <em>Hb Cascade</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbIdBag <em>Hb Id Bag</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbIndex <em>Hb Index</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getFilter <em>Filter</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getMapKeyManyToMany <em>Map Key Many To Many</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getFormula <em>Formula</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface HbAnnotatedETypeElement extends PAnnotatedEStructuralFeature, HbAnnotatedEModelElement {
	/**
	 * Returns the value of the '<em><b>Hb Where</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Where</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Where</em>' containment reference.
	 * @see #setHbWhere(Where)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_HbWhere()
	 * @model containment="true"
	 * @generated
	 */
	Where getHbWhere();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbWhere <em>Hb Where</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Where</em>' containment reference.
	 * @see #getHbWhere()
	 * @generated
	 */
	void setHbWhere(Where value);

	/**
	 * Returns the value of the '<em><b>Hb Collection Of Elements</b></em>' containment reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Collection Of Elements</em>' containment reference isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Hb Collection Of Elements</em>' containment reference.
	 * @see #setHbCollectionOfElements(CollectionOfElements)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbModelPackage#getHbAnnotatedETypeElement_HbCollectionOfElements()
	 * @model containment="true"
	 * @generated
	 */
	CollectionOfElements getHbCollectionOfElements();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbCollectionOfElements <em>Hb Collection Of Elements</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Collection Of Elements</em>' containment reference.
	 * @see #getHbCollectionOfElements()
	 * @generated
	 */
	void setHbCollectionOfElements(CollectionOfElements value);

	/**
	 * Returns the value of the '<em><b>Hb Map Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Map Key</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Map Key</em>' containment reference.
	 * @see #setHbMapKey(HbMapKey)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_HbMapKey()
	 * @model containment="true"
	 *        annotation="teneo/internal/PersistenceMapping elementName='hb-map-key'"
	 * @generated
	 */
	HbMapKey getHbMapKey();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbMapKey <em>Hb Map Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Map Key</em>' containment reference.
	 * @see #getHbMapKey()
	 * @generated
	 */
	void setHbMapKey(HbMapKey value);

	/**
	 * Returns the value of the '<em><b>Hb Columns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.annotations.pannotation.Column}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Columns</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Columns</em>' containment reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_HbColumns()
	 * @model containment="true"
	 * @generated
	 */
	EList<Column> getHbColumns();

	/**
	 * Returns the value of the '<em><b>Hb Cascade</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Cascade</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Cascade</em>' containment reference.
	 * @see #setHbCascade(Cascade)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_HbCascade()
	 * @model containment="true"
	 * @generated
	 */
	Cascade getHbCascade();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbCascade <em>Hb Cascade</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Cascade</em>' containment reference.
	 * @see #getHbCascade()
	 * @generated
	 */
	void setHbCascade(Cascade value);

	/**
	 * Returns the value of the '<em><b>Hb Id Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Id Bag</em>' containment reference isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Id Bag</em>' containment reference.
	 * @see #setHbIdBag(IdBag)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_HbIdBag()
	 * @model containment="true"
	 * @generated
	 */
	IdBag getHbIdBag();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbIdBag <em>Hb Id Bag</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Id Bag</em>' containment reference.
	 * @see #getHbIdBag()
	 * @generated
	 */
	void setHbIdBag(IdBag value);

	/**
	 * Returns the value of the '<em><b>Hb Index</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hb Index</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hb Index</em>' reference.
	 * @see #setHbIndex(Index)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_HbIndex()
	 * @model
	 * @generated
	 */
	Index getHbIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getHbIndex <em>Hb Index</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hb Index</em>' reference.
	 * @see #getHbIndex()
	 * @generated
	 */
	void setHbIndex(Index value);

	/**
	 * Returns the value of the '<em><b>Filter</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.teneo.hibernate.hbannotation.Filter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter</em>' reference list.
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_Filter()
	 * @model
	 * @generated
	 */
	EList<Filter> getFilter();

	/**
	 * Returns the value of the '<em><b>Map Key Many To Many</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map Key Many To Many</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map Key Many To Many</em>' containment reference.
	 * @see #setMapKeyManyToMany(MapKeyManyToMany)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_MapKeyManyToMany()
	 * @model containment="true"
	 *        annotation="teneo/internal/PersistenceMapping elementName='hb-map-key'"
	 * @generated
	 */
	MapKeyManyToMany getMapKeyManyToMany();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getMapKeyManyToMany <em>Map Key Many To Many</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Map Key Many To Many</em>' containment reference.
	 * @see #getMapKeyManyToMany()
	 * @generated
	 */
	void setMapKeyManyToMany(MapKeyManyToMany value);

	/**
	 * Returns the value of the '<em><b>Formula</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formula</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formula</em>' reference.
	 * @see #setFormula(Formula)
	 * @see org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage#getHbAnnotatedETypeElement_Formula()
	 * @model
	 * @generated
	 */
	Formula getFormula();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedETypeElement#getFormula <em>Formula</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formula</em>' reference.
	 * @see #getFormula()
	 * @generated
	 */
	void setFormula(Formula value);

} // HbAnnotatedETypeElement
