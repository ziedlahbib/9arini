/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entities.Article;
import entities.Comment;
import DB.DB;

/**
 *
 * @author yahia
 */
public class CommentService {

    Connection connexion;

    public CommentService() {
        connexion = DB.getInstance().getConnection();
    }

  /*  public void ajouterComment(Comment c) throws SQLException {
        String req = " INSERT INTO `comment` "
                + "(`id`,`utilisateurID`,`articleId`, `content`) "
                + "VALUES ('"
                + c.getId() + "', '"
                + c.getUtilisateurID()+ "', '"
                + c.getArticleId()+ "', '"
                + c.getContent()
                
                + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    */
    
    public void ajouterCommentparid(Comment c) throws SQLException {
      
        String req = " INSERT INTO `comment` "
                + "(`articleId`, `content`,`utilisateurID`,`userName`) "
                + "VALUES ('"
                       

                + c.getArticleId()+ "', '"
                + c.getContent()+"', '"

                + c.getUtilisateurID()+ "', '"
                + c.getUtilisateurNom()
                + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    /*
    public List<Comment> getAllComment() throws SQLException {

        List<Comment> comments = new ArrayList<>();
        String req = "select * from Comment";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Comment c = new Comment(rst.getInt("id"),
                     rst.getInt("utilisateurID") ,
                     rst.getString("content"));
            comments.add(c);
        }
        return comments;
    }
*/
    
    
    
    
    
    public List<Comment> getAllCommentForArticleId(int articleId) throws SQLException {

        List<Comment> comments = new ArrayList<>();
        //String req = "select * from Comment where articleId="+articleId;
        String req = "SELECT * FROM comment c JOIN utilisateur u ON c.utilisateurID=u.utilisateurID WHERE articleID="+articleId ;
 
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Comment c = new Comment(rst.getInt("id"),
                   
                    rst.getString("content"),
                   rst.getInt("utilisateurID"),
                   rst.getString("userName") );
            comments.add(c);
        }
        return comments;
    }
    
    
}
