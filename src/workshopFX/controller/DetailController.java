/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshopFX.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
//import javafx.scene.control.TextFlow; 
import javafx.scene.text.TextFlow ; 
import javafx.stage.Stage;
import workshodb.entities.Article;
import workshodb.entities.Comment;
import workshodb.entities.Reclamation;
import workshopdb.services.ArticleService;
import workshopdb.services.CommentService;
import workshopdb.services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author yahia
 */
public class DetailController implements Initializable {

    @FXML
    private TableView<Comment> table_comm;
    @FXML
    private TableColumn<?, ?> list_comm;
    @FXML
    private Button bnt_comm;
    @FXML
    private TextArea comm;
    @FXML
    private TextField text_object;
    @FXML
    private TextArea text_contenue;
    @FXML
    private Label articleid;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ArticleService as = ArticleService.getInstance();
        System.out.println("article : "+as.getArticle());
        Article a = as.getArticle();
        text_contenue.setText(a.getContent());
        text_object.setText(a.getObject()) ;
        
        
        
        CommentService commentService = new CommentService();
        ArrayList<Comment> c = new ArrayList<>();

        try {
            c = (ArrayList<Comment>) commentService.getAllCommentForArticleId(as.getArticle().getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Comment> com = FXCollections.observableArrayList(c);
        table_comm.setItems(com);
        list_comm.setCellValueFactory(new PropertyValueFactory<>("content"));
        
        articleid.setText(String.valueOf(as.getArticle().getId()));
        
        

    }    
/*
    @FXML
    private void ajouter_comm(ActionEvent event) {
        ArticleService as = ArticleService.getInstance();
      CommentService commentService = new CommentService();
        if( comm.getText().equals(""))
        { 
               Alert a=new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
         a.setHeaderText(null);
         a.showAndWait();
            
        }
             
        else{
           Comment c=new Comment(comm.getText() ,Integer.parseInt(articleid.getText() ));   
             try {
                 commentService.ajouterComment(c);
             } catch (SQLException ex) {
                  System.out.println("Catégorie ajouté");
             }
       
        }   
    } */

    @FXML
    private void gobackarticle(ActionEvent event) throws IOException {
       
   Parent page1=FXMLLoader.load(getClass().getResource("articleUI.fxml"));
    Scene scene=new Scene(page1);
   Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
                    
    }
    
    @FXML
     private void ajouter_comment(ActionEvent event) {
        CommentService commentService = new CommentService();
         ArticleService as = ArticleService.getInstance();

       // ArticleService articleService = ArticleService.getInstance();
        if (comm.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Please fill all fields ");
            a.setHeaderText(null);
            a.showAndWait();

        } else {

            try {

                Comment co = new Comment(Integer.parseInt(articleid.getText()), comm.getText());
                 System.out.println(co.getArticleId());        

                commentService.ajouterCommentparid(co);

            } catch (SQLException ex) {
                System.out.println("comment Ajouté");
            }

        }
        
       
   

}

}


 