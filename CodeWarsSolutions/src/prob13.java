import java.util.ArrayList;
import java.util.Scanner;

public class prob13
{
	public static ArrayList<Item> items;
	public static int numItems;
	public static int maxCost;
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int yen = scan.nextInt();
		int num = scan.nextInt();
		numItems = num;
		items = new ArrayList<Item>();
		while(num>0)
		{
			String n = scan.next();
			int c = scan.nextInt();
			items.add(new Item(n,c));
			num--;
		}
		
		maxCost = 0;
		
		for(int i = 0; i < items.size(); i++)
			 knapsack(yen, i, 0, 0);
		
		int count = 0;
		for(Item i : items)
		{
			System.out.println(i);
			if(!i.getAfford())
				count++;
		}
		
		if(count == numItems)
			System.out.println("I need more Yen!");
		
		System.out.println(yen-maxCost);
		
		scan.close();
	}

	public static int knapsack(int yen, int index, int currentCost, int currentItem) {
		currentCost += items.get(index).getCost();
		if(currentCost > yen) 
		{
			items.get(index).setAfford(false);
			return currentItem;
		}
		else 
		{
			 currentItem += items.get(index).getCost();
			 index++;
			 for(int i = index; i < items.size(); i++) 
			 {
				 int iterationCost = knapsack(yen, i, currentCost, currentItem);
				 if(iterationCost > maxCost)
				 {
					 maxCost = iterationCost;
				 }
			 }
		}
		return currentItem;
	}
	
}

class Item
{
	private String name;
	private int cost;
	private boolean afford;
	
	public Item(String n, int c)
	{
		name = n;
		cost = c;
		afford = true;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public boolean getAfford()
	{
		return afford;
	}
	
	public void setAfford(boolean a)
	{
		afford = a;
	}
	
	public String toString()
	{
		if(afford)
			return "I can afford "+getName();
		else
			return "I can't afford "+getName();
	}
	
}