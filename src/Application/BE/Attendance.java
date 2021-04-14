package Application.BE;

public class Attendance {

    private int id;
    private Class course;
    private Student stud;
    private int attendance;

    public Attendance(int id, Class course, Student stud, int attendance) {
        this.id = id;
        this.course = course;
        this.stud = stud;
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public Class getCourse() {
        return course;
    }

    public void setCourse(Class course) {
        this.course = course;
    }

    public Student getStud() {
        return stud;
    }

    public void setStud(Student stud) {
        this.stud = stud;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
