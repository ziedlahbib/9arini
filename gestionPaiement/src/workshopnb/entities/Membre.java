package workshopnb.entities;

/**
 *
 * @author lahbib
 */
public class Membre extends Utilisateur {

    public Membre() {
    }
    
    public Membre(String utilisateurPdp, String utilisateurNom, String utilisateurPrenom) {
        super(utilisateurPdp, utilisateurNom, utilisateurPrenom);
    }

    public Membre(int utilisateurID, String utilisateurPdp, String utilisateurNom, String utilisateurPrenom) {
        super(utilisateurID, utilisateurPdp, utilisateurNom, utilisateurPrenom);
    }
}
