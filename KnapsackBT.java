import java.util.*;

public class KnapsackBT {
    private static double maxWeight = 11; // Maximum allowed weight
    private static int[] w = {1,2,5,6,7}; // The weights of the items
    private static int[] c = { 1, 6, 18, 22, 28 }; // The costs of the items

    // stores final results
    private static boolean[] bestItems = new boolean[w.length];
    private static int bestCost = 0;

    public static void main(String[] args) {
        // starting conditions
        System.out.println("Max allowed weight: " + maxWeight);
        System.out.println("Weights: " + Arrays.toString(w));
        System.out.println("Costs: " + Arrays.toString(c));

        boolean[] useItems = new boolean[w.length]; // all false


        long start = System.currentTimeMillis();

        //search(0, useItems);
        searchLoop(useItems);
        // after the search, report the results
        System.out.println("\nBest cost combination: " + bestCost);
        int totWeight = 0;
        for (int i = 0; i < w.length; i++) {
            if (bestItems[i]) {
                System.out.println("  Item " + i + "; w: " + w[i] + "; c: " + c[i]);
                totWeight += w[i];
            }
        }
        System.out.println("Total weight: " + totWeight);

        long now = System.currentTimeMillis();
        System.out.println("Elapsed time: " + ((now - start) / 1000.0) + " secs");
    } // end of main()

    private static void search(int i, boolean[] useItems) {
        if (i == w.length){
            System.out.println(Arrays.toString(useItems));
            checkItems(useItems);
        }
        else { // try two possibilities
            useItems[i] = true;
            search(i + 1, useItems);
            useItems[i] = false; // order means that ith item choice is false at end
            search(i + 1, useItems); // of else block
        }
    } /* Search */

    private static void searchLoop(boolean[] useItems) {
        for (int i =0; i < Math.pow(2,w.length); i++) { // o(2^n)
            String temp = Integer.toBinaryString(i);
            for (int j =0; j<temp.length(); j++){
                if(temp.charAt(j) == '1'){
                    useItems[j] = false;
                }else{
                    useItems[j] = true;
                }
            }
            System.out.println(Arrays.toString(useItems));
            checkItems(useItems);
        }
    }

    private static void checkItems(boolean[] useItems) {
        // calculate total weight and cost of used items
        
        int totWeight = 0;
        int totCost = 0;
        for (int i = 0; i < w.length; i++) {
            if (useItems[i]) {
                totCost += c[i];
                totWeight += w[i];
            }
        }

        if (totWeight > maxWeight) // too heavy
            return;

        if (totCost > bestCost) { // store results globally
            bestCost = totCost;
            for (int i = 0; i < w.length; i++)
                bestItems[i] = useItems[i];
        }
    } /* checkItems */

} // end of KnapsackBT class