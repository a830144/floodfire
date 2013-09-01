package edu.nccu.floodfire.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the archives database table.
 * 
 */
@Entity
@Table(name="archives")
public class Archive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private BigInteger count;

	@Column(name="create_time")
	private int createTime;

	private String description;

	private String keyword;

	@Column(name="screen_name")
	private String screenName;

	private String tags;

	@Column(name="user_id")
	private String userId;

    public Archive() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getCount() {
		return this.count;
	}

	public void setCount(BigInteger count) {
		this.count = count;
	}

	public int getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}