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
    
    static double bruteForce(Point[] points, int n) {
    	double minDist = Double.POSITIVE_INFINITY;
    	for(int i = 0; i < n; i++) {
    		for(int j = i + 1; j < n; j++) {
    			Point p = points[i];
    			Point q = points[j];
    			double dist = distance(p, q);
    			minDist = min(minDist, dist);
    		}
    	}
    	return minDist;
    }
    
    static double minimalDistance(Point[] PointsSortedByX, int a, int b, Point[] PointsSortedByY) {
    	
    	if(a - b <= 3) {
    		return bruteForce(PointsSortedByY, b - a);
    	}
    	int midIndex = (b+a)/2;
    	Point midPoint = PointsSortedByX[midIndex];
    	          	
        Point[] leftPointsSortedY = new Point[midIndex - a];
        Point[] rightPointsSortedY = new Point[b - midIndex];
                        
        int l = 0, r = 0;       
        for(int i = 0; i < b - a; i++) {
        	if(PointsSortedByY[i].x < midPoint.x){
        		leftPointsSortedY[l] =  PointsSortedByY[i];
        		l++;
        	}
        	else {
        		rightPointsSortedY[r] = PointsSortedByY[i];
        		r++;
        	}
        }
        
        double dl = minimalDistance(PointsSortedByX, a, midIndex, leftPointsSortedY);
        double dr = minimalDistance(PointsSortedByX, midIndex, b, rightPointsSortedY);
        double d = min(dl, dr);
        
        Point[] strip = new Point[b - a];
        int actualSize = 0;
        for(int i = 0; i < b - a; i++) {
        	if(Math.abs(PointsSortedByY[i].x - midPoint.x) <= d) {
        		strip[actualSize ++] = PointsSortedByY[i];
        	}
        }
        
        return stripMin(strip, actualSize, d);
    }
    
    static double minimalDistance(int[] x, int y[], int n) {   	
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++) {
        	points[i] = new Point(x[i], y[i]);
        }
    	
    	Point[] PointsSortedByX = points.clone();
    	Point[] PointsSortedByY = points.clone();
    	Arrays.sort(PointsSortedByX, new compareByX());
    	Arrays.sort(PointsSortedByY, new compareByY());
        
        return minimalDistance(PointsSortedByX, 0, n, PointsSortedByY);        
    
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
