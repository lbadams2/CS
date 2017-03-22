import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class Chord_network {
	
	private Chord_node[] nodeArray;
	private static final String QUIT = "000000";
	private LinkedList<Integer> indexes; // no collisions in this data set
	private Random random;
	private ArrayList<Integer> totalRedirections;
	int redirections;
	
	public static void main(String[] args) {
		new Chord_network().run();
	}
	
	public Chord_network() {
		int p = (int)Math.pow(2, Chord_node.m);
		nodeArray = new Chord_node[p];
		indexes = new LinkedList<Integer>();
		random = new Random();
		totalRedirections = new ArrayList<Integer>();
	}
	
	public void run() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("nodeAddress.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int hash = 0;
		while(sc.hasNext()) {
			hash = h(sc.nextLine().trim());
			indexes.add(hash);
			nodeArray[hash] = new Chord_node(hash);
		}
		setSuccessorPredecessor();
		setFingerTables();
		getUserInput();
	}
	
	private void getUserInput() {
		Scanner sc = new Scanner(System.in);
		String input = null;
		int rand = 0;
		double sum = 0;
		while(true) {
			System.out.print("Enter document ID: ");
			input = sc.nextLine().trim();
			if(QUIT.equals(input))
				break;
			int docHash = h(input);
			System.out.println("\nHash of document ID is: K" + docHash);
			rand = getRandomNode();
			System.out.println("Picking random node ... N" + rand);
			redirections = 1;
			search(rand, docHash);
			System.out.println("Number of redirections: " + redirections);
			totalRedirections.add(redirections);
			System.out.print("\n\n");
		}
		for(int g : totalRedirections)
			sum += g;
		double size = totalRedirections.size();
		System.out.printf("\nAverage number of redirections: %.2f", sum/size);
		sc.close();
	}
	
	private int getRandomNode() {
		int rand = random.nextInt(indexes.size());
		int i = 0;
		for(Integer j : indexes) {
			if(i == rand)
				return j;
			i++;
		}
		throw new RuntimeException("Problem generating random node");
	}
	
	//109
	private void search(int node, int docHash) {
		Chord_node n = nodeArray[node];
		
		if(docHash == node || (node > docHash && n.predecessor.node_index < docHash)) {
			System.out.println(n.node_name + " has K" + docHash);
			return;
		}
		
		if(n.predecessor.node_index < docHash && n.predecessor.node_index > node && node < docHash) { // docHash greater than greatest node 
			System.out.println(n.node_name + " has K" + docHash);
			return;
		}
		
		if(docHash > node && node > n.successor.node_index) {
			System.out.println("N" + n.node_index + " redirected to " + n.successor.node_name);
			System.out.println(n.successor.node_name + " has K" + docHash);
			redirections++;
			return;
		}
		
		int closest = -1;
		for(int i = 0; i < n.finger_table.length; i++) 
			if((docHash - n.finger_table[i].node_index) < (docHash - closest) && n.finger_table[i].node_index <= docHash) 
				closest = n.finger_table[i].node_index;
		
		// every node in finger table was greater than the document key
		if(closest == -1 && n.predecessor.node_index >= docHash) 
			closest = n.predecessor.node_index;
		else if(closest == -1 && n.predecessor.node_index < docHash && node > docHash) {
			System.out.println(n.node_name + " has K" + docHash);
			return;
		}
		
		if(node < docHash && docHash - node < docHash - closest ) {
			int i = 0;
			while(nodeArray[node].finger_table[i].node_index < docHash)
				i++;
			System.out.println("N" + n.node_index + " redirected to " + nodeArray[node].finger_table[i].node_name);
			System.out.println(nodeArray[node].finger_table[i].node_name + " has K" + docHash);
			redirections++;
			return;
		}
		
		redirections++;
		System.out.println("N" + n.node_index + " redirected to " + nodeArray[closest].node_name);
		search(closest, docHash);
	}
	
	private void setSuccessorPredecessor() {
		Chord_node prev = null;
		Chord_node first = null;
		int count = 0;
		for(int i = 0; i < nodeArray.length; i++) {
			if(nodeArray[i] != null) {
				if(count == 0)
					first = nodeArray[i];
				nodeArray[i].predecessor = prev;
				if(prev != null)
					prev.successor = nodeArray[i];
				prev = nodeArray[i];
				count++;
			}
		}
		first.predecessor = prev;
		prev.successor = first;
	}
	
	private void setFingerTables() {
		Chord_node successor = null;
		Chord_node current = null;
		Collections.sort(indexes);
		int lastIndex = 0;
		int lastRange = 0;
		for(int i = 0; i < nodeArray.length; i++) {
			current = nodeArray[i];
			if(current != null) {
				successor = current.successor;
				for(int j = 0; j < Chord_node.m; j++) {
					int range = (i + (int)Math.pow(2, j)) % (int)Math.pow(2, Chord_node.m);
					if(lastRange > range)
						successor = nodeArray[indexes.getFirst()]; // start over
					while(range > successor.node_index) {
						lastIndex = successor.node_index;
						successor = successor.successor;
						if(lastIndex > successor.node_index)
							break;
					}
					current.finger_table[j] = successor;
					lastRange = range;
				}
			}
		}
	}
	
	public int h(String s)  {
	      long h = 0;
	      int len = s.length();

	      for (int i = 0; i < len; i++)  {
	        h = 31 * h + s.charAt(i);
	      }

	      return (int) Math.abs(h % 256);  // In case of overflow and negative numbers
	}
	
	private static class Chord_node implements Comparable<Chord_node> {

		   private static int m = 8;

		   Chord_node successor;    // Node's successor
		   Chord_node predecessor;  // Node's predecessor
		   Chord_node[] finger_table;  // Finger table of at most m entries
		   int node_index;     // Integer index of this node [0 - 255]
		   String node_name;   // Name of node, e.g., N20 or N190
		   
		   public Chord_node(int index) {
			   node_index = index;
			   node_name = "N" + index;
			   finger_table = new Chord_node[m];
		   }
		   
		   public String toString() {
			   return node_name + " Predecessor: " + predecessor.node_name + " Successor: " + successor.node_name;
		   }

		@Override
		public int compareTo(Chord_node o) {
			Integer thisIndex = (Integer)node_index;
			Integer thatIndex = (Integer)o.node_index;
			return thisIndex.compareTo(thatIndex);
		}
	}
}