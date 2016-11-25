/*	MatrixGraph.java
*
*	Implements an adjacency matrix version of a graph
*	Implements the abstract class Graph
*	
*	You must submit this file
*/

import java.util.Scanner;

public class MatrixGraph extends Graph
{
	private int[][] adjMatrix;

	public MatrixGraph(Scanner input)
	{
		super(input);
		adjMatrix = new int[numVertices][numVertices];

		for(int i = 0; i < numVertices; i++)
		{
			String line = input.nextLine();
			if(line.equals(""))
			{
				i--;
				continue;
			}
			String[] edges = line.split(" ");
			for(int j = 1; j < edges.length; j++)
			{
				this.addEdge(i,Integer.parseInt(edges[j]));
			}
		}
	}

	public void addEdge(int u, int v)
	{
	}

	public boolean hasEdge(int u, int v)
	{
		return false;
	}

	public void removeEdge(int u, int v)
	{
	}

	LinkedList<Integer> getAdjacentVertices(int v) {
		return new LinkedList<Integer>();
	}

}