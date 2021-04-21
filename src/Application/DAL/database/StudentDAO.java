package Application.DAL.database;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;

import javax.swing.text.html.HTMLDocument;
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

    public void updateAbsenceDay(int studentId, String date)throws SQLException {
        String sql = "UPDATE CourseAttendance SET HasAttended = 'True' WHERE StudentId = ? AND [Date] = ?;";
        try (Connection con = connectionPool.checkOut()) {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setString(2, date);
            st.executeUpdate();
        }
    }

    /**public List<Attendance> getAttendance() throws SQLException{
        List<Attendance> attendanceCourse = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery( "SELECT Name, Persons.AttendancePercent, Persons.Id, Persons.IsStudent, " +
                    "FROM Persons " +
                    "INNER JOIN " +
                    "CourseAttendance " +
                    "ON Persons.Id = CourseAttendance.StudentId " +
                    "WHERE CourseAttendance.[Date] = CONVERT(date, getdate())");

            Student stud = null;
            Attendance att = null;
            while (rs.next()){
                int id = rs.getInt("StudentId");
                String studentName = rs.getString("Name");
                int classId = rs.getInt("CourseId");
                String className = rs.getString("CourseName");
                int type = rs.getInt("IsStudent");
                int checkedIn = rs.getInt("HasAttended");

                stud= new Student(id, studentName, type, checkedIn);
                int attendance = rs.getInt("AttendancePercent");

                att = new Attendance(id, null, stud, attendance);
                attendanceCourse.add(att);
            }
            return attendanceCourse;
        }
    }**/
}
