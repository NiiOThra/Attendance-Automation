package Application.BE;

public class Teacher {

    private int teacherID;
    private String name;
    private Class className;

    public Teacher(int teacherID, String name, Class className){
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

    public Class getClassName(){
        return className;
    }
}
