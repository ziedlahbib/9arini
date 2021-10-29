package workshopnb.interfaces;

import java.sql.SQLException;
import workshopnb.entities.Panier;
import workshopnb.entities.Utilisateur;

/**
 *
 * @author Aminous
 */
public interface IPanierService {

    public int ajouterPanier(Panier pn) throws SQLException;

    public void modifierPanier(Panier pn, Panier pn2) throws SQLException;

    public void supprimerPanier(Panier pn) throws SQLException;

    public Panier recupererPanier(Utilisateur p) throws SQLException;

}
