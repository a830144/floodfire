package edu.nccu.floodfire.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="contributors_enabled")
	private String contributorsEnabled;

	@Column(name="created_at")
	private String createdAt;

	@Column(name="default_profile")
	private String defaultProfile;

	@Column(name="default_profile_image")
	private String defaultProfileImage;

	private String description;

	@Column(name="favourites_count")
	private String favouritesCount;

	@Column(name="follow_request_sent")
	private String followRequestSent;

	@Column(name="followers_count")
	private String followersCount;

	private String following;

	@Column(name="friends_count")
	private String friendsCount;

	@Column(name="geo_enabled")
	private String geoEnabled;

	private String hashtags;

	@Column(name="is_translator")
	private String isTranslator;

	private String lang;

	@Column(name="listed_count")
	private String listedCount;

	private String location;

	private String media;

	private String name;

	private String notifications;

	@Column(name="profile_background")
	private String profileBackground;

	@Column(name="profile_background_color")
	private String profileBackgroundColor;

	@Column(name="profile_background_image_url_https")
	private String profileBackgroundImageUrlHttps;

	@Column(name="profile_background_tile")
	private String profileBackgroundTile;

	@Column(name="profile_banner_url")
	private String profileBannerUrl;

	@Column(name="profile_image_url")
	private String profileImageUrl;

	@Column(name="profile_image_url_https")
	private String profileImageUrlHttps;

	@Column(name="profile_link_color")
	private String profileLinkColor;

	@Column(name="profile_sidebar_border_color")
	private String profileSidebarBorderColor;

	@Column(name="profile_sidebar_fill_color")
	private String profileSidebarFillColor;

	@Column(name="profile_text_color")
	private String profileTextColor;

	@Column(name="profile_use_background_image")
	private String profileUseBackgroundImage;

	@Column(name="protected")
	private String protected_;

	@Column(name="screen_name")
	private String screenName;

	@Column(name="show_all_inline_media")
	private String showAllInlineMedia;

	private String status;

	@Column(name="statuses_count")
	private String statusesCount;

	@Column(name="time_zone")
	private String timeZone;

	private String url;

	private String urls;

	@Column(name="user_mentions")
	private String userMentions;

	@Column(name="utc_offset")
	private String utcOffset;

	private String verified;

	@Column(name="withheld_in_countries")
	private String withheldInCountries;

	@Column(name="withheld_scope")
	private String withheldScope;

	//bi-directional many-to-one association to Tweets
	@OneToMany(mappedBy="user")
	private Set<Tweets> tweets;

    public Users() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContributorsEnabled() {
		return this.contributorsEnabled;
	}

	public void setContributorsEnabled(String contributorsEnabled) {
		this.contributorsEnabled = contributorsEnabled;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDefaultProfile() {
		return this.defaultProfile;
	}

	public void setDefaultProfile(String defaultProfile) {
		this.defaultProfile = defaultProfile;
	}

	public String getDefaultProfileImage() {
		return this.defaultProfileImage;
	}

	public void setDefaultProfileImage(String defaultProfileImage) {
		this.defaultProfileImage = defaultProfileImage;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFavouritesCount() {
		return this.favouritesCount;
	}

	public void setFavouritesCount(String favouritesCount) {
		this.favouritesCount = favouritesCount;
	}

	public String getFollowRequestSent() {
		return this.followRequestSent;
	}

	public void setFollowRequestSent(String followRequestSent) {
		this.followRequestSent = followRequestSent;
	}

	public String getFollowersCount() {
		return this.followersCount;
	}

	public void setFollowersCount(String followersCount) {
		this.followersCount = followersCount;
	}

	public String getFollowing() {
		return this.following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

	public String getFriendsCount() {
		return this.friendsCount;
	}

	public void setFriendsCount(String friendsCount) {
		this.friendsCount = friendsCount;
	}

	public String getGeoEnabled() {
		return this.geoEnabled;
	}

	public void setGeoEnabled(String geoEnabled) {
		this.geoEnabled = geoEnabled;
	}

	public String getHashtags() {
		return this.hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}

	public String getIsTranslator() {
		return this.isTranslator;
	}

	public void setIsTranslator(String isTranslator) {
		this.isTranslator = isTranslator;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getListedCount() {
		return this.listedCount;
	}

	public void setListedCount(String listedCount) {
		this.listedCount = listedCount;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMedia() {
		return this.media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotifications() {
		return this.notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	public String getProfileBackground() {
		return this.profileBackground;
	}

	public void setProfileBackground(String profileBackground) {
		this.profileBackground = profileBackground;
	}

	public String getProfileBackgroundColor() {
		return this.profileBackgroundColor;
	}

	public void setProfileBackgroundColor(String profileBackgroundColor) {
		this.profileBackgroundColor = profileBackgroundColor;
	}

	public String getProfileBackgroundImageUrlHttps() {
		return this.profileBackgroundImageUrlHttps;
	}

	public void setProfileBackgroundImageUrlHttps(String profileBackgroundImageUrlHttps) {
		this.profileBackgroundImageUrlHttps = profileBackgroundImageUrlHttps;
	}

	public String getProfileBackgroundTile() {
		return this.profileBackgroundTile;
	}

	public void setProfileBackgroundTile(String profileBackgroundTile) {
		this.profileBackgroundTile = profileBackgroundTile;
	}

	public String getProfileBannerUrl() {
		return this.profileBannerUrl;
	}

	public void setProfileBannerUrl(String profileBannerUrl) {
		this.profileBannerUrl = profileBannerUrl;
	}

	public String getProfileImageUrl() {
		return this.profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getProfileImageUrlHttps() {
		return this.profileImageUrlHttps;
	}

	public void setProfileImageUrlHttps(String profileImageUrlHttps) {
		this.profileImageUrlHttps = profileImageUrlHttps;
	}

	public String getProfileLinkColor() {
		return this.profileLinkColor;
	}

	public void setProfileLinkColor(String profileLinkColor) {
		this.profileLinkColor = profileLinkColor;
	}

	public String getProfileSidebarBorderColor() {
		return this.profileSidebarBorderColor;
	}

	public void setProfileSidebarBorderColor(String profileSidebarBorderColor) {
		this.profileSidebarBorderColor = profileSidebarBorderColor;
	}

	public String getProfileSidebarFillColor() {
		return this.profileSidebarFillColor;
	}

	public void setProfileSidebarFillColor(String profileSidebarFillColor) {
		this.profileSidebarFillColor = profileSidebarFillColor;
	}

	public String getProfileTextColor() {
		return this.profileTextColor;
	}

	public void setProfileTextColor(String profileTextColor) {
		this.profileTextColor = profileTextColor;
	}

	public String getProfileUseBackgroundImage() {
		return this.profileUseBackgroundImage;
	}

	public void setProfileUseBackgroundImage(String profileUseBackgroundImage) {
		this.profileUseBackgroundImage = profileUseBackgroundImage;
	}

	public String getProtected_() {
		return this.protected_;
	}

	public void setProtected_(String protected_) {
		this.protected_ = protected_;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getShowAllInlineMedia() {
		return this.showAllInlineMedia;
	}

	public void setShowAllInlineMedia(String showAllInlineMedia) {
		this.showAllInlineMedia = showAllInlineMedia;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusesCount() {
		return this.statusesCount;
	}

	public void setStatusesCount(String statusesCount) {
		this.statusesCount = statusesCount;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrls() {
		return this.urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getUserMentions() {
		return this.userMentions;
	}

	public void setUserMentions(String userMentions) {
		this.userMentions = userMentions;
	}

	public String getUtcOffset() {
		return this.utcOffset;
	}

	public void setUtcOffset(String utcOffset) {
		this.utcOffset = utcOffset;
	}

	public String getVerified() {
		return this.verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
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

	public Set<Tweets> getTweets() {
		return this.tweets;
	}

	public void setTweets(Set<Tweets> tweets) {
		this.tweets = tweets;
	}
	
}