package com.capitalone.twitterbot;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;


@Component
public class SentimentAnalyzer {
	
	// * scale of 0 = very negative, 1 = negative, 2 = neutral, 3 = positive,
	//		   * and 4 = very positive.
	public enum SENTIMENTLEVEL{
		VERY_NEGATIVE(0),NEGATIVE(1),NEUTRAL(2), POSITIVE(3),VERY_POSITIVE(4);
		
		int value;
		
		private SENTIMENTLEVEL(int value){
			this.value = value;
		}
	}
	
	static StanfordCoreNLP pipeline;
	
	@PostConstruct
	public void init() {
		pipeline = new StanfordCoreNLP("sentiment.properties");
	}

	public static String findSentiment(String tweet) {
		int mainSentiment = 0;
		String sentimentDerived ="";
		if (tweet != null && tweet.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(tweet);
			for (CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {
				Tree tree = sentence
						.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}

			}
		}
		for( SENTIMENTLEVEL sentiment : SENTIMENTLEVEL.values()){
			if(sentiment.value == mainSentiment){
				sentimentDerived=sentiment.toString();
				break;
			}
		}
		
		return sentimentDerived;
	}
}