import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class prob15
{
	public static void main(String[] arg)
	{
		Scanner s = new Scanner(System.in);
		String numline = s.nextLine();
		String[] numbers = numline.split(" ");
		while (!numbers[0].equals("0") || !numbers[1].equals("0"))
		{
			String sentenceone = s.nextLine();
			String sentencetwo = s.nextLine();

			String sentences = sentenceone + " " + sentencetwo;
			String[] words1 = sentences.split(" ");

			TreeMap<String, Integer> repeatingwords = new TreeMap<String, Integer>();
			ArrayList<String> repeat = new ArrayList<String>();
			for (int i = 0; i < words1.length; i++)
			{
				String word = words1[i].toLowerCase();
				if (repeatingwords.containsKey(word))
					repeatingwords.put(word, repeatingwords.get(word) + 1);
				else
					repeatingwords.put(word, 1);
			}

			for (Entry<String, Integer> entry : repeatingwords.entrySet())
			{

				if (entry.getValue() > 1)
				{
					repeat.add(entry.getKey());
				}
			}

			System.out.println(sentenceone);
			System.out.println(sentencetwo);
			System.out.print(repeat.size() + " ");
			for (int i = 0; i < repeat.size(); i++)
			{
				System.out.print(repeat.get(i) + " ");
			}
			System.out.println();

			numline = s.nextLine();
			numbers = numline.split(" ");
		}

	}
}
