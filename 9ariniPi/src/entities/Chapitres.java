/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author fares
 */
public class Chapitres extends Cours {

    private int chapitreID;
    private String nomCours ;
    private String videoChapitre;
    private String chapitreNom;

    public Chapitres() {
    }

    public Chapitres(String chapitreNom) {
        this.chapitreNom = chapitreNom;
    }

   /* public Chapitres(String videoChapitre) {
        this.videoChapitre = videoChapitre;
    }*/
   

    public Chapitres(int chapitreID, String nomCours , String chapitreNom, String videoChapitre) {
        this.chapitreID = chapitreID;
        this.nomCours  = nomCours ;
        this.videoChapitre = videoChapitre;
        this.chapitreNom = chapitreNom;
    }

    public Chapitres(String chapitreNom, String videoChapitre) {
        this.chapitreNom = chapitreNom;
        this.videoChapitre = videoChapitre;
    }

    public Chapitres(String nomCours , String chapitreNom, String videoChapitre) {
        this.nomCours  = nomCours ;
        this.videoChapitre = videoChapitre;
        this.chapitreNom = chapitreNom;

    }

    public String getChapitreNom() {
        return chapitreNom;
    }

    public void setChapitreNom(String chapitreNom) {
        this.chapitreNom = chapitreNom;
    }

    public int getChapitreID() {
        return chapitreID;
    }

    public void setChapitreID(int chapitreID) {
        this.chapitreID = chapitreID;
    }

    public String getNomCours () {
        return nomCours ;
    }

    public void setNomCours (String nomCours ) {
        this.nomCours  = nomCours ;
    }

    public String getVideoChapitre() {
        return videoChapitre;
    }
  public void setVideoChapitre(String videoChapitre) {
        this.videoChapitre = videoChapitre;
    }
   

    @Override
    public String toString() {
        return "Chapitres{" + "chapitreID=" + chapitreID + ", nomCours =" + nomCours  + ", videoChapitre=" + videoChapitre + ", chapitreNom=" + chapitreNom + '}';
    }

 
}
