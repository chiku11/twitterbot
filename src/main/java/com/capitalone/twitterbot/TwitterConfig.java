package com.capitalone.twitterbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfig {
	
	@Value("${spring.social.twitter.app-id}")
	private String consumerKey;
	
	@Value("${spring.social.twitter.app-secret}")
	private String consumerSecret;
	
	@Value("${spring.social.twitter.access.token}")
	private String accessToken;
	
	@Value("${spring.social.twitter.access.secret}")
	private String accessTokenSecret;

	@Bean
	public Twitter twitter(){

		Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret,accessToken, accessTokenSecret);
		return twitter;
	}
}
