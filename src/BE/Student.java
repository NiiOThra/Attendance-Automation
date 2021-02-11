package BE;

public class Student {

    private int studentID;
    private String name;
    private int absence;


    public Student(int studentID, String name, int absence) {
        this.studentID = studentID;
        this.name = name;
        this.absence = absence;
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

    public int getAbsence(){
        return absence;
    }


}
