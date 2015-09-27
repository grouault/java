package com.anv.tutorial.hibernate.customtypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.hsqldb.Types;

public final class KilosType implements UserType {

	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
			throws HibernateException, SQLException {
		Integer number = (Integer) Hibernate.INTEGER.nullSafeGet(rs, names[0]);
		if (null == number) {
			return null;
		}
		return new Kilos(number);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		Kilos customField = (Kilos) value;

		if (null != customField) {
			int number = customField.getNumber();
			Hibernate.INTEGER.nullSafeSet(st, number, index);
		}
	}

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		return original;
	}

	public Class returnedClass() {
		return Kilos.class;
	}

	public int[] sqlTypes() {
		return new int[] { Types.INTEGER };
	}
}
