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
 * $Id: HbOneToManyAttributeAnnotator.java,v 1.5 2008/05/27 07:42:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.annotations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.annotations.mapper.OneToManyAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.mapper.SingleAttributeAnnotator;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbannotation.CollectionOfElements;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEAttribute;
import org.eclipse.emf.teneo.hibernate.hbmodel.HbAnnotatedEDataType;

/**
 * Annotates a one-to-many attribute (an eattribute with ismany=true), an example is a list of
 * primitives (list of ints).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public class HbOneToManyAttributeAnnotator extends OneToManyAttributeAnnotator {

	// The logger
	protected static final Log log = LogFactory.getLog(HbOneToManyAttributeAnnotator.class);

	protected SingleAttributeAnnotator singleAttributeAnnotator;

	@Override
	protected void initialize() {
		super.initialize();
		singleAttributeAnnotator = createAnnotator(SingleAttributeAnnotator.class);
	}

	/** Process the features of the eclass */
	@Override
	public void annotate(PAnnotatedEAttribute aAttribute) {
		boolean isCollectionOfElements =
				(aAttribute instanceof HbAnnotatedEAttribute && null != ((HbAnnotatedEAttribute) aAttribute)
					.getHbCollectionOfElements());

		final HbAnnotatedEAttribute hea = (HbAnnotatedEAttribute) aAttribute;
		final HbAnnotatedEDataType hed =
				(HbAnnotatedEDataType) aAttribute.getPaModel().getPAnnotated(
					aAttribute.getModelEAttribute().getEAttributeType());
		if (isCollectionOfElements) {
			CollectionOfElements coe = ((HbAnnotatedEAttribute) aAttribute).getHbCollectionOfElements();
			if (coe.getTargetElement() == null) {
				coe.setTargetElement(getTargetTypeName(aAttribute));
			}
		} else if (hea.getHbType() != null && hea.getOneToMany() == null && !aAttribute.getModelEAttribute().isMany()) {
			// assume this to be a single attribute, we can get here when
			// the instance is an array or list in that case the user type is
			// assumed
			// to be able to handle the complete list/collection
			singleAttributeAnnotator.annotate(aAttribute);
		} else if (hed != null && hed.getHbTypeDef() != null && hea.getOneToMany() == null &&
				!aAttribute.getModelEAttribute().isMany()) {
			// assume this to be a single attribute, we can get here when
			// the instance is an array or list in that case the user type is
			// assumed
			// to be able to handle the complete list/collection
			singleAttributeAnnotator.annotate(aAttribute);
		} else {
			super.annotate(aAttribute);
		}
	}

// MT: is now recomputed in the mapping step
// /** Returns the type name of a many attribute */
// @Override
// protected String getTargetTypeName(PAnnotatedEAttribute aAttribute) {
// final HbAnnotatedEDataType ped =
// (HbAnnotatedEDataType) aAttribute.getPaModel().getPAnnotated(
// aAttribute.getModelEAttribute().getEAttributeType());
// if (ped != null && ped.getHbTypeDef() != null) {
// return ped.getHbTypeDef().getName();
// }
// if (((HbAnnotatedEAttribute) aAttribute).getHbType() != null) {
// return ((HbAnnotatedEAttribute) aAttribute).getHbType().getType();
// }
// return EcoreDataTypes.INSTANCE.getTargetTypeName(aAttribute);
// }

}
