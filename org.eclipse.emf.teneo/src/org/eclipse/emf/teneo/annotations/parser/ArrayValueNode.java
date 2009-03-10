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
 * $Id: ArrayValueNode.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * An array node contains a list of child values.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
class ArrayValueNode extends NamedParserNode {
	/** Log it */
	private final static Log log = LogFactory.getLog(ArrayValueNode.class);

	/** The value */
	private List<Object> children = new ArrayList<Object>();

	/** Returns the list */
	List<Object> getChildren() {
		return children;
	}

	/** Translate into a list of eobjects */
	List<Object> convert(EClassResolver ecr) {
		log.debug("Converting array value node");

		final ArrayList<Object> result = new ArrayList<Object>();
		for (Object ob : children) {
			if (ob instanceof String) {
				result.add(ob);
			} else if (ob instanceof ComplexNode) {
				final ComplexNode cn = (ComplexNode) ob;
				result.add(cn.convert(ecr));
			} else if (ob instanceof ReferenceValueNode) {
				final ReferenceValueNode rvn = (ReferenceValueNode) ob;
				result.add(rvn.convert(ecr));
			} else if (ob instanceof ArrayValueNode) {
				final ArrayValueNode avn = (ArrayValueNode) ob;
				result.addAll((List<Object>) avn.convert(ecr));
			} else {
				throw new AnnotationParserException("Type "
						+ ob.getClass().getName() + " not supported here");
			}
		}
		return result;
	}
}
