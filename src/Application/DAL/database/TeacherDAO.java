package Application.DAL.database;

import Application.BE.Class;
import Application.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

    private MyDatabaseConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public TeacherDAO() throws IOException, SQLServerException {
        dbConnector = new MyDatabaseConnector();
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public Teacher getTeacherLogin(String username, String password) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            String query = "SELECT * FROM Persons INNER JOIN LoginInformation "
                    + "ON Persons.LoginId = LoginInformation.Id INNER JOIN Courses ON Persons.Id = Courses.TeacherId " +
                    "WHERE LoginInformation.Username = ? AND [LoginInformation].[Password] = ? AND IsStudent = 0";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.execute();

            ResultSet rs = st.getResultSet();
            Teacher teacher = null;
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");
                int loginID = rs.getInt("LoginId");
                String className = rs.getString("CourseName");
                teacher = new Teacher(id, name, className);
            }
            return teacher;
        }
    }

    public List<Class> getTeacherClass(int teacherId) throws SQLException {
        List<Class> allCourses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE TeacherId = ?";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, teacherId);
            st.execute();

            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("CourseName");
                Class course = new Class(id, name);
                allCourses.add(course);
            }
        } return allCourses;
    }
}
