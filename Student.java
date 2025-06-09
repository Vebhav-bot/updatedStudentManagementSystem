package model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    // Used only if you want to write/read in plain CSV (not used for formatted file)
    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + course;
    }

    // Reads from plain CSV (only used if you decide to go back to comma-based file)
    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(
            Integer.parseInt(parts[0]),
            parts[1],
            Integer.parseInt(parts[2]),
            parts[3]
        );
    }

    // ✅ NEW: Used for formatted lines in students.txt
    public static Student fromStringFormatted(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid student format: " + line);
        }

        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        int age = Integer.parseInt(parts[2]);
        String course = parts[3];
        return new Student(id, name, age, course);
    }
}