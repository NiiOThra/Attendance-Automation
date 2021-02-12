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

    /**
     * The constructor of the model class. Creating all the observable
     * arraylists and adding data to the lists.
     */
    public TeacherModel(){
        attendanceManager = new AttendanceManager();

        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(attendanceManager.getAllStudents());

        allClasses = FXCollections.observableArrayList();
        allClasses.addAll(attendanceManager.getAllClasses());
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
