/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author pc-asus
 */
public class Cible {
     private String Genre ;
     private   int agemin ;
     private   int agemax ;
     

     private Date dateDebutCible;
     private Date dateFinCible ;
     private   int  prix  ;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String Genre) {
        this.Genre = Genre;
    }

    public int getAgemin() {
        return agemin;
    }

    public void setAgemin(int agemin) {
        this.agemin = agemin;
    }

    public int getAgemax() {
        return agemax;
    }

    public void setAgemax(int agemax) {
        this.agemax = agemax;
    }

    public Date getDateDebutCible() {
        return dateDebutCible;
    }

    public void setDateDebutCible(Date dateDebutCible) {
        this.dateDebutCible = dateDebutCible;
    }

    public Date getDateFinCible() {
        return dateFinCible;
    }

    public void setDateFinCible(Date dateFinCible) {
        this.dateFinCible = dateFinCible;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Cible() {
    }

    public Cible(String Genre, int agemin, int agemax, Date dateDebutCible, Date dateFinCible) {
        this.Genre = Genre;
        this.agemin = agemin;
        this.agemax = agemax;
        this.dateDebutCible = dateDebutCible;
        this.dateFinCible = dateFinCible;
    }

    public Cible(String Genre, int agemin, int agemax, Date dateDebutCible, Date dateFinCible, int prix) {
        this.Genre = Genre;
        this.agemin = agemin;
        this.agemax = agemax;
        this.dateDebutCible = dateDebutCible;
        this.dateFinCible = dateFinCible;
        this.prix = prix;
    }

    public Cible(Date dateFinCible) {
        this.dateFinCible = dateFinCible;
    }

    public Cible(String Genre, int agemin, int agemax) {
        this.Genre = Genre;
        this.agemin = agemin;
        this.agemax = agemax;
        
    }
     
     
     

 

   
}
