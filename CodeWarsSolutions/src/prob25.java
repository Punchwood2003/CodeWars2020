// author@MatthewSheldon

import java.util.ArrayList;
import java.util.Scanner;

public class prob25 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numSystems = scan.nextInt();
		int numDestinations = scan.nextInt();
		scan.nextLine();
		
		/* Take in the cords and the names of the systems
		 */
		ArrayList<String> xCords = new ArrayList<String>();
		ArrayList<String> yCords = new ArrayList<String>();		
		ArrayList<String> systems = new ArrayList<String>();
		//int minX = 0, minY = 0, maxX = 0, maxY = 0;
		for(int i = 0; i < numSystems; i++) {
			Scanner temp = new Scanner(scan.nextLine());
			String cord = temp.next();
			String name = temp.next();
			
			String segment1 = cord.substring(0, 2);
			String segment2 = cord.substring(2);
			
			int x = Integer.parseInt(segment1);
			int y = Integer.parseInt(segment2);
			
			xCords.add(segment1);
			yCords.add(segment2);
			systems.add(name);
		}
		
		/*
		 * Take in the start and end Destinations
		 */
		ArrayList<String[]> destinations = new ArrayList<String[]>();
		for(int i = 0; i < numDestinations; i++) {
			Scanner temp = new Scanner(scan.nextLine());
			String[] arr = {temp.next(), temp.next()};
			destinations.add(arr);
		}
		
		getSolutions(xCords, yCords, systems, destinations);
	}
	public static void getSolutions(ArrayList<String> xCords, ArrayList<String> yCords, ArrayList<String> systems, ArrayList<String[]> destinations) {
		for(int i = 0; i < destinations.size(); i++) {
			int startX = 0, startY = 0, endX = 0, endY = 0;
			String start = destinations.get(i)[0];
			String end = destinations.get(i)[1];
			for(int j = 0; j < systems.size(); j++) {
				if(systems.get(j).equals(start)) {
					startX = Integer.parseInt(xCords.get(j));
					startY = Integer.parseInt(yCords.get(j));
				}
				if(systems.get(j).equals(end)) {
					endX = Integer.parseInt(xCords.get(j));
					endY = Integer.parseInt(yCords.get(j));
				}
			}
			
			int x1 = startY - (startX + (startX & 1)) / 2;
			int z1 = startX;
			int y1 = -x1-z1;
			
			int x2 = endY - (endX + (endX & 1)) / 2;
			int z2 = endX;
			int y2 = -x2-z2;
			
			int distance = (Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2)) / 2;
			
			System.out.println(destinations.get(i)[0] + " " + destinations.get(i)[1] + " " + distance);
		}
	}
}