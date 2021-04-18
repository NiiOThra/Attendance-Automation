package Application.GUI.Model;

import Application.BE.Class;
import Application.BE.Person;
import Application.BLL.AttendanceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class LoginModel {

    private static LoginModel instance;
    Person loggedinPerson = null;
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

    public Person loginPerson(String username, String password) throws SQLException {
        loggedinPerson = attManager.getLogin(username, password);
        return loggedinPerson;
    }

    public Person getLoggedinPerson(){
        return loggedinPerson;
    }

    public Class getTeacherCourse(int teacherId) throws SQLException {
        Class course = attManager.getTeacherClasses(teacherId);
        return course;
    }
}