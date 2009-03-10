/**
 * <copyright> Copyright (c) 2005, 2006, 2007, 2008 Springsite BV (The Netherlands) and others All rights
 * reserved. This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html Contributors: Martin Taal
 * </copyright> $Id: MappingException.java,v 1.8 2009/03/07 21:15:19 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.teneo.TeneoException;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEModelElement;
import org.eclipse.emf.teneo.annotations.pannotation.PAnnotation;

/**
 * Exception used to signal errors in mapping TODO is there an elver hierarchy
 * of exceptions? MT: no there is no real hierarchy, I tend to use have one
 * general exception which always logs the exception
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class MappingException extends TeneoException {

	/**
	 * Generated Serial ID
	 */
	private static final long serialVersionUID = 7816794038091161326L;

	/** Logger */
	private static final Log log = LogFactory.getLog(MappingException.class);

	public MappingException(String msg) {
		super(msg);
		log.error("EXCEPTION: " + msg + " CAUSE ");
	}

	/** Constructor */
	public MappingException(String msg, PAnnotatedEModelElement paElement) {
		super(msg + ", " + paElement);
		log.error("EXCEPTION: " + msg + " element "
				+ paElement.getModelElement().getName());
	}

	public MappingException(String msg, Exception cause) {
		super(msg, cause);
		log.error("EXCEPTION: " + msg + " CAUSE " + cause.getClass().getName());
	}

	public MappingException(String msg, PAnnotation pAnnotation) {
		super(msg + ", " + pAnnotation);
		log.error("EXCEPTION: "
				+ msg
				+ " element "
				+ pAnnotation.eClass().getName()
				+ " "
				+ (pAnnotation.getEModelElement() != null ? pAnnotation
						.getEModelElement().eClass().getName() : ""));
	}
}
