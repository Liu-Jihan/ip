package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testValidDeadlineCreation() {
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");

        // Check if the deadline description is stored correctly
        assertEquals("return book", deadline.description);  // Assuming 'description' is inherited from Task

        // Check formatted due date
        assertEquals("[D][ ] return book (by: Dec 02 2019, 6:00 PM)", deadline.toString());

        // Check file format representation
        assertEquals("D | 0 | return book | 2/12/2019 1800", deadline.toFileFormat());
    }

    @Test
    void testAnotherValidDeadline() {
        Deadline deadline = new Deadline("submit report", "25/12/2022 2359");

        assertEquals("submit report", deadline.description);
        assertEquals("[D][ ] submit report (by: Dec 25 2022, 11:59 PM)", deadline.toString());
        assertEquals("D | 0 | submit report | 25/12/2022 2359", deadline.toFileFormat());
    }

    @Test
    void testDeadlineWithLeadingZeroes() {
        Deadline deadline = new Deadline("wake up", "05/06/2023 0830");

        assertEquals("[D][ ] wake up (by: Jun 05 2023, 8:30 AM)", deadline.toString());
        assertEquals("D | 0 | wake up | 5/6/2023 0830", deadline.toFileFormat());
    }

    @Test
    void testInvalidDateFormat_ShouldReturnInvalidDate() {
        Deadline deadline = new Deadline("invalid date", "2023-12-31 2359"); // Wrong format

        assertEquals("[D][ ] invalid date (by: Invalid DateTime)", deadline.toString());
        assertEquals("D | 0 | invalid date | Invalid DateTime", deadline.toFileFormat());
    }

    @Test
    void testInvalidTimeFormat_ShouldReturnInvalidDate() {
        Deadline deadline = new Deadline("wrong time", "2/12/2019 18:00"); // Wrong format

        assertEquals("[D][ ] wrong time (by: Invalid DateTime)", deadline.toString());
        assertEquals("D | 0 | wrong time | Invalid DateTime", deadline.toFileFormat());
    }

    @Test
    void testEmptyDate_ShouldReturnInvalidDate() {
        Deadline deadline = new Deadline("no date", "");

        assertEquals("[D][ ] no date (by: Invalid DateTime)", deadline.toString());
        assertEquals("D | 0 | no date | Invalid DateTime", deadline.toFileFormat());
    }

    @Test
    void testEmptyDescription_ShouldStillWork() {
        Deadline deadline = new Deadline("", "2/12/2019 1800");

        assertEquals("[D][ ]  (by: Dec 02 2019, 6:00 PM)", deadline.toString());
        assertEquals("D | 0 |  | 2/12/2019 1800", deadline.toFileFormat());
    }

    @Test
    void testDeadlineMarkedAsDone() {
        Deadline deadline = new Deadline("finish homework", "10/10/2025 2230");
        deadline.markAsDone();

        assertEquals("[D][X] finish homework (by: Oct 10 2025, 10:30 PM)", deadline.toString());
        assertEquals("D | 1 | finish homework | 10/10/2025 2230", deadline.toFileFormat());
    }
}
