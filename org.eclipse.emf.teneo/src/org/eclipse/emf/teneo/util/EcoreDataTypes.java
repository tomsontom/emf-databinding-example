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
 *   Davide Marchignoli
 *   Brian Vetter (bugzilla 175909)
 *   Alexandros Karypidis (bugzilla 207799)
 * </copyright>
 *
 * $Id: EcoreDataTypes.java,v 1.14 2008/06/02 07:15:29 mtaal Exp $
 */

package org.eclipse.emf.teneo.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.teneo.PersistenceOptions;
import org.eclipse.emf.teneo.TeneoException;
import org.eclipse.emf.teneo.annotations.pamodel.PAnnotatedEAttribute;

/**
 * Utility class to classify Ecore datatypes.
 * 
 * @author <a href="mailto:marchign at elver.org">Davide Marchignoli</a>
 */
public class EcoreDataTypes {

	// The xml types
	private static XMLTypePackage xmlTypePackage = XMLTypePackage.eINSTANCE;
	private static EDataType xmlDateEDataType = xmlTypePackage.getDate();
	private static EDataType xmlDateTimeEDataType = xmlTypePackage.getDateTime();

	// The source of the annotations of extended metadata used by emf
	private static final String ANNOTATION_SOURCE_METADATA = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";

	// XML datatype factory instance
	private final DatatypeFactory dataTypeFactory;

	private static final List<EDataType> PRIMITIVES_ETYPES_LIST =
			Collections.unmodifiableList(Arrays.asList(new EDataType[] { EcorePackage.eINSTANCE.getEBoolean(),
					EcorePackage.eINSTANCE.getEByte(), EcorePackage.eINSTANCE.getEChar(),
					EcorePackage.eINSTANCE.getEDouble(), EcorePackage.eINSTANCE.getEFloat(),
					EcorePackage.eINSTANCE.getEInt(), EcorePackage.eINSTANCE.getELong(),
					EcorePackage.eINSTANCE.getEShort(), }));

	private static final List<Class<?>> PRIMITIVE_OBJECT_TYPE_LIST =
			Collections.unmodifiableList(Arrays.asList(new Class<?>[] { java.lang.Boolean.class, java.lang.Byte.class,
					java.lang.Double.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Long.class,
					java.math.BigDecimal.class, java.math.BigInteger.class }));

	private static final List<EDataType> WRAPPERS_ETYPES_LIST =
			Collections.unmodifiableList(Arrays.asList(new EDataType[] { EcorePackage.eINSTANCE.getEBooleanObject(),
					EcorePackage.eINSTANCE.getEByteObject(), EcorePackage.eINSTANCE.getECharacterObject(),
					EcorePackage.eINSTANCE.getEDoubleObject(), EcorePackage.eINSTANCE.getEFloatObject(),
					EcorePackage.eINSTANCE.getEIntegerObject(), EcorePackage.eINSTANCE.getELongObject(),
					EcorePackage.eINSTANCE.getEShortObject(), }));

	public static EcoreDataTypes INSTANCE = new EcoreDataTypes();

	private EcoreDataTypes() {
		try {
			dataTypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new TeneoException("Exception ", e);
		}
	}

	/** Returns the type name of a many attribute */
	public String getTargetTypeName(PAnnotatedEAttribute aAttribute) {
		final EAttribute eAttribute = aAttribute.getModelEAttribute();
		// check on equality on object.class is used for listunion simpleunions
		final Class<?> instanceClass = eAttribute.getEAttributeType().getInstanceClass();
		if (instanceClass != null && !Object.class.equals(instanceClass) && !List.class.equals(instanceClass)) {
			if (instanceClass.isArray()) {
				// get rid of the [] at the end
				return eAttribute.getEType().getInstanceClassName().substring(0,
					eAttribute.getEType().getInstanceClassName().length() - 2);
			}
			return instanceClass.getName();
		}
		// the type is hidden somewhere deep get it
		// the edatatype is the java.util.list
		// it has an itemType which is the name of the element edatatype
		// which contains the instanceclass
		// takes also into account inheritance between datatypes
		// NOTE the otm.targetentity can consist of a comma delimited list
		// of target
		// entities this is required for listunion types but is not
		// according to the ejb3 spec!
		ArrayList<EClassifier> eclassifiers = getItemTypes((EDataType) eAttribute.getEType());
		if (eclassifiers.size() > 0) {
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < eclassifiers.size(); i++) {
				final EClassifier eclassifier = eclassifiers.get(i);
				if (i > 0) {
					result.append(",");
				}
				result.append(eclassifier.getInstanceClassName());
			}
			return result.toString();
		}
		return Object.class.getName();
	}

	/** Walks up a edatatype inheritance structure to find the itemType */
	public ArrayList<EClassifier> getItemTypes(EDataType eDataType) {
		final ArrayList<EClassifier> result = new ArrayList<EClassifier>();
		if (eDataType == null) {
			return result;
		}
		final String itemType = getEAnnotationValue(eDataType, ANNOTATION_SOURCE_METADATA, "itemType");
		if (itemType != null) {
			final EClassifier eClassifier = getEClassifier(eDataType.getEPackage(), itemType);
			if (eClassifier != null) {
				result.add(eClassifier);
			}

			return result;
		}

		final String memberTypes = getEAnnotationValue(eDataType, ANNOTATION_SOURCE_METADATA, "memberTypes");
		if (memberTypes != null) {
			String[] mtypes = memberTypes.split(" ");
			for (String element : mtypes) {
				final EClassifier eclassifier = getEClassifier(eDataType.getEPackage(), element);
				if (eclassifier != null) {
					result.addAll(getItemTypes((EDataType) eclassifier));
				}
			}
			return result;
		}

		final String baseType = getEAnnotationValue(eDataType, ANNOTATION_SOURCE_METADATA, "baseType");
		if (baseType != null) {
			final EClassifier eClassifier = getEClassifier(eDataType.getEPackage(), baseType);
			if (eClassifier != null) {
				final ArrayList<EClassifier> tmpResult = getItemTypes((EDataType) eClassifier);
				if (tmpResult.size() > 0) {
					result.addAll(tmpResult);
					return result;
				}
			}
		}
		if (!Object.class.equals(eDataType.getInstanceClass())) {
			result.add(eDataType);
		}
		return result;
	}

	/**
	 * Returns the eclassifier using either the name of the eclassifier or the name element
	 */
	public EClassifier getEClassifier(EPackage epackage, String searchName) {
		for (EClassifier eclassifier : epackage.getEClassifiers()) {
			if (eclassifier.getName().compareTo(searchName) == 0) {
				return eclassifier;
			}
			String nameAnnotation = getEAnnotationValue(eclassifier, ANNOTATION_SOURCE_METADATA, "name");
			if (nameAnnotation != null && searchName.compareTo(nameAnnotation) == 0) {
				return eclassifier;
			}
		}
		return null;
	}

	/** Returns the value of an annotation with a certain key */
	public String getEAnnotationValue(EModelElement eModelElement, String source, String key) {
		final EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			return null;
		}
		return eAnnotation.getDetails().get(key);
	}

	// TODO: Make all utility methods static.

	/** Return a XMLGregorianCalendar on the basis of the date */
	public XMLGregorianCalendar getXMLGregorianCalendar(Date date) {
		final XMLGregorianCalendar gregCalendar = dataTypeFactory.newXMLGregorianCalendar();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		gregCalendar.setYear(calendar.get(Calendar.YEAR));
		gregCalendar.setMonth(calendar.get(Calendar.MONTH) + 1); // note the correction with 1
		gregCalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		return gregCalendar;
	}

	/** Return a XMLGregorianCalendar on datetime level (milliseconds) */
	public XMLGregorianCalendar getXMLGregorianCalendarDateTime(Date date) {
		final XMLGregorianCalendar gregCalendar = dataTypeFactory.newXMLGregorianCalendar();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		gregCalendar.setYear(calendar.get(Calendar.YEAR));
		gregCalendar.setMonth(calendar.get(Calendar.MONTH) + 1); // correct with 1 on purpose
		gregCalendar.setDay(calendar.get(Calendar.DAY_OF_MONTH));
		gregCalendar.setHour(calendar.get(Calendar.HOUR_OF_DAY));
		gregCalendar.setMinute(calendar.get(Calendar.MINUTE));
		gregCalendar.setSecond(calendar.get(Calendar.SECOND));
		gregCalendar.setMillisecond(calendar.get(Calendar.MILLISECOND));
		return gregCalendar;
	}

	/**
	 * @return Returns an immutable list of the Ecore EDataType for java primitives.
	 */
	public List<EDataType> getEPrimitives() {
		return PRIMITIVES_ETYPES_LIST;
	}

	/**
	 * @return Returns true if and only if the the given eDataType is the Ecore EDataType for a
	 *         primitive type.
	 */
	public boolean isEPrimitive(EDataType eDataType) {
		return eDataType != null && isPrimitive(eDataType.getInstanceClass());
	}

	public boolean isPrimitive(Class<?> clz) {
		if (clz == null) {
			return false;
		}
		return clz.isPrimitive() || PRIMITIVE_OBJECT_TYPE_LIST.contains(clz);
	}

	/**
	 * @return Returns an immutable list of the Ecore EDataType for java primitive wrapper classes.
	 */
	public List<EDataType> getEWrappers() {
		return WRAPPERS_ETYPES_LIST;
	}

	/**
	 * @return Returns true if and only if the the given eDataType is the Ecore EDataType for a
	 *         primitive wrapper class.
	 */
	public boolean isEWrapper(EDataType eDataType) {
		return WRAPPERS_ETYPES_LIST.contains(eDataType);
	}

	/**
	 * @return true if and only if the given dataType is a string datatype.
	 */
	public boolean isEString(EDataType eDataType) {
		// should be eDataType == EString but does not work due to XML type
		// implementations
		return String.class == eDataType.getInstanceClass();
	}

	public boolean isEDuration(EDataType eDataType) {
		String className = null;
		if (eDataType.getInstanceClassName() != null) {
			className = eDataType.getInstanceClassName();
		} else if (eDataType.getInstanceClass() != null) {
			className = eDataType.getInstanceClass().getName();
		} else {
			return false;
		}
		return className.compareTo("javax.xml.datatype.Duration") == 0;
	}

	/**
	 * - *
	 * 
	 * @return true if and only if the given dataType is a date datatype.
	 */
	public boolean isEDate(EDataType eDataType, PersistenceOptions po) {
		if (eDataType.equals(xmlDateEDataType)) {
			return true;
		}
		/*
		 * There is some ambiguity around the Java Date class since it can also hold time - a
		 * conflict with the DateTime class
		 */
		Class<?> ic = eDataType.getInstanceClass();
		// do a string comparison to prevent another dependency for this teneo library.
		if (eDataType.getInstanceClassName() != null &&
				eDataType.getInstanceClassName().compareTo(po.getXSDDateClass()) == 0) {
			return true;
		}
		return java.util.Date.class == ic || java.util.Calendar.class == ic || java.sql.Date.class == ic;
	}

	/**
	 * @return true if and only if the given dataType is a datetime/timestamp datatype.
	 */
	public boolean isEDateTime(EDataType eDataType) {
		if (eDataType.equals(xmlDateTimeEDataType)) {
			return true;
		}
		/*
		 * the InstanceClass for date type can be "Object" for XSD types. I'm not sure about ecore
		 * itself so I have kept the original check against the java classes.
		 */
		Class<?> ic = eDataType.getInstanceClass();
		// already handled through the first if
// if (ic == Object.class) {
// // could be an XML date type
// return eDataType.equals(xmlDateTimeEDataType);
// }
		return java.sql.Timestamp.class == ic || Date.class == ic;
	}

	/**
	 * @return Returns true if and only if the given type is either a primitive or a wrapper or
	 *         string or a date.
	 */
	public boolean isSimpleType(EDataType eType, PersistenceOptions po) {
		// TODO move elsewhere
		return isEPrimitive(eType) || isEWrapper(eType) || isEString(eType) || isEDate(eType, po) || isEDateTime(eType);
	}

	/**
	 * EJB3-SPEC 9.1.16
	 * 
	 * @return Returns true if the given eDataType is a Basic type
	 */
	public boolean isBasicType(EDataType eDataType, PersistenceOptions po) {
		// TODO consider also BigInteger, BigDecimal, java.util.Calendar,
		// java.sql.Date
		// java.sql.Time, java.sql.Timestamp, byte[], Byte[], char[],
		// Character[]
		// and any other type that implements Serializable
		return isSimpleType(eDataType, po) || isEnum(eDataType);
	}

	/**
	 * @return Returns true if the given EDataType is an Ecore enumerated type.
	 */
	public boolean isEnum(EClassifier eClassifier) {
		return (eClassifier instanceof EEnum);
	}

	/**
	 * @return true if the eType is a byte array.
	 */
	public boolean isByteArray(EDataType eType) {
		final Class<?> clazz = eType.getInstanceClass();
		if (clazz != null) {
			return (clazz.isArray() && clazz.getComponentType().equals(Byte.TYPE));
		}
		return false;
	}
}
