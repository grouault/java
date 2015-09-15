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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class LDAPUser {

    private String login;

    private String nom;

    private String prenom;

    private String email;
    
    private String tel;

    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Returns the nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom
     *            The nom to set.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return Returns the prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom
     *            The prenom to set.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    /**
     * @return Returns the login.
     */
    public String getLogin() {
        return login;
    }
    
    /**
     * @param login The login to set.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return returns the Tel
     */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel The tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
}
