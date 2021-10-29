package workshopdb.services;

import workshopnb.entities.Cours;
import workshopdb.utils.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import workshopnb.entities.Commande;
import workshopnb.interfaces.ICoursService;

/**
 *
 * @author fares
 */
public class CoursService implements ICoursService {

    Connection connexion;

    public CoursService() {
        connexion = db.getInstance().getConnection();
    }

    @Override
    public Cours rechercherCours(Commande cde) throws SQLException {
        String req = "SELECT * FROM cours WHERE coursID= " + cde.getCoursID();
        Statement stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery(req);
        Cours c = new Cours();
        if (rs.next()) {
            c = new Cours(
                    rs.getString("nomCours"),
                    rs.getInt("prixCours")
            );
        }
        return c;
    }
}
