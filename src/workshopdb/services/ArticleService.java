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
import workshodb.entities.Article;
import workshodb.entities.Forum;
import workshopdb.utils.MyDB;

/**
 *
 * @author yahia
 */
public class ArticleService {

    Connection connexion;
    Article article=new Article();
static ArticleService instance;
    private ArticleService() {
        connexion = MyDB.getInstance().getConnection();
    }

    public void ajouterservice(Article a) throws SQLException {
        String req = " INSERT INTO `article` "
                + "(`id`,`object`, `content`) "
                + "VALUES ('"
                + a.getId() + "', '"
                + a.getObject() + "', '"
                + a.getContent()
                + "')";

        Statement stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public List<Article> getAllArticle() throws SQLException {

        List<Article> articles = new ArrayList<>();
        String req = "select * from Article";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Article f = new Article(rst.getInt("id"),
                     rst.getString("object"),
                     rst.getString("content"));
            articles.add(f);
        }
        return articles;
    }
     public  void  supprimerArticlebyID(int id) throws SQLException   {

        String req = "DELETE FROM article WHERE id=?";
        try {
            PreparedStatement pss = connexion.prepareStatement(req);

            pss.setInt(1, id);
            pss.executeUpdate();
        } catch (SQLException ex) {
        }
    }
     public void updatearticle  (int idA, String newobj )  {
        try {
            String req = "UPDATE article SET object= '"+ newobj +"' WHERE id ="+ idA;
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
        }
    }
      public static  ArticleService getInstance(){
        if(instance == null)
            instance = new ArticleService();
        return instance;
    }
    
    public Article getArticle(){
               
        return article;
    }
    public void setArticle(Article article){
        this.article = article;
    }
}
