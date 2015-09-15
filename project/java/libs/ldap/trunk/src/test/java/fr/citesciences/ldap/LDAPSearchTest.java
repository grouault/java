package fr.citesciences.ldap;

import java.util.Collection;
import java.util.TreeMap;

import junit.framework.TestCase;

public class LDAPSearchTest extends TestCase {

	LDAPSearch search = null;
	
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		search = new LDAPSearch();
	}
		
	/**
	 * TEST de recherche par default : Site CSI.
	 *  - user : nataf.
	 */
	public void testSearchUsersLDAP(){	
	  String critere =  "(&(" + LDAPConstants.SEARCH_PERSON_FILTER + ")("
	  + LDAPConstants.SEARCH_PERSON_FILTER_ATTRIBUTE + "="
	  //+ "nataf" + "*))";
	  //+ "testdep" + "*))"; // recherche FARADAY
	  + "Mandat" + "*))"; // recherhe FARADAY OK
	  TreeMap users = search.searchUsersLDAP(critere); 
	  assertTrue(!users.isEmpty());
	}
	
    /**
     * TEST de recherche sur le site PALAIS.
     * Recherche se fait sur l'attribut sAMAccountName.
     */
	public void testSearchUsersLDAPPalais(){
	  String critere =  "(&(" + LDAPConstants.SEARCH_PERSON_FILTER + ")("
	    + LDAPConstants.SEARCH_PERSON_FILTER_PALAIS_ATTRIBUTE + "="
		+ "dtest" + "*))";
	  TreeMap users = search.searchUsersLDAP(critere, LDAPConstants.SITE_PALAIS);
	  assertTrue(!users.isEmpty());
	}
	
    /**
     * TEST de recherche sur le site UNIVERSCIENCE.
     */
	public void testSearchUsersLDAPUniverscience(){
	  String critere =  "(&(" + LDAPConstants.SEARCH_PERSON_FILTER + ")("
		+ LDAPConstants.SEARCH_PERSON_FILTER_ATTRIBUTE + "="
		+ "Test" + "*))";
	  TreeMap users = search.searchUsersLDAPUniverscience(critere);
	  assertTrue(!users.isEmpty());
	}
	
	/*
	public Collection getUtilisateursLDAP(String critere) {
		TreeMap utilisateurLDAPList = null;
		LDAPSearch search = null;
		try {
			search = new LDAPSearch();
			if (critere.equalsIgnoreCase("") == false){
				critere = "(&(" + LDAPConstants.SEARCH_PERSON_FILTER + ")("
						+ LDAPConstants.SEARCH_PERSON_FILTER_ATTRIBUTE + "="
						+ critere + "*))";
			}else{
				critere = "objectClass=Person";
			}
			utilisateurLDAPList = search.searchUsersLDAP(critere);
		} catch (Exception e) {
			
		}
		return utilisateurLDAPList.values();
	}
	*/	
	
}
