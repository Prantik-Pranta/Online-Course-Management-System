package elms.lib;

import java.util.ArrayList;

public class WorkShop extends Item {

    private ArrayList<String> projects = new ArrayList<>();

    public WorkShop(String title, String instructor, String startDate, int duration, int capacity) {
	super(title, instructor, startDate, duration, capacity);
	this.addPrefixCodeToId("W-");
    }

    public void assignProject(String project) {
	boolean found = false;
	for (String x : projects) {
	    if (x.equals(project)) {
		found = true;
		break;
	    }
	}
	if (!found) {
	    projects.add(project);
	}
    }

    public ArrayList<String> getProjects() {
	return projects;
    }

    public String getProject() {
        if (projects.isEmpty()) {
            return "No projects assigned";
        }
        return String.join(", ", projects);
    }
}
