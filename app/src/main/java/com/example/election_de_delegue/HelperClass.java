package com.example.election_de_delegue;

public class HelperClass {
    String nom_inscription, mail, mdp, mdp_confirmation;

    public String getNom_inscription() {
        return nom_inscription;
    }

    public void setNom_inscription(String nom_inscription) {
        this.nom_inscription = nom_inscription;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp_confirmation() {
        return mdp_confirmation;
    }

    public void setMdp_confirmation(String mdp_confirmation) {
        this.mdp_confirmation = mdp_confirmation;
    }

    public HelperClass(String nom_inscription, String mail, String mdp, String mdp_confirmation) {
        this.nom_inscription = nom_inscription;
        this.mail = mail;
        this.mdp = mdp;
        this.mdp_confirmation = mdp_confirmation;
    }
}
