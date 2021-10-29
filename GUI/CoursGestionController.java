/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import com.mysql.jdbc.Connection;
import entities.Categorie;
import entities.Cours;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import services.Categorieservice;
import services.Coursservice;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author fares
 */
public class CoursGestionController implements Initializable {

    public static Cours connectedCourss;
    public static Categorie connectedCategorie;
    private TableColumn<Cours, String> categorieimgggg;
    @FXML
    private TableColumn<?, ?> coursImgggg;
       

//public static Categorieservice connectedCategorie2;
    public CoursGestionController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    java.sql.Connection connexion;

    @FXML
    private Hyperlink ReturnAcceuilcateg5;
    @FXML
    private Hyperlink precedentCours;
    @FXML
    private TableView<Cours> tableCours;
    @FXML
    private TableColumn<Cours, Integer> coursIDD;
    @FXML
    private TableColumn<Cours, Integer> utilisateurIDD;
    @FXML
    private TableColumn<Cours, String> nomCourss;
    @FXML
    private TableColumn<Cours, Integer> nbrChapitress;
    @FXML
    private Button Reche;
    @FXML
    private TextField inputRech;
    @FXML
    private Button supp;
    @FXML
    private Button modiff;
    @FXML
    private Button ajcours;
    Coursservice ps = new Coursservice();

    //Coursservice ps =  Coursservice.getInstance();
    private ObservableList<Cours> data;
    Coursservice cs = new Coursservice();

    //Coursservice cs =  Coursservice.getInstance();
    ObservableList<Cours> obl = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Cours, String> descriptionss;
    @FXML
    private AnchorPane FenetreCoursGestion;
    @FXML
    private TableColumn<Cours, Integer> CategorieIDD;
    private Label labelcoursid;
    private Label labelutulisateurid;
    private Label labelcoursnom;
    private Label labelnbrchapitre;
    private Label labeldescription;
    private Label labelcategorieid;
    public ObservableList<Cours> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Coursservice pss = new Coursservice();
        // Coursservice pss = Coursservice.getInstance();
        ArrayList<Cours> c = new ArrayList<>();

        try {
            c = (ArrayList<Cours>) pss.afficherCours();
        } catch (SQLException ex) {
            System.out.println("erreurrrrrrrrrrrrr");
        }

        ObservableList<Cours> obs2 = FXCollections.observableArrayList(c);

        tableCours.setItems(obs2);
        coursIDD.setCellValueFactory(new PropertyValueFactory<>("coursID"));
        utilisateurIDD.setCellValueFactory(new PropertyValueFactory<>("utilisateurID"));
        nomCourss.setCellValueFactory(new PropertyValueFactory<>("nomCours"));
        nbrChapitress.setCellValueFactory(new PropertyValueFactory<>("nbrChapitres"));
        descriptionss.setCellValueFactory(new PropertyValueFactory<>("description"));
        CategorieIDD.setCellValueFactory(new PropertyValueFactory<>("categorieNom"));
                coursImgggg.setCellValueFactory(new PropertyValueFactory<>("coursImg"));


        try {
            list = FXCollections.observableArrayList(
                    pss.afficherCours()
            );
            //////////
            /*  Categorieservice ps = new Categorieservice();
        ArrayList<Categorie> a = new ArrayList<>();
        try {
            a = (ArrayList<Categorie>) ps.afficherCategorie();
        } catch (SQLException ex) {
        }
        ObservableList<Categorie> obsl = FXCollections.observableArrayList(a);

        table.setItems(obsl);
        categorieIDD.setCellValueFactory(new PropertyValueFactory<>("categorieID"));
        categorieNomm.setCellValueFactory(new PropertyValueFactory<>("categorieNom"));
        categorieImagee.setCellValueFactory(new PropertyValueFactory<>("categorieImage"));
        try {
            list = FXCollections.observableArrayList(
            ps.afficherCategorie()
        ); */
            //////////////
            FilteredList<Cours> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Cours>) Courss -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Courss.getNomCours().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Cours> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(tableCours.comparatorProperty());
                tableCours.setItems(sortedData);
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void ReturnAcceuilAjoutcategorie5(ActionEvent event) throws IOException {

        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 728, 671);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                       stage.setTitle("Gestion");

        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void PrecedentinterfaceCours(ActionEvent event) throws IOException {
        /* AnchorPane pane = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        FenetreCoursGestion.getChildren().setAll(pane);*/
        Parent page1 = FXMLLoader.load(getClass().getResource("DashbordFormateur.fxml"));
        Scene scene = new Scene(page1, 728, 671);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                               stage.setTitle("Gestion");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Rechercher(ActionEvent event) throws IOException {
    }

    @FXML
    private void supprimerCours(ActionEvent event) throws SQLException {
        if (event.getSource() == supp) {
            Cours cours = new Cours();
            cours.setCoursID(tableCours.getSelectionModel().getSelectedItem().getCoursID());
            // System.out.println(tableCours.getSelectionModel().getSelectedItem().getCoursID());

            Coursservice cs = new Coursservice();

            //  Coursservice cs = Coursservice.getInstance();
            cs.supprimerCours(cours);
            resetTableData();
        }

    }

    //////////
    @FXML
    private void modifierCours(ActionEvent event) throws IOException {
        Coursservice ps = new Coursservice();

//////////////////// singleont
        //Coursservice ps =  Coursservice.getInstance();
        Cours c = new Cours(tableCours.getSelectionModel().getSelectedItem().getCoursID(),
                tableCours.getSelectionModel().getSelectedItem().getUtilisateurID(),
                tableCours.getSelectionModel().getSelectedItem().getNomCours(),
                tableCours.getSelectionModel().getSelectedItem().getNbrChapitres(),
                tableCours.getSelectionModel().getSelectedItem().getDescription(),
 tableCours.getSelectionModel().getSelectedItem().getCoursImg(),

                tableCours.getSelectionModel().getSelectedItem().getCategorieNom());
        CoursGestionController.connectedCourss = c;
        /*System.out.println(c);
        // ps.setCours(c);
         */
        Parent page1 = FXMLLoader.load(getClass().getResource("CoursModifier.fxml"));
        Scene scene = new Scene(page1, 1144, 741);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
////////////////////////////////fin singleton ///////////////////////
        /* CoursModifierController c1 = new CoursModifierController();
        int id = tableCours.getSelectionModel().getSelectedItem().getCoursID();
        int id2 = tableCours.getSelectionModel().getSelectedItem().getUtilisateurID();
        int id3 = tableCours.getSelectionModel().getSelectedItem().getNbrChapitres();
        int id4 = tableCours.getSelectionModel().getSelectedItem().getCategorieID();

        Labelcoursid.setText(Integer.toString(id));
        Labelutulisateurid.setText(Integer.toString(id2));
        Labelnbrchapitre.setText(Integer.toString(id3));
        Labelcategorieid.setText(Integer.toString(id4));

        Labelcoursnom.setText(tableCours.getSelectionModel().getSelectedItem().getNomCours());

        Labeldescription.setText(tableCours.getSelectionModel().getSelectedItem().getDescription());
         */
        //confirmermodifier.setVisible(true);
    }

    @FXML
    private void AjouterCours(ActionEvent event) throws IOException, SQLException {
      //  String req = "select * from categorie c INNER JOIN cours c1 where c.categorieID =c1.categorieID";
        /*    String req = "select * from categorie";

      Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Categorie a = new Categorie(rst.getString("CategorieNom"));
            CoursGestionController.connectedCategorie = a;
            */
          
            
             Parent page1 = FXMLLoader.load(getClass().getResource("ajoutCours.fxml"));
            Scene scene = new Scene(page1, 1299, 884);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
         
      
            
                       
        
     }
    
      

    public void resetTableData() throws SQLDataException, SQLException {

        List<Cours> listCours = new ArrayList<>();
        listCours = cs.getAllCours();
        ObservableList<Cours> data = FXCollections.observableArrayList(listCours);
        tableCours.setItems(data);
    }
}
