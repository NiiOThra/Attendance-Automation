package Application.GUI.Controller;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.GUI.Model.AttendanceModel;
import Application.GUI.Model.LoginModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClassAttendanceController implements Initializable {

    private AttendanceModel attendanceModel;

    private ObservableList<Person> activeStudents;
    private ObservableList<Attendance> attendanceList;

    @FXML
    private TableView<Person> lstToday;
    @FXML
    private TableColumn<Attendance, String> nameColumn;
    @FXML
    private TableColumn<Attendance, Integer> attendanceColumn;
    @FXML
    private PieChart pieChart;
    @FXML
    private Text teacherlbl;
    @FXML
    private Button openBtn;

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
            teacherlbl.setText(teacherName + " please open class: " + course + " for today.");

            activeStudents = attendanceModel.getActiveStudents();
            lstToday.setItems(activeStudents);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

            attendanceList = attendanceModel.getAttendanceList();

            int teacher2 = LoginModel.getInstance().getLoggedinPerson().getId();
            Class courseT = LoginModel.getInstance().getTeacherCourse(teacher2);
            handleOpenClass(teacher2, courseT);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        /**lstAttendance.setItems(attendanceList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("stud"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("Attendance"));
        nameColumn.setSortType(TableColumn.SortType.DESCENDING);
        lstAttendance.getSortOrder().add(attendanceColumn);
        lstAttendance.sort();

        lstAttendance.setItems(attendanceList);**/
    }

    /**
     * First check what teacher is logged in and this loggedin teacher's class. Then opens the class and sets the text.
     * @throws SQLException
     * @throws IOException
     */
    public void handleOpenClass(int teacher, Class course) throws SQLException, IOException {
        attendanceModel.openClass(teacher, course);
        openBtn.setText("Class is open!");
    }

    public void getSpecificStudentInfo() {
        showSpecificStudentInfo("/gui/view/SpecificStudentAttendanceView.fxml");
    }

    public void showSpecificStudentInfo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Attendance Automation: student details");
            stage.setScene(new Scene(mainLayout));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**public void handleShowChart(ActionEvent event) {
     int selectedIndex = lstClasses.getSelectionModel().getSelectedIndex();
     ObservableList<PieChart.Data> pieChartITO = FXCollections.observableArrayList();
     ObservableList<PieChart.Data> pieChartSCO = FXCollections.observableArrayList();

     if (selectedIndex == 0) {
     pieChart.getData().removeAll(pieChartSCO);
     pieChartITO.add(new PieChart.Data("Doria Bulford", 50));
     pieChartITO.add(new PieChart.Data("Regen Hearson", 66));
     pieChartITO.add(new PieChart.Data("Bruis Hazlegrove", 90));
     pieChartITO.add(new PieChart.Data("Elisa Hammerberger", 40));
     pieChartITO.add(new PieChart.Data("Binni Crankshaw", 90));
     pieChartITO.add(new PieChart.Data("Lexy Davion", 20));
     pieChartITO.add(new PieChart.Data("Kellsie Goodburn", 100));
     pieChartITO.add(new PieChart.Data("Worth Velasquez", 75));
     pieChartITO.add(new PieChart.Data("Dalli Burnie", 83));
     pieChart.setTitle("Attendance for ITO");
     pieChart.getData().setAll(pieChartITO);

     pieChartITO.forEach(data ->
     data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), "%")));

     } else if (selectedIndex == 1) {
     pieChartSCO.add(new PieChart.Data("Doria Bulford", 40));
     pieChartSCO.add(new PieChart.Data("Regen Hearson", 100));
     pieChartSCO.add(new PieChart.Data("Bruis Hazlegrove", 90));
     pieChartSCO.add(new PieChart.Data("Elisa Hammerberger", 95));
     pieChartSCO.add(new PieChart.Data("Binni Crankshaw", 88));
     pieChartSCO.add(new PieChart.Data("Lexy Davion", 50));
     pieChartSCO.add(new PieChart.Data("Kellsie Goodburn", 95));
     pieChartSCO.add(new PieChart.Data("Worth Velasquez", 75));
     pieChartSCO.add(new PieChart.Data("Dalli Burnie", 83));
     pieChart.setTitle("Attendance for SCO");
     pieChart.getData().setAll(pieChartSCO);

     pieChartSCO.forEach(data ->
     data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), "%")));
     }


     }
     **/
}

