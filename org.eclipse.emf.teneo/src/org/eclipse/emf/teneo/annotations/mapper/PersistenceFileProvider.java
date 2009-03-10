/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal </copyright> $Id:
 * PersistenceMappingBuilder.java,v 1.10 2007/02/08 23:12:35 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.mapper;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.extension.ExtensionPoint;

/**
 * Provides files to the mapping as well as to the runtime layers. It can be
 * customized with an own implementation by replacing the class in the
 * ExtensionManager.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.2 $
 */
public class PersistenceFileProvider implements ExtensionPoint {

	/** The logger */
	protected static final Log log = LogFactory
			.getLog(PersistenceFileProvider.class);

	/**
	 * Returns an InputStream with the file content, note if the file does not
	 * exist then null may be returned. This implementation searches for the
	 * file in the classpath using the path parameters.
	 * 
	 * Custom implementations of this class may use any other method to find the
	 * file.
	 * 
	 * @param clz
	 *            the class to use when reading the file through a classloader
	 * @param path
	 *            the path to the file (incl. the filename and extension)
	 * 
	 * @return an InputStream if found, or null otherwise
	 */
	public InputStream getFileContent(Class<?> clz, String path) {
		if (clz == null) {
			return this.getClass().getClassLoader().getResourceAsStream(path);
		} else {
			return clz.getResourceAsStream(path);
		}
	}
}