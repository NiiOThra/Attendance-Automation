package GUI.viewController.TeacherView;


import BE.Class;
import GUI.Model.TeacherModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;

import javax.swing.*;
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
        lstView.getItems().add("Weekly");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        Axis<String> xAxis = lineChart.getXAxis();
        Axis<Number> yAxis = lineChart.getYAxis();

        lstView.setOnAction(event -> {
            int selectedIndex = lstView.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0) {
                series2.getData().removeAll(Collections.singleton(lineChart.getData().setAll()));

                xAxis.setLabel("Month");
                yAxis.setLabel("Attendance %");

                series.setName("Attendance for the whole semester");
                series.getData().add(new XYChart.Data<>("September", 100));
                series.getData().add(new XYChart.Data<>("October", 80));
                series.getData().add(new XYChart.Data<>("November", 95));
                series.getData().add(new XYChart.Data<>("December", 70));
                lineChart.getData().add(series);
            } else if(selectedIndex == 1) {
                series.getData().removeAll(Collections.singleton(lineChart.getData().setAll()));

                xAxis.setLabel("Day");
                yAxis.setLabel("Attendance %");

                series2.setName("Attendance daily");
                series2.getData().add(new XYChart.Data<>("Monday", 50));
                series2.getData().add(new XYChart.Data<>("Tuesday", 90));
                series2.getData().add(new XYChart.Data<>("Wednesday", 70));
                series2.getData().add(new XYChart.Data<>("Thursday", 98));
                series2.getData().add(new XYChart.Data<>("Friday", 99));
                lineChart.getData().add(series2);
            }
        });


    }
}
