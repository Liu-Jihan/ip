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

            // ✅ Inject OscarL into MainWindow
            MainWindow controller = fxmlLoader.getController();
            controller.setOscar(oscar);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("OscarL Task Manager");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
