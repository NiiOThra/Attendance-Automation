package Application.GUI.Controller;

import Application.BE.Class;
import Application.BE.Person;
import Application.GUI.Model.AttendanceModel;
import Application.GUI.Model.LoginModel;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassAttendanceController implements Initializable {

    private AttendanceModel attendanceModel;

    private ObservableList<Person> activeStudents;

    @FXML
    private TableView<Person> lstToday;
    @FXML
    private TableColumn<Person, String> checkedIn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private Label classLbl;
    @FXML
    private Label teacherinfo;


    public ClassAttendanceController() throws IOException, SQLException {
        attendanceModel = new AttendanceModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            attendanceModel = new AttendanceModel();
            LoginModel.getInstance();

            int teacherId = LoginModel.getInstance().getLoggedinPerson().getId();
            String teacherName = LoginModel.getInstance().getLoggedinPerson().getName();
            Class course = LoginModel.getInstance().getTeacherCourse(teacherId);
            classLbl.setText("Welcome to " + course);

            activeStudents = attendanceModel.getActiveStudents();
            lstToday.setItems(activeStudents);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            checkedIn.setCellValueFactory(new PropertyValueFactory<>("checkedIn"));

            int teacher2 = LoginModel.getInstance().getLoggedinPerson().getId();
            Class courseT = LoginModel.getInstance().getTeacherCourse(teacher2);
            handleOpenClass(teacher2, courseT);
            teacherinfo.setText("Welcome to class " + teacherName + ".\nYou teach " +course + " from 9-12." +
                    "\nOn the right, you can see a list\nof who checked in and who didn't yet.");

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * First check what teacher is logged in and this loggedin teacher's class. Then opens the class and sets the text.
     * @throws SQLException
     * @throws IOException
     */
    public void handleOpenClass(int teacher, Class course) throws SQLException, IOException {
        attendanceModel.openClass(teacher, course);
    }

    public void getSpecificStudentInfo() {
        showSpecificStudentInfo("/Application/GUI/View/SpecificStudentAttendanceView.fxml");
    }

    public void showSpecificStudentInfo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Attendance Automation: Student details");
            stage.setScene(new Scene(mainLayout));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleExit(ActionEvent event){
        Platform.exit();
    }
}

