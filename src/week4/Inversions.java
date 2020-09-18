package week4;

import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
    	//See: https://www.youtube.com/watch?v=k9RQh21KrH8
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        int i = left;
        int j = ave;
        int k = left;
        while (i < ave && j < right) {
        	if(a[i] > a[j]) {
        		numberOfInversions += ave - i;
        		//System.out.println(a[i]+" > "+a[j]+ " and count +" +" "+ (ave-i));
        		b[k] = a[j];
        		j++;
        		k++;
        	}
        	else {
        		b[k] =a[i];
        		i++;
        		k++;
        	}
        }
        while(i < ave && j == right) {
        	b[k] = a[i];
        	k++;
        	i++;
        }
        while(i ==ave && j < right) {
        	b[k] = a[j];
        	k++;
        	j++;
        }
        for(int idx = left; idx < right; idx++) {
        	a[idx] = b[idx];
        }
        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
        System.out.println(Arrays.toString(b));
    }
}

