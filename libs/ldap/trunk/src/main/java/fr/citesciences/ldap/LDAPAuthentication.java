/*
 * Created on 12 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.citesciences.ldap;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.LdapReferralException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Administrateur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LDAPAuthentication {

	private static Log logger = LogFactory.getLog(LDAPAuthentication.class);

	/**
	 * Connection securisee par default : Site_CSI
	 * l'echec peut etre du a un probleme reseau ou a un mot de passe invalide 
	 * 
	 * @param login
	 * @param password
	 * @return true si la connexion a reussi ou false si elle a echoue
	 * 
	 */
	 public boolean secureAuthentication(String login, String password) {
       return this.secureAuthentication(login, password, LDAPConstants.SITE_CSI);
	}
	 
     /**
      * connection securises en specifiant un site specifique.
      *   
      * @param login
      * @param password
      * @param site
      * @return true ou false suivant la reussite de la connexion.
      */
	 public boolean secureAuthentication(final String login, final String password, final String site) {
		DirContext ctx = null;
		boolean connected = false;
		Hashtable env = null;
		try {			
		  String secure = "false";
		  // on verifie si la connexion doit se faire en mode securise.
		  if (LDAPConstants.SITE_CSI.equals(site)) {
		    secure = LDAPConstants.MODE_SECURE_CSI;	  
		  } else if (LDAPConstants.SITE_PALAIS.equals(site)) {
			secure = LDAPConstants.MODE_SECURE_PALAIS;  
		  }
		  // recuperation des parametres de connexion.
		  if (Boolean.valueOf(secure).booleanValue()) {
			env = this.getEnvModeSecure(site, login, password); // connexion en mode securise  
		  } else {  
			env = this.getEnvModeNormal(site, login, password); // connexion en mode normal.
		  }
          ctx = new InitialDirContext(env);
		  connected = true;
		} catch (NamingException e) {
			logger.info("Echec de la communication LDAP pour le login [" + login + "], sur le site [" + site + "]");
		} 
		return connected;	 
	 }
	 
	/**
	 * permet de se connecter au site universcience.
	 *  - mode secure sur le site CSI.
	 *  - mode anonyme sur le site Palais.
	 *  
	 * @param login
	 * @param password
	 * @return : true ou false suivant la reussite de la connexion.
	 */
	public boolean secureAuthenticationUniverscience(String login, String  password) {
		//1- connnection CSI.
		boolean connected = secureAuthentication(login, password, LDAPConstants.SITE_CSI);
		if(!connected){
		  // 2- connexionxion PALAIS.
		  connected = secureAuthentication(login, password, LDAPConstants.SITE_PALAIS);
		}
		return connected;
	}
	 
		
	/**
	 * Authentification LDAP anonyme. 
	 * Mode par default : branche sur la conf cite.
	 * 
	 * @return le DirContext representant la connexion LDAP 
	 * @throws NamingException
	 */
	public DirContext connectLDAP() throws NamingException {
	  return connectLDAP(LDAPConstants.SITE_CSI);
	}
	
	/**
	 * permet une connexion anonyme pour un site donne.
	 * @param site
	 * @return : contexte.
	 * @throws NamingException
	 */
	public DirContext connectLDAP(final String site) throws NamingException {
		DirContext ctx = null;
		Properties props = new Properties();
		
		props.setProperty(Context.PROVIDER_URL, defineAnonymousUrl(site)); // choix URL anonyme ldap
		
		props.setProperty(Context.INITIAL_CONTEXT_FACTORY,LDAPConstants.LDAP_PROVIDER);
		String[] parametersSearchLogin = this.defineLoginSearch(site); // recuperation des login et mdp de connexion pour la recherche.
		if (parametersSearchLogin != null) {
		  props.setProperty(Context.SECURITY_AUTHENTICATION, "simple");
		  props.setProperty(Context.SECURITY_PRINCIPAL, parametersSearchLogin[0]); // recuperation du login.
		  props.setProperty(Context.SECURITY_CREDENTIALS, parametersSearchLogin[1]); // recuperation du mot de passe.
		} else {
		  props.setProperty(Context.SECURITY_AUTHENTICATION, LDAPConstants.SEARCH_SECURITY_LEVEL);
		}
		
		logger.debug("Tentative de connection LDAP anonyme : " + props);
		ctx = new InitialDirContext(props);

		return ctx;
	}
	
	 /**
	  * permet de ramener la conf. relative a un site donne en mode securise.
	  * - security protocol : ssl.
	  * @param site
	  * @return HashTable avec les parametres de connexion.
	  * @throws NamingException 
	  */
	 private Hashtable getEnvModeSecure (final String site, final String login, final String password) throws NamingException {
	   Hashtable env = new Hashtable();
	   // url de connexion a la base LDAP : depend du site.
	   env.put(Context.PROVIDER_URL, defineSecureUrl(site));
	   // classe utilise comme fournisser JNDI.
	   env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	   // IMORTANT : connexion en mode securise.
	   env.put(Context.SECURITY_PROTOCOL, "ssl");
	   // methode d'authentification utilisee.
	   env.put(Context.SECURITY_AUTHENTICATION, "simple");
	   // dn utilisateur : depend du site.
	   env.put(Context.SECURITY_PRINCIPAL, getDNFromLogin(login, site) + ","+ defineBaseSearch(site));	   
	   // mot de passe utilisateur.
	   env.put(Context.SECURITY_CREDENTIALS, password);
	   return env;
	 } 	
	
	 /**
	  * permet de ramenenr la conf. relative a un site donne en mode normal.
	  * attention - connexion en mode non securise.
	  * @param site
	  * @param login
	  * @param password
	  * @return
	  * @throws NamingException
	  */
	 private Hashtable getEnvModeNormal (final String site, final String login, final String password) throws NamingException {
	   Hashtable env = new Hashtable();
	   // url de connexion a la base LDAP : depend du site.
	   env.put(Context.PROVIDER_URL, defineSecureUrl(site));
	   // classe utilise comme fournisser JNDI.
	   env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	   // methode d'authentification utilisee.
	   env.put(Context.SECURITY_AUTHENTICATION, "simple"); // none ==> pas d'authentification.
	   // dn utilisateur : depend du site.
	   env.put(Context.SECURITY_PRINCIPAL, getDNFromLogin(login, site) + ","+ defineBaseSearch(site));	   
	   // mot de passe utilisateur.
	   env.put(Context.SECURITY_CREDENTIALS, password);
	   return env;
	 } 	
	 
	/**
	 * Recupere le DN pour un login donné
	 * 
	 * @param login
	 * @return le DN sous forme de String ou null si l'utilisateur n'existe pas
	 * @throws NamingException
	 */	
	protected String getDNFromLogin(final String login, final String site) throws NamingException {
		String DN = null;
		if (site != null && LDAPConstants.SITE_PALAIS.equals(site)) {
		  // recherche specifique PALAIS : le dn inclus de l'attribut sAMAccountName.	
		  DN = this.getDnFromSAMAccountName(login);	
		} else {
			// autre recherche : le dn inclux l'attribut cn pour le login : cas CSI.
		    DirContext context = null;
			NamingEnumeration results = null;
			try{
		      if (site != null) {
		        context = connectLDAP(site);	  
		      } else {
		    	context = connectLDAP();  
		      }
			  SearchControls constraints = new SearchControls();
			  constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);		
			  results = context.search(defineBaseSearch(site), "(&(objectClass=Person)(cn=" + login + "))", constraints);
			  if (results.hasMoreElements()) {
				SearchResult result = (SearchResult) results.next();
				DN = result.getName();
			  } else {
				logger.warn("Utilisateur LDAP inconnu : [" + login + "]");
			  }
			} finally {
			  if (results != null) try { 
			    results.close(); 
			  } catch (NamingException logOrIgnore) {}
			  if (context != null) try {
			    context.close();
			  } catch (NamingException logOrIgnore) {}
			}
		}
		return DN;
	}	
	
	/**
	 * permet de faire de verifier la validite du login pour un user palais.
	 * Attribut de recherce : sAMAccountName.
	 * @param login
	 * @return
	 */
	protected String getDnFromSAMAccountName (final String login) throws NamingException{
	  DirContext context = null;
	  String DN = null;
	  NamingEnumeration results = null;
	  try {
		context = context = connectLDAP(LDAPConstants.SITE_PALAIS);	 
	    SearchControls constraints = new SearchControls();
	    constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);		
	    results = context.search(LDAPConstants.BASE_SEARCH_PALAIS, "(&(objectClass=Person)(" + LDAPConstants.SEARCH_PERSON_FILTER_PALAIS_ATTRIBUTE + "=" + login + "))", constraints);
	    if (results.hasMoreElements()) {
		  SearchResult result = (SearchResult) results.next();
		  DN = result.getName();
	    } else {
		  logger.warn("Utilisateur LDAP PALAIS inconnu : [" + login + "]");
	    }		    
	  } finally {
		if (results != null) try { 
		  results.close(); 
		} catch (NamingException logOrIgnore) {}
		if (context != null) try {
		  context.close();
		} catch (NamingException logOrIgnore) {}
	  }
	  return DN;
	}
	
	
	/**
	 * retourne l'url de connexion anonyme pour une site donne.
	 * @param site
	 * @return
	 */
	private String defineAnonymousUrl(final String site){
	  String  DEFAULT_SITE = LDAPConstants.LDAP_URL_ANONYMOUS_AUTHENTICATION;
	  if(site==null){
		return DEFAULT_SITE;
	  }
	  else if(site.equals(LDAPConstants.SITE_CSI)){
	    return LDAPConstants.LDAP_URL_ANONYMOUS_AUTHENTICATION_CSI;
	  }
	  else if(site.equals(LDAPConstants.SITE_PALAIS)){
		return LDAPConstants.LDAP_URL_ANONYMOUS_AUTHENTICATION_PALAIS;
	  }
	  return DEFAULT_SITE;
	}	
	
	/**
	 * retourne l'url de connexion LDAP en mode securise.
	 * 
	 * @param site
	 * @return : url de connexion
	 */
    private String defineSecureUrl(final String site){
    	
	  String  DEFAULT_SITE = LDAPConstants.LDAP_URL_LOGIN_SECURE_AUTHENTICATION;
	  
	  if(site==null){
		return DEFAULT_SITE;
	  }
	  else if(site.equals(LDAPConstants.SITE_CSI)){
		return LDAPConstants.LDAP_URL_LOGIN_SECURE_AUTHENTICATION_CSI;
	  }
	  else if(site.equals(LDAPConstants.SITE_PALAIS)){
		return LDAPConstants.LDAP_URL_LOGIN_SECURE_AUTHENTICATION_PALAIS;
	  }
	  return DEFAULT_SITE;
	}
    
    /**
     * permet de recuperer le noeud de recherche.
     * 
     * @param site
     * @return : noeud de recherche
     */
	public String defineBaseSearch(final String site){
		
	  String  DEFAULT_BASE = LDAPConstants.BASE_SEARCH;
	
	  if(site==null){
		return DEFAULT_BASE;
	  }
	  else if(site.equals(LDAPConstants.SITE_CSI)){
		return LDAPConstants.BASE_SEARCH_CSI;
	  }
	  else if(site.equals(LDAPConstants.SITE_PALAIS)){
		return LDAPConstants.BASE_SEARCH_PALAIS;
	  }
	  
	  return DEFAULT_BASE;
	}
	
	/**
	 * pemet de retourner les parametres de connexion pour la recherche
	 *   en fonction du site.
	 * 
	 * @param site
	 * @return : parametres de connexion : login et passwd.
	 */
	private String[] defineLoginSearch(final String site){
	  String[] parameters = null;
	  
	  // Cas Site_CSI
	  if(site.equals(LDAPConstants.SITE_CSI)){
		if (LDAPConstants.SEARCH_LOGIN_CSI != null && LDAPConstants.SEARCH_PASSWORD_CSI != null) {
	       parameters = new String[2];
	       parameters[0] = LDAPConstants.SEARCH_LOGIN_CSI;
	       parameters[1] = LDAPConstants.SEARCH_PASSWORD_CSI;
		}  
	  }
	  
	  // Cas Site_PALAIS
	  if(site.equals(LDAPConstants.SITE_PALAIS)){
		if (LDAPConstants.SEARCH_LOGIN_PALAIS != null && LDAPConstants.SEARCH_PASSWORD_PALAIS != null) {
	       parameters = new String[2];
	       parameters[0] = LDAPConstants.SEARCH_LOGIN_PALAIS;
	       parameters[1] = LDAPConstants.SEARCH_PASSWORD_PALAIS;
		}  
	  }
	  
	  return parameters;
	}
	
	
}