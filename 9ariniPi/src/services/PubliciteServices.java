/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import IService.IPubliciteService;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Categorie;
import entities.Publicite;

import DB.DB;

/**
 *
 * @author pc-asus
 */
public class PubliciteServices implements IPubliciteService{

    static Connection connexion;

    public PubliciteServices() {
        connexion = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterPublicite(Publicite p) throws SQLException {
        String req = "INSERT INTO `publicité`( `videoPublicite`, `genre`, `ageMax`, `ageMin`, `utilisateurID`, `dateFinPublicite` , `dateDebutPublicite`,   `lienPublicite`, `nbrClick`, `nbrAffichage`, `imagePublicite`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getVideoPublicite());
            ps.setString(2, p.getGenre());
            ps.setInt(3, p.getAgeMax());
            ps.setInt(4, p.getAgeMin());
            ps.setInt(5, p.getUtilisateurID());
            ps.setDate(6, (Date) p.getDateFinPublicite());
            ps.setDate(7, (Date) p.getDateDebutPublicite());
            ps.setString(8, p.getLienPublicite());
            ps.setInt(9, p.getNbrClick());
            ps.setInt(10, p.getNbrAffichage());
            ps.setString(11, p.getImagePublicite());

            ps.executeUpdate();
            System.out.println("publicite ajouté");
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout" + ex.getMessage());
        }
    }

    @Override
    public List<Publicite> affichertab() throws SQLException {
        List<Publicite> publicites = new ArrayList<>();
        String req = "SELECT * FROM publicité";
        try {

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                Publicite P = new Publicite(
                       rst.getInt("publiciteID"),
                       rst.getString("videoPublicite"),
                        rst.getString("genre"),
                        rst.getInt("ageMax"),
                        rst.getInt("ageMin"),
                        rst.getInt("utilisateurID"),
                        rst.getDate("dateFinPublicite"),
                        rst.getDate("dateDebutPublicite"),
                        rst.getString("lienPublicite"),
                        rst.getInt("nbrClick"),
                        rst.getInt("nbrAffichage"),
                       rst.getString("imagePublicite"));
                publicites.add(P);
               
            }

        } catch (SQLException ex) {
            Logger.getLogger(Publicite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publicites;
    }

    @Override
    public void modifierPublicite(Publicite p) throws SQLException, NoSuchAlgorithmException {
       /* String req = "UPDATE `publicité` SET "
                + " `videoPublicite`='" + p.getVideoPublicite() + "'"
                + ",`genre`='" + p.getGenre() + "'"
                + ", `ageMax`='" + p.getAgeMax() + "'"
                + ", `ageMin`='" + p.getAgeMin() + "'"
                + ", `utilisateurID`='" + p.getUtilisateurID() + "'"
                + ", `dateFinPublicite`='" + p.getDateFinPublicite() + "'"
                + ", `dateDebutPublicite`='" + p.getDateDebutPublicite() + "'"
                + ", `lienPublicite`='" + p.getLienPublicite() + "'"
                + ", `nbrClick`='" + p.getNbrClick() + "'"
                + ", `nbrAffichage`='" + p.getNbrAffichage() + "'"
                + ", `imagePublicite`='" + p.getImagePublicite() + "'where lienPublicite LIKE'"+ p.getLienPublicite()+"'";*/
       String req = "UPDATE `publicité` SET "
                + " `videoPublicite`='" + p.getVideoPublicite() + "'"
                + ", `lienPublicite`='" + p.getLienPublicite() + "'"
                + ", `imagePublicite`='" + p.getImagePublicite() + "'where lienPublicite LIKE'"+ p.getLienPublicite()+"'";
        try {
            Statement stm = connexion.createStatement();
            stm.executeUpdate(req);
            System.out.println("publicite Modifier");
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout" + ex.getMessage());

        }

    }

   @Override
    public void supprimerPublicite(Publicite p) throws SQLException {

        try {
            String req = "DELETE FROM `publicité` where lienPublicite  = ?";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, p.getLienPublicite());
            ps.execute/*Update*/();
            System.out.println(" Publicite supprimé");
        } catch (SQLException ex) {
            System.out.println("Erreur de suppression" + ex.getMessage());
        }
    }

   
 
   
   
	 
	/*@Override
	public List<Publicite> AfficheCategoriePub(Categorie c) throws SQLException{
		List<Publicite> Publicites = new ArrayList<>();
	Statement St ;
        ResultSet rs;
		
			String req = "Select * from Publicité where (genre LIKE '"+c.getGenre()+"' or ageMax="+c.getAgeMax()+" and ageMax="+c.getAgeMin()+" )  ";
			St = connexion.createStatement();
                             
			try{
				rs =(ResultSet)St.executeQuery(req);
                                while (rs.next()) {
                Publicite p = new Publicite(
                         rs.getInt("publiciteID"),
                         rs.getString("videoPublicite"),
                         rs.getString("genre"),
                         rs.getInt("ageMax"),
                         rs.getInt("ageMin"),
                         rs.getInt("utilisateurID"),
                         rs.getDate("dateFinPublicite"),
                         rs.getDate("dateDebutPublicite"),
                         rs.getString("lienPublicite"),
                         rs.getInt("nbrClick"),
                         rs.getInt("nbrAffichage"),
                         rs.getString("imagePublicite"));
                        
                Publicites.add(p);
                                }
                                
			}catch (SQLException e) {
				System.out.println("Erreur d'affichage categorie" + e.getMessage());
			}
                        return Publicites;*/
		


}
	
    
    


