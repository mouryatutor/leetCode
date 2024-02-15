package count_number_connected_graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1,0},{2,0},{3,1},{3,2}};
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            adjList.add(i,new ArrayList<>());
            adjList.get(i).add(i);
        }
        
        for(int i = 0; i < edges.length; i++)
        {
            adjList.get(edges[i][0]).add(edges[i][1]);
            adjList.get(edges[i][1]).add(edges[i][0]);
        }

        int connnectedGraphs = 0 ;
        System.out.println(adjList.toString());

        for(int  i = 0; i < n; i++)
        {
            if(visited[i] == false)
            {
                ArrayList<Integer> visitedNodes = BfsTraversal(adjList,visited,i);
                System.out.println(visitedNodes.toString());
                if(isConnected(adjList, visitedNodes))
                {
                    connnectedGraphs++;
                }
            }
        }

        System.out.println("connnectedGraphs " + connnectedGraphs);

    }

    private static boolean isConnected(ArrayList<ArrayList<Integer>> adjList ,ArrayList<Integer> visitedNodes)
    {
        if(visitedNodes.size() == 0)
        {
            return false;
        }

        if(visitedNodes.size() == 1 || visitedNodes.size() == 2)
        {
            return true;
        }

        for(Integer node: visitedNodes)
        {
            if(adjList.get(node).containsAll(visitedNodes) == false)
            {
                return false;
            }
        }

        return true;
    } 

     private static ArrayList<Integer>  BfsTraversal(ArrayList<ArrayList<Integer>> adjList,boolean[] visited,int source)
    {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> visitedNodes = new ArrayList<>();
        queue.add(source);
        visited[source] = true;

        while (queue.isEmpty() == false) {
            
            int currentNode = queue.poll();
            visitedNodes.add(currentNode);

            for(Integer node: adjList.get(currentNode))
            {
                if(visited[node] == false)
                {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }

        return visitedNodes;
    }
}
