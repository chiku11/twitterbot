package com.capitalone.twitterbot;

import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Service;

@Service
public class StreamProcessing implements StreamListener{

	@Override
	public void onTweet(Tweet tweet) {
		System.out.println(tweet.getFromUser()+ ":"+tweet.getText());
		
	}

	@Override
	public void onDelete(StreamDeleteEvent deleteEvent) {
		// TODO Auto-generated method stub
		System.out.println("User deleted:"+deleteEvent.getTweetId());
	}

	@Override
	public void onLimit(int numberOfLimitedTweets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onWarning(StreamWarningEvent warningEvent) {
		// TODO Auto-generated method stub
		
	}

	
//	public void streamtwitter(){
//		Runnable run = ()->{
//			
//			while(true){
//				List<Tweet> tweets = twitter.timelineOperations().getUserTimeline("@CapitalOne");
//				tweets.forEach(tweet -> {
//					System.out.println(tweet.getText());
//				});
//				try {
//					Thread.currentThread().sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		};		
//		Thread twitterThread = new Thread(run);
//		twitterThread.start();
//	}
}
