/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotatedEPackageImpl.java,v 1.15 2008/10/13 05:35:43 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pamodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEClass;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEPackage;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedModel;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator;
import org.eclipse.emf.teneo.annotations.pannotation.TableGenerator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PAnnotated EPackage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getPaModel <em>Pa Model</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getModelEPackage <em>Model EPackage</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getPaEClasses <em>Pa EClasses</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getSequenceGenerators <em>Sequence Generators</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getTableGenerators <em>Table Generators</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getPaEDataTypes <em>Pa EData Types</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pamodel.impl.PAnnotatedEPackageImpl#getSequenceStyleGenerators <em>Sequence Style Generators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PAnnotatedEPackageImpl extends PAnnotatedEModelElementImpl implements PAnnotatedEPackage {
	/**
	 * The cached value of the '{@link #getPaEClasses() <em>Pa EClasses</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPaEClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<PAnnotatedEClass> paEClasses;

	/**
	 * The cached value of the '{@link #getSequenceGenerators() <em>Sequence Generators</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSequenceGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<SequenceGenerator> sequenceGenerators;

	/**
	 * The cached value of the '{@link #getTableGenerators() <em>Table Generators</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTableGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<TableGenerator> tableGenerators;

	/**
	 * The cached value of the '{@link #getPaEDataTypes() <em>Pa EData Types</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPaEDataTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<PAnnotatedEDataType> paEDataTypes;

	/**
	 * The cached value of the '{@link #getSequenceStyleGenerators() <em>Sequence Style Generators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceStyleGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<SequenceStyleGenerator> sequenceStyleGenerators;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PAnnotatedEPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PamodelPackage.Literals.PANNOTATED_EPACKAGE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PAnnotatedModel getPaModel() {
		if (eContainerFeatureID != PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL) return null;
		return (PAnnotatedModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPaModel(PAnnotatedModel newPaModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPaModel, PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPaModel(PAnnotatedModel newPaModel) {
		if (newPaModel != eInternalContainer() || (eContainerFeatureID != PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL && newPaModel != null)) {
			if (EcoreUtil.isAncestor(this, newPaModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPaModel != null)
				msgs = ((InternalEObject)newPaModel).eInverseAdd(this, PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES, PAnnotatedModel.class, msgs);
			msgs = basicSetPaModel(newPaModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL, newPaModel, newPaModel));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getModelEPackage() {
		EPackage modelEPackage = basicGetModelEPackage();
		return modelEPackage != null && modelEPackage.eIsProxy() ? (EPackage)eResolveProxy((InternalEObject)modelEPackage) : modelEPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EPackage basicGetModelEPackage() {
		return (EPackage) getModelElement();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PAnnotatedEClass> getPaEClasses() {
		if (paEClasses == null) {
			paEClasses = new EObjectContainmentWithInverseEList<PAnnotatedEClass>(PAnnotatedEClass.class, this, PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES, PamodelPackage.PANNOTATED_ECLASS__PA_EPACKAGE);
		}
		return paEClasses;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SequenceGenerator> getSequenceGenerators() {
		if (sequenceGenerators == null) {
			sequenceGenerators = new EObjectContainmentEList<SequenceGenerator>(SequenceGenerator.class, this, PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS);
		}
		return sequenceGenerators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableGenerator> getTableGenerators() {
		if (tableGenerators == null) {
			tableGenerators = new EObjectContainmentEList<TableGenerator>(TableGenerator.class, this, PamodelPackage.PANNOTATED_EPACKAGE__TABLE_GENERATORS);
		}
		return tableGenerators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PAnnotatedEDataType> getPaEDataTypes() {
		if (paEDataTypes == null) {
			paEDataTypes = new EObjectContainmentWithInverseEList<PAnnotatedEDataType>(PAnnotatedEDataType.class, this, PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES, PamodelPackage.PANNOTATED_EDATA_TYPE__PA_EPACKAGE);
		}
		return paEDataTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SequenceStyleGenerator> getSequenceStyleGenerators() {
		if (sequenceStyleGenerators == null) {
			sequenceStyleGenerators = new EObjectContainmentEList<SequenceStyleGenerator>(SequenceStyleGenerator.class, this, PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS);
		}
		return sequenceStyleGenerators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPaModel((PAnnotatedModel)otherEnd, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPaEClasses()).basicAdd(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPaEDataTypes()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				return basicSetPaModel(null, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES:
				return ((InternalEList<?>)getPaEClasses()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS:
				return ((InternalEList<?>)getSequenceGenerators()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__TABLE_GENERATORS:
				return ((InternalEList<?>)getTableGenerators()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES:
				return ((InternalEList<?>)getPaEDataTypes()).basicRemove(otherEnd, msgs);
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS:
				return ((InternalEList<?>)getSequenceStyleGenerators()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				return eInternalContainer().eInverseRemove(this, PamodelPackage.PANNOTATED_MODEL__PA_EPACKAGES, PAnnotatedModel.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				return getPaModel();
			case PamodelPackage.PANNOTATED_EPACKAGE__MODEL_EPACKAGE:
				if (resolve) return getModelEPackage();
				return basicGetModelEPackage();
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES:
				return getPaEClasses();
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS:
				return getSequenceGenerators();
			case PamodelPackage.PANNOTATED_EPACKAGE__TABLE_GENERATORS:
				return getTableGenerators();
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES:
				return getPaEDataTypes();
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS:
				return getSequenceStyleGenerators();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				setPaModel((PAnnotatedModel)newValue);
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES:
				getPaEClasses().clear();
				getPaEClasses().addAll((Collection<? extends PAnnotatedEClass>)newValue);
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS:
				getSequenceGenerators().clear();
				getSequenceGenerators().addAll((Collection<? extends SequenceGenerator>)newValue);
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__TABLE_GENERATORS:
				getTableGenerators().clear();
				getTableGenerators().addAll((Collection<? extends TableGenerator>)newValue);
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES:
				getPaEDataTypes().clear();
				getPaEDataTypes().addAll((Collection<? extends PAnnotatedEDataType>)newValue);
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS:
				getSequenceStyleGenerators().clear();
				getSequenceStyleGenerators().addAll((Collection<? extends SequenceStyleGenerator>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				setPaModel((PAnnotatedModel)null);
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES:
				getPaEClasses().clear();
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS:
				getSequenceGenerators().clear();
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__TABLE_GENERATORS:
				getTableGenerators().clear();
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES:
				getPaEDataTypes().clear();
				return;
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS:
				getSequenceStyleGenerators().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_MODEL:
				return getPaModel() != null;
			case PamodelPackage.PANNOTATED_EPACKAGE__MODEL_EPACKAGE:
				return basicGetModelEPackage() != null;
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_ECLASSES:
				return paEClasses != null && !paEClasses.isEmpty();
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_GENERATORS:
				return sequenceGenerators != null && !sequenceGenerators.isEmpty();
			case PamodelPackage.PANNOTATED_EPACKAGE__TABLE_GENERATORS:
				return tableGenerators != null && !tableGenerators.isEmpty();
			case PamodelPackage.PANNOTATED_EPACKAGE__PA_EDATA_TYPES:
				return paEDataTypes != null && !paEDataTypes.isEmpty();
			case PamodelPackage.PANNOTATED_EPACKAGE__SEQUENCE_STYLE_GENERATORS:
				return sequenceStyleGenerators != null && !sequenceStyleGenerators.isEmpty();
		}
		return super.eIsSet(featureID);
	}
} // PAnnotatedEPackageImpl
