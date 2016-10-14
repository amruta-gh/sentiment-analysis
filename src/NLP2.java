
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NLP2 {
	static StanfordCoreNLP pipeline;

	public static void init() {
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
		Properties props = new Properties();
		//props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref, sentiment");
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);
		//pipeline = new StanfordCoreNLP("MyPropFile.properties");
	}

	public static int findSentiment(String tweet) {

		int mainSentiment = 0;
		if (tweet != null && tweet.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(tweet);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				//Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
				Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);//TODO check this
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}

			}
		}
		return mainSentiment;
	}

	public static void test2() {
		String text = "I am feeling very upset";
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		//<...>
		Annotation annotation = pipeline.process(text);
		List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
		for (CoreMap sentence : sentences) {
			//String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
			String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
			System.out.println(sentiment + "\t" + sentence);
		}		
	}

	public static void main(String args[]) {
		ArrayList<String> tweets = new ArrayList<String>();

		String s = "Wow, wow, wow. I absolutely love this camera." ;
		tweets.add(s);
		tweets.add("I am very happy, thank you");
		tweets.add("I am very sad, thank you");
		tweets.add("I am laughing");
		tweets.add("I am little upset");
		tweets.add("I am upset");
		tweets.add("I am not upset");
		tweets.add("I am very very very sad");



		//String tweet = ;
		NLP n = new NLP();
		n.init();

		for (String tweet : tweets) {
			int sentimentValue = n.findSentiment(tweet);
			System.out.println(" tweet = " +  tweet +  "  " +  sentimentValue);
		}
	}



}
