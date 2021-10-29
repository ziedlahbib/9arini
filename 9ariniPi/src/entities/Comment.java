/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author yahia
 */
public class Comment {

    
    private int id,articleId , utilisateurID;
    private String content;
        private String utilisateurNom;


    public int getUtilisateurID() {
        return utilisateurID;
    }

    public void setUtilisateurID(int utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public Comment(int articleId, String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public Comment(int articleId,  String content,int utilisateurID, String utilisateurNom) {
        this.articleId = articleId;
        this.utilisateurID = utilisateurID;
        this.content = content;
        this.utilisateurNom = utilisateurNom;
    }

 

    

   
    
    
    

    public Comment(){};

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUtilisateurNom() {
        return utilisateurNom;
    }

    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }

    public Comment(int id, int articleId, int utilisateurID, String content) {
        this.id = id;
        this.articleId = articleId;
        this.utilisateurID = utilisateurID;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", articleId=" + articleId + ", utilisateurID=" + utilisateurID + ", content=" + content + ", utilisateurNom=" + utilisateurNom + '}';
    }

    

   
    
}
