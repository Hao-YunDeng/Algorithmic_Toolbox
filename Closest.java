package week4;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {
	
	static class Point{
		long x, y;
		Point(long x, long y){
			this.x = x;
			this.y = y;
		}
	}

    static class compareByY implements Comparator<Point> {
        @Override
        public int compare(Point p, Point q) {
            return p.y == q.y ? Long.signum(p.x - q.x) : Long.signum(p.y - q.y);
        }
    }
    
    static class compareByX implements Comparator<Point> {
        @Override
        public int compare(Point p, Point q) {
            return Long.signum(p.x - q.x);
        }
    }

    static double distance(Point p, Point q) {
    	return Math.sqrt(Math.pow(p.x - q.x, 2) + Math.pow(p.y - q.y, 2));
    }
    
    static double min(double x, double y) {
    	return x < y ? x : y;
    }
    
    static double stripMin(Point[] strip, int size, double d) {
    	double result = d;
    	for(int i = 0; i < size; i++) {
    		for(int j = i + 1; j < size && (strip[j].y - strip[i].y < d); j++) {
    			double dist = distance(strip[i], strip[j]);
    			if(dist < result) {
    				result = dist;
    			}
    		}
    	}
    	return result;
    }
    
    static double bruteForce(int[] x, int[] y, int n) {
    	double minDist = Double.POSITIVE_INFINITY;
    	for(int i = 0; i < n; i++) {
    		for(int j = i + 1; j < n; j++) {
    			Point p = new Point(x[i], y[i]);
    			Point q = new Point(x[j], y[j]);
    			double dist = distance(p, q);
    			minDist = min(minDist, dist);
    		}
    	}
    	return minDist;
    }
    
    static double minimalDistance(Point[] PointsSortedByX, Point[] PointsSortedByY, int n) {
    	int midIndex = n/2;
    	Point midPoint = PointsSortedByX[midIndex];
    	
//    	Point[] leftPointsSortedX = Arrays.copyOfRange(PointsSortedByX, 0, midIndex);
//    	Point[] rightPointsSortedX = Arrays.copyOfRange(PointsSortedByX, midIndex, size);
        
    	Point[] leftPointsSortedX = new Point[midIndex];
    	Point[] rightPointsSortedX = new Point[n - midIndex];
    	for(int i = 0; i < midIndex; i++) {
    		leftPointsSortedX[i] = PointsSortedByX[i];
    	}
    	for(int i = midIndex; i < n; i++) {
    		rightPointsSortedX[i - midIndex] = PointsSortedByX[i];
    	}
    	
        Point[] leftPointsSortedY = new Point[midIndex];
        Point[] rightPointsSortedY = new Point[n - midIndex];
                        
        int l = 0, r = 0;       
        for(int i = 0; i < n; i++) {
        	if(PointsSortedByY[i].x < midPoint.x){
        		leftPointsSortedY[l] =  PointsSortedByY[i];
        		l++;
        	}
        	else {
        		rightPointsSortedY[r] = PointsSortedByY[i];
        		r++;
        	}
        }
        
        double dl = minimalDistance(leftPointsSortedX, leftPointsSortedY, midIndex);
        double dr = minimalDistance(rightPointsSortedX, rightPointsSortedY, n - midIndex);
        double d = min(dl, dr);
        
        Point[] strip = new Point[n];
        int actualSize = 0;
        for(int i = 0; i < n; i++) {
        	if(Math.abs(PointsSortedByY[i].x - midPoint.x) <= d) {
        		strip[actualSize ++] = PointsSortedByY[i];
        	}
        }
        
        return stripMin(strip, actualSize, d);
    }
    
    static double minimalDistance(int[] x, int y[], int n) {
    	if(n <= 3) {
    		return bruteForce(x, y, n);
    	}
    	
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++) {
        	points[i] = new Point(x[i], y[i]);
        }
    	
    	Point[] PointsSortedByX = points.clone();
    	Point[] PointsSortedByY = points.clone();
    	Arrays.sort(PointsSortedByX, new compareByX());
    	Arrays.sort(PointsSortedByY, new compareByY());
        
        return minimalDistance(PointsSortedByX, PointsSortedByY, n);        
    
    }
    


    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }
        System.out.println(minimalDistance(x, y, n));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
