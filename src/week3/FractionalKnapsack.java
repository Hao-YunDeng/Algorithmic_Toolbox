package week3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;        
        //write your code here
        
        
        double currWeight = 0;
        double[] carryings = new double[values.length];
        //Haoyun: the default value of double[] is positive 0.0d
        
        //Haoyun: to use junit, add junit library
        //through build path -> configure -> add library      
        assertEquals("values and weights have different length", 
        		values.length, weights.length);
        
        for(int i = 0; i < values.length; i++) {
        	if(capacity == 0) {
        		System.out.println("carryings with bag full " + Arrays.toString(carryings));
        		return value;
        	}
        	
        	double maxValPerWeight = 0;
        	int maxValIndex = 0;
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
          	
        }
        
        System.out.println("carryings with some capacity left " + Arrays.toString(carryings));       
        return value;
    }
    
    public class valueComparator implements Comparator<Integer>{
    	public int[] values;
    	public int[] weights;
    	
    	public valueComparator(int[] values, int[] weights) {
    		this.values = values;
    		this.weights = weights;
    	}
    	
    	@Override
    	public int compare(Integer i, Integer j) {
    		if((double)values[i]/weights[i] - (double)values[j]/weights[j] > 0.0) {
    			return -1;
    		}
    		else if((double)values[i]/weights[i] - (double)values[j]/weights[j] < 0.0) {
    			return 1;
    		}
    		return 0;
    	}
    }
    
    public double getOptimalBySorting(int capacity, int[] values, int[] weights) {
    	Integer[] indices = new Integer[values.length];
    	for(int i = 0; i < indices.length; i++) {
    		indices[i] = i;
    	}
    	valueComparator myComparator = new valueComparator(values, weights);
    	Arrays.sort(indices, myComparator);
    	
    	double value = 0;
    	double currWeight = 0;
    	
    	for(Integer index : indices) {
    		if(capacity == 0) {
    			return value;
    		}
    		currWeight = Math.min(capacity, weights[index]);
    		value += currWeight * values[index]/weights[index];
    		capacity -= currWeight;
    	}
    	
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
        //System.out.println(getOptimalValue(capacity, values, weights));
        FractionalKnapsack myKnapsack = new FractionalKnapsack();
        System.out.println(myKnapsack.getOptimalBySorting(capacity, values, weights));
        
    }
} 
