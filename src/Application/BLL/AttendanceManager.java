package Application.BLL;

import Application.BE.Class;
import Application.BE.Student;
import Application.DAL.DALManager;

import java.util.List;

public class AttendanceManager {

    private final DALManager DALManager;

    public AttendanceManager(){
        DALManager = new DALManager();
    }

    /**
     * Gets a list of all the students from the DALManager.
     * @return the list of all students.
     */
    public List<Student> getAllStudents(){
        return DALManager.getAllStudents();
    }

    /**
     * Gets a list of all the classes from the DALManager.
     * @return the list of students.
     */
    public List<Class> getAllClasses(){
        return DALManager.getAllClasses();
    }
}
