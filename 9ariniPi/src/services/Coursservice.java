/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cours;
import DB.DB;
import IService.IServiceCours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fares
 */
public class Coursservice implements IServiceCours {

    Connection connexion;
        
      public Coursservice() {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterProduitCours(Cours c) throws SQLException {
        String req = "INSERT INTO `cours` (`utilisateurNom`,`nomCours`,`nbrChapitres`,`description`,`coursImg`,`categorieNom`) "
                + "VALUES (?,?,?,?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, c.getUtilisateurNom());
        ps.setString(2, c.getNomCours());
        ps.setInt(3, c.getNbrChapitres());
        ps.setString(4, c.getDescription());
         ps.setString(5, c.getCoursImg());
        ps.setString(6, c.getCategorieNom());

        ps.executeUpdate();
    }

    @Override
    public List<Cours> getAllCours() throws SQLException {
        List<Cours> Courss = new ArrayList<>();
        String req = "select * from cours";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        while (rst.next()) {
            Cours c = new Cours(rst.getInt("coursID"),
                    rst.getString("utilisateurNom"),
                    rst.getString("nomCours"),
                    rst.getInt("nbrChapitres"),
                    rst.getString("description"),
                    rst.getString("coursImg"),

                    rst.getString("categorieNom"));
            Courss.add(c);
        }
        return Courss;
    }

    @Override
    public List<Cours> afficherCours() throws SQLException {
        List<Cours> list = new ArrayList();
        String requetee = "SELECT * FROM cours ";
        PreparedStatement pst = connexion.prepareStatement(requetee);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Cours c = new Cours();
            c.setCoursID(rs.getInt(1));
            c.setUtilisateurNom(rs.getString(2));
            c.setNomCours(rs.getString(3));
            c.setNbrChapitres(rs.getInt(4));
            c.setDescription(rs.getString(5));
                        c.setCoursImg(rs.getString(6));

            c.setCategorieNom(rs.getString(7));
            list.add(c);
        }
        return list;
    }
    @Override
    public void supprimerCours(Cours c) throws SQLException {

        String req = "DELETE FROM cours WHERE CoursID =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setInt(1, c.getCoursID());
            ps.execute();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void ModifierCours(Cours c) {
        try {
            String req = "UPDATE cours SET "
                    + " utilisateurNom='" + c.getUtilisateurNom() + "'"
                    + ", nomCours='" + c.getNomCours() + "'"
                    + ", nbrChapitres='" + c.getNbrChapitres() + "'"
                    + ", description='" + c.getDescription()+ "'"
                                        + ", coursImg='" + c.getCoursImg()+ "'"

                    + ", categorieNom='" + c.getCategorieNom() + "'where coursID = " + c.getCoursID() + "";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
        }
    }

    @Override
    public void editCours(Cours c) throws SQLException {
        try {
            String req = "UPDATE  categorie  SET  utilisateurNom=?, nomCours=?, nbrChapitres=?, description=?, coursImg=?, categorieNom=?   WHERE coursID = ?  ";
            PreparedStatement ps = connexion.prepareStatement(req);

            ps.setString(1, c.getUtilisateurNom());
            ps.setString(2, c.getNomCours());
            ps.setInt(3, c.getNbrChapitres());
            ps.setString(4, c.getDescription());
                        ps.setString(5, c.getCoursImg());

            ps.setString(6, c.getCategorieNom());
            ps.setInt(7, c.getCoursID());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        } 
    }
  
}
