package week3;

import java.util.*;
import java.io.*;

public class CarFueling {
	
	static int computeMinRefillsYang(int dist, int tank, int[] stops) {
		int lastRefillLoc = 0;
		int numRefill = 0;
		for (int i = 0; i <= stops.length; i++) {
			int target = (i == stops.length) ? dist : stops[i];
			int previous = (i == 0) ? 0 : stops[i - 1];
			if ((target - previous) > tank) return -1;
			if ((target - lastRefillLoc) > tank) {
				lastRefillLoc = previous;
				numRefill += 1;
			}
		}
		return numRefill;
	}
	
    static int computeMinRefills(int dist, int tank, int[] stops) {
    	int numRefill = 0;
    	int currentRefill = -1;
    	int currentDist = 0;

    	while(currentRefill < stops.length - 1) {
    		int lastRefill = currentRefill;
    		while(currentRefill < stops.length - 1 
    				&& stops[currentRefill + 1] - currentDist <= tank) {
    			currentRefill = currentRefill + 1;    			
    		}
    		if(currentRefill == lastRefill) {
    			return -1;
    		}
    		if(currentRefill < stops.length - 1) {
    			numRefill ++;
    			currentDist = stops[currentRefill];
    		}   		
    		if(currentRefill == stops.length - 1
    				/*&& stops[currentRefill] - currentDist <= tank*/) { 
    			if(dist - currentDist <= tank) {
    				return numRefill;
    			}
    			else if(dist - stops[currentRefill] <= tank) {
    				numRefill ++;
    			}
    			else {
    				return -1;
    			}
    		}
    	}


        return numRefill;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefillsYang(dist, tank, stops));
    }
}
