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
 * $Id: ComplexNode.java,v 1.3 2008/09/01 12:45:19 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Models a real type (a complex type in xml schema speak), an EClass.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
class ComplexNode extends NamedParserNode {

	/** Log it */
	private final static Log log = LogFactory.getLog(ComplexNode.class);

	/** The child nodes */
	private List<NamedParserNode> children = new ArrayList<NamedParserNode>();

	/** Is set if this is a list */
	private boolean isList = false;

	/** Returns the list of children */
	List<NamedParserNode> getChildren() {
		return children;
	}

	/** Translate into an eclass */
	@Override
	@SuppressWarnings("unchecked")
	Object convert(EClassResolver ecr) {
		log.debug("Converting " + getName() + " to EObject ");

		// special case in which the main type is just a list of other types
		// for example SecondaryTables which is just a list of SecondaryTable
		// TODO: repair this hard link to a separate type!
		final EClass eClass = ecr.getEClass(getName());
		if (isList() && eClass == null) {
			assert (children.size() == 1);
			assert (children.get(0) instanceof ArrayValueNode);
			return ((ArrayValueNode) children.get(0)).convert(ecr);
		}

		if (eClass == null) {
			throw new AnnotationParserException("No eclass found with name " + getName());
		}
		final EObject eobj = EcoreUtil.create(eClass);

		for (NamedParserNode child : children) {
			final EStructuralFeature efeature = ecr.getEStructuralFeature(eClass, child.getName());
			if (child instanceof PrimitiveValueNode) {
				final PrimitiveValueNode pvn = (PrimitiveValueNode) child;
				log.debug("Primitive child: " + pvn.getName() + ": " + pvn.getValue());
				if (!(efeature instanceof EAttribute)) {
					throw new AnnotationParserException("The EFeature " + efeature.getName() + "/" + eClass.getName() +
							" is not an eattribute but a " + efeature.getClass().getName());
				}
				final EClassifier eType = efeature.getEType();
				if (!efeature.isMany()) {
					eobj.eSet(efeature, ParserUtil.convertValue((EDataType) eType, pvn.getValue()));
				} else {
					final String[] sources = pvn.getValue().split("\\s+");
					log.debug("Child is many, splitting content into " + sources.length + " parts");
					final List<Object> referenced = new ArrayList<Object>(sources.length);
					for (String source : sources) {
						referenced.add(ParserUtil.convertValue((EDataType) eType, source));
					}
					final List currentList = (List) eobj.eGet(efeature);
					currentList.addAll(referenced);
				}
			} else if (child instanceof ArrayValueNode && efeature instanceof EAttribute) {
				final EAttribute eattr = (EAttribute) efeature;
				if (!eattr.isMany()) {
					throw new AnnotationParserException("The EFeature " + efeature.getName() + "/" + eClass.getName() +
							" is not ismany");
				}
				log.debug("Array child with primitive values");
				List<Object> list = ((ArrayValueNode) child).convert(ecr);
				List<Object> convertedList = new ArrayList<Object>();
				for (Object object : list) {
					final String val = (String) object;
					log.debug("Value " + val);
					convertedList.add(ParserUtil.convertValue((EDataType) eattr.getEType(), val));
				}
				final List currentList = (List) eobj.eGet(efeature);
				currentList.addAll(convertedList);
			} else if (child instanceof ArrayValueNode) {
				if (!(efeature instanceof EReference)) {
					throw new AnnotationParserException("The EFeature " + efeature.getName() + "/" + eClass.getName() +
							" is not an ereference but a " + efeature.getClass().getName());
				}
				final EReference eref = (EReference) efeature;
				if (!eref.isMany()) {
					throw new AnnotationParserException("The EFeature " + efeature.getName() + "/" + eClass.getName() +
							" is not ismany");
				}
				log.debug("Array child");
				eobj.eSet(eref, ((ArrayValueNode) child).convert(ecr));
			} else if (child instanceof ReferenceValueNode) {
				if (!(efeature instanceof EReference)) {
					throw new AnnotationParserException("The EFeature " + efeature.getName() + "/" + eClass.getName() +
							" is not an ereference but a " + efeature.getClass().getName());
				}
				final EReference eref = (EReference) efeature;
				log.debug("Reference child " + child.getName());
				if (eref.isMany()) {
					((List) eobj.eGet(eref)).add(((ReferenceValueNode) child).convert(ecr));
				} else {
					eobj.eSet(eref, ((ReferenceValueNode) child).convert(ecr));
				}
			}
		}
		return eobj;
	}

	/**
	 * @return the isList
	 */
	public boolean isList() {
		return isList;
	}

	/**
	 * @param isList
	 *            the isList to set
	 */
	public void setList(boolean isList) {
		this.isList = isList;
	}
}
