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
 * $Id: NamedParserNode.java,v 1.2 2008/02/28 07:08:33 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

/**
 * Main class of the nodes which are the result of the annotation parser.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
abstract class NamedParserNode {

	/** The name parsed */
	private String name = "value";

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Translate to pamodel/pannotation */
	abstract Object convert(EClassResolver ecr);
}
