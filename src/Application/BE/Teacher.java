package Application.BE;

public class Teacher {

    private int teacherID;
    private String name;
    private String className;

    public Teacher(int teacherID, String name, String className){
        this.teacherID = teacherID;
        this.name = name;
        this.className = className;
    }

    public int getTeacherID(){
        return teacherID;
    }

    public String getName(){
        return name;
    }

    public String getClassName(){
        return className;
    }
}
