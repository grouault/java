package team.manager.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import team.manager.exceptions.FatalException;


/**
* Utilise un bloc statique pour initialiser la
* SessionFactory Stocke la Session et les Transactions
* dans des variables threadLocal
* @author christian@hibernate.org
*/
public class HibernateUtil {
	
  private static Log log = LogFactory.getLog(HibernateUtil.class);
  private static Configuration configuration;
  private static SessionFactory sessionFactory;
  private static final ThreadLocal threadSession = new ThreadLocal();
  private static final ThreadLocal threadTransaction = new ThreadLocal();
  private static final ThreadLocal threadInterceptor = new ThreadLocal();
  
  // Create the initial SessionFactory from the default configuration
  // files
    static {
      try {
        configuration = new Configuration();
        sessionFactory = configuration.configure().buildSessionFactory();
      // We could also let Hibernate bind it to JNDI:
      // configuration.configure().buildSessionFactory()
      } catch (Throwable ex) {
      // We have to catch Throwable, otherwise we will miss
      // NoClassDefFoundError and other subclasses of Error
        log.error("Building SessionFactory failed.", ex);
        throw new ExceptionInInitializerError(ex);
      }  
    }
    
    public static Configuration getConfiguration() {
      return configuration;
    }
    
    public static SessionFactory getSessionFactory() {
      /* Instead of a static variable, use JNDI:
      SessionFactory sessions = null;
      try {
      Context ctx = new InitialContext();
      String jndiName = "java:hibernate/HibernateFactory";
      sessions = (SessionFactory)ctx.lookup(jndiName);
      } catch (NamingException ex) {
      throw new FatalException (ex);
      }
      return sessions;
      */
      return sessionFactory;
    }
    
    /**
    * Retrieves the current Session local to the thread.
    * <p/>
    * If no Session is open, opens a new Session for the running thread.
    *
    * @return Session
    */
    public static Session getSession() throws FatalException {
      Session s = (Session) threadSession.get();
      try {
        if (s == null) {
          log.debug("Opening new Session for this thread.");
          s = getSessionFactory().openSession();
          threadSession.set(s);
//          if (getInterceptor() != null) {
//            log.debug("Using interceptor: " + getInterceptor().getClass());
//            s = getSessionFactory().openSession(getInterceptor());
//          } else {
//            s = getSessionFactory().openSession();
//          }
//          threadSession.set(s);
        }
      } catch (HibernateException ex) {
    	  throw new FatalException(ex);
      }
      return s;
    }    
    
    public static void closeSession() throws FatalException {
      try {
        Session s = (Session) threadSession.get();
        threadSession.set(null);
        if (s != null && s.isOpen()) {
          log.debug("Closing Session of this thread.");
          s.close();
        }
      } catch (HibernateException ex) {
        throw new FatalException (ex);
      }
    }    
    
    /**
    * Start a new database transaction.
    */
    public static void beginTransaction() throws FatalException {
      Transaction tx = (Transaction) threadTransaction.get();
      try {
        if (tx == null) {
          log.debug("Starting new database transaction in this thread.");
          tx = getSession().beginTransaction();
          threadTransaction.set(tx);
        }
      } catch (HibernateException ex) {
        throw new FatalException(ex);
      }
    }
    
    /**
    * Commit the database transaction.
    */
    public static void commitTransaction() throws FatalException {
      Transaction tx = (Transaction) threadTransaction.get();
      try {
        if ( tx != null && !tx.wasCommitted()
          && !tx.wasRolledBack() ) {
          log.debug("Committing database transaction of this thread.");
          tx.commit();
        }
        threadTransaction.set(null);
      } catch (HibernateException ex) {
        rollbackTransaction();
        throw new FatalException(ex);
      }
    }

    /**
    * Rollback the database transaction.
    */
    public static void rollbackTransaction() throws FatalException {
      Transaction tx = (Transaction) threadTransaction.get();
      try {
        threadTransaction.set(null);
        if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {
          log.debug("Tyring to rollback database transaction of this thread.");
          tx.rollback();
        }
      } catch (HibernateException ex) {
        throw new FatalException(ex);
      } finally {
        closeSession();
      }
    }    
    
    
    public static void rebuildSessionFactory()
    throws FatalException {
      synchronized(sessionFactory) {
        try {
          sessionFactory = getConfiguration().buildSessionFactory();
        } catch (Exception ex) {
          throw new FatalException (ex);
        }
      }
    }
    
    public static void rebuildSessionFactory(Configuration cfg)
      throws FatalException {
      synchronized(sessionFactory) {
        try {
          sessionFactory = cfg.buildSessionFactory();
          configuration = cfg;
        } catch (Exception ex) {
          throw new FatalException (ex);
        }
      }
    }

	public static ThreadLocal getInterceptor() {
		return threadInterceptor;
	}  
    
   
}
