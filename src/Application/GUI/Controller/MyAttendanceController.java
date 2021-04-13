package Application.GUI.Controller;


import Application.BE.Class;
import Application.BE.Student;
import Application.GUI.Model.AttendanceModel;
import Application.GUI.Model.LoginModel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MyAttendanceController implements Initializable {

    private AttendanceModel attendanceModel;

    private ObservableList<Class> allClasses;

    @FXML
    private JFXComboBox<Class> lstClasses;
    @FXML
    private Button checkInBtn;
    @FXML
    private JFXComboBox<String> lstView;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label percLbl;
    @FXML
    private JFXListView lstAbsenceDays;
    @FXML
    private Button updateBtn;
    @FXML
    private Label nameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            attendanceModel = new AttendanceModel();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            LoginModel.getInstance();
            String name = LoginModel.getInstance().getLoggedInStudent().getName();
            nameField.setText(name);
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        allClasses = attendanceModel.getAllClasses();

        lstView.getItems().add("Whole semester");
    }

    public void handleCheckIn(ActionEvent event) {

    }

    public void handleChooseView(ActionEvent event){
        int selectedView = lstView.getSelectionModel().getSelectedIndex();

        if (selectedView == 0){
            percLbl.setText("Absence for this semester: 4%");
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

        pieChartSmst.forEach(data ->
                data.nameProperty().bind(Bindings.concat(data.getName(), " ", data.pieValueProperty(), "%")));
    }

    public void handleCloseApp(ActionEvent event){
        Platform.exit();
    }

}