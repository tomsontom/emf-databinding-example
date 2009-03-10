/**
 * <copyright>
 * </copyright>
 *
 * $Id: SequenceStyleGeneratorImpl.java,v 1.1 2008/10/13 05:35:43 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.teneo.annotations.pannotation.OptimizerType;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Style Generator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl#getSequenceName <em>Sequence Name</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl#getIncrementSize <em>Increment Size</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.SequenceStyleGeneratorImpl#getOptimizer <em>Optimizer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceStyleGeneratorImpl extends PAnnotationImpl implements SequenceStyleGenerator {
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
	 * The default value of the '{@link #getSequenceName() <em>Sequence Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceName()
	 * @generated
	 * @ordered
	 */
	protected static final String SEQUENCE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSequenceName() <em>Sequence Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceName()
	 * @generated
	 * @ordered
	 */
	protected String sequenceName = SEQUENCE_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected static final int INITIAL_VALUE_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getInitialValue() <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialValue()
	 * @generated
	 * @ordered
	 */
	protected int initialValue = INITIAL_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIncrementSize() <em>Increment Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrementSize()
	 * @generated
	 * @ordered
	 */
	protected static final int INCREMENT_SIZE_EDEFAULT = 50;

	/**
	 * The cached value of the '{@link #getIncrementSize() <em>Increment Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncrementSize()
	 * @generated
	 * @ordered
	 */
	protected int incrementSize = INCREMENT_SIZE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOptimizer() <em>Optimizer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptimizer()
	 * @generated
	 * @ordered
	 */
	protected static final OptimizerType OPTIMIZER_EDEFAULT = OptimizerType.NONE;

	/**
	 * The cached value of the '{@link #getOptimizer() <em>Optimizer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptimizer()
	 * @generated
	 * @ordered
	 */
	protected OptimizerType optimizer = OPTIMIZER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceStyleGeneratorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PannotationPackage.Literals.SEQUENCE_STYLE_GENERATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.SEQUENCE_STYLE_GENERATOR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSequenceName() {
		return sequenceName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequenceName(String newSequenceName) {
		String oldSequenceName = sequenceName;
		sequenceName = newSequenceName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME, oldSequenceName, sequenceName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInitialValue() {
		return initialValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialValue(int newInitialValue) {
		int oldInitialValue = initialValue;
		initialValue = newInitialValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE, oldInitialValue, initialValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIncrementSize() {
		return incrementSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncrementSize(int newIncrementSize) {
		int oldIncrementSize = incrementSize;
		incrementSize = newIncrementSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE, oldIncrementSize, incrementSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptimizerType getOptimizer() {
		return optimizer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptimizer(OptimizerType newOptimizer) {
		OptimizerType oldOptimizer = optimizer;
		optimizer = newOptimizer == null ? OPTIMIZER_EDEFAULT : newOptimizer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.SEQUENCE_STYLE_GENERATOR__OPTIMIZER, oldOptimizer, optimizer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__NAME:
				return getName();
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME:
				return getSequenceName();
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE:
				return new Integer(getInitialValue());
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE:
				return new Integer(getIncrementSize());
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__OPTIMIZER:
				return getOptimizer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__NAME:
				setName((String)newValue);
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME:
				setSequenceName((String)newValue);
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE:
				setInitialValue(((Integer)newValue).intValue());
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE:
				setIncrementSize(((Integer)newValue).intValue());
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__OPTIMIZER:
				setOptimizer((OptimizerType)newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME:
				setSequenceName(SEQUENCE_NAME_EDEFAULT);
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE:
				setInitialValue(INITIAL_VALUE_EDEFAULT);
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE:
				setIncrementSize(INCREMENT_SIZE_EDEFAULT);
				return;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__OPTIMIZER:
				setOptimizer(OPTIMIZER_EDEFAULT);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__SEQUENCE_NAME:
				return SEQUENCE_NAME_EDEFAULT == null ? sequenceName != null : !SEQUENCE_NAME_EDEFAULT.equals(sequenceName);
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INITIAL_VALUE:
				return initialValue != INITIAL_VALUE_EDEFAULT;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__INCREMENT_SIZE:
				return incrementSize != INCREMENT_SIZE_EDEFAULT;
			case PannotationPackage.SEQUENCE_STYLE_GENERATOR__OPTIMIZER:
				return optimizer != OPTIMIZER_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", sequenceName: ");
		result.append(sequenceName);
		result.append(", initialValue: ");
		result.append(initialValue);
		result.append(", incrementSize: ");
		result.append(incrementSize);
		result.append(", optimizer: ");
		result.append(optimizer);
		result.append(')');
		return result.toString();
	}

} //SequenceStyleGeneratorImpl
