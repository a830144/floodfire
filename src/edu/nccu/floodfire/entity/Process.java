package edu.nccu.floodfire.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the processes database table.
 * 
 */
@Entity
@Table(name="processes")
public class Process implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pid;

	@Column(name="last_ping")
	private int lastPing;

	private String process;

    public Process() {
    }

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getLastPing() {
		return this.lastPing;
	}

	public void setLastPing(int lastPing) {
		this.lastPing = lastPing;
	}

	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}