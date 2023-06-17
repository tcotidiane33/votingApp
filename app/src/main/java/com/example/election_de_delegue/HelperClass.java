package com.example.election_de_delegue;

public class HelperClass {
    String nom_inscription, prenoms_inscription, mdp, mdp_confirmation;

    public String getNom_inscription() {
        return nom_inscription;
    }

    public void setNom_inscription(String nom_inscription) {
        this.nom_inscription = nom_inscription;
    }

    public String getPrenoms_inscription() {
        return prenoms_inscription;
    }

    public void setPrenoms_inscription(String prenoms_inscription) {
        this.prenoms_inscription = prenoms_inscription;
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

    public HelperClass(String nom_inscription, String prenoms_inscription, String mdp, String mdp_confirmation) {
        this.nom_inscription = nom_inscription;
        this.prenoms_inscription = prenoms_inscription;
        this.mdp = mdp;
        this.mdp_confirmation = mdp_confirmation;
    }
}
