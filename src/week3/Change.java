package week3;

import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
        //write your code here
    	int num = 0;
    	int left = m;
    	while(left >= 10) {
    		left = left -10;
    		num++;
    	}
    	while(left >= 5) {
    		left = left -5;
    		num++;
    	}
    	while(left >= 1) {
    		left =left -1;
    		num++;
    	}
        return num;
    }
    //Haoyun: greedy change is right only if all the denominations are multiples of the smaller ones, or 
    // larger ones are more than twice of a smaller one like 1, 2, 5. Exception for example can be 1, 7, 10 to change 14.
    //Otherwise, use DP.
    private static int getChangeChen(int m) {
    	int numTotal = 0;
    	int numCoin = 0;
    	int[] coins = {10, 5, 1};
    	for(int coin : coins) {
    		numCoin = m/coin;
    		m -= numCoin * coin;
    		numTotal += numCoin;
    	}
    	return numTotal;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        //System.out.println(getChange(m));
        System.out.println(getChangeChen(m));

    }
}

