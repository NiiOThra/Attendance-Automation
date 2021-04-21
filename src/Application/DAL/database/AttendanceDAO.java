package Application.DAL.database;

import Application.BE.*;
import Application.BE.Class;
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
     * Select a list of all students from the database for the teacher to get an overview of todays class.. Who's checked in and who's not checked in.
     * @throws SQLException
     */
    public List<Person> getTodaysStudent() throws SQLException{
        List<Person> todaysClass = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery("SELECT Name, CourseAttendance.HasAttended, Persons.Id, Persons.IsStudent, CourseAttendance.StudentId, CourseAttendance.[Date] " +
                    "FROM Persons " +
                    "INNER JOIN " +
                    "CourseAttendance " +
                    "ON Persons.Id = CourseAttendance.StudentId " +
                    "WHERE CourseAttendance.[Date] = CONVERT(date, getdate()) AND Persons.IsStudent = 1");

            while (rs.next()){
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");
                String attended = rs.getString("HasAttended");

                Student stud = new Student(id, name, type, attended);

                todaysClass.add(stud);
            }
            return todaysClass;
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
                    person = new Student(id, name, type, null);

            }return person;
        }
    }
}
