package task;

import oscarl.OscarLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<>());  // Initialize an empty TaskList before each test
    }

    @Test
    void testAddTask() {
        Task todoTask = new ToDo("borrow book");
        taskList.addTask(todoTask);

        assertEquals(1, taskList.size());
        assertEquals("[T][ ] borrow book", taskList.getTasks().get(0).toString());
    }


    @Test
    void testMarkTaskAsDone() throws OscarLException {
        Task task = new ToDo("finish homework");
        taskList.addTask(task);

        taskList.markTask(0, true);  // Mark as done

        assertEquals("[T][X] finish homework", taskList.getTasks().get(0).toString());
    }

    @Test
    void testRemoveTask() throws OscarLException {
        Task task = new ToDo("practice coding");
        taskList.addTask(task);

        Task removedTask = taskList.removeTask(0);  // Remove task at index 0
        assertEquals("[T][ ] practice coding", removedTask.toString());
        assertEquals(0, taskList.size());  // Ensure the list is empty after removal
    }

    @Test
    void testRemoveTaskInvalidIndex() {
        Exception exception = assertThrows(OscarLException.class, () -> {
            taskList.removeTask(0);  // No task exists at index 0
        });

        assertEquals("Invalid task number.", exception.getMessage());
    }

    @Test
    void testMarkTaskInvalidIndex() {
        Exception exception = assertThrows(OscarLException.class, () -> {
            taskList.markTask(0, true);  // No task exists at index 0
        });

        assertEquals("Invalid task number.", exception.getMessage());
    }
}
