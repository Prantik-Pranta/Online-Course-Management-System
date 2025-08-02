package elms.lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public abstract class Item {

    private String title, instructor, id, status;
    private LocalDate startDate;
    private int duration, capacity;
    private ArrayList<String> registeredStudentIds;

    private Item(String title, String instructor, int duration, int capacity) {
	this.title = title;
	this.instructor = instructor;
	this.duration = duration;
	this.capacity = capacity;
	Random rn = new Random();
	this.id = String.format("%04d", rn.nextInt(10000));
	this.status = "scheduled";
	this.registeredStudentIds = new ArrayList<>();
    }

    public Item(String title, String instructor, LocalDate startDate, int duration, int capacity) 
	{
	this(title, instructor, duration, capacity); 
	
	this.startDate = startDate;
    }

    public Item(String title, String instructor, String startDate, int duration, int capacity) {
	this(title, instructor, duration, capacity);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	LocalDate date = LocalDate.parse(startDate, formatter);
		this.startDate = date;

    }



    protected void addPrefixCodeToId(String prefix) {
	id = prefix + id; 
    }

    public String getTitle() {
	return title;
    }

    public String getInstructor() {
	return instructor;
    }

    public String getId() {
	return id;
    }

    public String getStatus() {
	return status;
    }

    public LocalDate getStartDate() {
	return startDate;
    }

    public int getDuration() {
	return duration;
    }

    public int getCapacity() {
	return capacity;
    }

    public ArrayList<String> getRegisteredStudentIds() {
	return registeredStudentIds;
    }




    public void registerStudent(String studentId) {
	if (capacity > registeredStudentIds.size()) {
	    boolean found = false;
	    for (String x : registeredStudentIds) {
		if (x.equals(studentId)) {
		    found = true;
		    break;
		}
	    }
		
	    if (!found) {
		registeredStudentIds.add(studentId);
	    }
	}
    }

    public void cancelRegistration(String studentId) {
	for (String x : registeredStudentIds) {
	    if (x.equals(studentId)) {
		registeredStudentIds.remove(studentId);
		break;
	    }
	}
    }

    public void start() {
	status = "in progress";
    }

    public void complete() {
	status = "completed";
    }
}
 