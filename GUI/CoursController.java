/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import controller.AuthentificationController;
import entities.Categorie;
import entities.Chapitres;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.text.Document;
import services.Chapitresservice;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class CoursController implements Initializable {

    @FXML
    private TableColumn<Chapitres, String> ChapitresdansCoursEtu;
    @FXML
    private TableView<Chapitres> tableChapitresdansCours;
    @FXML
    private Hyperlink profiliio;
    @FXML
    private Label testithis;
    @FXML
    private MediaView VideoCoursss;
    @FXML
    private ImageView Imgcoruusr;
    @FXML
    private Button reclamation;

    public CoursController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;
    @FXML
    private Label Flistechap;
    @FXML
    private AnchorPane Fenetrecoursss;
    @FXML
    private Hyperlink acceuilducours;
    @FXML
    private Hyperlink retourducours;
    @FXML
    private Button buttonpdf;
    @FXML
    private Text Descriptionsdanscours;
    @FXML
    private Label CoursnomdansCours;
    @FXML
    private TextField searchfilcouus;
    ObservableList<Chapitres> data;
private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    /**
     * Initializes the controller class.
     */

    /*  public ObservableList<Chapitres> Thisisit() throws SQLDataException, SQLException {
         List<Chapitres> list =new ArrayList<Chapitres>();
             ObservableList<Chapitres> lc_final = FXCollections.observableArrayList(list);

            /*String req = "select  chapitreNom from  chapitre c Join  cours  c1 where c.nomCours=c1.nomCours "
                    + "And  c1.nomCours LIKE 'React'";*/

 /*  String req = "select  chapitreNom from  chapitre c Join  cours  c1 where c.nomCours=c1.nomCours "
                    + "And  c1.nomCours LIKE'"+EspaceEtudiantController.connectedCours.getNomCours()+ "'";
      Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Chapitres a = new Chapitres(rst.getString("chapitreNom"));
 String xx=rst.getString("chapitreNom");
                 list.add(a);

       }
        
       return lc_final;
       
       } */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //////////
         FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
               CoursnomdansCours.setText(EspaceEtudiantController.connectedCours.getNomCours());
        Descriptionsdanscours.setText(EspaceEtudiantController.connectedCours.getDescription());
        profiliio.setText(AuthentificationController.connectedUser.getUtilisateurNom());
        /////////////////////////////
        
        try {
String req2 = "select  videoChapitre from  chapitre c Join  cours  c1 where c.nomCours=c1.nomCours "
                    + "And  c1.nomCours LIKE'" + EspaceEtudiantController.connectedCours.getNomCours() + "'";
            Statement stm2 = connexion.createStatement();
            ResultSet rst2 = stm2.executeQuery(req2);
             while (rst2.next()) {
  Chapitres c = new Chapitres(rst2.getString("videoChapitre"));
                                       Chapitres connectedCHapitre;
            String yy = rst2.getString("videoChapitre");
connectedCHapitre=c;  
tableChapitresdansCours.setRowFactory(tv -> {
            TableRow<Chapitres> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Chapitres rowData = row.getItem();
                    
                    System.out.println(rowData);
                    
                     testithis.setText(yy);
          
                //////////////////////Media////////////////////////////////////    
                file = new File(testithis.getText());
        media= new Media(file.toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        VideoCoursss.setMediaPlayer(mediaPlayer); 
        mediaPlayer.play();     
       ////////////////////////////////////////////////
       ///////////////////File//////////
        File file = new File(testithis.getText());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            Imgcoruusr.setImage(image);
            Imgcoruusr.setFitWidth(200);
            Imgcoruusr.setFitHeight(200);
            Imgcoruusr.scaleXProperty();
            Imgcoruusr.scaleYProperty();
            Imgcoruusr.setSmooth(true);
            Imgcoruusr.setCache(true);
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
                                    }
            });
            return row;
        } ); 


            }
            /////////////////
            String req = "select  * from  chapitre c Join  cours  c1 where c.nomCours=c1.nomCours "
                    + "And  c1.nomCours LIKE'" + EspaceEtudiantController.connectedCours.getNomCours() + "'";
            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                Chapitres a = new Chapitres(rst.getString("chapitreNom"));
                              
                String xx = rst.getString("chapitreNom");
                //String yy = rst.getString("videoChapitre");

                ArrayList<Chapitres> zz = new ArrayList<>();
                zz.add(a);

                ObservableList<Chapitres> obsl = FXCollections.observableArrayList(zz);
                tableChapitresdansCours.getItems().add(a);
                ChapitresdansCoursEtu.setCellValueFactory(new PropertyValueFactory<>("chapitreNom"));
////////////
/*
        tableChapitresdansCours.setRowFactory(tv -> {
            TableRow<Chapitres> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    Chapitres rowData = row.getItem();
                    
                    System.out.println(rowData);
                    
                        
                         
                   
       
       
        File file = new File(testithis.getText());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            VideoCoursss.setImage(image);
            VideoCoursss.setFitWidth(200);
            VideoCoursss.setFitHeight(200);
            VideoCoursss.scaleXProperty();
            VideoCoursss.scaleYProperty();
            VideoCoursss.setSmooth(true);
            VideoCoursss.setCache(true);
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
                                    }
            });
            return row;
        } );  
*/

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
        
     
           }

    @FXML
    private void retourAcceuilducours(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("FormateurEtudiant.fxml"));
        Scene scene = new Scene(page1, 728, 671);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Se connecter");

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void Retourducours(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("FormateurEtudiant.fxml"));
        Scene scene = new Scene(page1, 728, 671);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void onbuttonpdf(ActionEvent event) {
        /*  if(event.getSource()==buttonpdf){
            printPDF();
            TrayNotification tray = new TrayNotification();
                        AnimationType type = AnimationType.SLIDE;
                        tray.setAnimationType(type);
                        tray.setTitle("PDF");
                        tray.setMessage("PDF Evenements");
                        tray.setNotificationType(NotificationType.INFORMATION);//
                        tray.showAndDismiss(Duration.millis(3000));
            
        }*/

    }

    private void printPDF() {
        /*   String path = "C:\\Users\\fares\\Desktop\\xx/ressource.pdf";
        
        try {Document d = new Document() {};
            PdfWriter.getInstance(d, new FileOutputStream(path));
            System.out.println(path);
            d.open();
            PdfPTable pTable = new PdfPTable(9);
            pTable.addCell("Username");
            pTable.addCell("Nom");
            pTable.addCell("Date Debut");
            pTable.addCell("Description");
            pTable.addCell("Pays");
            pTable.addCell("Ville");
            pTable.addCell("Prix");
            pTable.addCell("Place");
            pTable.addCell("Note");
            
                tableViewEvent.getItems().forEach((t) -> {
                    pTable.addCell(String.valueOf(t.getId()) );
                    pTable.addCell(t.getUsername());
                    pTable.addCell(t.getNom());
                    pTable.addCell(t.getDate_debut().toString());
                    pTable.addCell(t.getDescription());
                    pTable.addCell(t.getPays());
                    pTable.addCell(t.getVille());
                    pTable.addCell(String.valueOf(t.getPrix()));
                    pTable.addCell(String.valueOf(t.getNbr_places()));
                    pTable.addCell(String.valueOf(t.getRate()));
                    
                try {
                    d.add(pTable);
                } catch (DocumentException ex) {
                    System.out.println(ex);                }
                });
            d.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (DocumentException ex) {
            System.out.println(ex);
        }*/

    }

    @FXML
    private void SearchonCours(ActionEvent event) throws SQLException {
        String req = "SELECT * from cours WHERE nomCours LIKE '" + searchfilcouus.getText() + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

            Cours c = new Cours(rst.getInt("coursID"),
                     rst.getInt("utilisateurID"),
                     rst.getString("nomCours"),
                     rst.getInt("nbrChapitres"),
                     rst.getString("description"),
                     rst.getString("coursImg"),
                     rst.getString("categorieNom"));
            EspaceEtudiantController.connectedCours = c;
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("RechercheCours.fxml"));
                Scene scene = new Scene(page1, 848, 654);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle(EspaceEtudiantController.connectedCours.getNomCours() + " " + ":Résultat de la recherche");

                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
            }

        }
    }

    @FXML
    private void aj_reclamation(ActionEvent event)  throws IOException {
        
        
       
        
       mediaPlayer.pause();
       Parent page1=FXMLLoader.load(getClass().getResource("/view/Reclamation.fxml"));
       Scene scene=new Scene(page1,1086,749);
       Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
                                     stage.setTitle("espace de reclamation");

    stage.setScene(scene);
    stage.show(); 
        
    }
    
}
