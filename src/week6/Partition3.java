package week6;

import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] A) {
        //write your code here
    	//Haoyun
//    	I think there are mistakes if one simply takes this as a knapsack problem, 
//    	since there may be multiples ways to separate the arrays to find the FIRST subset with the sum of totalSum/3. 
//    	But the algorithm may finally return 0 if it has chosen the way 
//    	that won't be able to find the SECOND subset afterwards. 
//    	This can be mistaken since there may exist a successful choice 
//    	for the first subset and that algorithm just missed it.
//    	I believe the correct DP algorithm is:
//
//    	Let partition(sum1, sum2, i) denote if we can have two disjoint subsets 
//    	with sum = sum1 and sum = sum2, 
//    	respectively, by using only the elements a_1 ~ a_i.
//    	Initialization: partition(0, 0, all i) = 1
//
//    	Recursive relation: partition(sum1, sum2, n) = 1 if anyone of the three equals 1,
//    			1) partition(sum1, sum2, n - 1) == 1;
//    			2) partition(sum1 - a_n, sum2, n - 1) == 1;
//   			3) partition(sum1, sum2 - a_ n, n - 1) == 1.
//
//    	Ref: https://cs.stackexchange.com/questions/103355/solve-partition-into-three-sets-in-pseudo-polynomial-time
    	int sum = 0;
    	for(int i: A) {
    		sum += i;
    	}
    	if(sum % 3 != 0) {
    		return 0;
    	}
    	
    	sum = sum / 3;
    	int[][][] partition = new int[sum + 1][sum + 1][A.length + 1];
    	
    	for(int i = 0; i <= A.length; i++) {
    		partition[0][0][i] = 1;
    		
    	}
    	for(int i = 1; i <= A.length; i++) {
        	for(int sum1 = 0; sum1 <= sum; sum1++) {
        		for(int sum2 = 0; sum2 <= sum; sum2++) {
        			
        			if(partition[sum1][sum2][i - 1] == 1) {
        				partition[sum1][sum2][i] = 1;
        				
        			}
        			
        			if(sum1 >= A[i - 1] && partition[sum1 - A[i - 1]][sum2][i - 1] == 1) {
        				partition[sum1][sum2][i] = 1;
        				
        			}
        			
        			if(sum2 >= A[i - 1] && partition[sum1][sum2 - A[i - 1]][i - 1] == 1) {
        				partition[sum1][sum2][i] = 1;
        			}
        		}
        	}
    	}
    	
//    	for(int i = 0; i <= A.length; i++) {
//        	for(int sum1 = 0; sum1 <= sum; sum1++) {
//        		for(int sum2 = 0; sum2 <= sum; sum2++) {
//        			if(partition[sum1][sum2][i] == 1) {
//        				System.out.println("" + sum1 + " " + sum2 + " " + i);     		
//        			}
//        		}
//        	}
//    	}
    	
        return partition[sum][sum][A.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

