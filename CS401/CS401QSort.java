public class CS401QSort
{
   public static void main(String args[])
   {
      Integer a[] = {10, 89, 0, 88, 76, 56, 20, 22, 100, 56, 44, 30, 32, 23 };

      System.out.println("Unsorted array: ");
      for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + " ");
      System.out.println();

      quicksort(a, 0, a.length-1);

      System.out.println("---");
      System.out.println("Sorted array: ");

      for (int i = 0; i < a.length; i++)
         System.out.print(a[i] + " ");
      System.out.println();

      return;
   }
   
   /*
		Creates a tree of height log(n), n comparisons at each level as a bound
		worst case occurs when one subarray has n-1 elements and the other subarray has 0. this represents a skewed tree, with n levels and n comparisons O(n^2)
		can choose median of first last and middle elements to be the pivot
		space is O(log(n)) using in place partitioning, O(log(n)) recursive calls
   */
	// partition array such that the pivot element is in the correct position, then perform that step on the left array and right array
   public static void quicksort(Integer a[], int left, int right)
   {
      if (left < right)
      {
          int pi = (left+right)/2;
          int newPivotIndex = partition(a, left, right, pi);
          quicksort(a, left, newPivotIndex-1);
          quicksort(a, newPivotIndex+1, right);
      }
   }

   public static int partition(Integer a[], int left, int right, int pivotIndex)
   {
      int pivot;
      int tmp, i, storeIndex;

      pivot = a[pivotIndex];

      tmp = a[pivotIndex];       /* Swap a[pivotIndex] and a[right] */
      a[pivotIndex] = a[right];
      a[right] = tmp;

     storeIndex = left;
     for (i = left; i < right; i++)
     {
        if (a[i] < pivot)
        {
           tmp = a[storeIndex];       /* Swap a[i] and a[storeIndex] */
           a[storeIndex] = a[i];
           a[i] = tmp;
    
           storeIndex++; // only increment storeIndex in a swap
        }
     }

     tmp = a[right];                /* Swap a[storeIndex] and a[right] */
     a[right] = a[storeIndex];      /* Moves pivot to its final place */
     a[storeIndex] = tmp; // a[right] contains pivot element, swap it with a[storeIndex] which contains first element greater than the pivot. pivot is at storeIndex 
						  // where it belongs in the array

     return storeIndex;
   }
}
