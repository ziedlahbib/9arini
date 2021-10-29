package workshopnb.interfaces;

/**
 *
 * @author fares
 */
import java.sql.SQLException;

import workshopnb.entities.Commande;
import workshopnb.entities.Cours;

public interface ICoursService {

    public Cours rechercherCours(Commande cde) throws SQLException;

}
