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
 *   Martin Taal - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: MixedFeatureMapEntry.java,v 1.5 2008/04/11 23:43:43 mtaal Exp $
 */

package org.eclipse.emf.teneo.type;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.teneo.Constants;

/**
 * Is a specific EMF feature map for handling mixed content. Mixed content is content which consists
 * of normal nodes and other content. The other content supported here is text, cdata and comment.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.5 $
 */

public abstract class MixedFeatureMapEntry extends FeatureMapEntry {
	private static final long serialVersionUID = 1L;

	/** Constant used to encode the TEXT feature in the db. */
	static final String TEXT_FEATURE_DBID = "TEXT";

	/** Constant used to encode the CDATA feature in the db. */
	static final String CDATA_FEATURE_DBID = "CDATA";

	/** Constant used to encode the COMMENT feature in the db. */
	static final String COMMENT_FEATURE_DBID = "COMMENT";

	/** Overridden to encode the TEXT, CDATA or COMMENT structural features */
	@Override
	protected String createStructuralFeatureDBID() {
		final EStructuralFeature structuralFeature = getEStructuralFeature();

		if (structuralFeature == Constants.TEXT) {
			return TEXT_FEATURE_DBID;
		} else if (structuralFeature == Constants.CDATA) {
			return CDATA_FEATURE_DBID;
		} else if (structuralFeature == Constants.COMMENT) {
			return COMMENT_FEATURE_DBID;
		}
		return super.createStructuralFeatureDBID();
	}

	/** Gets a structuralfeature on the basis of the passed id */
	@Override
	protected EStructuralFeature retrieveStructuralFeature(String dbid) {
		if (TEXT_FEATURE_DBID.compareTo(dbid) == 0) {
			return Constants.TEXT;
		}
		if (CDATA_FEATURE_DBID.compareTo(dbid) == 0) {
			return Constants.CDATA;
		}
		if (COMMENT_FEATURE_DBID.compareTo(dbid) == 0) {
			return Constants.COMMENT;
		}

		return super.retrieveStructuralFeature(dbid);
	}

	/** Returns true if the feature is a TEXT, CDATA or COMMENT */
	protected boolean isMixedFeature() {
		final EStructuralFeature structuralFeature = getEStructuralFeature();
		return structuralFeature == Constants.TEXT || structuralFeature == Constants.CDATA ||
				structuralFeature == Constants.COMMENT;
	}
}