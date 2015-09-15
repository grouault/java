/*
 * Created on 12 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.citesciences.ldap;

/**
 * @author Administrateur
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LDAPConstants {

    // URL Connection serveur LDAP SPIDER
    //public static String LDAP_URL_SPIDER = "ldap://cdp.spiderbusiness.com:389";
	
	// sites
	public static String SITE_PALAIS ="SITE_PALAIS";
	public static String SITE_CSI ="SITE_CSI";
	
	// parametre SITE DEFAULT
    public static String BASE_SEARCH = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.base"); 
    public static String LDAP_URL_LOGIN_SECURE_AUTHENTICATION = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.secured.url");
    public static String LDAP_URL_ANONYMOUS_AUTHENTICATION = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.anonymous.url");
    
	// parametre SITE CSI.
    public static String LDAP_URL_LOGIN_SECURE_AUTHENTICATION_CSI = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.secured.url");
    public static String LDAP_URL_ANONYMOUS_AUTHENTICATION_CSI = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.anonymous.url");
    public static String BASE_SEARCH_CSI = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.base");
	public static String SEARCH_LOGIN_CSI = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.login");
	public static String SEARCH_PASSWORD_CSI = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.password");    
	public static String MODE_SECURE_CSI = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.mode.secure.csi");   
	
	// parametre SITE Palais.
    public static String LDAP_URL_LOGIN_SECURE_AUTHENTICATION_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.secured.url.palais");
    public static String LDAP_URL_ANONYMOUS_AUTHENTICATION_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.anonymous.url.palais");
    public static String BASE_SEARCH_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.base.palais");
	public static String SEARCH_LOGIN_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.login.palais");
	public static String SEARCH_PASSWORD_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.password.palais");
	public static String MODE_SECURE_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.mode.secure.palais");      
	
    // Fournisseur JNDI
    public static String LDAP_PROVIDER = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.jndi.provider");
    
	// Niveau de securite utilise  pour la connection au serveur LDAP
	public static String SEARCH_SECURITY_LEVEL = "none";
	// Niveau de securite pour mise a jour
	public static String UPDATE_SECURITY_LEVEL = "simple";
	// Chemin du certificat + nom
	public static String CERTIFICATE_PATH = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.certificate.path");
	// Mot de passe utilise dans le certificat
	public static String CERTIFICATE_PASSWORD = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.certificate.password");
	// Filtre de recherche
	public static String SEARCH_PERSON_FILTER = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.filter.person");
	// Attribut de recherche
	public static String SEARCH_PERSON_FILTER_ATTRIBUTE = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.filter.person.attribute");
	
	// Datas attibutes
	public static String ATTRIBUT_FIRSTNAME = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.attribut.firstname");
	public static String ATTRIBUT_LASTNAME = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.attribut.lastname");
	public static String ATTRIBUT_EMAIL = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.attribut.email");
	public static String ATTRIBUT_TEL = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.attribut.tel");
	
	// Datas attribute site PALAIS.
	public static String ATTRIBUT_LOGIN_PALAIS = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.attribut.login.palais");
	public static String SEARCH_PERSON_FILTER_PALAIS_ATTRIBUTE = LDAPProperties.getInstance().getLDAPProperties().getProperty("ldap.search.filter.person.palais.attribute");
	
	
}
