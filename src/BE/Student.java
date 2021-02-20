package BE;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Student extends RecursiveTreeObject<Student> {

    private int studentID;
    private String name;
    private int attendance;


    public Student(int studentID, String name, int attendance) {
        this.studentID = studentID;
        this.name = name;
        this.attendance = attendance;
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

    public int getAttendance(){
        return attendance;
    }

}
