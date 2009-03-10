/**
 * <copyright>
 * </copyright>
 *
 * $Id: PAnnotationImpl.java,v 1.3 2008/07/13 13:12:49 mtaal Exp $
 */
package org.eclipse.emf.teneo.annotations.pannotation.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.teneo.annotations.pannotation.CascadeType;
import org.eclipse.emf.teneo.annotations.pannotation.DiscriminatorType;
import org.eclipse.emf.teneo.annotations.pannotation.EnumType;
import org.eclipse.emf.teneo.annotations.pannotation.FetchType;
import org.eclipse.emf.teneo.annotations.pannotation.GenerationType;
import org.eclipse.emf.teneo.annotations.pannotation.InheritanceType;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.annotations.pannotation.TemporalType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PAnnotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.teneo.annotations.pannotation.impl.PAnnotationImpl#getEModelElement <em>EModel Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PAnnotationImpl extends EObjectImpl implements PAnnotation {

	/** Translation from enumerate class to import annotation class */
	private static Map<Class<?>, String> enumToImport = new HashMap<Class<?>, String>();

	static {
		enumToImport.put(CascadeType.class, "javax.persistence.CascadeType");
		enumToImport.put(DiscriminatorType.class, "javax.persistence.DiscriminatorType");
		enumToImport.put(EnumType.class, "javax.persistence.EnumType");
		enumToImport.put(FetchType.class, "javax.persistence.FetchType");
		enumToImport.put(GenerationType.class, "javax.persistence.GenerationType");
		enumToImport.put(InheritanceType.class, "javax.persistence.InheritanceType");
		enumToImport.put(TemporalType.class, "javax.persistence.TemporalType");
	}

	/**
	 * The cached value of the '{@link #getEModelElement() <em>EModel Element</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getEModelElement()
	 * @generated
	 * @ordered
	 */
	protected EModelElement eModelElement;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PAnnotationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PannotationPackage.Literals.PANNOTATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EModelElement getEModelElement() {
		if (eModelElement != null && eModelElement.eIsProxy()) {
			InternalEObject oldEModelElement = (InternalEObject)eModelElement;
			eModelElement = (EModelElement)eResolveProxy(oldEModelElement);
			if (eModelElement != oldEModelElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PannotationPackage.PANNOTATION__EMODEL_ELEMENT, oldEModelElement, eModelElement));
			}
		}
		return eModelElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EModelElement basicGetEModelElement() {
		return eModelElement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEModelElement(EModelElement newEModelElement) {
		EModelElement oldEModelElement = eModelElement;
		eModelElement = newEModelElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PannotationPackage.PANNOTATION__EMODEL_ELEMENT, oldEModelElement, eModelElement));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PannotationPackage.PANNOTATION__EMODEL_ELEMENT:
				if (resolve) return getEModelElement();
				return basicGetEModelElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PannotationPackage.PANNOTATION__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
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
			case PannotationPackage.PANNOTATION__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
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
			case PannotationPackage.PANNOTATION__EMODEL_ELEMENT:
				return eModelElement != null;
		}
		return super.eIsSet(featureID);
	}

	/** Return the class name of the annotation */
	public String getAnnotationClassName() {
		return "javax.persistence." + this.eClass().getName();
	}

	/** Return the java annotation, any importStatements are added to the passed list */
	public String getJavaAnnotation(List<String> imports) {
		final StringBuffer sb = new StringBuffer();
		for (EStructuralFeature eFeature : eClass().getEStructuralFeatures()) {
			if (isFeatureValidJavaAnnotation(eFeature)) {
				sb.append(getAnnotationValue(this, eFeature, sb.length() > 0, imports));
			}
		}

		// ensure that there is always at most one
		addImport(imports, getAnnotationClassName());

		if (sb.length() == 0) {
			return "@" + eClass().getName();
		}
		return "@" + eClass().getName() + "(" + sb.toString() + ")";
	}

	// to handle some non standard annotation
	protected boolean isFeatureValidJavaAnnotation(EStructuralFeature eFeature) {
		return true;
	}

	/** Returns an annotation correct name value pair if the feature is set */
	protected String getAnnotationValue(org.eclipse.emf.ecore.EObject eObject, EStructuralFeature eFeature,
			boolean prependComma, List<String> imports) {
		final Object value = eObject.eGet(eFeature);
		if (value == null) {
			return "";
		}
		if (!eObject.eIsSet(eFeature)) {
			return "";
		}
		final String result;
		if (value instanceof List<?>) {
			final List<?> values = (List<?>) value;
			if (values.size() == 0) {
				return "";
			}
			final StringBuffer sb = new StringBuffer();
			for (Object o : values) {
				if (o instanceof PAnnotation) {
					final PAnnotation pAnnotation = (PAnnotation) o;
					sb.append(pAnnotation.getJavaAnnotation(imports));
				} else {
					sb.append(getAnnotationValue(imports, o));
				}
			}
			if (sb.length() == 0) {
				return "";
			}
			result = eFeature.getName() + " = {" + sb.toString() + "}";
		} else {
			result = eFeature.getName() + " = " + getAnnotationValue(imports, value);
		}
		if (prependComma) {
			return ", " + result;
		}
		return result;
	}

	protected String getAnnotationValue(List<String> imports, Object value) {
		if (value instanceof String) {
			return "\"" + (String) value + "\"";
		} else if (value instanceof Enumerator) {
			addImport(imports, getEnumImport(value));
			return ((Enumerator) value).getClass().getSimpleName() + "." + ((Enumerator) value).getLiteral();
		} else if (value instanceof List<?>) {
			final List<?> values = (List<?>) value;
			if (values.size() == 0) {
				return "";
			}
			final StringBuffer sb = new StringBuffer();
			for (Object o : values) {
				if (sb.length() > 0) {
					sb.append(", ");
				}
				if (o instanceof PAnnotation) {
					final PAnnotation pAnnotation = (PAnnotation) o;
					sb.append(pAnnotation.getJavaAnnotation(imports));
				} else {
					sb.append(getAnnotationValue(imports, o));
				}
			}
			return "{" + sb.toString() + "}";
		} else {
			return "" + value;
		}

	}

	protected void addImport(List<String> imports, String newImport) {
		if (!imports.contains(newImport)) {
			imports.add(newImport);
		}
	}

	/** Return the import statement required for the enum */
	protected String getEnumImport(Object enumInstance) {
		final String enumImport = enumToImport.get(enumInstance.getClass());
		if (enumImport == null) {
			throw new IllegalArgumentException("No import defined for enum class: " + enumInstance.getClass().getName());
		}
		return enumImport;
	}

} // PAnnotationImpl
