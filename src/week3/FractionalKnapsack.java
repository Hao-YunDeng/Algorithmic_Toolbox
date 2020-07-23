package week3;

import java.util.Scanner;
import java.util.Arrays;
import static org.junit.Assert.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;        
        //write your code here
        double maxValPerWeight = 0;
        int maxValIndex = 0;
        double currWeight = 0;
        double[] carryings = new double[values.length];
        //Haoyun: the default value of double[] is positive 0.0d
        
        //Haoyun: to use junit, add junit library
        //through build path -> configure -> add library      
        assertEquals("values and lengths have different length", 
        		values.length, weights.length);
        
        for(int i = 0; i < values.length; i++) {
        	if(capacity == 0) {
        		System.out.println("carryings with bag full " + Arrays.toString(carryings));
        		return value;
        	}
        	for(int j = 0; j< values.length; j++) {
        		if(weights[j] > 0 
        				&& values[j]/weights[j] > maxValPerWeight) {
        			 maxValPerWeight = (double)values[j]/weights[j];
        			 maxValIndex = j;
        		}
        	}
        	
        	currWeight = Math.min(capacity, weights[maxValIndex]);
        	value += currWeight * maxValPerWeight;
        	weights[maxValIndex] -= currWeight;
        	carryings[maxValIndex] += currWeight;
        	capacity -= currWeight;
        	
        	//Haoyun: This line is crucial!
        	maxValPerWeight = 0;
        	
        	
        }
        
        System.out.println("carryings with some capacity left " + Arrays.toString(carryings));       
        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
} 
