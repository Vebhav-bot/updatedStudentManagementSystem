package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class FileUtil {
    private static final String FILE_NAME = "students.txt";

    public static void initFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error initializing file: " + e.getMessage());
        }
    }

    public static List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean skipHeader = true;
            while ((line = br.readLine()) != null) {
                if (skipHeader || line.startsWith("-") || line.trim().isEmpty()) {
                    skipHeader = false;
                    continue; // skip headers and divider lines
                }
                try {
                    students.add(Student.fromStringFormatted(line));
                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return students;
    }

    public static void writeStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            // Write header
            bw.write(String.format("%-10s %-20s %-5s %-15s", "ID", "Name", "Age", "Course"));
            bw.newLine();
            bw.write("----------------------------------------------------------");
            bw.newLine();

            // Write student data
            for (Student s : students) {
                bw.write(String.format("%-10d %-20s %-5d %-15s",
                        s.getId(), s.getName(), s.getAge(), s.getCourse()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}