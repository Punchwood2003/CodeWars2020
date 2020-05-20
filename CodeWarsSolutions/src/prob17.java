import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class prob17 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numNodes = scan.nextInt(); scan.nextLine();
		char[] nodes = new char[numNodes];
		String order = "";
		for(int i = 0; i < numNodes; i++) {
			nodes[i] = scan.nextLine().charAt(0);
			order += nodes[i];
		}
		
		
		Graph graph = new Graph();
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			char node1 = line.charAt(5);
			char node2 = line.charAt(28);
			String mode = line.substring(33);
			switch(mode) {
				case "air" : {
					graph.addNode(node1);
					graph.addNode(node2);
					break;
				} 
				default : {
					graph.addNode(node1);
					graph.addNode(node2);
					graph.addEdge(node1, 0, node2);
					break;
				}
			}
		}
		
		for(int i = 0; i < numNodes; i++) {
			Step step = new Step(nodes[i]);
			ArrayList<Step> temp = step.getAllNeighbors(graph);
			Step[] neighbors = null;
			if(temp != null) {
				neighbors = sortStep(order, temp.toArray(new Step[temp.size()]));
			}
			if(neighbors != null) {
				System.out.printf("City %c is neighbour to Cities ", nodes[i]);
				for(int j = 0; j < neighbors.length; j++) {
					if(j == neighbors.length-1) {
						System.out.print(neighbors[j].node + "\n");
					}
					else {
						System.out.print(neighbors[j].node + ",");
					}
				}
			}
			else {
				System.out.printf("City %c is remote and has no neighbours!\n", nodes[i]);
			}
		}
	}
	public static Step[] sortStep(String key, Step[] list) {
		boolean unsorted = true;
		while(unsorted) {
			unsorted = true;
			for(int i = 0; i < list.length-1; i++) {
				if(i+1 < list.length && key.indexOf(list[i+1].node) < key.indexOf(list[i].node)) {
					Step node1 = list[i];
					Step node2 = list[i+1];
					
					list[i] = node2;
					list[i+1] = node1;
					unsorted = false;
				}
			}
		}
		return list;
	}
}
class Step {
	public Character node;
	public boolean visited;

	public Step(Character node) {
		this.node = node;
		visited = false;
	}
	public Step(Character node, boolean t) {
		this.node = node;
		visited = true;
	}
	public Step moveTo(Character node, Graph graph) {
		Step newStep = new Step(node);
		return newStep;
	}
	public ArrayList<Step> getAllNeighbors(Graph graph) {
		Queue<Step> toSearch = new LinkedList<Step>(); 
		ArrayList<Step> list = new ArrayList<Step>();
        toSearch.add(new Step(this.node));
        while (!toSearch.isEmpty()) {
            Step currentNode = toSearch.poll();
            if(!list.contains(currentNode)) {
            	list.add(currentNode);
            	for(Step neighbor : currentNode.neighbors(graph)) {
                    if(!list.contains(neighbor)) {
                    	toSearch.add(neighbor);
                    }
                }
            }
        }
        return list;
	}
	public List<Step> neighbors(Graph graph) {
        List<Step> neighbors = new ArrayList<Step>();
        for(Character neighbor : graph.edges.get(this.node).keySet()) {
            neighbors.add(this.moveTo(neighbor, graph));
        }
        return neighbors;    
    }
	/*public ArrayList<Step> neighbors(Graph graph) {
		if(graph.edges.containsKey(this.node)) {
			ArrayList<Step> toSearch = new ArrayList<Step>();
			for(Character neighbor : graph.edges.get(this.node).keySet()) {
	            if(!toSearch.contains(new Step(neighbor))) { 
	            	toSearch.add(new Step(neighbor));
	            }
	        }
			toSearch = recursiveSearch(toSearch, new ArrayList<Step>(), this.node, graph);
			return toSearch;    
		}
		else {
			return null;
		}
	}*/
	/*public ArrayList<Step> recursiveSearch(ArrayList<Step> toSearch, ArrayList<Step> returnList, Character originalNode, Graph graph) {
		
		Step[] nodesVisited = toSearch.toArray(new Step[toSearch.size()]);
		boolean allSearched = true;
		for(int i = 0; i < nodesVisited.length; i++) {
			if(nodesVisited[i].visited == false) {
				allSearched = false;
				i = nodesVisited.length;
			}
		}
		if(allSearched) {
			return returnList;
		}
		else {
			for(int i = 0; i < nodesVisited.length; i++) {
				System.out.println(nodesVisited.toString() );
				if(!nodesVisited[i].visited && !nodesVisited[i].node.equals(originalNode)) {
					toSearch.get(i).visited = true;
					if(!returnList.contains(new Step(nodesVisited[i].node, true))) {
						returnList.add(new Step(nodesVisited[i].node, true));
					}
					ArrayList<Step> neighbors = new ArrayList<Step>();
					for(Character neighbor : graph.edges.get(nodesVisited[i].node).keySet()) {
			            neighbors.add(new Step(neighbor));
			        }
					returnList = recursiveSearch(neighbors, returnList, originalNode, graph);
				}
			}
		}
		return returnList;
	}*/
	public String toString() {
		return "" + this.node + " " + this.visited;
	}
}
class Graph {
    public Set<Character> nodes;
    public Map<Character, Map<Character, Integer>> edges;
    public Graph() {
        nodes = new HashSet<Character>();
        edges = new HashMap<Character, Map<Character, Integer>>();
    }
    public void addNode(Character node) {
        nodes.add(node);
    }
    public void addEdge(Character nodeA, Integer weight, Character nodeB) {
        if(!edges.containsKey(nodeA)) {
            edges.put(nodeA, new HashMap<Character, Integer>());
        }
        edges.get(nodeA).put(nodeB, weight);
        if(!edges.containsKey(nodeB)) {
            edges.put(nodeB, new HashMap<Character, Integer>());
        }
        edges.get(nodeB).put(nodeA, weight);
    }
}
