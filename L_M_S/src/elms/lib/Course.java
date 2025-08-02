package elms.lib;

import java.util.ArrayList;

public class Course extends Item {

    private ArrayList<String> topicsCovered;

    public Course(String title, String instructor, String startDate, int duration, int capacity) {
	super(title, instructor, startDate, duration, capacity);
	this.topicsCovered = new ArrayList<>();
	this.addPrefixCodeToId("C-");
    }

    public Course(String title, String instructor, String startDate, int duration, int capacity,
	    ArrayList<String> topics) {
	this(title, instructor, startDate, duration, capacity);
	this.topicsCovered.addAll(topics);
    }

    public void addTopic(String topic) {
	boolean found = false;
	for (String x : topicsCovered) {
	    if (x.equals(topic)) {
		found = true;
		break;
	    }
	}
	if (!found) {
	    topicsCovered.add(topic);
	}
	topicsCovered.add(topic);
    }

    public ArrayList<String> getTopicsCovered() {
	return topicsCovered;
    }

}
