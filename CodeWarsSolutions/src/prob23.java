// author@MatthewSheldon
import java.util.Scanner;

public class prob23 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numTimes = scan.nextInt(); scan.nextLine();
		for(int i = 0; i < numTimes; i++) {
			String temp = scan.nextLine().toLowerCase().replaceAll("[,.?:!'\";]", "");
			String mirror = ""; 
			for(int j = temp.length()-1; j >= 0 ; j--) {
				mirror += temp.charAt(j);
			}
			String rot = "";
			for(int j = 0; j < mirror.length(); j++) {
				rot += getComplement(mirror.charAt(j));
			}
			
			String work = "not";
			String compare1 = temp.replaceAll(" ", "");
			String compare2 = rot.replaceAll(" ", "");
			if(compare1.compareTo(compare2) == 0) {
				work = "is";
			}
			
			System.out.println(temp + " (" + work + ") " + rot);
		}
	}
	public static char getComplement(char curr) {
		char rot = curr;
		switch(curr) {
			case 'a' : {
				rot = 'e';
				break;
			}
			case 'e' : {
				rot = 'a';
				break;
			}
			case 'b' : {
				rot = 'q';
				break;
			}
			case 'q' : {
				rot = 'b';
				break;
			}
			case 'd' : {
				rot = 'p';
				break;
			}
			case 'p' : {
				rot = 'd';
				break;
			}
			case 'h' : {
				rot = 'y';
				break;
			}
			case 'y' : {
				rot = 'h';
				break;
			}
			case 'm' : {
				rot = 'w';
				break;
			}
			case 'w' : {
				rot = 'm';
				break;
			}
			case 'n' : {
				rot = 'u';
				break;
			}
			case 'u' : {
				rot = 'n';
				break;
			}
			default : {break;}
		}
		return rot;
	}
}