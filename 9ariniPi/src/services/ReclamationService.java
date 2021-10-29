/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Reclamation;
import DB.DB;

/**
 *
 * @author yahia
 */
public class ReclamationService {
    
    Connection connexion;
public ReclamationService() {
        connexion = DB.getInstance().getConnection();
    } 
public void ajouterReclamation(Reclamation r) throws SQLException {
        String req = "INSERT INTO `reclamation`"
            + "(`coursid`,`utilisateurid`,`formateurid`,`formationid`,`etat`, `message`,`titre`) "
            + "VALUES ( '"
            + r.getCoursid() + "', '"
            + r.getUtilisateurid() + "', '"
            + r.getFormateurid() + "', '"
            + r.getFormationid() + "', '"
            + r.getEtat() + "', '"
            + r.getMessage() + "', '"
            + r.getTitre()
            + "') ";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

/*
public void ajouterReclamationPs(Reclamation r) throws SQLException {
        String req = "INSERT INTO `personne` (`nom`, `prenom`) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, r.getNom());
        ps.setString(2, r.getPrenom());
        ps.executeUpdate();
        
        
  */      
        
public List<Reclamation> getAllReclamation() throws SQLException {

        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from Reclamation";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Reclamation r = new Reclamation(rst.getInt("reclamationid")
                    , rst.getInt("formateurid")
                    , rst.getInt("formationid")
                    , rst.getInt("coursid")
                    , rst.getInt("utilisateurid")
                    , rst.getString("etat")
                    , rst.getString("message")
                    , rst.getString("titre"));
            reclamations.add(r);
        }
        return reclamations;
    }
/*
public List<Reclamation> updateReclamation(int idR , String newMsg) throws SQLException{
    
    String req = "UPDATE reclamation SET message = '"+ newMsg +"' WHERE reclamationid ="+ idR;
    Statement stm = connexion.createStatement();
    stm.executeUpdate(req);
    return getAllReclamation();
}
*/


    public  void  supprimerReclamationbyId(int reclamationid) throws SQLException   {

        String req = "DELETE FROM reclamation WHERE reclamationid=?";
        try {
            PreparedStatement pss = connexion.prepareStatement(req);

            pss.setInt(1, reclamationid);
            pss.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    public List<Reclamation> getAllReclamationParId(int utilisateurId) throws SQLException {

        List<Reclamation> reclamations = new ArrayList<>();
        String req = "select * from Reclamation where utilisateurId="+utilisateurId;
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
           Reclamation r = new Reclamation(rst.getInt("reclamationid")
                    , rst.getInt("formateurid")
                    , rst.getInt("formationid")
                    , rst.getInt("coursid")
                    , rst.getInt("utilisateurid")
                    , rst.getString("etat")
                    , rst.getString("message")
                    , rst.getString("titre"));
            reclamations.add(r);
        }
        return reclamations;
    }
    
    
    
        public void updatereclamation  (int idR, String newMsg )  {
        try {
            String req = "UPDATE reclamation SET message = '"+ newMsg +"' WHERE reclamationid ="+ idR;
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
        }
    }
         public void supprimerReclamation( Reclamation r ) throws SQLException {

        String req = "DELETE FROM reclamation WHERE reclamationid=?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
           // css.remove(cs);

            ps.setInt(1, r.getReclamationid());
            ps.execute/*Update*/();
        } catch (SQLException ex) {
                        System.out.println(ex);

        }
    }

/*
 public void supprimerReclamation(int reclamationid) {
        try {
            String requete = "DELETE FROM reclamation WHERE id = ?";
               PreparedStatement pst = connexion.prepareStatement(requete);
            pst.setInt(1, reclamationid);//index starts with 1 for the first value
            pst.executeUpdate();
            System.out.println("reclamation deleted succesfully ! ");
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }
*/

}


   