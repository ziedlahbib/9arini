package workshopnb.entities;

/**
 *
 * @author fares
 */
public class Cours {

    private int coursID, prixCours;
    private String nomCours;

    public Cours() {
    }

    public Cours(String nomCours, int prixCours) {
        this.nomCours = nomCours;
        this.prixCours = prixCours;
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

    public int getPrixCours() {
        return prixCours;
    }

    public void setPrixCours(int prixCours) {
        this.prixCours = prixCours;
    }

}
