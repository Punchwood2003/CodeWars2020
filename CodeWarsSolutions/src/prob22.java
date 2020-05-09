import java.util.Scanner;

public class prob22 {
	public static final Note[][] SCALES= {{new Note('E', 0, 'E'), new Note('F', 1, 'E'), new Note('G', 3, 'E'), new Note('A', 5, 'E'), new Note('B', 7, 'E'), new Note('C', 8, 'E'), new Note('D', 10, 'E'), new Note('E', 12, 'E')}, 
										  {new Note('A', 0, 'A'), new Note('B', 2, 'A'), new Note('C', 3, 'A'), new Note('D', 5, 'A'), new Note('E', 7, 'A'), new Note('F', 8, 'A'), new Note('G', 10, 'A'), new Note('A', 12, 'A')}};
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			if(line.charAt(0) >= '0' && line.charAt(0) <= '9') {
				Scanner temp = new Scanner(line);
				int fret = Integer.parseInt(temp.next());
				char string = temp.next().charAt(0);
				Note target = null;
				for(int i = 0; i < SCALES.length; i++) { 
					for(int j = 0; j < SCALES[i].length; j++) {
						if(SCALES[i][j].equals(fret, string)) {
							if(j+1 < 8) {
								target = SCALES[i][j+1];
								break;
							}
							else {
								target = SCALES[i][0];
								break;
							}
						}
					}
				}
				System.out.println(target.getNote());
			} 
			else {
				char note = line.charAt(0);
				String output = "";
				for(int i = 0; i < SCALES.length; i++) { 
					for(int j = 0; j < SCALES[i].length; j++) {
						if(SCALES[i][j].getNote() == note) {
							output += SCALES[i][j].getFret() + " " + SCALES[i][j].getString() + " ";
						}
					}
				}
				System.out.println(output);
			}
		}
	}
}
class Note {
	private char note;
	private int fret;
	private char string;
	public Note(char note, int fret, char string) {
		this.note = note;
		this.fret = fret;
		this.string = string;
	}
	public char getNote() {
		return this.note;
	}
	public char getString() {
		return this.string;
	}
	public int getFret() {
		return this.fret;
	}
	public boolean equals(int fret, char string) {
		if(this.fret == fret && this.string == string) {
			return true;
		}
		else {
			return false;
		}
	}
}
