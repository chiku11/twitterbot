package com.capitalone.twitterbot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.FilterStreamParameters;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamListener;
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

	@Autowired
	private StreamProcessing streamProcessing;
	
	@PostConstruct
	public void intialize(){
		
		//Streams by trackids for eg: ladygaga
//		twitter.streamingOperations().filter("ladygaga", new ArrayList<StreamListener>(){
//			private static final long serialVersionUID = 1L;
//
//			{
//				add(streamProcessing);
//			}
//		});
		
		//Streams User Profile Activity.
		twitter.streamingOperations().user( new ArrayList<StreamListener>(){
			private static final long serialVersionUID = 1L;

			{
				add(streamProcessing);
			}
		});
	}
	
	@GetMapping("/friends")
	public String getFriends(){
		return twitter.friendOperations().getFriends().stream().map(friends -> friends.getName()).collect(Collectors.joining(","));
	}
	
	@PostMapping("/tweet/{tweet}")
	public void tweet(@PathVariable String tweet){
		twitter.timelineOperations().updateStatus(tweet);		
	}
}