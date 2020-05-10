//author@MatthewSheldon
/*
 * ToDo: add the ability to incorporate fixed characters
 * 		 check to see if the program is just running or if it
 * 		 is in an infinite loop.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class prob24 {
	private int numSpaces;
	public int[][] board;
	public boolean[] usedWords;
	public String[] words;
	public Letter[][] currentWords;
	public ArrayList<String> finalWords;
	public ArrayList<Integer> finalWordNumbers;
	public static final int SOLID = -1000;
	public static final int BLANK = -2000;
	
	/*
	 * This constructor creates an object with access to the current board,
	 * the list of words available and the number of words that are needed
	 * to fill up the board.
	 */
	public prob24(int[][] board, String[] words, int numSpaces) {
		this.words = new String[words.length];
		System.arraycopy(words, 0, this.words, 0, words.length);

		this.usedWords = new boolean[words.length];

		this.board = new int[11][11];

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				this.board[i][j] = board[i][j];
			}
		}

		this.currentWords = new Letter[11][11];

		this.numSpaces = numSpaces;
		
		finalWords = new ArrayList<String>();
		finalWordNumbers = new ArrayList<Integer>();
	}
	
	/*
	 * This method returns the starting position for a given
	 * word number on the board.
	 */
	private int[] getSpaceStart(int spaceNumber) {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (board[i][j] == BLANK || board[i][j] == SOLID) {
                    continue;
                }
                if (Math.abs(board[i][j]) == spaceNumber) {
                    int[] arr = {i, j};
                    return arr;
                }
            }
        }
        return null;
    }
	
	/*
	 * Returns the total size of the current blank that 
	 * we are trying to fit a word into. 
	 */
	public int getSpaceSize(int spaceNumber) {
        int[] spaceCoord = getSpaceStart(spaceNumber);

        int row = spaceCoord[0];
        int col = spaceCoord[1];

        boolean isAcross = board[row][col] > 0;

        int limit;

        if (isAcross) {
            limit = board[0].length - col;
        } 
        else {
            limit = board.length - row;
        }

        /*
         * In the for loop below, we break if we reach a SOLID index before
         * reaching the edge of the board.
         */
        int i;
        for (i = 0; i < limit; i++) {
            if(isAcross && board[row][col + i] == SOLID) {
                break;
            }

            if (!isAcross && board[row + i][col] == SOLID) {
                break;
            }
        }

        return i;
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
        boolean isAcross = board[row][col] > 0;
        String word = words[wordIndex];

        for(int i = 0; i < spaceSize; i++) {
            if(currentWords[row][col] != null && currentWords[row][col].character != word.charAt(i)) {
                return false;
            }

            if (isAcross) {
                col += 1;
            } else {
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
		boolean isAcross = board[row][col] > 0;

		Letter[] ltrs = Letter.lettersFromString(words[wordIndex], wordIndex);

		for(int i = 0; i < spaceSize; i++) {
			if(currentWords[row][col] == null) {
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
        boolean isAcross = board[row][col] > 0;

        for (int i = 0; i < spaceSize; i++) {
            if(currentWords[row][col] != null && currentWords[row][col].wordIndex == wordIndex) {
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
	private void findSolutions(int spaceNumber) {
        if(spaceNumber > numSpaces) {
            // all the spaces have been filled!
            displaySolution();
            return;
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

                findSolutions(spaceNumber + 1);

                removeValue(wordIndex, spaceNumber);
            }
        }
    }
	
	public void displaySolution() {
		for(int i = 0; i < finalWords.size(); i++) {
			int index = finalWordNumbers.indexOf((Integer) (i+1));
			System.out.println(String.format("%02d is %s", index, finalWords.get(index)));
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/*
		 *  Create a blank canvas of an 11 x 11 board prior to filling in indexes. 
		 *  Use SOLID because that way we don't have to fill in the SOLID Spaces
		 */
		int[][] board = { {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 				
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID}, 
						  {SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID, SOLID} };
		
		/*
		 * First loop will instantiate the board into a working condition.
		 */
		String line = scan.nextLine();
		while(!line.equals("-------")) {
			Scanner temp = new Scanner(line);
			
			int wordNumber = temp.nextInt();
			char orientation = temp.next().charAt(0);
			int len = temp.nextInt();
			int x = temp.nextInt();
			int y = temp.nextInt();

			/*
			 * Populate the board with the indexes of the 
			 * start of a word and add any BLANK spaces
			 * Using a negative value of wordNumber to 
			 * indicate the direction down or 
			 * orientation == 'V' 
			 */
			if(orientation == 'V') {
				board[x][y] = -1 * wordNumber;
				for(int i = 1; i < len-1; i++) {
					if(board[x][y+i] == SOLID) {
						board[x][y+i] = BLANK;
					}
				}
			}
			else {
				board[x][y] = wordNumber;
				for(int i = 1; i < len-1; i++) {
					if(board[x][y+i] == SOLID) {
						board[x+i][y] = BLANK;
					}
				}
			}

			line = scan.nextLine();
		} 

		/*
		 * Second loop adds confirmed letters into the board
		 */
		line = scan.nextLine();
		ArrayList<Letter> confirmedLetters = new ArrayList<Letter>();
		while(!line.equals("-------")) {
			Scanner temp = new Scanner(line);
			int x = temp.nextInt();
			int y = temp.nextInt();
			char letter = temp.next().charAt(0);
			
		}

		/*
		 * Third loop makes an array of all possible words that could 
		 * fill in the Cross Word Puzzle
		 */
		ArrayList<String> possibleWords = new ArrayList<String>();
		while(scan.hasNextLine()) {
			possibleWords.add(scan.nextLine());
		}
		String[] words = (String[]) possibleWords.toArray();
		prob24 crossWordPuzzle = new prob24(board, words, words.length);
		crossWordPuzzle.findSolutions(1);
	}
}
class Letter {
	public int wordIndex;
	public char character;

	public Letter(int wordIndex, char ch) {
		this.wordIndex = wordIndex;
		this.character = ch;
	}
	public static Letter[] lettersFromString(String str, int wordIndex) {
		Letter[] letters = new Letter[str.length()];
		for (int i = 0; i < str.length(); i++) {
			letters[i] = new Letter(wordIndex, str.charAt(i));
		}

		return letters;
	}
}