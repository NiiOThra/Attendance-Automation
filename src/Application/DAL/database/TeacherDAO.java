package Application.DAL.database;

import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;
import Application.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TeacherDAO {

    private MyDatabaseConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public TeacherDAO() throws IOException, SQLServerException {
        dbConnector = new MyDatabaseConnector();
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     *
     * Gets the class of a specific teacher from the database.
     * @param teacherId
     * @return a Class object
     * @throws SQLException
     */
    public Class getTeacherClass(int teacherId) throws SQLException {
        String sql = "SELECT * FROM Courses WHERE TeacherId = ?";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, teacherId);
            st.execute();

            ResultSet rs = st.getResultSet();
            Class course = null;
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("CourseName");
                course = new Class(id, name);
            }
            return course;
        }
    }

    /**
     * When this method is run, the code will check if there is already created a row with today's date; if not
     * we will continue to get a list of students that is on the logged in teachers course.
     * At last I create a new row for each student on this course.
     * That means students are set to default to not be present at class but it makes it possible for a student to
     * check in on this day and on this course.
     * @param teacherId
     * @param course
     * @throws SQLException
     */
    public void openClass(int teacherId, Class course) throws SQLException {
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM CourseAttendance WHERE Date =  CONVERT(date, getdate())");
            if (rs.next() == false) {
                List<Person> studentsInCoure = new ArrayList<>();
                String sql = "SELECT Name, Id, IsStudent FROM Persons;";

                PreparedStatement st1 = con.prepareStatement(sql);
                st1.execute();

                ResultSet rs2 = st1.getResultSet();
                while (rs2.next()) {
                    int id = rs2.getInt("Id");
                    String name = rs2.getString("Name");
                    int type = rs2.getInt("IsStudent");
                    Person stud = new Student(id, name, type, 0);
                    studentsInCoure.add(stud);
                }

                String sql2 = "INSERT INTO CourseAttendance(HasAttended, CreatedBy, StudentId, CourseId) VALUES(0, ?, ?, ?);";
                PreparedStatement st2 = con.prepareStatement(sql2);
                for (Iterator<Person> iterator = studentsInCoure.iterator(); iterator.hasNext(); ) {
                    Person per = (Person) iterator.next();
                    st2.setInt(1, teacherId);
                    st2.setInt(2, per.getId());
                    st2.setInt(3, course.getClassID());
                    st2.addBatch();
                }
                st2.executeBatch();
            } else System.out.println(course.getName() + " already opened today so you cant open class until tomorrow");
        }
    }
}
