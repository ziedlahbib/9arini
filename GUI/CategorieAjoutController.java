/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Categorie;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import services.Categorieservice;
import services.Coursservice;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * FXML Controller class
 *
 * @author fares
 */
public class CategorieAjoutController implements Initializable  {

    @FXML
    private Hyperlink ReturnAcceuilcateg;
    @FXML
    private Button ajcat;
    @FXML
    private TextField FnomCategorie;
    @FXML
    private Button browse;
    @FXML
    private Label imgpath;
    @FXML
    private ImageView ImgAjout;
    @FXML
    private Label imagename;
    @FXML
    private AnchorPane Fenetrez;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ReturnAcceuilAjoutcategorie(ActionEvent event) throws IOException {
          Parent page1=FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
Scene scene=new Scene(page1,728,671);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
        stage.setTitle("Gestion");

    stage.show();
        
        
        
    }

    @FXML
    private void AjouterCateg(ActionEvent event) {
         Categorieservice productService = new Categorieservice();
        if( FnomCategorie.getText().equals(""))
        { //x|| Fdescription.getText().equals("")
               Alert a=new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
         a.setHeaderText(null);
         a.showAndWait();
            
        }
             
        else{
           Categorie c=new Categorie(FnomCategorie.getText(),imgpath.getText());   
             try {
                 productService.ajouterProduitCategorie(c);
                  String message =  FnomCategorie.getText();
            String title = "Ajouté";
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
                  
                 
             } catch (SQLException ex) {
                  System.out.println("Catégorie ajouté");
             }
       
        } 
    }

    @FXML
    private void Ajouterimg(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImgAjout.setImage(image);
            ImgAjout.setFitWidth(200);
            ImgAjout.setFitHeight(200);
            ImgAjout.scaleXProperty();
            ImgAjout.scaleYProperty();
            ImgAjout.setSmooth(true);
            ImgAjout.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();

        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpath.setText(file.getAbsolutePath());
        //imagename.setText(file.getName());
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
