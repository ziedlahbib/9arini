/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import entities.Categorie;
import entities.Chapitres;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.Categorieservice;
import services.Chapitresservice;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class ChapitresGestionController implements Initializable {

     public ChapitresGestionController() throws IOException, SQLException, NoSuchAlgorithmException  {
         
   
        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    
    
    
    
    @FXML
    private TableView<Chapitres> tablechapitres;
    @FXML
    private TableColumn<Chapitres, Integer> ChapitreIDD;
    
    @FXML
    private TableColumn<Chapitres, String> chapitrenomm;
    @FXML
    private TableColumn<Chapitres, String> videochapitress;
    @FXML
    private TextField FnomChapitree;
    private TextField inputRech;
    @FXML
    private Hyperlink returnacceiuilchapitre;
    @FXML
    private Hyperlink precedenttchapitre;
    @FXML
    private Button modifierChapitres;
    @FXML
    private Label idlabelChapitre;
    @FXML
    private Label imgpathchapitre;
    @FXML
    private Button browsevideochapitre;
    @FXML
    private ImageView imgajoutchapitre;
public ObservableList<Chapitres> list;
    @FXML
    private AnchorPane FenetreChapitres;
    @FXML
    private TextField inputRechcc;
    
        Chapitresservice cs = new Chapitresservice();
    @FXML
    private Button supprimerch;
    @FXML
    private Button ajimg;
    @FXML
    private TableColumn<Chapitres, String> CoursNommm;
    @FXML
    private ComboBox<String> CoursNomchapitre;
        private ObservableList<Chapitres> data;
    @FXML
    private Button confirmermodifierChap;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                 Chapitresservice ps = new Chapitresservice();
        ArrayList<Chapitres> a = new ArrayList<>();
        try {
            a = (ArrayList<Chapitres>) ps.afficherChapitre();
        } catch (SQLException ex) {
        }
        ObservableList<Chapitres> obsl = FXCollections.observableArrayList(a);

        tablechapitres.setItems(obsl);
        ChapitreIDD.setCellValueFactory(new PropertyValueFactory<>("chapitreID"));
        CoursNommm.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        chapitrenomm.setCellValueFactory(new PropertyValueFactory<>("chapitreNom"));
                videochapitress.setCellValueFactory(new PropertyValueFactory<>("videoChapitre"));
        try {
            list = FXCollections.observableArrayList(
            ps.afficherChapitre()); 
       
   FilteredList<Chapitres> filteredData = new FilteredList<>(list, e -> true);
         inputRechcc.setOnKeyReleased(e -> {
            inputRechcc.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Chapitres>) Chapitress -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if (Chapitress.getChapitreNom().toLowerCase().contains(lower)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Chapitres> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(tablechapitres.comparatorProperty());
            tablechapitres.setItems(sortedData);
            
             }); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
       try {
            String req = "select * from cours";

      Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Cours c = new Cours(rst.getString("nomCours"));
 String xx=rst.getString("nomCours");
                 CoursNomchapitre.getItems().add(xx);
       

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }           
        
        
        
        
        
        
        
        
        
        
        
        
        
    }


    @FXML
    private void Returnacceiuilchapitre(ActionEvent event) throws IOException {
           Parent page1=FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
Scene scene=new Scene(page1,728,671);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setTitle("Gestion");

    stage.setScene(scene);
    
    stage.show();
        
    }

    @FXML
    private void PrecedentGestionChapitre(ActionEvent event) {
    }

    @FXML
    private void ModifierChapitres(ActionEvent event) {
         int id7 = tablechapitres.getSelectionModel().getSelectedItem().getChapitreID();
       //nomTextField.setText(user.getNom());
       FnomChapitree.setText(tablechapitres.getSelectionModel().getSelectedItem().getChapitreNom());
       CoursNomchapitre.setValue(tablechapitres.getSelectionModel().getSelectedItem().getNomCours());
              imgpathchapitre.setText(tablechapitres.getSelectionModel().getSelectedItem().getVideoChapitre());

       idlabelChapitre.setText(Integer.toString(id7));
              confirmermodifierChap.setVisible(true);
        
    }
  @FXML
    private void ConfirmerModifierChapitre(ActionEvent event) {
           try {
       Chapitres c1 = new Chapitres(Integer.parseInt(idlabelChapitre.getText()),CoursNomchapitre.getValue(),
               FnomChapitree.getText(),imgpathchapitre.getText() );
Chapitresservice chapitresservice = new Chapitresservice();
chapitresservice.editChapitres(c1);
 resetTableDataChapitres();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    @FXML
    private void supprimerrchapitres(ActionEvent event) throws SQLException {
        
        if (event.getSource() == supprimerch) {
            Chapitres chapitres = new Chapitres();
            chapitres.setChapitreID(tablechapitres.getSelectionModel().getSelectedItem().getChapitreID());
            System.out.println(tablechapitres.getSelectionModel().getSelectedItem().getChapitreID());
//System.out.println("delete" + categorie.getCategorieID());
            Chapitresservice chapitreservice = new Chapitresservice();
            chapitreservice.supprimerChapitres(chapitres);
            resetTableDataChapitres();
        }
    }
    
   public void resetTableDataChapitres() throws SQLDataException, SQLException {

       List<Chapitres> listChapitre = new ArrayList<>();
        listChapitre = cs.getAllChapitres();
        ObservableList<Chapitres> data = FXCollections.observableArrayList(listChapitre);
        tablechapitres.setItems(data);
         }
    
   

    @FXML
    private void Ajouterimggch(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
/*
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFiltermp4
                = new FileChooser.ExtensionFilter("mp4 files (*.mp4)", "*.mp4");
        FileChooser.ExtensionFilter extFilterMP4
                = new FileChooser.ExtensionFilter("MP4 files (*.MP4)", "*.MP4");
              
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng,extFiltermp4,extFilterMP4);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
          /*  imgajoutchapitre.setImage(image);
            imgajoutchapitre.setFitWidth(200);
            imgajoutchapitre.setFitHeight(200);
            imgajoutchapitre.scaleXProperty();
            imgajoutchapitre.scaleYProperty();
            imgajoutchapitre.setSmooth(true);
            imgajoutchapitre.setCache(true);*/
           /* FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();

        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathchapitre.setText(file.getAbsolutePath());
        */
           FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select your media(*.mp4)", "*.mp4");
            chooser.getExtensionFilters().add(filter);
            File file = chooser.showOpenDialog(null);
            if ( file !=null){

Media source = new Media(file.toURI().toString());
MediaPlayer player = new MediaPlayer(source);
                MediaView view = new MediaView(player);
              //  root.getChildren().add(view);
                player.play();
imgpathchapitre.setText(file.getAbsolutePath());

          }   }
           
    
    
///////////
      /*  public void handle(ActionEvent event) {
            FileChooser chooser = new FileChooser();
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("select your media(*.mp4)", "*.mp4");
            chooser.getExtensionFilters().add(filter);
            File file = chooser.showOpenDialog(primaryStage);
            if ( file !=null){

                Media source = new Media(file.getPath());
                MediaPlayer player = new MediaPlayer(source);
                MediaView view = new MediaView(player);
                root.getChildren().add(view);
                player.play();


            }
             else {
                label.setText("vide ");
            }
      } }*/
    //////////////////
  
    @FXML
    private void AjouterChapitres(ActionEvent event) throws SQLException {
        Chapitresservice productService = new Chapitresservice();
        if (FnomChapitree.getText().equals("")) { 
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {
            Chapitres c = new Chapitres(CoursNomchapitre.getValue(),FnomChapitree.getText(),imgpathchapitre.getText());
            try {
                productService.ajouterProduitChapitres(c);
                resetTableDataChapitres();
            } catch (SQLException ex) {
                System.out.println("erreur");

            }
        }
    
    }
   
    
   
    
    
    
    
    
    
}
