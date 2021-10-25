/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Node;
import entities.Admin;
import entities.Membre;
import entities.Utilisateur;
import utils.MyDB;

   
    
/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class AuthentificationController implements Initializable  {
    
    
    public static Utilisateur connectedUser;

    public AuthentificationController(TextField AcceuilEmail, TextField AcceuilPasswd) {
        this.AcceuilEmail = AcceuilEmail;
        this.AcceuilPasswd = AcceuilPasswd;
    }
    
    
    public AuthentificationController() throws IOException, SQLException, NoSuchAlgorithmException  {
         
   
        connexion = MyDB.getInstance().getConnection();
    }
    Connection connexion;
   
    
    
     
         @FXML
    private Button btn_AcceuilInscription;
     @FXML
    private Button btn_AcceuilConnexion;
     @FXML
    private TextField AcceuilEmail;
      @FXML
    private TextField AcceuilPasswd;
      @FXML 
    private Hyperlink zd_mdpoublier;
      
      private TextField zd_emailcon= AcceuilEmail;
      private TextField zd_Passwdconn=AcceuilPasswd;

    public TextField getZd_emailcon() {
        return zd_emailcon;
    }

   

    public TextField getZd_Passwdconn() {
        return zd_Passwdconn;
    }


      
      
      
      

    
      
    /**
     * Initializes the controller class.
     * @param 
     * @param 
     */
      
    // TODO
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
      
       
    }    
    @FXML
    private void goToInscription(ActionEvent event) throws IOException{
    try{
    Parent page1=FXMLLoader.load(getClass().getResource("/view/Inscription.fxml"));
    Scene scene =btn_AcceuilInscription.getScene();
    scene.setRoot(page1);
    Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }catch (IOException ex){
    }
    }
    @FXML
    private void login(ActionEvent event)throws IOException, SQLException, NoSuchAlgorithmException{
        String req ="SELECT * from utilisateur WHERE utilisateurAdresseEmail LIKE '"+AcceuilEmail.getText()+"' and utilisateurMDP LIKE '"+hashmdp(AcceuilPasswd.getText())+"' ";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        
        while (rst.next()) {
           
                    Admin p = new Admin(rst.getInt("utilisateurID")
                    , rst.getInt("utilisateurphone")
                    , rst.getString("utilisateurPdp")
                    , rst.getString("utilisateurNom")
                    , rst.getString("utilisateurPrenom")
                    , rst.getString("utilisateurAdresse")
                    , rst.getString("utilisateurPays")
                    , rst.getString("utilisateurGenre")
                    , rst.getString("utilisateurAdresseEmail")
                    , rst.getString("utilisateurMDP")
                    , rst.getString("utilisateurRole")
                    , rst.getString("utilisateurOrganisme")
                    , rst.getString("utilisateurFonction")
                    , rst.getString("utilisateurSavoirEtre")
                    , rst.getString("nomEntreprise")
                    , rst.getString("EntrepreneurSiteWeb")
                    , rst.getString("EntrepreneurUsage")
                    , rst.getDate("utilisateurDDN"));
                            
            
            AuthentificationController.connectedUser=p;
            
        
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
            Scene scene2 = btn_AcceuilConnexion.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
        }
        }
       
    }
   

    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
     
    
       return hexString.toString();
    }
}
    

