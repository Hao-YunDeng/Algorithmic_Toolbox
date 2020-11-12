package week5;

import java.util.*;

public class PrimitiveCalculator {
	//Haoyun: this seemingly correct greedy algorithm is in fact incorrect: 
	//sometimes it's faster to subtract 1 first and get to multiple of 3, for example, 10. 
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> DP_optimal_sequence(int n) {
    	//Haoyun: we use list rather than array because we also need to return the exact path
    	//The recursion relation is C(n)=1+min{C(n/3) if n|3, C(n/2) if n|2, C(n-1)}
    	List<Integer> sequence = new ArrayList<Integer>();
    	int[] allNum = new int[n + 1]; //index is the num; its value is the num of steps. Default value is 0
    	int[] prev = new int[n + 1]; //index is the num, its value is its prev number  
    	for(int i = 2; i <=n; i++) {
    		int minOfSteps = allNum[i - 1];
    		int prevNum = i - 1;
    		if(i % 2 == 0) {
    			if(minOfSteps > allNum[i / 2]) {
    				minOfSteps = allNum[i / 2];
    				prevNum = i / 2;
    			}
    		}
    		if(i % 3 == 0) {
    			if(minOfSteps > allNum[i / 3]) {
    				minOfSteps = allNum[i / 3];
    				prevNum = i / 3;
    			}
    		}
    		allNum[i] = minOfSteps + 1;
    		prev[i] = prevNum; 
    	}
    	while(n >= 1) {
    		sequence.add(n);
    		n = prev[n];
    	}
    	Collections.reverse(sequence);
    	return sequence;  	
    }
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = DP_optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

