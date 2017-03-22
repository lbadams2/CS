import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.Result;
import static org.junit.runner.JUnitCore.runClasses;
import java.util.*;

public class BinarySearchTreeTest extends BinarySearchTree
{
    public static void main(String[ ] args)
    {  
        Result result = runClasses (BinarySearchTreeTest.class);
        System.out.println ("Tests run = " + result.getRunCount() +
                            "\nTests failed = " + result.getFailures());
    } // method main

    protected BinarySearchTree<String> tree;          
                       
    @Before    
    public void RunBeforeEachTest()
    {
        tree = new BinarySearchTree<String>();      
    } // method RunBeforeEachTest 

/*
     * 							m
     * 						  /   \
     * 						 /	   \
     * 					   g         s
     * 					 / \         / \
     * 					/	\       /   \
     * 				   d     j      p     v
     * 				 / \    / \    / \    / \
     * 				b   f  h   l  o   q  t   x
     * 
     * 			n = 15
     * 			height = 3
     * 			floor(lg(15)) = 3
     */
    @Test
    public void testHeight() 
    {
    	tree.add("m");
    	tree.add("g");
    	tree.add("s");
    	tree.add("d");
    	tree.add("j");
    	tree.add("p");
    	tree.add("v");
    	tree.add("b");
    	tree.add("f");
    	tree.add("h");
    	tree.add("l");
    	tree.add("o");
    	tree.add("q");
    	tree.add("t");
    	tree.add("x");
    	Assert.assertEquals(3, tree.height());
    }
    
    @Test
    public void testHeight2() 
    {
    	tree.add("m");
    	tree.add("g");
    	tree.add("s");
    	tree.add("d");
    	tree.add("j");
    	tree.add("p");
    	tree.add("v");
    	tree.add("b");
    	tree.add("f");
    	tree.add("h");
    	tree.add("l");
    	Assert.assertEquals(3, tree.height());
    }
    
    @Test
    public void testHeight3() 
    {
    	tree.add("m");
    	tree.add("g");
    	tree.add("s");
    	tree.add("d");
    	tree.add("j");
    	tree.add("p");
    	tree.add("v");
    	tree.add("o");
    	tree.add("q");
    	tree.add("t");
    	tree.add("x");
    	Assert.assertEquals(3, tree.height());
    }
	}