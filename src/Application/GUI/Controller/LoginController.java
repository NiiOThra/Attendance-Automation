package Application.GUI.Controller;

import Application.GUI.Model.LoginModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField userField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private Button closeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userField.setText("nkildahl4@tumblr.com");
        passwordField.setText("lGmd5yh");
    }

    /**
     * Handles the login button. First we get a instance of the person who is trying to login. After that we get the type
     * so we can determine whether we go to one side or the other of the user interface.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    public void handleLogin(ActionEvent event) throws IOException, SQLException {
        LoginModel.getInstance().loginPerson(userField.getText(), passwordField.getText());

        int type = LoginModel.getInstance().getLoggedinPerson().getType();
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        if (type == 1){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/GUI/View/MyAttendanceView.FXML"));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }
        else if (type == 0){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/GUI/View/ClassAttendanceView.FXML"));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }
    }

    /**
     * Just a button to exit the application.
     * @param event
     */
    public void handleCloseApp(ActionEvent event) {
        Platform.exit();
    }
}
