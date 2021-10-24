/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshodb.entities;

/**
 *
 * @author yahia
 */
public class Comment {
    private int id,articleId;
    private String content;

    public Comment(int id, String content, int articleId) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
    }
    
    public Comment(int articleId , String content) {
        this.articleId = articleId;
        this.content = content;
    }

    public Comment(){};

    public Comment(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
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

    
    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", content=" + content + '}';
    }
    
    
    
}
