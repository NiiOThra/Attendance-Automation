package GUI.viewController.StudentView;


import BE.Class;
import GUI.Model.TeacherModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceController implements Initializable {

    private TeacherModel teacherModel;

    private ObservableList<Class> allClasses;

    @FXML
    private JFXComboBox<Class> lstClasses;
    @FXML
    private Button checkInBtn;
    @FXML
    private JFXComboBox<String> lstView;
    @FXML
    private PieChart pieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherModel = new TeacherModel();

        allClasses = teacherModel.getAllClasses();

        lstClasses.setItems(allClasses);

        lstView.getItems().add("Whole semester");
    }

    public void handleCheckIn(ActionEvent event) {
        Class chosenClass = lstClasses.getSelectionModel().getSelectedItem();

        if (chosenClass == null) {
            checkInBtn.setText("Choose a class to check in!");
        } else if (chosenClass != null) {
            checkInBtn.setText("You are checked in for " + chosenClass + " today!");
        }
    }

    public void handleChooseView(ActionEvent event){
        int selectedView = lstView.getSelectionModel().getSelectedIndex();

        if (selectedView == 0){
            getPieChart();
        }
    }

    private void getPieChart(){
        ObservableList<PieChart.Data> pieChartSmst = FXCollections.observableArrayList();

        pieChartSmst.add(new PieChart.Data("SCO", 90));
        pieChartSmst.add(new PieChart.Data("DBOS", 98));
        pieChartSmst.add(new PieChart.Data("SDE", 78));
        pieChartSmst.add(new PieChart.Data("ITO", 67));

        pieChart.setData(pieChartSmst);
        pieChart.setTitle("Attendance for the semester");
    }

    

}
