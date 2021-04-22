package Application.DAL.database.DAO;

import Application.BE.Person;
import Application.BE.Student;
import Application.BE.Teacher;
import Application.DAL.database.JDBCConnectionPool;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceDAO {

    private final JDBCConnectionPool connectionPool;

    public AttendanceDAO() throws IOException, SQLServerException {
        connectionPool = JDBCConnectionPool.getInstance();
    }

    /**
     * Method to correct the attendance on a student with a specific id.
     * @param studentId
     * @param date
     * @throws SQLException
     */
    public void updateAbsenceDay(int studentId, String date)throws SQLException {
        String sql = "UPDATE CourseAttendance SET HasAttended = 'True' WHERE StudentId = ? AND [Date] = ?;";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setString(2, date);
            st.executeUpdate();
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
