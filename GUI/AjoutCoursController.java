/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import entities.Categorie;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.Categorieservice;
import services.Coursservice;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class AjoutCoursController implements Initializable {

    @FXML
    private Label aas;
    @FXML
    private Label imgpathttt;
    @FXML
    private ImageView imgajoutincours;

   
     public AjoutCoursController() throws IOException, SQLException, NoSuchAlgorithmException  {
         
   
        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    
    @FXML
    private TextField FnomCours;
    @FXML
    private Button FajouterCours;
    @FXML
    private ComboBox<String> FcategorieCours;
    @FXML
    private TextField FnbrCh;
    @FXML
    private TextField FidUtilisateur;
    @FXML
    private TextField Fdescription;
    private Button btnAcceuilAjoutcours;
    @FXML
    private Hyperlink ReturnAcceuil;
    @FXML
    private AnchorPane Fenetreajoutcourss;
    @FXML
    private Hyperlink précedent;
            Coursservice productService = new Coursservice();

       //  Coursservice productService =  Coursservice.getInstance();

    /**
     * Initializes the controller class.
     */
    

    Categorieservice categorieservice;
           
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

              try {
            String req = "select * from categorie";

      Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categorie a = new Categorie(rst.getString("CategorieNom"));
 String xx=rst.getString("CategorieNom");
 //ObservableList<String> list_ne = FXCollections.observableArrayList(xx);
                 FcategorieCours.getItems().add(xx);
       
            //FcategorieCours.setItems(list_ne);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }         
              

//ObservableList<String> list_ne = FXCollections.observableArrayList(CoursGestionController.connectedCategorie.getCategorieNom());


  
            /*  List<String> a = new
            String cat = "";
            for (int i = 0; i < listCat.size(); i++) {
            cat+=listCat.get(i).getCategorieNom()+",";
            }
            cat = cat.substring(0, cat.length()-1); */
           

           // ObservableList<String> list_ne = FXCollections.observableArrayList(categorieservice.getAllCategorieNom().get(0));
            //   ObservableList<String> list_ne = FXCollections.observableArrayList(Productt.getCategorieNom());
            
     
        
    }
    
    
     @FXML
    private void addimgcours(ActionEvent event) {
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
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
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
        imgpathttt.setText(file.getAbsolutePath());
        //imagename.setText(file.getName());
        
        
    }
    
    
    @FXML
    private void FajouterCours(ActionEvent event) throws SQLException {
       // Coursservice productService = Coursservice.getInstance();
                   Coursservice productService = new Coursservice();
          
                   
                

 Categorie ca;
Categorieservice cs = new Categorieservice();
        if( FidUtilisateur.getText().equals("") || FnbrCh.getText().equals("")
                || FcategorieCours.getValue().equals("")|| FnomCours.getText().equals("") 
                || Fdescription.getText().equals(""))
        {
               Alert a=new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
         a.setHeaderText(null);
         a.showAndWait();
            
        }
       else  if( FidUtilisateur.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+") || FnbrCh.getText().equals("")
                || FcategorieCours.getValue().equals("")|| FnomCours.getText().matches("[\\\\!\"#$%&()*+,./:;<=>?@\\[\\]^_{|}~]+")
                || Fdescription.getText().equals(""))
       {
           Alert a=new Alert(Alert.AlertType.WARNING);
            a.setContentText("Une erreur s’est produite. Veuillez réessayer. ");
         a.setHeaderText(null);
         a.showAndWait();
       }
        
        /*else if(!  (Integer.parseInt(FcategorieCours.getValue())==1)&& !(Integer.parseInt(FcategorieCours.getValue()) ==2)  && !  (Integer.parseInt(FcategorieCours.getValue() )==3 )) 
        {
             Alert a=new Alert(Alert.AlertType.WARNING);
         a.setContentText(FcategorieCours.getValue()+"  "+"n'est pas une catégorie" +" Choisissez une catégorie existante");
         a.setHeaderText(null);
         a.showAndWait(); 
        }*/
                
      //  else{
        try {
   Cours c=new Cours(Integer.parseInt(FidUtilisateur.getText()),FnomCours.getText(),
           Integer.parseInt(FnbrCh.getText()),Fdescription.getText(),imgpathttt.getText()
           ,FcategorieCours.getValue());     
            productService.ajouterProduitCours(c);
            String message =  FnomCours.getText();
            String title = "Ajouté";
           TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(title);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
                  
        } catch (SQLException ex) {
            System.out.println("Cours non ajouté");

        }
        //}
    }


    @FXML
    private void ReturnAcceuilAjoutcours(ActionEvent event) throws IOException {
        
           Parent page1=FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
Scene scene=new Scene(page1,728,671);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
        stage.setTitle("Gestion");

    stage.show();
            
    }


    @FXML
    private void précedent(ActionEvent event) throws IOException {
        
        
       Parent page1=FXMLLoader.load(getClass().getResource("CoursGestion.fxml"));
Scene scene=new Scene(page1,1236,785);

       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.setTitle("Gestion des cours");
    stage.show();
    }

   
}
