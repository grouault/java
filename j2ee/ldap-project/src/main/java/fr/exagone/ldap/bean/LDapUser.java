package fr.exagone.ldap.bean;

import javax.naming.Name;

import org.apache.commons.lang.builder.ToStringBuilder;

public class LDapUser {

    private Name dn = null;
    private String nom = null;
    private String prenom = null;
    private String nomComplet = null;
    private String matricule = null;
    private String mail = null;
    private String userPassword = null;

    private String organisationParente = null;
    
    public Name getDn() {
        return dn;
    }
    public void setDn(Name dnSet) {
        dn = dnSet;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nomToSet) {
        nom = nomToSet;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenomToSet) {
        prenom = prenomToSet;
    }
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matriculeToSet) {
        matricule = matriculeToSet;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mailToSet) {
        mail = mailToSet;
    }
    public String getOrganisationParente() {
        return organisationParente;
    }
    public void setOrganisationParente(String organisationParente) {
        this.organisationParente = organisationParente;
    }
    public String getNomComplet() {
        return nomComplet;
    }
    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}
