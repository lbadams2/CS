import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Network {
	
	private TreeMap<Vertex, TreeMap<Vertex, Double>> adjacencyMap = new TreeMap<Vertex, TreeMap<Vertex, Double>>();
	private static final String PROBLEM1 = "span";
	private static final String PROBLEM2 = "path";
	
	public static void main(String args[]) {
		if(args[0] == null || !(PROBLEM1.equals(args[0]) || PROBLEM2.equals(args[0])))
			throw new IllegalArgumentException("Input to program must be span or path.");
		new Network().run(args[0], args[1]);
	}
	
	public void run(String problem, String graphType) {
		Scanner sc = null;
		Vertex start = null;
		if(PROBLEM1.equals(problem)) {
			try {
				sc = new Scanner(new File("graph1.txt"));
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			Scanner keyboardScanner = new Scanner(System.in);
			while(true) {
				System.out.print("Enter graph topology file: ");
				String fileName = keyboardScanner.next();
				System.out.print("\nPlease enter a start vertex: ");
				start = new Vertex(keyboardScanner.next()); 
				try {
					sc = new Scanner(new File(fileName));
					keyboardScanner.close();
					break;
				} catch(FileNotFoundException e) {
					e.printStackTrace();
					System.out.print("\n");
				}
			}
		}
			
		String[] tokens;
		Vertex to, from;
		Double weight;
		while(sc.hasNext()) {
			tokens = sc.nextLine().split("\\s");
			from = new Vertex(tokens[0]);
			to = new Vertex(tokens[1]);
			weight = Double.parseDouble(tokens[2]);
			if(!adjacencyMap.containsKey(to))
				adjacencyMap.put(to, new TreeMap<Vertex, Double>());
			if(!adjacencyMap.containsKey(from))
				adjacencyMap.put(from, new TreeMap<Vertex, Double>());
			if("undirected".equals(graphType)) // problem 1 is undirected graph
				adjacencyMap.get(to).put(from, weight);
			adjacencyMap.get(from).put(to, weight);
		}
		if(PROBLEM1.equals(problem))
			minimumSpanningTree();
		else
			shortestPath(start);
	}
	
	public void minimumSpanningTree() {
		FairPQ<EdgeWeightTriple> pq = new FairPQ<EdgeWeightTriple>();
		System.out.println("Begin Spanning Tree Prim");
		TreeMap<Vertex, Double> neighbors;
		ArrayList<Vertex> tree = new ArrayList<Vertex>();
		double totalWeight;
		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> entry : adjacencyMap.entrySet()) {
			System.out.println("\tStart vertex: " + entry.getKey());
			totalWeight = 0;
			tree.add(entry.getKey());
			neighbors = entry.getValue();
			for(Map.Entry<Vertex, Double> neighborEntry : neighbors.entrySet()) 
				pq.add(new EdgeWeightTriple(entry.getKey(), neighborEntry.getKey(), neighborEntry.getValue()));
			while(tree.size() < adjacencyMap.size()) {
				EdgeWeightTriple leastWeight = pq.remove();
				Vertex leastWeightVertex = leastWeight.to;
				if(!tree.contains(leastWeightVertex)) {
					System.out.println("\t  Add edge <" + leastWeight.from + ", " + leastWeightVertex + ", " + leastWeight.weight + ">");
					tree.add(leastWeightVertex);
					totalWeight += leastWeight.weight;
					TreeMap<Vertex, Double> nextNeighbors = adjacencyMap.get(leastWeightVertex);
					for(Map.Entry<Vertex, Double> nextNeighborEntry : nextNeighbors.entrySet()) 
						if(!tree.contains(nextNeighborEntry.getKey()))
							pq.add(new EdgeWeightTriple(leastWeightVertex, nextNeighborEntry.getKey(), nextNeighborEntry.getValue()));
				} // end if which adds neighbors of next vertex to pq
			} // end while
			tree.clear();
			pq = new FairPQ<EdgeWeightTriple>();
			System.out.println("\t  Total cost: " + totalWeight + "\n");
		} // end for
		System.out.println("End Spanning Tree Prim");
	}
	
	public void shortestPath(Vertex start) {
		List<Vertex> orderedVertices = new ArrayList<Vertex>(adjacencyMap.size()); 
		double[] d = new double[adjacencyMap.size()];
		Vertex[] p = new Vertex[adjacencyMap.size()];
		Set<Vertex> s = new HashSet<Vertex>();
		Set<Vertex> vs = new HashSet<Vertex>();
		
		s.add(start);
		for(Map.Entry<Vertex, TreeMap<Vertex, Double>> entry : adjacencyMap.entrySet()) {
			orderedVertices.add(entry.getKey()); 
			if(!entry.getKey().equals(start))
				vs.add(entry.getKey());
		}
		Collections.sort(orderedVertices);
		Map<Vertex, Integer> vertexIndexMap = new HashMap<Vertex, Integer>();
		int i = 0;
		for(Vertex v : orderedVertices)
			vertexIndexMap.put(v, i++);
		TreeMap<Vertex, Double> startNeighbors = adjacencyMap.get(start);
		for(Vertex v : vs) {
			p[vertexIndexMap.get(v)] = start;
			for(Map.Entry<Vertex, Double> entry : startNeighbors.entrySet())
				if(entry.getKey().name.equals(v.name)) {
					d[vertexIndexMap.get(v)] = entry.getValue();
					break;
				}
				else
					d[vertexIndexMap.get(v)] = Double.MAX_VALUE;
		}
		
		while(!vs.isEmpty()) {
			double smallestWeightValue = Double.MAX_VALUE;
			Vertex smallestWeightVertex = null;
			for(Vertex v : vs) 
				if(d[vertexIndexMap.get(v)] < smallestWeightValue) {
					smallestWeightValue = d[vertexIndexMap.get(v)];
					smallestWeightVertex = v;
				}
			vs.remove(smallestWeightVertex);
			s.add(smallestWeightVertex);
			for(Map.Entry<Vertex, Double> entry : adjacencyMap.get(smallestWeightVertex).entrySet()) {
				int smallestVertexNeighborIndex = vertexIndexMap.get(entry.getKey());
				if(smallestWeightValue + entry.getValue() < d[smallestVertexNeighborIndex]) {
					d[smallestVertexNeighborIndex] = smallestWeightValue + entry.getValue();
					p[smallestVertexNeighborIndex] = smallestWeightVertex;
				}
			} // end for
		}// end while
		int startIndex = vertexIndexMap.get(start);
		int currentIndex;
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		System.out.println("Start vertex: " + start);
		for(Vertex v : orderedVertices) {
			currentIndex = vertexIndexMap.get(v);
			if(currentIndex == startIndex)
				continue;
			Vertex predecessor = p[currentIndex];
			int predIndex;
			while(predecessor != null) {
				path.addFirst(predecessor);
				predIndex = vertexIndexMap.get(predecessor);
				predecessor = p[predIndex];
			}
			path.addLast(v);
			System.out.print(start + " --> " + v + " : Cost is " + d[currentIndex] + ", Path is ");
			Iterator<Vertex> itr = path.iterator();
			while(itr.hasNext()) {
				System.out.print(itr.next());
				if(itr.hasNext())
					System.out.print("->");
				else
					System.out.print("\n");
			}
			path.clear();
		}
	}
	
	private static class Vertex implements Comparable<Vertex> {
		
		String name;
		public Vertex(String name) {
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o) {
			Vertex that = (Vertex)o;
			return name.equals(that.name);
		}
		
		@Override
		public int hashCode() {
			return name.hashCode();
		}

		@Override
		public int compareTo(Vertex arg0) {
			return name.compareTo(arg0.name);
		}
		
		public String toString() {
			return name;
		}
	}
	
	private static class EdgeWeightTriple implements Comparable<EdgeWeightTriple>{
		Vertex from, to;
		Double weight;
		
		public EdgeWeightTriple(Vertex from, Vertex to, Double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(EdgeWeightTriple arg0) {
			return weight.compareTo(arg0.weight);
		}
	}
}