package week2;

import java.util.*;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }
    
    static long findPisanoPeriod(long m) {
    	long previous = 0;
    	long current = 1;
    	long period = 0;
    	while(true) {
    		long tempPrev = previous;
    		previous = current;
    		current = (tempPrev + current) % m;
    		period ++;
    		if(previous == 0 && current == 1) {
    			return period;
    		}
    	}
    }
    
    static long getFibonacciHugeFast(long n, long m) {
    	if(n <= 1) {
    		return n;
    	}
    	long period = findPisanoPeriod(m);
    	long index = n % period;
    	if(index == 0) {
    		index = period;
    	}
    	long previous = 0;
    	long current = 1;
    	for(int i = 0; i < index - 1; i++) {
    		long tempPrev = previous;
    		previous = current;
    		current = (tempPrev + current) % m;	
    	}
    	return current;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        //System.out.println(getFibonacciHugeNaive(n, m));
        //System.out.println(findPisanoPeriod(m));
        System.out.println(getFibonacciHugeFast(n, m));
    }
}

