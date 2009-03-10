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
 * $Id: HibernateXMLResource.java,v 1.1 2008/03/12 18:36:34 mtaal Exp $
 */

package org.eclipse.emf.teneo.hibernate.resource;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.DOMHandler;
import org.eclipse.emf.ecore.xmi.DOMHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * Tries to add XMLResource behavior to the Hibernate Resource. Not that not all methods are
 * implemented.
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.1 $
 */

public class HibernateXMLResource extends HibernateResource implements XMLResource {

	public HibernateXMLResource(URI uri) {
		super(uri);
	}

	public Map<Object, Object> getDefaultLoadOptions() {
		return new HashMap<Object, Object>();
	}

	public Map<Object, Object> getDefaultSaveOptions() {
		return new HashMap<Object, Object>();
	}

	public DOMHelper getDOMHelper() {
		return null;
	}

	public String getEncoding() {
		return null;
	}

	public Map<EObject, AnyType> getEObjectToExtensionMap() {
		return null;
	}

	public Map<EObject, String> getEObjectToIDMap() {
		return new HashMap<EObject, String>();
	}

	public String getID(EObject object) {
		return getURIFragment(object);
	}

	public Map<String, EObject> getIDToEObjectMap() {
		return new HashMap<String, EObject>();
	}

	public String getPublicId() {
		return null;
	}

	public String getSystemId() {
		return null;
	}

	public String getXMLVersion() {
		return null;
	}

	public void load(InputSource inputSource, Map<?, ?> options) throws IOException {
		final XMLResource xmlResource = new XMLResourceImpl();
		xmlResource.load(inputSource, options);
		getContents().addAll(xmlResource.getContents());
		setLoaded(true);
	}

	public void load(Node node, Map<?, ?> options) throws IOException {
		final XMLResource xmlResource = new XMLResourceImpl();
		xmlResource.load(node, options);
		getContents().addAll(xmlResource.getContents());
		setLoaded(true);
	}

	public Document save(Document document, Map<?, ?> options, DOMHandler handler) {
		final XMLResource xmlResource = new XMLResourceImpl();
		xmlResource.getContents().addAll(getContents());
		return xmlResource.save(document, options, handler);
	}

	public void save(Writer writer, Map<?, ?> options) throws IOException {
		final XMLResource xmlResource = new XMLResourceImpl();
		xmlResource.getContents().addAll(getContents());
		xmlResource.save(writer, options);
	}

	public void setDoctypeInfo(String publicId, String systemId) {
	}

	public void setEncoding(String encoding) {
	}

	public void setID(EObject object, String id) {
	}

	public void setUseZip(boolean useZip) {
	}

	public void setXMLVersion(String version) {
	}

	@Override
	public boolean useZip() {
		return false;
	}
}
