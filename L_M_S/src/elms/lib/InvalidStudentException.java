package elms.lib;

public class InvalidStudentException extends Exception {
    public InvalidStudentException(String studentId) {
        super(studentId + " is not a valid student's id.");
    }
}