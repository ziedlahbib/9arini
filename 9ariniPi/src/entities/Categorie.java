/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fares
 */
public class Categorie {
  private int categorieID;
private String categorieNom;  
private String categorieImage;
        List<Cours> css;

    public Categorie() {
    }

    public Categorie(int categorieID, String categorieNom,String categorieImage) {
        this.categorieID = categorieID;
        this.categorieNom = categorieNom;
        this.categorieImage=categorieImage;
    }

    public Categorie(String categorieNom,String categorieImage) {
        this.categorieNom = categorieNom;
                this.categorieImage=categorieImage;

            css = new ArrayList<Cours>();

    }

    public Categorie(String categorieNom) {
        this.categorieNom = categorieNom;
    }

    public String getCategorieImage() {
        return categorieImage;
    }

    public void setCategorieImage(String categorieImage) {
        this.categorieImage = categorieImage;
    }

    public Categorie(int categorieID) {
        this.categorieID = categorieID;
    }
    

    public int getCategorieID() {
        return categorieID;
    }

    public void setCategorieID(int categorieID) {
        this.categorieID = categorieID;
    }

    public String getCategorieNom() {
        return categorieNom;
    }

    public void setCategorieNom(String categorieNom) {
        this.categorieNom = categorieNom;
    }






}
