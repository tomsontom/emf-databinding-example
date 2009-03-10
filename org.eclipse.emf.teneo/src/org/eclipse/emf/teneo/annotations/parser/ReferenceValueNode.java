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
 * $Id: ReferenceValueNode.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Node which has a complex type as its value.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
class ReferenceValueNode extends NamedParserNode {
	/** Log it */
	private final static Log log = LogFactory.getLog(ArrayValueNode.class);

	/** The value */
	private NamedParserNode value;

	/**
	 * @return the value
	 */
	public NamedParserNode getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(NamedParserNode value) {
		this.value = value;
	}

	/** Translate into a list of eobjects */
	Object convert(EClassResolver ecr) {
		log.debug("Converting reference node " + getName());
		if (!(value instanceof ComplexNode)) {
			throw new AnnotationParserException(
					"A reference annotation value may only "
							+ "contain a typename");
		}
		final ComplexNode cn = (ComplexNode) value;
		return cn.convert(ecr);
	}
}
