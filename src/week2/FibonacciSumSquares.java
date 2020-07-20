package week2;

import java.util.*;

public class FibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
            sum += (current * current) % 10;
        }

        return sum % 10;
    }
    
    public static long getFibonacciSumSquaresFast(long n) {
    	long fN = FibonacciHuge.getFibonacciHugeFast(n, 10);
    	long fNPlusOne = FibonacciHuge.getFibonacciHugeFast(n + 1, 10);
    	return (fN * fNPlusOne) % 10;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s1 = getFibonacciSumSquaresNaive(n);
        long s2 = getFibonacciSumSquaresFast(n);
        System.out.println("The naive solution yields "+ s1 + ", and my answer is " + s2);
    }
}

