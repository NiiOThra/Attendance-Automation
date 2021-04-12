package Application.GUI.Controller;

import Application.BE.Student;
import Application.GUI.Model.AttendanceModel;
import Application.GUI.Model.LoginModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    AttendanceModel attendanceModel;

    @FXML
    private JFXTextField userField;
    @FXML
    private JFXPasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userField.setText("mwatling0@globo.com");
        passwordField.setText("GVYJI0i6");

        try {
            attendanceModel = new AttendanceModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void handleStudentLogin(ActionEvent event) throws SQLException, IOException {
        LoginModel.getInstance().logInStudent(userField.getText(), passwordField.getText());

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/GUI/View/AttendanceView.FXML"));

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
}
