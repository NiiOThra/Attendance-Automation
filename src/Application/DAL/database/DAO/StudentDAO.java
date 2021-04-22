package Application.DAL.database.DAO;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;
import Application.DAL.database.JDBCConnectionPool;
import Application.DAL.database.MyDatabaseConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private MyDatabaseConnector dbConnector;
    private final JDBCConnectionPool connectionPool;

    public StudentDAO() throws SQLException, IOException {
        connectionPool = JDBCConnectionPool.getInstance();
        dbConnector = new MyDatabaseConnector();
    }

    /**
     * Gets a list of all persons with type 1 from the database. Type 1 is students.
     * @return a list of Student objects.
     * @throws SQLException
     */
    public List<Attendance> getAllStudents() throws SQLException{
        List<Attendance> allStudents = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM Persons WHERE IsStudent = 1");

            Attendance att = null;
            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");
                int attendancePercent = rs.getInt("AttendancePercent");

                Student stud = new Student(id, name, type, null);
                att = new Attendance(id, null, stud, attendancePercent);
                allStudents.add(att);
            }
            return allStudents;
        }
    }

    /**
     * This is for the student to check in. When this method is run, it will update the row for the loggedin student so
     * that the student has attended the class.
     * @param studentId the loggedin
     * @param courseId the course that the teacher opened.
     * @throws SQLException
     */
    public void checkIn(int studentId, int courseId) throws SQLException{
        String sql = "UPDATE CourseAttendance SET HasAttended = 'True', CreatedBy = ? WHERE StudentId = ? AND CourseId = ? AND Date = CONVERT(date, GETDATE());";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, studentId);
            st.setInt(2, studentId);
            st.setInt(3, courseId);

            st.execute();
        }
    }

    public int getOffDay(int studentId, String weekday) throws SQLException {
        String sql = "SELECT CourseAttendance.WeekDay, COUNT(WeekDay) AS offDays " +
                "FROM CourseAttendance " +
                "WHERE HasAttended = 'False' AND studentId = ? AND WeekDay = ? " +
                "GROUP BY WeekDay;";

        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, studentId);
            st.setString(2, weekday);
            st.execute();

            int offdays = 0;
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                offdays = rs.getInt("offDays");
                return offdays;
            }
            return offdays;
        }
    }

    public int getAttendance(int studentId) throws SQLException{
        String sql = "SELECT AttendancePercent FROM Persons WHERE Id = ?";

        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, studentId);
            st.execute();

            int attendance = 0;
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                attendance = rs.getInt("AttendancePercent");
            }
            return attendance;
        }
    }

    public int getAbsence(int studentId) throws SQLException{
        String sql = "SELECT AttendancePercent FROM Persons WHERE Id = ?";

        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, studentId);
            st.execute();

            int attendance = 0;
            int absence = 0;
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                attendance = rs.getInt("AttendancePercent");
                absence = 100 - attendance;
            }
            return absence;
        }
    }

    public List<String> getAbsenceDays(int studentId) throws SQLException{
        List<String> absenceDays = new ArrayList<>();
        String sql = "SELECT [Date] FROM CourseAttendance WHERE HasAttended = 'False' AND StudentId = ?;";
        try (Connection con = connectionPool.checkOut()){
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, studentId);
            st.execute();

            String absenceDay = null;
            ResultSet rs = st.getResultSet();
            while (rs.next()){
                absenceDay = rs.getString("Date");
                absenceDays.add(absenceDay);
            }
            return absenceDays;
        }
    }

    /**
     * Select a list of all students from the database for the teacher to get an overview of todays class..
     * Who's checked in and who's not checked in.
     * @throws SQLException
     */
    public List<Person> getTodaysStudent() throws SQLException{
        List<Person> todaysStudents = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery("SELECT Name, CourseAttendance.HasAttended, Persons.Id, Persons.IsStudent, " +
                    "CourseAttendance.StudentId, CourseAttendance.[Date] " +
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

                todaysStudents.add(stud);
            }
            return todaysStudents;
        }
    }
}
