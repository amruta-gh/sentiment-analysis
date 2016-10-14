import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TweetManager {

	public static ArrayList<String> getTweets(String keyword, int numOfTweets) {

		Twitter twitter = new TwitterFactory().getInstance();
		ArrayList<String> tweetList = new ArrayList<String>();

		/* filter the twitter search results to make sure no retweet, or tweet with links, or tweet with images are returned. 
         The reason for this is to make sure that we get tweets which have text.
		 */
		Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");
		query.setCount(numOfTweets); // number of tweets 
		query.setLocale("en");
		query.setLang("en");;
		try {
			QueryResult result = twitter.search(query);
			List<Status> tweets = result.getTweets();
			for (Status tweet : tweets) {
				tweetList.add(tweet.getText());
			}            
		} catch (TwitterException e) {
			// ignore
			e.printStackTrace();
		}

		/* try {
            Query query = new Query(keyword);
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    tweetList.add(tweet.getText());
                }
                count++;
                //if (count > maxCount) {
                //	break;
                //}
            } while ((query = result.nextQuery()) != null);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        } */
		return tweetList;
	}

	public static Map sortByValue(Map<String, Integer> unsortMap) {	 
		List<String> list = new LinkedList(unsortMap.entrySet());

		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
						.compareTo(((Map.Entry) (o1)).getValue());
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	
	public Map<String, Integer> occurenceOfWords(List<String> allTweets) {
		
		// count the occurence of words
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		StringTokenizer st = null;
		for (String t : allTweets) {
			st = new StringTokenizer(t);
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				if (countMap.containsKey(token)) {
					countMap.put(token, countMap.get(token)+1);
				} else{
					countMap.put(token, 1);
				}
			}		
		}

		//remove stop words
		Set<String> stopWords = new StopWords().getStopwordList() ;

		Iterator<Map.Entry<String,Integer>> iter = countMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String,Integer> entry = iter.next();
			if(stopWords.contains(entry.getKey().toLowerCase())){
				iter.remove();
			}
		}

		// sort the number of counts decending order
		Map<String, Integer> x = sortByValue(countMap); 
		return x;		
	}
	
	public static void main(String[] args) {
		String topic = "iphone";
		TweetManager tm = new TweetManager();
		ArrayList<String> allTweets =  tm.getTweets(topic, 10);
		int count = 0;
		for (String s : allTweets) {
			System.out.println(" >>>>>>>>>>>>>>>>>>>>>  " + count++);
			System.out.println(s);
		}

		Map <String, Integer> x = tm.occurenceOfWords(allTweets);
	}
} 