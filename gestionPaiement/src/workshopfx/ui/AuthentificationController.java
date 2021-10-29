package workshopfx.ui;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.Node;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import workshopdb.utils.db;
import workshopnb.entities.Membre;
import workshopnb.entities.Utilisateur;

/**
 * FXML Controller class
 *
 * @author lahbib
 */
public class AuthentificationController implements Initializable {

    @FXML
    private Button zd_ok;
    private TextField zd_numtel;
    @FXML
    private Hyperlink zd_mdpoublier;
    @FXML
    private Label kkkk;

    public AuthentificationController(TextField AcceuilEmail, TextField AcceuilPasswd) {
        this.AcceuilEmail = AcceuilEmail;
        this.AcceuilPasswd = AcceuilPasswd;
    }

    public AuthentificationController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = db.getInstance().getConnection();
    }
    Connection connexion;
    public static Utilisateur connectedUser;
    @FXML
    private TextField zd_codev;
    private Label zd_lcodev;
    @FXML
    private Button btn_AcceuilInscription;
    @FXML
    private Button btn_AcceuilConnexion;
    @FXML
    private TextField AcceuilEmail;
    @FXML
    private TextField AcceuilPasswd;

    private TextField zd_emailcon = AcceuilEmail;
    private TextField zd_Passwdconn = AcceuilPasswd;

    public TextField getZd_emailcon() {
        return zd_emailcon;
    }

    public TextField getZd_Passwdconn() {
        return zd_Passwdconn;
    }
    public static final String ACCOUNT_SID = "AC04fedb666177e902b410a42d0b4614b9";
    public static final String AUTH_TOKEN = "e19969ecb5f0279d8539e03f2c414f40";

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException, NoSuchAlgorithmException {
        int rowCount1 = 0;
        Statement stm1 = connexion.createStatement();
        String req1 = "SELECT count(*) as rowCount1 from utilisateur WHERE utilisateurAdresseEmail LIKE '" + AcceuilEmail.getText() + "' and utilisateurMDP LIKE '" + hashmdp(AcceuilPasswd.getText()) + "' ";
        ResultSet rp1 = stm1.executeQuery(req1);
        if (rp1.next()) {
            rowCount1 = rp1.getInt("rowcount1");
        }
        System.out.println(rowCount1);
        if (rowCount1 == 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("warning !! ");
            alert.setHeaderText(null);
            alert.setContentText("email ou mot de passe non valide");
            alert.show();

        } else {

            String req = "SELECT * from utilisateur WHERE utilisateurAdresseEmail LIKE '" + AcceuilEmail.getText() + "' and utilisateurMDP LIKE '" + hashmdp(AcceuilPasswd.getText()) + "' ";

            Statement stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {

                Membre p = new Membre(rst.getInt("utilisateurID"),
                        rst.getString("utilisateurPdp"),
                        rst.getString("utilisateurNom"),
                        rst.getString("utilisateurPrenom"));
                AuthentificationController.connectedUser = p;
                String codev = zd_codev.getText();
                zd_codev.setVisible(true);
                kkkk.setVisible(true);
                zd_ok.setVisible(true);
                AcceuilEmail.setVisible(false);
                zd_mdpoublier.setVisible(false);
                AcceuilPasswd.setVisible(false);
                btn_AcceuilConnexion.setVisible(false);

            }

        }
    }

    @FXML
    private void ok(ActionEvent event) throws IOException {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Parent page1 = FXMLLoader.load(getClass().getResource("/workshopfx/gui/AjouterCommande.fxml"));
            Scene scene1 = new Scene(page1, 1058, 817);
            Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException ex) {
        }
    }

    private String hashmdp(String mdp) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
