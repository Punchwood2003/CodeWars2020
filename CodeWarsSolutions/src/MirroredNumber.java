import java.util.Scanner;

public class MirroredNumber
{
	public static void main(String[]args)
	{
		Scanner s = new Scanner(System.in);
		String num = s.next();
		for(int i = num.length()-1 ; i >= 0; i--)
		{
			System.out.print(num.charAt(i));
		}
		
	}
}
