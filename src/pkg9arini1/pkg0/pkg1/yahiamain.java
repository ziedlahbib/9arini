/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9arini1.pkg0.pkg1;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author yahia
 */
public class yahiamain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                 try {
         Parent root = FXMLLoader.load(getClass().getResource("/workshopFX/view/Reclamation.fxml"));
            Scene scene = new Scene(root);
             primaryStage.setTitle("Interface rec");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); 
    }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
