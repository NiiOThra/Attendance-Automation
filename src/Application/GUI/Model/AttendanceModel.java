package Application.GUI.Model;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.BLL.AttendanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class AttendanceModel {

    private final AttendanceManager attendanceManager;

    private ObservableList<Attendance> allStudents;
    private ObservableList<Class> allClasses;
    private ObservableList<Person> todaysStudents;
    private ObservableList<String> absenceDays;

    /**
     * The constructor of the model class. Creating all the observable
     * arraylists and adding data to the lists.
     */
    public AttendanceModel() throws IOException, SQLException {
        attendanceManager = new AttendanceManager();

        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(attendanceManager.getAllStudents());

        allClasses = FXCollections.observableArrayList();
        allClasses.addAll(attendanceManager.getStudentClasses());

        todaysStudents = FXCollections.observableArrayList();
        todaysStudents.addAll(attendanceManager.getTodaysStudent());
    }

    public ObservableList<Person> getActiveStudents(){
        return todaysStudents;
    }

    public ObservableList<Attendance> getAllStudents(){
        return allStudents;
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

    public int getOffDay(int studentId, String weekDay) throws SQLException {
        return attendanceManager.getOffDays(studentId, weekDay);
    }

    public int getAbsence(int studentId) throws SQLException {
        return attendanceManager.getAbsence(studentId);
    }

    public ObservableList<String> getAbsenceDays(int studentId) throws SQLException {
        absenceDays = FXCollections.observableArrayList();
        absenceDays.addAll(attendanceManager.getAbsenceDays(studentId));
        return absenceDays;
    }

    public void updateAbsentDay(int studentId, String date) throws SQLException {
        attendanceManager.updateAbsentDay(studentId, date);
    }

    public int getAttendance(int studentId) throws SQLException {
        return attendanceManager.getAttendance(studentId);
    }
}