/*
 * Created on 12 janv. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package fr.citesciences.ldap;

import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 * @author Administrateur
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LDAPSearch {
	
    /**
     * permet de faire une recherche en mode par defaut.
     * Mode par default: site CSI.
     * @param criteria
     * @return : map des users.
     */
    public TreeMap searchUsersLDAP(String criteria) {
        return searchUsersLDAP(criteria, LDAPConstants.SITE_CSI);
    }
    
    /**
     * permet de faire une recherche sur le site Universcience.
     * Pour palier au problème de personne présent dans les deux AD (Palais et CSI)
     * Si une personne est trouvé dans l'ad Palais on ne poursuit pas la recherche dans l'ad Citée
     * @param criteria
     * @return : map des users.
     */
    public TreeMap searchUsersLDAPUniverscience(String criteria) {
    	
    	// recherche des gens PALAIS
    	TreeMap usersPalais = searchUsersLDAP(criteria, LDAPConstants.SITE_PALAIS);
    	if (usersPalais != null && !usersPalais.isEmpty()){
    	  //users.putAll(usersPalais);	
    	 // on ne retourne que les users palais si il existe
    		return usersPalais;
    	}else{
    	// recherche des gens CSI
    	TreeMap users = searchUsersLDAP(criteria, LDAPConstants.SITE_CSI);
    		return users;
    	}
    }

	
    /**
     * permet de faire une rercherche pour un site donne.
     * @param criteria : critere de recherche.
     * @param site     : site de recherche.
     * @return : map des users.
     */
    public TreeMap searchUsersLDAP(String criteria, final String site) {
        DirContext ctx = null;
        TreeMap userLDAPList = null;
        LDAPUser userLDAP = null;
        NamingEnumeration results =null;
        LDAPAuthentication authentication = null;
        try {
            authentication = new LDAPAuthentication();
            userLDAPList = new TreeMap();
            ctx = authentication.connectLDAP(site); // connexion anonyme.
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search(authentication.defineBaseSearch(site), criteria, constraints); // recherche de la base suivant le site.
            while (results.hasMoreElements()) {
            	StringBuilder  ldapInfosLogs = new StringBuilder(); 
            	ldapInfosLogs.append("[searchUsersLDAP - infos : ]") ;
                userLDAP = new LDAPUser();
                SearchResult result = (SearchResult) results.next();
                if(LDAPConstants.ATTRIBUT_LASTNAME != null){
                	Attribute attrSN = result.getAttributes().get(LDAPConstants.ATTRIBUT_LASTNAME);
                	if(attrSN != null){
                	  userLDAP.setNom(attrSN.get().toString());
                	}else{
                	  userLDAP.setNom("");
                	}
                }
                                
                if(LDAPConstants.ATTRIBUT_FIRSTNAME != null){
                	Attribute attr = result.getAttributes().get(LDAPConstants.ATTRIBUT_FIRSTNAME);
                	if(attr != null){
                		userLDAP.setPrenom(attr.get().toString());
                	}else{
                		userLDAP.setPrenom("");
                	}
                }
                
                if(LDAPConstants.ATTRIBUT_EMAIL != null){
                	Attribute attr = result.getAttributes().get(LDAPConstants.ATTRIBUT_EMAIL);
                	if(attr != null){
                		userLDAP.setEmail(attr.get().toString());
                	}else{
                		userLDAP.setEmail("");
                	}
                }
                
                if(LDAPConstants.ATTRIBUT_TEL != null){
                	Attribute attr = result.getAttributes().get(LDAPConstants.ATTRIBUT_TEL);
                	if(attr != null){
                		userLDAP.setTel(attr.get().toString());
                	}else{
                		userLDAP.setTel("");
                	}
                }
                
                // prise en compte d'un login specifique selon le site
                if (site != null && LDAPConstants.SITE_PALAIS.equals(site)) {
                  if (LDAPConstants.ATTRIBUT_LOGIN_PALAIS != null && !"".equals(LDAPConstants.ATTRIBUT_LOGIN_PALAIS)) {
                    Attribute login = result.getAttributes().get(LDAPConstants.ATTRIBUT_LOGIN_PALAIS);
                    if (login != null) {
                	  userLDAP.setLogin(login.get(0).toString());
                    }
                    else {
                	  userLDAP.setLogin("undefined");
                    }
                  }
                } else {
                  String login = this.getLoginFromDN(result.getName());	
                  userLDAP.setLogin(login);
                }
                
                userLDAPList.put(userLDAP.getLogin(), userLDAP);
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e.toString());
            e.printStackTrace();
        } finally {
            try {
                if (null != ctx)
                    ctx.close();
            } catch (Exception e2) {
                System.out.println("Exception : " + e2.toString());
                e2.printStackTrace();
            }
            if(results!=null){
            	try{
            	results.close();
            	}
             catch (Exception ignoreException) {}
            }
        }
        return userLDAPList;
    }
    
    /**
     * permet d'extraire le login du dn.
     * @param dn
     * @return
     */
    private String getLoginFromDN(String dn) {
        String login = "";
        String token = "";
        try {
            StringTokenizer tokenizer = new StringTokenizer(dn, ",");
            while (tokenizer.hasMoreElements()) {
                token = tokenizer.nextToken();
                String name = new String(token);
                if (token.toLowerCase().startsWith("cn=")) {
                    login = name.substring(3);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }
        
}
