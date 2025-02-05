package storage;

import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the loading and saving of tasks to a persistent storage file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFileExists(); // Ensures the file and directory exist at initialization
    }

    /**
     * Ensures that the storage file and its directory exist.
     */

    private void ensureFileExists() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if necessary
                file.createNewFile(); // Create the file if it does not exist
            }
        } catch (IOException e) {
            System.out.println("OOPS!!! Error creating storage file: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     */

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No previous tasks found. Starting with an empty list.");
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("OOPS!!! Error reading file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(List<Task> tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // Ensure the directory exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            StringBuilder sb = new StringBuilder();
            for (Task task : tasks) {
                sb.append(task.toFileFormat()).append("\n");
            }
            writer.write(sb.toString()); // Write all tasks at once (improves performance)
        } catch (IOException e) {
            System.out.println("OOPS!!! Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Deletes all stored tasks by removing the storage file.
     */
    public void clearTasks() {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("All tasks cleared successfully.");
            } else {
                System.out.println("OOPS!!! Failed to clear tasks.");
            }
        }
    }
}

