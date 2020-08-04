package week3;

import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
    	Arrays.sort(a, new numberComparator().reversed());
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }
    
    public static class numberComparator implements Comparator<String>{
    	@Override
    	public int compare(String firstNum, String secondNum) {
    		if(firstNum.equals(secondNum)) {
    			return 0;
    		}
    		else if(firstNum.charAt(0) != secondNum.charAt(0)) {
    			return firstNum.charAt(0) - secondNum.charAt(0);
    		}
    		else {
    			return (firstNum + secondNum).compareTo(secondNum + firstNum);
    		}
    	}
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

