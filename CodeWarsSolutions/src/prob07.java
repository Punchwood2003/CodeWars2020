import java.util.Scanner;

public class prob07
{
	public static void main(String[]args)
	{
		Scanner s = new Scanner(System.in);
		while(s.hasNext())
		{
			String line = s.nextLine();
			String[] nums = new String[3];
			nums = line.split(" ");
			double sumInches = 0;
			if(nums.length == 3)
				sumInches = (Integer.parseInt(nums[0])*36) + (Integer.parseInt(nums[1])*12) + Integer.parseInt(nums[2]);
			else if(nums.length == 2)
				sumInches = (Integer.parseInt(nums[0])*36) + (Integer.parseInt(nums[1])*12);
			else
				sumInches = (Integer.parseInt(nums[0])*36);
			sumInches *= 2.54;
			System.out.printf("%.2f",sumInches);

		}


	}
}
