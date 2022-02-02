// Cryptarithmetic.java
// Andrew Davison, ad@fivedots.coe.psu.ac.th, Jan 2022


/* Solve Cryptarithmetic Problems
   // https://en.wikipedia.org/wiki/Verbal_arithmetic

  Cryptarithmetic is a mathematical game consisting of an equation using 
  unknown numbers, whose digits are represented by letters of the alphabet. 

  The goal is to identify the value of each letter. 

  The classic example, published in the July 1924 issue of Strand Magazine by Henry Dudeney is:

           SEND + MORE = MONEY 

  One solution to this puzzle is:
       O = 0, M = 1, Y = 2, E = 5, N = 6, D = 7, R = 8, and S = 9. 

   -----------------------------
   Usage:

      > javac CryptArithmetic.java
      
      > java CryptArithmetic
      Enter first string: send
      Enter second string: more
      Enter string sum: money

      Calculating "send" + "more" == "money"
      
      send(2817) + more(368) == money(3185)
        d:7;  e:8;  m:0;  n:1;  o:3;  r:6;  s:2;  y:5;
      
      send(2817) + more(368) == money(3185)
        d:7;  e:8;  m:0;  n:1;  o:3;  r:6;  s:2;  y:5;
      
      send(2819) + more(368) == money(3187)
        d:9;  e:8;  m:0;  n:1;  o:3;  r:6;  s:2;  y:7;
      
      send(2819) + more(368) == money(3187)
        d:9;  e:8;  m:0;  n:1;  o:3;  r:6;  s:2;  y:7;
      
      send(3712) + more(467) == money(4179)
        d:2;  e:7;  m:0;  n:1;  o:4;  r:6;  s:3;  y:9;
      
      send(3712) + more(467) == money(4179)
        d:2;  e:7;  m:0;  n:1;  o:4;  r:6;  s:3;  y:9;

             :
      Elapsed time: 1.1 secs
*/


import java.util.*;

public class CryptArithmetic
{
  private static ArrayList<Character> letters = new ArrayList<>();
  private static String s1, s2, sumStr;

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter first string: ");
    s1 = sc.nextLine().trim().toLowerCase();

    System.out.print("Enter second string: ");
    s2 = sc.nextLine().trim().toLowerCase();

    System.out.print("Enter string sum: ");
    sumStr = sc.nextLine().trim().toLowerCase();

    System.out.println("\nCalculating \"" + s1 + "\" + \"" + s2 + 
                                "\" == \"" + sumStr + "\"");
    
    long start = System.currentTimeMillis();

    addLetters(s1, letters);
    addLetters(s2, letters);
    addLetters(sumStr, letters);

    int[] digits = {0,1,2,3,4,5,6,7,8,9};

    matchWithLetters(digits, 0);

    long now = System.currentTimeMillis();
    System.out.println("Elapsed time: " + ((now - start) / 1000.0) + " secs");
  } /* main */



  public static void addLetters(String s, ArrayList<Character> letters)
  {
    for(int i=0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if(!letters.contains(ch))
        letters.add(ch);
    }
  }



  private static void matchWithLetters(int[] digits, int pos)
  {
	if(pos==letters.size())
	{
		int[] perm = {};
		for(int i=0;i<letters.size();i++)
		{
			perm = Arrays.copyOf(perm, perm.length + 1);
			perm[perm.length - 1] = digits[i];
			//System.out.println(Arrays.toString(perm));
		}
		if(sumWorks(perm)){ //if found the solution backtrack now! -> not continue because when subsitution with next outcome it will false
							// proof shown at fig.2
			return;
		}
	}	
	else
	{	
		for (int i = pos; i < digits.length; i++)
		{
      swap(digits, pos, i); 
			matchWithLetters(digits,pos+1);
      swap(digits, i, pos);
		}
	}
  } /* matchWithLetters */

  private static void swap(int[] data, int i, int j) 
  // swap ith and jth chars of the array
  { int temp = data[i]; 
    data[i] = data[j]; 
    data[j] = temp; 
  } 


  private static boolean sumWorks(int[] digits)
  {
    // pair letters with digits
    TreeMap<Character, Integer> letterVals = new TreeMap<Character, Integer>();
    for(int j=0; j < letters.size(); j++)
      letterVals.put(letters.get(j),digits[j]);

    // use map to convert strings to numbers
    int no1 = evalNumber(s1, letterVals);
    int no2 = evalNumber(s2, letterVals);
    int sum = evalNumber(sumStr, letterVals);

    // check if the calculation works
    boolean hasSolution = false;
    if(sum == no1+no2) {
      hasSolution = true;
      System.out.println("\n" + s1 + "(" + no1 + ") + " + s2 + "(" + no2 + 
                         ") == " + sumStr + "(" + sum + ")");
      listMap(letterVals);
    }

    return hasSolution;
  } /* sumWorks */


  private static int evalNumber(String s, TreeMap<Character,Integer> letterVals)
  {
    int val = 0;
    for(int i = 0; i < s.length(); i++)
      val = (10*val) + letterVals.get(s.charAt(i));
    return val;
  } /* evalNumber */



  private static void listMap(TreeMap<Character, Integer> letterVals)
  {
    for (Map.Entry<Character, Integer> entry : letterVals.entrySet()) {
      char key = entry.getKey();
      int value = entry.getValue();
      System.out.print("  " + key + ":" + value + ";");
    }
    System.out.println();
  } 



}  // end of Cryptarithmetic class