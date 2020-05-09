// author@MatthewSheldon
import java.util.Scanner;

public class prob21 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			String tempName = scan.nextLine().substring(9);
			double tempRate = Double.parseDouble(scan.nextLine().substring(5));
			String tempIn1 = scan.nextLine().substring(3);
			String tempOut1 = scan.nextLine().substring(4);
			String tempIn2 = scan.nextLine().substring(3);
			String tempOut2 = scan.nextLine().substring(4);
			Employee current = new Employee(tempName, tempRate, tempIn1, tempOut1, tempIn2, tempOut2);
			System.out.println(current);
		}
	}
}
class Employee {
	private double rate, earnings;
	private String in1, in2, out1, out2;
	private String name;
	public Employee(String n, double r, String one, String two, String three, String four) {
		this.name = n;
		this.rate = r;
		this.in1 = one;
		this.out1 = two;
		this.in2 = three;
		this.out2 = four;
		calculateEarnings();
	}
	private void calculateEarnings() {
		String temp1 = in1.substring(0,2);
		String temp2 = in1.substring(2);
		String temp3 = out1.substring(0,2);
		String temp4 = out1.substring(2);
		double hours = (Integer.parseInt(temp3) - Integer.parseInt(temp1)); 
		hours += ((double) Integer.parseInt(temp4) - (double) Integer.parseInt(temp2)) / 60;
		
		temp1 = in2.substring(0,2);
		temp2 = in2.substring(2);
		temp3 = out2.substring(0,2);
		temp4 = out2.substring(2);
		hours += (Integer.parseInt(temp3) - Integer.parseInt(temp1)); 
		hours += ((double) Integer.parseInt(temp4) - (double) Integer.parseInt(temp2)) / 60;
		
		earnings = hours * rate;
	}
	public String toString() {
		return String.format("%s earned $%.2f", name, earnings);
	}
}
