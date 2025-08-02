package elms.lib;

import java.util.ArrayList;

public class ManagementSystem {

    private String name;
    private ArrayList<Item> courses = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public ManagementSystem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Item> getCourses() {
        return courses;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    
    public String signUp(String name, String birthDate) {
        Student newStudent = new Student(name, birthDate);
        students.add(newStudent);
        return newStudent.getID(); 
    }
    
    public String offerCourse(String title, String instructor, String startDate, int duration, int capacity) {
        Course offerCourse = new Course(title, instructor, startDate, duration, capacity);
        courses.add(offerCourse);
        return offerCourse.getId();
    }

    public String offerWorkshop(String title, String instructor, String startDate, int duration, int capacity) {
        WorkShop offerWorkSp = new WorkShop(title, instructor, startDate, duration, capacity);
        courses.add(offerWorkSp);
        return offerWorkSp.getId();
    }

    public Item findItem(String id) throws InvalidItemException {
        for (Item x : courses) {
            if (x.getId().equals(id)) {
                return x;
            }
        }
        throw new InvalidItemException(id); 
    }

    public ArrayList<Item> findItems(String title) {
        ArrayList<Item> find_Item = new ArrayList<>();

        for (Item x : courses) {
            if (x.getTitle().equals(title)) {
                find_Item.add(x);
            }
        }

        return find_Item;
    }

    public Student findStudent(String id) throws InvalidStudentException {
        for (Student x : students) {
            if (x.getID().equals(id)) {
                return x;
            }
        }
        throw new InvalidStudentException(id); 
    }

    public void registerCourse(String itemId, String studentId) 
            throws InvalidItemException, InvalidStudentException {
        Item tempI = findItem(itemId);
        Student tempS = findStudent(studentId);

        tempI.registerStudent(studentId);
        tempS.registerCourse(itemId);
    }

    public void cancelRegistration(String itemId, String studentId) 
            throws InvalidItemException, InvalidStudentException {
        Item tempI = findItem(itemId);
        Student tempS = findStudent(studentId);

        tempI.cancelRegistration(studentId);
        tempS.cancelRegistration(itemId);
    }

    public void startCourse(String courseId) throws InvalidItemException {
        Item tempI = findItem(courseId);
        tempI.start();
    }

    public void completeCourse(String courseId) throws InvalidItemException {
        Item tempI = findItem(courseId);
        tempI.complete();
    }

    public void addTopicToCourse(String id, String topic) throws InvalidItemException {
        Item tempI = findItem(id);

        if (tempI instanceof Course) {
            ((Course) tempI).addTopic(topic);
        }
    }

    public void assignProject(String id, String project) throws InvalidItemException {
        Item tempI = findItem(id);

        if (tempI instanceof WorkShop) {
            ((WorkShop) tempI).assignProject(project);
        }
    }

    public ArrayList<Student> getRegisteredStudentInfo(String courseId) throws InvalidItemException, InvalidStudentException {
        ArrayList<Student> find_ItemS = new ArrayList<>();
        Item tempI = findItem(courseId);

        for (String x : tempI.getRegisteredStudentIds()) {
            Student tempS = findStudent(x);
            find_ItemS.add(tempS);
        }

        return find_ItemS;
    }

    public ArrayList<Item> getRegisteredCourseInfo(String studentId) throws InvalidStudentException, InvalidItemException {
        ArrayList<Item> find_ItemI = new ArrayList<>();
        Student tempS = findStudent(studentId);

        if (tempS == null) {
            throw new InvalidStudentException(studentId);
        }

        for (String x : tempS.getRegisteredCourseIds()) {
            Item tempI = findItem(x);
            if (tempI != null) {
                find_ItemI.add(tempI);
            }
        }

        return find_ItemI;
    }

    public ArrayList<Item> getCompletedCourseInfo(String studentId) throws InvalidStudentException, InvalidItemException {
        ArrayList<Item> find_ItemI = new ArrayList<>();
        Student tempS = findStudent(studentId);

        if (tempS == null) {
            throw new InvalidStudentException(studentId);
        }

        for (String x : tempS.getCompletedCourseIds()) {
            Item tempI = findItem(x);
            if (tempI != null) {
                find_ItemI.add(tempI);
            }
        }

        return find_ItemI;
    }
}