import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.Collections;

/* 
 * Example of a graph in Java.  This program will print out the graph, 
 * listing the vertices and the edges.  The graph used here corresponds
 * to the graph studied in Lecture 13 for DFS and BFS, plus the "I"
 * vertex as described in the end of the lecture. */

public class CS401Graph implements Iterable<CS401Graph.Vertex>
{
	
   Vector<Vertex> graph = new Vector<Vertex>();
   String start;
   
   public static void main(String[] args)
   {
	  if(!("depth".equals(args[0]) || "breadth".equals(args[0])) || args[0] == null)
		  throw new IllegalArgumentException("Argument to program must be either breadth or depth");
      new CS401Graph().run(args[0]);

      return;
   }

   public void run(String traversal)
   {
      /*
       *                
       */
      int inf = Integer.MAX_VALUE;
      int max_row, max_col;
                      /*        A    B    C    D    E    F    G    H    I */
      int adj[][] = { /* A */ { inf, 8,   inf, 10,  inf, inf, 12,  inf, inf },
                      /* B */ { 8,   inf, inf, inf, 12,  18,  inf, inf, inf },
                      /* C */ { inf, inf, inf, inf, inf, 2,   inf, 10,  inf },
                      /* D */ { 10,  inf, inf, inf, inf, 8,   inf, inf, inf },
                      /* E */ { inf, 12,  inf, inf, inf, inf, 24,  inf, inf },
                      /* F */ { inf, 18,  2,   8,   inf, inf, inf, inf, inf },
                      /* G */ { 12,  inf, inf, inf, 24,  inf, inf, inf, inf },
                      /* H */ { inf, inf, 10,  inf, inf, inf, inf, inf, inf },
                      /* I */ { inf, inf, inf, inf, inf, inf, inf, 3,   inf }
      };

      max_row  = max_col = 9;

      graph.add(new Vertex("A"));
      graph.add(new Vertex("B"));
      graph.add(new Vertex("C"));
      graph.add(new Vertex("D"));
      graph.add(new Vertex("E"));
      graph.add(new Vertex("F"));
      graph.add(new Vertex("G"));
      graph.add(new Vertex("H"));
      graph.add(new Vertex("I"));

      for (int i = 0; i < max_row; i++)  {
	 // Go through each row of the adjacency matrix collecting neighbours
	  Vertex v = graph.elementAt(i);
	  for (int j = 0; j < max_col; j++)  {
	      if (adj[i][j] != inf)  {
 	         v.add_edge(new Edge(i, j, adj[i][j]));
	      }
	  }
	  v.order_edges(); /* Order (sort) the neighbours for this vertex */
      }                    /* based on cost (lowest to highest)           */
      
      Scanner sc = null;
      while(true) {
    	  try {
    		  System.out.print("Enter starting vertex or q to quit: ");
    		  sc = new Scanner(System.in);
    		  String name = sc.nextLine();
    		  if(name.equals("q"))
    			  break;
    		  if(Vertex.get_vertex_index(name) == -1) {
    			  System.out.println("Vertex not in graph");
    			  continue;
    		  }
    		  start = name;
    		  System.out.print("\nFor starting vertex " + name + ", the " + traversal + "-first traversal produces: ");
    		  if(traversal.equals("breadth")) {
	    		  Iterator<Vertex> itr = iterator();
	    		  Vertex v;
	    		  while(itr.hasNext()) {
	    			  v = itr.next();
	    			  System.out.print(v.name);
	    			  if(itr.hasNext())
	    				  System.out.print(", ");
	    		  }
	    		  System.out.println("\n");
    		  }
    		  else {
    			  for(Vertex v : graph)
    				  v.reachable = false;
	    		  DFS(name);
	    		  System.out.println("\n");
    		  }
    	  } catch(Exception e) {
    		  e.printStackTrace();
    		  sc.close();
    		  break;
    	  }
      }
      sc.close();
   }
   
   public void DFS(String start) {
	   Vertex v = getVertex(start);
	   Vertex neighbor;
	   v.reachable = true;
	   System.out.print(v.name + ", ");
	   for(Edge e : v.edgelist) {
		   neighbor = graph.elementAt(e.destination);
		   if(!neighbor.reachable)
			   DFS(neighbor.name);
	   }
   }
   
   public Vertex getVertex(String name) {
	   for(Vertex v : graph) 
		   if(v.match_name(name))
			   return v;
	   return null;
   }
   
	@Override
	public Iterator<Vertex> iterator() {
		return new BreadthFirstIterator(start);
	}
   
   private class BreadthFirstIterator implements Iterator<Vertex>{
	   Vertex next;
	   CS401QueueLinkedListImpl<Vertex> queue = new CS401QueueLinkedListImpl<Vertex>();
	   
	   public BreadthFirstIterator(String start) {
		   for(Vertex v : graph) {
			   if(v.match_name(start)) {
				   v.reachable = true;
				   queue.add(v);
				   continue;
			   }
			   v.reachable = false;
		   }
	   }
	   
	   public boolean hasNext() {
		   return !queue.is_empty();
	   }
	
	   public Vertex next() {
		   next = queue.remove();
		   Vertex neighbor;
		   for(Edge e : next.edgelist) {
			   neighbor = graph.elementAt(e.destination);
			   if(!neighbor.reachable) {
				   neighbor.reachable = true;
				   queue.add(neighbor);
			   }
		   }
		   return next;
	   }
   }
   
  //--------------------------------------------------------------------
   static class Vertex {
      private String name;
      private int id;    /* Integral id of vertex: [0, n-1] */
      private int state; /* 0: undiscovered; 1: discovered; 2: visited */
      private int pred;  /* Predecessor node.  Unused here */
      private boolean reachable;
      private Vector<Edge> edgelist;

      private static int counter = 0;

      public Vertex(String name)  {
         this.name = name;
	 state = 0;
         pred = -1;
	 id = counter++;
	 edgelist = null;
      }

      public void order_edges()  {
	  Collections.sort(edgelist);
      }

      public String toString()  {
	  StringBuffer s = new StringBuffer();

	  s.append("Vertex: " + name + "(" + id + ")");
	  s.append(" (" + state + ", " + pred + ")\n");
	  s.append("  Neighbours: ");
	  for (Edge e : edgelist)  {
	      s.append(e);
	      s.append(" ");
	  }
	 
	  return s.toString();
      }

      public void add_edge(Edge e)  {
	   if (edgelist == null)  {
	       edgelist = new Vector<Edge>();
	   }
	   edgelist.add(e);
      }

      public boolean match_name(String name)  {
	  if (this.name.equals(name))
	      return true;
	  else
	      return false;
      }

      public void visited()  {
	  state = 2;
      }

      public String get_name()  {
	  return name;
      }

      public static int get_vertex_index(String name)  {
         int v = -1;

         switch(name)  {
 	    case "A": v = 0; break;
 	    case "B": v = 1; break;
 	    case "C": v = 2; break;
 	    case "D": v = 3; break;
 	    case "E": v = 4; break;
 	    case "F": v = 5; break;
 	    case "G": v = 6; break;
 	    case "H": v = 7; break;
 	    case "I": v = 8; break;
	    default: System.out.println("get_vertex_index: invalid name"); break;
	 }
	 return v;
      }

      public static String get_vertex_name(int index)  {
         String v = "null";
	 switch(index)  {
	    case 0: v = "A"; break;
	    case 1: v = "B"; break;
	    case 2: v = "C"; break;
	    case 3: v = "D"; break;
	    case 4: v = "E"; break;
	    case 5: v = "F"; break;
	    case 6: v = "G"; break;
	    case 7: v = "H"; break;
	    case 8: v = "I"; break;
	    default: System.out.println("get_vertex_name: invalid index"); break;
	 }
	 return v;
      }
   } // Class Vertex

   static class Edge implements Comparable<Edge>  {
      private int source;
      private int destination;
      private int cost;

      public Edge(int s, int d, int c)  {
	 source = s; destination = d; cost = c;
      }

      public String toString()  {
	  StringBuffer s = new StringBuffer();

	  s.append("(" + Vertex.get_vertex_name(source) + ", " + 
                   Vertex.get_vertex_name(destination) + ", " + cost + ")");
	  return s.toString();                   
      }

      public int compareTo(Edge o)  {
          if (this.cost < o.cost)
              return -1;
          else if (this.cost > o.cost)
              return 1;
          else
              return 0;
      }

   } // Class Edge

}