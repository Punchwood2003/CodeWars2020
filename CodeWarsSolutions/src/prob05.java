import java.util.Scanner;

public class prob05
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		String isPrime = "is";
		for(int i = 2; i <= Math.sqrt(num); i++)
		{
			if(num % i == 0)
			{
				isPrime = "is NOT";
				break;
			}
		}
		System.out.println(num + " " + isPrime + " Prime");

	}
}
