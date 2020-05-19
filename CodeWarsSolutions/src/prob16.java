import java.util.Scanner;

public class prob16
{
	public static void main(String [] args)
	{
		Scanner s= new Scanner(System.in);
		while(s.hasNext())
		{
			double num1 = s.nextInt();
			double num2 = s.nextInt();
			String word = s.next();
			double guess = s.nextDouble();	
			double answer = 0.0;
			String statement = "";
			
			if(word.equals("POWER"))
			{
				answer = Math.pow(num1, num2);
				statement = num1 + " ^ " + num2;
			}
			else if(word.equals("MULTIPLY"))
			{
				answer = num1*num2;
				statement = num1 + " * " + num2;
			}
			else if(word.equals("DIVIDE"))
			{
				answer = num1/num2;
				statement = num1 + " / " + num2;
			}
			else if(word.equals("ADD"))
			{
				answer = num1+num2;
				statement = num1 + " + " + num2;
			}
			else if(word.equals("SUBTRACT"))
			{
				answer = num1-num2;
				statement = num1 + " - " + num2;
			}
			
			if(answer == guess)
			{
				System.out.printf(guess + " is correct for " + statement);
			}
			else
			{
				System.out.printf(statement + " = " + "%.1f" + ", not " + guess, answer);
			}
		}	
	}
}
