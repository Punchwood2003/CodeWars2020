import java.util.Scanner;

public class prob06
{
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);

		while(s.hasNext())
		{
			int Arabic = s.nextInt();
			String Martian = "";
			if(Arabic / 100 >= 1)
			{
				if(Arabic == 9)
					Martian += "BR";
				else if(Arabic /100 ==4)
					Martian += "BG";

				else if(Arabic == 10)
					Martian += "R";
				else
				{
					if(Arabic / 100>=5)
					{
						Martian += "G";
						for(int i = 0 ;i < (Arabic /100)-5; i++)
							Martian += "B";
					}
					else
					{
						for(int i = 0 ; i < (Arabic /100);i++)
							Martian += "B";
					}
				}
				Arabic%=100;
			}

			if(Arabic / 10 >= 1)
			{
				if(Arabic / 10 == 9)
					Martian += "ZB";
				else if(Arabic / 10 == 4)
					Martian += "ZP";
				else
				{
					if(Arabic / 10 >= 5)
					{
						Martian += "P";
						for(int i = 0 ;i < (Arabic /10)-5; i++)
							Martian += "Z";
					}
					else
					{   
						for(int i = 0 ; i < (Arabic /10);i++)
							Martian += "Z";
					}
				}
				Arabic%=10;
			}

			if(Arabic >= 1)
			{
				if(Arabic == 9)
					Martian += "BK";
				else if(Arabic  == 4)
					Martian += "BW";
				else
				{
					if(Arabic >= 5)
					{
						Martian += "W";
						for(int i = 0 ;i < Arabic-5; i++)
							Martian += "B";
					}
					else
					{   
						for(int i = 0 ; i < Arabic;i++)
							Martian += "B";
					}
				}
				Arabic%=10;
			}
			System.out.println(Martian);
		}
	}


}

