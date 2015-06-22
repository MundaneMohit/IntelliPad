import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DictEntry {
	private String word; // word for this entry
	private double prob; // bigram language model probability
	ArrayList<DictEntry> Sorter = new ArrayList<DictEntry>();

	// Constructor, all the information
	public DictEntry(String word, double prob) {
		this.word = word;
		this.prob = prob;
	}

	// Constructor taking object
	public DictEntry(DictEntry d) {
		this.prob = d.prob;
		this.word = d.word;
	}

	// Sorting Object array
	DictEntry[] SortPut(DictEntry d[]) {
		int i = 0;
		Sorter.clear();
		while (d[i] != null) {

			if(d[i].getWord().equals(this.getWord()))
				Sorter.add(this);
			else	
				Sorter.add(d[i]);
			i++;
		}
		if(!Sorter.contains(this))
			Sorter.add(this);
		Collections.sort(Sorter, new ResultComparator());
		for (i = 0; i < Sorter.size(); i++) {
			d[i] = Sorter.get(i);
			if (i >= 2)
				break;
		}
		return d;
	}

	// Getter for the word
	public String getWord() {
		return word;
	}

	// Getter for the probability
	public double getProb() {
		return prob;
	}

	// Creating comparator to find suitable words
	public class ResultComparator implements Comparator<DictEntry> {
		@Override
		public int compare(DictEntry r1, DictEntry r2) {
			double f1 = r1.getProb();
			double f2 = r2.getProb();
			if (f1 < f2)
				return 1;
			else if (f1 > f2)
				return -1;
			return 0;
		}
	}

	@Override
	public String toString() {

		String result = "";
		result += word;
		//result += "\t";

		//result += prob;

		return result;
	}
}