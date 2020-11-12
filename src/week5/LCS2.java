package week5;

import java.util.*;

public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        //Write your code here
    	//Recursion relation D[i][j] = max{D[i - 1][j - 1] + 1 if a[i] == b[j] else no plus 1, D[i - 1][j], D[i][j - 1]}
  	  int[][] D = new int[a.length + 1][b.length + 1];
  	  for(int i = 0; i <= a.length; i++) {
  		  D[i][0] = 0;
  	  }
  	  for(int j = 0; j <= b.length; j++) {
  		  D[0][j] = 0;
  	  }
  	  for(int i = 1; i <= a.length; i++) {
  		  for(int j = 1; j <= b.length; j++) {
  			  if(a[i - 1] == b[j - 1]) { 
  				  D[i][j] = D[i - 1][j - 1] + 1;
  			  }
  			  else {
  				  D[i][j] = D[i - 1][j - 1];
  			  }
  			  D[i][j] = Math.max(D[i][j], D[i - 1][j]);
  			  D[i][j] = Math.max(D[i][j], D[i][j - 1]);
  		  }
  	  }
  	  return D[a.length][b.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

