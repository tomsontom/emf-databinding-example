/**
 * <copyright>
 * </copyright>
 *
 * $Id: HbAnnotationImpl.java,v 1.1 2007/11/14 16:38:33 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.teneo.annotations.pannotation.impl.PAnnotationImpl;
import org.eclipse.emf.teneo.hibernate.hbannotation.CacheConcurrencyStrategy;
import org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbAnnotation;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbannotation.OnDeleteAction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Hb Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class HbAnnotationImpl extends PAnnotationImpl implements HbAnnotation {

	/** Translation from enumerate class to import annotation class */
	private static Map<Class<?>, String> hbEnumToImport = new HashMap<Class<?>, String>();

	static {
		hbEnumToImport.put(CacheConcurrencyStrategy.class, "org.hibernate.annotations.");
		hbEnumToImport.put(HbFetchType.class, "org.hibernate.annotations.FetchType");
		hbEnumToImport.put(OnDeleteAction.class, "org.hibernate.annotations.OnDeleteAction");
		hbEnumToImport.put(GenerationTime.class, "org.hibernate.annotations.GenerationTime");
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected HbAnnotationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HbannotationPackage.Literals.HB_ANNOTATION;
	}

	/** Return the import statement required for the enum */
	@Override
	protected String getEnumImport(Object enumInstance) {
		final String enumImport = hbEnumToImport.get(enumInstance.getClass());
		if (enumImport == null) {
			return super.getEnumImport(enumInstance);
		}
		return enumImport;
	}

	/** Return the class name of the annotation */
	@Override
	public String getAnnotationClassName() {
		return "org.hibernate.annotations." + this.eClass().getName();
	}
} // HbAnnotationImpl
