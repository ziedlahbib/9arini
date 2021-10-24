/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopFX.controller;

import com.mysql.jdbc.Connection;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import workshodb.entities.Article;
import workshodb.entities.Reclamation;
import workshopdb.services.ArticleService;
import workshopdb.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author yahia
 */
public class ReclamationController implements Initializable {

    Connection cnx;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private Button btn_ajout_reclamation;
    @FXML
    private Label username;
    @FXML
    private TableColumn<?, ?> titre;
    @FXML
    private TableColumn<?, ?> rep;
    @FXML
    private TextArea message;
    @FXML
    private ComboBox<?> type_rec;
    @FXML
    private TextField titre_rec;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button supprimer_btn;

    private ObservableList<Reclamation> data;
    ReclamationService cs = new ReclamationService();
    // ObservableList<Reclamation> rec1 = FXCollections.observableArrayList();
    @FXML
    private TextField formation;
    @FXML
    private TextField cour;
    @FXML
    private TextField formateur;
    @FXML
    private TextField utilisateur;
    @FXML
    private TextField etat2;
    @FXML
    private TableColumn<?, ?> date;
    @FXML
    private TableColumn<?, ?> message2;
    @FXML
    private TableColumn<?, ?> etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReclamationService reclamationService = new ReclamationService();
        ArrayList<Reclamation> r = new ArrayList<>();

        try {
            r = (ArrayList<Reclamation>) reclamationService.getAllReclamationParId(34);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Reclamation> rec1 = FXCollections.observableArrayList(r);
        table.setItems(rec1);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        message2.setCellValueFactory(new PropertyValueFactory<>("message"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    }

    @FXML
    private void ajouter_reclamation(ActionEvent event) {

        ReclamationService reclamationservice = new ReclamationService();
        if (message.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {

            try {

                Reclamation r = new Reclamation(Integer.parseInt(cour.getText()),
                        Integer.parseInt(utilisateur.getText()),
                        Integer.parseInt(formateur.getText()), 
                        Integer.parseInt(formation.getText()),
                        etat2.getText(),
                        message.getText(),
                        titre_rec.getText() 
            );
                reclamationservice.ajouterReclamation(r);
                            resetTableData();


            } catch (SQLException ex) {
                System.out.println("Reclamation Ajouté");
            }

        }

    }

    public void resetTableData() throws SQLDataException, SQLException {

        List<Reclamation> listReclamation = new ArrayList<>();
        listReclamation = cs.getAllReclamation();
        ObservableList<Reclamation> data = FXCollections.observableArrayList(listReclamation);
        table.setItems(data);
    }

    @FXML
    private void updateReclamation(ActionEvent event) {
    }

    @FXML
    private void supprimer_rec(ActionEvent event) throws SQLException {

        if (event.getSource() == supprimer_btn) {
            Reclamation reclamation = new Reclamation();
            reclamation.setReclamationid(table.getSelectionModel().getSelectedItem().getReclamationid());

            System.out.println(table.getSelectionModel().getSelectedItem().getReclamationid());

            ReclamationService reclamationService = new ReclamationService();
            reclamationService.supprimerReclamation(reclamation);
            resetTableData();
        }

    }

}
