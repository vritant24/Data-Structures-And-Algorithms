/*	ListGraph.java
*
*	Implements an Adjacency List version of a Graph
*	Implements the abstract class Graph
*
*	You must submit this file
*/

import java.util.Scanner;

public class ListGraph extends Graph
{

	private LinkedList<Integer>[] adjLists;

	@SuppressWarnings("unchecked")
	public ListGraph(Scanner input)
	{
		super(input);

		adjLists = new LinkedList[numVertices];

		for(int i = 0; i < numVertices; i++)
		{
			adjLists[i] = new LinkedList<Integer>();
		}

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
			adjLists[u].add(v);
	}

	public boolean hasEdge(int u, int v)
	{
		if(adjLists[u].contains(v)) {
			return true;
		}
		return false;
	}

	public void removeEdge(int u, int v)
	{
		if(hasEdge(u, v)) {
			adjLists[u].remove(v);
		}
	}


	LinkedList<Integer> getAdjacentVertices(int v) {
		LinkedList<Integer> temp = new LinkedList<>();
		
		for(int i = 0; i < adjLists.length; i++) {
			if(adjLists[i].contains(v)) {
				temp.add(i);
			}
		}
		return temp;
	}

}
