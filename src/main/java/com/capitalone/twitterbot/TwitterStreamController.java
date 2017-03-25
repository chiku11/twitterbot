package com.capitalone.twitterbot;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class TwitterStreamController {
	
	@Autowired
	private Twitter twitter;

	@GetMapping("/friends")
	public String getFriends(){
		return twitter.friendOperations().getFriends().stream().map(friends -> friends.getName()).collect(Collectors.joining(","));
	}
	
	@PostMapping("/tweet/{tweet}")
	public void tweet(@PathVariable String tweet){
		twitter.timelineOperations().updateStatus(tweet);
		
	}
}