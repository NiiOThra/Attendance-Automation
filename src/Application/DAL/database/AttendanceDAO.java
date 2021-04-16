package Application.DAL.database;

import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;
import Application.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AttendanceDAO {

    private final JDBCConnectionPool connectionPool;

    public AttendanceDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public List<Class> getAllClasses() throws SQLException {
        List<Class> allCourses = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement statement = con.createStatement()){
            ResultSet rs = statement.executeQuery("SELECT * FROM Courses");
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("CourseName");
                Class course = new Class(id, name);
                allCourses.add(course);
            }
            return allCourses;
        }
    }

    public Person getLogin(String username, String password) throws SQLException{
        try (Connection con = connectionPool.checkOut()){
            String query = "SELECT * FROM Persons INNER JOIN LoginInformation "
                    + "ON Persons.LoginId = LoginInformation.Id WHERE LoginInformation.Username = ? AND [LoginInformation].[Password] = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.execute();

            ResultSet rs = st.getResultSet();
            Person person = null;
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");
                int loginID = rs.getInt("LoginID");
                if (type == 0)
                    person = new Teacher(id, name, type);
                else if (type ==1)
                    person = new Student(id, name, type);

            }return person;
        }
    }
}
