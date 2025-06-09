package main;

import service.StudentService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("6. Update Student");                // ✅ NEW
            System.out.println("7. View Students Sorted by Name");  // ✅ NEW
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: service.addStudent(); break;
                case 2: service.viewStudents(); break;
                case 3: service.searchStudent(); break;
                case 4: service.deleteStudent(); break;
                case 5: System.out.println("Exiting..."); break;
                case 6: service.updateStudent(); break;              // ✅ NEW
                case 7: service.viewSortedStudents(); break;         // ✅ NEW
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
}
