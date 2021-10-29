/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fares
 */
public class Cours {

    private int coursID;
    private String utilisateurNom;
    private String nomCours;
    private int nbrChapitres;
    private String description;
         private String coursImg;
    private String categorieNom;
    List<Chapitres> ch;
    List<Ressources> res;   
    public Cours() {
    }
    public Cours(String utilisateurNom, String nomCours, int nbrChapitres,String description,String categorieNom) {
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
                this.description=description;

        this.categorieNom=categorieNom;
            ch  = new ArrayList<Chapitres>();
    res  = new ArrayList<Ressources>();

    }
    
    
    public Cours(String utilisateurNom, String nomCours, int nbrChapitres, String description, String coursImg, String categorieNom) {
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
        this.description = description;
        this.coursImg = coursImg;
        this.categorieNom = categorieNom;
    }

    public Cours(int coursID, String utilisateurNom, String nomCours, int nbrChapitres, String description, String coursImg, String categorieNom) {
        this.coursID = coursID;
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
        this.description = description;
        this.coursImg = coursImg;
        this.categorieNom = categorieNom;
    }

    public String getCoursImg() {
        return coursImg;
    }

    public void setCoursImg(String coursImg) {
        this.coursImg = coursImg;
    }

    public Cours(String nomCours) {
        this.nomCours = nomCours;
    }

    public Cours(int coursID, String utilisateurNom, String nomCours, int nbrChapitres,String description,String categorieNom) {
        this.coursID = coursID;
        this.utilisateurNom = utilisateurNom;
        this.nomCours = nomCours;
        this.nbrChapitres = nbrChapitres;
                this.description=description;
        this.categorieNom=categorieNom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public int getCoursID() {
        return coursID;
    }

    public void setCoursID(int coursID) {
        this.coursID = coursID;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public int getNbrChapitres() {
        return nbrChapitres;
    }

    public void setNbrChapitres(int nbrChapitres) {
        this.nbrChapitres = nbrChapitres;
    }

    @Override
    public String toString() {
        return "Cours{" + "coursID=" + coursID + ", utilisateurNom=" + utilisateurNom + ", nomCours=" + nomCours + ", nbrChapitres=" + nbrChapitres + ", description=" + description + ", categorieNom=" + categorieNom + '}';
    }

 



    

  

}
