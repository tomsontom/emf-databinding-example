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
 * $Id: EFeatureAnnotator.java,v 1.10 2008/09/21 18:36:02 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEReference;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEStructuralFeature;
import org.eclipse.emf.teneo.annotations.pannotation.Transient;
import org.eclipse.emf.teneo.extension.ExtensionPoint;
import org.eclipse.emf.teneo.util.StoreUtil;

/**
 * Sets the annotation on an efeature. In fact determines which efeature annotator to use
 * (one-to-many, many-to-many etc.).
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.10 $
 */

public class EFeatureAnnotator extends AbstractAnnotator implements ExtensionPoint {

	// The logger
	protected static final Log log = LogFactory.getLog(EFeatureAnnotator.class);

	// the annotators
	protected OneToManyAttributeAnnotator otmAttributeAnnotator;
	protected SingleAttributeAnnotator singleAttributeAnnotator;
	protected BidirectionalManyToManyAnnotator bidirectionalManyToManyAnnotator;
	protected UnidirectionalManyToManyAnnotator unidirectionalManyToManyAnnotator;
	protected OneToManyReferenceAnnotator oneToManyReferenceAnnotator;
	protected OneToOneReferenceAnnotator oneToOneReferenceAnnotator;
	protected ManyToOneReferenceAnnotator manyToOneReferenceAnnotator;

	@Override
	protected void initialize() {
		super.initialize();
		otmAttributeAnnotator = createAnnotator(OneToManyAttributeAnnotator.class);
		otmAttributeAnnotator.setEFeatureAnnotator(this);
		singleAttributeAnnotator = createAnnotator(SingleAttributeAnnotator.class);
		singleAttributeAnnotator.setEFeatureAnnotator(this);
		bidirectionalManyToManyAnnotator = createAnnotator(BidirectionalManyToManyAnnotator.class);
		bidirectionalManyToManyAnnotator.setEFeatureAnnotator(this);
		unidirectionalManyToManyAnnotator = createAnnotator(UnidirectionalManyToManyAnnotator.class);
		unidirectionalManyToManyAnnotator.setEFeatureAnnotator(this);
		oneToManyReferenceAnnotator = createAnnotator(OneToManyReferenceAnnotator.class);
		oneToManyReferenceAnnotator.setEFeatureAnnotator(this);
		oneToOneReferenceAnnotator = createAnnotator(OneToOneReferenceAnnotator.class);
		oneToOneReferenceAnnotator.setEFeatureAnnotator(this);
		manyToOneReferenceAnnotator = createAnnotator(ManyToOneReferenceAnnotator.class);
		manyToOneReferenceAnnotator.setEFeatureAnnotator(this);
	}

	/** Process the features of the eclass */
	public void annotate(PAnnotatedEStructuralFeature aStructuralFeature) {
		EStructuralFeature eStructuralFeature = aStructuralFeature.getModelEStructuralFeature();

		boolean errorOccured = true;
		try {
			// a feature is transient if:
			// - transient is true and it is an eattribute or
			// - transient is true and it does not have an opposite
			// - transietn is true and it's opposite is not a containment
			// relation
			// - it refers to an eclass which is transient
			boolean isTransient =
					eStructuralFeature.isTransient() &&
							(eStructuralFeature instanceof EAttribute ||
									((EReference) eStructuralFeature).getEOpposite() == null ||
									!((EReference) eStructuralFeature).getEOpposite().isContainment() || ((EReference) eStructuralFeature)
								.getEOpposite().isTransient());

			// check if the refered to eclass is transient if so then this efeature is
			// also transient
			if (!isTransient && eStructuralFeature instanceof EReference) {
				final PAnnotatedEReference aReference = (PAnnotatedEReference) aStructuralFeature;
				if (aReference.getAReferenceType() != null) {
					isTransient = aReference.getAReferenceType().getTransient() != null;
				}
			}

			if (aStructuralFeature.getTransient() == null &&
					((!mapVolitatileFeature() && eStructuralFeature.isVolatile()) || isTransient)) {
				log.debug("Structural feature " + eStructuralFeature.getName() +
						" is transient, therefore adding transient annotation");
				final Transient trans = getFactory().createTransient();
				trans.setEModelElement(eStructuralFeature);
				aStructuralFeature.setTransient(trans);
			}

			// process transients further because they can be part of a
			// featuremap, the specific mapper should
			// handle transient
			// Note that this means that transient features will still have
			// additional annotations such as basic etc.
			// if (aStructuralFeature.getTransient() != null) return;
			if (aStructuralFeature instanceof PAnnotatedEAttribute) {
				final PAnnotatedEAttribute aAttribute = (PAnnotatedEAttribute) aStructuralFeature;
				if (((PAnnotatedEAttribute) aStructuralFeature).getVersion() != null) {
					return;
				}

				final Class<?> instanceClass = eStructuralFeature.getEType().getInstanceClass();
				boolean isMany = false;
				// instanceClass will be null for enums
				// Lob-annotated attributes must not be treated as one-to-many.
				// eattributes with a hibernate type annotations should not be
				// treated as a list
				if (instanceClass != null && aAttribute.getLob() == null) {
					isMany =
							eStructuralFeature.isMany() || instanceClass.isArray() ||
									Collection.class.isAssignableFrom(instanceClass) ||
									Set.class.isAssignableFrom(instanceClass) ||
									List.class.isAssignableFrom(instanceClass);
					isMany = isMany && !StoreUtil.isElementOfAGroup(eStructuralFeature);
				}

				if (isMany) {
					otmAttributeAnnotator.annotate(aAttribute);
				} else {
					singleAttributeAnnotator.annotate(aAttribute);
				}

				if (aAttribute.getColumn() != null && aAttribute.getColumn().getName() == null) {
					aAttribute.getColumn().setName(getSqlNameStrategy().getColumnName(aAttribute, null));
				}

			} else if (aStructuralFeature instanceof PAnnotatedEReference) {

				final PAnnotatedEReference aReference = (PAnnotatedEReference) aStructuralFeature;

				// detect the type of relation
				// note using the emf model it can not be checked if a relation
				// is a
				// uni-manytoone (2.1.8.3.2) or a uni onetoone (2.1.8.3.1)
				// neither can a uni-manytomany (2.1.8.5.2) be detected
				// because there is no eopposite. However this can be
				// specified manually, the system as a default will choose
				// uni-manytoone

				final EReference eReference = (EReference) aStructuralFeature.getModelElement();
				final EReference eOpposite = eReference.getEOpposite();

				// elements of a group are never multi-occurence because the
				// multi-occurence is
				// handled by the containing featuremap
				final boolean isMany = eReference.isMany() && !StoreUtil.isElementOfAGroup(eReference);
				final boolean isOppositeMany =
						eOpposite != null && eOpposite.isMany() && !StoreUtil.isElementOfAGroup(eOpposite);

				final boolean mtmBidirectionalRelation = isMany && eOpposite != null && isOppositeMany;
				final boolean mtmUnidirectionalRelation =
						isMany && eOpposite == null && aReference.getManyToMany() != null;
				final boolean otmBidirectionalRelation = isMany && eOpposite != null && !isOppositeMany;
				final boolean otmUnidirectionalRelation = isMany && eOpposite == null;

				// note as a default if the system has to choose between oto uni
				// or mto uni then it will
				// place a mto
				final boolean otoBidirectionalRelation =
						aReference.getManyToOne() == null && !isMany && eOpposite != null && !isOppositeMany;
				final boolean otoUnidirectionalRelation =
						aReference.getManyToOne() == null && !isMany && eOpposite == null &&
								(aReference.getOneToOne() != null || !aReference.getPrimaryKeyJoinColumns().isEmpty());
				final boolean mtoBidirectionalRelation = !isMany && eOpposite != null && isOppositeMany;
				final boolean mtoUnidirectionalRelation = !isMany && eOpposite == null && !otoUnidirectionalRelation;

				if (mtmBidirectionalRelation) {
					bidirectionalManyToManyAnnotator.annotate(aReference);
				} else if (mtmUnidirectionalRelation) {
					unidirectionalManyToManyAnnotator.annotate(aReference);
				} else if (otmBidirectionalRelation || otmUnidirectionalRelation) {
					oneToManyReferenceAnnotator.annotate(aReference);
				} else if (aReference.getManyToOne() == null && (otoBidirectionalRelation || otoUnidirectionalRelation)) {
					oneToOneReferenceAnnotator.annotate(aReference);
				} else if (mtoBidirectionalRelation) {
					manyToOneReferenceAnnotator.annotate(aReference);
				} else if (mtoUnidirectionalRelation) {
					manyToOneReferenceAnnotator.annotate(aReference);
				}

				// handle column naming at this level
				if (aReference.getColumn() != null && aReference.getColumn().getName() == null) {
					aReference.getColumn().setName(getSqlNameStrategy().getColumnName(aReference, null));
				}

			} else {
				throw new IllegalArgumentException("This type of StructuralFeature is not supported: " +
						aStructuralFeature.getClass().getName());
			}
			errorOccured = false;
		} finally {

			// check that at least one ann was set
			if (aStructuralFeature instanceof PAnnotatedEAttribute) {
				PAnnotatedEAttribute pae = (PAnnotatedEAttribute) aStructuralFeature;
				assert (errorOccured || pae.getBasic() != null || pae.getVersion() != null || pae.getId() != null ||
						pae.getTransient() != null || pae.getOneToMany() != null);
			} else {
				PAnnotatedEReference par = (PAnnotatedEReference) aStructuralFeature;
				assert (errorOccured || par.getTransient() != null || par.getOneToMany() != null ||
						par.getManyToMany() != null || par.getManyToOne() != null || par.getOneToOne() != null);
			}

		}
	}

	/** Map the feature if it is volatile, default is false */
	protected boolean mapVolitatileFeature() {
		return false;
	}

	/**
	 * @return the manyToOneReferenceAnnotator
	 */
	public ManyToOneReferenceAnnotator getManyToOneReferenceAnnotator() {
		return manyToOneReferenceAnnotator;
	}

}
