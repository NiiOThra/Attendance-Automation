package GUI.viewController.TeacherView;


import BE.Class;
import GUI.Model.TeacherModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SpecificStudentAttendanceController implements Initializable {

    private TeacherModel teacherModel;

    ObservableList<Class> allClasses;

    @FXML
    private ChoiceBox<Class> lstClasses;
    @FXML
    private LineChart<String, Number> lineChart;

    public SpecificStudentAttendanceController(){
        teacherModel = new TeacherModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allClasses = teacherModel.getAllClasses();

        lstClasses.setItems(allClasses);

        Axis<String> xAxis = lineChart.getXAxis();
        xAxis.setLabel("Month");
        Axis<Number> yAxis = lineChart.getYAxis();
        yAxis.setLabel("Attendance %");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Attendance for the whole semester");
        series.getData().add(new XYChart.Data<>("September", 100));
        series.getData().add(new XYChart.Data<>("October", 80));
        series.getData().add(new XYChart.Data<>("November", 95));
        series.getData().add(new XYChart.Data<>("December", 70));
        lineChart.getData().add(series);
    }
}
