package Application.DAL.database;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;

import java.sql.SQLException;
import java.util.List;

public interface DALInterface {

    Class todaysCourse(int studentId) throws SQLException;

    List<Class> getAllClasses() throws SQLException;

    Person getLogin(String username, String password) throws SQLException;

    void updateAbsenceDay(int studentId, String date) throws SQLException;

    int getAttendance(int studentId) throws SQLException;

    List<Person> getTodaysStudent() throws SQLException;

    List<String> getAbsenceDays(int studentId) throws SQLException;

    int getAbsence(int studentId) throws SQLException;

    int getOffDay(int studentId, String weekday) throws SQLException;

    void checkIn(int studentId, int courseId) throws SQLException;

    List<Attendance> getAllStudents() throws SQLException;

    Class getTeacherClass(int teacherId) throws SQLException;

    void openClass(int teacherId, Class course) throws SQLException;
}
