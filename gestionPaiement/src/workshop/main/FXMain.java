package workshop.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Aminous
 */
public class FXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/workshopfx/gui/Authentification.fxml"))));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/workshopfx/gui/9ariniLogo.png")));
        primaryStage.setTitle("9arini");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
