package edu.nccu.floodfire.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tweets database table.
 * 
 */
@Entity
@Table(name="tweets")
@IdClass(TweetsId.class)
public class Tweets implements Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tweets other = (Tweets) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Id
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	private String contributors;

	private String coordinates;

	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdAt;

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	@Column(name="current_user_retweet")
	private String currentUserRetweet;

	@Column(name="favorite_count")
	private String favoriteCount;

	private String favorited;

	@Column(name="filter_level")
	private String filterLevel;

	@Column(name="in_reply_to_screen_name")
	private String inReplyToScreenName;

	@Column(name="in_reply_to_status_id")
	private String inReplyToStatusId;

	@Column(name="in_reply_to_user_id")
	private String inReplyToUserId;

	private String lang;

	@Column(name="possibly_sensitive")
	private String possiblySensitive;

	@Column(name="retweet_count")
	private String retweetCount;

	private String retweeted;

	private String scopes;

	private String source;

	private String text;

	private String truncated;

	@Column(name="withheld_copyright")
	private String withheldCopyright;

	@Column(name="withheld_in_countries")
	private String withheldInCountries;

	@Column(name="withheld_scope")
	private String withheldScope;
	
	private String jobSeq;

	public String getJobSeq() {
		return jobSeq;
	}

	public void setJobSeq(String jobSeq) {
		this.jobSeq = jobSeq;
	}

	//bi-directional many-to-one association to Users
    @ManyToOne(cascade=CascadeType.ALL)
	private Users user;

    public Tweets() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContributors() {
		return this.contributors;
	}

	public void setContributors(String contributors) {
		this.contributors = contributors;
	}

	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	

	

	public String getCurrentUserRetweet() {
		return this.currentUserRetweet;
	}

	public void setCurrentUserRetweet(String currentUserRetweet) {
		this.currentUserRetweet = currentUserRetweet;
	}

	public String getFavoriteCount() {
		return this.favoriteCount;
	}

	public void setFavoriteCount(String favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public String getFavorited() {
		return this.favorited;
	}

	public void setFavorited(String favorited) {
		this.favorited = favorited;
	}

	public String getFilterLevel() {
		return this.filterLevel;
	}

	public void setFilterLevel(String filterLevel) {
		this.filterLevel = filterLevel;
	}

	public String getInReplyToScreenName() {
		return this.inReplyToScreenName;
	}

	public void setInReplyToScreenName(String inReplyToScreenName) {
		this.inReplyToScreenName = inReplyToScreenName;
	}

	public String getInReplyToStatusId() {
		return this.inReplyToStatusId;
	}

	public void setInReplyToStatusId(String inReplyToStatusId) {
		this.inReplyToStatusId = inReplyToStatusId;
	}

	public String getInReplyToUserId() {
		return this.inReplyToUserId;
	}

	public void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getPossiblySensitive() {
		return this.possiblySensitive;
	}

	public void setPossiblySensitive(String possiblySensitive) {
		this.possiblySensitive = possiblySensitive;
	}

	public String getRetweetCount() {
		return this.retweetCount;
	}

	public void setRetweetCount(String retweetCount) {
		this.retweetCount = retweetCount;
	}

	public String getRetweeted() {
		return this.retweeted;
	}

	public void setRetweeted(String retweeted) {
		this.retweeted = retweeted;
	}

	public String getScopes() {
		return this.scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
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

	public String getTruncated() {
		return this.truncated;
	}

	public void setTruncated(String truncated) {
		this.truncated = truncated;
	}

	public String getWithheldCopyright() {
		return this.withheldCopyright;
	}

	public void setWithheldCopyright(String withheldCopyright) {
		this.withheldCopyright = withheldCopyright;
	}

	public String getWithheldInCountries() {
		return this.withheldInCountries;
	}

	public void setWithheldInCountries(String withheldInCountries) {
		this.withheldInCountries = withheldInCountries;
	}

	public String getWithheldScope() {
		return this.withheldScope;
	}

	public void setWithheldScope(String withheldScope) {
		this.withheldScope = withheldScope;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
}