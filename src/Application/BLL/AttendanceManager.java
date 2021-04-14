package Application.BLL;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Student;
import Application.BE.Teacher;
import Application.DAL.database.CourseDAO;
import Application.DAL.database.StudentDAO;
import Application.DAL.database.TeacherDAO;
import Application.DAL.mock.DALManager;
//import Application.DAL.mock.DALManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttendanceManager {

    //private final DALManager DALManager;
    private final StudentDAO studentDAO;
    private final TeacherDAO teacherDAO;
    private final CourseDAO courseDAO;


    public AttendanceManager() throws IOException, SQLException {
        //DALManager = new DALManager();
        studentDAO = new StudentDAO();
        teacherDAO = new TeacherDAO();
        courseDAO = new CourseDAO();
    }

    /**
     * Gets a list of all the students from the DALManager.
     * @return the list of all students.
     */
    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    /**
     * Gets a list of all the classes from the DALManager.
     * @return the list of students.
     */
    /**public List<Class> getAllClasses(){
     return DALManager.getAllClasses();
     }**/

    public Student getStudentData(String userName, String password) throws SQLException {
        return studentDAO.getStudentData(userName, password);
    }

    public Teacher getTeacherLogin(String username, String password) throws SQLException {
        return teacherDAO.getTeacherLogin(username, password);
    }

    public List<Class> getAllClasses(int teacherId) throws SQLException {
        return teacherDAO.getClasses(teacherId);
    }

    public List<Attendance> getAttendance() throws SQLException {
        return studentDAO.getAttendance();
    }
}