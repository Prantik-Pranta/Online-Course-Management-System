package elms.app;

import elms.lib.ManagementSystem;
import elms.lib.Item;
import elms.lib.Student;
import elms.lib.WorkShop;
import elms.lib.Course;
import elms.lib.InvalidItemException;
import elms.lib.InvalidStudentException;

import java.util.ArrayList;
import java.util.Scanner;



public class OnlineCourseManagementApp {
    public static void main(String[] args) {
        ManagementSystem managementSystem = new ManagementSystem("Online Course Management");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Online Course Management System ===");
            System.out.println("1. Login as admin");
            System.out.println("2. Login as student");
            System.out.println("3. Switch role");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    adminMenu(scanner, managementSystem);
                    break;
                case 2:
                    studentMenu(scanner, managementSystem);
                    break;
                case 3:
                    System.out.println("Switching roles...");
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void adminMenu(Scanner scanner, ManagementSystem managementSystem) {
        boolean adminLoggedIn = true; 

        while (true) {
            if (adminLoggedIn) {
                System.out.println("Admin logged in successfully!");
            }

            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add course");
            System.out.println("2. Add workshop");
            System.out.println("3. Start a course");
            System.out.println("4. Complete a course");
            System.out.println("5. View registered students for a course");
            System.out.println("6. Search for courses");
            System.out.println("7. Add topic to a course");
            System.out.println("8. Assign project for a workshop");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addCourse(scanner, managementSystem);
                    break;
                case 2:
                    addWorkshop(scanner, managementSystem);
                    break;
                case 3:
                    startCourse(scanner, managementSystem);
                    break;
                case 4:
                    completeCourse(scanner, managementSystem);
                    break;
                case 5:
                    viewRegisteredStudents(scanner, managementSystem);
                    break;
                case 6:
                    searchCourses(scanner, managementSystem);
                    break;
                case 7:
                    addTopicToCourse(scanner, managementSystem);
                    break;
                case 8:
                    assignProjectToWorkshop(scanner, managementSystem);
                    break;
                case 9:
                    System.out.println("Admin logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

 

    private static void studentMenu(Scanner scanner, ManagementSystem managementSystem) {
        boolean studentLoggedIn = false;
        String currentStudentId = "";

        while (true) {
            if (!studentLoggedIn) {
                System.out.println("\n=== Student Access ===");
                System.out.println("1. Sign up");
                System.out.println("2. Login");
                System.out.println("3. Return to main menu");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter your date of birth (dd/MM/yyyy): ");
                        String dob = scanner.nextLine();
                        currentStudentId = managementSystem.signUp(name, dob);
                        System.out.println("Sign up successful! Your Student ID is: " + currentStudentId);
                        studentLoggedIn = true;
                        break;
                    case 2:
                        System.out.print("Enter your Student ID: ");
                        String studentId = scanner.nextLine();
					Student student = null;
					try {
						student = managementSystem.findStudent(studentId);
					} catch (InvalidStudentException e) {
					
					System.out.println(e.getMessage());
					}
                        if (student != null) {
                            currentStudentId = studentId;
                            studentLoggedIn = true;
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Invalid Student ID!");
                        }
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option!");
                        continue;
                }
            }

            
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View/search courses");
            System.out.println("2. Register for a course");
            System.out.println("3. View assigned projects");
            System.out.println("4. View registered courses");
            System.out.println("5. View completed courses");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    searchCourses(scanner, managementSystem);
                    break;
                case 2:
                    registerForCourse(scanner, managementSystem, currentStudentId);
                    break;
                case 3:
                    viewAssignedProjects(scanner, managementSystem, currentStudentId);
                    break;
                case 4:
                    viewRegisteredCourses(managementSystem, currentStudentId);
                    break;
                case 5:
                    viewCompletedCourses(managementSystem, currentStudentId);
                    break;
                case 6:
                    studentLoggedIn = false;
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

 
    private static void addCourse(Scanner scanner, ManagementSystem managementSystem) {
        System.out.println("\n=== Add New Course ===");
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter start date (dd/MM/yyyy): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter duration (in days): ");
        int duration = scanner.nextInt();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        
        String courseId = managementSystem.offerCourse(title, instructor, startDate, duration, capacity);
        System.out.println("Course added successfully! Course ID: " + courseId);
    }

    private static void addWorkshop(Scanner scanner, ManagementSystem managementSystem) {
        System.out.println("\n=== Add New Workshop ===");
        System.out.print("Enter workshop title: ");
        String title = scanner.nextLine();
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();
        System.out.print("Enter start date (dd/MM/yyyy): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter duration (in days): ");
        int duration = scanner.nextInt();
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        
        String workshopId = managementSystem.offerWorkshop(title, instructor, startDate, duration, capacity);
        System.out.println("Workshop added successfully! Workshop ID: " + workshopId);
    }

    private static void startCourse(Scanner scanner, ManagementSystem managementSystem) {
        System.out.print("Enter course ID to start: ");
        String courseId = scanner.nextLine();
        try {
			managementSystem.startCourse(courseId);
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("Course started successfully!");
    }

    private static void completeCourse(Scanner scanner, ManagementSystem managementSystem) {
        System.out.print("Enter course ID to complete: ");
        String courseId = scanner.nextLine();
        try {
			managementSystem.completeCourse(courseId);
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("Course completed successfully!");
    }

    private static void viewRegisteredStudents(Scanner scanner, ManagementSystem managementSystem) {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        ArrayList<Student> students = null;
		try {
			students = managementSystem.getRegisteredStudentInfo(courseId);
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
		}
        if (students.isEmpty()) {
            System.out.println("No students registered for this course.");
        } else {
            System.out.println("\nRegistered Students:");
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }
    }

    private static void searchCourses(Scanner scanner, ManagementSystem managementSystem) {
        System.out.print("Enter course title to search: ");
        String title = scanner.nextLine();
        ArrayList<Item> items = managementSystem.findItems(title);
        if (items.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("\nFound Courses:");
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    private static void addTopicToCourse(Scanner scanner, ManagementSystem managementSystem) {
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Enter topic: ");
        String topic = scanner.nextLine();
        try {
			managementSystem.addTopicToCourse(courseId, topic);
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("Topic added successfully!");
    }

    private static void assignProjectToWorkshop(Scanner scanner, ManagementSystem managementSystem) {
        System.out.print("Enter workshop ID: ");
        String workshopId = scanner.nextLine();
        System.out.print("Enter project details: ");
        String project = scanner.nextLine();
        try {
			managementSystem.assignProject(workshopId, project);
		} catch (InvalidItemException e) {
		
			System.out.println(e.getMessage());
		}
        System.out.println("Project assigned successfully!");
    }

  
    private static void registerForCourse(Scanner scanner, ManagementSystem managementSystem, String studentId) {
        System.out.print("Enter course/workshop ID to register: ");
        String itemId = scanner.nextLine();
        try {
			managementSystem.registerCourse(itemId, studentId);
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
		}
        System.out.println("Registration successful!");
    }

    private static void viewAssignedProjects(Scanner scanner, ManagementSystem managementSystem, String studentId) {
        ArrayList<Item> registeredCourses = null;
		try {
			registeredCourses = managementSystem.getRegisteredCourseInfo(studentId);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		}
        boolean foundWorkshop = false;
        
        for (Item item : registeredCourses) {
            if (item instanceof WorkShop) {
                foundWorkshop = true;
                WorkShop workshop = (WorkShop) item;
                System.out.println("Workshop: " + workshop.getTitle());
                System.out.println("Assigned Project: " + workshop.getProject());
            }
        }
        
        if (!foundWorkshop) {
            System.out.println("No workshops registered or no projects assigned.");
        }
    }

    private static void viewRegisteredCourses(ManagementSystem managementSystem, String studentId) {
        ArrayList<Item> registeredCourses = null;
		try {
			registeredCourses = managementSystem.getRegisteredCourseInfo(studentId);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		}
        if (registeredCourses.isEmpty()) {
            System.out.println("No registered courses found.");
        } else {
            System.out.println("\nRegistered Courses:");
            for (Item item : registeredCourses) {
                System.out.println(item.toString());
            }
        }
    }

    private static void viewCompletedCourses(ManagementSystem managementSystem, String studentId) {
        ArrayList<Item> completedCourses = null;
		try {
			completedCourses = managementSystem.getCompletedCourseInfo(studentId);
		} catch (InvalidStudentException e) {
			System.out.println(e.getMessage());
		} catch (InvalidItemException e) {
			System.out.println(e.getMessage());
		}
        if (completedCourses.isEmpty()) {
            System.out.println("No completed courses found.");
        } else {
            System.out.println("\nCompleted Courses:");
            for (Item item : completedCourses) {
                System.out.println(item.toString());
            }
        }
    }
}