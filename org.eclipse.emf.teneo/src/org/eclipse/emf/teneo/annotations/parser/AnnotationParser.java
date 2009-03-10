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
 * $Id: AnnotationParser.java,v 1.3 2008/04/06 13:44:04 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.ENamedElement;

/**
 * Parses an annotation and creates a tree of parserNodes.
 * 
 * See AnnotationTokenizer for a short description on the type of parsed tokens.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
public class AnnotationParser {

	/** The StringTokenizer being used */
	private AnnotationTokenizer annotationTokenizer;

	/** The list of root nodes */
	private List<NamedParserNode> parserNodes = new ArrayList<NamedParserNode>();

	private long expectedToken = Long.MAX_VALUE;

	/** Parses the content and returns a list of parsernodes */
	public List<NamedParserNode> parse(ENamedElement eNamedElement, String content) {
		annotationTokenizer = new AnnotationTokenizer(eNamedElement, content);
		parserNodes.clear();
		int token;
		while (annotationTokenizer.getCurrentToken() != AnnotationTokenizer.T_EOF &&
				(token = annotationTokenizer.nextToken()) != AnnotationTokenizer.T_EOF) {
			if (token != AnnotationTokenizer.T_TYPENAME) {
				throw new AnnotationParserException("Only typenames are allowed at the root of the " +
						"annotation, see _ for the error " + annotationTokenizer.getErrorText());
			}
			parseTypeName(null);
		}
		return parserNodes;
	}

	/** Adds a child to the parent */
	private void addToParent(NamedParserNode parent, NamedParserNode child) {
		if (parent == null) {
			return;
		}
		if (parent instanceof ComplexNode) {
			((ComplexNode) parent).getChildren().add(child);
		} else if (parent instanceof ArrayValueNode) {
			((ArrayValueNode) parent).getChildren().add(child);
		} else if (parent instanceof ReferenceValueNode) {
			((ReferenceValueNode) parent).setValue(child);
		}
	}

	/** Parse a type name (a complex type) */
	private void parseTypeName(NamedParserNode pn) {
		final ComplexNode cn = new ComplexNode();
		cn.setName(annotationTokenizer.getLexeme());
		addToParent(pn, cn);
		if (pn == null) {
			parserNodes.add(cn);
		}

		// now parse the next token
		final int token = annotationTokenizer.nextToken();

		switch (token) {
			case AnnotationTokenizer.T_EOF:
				return;
			case AnnotationTokenizer.T_CONTENTSTART:
				parseContent(cn);
				break;
			case AnnotationTokenizer.T_TYPENAME:
				parseTypeName(null); // the next one
				break;
			case AnnotationTokenizer.T_COMMA: // in case of array
				if (!(pn instanceof ArrayValueNode)) {
					throw new AnnotationParserException(
						"Encountered comma but not within an array definition, see _ for error location " +
								annotationTokenizer.getErrorText());
				}
				return;
			default:
				throw new AnnotationParserException("Unknown token, see _ for error position: " +
						annotationTokenizer.getErrorText());
		}
	}

	/** Parse the content of a typeName */
	private void parseContent(NamedParserNode pn) {
		// content can either be an array or a set of values

		expectedToken =
				AnnotationTokenizer.T_ARRAYSTART + AnnotationTokenizer.T_IDENTIFIER + AnnotationTokenizer.T_VALUE;

		boolean finished = false;
		while (!finished) {
			final int token = annotationTokenizer.nextToken();
			checkToken(token);
			switch (token) {
				case AnnotationTokenizer.T_COMMA:
					expectedToken = AnnotationTokenizer.T_IDENTIFIER;
					break;
				case AnnotationTokenizer.T_EOF:
					throw new AnnotationParserException("Unexcepted end to annotation string, " +
							annotationTokenizer.getErrorText());
				case AnnotationTokenizer.T_CONTENTEND:
					return;
				case AnnotationTokenizer.T_VALUE:
					final String theValue = annotationTokenizer.getLexeme();
					final PrimitiveValueNode valueNode = new PrimitiveValueNode();
					valueNode.setName("value");
					valueNode.setValue(theValue);
					addToParent(pn, valueNode);
					if (annotationTokenizer.nextToken() != AnnotationTokenizer.T_CONTENTEND) {
						throw new AnnotationParserException("After this value a closing ) should follow " +
								annotationTokenizer.getErrorText());
					}
					return;
				case AnnotationTokenizer.T_IDENTIFIER:
					final String identifier = annotationTokenizer.getLexeme();
					// next token must be an is
					int nextToken = annotationTokenizer.nextToken();
					// in case of simple annotations with just a value member
					if (nextToken == AnnotationTokenizer.T_CONTENTEND) {
						final PrimitiveValueNode vn = new PrimitiveValueNode();
						vn.setName("value");
						vn.setValue(identifier);
						addToParent(pn, vn);
						return;
					}
					if (nextToken != AnnotationTokenizer.T_IS) {
						throw new AnnotationParserException(
							"No = character after identifier, see _ for error position " +
									annotationTokenizer.getErrorText());
					}
					nextToken = annotationTokenizer.nextToken();
					// if (nextToken == AnnotationTokenizer.T_VALUE) {
					// final String value = annotationTokenizer.getLexeme();
					// final PrimitiveValueNode vn = new PrimitiveValueNode();
					// vn.setName(identifier);
					// vn.setValue(value);
					// addToParent(pn, vn);
					// }
					if (nextToken == AnnotationTokenizer.T_VALUE) {
						final String value = annotationTokenizer.getLexeme();
						final PrimitiveValueNode vn = new PrimitiveValueNode();
						vn.setName(identifier);
						vn.setValue(value);
						addToParent(pn, vn);
					} else if (nextToken == AnnotationTokenizer.T_IDENTIFIER) {
						final String value = annotationTokenizer.getLexeme();
						final PrimitiveValueNode vn = new PrimitiveValueNode();
						vn.setName(identifier);
						vn.setValue(value);
						addToParent(pn, vn);
					} else if (nextToken == AnnotationTokenizer.T_TYPENAME) {
						final ReferenceValueNode rvn = new ReferenceValueNode();
						rvn.setName(identifier);
						parseTypeName(rvn);
						addToParent(pn, rvn);
					} else if (nextToken == AnnotationTokenizer.T_ARRAYSTART) {
						final ArrayValueNode avn = new ArrayValueNode();
						avn.setName(identifier);
						parseArray(avn);
						addToParent(pn, avn);
					} else if (annotationTokenizer.nextToken() != AnnotationTokenizer.T_VALUE) {
						throw new AnnotationParserException("No value token after =, see _ for error position " +
								annotationTokenizer.getErrorText());
					}
					expectedToken =
							AnnotationTokenizer.T_COMMA + AnnotationTokenizer.T_IDENTIFIER +
									AnnotationTokenizer.T_CONTENTEND;
					break;
				case AnnotationTokenizer.T_ARRAYSTART:
					// special case in which the main type is just a list of other
					// types
					// for example SecondaryTables which is just a list of
					// SecondaryTable
					parseArray(pn);
					((ComplexNode) pn).setList(true);
					expectedToken =
							AnnotationTokenizer.T_COMMA + AnnotationTokenizer.T_IDENTIFIER +
									AnnotationTokenizer.T_CONTENTEND;
					break;
			}
		}
	}

	/** Parse an array */
	private void parseArray(NamedParserNode pn) {
		// content can either be an array or a set of values
		final ArrayValueNode an = new ArrayValueNode();
		addToParent(pn, an);
		boolean finished = false;

		expectedToken = AnnotationTokenizer.T_TYPENAME + AnnotationTokenizer.T_VALUE + AnnotationTokenizer.T_IDENTIFIER;

		while (!finished) {
			final int token = annotationTokenizer.nextToken();
			checkToken(token);
			switch (token) {
				case AnnotationTokenizer.T_EOF:
					throw new AnnotationParserException("Unexcepted end to annotation string, " +
							annotationTokenizer.getErrorText());
				case AnnotationTokenizer.T_TYPENAME:
					parseTypeName(an);

					expectedToken = AnnotationTokenizer.T_ARRAYEND + AnnotationTokenizer.T_COMMA;

					break;
				case AnnotationTokenizer.T_VALUE:
					String value = annotationTokenizer.getLexeme();
					if (value != null && value.length() > 1 && value.charAt(0) == '"' &&
							value.charAt(value.length() - 1) == '"') {
						value = value.substring(1, value.length() - 1);
					}
					an.getChildren().add(value);
					expectedToken = AnnotationTokenizer.T_ARRAYEND + AnnotationTokenizer.T_COMMA;
					break;
				case AnnotationTokenizer.T_IDENTIFIER:
					an.getChildren().add(annotationTokenizer.getLexeme());

					expectedToken =
							AnnotationTokenizer.T_IS + AnnotationTokenizer.T_ARRAYEND + AnnotationTokenizer.T_COMMA;
					break;
				case AnnotationTokenizer.T_COMMA:
					expectedToken =
							AnnotationTokenizer.T_TYPENAME + AnnotationTokenizer.T_VALUE +
									AnnotationTokenizer.T_IDENTIFIER;
					break;
				case AnnotationTokenizer.T_ARRAYEND:
					expectedToken = Long.MAX_VALUE;
					return;
			}
		}
		throw new AnnotationParserException("Unexpected end of array. " + annotationTokenizer.getErrorText());
	}

	protected void checkToken(int currentToken) {
		if ((currentToken & expectedToken) == 0) {
			final String msg =
					"Found " + annotationTokenizer.getCurrentTokenName() + " but expected one of : " +
							annotationTokenizer.getTokenNames(expectedToken);
			throw new AnnotationParserException(msg + ". " + annotationTokenizer.getErrorText());
		}
	}
}
