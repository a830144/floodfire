package edu.nccu.floodfire.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rawstream database table.
 * 
 */
@Entity
@Table(name="rawstream")
public class Rawstream implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="created_at")
	private String createdAt;

	private String flag;

	@Column(name="from_user")
	private String fromUser;

	@Column(name="from_user_id")
	private String fromUserId;

	@Column(name="geo_coordinates_0")
	private double geoCoordinates0;

	@Column(name="geo_coordinates_1")
	private double geoCoordinates1;

	@Column(name="geo_type")
	private String geoType;

	@Column(name="iso_language_code")
	private String isoLanguageCode;

	@Column(name="profile_image_url")
	private String profileImageUrl;

	private String source;

	private String text;

	private int time;

	@Column(name="to_user_id")
	private String toUserId;

    public Rawstream() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFromUser() {
		return this.fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public double getGeoCoordinates0() {
		return this.geoCoordinates0;
	}

	public void setGeoCoordinates0(double geoCoordinates0) {
		this.geoCoordinates0 = geoCoordinates0;
	}

	public double getGeoCoordinates1() {
		return this.geoCoordinates1;
	}

	public void setGeoCoordinates1(double geoCoordinates1) {
		this.geoCoordinates1 = geoCoordinates1;
	}

	public String getGeoType() {
		return this.geoType;
	}

	public void setGeoType(String geoType) {
		this.geoType = geoType;
	}

	public String getIsoLanguageCode() {
		return this.isoLanguageCode;
	}

	public void setIsoLanguageCode(String isoLanguageCode) {
		this.isoLanguageCode = isoLanguageCode;
	}

	public String getProfileImageUrl() {
		return this.profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

}