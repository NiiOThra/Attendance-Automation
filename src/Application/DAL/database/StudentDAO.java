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
    public List<Student> getAllStudents() throws SQLException{
        List<Student> allStudents = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM Persons WHERE IsStudent = 1");

            while (rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");

                Student stud = new Student(id, name, type);
                allStudents.add(stud);
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
        String sql = "UPDATE CourseAttendance SET HasAttended = 1, CreatedBy = ? WHERE StudentId = ? AND CourseId = ? AND Date = CONVERT(date, GETDATE());";
        try (Connection con = connectionPool.checkOut();
             PreparedStatement st = con.prepareStatement(sql)){
            st.setInt(1, studentId);
            st.setInt(2, studentId);
            st.setInt(3, courseId);

            st.execute();
        }
    }

    /**public List<Attendance> getAttendance() throws SQLException{
        List<Attendance> attendanceCourse = new ArrayList<>();
        Connection con = connectionPool.checkOut();
        try (Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery( "SELECT *, Courses.CourseName " +
                    "FROM CourseAttendance " +
                    "INNER JOIN StudentCourse " +
                    "ON CourseAttendance.StudentCourseId = StudentCourse.Id " +
                    "INNER JOIN Persons ON StudentCourse.StudentId = Persons.ID " +
                    "INNER JOIN Courses ON StudentCourse.CourseId = Courses.Id");


            Student stud = null;
            Attendance att = null;
            while (rs.next()){
                int id = rs.getInt("StudentId");
                String studentName = rs.getString("Name");
                int classId = rs.getInt("CourseId");
                String className = rs.getString("CourseName");
                Class course = new Class(classId, className);

                stud= new Student(id, studentName);
                int attendance = rs.getInt("Attendance");

                att = new Attendance(id, course, stud, attendance);
                attendanceCourse.add(att);
            }
            return attendanceCourse;
        }
    }**/
}
