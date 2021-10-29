package workshopnb.entities;

/**
 *
 * @author lahbib
 */
public abstract class Utilisateur {

    private int utilisateurID;
    private String utilisateurPdp, utilisateurNom, utilisateurPrenom, utilisateurAddressEmail, utilisateurMDP;

    public Utilisateur() {
    }
    
    public Utilisateur(String utilisateurPdp, String utilisateurNom, String utilisateurPrenom) {
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public Utilisateur(int utilisateurID, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom) {
        this.utilisateurID = utilisateurID;
        this.utilisateurPdp = utilisateurPdp;
        this.utilisateurNom = utilisateurNom;
        this.utilisateurPrenom = utilisateurPrenom;
    }
    
    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }
        
    public String getUtilisateurPdp() {
        return utilisateurPdp;
    }

    public void setUtilisateurPdp(String utilisateurPdp) {
        this.utilisateurPdp = utilisateurPdp;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public String getUtilisateurPrenom() {
        return utilisateurPrenom;
    }

    public void setUtilisateurPrenom(String utilisateurPrenom) {
        this.utilisateurPrenom = utilisateurPrenom;
    }

    public String getUtilisateurAddressEmail() {
        return utilisateurAddressEmail;
    }

    public void setUtilisateurAddressEmail(String utilisateurAddressEmail) {
        this.utilisateurAddressEmail = utilisateurAddressEmail;
    }

    public String getUtilisateurMDP() {
        return utilisateurMDP;
    }

    public void setUtilisateurMDP(String utilisateurMDP) {
        this.utilisateurMDP = utilisateurMDP;
    }
}
