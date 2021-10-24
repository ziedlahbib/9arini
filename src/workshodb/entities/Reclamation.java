/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshodb.entities;

/**
 *
 * @author yahia
 */
public class Reclamation {

    private int reclamationid, coursid, utilisateurid, formateurid, formationid;
    private String etat, message, titre;

    public Reclamation(int reclamationid, int coursid, int utilisateurid, int formateurid, int formationid, String etat, String message, String titre) {
        this.reclamationid = reclamationid;
        this.coursid = coursid;
        this.utilisateurid = utilisateurid;
        this.formateurid = formateurid;
        this.formationid = formationid;
        this.etat = etat;
        this.message = message;
        this.titre = titre;
    }

    /* public Reclamation(int coursid, int utilisateurid, int formateurid, int formationid, String etat, String message) {
        this.coursid = coursid;
        this.utilisateurid = utilisateurid;
        this.formateurid = formateurid;
        this.formationid = formationid;
        this.etat = etat;
        this.message = message;
    }
     */
    public Reclamation() {
    }

    public Reclamation(int coursid, int utilisateurid, int formateurid, int formationid, String etat, String message, String titre) {
        this.coursid = coursid;
        this.utilisateurid = utilisateurid;
        this.formateurid = formateurid;
        this.formationid = formationid;
        this.etat = etat;
        this.message = message;
        this.titre = titre;
    }

    public Reclamation(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reclamation(String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setReclamationid(int reclamationid) {
        this.reclamationid = reclamationid;
    }

    public void setCoursid(int coursid) {
        this.coursid = coursid;
    }

    public void setUtilisateurid(int utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public void setFormateurid(int formateurid) {
        this.formateurid = formateurid;
    }

    public void setFormationid(int formationid) {
        this.formationid = formationid;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReclamationid() {
        return reclamationid;
    }

    public int getCoursid() {
        return coursid;
    }

    public int getUtilisateurid() {
        return utilisateurid;
    }

    public int getFormateurid() {
        return formateurid;
    }

    public int getFormationid() {
        return formationid;
    }

    public String getEtat() {
        return etat;
    }

    public String getMessage() {
        return message;
    }

    public String getTitre() {
        return titre;

    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    /*@Override
    public String toString() {
        return "Reclamation{" + "reclamationid=" + reclamationid + ", coursid=" + coursid + ", utilisateurid=" + utilisateurid + ", formateurid=" + formateurid + ", formationid=" + formationid + ", etat=" + etat + ", message=" + message + ", titre" + titre'}';
    }*/

    @Override
    public String toString() {
        return "Reclamation{" + "reclamationid=" + reclamationid + ", coursid=" + coursid + ", utilisateurid=" + utilisateurid + ", formateurid=" + formateurid + ", formationid=" + formationid + ", etat=" + etat + ", message=" + message + ", titre=" + titre + '}';
    }
    
    
}
