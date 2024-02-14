package findchampiontwo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0,2},
                         {1,3},
                         {1,2}};
        boolean[] visited = new boolean[n];
        ArrayList<Integer> stronger = new ArrayList<>();
        ArrayList<Integer> weeker = new ArrayList<>();

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < n; i++)
        {
            adjList.add(new ArrayList<>());
        }

        for(int i = 0; i < edges.length; i++)
        {
            adjList.get(edges[i][0]).add(edges[i][1]);
        }


        for(int i = 0 ; i < n; i++)
        {
            Arrays.fill(visited, false);
            BfsTraversal(adjList,visited,i);
            if(checkAllVisitedOrNot(visited))
            {
                stronger.add(i);
            }else{
                weeker.add(i);
            }
        }

        System.out.println("Champion team : " + (stronger.size() == 1 ? stronger.get(0) : -1));

    }

    private static boolean checkAllVisitedOrNot(boolean[] visited)
    {
        for(Boolean isVisited: visited)
        {
            if(isVisited == false)
            {
                return false;
            }
        }
        return true;
    }

    private static void BfsTraversal(ArrayList<ArrayList<Integer>> adjList,boolean[] visited,int source)
    {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;

        while (queue.isEmpty() == false) {
            
            int currentNode = queue.poll();

            for(Integer node: adjList.get(currentNode))
            {
                if(visited[node] == false)
                {
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
    }
}
