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
 * $Id: AnnotationTokenizer.java,v 1.11 2009/03/03 15:33:23 mtaal Exp $
 */

package org.eclipse.emf.teneo.annotations.parser;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.teneo.annotations.StoreAnnotationsException;

/**
 * Tokenizes a java annotation. The main tokens are: - TypeName - Identifier -
 * Value - Array
 * 
 * For example the following java annotation
 * 
 * @GenericGenerator(name="hibseq", strategy = "hilo", parameters = {
 * @Parameter(name="table", value = "hilo_table"),
 * @Parameter(name="column", value="the_hilo_column")} )
 * 
 *                           Here GenericGenerator is a TypeName, name and
 *                           strategy are Identifiers and "hilo_table" is a
 *                           value, the array is the part between the {}.
 * 
 *                           There is a special case where the typename is
 *                           actually a list of values, e.g. SecondaryTables.
 *                           These are treated as a special type of TypeName
 *                           which is translated into a ComplexNode with
 *                           isList=true. This is currently only supported at
 *                           the top level.
 * 
 * @author <a href="mailto:mtaal at elver.org">Martin Taal</a>
 */
class AnnotationTokenizer {
	/** Log it */
	private final static Log log = LogFactory.getLog(AnnotationTokenizer.class);

	/** Special Tokens */
	static final int T_EOF = 4096;

	private static final int T_EOL = 8192;

	private static final int T_UNKNOWN = 16384;

	/**
	 * Annotation tokens
	 */
	static final int T_TYPENAME = 2;

	static final int T_IDENTIFIER = 4;

	static final int T_ARRAYSTART = 8;

	static final int T_ARRAYEND = 16;

	static final int T_VALUE = 32;

	static final int T_IS = 64;

	static final int T_CONTENTSTART = 128;

	static final int T_CONTENTEND = 256;

	static final int T_COMMA = 512;

	/** Data */
	private char[] data;

	/** Length */
	private int length;

	/** Points to the start of the current token */

	private int tokBeg;

	/** Ponts to the end of the current token. */
	private int tokEnd;

	/** The last returned token */
	private int currentToken = T_EOF - 1;

	private HashMap<Integer, String> constantToName = new HashMap<Integer, String>();

	/**
	 * Constructor
	 */

	AnnotationTokenizer(ENamedElement eNamedElement, String source) {
		setSource(source.toCharArray());
		constantToName.put(2, "Annotation");
		constantToName.put(4, "Attribute Name");
		constantToName.put(8, "Array Start ({)");
		constantToName.put(16, "Array End (})");
		constantToName.put(32, "Value (e.g. String, int)");
		constantToName.put(64, "= character");
		constantToName.put(128, "Annotation content start ('(')");
		constantToName.put(256, "Annotation content end (')')");
		constantToName.put(512, "Comma (,)");

		constantToName.put(1024, "Carriage Return");
		constantToName.put(2048, "Line Feed");
		constantToName.put(4096, "EOF");
		constantToName.put(8192, "EOL");
		constantToName.put(16384, "Unknown");
	}

	public String getCurrentTokenName() {
		final String name = constantToName.get(currentToken);
		if (name == null) {
			throw new StoreAnnotationsException("Illegal token " + currentToken);
		}
		return name;
	}

	public String getTokenNames(long tokens) {
		final StringBuffer sb = new StringBuffer();
		for (Integer key : constantToName.keySet()) {

			if ((tokens & key.intValue()) > 0) {
				if (sb.length() > 0) {
					sb.append(", ");
				}
				sb.append(constantToName.get(key));
			}
		}
		return sb.toString();
	}

	/**
	 * Sets the source to be tokenized form a character array.
	 */

	private void setSource(char[] iSource) {
		length = iSource.length;
		// Append three null-characters as sentinel since three
		// look-ahead characters are required (e.g. for the '>>>=' token).
		data = new char[length + 3];
		System.arraycopy(iSource, 0, data, 0, length);

		data[length] = 0; // Append the sentinel characters.
		data[length + 1] = 0;
		data[length + 2] = 0;

		tokBeg = 0;
		tokEnd = 0;

		log.debug(dump());
	}

	/**
	 * Returns the next token.
	 */

	final int nextToken() {
		currentToken = getNextToken();
		return currentToken;
	}

	/** Return the curren token */
	final int getCurrentToken() {
		return currentToken;
	}

	/**
	 * Returns the next token.
	 */

	final int getNextToken() {
		int lCur = tokEnd;

		Loop: for (;;) {
			char lChar = data[lCur]; // Grab next character.

			switch (lChar) {
			case ' ': // Skip leading whitespace!
			case '\n': // new line
			case '\r': // Carriage Return.
			case '\f': // Line Feed.
			case '\t': {
				lCur++;
				continue Loop; // --> Keep on skipping leading whitespace!
			}

			case 0: // End of buffer.
			{
				if (lCur == length) // Guard against embedded nulls in the
				// Source.
				{
					// EOBuf may only occur at the first non whitespace char.

					return T_EOF; // --> End of file.
				}
				throw new AnnotationParserException(
						"Char is 0 but end not reached " + lCur + " " + length);
			}

				// TYPENAME
			case '@': {
				++lCur; // get rid of the @
				tokBeg = lCur; // Save starting point of current lexeme.

				do {
					lChar = data[++lCur];
				} while (lChar == '-' || lChar == '_' || lChar == '/'
						|| lChar == '@' || ('0' <= lChar && lChar <= '9')
						|| lChar == ':' || ('a' <= lChar && lChar <= 'z')
						|| ('A' <= lChar && lChar <= 'Z'));

				tokEnd = lCur; // Save endpoint of current lexeme.

				return T_TYPENAME; // --> Identifier.
			}
				// VALUE with double quotes
			case '"': {
				// after the dollar the identifier part needs to be found
				tokBeg = lCur; // Save starting point of current lexeme.

				do {
					lChar = data[++lCur];
				} while (lChar == ',' || lChar == '-' || lChar == '.'
						|| lChar == ' ' || lChar == '_' || lChar == '/'
						|| lChar == '`' || lChar == '@' || lChar == ':'
						|| lChar == '=' || lChar == '(' || lChar == ')'
						|| lChar == '{' || lChar == '}' || lChar == '\''
						|| lChar == '#' || lChar == '&' || lChar == '<'
						|| lChar == '>' || lChar == '$' || lChar == ';'
						|| lChar == '%' || lChar == '*' || lChar == '\''
						|| ('0' <= lChar && lChar <= '9')
						|| ('a' <= lChar && lChar <= 'z') || lChar == '?'
						|| ('A' <= lChar && lChar <= 'Z'));

				if (lChar != '"') {
					final AnnotationParserException e = new AnnotationParserException(
							"Value not closed with double quote, see the _ for the location "
									+ getErrorText());
					tokEnd = lCur + 1; // prevent infinite looping
					throw e;
				}
				tokEnd = lCur + 1;
				return T_VALUE;
			}
			case '(': {
				tokBeg = lCur;
				tokEnd = lCur + 1;
				return T_CONTENTSTART;
			}
			case ')': {
				tokBeg = lCur;
				tokEnd = lCur + 1;
				return T_CONTENTEND;
			}
			case '{': {
				tokBeg = lCur;
				tokEnd = lCur + 1;
				return T_ARRAYSTART;
			}
			case '}': {
				tokBeg = lCur;
				tokEnd = lCur + 1;
				return T_ARRAYEND;
			}
			case ',': {
				tokBeg = lCur;
				tokEnd = lCur + 1;
				return T_COMMA;
			}
			case '=': {
				tokBeg = lCur;
				tokEnd = lCur + 1;
				return T_IS;
			}
			default: // the rest must be identifiers
			{
				// after the dollar the identifier part needs to be found
				tokBeg = lCur; // Save starting point of current lexeme.

				do {
					lChar = data[++lCur];
				} while (lChar == '.' || lChar == '-' || lChar == '_'
						|| lChar == '/' || lChar == '@'
						|| ('0' <= lChar && lChar <= '9')
						|| ('a' <= lChar && lChar <= 'z')
						|| ('A' <= lChar && lChar <= 'Z'));

				tokEnd = lCur; // Save endpoint of current lexeme.

				return T_IDENTIFIER; // --> Identifier.
			}
			}
		}
	}

	/**
	 * Returns the current lexeme.
	 */

	final String getLexeme() {
		return new String(data, tokBeg, tokEnd - tokBeg);
	}

	/**
	 * Returns an error version of the query with a _ at the error location.
	 */
	final String getErrorText() {
		// final StringBuffer result = new StringBuffer();
		// result.append("E Element: " + eNamedElement.getName() + "\n");
		// result.append("Begin: " + tokBeg + "\n");
		// result.append("End: " + tokEnd + "\n");
		// result.append("Length: " + data.length + "\n");
		// result.append("first part: " + new String(data, 0, tokEnd) + "\n");
		// result.append("Last part: " + new String(data, tokEnd, data.length -
		// tokEnd - 2) + "\n");

		return new String(data, 0, tokEnd) + "_"
				+ new String(data, tokEnd, data.length - tokEnd - 2)
				+ "\nCurrent lexeme: " + getLexeme();
	}

	/**
	 * Dumps the tokens.
	 */

	final String dump() {
		final StringBuffer result = new StringBuffer();
		int oldTokBeg = tokBeg;
		int oldTokEnd = tokEnd;
		int oldCurrentToken = currentToken;

		// Reset pointers.
		tokBeg = 0;
		tokEnd = 0;

		boolean lFinished = false;
		int lTok = 0;
		while (!lFinished) {
			try {
				lTok = nextToken();
				if (lTok != T_EOL) // Don't log End-of-line tokens.
				{
					result.append("Tok: " + lTok + ": '" + getLexeme() + "'\n");
				}
			} catch (AnnotationParserException e) {
				result.append("Tok: " + T_UNKNOWN + ": " + getLexeme() + "'");
				throw e;
			}
			lFinished = lTok == T_EOF;
		}

		// Restore state.
		tokBeg = oldTokBeg;
		tokEnd = oldTokEnd;
		currentToken = oldCurrentToken;
		return result.toString();
	}
}
