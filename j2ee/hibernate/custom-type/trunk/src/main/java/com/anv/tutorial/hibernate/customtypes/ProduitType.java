package com.anv.tutorial.hibernate.customtypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
import org.hsqldb.Types;

public class ProduitType implements UserType {

	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}
	
	public Object nullSafeGet(ResultSet inResultSet, String[] names, Object arg2)
			throws HibernateException, SQLException {
		return null;
	}

	public void nullSafeSet(PreparedStatement inPreparedStatement, Object o, int i)
			throws HibernateException, SQLException {
		String toto = "";
		System.out.println(toto);
		// TODO
	}
	
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object deepCopy(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
        return (x == y) || (x != null && y != null && (x.equals(y)));
	}

	public int hashCode(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}


	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Class returnedClass() {
		// TODO Auto-generated method stub
		return null;
	}



}
