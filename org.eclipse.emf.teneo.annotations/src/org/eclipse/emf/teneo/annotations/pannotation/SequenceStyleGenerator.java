/**
 * <copyright>
 * </copyright>
 *
 * $Id: SequenceStyleGenerator.java,v 1.1 2008/10/13 05:35:42 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Style Generator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getSequenceName <em>Sequence Name</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getIncrementSize <em>Increment Size</em>}</li>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getOptimizer <em>Optimizer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getSequenceStyleGenerator()
 * @model annotation="teneo/internal/Target 0='EClass' 1='EStructuralFeature' 2='EPackage'"
 * @generated
 */
public interface SequenceStyleGenerator extends PAnnotation {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getSequenceStyleGenerator_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Sequence Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Name</em>' attribute.
	 * @see #setSequenceName(String)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getSequenceStyleGenerator_SequenceName()
	 * @model
	 * @generated
	 */
	String getSequenceName();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getSequenceName <em>Sequence Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence Name</em>' attribute.
	 * @see #getSequenceName()
	 * @generated
	 */
	void setSequenceName(String value);

	/**
	 * Returns the value of the '<em><b>Initial Value</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Value</em>' attribute.
	 * @see #setInitialValue(int)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getSequenceStyleGenerator_InitialValue()
	 * @model default="1"
	 * @generated
	 */
	int getInitialValue();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getInitialValue <em>Initial Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Value</em>' attribute.
	 * @see #getInitialValue()
	 * @generated
	 */
	void setInitialValue(int value);

	/**
	 * Returns the value of the '<em><b>Increment Size</b></em>' attribute.
	 * The default value is <code>"50"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Increment Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Increment Size</em>' attribute.
	 * @see #setIncrementSize(int)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getSequenceStyleGenerator_IncrementSize()
	 * @model default="50"
	 * @generated
	 */
	int getIncrementSize();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getIncrementSize <em>Increment Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Increment Size</em>' attribute.
	 * @see #getIncrementSize()
	 * @generated
	 */
	void setIncrementSize(int value);

	/**
	 * Returns the value of the '<em><b>Optimizer</b></em>' attribute.
	 * The default value is <code>"NONE"</code>.
	 * The literals are from the enumeration {@link org.eclipse.emf.teneo.annotations.pannotation.OptimizerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optimizer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optimizer</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OptimizerType
	 * @see #setOptimizer(OptimizerType)
	 * @see org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage#getSequenceStyleGenerator_Optimizer()
	 * @model default="NONE"
	 * @generated
	 */
	OptimizerType getOptimizer();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.teneo.annotations.pannotation.SequenceStyleGenerator#getOptimizer <em>Optimizer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optimizer</em>' attribute.
	 * @see org.eclipse.emf.teneo.annotations.pannotation.OptimizerType
	 * @see #getOptimizer()
	 * @generated
	 */
	void setOptimizer(OptimizerType value);

} // SequenceStyleGenerator
