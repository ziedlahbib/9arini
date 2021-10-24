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
public class Forum {
    private int forumid ,utilisateurid , like , dislike ;
    private String comment ; 

    public Forum(int utilisateurid, int like, int dislike, String comment) {
        this.utilisateurid = utilisateurid;
        this.like = like;
        this.dislike = dislike;
        this.comment = comment;
    }

    public Forum(int like, int dislike, String comment) {
        this.like = like;
        this.dislike = dislike;
        this.comment = comment;
    }

    
    public Forum(int forumid, int utilisateurid, int like, int dislike, String comment) {
        this.forumid = forumid;
        this.utilisateurid = utilisateurid;
        this.like = like;
        this.dislike = dislike;
        this.comment = comment;
    }

    public Forum() {
    }

    public void setForumid(int forumid) {
        this.forumid = forumid;
    }

    public void setUtilisateurid(int utilisateurid) {
        this.utilisateurid = utilisateurid;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getForumid() {
        return forumid;
    }

    public int getUtilisateurid() {
        return utilisateurid;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "forum{" + "forumid=" + forumid + ", utilisateurid=" + utilisateurid + ", like=" + like + ", dislike=" + dislike + ", comment=" + comment + '}';
    }
}
