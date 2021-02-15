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

        ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList(
                new PieChart.Data("Doria BulFord", 66),
                new PieChart.Data("Regen Hearson", 50),
                new PieChart.Data("Bruis Hazlegrove", 100),
                new PieChart.Data("Elisa Hammerberger", 100),
                new PieChart.Data("Binni Crankshaw", 90),
                new PieChart.Data("Lexy Davion", 95),
                new PieChart.Data("Kellsie Goodburn", 85),
                new PieChart.Data("Worth Velasquez", 75),
                new PieChart.Data("Dalli Burnie", 100)
        );
        pieChart.setTitle("Attendance for SCO");
        pieChart.setData(pieCharData);
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

