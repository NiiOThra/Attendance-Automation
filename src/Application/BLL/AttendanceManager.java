package Application.BLL;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.DAL.database.DalController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

//import Application.DAL.mock.DALManager;

public class AttendanceManager {

    //private DALManager dalManager;
    private DalController dalController;

    public AttendanceManager() throws IOException, SQLException {
        //DALManager = new DALManager();
        dalController = new DalController();
    }

    /**
     * Gets a list of all the students from the studentDAO.
     * @return the list of all students.
     */
    public List<Attendance> getAllStudents() throws SQLException {
        return dalController.getAllStudents();
    }

    /**
     * Gets the login data.
     * @param username typed in username
     * @param password typed in password
     * @return a Person object depending on the input from the user.
     * @throws SQLException
     */
    public Person getLogin(String username, String password) throws SQLException {
        return dalController.getLogin(username, password);
    }

    /**
     * Gets a list of classes depending on the loggedin teacher
     * @param teacherId
     * @return a class object
     * @throws SQLException
     */
    public Class getTeacherClasses(int teacherId) throws SQLException {
        return dalController.getTeacherClass(teacherId);
    }

    /**
     * Gets a list of classes.
     * @return a list of classes
     * @throws SQLException
     */
    public List<Class> getStudentClasses() throws SQLException {
        return dalController.getAllClasses();
    }

    /**
     * Lets the teacher open today's class so students can check in.
     * @param teacherId the loggedin teacher
     * @param course the loggedin teacher course
     * @throws SQLException
     */
    public void openClass(int teacherId, Class course) throws SQLException {
        dalController.openClass(teacherId, course);
    }

    /**
     * Lets the student check in for the class that the teacher has opened.
     * @param studentId the logged in student
     * @param courseId the course that the teacher has opened
     * @throws SQLException
     */
    public void checkIn(int studentId, int courseId) throws SQLException {
        dalController.checkIn(studentId, courseId);
    }

    /**
     * Just to show the student what course the teacher has opened.
     * @param studentId logged in student
     * @return a cass object for the student to see
     * @throws SQLException
     */
    public Class todaysCourse(int studentId) throws SQLException {
        return dalController.todaysCourse(studentId);
    }

    public int getOffDays(int studentId, String weekday) throws SQLException {
        return dalController.getOffDay(studentId, weekday);
    }

    public int getAbsence(int studentId) throws SQLException {
        return dalController.getAbsence(studentId);
    }

    public List<String> getAbsenceDays(int studentId) throws SQLException {
        return dalController.getAbsenceDays(studentId);
    }

    public void updateAbsentDay(int studentId, String date) throws SQLException {
        dalController.updateAbsenceDay(studentId, date);
    }

    public int getAttendance(int studentId) throws SQLException {
        return dalController.getAttendance(studentId);
    }

    public List<Person> getTodaysStudent() throws SQLException {
        return dalController.getTodaysStudent();
    }
}