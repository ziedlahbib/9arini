/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DB.DB;
import controller.AuthentificationController;
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
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
public class RechercheCoursController implements Initializable {

    @FXML
    private Button buttonrechfilrech;
    @FXML
    private AnchorPane FentreRechCours;
    @FXML
    private Label descriptioninrecherchee;
    @FXML
    private ImageView imginsearch;
    @FXML
    private Label labelpathimg;
    @FXML
    private TextField mail_user;
    @FXML
    private Hyperlink profilllll;

    public RechercheCoursController() throws IOException, SQLException, NoSuchAlgorithmException {

        connexion = DB.getInstance().getConnection();
    }
    Connection connexion;

    @FXML
    private Hyperlink ReturnAcceuilcateg5;
    @FXML
    private Hyperlink retourrrrr;
    @FXML
    private Label LableCourss;
    @FXML
    private TextField searchfisearch;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        profilllll.setText(AuthentificationController.connectedUser.getUtilisateurNom());

        LableCourss.setText(EspaceEtudiantController.connectedCours.getNomCours());
        descriptioninrecherchee.setText(EspaceEtudiantController.connectedCours.getDescription());
        labelpathimg.setText(EspaceEtudiantController.connectedCours.getCoursImg());

        FileChooser fileChooser = new FileChooser();
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
        File file = new File(labelpathimg.getText());
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imginsearch.setImage(image);
            imginsearch.setFitWidth(200);
            imginsearch.setFitHeight(200);
            imginsearch.scaleXProperty();
            imginsearch.scaleYProperty();
            imginsearch.setSmooth(true);
            imginsearch.setCache(true);
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

    @FXML
    private void ReturnAcceuilRechCours(ActionEvent event) {
    }

    @FXML
    private void PrecedentinterfaceRechcours(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("FormateurEtudiant.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void SearchfiSearch(ActionEvent event) throws SQLException {
        String req = "SELECT * from cours WHERE nomCours LIKE '" + searchfisearch.getText() + "'";
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
    private void GotoceCours(ActionEvent event) throws IOException, Exception {

        Parent page5 = FXMLLoader.load(getClass().getResource("Cours.fxml"));
        Scene scene = new Scene(page5, 1177, 899);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
sendMail("fares.moalla1996@gmail.com");
    }
   /////////////////////
       public  void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "fares.moalla1996@gmail.com";
        //Your gmail password
        String password = "Fares5683@";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
               return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
            
            
});
           

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }
    /////////////////
      private  Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
         //  OffreTravail ol = list.getItems().get(list.getSelectionModel().getSelectedIndex());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Vous etes inscrit au cours"+EspaceEtudiantController.connectedCours.getNomCours());
            message.setText("bienvenue");
            return message;
        } catch (Exception ex) {
        }
        return null;
    }
    
    
    //////////////////// 
    
    
    
    
    
    
    
    
    
    
    
    
    

}
