package DAL;

import BE.Class;
import BE.Student;

import java.util.ArrayList;
import java.util.List;

public class DALManager {


    /**
     * Creating a bunch of students and adds them to an arraylist.
     * @return the list of all students.
     */
    public List<Student> getAllStudents(){
        List<Student> allStudents = new ArrayList<>();

        allStudents.add(new Student(1, "Doria Bulford", 66));
        allStudents.add(new Student(2, "Regen Hearson", 50));
        allStudents.add(new Student(3, "Bruis Hazlegrove", 100));
        allStudents.add(new Student(3, "Elisa Hammerberger", 100));
        allStudents.add(new Student(3, "Binni Crankshaw", 90));
        allStudents.add(new Student(3, "Lexy Davion", 95));
        allStudents.add(new Student(3, "Kellsie Goodburn", 85));
        allStudents.add(new Student(3, "Worth Velasquez", 75));
        allStudents.add(new Student(3, "Dalli Burnie",100));

        return allStudents;
    }

    /**
     * Creating some classes and adds them to an arraylist.
     * @return the list of all classes.
     */
    public List<Class> getAllClasses() {
        List<Class> allClasses = new ArrayList<>();

        allClasses.add(new Class(1, "ITO", "09:00-11:15"));
        allClasses.add(new Class(2, "SCO", "11:15-12:00"));
        allClasses.add(new Class(3, "SDE", "08:45-12:00"));
        allClasses.add(new Class(4, "DBOS", "08:45-14:00"));

        return  allClasses;
    }
}
