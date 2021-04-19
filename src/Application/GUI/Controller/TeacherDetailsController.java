package Application.GUI.Controller;


import Application.BE.Attendance;
import Application.GUI.Model.AttendanceModel;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherDetailsController implements Initializable {

    private AttendanceModel attendanceModel;

    private ObservableList<Attendance> allStudents;

    @FXML
    private JFXComboBox<String> weekdays;
    @FXML
    private Label studentLbl;
    @FXML
    private Label studentInfoLbl;
    @FXML
    private TableView<Attendance> lstAllStudents;
    @FXML
    private TableColumn<Attendance, String> nameCol;
    @FXML
    private TableColumn<Attendance, Integer> attCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            attendanceModel = new AttendanceModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        allStudents = attendanceModel.getAllStudents();
        lstAllStudents.setItems(allStudents);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("stud"));
        attCol.setCellValueFactory(new PropertyValueFactory<>("attendance"));

        weekdays.getItems().add("Monday");
        weekdays.getItems().add("Tuesday");
        weekdays.getItems().add("Wednesday");
        weekdays.getItems().add("Thursday");
        weekdays.getItems().add("Friday");
        weekdays.setOnAction(event -> {
            try {
                getOffDays();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public void setStudentClicked(MouseEvent event){
        String studName = lstAllStudents.getSelectionModel().getSelectedItem().getStud().getName();
        studentLbl.setText("Student: " + studName);
    }

    public void getOffDays() throws SQLException {
        String studName = lstAllStudents.getSelectionModel().getSelectedItem().getStud().getName();
        String chosenDay = weekdays.getSelectionModel().getSelectedItem();
        int studentId = lstAllStudents.getSelectionModel().getSelectedIndex();
        int offDays = attendanceModel.getOffDay(studentId, chosenDay);
        studentInfoLbl.setText(studName + " has " + offDays + " off days on " + chosenDay);
    }

    public void handleExit(ActionEvent event){
        Platform.exit();
    }
}
