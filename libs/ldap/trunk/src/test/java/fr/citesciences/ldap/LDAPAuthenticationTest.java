package fr.citesciences.ldap;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import junit.framework.TestCase;

public class LDAPAuthenticationTest extends TestCase {

	
	private LDAPAuthentication ldapAuthentication;
	private DirContext ctx =null;
	protected void setUp() throws Exception {
		
		super.setUp();
		ldapAuthentication = new LDAPAuthentication();
		
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
		if(ctx!=null){
			ctx.close();
		}
			
	}

	/**
	 * Test de connexion anonyme par default.
	 */
	public void testConnectLDAP(){
		try {
		  ctx=ldapAuthentication.connectLDAP();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		assertNotNull(ctx);		
	}

	
	/**
	 * Test de connexion anonyme CITE.
	 */
	public void  testConnectLDAPCsi(){
		try {
			ctx = ldapAuthentication.connectLDAP(LDAPConstants.SITE_CSI);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(ctx);
	}
	
	/**
	 * Test de connexion anonyme PALAIS.
	 */
	public void  testConnectLDAPPalais(){
		try {
			ctx =ldapAuthentication.connectLDAP(LDAPConstants.SITE_PALAIS);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(ctx);
	}

	/**
	 * Test de connexion securisee.
	 */
	public void testSecureAuthentication(){
		// boolean connected = ldapAuthentication.secureAuthentication("TestDep", "TestDep");
		boolean connected = ldapAuthentication.secureAuthentication("SIFP-Applis", "Appl1c@t1on");
		assertTrue(connected);
	}		

	/**
	 * Test de connexion securisee CSI.
	 */
	public void testSecureAuthenticationCSI(){
		// mode normal : faire la conf adequat.
		// boolean connected = ldapAuthentication.secureAuthentication("testdep", "DepTest00", LDAPConstants.SITE_CSI);
		boolean connected = ldapAuthentication.secureAuthentication("SIFP-Applis", "Appl1c@t1on", LDAPConstants.SITE_CSI);
		
		// mode ssl : faire la conf adequat.
		// boolean connected = ldapAuthentication.secureAuthentication("TestDep", "TestDep", LDAPConstants.SITE_CSI);
		assertTrue(connected);
	}		
	
	
	
	/**
	 * Test de connexion securise PALAIS.
	 */
	public void testSecureAuthenticationPalais(){
		// boolean connected = ldapAuthentication.secureAuthentication("Bellamy Y.", "Simple00", LDAPConstants.SITE_PALAIS);
		boolean connected = ldapAuthentication.secureAuthentication("dtest", "Simple00", LDAPConstants.SITE_PALAIS);
		assertTrue(connected);
	}
	
	/**
	 * Test de connexion au site UNIVERSCIENCE.
	 */
	public void testSecureAuthenticationUniverscience(){	
		// boolean connected=ldapAuthentication.secureAuthenticationUniverscience("TestDep", "TestDep");
		boolean connected=ldapAuthentication.secureAuthenticationUniverscience("Test D.", "Simple00");
		assertTrue(connected);
	}
	
	

}
