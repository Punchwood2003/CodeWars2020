// author@MatthewSheldon
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
			printRhyme(words, numWords);
			
			line = scan.nextLine();
		}
	}
	public static void printRhyme(ArrayList<String> words, int numWords) {
		ArrayList<Word> rhymes = new ArrayList<Word>();
		
		for(int i = 0; i < numWords; i++) {
			rhymes.add(new Word(words.get(i)));
		}
		
		String type = findRhyme(rhymes);
		
		switch(type) {
			case "SHM" : {
				print("SHM", words);
				break;
			}
			case "PROGRESSIVE" : {
				print("PROGRESSIVE", words);
				break;
			}
			case "RHYMING" : {
				print("RHYMING", words);
				break;
			}
			case "ABLAUT" : {
				print("ABLAUT", words);
				break;
			}
			case "COPY" : {
				print("COPY", words);
				break;
			}
		}
	}
	public static String findRhyme(ArrayList<Word> words) {
		boolean setIsCopyReduplication = true;
		for(int i = 0; i < words.size()-1; i++) {
			if(!words.get(i).isCopyReduplication(words.get(i+1))) {
				setIsCopyReduplication = false;
				break;
			}
		}
		if(setIsCopyReduplication) {
			return "COPY";
		}
		
		boolean setIsShmReduplication = true;
		for(int i = 0; i < words.size()-1; i++) {
			if(!words.get(i).isShmReduplication(words.get(i+1))) {
				setIsShmReduplication = false;
				break;
			}
		}
		if(setIsShmReduplication) {
			return "SHM";
		}
		
		boolean setIsProgressiveVowelRetraction = true;
		for(int i = 0; i < words.size()-1; i++) {
			if(!words.get(i).isProgressiveVowelRetraction(words.get(i+1))) {
				setIsProgressiveVowelRetraction = false;
				break;
			}
		}
		if(setIsProgressiveVowelRetraction) {
			return "PROGRESSIVE";
		}
		
		boolean setIsAblautReduplication = true; 
		for(int i = 0; i < words.size()-1; i++) {
			if(!words.get(i).isAblautReduplication(words.get(i+1))) {
				setIsAblautReduplication = false;
				break;
			}
		}
		if(setIsAblautReduplication) {
			return "ABLAUT";
		}
		
		/*boolean setIsRhymingReduplication = true; 
		for(int i = 0; i < words.size()-1; i++) {
			if(!words.get(i).isRhymingReduplication(words.get(i+1))) {
				setIsRhymingReduplication = false;
				break;
			}
		}
		if(setIsRhymingReduplication) {
			return "RHYMING";
		}*/
		
		return "RHYMING";
		
		
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
	public Word(String word) {
		this.word = word;
	}
	// Perfect
	public boolean isAblautReduplication(Word other) {
		String parts1 = this.word.replaceAll("[AEIOU]", "");
		String parts2 = other.word.replaceAll("[AEIOU]", "");
		if(parts1.equals(parts2)) {
			return true;
		}
		return false;
	}
	// Perfect
	public boolean isProgressiveVowelRetraction(Word other) {
		String parts1 = this.word.replaceAll("[AEIOU]", "");
		String parts2 = other.word.replaceAll("[AEIOU]", "");
		if(!parts1.equals(parts2)) {
			return false;
		}
		
		String vowels = "IAEOU";
		String vowels1 = this.word.replaceAll("[^AEIOU]", "");
		String vowels2 = other.word.replaceAll("[^AEIOU]", "");
		for(int i = 0; i < vowels1.length(); i++) {
			if(vowels.indexOf(vowels1.charAt(i)) < vowels.indexOf(vowels2.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	public boolean isCopyReduplication(Word other) {
		return this.word.equals(other.word);
	}
	/*public boolean isRhymingReduplication(Word other) {
		String after1 = "HELP", after2 = "ME";
		String vowels = "AEIOU";
		for(int i = 0; i < this.word.length(); i++) {
			if(vowels.indexOf(this.word.charAt(i)) < 0 && i+1 < this.word.length()) {
				after1 = this.word.substring(i+1);
				break;
			}
		}
		for(int i = 0; i < other.word.length(); i++) {
			if(vowels.indexOf(other.word.charAt(i)) < 0 && i+1 < other.word.length()) {
				after2 = other.word.substring(i+1);
				break;
			}
		}
		if(after1.equals(after2)) {
			return true;
		}
		else {
			if(this.word.length() > other.word.length()) {
				if(this.word.substring(1).equals(other.word)) {
					return true;
				}
			}
			else {
				if(other.word.substring(1).equals(this.word)) {
					return true;
				}
			}
		}
		return false;
	}*/
	// Perfect
	public boolean isShmReduplication(Word other) {
		int index = 0;
		if(this.word.length() > 3 && this.word.substring(0,3).equals("SHM")) {
			for(int i = 0; i < other.word.length(); i++) {
				if(other.word.charAt(i) == 'A' || other.word.charAt(i) == 'E' || other.word.charAt(i) == 'I' || other.word.charAt(i) == 'O' || other.word.charAt(i) == 'U') {
					index = i;
					i = other.word.length();
				}
			}
			if(other.word.length() > 3 && other.word.substring(index).equals(this.word.substring(3))) {
				return true;
			}
		}
		if(other.word.length() > 3 && other.word.substring(0,3).equals("SHM")) {
			for(int i = 0; i < this.word.length(); i++) {
				if(this.word.charAt(i) == 'A' || this.word.charAt(i) == 'E' || this.word.charAt(i) == 'I' || this.word.charAt(i) == 'O' || this.word.charAt(i) == 'U') {
					index = i;
					i = this.word.length();
				}
			}
			if(this.word.substring(index).equals(other.word.substring(3))) {
				
				return true;
			}
		}
		return false;
	}
}