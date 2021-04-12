package Application.DAL.database;

import Application.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDAO {

    private MyDatabaseConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public TeacherDAO() throws IOException, SQLServerException {
        dbConnector = new MyDatabaseConnector();
        connectionPool = JDBCConnectionPool.getInstance();
    }

    public Teacher getTeacherLogin(String username, String password) throws SQLException {
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
            Teacher teacher = null;
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int type = rs.getInt("Type");
                int loginID = rs.getInt("LoginId");
                teacher = new Teacher(id, name, type);
            }
            return teacher;
        }
    }
}
