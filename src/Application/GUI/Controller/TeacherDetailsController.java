package Application.GUI.Controller;


import Application.BE.Attendance;
import Application.GUI.Model.AttendanceModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
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
    private ObservableList<String> allAbsenceDays;

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
    @FXML
    private JFXListView<String> lstAbsenceDays;
    @FXML
    private Label studentInfoLbl1;
    @FXML
    private JFXButton absentDayBtn;

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
        attCol.setSortType(TableColumn.SortType.ASCENDING);
        lstAllStudents.getSortOrder().add(attCol);
        lstAllStudents.sort();

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

    /**
     * Reacts if you click on a specific student in the list of students. You can choose a student to get a view of the
     * specific student, in regards to absence and if there's any day in the week he/she is particular absence.
     * @param event
     * @throws SQLException
     */
    public void setStudentClicked(MouseEvent event) throws SQLException {
        lstAbsenceDays.getItems().clear();
        String studName = lstAllStudents.getSelectionModel().getSelectedItem().getStud().getName();
        int studentId = lstAllStudents.getSelectionModel().getSelectedItem().getId();
        allAbsenceDays = attendanceModel.getAbsenceDays(studentId);
        int absence = attendanceModel.getAbsence(studentId);

        studentLbl.setText("Student: " + studName);
        lstAbsenceDays.getItems().addAll(allAbsenceDays);
        studentInfoLbl1.setText("Total percent of absence: " + absence + "%");
    }

    /**
     * Just to set the studentInfoLbl.
     * @throws SQLException
     */
    public void getOffDays() throws SQLException {
        String studName = lstAllStudents.getSelectionModel().getSelectedItem().getStud().getName();
        String chosenDay = weekdays.getSelectionModel().getSelectedItem();
        int studentId = lstAllStudents.getSelectionModel().getSelectedItem().getId();
        int offDays = attendanceModel.getOffDay(studentId, chosenDay);

        studentInfoLbl.setText(studName + " has " + offDays + " days of absence on " + chosenDay);
    }

    /**
     * A tool for the teacher to update an off day for the student.
     * @param event
     * @throws SQLException
     */
    public void handleAbsentDay(ActionEvent event) throws SQLException {
        String date = lstAbsenceDays.getSelectionModel().getSelectedItem();
        int student = lstAllStudents.getSelectionModel().getSelectedItem().getId();

        attendanceModel.updateAbsentDay(student, date);
        lstAbsenceDays.getItems().remove(date);
        absentDayBtn.setText("Absent day " + date + " for student updated.");
    }

    /**
     * Just a button to exit the application.
     * @param event
     */
    public void handleExit(ActionEvent event){
        Platform.exit();
    }
}
