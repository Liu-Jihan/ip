import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // Loads tasks from the file
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
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
        file.getParentFile().mkdirs(); // Ensure the folder exists

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + "\n"); // Save each task in file format
            }
        } catch (IOException e) {
            System.out.println("OOPS!!! Error writing to file: " + e.getMessage());
        }
    }
}
