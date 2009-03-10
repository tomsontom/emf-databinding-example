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
 * $Id: FeatureMapper.java,v 1.10 2008/12/16 20:40:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PamodelPackage;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Dispatch events to the appropriate Mapper.
 * 
 * @author <a href="mailto:marchign at elver.org">Davide Marchignoli</a>
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class FeatureMapper implements ExtensionPoint {

	/** Switch features for the annotated eattribute */
	private static final EStructuralFeature[] PAEATTRIBUTE_DISCR_FEATURES = new EStructuralFeature[] {
			PamodelPackage.eINSTANCE.getPAnnotatedEModelElement_Transient(),
			PamodelPackage.eINSTANCE.getPAnnotatedEAttribute_Id(),
			PamodelPackage.eINSTANCE.getPAnnotatedEAttribute_Basic(),
			PamodelPackage.eINSTANCE.getPAnnotatedETypedElement_OneToMany(),
			PamodelPackage.eINSTANCE.getPAnnotatedEAttribute_Version() };

	/** Switch features for the annotated ereference */
	private static final EStructuralFeature[] PAEREFERENCE_DISCR_FEATURES = new EStructuralFeature[] {
			PamodelPackage.eINSTANCE.getPAnnotatedEReference_External(),
			PamodelPackage.eINSTANCE.getPAnnotatedEModelElement_Transient(),
			PamodelPackage.eINSTANCE.getPAnnotatedETypedElement_OneToMany(),
			PamodelPackage.eINSTANCE.getPAnnotatedEReference_Embedded(),
			PamodelPackage.eINSTANCE.getPAnnotatedEReference_EmbeddedId(),
			PamodelPackage.eINSTANCE.getPAnnotatedEReference_ManyToMany(),
			PamodelPackage.eINSTANCE.getPAnnotatedEReference_ManyToOne(),
			PamodelPackage.eINSTANCE.getPAnnotatedEReference_OneToOne() };

	/**
	 * Different Mappers used in the switches below, each Mapper handles a
	 * specific annotation case
	 */
	private BasicMapper basicMapper = null;

	private EmbeddedMapper embeddedMapper = null;

	private IdMapper idMapper = null;

	private ManyToManyMapper manyToManyMapper = null;

	private ManyToOneMapper manyToOneMapper = null;

	private ManyAttributeMapper manyAttributeMapper = null;

	private OneToManyMapper oneToManyMapper = null;

	private OneToOneMapper oneToOneMapper = null;

	private ManyExternalReferenceMapper manyExternalReferenceMapper;

	private MappingContext hbmContext = null;

	/**
	 * Used to signal the end of a class or mapped superclass. public void end()
	 * { if (nestedBegin <= 0) throw new
	 * IllegalStateException("Unexpected end"); nestedBegin--; if (idStrategy !=
	 * null) idStrategy.end(); // can happen in case of featuremap if
	 * (nestedBegin == 0) idStrategy = null; }
	 */

	/** Find the feature to switch on */
	public static final EStructuralFeature getSwitchFeature(EObject eObject,
			EStructuralFeature[] features) {
		for (EStructuralFeature element : features) {
			if (eObject.eIsSet(element)) {
				return element;
			}
		}
		return null;
	}

	/**
	 * Used to process the given feature.
	 * 
	 * @see org.eclipse.emf.teneo.annotations.builder.DelegatingBuilder#process(org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature)
	 */
	public void process(PAnnotatedEStructuralFeature paFeature) {
		hbmContext.setCurrentFeature(paFeature);
		if (paFeature instanceof PAnnotatedEAttribute) {
			processPAnnotatedEAttribute((PAnnotatedEAttribute) paFeature);
		} else {
			processPAnnotatedEReference((PAnnotatedEReference) paFeature);
		}
		hbmContext.setCurrentFeature(null);
	}

	/** Process the eattribute */
	private void processPAnnotatedEAttribute(
			PAnnotatedEAttribute pAnnotatedEAttribute) {
		final EStructuralFeature discrFeature;
		if (pAnnotatedEAttribute.getAnnotations().isEmpty()) {
			discrFeature = PamodelPackage.eINSTANCE
					.getPAnnotatedEAttribute_Basic();
		} else {
			discrFeature = getSwitchFeature(pAnnotatedEAttribute,
					PAEATTRIBUTE_DISCR_FEATURES);
		}
		if (discrFeature == null) {
			throw new MappingException("Can not map this paElement",
					pAnnotatedEAttribute);
		} else {
			switch (discrFeature.getFeatureID()) {
			case PamodelPackage.PANNOTATED_ESTRUCTURAL_FEATURE__TRANSIENT:
				basicMapper.processTransient(pAnnotatedEAttribute);
				break;
			case PamodelPackage.PANNOTATED_EATTRIBUTE__BASIC:
				caseBasic(pAnnotatedEAttribute);
				// if (result == null) result =
				// caseProperty(pAnnotatedEAttribute);
				break;
			case PamodelPackage.PANNOTATED_EATTRIBUTE__ID:
				idMapper.processIdProperty(pAnnotatedEAttribute);
				break;
			case PamodelPackage.PANNOTATED_EATTRIBUTE__ONE_TO_MANY:
				manyAttributeMapper.processManyAttribute(pAnnotatedEAttribute);
				break;
			case PamodelPackage.PANNOTATED_EATTRIBUTE__VERSION:
				basicMapper.processVersion(pAnnotatedEAttribute);
				break;
			default:
				throw new AssertionError("unexpected case");
			}
		}
	}

	/** Process the ereference */
	private void processPAnnotatedEReference(
			PAnnotatedEReference pAnnotatedEReference) {
		final EStructuralFeature discrFeature;
		if (pAnnotatedEReference.getAnnotations().isEmpty()) {
			discrFeature = PamodelPackage.eINSTANCE
					.getPAnnotatedEReference_Embedded();
		} else {
			discrFeature = getSwitchFeature(pAnnotatedEReference,
					PAEREFERENCE_DISCR_FEATURES);
		}
		if (discrFeature == null) {
			throw new MappingException("Can not map this paElement",
					pAnnotatedEReference);
		} else {
			switch (discrFeature.getFeatureID()) {
			case PamodelPackage.PANNOTATED_EREFERENCE__EXTERNAL:
				if (pAnnotatedEReference.getModelEReference().isMany()) {
					manyExternalReferenceMapper
							.processManyReference(pAnnotatedEReference);
				} else {
					basicMapper.processExternalEReference(pAnnotatedEReference);
				}
				break;
			case PamodelPackage.PANNOTATED_ESTRUCTURAL_FEATURE__TRANSIENT:
				basicMapper.processTransient(pAnnotatedEReference);
				break;
			case PamodelPackage.PANNOTATED_EREFERENCE__EMBEDDED:
				embeddedMapper.process(pAnnotatedEReference);
				break;
			case PamodelPackage.PANNOTATED_EREFERENCE__EMBEDDED_ID:
				idMapper.processEmbeddedId(pAnnotatedEReference);
				break;
			case PamodelPackage.PANNOTATED_EREFERENCE__MANY_TO_MANY:
				manyToManyMapper.process(pAnnotatedEReference);
				break;
			case PamodelPackage.PANNOTATED_EREFERENCE__MANY_TO_ONE:
				manyToOneMapper.process(pAnnotatedEReference);
				break;
			case PamodelPackage.PANNOTATED_EREFERENCE__ONE_TO_MANY:
				oneToManyMapper.process(pAnnotatedEReference);
				break;
			case PamodelPackage.PANNOTATED_EREFERENCE__ONE_TO_ONE:
				oneToOneMapper.process(pAnnotatedEReference);
				break;
			default:
				throw new AssertionError("unexpected case");
			}
		}
	}

	/** Handle basic annotation, is basic, enumerated, temporal */
	public Object caseBasic(PAnnotatedEAttribute paAttribute) {
		if (paAttribute.getEnumerated() != null) {
			basicMapper.processEnum(paAttribute);
		} else if (paAttribute.getLob() != null) {
			basicMapper.processLob(paAttribute);
		} else if (paAttribute.getTemporal() != null) {
			basicMapper.processTemporal(paAttribute);
		} else {
			basicMapper.processBasic(paAttribute);
		}
		return Boolean.TRUE;
	}

	public Object defaultCase(EObject object) {
		throw new IllegalArgumentException("Unexpected argument " + object);
	}

	public void setBasicMapper(BasicMapper basicMapper) {
		this.basicMapper = basicMapper;
	}

	public void setEmbeddedMapper(EmbeddedMapper embeddedMapper) {
		this.embeddedMapper = embeddedMapper;
	}

	public void setIdMapper(IdMapper idMapper) {
		this.idMapper = idMapper;
	}

	public void setManyToManyMapper(ManyToManyMapper manyToManyMapper) {
		this.manyToManyMapper = manyToManyMapper;
	}

	public void setManyToOneMapper(ManyToOneMapper manyToOneMapper) {
		this.manyToOneMapper = manyToOneMapper;
	}

	public void setManyAttributeMapper(ManyAttributeMapper manyAttributeMapper) {
		this.manyAttributeMapper = manyAttributeMapper;
	}

	public void setOneToManyMapper(OneToManyMapper oneToManyMapper) {
		this.oneToManyMapper = oneToManyMapper;
	}

	public OneToManyMapper getOneToManyMapper() {
		return oneToManyMapper;
	}

	public void setOneToOneMapper(OneToOneMapper oneToOneMapper) {
		this.oneToOneMapper = oneToOneMapper;
	}

	/**
	 * @param hbmContext
	 *            the hbmContext to set
	 */
	public void setHbmContext(MappingContext hbmContext) {
		this.hbmContext = hbmContext;
	}

	/**
	 * @return the manyExternalReferenceMapper
	 */
	public ManyExternalReferenceMapper getManyExternalReferenceMapper() {
		return manyExternalReferenceMapper;
	}

	/**
	 * @param manyExternalReferenceMapper
	 *            the manyExternalReferenceMapper to set
	 */
	public void setManyExternalReferenceMapper(
			ManyExternalReferenceMapper manyExternalReferenceMapper) {
		this.manyExternalReferenceMapper = manyExternalReferenceMapper;
	}
}