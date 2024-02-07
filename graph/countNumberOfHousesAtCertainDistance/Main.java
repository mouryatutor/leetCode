package countNumberOfHousesAtCertainDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    public static void main(String[] args) {
        
        // n , x  y;
        int n = 4;
        int x = 1;
        int y = 1;
        int[] minDistances = new int[n];
        Arrays.fill(minDistances, 0);

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>(n+1);
        adjList.add(null);
        // initialize adjList with n streets
        for(int i = 1; i <= n; i++)
        {
            adjList.add(new ArrayList<Integer>());
        }

        for(int i = 1; i <= n; i++)
        {
            if(i!=n)
            {
                adjList.get(i).add(i+1);
                adjList.get(i+1).add(i);
            }
        }
        adjList.get(x).add(y);
        adjList.get(y).add(x);
        System.out.println(adjList);


        for(int source = 1; source <= n; source++)
        {
            findMinimumDistancesFromHome1ToHome2UsingBFS(adjList,minDistances,n,source);
        }
        System.out.println();
        for(int i = 0; i < n; i++)
        {
            System.out.print(minDistances[i] + " ");
        }

    }

    private static void findMinimumDistancesFromHome1ToHome2UsingBFS(ArrayList<ArrayList<Integer>> adjList,int[] minDistances,int n,int source)
    {
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited,false);
        Queue<Integer> queue = new LinkedList<>();

        visited[source] = true;
        queue.add(source);
        int level = 0;
        while(!queue.isEmpty())
        {
            int size = queue.size();
            for(int i = 0; i < size; i++)
            {
                int currentHome = queue.poll();
            
                for(Integer otherHomes: adjList.get(currentHome))
                {
                    if(visited[otherHomes] == false)
                    {
                        visited[otherHomes] = true;
                        queue.add(otherHomes);
                    }
                }
            }
            minDistances[level] = minDistances[level] + queue.size();
            level++;
        }

    }

}
