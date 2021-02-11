package BLL;

import BE.Class;
import BE.Student;
import DAL.DALManager;

import java.util.List;

public class AttendanceManager {

    private final DALManager DALManager;

    public AttendanceManager(){
        DALManager = new DALManager();
    }

    public List<Student> getAllStudents(){
        return DALManager.getAllStudents();
    }

    public List<Class> getAllClasses(){
        return DALManager.getAllClasses();
    }
}
