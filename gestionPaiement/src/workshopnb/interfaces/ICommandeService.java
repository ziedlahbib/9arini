package workshopnb.interfaces;

import java.sql.SQLException;
import java.util.List;
import workshopnb.entities.Commande;
import workshopnb.entities.Membre;
import workshopnb.entities.Panier;

/**
 *
 * @author Aminous
 */
public interface ICommandeService {

    public Panier ajouterCommande(Commande cde, Membre p) throws SQLException;

    public void supprimerCommande(Commande cde) throws SQLException;

    public List<Commande> recupererCommandes(Panier pn) throws SQLException;
    
   public Commande premiereCommande(Panier pn) throws SQLException;
    
    public int nombreTotalCommandes(Panier pn) throws SQLException;
}
