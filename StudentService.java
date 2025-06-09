package service;

import model.Student;
import util.FileUtil;

import java.util.*;

public class StudentService {

    Scanner sc = new Scanner(System.in);

    public void addStudent() {
        List<Student> students = FileUtil.readStudents();

        int id;
        try {
            System.out.print("Enter ID: ");
            id = sc.nextInt();
            sc.nextLine(); // consume newline
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. ID must be a number.");
            sc.nextLine();
            return;
        }

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("A student with this ID already exists.");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        int age;
        try {
            System.out.print("Enter Age: ");
            age = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Age must be a number.");
            sc.nextLine();
            return;
        }

        if (age <= 0) {
            System.out.println("Age must be a positive number.");
            return;
        }

        System.out.print("Enter Course: ");
        String course = sc.nextLine().trim();
        if (course.isEmpty()) {
            System.out.println("Course cannot be empty.");
            return;
        }

        Student student = new Student(id, name, age, course);
        students.add(student);
        FileUtil.writeStudents(students);

        System.out.println("Student added successfully!");
    }

    public void viewStudents() {
        List<Student> students = FileUtil.readStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.printf("%-10s %-15s %-5s %-10s\n", "ID", "Name", "Age", "Course");
            System.out.println("---------------------------------------------");
            for (Student s : students) {
                System.out.printf("%-10d %-15s %-5d %-10s\n",
                        s.getId(), s.getName(), s.getAge(), s.getCourse());
            }
        }
    }

    public void searchStudent() {
        List<Student> students = FileUtil.readStudents();
        int id;
        try {
            System.out.print("Enter ID to search: ");
            id = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. ID must be a number.");
            sc.nextLine();
            return;
        }

        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student Found: ");
                System.out.printf("ID: %d | Name: %s | Age: %d | Course: %s\n",
                        s.getId(), s.getName(), s.getAge(), s.getCourse());
                return;
            }
        }
        System.out.println("Student not found.");
    }

    public void deleteStudent() {
        List<Student> students = FileUtil.readStudents();
        int id;
        try {
            System.out.print("Enter ID to delete: ");
            id = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. ID must be a number.");
            sc.nextLine();
            return;
        }

        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed) {
            FileUtil.writeStudents(students);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void updateStudent() {
        List<Student> students = FileUtil.readStudents();
        int id;
        try {
            System.out.print("Enter student ID to update: ");
            id = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. ID must be a number.");
            sc.nextLine();
            return;
        }

        boolean found = false;
        for (Student s : students) {
            if (s.getId() == id) {
                found = true;
                System.out.println("1. Update Name");
                System.out.println("2. Update Age");
                System.out.println("3. Update Course");
                System.out.print("Enter your choice: ");

                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Must be a number.");
                    sc.nextLine();
                    return;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine().trim();
                        if (newName.isEmpty()) {
                            System.out.println("Name cannot be empty.");
                            return;
                        }
                        s.setName(newName);
                        break;
                    case 2:
                        int newAge;
                        try {
                            System.out.print("Enter new age: ");
                            newAge = sc.nextInt();
                            sc.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid age input.");
                            sc.nextLine();
                            return;
                        }

                        if (newAge <= 0) {
                            System.out.println("Age must be positive.");
                            return;
                        }
                        s.setAge(newAge);
                        break;
                    case 3:
                        System.out.print("Enter new course: ");
                        String newCourse = sc.nextLine().trim();
                        if (newCourse.isEmpty()) {
                            System.out.println("Course cannot be empty.");
                            return;
                        }
                        s.setCourse(newCourse);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        return;
                }
                break;
            }
        }

        if (found) {
            FileUtil.writeStudents(students);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void viewSortedStudents() {
        List<Student> students = FileUtil.readStudents();
        students.sort(Comparator.comparing(Student::getName));

        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.printf("%-10s %-15s %-5s %-10s\n", "ID", "Name", "Age", "Course");
            System.out.println("---------------------------------------------");
            for (Student s : students) {
                System.out.printf("%-10d %-15s %-5d %-10s\n",
                        s.getId(), s.getName(), s.getAge(), s.getCourse());
            }
        }
    }
}
