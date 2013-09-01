package edu.nccu.floodfire.model.twitter;

import java.util.Date;

public class TweetData {
	
	private String tweetId;
	private String contributors;
	private String coordinates;
	private Date created_at;
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	//need to modified
	private String current_user_retweet;
	private String entities;
	private int favorite_count;
	
	private boolean favorited;
	private String filter_level;
	private String in_reply_to_screen_name;
	private double in_reply_to_status_id;
	public int getFavorite_count() {
		return favorite_count;
	}

	private double in_reply_to_user_id;
	private String lang;
	private String place;
	private boolean possibly_sensitive;
	private String scopes;
	private int retweet_count;
	private boolean retweeted;
	private String source;
	private String text;
	private boolean truncated;
	private String user;
	private boolean withheld_copyright;
	private String[] withheld_in_countries;
	private String withheld_scope;
	
	private String event;
	private String CalendarDate;
	private String totalTweetJson;
	
	public String getTotalTweetJson() {
		return totalTweetJson;
	}
	public void setTotalTweetJson(String totalTweetJson) {
		this.totalTweetJson = totalTweetJson;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getCalendarDate() {
		return CalendarDate;
	}
	public void setCalendarDate(String calendarDate) {
		CalendarDate = calendarDate;
	}
	public String getTweetId() {
		return tweetId;
	}
	public void setTweetId(String tweetId) {
		this.tweetId = tweetId;
	}
	public String getContributors() {
		return contributors;
	}
	public void setContributors(String contributors) {
		this.contributors = contributors;
	}
	public String getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	
	public Date getCreated_at() {
		return created_at;
	}
	public String getCurrent_user_retweet() {
		return current_user_retweet;
	}
	public void setCurrent_user_retweet(String current_user_retweet) {
		this.current_user_retweet = current_user_retweet;
	}
	public String getEntities() {
		return entities;
	}
	public void setEntities(String entities) {
		this.entities = entities;
	}

	public boolean isFavorited() {
		return favorited;
	}
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	public String getFilter_level() {
		return filter_level;
	}
	public void setFilter_level(String filter_level) {
		this.filter_level = filter_level;
	}
	public String getIn_reply_to_screen_name() {
		return in_reply_to_screen_name;
	}
	public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
		this.in_reply_to_screen_name = in_reply_to_screen_name;
	}
	public double getIn_reply_to_status_id() {
		return in_reply_to_status_id;
	}
	public void setIn_reply_to_status_id(double in_reply_to_status_id) {
		this.in_reply_to_status_id = in_reply_to_status_id;
	}
	public double getIn_reply_to_user_id() {
		return in_reply_to_user_id;
	}
	public void setIn_reply_to_user_id(double in_reply_to_user_id) {
		this.in_reply_to_user_id = in_reply_to_user_id;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public boolean isPossibly_sensitive() {
		return possibly_sensitive;
	}
	public void setPossibly_sensitive(boolean possibly_sensitive) {
		this.possibly_sensitive = possibly_sensitive;
	}
	public String getScopes() {
		return scopes;
	}
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}
	public int getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(int retweet_count) {
		this.retweet_count = retweet_count;
	}
	public boolean isRetweeted() {
		return retweeted;
	}
	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isTruncated() {
		return truncated;
	}
	public void setTruncated(boolean truncated) {
		this.truncated = truncated;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public boolean isWithheld_copyright() {
		return withheld_copyright;
	}
	public void setWithheld_copyright(boolean withheld_copyright) {
		this.withheld_copyright = withheld_copyright;
	}
	public String[] getWithheld_in_countries() {
		return withheld_in_countries;
	}
	public void setWithheld_in_countries(String[] withheld_in_countries) {
		this.withheld_in_countries = withheld_in_countries;
	}
	public String getWithheld_scope() {
		return withheld_scope;
	}
	public void setWithheld_scope(String withheld_scope) {
		this.withheld_scope = withheld_scope;
	}
	
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}

}
