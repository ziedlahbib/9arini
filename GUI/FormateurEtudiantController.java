/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class FormateurEtudiantController implements Initializable {

    @FXML
    private AnchorPane Fenetreetudiform;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Etudiant(ActionEvent event) throws IOException {
       /* AnchorPane pane = FXMLLoader.load(getClass().getResource("Cours.fxml"));
        Fenetreetudiform.getChildren().setAll(pane);*/
            Parent page1=FXMLLoader.load(getClass().getResource("EspaceEtudiant.fxml"));
Scene scene=new Scene(page1,1129,819);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
 stage.setTitle("Cours en ligne, Apprenez ce que vous voulez");

    stage.setScene(scene);
    stage.show();
       
       
    }

    @FXML
    private void Formateur(ActionEvent event) throws IOException {
      /*  AnchorPane panee = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Fenetreetudiform.getChildren().setAll(panee);*/
      
             Parent page1=FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
Scene scene=new Scene(page1,728,671);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
                                     stage.setTitle("Gestion");

    stage.setScene(scene);
    stage.show();
    }
    
}
