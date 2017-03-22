
public class ComplexityExperiment {
	 public static void main (String args []) throws InterruptedException {	
			
		  
         /* run_method_A(250);
         run_method_A(500);
         run_method_A(1000);
         run_method_A(2000);
         System.out.println(); */

         run_method_B(250);
         run_method_B(500);
         run_method_B(1000);
         run_method_B(2000);
         System.out.println();

         /* run_method_C(250);
         run_method_C(500);
         run_method_C(1000);
         run_method_C(2000);
         System.out.println(); */

     }
	
     public static void run_method_A(int n)  {
		int i = 0;
		double start, end;
	        start = System.currentTimeMillis();
		methodA(n);
		end = System.currentTimeMillis() - start;
		System.out.println("methodA(n = " + n + ") time = " + end + "ms");
     }


     public static void run_method_B(int n)  {
		int i = 0, loop = 10;
		double start, end;
	        start = System.currentTimeMillis();
	        for (i = 0; i < loop; i++)  {
		    methodB(n);
		}
		end = System.currentTimeMillis() - start;
		System.out.println("methodE(n = " + n + ") time = " + end/loop + "ms");
     }


     public static void run_method_C(int n)  {
        int i = 0;
        double start, end;
        start = System.currentTimeMillis();
        methodC(n);
        end = System.currentTimeMillis() - start;
        System.out.println("methodC(n = " + n + ") time = " + end + "ms");
     }

      public static void methodA (int n){

		int i = 0; // 1
		int j = 0; // 1
		int k = 0; // 1
		int total = 0; // 1
		while (i<n){
			while (j<n){
				while (k<n) {
					total++; // 2n statements
					k++;
				}
				k=0; // 2n statements
				j++;
			}
			j=0; // 2n statements
			i++;
		}
	}
	
	public static void methodB (int n){
		int i = 0; // 1
		int j = 0; // 1
		int total = 0; // 1
		while (i<n){
			while (j<n){
				total++; //2n statements
				j++;
			}
			i++; //n statements
		}
	}

        public static void methodC (int n){
                int i = 0; // 1 
                int j = 0; // 1
                int total = 0; // 1

                j = n; // 1
                while ((j = j/2) > 0)  { // lg(n)
                    for (i = 0; i < 100*n; i++)
                         total++; // 100n
                }
        }
}
