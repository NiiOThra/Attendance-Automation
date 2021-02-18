package GUI.viewController.TeacherView;

import BE.Class;
import BE.Student;
import GUI.Model.TeacherModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class ClassAttendanceController implements Initializable {

    private TeacherModel teacherModel;

    private ObservableList<Student> allStudents;
    private ObservableList<Class> allClasses;

    @FXML
    private TableView<Student> lstAllStudents;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, Integer> attendanceColumn;
    @FXML
    private ChoiceBox<Class> lstClasses;
    @FXML
    private PieChart pieChart;

    public ClassAttendanceController(){
        teacherModel = new TeacherModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allStudents = teacherModel.getAllStudents();
        allClasses = teacherModel.getAllClasses();


        lstAllStudents.setItems(allStudents);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("Attendance"));

        lstClasses.setItems(allClasses);

        lstClasses.setOnAction(event -> handlelstClasses());
    }

    public void handlelstClasses(){
        int selectedIndex = lstClasses.getSelectionModel().getSelectedIndex();
        ObservableList<PieChart.Data> pieChartITO = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> pieChartSCO = FXCollections.observableArrayList();

        if (selectedIndex == 0) {

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
            pieChart.setData(pieChartITO);
        } else if (selectedIndex == 1)
        {
            pieChartITO.removeAll();
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
            pieChart.setData(pieChartSCO);
        }
    }

    @FXML
    public void getSpecificStudentInfo(){
        showSpecificStudentInfo("/GUI/viewController/TeacherView/SpecificStudentAttendanceView.fxml");
    }
    public void showSpecificStudentInfo(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));
            Parent mainLayout = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(mainLayout));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

