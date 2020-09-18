package week4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PointsAndSegments {
	//See: https://www.coursera.org/learn/algorithmic-toolbox/discussions/all/threads/QJ1jK9wNEeWdPBL2iFTrAw/replies/Ihiw4txhEeWK5g7mfcS2Xw/comments/oyAMaeIiEeWyqwpvChh66Q
    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        //write your code here
        Point[] allPoints = new Point[starts.length + ends.length + points.length];
        int i = 0;
        while(i < starts.length) {
        	allPoints[i] = new Point(starts[i], -1, 'l');   
        	i++;
        }
        while(i < starts.length + ends.length) {
        	allPoints[i] = new Point(ends[i - starts.length], -1, 'r'); 
        	i++;
        }
        while(i < starts.length + ends.length + points.length) {
        	allPoints[i] = new Point(points[i - starts.length - ends.length], i - starts.length - ends.length ,'p'); 
        	i++;
        }
        Arrays.sort(allPoints, new pointComparator());
        int count = 0;
        for(Point po : allPoints) {
        	if(po.c == 'l') {
        		count ++;
        	}
        	else if(po.c == 'r') {
        		count --;
        	}
        	else{
        		if(po.idx != -1) {
        			cnt[po.idx] = count;
        		}
        	}
        }
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }
    
    public static class Point{
    	int coord;
    	int idx; 
    	//Haoyun: put an index here so that after sorting 
    	//we don't lose the original order
    	char c;
    	Point(int coord, int idx, char c){
    		this.coord = coord;
    		this.idx = idx;
    		this.c = c;
    	}
//    	@Override
//    	public int compareTo(Point p) {
//    		return this.coord - p.coord;
//    	}
    	//Haoyun: something wrong here??
    }
    
    public static class pointComparator implements Comparator<Point>{
    	@Override
    	public int compare(Point p1, Point p2) {
    		if(p1.coord != p2.coord) {
    			return (p1.coord - p2.coord);
    		}
    		return (p1.c - p2.c);
    	}
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

