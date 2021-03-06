/**
 * <copyright>
 * </copyright>
 *
 * $Id: GeneratedValidator.java,v 1.1 2007/07/11 17:35:11 mtaal Exp $
 */
package org.eclipse.emf.teneo.hibernate.hbannotation.validation;

import org.eclipse.emf.teneo.hibernate.hbannotation.GenerationTime;

/**
 * A sample validator interface for {@link org.eclipse.emf.teneo.hibernate.hbannotation.Generated}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface GeneratedValidator {
	boolean validate();

	boolean validateValue(GenerationTime value);
}
