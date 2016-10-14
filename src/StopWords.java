import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class StopWords {
	
	Set<String> stopwordList; 

	public Set<String> getStopwordList() {
		stopwordList = prepareTheStopWordList();
		return stopwordList;
	}

	public void setStopwordList(Set<String> stopwordList) {
		this.stopwordList = stopwordList;
	}
 
	public Set<String> prepareTheStopWordList() {
		// can copy from here
		//http://dev.mysql.com/doc/refman/5.5/en/fulltext-stopwords.html
		Set<String> stopWords = new HashSet<String>();
		stopWords.add("the"); stopWords.add("a"); stopWords.add("on");
		stopWords.add("of"); stopWords.add("else"); stopWords.add("?");
		stopWords.add("and");stopWords.add("if");stopWords.add("so");
		stopWords.add("my");stopWords.add("I");stopWords.add("to");
		stopWords.add("that");stopWords.add("this");stopWords.add("from");
		stopWords.add("me");stopWords.add("an");stopWords.add("you");
		// add more words here
		return stopWords;
	}
}
