package Application.BE;

public class Teacher {

    private int teacherID;
    private String name;

    public Teacher(int teacherID, String name){
        this.teacherID = teacherID;
        this.name = name;
    }

    public int getTeacherID(){
        return teacherID;
    }

    public String getName(){
        return name;
    }
}
