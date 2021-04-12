package Application.GUI.Model;

import Application.BE.Student;
import Application.BE.Teacher;
import Application.BLL.AttendanceManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {

    private static LoginModel instance;
    Student loggedInStudent = null;
    Teacher loggedInTeacher = null;
    AttendanceManager attManager;

    private LoginModel() throws IOException, SQLException {
        attManager = new AttendanceManager();
    }

    public static LoginModel getInstance() throws IOException, SQLException {
        if (instance == null){
            instance = new LoginModel();
        }
        return instance;
    }

    public Student logInStudent(String username, String password) throws SQLException {
        loggedInStudent = attManager.getStudentData(username, password);
        return loggedInStudent;
    }

    public Student getLoggedInStudent(){
        return loggedInStudent;
    }

    public Teacher logInTeacher(String username, String password) throws SQLException {
        loggedInTeacher = attManager.getTeacherLogin(username, password);
        return loggedInTeacher;
    }

    public Teacher getLoggedInTeacher(){
        return loggedInTeacher;
    }
}