package Application.GUI.Model;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
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
    private ObservableList<Person> activeStudents;

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

        activeStudents = FXCollections.observableArrayList();
        activeStudents.addAll(attendanceManager.getActiveStudents());
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

    public ObservableList<Person> getActiveStudents(){
        return activeStudents;
    }

    public void openClass(int teacherId, Class course) throws SQLException {
        attendanceManager.openClass(teacherId, course);
    }

    public void checkIn(int studentId, int courseId) throws SQLException {
        attendanceManager.checkIn(studentId, courseId);
    }

    public Class getTodayClass(int studentId) throws SQLException {
        return attendanceManager.todaysCourse(studentId);
    }
}
