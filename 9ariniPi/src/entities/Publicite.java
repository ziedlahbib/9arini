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
public class Publicite {
   private int publiciteID ;
   private String videoPublicite ;
   private String genre ;
   private int ageMax ;
   private int ageMin ;
   private int utilisateurID ;
   private Date dateFinPublicite ;
   private Date dateDebutPublicite  ;
   private String lienPublicite  ;
 
   private int nbrClick  ;
   private int nbrAffichage;
   private String imagePublicite ;

    

    public int getPubliciteID() {
        return publiciteID;
    }

    public void setPubliciteID(int publiciteID) {
        this.publiciteID = publiciteID;
    }

    public String getVideoPublicite() {
        return videoPublicite;
    }

    public void setVideoPublicite(String videoPublicite) {
        this.videoPublicite = videoPublicite;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public Date getDateFinPublicite() {
        return dateFinPublicite;
    }

    public void setDateFinPublicite(Date dateFinPublicite) {
        this.dateFinPublicite = dateFinPublicite;
    }

    public Date getDateDebutPublicite() {
        return dateDebutPublicite;
    }

    public void setDateDebutPublicite(Date dateDebutPublicite) {
        this.dateDebutPublicite = dateDebutPublicite;
    }

    public String getLienPublicite() {
        return lienPublicite;
    }

    public void setLienPublicite(String lienPublicite) {
        this.lienPublicite = lienPublicite;
    }

    public int getNbrClick() {
        return nbrClick;
    }

    public void setNbrClick(int nbrClick) {
        this.nbrClick = nbrClick;
    }

    public int getNbrAffichage() {
        return nbrAffichage;
    }

    public void setNbrAffichage(int nbrAffichage) {
        this.nbrAffichage = nbrAffichage;
    }

    public String getImagePublicite() {
        return imagePublicite;
    }

    public void setImagePublicite(String imagePublicite) {
        this.imagePublicite = imagePublicite;
    }

    public Publicite() {
    }

   

   
    ///////////////////////////////////

    public Publicite(String videoPublicite, String lienPublicite, String imagePublicite) {
        this.videoPublicite = videoPublicite;
        this.lienPublicite = lienPublicite;
        this.imagePublicite = imagePublicite;
    }

    public Publicite(String videoPublicite) {
        this.videoPublicite = videoPublicite;
    }

    public Publicite(int publiciteID, String videoPublicite, String genre, int ageMax, int ageMin, int utilisateurID, Date dateFinPublicite, Date dateDebutPublicite, String lienPublicite, int nbrClick, int nbrAffichage, String imagePublicite) {
        this.publiciteID = publiciteID;
        this.videoPublicite = videoPublicite;
        this.genre = genre;
        this.ageMax = ageMax;
        this.ageMin = ageMin;
        this.utilisateurID = utilisateurID;
        this.dateFinPublicite = dateFinPublicite;
        this.dateDebutPublicite = dateDebutPublicite;
        this.lienPublicite = lienPublicite;
        this.nbrClick = nbrClick;
        this.nbrAffichage = nbrAffichage;
        this.imagePublicite = imagePublicite;
    }

    public Publicite(String videoPublicite, String genre, int ageMax, int ageMin, int utilisateurID, Date dateFinPublicite, Date dateDebutPublicite, String lienPublicite, int nbrClick, int nbrAffichage, String imagePublicite) {
        this.videoPublicite = videoPublicite;
        this.genre = genre;
        this.ageMax = ageMax;
        this.ageMin = ageMin;
        this.utilisateurID = utilisateurID;
        this.dateFinPublicite = dateFinPublicite;
        this.dateDebutPublicite = dateDebutPublicite;
        this.lienPublicite = lienPublicite;
        this.nbrClick = nbrClick;
        this.nbrAffichage = nbrAffichage;
        this.imagePublicite = imagePublicite;
    }

    public Publicite(int publiciteID, String genre, int ageMax, int ageMin, int utilisateurID, Date dateFinPublicite, Date dateDebutPublicite, int nbrClick, int nbrAffichage) {
        this.publiciteID = publiciteID;
        this.genre = genre;
        this.ageMax = ageMax;
        this.ageMin = ageMin;
        this.utilisateurID = utilisateurID;
        this.dateFinPublicite = dateFinPublicite;
        this.dateDebutPublicite = dateDebutPublicite;
        this.nbrClick = nbrClick;
        this.nbrAffichage = nbrAffichage;
    }

    public Publicite(String videoPublicite, String genre, int ageMax, int ageMin, Date dateFinPublicite, Date dateDebutPublicite, String lienPublicite, String imagePublicite) {
        this.videoPublicite = videoPublicite;
        this.genre = genre;
        this.ageMax = ageMax;
        this.ageMin = ageMin;
        this.dateFinPublicite = dateFinPublicite;
        this.dateDebutPublicite = dateDebutPublicite;
        this.lienPublicite = lienPublicite;
        this.imagePublicite = imagePublicite;
    }
    

    @Override
    public String toString() {
        return "Publicite{" + "videoPublicite=" + videoPublicite + ", lienPublicite=" + lienPublicite + ", imagePublicite=" + imagePublicite + '}';
    }

   
    
    
}
