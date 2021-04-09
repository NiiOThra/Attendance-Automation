package Application.BLL;

import Application.BE.Class;
import Application.BE.Student;
import Application.DAL.database.StudentDAO;
import Application.DAL.mock.DALManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AttendanceManager {

    private final DALManager DALManager;
    private final StudentDAO studentDAO;


    public AttendanceManager() throws IOException, SQLException {
        DALManager = new DALManager();
        studentDAO = new StudentDAO();
    }

    /**
     * Gets a list of all the students from the DALManager.
     * @return the list of all students.
     */
    public List<Student> getAllStudents(){
        return DALManager.getAllStudents();
    }

    /**
     * Gets a list of all the classes from the DALManager.
     * @return the list of students.
     */
    public List<Class> getAllClasses(){
        return DALManager.getAllClasses();
    }

    public Student getStudentData(String userName, String password) throws SQLException {
        return studentDAO.getStudentData(userName, password);
    }
}
