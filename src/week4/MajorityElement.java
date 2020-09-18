package week4;

import java.util.*;
import java.io.*;

public class MajorityElement {
	private static int countInRange(int[] a, int num, int left, int right) {
		int count = 0;
		for(int i = left; i < right; i++) {
			if(a[i] == num) {
				count++;
			}
		}
		return count;
	}
    private static int getMajorityElement(int[] a, int left, int right) {  	
    	//Approach 1
//        if (left == right) {
//            return -1;
//        }
//        if (left + 1 == right) {
//            return a[left];
//        }
//        int mid = left + (right - left)/2;
//        int leftCandidate = getMajorityElement(a, left, mid);
//        int rightCandidate = getMajorityElement(a, mid, right);
//        
//        if(leftCandidate == rightCandidate) {
//        	return leftCandidate;
//        }
//        int leftCount = countInRange(a, leftCandidate, left, right);
//        int rightCount = countInRange(a, rightCandidate, left, right);
//        
//        if(leftCount * 2 > right - left) {
//        	return leftCandidate;
//        }
//        if(rightCount * 2 > right - left) {
//        	return rightCandidate;
//        }
//        return -1;

        //Approach 2 
//      if (left == right) {
//          return -1;
//      }
//      if (left + 1 == right) {
//          return a[left];
//      }        
//      Arrays.sort(a);
//      int currElement = -1;
//      int currCount = 0;
//      // iteration costs O(n)
//      for(int currIdx = 0; currIdx < a.length; currIdx++) {
//      	if(a[currIdx] > currElement) {
//      		currElement = a[currIdx];
//      		currCount = 1;
//      		if(currCount * 2 > a.length)
//      			return currElement;
//      	}
//      	else if(a[currIdx] == currElement) {
//      		currCount++;
//      		if(currCount * 2 > a.length)
//      			return currElement;
//      	}
//      }
//      return -1;  
    	
    	//Approach 3
      HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
      for(int i = 0; i < a.length; i++) {
    	  if(!count.containsKey(a[i])) {
    		  count.put(a[i], 1);
    	  }
    	  else {
    		  count.put(a[i], count.get(a[i]) + 1);
    	  }
      }
      for(int num : count.keySet()) {
    	  if(count.get(num) * 2 > a.length) {
    		  return num;
    	  }
      }
      return -1;
    
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

