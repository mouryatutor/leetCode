import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) {
        
        int n = 3;
        int[][] edges = {{0,1},{1,2},{2,0}};
        int source = 0;
        int destination = 2;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();

        for(int i = 0; i < n; i++)
        {
            arrayList.add(i,new ArrayList<Integer>());
        }

        for(int i = 0; i < edges.length; i++)
        {
            arrayList.get(edges[i][0]).add(edges[i][1]);
            arrayList.get(edges[i][1]).add(edges[i][0]);
        }

        int s = source;
        visited[s] = true;
        pathGraphFinder(arrayList,visited,source);

        System.out.println();
        for(int i = 0; i < n; i++)
        {
            System.out.print(visited[i] + " ");
        }

        System.out.println("Path exists : " + visited[destination]);
    }

    private static void pathGraphFinder(ArrayList<ArrayList<Integer>> arrayList,boolean[] visited,int source)
    {
        visited[source] = true;

        for(int u : arrayList.get(source))
        {
            if(visited[u] == false)
            {
                pathGraphFinder(arrayList,visited,u);
            }
        }
    }
}
