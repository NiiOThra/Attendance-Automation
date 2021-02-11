package GUI.viewController.TeacherView;

import BE.Class;
import GUI.Model.TeacherModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherClassesController implements Initializable {

    private TeacherModel teacherModel;
    private ObservableList<Class> allClasses;

    @FXML
    private TableView<Class> lstClasses;
    @FXML
    private TableColumn<Class, String> nameColumn;

    public TeacherClassesController(){
        teacherModel = new TeacherModel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allClasses = teacherModel.getAllClasses();

        lstClasses.setItems(allClasses);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    }

    @FXML
    public void getClassAttendanceOverview(ActionEvent event){
        showClassAttendanceView("/GUI/viewController/TeacherView/ClassAttendanceView.fxml");
    }

    public void showClassAttendanceView(String fxmlPath) {
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
