import java.util.Scanner;

public class prob08
{
	public static void main(String[]args)
	{
		Scanner s = new Scanner(System.in);
		while(s.hasNext())
		{
		String line = s.nextLine();
		String[] words = line.split(" ");
		int sum = 0;
		for(int i = 0 ; i < words.length; i++)
		{
			sum += words[i].length();
			if(sum > 80)
			{
				words[i] = "\n" + words[i];
				sum = -2;
				i--;
			}
			sum+=1;
			//System.out.print(sum + " ");
			
		}
		
		for(int i = 0 ; i < words.length; i++)
		{
			System.out.print(words[i] + " ");
		}
		System.out.println();
	}
}
}
