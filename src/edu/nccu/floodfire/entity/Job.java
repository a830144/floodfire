package edu.nccu.floodfire.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the job database table.
 * 
 */
@Entity
@Table(name="job")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Job_Seq")
	private String job_Seq;

	@Column(name="create_id")
	private String createId;

	@Column(name="job_status")
	private String jobStatus;

	@Column(name="query_function")
	private String queryFunction;

	@Column(name="query_type")
	private String queryType;

	@Column(name="start_time")
	private String startTime;

	@Column(name="stop_time")
	private String stopTime;

	@Column(name="store_data_sum")
	private String storeDataSum;
	
	@Column(name="keyword")
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	//bi-directional many-to-one association to Event
    @ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="event_name")
	private Event event;

    public Job() {
    }

	public String getJob_Seq() {
		return this.job_Seq;
	}

	public void setJob_Seq(String job_Seq) {
		this.job_Seq = job_Seq;
	}

	public String getCreateId() {
		return this.createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getJobStatus() {
		return this.jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getQueryFunction() {
		return this.queryFunction;
	}

	public void setQueryFunction(String queryFunction) {
		this.queryFunction = queryFunction;
	}

	public String getQueryType() {
		return this.queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getStoreDataSum() {
		return this.storeDataSum;
	}

	public void setStoreDataSum(String storeDataSum) {
		this.storeDataSum = storeDataSum;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
}