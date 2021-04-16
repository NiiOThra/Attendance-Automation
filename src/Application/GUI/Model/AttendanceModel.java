package Application.GUI.Model;

import Application.BE.Attendance;
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
    private ObservableList<Attendance> attendanceList;
    private ObservableList<Class> allClasses;



    /**
     * The constructor of the model class. Creating all the observable
     * arraylists and adding data to the lists.
     */
    public AttendanceModel() throws IOException, SQLException {
        attendanceManager = new AttendanceManager();

        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(attendanceManager.getAllStudents());

        /**attendanceList = FXCollections.observableArrayList();
        attendanceList.addAll(attendanceManager.getAttendance());**/

        allClasses = FXCollections.observableArrayList();
        allClasses.addAll(attendanceManager.getStudentClasses());
    }

    public ObservableList<Attendance> getAttendanceList(){
        return attendanceList;
    }

    /**
     * Gets a list of all classes.
     * @return the list of students.
     */
    public ObservableList<Class> getAllClasses(){
        return allClasses;
    }
}
