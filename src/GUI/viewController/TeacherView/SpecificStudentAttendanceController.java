package GUI.viewController.TeacherView;


import BE.Class;
import GUI.Model.TeacherModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

public class SpecificStudentAttendanceController implements Initializable {

    private TeacherModel teacherModel;

    ObservableList<Class> allClasses;

    @FXML
    private ChoiceBox<Class> lstClasses;
    @FXML
    ChoiceBox<String> lstView;
    @FXML
    private LineChart<String, Number> lineChart;

    public SpecificStudentAttendanceController(){
        teacherModel = new TeacherModel();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allClasses = teacherModel.getAllClasses();

        lstClasses.setItems(allClasses);
        lstClasses.getSelectionModel().select(0);

        lstView.getItems().add("Whole semester");
        lstView.getItems().add("Weekdays");

        XYChart.Series<String, Number> semesterSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> weekDaysSeries = new XYChart.Series<>();
        Axis<String> xAxis = lineChart.getXAxis();
        Axis<Number> yAxis = lineChart.getYAxis();

        lstView.setOnAction(event -> {
            int selectedIndexView = lstView.getSelectionModel().getSelectedIndex();
            if (selectedIndexView == 0) {
                weekDaysSeries.getData().removeAll(Collections.singleton(lineChart.getData().setAll()));

                xAxis.setLabel("Month");
                yAxis.setLabel("Attendance %");

                semesterSeries.setName("Attendance for the whole semester");
                semesterSeries.getData().add(new XYChart.Data<>("September", 100));
                semesterSeries.getData().add(new XYChart.Data<>("October", 80));
                semesterSeries.getData().add(new XYChart.Data<>("November", 95));
                semesterSeries.getData().add(new XYChart.Data<>("December", 70));
                lineChart.getData().add(semesterSeries);
            } else if(selectedIndexView == 1) {
                semesterSeries.getData().removeAll(Collections.singleton(lineChart.getData().setAll()));

                xAxis.setLabel("Day");
                yAxis.setLabel("Attendance %");

                weekDaysSeries.setName("Attendance weekdays");
                weekDaysSeries.getData().add(new XYChart.Data<>("Monday", 50));
                weekDaysSeries.getData().add(new XYChart.Data<>("Tuesday", 90));
                weekDaysSeries.getData().add(new XYChart.Data<>("Wednesday", 70));
                weekDaysSeries.getData().add(new XYChart.Data<>("Thursday", 98));
                weekDaysSeries.getData().add(new XYChart.Data<>("Friday", 99));
                lineChart.getData().add(weekDaysSeries);
            }
        });
    }
}
