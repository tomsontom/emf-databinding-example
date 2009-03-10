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
 * $Id: Element.java,v 1.8 2008/06/29 14:23:09 mtaal Exp $
 */

package org.eclipse.emf.teneo.simpledom;

import java.util.ArrayList;
import java.util.List;

/**
 * This simple class is part of the replacement of dom4j.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.8 $
 */

public class Element extends Node {

	/** The children */
	private List<Element> children = new ArrayList<Element>();

	/** The attributes */
	private List<Attribute> attributes = new ArrayList<Attribute>();

	/** The parent element */
	private Element parent = null;

	/** Constructor */
	public Element() {
	}

	/** Constructor */
	public Element(String name) {
		setName(name);
	}

	/** Method to add attribute */
	public Element addAttribute(String name, String text) {
		// check if already present
		for (Attribute attr : attributes) {
			if (attr.getName().compareTo(name) == 0) {
				attr.setText(text);
				return this;
			}
		}

		final Attribute attr = new Attribute(name, text);
		attributes.add(attr);
		return this;
	}

	/**
	 * Return the value of an attribute the attribute name, if not found then null is returned
	 */
	public String getAttributeValue(String name) {
		for (Attribute attr : attributes) {
			if (attr.getName().compareTo(name) == 0) {
				return attr.getText();
			}
		}
		return null;
	}

	/** Remove the attribute using a specific name */
	public void removeAttribute(String name) {
		Attribute toRemove = null;
		for (Attribute attr : attributes) {
			if (attr.getName().compareTo(name) == 0) {
				toRemove = attr;
			}
		}
		if (toRemove != null) {
			attributes.remove(toRemove);
		}
	}

	/** Method to add attribute */
	public Element addAttribute(Attribute attr) {
		attributes.add(attr);
		return this;
	}

	/** Method to add Element */
	public Element addElement(String name) {
		final Element element = new Element();
		element.setName(name);
		children.add(element);
		element.setParent(this);
		return element;
	}

	/** Method to add Element */
	public Element addElement(Element element) {
		children.add(element);
		element.setParent(this);
		return element;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void addText(String text) {
		setText(getText() + text);
	}

	/** Add */
	public Element add(Element element) {
		children.add(element);
		element.setParent(this);
		return element;
	}

	/** Add */
	public Element add(int index, Element element) {
		children.add(index, element);
		element.setParent(this);
		return element;
	}

	/** Find a child element */
	public Element element(String name) {
		for (Element elem : children) {
			if (elem.getName().compareTo(name) == 0) {
				return elem;
			}
		}
		return null;
	}

	/** The indexof */
	public int indexOf(Element element) {
		return children.indexOf(element);
	}

	/** The remove */
	public boolean remove(Element element) {
		return children.remove(element);
	}

	/**
	 * @return the parent
	 */
	public Element getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Element parent) {
		this.parent = parent;
	}

	/** Emit ourselves */
	String emitXML() {
		final StringBuffer result = new StringBuffer("\n");
		int level = getLevel();
		for (int i = 0; i < level; i++) {
			result.append("\t");
		}

		result.append("<" + getName());
		for (int i = 0; i < attributes.size(); i++) {
			result.append(" ");
			final Attribute attr = attributes.get(i);
			result.append(attr.emitXML());
		}
		if (children.size() == 0 && getText().length() == 0) {
			result.append("/>");
			return result.toString();
		}
		result.append(">");
		if (children.size() == 0) {
			result.append(getText());
			result.append("</" + getName() + ">");
			return result.toString();
		}

		for (int i = 0; i < children.size(); i++) {
			final Element element = children.get(i);
			result.append(element.emitXML());
		}
		result.append("\n");
		for (int i = 0; i < level; i++) {
			result.append("\t");
		}
		result.append("</" + getName() + ">");
		return result.toString();
	}

	/* Return the level */
	int getLevel() {
		if (getParent() != null) {
			return 1 + getParent().getLevel();
		}
		return 0;
	}

	/** Return the children */
	public List<Element> getChildren() {
		return children;
	}

	/** Clone */
	@Override
	public Object clone() {
		final Element element = new Element();
		element.setName(getName());
		element.setText(getText());
		element.children.addAll(children);
		element.attributes.addAll(attributes);
		element.setParent(getParent());
		return element;
	}
}