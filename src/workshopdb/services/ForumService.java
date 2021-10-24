/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopdb.services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import workshodb.entities.Forum;
import workshopdb.utils.MyDB;
/**
 *
 * @author yahia
 */
public class ForumService {
    
    Connection connexion;
public ForumService() {
        connexion = MyDB.getInstance().getConnection();
    } 

public void ajouterforum(Forum f) throws SQLException {
        String req =  " INSERT INTO `forum` "
                + "(`utilisateurid`,`like`, `dislike`, `comment`) "
                + "VALUES ('"
                
                + f.getUtilisateurid()+ "', '" 
                + f.getLike() + "', '" 
                + f.getDislike() + "', '" 
                + f.getComment()
                  +"')";
        
        
        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
/*
public void ajouterForumPs(Forum f) throws SQLException {
        String req = "INSERT INTO `forum` (`etat `, `comment`) "
                + "VALUES ( ?, ?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, f.getEtat());
        ps.setString(2, f.getComment);
        ps.executeUpdate();
*/






public List<Forum> getAllForum() throws SQLException {

        List<Forum> forums = new ArrayList<>();
        String req = "select * from Forum";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Forum f = new Forum(rst.getInt("forumid")
                    , rst.getInt("utilisateurid")
                    , rst.getInt("like")
                    , rst.getInt("dislike")
                    , rst.getString("comment"));
            forums.add(f);
        }
        return forums;
    }

public List<Forum> deleteForum(int idF) throws SQLException{
    
    /*String req = "select forumId from Forum where forumId=idF";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);*/
       
    String req = "DELETE FROM `Forum` WHERE forumId=" + idF;
    Statement stm = connexion.createStatement();
    stm.executeUpdate(req);
    return getAllForum();
}

public List<Forum> updateComment(int idF , String newMsg) throws SQLException{
    
    /*String req = "select forumId from Forum where forumId=idF";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);*/
       
    String req = "UPDATE Forum SET comment = '"+ newMsg +"' WHERE forumId ="+ idF;
    Statement stm = connexion.createStatement();
    stm.executeUpdate(req);
    return getAllForum();
}

}
