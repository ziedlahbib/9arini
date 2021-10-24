/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopdb.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import workshodb.entities.Article;
import workshodb.entities.Comment;
import workshopdb.utils.MyDB;

/**
 *
 * @author yahia
 */
public class CommentService {

    Connection connexion;

    public CommentService() {
        connexion = MyDB.getInstance().getConnection();
    }

    public void ajouterComment(Comment c) throws SQLException {
        String req = " INSERT INTO `comment` "
                + "(`id`,`articleId`, `content`) "
                + "VALUES ('"
                + c.getId() + "', '"
                + c.getArticleId()+ "', '"
                + c.getContent()
                
                + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    
    public void ajouterCommentparid(Comment c) throws SQLException {
      
        String req = " INSERT INTO `comment` "
                + "(`articleId`, `content`) "
                + "VALUES ('"
                       

                + c.getArticleId()+ "', '"
                + c.getContent()
                + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    public List<Comment> getAllComment() throws SQLException {

        List<Comment> comments = new ArrayList<>();
        String req = "select * from Comment";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Comment c = new Comment(rst.getInt("id"),
                    rst.getString("content"));
            comments.add(c);
        }
        return comments;
    }
    
    
    
    
    
    public List<Comment> getAllCommentForArticleId(int articleId) throws SQLException {

        List<Comment> comments = new ArrayList<>();
        String req = "select * from Comment where articleId="+articleId;
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Comment c = new Comment(rst.getInt("id"),
                    rst.getString("content"));
            comments.add(c);
        }
        return comments;
    }
    
    
}
