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
 * $Id: HbXmlPersistenceMapper.java,v 1.2 2008/02/28 07:07:43 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.annotations;

import org.eclipse.emf.teneo.annotations.xml.XmlPersistenceMapper;

/**
 * Overrides the xml persistence mapper for providing an additional prefix.
 */
public class HbXmlPersistenceMapper extends XmlPersistenceMapper {
	/** Return the hb prefix */
	@Override
	protected String getPrefix() {
		return "hb";
	}
}
