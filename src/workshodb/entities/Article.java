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
public class Article {
    private int id;
    private String object;
    private String content;

    public Article(int id, String object, String content) {
        this.id = id;
        this.object = object;
        this.content = content;
    }

    public Article(String object, String content) {
        this.object = object;
        this.content = content;
    }

    public Article() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", object=" + object + ", content=" + content + '}';
    }
    
    
    
}
