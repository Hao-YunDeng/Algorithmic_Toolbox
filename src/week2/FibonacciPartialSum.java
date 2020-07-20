package week2;

import java.util.*;

public class FibonacciPartialSum {
    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current % 10;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }
    
    public static long getFibonacciPartialSumFast(long from, long to) {
    	long sum1 = FibonacciSumLastDigit.getFibonacciSumFast(from - 1);
    	long sum2 = FibonacciSumLastDigit.getFibonacciSumFast(to);
    	long sum = sum2 - sum1;
    	if(sum < 0) {
    		sum += 10;
    	}
    	return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumNaive(from, to));
        System.out.println(getFibonacciPartialSumFast(from, to));
    }
}

