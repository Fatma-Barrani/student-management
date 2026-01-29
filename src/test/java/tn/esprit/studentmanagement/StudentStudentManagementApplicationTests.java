package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StudentManagementApplicationTests {

    @Test
    void contextLoads() {
        // Test if Spring Boot context loads successfully
        assertNotNull(this);
    }

    @Test
    void simpleLogicTest() {
        int a = 5;
        int b = 3;
        int sum = a + b;

        assertEquals(8, sum); // expected result = 8
    }
}
