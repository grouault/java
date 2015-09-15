package fr.citesciences.ldap;

import java.io.IOException;

import javax.naming.NamingException;

public class LDAPTest {

    public static void main(String args[]) throws NamingException, IOException {
        
        /**
         * Connection s�curis�e LDAP
         */
        
        //LDAPAuthentication authentication = new LDAPAuthentication();
        //System.out.println("--> "+authentication.getDNFromLogin("Marc1"));
        
        LDAPAuthentication authentication = new LDAPAuthentication();
        if (authentication.secureAuthentication("Astuto", "ldap1"))
            System.out.println("connection OK");
        else
            System.out.println("connection KO");
        
        /**
         * Connection anonyme LDAP + recherche
         */
//        LDAPSearch search = new LDAPSearch();
//        String testCritere = "";
//        String testValue = "S";
//        if (testValue.equalsIgnoreCase("") == false)
//            testCritere = "(&("+LDAPConstants.SEARCH_PERSON_FILTER+")("+LDAPConstants.SEARCH_PERSON_FILTER_ATTRIBUTE+"="+testValue+"*))";
//        else
//            testCritere = LDAPConstants.SEARCH_PERSON_FILTER;
//        TreeMap userLDAPList = search.searchUsersLDAP(testCritere);
//        System.out.println("Nb utilisateurs : " + userLDAPList.size());
//        Set set = userLDAPList.entrySet();
//        for (Iterator iter = set.iterator(); iter.hasNext();) {
//        	Entry entry = (Entry)iter.next();
//        	LDAPUser user = (LDAPUser)entry.getValue();
//            System.out.println("");
//            System.out.println("Login : "+user.getLogin());
//            System.out.println("Nom : " + user.getNom());
//            System.out.println("Pr�nom : " + user.getPrenom());
//            System.out.println("Email : " + user.getEmail());
//        }
        //ldapRunner.searchSpider();
    }
}