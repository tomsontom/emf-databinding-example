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
 *   Martin Taal
 * </copyright>
 *
 * $Id: HbEDataTypeAnnotator.java,v 1.6 2008/05/27 07:42:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.teneo.annotations.mapper.EDataTypeAnnotator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.hbannotation.HbannotationFactory;
import org.eclipse.emf.teneo.hibernate.hbannotation.Parameter;
import org.eclipse.emf.teneo.hibernate.hbannotation.TypeDef;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;
import org.eclipse.emf.teneo.hibernate.mapper.HbMapperConstants;
import org.eclipse.emf.teneo.util.EcoreDataTypes;

/**
 * Annotates an EDataType
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $
 */

public class HbEDataTypeAnnotator extends EDataTypeAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(HbEDataTypeAnnotator.class);

	/** Annotate it */
	@Override
	public void annotate(PAnnotatedEDataType ped) {
		final HbAnnotatedEDataType hed = (HbAnnotatedEDataType) ped;
		if (hed.getHbTypeDef() != null) {
			return; // already there do nothing
		}
		final String typeClass = getCustomDataType(ped.getModelEDataType());
		if (typeClass == null) {
			log.debug("Not creating typedef for edatatype: " + ped.getModelEDataType().getName() +
					" because it is natively handled by hibernate");
			return;
		}

		// create default typedef
		log.debug("Creating default typedef for edatatype " + hed.getModelEDataType().getName());
		final TypeDef typeDef = HbannotationFactory.eINSTANCE.createTypeDef();
		typeDef.setName(hed.getModelEDataType().getEPackage().getName() + "." + ped.getModelEDataType().getName());
		typeDef.setTypeClass(getDefaultUserType());
		// add default parameters
		final Parameter paramPackage = HbannotationFactory.eINSTANCE.createParameter();
		paramPackage.setName(HbMapperConstants.EPACKAGE_PARAM);
		paramPackage.setValue(hed.getModelEDataType().getEPackage().getNsURI());
		typeDef.getParameters().add(paramPackage);
		final Parameter edParam = HbannotationFactory.eINSTANCE.createParameter();
		edParam.setName(HbMapperConstants.EDATATYPE_PARAM);
		edParam.setValue(hed.getModelEDataType().getName());
		typeDef.getParameters().add(edParam);
		hed.setHbTypeDef(typeDef);
	}

	/**
	 * If the specified classifier represents a custom data type not handled natively by Hibernate,
	 * get the Java class name of the class used to represent the custom data type.
	 * 
	 * @param classifier
	 *            non-null classifier to inspect.
	 * @return Class name of the custom data type or null if the classifier does not represent a
	 *         custom data type that is not already handled by Hibernate.
	 */
	private String getCustomDataType(EDataType eDataType) {
		final String typeClassName = eDataType.getInstanceClassName();
		if (EcoreDataTypes.INSTANCE.isSimpleType(eDataType, getPersistenceOptions())) {
			return null;
		} else if (EcoreDataTypes.INSTANCE.isEnum(eDataType)) {
			return null;
		} else if (eDataType.getInstanceClass() == Object.class) {
			return null;
// } else if (typeClassName != null && TypeFactory.basic(typeClassName) != null) {
// // If Hibernate natively handles the type then don't bother creating
// // a typedef.
// return null;
		}
		if (typeClassName != null) {
			final Class<?> instanceClass = eDataType.getInstanceClass();
			if (instanceClass != null && instanceClass.isArray()) {
				final Class<?> compClass = instanceClass.getComponentType();
				if (EcoreDataTypes.INSTANCE.isPrimitive(compClass)) {
					return null;
				}
				// get rid of the [] at the end
// final String primType = typeClassName.substring(0, typeClassName.length() - 2);
				// check if hb supports it
				// no dots is primitve
// if (TypeFactory.basic(primType) != null) {
// return null; // if so let hb do it
// }
			}
		}

		return typeClassName;
	}

	/** Return the default user type */
	public String getDefaultUserType() {
		return "org.eclipse.emf.teneo.hibernate.mapping.DefaultToStringUserType";
	}

}
