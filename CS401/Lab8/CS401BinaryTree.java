import java.util.Stack;

/* 
 * A very simple binary tree class for Lab 8.  The intent is to get you
 * familiar with binary trees and their recursive properties.
 *
 * The tree is built from the left, going across a level.  Thus, at any
 * point in time, the tree is complete.
 *
 * The scaffolding below should aid you in finishing the lab.  Please
 * see the lecture (Lecture 8) associated with the lab.
 *
 * You need to provide code for the following methods:
 *
 *   add()
 *   size_p()      (Called from public method size())
 *   inorder_p()   (Called from public method inorder())
 *   postorder_p() (Called from public method postorder())
 *   preorder_p()  (Called from public method preorder())
 */

public class CS401BinaryTree<E>  {

   private int index;        /* This is the index of the next node to insert
                                in the tree. */
   private TreeNode<E> root; /* The absolute root of the tree. */

   /*
    * Default constructor. */
   public CS401BinaryTree()  {
      index = 0;
      root = null;
   }

   /* ------------------------------------------------------------------
    * Recursively calculates the size of the tree; i.e., the number of
    * elements in the binary tree. */
   public int size() { return size_p(root); }

   /*
    * Complete the following method to calculate size. */
   private int size_p(TreeNode<E> t)  {

      int num_nodes = 0;
      Stack<TreeNode<E>> nodeStack = new Stack<TreeNode<E>>();
      TreeNode<E> tmp;
      if(t != null) {
    	  nodeStack.push(t);
    	  while(!nodeStack.isEmpty()) {
    		  tmp = nodeStack.pop();
    		  num_nodes++;
    		  if(tmp.get_left() != null)
    			  nodeStack.push(tmp.get_left());
    		  if(tmp.get_right() != null)
    			  nodeStack.push(tmp.get_right());
    	  }
      }
      return num_nodes;
   }

   /* -------------------------------------------------------------------
    * Recursively does an inorder traversal of the tree
    */
   public void inorder() { inorder_p(root); }

   /*
    * Complete the following method to do an inorder traversal. */
   private void inorder_p(TreeNode<E> t)  {
      /*
       * Note: t.get_left() gives you the entire left subtree and
       *       t.get_right() gives you the right tree.
       */
	   if(t != null) {
	    	inorder_p(t.get_left());
	    	System.out.println(t);
	    	inorder_p(t.get_right());
	      }
      return;
   }

   /* --------------------------------------------------------------------
    * Recursively does an preorder traversal of the tree
    */
   public void preorder() { preorder_p(root); }

   /*
    * Complete the following method to do an preorder traversal. */
   private void preorder_p(TreeNode<E> t)  {
	   if(t != null) {
		   System.out.println(t);
		   preorder_p(t.get_left());
		   preorder_p(t.get_right());
	      }
      return;
   }

   /* -------------------------------------------------------------------
    * Recursively does an postorder traversal of the tree
    */
   public void postorder() { postorder_p(root); }

   /*
    * Complete the following method to do an postorder traversal. */
   private void postorder_p(TreeNode<E> t)  {
	   if(t != null) {
		   preorder_p(t.get_left());
		   preorder_p(t.get_right());
		   System.out.println(t);
	      }
      return;
   }

   /* -------------------------------------------------------------------
    * Adds a node to the binary tree.  Nodes are added from the left.
    * Thus, each level in the tree is fleshed out before a new level 
    * is started. */
   public void add(E info)
   {
      /*
       * Additions are done from the leftmost.  Thus, the first node
       * (index 0) is the root; the second node (index 1) is the left
       * child of the root, the third node (index 2) is the right child
       * of the root.  The fourth node (index 3) will be the left child
       * of the node at index 1.  And so on.
       *
       * To give you some context, the node having index 0 occurs at
       * level 0, the nodes having indexes 1 and 2 occur at level 1,
       * the nodes having index 3,4,5 occur at level 2. */

      TreeNode<E> node = new TreeNode<E>(info);

       if (index == 0)  {
           root = node;
       }
       else if (index == 1)  {
          root.left = node;
       }
       else if (index == 2)  {
          root.right = node;
       }
       else if (index == 3)  {
          root.left.left = node;
       }
       else if (index == 4)  {
          root.left.right = node;
       }
       else if (index == 5)  {
          root.right.left = node;
       }

       index++;
   }

   /* -------------------------------------------------------------------
    * Each node in the tree is an object of this type. */
   protected static class TreeNode<E>  {
      private TreeNode<E> left,
                          right;
      private E info;

      public TreeNode(E info) { left = right = null; this.info = info; }
      public TreeNode<E> get_left()  { return left; }
      public TreeNode<E> get_right() { return right;}
      
      public String toString() {
    	  return info.toString();
      }
   }

   /*
    * --------------------------------------------------------------------
    * Main driver
    */
   public static void main(String[] args)  {
      CS401BinaryTree<Integer> bt = new CS401BinaryTree<Integer>();

      bt.add(32);  
      bt.add(40);  
      bt.add(60);  
      bt.add(9);   
      bt.add(8);   
      bt.add(15); 
      /*
       * The above tree will look like:
       *                            32 
       *                           /  \
       *                          /    \
       *                        40      60
       *                       / \      /
       *                      /   \    /
       *                     9    8   15
       */
      System.out.println("The tree has " + bt.size() + " nodes.");
      System.out.println("\nPreOrder Traversal:");
      bt.preorder();
      System.out.println("\nInOrder Traversal:");
      bt.inorder();
      System.out.println("\nPostOrder Traversal:");
      bt.postorder();

      return;
   }
}