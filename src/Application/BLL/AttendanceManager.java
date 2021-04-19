package Application.BLL;

import Application.BE.*;
import Application.BE.Class;
import Application.DAL.database.AttendanceDAO;
import Application.DAL.database.StudentDAO;
import Application.DAL.database.TeacherDAO;
//import Application.DAL.mock.DALManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttendanceManager {

    //private final DALManager DALManager;
    private final StudentDAO studentDAO;
    private final TeacherDAO teacherDAO;
    private final AttendanceDAO attendanceDAO;


    public AttendanceManager() throws IOException, SQLException {
        //DALManager = new DALManager();
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();
        attendanceDAO = new AttendanceDAO();
    }

    /**
     * Gets a list of all the students from the studentDAO.
     * @return the list of all students.
     */
    public List<Attendance> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    /**
     * Gets the login data.
     * @param username typed in username
     * @param password typed in password
     * @return a Person object depending on the input from the user.
     * @throws SQLException
     */
    public Person getLogin(String username, String password) throws SQLException {
        return attendanceDAO.getLogin(username, password);
    }

    /**
     * Gets a list of classes depending on the loggedin teacher
     * @param teacherId
     * @return a class object
     * @throws SQLException
     */
    public Class getTeacherClasses(int teacherId) throws SQLException {
        return teacherDAO.getTeacherClass(teacherId);
    }

    /**
     * Gets a list of classes.
     * @return a list of classes
     * @throws SQLException
     */
    public List<Class> getStudentClasses() throws SQLException {
        return studentDAO.getAllClasses();
    }

    /**
     * Gets a list of students that has checked in for today.
     * @return
     * @throws SQLException
     */
    public List<Person> getActiveStudents() throws SQLException {
        return attendanceDAO.getTodaysStudent();
    }

    /**
     * Lets the teacher open today's class so students can check in.
     * @param teacherId the loggedin teacher
     * @param course the loggedin teacher course
     * @throws SQLException
     */
    public void openClass(int teacherId, Class course) throws SQLException {
        teacherDAO.openClass(teacherId, course);
    }

    /**
     * Lets the student check in for the class that the teacher has opened.
     * @param studentId the logged in student
     * @param courseId the course that the teacher has opened
     * @throws SQLException
     */
    public void checkIn(int studentId, int courseId) throws SQLException {
        studentDAO.checkIn(studentId, courseId);
    }

    /**
     * Just to show the student what course the teacher has opened.
     * @param studentId logged in student
     * @return a cass object for the student to see
     * @throws SQLException
     */
    public Class todaysCourse(int studentId) throws SQLException {
        return studentDAO.todaysCourse(studentId);
    }

    public int getOffDays(int studentId, String weekday) throws SQLException {
        return studentDAO.getOffDay(studentId, weekday);
    }

    public int getAbsence(int studentId) throws SQLException {
        return studentDAO.getAbsence(studentId);
    }

    public List<String> getAbsenceDays(int studentId) throws SQLException {
        return studentDAO.getAbsenceDays(studentId);
    }

    public void updateAbsentDay(int studentId, String date) throws SQLException {
        studentDAO.updateAbsenceDay(studentId, date);
    }

    /**public List<Attendance> getAttendance() throws SQLException {
        return studentDAO.getAttendance();
    }**/

    /**
     * Gets a list of all the classes from the DALManager.
     * @return the list of students.
     */
    /**public List<Class> getAllClasses(){
     return DALManager.getAllClasses();
     }**/
}