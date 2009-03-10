/**
 * <copyright>
 *
 * Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Douglas Bitting
 * </copyright>
 *
 * $Id: HbAnnotationModelBuilder.java,v 1.3 2008/02/28 07:07:43 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.annotations;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.teneo.annotations.mapper.BasicPamodelBuilder;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelFactory;

/**
 * This class create the Hibernate-model versions of the various PaAnnotatedE* objects. 
 */
public class HbAnnotationModelBuilder extends BasicPamodelBuilder {

	/** Use hb annotation types */
	@Override
	protected PAnnotatedEModelElement doCreate(EModelElement eModelElement) throws AssertionError {
		final EClass eModelElementEClass = eModelElement.eClass();
		PAnnotatedEModelElement paElement;
		switch (eModelElementEClass.getClassifierID()) {
			case EcorePackage.EATTRIBUTE:
				paElement = HbmodelFactory.eINSTANCE.createHbAnnotatedEAttribute();
				break;
			case EcorePackage.EREFERENCE:
				paElement = HbmodelFactory.eINSTANCE.createHbAnnotatedEReference();
				break;
			case EcorePackage.ECLASS:
				paElement = HbmodelFactory.eINSTANCE.createHbAnnotatedEClass();
				break;
			case EcorePackage.EPACKAGE:
				paElement = HbmodelFactory.eINSTANCE.createHbAnnotatedEPackage();
				break;
			case EcorePackage.EENUM:
			case EcorePackage.EDATA_TYPE:
				paElement = HbmodelFactory.eINSTANCE.createHbAnnotatedEDataType();
				break;
			default:
				throw new AssertionError("Trying to build HbAnnotatedEModelElement for a " + eModelElementEClass);
		}
		paElement.setModelElement((ENamedElement) eModelElement);
		return paElement;
	}
}
