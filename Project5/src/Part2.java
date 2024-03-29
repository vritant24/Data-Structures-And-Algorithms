import java.awt.Point;
import java.util.Arrays;
import java.util.Iterator;

import sun.awt.image.ImageWatched.Link;


/*		Part 2
 * 
 * 		TODO: Implement the following methods
 *              
 *      maxInDegree
 *      maxOutDegree
 *      hasOneCycle 
 * 		numEdgeTriangles
 * 		numTriangles
 * 		vertexClusterCoeff
 * 		globalClusterCoeff
 * 
 */
public class Part2 {
	

	// TODO:
	// Returns the maximum in-degree in the graph G
	public int maxInDegree(Graph G)
	{
		int length = G.getNumVertices();
		int[] array = new int[length];
		
		Iterator<Integer> iterator;
		
		for(int i = 0; i < length; i++) {
			iterator = G.getAdjacentVertices(i).iterator();
			while(iterator.hasNext()) {
				array[iterator.next()]++;
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < length; i++) {
			if(array[i] > max) {
				max = array[i];
			}
		}
		
		return max;
	}
	
	
	
	// TODO:
	// Returns the maximum out degree in the graph G
	public int maxOutDegree(Graph G)
	{
		int length = G.getNumVertices();
		int max = 0;
		int tempSize;
		
		for(int i = 0; i < length; i++) {
			tempSize = G.getAdjacentVertices(i).size();
			if(tempSize > max) {
				max = tempSize;
			}
		}
		
		return max;
	}

	
	
	// TODO:
	// Determines if a graph has only one cycle
	// Returns a linked list containing the vertices in the cycle if there was only one cycle
	// Returns an empty linked list if there are none or more than one cycle
	public LinkedList<Integer> hasOneCycle(Graph G)
	{
		int length = G.getNumVertices();
		boolean haveOne = false;
		int vertex = 0;
		boolean result;
		boolean[] array;
		
		for(int i = 0; i < length; i++) {
			array = new boolean[length];
			result = isCycle(G, i, 0, array, i);
			if(result && haveOne) {
				return new LinkedList<Integer>();
			}
			if(result) {
				vertex = i;
				haveOne = result;
			}
		}
		
		if(!haveOne) {
			return new LinkedList<Integer>();
		}
		return getCycle(G, vertex, vertex, new LinkedList<Integer>());
	}
	
	public boolean isCycle(Graph G, int vertex, int depth, boolean[] array, int end) {
		LinkedList<Integer> list = G.getAdjacentVertices(vertex);
		Iterator<Integer> iterator = list.iterator();
		
		if(depth > 0) {
			if(list.contains(end)) {
				return true;
			}
		}
		array[vertex] = true;
		
		boolean[] temp = Arrays.copyOf(array, array.length);
		
		if(vertex != end) {
			temp[vertex] = true;
		}
		while(iterator.hasNext()) {
			int next = iterator.next();
			if(next <= end) {
				continue;
			}
			if(!array[next]) {
				if(isCycle(G, next, depth + 1, temp, end)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public LinkedList<Integer> getCycle(Graph G, int vertex, int end, LinkedList<Integer> list ) {
		LinkedList<Integer> temp = G.getAdjacentVertices(vertex);
		Iterator<Integer> iterator = temp.iterator();
		if(temp.contains(end)) {
			list.add(vertex); 
			return list;
		}
		
		while(iterator.hasNext()) {
			getCycle(G, iterator.next(), end, list);
			if(list.size() > 0) {
				list.add(vertex);
				break;
			}
		}
		
		return list;
	}
	
	// TODO:
	// Returns the total number of triangles in the entire graph G that contain the edge (u,v)
	public int numEdgeTriangles(Graph G, int u, int v)
	{
		if(!G.hasEdge(u, v)) {
			return 0;
		}
		int counter = 0;
		LinkedList<Integer> listU = G.getAdjacentVertices(u);
		LinkedList<Integer> listV = G.getAdjacentVertices(v);
		Iterator<Integer> iterator = listU.iterator();
		
		while(iterator.hasNext()) {
			if(listV.contains(iterator.next())) {
				counter++;
			}
		}
		
		return counter;
	}
	

	// TODO:
	// Returns the total number of triangles in the graph
	public int numTriangles(Graph G)
	{
		int length = G.getNumVertices();
		int count = 0;
		
		for(int i = 0; i < length; i++) {
			for(int j = i; j < length; j++) {
				if(!G.hasEdge(i, j)) {
					continue;
				}
				for(int k = j; k < length; k++) {
					if(G.hasEdge(i, k) && G.hasEdge(j, k)) {
						count++;
					}
				}
			}
		}
		
		return count;
	}
	
	// TODO:
	// Returns the vertex clustering coefficient of v
	// The vertex clustering coefficient is the fraction of pairs of vertices
	// that are adjacent to v that are also adjacent to each other.
	public double vertexClusterCoeff(Graph G, int v)
	{
		LinkedList<Integer> list = G.getAdjacentVertices(v);
		Iterator<Integer> iterator = list.iterator();
		Iterator<Integer> iterator2;
		double denominator = calcDenom(list.size() - 1);
		double count = 0;
		
		if(denominator == 0) { return 0;}
		int next;
		int track = 0;
		
		while(iterator.hasNext()) {
			next = iterator.next();
			iterator2 = list.iterator();
			for(int i = 0; i < track; i++) {
				if(!iterator2.hasNext()) {
					break;
				}
				iterator2.next();
			}
			while(iterator2.hasNext()) {
				if(G.hasEdge(next, iterator2.next())) {
					count++;
				}
			}
			track++;
		}
		return count/denominator;
	}
	
	public double calcDenom(int a) {
		int count = 0;
		for(int i = 0; i <= a; i++) {
			count += i;
		}
		return count;
	}

	// TODO:
	// Returns the average of all n vertices clustering coefficients
	public double globalClusterCoeff(Graph G)
	{
		double count = 0;
		int length = G.getNumVertices();
		
		for(int i = 0; i < length; i++) {
			count += vertexClusterCoeff(G, i);
		}
		
		return count / (double)length;
	}
	
	
	
}
