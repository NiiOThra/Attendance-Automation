package Application.DAL.database;

import Application.BE.Class;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
