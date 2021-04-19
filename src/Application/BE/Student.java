package Application.BE;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Student extends Person {

    int checkedIn;

    public Student(int studentID, String name, int type, int checkedIn) {
        super(studentID, name, type);
        this.checkedIn = checkedIn;
    }

    public int getCheckedIn(){
        return checkedIn;
    }

    public void setCheckedIn(int checkedIn){
        this.checkedIn = checkedIn;
    }
}
