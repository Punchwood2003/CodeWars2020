//author@MatthewSheldon
/*
 * ToDo: add the ability to incorporate fixed characters
 * 		 
 */
import java.util.ArrayList;
import java.util.Scanner;

public class prob24 {
	// This object keeps track of the possible words to fill the object board
	public Letter[][] currentWords;
	
	// This object keeps track of all of the indexes of the spaces as well as the length and orientation.
	public ArrayList<Index> indexes;
	
	private int numSpaces;
	public boolean[] usedWords;
	public String[] words;
	public ArrayList<String> finalWords;
	public ArrayList<Integer> finalWordNumbers;
	
	/*
	 * This constructor creates an object with access to the current board,
	 * the list of words available and the number of words that are needed
	 * to fill up the board.
	 */
	public prob24(String[] words, int numSpaces, ArrayList<Index> indexes, ArrayList<Letter> confirmedLetters) {
		this.words = new String[words.length];
		System.arraycopy(words, 0, this.words, 0, words.length);

		this.indexes = indexes;
		this.usedWords = new boolean[words.length];


		this.currentWords = new Letter[11][11];

		for(int i = 0 ; i < confirmedLetters.size(); i++) {
			int[] cords = confirmedLetters.get(i).cord;
			currentWords[cords[0]][cords[1]] = confirmedLetters.get(i);
		}
		
		this.numSpaces = numSpaces;
		
		finalWords = new ArrayList<String>();
		finalWordNumbers = new ArrayList<Integer>();
	}
	
	/*
	 * This method returns the starting position for a given
	 * word number on the board. References the new object
	 * of the list of Index objects
	 */
	private int[] getSpaceStart(int spaceNumber) {
		for(int i = 0; i < indexes.size(); i++) {
			if(Math.abs(indexes.get(i).wordIndex) == spaceNumber) {
				return indexes.get(i).cords;
			}
		} 
		return null;
	}
	
	/*
	 * Returns the total size of the current blank that 
	 * we are trying to fit a word into. 
	 */
	public int getSpaceSize(int spaceNumber) {
		return this.indexes.get(spaceNumber-1).length;
    }
	
	/*
	 * Checks to make sure that the current spot is not already filled and 
	 * that the current spot equals the same character.
	 */
	private boolean intersectionsMatch(int wordIndex, int spaceNumber) {
        int[] spaceCoord = getSpaceStart(spaceNumber);

        int row = spaceCoord[0];
        int col = spaceCoord[1];

        int spaceSize = getSpaceSize(spaceNumber);
        
        boolean isAcross = this.indexes.get(spaceNumber-1).wordIndex > 0;
        String word = words[wordIndex];

        for(int i = 0; i < spaceSize; i++) {
            if(currentWords[row][col] != null && currentWords[row][col].character != word.charAt(i)) {
                return false;
            }

            if(isAcross) {
                col += 1;
            } 
            else {
                row += 1;
            }
        }

        return true;
    }
	
	/*
	 * Checks to see if the current word fits in the current
	 * set of blanks. Also checks to see that it is not already filled
	 * with a different word.
	 */
	private boolean isValid(int wordIndex, int spaceNumber) {
        int spaceSize = getSpaceSize(spaceNumber);

        return words[wordIndex].length() == spaceSize &&
               intersectionsMatch(wordIndex, spaceNumber);
    }
	
	/*
	 * Add the current word into the list of letters
	 * that are the filled-in board for the solution.
	 * Mark that word as used so that we don't try
	 * to re-use the same word multiple times.
	 */
	private void applyValue(int wordIndex, int spaceNumber) {
		int[] spaceCoord = getSpaceStart(spaceNumber);

		int row = spaceCoord[0];
		int col = spaceCoord[1];

		int spaceSize = getSpaceSize(spaceNumber);
		boolean isAcross = this.indexes.get(spaceNumber-1).wordIndex > 0;

		Letter[] ltrs = Letter.lettersFromString(words[wordIndex], wordIndex);

		for(int i = 0; i < spaceSize; i++) {
			if(currentWords[row][col] == null || (!currentWords[row][col].set && currentWords[row][col].character == ltrs[i].character)) {
				currentWords[row][col] = ltrs[i];
			}

			if(isAcross) {
				col += 1;
			} 
			else {
				row += 1;
			}
		}

		usedWords[wordIndex] = true;
		finalWords.add(words[wordIndex]);
		finalWordNumbers.add((Integer) Math.abs(spaceNumber));
	}
	
	/*
	 * Remove the current word from the list of letters
	 * that are the filled-in board for the solution.
	 * Mark that word as not used so that we can
	 * use it again in the future.
	 */
	private void removeValue(int wordIndex, int spaceNumber) {
        int[] spaceCoord = getSpaceStart(spaceNumber);

        int row = spaceCoord[0];
        int col = spaceCoord[1];

        int spaceSize = getSpaceSize(spaceNumber);
        boolean isAcross = this.indexes.get(spaceNumber-1).wordIndex > 0;

        for (int i = 0; i < spaceSize; i++) {
            if((currentWords[row][col] != null && !currentWords[row][col].set) && currentWords[row][col].wordIndex == wordIndex) {
                currentWords[row][col] = null;
            }

            if (isAcross) {
                col += 1;
            } else {
                row += 1;
            }
        }

        usedWords[wordIndex] = false;
        finalWords.remove(words[wordIndex]);
		finalWordNumbers.remove((Integer) Math.abs(spaceNumber));
    }
	
	/*
	 * Recursive method that will find the solutions to the crossword puzzle.
	 */
	private boolean findSolutions(int spaceNumber) {
        if(spaceNumber > numSpaces) {
            // all the spaces have been filled!
            displaySolution();
            return false;
        }

        /*
         * Loop through possible valid words for this space.
         * Skip words that have already been used.
         */
        for(int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            if (usedWords[wordIndex]) {
                continue;
            }

            if (isValid(wordIndex, spaceNumber)) {

                applyValue(wordIndex, spaceNumber);

                if(!findSolutions(spaceNumber + 1)) {
                	return false;
                }
                removeValue(wordIndex, spaceNumber);
            }
        }
        return true;
    }
	
	public void displaySolution() {
		for(int i = 0; i < finalWords.size(); i++) {
			int index = finalWordNumbers.indexOf((Integer) (i+1));
			System.out.println(String.format("%02d is %s", index+1, finalWords.get(index)));
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Index> indexes = new ArrayList<Index>();
		
		/*
		 * First loop will instantiate the board into a working condition.
		 */
		String line = scan.nextLine();
		int numSpaces = 0;
		while(!line.equals("-------")) {
			Scanner temp = new Scanner(line);
			
			int wordNumber = temp.nextInt();
			char orientation = temp.next().charAt(0);
			int len = temp.nextInt();
			int y = temp.nextInt();
			int x = temp.nextInt();

			/*
			 * Populate the board with the indexes of the 
			 * start of a word and add any BLANK spaces
			 * Using a negative value of wordNumber to 
			 * indicate the direction down or 
			 * orientation == 'V' 
			 */
			if(orientation == 'V') {
				indexes.add(new Index(x, y, len, (-1 * wordNumber)));
			}
			else {
				indexes.add(new Index(x, y, len, wordNumber));
			}
			numSpaces++;

			line = scan.nextLine();
		}

		/*
		 * Second loop adds confirmed letters into the board
		 */
		line = scan.nextLine();
		ArrayList<Letter> confirmedLetters = new ArrayList<Letter>();
		while(!line.equals("-------")) {
			Scanner temp = new Scanner(line);
			int y = temp.nextInt();
			int x = temp.nextInt();
			char letter = temp.next().charAt(0);
			confirmedLetters.add(new Letter(letter, x, y));
			line = scan.nextLine();
		}

		/*
		 * Third loop makes an array of all possible words that could 
		 * fill in the Cross Word Puzzle
		 */
		ArrayList<String> possibleWords = new ArrayList<String>();
		while(scan.hasNextLine()) {
			possibleWords.add(scan.nextLine());
		}
		String[] words = new String[possibleWords.size()]; 
		for(int i = 0; i < possibleWords.size(); i++) {
			words[i] = possibleWords.get(i);
		}
		
		
		prob24 crossWordPuzzle = new prob24(words, numSpaces, indexes, confirmedLetters);
		crossWordPuzzle.findSolutions(1);
	}
}
class Letter {
	public int wordIndex;
	public char character;
	public boolean set;
	public int[] cord;
	
	public Letter(int wordIndex, char ch) {
		this.wordIndex = wordIndex;
		this.character = ch;
		this.set = false;
	}
	public Letter(char ch, int x, int y) {
		this.character = ch;
		this.wordIndex = 0;
		this.set = true;
		int[] cord = {x, y};
		this.cord = cord;
	}
	public static Letter[] lettersFromString(String str, int wordIndex) {
		Letter[] letters = new Letter[str.length()];
		for (int i = 0; i < str.length(); i++) {
			letters[i] = new Letter(wordIndex, str.charAt(i));
		}

		return letters;
	}
}
class Index {
	public int wordIndex;
	public int length;
	public int[] cords;
	public Index(int x, int y, int len, int index) {
		this.wordIndex = index;
		this.length = len;
		int[] cords = {x, y};
		this.cords = cords;
	}
}
