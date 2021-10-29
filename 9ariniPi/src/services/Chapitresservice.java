/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chapitres;
import DB.DB;
import IService.IServiceChapitres;
import entities.Categorie;
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
public class Chapitresservice implements IServiceChapitres {

    Connection connexion;
    //Utilisateur loggeduser;

    public Chapitresservice() {
        connexion = DB.getInstance().getConnection();
    }
    @Override
    public void ajouterProduitChapitres(Chapitres c) throws SQLException {
        String req = "INSERT INTO `chapitre` (`nomCours` , `chapitreNom`, `videoChapitre`) "
                + "VALUES ( ?, ?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, c.getNomCours ());
        ps.setString(2, c.getChapitreNom());
        ps.setString(3, c.getVideoChapitre());
        ps.executeUpdate();
    }
    @Override
    public List<Chapitres> getAllChapitres() throws SQLException {

        List<Chapitres> Chapitress = new ArrayList<>();
        String req = "select * from chapitre";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chapitres c = new Chapitres(rst.getInt("chapitreID"),
                    rst.getString("nomCours"),
                    rst.getString("chapitreNom"),
                    rst.getString("videoChapitre"));
            Chapitress.add(c);
        }
        return Chapitress;
    }
       @Override
    public List<Chapitres> afficherChapitre() throws SQLException {
        List<Chapitres> myList = new ArrayList();

        String requete = "SELECT * FROM chapitre ";
        PreparedStatement pst = connexion.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Chapitres a = new Chapitres();
            a.setChapitreID(rs.getInt(1));
            a.setNomCours(rs.getString(2));
              a.setChapitreNom(rs.getString(3));
            a.setVideoChapitre(rs.getString(4));
            myList.add(a);

        } 
        return myList;
    } 
    @Override
    public void supprimerChapitres(Chapitres c) throws SQLException {

        String req = "DELETE FROM chapitre WHERE chapitreID =?";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);

            ps.setInt(1, c.getChapitreID());
            ps.execute/*Update*/();
        } catch (SQLException ex) {
              System.out.println(ex);
        }
    }
     @Override
         public void editChapitres(Chapitres c) throws SQLException {
        try {
            String req = "UPDATE  chapitre  SET  nomCours=?, chapitreNom=? , videoChapitre=?   WHERE chapitreID    = ?  ";
            PreparedStatement ps = connexion.prepareStatement(req);
           
            ps.setString(1, c.getNomCours());
                        ps.setString(2, c.getChapitreNom());
                                                ps.setString(3, c.getVideoChapitre());
 ps.setInt(4, c.getChapitreID());
            ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        }

    @Override
    public void ModifierChapitres(Chapitres c, int chapitreID) {
        try {
            String req = "UPDATE chapitre SET "
                    + " nomCours ='" + c.getNomCours () + "'"
                    + " chapitreNom='" + c.getChapitreNom() + "'"
                    + ", videoChapitre='" + c.getVideoChapitre() + "'where chapitreID = " + chapitreID + "";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);

        } catch (SQLException ex) {
        }
    }
       
}
