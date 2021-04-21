package Application.BE;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Student extends Person {

    String checkedIn;

    public Student(int studentID, String name, int type, String checkedIn) {
        super(studentID, name, type);
        this.checkedIn = checkedIn;
    }

    public String getCheckedIn(){
        return checkedIn;
    }

    public void setCheckedIn(String checkedIn){
        this.checkedIn = checkedIn;
    }
}
