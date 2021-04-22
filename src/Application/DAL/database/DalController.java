package Application.DAL.database;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.DAL.database.DAO.AttendanceDAO;
import Application.DAL.database.DAO.CourseDAO;
import Application.DAL.database.DAO.StudentDAO;
import Application.DAL.database.DAO.TeacherDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DalController implements DALInterface {

    private CourseDAO courseDAO;
    private StudentDAO studentDAO;
    private TeacherDAO teacherDAO;
    private AttendanceDAO attDAO;

    public DalController() throws IOException, SQLException {
        courseDAO = new CourseDAO();
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();
        attDAO = new AttendanceDAO();
    }

    @Override
    public Class todaysCourse(int studentId) throws SQLException {
        return courseDAO.todaysCourse(studentId);
    }

    @Override
    public List<Class> getAllClasses() throws SQLException {
        return courseDAO.getAllClasses();
    }

    @Override
    public Person getLogin(String username, String password) throws SQLException {
        return attDAO.getLogin(username, password);
    }

    @Override
    public void updateAbsenceDay(int studentId, String date) throws SQLException {
        attDAO.updateAbsenceDay(studentId, date);
    }

    @Override
    public List<String> getAbsenceDays(int studentId) throws SQLException {
        return studentDAO.getAbsenceDays(studentId);
    }

    @Override
    public int getAbsence(int studentId) throws SQLException {
        return studentDAO.getAbsence(studentId);
    }

    @Override
    public int getAttendance(int studentId) throws SQLException {
        return studentDAO.getAttendance(studentId);
    }

    @Override
    public List<Person> getTodaysStudent() throws SQLException {
        return studentDAO.getTodaysStudent();
    }

    @Override
    public int getOffDay(int studentId, String weekday) throws SQLException {
        return studentDAO.getOffDay(studentId, weekday);
    }

    @Override
    public void checkIn(int studentId, int courseId) throws SQLException {
        studentDAO.checkIn(studentId, courseId);
    }

    @Override
    public List<Attendance> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    @Override
    public Class getTeacherClass(int teacherId) throws SQLException {
        return teacherDAO.getTeacherClass(teacherId);
    }

    @Override
    public void openClass(int teacherId, Class course) throws SQLException {
        teacherDAO.openClass(teacherId, course);
    }
}
