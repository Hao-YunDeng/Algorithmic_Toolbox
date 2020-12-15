package week6;

import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
        //write your code here
    	int size = (exp.length() + 1) / 2;

    	long[][] m = new long[size][size];
    	long[][] M = new long[size][size];
    	for(int i = 0; i < size; i++) {
    		m[i][i] = Character.getNumericValue(exp.charAt(2 * i)); 
    		M[i][i] = Character.getNumericValue(exp.charAt(2 * i));   	
    	}
    	for(int s = 1; s < size; s++) {
    		for(int i = 0; i < size - s; i++) {
    			int j = i + s;
    			long[] minMax = minAndMax(i, j, m, M, exp);
    			m[i][j] = minMax[0];
    			M[i][j] = minMax[1];   			
    		}
    	}
    	return M[0][size - 1];
    }
    
    private static long[] minAndMax(int i, int j, long[][] m, long[][] M, String exp) {  	
    	long min = Integer.MAX_VALUE;
    	long max = Integer.MIN_VALUE;
    	for(int k = i; k < j; k++) {
    		long a = eval(M[i][k], M[k + 1][j], exp.charAt(2 * k + 1));
    		long b = eval(M[i][k], m[k + 1][j], exp.charAt(2 * k + 1));
    		long c = eval(m[i][k], M[k + 1][j], exp.charAt(2 * k + 1));
    		long d = eval(m[i][k], m[k + 1][j], exp.charAt(2 * k + 1));
    		min = Math.min(min, Math.min(a, Math.min(b, Math.min(c, d)))); 
    		max = Math.max(max, Math.max(a, Math.max(b, Math.max(c, d)))); 
    	}
    	return new long[] {min, max};
    	
    	//return {0L, 0L}; Haoyun: this won't work! use return new long[] {0, 0};
    	// Array is an object; you have to use 'new' to declare
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

