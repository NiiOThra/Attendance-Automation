package Application.BE;

public class Person {

    private int id;
    private String name;
    private int type;

    public Person(int studentID, String name, int type) {
        this.id = studentID;
        this.name = name;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }
}
