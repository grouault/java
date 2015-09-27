package eni.jse.tp.bd.JNDI;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/** Enonce 5 / Etape 1 */
/**
 * Ici on lie une ressource au nom jdbc/BD1
 */
public class JNDIDataSource {
	public static void main(String[] args) throws Exception {
		Properties env = System.getProperties();
		env.put("java.naming.factory.initial",
		        "com.sun.jndi.rmi.registry.RegistryContextFactory");
		env.put("java.naming.provider.url","rmi://localhost:1099");
		Context c = new InitialContext( env );
		com.mysql.jdbc.jdbc2.optional.MysqlDataSource ds = new com.mysql.jdbc.jdbc2.optional.MysqlDataSource();
        ds.setDatabaseName("entreprise");
        ds.setUser("root");
        ds.setPassword("41176");
        // Establish initial context and bind to the datasource target
        InitialContext ic = new InitialContext();
        System.out.println( "BIND avec jdbc/BD1" );
        ic.bind("jdbc/BD1",ds);	
	}
}
