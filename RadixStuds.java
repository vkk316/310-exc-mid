import java.io.*;
import java.util.*;

public class RadixStuds {
    public static void main(String[] args) {
        
        if (args.length != 1) {
            System.out.println("Usage: java GetStudents <fnm>");
            System.exit(1);
          }
      
          ArrayList<String> lines = readLines(args[0]);
          if (lines == null)
            return;
      
          long[] studs = new long[lines.size()];
          for (int i=0; i < studs.length; i++)
             studs[i] = getLong(lines.get(i));
          
          System.out.println("  Unsorted array: " + Arrays.toString(studs));
    
        radixSort(studs); 
        System.out.println("  Sorted array: " + Arrays.toString(studs));
    }

    private static ArrayList<String> readLines(String fnm)
    {
      try (BufferedReader br = new BufferedReader(new FileReader(fnm))) {
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null)
          lines.add(line);
  
        System.out.println("Read " + lines.size() + " lines from " + fnm);
        return lines;
      } 
      catch (IOException e) 
      { System.out.println("Could not read " + fnm);  }
  
      return null;
    } // end of readLines()
  
  
  
    private static long getLong(String s)
    {
      long val = -1L;
      try {
        val = Long.parseLong(s); 
      }
      catch (NumberFormatException e)
      {  System.out.println("Could not parse: \"" + s + "\"");  }
  
      return val;
    }  // end of getLong()


    /////// from RadixSort.java /////////
    private static void radixSort(long arr[]) 
    { 
      long max = findMax(arr); 
      int numDigits = (int) Math.log10(max) + 1; 
                               // valid only if max > 0
      int placeVal = 1;
      while (numDigits-- > 0) {
        countSort(arr, placeVal);
        placeVal *= 10;
      }
    } 
  
  
  
    private static long findMax(long arr[]) 
    // return maximum value in arr[] 
    { 
      long max = arr[0]; 
      for (int i = 1; i < arr.length; i++) 
        if (arr[i] > max) 
          max = arr[i]; 
      return max; 
    } 
  
  
    private static void countSort(long arr[], int placeVal) 
    // counting sort of arr[] according to placeVal
    { 
      int n = arr.length; 
  
      int count[] = new int[10]; // k range is 0..9
      Arrays.fill(count, 0); 
  
      // count the values in arr[]
      for (int i = 0; i < n; i++) {
        long pos = (arr[i]/placeVal)%10;
        count[(int)pos]++; 
      }
  
      /* modify count[i] so that it contains the 
         sum of earlier counts, which will be equivalent
         to the position of i in the sorted array 
      */
      for (int i = 1; i < count.length; i++) 
        count[i] += count[i-1]; 
  
      // build the sorted array in reverse order
      // so sort is stable
      long sorted[] = new long[n];  
      for (int i = n-1; i >= 0; i--) { 
        long pos = (arr[i]/placeVal)%10;
        sorted[count[(int)pos] - 1] = arr[i]; 
        count[(int)pos]--; 
      } 
  
      // copy the sorted array back to arr[] 
      for (int i = 0; i < n; i++) 
        arr[i] = sorted[i]; 
    } // end of countSort()
  
}
