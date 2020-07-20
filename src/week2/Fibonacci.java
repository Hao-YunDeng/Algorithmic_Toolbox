package week2;

import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }

  private static long calc_fib_array(int n) {
	  int[] fibArray = new int[n + 1];
	  //Haoyun: n+1 instead of n for user to input n=1 and I still have 2 elements 
	  fibArray[0] = 1;
	  fibArray[1] = 1;
	  for(int i = 2; i < n; i++) {
		  fibArray[i] = fibArray[i - 1] + fibArray[i - 2];
	  }
	  return fibArray[n - 1];
  }
  
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    //System.out.println(calc_fib(n));
    System.out.println(calc_fib_array(n));
  }
}
