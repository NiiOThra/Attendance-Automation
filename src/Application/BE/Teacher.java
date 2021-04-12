package Application.BE;

public class Teacher {

    private int teacherID;
    private String name;
    private int type = 0;

    public Teacher(int teacherID, String name, int type){
        this.teacherID = teacherID;
        this.name = name;
        this.type = type;
    }

    public int getTeacherID(){
        return teacherID;
    }

    public String getName(){
        return name;
    }

    public int getType(){
        return type;
    }
}
