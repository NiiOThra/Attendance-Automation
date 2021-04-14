package Application.GUI.Controller;

import Application.BE.Student;
import Application.GUI.Model.AttendanceModel;
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
        userField.setText("mmagrannell3@topsy.com");
        passwordField.setText("P67TAm7djE");
    }

    @FXML
    public void handleStudentLogin(ActionEvent event) throws SQLException, IOException {
        LoginModel.getInstance().logInStudent(userField.getText(), passwordField.getText());

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/GUI/View/MyAttendanceView.FXML"));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void handleTeacherLogin(ActionEvent event) throws IOException, SQLException {
        LoginModel.getInstance().logInTeacher(userField.getText(), passwordField.getText());
        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/GUI/View/ClassAttendanceView.FXML"));

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    public void handleCloseApp(ActionEvent event) {
        Platform.exit();
    }
}
