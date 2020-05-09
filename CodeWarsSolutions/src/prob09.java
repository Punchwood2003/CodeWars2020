import java.util.Scanner;

public class prob09
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		while(s.hasNext())
		{
			int minutes = s.nextInt();
			int seconds = s.nextInt();
			if(minutes != 0 && seconds != 0)
			{
				
				double minutessecond = minutes + ((double)seconds/60);
				int remainingminutes = (int)(50 - minutessecond);
				int remainingseconds = 60-seconds;
				if( minutessecond > 50)
				{
					System.out.println("Time remaining " + (50-minutes) + " minutes and " + "-" + seconds + " seconds (we're gonna need a bigger record)");
				}
				else if( minutessecond > 25)
				{
					System.out.println("Time remaining " + remainingminutes + " minutes and "  + remainingseconds + " seconds (we'll need both sides)");
				}
				else
				{
					System.out.println("Time remaining " + remainingminutes + " minutes and "  + remainingseconds + " seconds");
				}
			}
		}
	}
}
