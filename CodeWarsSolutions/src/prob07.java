import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class prob07
{
	public static void main(String[] args)
	{
		List<Color> colors = new ArrayList<>();

		colors.add(new Color("RED", "YELLOW", "ORANGE"));
		colors.add(new Color("RED", "BLUE", "PURPLE"));
		colors.add(new Color("YELLOW", "BLUE", "GREEN"));

		colors.add(new Color("GREEN", "BLACK", "DARK GREEN"));
		colors.add(new Color("PURPLE", "BLACK", "DARK PURPLE"));
		colors.add(new Color("ORANGE", "BLACK", "DARK ORANGE"));
		colors.add(new Color("GREEN", "WHITE", "LIGHT GREEN"));
		colors.add(new Color("PURPLE", "WHITE", "LIGHT PURPLE"));
		colors.add(new Color("ORANGE", "WHITE", "LIGHT ORANGE"));

		colors.add(new Color("BLUE", "BLACK", "DARK BLUE"));
		colors.add(new Color("RED", "BLACK", "DARK RED"));
		colors.add(new Color("YELLOW", "BLACK", "DARK YELLOW"));
		colors.add(new Color("BLUE", "WHITE", "LIGHT BLUE"));
		colors.add(new Color("RED", "WHITE", "LIGHT RED"));
		colors.add(new Color("YELLOW", "WHITE", "LIGHT YELLOW"));

		Scanner s = new Scanner(System.in);
		while (s.hasNext())
		{
			String color1 = s.next();
			String color2 = s.next();

			for (Color c : colors)
			{
				String result = c.getResult(color1, color2);
				if (result != null)
				{
					System.out.println(result);
					break;
				}
			}

		}
	}

	static class Color
	{
		private String color1;
		private String color2;
		private String result;

		public Color(String c1, String c2, String r)
		{
			color1 = c1;
			color2 = c2;
			result = r;
		}

		public String getResult(String c1, String c2)
		{
			if (c1.equals(c2)) {
				return c1;
			}
			
			if (c1.equals(color1) && c2.equals(color2) || c1.equals(color2) && c2.equals(color1))
				return result;
			else
				return null;
		}
	}
}
