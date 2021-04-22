package Application.DAL.database.DAO;

import Application.BE.Class;
import Application.DAL.database.JDBCConnectionPool;
import Application.DAL.database.MyDatabaseConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {


    private MyDatabaseConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public CourseDAO() throws IOException, SQLServerException {
        dbConnector = new MyDatabaseConnector();
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Gets a list of courses from the database.
     * @return a list of Class objects.
     * @throws SQLException
     */
    public List<Class> getAllClasses() throws SQLException {
        List<Class> allCourses = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){

            ResultSet rs = st.executeQuery("SELECT * FROM Courses");
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("CourseName");
                Class course = new Class(id, name);
                allCourses.add(course);
            }
        } return allCourses;
    }

    /**
     * Gets today's course for the student to see..
     * @param studentId
     * @return a Class object
     * @throws SQLException
     */
    public Class todaysCourse(int studentId) throws SQLException{
        String sql = "SELECT CourseId, Courses.CourseName FROM CourseAttendance INNER JOIN Courses " +
                "ON CourseAttendance.CourseId = Courses.Id WHERE CourseAttendance.[Date] = CONVERT(date, GETDATE()) " +
                "AND CourseAttendance.StudentId = ?;";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, studentId);
            st.execute();

            Class course = null;
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                int id = rs.getInt("CourseId");
                String name = rs.getString("CourseName");
                course= new Class(id, name);
            }
            return course;
        }
    }
}
