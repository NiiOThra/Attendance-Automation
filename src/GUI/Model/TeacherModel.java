package GUI.Model;

import BE.Class;
import BE.Student;
import BLL.AttendanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeacherModel {

    private final AttendanceManager attendanceManager;

    private ObservableList<Student> allStudents;
    private ObservableList<Class> allClasses;


    public TeacherModel(){
        attendanceManager = new AttendanceManager();

        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(attendanceManager.getAllStudents());

        allClasses = FXCollections.observableArrayList();
        allClasses.addAll(attendanceManager.getAllClasses());
    }

    public ObservableList<Student> getAllStudents() {
        return allStudents;
    }

    public ObservableList<Class> getAllClasses(){
        return allClasses;
    }
}
