package edu.nccu.floodfire.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the token database table.
 * 
 */
@Entity
@Table(name="token")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 
	@Column(name="tokenId")
    private String tokenId;
    
	@Column(name="tokenName")
	private String tokenName;

	@Column(name="consumerKey")
	private String consumerKey;
	
	@Column(name="consumerSecret")
	private String consumerSecret;
	
	@Column(name="accessToken")
	private String accessToken;
	
	@Column(name="accessTokenSecret")
	private String accessTokenSecret;
	
	@Column(name="locked")
	private String locked;
	
	@Column(name="frequency")
	private String frequency;
	
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	@Column(name="jobSeq")
	private String jobSeq;

	
	
	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getJobSeq() {
		return jobSeq;
	}

	public void setJobSeq(String jobSeq) {
		this.jobSeq = jobSeq;
	}

	
}
