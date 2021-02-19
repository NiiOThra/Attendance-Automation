package GUI.viewController.LoginView;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField userField;
    @FXML
    private JFXTextField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userField.setText("kell0825@easv365.dk");
        passwordField.setText("******");

    }

    @FXML
    public void prototypeStudentView(ActionEvent event){
        startStudentView("/GUI/viewController/StudentView/AttendanceView.fxml");
    }

    public void startStudentView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Attendance Automation: logged in as student");
            stage.setScene(new Scene(mainLayout));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void prototypeTeacherView()
    {
        startTeacherView("/GUI/viewController/TeacherView/ClassAttendanceView.fxml");
    }

    public void startTeacherView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Attendance Automation: logged in as teacher");
            stage.setScene(new Scene(mainLayout));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
