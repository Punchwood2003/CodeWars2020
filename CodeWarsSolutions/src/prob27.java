// ToDo: add the patterns for all of the rhyming schemes
import java.util.ArrayList;
import java.util.Scanner;

public class prob27 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		while(!line.equals("0")) {
			Scanner temp = new Scanner(line);
			int numWords = temp.nextInt();
			ArrayList<String> words = new ArrayList<String>();
			for(int i = 0; i < numWords; i++) {
				words.add(temp.next());			
			}
			getRhyme(words, numWords);
			
			line = scan.nextLine();
		}
	}
	public static void getRhyme(ArrayList<String> words, int numWords) {
		ArrayList<Word> rhymes = new ArrayList<Word>();
		boolean[] ablautReduplication = new boolean[words.size()]; 
		boolean[] progressiveVowelRetraction = new boolean[words.size()]; 
		boolean[] copyReduplication = new boolean[words.size()];
		boolean[] rhymingReduplication = new boolean[words.size()];
		boolean[] shmReduplication = new boolean[words.size()];
		
		for(int i = 0; i < numWords; i++) {
			rhymes.add(new Word(words.get(i)));
			ablautReduplication[i] = rhymes.get(i).ablautReduplication;
			progressiveVowelRetraction[i] = rhymes.get(i).progressiveVowelRetraction;
			copyReduplication[i] = rhymes.get(i).copyReduplication;
			rhymingReduplication[i] = rhymes.get(i).rhymingReduplication;
			shmReduplication[i] = rhymes.get(i).shmReduplication;
		}
		
		boolean setIsAblautReduplication = isRhyme(ablautReduplication);
		boolean setIsProgressiveVowelRetraction = isRhyme(progressiveVowelRetraction);
		boolean setIsCopyReduplication = isRhyme(copyReduplication);
		boolean setIsRhymingReduplication = isRhyme(rhymingReduplication);
		boolean setIsShmReduplication = isRhyme(shmReduplication);
		
		if(setIsShmReduplication) {
			print("SHM", words);
		}
		else if(setIsProgressiveVowelRetraction) {
			print("PROGRESSIVE", words);
		}
		else if(setIsRhymingReduplication) {
			print("RHYMING", words);
		}
		else if(setIsAblautReduplication) {
			print("ABLAUT", words);
		}
		else {
			print("COPY", words);
		}
	}
	public static boolean isRhyme(boolean[] rhymes) {
		for(int i = 0; i < rhymes.length; i++) {
			if(!rhymes[i]) {
				return false;
			}
		}
		return true;
	}
	public static void print(String type, ArrayList<String> words) {
		String out = "";
		for(int i = 0; i < words.size(); i++) {
			out += words.get(i) + " ";
		}
		out += type;
		System.out.println(out);
	}
}
class Word {
	String word;
	boolean ablautReduplication, progressiveVowelRetraction, 
			copyReduplication, rhymingReduplication, shmReduplication;
	public Word(String word) {
		this.word = word;
		this.ablautReduplication = isAblautReduplication();
		this.progressiveVowelRetraction = isProgressiveVowelRetraction();
		this.copyReduplication = isCopyReduplication();
		this.rhymingReduplication = isRhymingReduplication();
		this.shmReduplication = isShmReduplication();
	}
	public boolean isAblautReduplication() {
		return false;
	}
	public boolean isProgressiveVowelRetraction() {
		return false;
	}
	public boolean isCopyReduplication() {
		return false;
	}
	public boolean isRhymingReduplication() {
		return false;
	}
	public boolean isShmReduplication() {
		return false;
	}
}