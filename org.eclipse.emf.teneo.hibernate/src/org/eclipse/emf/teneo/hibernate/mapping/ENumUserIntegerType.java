package org.eclipse.emf.teneo.hibernate.mapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Properties;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.teneo.classloader.ClassLoaderResolver;
import org.eclipse.emf.teneo.classloader.StoreClassLoadException;
import org.eclipse.emf.teneo.hibernate.HbMapperException;
import org.eclipse.emf.teneo.hibernate.mapper.HbMapperConstants;
import org.hibernate.HibernateException;

/**
 * Stores a java enum
 * 
 * @author <a href="mailto:mtaal@elver.org">Martin Taal</a>
 * @version $Revision: 1.6 $ $Date: 2007/04/17 15:49:44 $
 */

public class ENumUserIntegerType extends ENumUserType {
	
	/** The sql types used for enums */
	private static final int[] SQL_TYPES = new int[] { Types.INTEGER };

	/** Hashmap with string to enum mappings */
	private final HashMap<Integer, Enumerator> localCache = new HashMap<Integer, Enumerator>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
	 */
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
		final int value = rs.getInt(names[0]);
		if (rs.wasNull())
			return null;

		Integer objValue = new Integer(value);
		Enumerator enumValue = (Enumerator) localCache.get(objValue);
		if (enumValue != null)
			return enumValue;

		// call the getMethod!
		try {
			// use non
			enumValue = (Enumerator) getMethod.invoke(null, new Object[] { objValue });
			localCache.put(objValue, enumValue);
			return enumValue;
		} catch (Exception e) {
			throw new HbMapperException("Exception when getting enum for class: " + enumType.getName()
					+ " using int value: " + value, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
	 */
	public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.INTEGER);
		} else {
			st.setInt(index, ((Enumerator) value).getValue());
		}
	}

	/** An enum is stored in one varchar */
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	/** Sets the enumclass */
	public void setParameterValues(Properties parameters) {
		final String enumClassName = parameters.getProperty(HbMapperConstants.ENUM_CLASS_PARAM);
		try {
			enumType = ClassLoaderResolver.classForName(enumClassName);
			getMethod = enumType.getMethod("get", new Class[] { int.class });
		} catch (StoreClassLoadException e) {
			throw new HbMapperException("Enum class " + enumClassName + " can not be found", e);
		} catch (NoSuchMethodException e) {
			throw new HbMapperException("Get method not present in enum class " + enumClassName, e);
		}
	}
}