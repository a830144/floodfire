package edu.nccu.floodfire.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@Table(name="event")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String eventName;

	private String eventType;

	//bi-directional many-to-one association to Job
	@OneToMany(mappedBy="event")
	private Set<Job> jobs;

    public Event() {
    }

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return this.eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Set<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	
}