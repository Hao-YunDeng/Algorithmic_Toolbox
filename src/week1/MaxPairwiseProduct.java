package week1;

import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        int max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = Math.max(max_product,
                    numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    static long getMaxPairwiseProductFast(int[] numbers) {
        int n = numbers.length;
        int first = 0;
        for (int i = 1; i < n; ++i) {
        	if(numbers[i] > numbers[first]) {
        		first = i;
        	}
        }
        int second = 0;
        if(first == 0) {
        	second = 1;
        }
        for(int i = 1; i < n; ++i) {
        	if(numbers[i] > numbers[second] && second != first) {
        		second = i;
        	}
        }
        return numbers[first] * numbers[second];
    }
    
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        //System.out.println(getMaxPairwiseProduct(numbers));
        System.out.println(getMaxPairwiseProductFast(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
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