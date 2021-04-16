package Application.GUI.Model;

import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;
import Application.BE.Teacher;
import Application.BLL.AttendanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginModel {

    private static LoginModel instance;
    Student loggedInStudent = null;
    Teacher loggedInTeacher = null;
    Person loggedinPerson = null;
    AttendanceManager attManager;
    private ObservableList<Class> teacherCourses;

    private LoginModel() throws IOException, SQLException {
        attManager = new AttendanceManager();
    }

    public static LoginModel getInstance() throws IOException, SQLException {
        if (instance == null){
            instance = new LoginModel();
        }
        return instance;
    }

    public Person loginPerson(String username, String password) throws SQLException {
        loggedinPerson = attManager.getLogin(username, password);
        return loggedinPerson;
    }

    public Person getLoggedinPerson(){
        return loggedinPerson;
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

    public ObservableList<Class> getTeacherClasses(int id) throws SQLException {
        teacherCourses = FXCollections.observableArrayList();
        teacherCourses.addAll(attManager.getTeacherClasses(id));
        return teacherCourses;

    }
}