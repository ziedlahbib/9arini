/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopFX.controller;

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
import workshodb.entities.Admin;
import workshodb.entities.Entrepreneur;
import workshodb.entities.Formateur;
import workshodb.entities.Membre;
import workshopdb.services.AdminService;
import workshopdb.services.EntrepreneurService;
import workshopdb.services.FormateurService;
import workshopdb.services.MembreService;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class ListeEntrepreneurController implements Initializable {

    @FXML
    private TableView<Entrepreneur> zd_tableEntrepreneur;
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
    private Button zd_retour;
    @FXML
    private Button zd_supp;
    private ObservableList<Entrepreneur> data;
    EntrepreneurService es = new EntrepreneurService();
    ObservableList<Entrepreneur> obl = FXCollections.observableArrayList();
    public ObservableList<Entrepreneur> list;
    @FXML
    private TextField text_recherche;

    /**
     * Initializes the controller class.
     */
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            try {
                // TODO
                EntrepreneurService es = new EntrepreneurService();
                ArrayList<Entrepreneur> e;
                e= (ArrayList<Entrepreneur>) es.getAllEntrepreneur();
                ObservableList obe = FXCollections.observableArrayList(e);
                
                zd_tableEntrepreneur.setItems(obe);
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
                Logger.getLogger(ListeEntrepreneurController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            EntrepreneurService esa = new EntrepreneurService();
            ArrayList<Entrepreneur> a = new ArrayList<>();
            a = (ArrayList<Entrepreneur>) esa.getAllEntrepreneur();
            ObservableList<Entrepreneur> art1 = FXCollections.observableArrayList(a);
            zd_tableEntrepreneur.setItems(art1);
            zd_utilisateurID.setCellValueFactory(new PropertyValueFactory<>("utilisateurID"));
            zd_utilisateurAdresseEmail.setCellValueFactory(new PropertyValueFactory<>("utilisateurAdresseEmail"));
            list = FXCollections.observableArrayList(
                    es.getAllEntrepreneur());
            FilteredList<Entrepreneur> filteredData = new FilteredList<>(list, e -> true);
            text_recherche.setOnKeyReleased(e -> {
                text_recherche.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Entrepreneur>) Entrepreneur -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (Entrepreneur.getUtilisateurAddressEmail().toLowerCase().contains(lower)) {
                            return true;
                        }
                        
                        return false;
                    });
                });
                SortedList<Entrepreneur> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(zd_tableEntrepreneur.comparatorProperty());
                zd_tableEntrepreneur.setItems(sortedData);
            });
        } catch (SQLException ex) {
            Logger.getLogger(ListeEntrepreneurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    @FXML
    private void supprimerMembre(ActionEvent event) throws SQLException {
         if (event.getSource()==zd_supp) {
            Entrepreneur m = new Entrepreneur();
            m.setUtilisateurID(zd_tableEntrepreneur.getSelectionModel().getSelectedItem().getUtilisateurID());
            System.out.println(zd_tableEntrepreneur.getSelectionModel().getSelectedItem().getUtilisateurID());
;
        EntrepreneurService es = new EntrepreneurService();
        es.supprimerEntrepreneur(m);
                     resetTableData();
        }
          }
    public void resetTableData() throws SQLDataException, SQLException{
    
        List<Entrepreneur> listEntrepreneur = new ArrayList<>();
        listEntrepreneur = es.getAllEntrepreneur();
        ObservableList<Entrepreneur> data = FXCollections.observableArrayList(listEntrepreneur);
        zd_tableEntrepreneur.setItems(data);
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/workshopFX/view/Acceuil.fxml"));
            Scene scene = zd_retour.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }
    
}
