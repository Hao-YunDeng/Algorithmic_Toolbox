package week5;

import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {
        //write your code here
    	int[] coins = {1, 3, 4};
    	int[] minNumCoins = new int[m + 1];
    	minNumCoins[0] = 0;
    	for(int currMoney = 1; currMoney <= m; currMoney++) {
    		minNumCoins[currMoney] = Integer.MAX_VALUE;
    		for(int coin : coins) {
    			if(currMoney >= coin) {
    				int numCoins = minNumCoins[currMoney - coin] + 1;
    				if(numCoins < minNumCoins[currMoney]) {
    					minNumCoins[currMoney] = numCoins;
    				}
    			}
    		}
    	}
        return minNumCoins[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

