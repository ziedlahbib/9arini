/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopFX.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import sun.misc.IOUtils;
import static sun.security.krb5.Confounder.bytes;
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
public class InscriptionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection connexion;
    @FXML
    private Button zd_upload;
    @FXML
    private Button zd_CInscription;
    @FXML
    private Button zd_annulerInscription;
    @FXML
    private ImageView zd_Pdp;
    @FXML
    private Label zd_Pdppath;
    @FXML
    private Label zd_Pdpnom;
    @FXML
    private Label zd_Rolel;
    @FXML
    private Label zd_LOrg;
    @FXML
    private Label zd_LFonction;
    @FXML
    private Label zd_LSoftSkills;
    @FXML
    private Label zd_Lnomeentrprise;
    @FXML
    private Label zd_LsitewebEntreprise;
    @FXML
    private Label zd_LEntrpreneurUsage;
    @FXML
    private TextField zd_nom;
    @FXML
    private TextField zd_prenom;
    @FXML
    private TextField zd_adresse;
    @FXML
    private TextField zd_pays;
    @FXML
    private ToggleGroup zd_genre;
    @FXML
    private TextField zd_Email;
    @FXML
    private TextField zd_Mdp;
    @FXML
    private TextField zd_CMdp;
    @FXML
    private TextField zd_numtel;
    @FXML
    private DatePicker zd_DDN;
    @FXML
    private ComboBox<String> zd_Role;
    @FXML
    private TextField zd_Org;
    @FXML
    private TextField zd_Fonction;
    @FXML
    private TextArea zd_softskills;
    @FXML
    private TextField zd_nomeentrprise;
    @FXML
    private TextField zd_sitewebEntreprise;
    @FXML
    private TextField zd_EntrpreneurUsage;
    @FXML
    private Label zd_LCmdp;
    @FXML
    private RadioButton zd_homme;
    @FXML
    private RadioButton zd_femme;
    @FXML
    private Button zd_APdp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        ObservableList<String> list_ne = FXCollections.observableArrayList("Formateur", "Membre", "Admin", "Entrepreneur");
        zd_Role.setItems(list_ne);
        MembreService ms = new MembreService();
        AdminService as = new AdminService();
        FormateurService fs = new FormateurService();
        EntrepreneurService es = new EntrepreneurService();
        String Membre = new String("Membre");
        String Admin = new String("Admin");
        String Formateur = new String("Formateur");
        String Entrepreneur = new String("Entrepreneur");

        zd_Role.setOnAction(e -> {
            if (Membre.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que membre :) ");
                zd_LOrg.setVisible(true);
                zd_LFonction.setVisible(true);
                zd_Org.setVisible(true);
                zd_Fonction.setVisible(true);
                zd_LSoftSkills.setVisible(false);
                zd_softskills.setVisible(false);
                zd_Lnomeentrprise.setVisible(false);
                zd_LsitewebEntreprise.setVisible(false);
                zd_LEntrpreneurUsage.setVisible(false);
                zd_nomeentrprise.setVisible(false);
                zd_sitewebEntreprise.setVisible(false);
                zd_EntrpreneurUsage.setVisible(false);
            } else if (Formateur.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que Formateur :) ");
                zd_LOrg.setVisible(true);
                zd_LFonction.setVisible(true);
                zd_Org.setVisible(true);
                zd_Fonction.setVisible(true);
                zd_LSoftSkills.setVisible(true);
                zd_softskills.setVisible(true);
                zd_Lnomeentrprise.setVisible(false);
                zd_LsitewebEntreprise.setVisible(false);
                zd_LEntrpreneurUsage.setVisible(false);
                zd_nomeentrprise.setVisible(false);
                zd_sitewebEntreprise.setVisible(false);
                zd_EntrpreneurUsage.setVisible(false);
            } else if (Admin.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que Admin :) ");
                zd_LOrg.setVisible(false);
                zd_LFonction.setVisible(false);
                zd_Org.setVisible(false);
                zd_Fonction.setVisible(false);
                zd_LSoftSkills.setVisible(false);
                zd_softskills.setVisible(false);
                zd_Lnomeentrprise.setVisible(false);
                zd_LsitewebEntreprise.setVisible(false);
                zd_LEntrpreneurUsage.setVisible(false);
                zd_nomeentrprise.setVisible(false);
                zd_sitewebEntreprise.setVisible(false);
                zd_EntrpreneurUsage.setVisible(false);
            } else if (Entrepreneur.equals(zd_Role.getValue())) {
                zd_Rolel.setText("completer le formulaire pour terminer l'inscription en tant que Enrepreneur :) ");
                zd_LOrg.setVisible(false);
                zd_LFonction.setVisible(false);
                zd_Org.setVisible(false);
                zd_Fonction.setVisible(false);
                zd_LSoftSkills.setVisible(false);
                zd_softskills.setVisible(false);
                zd_Lnomeentrprise.setVisible(true);
                zd_LsitewebEntreprise.setVisible(true);
                zd_LEntrpreneurUsage.setVisible(true);
                zd_nomeentrprise.setVisible(true);
                zd_sitewebEntreprise.setVisible(true);
                zd_EntrpreneurUsage.setVisible(true);
            }

        });

        zd_CInscription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                java.util.Date date
                        = java.util.Date.from(zd_DDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                //ToggleGroup genre = new ToggleGroup();

                //int n=count();
                // if (n == 0) {
                if (zd_Role.getValue().equals("")) {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("Please fill all fields ");
                    a.setHeaderText(null);
                    a.showAndWait();
                } else {
                    try {
                        boolean verifMail = true;
                        for (int i = 0; i < ms.getAllMembre().size(); i++) {
                           if ((ms.getAllMembre().get(i).getUtilisateurAddressEmail()).equals(zd_Email.getText()) );
                            verifMail = false;
                           break;
                        }
                        //if (ms.getAllMembre().ge) {
                    } catch (SQLException ex) {
                    }
                    
                        if ((zd_Mdp.getText().equals(zd_CMdp.getText()))) {
                            if (Membre.equals(zd_Role.getValue())) {
                                if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                        || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                        || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("")) {
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("Please fill all fields ");
                                    a.setHeaderText(null);
                                    a.showAndWait();

                                } else {
                                    try {
                                        ms.ajouterMembre(new Membre(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                                zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                                zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), sqlDate));
                                        Alert a = new Alert(Alert.AlertType.WARNING);
                                        a.setContentText("Bienvenue dans notre platform 9arini ");
                                        a.setHeaderText(null);
                                        a.showAndWait();

                                    } catch (SQLException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            } else if (Admin.equals(zd_Role.getValue())) {
                                if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                        || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                        || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("")) {
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("Please fill all fields ");
                                    a.setHeaderText(null);
                                    a.showAndWait();

                                } else {
                                    try {
                                        as.ajouterAdmin(new Admin(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                                zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                                zd_Mdp.getText(), zd_Role.getValue(), sqlDate));
                                        Alert a = new Alert(Alert.AlertType.WARNING);
                                        a.setContentText("Bienvenue dans notre platform 9arini ");
                                        a.setHeaderText(null);
                                        a.showAndWait();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            } else if (Formateur.equals(zd_Role.getValue())) {
                                if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                        || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                        || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("")) {
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("Please fill all fields ");
                                    a.setHeaderText(null);
                                    a.showAndWait();

                                } else {
                                    try {
                                        fs.ajouterFormateur(new Formateur(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                                zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                                zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_softskills.getText(), sqlDate));
                                        Alert a = new Alert(Alert.AlertType.WARNING);
                                        a.setContentText("Bienvenue dans notre platform 9arini ");
                                        a.setHeaderText(null);
                                        a.showAndWait();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            } else if (Entrepreneur.equals(zd_Role.getValue())) {
                                if (zd_nom.getText().equals("") || zd_prenom.getText().equals("")
                                        || zd_numtel.getText().equals("") || zd_adresse.getText().equals("")
                                        || zd_pays.getText().equals("") || zd_Email.getText().equals("") || zd_Mdp.getText().equals("") || zd_CMdp.getText().equals("")) {
                                    Alert a = new Alert(Alert.AlertType.WARNING);
                                    a.setContentText("Please fill all fields ");
                                    a.setHeaderText(null);
                                    a.showAndWait();

                                } else {
                                    try {
                                        es.ajouterEntrepreneur(new Entrepreneur(Integer.parseInt(zd_numtel.getText()), zd_Pdppath.getText(), zd_nom.getText(), zd_prenom.getText(),
                                                zd_adresse.getText(), zd_pays.getText(), ((RadioButton) zd_genre.getSelectedToggle()).getText(), zd_Email.getText(),
                                                zd_Mdp.getText(), zd_Role.getValue(), zd_Org.getText(), zd_Fonction.getText(), zd_nomeentrprise.getText(), zd_sitewebEntreprise.getText(), zd_EntrpreneurUsage.getText(), sqlDate));
                                        Alert a = new Alert(Alert.AlertType.WARNING);
                                        a.setContentText("Bienvenue dans notre platform 9arini ");
                                        a.setHeaderText(null);
                                        a.showAndWait();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            }

                        } else {
                            zd_LCmdp.setText("les mot de passe doivent etre identique");
                        }
                    }
                }

                /*  } else {
                        System.out.println("ce email existe");
                    }*/
            }

        });

        /*
        zd_upload.setOnAction(e -> {
            try {
                String path = zd_Pdppath.getText();
                String fileName = zd_Pdpnom.getText();
                Socket soc = null;
                BufferedImage img = null;
                try {
                    soc = new Socket("image", 80);
                } catch (IOException ex) {
                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Client is running. ");
                try {
                    System.out.println("Reading image from disk. ");
                    img = ImageIO.read(new File(zd_Pdppath.getText()));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    
                    ImageIO.write(img, "jpg", baos);
                    baos.flush();
                    
                    byte[] bytes = baos.toByteArray();
                    baos.close();
                    
                    System.out.println("Sending image to server. ");
                    
                    OutputStream out = soc.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    
                    dos.writeInt(bytes.length);
                    dos.write(bytes, 0, bytes.length);
                    
                    System.out.println("Image sent to server. ");
                    
                    dos.close();
                    out.close();
                    
                } catch (IOException ex) {
                    System.out.println("Exception: " + ex.getMessage());
                    try {
                        soc.close();
                    } catch (IOException exe) {
                        Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    soc.close();
                } catch (IOException ex) {
                    Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                // Creating an object of ServerSocket class
                // in the main() method  for socket connection
                ServerSocket ss = new ServerSocket(6666);
                
                // Establishing a connection
                Socket sock = ss.accept();
                File tempFile = new File(path, fileName);
                
                // Invoking input stream via getInputStream()
                // method by creating DataInputStream class
                // object
                DataInputStream is
                        = new DataInputStream(soc.getInputStream());
                BufferedInputStream bis = new BufferedInputStream(is);
                try (DataInputStream dis = new DataInputStream(bis)) {
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                        img = ImageIO.read(new File(zd_Pdppath.getText()));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    
                    ImageIO.write(img, "jpg", baos);
                    baos.flush();
                    
                    byte[] bytes = baos.toByteArray();
                    baos.close();
                        OutputStream out = sock.getOutputStream();
                    
                    
                    
                    fos.write(bytes, 0, bytes.length);
                    }
                    
                    String str = (String)is.readUTF();
                    
                    // Display the string on the console
                    System.out.println("message= " + str);
                    
                    // Lastly close the socket using standard close
                    // method to release memory resources
                    ss.close();
                }
                
                // Catch block to handle the exceptions
                catch (Exception exe) {
                    
                    // Display the exception on the console
                    System.out.println(e);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        }
        );*/
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) throws FileNotFoundException {

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
            zd_Pdp.setImage(image);
            zd_Pdp.setFitWidth(200);
            zd_Pdp.setFitHeight(200);
            zd_Pdp.scaleXProperty();
            zd_Pdp.scaleYProperty();
            zd_Pdp.setSmooth(true);
            zd_Pdp.setCache(true);
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
        zd_Pdppath.setText(file.getAbsolutePath());
        zd_Pdpnom.setText(file.getName());
    }

    @FXML
    private void annulerInscription(ActionEvent event) throws IOException {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/workshopFX/view/Authentification.fxml"));
            Scene scene = zd_annulerInscription.getScene();
            scene.setRoot(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
        }
    }
    /*
    @FXML
    private int count() throws IOException, SQLException, NoSuchAlgorithmException {
        String req = "SELECT count(*) from utilisateur WHERE utilisateurAdresseEmail LIKE '" + zd_Email.getText() + "'";
        Statement stm = connexion.createStatement();
        ResultSet rst = stm.executeQuery(req);
        int verif = rst.getInt(0);

        return verif;

    }*/
}
