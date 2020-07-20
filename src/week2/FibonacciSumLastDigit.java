package week2;

import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
            sum += current;
            sum = sum % 10;
        }

        return sum % 10;
    }
    
    public static long getFibonacciSumFast(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sumToIndex = 0;
        long sumToPeriod = 0;
        
    	long period = FibonacciHuge.findPisanoPeriod(10);
    	
    	//System.out.println(period);
    	
    	long multiple = n / period;
    	long index = n % period;
    	
    	for(long i = 0; i < period; i++) {
  
            sumToPeriod += current;  

            if(i < index ) {
            	sumToIndex += current;
            }
            
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
    	}
    	//System.out.println("sum to period " + sumToPeriod);
    	//System.out.println("sum to index " + sumToIndex);
    	
    	return (sumToPeriod * multiple + sumToIndex) % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s1 = getFibonacciSumNaive(n);
        long s2 = getFibonacciSumFast(n);
        System.out.println("The naive solution gives " + s1 + ", and my answer is " + s2);
    }
}

