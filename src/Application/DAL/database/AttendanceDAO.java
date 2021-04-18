package Application.DAL.database;

import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;
import Application.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    private final JDBCConnectionPool connectionPool;

    public AttendanceDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Select a list of persons from the database that has checked in on today's class. This will be a list of students.
     * @return a list of student object
     * @throws SQLException
     */
    public List<Person> getTodaysStudent() throws SQLException{
        List<Person> activeStudents = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery("SELECT Name, Persons.Id, Persons.IsStudent, CourseAttendance.StudentId, CourseAttendance.[Date] " +
                    "FROM Persons " +
                    "INNER JOIN " +
                    "CourseAttendance " +
                    "ON Persons.Id = CourseAttendance.StudentId " +
                    "WHERE CourseAttendance.[Date] = CONVERT(date, getdate()) " +
                    "AND CourseAttendance.HasAttended = 1");
            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");
                Person stud = new Student(id, name, type);
                activeStudents.add(stud);
            }
            return activeStudents;
        }
    }

    /**
     * This select a person from the database that matches the username and password that comes from the user.
     * @param username the typed in username
     * @param password the typed in password
     * @return a person object. either a student or teacher.
     * @throws SQLException
     */
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
