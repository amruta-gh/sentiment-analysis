import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import drawGraphs.BarChartValue;
import drawGraphs.PieChartValue;

public class TwitterSentiments {

	public static void main(String[] args) {
		int numOfTweets = 100;
		String keyword = "Roger Federer"; //"ICCT20WC";
		int numOfTopWords = 10;
		Integer countNegative = 0;		
		Integer countPositive = 0;
		Integer countNeutral = 0;
		String positiveSentiments = "positive Sentiments";
		String negativeSentiments = "negative Sentiments";
		String neutralSentiments = "neutral Sentiments";
						
		
		// get the tweets from twitter api
		TweetManager tm = new TweetManager();
		ArrayList<String> tweets = tm.getTweets(keyword, numOfTweets);
		
		// initializes the stanford library
		NLP n = new NLP();
	n.init();
		
		//initialise the graphs
		CallGraphsAPI graph = new CallGraphsAPI();

		for (int i=0; i<10; i++) {
			System.out.println();
		}

		
		for(String tweet : tweets) {
			int sentimentValue =NLP.findSentiment(tweet);
			
			if (sentimentValue == 1) {
				countNegative++;
			} else if (sentimentValue == 2) {
				countNeutral++;
			} else if (sentimentValue == 3) {
				countPositive++;
			}
			//System.out.println(tweet + " <><><><><><><><> " + sentimentValue);  			
		}
				
		//draw the setiment chart
		List<PieChartValue> pieValues = new ArrayList<PieChartValue>();	
		pieValues.add(new PieChartValue(negativeSentiments, countNegative));
		pieValues.add(new PieChartValue(positiveSentiments, countPositive));
		pieValues.add(new PieChartValue(neutralSentiments, countNeutral));
		graph.callDrawPieChart(pieValues);

		
		// occurence of words, bar chart
		Map <String, Integer> wordsInTweets = tm.occurenceOfWords(tweets);
		List<BarChartValue> barValues = new ArrayList<BarChartValue>();
		int count = 0;
		for (String s : wordsInTweets.keySet()) {
			if (numOfTopWords < count) {
				break;
			}
			count++;
			barValues.add(new BarChartValue (wordsInTweets.get(s), s));
			System.out.println(s +  " :  "+ wordsInTweets.get(s));
		}
		graph.callDrawBarChart(barValues);
	}
}
