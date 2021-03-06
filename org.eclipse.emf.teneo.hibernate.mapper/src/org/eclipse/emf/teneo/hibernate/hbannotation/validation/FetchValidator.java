/**
 * <copyright>
 * </copyright>
 *
 * $Id: FetchValidator.java,v 1.1 2007/02/08 23:13:13 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.validation;

import org.eclipse.emf.teneo.hibernate.hbannotation.HbFetchType;

/**
 * A sample validator interface for {@link org.eclipse.emf.teneo.hibernate.hbannotation.Fetch}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface FetchValidator {
	boolean validate();

	boolean validateValue(HbFetchType value);
}
