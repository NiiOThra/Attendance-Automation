package Application.DAL.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDatabaseConnector {

    private static final String PROP_FILE = "src/Application/DAL/database/database.settings.txt";
    private SQLServerDataSource ds;

    public MyDatabaseConnector() throws IOException {
        Properties dbProperties = new Properties();
        dbProperties.load(new FileInputStream(new File(PROP_FILE)));

        ds = new SQLServerDataSource();
        ds.setServerName(dbProperties.getProperty("server"));
        ds.setDatabaseName(dbProperties.getProperty("database"));
        ds.setUser(dbProperties.getProperty("username"));
        ds.setPassword(dbProperties.getProperty("password"));

    }

    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }

    public static void main(String[]args) throws SQLException, IOException {
        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();
        Connection connection = databaseConnector.getConnection();

        System.out.print("Is it open? " + !connection.isClosed());

        connection.close();
    }
}
