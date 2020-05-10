import java.util.ArrayList;
import java.util.Scanner;

public class prob14
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int n1 = scan.nextInt();
		int n2 = scan.nextInt();
		
		ArrayList<Integer> equalsums = new ArrayList<Integer>();
		
		while(n1<n2)
		{
			String num = n1+"";
			
			if(num.length() >= 2)
			{
				int sum1 = 0;
				int sum2 = 0;
				
				for(int i = num.length()-1; i >= 0; i-=2)
				{
					sum1 += Integer.parseInt(num.charAt(i)+"");
					if((i-1) >=0)
						sum2 += Integer.parseInt(num.charAt(i-1)+"");
				}
				
				if(sum1==sum2)
					equalsums.add(n1);
			}
			
			n1++;
		}
		if(!(equalsums.size()==0))
			for(Integer i : equalsums)
				System.out.print(i+" ");
		else
			System.out.println("No Numbers found with Equal Sum in the given range!!");
		scan.close();
	}
}
