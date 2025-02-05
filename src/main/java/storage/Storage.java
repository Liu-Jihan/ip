package storage;

import task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Storage {
    private final String filePath;
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    public Storage(String filePath) {
        this.filePath = filePath;
        ensureFileExists(); // Ensures the file and directory exist at initialization
    }

    // Ensures the file and directory exist
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

    // Loads tasks from the file
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

    // Saves tasks to the file
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

    // Deletes all stored tasks (for reset)
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

