package Application.GUI.Controller;

import Application.BE.Class;
import Application.GUI.Model.AttendanceModel;
import Application.GUI.Model.LoginModel;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyAttendanceController implements Initializable {

    private AttendanceModel attendanceModel;
    private ObservableList<String> allAbsenceDays;

    @FXML
    private Button checkInBtn;
    @FXML
    private Label nameField;
    @FXML
    private Label myAttendanceLbl;
    @FXML
    private JFXListView<String> lstAbsenceDays;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            attendanceModel = new AttendanceModel();

            LoginModel.getInstance();
            String name = LoginModel.getInstance().getLoggedinPerson().getName();
            nameField.setText(name);

            int loggedInStudent = LoginModel.getInstance().getLoggedinPerson().getId();
            Class course = attendanceModel.getTodayClass(loggedInStudent);
            if (attendanceModel.getTodayClass(loggedInStudent)== null){
                checkInBtn.setText("No class available right now.");
            } else checkInBtn.setText("Check in for " + course + " now");

            int myAttendance = attendanceModel.getAttendance(loggedInStudent);
            myAttendanceLbl.setText("Your attendance for this semester: " + myAttendance);
            allAbsenceDays = attendanceModel.getAbsenceDays(loggedInStudent);
            lstAbsenceDays.getItems().addAll(allAbsenceDays);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Handles the button where the student checks in for class. The class is already set for student and the student
     * can only check in for today.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void handleCheckIn(ActionEvent event) throws IOException, SQLException {
        int student = LoginModel.getInstance().getLoggedinPerson().getId();
        int course = attendanceModel.getTodayClass(student).getClassID();
        attendanceModel.checkIn(student, course);
        checkInBtn.setText("You're checked in for today!");
    }

    public void handleCloseApp(ActionEvent event){
        Platform.exit();
    }
}