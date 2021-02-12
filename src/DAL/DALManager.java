package DAL;

import BE.Class;
import BE.Student;

import java.util.ArrayList;
import java.util.List;

public class DALManager {


    public List<Student> getAllStudents(){
        List<Student> allStudents = new ArrayList<>();

        allStudents.add(new Student(1, "Doria Bulford", 10));
        allStudents.add(new Student(2, "Regen Hearson", 1));
        allStudents.add(new Student(3, "Bruis Hazlegrove", 0));
        allStudents.add(new Student(3, "Elisa Hammerberger", 0));
        allStudents.add(new Student(3, "Binni Crankshaw", 2));
        allStudents.add(new Student(3, "Lexy Davion", 5));
        allStudents.add(new Student(3, "Kellsie Goodburn", 15));
        allStudents.add(new Student(3, "Worth Velasquez", 0));
        allStudents.add(new Student(3, "Dalli Burnie",0));

        return allStudents;
    }

    public List<Class> getAllClasses() {
        List<Class> allClasses = new ArrayList<>();

        allClasses.add(new Class(1, "ITO", "09:00-11:15"));
        allClasses.add(new Class(2, "SCO", "11:15-12:00"));
        allClasses.add(new Class(3, "SDE", "08:45-12:00"));
        allClasses.add(new Class(4, "DBOS", "08:45-14:00"));

        return  allClasses;
    }
}