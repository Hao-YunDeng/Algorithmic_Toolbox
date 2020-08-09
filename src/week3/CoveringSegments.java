package week3;

import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here   	
    	//Scripts below are provided
        int[] points = new int[2 * segments.length];
        for (int i = 0; i < segments.length; i++) {
            points[2 * i] = segments[i].start;
            points[2 * i + 1] = segments[i].end;
        }
        return points;
    }
    
    private static int[] optimalPointsHaoyun(Segment[] segments) {
    	List<Integer> pointList = new ArrayList<Integer>();
    	Arrays.sort(segments, new segmentComparator());
    	
    	
    	int currIndex = 0;
    	
    	while(currIndex < segments.length) {
    		int lastIndex = currIndex;
    		int lastPoint = segments[lastIndex].end;
    		while(currIndex < segments.length && segments[currIndex].start <= lastPoint) {
    			currIndex ++;
    		}
    		pointList.add(lastPoint);
    	}
    	
        int[] points = new int[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            points[i] = pointList.get(i);
        }
        return points;
    }
    
    private static int[] optimalPointsForLoop(Segment[] segments) {
    	List<Integer> pointList = new ArrayList<Integer>();
    	Arrays.sort(segments, new segmentComparator());
    	
    	int lastPoint = segments[0].end;
    	pointList.add(lastPoint);
    	
    	for(int i = 1; i < segments.length; i++) {
    		
    		if(segments[i].start > lastPoint) {
    			lastPoint = segments[i].end;
    			pointList.add(lastPoint);   			
    		}
    	}
    	
        int[] points = new int[pointList.size()];
        for (int i = 0; i < pointList.size(); i++) {
            points[i] = pointList.get(i);
        }
        return points;
    }
    
    public static class segmentComparator implements Comparator<Segment> {
    	 
        @Override
        public int compare(Segment firstSegment, Segment secondSegment) {
           return (firstSegment.end - secondSegment.end);
        }
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        //int[] points = optimalPointsHaoyun(segments);
        int[] points = optimalPointsForLoop(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
