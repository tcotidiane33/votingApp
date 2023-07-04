package com.example.election_de_delegue;

public class HlpherClassVote {
    String nom_inscription, mail, nom_candidat;
    Integer Vote=1;

    public String getNom_inscription() {
        return nom_inscription;
    }

    public void setNom_inscription(String nom_inscription) {
        this.nom_inscription = nom_inscription;
    }

    public String getNom_candidat() {
        return nom_candidat;
    }

    public void setNom_candidat(String nom_candidat) {
        this.nom_candidat = nom_candidat;
    }

    public String getmail() {
        return mail;
    }

    public void setmail(String mail) {
        this.mail = mail;
    }

    public Integer getVote() {
        return Vote;
    }

    public void setVote(Integer Vote) {
        this.Vote = Vote;
    }


    public HlpherClassVote(String nom_inscription, String mail, Integer Vote,String nom_candidat) {
        this.nom_inscription = nom_inscription;
        this.nom_candidat=nom_candidat;
        this.mail = mail;
        this.Vote = Vote;
    }
}
