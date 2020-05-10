import java.util.Scanner;

public class prob18
{
	public static void main(String [] args)
	{
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine())
		{
			String[] nums = scan.nextLine().split(":");
			double hours = Double.parseDouble(nums[0]);
			double minutes = Double.parseDouble(nums[1]);
			
			double hDegrees = ((hours*30.0) + (minutes*0.5))%360;
			double mDegrees = (minutes*6.0)%360;
			
			double dist1 = Math.abs(hDegrees-mDegrees);
			double dist2 = Math.abs(mDegrees-hDegrees);
			
			double choice = Math.min(dist1, dist2);
			choice = Math.min(choice, 360-choice);
			
			System.out.printf("The angle between the Hour hand and Minute hand is %.2f degrees.\n", choice);
			
		}
		
		scan.close();
	}
}
