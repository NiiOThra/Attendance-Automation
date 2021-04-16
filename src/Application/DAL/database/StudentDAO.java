package Application.DAL.database;

import Application.BE.Attendance;
import Application.BE.Class;
import Application.BE.Person;
import Application.BE.Student;

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

    public Student getStudentData(String username, String password) throws SQLException {
        try (Connection con = connectionPool.checkOut()) {
            String query = "SELECT Name, Persons.Id, Persons.IsStudent, Persons.LoginId " +
                    "FROM Persons " +
                    "INNER JOIN " +
                    "LoginInformation ON Persons.LoginId = LoginInformation.Id WHERE Username = ? AND [Password] = ? AND IsStudent = 1";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            st.execute();

            ResultSet rs = st.getResultSet();
            Student stud = null;
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int type = rs.getInt("IsStudent");
                int loginID = rs.getInt("LoginId");
                stud = new Student(id, name, type);
            }
            return stud;
        }
    }


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
