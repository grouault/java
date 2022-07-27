package fr.exagone.bd.entity;

import java.util.StringJoiner;

public class CompteEntity {

    private Integer id;
    private String libelle;
    private Integer solde;
    private Integer utilisateurId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getSolde() {
        return solde;
    }

    public void setSolde(Integer solde) {
        this.solde = solde;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CompteEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("libelle='" + libelle + "'")
                .add("solde=" + solde)
                .add("utilisateurId=" + utilisateurId)
                .toString();
    }

}
