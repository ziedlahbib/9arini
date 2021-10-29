/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entities.Publicite;
import services.PubliciteServices;

/**
 * FXML Controller class
 *
 * @author pc-asus
 */
public class ModifierPubliciteController implements Initializable {

    @FXML
    private TextField modif_video;
    @FXML
    private TextField modif_image;
    @FXML
    private TextField modif_agemin;
    @FXML
    private TextField modif_agemax;
    @FXML
    private TextField modif_genre;
    @FXML
    private TextField modif_dateDebut;
    @FXML
    private TextField modif_Datefin;
    @FXML
    private TextField modif_Lien;
    @FXML
    private AnchorPane C_Modifier;
    @FXML
    private Button modifier_pub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        modif_video.setText(GestionPubliciteController.connectedPub.getVideoPublicite());
        modif_Lien.setText(GestionPubliciteController.connectedPub.getLienPublicite());
        modif_image.setText(GestionPubliciteController.connectedPub.getImagePublicite());
        modif_agemin.setText(Integer.toString(GestionPubliciteController.connectedPub.getAgeMin()));
        modif_agemax.setText(Integer.toString(GestionPubliciteController.connectedPub.getAgeMax()));
        modif_Datefin.setText(((Date) GestionPubliciteController.connectedPub.getDateFinPublicite()).toString());
        modif_dateDebut.setText(((Date) GestionPubliciteController.connectedPub.getDateDebutPublicite()).toString());
        modif_genre.setText(GestionPubliciteController.connectedPub.getGenre());

    }

    @FXML
    private void modifierPublicite(ActionEvent event) throws SQLException, NoSuchAlgorithmException {
        String video = modif_video.getText();
       
        String lien = modif_Lien.getText();
        String image = modif_image.getText();
      
         Publicite p = new Publicite(video, lien, image);
        
        PubliciteServices productService = new PubliciteServices();
        productService.modifierPublicite(p);
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/GPI/GestionPublicite.fxml"));
            Scene scene = new Scene(page, 901, 514);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            

        } catch (IOException IOex) {
            System.out.println("Une erreur s'est produite. " + IOex.getMessage());
        }
        

    }

}
