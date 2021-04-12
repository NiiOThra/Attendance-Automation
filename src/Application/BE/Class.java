package Application.BE;


public class Class {

    private int classID;
    private String name;

    public Class(int classID, String name) {
        this.classID = classID;
        this.name = name;
    }

    public int getClassID() {
        return classID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
