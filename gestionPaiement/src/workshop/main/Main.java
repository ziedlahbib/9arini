package workshop.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import workshopnb.entities.Commande;
import workshopdb.services.PanierService;
import workshopdb.services.CommandeService;
import workshopdb.utils.db;
import workshopnb.entities.Membre;

/**
 *
 * @author Aminous
 */
public class Main {

    static Connection connexion;

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        connexion = db.getInstance().getConnection();
        PanierService panierService = new PanierService();
        CommandeService commandeService = new CommandeService();
        Commande cde = new Commande(800);
        Membre p = new Membre("lahbibmedzied@gmail.com", "01234", "");   
//        try {
//            Panier pn = commandeService.ajouterCommande(cde, p);
//            System.out.println("Votre article a été ajouté à votre panier. Vous avez " + commandeService.getAllCommandes(pn).size() + " commandes dans votre panier.");
//        } catch (SQLException ex) {
//            System.out.println("Une erreur s'est produite. " + ex.getMessage());
//        }
//        try {
//            Panier pn = commandeService.ajouterCommande(cde, p);
//            Panier pn2 = new Panier(sqlDate, "Payée");
//            panierService.modifierPanier(pn, pn2);
//            System.out.println("Votre panier a été modifié avec succès.");
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            Panier pn = commandeService.ajouterCommande(cde, p);
//            panierService.supprimerPanier(pn);
//            System.out.println("Votre panier a été supprimé avec succès.");
//        } catch (SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        try {
            for (Commande cdes : commandeService.recupererCommandes(panierService.recupererPanier(p))) {
                System.out.println(cdes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
