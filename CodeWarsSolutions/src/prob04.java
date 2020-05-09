import java.util.Scanner;

public class prob04
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int one = s.nextInt();
		int two = s.nextInt();
		int gcd = 1;
		for(int i  = 1; i < one && i < two; i ++)
		{
			if (one % i == 0 && two % i ==0)
				gcd = i;
		}
		int lcm = one * two / gcd;
		System.out.println(lcm);
		
				
	}
}
