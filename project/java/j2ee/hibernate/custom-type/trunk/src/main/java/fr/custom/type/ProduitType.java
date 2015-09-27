package fr.custom.type;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import fr.customtype.utils.BooleanUtil;

public class ProduitType implements UserType {

	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object nullSafeGet(ResultSet inResultSet, String[] names, Object arg2)
			throws HibernateException, SQLException {
        String val = (String)Hibernate.STRING.nullSafeGet(inResultSet, names[0]);
        return BooleanUtil.toBooleanValue(val);
	}

	public void nullSafeSet(PreparedStatement inPreparedStatement, Object o, int i)
			throws HibernateException, SQLException {
        String val = BooleanUtil.toStringValue((Boolean)o);
        inPreparedStatement.setString(i, val);
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
