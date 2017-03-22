
public class FibonacciUser {
	
	public static void main(String[] args) {

			FibonacciUser f = new FibonacciUser();
			f.run(3);
			System.out.println("");
			f.run(7);
			System.out.println("");
			f.run(11);
			System.out.println("");
			f.run(15);
			System.out.println("");
			f.run(19);
	} // answer is b, c (n) = 2*f (n) - 1
	
	public void run(int n) {
		fib(n);
	}
	
	  public static void fib (int n)  
	  {
	      final int MAX_N = 92;
	      long startTime, finishTime, elapsedTime, result;
	      final String ERROR_MESSAGE = "\nThe number entered must be " +
	            "greater than 0 and at most " + MAX_N + ".";

	       if (n <= 0 || n > MAX_N)
	            throw new IllegalArgumentException (ERROR_MESSAGE);
	       startTime = System.nanoTime();
	       result = f (n);
	       finishTime = System.nanoTime();
	       elapsedTime = finishTime - startTime;
	       System.out.println("Slow recursion elapsed time " + elapsedTime + " f(" + n + ")=" + result );
	       
	       startTime = System.nanoTime();
	       result = efficient(n, 1, 0);
	       finishTime = System.nanoTime();
	       elapsedTime = finishTime - startTime;
	       System.out.println("Efficient recursion elapsed time " + elapsedTime + " f(" + n + ")=" + result );
	       
	       startTime = System.nanoTime();
	       result = fibIteration(n);
	       finishTime = System.nanoTime();
	       elapsedTime = finishTime - startTime;
	       System.out.println("Iterative elapsed time " + elapsedTime + " f(" + n + ")=" + result );
	       
	       startTime = System.nanoTime();
	       double fResult = fibFormula(n);
	       finishTime = System.nanoTime();
	       elapsedTime = finishTime - startTime;
	       System.out.println("Formula elapsed time " + elapsedTime + " f(" + n + ")=" + fResult );
	  } // method fib
	  
	private static long f(int n) {
		 if (n <= 2 )
	           return 1;
	       return f (n - 1) + f (n - 2); 
	}

	/*
	 * Accumulating the total in the val parameter, the result is gradually accumulated on each call of the method, not added up
	 * upon the methods returning as in the other recursive method.
	 */
	private static long efficient(int term, int val, int prev) {
//		 if(term == 0) 
//			 return prev;
		 if(term == 1) 
			 return val;
		 return efficient(term - 1, val+prev, val);
	}
	
	 private static long fibIteration(int n) {
        int x = 0, y = 1, z = 1;
        for (int i = 0; i < n; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return x;
    }
	 
	private static double fibFormula(int n) {
		final double phi1 = (1 + Math.sqrt(5))/2;
		final double phi2 = (1 - Math.sqrt(5))/2;
		return (Math.pow(phi1, n) -Math.pow(phi2, n))/Math.sqrt(5);
	}
}
