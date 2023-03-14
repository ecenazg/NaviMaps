//-----------------------------------------------------
// Title: Navi Maps class
// Description: This class is used for calculating closest point with using priority queue.
//-----------------------------------------------------
import java.util.*;
public class NaviMaps {

	// Since user is located (0,0) only sqrt(a2+b2) is enough
	public static double dist(Coordinate p1) {
		return Math.sqrt((p1.x*p1.x) +  (p1.y*p1.y));
	}

	// calculate distance and update each element
	public static void calculateDistancesFromCenter(List<Coordinate> cList) {
		for(Coordinate coord:cList) {
			double distance = dist(coord);
			coord.distanceFromCenter = distance;
		}
	}

	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		String allValue = scanner.nextLine();
		int k = Integer.parseInt(scanner.nextLine());
		
		// List store all coordinate values
		List<Coordinate> cList = new ArrayList<Coordinate>();
		
		
		// Remove all empty values and brackets
		// Since brackets are special character, I need to escape them
		allValue = allValue.replaceAll(" ", "").replaceAll("\\[", "").replaceAll("\\]", "");
		String[] values = allValue.split(",");
		for(int i=0;i<values.length;i+=2) {
			Coordinate coord = new Coordinate(Integer.parseInt(values[i]),Integer.parseInt(values[i+1]));
			cList.add(coord);
		}
		
		// Call calculate distance and update each distance from origin
		calculateDistancesFromCenter(cList);

		
		// Java 1.8 has lambda properties. This line sort all coordinates based on their distance
		// It is similar to compareTo method of Comparable interface
		Collections.sort(cList, (a,b)-> Double.compare(a.distanceFromCenter, b.distanceFromCenter));
		
		// BruteForce print
		print(cList, k);		
		
		// Task2
		task2(cList.subList(0, k),k);
		
		scanner.close();
	}

	private static void print(List<Coordinate> cList, int k) {
		// Printing results
		// Iterate to k-1 element
		// I add comma end of the coordinates.
		// I manually add last element
		String result = "[";
		for(int i=0;i<k-1;i++) {
			result+=cList.get(i)+",";
		}
		result+=cList.get(k-1) + "]";
				
		// Print stored result string
		System.out.println(result);
		
	}

	// Max capacity is k
	// Push all values to priority queue (heap)
	private static void task2(List<Coordinate> cList, int k) {
		PQ pqheap = new PQ(k);
		for(Coordinate coord:cList) {
			pqheap.enqueue(coord);
		}
		
		// Since it is a max heap and outputs should be ascending order
		// I need to reverse it.
		// So I used stack
		Stack<Coordinate> st = new Stack<>();
		while(pqheap.getSize()>0) {
			st.add(pqheap.dequeue());
		}
		
		List<Coordinate> retList = new ArrayList<>();
		
		// Reverse items
		while(st.size()>0) {
			retList.add(st.pop());
		}
		
		
		//print(retList,k);
		
	}

}