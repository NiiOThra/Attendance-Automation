package Application.GUI.Controller;

import Application.BE.Student;
import Application.GUI.Model.AttendanceModel;
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
        userField.setText("scuphus0");
        passwordField.setText("zs7dCV6Qr");

        try {
            attendanceModel = new AttendanceModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void handleStudentView(ActionEvent event){
        startStudentView("/Application/GUI/View/AttendanceView.fxml");
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
    private void handleTeacherView(ActionEvent event)
    {
        startTeacherView("/Application/GUI/View/ClassAttendanceView.fxml");
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

    public void setAttendanceModel(AttendanceModel attendanceModel) throws IOException, SQLException {
        attendanceModel.getLoggedinStudent();
    }

    @FXML
    public void handleSendData(ActionEvent event) throws SQLException {
        Student stud = attendanceModel.logInStudent(userField.getText(), passwordField.getText());

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Application/GUI/View/AttendanceView.FXML"));

            root.setUserData(stud);

            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
