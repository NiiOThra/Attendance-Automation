package Application.BLL;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.SQLException;


class AttendanceManagerTest {

    AttendanceManager attManager;

    AttendanceManagerTest () throws IOException, SQLException {
        attManager = new AttendanceManager();
    }

    @org.junit.jupiter.api.Test void getLogin() throws SQLException {

        //Act - do the actual method run
        String actualPerson = String.valueOf(attManager.getLogin("apicot4@indiatimes.com", "wHVXcTeY3t"));
        String expectedPerson = "Alaric Picot";

        //Assert - check if actual val is equal to expected val
        Assertions.assertEquals(expectedPerson, actualPerson);

    }

    @org.junit.jupiter.api.Test
    void getTeacherClasses() throws SQLException {
        //Act - do the actual method run

        String actualClass = String.valueOf(attManager.getTeacherClasses(5));
        String expectedClass ="ITO";

        //Assert - check if actual val i equal to expected cal
        Assertions.assertEquals(expectedClass, actualClass);
    }

    @org.junit.jupiter.api.Test
    void todaysCourse() throws SQLException {

        //Act - do the actual method run
        String actualCourse = String.valueOf(attManager.todaysCourse(20));
        String expectedCourse = "ITO";

        //Assert - check if actual val i equal to expected cal
        Assertions.assertEquals(expectedCourse, actualCourse);

    }

    @org.junit.jupiter.api.Test
    void getOffDays() throws SQLException {

        //Act - do the actual method run
        int actualOffDays = attManager.getOffDays(10, "Monday");
        int expectedOffDays = 1;

        //Assert - check if actual val i equal to expected cal
        Assertions.assertEquals(expectedOffDays, actualOffDays);
    }

    @org.junit.jupiter.api.Test
    void getAbsence() throws SQLException {

        //Act - do the actual method run
        int actualAbsence = attManager.getAbsence(18);
        int expectedAbsence = 100-88;

        //Assert - check if actual val i equal to expected cal
        Assertions.assertEquals(expectedAbsence, actualAbsence);
    }

    @org.junit.jupiter.api.Test
    void getAttendance() throws SQLException {

        //Act - do the actual method run
        int actualAttendance = attManager.getAttendance(16);
        int expectedAttendance = 34;

        //Assert - check if actual val i equal to expected cal
        Assertions.assertEquals(expectedAttendance, actualAttendance);
    }
}