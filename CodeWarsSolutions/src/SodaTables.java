import java.util.Scanner;

public class SodaTables
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		int one = s.nextInt();
		int two = s.nextInt();
		int gcd = 1;
		for(int i  = 0; i < one && i < two; i ++)
		{
			if (one % i == 0 && two % i ==0)
				gcd = i;
		}
		System.out.println(gcd);
	}
}