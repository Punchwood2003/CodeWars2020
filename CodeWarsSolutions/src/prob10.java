import java.util.ArrayList;
import java.util.Scanner;

public class prob10
{
	public static void main(String[]args)
	{
		Scanner s =new Scanner(System.in);
		String sentenceString = s.nextLine();
		String[] sentence = sentenceString.split(" ");
		String[] sentenceedit = sentenceString.split(" ");
		ArrayList<String> NOUNS = new ArrayList<String>();
		ArrayList<String> ADVERBS = new ArrayList<String>();
		ArrayList<String> VERBS = new ArrayList<String>();
		ArrayList<String> ADJECTIVES = new ArrayList<String>();
		s.next();
		String word = s.next();
		while(!word.equals("ADVERBS"))
		{
			NOUNS.add(word);
			word = s.next();
		}
		word = s.next();
		while(!word.equals("VERBS"))
		{
			ADVERBS.add(word);
			word = s.next();
		}
		word = s.next();
		while(!word.equals("ADJECTIVE"))
		{
			VERBS.add(word);
			word = s.next();
		}
		word = s.next();
		while(!word.contentEquals("END"))
		{
			ADJECTIVES.add(word);
			word = s.next();
		}
//		System.out.println(NOUNS);
//		System.out.println(ADVERBS);
//		System.out.println(VERBS);
//		System.out.println(ADJECTIVES);
		for(int i = 0 ; i < 2; i++)
		{
			for(int ii = 0 ; ii < sentence.length; ii++)
			{
				if(sentence[ii].contentEquals("[N]"))
				{
					sentenceedit[ii] = NOUNS.get(0);
					NOUNS.remove(0);
				}
				else if(sentence[ii].contentEquals("[AV]"))
				{
					sentenceedit[ii] = ADVERBS.get(0);
					ADVERBS.remove(0);
				}
				else if(sentence[ii].contentEquals("[V]"))
				{
					sentenceedit[ii] = VERBS.get(0);
					VERBS.remove(0);
				}
				else if(sentence[ii].contentEquals("[AJ]"))
				{
					sentenceedit[ii] = ADJECTIVES.get(0);
					ADJECTIVES.remove(0);
				}
			}
			for(int iii = 0 ; iii < sentence.length; iii++)
			{
				System.out.print(sentenceedit[iii] + " ");
			}
			System.out.print("\n");
		}
		


	}
}
