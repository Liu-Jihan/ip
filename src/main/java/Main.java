import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oscarl.OscarL;

import java.io.IOException;

public class Main extends Application {
    private OscarL oscar = new OscarL("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();

            assert root != null : "FXML file should be loaded successfully";

            // Inject OscarL into MainWindow
            MainWindow controller = fxmlLoader.getController();
            assert controller != null : "Controller should not be null";

            controller.setOscar(oscar);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("OscarL Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "IOException occurred while loading FXML";
        }
    }


    public static void main(String[] args) {
        assert args != null : "Program arguments should not be null";
        launch(args);
    }

}
