package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    private StudentManager manager;

    @BeforeEach
    void setUp() {
        manager = new StudentManager();
    }

    @Test
    void testAddStudent() {
        manager.addStudent("Alice");
        assertTrue(manager.hasStudent("Alice"));//Alice 있으니까 hasStudent true 
    }

    @Test
    void testRemoveStudent() {
        manager.addStudent("Bob");
        manager.removeStudent("Bob");
        assertFalse(manager.hasStudent("Bob"));//Bob 제거했으니까 hasStudent false
    }

    @Test
    void testDuplicateStudentThrowsException() {
        manager.addStudent("Charlie");
        assertThrows(IllegalArgumentException.class, () -> manager.addStudent("Charlie"));//Charlie 두 번 추가하면 예외.
    }

    @Test
    void testRemoveNonExistentStudentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> manager.removeStudent("Daniel"));//Daniel은 없으니까 예외.
    }
}
