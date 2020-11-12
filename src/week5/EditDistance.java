package week5;

import java.util.*;

class EditDistance {
  public static int EditDistance(String s, String t) {
	  //write your code here
	  int[][] D = new int[s.length() + 1][t.length() + 1];
	  for(int i = 0; i <= s.length(); i++) {
		  D[i][0] = i;
	  }
	  for(int j = 0; j <= t.length(); j++) {
		  D[0][j] = j;
	  }
	  for(int i = 1; i <= s.length(); i++) {
		  for(int j = 1; j <= t.length(); j++) {
			  int ins = D[i][j - 1] + 1;
			  int del = D[i - 1][j] + 1;
			  int mat = D[i - 1][j - 1];
			  int mis = D[i - 1][j - 1] + 1;
			  if(s.charAt(i - 1) == t.charAt(j - 1)) {
				  D[i][j] = Math.min(Math.min(ins, del), mat); 
//				  D[i][j] = D[i - 1][j - 1];
			  }
			  else {
				  D[i][j] = Math.min(Math.min(ins, del), mis);
//				  D[i][j] = D[i - 1][j - 1] + 1;
			  }
//			  D[i][j] = Math.min(D[i][j], D[i - 1][j]+ 1);
//			  D[i][j] = Math.min(D[i][j], D[i][j - 1]+ 1);
		  }
	  }
	  return D[s.length()][t.length()];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
