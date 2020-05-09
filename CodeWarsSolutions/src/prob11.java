import java.util.Scanner;

public class prob11
{
	public static void main(String [] args)
	{
		Scanner s= new Scanner(System.in);
		while(s.hasNext())
		{
			int num = s.nextInt();
			String binary = Integer.toBinaryString(num);
			if(binary.length() % 2 == 1 &&
			  (binary.indexOf('0') == binary.lastIndexOf('0') &&
			  (binary.charAt(binary.length() / 2) == '0')))
			{
				System.out.println(num + " yes");
			}
			else
			{
				System.out.println(num + " no");
			}
		}
		
	}
}
