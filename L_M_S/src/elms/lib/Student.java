package elms.lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Student {

    private String name;
    private String id;
    private LocalDate dateOfBirth; // LocalDate for date of birth
    private ArrayList<String> registeredCourseIds = new ArrayList<>();
    private ArrayList<String> completedCourseIds = new ArrayList<>();

    // Parameterized constructor
    public Student(String name, String dateOfBirth) {
        this.name = name;
        Random rn = new Random();
        this.id = String.format("S-%04d", rn.nextInt(10000));

        // Convert dateOfBirth parameter to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
    }

    public void registerCourse(String courseId) {
        if (!registeredCourseIds.contains(courseId)) {
            registeredCourseIds.add(courseId);
        }
    }

    public void cancelRegistration(String courseId) {
        if (registeredCourseIds.contains(courseId)) {
            registeredCourseIds.remove(courseId);
        } else {
            System.out.println("Course ID Not Found");
        }
    }

    public boolean completeCourse(String courseId) {
        if (registeredCourseIds.contains(courseId)) {
            registeredCourseIds.remove(courseId);
            completedCourseIds.add(courseId);
            return true;
        }
        return false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getID() {
        return id;
    }

    public ArrayList<String> getRegisteredCourseIds() {
        return registeredCourseIds;
    }

    public ArrayList<String> getCompletedCourseIds() {
        return completedCourseIds;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nID: %s\nDate of Birth: %s\n", name, id, dateOfBirth) +
               "Registered Course Ids:\n" + registeredCourseIds +
               "\nCompleted Course Ids:\n" + completedCourseIds + "\n";
    }
}