/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg9arini1.pkg0.pkg1;

import java.io.IOException;
import static java.nio.file.Paths.get;
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
 * @author lahbib
 */
public class JavaFX extends Application {
    
    private Stage primaryStage;
    private Parent parentPage;
    

   
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage =primaryStage;
        this.primaryStage.setTitle("9arini");
        parentPage =FXMLLoader.load(getClass().getResource("/view/Authentification.fxml"));
        Scene scene =new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        /*
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}