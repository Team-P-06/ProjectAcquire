package ProjectAcquire;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Controller for actions of FXMLUI.
 * All functions and variables that are used communicating with FXMLUI.fxml need a @FXML proceeding them.
 */
public class FXController {
    @FXML private Button TopLeftButton;
    @FXML private Label myLabel;
    @FXML private TextArea textArea;


    public void initialize(){}

    /**
     * Simple test action for clicking a button
     * @param event The button is pressed
     */
    @FXML
    private void buttonActionHandlerTest(ActionEvent event){
        TopLeftButton.setText("booped");
        myLabel.setText("I'm a label too");
    }
}
