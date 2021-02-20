package DAL;

import BE.Class;
import BE.Student;

import java.util.ArrayList;
import java.util.List;

public class DALManager {


    /**
     * Creating a bunch of students and adds them to an arraylist.
     *
     * @return the list of all students.
     */
    public static List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();

        allStudents.add(new Student(1, "Doria Bulford", 50));
        allStudents.add(new Student(2, "Regen Hearson", 100));
        allStudents.add(new Student(3, "Bruis Hazlegrove", 96));
        allStudents.add(new Student(4, "Elisa Hammerberger", 84));
        allStudents.add(new Student(5, "Binni Crankshaw", 55));
        allStudents.add(new Student(6, "Lexy Davion", 90));
        allStudents.add(new Student(7, "Kellsie Goodburn", 55));
        allStudents.add(new Student(8, "Worth Velasquez", 88));
        allStudents.add(new Student(9, "Dalli Burnie", 89));

        return allStudents;
    }

    /**
     * Creating some classes and adds them to an arraylist.
     *
     * @return the list of all classes.
     */
    public static List<Class> getAllClasses() {
        List<Class> allClasses = new ArrayList<>();

        allClasses.add(new Class(1, "ITO", "09:00-11:15"));
        allClasses.add(new Class(2, "SCO", "11:15-12:00"));
        allClasses.add(new Class(3, "SDE", "08:45-12:00"));
        allClasses.add(new Class(4, "DBOS", "08:45-14:00"));

        return allClasses;
    }
}
