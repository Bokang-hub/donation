package za.ac.cput.donation.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.donation.entity.Donor;
import za.ac.cput.donation.entity.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    @Test
    void createStudent() {
        Student s = StudentFactory.createStudent("King", "Pin", "Kpin@gmail.com", true, "yynnm");
        assertNotNull(s);
        assertEquals("Pin", s.getLastName());
        System.out.println(s);
    }
}