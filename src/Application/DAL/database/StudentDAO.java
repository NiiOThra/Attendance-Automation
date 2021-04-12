package Application.DAL.database;

import Application.BE.Class;
import Application.BE.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private MyDatabaseConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public StudentDAO() throws SQLException, IOException {
        connectionPool = JDBCConnectionPool.getInstance();
        dbConnector = new MyDatabaseConnector();
    }

    public Student getStudentData(String username, String password) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            String query = "SELECT Name, Persons.Id, Persons.[Type], Persons.LoginId " +
                    "FROM Persons " +
                    "INNER JOIN " +
                    "LoginInformation ON Persons.LoginId = LoginInformation.Id WHERE Username = ? AND [Password] = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.execute();

            ResultSet rs = st.getResultSet();
            Student stud = null;
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int type = rs.getInt("Type");
                int loginID = rs.getInt("LoginId");
                stud = new Student(id, name);
            }
            return stud;
        }
    }

    public List<Student> getStudents(Class course) throws SQLException{
        List<Student> allStudents = new ArrayList<>();
        try (Connection con = connectionPool.checkOut()){
            String query = "SELECT StudentId, Persons.Name FROM StudentCourse INNER JOIN Persons ON StudentCourse.StudentId = Persons.Id WHERE StudentCourse.CourseId = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, course.getClassID());
            st.execute();

            ResultSet rs= st.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("StudentId");
                String name = rs.getString("Name");

                Student stud = new Student(id, name);
                allStudents.add(stud);
            }
            return allStudents;
        }
    }

}
