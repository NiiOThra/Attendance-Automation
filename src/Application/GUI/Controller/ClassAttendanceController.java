package Application.GUI.Controller;

import Application.BE.Class;
import Application.BE.Student;
import Application.BE.Teacher;
import Application.GUI.Model.AttendanceModel;
import Application.GUI.Model.LoginModel;
import com.jfoenix.controls.JFXComboBox;
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

    private ObservableList<Student> allStudents;
    private ObservableList<Class> allClasses;

    @FXML
    private TableView<Student> lstAllStudents;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, Integer> attendanceColumn;
    @FXML
    private JFXComboBox<Class> lstClasses;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label classNamelbl;
    @FXML
    private Label welcomeTeacherLbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            attendanceModel = new AttendanceModel();
            LoginModel.getInstance();

            String teacherName = LoginModel.getInstance().getLoggedInTeacher().getName();
            String courseName = LoginModel.getInstance().getLoggedInTeacher().getClassName().getName();
            welcomeTeacherLbl.setText("Welcome back " + teacherName + "! You have " + courseName + " on the schedule today.");
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        allStudents = attendanceModel.getAllStudents();
        allClasses = attendanceModel.getAllClasses();

        lstAllStudents.setItems(allStudents);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("Attendance"));
        nameColumn.setSortType(TableColumn.SortType.DESCENDING);
        lstAllStudents.getSortOrder().add(attendanceColumn);
        lstAllStudents.sort();

        lstClasses.setItems(allClasses);
    }

    public void handleShowChart(ActionEvent event){
        int selectedIndex = lstClasses.getSelectionModel().getSelectedIndex();
        ObservableList<PieChart.Data> pieChartITO = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> pieChartSCO = FXCollections.observableArrayList();

        if (selectedIndex == 0){
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

        } else if (selectedIndex == 1){
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

    public void getSpecificStudentInfo(){
        showSpecificStudentInfo("/Application/GUI/View/SpecificStudentAttendanceView.fxml");
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
}

