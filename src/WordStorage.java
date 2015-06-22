import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class WordStorage {
	// The counts we use to train the probabilities in the unigram model
	HashMap<String, Integer> wordToCount = new HashMap<String, Integer>();

	// A word prefix to dictionary entry map, lets us make the predictions
	private HashMap<String, DictEntry[]> prefixToEntry = new HashMap<String, DictEntry[]>();

	// Hashmap to predict next most probable words
	private HashMap<String, NextEntry[]> wordToNext = new HashMap<String, NextEntry[]>();
	
	// Total count of all the words we've been trained on
	private long total = 0;

	// Train on the specified file, this method can be called multiple
	// times in order to train on a set of files.
	public void train(InputStream trainingFile) throws IOException,
	NullPointerException, FileNotFoundException {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(trainingFile));    //Try to read the file

		String line = null; // For retrieving lines from file
		String[] wordarr = null; // For retrieving words from line

		while ((line = br.readLine()) != null) {
			wordarr = line.replaceAll("[^a-zA-Z]'", " ").split(" ");
			for(String word:wordarr)
				trainWord(word);

			for (int i=0;i<wordarr.length-1;i++)
					trainNextWord(wordarr[i], wordarr[i+1]);
			}
		br.close();

		// Removing any extra value that might've been added to HashMap
		wordToCount.remove("");
		wordToNext.remove("");

	}

	// Train on an instance of the given word
	public void trainWord(String word) {
		if (wordToCount.containsKey(word))
			wordToCount.put(word, wordToCount.get(word) + 1);
		else
			wordToCount.put(word, 1);
	}

	// Train previous and next word pairs
	public void trainNextWord(String prevWord, String nextWord) {
		prevWord = prevWord.trim();
		nextWord = nextWord.trim();
		if(nextWord.equals(""))
				return;
			NextEntry ne = new NextEntry(nextWord, 1);
		if (wordToNext.containsKey(prevWord))
			wordToNext.put(prevWord, ne.nextAllWords(wordToNext.get(prevWord)));
		else {
			NextEntry temp[] = new NextEntry[50];
			temp[0] = new NextEntry(ne);
			wordToNext.put(prevWord, temp);
		}
	}

	// Find out how many total words of training data we've seen
	public long getTrainingCount() {
		total = 0;
		for (String key : wordToCount.keySet())
			total += wordToCount.get(key);
		return total;
	}

	// Return the number of times we've seen a particular word.
	// Returns 0 if we've never seen this word.
	public int getWordCount(String word) {
		if (wordToCount.containsKey(word))
			return wordToCount.get(word);
		else
			return 0;
	}

	// Prepare the object for prefix to word mapping.
	// This should be called AFTER all the training.
	// It MUST be called before getBest() will work.
	public void build() {

		total = getTrainingCount();
		String temppref = "";
		for (Map.Entry<String, Integer> entry : wordToCount.entrySet()) {
			DictEntry de = new DictEntry(entry.getKey(), (double) entry.getValue() / total);
			for (int l = 1; l < de.getWord().length(); l++) {
				temppref = entry.getKey().substring(0, l);
				if (prefixToEntry.containsKey(temppref))
					prefixToEntry.put(temppref,	de.SortPut(prefixToEntry.get(temppref)));
				else {
					DictEntry temp[] = new DictEntry[4];
					temp[0] = new DictEntry(de);
					prefixToEntry.put(temppref, temp);
				}
			}
		}
	}

	// Return the best matching DictEntry object based on a prefix.
	// If the prefix doesn't match any known word, returns null.
	public String getBest(String prefix){
		prefix= prefix.toLowerCase();
		String Best = "";
		int i = 0;
		if (prefixToEntry.get(prefix) == null)
			return Best;
		else {
			while (prefixToEntry.get(prefix)[i] != null) {
				Best += prefixToEntry.get(prefix)[i] + "\n";
				i++;
			}
			return Best;
		}
	}
	
	// Return the most probable nextEntry object based on the previous word.
	// If the previous word doesn't match any known word, returns null.
	public String getNextBest(String word){
		word= word.toLowerCase().trim();
		String BestNexts = "";
		int i = 0;
		if (wordToNext.get(word) == null)
			return BestNexts;
		else {
			while (wordToNext.get(word)[i] != null) {
				BestNexts += wordToNext.get(word)[i] + "\n";
				i++;
			}
			return BestNexts;
		}
	}
}