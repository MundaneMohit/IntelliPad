import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class NextEntry {
	private String nextWord;
	private int nextChance;
	ArrayList <NextEntry> SortNextBest = new ArrayList<NextEntry>(); 

	// Constructor, all the information
	NextEntry(String a, int b)
	{
		nextWord = a;
		nextChance = b;
	}

	// Constructor taking object
	NextEntry(NextEntry a)
	{
		nextWord = a.nextWord;
		nextChance = a.nextChance;
	}

	// Getter for the word
	public String getnextWord() {
		return nextWord;
	}

	// Getter for the probability
	public int getChance() {
		return nextChance;
	}

	// Sorting Object array
	NextEntry[] nextAllWords(NextEntry d[]) {
		int i = 0;
		int flag = 0;
		SortNextBest.clear();
		while (d[i] != null) {
			
			if(d[i].getnextWord().equals(this.getnextWord())){
				SortNextBest.add(new NextEntry(d[i].getnextWord(), d[i].getChance() + 1));
				flag=1;}
			else	
				SortNextBest.add(d[i]);
			i++;
		}
		if(flag == 0)
			SortNextBest.add(this);
		Collections.sort(SortNextBest, new MyComparator());
		for (i = 0; i < SortNextBest.size(); i++) {
			d[i] = SortNextBest.get(i);
			if (i >= 2)
				break;
		}
		return d;
	}

	// Creating comparator to find suitable words
	public class MyComparator implements Comparator<NextEntry> {
		@Override
		public int compare(NextEntry n1, NextEntry n2) {
			int f1 = n1.getChance();
			int f2 = n2.getChance();
			if (f1 < f2)
				return 1;
			else if (f1 >= f2)
				return -1;
			return 0;
		}
	}

	@Override
	public String toString() {

		String result = "";
		result += nextWord;
		//result += "\t";
		//result += nextChance;

		return result;
	}
}
