package fr.exagone.bd.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.StringJoiner;

public class UtilisateurEntity {

    private Integer id;
    private String login;
    private String nom;
    private String prenom;
    private String pwd;
    private String adresse;
    private String telephone;
    private Integer codePostal;
    private java.sql.Date dateDeNaissance;
    private ESex sex;
    private Timestamp derniereConnection;

    public Integer getId() {
        return id;
    }

    public UtilisateurEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UtilisateurEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public UtilisateurEntity setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public UtilisateurEntity setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public String getPwd() {
        return pwd;
    }

    public UtilisateurEntity setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public String getAdresse() {
        return adresse;
    }

    public UtilisateurEntity setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public UtilisateurEntity setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Integer getCodePostal() {
        return codePostal;
    }

    public UtilisateurEntity setCodePostal(Integer codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public UtilisateurEntity setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        return this;
    }

    public ESex getSex() {
        return sex;
    }

    public UtilisateurEntity setSex(ESex sex) {
        this.sex = sex;
        return this;
    }

    public Timestamp getDerniereConnection() {
        return derniereConnection;
    }

    public UtilisateurEntity setDerniereConnection(Timestamp derniereConnection) {
        this.derniereConnection = derniereConnection;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UtilisateurEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nom='" + nom + "'")
                .add("prenom='" + prenom + "'")
                .add("pwd='" + pwd + "'")
                .add("adresse='" + adresse + "'")
                .add("telephone='" + telephone + "'")
                .add("codePostal=" + codePostal)
                .add("dateDeNaissance=" + dateDeNaissance)
                .add("sex=" + sex)
                .add("derniereConnection=" + derniereConnection)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtilisateurEntity that = (UtilisateurEntity) o;
        if (this.getId() != null && that.getId() != null) {
            return Objects.equals(this.getId(), that.getId());
        }
        return Objects.equals(id, that.id)
                && Objects.equals(login, that.login)
                && Objects.equals(nom, that.nom)
                && Objects.equals(prenom, that.prenom)
                && Objects.equals(pwd, that.pwd)
                && Objects.equals(adresse, that.adresse)
                && Objects.equals(telephone, that.telephone)
                && Objects.equals(codePostal, that.codePostal)
                && Objects.equals(dateDeNaissance, that.dateDeNaissance)
                && sex == that.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, nom, prenom, pwd, adresse, telephone, codePostal, dateDeNaissance, sex, derniereConnection);
    }

}
