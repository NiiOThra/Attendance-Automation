package Application.GUI.Model;

import Application.BE.Class;
import Application.BE.Student;
import Application.BLL.AttendanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class AttendanceModel {

    private final AttendanceManager attendanceManager;

    private ObservableList<Student> allStudents;
    private ObservableList<Class> allClasses;
    Student loggedInStudent = null;

    /**
     * The constructor of the model class. Creating all the observable
     * arraylists and adding data to the lists.
     */
    public AttendanceModel() throws IOException, SQLException {
        attendanceManager = new AttendanceManager();

        allStudents = FXCollections.observableArrayList();
        //allStudents.addAll(attendanceManager.getAllStudents());

        allClasses = FXCollections.observableArrayList();
        //allClasses.addAll(attendanceManager.getAllClasses());
    }

    /**
     * Gets a list of students.
     * @return the list of students.
     */
    public ObservableList<Student> getAllStudents() {
        return allStudents;
    }

    /**
     * Gets a list of all classes.
     * @return the list of students.
     */
    public ObservableList<Class> getAllClasses(){
        return allClasses;
    }
}
