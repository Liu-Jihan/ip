import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import oscarl.OscarL;
import ui.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private OscarL oscar;
    private Ui ui = new Ui(); 

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/teddy.png"));
    private Image oscarImage = new Image(this.getClass().getResourceAsStream("/images/iu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().add(
                DialogBox.getOscarDialog(ui.getWelcomeMessage(), oscarImage)
        );
    }

    /**
     * Injects the OscarL instance.
     *
     * @param oscar The OscarL instance.
     */
    public void setOscar(OscarL oscar) {
        this.oscar = oscar;
    }

    /**
     * Handles user input by processing commands and displaying responses.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = (oscar != null) ? oscar.getResponse(input) : "OscarL is not initialized yet!";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOscarDialog(response, oscarImage)
        );

        userInput.clear();
    }
}
