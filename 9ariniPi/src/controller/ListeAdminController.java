/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.Admin;
import entities.Formateur;
import entities.Membre;
import java.security.NoSuchAlgorithmException;
import services.AdminService;
import services.FormateurService;
import services.MembreService;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class ListeAdminController implements Initializable {
public static Admin connectedAdmin;
    @FXML
    private TableView<Admin> zd_tableAdmin;
    @FXML
    private TableColumn<?, ?> zd_utilisateurID;
    @FXML
    private TableColumn<?, ?> zd_utilisateurPDP;
    @FXML
    private TableColumn<?, ?> zd_utilisateurPrenom;
    @FXML
    private TableColumn<?, ?> zd_utilisateurNom;
    @FXML
    private TableColumn<?, ?> zd_utilisateurGenre;
    @FXML
    private TableColumn<?, ?> zd_utilisateurDDN;
    @FXML
    private TableColumn<?, ?> zd_utilisateurAdresse;
    @FXML
    private TableColumn<?, ?> zd_utilisateurPays;
    @FXML
    private TableColumn<?, ?> zd_utilisateurphone;
    @FXML
    private TableColumn<?, ?> zd_utilisateurFonction;
    @FXML
    private TableColumn<?, ?> zd_utilisateurOrganisme;
    @FXML
    private TableColumn<?, ?> zd_utilisateurSavoirEtre;
    @FXML
    private TableColumn<?, ?> zd_utilisateurAdresseEmail;
    @FXML
    private TableColumn<?, ?> zd_utilisateurMDP;
    @FXML
    private TableColumn<?, ?> zd_utilisateurRole;
    @FXML
    private TableColumn<?, ?> zd_nomEntreprise;
    @FXML
    private TableColumn<?, ?> zd_EntrepreneurSiteWeb;
    @FXML
    private TableColumn<?, ?> zd_EntrepreneurUsage;
    @FXML
    private Button zd_rla;
    @FXML
    private Button zd_suppa;
    private ObservableList<Admin> data;
    AdminService as = new AdminService();
    ObservableList<Admin> obl = FXCollections.observableArrayList();
    public ObservableList<Admin> list;
    @FXML
    private TextField text_recherche;
    @FXML
    private Button zd_modfier;

    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                // TODO
                AdminService as = new AdminService();
                ArrayList<Admin> a;
                a= (ArrayList<Admin>) as.getAllAdmin();
                ObservableList oba = FXCollections.observableArrayList(a);
                zd_tableAdmin.setItems(oba);
                zd_utilisateurID.setCellValueFactory(new PropertyValueFactory<>("utilisateurID"));
                zd_utilisateurPDP.setCellValueFactory(new PropertyValueFactory<>("utilisateurPDP"));
                zd_utilisateurPrenom.setCellValueFactory(new PropertyValueFactory<>("utilisateurPrenom"));
                zd_utilisateurNom.setCellValueFactory(new PropertyValueFactory<>("utilisateurNom"));
                zd_utilisateurGenre.setCellValueFactory(new PropertyValueFactory<>("utilisateurGenre"));
                zd_utilisateurDDN.setCellValueFactory(new PropertyValueFactory<>("utilisateurDDN"));
                zd_utilisateurAdresse.setCellValueFactory(new PropertyValueFactory<>("utilisateurAdresse"));
                zd_utilisateurPays.setCellValueFactory(new PropertyValueFactory<>("utilisateurPays"));
                zd_utilisateurphone.setCellValueFactory(new PropertyValueFactory<>("utilisateurphone"));
                zd_utilisateurFonction.setCellValueFactory(new PropertyValueFactory<>("utilisateurFonction"));
                zd_utilisateurOrganisme.setCellValueFactory(new PropertyValueFactory<>("utilisateurOrganisme"));
                zd_utilisateurSavoirEtre.setCellValueFactory(new PropertyValueFactory<>("utilisateurSavoirEtre"));
                zd_utilisateurAdresseEmail.setCellValueFactory(new PropertyValueFactory<>("utilisateurAdresseEmail"));
                zd_utilisateurMDP.setCellValueFactory(new PropertyValueFactory<>("utilisateurMDP"));
                zd_utilisateurRole.setCellValueFactory(new PropertyValueFactory<>("utilisateurRole"));
                zd_nomEntreprise.setCellValueFactory(new PropertyValueFactory<>("nomEntreprise"));
                zd_EntrepreneurSiteWeb.setCellValueFactory(new PropertyValueFactory<>("EntrepreneurSiteWeb"));
                zd_EntrepreneurUsage.setCellValueFactory(new PropertyValueFactory<>("EntrepreneurUsage"));
            } catch (SQLException ex) {
                Logger.getLogger(ListeAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            AdminService asa = new AdminService();
            ArrayList<Admin> a = new ArrayList<>();
            a = (ArrayList<Admin>) asa.getAllAdmin();
            ObservableList<Admin> art1 = FXCollections.observableArrayList(a);
            zd_tableAdmin.setItems(art1);
            zd_utilisateurID.setCellValueFactory(new PropertyValueFactory<>("utilisateurID"));
            zd_utilisateurAdresseEmail.setCellValueFactory(new PropertyValueFactory<>("utilisateurAdresseEmail"));
            list = FXCollections.observableArrayList(
                    as.getAllAdmin());
            FilteredList<Admin> filteredData = new FilteredList<>(list, e -> true);
            text_recherche.setOnKeyReleased(e -> {
                text_recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Admin>) Admin -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Admin.getUtilisateurAddressEmail().toLowerCase().contains(lower)) {
                            return true;
                        }
                        
                        return false;
                    });
                });
                SortedList<Admin> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(zd_tableAdmin.comparatorProperty());
                zd_tableAdmin.setItems(sortedData);
            });
        } catch (SQLException ex) {
            Logger.getLogger(ListeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
@FXML
    private void supprimerAdmin(ActionEvent event) throws SQLException {
         if (event.getSource()==zd_suppa) {
            Admin m = new Admin();
            m.setUtilisateurID(zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurID());
            System.out.println(zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurID());
;
        AdminService as = new AdminService();
        as.supprimerAdmin(m);
                     resetTableData();
        }
          }
    public void resetTableData() throws SQLDataException, SQLException{
    
        List<Admin> listAdmin = new ArrayList<>();
        listAdmin = as.getAllAdmin();
        ObservableList<Admin> data = FXCollections.observableArrayList(listAdmin);
        zd_tableAdmin.setItems(data);
    }    
    @FXML
    private void retour(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/view/Acceuil.fxml"));
            Scene scene = zd_rla.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }

    @FXML
    private void mofiierAdmin(ActionEvent event) {
    
            
        
        int id = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurID();
        String Nom = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurNom();
        String Prenom = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurPrenom();
        String Pdp = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurPdp();
        String genre = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurGenre();
        String role = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurRole();
        String address = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurAddress();
        String pays = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurPays();
        String email = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurAddressEmail();
        java.util.Date ddn = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurDDN();
        String fonction = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurFonction();
        String org = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurOrganisme();
        int phone = zd_tableAdmin.getSelectionModel().getSelectedItem().getUtilisateurphone();
        Admin m1 = new Admin(id, phone, Pdp, Nom, Prenom, address, pays, genre, email, role, ddn);
        ListeAdminController.connectedAdmin = m1;
        try {
            
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/AjouterAdmin.fxml"));
            Scene scene2 = zd_modfier.getScene();
            scene2.setRoot(page2);
            Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage2.setScene(scene2);
            stage2.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
