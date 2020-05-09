// author@MatthewSheldon
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class prob20 {
	
	public static int numPepperoni = 0, numRedPeppers = 0, 
					   numPineapple = 0, numOlives = 0, 
					   numSardines = 0, numOnion = 0, 
					   numSausage = 0, numHam = 0;
	public static void main(String[] args)  {
		Scanner scan = new Scanner(System.in);
		
		ArrayList<Order> order = new ArrayList<Order>();
		numPepperoni = 0; numRedPeppers = 0; 
		numPineapple = 0; numOlives = 0; 
		numSardines = 0; numOnion = 0; 
		numSausage = 0; numHam = 0;
		
		
		while(scan.hasNextLine()) {
			String[] line = scan.nextLine().split(" ");
			int numPies = Integer.parseInt(line[0]);
			double multiplier = (double) numPies;
			for(int i = 1; i < line.length; i++) {
				if(line[i].contains("/")) {
					multiplier = (double) numPies / Integer.parseInt(line[i].substring(2));
				}
				else if (!line[i].equals("&")) {
					if(line[i].equals("Red")) {
						order.add(getOrder(line[i] + " " + line[i+1], multiplier));
						i++;
					}
					else {
						order.add(getOrder(line[i], multiplier));
					}
				}
			}
		}
		
		calculateTotals(order);
		
		printTotals();
		scan.close();
		
	}
	public static void printTotals() {
		System.out.printf("%s: %d%n", "Pepperoni", numPepperoni);
		System.out.printf("%s: %d%n", "Red Peppers", numRedPeppers);
		System.out.printf("%s: %d%n", "Pineapple", numPineapple);
		System.out.printf("%s: %d%n", "Olives", numOlives);
		System.out.printf("%s: %d%n", "Sardines", numSardines);
		System.out.printf("%s: %d%n", "Onion", numOnion);
		System.out.printf("%s: %d%n", "Sausage", numSausage);
		System.out.printf("%s: %d%n", "Ham", numHam);
	} 
	public static void calculateTotals(ArrayList<Order> order) {
		for(int i = 0; i < order.size(); i++) {
			ArrayList<Topping> tempOrder = order.get(i).getOrder();
			for(int j = 0; j < tempOrder.size(); j++) {
				Topping tempTopping = tempOrder.get(j);
				String toppingName = tempTopping.getTopping();
				switch(toppingName) {
					case "Pepperoni" : {
						numPepperoni += tempTopping.getNumToppings();
						break;
					}
					case "Red Peppers" : {
						numRedPeppers += tempTopping.getNumToppings();
						break;
					}
					case "Pineapple" : {
						numPineapple += tempTopping.getNumToppings();
						break;
					}
					case "Olives" : {
						numOlives += tempTopping.getNumToppings();
						break;
					}
					case "Sardines" : {
						numSardines += tempTopping.getNumToppings();
						break;
					}
					case "Onion" : {
						numOnion += tempTopping.getNumToppings();
						break;
					}
					case "Sausage" : {
						numSausage += tempTopping.getNumToppings();
						break;
					}
					case "Ham" : {
						numHam += tempTopping.getNumToppings();
						break;
					}
				}
			}
		}
	}
	public static Order getOrder(String temp, double multiplier) {
		ArrayList<Topping> orderToppings = new ArrayList<Topping>();
		switch(temp) {
			case "Pepperoni" : {
				Topping tempTopping = new Topping("Pepperoni", (int) (multiplier * 32.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Red Peppers" : {
				Topping tempTopping = new Topping("Red Peppers", (int) (multiplier * 16.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Pineapple" : {
				Topping tempTopping = new Topping("Pineapple", (int) (multiplier * 84.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Olive" : {
				Topping tempTopping = new Topping("Olives", (int) (multiplier * 20.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Sardines" : {
				Topping tempTopping = new Topping("Sardines", (int) (multiplier * 12.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Onion" : {
				Topping tempTopping = new Topping("Onion", (int) (multiplier * 28.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Sausage" : {
				Topping tempTopping = new Topping("Sausage", (int) (multiplier * 40.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Ham" : {
				Topping tempTopping = new Topping("Ham", (int) (multiplier * 36.0));
				orderToppings.add(tempTopping);
				break;
			}
			case "Cheese" : {
				Topping tempTopping = new Topping("Cheese", 0);
				orderToppings.add(tempTopping);
				break;
			}
			default : {
				orderToppings = getCombo(temp, multiplier);
				break;
			}
		}
		return new Order(orderToppings);
	}
	public static ArrayList<Topping> getCombo(String combo, double multiplier) {
		ArrayList<Topping> comboToppings = new ArrayList<Topping>();
		switch(combo) {
			case "Hawaiian" : {
				comboToppings.add(new Topping("Pineapple", (int) (multiplier * 84.0)));
				comboToppings.add(new Topping("Ham", (int) (multiplier * 36.0)));
				break;
			}
			case "Combo" : {
				comboToppings.add(new Topping("Red Peppers", (int) (multiplier * 16.0)));
				comboToppings.add(new Topping("Olives", (int) (multiplier * 20.0)));
				comboToppings.add(new Topping("Onion", (int) (multiplier * 28.0)));
				comboToppings.add(new Topping("Sausage", (int) (multiplier * 40.0)));
				break;
			}
			case "Fishaster" : {
				comboToppings.add(new Topping("Sardines", (int) (multiplier * 12.0)));
				comboToppings.add(new Topping("Onion", (int) (multiplier * 28.0)));
				break;
			}
			case "Meat-Lovers" : {
				comboToppings.add(new Topping("Pepperoni", (int) (multiplier * 32.0)));
				comboToppings.add(new Topping("Sausage", (int) (multiplier * 40.0)));
				comboToppings.add(new Topping("Ham", (int) (multiplier * 36.0)));
				break;
			}
			case "Cheese" : {
				comboToppings.add(new Topping("Cheese", 0));
				break;
			}
		}
		return comboToppings;
	}
}
class Order {
	private ArrayList<Topping> toppings;
	public Order(ArrayList<Topping> list) {
		this.toppings = list;
	}
	public ArrayList<Topping> getOrder() {
		return toppings;
	}
	
}
class Topping {
	
	private String topping;
	private int numToppings; 
	public Topping(String t, int n) {
		this.topping = t;
		this.numToppings = n;
	}
	public int getNumToppings() {
		return numToppings;
	}
	public String getTopping() {
		return topping;
	}
	public String toString() {
		return topping + " " + numToppings;
	}
}
