package week2;

import java.util.*;

public class GCD {
  private static int gcd_naive(int a, int b) {
    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }

    return current_gcd;
  }
  
  static int gcdFast(int a, int b) {
	  if(a < b) {
		  int temp = a;
		  a = b;
		  b = temp;
	  }
	  while(b != 0) {
		  int c = b;
		  b = a % b;
		  a = c;
	  }
	  return a;
  }
	  
  

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    //System.out.println(gcd_naive(a, b));
    System.out.println(gcdFast(a, b));
  }
}
