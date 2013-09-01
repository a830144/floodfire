CREATE DATABASE `manageent`;
/** manageent event*/
CREATE TABLE `event` (
  `eventName` varchar(30) NOT NULL,
  `eventType` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`eventName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/** manageent job*/
CREATE TABLE `job` (
  `Job_Seq` varchar(80) NOT NULL,
  `start_time` varchar(30) DEFAULT NULL,
  `stop_time` varchar(30) DEFAULT NULL,
  `create_id` varchar(15) DEFAULT NULL,
  `query_type` varchar(1) DEFAULT NULL,
  `query_function` varchar(30) DEFAULT NULL,
  `store_data_sum` varchar(10) DEFAULT NULL,
  `job_status` varchar(1) DEFAULT NULL,
  `event_name` varchar(20) DEFAULT NULL,
  `keywprd` varchar(45) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Job_Seq`),
  KEY `job_fk_1_idx` (`event_name`),
  CONSTRAINT `job_fk_1` FOREIGN KEY (`event_name`) REFERENCES `event` (`eventName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/** manageent token*/
CREATE TABLE `token` (
  `tokenId` varchar(5) NOT NULL,
  `tokenName` varchar(45) DEFAULT NULL,
  `consumerKey` varchar(100) DEFAULT NULL,
  `consumerSecret` varchar(100) DEFAULT NULL,
  `accessToken` varchar(100) DEFAULT NULL,
  `accessTokenSecret` varchar(100) DEFAULT NULL,
  `locked` varchar(1) DEFAULT NULL,
  `jobSeq` varchar(80) DEFAULT NULL,
  `frequency` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`tokenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE DATABASE `tweetdata`;
/** tweetData tweets*/
CREATE TABLE `tweets` (
  `id` varchar(64) NOT NULL,
  `timestamp` varchar(45) CHARACTER SET latin1 NOT NULL,
  `contributors` varchar(45) DEFAULT NULL,
  `coordinates` varchar(45) DEFAULT NULL,
  `created_at` varchar(45) DEFAULT NULL,
  `current_user_retweet` varchar(45) DEFAULT NULL,
  `favorite_count` varchar(45) DEFAULT NULL,
  `favorited` varchar(45) DEFAULT NULL,
  `filter_level` varchar(45) DEFAULT NULL,
  `in_reply_to_screen_name` varchar(45) DEFAULT NULL,
  `in_reply_to_status_id` varchar(45) DEFAULT NULL,
  `in_reply_to_user_id` varchar(45) DEFAULT NULL,
  `lang` varchar(45) DEFAULT NULL,
  `possibly_sensitive` varchar(45) DEFAULT NULL,
  `scopes` varchar(45) DEFAULT NULL,
  `retweet_count` varchar(45) DEFAULT NULL,
  `retweeted` varchar(45) DEFAULT NULL,
  `source` varchar(45) DEFAULT NULL,
  `text` varchar(140) DEFAULT NULL,
  `truncated` varchar(45) DEFAULT NULL,
  `withheld_copyright` varchar(45) DEFAULT NULL,
  `withheld_in_countries` varchar(45) DEFAULT NULL,
  `withheld_scope` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) NOT NULL DEFAULT '',
  `jobSeq` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`,`timestamp`),
  KEY `tweets_fk_1_idx` (`user_id`),
  CONSTRAINT `tweets_fk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/** tweetData users*/
CREATE TABLE `users` (
  `id` varchar(64) NOT NULL,
  `contributors_enabled` varchar(45) DEFAULT NULL,
  `created_at` varchar(45) DEFAULT NULL,
  `default_profile` varchar(45) DEFAULT NULL,
  `default_profile_image` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `favourites_count` varchar(45) DEFAULT NULL,
  `follow_request_sent` varchar(45) DEFAULT NULL,
  `following` varchar(45) DEFAULT NULL,
  `followers_count` varchar(45) DEFAULT NULL,
  `friends_count` varchar(45) DEFAULT NULL,
  `geo_enabled` varchar(45) DEFAULT NULL,
  `is_translator` varchar(45) DEFAULT NULL,
  `lang` varchar(45) DEFAULT NULL,
  `listed_count` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `notifications` varchar(45) DEFAULT NULL,
  `profile_background_color` varchar(45) DEFAULT NULL,
  `profile_background` varchar(45) DEFAULT NULL,
  `profile_background_image_url_https` varchar(45) DEFAULT NULL,
  `profile_background_tile` varchar(45) DEFAULT NULL,
  `profile_banner_url` varchar(45) DEFAULT NULL,
  `profile_image_url` varchar(45) DEFAULT NULL,
  `profile_image_url_https` varchar(45) DEFAULT NULL,
  `profile_link_color` varchar(45) DEFAULT NULL,
  `profile_sidebar_border_color` varchar(45) DEFAULT NULL,
  `profile_sidebar_fill_color` varchar(45) DEFAULT NULL,
  `profile_text_color` varchar(45) DEFAULT NULL,
  `profile_use_background_image` varchar(45) DEFAULT NULL,
  `protected` varchar(45) DEFAULT NULL,
  `screen_name` varchar(45) DEFAULT NULL,
  `show_all_inline_media` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `statuses_count` varchar(45) DEFAULT NULL,
  `time_zone` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `utc_offset` varchar(45) DEFAULT NULL,
  `verified` varchar(45) DEFAULT NULL,
  `withheld_in_countries` varchar(45) DEFAULT NULL,
  `withheld_scope` varchar(45) DEFAULT NULL,
  `hashtags` varchar(45) DEFAULT NULL,
  `media` varchar(45) DEFAULT NULL,
  `urls` varchar(45) DEFAULT NULL,
  `user_mentions` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
