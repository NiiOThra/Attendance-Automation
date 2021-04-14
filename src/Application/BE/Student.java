package Application.BE;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Student extends RecursiveTreeObject<Student> {

    private int studentID;
    private String name;


    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
    }

    public int getId(){
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
