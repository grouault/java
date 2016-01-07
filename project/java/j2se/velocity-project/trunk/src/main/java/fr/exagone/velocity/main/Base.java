package fr.exagone.velocity.main;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

import javax.naming.*;
import javax.sql.*;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.DataSourceResourceLoader;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * http://www.coderanch.com/t/61382/oa/dataSourceResourceLoader-load-velocity-Template-db 
 * */
public class Base {

	   public static String LOGGER_NAME = "velexample";
	
	   public Base(String templateFile, DataSource dataSource)
	    {
	        try
	        {
	            /*
	             * setup
	             */

	            Logger log = Logger.getLogger( LOGGER_NAME );

	            log.info("Log4jLoggerExample: ready to start velocity");
	        	
	            DataSourceResourceLoader ds = new DataSourceResourceLoader();
	            ds.setDataSource(dataSource);
	            VelocityEngine ve = new VelocityEngine();
	            
	            ve.setProperty("runtime.log", "d:/tmp/logs/velocity/velocity_example.log");
	            ve.setProperty("resource.loader","ds");
	            ve.setProperty("ds.resource.loader.class","org.apache.velocity.runtime.resource.loader.DataSourceResourceLoader");
	            ve.setProperty("ds.resource.loader.instance", ds);
	            ve.setProperty("ds.resource.loader.resource.datasource", "java:comp/env/jdbc/velocity");
	            ve.setProperty("ds.resource.loader.resource.table", "tb_velocity_template");
	            ve.setProperty("ds.resource.loader.resource.keycolumn", "id_template");
	            ve.setProperty("ds.resource.loader.resource.templatecolumn","template_definition");
	            ve.setProperty("ds.resource.loader.resource.timestampcolumn","template_timestamp");
	            ve.setProperty("ds.resource.loader.cache","false");
	            ve.setProperty("ds.resource.loader.modificationCheckInterval","60");
	            ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,"org.apache.velocity.runtime.log.Log4JLogChute" );
	            ve.setProperty("runtime.log.logsystem.log4j.logger", LOGGER_NAME);    
	            ve.init();
	           
	           	            
	            /*
	             *  Make a context object and populate with the data.  This 
	             *  is where the Velocity engine gets the data to resolve the
	             *  references (ex. $list) in the template
	             */

	            VelocityContext context = new VelocityContext();
	            context.put("list", getNames());
	            String boucle = "#foreach( $name in $list ) $name is great! #end";
	            context.put("boucle", boucle);
	            
	            /*
	             *  get the Template object.  This is the parsed version of your 
	             *  template input file.  Note that getTemplate() can throw
	             *   ResourceNotFoundException : if it doesn't find the template
	             *   ParseErrorException : if there is something wrong with the VTL
	             *   Exception : if something else goes wrong (this is generally
	             *        indicative of as serious problem...)
	             */
	            Template template =  null;

	            try {
	            	template = ve.getTemplate("exemple");
	            } catch( ResourceNotFoundException rnfe ) {
	                System.out.println("Example : error : cannot find template " + templateFile );
	            }
	            catch( ParseErrorException pee )
	            {
	                System.out.println("Example : Syntax error in template " + templateFile + ":" + pee );
	            }

	            /*
	             *  Now have the template engine process your template using the
	             *  data placed into the context.  Think of it as a  'merge' 
	             *  of the template and the data to produce the output stream.
	             */

	            BufferedWriter writer = writer = new BufferedWriter(
	                new OutputStreamWriter(System.out));

	            if ( template != null)
	                template.merge(context, writer);

	            /*
	             *  flush and cleanup
	             */

	            writer.flush();
	            writer.close();
	        }
	        catch( Exception e )
	        {
	            System.out.println(e);
	        }
	    }

	    public ArrayList getNames()
	    {
	        ArrayList list = new ArrayList();

	        list.add("ArrayList element 1");
	        list.add("ArrayList element 2");
	        list.add("ArrayList element 3");
	        list.add("ArrayList element 4");

	        return list;
	    }

	    public static void main(String[] args)
	    {
	    	
	    	 try {
	    		 
	    	      startRegistry();
	    	      ConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
	    	      
	    	      ((MysqlDataSource) dataSource).setUser("velocity");
	    	      ((MysqlDataSource) dataSource).setPassword("velocity");
	    	      ((MysqlDataSource) dataSource).setServerName("localhost");
	    	      ((MysqlDataSource) dataSource).setPort(3306);
	    	      ((MysqlDataSource) dataSource).setDatabaseName("velocity");

	    	      InitialContext context = createContext();
	    	      context.rebind("jdbc/velocity", dataSource);
	    	      
	    	      DataSource ds = (DataSource) context.lookup("jdbc/velocity");
	    	      Base t = new Base("exemple", ds);
	    	      
	    	    } catch (Exception e) {
	    	      System.out.println("SetupJNDIDataSource err: " + e.getMessage());
	    	      e.printStackTrace();
	    	    }
	    	
	        
	    }
	    
	    private static void startRegistry() throws RemoteException {
	       LocateRegistry.createRegistry(1099);
	        System.out.println("RMI registry ready.");
	      }

	      private static InitialContext createContext() throws NamingException {
	        Properties env = new Properties();
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
	        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");
	        InitialContext context = new InitialContext(env);
	        return context;
	      }	    
	
}
