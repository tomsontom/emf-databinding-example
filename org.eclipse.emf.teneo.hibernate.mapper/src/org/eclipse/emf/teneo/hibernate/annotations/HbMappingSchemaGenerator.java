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
 * $Id: HbMappingSchemaGenerator.java,v 1.3 2008/03/30 10:01:15 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import java.io.FileWriter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.teneo.annotations.StoreAnnotationsException;
import org.eclipse.emf.teneo.annotations.pannotation.PannotationPackage;
import org.eclipse.emf.teneo.annotations.xml.PersistenceMappingSchemaGenerator;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationPackage;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbmodelPackage;

/**
 * Generates the xsd for the hibernate mapping.
 */
public class HbMappingSchemaGenerator extends PersistenceMappingSchemaGenerator {

	/** The main method */
	public static void main(String[] args) {
		final HbMappingSchemaGenerator pmsg = new HbMappingSchemaGenerator();
		pmsg.setAnnotationEPackages(new EPackage[] { HbannotationPackage.eINSTANCE, PannotationPackage.eINSTANCE });
		pmsg.setModelEPackage(HbmodelPackage.eINSTANCE);
		pmsg.setNameSpace("http://www.eclipse.org/emft/teneo");
		try {
			final FileWriter fw = new FileWriter("/home/mtaal/mytmp/persistence-mapping-hibernate.xsd");
			fw.write(pmsg.generate());
			fw.close();
		} catch (Exception e) {
			throw new StoreAnnotationsException("Exception while generating mapping.xsd", e);
		}
	}

	/** Return the PAnnotatedEClass */
	@Override
	protected EClass getPAnnotatedEPackage() {
		return (EClass) getModelEPackage().getEClassifier("HbAnnotatedEPackage");
	}

	/** Return the PAnnotatedEClass */
	@Override
	protected EClass getPAnnotatedEClass() {
		return (EClass) getModelEPackage().getEClassifier("HbAnnotatedEClass");
	}

	/** Return the PAnnotatedEReference */
	@Override
	protected EClass getPAnnotatedEReference() {
		return (EClass) getModelEPackage().getEClassifier("HbAnnotatedEReference");
	}

	/** Return the PAnnotatedEAttribute */
	@Override
	protected EClass getPAnnotatedEAttribute() {
		return (EClass) getModelEPackage().getEClassifier("HbAnnotatedEAttribute");
	}

	/** Return the PAnnotatedEDataType */
	@Override
	protected EClass getPAnnotatedEDataType() {
		return (EClass) getModelEPackage().getEClassifier("HbAnnotatedEDataType");
	}

	/** get rid of the hb- prefix */
	@Override
	protected String convertToXmlName(String name) {
		String result = super.convertToXmlName(name);
		if (result.compareTo("hb-column") == 0) {
			return "columns"; // prevents a specific nameclash
		}
		if (result.startsWith("hb-") && result.compareTo("hb-map-key") != 0) {
			return result.substring(3);
		}
		return result;
	}
}