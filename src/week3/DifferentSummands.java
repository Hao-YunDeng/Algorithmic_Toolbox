package week3;

import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here
        int left = n;
        int i = 0;
//        while(left > 0) {
//        	if(left - (i + 1) > i + 1) {
//        		i = i + 1;
//        		summands.add(i);
//        		left = left - i;
//        	}
//        	else break;
//        }
//        summands.add(left);
        while(left - (i + 1) > i + 1) {
        	i++;
        	summands.add(i);
        	left = left - i;
        }
        summands.add(left);
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

